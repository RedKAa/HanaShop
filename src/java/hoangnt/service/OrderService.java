/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service;

import hoangnt.model.Order;
import java.util.List;

/**
 *
 * @author user
 */
public interface OrderService {

    boolean insert(Order order);

    boolean delete(Order order);

    boolean update(Order order);

    Order get(String orderId);

     List<Order> getAllOrdersByUser(String userID) ;
    
    
}
