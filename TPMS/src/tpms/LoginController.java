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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
class sceneHandler{
Stage stage;


}


public class LoginController implements Initializable {

    @FXML
    private TextField badgetxt;
    @FXML
    private PasswordField passtxt;
    @FXML
    private Button submitbt;
    @FXML
    private Button cancelbt;
    @FXML
    private ComboBox<String> levelcombo;
    
    String user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         int yy=2;
         
         levelcombo.getItems().add("ADMINISTARATOR");
        levelcombo.getItems().add("MANAGER");
        levelcombo.getItems().add("OFFICER");
        allData alldata= new allData();
        sceneHandler scenehandler= new sceneHandler();
        TPMS tps= new TPMS();
        managerScene mns= new managerScene();
        officerScene ofs= new officerScene();
        adminScene ads= new adminScene();
        OfficersceneController ofsc= new OfficersceneController();
        
      
       
        
        submitbt.setOnAction((ActionEvent event) -> {
           
            String level, uname, username, upass, password;
            username= badgetxt.getText();
            setUser(username);
            password=passtxt.getText();
            level= levelcombo.getSelectionModel().getSelectedItem();
            int count = 0;
            boolean found= false;
            int control=0;
            int i=0;
            String url1 = "jdbc:mysql://localhost:3306/TPMS";
            try {
                Connection connection = DriverManager.getConnection(url1, "root", "");
                Statement sqlquery= connection.createStatement();
                Statement query= connection.createStatement();
                String selectquery="SELECT * FROM PASSWORDS";
                //String selectquery2="SELECT * FROM PASSWORDS";
                ResultSet resultset= sqlquery.executeQuery(selectquery);
                ResultSet RS=query.executeQuery("SELECT COUNT(*) FROM PASSWORDS");
                while(RS.next()){
                    count++;
                }
                
                    while(resultset.next()){
                        control++;
                        uname= resultset.getString("BADGE");
                        upass= resultset.getString("PASSWORD");
                        if(uname.equals(username)&&upass.equals(password)){
                            JOptionPane.showMessageDialog(null, "LOGIN SUCCESS");
                            found= true;
                            ofsc.setb(uname);
                            ofsc.setn(upass);
                           
                        }
                         if(found==true){ 
                             
                              
                            switch (level) {
                                case "MANAGER":
                                    mns.toManagerScene();
                                    break;
                                case "OFFICER":
                                    ofs.toOfficerScene();
                                    break;
                                default:
                                    ads.toAdminScene();
                                    break;
                            }
                             
                             
                             break;}
                    else if(found==false&&(control)==count){
                        JOptionPane.showMessageDialog(null, "USER NOT FOUND\n EDIT DETAILS AND TRY AGAIN"); break;}
                    }  
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
          
         });
        
        
       
    }  
    
    public void setUser(String user){
    this.user= user;
    }
    
    public String getBad(){
    
    return user;
    }
}
