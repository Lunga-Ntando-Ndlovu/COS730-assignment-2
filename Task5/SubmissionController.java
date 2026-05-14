package Task5;

import java.util.ArrayList;

public class SubmissionController {

    public void submit(String data, UI ui) {

        // Validate
        Validator validator = new Validator();
        if (validator.validateFormat(data).equals("invalid")) {
            System.out.println("[ERROR]: Invalid submission format.");
            return;
        }

        // All DB access goes through DBManager
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.saveSubmission(data));

        // Get filtered reviewers — ReviewerManager handles fetch + filter internally
        ReviewerManager reviewerManager = new ReviewerManager(dbManager);
        ArrayList<Reviewer> filteredReviewers = reviewerManager.getAvailableReviewers();

        // Guard: no eligible reviewers
        if (filteredReviewers.isEmpty()) {
            System.out.println("[ERROR]: No eligible reviewers available.");
            return;
        }

        // ReviewerManager owns the assignment loop — returns boolean confirmation
        boolean assigned = reviewerManager.assignAllReviews(filteredReviewers);
        if (!assigned) {
            System.out.println("[ERROR]: Reviewer assignment failed.");
            return;
        }

        // NotificationService needs UI reference so it can call sendNotification()
        NotificationService notificationService = new NotificationService(ui);
        EvaluationManager evaluationManager = new EvaluationManager(dbManager, notificationService);
        evaluationManager.startEvaluation();

        // Each reviewer submits their score to EvaluationManager (scores held in memory)
        for (Reviewer reviewer : filteredReviewers) {
            evaluationManager.submitScore(reviewer.score);
        }

        // Single method: calculate + consensus + batch save + notify
        evaluationManager.evaluateResearchOutput();
    }
}
