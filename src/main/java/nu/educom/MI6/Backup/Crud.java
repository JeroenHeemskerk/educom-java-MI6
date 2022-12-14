package nu.educom.MI6.Backup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Crud {

    private DateTimeFormatter dtf   = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Connection conn;
    private String url              = "jdbc:mysql://127.0.0.1:3306/MI6";
    private String user             = "user_MI6";
    private String password         = "sHiEINkFyQuDdOuL";

    public Connection openConn() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Agent readOneAgentRow(String agentNumber) {
        String sql = "SELECT * FROM agents WHERE agent_number = ?";
        PreparedStatement sqlPrep = null;
        ResultSet rs = null;
        Agent agent = null;
        this.openConn();

        try {
            sqlPrep = conn.prepareStatement(sql);
            sqlPrep.setString(1, agentNumber);
            rs = sqlPrep.executeQuery();
            if(rs.next())
            {
                agent = new Agent(rs);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return agent;
    }

    public void createLoginRow(String agentNumber, boolean login_success) {
        String sql = "INSERT INTO login_attempts (agent_number, login_success) VALUES (?, ?);";
        PreparedStatement sqlPrep = null;
        this.openConn();

        try {
            sqlPrep = conn.prepareStatement(sql);
            sqlPrep.setString(1, agentNumber);
            sqlPrep.setBoolean(2, login_success);
            sqlPrep.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<LoginAttempt> getLastLoginAttempts(String agentNumber) {
        List<LoginAttempt> output = new ArrayList<LoginAttempt>();
        String sql = "SELECT * FROM login_attempts WHERE agent_number = ? AND id > (SELECT id FROM login_attempts WHERE login_success = true  AND agent_number = ? ORDER BY id DESC LIMIT 1) ORDER BY id DESC;";
        PreparedStatement sqlPrep = null;
        ResultSet rs = null;

        this.openConn();

        try {
            sqlPrep = conn.prepareStatement(sql);
            sqlPrep.setString(1, agentNumber);
            rs = sqlPrep.executeQuery();
            while(rs.next())
            {
                output.add(new LoginAttempt(rs));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return output;

    }
}
