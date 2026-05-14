package Task_1;

import java.util.ArrayList;
import java.util.Iterator;

public class SubmissionController {

    public void submit(String data) {
        Validator validator = new Validator();
        if (validator.validateFormat(data).equals("invalid")) {
            throw new Error("ERROR: Invalid String Format");
        } else {
            Database database = new Database();
            database.saveSubmission(data);

            ReviewerManager reviewerManager = new ReviewerManager();
            ArrayList<Reviewer> filteredReviwers = reviewerManager.getAvailableReviewers();

            Iterator<Reviewer> itRev = filteredReviwers.iterator();
            System.out.println("---------ASSIGNING REVIEWERS: ----------");
            while (itRev.hasNext()) {
                Reviewer reviewer = itRev.next();
                reviewer.assignReview();
                System.out.println("{ASSIGNED REVIEWER }:" + reviewer.reviwerName);
            }
            System.out.println("-----------------------------------\n");
            EvaluationManager evaluationManager = new EvaluationManager();
            evaluationManager.startEvaluation();
            for (Reviewer reviewer : filteredReviwers) {
                evaluationManager.submitScore(reviewer.score);
            }

            evaluationManager.calculateAverage();
            evaluationManager.checkConsensus();
            evaluationManager.applyRules();
        }

    }
}
