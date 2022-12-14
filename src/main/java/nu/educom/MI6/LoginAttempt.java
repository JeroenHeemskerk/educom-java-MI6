package nu.educom.MI6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LoginAttempt {
    private int id = 0;
    private String agent_number = null;
    private LocalDateTime date = null;
    private boolean login_success = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgent_number() {
        return agent_number;
    }

    public void setAgent_number(String agent_number) {
        this.agent_number = agent_number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isLogin_success() {
        return login_success;
    }

    public void setLogin_success(boolean login_success) {
        this.login_success = login_success;
    }
}
