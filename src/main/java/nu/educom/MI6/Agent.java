package nu.educom.MI6;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Agent {

    private int id = 0;
    private String agentNumber = null;
    private boolean active = false;
    private Date licence_to_kill = null;
    private String personal_sentence = null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLicence_to_kill() {
        return licence_to_kill;
    }

    public void setLicence_to_kill(Date licence_to_kill) {
        this.licence_to_kill = licence_to_kill;
    }

    public String getPersonal_sentence() {
        return personal_sentence;
    }

    public void setPersonal_sentence(String personal_sentence) {
        this.personal_sentence = personal_sentence;
    }
}
