/*
 * This code is the property of mylo
 * The developers of this project own full rights to this code  * 
 */
package dbAccessor;

import java.util.*;

import model.Login;

/**
 *
 * @author Suhag
 */

public interface LoginDAO
{
    public List<Login> getAll();
    
    public Login getLoginFromID(String idlogin);
    
    public boolean addLogin(Login login);
    
    public boolean updatePass(Login login);
    
}
