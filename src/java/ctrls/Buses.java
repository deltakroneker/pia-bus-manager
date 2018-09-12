package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import objs.Agency;
import objs.Bus;
import objs.Location;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

@Named(value = "busesCtrl")
@SessionScoped
public class Buses implements Serializable {
    
    private String manufacturer;
    private String model;
    private int seats;

    public Buses() {
    }
    
    public List<Bus> LoadLines(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            List<Bus> list = s.createCriteria(Bus.class).list();
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
    
    public void addBus(){
         FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Bus b;        
         try {
            tx = s.beginTransaction(); 
           
            b = new Bus();
            
            b.setManufacturer(manufacturer);
            b.setModel(model);
            b.setSeats(seats);
            b.setAvailability(1);
           
            s.save(b);
            
            tx.commit();
                        
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
    
    public List<Integer> LoadUnassignedBusesIDs(){
        List<Integer> idlist;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("SELECT id FROM Bus WHERE availability = :avail");
            q.setParameter("avail", 1);
            idlist = q.list();
            tx.commit();
            return idlist;
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
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }
    
}
