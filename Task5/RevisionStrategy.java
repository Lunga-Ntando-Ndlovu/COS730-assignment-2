public class RevisionStrategy implements DecisionStrategy {
    @Override
    public void execute() {
        System.out.println("[NOTIFICATION]: REVISION REQUIRED!");
    }
}
