package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import objs.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Constants;
import utils.NewHibernateUtil;

@Named(value = "navigationCtrl")
@SessionScoped
public class Navigation implements Serializable {

    private String username;
    private String password;
    private String passwordHash;
    
    private String firstName;
    private String lastName;
    
    public Navigation() {
    }
    
    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();

        String address = "login";
        
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = s.beginTransaction();        
            List<User> list = s.createCriteria(User.class).list();
            
            for (User user : list) {
                if (user.getUsername().equals(getUsername()))
                    if (getUsername().equals(Constants.ADMIN_USERNAME)) {
                        address = adminHome();
                        //context.getExternalContext().getSessionMap().put("user", user);
                        firstName = user.getFirstname();
                        lastName = user.getLastname();
                        break;
                    }
                    else {
                        address = userHome();
                        //context.getExternalContext().getSessionMap().put("user", user);
                        firstName = user.getFirstname();
                        lastName = user.getLastname();
                        break;
                    }
                else 
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password is not valid.", null));
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
               
        return address;
    }
    
    public String logout(){
       // TODO: unload all login info
       return Constants.LOGIN_PAGE;
    }
    
    public String loginPage(){
       return Constants.LOGIN_PAGE;
    }
    
    public String registerPage(){
       return Constants.REGISTER_PAGE;
    }
    
    public String guestHome(){
       return Constants.GUESTHOME_PAGE;
    }
    
    public String userHome(){
       return Constants.USERHOME_PAGE;
    }
    
    public String adminHome(){
       return Constants.ADMINHOME_PAGE;
    }
    
    public String adminCLM(){
        return Constants.ADMINCLM_PAGE;
    }
    
    public String adminICLM(){
        return Constants.ADMINICLM_PAGE;
    }
    
    public String adminRM(){
        return Constants.ADMINRM_PAGE;
    }

    public String adminND(){
        return Constants.ADMINND_PAGE;
    }
     
    public String adminNCL(){
        return Constants.ADMINNCL_PAGE;
    }
      
    public String adminDCL(){
        return Constants.ADMINDCL_PAGE;
    }
    
    public String adminNICL(){
        return Constants.ADMINNICL_PAGE;
    }
    
    public String adminNB(){
        return Constants.ADMINNB_PAGE;
    }
    
    public String adminNA(){
        return Constants.ADMINNA_PAGE;
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
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
