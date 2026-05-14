package Task5;

import java.util.ArrayList;

public class DBManager {

    private Database database = new Database();

    public String saveSubmission(String data) {
        return database.saveSubmission(data);
    }

    public ArrayList<Reviewer> fetchReviewers() {
        return database.fetchReviewers();
    }

    public void saveScores(ArrayList<Integer> scores) {
        database.saveScores(scores);
    }
}
