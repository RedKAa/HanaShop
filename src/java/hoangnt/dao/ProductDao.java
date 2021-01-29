/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao;

import hoangnt.model.Product;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public interface ProductDao {

    //ADMIN ONLY
    boolean insert(Product product) throws SQLException, NamingException;//done

    boolean edit(Product product) throws SQLException, NamingException;//done

    boolean delete(String productID) throws SQLException, NamingException;//done

    //ALL ROLE
    Product get(String id) throws SQLException, NamingException; //done

    List<Product> getAll(int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException;//done

    int countAll(boolean isAdmin) throws SQLException, NamingException; // done

    List<Product> seachByCategory(String cid, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException;//done

    int countSearchByCategory(String cid, boolean isAdmin) throws SQLException, NamingException;//done

    List<Product> seachByName(String productName, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException;//done

    int countSearchByName(String productName, boolean isAdmin) throws SQLException, NamingException;//done

    List<Product> seachByPrice(long min, long max, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException;//done

    int countSearchByPrice(long min, long max, boolean isAdmin) throws SQLException, NamingException;

    String getBestSellerProduct() throws SQLException, NamingException; //done
}
