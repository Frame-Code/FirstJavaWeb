package com.mycompany.firstweb.dao.impl;

import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.model.User;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Artist-Code
 */
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory emf;

    public UserDaoImpl() {
        this.emf = Persistence.createEntityManagerFactory("webPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public User getById(Long id) {
        EntityManager em = getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.id = :id ";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", id);
        User user;
        try {
            user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> getAll() {
        EntityManager em = getEntityManager();
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users;
        try {
            users = query.getResultList();
            return users;
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();
        em.remove(getById(id));
    }

    @Override
    public void create(User object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (IllegalStateException | EntityExistsException | TransactionRequiredException e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User object) {
        EntityManager em = getEntityManager();
        em.merge(em);
    }

}
