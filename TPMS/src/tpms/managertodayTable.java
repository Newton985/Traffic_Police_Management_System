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
public class managertodayTable {
    
     SimpleStringProperty name = new SimpleStringProperty();
     SimpleStringProperty badge= new SimpleStringProperty();
    SimpleStringProperty location= new SimpleStringProperty();
    SimpleStringProperty shift= new SimpleStringProperty();
    
 
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
      public void setLocation(String location) {
        this.location = new SimpleStringProperty(location);
    }
        public void setShift(String shift) {
        this.shift = new SimpleStringProperty(shift);
    }
    
    
     public String getName() {
        return name.get();
}
          public String getLocation() {
        return location.get();
}
               public String getShift() {
        return shift.get();
}
                     public void setBadge(String badge) {
        this.badge = new SimpleStringProperty(badge);
    }
    
    
     public String getBadge() {
        return badge.get();
}
    
}
