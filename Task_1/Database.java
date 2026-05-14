package Task_1;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    public ArrayList<String> submissionData  = new ArrayList<>();;
    public ArrayList<Reviewer> reviewerList;
    public ArrayList<Integer> scoreList  = new ArrayList<>();;
    public ArrayList<Reviewer> fetchReviewers() {
    ArrayList<Reviewer> presentReviewers = new ArrayList<>();
        
    Random rand = new Random();
    int reviewerCount = rand.nextInt(51); // 0–9 inclusive

    for (int i = 1; i <= reviewerCount; i++) {
        Reviewer r = new Reviewer();
        r.reviwerName = "Reviewer_" + i;
        r.isBusy = rand.nextBoolean();       
        r.hasConflict = rand.nextBoolean(); 
        presentReviewers.add(r); 
        System.err.println("[FETCHED REVIEWER {"+r.reviwerName+"}"+"| HAS_CONFLICTS:"+(r.hasConflict==true?"YES":"NO")+ "| BUSY_WORKLOAD:"+(r.isBusy==true?"YES":"NO"));
    }
        return presentReviewers;
    }

    public String saveSubmission(String data) {
        submissionData.add(data);
        return "\n-------------------------------|SUBMISSION SAVED SUCCESSFULLY|\n------------------------------\n";
    }

    public void saveScore(Integer score) {
        scoreList.add(score);
    }

}