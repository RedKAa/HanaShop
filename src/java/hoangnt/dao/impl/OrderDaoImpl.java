/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao.impl;

import hoangnt.dao.OrderDao;
import hoangnt.model.Order;
import hoangnt.utilities.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean insert(Order order) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblOrder(orderID, userID)\n"
                        + "values(?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getOrderID());
                stm.setString(2, order.getUserID());
                int row = stm.executeUpdate();
                return row == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Order order) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM [dbo].[tblOrder] \n"
                        + "WHERE orderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getOrderID());
                int row = stm.executeUpdate();
                return row == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Update tblOrder\n"
                        + "set shippingAddresss = ?, userPhoneNumber = ?, payment_success = ?\n"
                        + "where orderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getShippingAddress());
                stm.setString(2, order.getPhone());
                stm.setBoolean(3, order.isPaymentSuccess());
                stm.setString(4, order.getOrderID());
                int row = stm.executeUpdate();
                return row == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    @Override
    public Order get(String orderId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Order result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = " select orderID, userID, shippingAddresss, userPhoneNumber, orderDate, totalPrice, payment_success, payment\n"
                        + " from tblOrder\n"
                        + " where orderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    String shippingAddresss = rs.getString("shippingAddresss");
                    String userPhoneNumber = rs.getString("userPhoneNumber");
                    Date orderDate = rs.getDate("orderDate");
                    Float totalPrice = rs.getFloat("totalPrice");
                    Boolean paymentSuccess = rs.getBoolean("payment_success");
                    String payment = rs.getString("payment");
                    result = new Order(orderID, userID, payment, paymentSuccess, shippingAddresss, userPhoneNumber, orderDate, totalPrice);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    @Override
    public List<Order> getAllOrdersByUser(String userID) throws SQLException, NamingException {
        return null;
    }

}
