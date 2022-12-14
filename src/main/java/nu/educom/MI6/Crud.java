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

    private void closeConn() {
        try {
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Agent readOneRow(String sql, String agentNumber) {
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
}
