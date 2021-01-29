/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao.impl;

import hoangnt.dao.CategoryDao;
import hoangnt.model.Category;
import hoangnt.utilities.DBUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class CategoryDaoImpl implements CategoryDao{

    @Override
    public List<Category> getAll() throws SQLException, NamingException {
       Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Category> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select categoryID, name from tblCategory";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("categoryID");
                    String name = rs.getString("name");
                    Category dto = new Category(id, name);
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
    
}
