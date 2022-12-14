package nu.educom.MI6.Backup;

import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginAttempt {
    private int id;
    private String agent_number;
    private String date;
    private boolean login_success;

    public LoginAttempt(ResultSet rs) {
        try {
            id = rs.getInt("id");
            agent_number = rs.getString("agent_number");
            date = rs.getString("time_login").replace('-', '/');
            login_success = rs.getBoolean("success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public String getAgent_number() {
        return agent_number;
    }

    public String getDate() {
        return date;
    }

    public boolean isLogin_success() {
        return login_success;
    }

    public void read() {
        System.out.println(String.format("id: %d", id));
        System.out.println(String.format("agent_number: %s", agent_number));
        System.out.println(String.format("date: %s", date));
        System.out.println(String.format("login_success: %s \n\r", login_success));
    }
}
