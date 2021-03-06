package objs;
// Generated Aug 29, 2018 3:29:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cityline generated by hbm2java
 */
public class Cityline  implements java.io.Serializable {


     private Integer id;
     private int number;
     private int status;
     private String headline;
     private Set citylinedriverses = new HashSet(0);
     private Set citylinetimetables = new HashSet(0);
     private Set citystops = new HashSet(0);

    public Cityline() {
    }

	
    public Cityline(int number, int status, String headline) {
        this.number = number;
        this.status = status;
        this.headline = headline;
    }
    public Cityline(int number, int status, String headline, Set citylinedriverses, Set citylinetimetables, Set citystops) {
       this.number = number;
       this.status = status;
       this.headline = headline;
       this.citylinedriverses = citylinedriverses;
       this.citylinetimetables = citylinetimetables;
       this.citystops = citystops;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    public String getHeadline() {
        return this.headline;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public Set getCitylinedriverses() {
        return this.citylinedriverses;
    }
    
    public void setCitylinedriverses(Set citylinedriverses) {
        this.citylinedriverses = citylinedriverses;
    }
    public Set getCitylinetimetables() {
        return this.citylinetimetables;
    }
    
    public void setCitylinetimetables(Set citylinetimetables) {
        this.citylinetimetables = citylinetimetables;
    }
    public Set getCitystops() {
        return this.citystops;
    }
    
    public void setCitystops(Set citystops) {
        this.citystops = citystops;
    }




}


