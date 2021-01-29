/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class Order implements Serializable{
    private String orderID;
    private User user;
    private String payment;
    private boolean paymentSuccess;
    private String shippingAddress;
    private String phone;

    public Order() {
    }

    public Order(String orderID, User user, String payment, boolean paymentSuccess, String shippingAddress, String phone) {
        this.orderID = orderID;
        this.user = user;
        this.payment = payment;
        this.paymentSuccess = paymentSuccess;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
