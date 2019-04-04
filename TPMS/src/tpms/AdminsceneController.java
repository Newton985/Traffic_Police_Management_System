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
public class AdminsceneController implements Initializable {

    @FXML
    private TableColumn<adminTableHandler, String> colname;
    @FXML
    private TableColumn<adminTableHandler, String> colbadge;
    @FXML
    private TableColumn<adminTableHandler, String> colid;
    @FXML
    private TableColumn<adminTableHandler, String> colphone;
    
    @FXML
    private TextField nameadtex;
    @FXML
    private TextField idadtxt;
    @FXML
    private TextField badgeadtxt;
    @FXML
    private TextField ageadtxt;
    @FXML
    private TextField contadtxt;
    @FXML
    private TextField passadtxt;
    @FXML
    private ComboBox<String> gencombo;
    @FXML
    private ComboBox<String> rankadtxt;
    @FXML
    private Button clearadbt;
    @FXML
    private Button canceladbt;
    @FXML
    private Button submitadbt;
    @FXML
    private TableView<adminTableHandler> adminTable;
    @FXML
    private TextField badgedeltxt;
    @FXML
    private Button deletebut;
    @FXML
    private Button updatebut;
    @FXML
    private TextField oldbadgetxt;
    @FXML
    private TextField newpasstxt;
    @FXML
    private TextField newnametxt;
    @FXML
    private TextField newidtxt;
    @FXML
    private TextField newconttxt;
    @FXML
    private TextField newbadgetxt;
    @FXML
    private Button cancadbut;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gencombo.getItems().add("MALE");
        gencombo.getItems().add("FEMALE");
        gencombo.setValue("GENDER");
        rankadtxt.getItems().add("OFFICER");
        rankadtxt.getItems().add("MANAGER");
        rankadtxt.getItems().add("ADMINISTRATOR");
        rankadtxt.setValue("PRIVILEGE");
        allData alldata= new allData();
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colbadge.setCellValueFactory(new PropertyValueFactory<>("badge"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("contact"));
       // adminTable.setItems(information);
        
        submitadbt.setOnAction((ActionEvent event) -> {
            String name, badge, gender, rank, password, age, contact, idno;
            //String gender;
            int g=2;
            while(g>0){
            try{
                name=nameadtex.getText().toUpperCase();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                int id=Integer.parseInt(idadtxt.getText());
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                rank=rankadtxt.getSelectionModel().getSelectedItem();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                int id=Integer.parseInt(contadtxt.getText());
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                gender=gencombo.getSelectionModel().getSelectedItem();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                password=passadtxt.getText().toUpperCase();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                int ag=Integer.parseInt(ageadtxt.getText());
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT"); break;
            }
            try{
                badge=badgeadtxt.getText().toUpperCase();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT");  break;
            }
            name=nameadtex.getText().toUpperCase();
            idno=idadtxt.getText().toUpperCase();
            rank=rankadtxt.getSelectionModel().getSelectedItem();
            contact=contadtxt.getText().toUpperCase();
            gender=gencombo.getSelectionModel().getSelectedItem();
            password=passadtxt.getText().toUpperCase();
            age=ageadtxt.getText().toUpperCase();
            badge= badgeadtxt.getText();
            switch (rank) {
                case "OFFICER":
                    alldata.newUser(badge.toString());
                    alldata.intoOfficers(name, idno, badge, contact);
                    alldata.intoPasswords(badge, password);
                    alldata.intoAdmin(badge, password);
                    break;
                case "MANAGER":
                    alldata.intoPasswords(badge, password);
                    break;
                case "ADMINISTRATOR":
                    alldata.intoAdmin(name, password);
                    break;
            }
            break;
            } });
        
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
            
            adminTable.setItems(information);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
           // Logger.getLogger(AdminsceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     deletebut.setOnAction((ActionEvent event) -> {
         String badf= badgedeltxt.getText();
         alldata.deleteUser(badf);
         alldata.deleteFromAdmin(badf);
         alldata.deleteFromLocations(badf);
         alldata.deleteFromOfficers(badf);
         alldata.deleteFromPasswords(badf);
        });
     int c=2;
     
     updatebut.setOnAction((ActionEvent event) -> {
         while(c>0){
         try{ String bd= oldbadgetxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input"); break;}
         try{ String bd= newpasstxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input"); break;}
         try{ String bd= newnametxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input"); break;}
         try{ String bd= newidtxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input"); break;}
         try{ String bd= newconttxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input");break;}
         try{ String bd= newbadgetxt.getText();} catch(Exception e){ JOptionPane.showMessageDialog(null, "Invalid input");break;}
        
         String badge= oldbadgetxt.getText(); String newpass= newpasstxt.getText();
         String newname= newnametxt.getText(); String newid= newidtxt.getText();
         String newbadge= newbadgetxt.getText(); String newcont= newconttxt.getText();
         alldata.updateTablename(badge, newbadge);
         alldata.updatePasswords(badge, newbadge, newpass);
         alldata.updateAdmin(badge, newbadge, newpass);
         alldata.updateLocations(newname, newbadge, badge);
         alldata.updateOfficers(badge, newname, newid, newbadge, newcont);
         break;
         }});
       
    }

public ObservableList<adminTableHandler> information= FXCollections.observableArrayList();

public ObservableList getList(){
return information;
}

}
