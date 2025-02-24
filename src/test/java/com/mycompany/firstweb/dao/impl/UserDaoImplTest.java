package com.mycompany.firstweb.dao.impl;

import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Artist-Code
 */
public class UserDaoImplTest {
    private UserDao userDao;

    public UserDaoImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.userDao = new UserDaoImpl();
    }

    /** 
    * Test of getById method, of class UserDaoImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        ResultDTO<User> user = userDao.findById(1L);
        System.out.println(user);
    }

    /**
     * Test of getAll method, of class UserDaoImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        System.out.println(userDao.findAll().toString());
    }

    /**
     * Test of deleteById method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteById() {
        System.out.println("deleteById");
       // userDao.deleteById(1L);
    }

    /**
     * Test of create method, of class UserDaoImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        User user = new User("Daniel", "Mora Cantillo", "0941239261");
        //userDao.create(user);
    }

    /**
     * Test of update method, of class UserDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ResultDTO<User> user = userDao.findById(2L);
        user.getData().setPhone("0967971428");
        //System.out.println(userDao.update(user.getData()));
    }

}
