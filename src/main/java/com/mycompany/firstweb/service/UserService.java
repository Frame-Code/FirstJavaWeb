package com.mycompany.firstweb.service;

import com.mycompany.firstweb.dao.impl.UserDaoImpl;
import com.mycompany.firstweb.dao.interfaces.UserDao;

/**
 *
 * @author Artist-Code
 */
public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }
    
}
