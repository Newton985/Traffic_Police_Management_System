/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */


class controller{
    Stage stage= new Stage();
public void toChooseLoginScene(){

    try {
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TPMS");
        stage.setResizable(false);
        stage.show();
        
        
    } catch (IOException ex) {
        Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}


public class WelcomeController implements Initializable {

    @FXML
    private TextField stnametxt;
    @FXML
    private TextField stlocatetxt;
    @FXML
    private TextField adminnametxt;
    @FXML
    private PasswordField adminpasstxt;
    @FXML
    private PasswordField adminpass2txt;
    @FXML
    private Button cancelbt;
    @FXML
    private Button donebt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        controller control= new controller();
        
        donebt.setOnAction((ActionEvent event) -> {
            String stname= stnametxt.getText();
            String location= stlocatetxt.getText();
            String admin=adminnametxt.getText();
            String adpass=adminpasstxt.getText();
            String adpass2=adminpass2txt.getText();
            String urt = "jdbc:mysql://localhost:3306";
            
            try {
                Connection conn= DriverManager.getConnection(urt, "root", "");
                Statement stmt=conn.createStatement();
                String CREATE_DATABASE= "CREATE DATABASE TPMS";
                stmt.executeUpdate(CREATE_DATABASE);
                allData alldata= new allData();
                alldata.createDatabase();
                alldata.intoAdmin(admin, adpass);
                JOptionPane.showMessageDialog(null,"CONFIGURATION SUCCESSFUL");
                control.toChooseLoginScene();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                
            }
        });
        
        cancelbt.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });
        
    }    
    
}
