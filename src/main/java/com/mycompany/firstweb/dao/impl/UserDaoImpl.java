package com.mycompany.firstweb.dao.impl;

import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.model.User;
import java.util.List;
import java.lang.IllegalArgumentException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Artist-Code
 */
public class UserDaoImpl implements UserDao {

    private final String TITLE_ERROR_MESSAGE = this.getClass().getName() + ": ";
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
        String jpql = "SELECT u FROM User u WHERE u.idUser = :id";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", id);
        User user;
        try {
            user = query.getSingleResult();
            return user;
        } catch (NoResultException | NonUniqueResultException e) {
            System.out.println(TITLE_ERROR_MESSAGE + e.getMessage());
            return null;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
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
        } catch (NoResultException | NonUniqueResultException e) {
            System.out.println(TITLE_ERROR_MESSAGE + e.getMessage());
            return null;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.getReference(User.class, id);
            em.remove(user);
            em.getTransaction().commit();
        } catch (IllegalStateException | TransactionRequiredException | IllegalArgumentException | EntityNotFoundException | RollbackException e) {
            System.out.println(TITLE_ERROR_MESSAGE + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public void create(User object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (IllegalStateException | EntityExistsException | TransactionRequiredException e) {
            System.out.println(TITLE_ERROR_MESSAGE + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (IllegalStateException | EntityExistsException | TransactionRequiredException | IllegalArgumentException e) {
            System.out.println(TITLE_ERROR_MESSAGE + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
