/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */


public class OfficersceneController  implements Initializable {

   
    @FXML
    private Button viewbt;
    @FXML
    private TextField locatxt;
    @FXML
    private ComboBox<String> timecombo;
   
    @FXML
    private TableColumn<officerTable, String> COL_DATE;
    @FXML
    private TableColumn<officerTable, String> COL_LOC;
    @FXML
    private TableColumn<officerTable, String> COL_SHIFT;
    @FXML
    private TableView<officerTable> tableOfficer;
    @FXML
    private TextField bdtxt;
    @FXML
    private TextField badtxt;
    @FXML
    private ComboBox<String> periodcomb;
    @FXML
    private Button submitofficerbt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        timecombo.getItems().add("DAY");
        timecombo.getItems().add("NIGHT");
        timecombo.setValue("TIME OF DAY");
        allData alldata= new allData();
     //officerTable oftb= new officerTable();
        
     DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
     Date datec =new Date();
     String date= format.format(datec);
     periodcomb.getItems().add("TWO WEEKS");
     periodcomb.getItems().add("ONE MONTH");
     periodcomb.getItems().add("ALL");
     COL_DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
     COL_LOC.setCellValueFactory(new PropertyValueFactory<>("location"));
     COL_SHIFT.setCellValueFactory(new PropertyValueFactory<>("shift"));
     DBClass obj= new DBClass();
     
     viewbt.setOnAction((ActionEvent event) -> {
         String st= bdtxt.getText();
         //String period= periodcomb.getSelectionModel().getSelectedItem();
         try{
             Connection con= obj.getConnection();
             String period= periodcomb.getSelectionModel().getSelectedItem();
             
             buildData(st, period);
         } catch(ClassNotFoundException | SQLException e){
             JOptionPane.showMessageDialog(null, e);
         }  });
     
     
        submitofficerbt.setOnAction((ActionEvent event) -> {
            try{
                String b= locatxt.getText().toUpperCase();
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                
            }
            String location;
            location= locatxt.getText().toUpperCase();
            String badge1 = badtxt.getText();
            String shift = timecombo.getSelectionModel().getSelectedItem();
            try {
                String url2="jdbc:mysql://localhost/TPMS";
                Connection conn= DriverManager.getConnection(url2, "root", "");
                Statement stmt= conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT NAME FROM OFFICERS WHERE BADGE= '" + badge1 + "'  ");
                while (rs.next()) {
                    String name1 = rs.getString("name");
                    setn(name1);
                    String insert = "INSERT INTO " + badge1 + " VALUE ('" + date + "', '" + location + "','" + shift + "')";
                    Statement stm2= conn.createStatement();
                    stm2.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "REPORT SUCCESSFUL");
                    alldata.intoLocations(name1, badge1, date, location, shift);
                }
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        });
   
        
        
    }
    String badge; 
    String name;

public void setb(String badg){

badge= badg;
}

public String getb(){

    return badge;
}

public void setn(String nam){
name= nam;
   
}

public String getn(){

return name;
}

           private ObservableList<officerTable> details;
           
public void buildData(String bad, String period){
    
        details= FXCollections.observableArrayList();
        
        String sqlquery="SELECT * FROM "+bad;
        String countquery="SELECT COUNT(*) FROM "+bad;
    
        try {
            
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/TPMS","root","");
            Statement st= con.createStatement();
            Statement sc=con.createStatement();
            Statement st2= con.createStatement();
            
            ResultSet rs2= st.executeQuery(sqlquery);
            ResultSet resultset= sc.executeQuery(sqlquery);
            int count = 0;
            while(resultset.next()){
                
            count++;
            
            }
            int control=0;
            int rowjump = 0;
            
            if(count<14||period.equals("ALL"))
                
                {
            while(rs2.next()){
                
                
                
            officerTable ot= new officerTable(); 
            String datew= rs2.getString("DATE");
            String loc= rs2.getString("LOCATION");
            String shif= rs2.getString("SHIFT");
            
            ot.date2.set(datew);
            ot.location.set(loc);
            ot.shift.set(shif);
            details.add(ot);
            }
            
            } else
                    
            if(period.equals("TWO WEEKS")){ rowjump= count-14; } else if(period.equals("ONE MONTH")){ rowjump= count-30;}
            
           while(rs2.next()){
                officerTable ot= new officerTable(); 
            String datew= rs2.getString("DATE");
            String loc= rs2.getString("LOCATION");
            String shif= rs2.getString("SHIFT");
            if(control<rowjump){
            control++;
            continue;
            }
            
            ot.date2.set(datew);
            ot.location.set(loc);
            ot.shift.set(shif);
            details.add(ot);    
            }
            tableOfficer.setItems(details); 
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex);
        } 
              
                
           }
  
                    
}

