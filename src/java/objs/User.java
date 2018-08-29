package objs;
// Generated Aug 29, 2018 3:29:04 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private Location location;
     private String username;
     private String firstname;
     private String lastname;
     private String password;
     private Date birthdate;
     private String contact;
     private String category;
     private String email;

    public User() {
    }

	
    public User(Location location, String username, String firstname, String lastname, String password, Date birthdate, String contact, String category) {
        this.location = location;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
        this.contact = contact;
        this.category = category;
    }
    public User(Location location, String username, String firstname, String lastname, String password, Date birthdate, String contact, String category, String email) {
       this.location = location;
       this.username = username;
       this.firstname = firstname;
       this.lastname = lastname;
       this.password = password;
       this.birthdate = birthdate;
       this.contact = contact;
       this.category = category;
       this.email = email;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


