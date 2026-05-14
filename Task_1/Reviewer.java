import java.util.Random;

public class Reviewer {
    public String reviwerName;
    public boolean isBusy;
    public boolean hasConflict;
    public boolean assignedToReviwew = false;
    public Integer score = 0;

    
    public void assignReview(){
        assignedToReviwew = true;
        score = new Random().nextInt(10)+1;
    };
}


