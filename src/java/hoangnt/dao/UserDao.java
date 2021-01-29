/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.dao;
import hoangnt.model.User;
import java.sql.SQLException;
import javax.naming.NamingException;
/**
 *
 * @author user
 */
public interface UserDao {
    User login(String id, String password) throws SQLException, NamingException;;
}
