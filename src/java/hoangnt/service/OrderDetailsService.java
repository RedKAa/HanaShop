/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service;

import hoangnt.model.OrderDetails;
import java.util.List;

/**
 *
 * @author user
 */
public interface OrderDetailsService {

    boolean insert(OrderDetails orderDetails);

    boolean update(OrderDetails orderDetails);

    boolean delete(OrderDetails orderDetails);

    List<OrderDetails> getAllByOrderID(String orderID);
}
