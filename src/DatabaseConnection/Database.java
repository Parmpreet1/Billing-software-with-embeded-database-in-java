/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Parm sohi
 */
public class Database{
    private final String current_dir=System.getProperty("user.dir");
    private final String url="jdbc:derby:"+current_dir+"\\App;create=true;SCHEMA=App";
    private final String username="";
    private final String password="";
    
    Connection con;
    
    public Connection connect(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Driver Loaded");
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection Estblished");
            
       
       
     
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver not Loaded"+e);
        }
        catch(SQLException e){
            System.out.println("Connection Failed"+e);
        }
        return con;
    }
    public  ResultSet executeQuery(String sql){
       
        ResultSet rs = null;
        try {
            Statement stmt=con.createStatement();
                        
            rs=stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            System.out.println("get state "+ex.getSQLState());
            System.out.println("get message "+ex.getMessage());
            System.out.println("get error code "+ex.getErrorCode());
        }
    
        return rs;
    }
    public void executeBatch(String sql,String sql1){
        
        try {
            Statement stmt=con.createStatement();
            //Statement stmt1=con.createStatement();
            stmt.addBatch(sql);
            stmt.addBatch(sql1);
            stmt.executeBatch();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void executeUpdate(String sql){
        
        try {
            Statement stmt=con.createStatement();
            
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
           
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void closecon(){
        try{
            con.close();
            System.out.println("Connection close");
        }
        catch(SQLException e){
            System.out.println("Connection not close"+e);
        }
    }
    
      
}

