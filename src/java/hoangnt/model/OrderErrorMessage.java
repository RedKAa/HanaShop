/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

/**
 *
 * @author user
 */
public class OrderErrorMessage {
    private String notAvailableQuantity;
    private String userRole;
    private String emptyCart;

    public OrderErrorMessage() {
    }

    public OrderErrorMessage(String notAvailableQuantity, String userRole, String emptyCart) {
        this.notAvailableQuantity = notAvailableQuantity;
        this.userRole = userRole;
        this.emptyCart = emptyCart;
    }

    public String getEmptyCart() {
        return emptyCart;
    }

    public void setEmptyCart(String emptyCart) {
        this.emptyCart = emptyCart;
    }



    public String getNotAvailableQuantity() {
        return notAvailableQuantity;
    }

    public void setNotAvailableQuantity(String notAvailableQuantity) {
        this.notAvailableQuantity = notAvailableQuantity;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    
    
}
