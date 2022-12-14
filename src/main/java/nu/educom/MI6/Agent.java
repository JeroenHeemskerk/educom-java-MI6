package nu.educom.MI6;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Agent {

    private int id;
    private String agentNumber;
    private boolean active;
    private Date licence_to_kill;


    public Agent(ResultSet set) {
        try {
            id = set.getInt("id");
            agentNumber = set.getString("agent_number");
            active = set.getBoolean("active");
            licence_to_kill = set.getDate("licence_to_kill");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read() {
        System.out.println(String.format("id: %d", id));
        System.out.println(String.format("agentNumber: %s", agentNumber));
        System.out.println(String.format("active: %s", active));
        System.out.println(String.format("license_to_kill: %s", licence_to_kill));
    }
}
