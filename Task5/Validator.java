public class Validator {

    public String validateFormat(String data) {
        if (data.equals("LegitResearchData")) {
            return "valid";
        } else {
            return "invalid";
        }
    }
}
