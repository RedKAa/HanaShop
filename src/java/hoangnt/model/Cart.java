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

    public Cart() {
    }

    private boolean addToCart(String productID, int quantity,float unitPrice) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        if(isExistedProduct(productID)){
            OrderDetails orderDetail = cart.get(productID);
            int newQuantity = orderDetail.getQuantity() + quantity;
            orderDetail.setQuantity(newQuantity);
            orderDetail.setPrice(unitPrice*newQuantity);
            return cart.put(productID, orderDetail) != null;
        }else{
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setID(UUID.randomUUID().toString().substring(0, 20));
            orderDetail.setProductID(productID);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(unitPrice*quantity);
             return cart.put(productID, orderDetail) != null;
        }
    }
    
    private void removeProduct(String productID){
        if(cart!= null){
            if(isExistedProduct(productID)){
                cart.remove(productID);
            }
        }
    }
    
    

    private boolean isExistedProduct(String productID) {
        if (cart != null) {
            if(cart.keySet().contains(productID)){
                return true;
            }
        }
            return false;
    }
}
