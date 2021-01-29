/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao.impl;

import hoangnt.dao.UserDao;
import hoangnt.model.User;
import hoangnt.utilities.DBUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class UserDaoImp implements UserDao {

    @Override
    public User login(String id, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        User result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, [password], fullName, isAdmin \n"
                        + "FROM dbo.tblUser \n"
                        + "WHERE userID = ? COLLATE SQL_Latin1_General_CP1_CS_AS \n"
                        + "AND [password] = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String userPassword = rs.getString("password");
                    String fullname = rs.getString("fullName");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new User(userID, "", fullname, isAdmin);
                    //User will be stored in session, set password to "" for more security
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
