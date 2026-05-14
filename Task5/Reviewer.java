import java.util.Random;

public class Reviewer {

    public String reviewerName;
    public boolean isBusy;
    public boolean hasConflict;
    public boolean assignedToReview = false;
    public Integer score = 0;

    public void assignReview() {
        assignedToReview = true;
        score = new Random().nextInt(10) + 1;
        System.out.println("[REVIEWER: " + reviewerName + "]: Assigned with score " + score);
    }
}
