package nu.educom.MI6.Backup;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Agent {

    private int id;
    private String agentNumber;
    private boolean active;
    private Date licence_to_kill;
    private String personal_sentence;


    public Agent(ResultSet set) {
        try {
            id = set.getInt("id");
            agentNumber = set.getString("agent_number");
            personal_sentence = set.getString("personal_sentence");
            active = set.getBoolean("active");
            licence_to_kill = set.getDate("licence_to_kill");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPersonal_Sentence() {
        return personal_sentence;
    }

    public boolean getActive() {
        return active;
    }

    public void read() {
        System.out.println(String.format("id: %d", id));
        System.out.println(String.format("agentNumber: %s", agentNumber));
        System.out.println(String.format("personal_sentence: %s", personal_sentence));
        System.out.println(String.format("active: %s", active));
        System.out.println(String.format("license_to_kill: %s", licence_to_kill));
    }
}
