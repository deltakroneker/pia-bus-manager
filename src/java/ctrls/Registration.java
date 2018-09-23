package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import objs.Agency;
import objs.Location;
import objs.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

@Named(value = "registrationCtrl")
@SessionScoped
public class Registration implements Serializable {
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String city;
    private String municipality;
    private String address;
    private String contact;
    private String email;
    private Date birth;
    
    public Registration() {
    }

    public void addUser(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User u;
        Location l;
        
         try {
            tx = s.beginTransaction(); 
           
            u = new User();
            l = new Location();
            
            l.setCity(city);
            l.setMunicipality(municipality);
            l.setAddress(address);
            
            s.save(l);
            
            u.setUsername(username);
            u.setFirstname(firstname);
            u.setLastname(lastname);
            u.setPassword(password);
            u.setLocation(l);
            u.setBirthdate(birth);
            u.setContact(contact);
            u.setCategory("student");
            u.setEmail(email);
            
            s.save(u);
            
            tx.commit();
            resetAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User successfuly registered!", null));
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
            
        } finally {
            s.close(); 
        }
    }
    
    public void resetAll(){
        username="";
        password="";
        firstname="";
        lastname="";
        password="";
        contact="";
        city="";
        municipality="";
        address="";
        email="";
        birth=null;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * @param municipality the municipality to set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth the birth to set
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
}
