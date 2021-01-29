/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao;

import hoangnt.model.LogFile;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public interface HistoryLogDao {
    
    boolean writeLog(String productID, String userID, String description) throws SQLException, NamingException;
    
    List<LogFile> getHistoryByUser(String userID) throws SQLException, NamingException;
}
