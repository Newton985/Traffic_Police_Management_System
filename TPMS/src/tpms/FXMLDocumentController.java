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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField badgenotxt;
    @FXML
    private ComboBox<String> pcombo;
    @FXML
    private Button showbt;
    @FXML
    private TableView<managertodayTable> tableMan;
    @FXML
    private TableView<manageroneTable> tablemanser;
    @FXML
    private TableColumn<managertodayTable, String> NAME_COL;
    @FXML
    private TableColumn<managertodayTable, String> BADGE_COL;
    @FXML
    private TableColumn<managertodayTable, String> LOC_COL;
    @FXML
    private TableColumn<managertodayTable, String> SHIFT_COL;
    @FXML
    private TableColumn<officerTable, String> DATE_COL;
    @FXML
    private TableColumn<officerTable, String> LOCS_COL;
    @FXML
    private TableColumn<officerTable, String> SHIFTS_COL;
    @FXML
    private TextField datetxt;
    @FXML
    private Button showdatebt;
    @FXML
    private TableView<adminTableHandler> tableManAll;
    @FXML
    private TableColumn<adminTableHandler, String> names_col;
    @FXML
    private TableColumn<adminTableHandler, String> badges_col;
    @FXML
    private TableColumn<adminTableHandler, String> ids_col;
    @FXML
    private TableColumn<adminTableHandler, String> conta_col;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOa
        AdminsceneController b= new AdminsceneController();
        
        pcombo.getItems().add("TWO WEEKS");
        pcombo.getItems().add("ONE MONTH");
        pcombo.getItems().add("ALL");
        
        NAME_COL.setCellValueFactory(new PropertyValueFactory<>("name"));
        BADGE_COL.setCellValueFactory(new PropertyValueFactory<>("badge"));
        LOC_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
        SHIFT_COL.setCellValueFactory(new PropertyValueFactory<>("shift"));
        
     DATE_COL.setCellValueFactory(new PropertyValueFactory<>("date"));
     LOCS_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
     SHIFTS_COL.setCellValueFactory(new PropertyValueFactory<>("shift"));
     
        names_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        badges_col.setCellValueFactory(new PropertyValueFactory<>("badge"));
        ids_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        conta_col.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
     
     DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
     Date datec =new Date();
     String date= format.format(datec);
     
     
     showbt.setOnAction((ActionEvent event) -> {
         try {
             Connection c= DriverManager.getConnection("jdbc:mysql://localhost/TPMS", "root", "");
             
             String period = pcombo.getSelectionModel().getSelectedItem();
             
             Statement st= c.createStatement();
             Statement sc=c.createStatement();
             //ResultSet resultset= st.executeQuery(sqlquery);
             String tname= badgenotxt.getText();
             ResultSet rs= st.executeQuery("SELECT * FROM "+tname);
             ResultSet resultset= sc.executeQuery("SELECT * FROM "+tname);
             int count = 0;
             while(resultset.next()){   
                 
                 count++;
                 
             }
             int control=0;
             int rowjump = 0;
             
             if(count<14||period.equals("ALL")){
                 
                 while(rs.next()){
                     manageroneTable ot= new manageroneTable();
                     String datew= rs.getString("DATE");
                     String loc= rs.getString("LOCATION");
                     String shif= rs.getString("SHIFT");
                     
                     ot.date.set(datew);
                     ot.location.set(loc);
                     ot.shift.set(shif);
                     details2.add(ot);
                 }}
             
             else {
                 if(period.equals("TWO WEEKS")){ rowjump= count-14; } else if(period.equals("ONE MONTH")){ rowjump= count-30;}
                 while(rs.next()){
                     manageroneTable ot= new manageroneTable();
                     String datew= rs.getString("DATE");
                     String loc= rs.getString("LOCATION");
                     String shif= rs.getString("SHIFT");
                     if(control<rowjump){
                         control++;
                         continue;
                     }
                     
                     ot.date.set(datew);
                     ot.location.set(loc);
                     ot.shift.set(shif);
                     details2.add(ot);
                     
                 }}
             tablemanser.getItems().clear();
             tablemanser.setItems(details2);
             
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
             //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        });
     
     showdatebt.setOnAction((ActionEvent event) -> {
         String date2= datetxt.getText();
         try {
             Connection r= DriverManager.getConnection("jdbc:mysql://localhost/TPMS", "root", "");
             Statement str= r.createStatement();
             //ResultSet resultset= st.executeQuery(sqlquery);
             String tname= badgenotxt.getText();
             ResultSet rss= str.executeQuery("SELECT * FROM LOCATIONS WHERE DATE='"+date2+"';");
             
             while(rss.next()){
                 managertodayTable ot= new managertodayTable();
                 String namew= rss.getString("NAME");
                 String badge=rss.getString("BADGE");
                 String shif= rss.getString("LOCATION");
                 String sh= rss.getString("SHIFT");
                 
                 ot.name.set(namew);
                 ot.badge.set(badge);
                 ot.location.set(shif);
                 ot.shift.set(sh);
                 
                 todayList.add(ot);
             }
             tableMan.setItems(todayList);
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
         }
        });
     
        try {
            Connection x= DriverManager.getConnection("jdbc:mysql://localhost/TPMS", "root", "");
             
              Statement st= x.createStatement();
            //ResultSet resultset= st.executeQuery(sqlquery);
            String tname= badgenotxt.getText();
            ResultSet rs= st.executeQuery("SELECT * FROM LOCATIONS WHERE DATE='"+date+"';");
            
            while(rs.next()){
            managertodayTable ot= new managertodayTable(); 
            String datew= rs.getString("NAME");
            String loc= rs.getString("BADGE");
            String shif= rs.getString("LOCATION");
            String sh= rs.getString("SHIFT");
            
            ot.name.set(datew);
            ot.badge.set(loc);
            ot.location.set(shif);
            ot.shift.set(sh);
            
            searchList.add(ot);
            }
            
            
            
            tableMan.setItems(searchList);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

//Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                try {
            
            Connection cv= DriverManager.getConnection("jdbc:mysql://localhost/TPMS", "root", "");
            Statement sr= cv.createStatement();
            ResultSet rset= sr.executeQuery("SELECT * FROM OFFICERS");
            
            while(rset.next()){
                adminTableHandler addtable= new adminTableHandler();
                String namew= rset.getString("NAME");
                String badgew= rset.getString("BADGE");
                String idw=rset.getString("ID");
                String contactw=rset.getString("CONTACT");
                
                addtable.name.set(namew);
                addtable.badge.set(badgew);
                addtable.id.set(idw);
                addtable.contact.set(contactw);
               information.add(addtable);
                          
            }
            
            tableManAll.setItems(information);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
           // Logger.getLogger(AdminsceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
           
    } 
    
           private final  ObservableList<manageroneTable> details2 = FXCollections.observableArrayList();
           private final  ObservableList<managertodayTable> todayList = FXCollections.observableArrayList();
           private final ObservableList<managertodayTable> searchList= FXCollections.observableArrayList();
           public ObservableList<adminTableHandler> information= FXCollections.observableArrayList();
    
}

 