import java.util.ArrayList;

public class EvaluationManager {

    private ArrayList<Integer> evaluationScores = new ArrayList<>();
    private DBManager dbManager;
    private NotificationService notificationService;
    private double calculatedAverage;
    private String consensus;

    public EvaluationManager(DBManager dbManager, NotificationService notificationService) {
        this.dbManager = dbManager;
        this.notificationService = notificationService;
    }

    public void startEvaluation() {
        System.out.println("[EVALUATION MANAGER]: Evaluation started.");
    }

    // Scores are now collected in memory — no individual DB write per score
    public void submitScore(Integer score) {
        evaluationScores.add(score);
        System.out.println("[SCORE RECEIVED]: " + score);
    }

    // Collapsed calculateAverage + checkConsensus + applyRules into one method
    public void evaluateResearchOutput() {
        System.out.println("\n---------- EVALUATING RESEARCH OUTPUT ----------");

        if (evaluationScores.isEmpty()) {
            System.out.println("[ERROR]: No scores to evaluate.");
            return;
        }

        // calculateAverage
        int sum = 0;
        for (int score : evaluationScores) sum += score;
        calculatedAverage = (double) sum / evaluationScores.size();
        System.out.println("[AVERAGE SCORE]: " + calculatedAverage);

        // checkConsensus
        if (calculatedAverage > 7) {
            consensus = "accepted";
        } else if (calculatedAverage > 3) {
            consensus = "revision";
        } else {
            consensus = "rejected";
        }
        System.out.println("[CONSENSUS]: " + consensus.toUpperCase());

        // Batch save all scores in a single DB call
        dbManager.saveScores(evaluationScores);

        // applyRules via Strategy — explicit result passed to notifyDecision
        DecisionStrategy strategy = switch (consensus) {
            case "accepted" -> new AcceptanceStrategy();
            case "revision"  -> new RevisionStrategy();
            default          -> new RejectionStrategy();
        };
        notificationService.notifyDecision(strategy);

        System.out.println("---------- EVALUATION COMPLETE ----------\n");
    }
}
