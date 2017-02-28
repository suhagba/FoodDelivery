/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author suhag
 */
public class Order {
    private String orderNumber;
    private User user;
    private List<OrderItem> orderItems;
    private double totalAmount;
    private Discount discount;
    private String orderStatus;
    
    static final String ORDER_PLACED = "ORDER PLACED";
    static final String ORDER_CONFIRMED = "ORDER CONFIRMED";
    static final String ORDER_DELIVERED = "ORDER DELIVERED";

    public Order(String orderNumber, User user, List<OrderItem> orderItems, double totalAmount, Discount discount, String orderStatus) {
        this.orderNumber = orderNumber;
        this.user = user;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderStatus = orderStatus;
    }

    public Order(User user, List<OrderItem> orderItems, double totalAmount, Discount discount, String orderStatus) {
        this.user = user;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderStatus = orderStatus;
    }
    
    

    public Order(String orderNumber, User user, List<OrderItem> orderItems, double totalAmount, Discount discount) {
        this.orderNumber = orderNumber;
        this.user = user;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderStatus = ORDER_PLACED;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    //set order status from status code
    /* 
    * code 1 = placed
    * code 2 = confirmed 
    * code 3 = delivered
    */
    public void setOrderStatus(int statusCode)
    {
        switch(statusCode){
            case 1:
            {
                this.orderStatus = ORDER_PLACED;
                break;
            }
            case 2:
            {
                this.orderStatus = ORDER_CONFIRMED;
                break;
            }
            case 3:
            {
                this.orderStatus = ORDER_DELIVERED;
                break;
            }
        }
    }
    //calculate total amount from the item list and discount code
    public void calculateTotalAmount()
    {
        double amount = 0;
        for (OrderItem oT : this.orderItems) {
           amount = amount + (oT.getQuantity() * oT.getItem().getAmount());
        }
        amount = amount - this.discount.getDiscountAmount();
        if(amount<0)
            this.totalAmount = 0;
        else
            this.totalAmount = amount;
    }
}
