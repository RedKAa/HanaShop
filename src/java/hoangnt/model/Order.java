/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author user
 */
public class Order implements Serializable{
    private String orderID;
    private String userID;
    private String payment;
    private boolean paymentSuccess;
    private String shippingAddress;
    private String phone;
    private Date orderDate;
    private float totalPrice;
    
    public Order() {
    }

    public Order(String orderID, String userID, String payment, boolean paymentSuccess, String shippingAddress, String phone) {
        this.orderID = orderID;
        this.userID = userID;
        this.payment = payment;
        this.paymentSuccess = paymentSuccess;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
    }

    public Order(String orderID, String userID, String payment, boolean paymentSuccess, String shippingAddress, String phone, Date orderDate, float totalPrice) {
        this.orderID = orderID;
        this.userID = userID;
        this.payment = payment;
        this.paymentSuccess = paymentSuccess;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    

 

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public void setPaymentSuccess(boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
