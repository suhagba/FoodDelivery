/*
 * This code is the property of mylo
 * The developers of this project own full rights to this code  * 
 */
package dbAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Login;

/**
 *
 * @author Ninad
 */

public class LoginDAOImpl implements LoginDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public LoginDAOImpl()
    {
        try
        {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context)initCtx.lookup("java:comp/env");
//            ds = (DataSource)envCtx.lookup("myloDB");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Login> getAll()
    {
        List<Login> ret = new ArrayList<>();
        try
        {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM login;");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Login login = new Login();
                login.setId(this.rs.getString("idlogin"));
                login.setHash(this.rs.getString("idpass"));
                login.setSalt(this.rs.getString("salt"));
                ret.add(login);
            }
            
            if (rs != null)
            {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Login getLoginFromID(String idlogin)
    {
        Login ret = null;
        try
        {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
//            this.conn = DriverManager.getConnection("jdbc:mysql://ec2-52-198-27-52.ap-northeast-1.compute.amazonaws.com:3306/mylodb_main?user=mylo&password=mypass");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM login"
                    + " WHERE idlogin = ?;");
            ps.setString(1, idlogin);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Login login = new Login();
                login.setId(this.rs.getString("idlogin"));
                login.setHash(this.rs.getString("idpass"));
                login.setSalt(this.rs.getString("salt"));
                ret = login;
            }
            
            if (rs != null)
            {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean addLogin(Login login)
    {
        int rows = 0;
        try
        {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
//            this.conn = DriverManager.getConnection("jdbc:mysql://ec2-52-198-27-52.ap-northeast-1.compute.amazonaws.com:3306/mylodb_main?user=mylo&password=mypass");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO login "
                    + "(idlogin, idpass, salt) "
                    + "VALUES(?, ?, ?);");
            ps.setString(1, login.getId());
            ps.setString(2, login.getHash());
            ps.setString(3, login.getSalt());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updatePass(Login login)
    {
        int rows = 0;
        try
        {
//            this.conn = ds.getConnection();
            this.conn = DriverManager.getConnection(System.getProperty("JDBC_CONNECTION_STRING"));
//             this.conn = DriverManager.getConnection("jdbc:mysql://ec2-52-198-27-52.ap-northeast-1.compute.amazonaws.com:3306/mylodb_main?user=mylo&password=mypass");
            PreparedStatement ps = conn.prepareStatement("UPDATE login SET "
                    + "idpass = ?, "
                    + "salt = ? "
                    + "WHERE idlogin = ?;");
            ps.setString(1, login.getHash());
            ps.setString(2, login.getSalt());   
            ps.setString(3, login.getId());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}