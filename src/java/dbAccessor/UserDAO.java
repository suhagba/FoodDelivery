/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccessor;

import java.util.List;
import model.User;

/**
 *
 * @author suhag
 */
public interface UserDAO {
    public List<User> getAll();
    
    public User getUserFromID(String email);
    
    public boolean addUser(User user);
    
    public boolean updateUser(User user);
    
}
