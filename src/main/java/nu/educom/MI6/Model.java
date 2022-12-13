package nu.educom.MI6;

import java.util.*;;

public class Model {
    private static String CodeSentence = "For ThE Royal QUEEN";
    private static ArrayList<String> blacklist = new ArrayList<String>();
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean validateLogin(String agentNumber, String sentence){
        try{
            Integer.parseInt(agentNumber);
            //if (agentNumber == null) {System.exit(0);}
        } catch (Exception e) {
            errors.put("Validation", "Only numbers are allowed.");
            return false;
        }
        
        int lengInput = agentNumber.length();
        if (lengInput > 3) {
            errors.put("Validation", String.format("Agent number %s is wrong", agentNumber));
            return false;
        }
        if (Integer.parseInt(agentNumber) < 1 || Integer.parseInt(agentNumber) > 956) {
            errors.put("Validation", String.format("Agent number %s is not in defined range.", agentNumber));
            return false;
        }
        
        if (lengInput < 3) {
            agentNumber =  "0".repeat(3 - lengInput) + agentNumber;
            //System.out.println(agentNumber);
        }
        
       if (blacklist.contains(agentNumber)) {
            errors.put("Validation", "ACCESS DENIED");
            return false;
        }
        
        //if (sentence == null) {System.exit(0);}

        if (!sentence.equals(CodeSentence)){
            errors.put("Validation", "ACCESS DENIED");
            blacklist.add(agentNumber);
            return false;
        }

        return true;
    }
}

