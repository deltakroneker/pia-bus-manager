
package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import objs.Cityline;
import objs.Citystop;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

@Named(value = "cityStationsCtrl")
@SessionScoped
public class CityStations implements Serializable {

    private int linenum;
    private int number;
    private String name;
    
    private List<Citystop> stops;
    
    public CityStations() {
    }
    
    public void AddStation(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Citystop cs;
        
         try {
            tx = s.beginTransaction(); 
            
            Query q1 = s.createQuery("SELECT id FROM Cityline WHERE number = :num");
            q1.setParameter("num", linenum);
            int did = (int) q1.uniqueResult();
            
            cs = new Citystop();
            cs.setName(name);
            cs.setNumber(number);
            Cityline cl = (Cityline) s.load(Cityline.class, did);
            cs.setCityline(cl);

            s.save(cs);
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
        name = "";
    }
    
    public void loadStopTable(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();     
            Query q1 = s.createQuery("SELECT id FROM Cityline WHERE number = :num");
            q1.setParameter("num", linenum);
            Query q = s.createQuery("FROM Citystop WHERE idline = :myid ORDER BY number ASC");
            q.setParameter("myid", q1.uniqueResult());
            setStops(q.list());
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
        }    
    }


    /**
     * @return the lineid
     */
    public int getLinenum() {
        return linenum;
    }

    /**
     * @param linenum the lineid to set
     */
    public void setLinenum(int linenum) {
        this.linenum = linenum;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
