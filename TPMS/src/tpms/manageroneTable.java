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
public class manageroneTable {
    
    SimpleStringProperty date= new SimpleStringProperty();
    SimpleStringProperty location= new SimpleStringProperty();
    SimpleStringProperty shift= new SimpleStringProperty();
   
    
    
        public String getDate() {
        return date.get();
}
          public String getLocation() {
        return location.get();
}
               public String getShift() {
        return shift.get();
}
    
}
