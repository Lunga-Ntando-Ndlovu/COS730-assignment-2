package Task5;

public class NotificationService {

    private UI ui;

    public NotificationService(UI ui) {
        this.ui = ui;
    }

    // Single entry point - Strategy handles which notification fires
    public void notifyDecision(DecisionStrategy strategy) {
        strategy.execute();
        ui.sendNotification();
    }
}
