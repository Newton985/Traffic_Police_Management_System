/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class TPMS extends Application {
    Stage stage, admstage,offstage, manstage;
     Stage stage2;
     Parent root, admroot, offroot, manroot;
    
     
    @Override
    public void start(Stage stage) throws Exception {
  
       int x;
        
        try{
        Connection todb= DriverManager.getConnection("jdbc:mysql://localhost/TPMS", "root","");
        JOptionPane.showMessageDialog(null, "DATABASE CONNECTED");
        
         x= 2;
        } catch(SQLException e){
            
             x=3;
        }
         if(x==3){   
        
        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("TPMS");
        stage.show();
         } else if(x==2){
             Parent root2= FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene2=new Scene(root2);
         stage.setScene(scene2);
         stage.setTitle("TPMS");
         stage.setResizable(false);
         stage.show();
         }
        
    }
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void initScenes(){}
    
}




class adminScene{
    Stage stage2= new Stage();
    
    
     public void toAdminScene(){
     Parent root2;
     
    try {
              root2=FXMLLoader.load(getClass().getClassLoader().getResource("tpms/adminscene.fxml"));
              Scene scene2= new Scene(root2);
               stage2.setScene(scene2);
               stage2.setTitle("TPMS");
               stage2.setResizable(false);
               stage2.show();
        } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, ex);
        }
    
    }
     
}
class officerScene{
    Stage stage3= new Stage();
   

 public void toOfficerScene(){
    try {
           
            //Stage stage;
            Parent root;
            root= FXMLLoader.load(getClass().getClassLoader().getResource("tpms/officerscene.fxml"));
               Scene scene= new Scene(root);
               stage3.setScene(scene);
               stage3.setTitle("TPMS");
               stage3.setResizable(false);
               stage3.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 
 }
 }
 
class managerScene{
    Stage stage4= new Stage();
   
 
 public void toManagerScene(){
  try {
      Parent root;
            root= FXMLLoader.load(getClass().getClassLoader().getResource("tpms/FXMLDocument.fxml"));
             Scene scene= new Scene(root);
               stage4.setScene(scene);
               stage4.setTitle("TPMS");
               stage4.setResizable(false);
               stage4.show();
        } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, ex);
        }
 }}
  
