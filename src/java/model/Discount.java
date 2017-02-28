/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.SecureRandom;

/**
 *
 * @author suhag
 */
public class Discount {
    private String discountCode;
    private int discountAmount;

    public Discount(String discountCode, int discountAmount) {
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    // Function to create a discount code and set the discount ammount
    public Discount createDiscount(int discountAmount)
    {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int len = 6;// random string of six letters
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        Discount discount = new Discount(sb.toString(),discountAmount);
        return discount; 
    }
    
}
