/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service.impl;

import hoangnt.dao.impl.UserDaoImp;
import hoangnt.model.User;
import hoangnt.service.AccountService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public User login(String id, String password) {
        User result = null;
        try {
            UserDaoImp dao = new UserDaoImp();
            result = dao.login(id, password);
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

}
