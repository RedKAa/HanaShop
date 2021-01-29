/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao.impl;

import hoangnt.dao.HistoryLogDao;
import hoangnt.model.LogFile;
import hoangnt.utilities.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
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
public class HistoryLogDaoImpl implements HistoryLogDao {

    @Override
    public boolean writeLog(String productID, String userID, String description) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblHistoryLog(productID, userID, [description])\n"
                        + "values(?, ?, ?)\n";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setString(2, userID);
                stm.setString(3, description);
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
    public List<LogFile> getHistoryByUser(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<LogFile> result = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, userID, updateDate, [description]\n"
                        + "FROM tblHistoryLog \n"
                        + "WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String userId = rs.getString("userID");
                    Date updateDate = rs.getDate("updateDate");
                    String description = rs.getString("description");
                    LogFile dto = new LogFile(productID, userId, updateDate, description);
                    if(result == null){
                        result = new ArrayList<LogFile>();
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
