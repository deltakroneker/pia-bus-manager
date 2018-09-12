package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import objs.Cityline;
import objs.Citylinedrivers;
import objs.Citystop;
import objs.User;
import objs.Driver;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Constants;
import utils.NewHibernateUtil;

@Named(value = "cityLinesCtrl")
@SessionScoped
public class CityLines implements Serializable {

    private int id;
    private int number;
    private String start;
    private String end;
    
    private List<Citystop> stops;
        
    public CityLines() {
    }
    
    public List<Cityline> LoadLines(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            List<Cityline> list = s.createCriteria(Cityline.class).list();
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
    
    public String getDriver(int id){        
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("FROM Citylinedrivers WHERE idline = :lineid");
            q.setParameter("lineid", id);
            Citylinedrivers cld = (Citylinedrivers)q.uniqueResult();
            
            if (cld != null){ 
                Query q2 = s.createQuery("FROM Driver WHERE id= :driverid");
                q2.setParameter("driverid", cld.getId());
                Driver d = (Driver)q2.uniqueResult();
                tx.commit();
                return d.getFirstname() + " " + d.getLastname();
            }
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
        return "/";
    }
    
    public void addLine(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Cityline cl;
        
         try {
            tx = s.beginTransaction(); 
            
            cl = new Cityline();
            cl.setNumber(number);
            cl.setHeadline(start + " - " + end);
            cl.setStatus(1);

            s.save(cl);
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
        System.out.println("kek");
    }
    
    public void resetFields(){
        number = 0;
        start = "";
        end = "";
    }
    
    public List<Integer> LoadLinesNums(){
        List<Integer> llist;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("SELECT number FROM Cityline");
            llist = q.list();
            tx.commit();
            return llist;
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

    public void changeStatus(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Cityline cl;
        
         try {
            tx = s.beginTransaction(); 
            
            Query q1 = s.createQuery("SELECT id FROM Cityline WHERE number = :num");
            q1.setParameter("num", number);
            int did = (int) q1.uniqueResult();
            
            cl = (Cityline) s.load(Cityline.class, did);
            
            if (cl.getStatus() == 0) {
                cl.setStatus(1);
            } else {
                cl.setStatus(0);
            }

            s.update(cl);
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
        System.out.println("kek");
    
    }
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return the stops
     */
    public List<Citystop> getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(List<Citystop> stops) {
        this.stops = stops;
    }
    
}
