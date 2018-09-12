/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import objs.Agency;
import objs.Driver;
import objs.Intercityline;
import objs.Location;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

/**
 *
 * @author Nikola
 */
@Named(value = "agenciesCtrl")
@SessionScoped
public class Agencies implements Serializable {

    private String name;
    private String city;
    private String municipality;
    private String address;
    private String contact;
    
    public Agencies() {
    }
    
    public List<Agency> LoadLines(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            List<Agency> list = s.createCriteria(Agency.class).list();
            tx.commit();
            return list;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }            
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            
        }
        return null;
    }
    
    public void addAgency(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Agency a;
        Location l;
        
         try {
            tx = s.beginTransaction(); 
           
            a = new Agency();
            l = new Location();
            
            l.setCity(city);
            l.setMunicipality(municipality);
            l.setAddress(address);
            
            s.save(l);
            
            a.setName(name);
            a.setLocation(l);
            a.setContact(contact);
            
            s.save(a);
            
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
    
    public List<Integer> LoadAgencyIDs(){
        List<Integer> agencyids;
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();        
            Query q = s.createQuery("SELECT id FROM Agency");
            agencyids = q.list();
            tx.commit();
            return agencyids;
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
    
}
