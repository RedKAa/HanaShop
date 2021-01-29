package hoangnt.dao.impl;

import hoangnt.dao.ProductDao;
import hoangnt.model.Product;
import hoangnt.utilities.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean insert(Product product) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblProduct (productID, productName, productImage, productDescription, productPrice, quantity, [status], createDate, categoryID)\n"
                        + "values(?, ?,?,?,?,?,?,getDate() ,?)\n";
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getId());
                stm.setString(2, product.getName());
                stm.setString(3, product.getImage());
                stm.setString(4, product.getDescription());
                stm.setString(5, product.getPrice() + "");
                stm.setString(6, product.getQuantity() + "");
                stm.setBoolean(7, true);
                stm.setString(8, product.getCategoryID());
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
    public boolean edit(Product product) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct\n"
                        + "SET productName = ?, productImage = ?, productDescription = ?, productPrice = ?, lastUpdate = getDate(), quantity = ?,  [status] = ?, categoryID = ?\n"
                        + "WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getName());
                stm.setString(2, product.getImage());
                stm.setString(3, product.getDescription());
                stm.setString(4, product.getPrice() + "");
                stm.setString(5, product.getQuantity() + "");
                stm.setBoolean(6, true);
                stm.setString(7, product.getCategoryID());
                stm.setString(8, product.getId());
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
    public boolean delete(String productID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct\n"
                        + "SET lastUpdate = getDate(), [status] = 0\n"
                        + "WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
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
    public Product get(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Product result = null;
        int quantity = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select productID, productName, productImage, productDescription, productPrice, quantity\n"
                        + "from tblProduct\n"
                        + "where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String idP = rs.getString("productID");
                    String name = rs.getString("productName");
                    String image = rs.getString("productImage");
                    String description = rs.getString("productDescription");
                    float price = rs.getFloat("productPrice");
                    quantity = rs.getInt("quantity");
                    result = new Product(idP, name, image, description, price, quantity);
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
    public List<Product> seachByCategory(String cid, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productImage, productDescription, productPrice, createDate, quantity, [status], categoryID\n"
                        + "FROM (\n"
                        + "SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *\n"
                        + "FROM tblProduct\n"
                        + "WHERE categoryID = ?\n"
                        + ") AS x\n"
                        + "WHERE i BETWEEN ? and ?\n";
                if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, cid);
                stm.setInt(2, beginIndex);
                stm.setInt(3, endIndex);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("productName");
                    String image = rs.getString("productImage");
                    String description = rs.getString("productDescription");
                    float price = rs.getFloat("productPrice");
                    Date createDate = rs.getDate("createDate");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("categoryID");
                    Product dto = new Product(id, name, image, description, price, createDate, quantity, status, cateID);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
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
    public int countSearchByCategory(String cid, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(productID) as total\n"
                        + "FROM tblProduct\n"
                        + "WHERE categoryID = ?\n";
                if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 and [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, cid);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = Integer.parseInt(rs.getString("total"));
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
    public int countAll(boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(productID) as total\n"
                        + "FROM tblProduct\n";
                if (isAdmin) {
                    sql = sql.concat("WHERE quantity > 0 and [status] = 1");
                }

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = Integer.parseInt(rs.getString("total"));
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
    public String getBestSellerProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID\n"
                        + "FROM  findBestSeller()";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = rs.getString("productID");
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
    public List<Product> getAll(int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productImage, productDescription, productPrice, createDate, quantity, [status], categoryID\n"
                        + "FROM (\n"
                        + "SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *\n"
                        + "FROM tblProduct\n"
                        + ") AS x\n"
                        + "WHERE i BETWEEN ? and ?\n";
                if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setInt(1, beginIndex);
                stm.setInt(2, endIndex);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("productName");
                    String image = rs.getString("productImage");
                    String description = rs.getString("productDescription");
                    float price = rs.getFloat("productPrice");
                    Date createDate = rs.getDate("createDate");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("categoryID");
                    System.out.println(id+"_"+name+"_"+image+"_"+description+"_"+price+"_"+createDate+"_"+quantity+"_"+status+"_"+cateID);
                    Product dto = new Product(id, name, image, description, price, createDate, quantity, status, cateID);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
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
    public List<Product> seachByName(String productName, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productImage, productDescription, productPrice, createDate, quantity, [status], categoryID\n"
                        + "FROM (\n"
                        + "	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *\n"
                        + "	FROM tblProduct\n"
                        + "	WHERE productName LIKE ?\n"
                        + ") AS x\n"
                        + "WHERE i BETWEEN ? and ?\n";
                if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + productName + "%");
                stm.setInt(2, beginIndex);
                stm.setInt(3, endIndex);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("productName");
                    String image = rs.getString("productImage");
                    String description = rs.getString("productDescription");
                    float price = rs.getFloat("productPrice");
                    Date createDate = rs.getDate("createDate");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("categoryID");
                    Product dto = new Product(id, name, image, description, price, createDate, quantity, status, cateID);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
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
    public int countSearchByName(String productName, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select COUNT(productID) as total\n"
                        + " from tblProduct\n"
                        + " where productName LIKE ?\n";
                 if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + productName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = Integer.parseInt(rs.getString("total"));
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
    public List<Product> seachByPrice(long min, long max, int beginIndex, int endIndex, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productImage, productDescription, productPrice, createDate, quantity, [status], categoryID\n"
                        + "FROM (\n"
                        + "	SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(DATE, createDate) DESC) AS i, *\n"
                        + "	FROM tblProduct\n"
                        + "	WHERE productPrice BETWEEN ? AND ?\n"
                        + ") AS x\n"
                        + "WHERE i BETWEEN ? and ?\n";
                if (!isAdmin) {
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setLong(1, min);
                stm.setLong(2, max);
                stm.setInt(3, beginIndex);
                stm.setInt(4, endIndex);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("productName");
                    String image = rs.getString("productImage");
                    String description = rs.getString("productDescription");
                    float price = rs.getFloat("productPrice");
                    Date createDate = rs.getDate("createDate");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    String cateID = rs.getString("categoryID");
                    Product dto = new Product(id, name, image, description, price, createDate, quantity, status, cateID);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
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
    public int countSearchByPrice(long min, long max, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(productID) as total\n"
                        + "FROM tblProduct\n"
                        + "WHERE productPrice BETWEEN ? AND ?\n";
                if(!isAdmin){
                    sql = sql.concat("AND quantity > 0 AND [status] = 1");
                }
                stm = con.prepareStatement(sql);
                stm.setLong(1, min);
                stm.setLong(2, max);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = Integer.parseInt(rs.getString("total"));
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

}
