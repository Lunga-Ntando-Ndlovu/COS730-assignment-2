public class UI {

    public void submitResearchOutput(String data) {
        System.out.println("[UI]: Submitting data: [" + data + "]");
        SubmissionController submissionController = new SubmissionController();
        submissionController.submit(data, this);
    }

    // Called by NotificationService at the end of the evaluation flow
    public void sendNotification() {
        System.out.println("[UI]: Researcher has been notified.");
    }
}
