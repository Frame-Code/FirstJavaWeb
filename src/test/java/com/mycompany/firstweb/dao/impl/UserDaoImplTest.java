package com.mycompany.firstweb.dao.impl;

import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Artist-Code
 */
public class UserDaoImplTest {
    private UserDao dao;

    public UserDaoImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.dao = new UserDaoImpl();
    }

    /**
     * Test of getById method, of class UserDaoImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        System.out.println(dao.getById(1L).toString());
    }

    /**
     * Test of getAll method, of class UserDaoImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        System.out.println(dao.getAll().toString());
    }

    /**
     * Test of deleteById method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteById() {
        System.out.println("deleteById");
        dao.deleteById(1L);
    }

    /**
     * Test of create method, of class UserDaoImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        User user = new User("Daniel", "Mora", "0967972428");
        dao.create(user);
    }

    /**
     * Test of update method, of class UserDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        User user = dao.getById(1L);
        user.setPhone("0941239261");
        dao.update(user);
    }

}
