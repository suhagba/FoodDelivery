/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccessor;

import java.util.List;
import model.Item;

/**
 *
 * @author suhag
 */
public interface ItemDAO {
     public List<Item> getAll();
    
    public Item getItemFromCode(String itemCode);
    
    public boolean addItem(Item item);
    
    public boolean updateItem(Item item);
    
}
