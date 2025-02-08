package com.mycompany.firstweb.model;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Artist-Code
 */
public class UserTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserTest() {
    }

    @BeforeEach
    public void setUp() {
        this.emf = Persistence.createEntityManagerFactory("webPU");
        this.em = emf.createEntityManager();

    }

    @Test
    public void testCreateTable() {
        User user = new User("Daniel", "Mora", "0967972428");
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (IllegalStateException | EntityExistsException | TransactionRequiredException e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
