/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author suhag
 */
public class Item {
    private String itemCode;
    private String itemName;
    private int amount;
    private boolean veg;

    public Item(String itemCode,String itemName, int amount, boolean veg) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.amount = amount;
        this.veg = veg;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }
    
    
    
}
