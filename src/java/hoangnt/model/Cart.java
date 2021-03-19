/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author user
 */
public class Cart implements Serializable {

    HashMap<String, OrderDetails> cart;
    int numOfItems;

    public Cart() {
        cart = new HashMap<>();
        numOfItems = 0;
    }

    public HashMap<String, OrderDetails> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, OrderDetails> cart) {
        this.cart = cart;
    }

    public boolean addToCart(String productID, int quantity, float unitPrice) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (isExistedProduct(productID)) {
            OrderDetails orderDetail = cart.get(productID);
            int newQuantity = orderDetail.getQuantity() + quantity;
            orderDetail.setQuantity(newQuantity);
            orderDetail.setPrice(unitPrice * newQuantity);
            return cart.put(productID, orderDetail) != null;
        } else {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setID(UUID.randomUUID().toString().substring(0, 20));
            orderDetail.setProductID(productID);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(unitPrice * quantity);
            setNumOfItems(this.numOfItems+1);
            return cart.put(productID, orderDetail) != null;
        }
    }

    public void removeProduct(String productID) {
        if (cart != null) {
            if (isExistedProduct(productID)) {
                cart.remove(productID);
                setNumOfItems(this.numOfItems-1);
            }
        }
    }

    public void increaseQuantity(String productID, float unitPrice) {
        if (cart != null) {
            if (isExistedProduct(productID)) {
                OrderDetails orderDetails = cart.get(productID);
                int newQuantity = orderDetails.getQuantity() + 1;
                orderDetails.setQuantity(newQuantity);
                float newPrice = newQuantity * unitPrice;
                orderDetails.setPrice(newPrice);
                cart.put(productID, orderDetails);
            }
        }
    }

    public void decreaseQuantity(String productID, float unitPrice) {
        if (cart != null) {
            if (isExistedProduct(productID)) {
                OrderDetails orderDetails = cart.get(productID);
                int newQuantity = orderDetails.getQuantity() - 1;
                if (newQuantity >= 1) {
                    orderDetails.setQuantity(newQuantity);
                    float newPrice = newQuantity * unitPrice;
                    orderDetails.setPrice(newPrice);
                    cart.put(productID, orderDetails);
                } else {
                    removeProduct(productID);
                }
            }
        }
    }

    public boolean isExistedProduct(String productID) {
        if (cart != null) {
            if (cart.keySet().contains(productID)) {
                return true;
            }
        }
        return false;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }
    
    

}
