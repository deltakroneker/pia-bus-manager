package objs;
// Generated Aug 29, 2018 3:29:04 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Intercityline generated by hbm2java
 */
public class Intercityline  implements java.io.Serializable {


     private Integer id;
     private Agency agency;
     private Bus bus;
     private Driver driver;
     private Date starttime;
     private String headline;
     private Set intercitystops = new HashSet(0);

    public Intercityline() {
    }

	
    public Intercityline(Agency agency, Bus bus, Driver driver, Date starttime, String headline) {
        this.agency = agency;
        this.bus = bus;
        this.driver = driver;
        this.starttime = starttime;
        this.headline = headline;
    }
    public Intercityline(Agency agency, Bus bus, Driver driver, Date starttime, String headline, Set intercitystops) {
       this.agency = agency;
       this.bus = bus;
       this.driver = driver;
       this.starttime = starttime;
       this.headline = headline;
       this.intercitystops = intercitystops;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Agency getAgency() {
        return this.agency;
    }
    
    public void setAgency(Agency agency) {
        this.agency = agency;
    }
    public Bus getBus() {
        return this.bus;
    }
    
    public void setBus(Bus bus) {
        this.bus = bus;
    }
    public Driver getDriver() {
        return this.driver;
    }
    
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public String getHeadline() {
        return this.headline;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public Set getIntercitystops() {
        return this.intercitystops;
    }
    
    public void setIntercitystops(Set intercitystops) {
        this.intercitystops = intercitystops;
    }




}


