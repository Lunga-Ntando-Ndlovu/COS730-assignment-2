package Task5;

import java.util.ArrayList;
import java.util.Iterator;

public class ReviewerManager {

    private DBManager dbManager;

    public ReviewerManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    // Single call to DBManager — no direct Database coupling
    public ArrayList<Reviewer> getAvailableReviewers() {
        ArrayList<Reviewer> reviewerList = dbManager.fetchReviewers();
        return filterReviewers(reviewerList);
    }

    // Collapsed filterConflicts + checkWorkload into one method
    public ArrayList<Reviewer> filterReviewers(ArrayList<Reviewer> reviewerList) {
        System.out.println("\n--------- FILTERING REVIEWERS (conflicts + workload) ---------");
        Iterator<Reviewer> it = reviewerList.iterator();
        while (it.hasNext()) {
            Reviewer r = it.next();
            if (r.hasConflict || r.isBusy) {
                System.out.println("{REMOVING}: " + r.reviewerName
                    + " | conflict=" + r.hasConflict + " busy=" + r.isBusy);
                it.remove();
            }
        }
        System.out.println("--------------------------------------------------------------\n");
        return reviewerList;
    }

    // ReviewerManager owns the assignment loop - SubmissionController no longer iterates
    public boolean assignAllReviews(ArrayList<Reviewer> filteredReviewers) {
        if (filteredReviewers.isEmpty()) return false;
        System.out.println("--------- ASSIGNING REVIEWERS ----------");
        for (Reviewer r : filteredReviewers) {
            r.assignReview();
        }
        System.out.println("----------------------------------------\n");
        return true;
    }
}
