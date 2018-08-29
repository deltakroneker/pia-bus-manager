package ctrls;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import utils.Constants;

@Named(value = "navigationCtrl")
@SessionScoped
public class Navigation implements Serializable {

    public Navigation() {
    }
    
    public String logout(){
       // TODO: unload all login info
       return Constants.LOGIN_PAGE;
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
}
