package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;
import objs.Driver;
import org.hibernate.Query;

@Named(value = "driverCtrl")
@SessionScoped
public class Drivers implements Serializable {

    private String firstname;
    private String lastname;
    private Date birthdate;
    private int startyear;
    private boolean availability;
    
    public Drivers() {
    }
    
    public void createDriver(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Driver d;
        
         try {
            tx = s.beginTransaction(); 
            
            d = new Driver();
            d.setFirstname(firstname);
            d.setLastname(lastname);
            d.setStartyear(startyear);
            d.setBirthdate(birthdate);
            d.setAvailability(1);

            s.save(d);
            tx.commit();
                        
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
            
        } finally {
            resetFields();
            s.close(); 
        }
               
    }
    
    public void resetFields(){
        setFirstname("");
        setLastname("");
    }
    
    public List<Integer> getUnassignedDriversIDs(){
        List<Integer> dlist;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("SELECT id FROM Driver WHERE availability = :avail");
            q.setParameter("avail", 1);
            dlist = q.list();
            tx.commit();
            return dlist;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            s.close(); 
        }
        return null;
        
    }
    
    public List<Driver> getAllDrivers(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            List<Driver> list = s.createCriteria(Driver.class).list();
            tx.commit();
            return list;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            s.close(); 
        }
        return null;
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
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the startyear
     */
    public int getStartyear() {
        return startyear;
    }

    /**
     * @param startyear the startyear to set
     */
    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    /**
     * @return the availability
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
}
