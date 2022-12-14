package nu.educom.MI6.Backup;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Model {
//    private static String CodeSentence = "For ThE Royal QUEEN";
    private static ArrayList<String> blacklist = new ArrayList<String>();
    private Map<String, String> errors = new HashMap<String, String>();
    private Crud crud;

    private IPresenter presenter;

    public Map<String, String> getErrors() {
        return errors;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Timer cdt = null;

    public Model(Crud crud, IPresenter presenter) {
        this.crud = crud;
        this.presenter = presenter;
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
            agentNumber =  "0".repeat(3 - lengInput) + agentNumber;
            crud.createLoginRow(agentNumber, false);
            return false;
        }
        if (Integer.parseInt(agentNumber) < 1 || Integer.parseInt(agentNumber) > 956) {
            errors.put("Validation", String.format("Agent number %s is not in defined range.", agentNumber));
            agentNumber =  "0".repeat(3 - lengInput) + agentNumber;
            crud.createLoginRow(agentNumber, false);
            return false;
        }
        
        if (lengInput < 3) {
            agentNumber =  "0".repeat(3 - lengInput) + agentNumber;
            //System.out.println(agentNumber);
        }

        List<LoginAttempt> rows = crud.getLastLoginAttempts(agentNumber);
        int potTimeout = 0;
        String lastDate = null;
        try {
            lastDate = rows.get(0).getDate();

        } catch (Exception e) {
            System.out.println(e);
        }
        int size = rows.size();
        if (size != 0) {
            potTimeout = (int)Math.pow(2, size);
        } else {
            potTimeout = 1;
        }

//        if (!this.validateTime(lastDate, potTimeout)) {
//            errors.put("Validation", "TIMED OUT");
//            control.setIntervalValue(potTimeout * 60);
//            return false;
//        }
        
       if (blacklist.contains(agentNumber)) {
            errors.put("Validation", "ACCESS DENIED");
            return false;
        }
        
        //if (sentence == null) {System.exit(0);}
        Agent agent = crud.readOneAgentRow(agentNumber);
        if(agent == null) {
            errors.put("Validation", "ACCESS DENIED");
            crud.createLoginRow(agentNumber, false);
            return false;
        }
        String personal_sentence = agent.getPersonal_Sentence();
        if(!sentence.equals(personal_sentence)) {
            errors.put("Validation", "ACCESS DENIED");
            crud.createLoginRow(agentNumber, false);
            blacklist.add(agentNumber);
            return false;
        }

        if(!agent.getActive()) {
            errors.put("Validation", "NOT ACTIVE");
            crud.createLoginRow(agentNumber, false);
            blacklist.add(agentNumber);
            return false;
        }
        crud.createLoginRow(agentNumber, true);
        return true;
    }

    private boolean validateTime(String lastDate,int potTimeout) {
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
//        System.out.println(String.format("Potential timeout: %s", potTimeout));
        if(lastDate == null) {
            return true;
        }

        int lastDay = Integer.parseInt(lastDate.substring(8, 10));
        int currentDay = Integer.parseInt(currentTime.substring(8, 10));
        int lastHours = Integer.parseInt(lastDate.substring(11, 13));
        int currentHours = Integer.parseInt(currentTime.substring(11, 13));
        int lastMinutes = Integer.parseInt(lastDate.substring(14, 16));
        int currentMinutes = Integer.parseInt(currentTime.substring(14, 16));
        int newMinutes = lastMinutes + potTimeout;
        while (newMinutes > 60) {
            newMinutes -= 60;
            lastHours += 1;
            if(lastHours > 24) {
                lastHours -= 24;
                lastDay += 1;
            }
        }
        if(lastDay <= currentDay) {
            if(lastHours <= currentHours) {
                if(newMinutes <= currentMinutes) {

                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}

