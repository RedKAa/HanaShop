/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service.impl;

import hoangnt.dao.impl.HistoryLogDaoImpl;
import hoangnt.dao.impl.ProductDaoImpl;
import hoangnt.model.Product;
import hoangnt.service.ProductService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class ProductServiceImpl implements ProductService {

    @Override
    public boolean insert(Product product, String userID) {
        boolean flag = true;
        try {
            ProductDaoImpl proDao = new ProductDaoImpl();
            if (!proDao.insert(product)) {
                flag = false;
            }
            HistoryLogDaoImpl logDao = new HistoryLogDaoImpl();
            if (!logDao.writeLog(product.getId(), userID, "Add new product")) {
                flag = false;
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public boolean edit(Product product, String userID) {
        boolean flag = true;
        try {
            ProductDaoImpl proDao = new ProductDaoImpl();
            if (!proDao.edit(product)) {
                flag = false;
            }
            HistoryLogDaoImpl logDao = new HistoryLogDaoImpl();
            if (!logDao.writeLog(product.getId(), userID, "Update product")) {
                flag = false;
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public boolean delete(String productID, String userID) {
        boolean flag = true;
        try {
            ProductDaoImpl proDao = new ProductDaoImpl();
            if (!proDao.delete(productID)) {
                flag = false;
            }
            HistoryLogDaoImpl logDao = new HistoryLogDaoImpl();
            if (!logDao.writeLog(productID, userID, "Turn product to inactive status")) {
                flag = false;
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public Product get(String id) {
        Product p = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            p = dao.get(id);
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return p;
    }

    @Override
    public List<Product> getAll(int beginIndex, int endIndex, boolean isAdmin) {
        List<Product> list = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            list = dao.getAll(beginIndex, endIndex, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int countAll(boolean isAdmin) {
        int result = 0;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            result = dao.countAll(isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Product> seachByCategory(String cid, int beginIndex, int endIndex, boolean isAdmin) {
        List<Product> list = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            list = dao.seachByCategory(cid, beginIndex, endIndex, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int countSearchByCategory(String cid, boolean isAdmin) {
        int result = 0;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            result = dao.countSearchByCategory(cid, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Product> seachByName(String productName, int beginIndex, int endIndex, boolean isAdmin) {
        List<Product> list = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            list = dao.seachByName(productName, beginIndex, endIndex, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int countSearchByName(String productName, boolean isAdmin) {
        int result = 0;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            result = dao.countSearchByName(productName, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Product> seachByPrice(long min, long max, int beginIndex, int endIndex, boolean isAdmin) {
        List<Product> list = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            list = dao.seachByPrice(min, max, beginIndex, endIndex, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int countSearchByPrice(long min, long max, boolean isAdmin) {
        int result = 0;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            result = dao.countSearchByPrice(min, max, isAdmin);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Product getBestSellerProduct() {
        Product result = null;
        try {
            ProductDaoImpl dao = new ProductDaoImpl();
            String productID = dao.getBestSellerProduct();
            result = dao.get(productID);
        } catch (SQLException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
