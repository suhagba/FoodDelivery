/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccessor;

import java.util.List;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author suhag
 */
public class UserDAOImpl implements UserDAO {

    DataSource ds;
    Connection conn;
    ResultSet rs;

    public UserDAOImpl() {
        try {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            ds = (DataSource) envCtx.lookup("myloDB");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> ret = new ArrayList<>();
        try {
//            this.conn = ds.getConnection(); // local testing
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING")); // server deployment. Server variable
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_detail;");
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                User user = new User(this.rs.getString("fname"), this.rs.getString("lname"),this.rs.getString("salutation") , this.rs.getString("email"),this.rs.getString("address"), this.rs.getString("pNumber"), this.rs.getString("pNumber2"));
                ret.add(user);
            }

            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public User getUserFromID(String email) {
        User ret = null;
        try {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_detail"
                    + " WHERE email = ?;");
            ps.setString(1, email);
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                User user = new User(this.rs.getString("fname"), this.rs.getString("lname"),this.rs.getString("salutation") , this.rs.getString("email"),this.rs.getString("address"), this.rs.getString("pNumber"), this.rs.getString("pNumber2"));
                ret = user;
            }

            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public boolean addUser(User user) {
        int rows = 0;
        try {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
            PreparedStatement ps = conn.prepareStatement("REPLACE INTO user_detail "
                    + "(email, salutation, fname, lname, address, pNumber, pNumber2) "
                    + "VALUES(?, ?, ?,?,?,?,?);");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getSalutation());
            ps.setString(3, user.getfName());
            ps.setString(4, user.getlName());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getpNumber());
            ps.setString(7, user.getpNumber2());

            rows = ps.executeUpdate();

            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }

    @Override
    public boolean updateUser(User user) {
        int rows = 0;
        try {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
            PreparedStatement ps = conn.prepareStatement("UPDATE user_detail SET "
                    + "salutation = ?, "
                    + "lname = ?, "
                    + "fname = ?,"
                    + "address = ?,"
                    + "pNumber = ?,"
                    + "pNumber2 = ?"
                    + "WHERE email = ?;");
            ps.setString(1, user.getSalutation());
            ps.setString(2, user.getlName());
            ps.setString(3, user.getfName());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getpNumber());
            ps.setString(6, user.getpNumber2());
            ps.setString(7, user.getEmail());
            rows = ps.executeUpdate();

            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}
