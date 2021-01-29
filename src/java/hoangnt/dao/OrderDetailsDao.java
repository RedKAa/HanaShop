/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao;
import hoangnt.model.OrderDetails;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public interface OrderDetailsDao {
    boolean insert(OrderDetails orderDetails) throws SQLException, NamingException;
    boolean update(OrderDetails orderDetails) throws SQLException, NamingException;
    boolean delete(OrderDetails orderDetails) throws SQLException, NamingException;
    List<OrderDetails> getAllByOrderID(String orderID) throws SQLException, NamingException;
    
}
