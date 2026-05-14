package Task5;

public class RevisionStrategy implements DecisionStrategy {
    @Override
    public void execute() {
        System.out.println("[NOTIFICATION]: REVISION REQUIRED!");
    }
}
