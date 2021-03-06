package objs;
// Generated Aug 29, 2018 3:29:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Bus generated by hbm2java
 */
public class Bus  implements java.io.Serializable {


     private Integer id;
     private String manufacturer;
     private String model;
     private int seats;
     private int availability;
     private Set intercitylines = new HashSet(0);

    public Bus() {
    }

	
    public Bus(String manufacturer, String model, int seats, int availability) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.seats = seats;
        this.availability = availability;
    }
    public Bus(String manufacturer, String model, int seats, int availability, Set intercitylines) {
       this.manufacturer = manufacturer;
       this.model = model;
       this.seats = seats;
       this.availability = availability;
       this.intercitylines = intercitylines;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    public int getSeats() {
        return this.seats;
    }
    
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public int getAvailability() {
        return this.availability;
    }
    
    public void setAvailability(int availability) {
        this.availability = availability;
    }
    public Set getIntercitylines() {
        return this.intercitylines;
    }
    
    public void setIntercitylines(Set intercitylines) {
        this.intercitylines = intercitylines;
    }




}


