package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.hibernate.Session;
import utils.NewHibernateUtil;

@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    public Login() {
    }
    
    public void Test() {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        System.out.println("kek");
    }
    
}
