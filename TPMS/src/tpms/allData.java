/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class allData {
    
    
    public void createDatabase(){
    String urt= "jdbc:mysql://localhost:3306/TPMS";
    
        try {
            Connection conne= DriverManager.getConnection(urt, "root", "");
            Statement stmt= conne.createStatement();
            
            String passwordTable="CREATE TABLE PASSWORDS"+
                    "(BADGE VARCHAR(20),PASSWORD VARCHAR(20))";
            stmt.executeUpdate(passwordTable);
            String employeeList="CREATE TABLE OFFICERS"+
                    "(NAME VARCHAR(20),ID VARCHAR(9),BADGE VARCHAR(9),CONTACT VARCHAR(20))";
            stmt.executeUpdate(employeeList);
            String locationsTable="CREATE TABLE LOCATIONS"+
                    "(NAME VARCHAR(20),BADGE VARCHAR(9),DATE VARCHAR(20), LOCATION VARCHAR(20),SHIFT VARCHAR(9))";
            stmt.executeUpdate(locationsTable);
          
            String CREATE_TABLE_ADMIN="CREATE TABLE ADMIN"+
                        "(NAME VARCHAR(30),PASSWORD VARCHAR(20))";
            stmt.executeUpdate(CREATE_TABLE_ADMIN);              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }  
    }
    public void connectDataBase(){
    String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
 public void intoAdmin(String name, String password){
   String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("INSERT INTO ADMIN VALUES('"+name+"','"+password+"')");
            JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 }
 public void intoPasswords(String badge, String password){
 
    String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("INSERT INTO PASSWORDS VALUES('"+badge+"','"+password+"')");
            JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 }
 public void intoOfficers(String name, String id, String badge, String contact){
    String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("INSERT INTO OFFICERS VALUES('"+name+"','"+id+"','"+badge+"','"+contact+"')");
            JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 }
 
 public void intoLocations(String name, String badge,String date, String location, String shift){
    String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("INSERT INTO LOCATIONS VALUES('"+name+"','"+badge+"','"+date+"','"+location+"','"+shift+"')");
            JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 }
 public void newUser(String badge){
 
   String activityTable="CREATE TABLE "+badge+"(DATE VARCHAR(20), LOCATION VARCHAR(20), SHIFT  VARCHAR(9))";
           //"CREATE TABLE "+badge+"(DATE VARCHAR(20), LOCATION VARCHAR(20), SHIFT VARCHAR(9))";
                     
     String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate(activityTable);
            JOptionPane.showMessageDialog(null, "NEW USER CREATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
  
 }
 
 public void deleteUser(String badge){
      String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("DROP TABLE "+badge);
            JOptionPane.showMessageDialog(null, " USER DELETED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 
 }
 
 public void deleteFromPasswords(String badge){
      String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
           sqlquery.executeUpdate("DELETE  FROM  PASSWORDS WHERE BADGE='"+badge+";'");
            JOptionPane.showMessageDialog(null, " USER DELETED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void deleteFromLocations(String badge){
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("DELETE  FROM LOCATIONS WHERE BADGE='"+badge+";'");
            JOptionPane.showMessageDialog(null, " USER DELETED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void deleteFromOfficers(String badge){
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("DELETE  FROM OFFICERS WHERE BADGE='"+badge+";'");
            JOptionPane.showMessageDialog(null, " USER DELETED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void deleteFromAdmin(String badge){
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("DELETE  FROM ADMIN WHERE NAME='"+badge+";'");
            JOptionPane.showMessageDialog(null, " USER DELETED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 
 public void updateTablename(String badge, String newBadge){
        String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate("RENAME TABLE "+badge+" TO "+newBadge);
            JOptionPane.showMessageDialog(null, " USER TABLE UPDATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void updateLocations(String newname,String newbadge, String badge){
     
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate(" UPDATE LOCATIONS SET NAME='"+newname+"', BADGE= '"+newbadge+"' WHERE BADGE='"+badge+"'");
            JOptionPane.showMessageDialog(null, " USER INFO UPDATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void updateAdmin(String badge,String newbadge, String newpassword){
     
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate(" UPDATE ADMIN SET PASSWORD='"+newpassword+"', NAME= '"+newbadge+"' WHERE NAME='"+badge+"'");
            JOptionPane.showMessageDialog(null, " USER INFO UPDATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void updatePasswords(String badge,String newbadge, String newpassword){
     
        String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate(" UPDATE PASSWORDS SET PASSWORD='"+newpassword+"', BADGE= '"+newbadge+"' WHERE BADGE='"+badge+"'");
            JOptionPane.showMessageDialog(null, " USER INFO UPDATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 
 public void updateOfficers(String badge, String newname, String newid, String newbadge, String newcontact ){
     
       String url= "jdbc:mysql://localhost:3306/TPMS";
        try {
            Connection connection= DriverManager.getConnection(url,"root","");
            Statement sqlquery= connection.createStatement();
            sqlquery.executeUpdate(" UPDATE OFFICERS SET NAME='"+newname+"', BADGE= '"+newbadge+"', ID='"+newid+"', CONTACT='"+newcontact+"' WHERE BADGE='"+badge+"'");
            JOptionPane.showMessageDialog(null, " USER INFO UPDATED SUCCESSFULLY");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 

 
}
