/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao;

import java.sql.SQLException;
import javax.naming.NamingException;
import hoangnt.model.Order;
import java.util.List;

/**
 *
 * @author user
 */
public interface OrderDao {
    boolean insert(Order order) throws SQLException, NamingException;
    boolean delete(Order order) throws SQLException, NamingException;
    boolean update(Order order) throws SQLException, NamingException;
    Order get(String orderId) throws SQLException, NamingException;
    List<Order> getAllOrdersByUser(String userID) throws SQLException, NamingException;
    
}
