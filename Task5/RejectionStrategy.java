public class RejectionStrategy implements DecisionStrategy {
    @Override
    public void execute() {
        System.out.println("[NOTIFICATION]: SUBMISSION REJECTED!");
    }
}
