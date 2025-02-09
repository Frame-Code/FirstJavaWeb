package com.mycompany.firstweb.dao.impl;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final EntityManagerFactory emf;
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());

    public UserDaoImpl() {
        this.emf = Persistence.createEntityManagerFactory("webPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public ResultDTO<User> findById(Long id) {
        EntityManager em = getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.idUser = :id";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", id);
        try {
            return new ResultDTO<>(query.getSingleResult(), true, null);
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, "User with the ID: {0} not found", id);
            return new ResultDTO<>(null, false, "User with the ID " + id + " not found");
        } catch (NonUniqueResultException e) {
            LOG.log(Level.SEVERE, "There are multple users with the ID: {0}", id);
            throw e;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unexpected error to find the user with the ID {0}", id);
            throw new RuntimeException("Unexpected error");
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public ResultDTO<List<User>> findAll() {
        EntityManager em = getEntityManager();
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        try {
            return new ResultDTO<>(query.getResultList(), true, null);
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, "Users not founded");
            return new ResultDTO<>(null, false, "Users not founded");
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public ResultDTO<User> deleteById(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.getReference(User.class, id);
            em.remove(user);
            em.getTransaction().commit();
            return new ResultDTO<>(null, true, "User deleted");
        } catch (IllegalStateException e) {
            LOG.log(Level.WARNING, "Ilegal state exception");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 1. Call to systems");
        } catch (EntityNotFoundException e) {
            LOG.log(Level.WARNING, "User to be deleted with the ID {0} not found", id);
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "User with the ID " + id + " not found");
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "The argument getted is incorrect");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 2. Call to systems");
        } catch (TransactionRequiredException e) {
            LOG.log(Level.SEVERE, "The transaction is not initialized");
            em.getTransaction().rollback();
            throw e;
        } catch (RollbackException e) {
            LOG.log(Level.SEVERE, "Commit of the transaction failed");
            em.getTransaction().rollback();
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public ResultDTO<User> create(User object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return new ResultDTO<>(null, true, "User created correctly");
        } catch (IllegalStateException e) {
            LOG.log(Level.WARNING, "Ilegal state exception");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 1. Call to systems");
        } catch (EntityExistsException e) {
            LOG.log(Level.WARNING, "The entity {0} already exists", object);
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "The user is registered");
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Argument getted is incorrect");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 2. Call to systems");
        } catch (RollbackException e) {
            LOG.log(Level.SEVERE, "Commit of the transaction failed");
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            LOG.log(Level.SEVERE, "The transaction is not initialized");
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public ResultDTO<User> update(User object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
            return new ResultDTO<>(null, true, "User updated correctly");
        } catch (IllegalStateException e) {
            LOG.log(Level.WARNING, "Ilegal state exception");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 1. Call to systems");
        } catch (TransactionRequiredException e) {
            LOG.log(Level.SEVERE, "The transaction is not initialized");
            em.getTransaction().rollback();
            throw e;
        }catch (RollbackException e) {
            LOG.log(Level.SEVERE, "Commit of the transaction failed");
            em.getTransaction().rollback();
            throw e;
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Argument getted is incorrect");
            em.getTransaction().rollback();
            return new ResultDTO<>(null, false, "Internal Error 2. Call to systems");
        } finally {
            em.close();
        }
    }

}
