import java.util.ArrayList;

public class EvaluationManager {

    public void startEvaluation() {
        System.out.println("---------------EVALUATION STARTING---------------");
    }

    public ArrayList<Integer> evaluationScores = new ArrayList<>();
    public Database database = new Database();
    public String consensus;
    public double calculatedAverage;

    public void submitScore(Integer score) {
        evaluationScores.add(score);

        database.saveScore(score);
        System.out.println("[SCORE SAVED]\n-------------------------\n");

    }

    public void calculateAverage() {
        System.out.println("\n------------------CALCULATING AVERAGE SOCRE -----------------");
        if (evaluationScores.isEmpty()) {
            System.out.println("No scores to evaluate.");
            return;
        }

        int sum_up = 0;
        for (int score : evaluationScores) {
            sum_up += score;
        }

        calculatedAverage = (double) sum_up / evaluationScores.size();
        System.out.println("AVERAGE SCORE: " + calculatedAverage);
    }

    public void checkConsensus() {
        System.out.println("\n------------------CHECKING CONCENSUS-----------------");
        if (calculatedAverage > 7) {
            consensus = "accepted";
            System.out.println("{CONCENSUS}: SUBMISSION ACCPETED - {notifying}");
        } else if (calculatedAverage > 3) {
            consensus = "revision";
            System.out.println("{CONCENSUS}: REVISION REQUIRED - {notifying}");
        } else {
            consensus = "rejected";
            System.out.println("{CONCENSUS}: SUBMISSION REJECTED - {notifying}");
        }
        System.out.println("\n------------------CHECKING CONCENSUS-----------------");
    }

    public void applyRules() {
        NotificationService notificationService = new NotificationService();
        switch (consensus) {
            case "accepted" -> notificationService.notifyAcceptance();
            case "revision" -> notificationService.notifyRevision();
            default -> notificationService.notifyRejection();
        }
    }

}
