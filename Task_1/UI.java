public class UI{
    public void submitResearchOutput(String data){
        SubmissionController submissionController = new SubmissionController();
        System.out.println("[SUBMITTING DATA:["+data+"] USING UI");
        submissionController.submit(data);
    }
    public void sendNotification() {
    System.out.println("[UI]: NOTIFICATION RECEIVED BY RESEARCHER.");
}

}