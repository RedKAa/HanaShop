/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service;

import hoangnt.model.User;

/**
 *
 * @author user
 */
public interface AccountService {
    User login(String id, String password);
}
