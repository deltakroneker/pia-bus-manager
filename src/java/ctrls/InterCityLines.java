package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import objs.Agency;
import objs.Bus;
import objs.Cityline;
import objs.Driver;
import objs.Intercityline;
import objs.Intercitystop;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

@Named(value = "interCityLinesCtrl")
@SessionScoped
public class InterCityLines implements Serializable {
    
    private int step = 1;
    private int busnum;
    private int drivernum;
    private String from;
    private String to;
    private int agency;
    private Date starttime;
    
    private String linename;
    private int rnum;
    private String stopcode;
    private Date arrival;
    
    public class Top10{
        private Date time;
        private String from;
        private String to;
        private String agency;
        
        public Top10(Date time, String from, String to, String agency){
            this.time = time;
            this.from = from;
            this.to = to;
            this.agency = agency;
        }

        /**
         * @return the time
         */
        public Date getTime() {
            return time;
        }

        /**
         * @param time the time to set
         */
        public void setTime(Date time) {
            this.time = time;
        }

        /**
         * @return the from
         */
        public String getFrom() {
            return from;
        }

        /**
         * @param from the from to set
         */
        public void setFrom(String from) {
            this.from = from;
        }

        /**
         * @return the to
         */
        public String getTo() {
            return to;
        }

        /**
         * @param to the to to set
         */
        public void setTo(String to) {
            this.to = to;
        }

        /**
         * @return the agency
         */
        public String getAgency() {
            return agency;
        }

        /**
         * @param agency the agency to set
         */
        public void setAgency(String agency) {
            this.agency = agency;
        }
    }
    
    public InterCityLines() {
    }
    
    public List<Intercityline> LoadLines(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            List<Intercityline> list = s.createCriteria(Intercityline.class).list();
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
    
    public void next(){
        switch (step) {
            case 1:
                step = 2;
                break;
            case 2:
                step = 3;
                if (from!="" && to!="")
                    addLine();
                break;
            case 3:
                step = 1;
                break;
            default:
                break;
        }
    }

    public List<String> LoadLinesNames(){
        List<String> llist;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("SELECT headline FROM Intercityline");
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
    
    public void resetFields(){
        busnum=0;
        from="";
        to="";
        agency=0;
        starttime=null;
    }
    
    public void addLine(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Intercityline icl;
        Agency ag;
        Bus b;
        Driver d;
        
         try {
            tx = s.beginTransaction(); 
            
            icl = new Intercityline();
            icl.setHeadline(from+" - "+to);
            icl.setStarttime(starttime);
            
            ag = (Agency) s.load(Agency.class, agency);
            icl.setAgency(ag);
            b = (Bus) s.load(Bus.class, busnum);
            icl.setBus(b);
            d = (Driver) s.load(Driver.class, drivernum);
            icl.setDriver(d);
                       
            s.save(icl);
            
            tx.commit();
                        
            /*linename = from+" - "+to;*/
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
    
    public void AddStop(){
         FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Intercityline icl;
        Intercitystop ics;
        
         try {
            tx = s.beginTransaction(); 
            
            Query q1 = s.createQuery("SELECT id FROM Intercityline WHERE headline = :hl");
            q1.setParameter("hl", linename);
            int did = (int) q1.uniqueResult();
                        
            icl = (Intercityline) s.load(Intercityline.class, did);
            
            ics = new Intercitystop();
            
            ics.setIntercityline(icl);
            ics.setNumber(rnum);
            ics.setName(stopcode);
            ics.setArrivaltime(arrival);
            
            s.save(ics);
            
            tx.commit();
                        
            resetThird();
            /*linename = from+" - "+to;*/
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
    
    public void resetThird(){
        rnum=0;
        stopcode="";
        arrival=null;
    }
    
    public List<Top10> get10MostRecentLines(){
        
        List<Top10> top10lines = new ArrayList<>();
        
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Intercityline icl;
        Intercitystop ics;
        
         try {
            tx = s.beginTransaction(); 
            
            Query q1 = s.createQuery("FROM Intercitystop ORDER BY arrivaltime ASC");
            List<Intercitystop> stops = q1.list();
            for (Intercitystop stop : stops) {
                 int id = stop.getIntercityline().getId() ;
                 
                 Query q2 = s.createQuery("FROM Intercitystop WHERE idline = :theid ORDER BY number DESC");
                 q2.setParameter("theid", id);
                 List<Intercitystop> tempstops = q2.list();
                 List<Integer> numbers = new ArrayList<>();
                 
                 for (Intercitystop st : tempstops){
                     numbers.add(st.getNumber());
                 }
                 
                 int maxNum;
                 if (!numbers.isEmpty()){
                     maxNum = numbers.get(0);
                     
                     for (Intercitystop stp : stops){
                         int lineId = stp.getIntercityline().getId();
                         int lineNum = stp.getNumber();
                         if (lineId == id && lineNum <= maxNum && lineNum > stop.getNumber()) {
                             Top10 entry = new Top10(stop.getArrivaltime(), stop.getName(), stp.getName(), "");
                             top10lines.add(entry);
                         }
                     }
                     
                 }
                 
             }
            
            Collections.sort(top10lines, new Comparator<Top10>() {
                @Override
                public int compare(Top10 lhs, Top10 rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.time.compareTo(rhs.time) < 0? -1 : (lhs.time.compareTo(rhs.time) > 0) ? 1 : 0;
                }
            });
     
            tx.commit();
            return top10lines.subList(0, 9);
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
     * @return the step
     */
    public int getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(int step) {
        this.step = step;
    }

    /**
     * @return the linenum
     */
    public int getBusnum() {
        return busnum;
    }

    /**
     * @param busnum the linenum to set
     */
    public void setBusnum(int busnum) {
        this.busnum = busnum;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the agency
     */
    public int getAgency() {
        return agency;
    }

    /**
     * @param agency the agency to set
     */
    public void setAgency(int agency) {
        this.agency = agency;
    }

    /**
     * @return the starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the drivernum
     */
    public int getDrivernum() {
        return drivernum;
    }

    /**
     * @param drivernum the drivernum to set
     */
    public void setDrivernum(int drivernum) {
        this.drivernum = drivernum;
    }

    /**
     * @return the linename
     */
    public String getLinename() {
        return linename;
    }

    /**
     * @param linename the linename to set
     */
    public void setLinename(String linename) {
        this.linename = linename;
    }

    /**
     * @return the rnum
     */
    public int getRnum() {
        return rnum;
    }

    /**
     * @param rnum the rnum to set
     */
    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    /**
     * @return the stopcode
     */
    public String getStopcode() {
        return stopcode;
    }

    /**
     * @param stopcode the stopcode to set
     */
    public void setStopcode(String stopcode) {
        this.stopcode = stopcode;
    }

    /**
     * @return the arrival
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
    
}
