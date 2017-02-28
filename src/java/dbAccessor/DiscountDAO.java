/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccessor;

import java.util.List;
import model.Discount;

/**
 *
 * @author suhag
 */
public interface DiscountDAO {
    public List<Discount> getAll();
    
    public Discount getDiscountFromCode(String discountCode);
    
    public boolean addDiscount(Discount discount);
    
    public boolean updateDiscount(Discount discount);
    
}
