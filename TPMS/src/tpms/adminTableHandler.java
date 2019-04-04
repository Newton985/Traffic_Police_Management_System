/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpms;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author root
 */
public class adminTableHandler {
    
    SimpleStringProperty name= new SimpleStringProperty();
    SimpleStringProperty badge=new SimpleStringProperty();
    SimpleStringProperty id=new SimpleStringProperty();
    SimpleStringProperty contact=new SimpleStringProperty();
    private  SimpleStringProperty location=new SimpleStringProperty();
  
    
    public void setName(String name){
   this.name= new SimpleStringProperty(name);
    }
    public String getName(){
    return name.get();
    }
     public void setBadge(String badge){
   this.badge= new SimpleStringProperty(badge);
    }
    public String getBadge(){
    return badge.get();
    }
     public void setId(String id){
   this.id= new SimpleStringProperty(id);
    }
    public String getId(){
    return id.get();
    }
     public void setLocation(String location){
   this.location= new SimpleStringProperty(location);
    }
    public String getLocation(){
    return location.get();
    }
     public void setContact(String contact){
   this.contact= new SimpleStringProperty(contact);
    }
    public String getContact(){
    return contact.get();
    }
    
}
