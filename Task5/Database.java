package Task5;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    public ArrayList<String> submissionData = new ArrayList<>();
    public ArrayList<Integer> scoreList = new ArrayList<>();

    public ArrayList<Reviewer> fetchReviewers() {
        ArrayList<Reviewer> presentReviewers = new ArrayList<>();
        Random rand = new Random();
        int reviewerCount = rand.nextInt(51);

        for (int i = 1; i <= reviewerCount; i++) {
            Reviewer r = new Reviewer();
            r.reviewerName = "Reviewer_" + i;
            r.isBusy = rand.nextBoolean();
            r.hasConflict = rand.nextBoolean();
            presentReviewers.add(r);
            System.err.println("[DB FETCHED]: " + r.reviewerName
                + " | HAS_CONFLICT: " + (r.hasConflict ? "YES" : "NO")
                + " | BUSY: " + (r.isBusy ? "YES" : "NO"));
        }
        return presentReviewers;
    }

    public String saveSubmission(String data) {
        submissionData.add(data);
        return "\n-------------------------------|SUBMISSION SAVED SUCCESSFULLY|------------------------------\n";
    }

    // Batch save - single call instead of one per score
    public void saveScores(ArrayList<Integer> scores) {
        scoreList.addAll(scores);
        System.out.println("[DB]: Batch saved " + scores.size() + " score(s) successfully.");
    }
}
