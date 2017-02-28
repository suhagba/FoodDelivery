/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccessor;

import java.util.List;
import model.Order;

/**
 *
 * @author suhag
 */
public interface OrderDAO {
    public List<Order> getAll();
    
    public Order getItemFromCode(String orderNumber);
    
    public boolean addItem(Order order);
    
    public boolean updateItem(Order order);
    
}
