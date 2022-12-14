package nu.educom.MI6;

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
    private Connection conn = null;
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
        Agent agent = null;
        boolean check = false;
        this.openConn();

        try {
            PreparedStatement sqlPrep = conn.prepareStatement(sql);
            sqlPrep.setString(1, agentNumber);
            ResultSet rs = sqlPrep.executeQuery();
            agent = new Agent();
            if(rs.next())
            {
                check = true;
                agent.setId(rs.getInt("id"));
                agent.setAgentNumber(rs.getString("agent_number"));
                agent.setPersonal_sentence(rs.getString("personal_sentence"));
                agent.setActive(rs.getBoolean("active"));
                agent.setLicence_to_kill(rs.getDate("licence_to_kill"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return check ? agent : null;
    }

    public void createLoginRow(String agentNumber, boolean login_success) {
        String sql = "INSERT INTO login_attempts (agent_number, time_login, login_success) VALUES (?, ?,?);";
        PreparedStatement sqlPrep = null;
        this.openConn();
        var now = LocalDateTime.now();

        try {
            sqlPrep = conn.prepareStatement(sql);
            sqlPrep.setString(1, agentNumber);
            sqlPrep.setString(2, dtf.format(now));
            sqlPrep.setBoolean(3, login_success);
            sqlPrep.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<LoginAttempt> getLastLoginAttempts(String agentNumber) throws SQLException {
        // Find the latest successful login-attempt
        String subquery         = "SELECT MAX(time_login) FROM login_attempts WHERE agent_number = ? AND login_success=1";
        // Combine with main query of finding the id of said login-attempt
        String query            = String.format("SELECT id as 'max_id' FROM login_attempts WHERE agent_number = ? AND time_login=(%s)", subquery);

        this.openConn();
        //System.out.println("test123");

        PreparedStatement ps    = conn.prepareStatement(query);
        ps.setString(1, agentNumber);
        ps.setString(2, agentNumber);
        ResultSet rs            = ps.executeQuery();

        // If there is no previous successful login, set value to 0
        int lastSuccess;
        try {
            rs.next();
            lastSuccess = rs.getInt("max_id");}
        catch (Exception e) {lastSuccess = 0;}

        query                   = "SELECT * FROM login_attempts WHERE agent_number= ?";
        if (lastSuccess > 0) {  // ^ SELECT ALL if there is no lastSuccess, otherwise v
            query               = String.format("SELECT * FROM login_attempts WHERE agent_number= ? AND id >= %s", lastSuccess);
        }

        ps                      = conn.prepareStatement(query);
        ps.setString(1, agentNumber);
        rs                      = ps.executeQuery();
        boolean check           = false;

        List<LoginAttempt> loginAttempts = new ArrayList<LoginAttempt>();

        while (rs.next()) {
            System.out.println("testasbs");
            check = true;
            var la = new LoginAttempt();
            la.setId(rs.getInt("id"));
            System.out.println("test1");
            la.setAgent_number(rs.getString("agent_number"));
            System.out.println("test2");
            la.setDate(LocalDateTime.parse(rs.getString("time_login"), dtf));
            System.out.println("test3");
            la.setLogin_success(rs.getBoolean("login_success"));
            loginAttempts.add(la);
            System.out.println("test4");
        }
        System.out.println("testtothier");
        conn.close();

        // If there are no records, return null
        return check ? loginAttempts : null;
    }
//        List<LoginAttempt> output = new ArrayList<LoginAttempt>();
//        String sql = "SELECT * FROM login_attempts WHERE agent_number = ? AND id > (SELECT id FROM login_attempts WHERE login_success = true  ORDER BY id DESC LIMIT 1) ORDER BY id DESC;";
//        PreparedStatement sqlPrep = null;
//        ResultSet rs = null;
//
//        this.openConn();
//
//        try {
//            sqlPrep = conn.prepareStatement(sql);
//            sqlPrep.setString(1, agentNumber);
//            rs = sqlPrep.executeQuery();
//            while(rs.next())
//            {
//                output.add(new LoginAttempt(rs));
//            }
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(output);
//
//        return output;
//
//    }
}
