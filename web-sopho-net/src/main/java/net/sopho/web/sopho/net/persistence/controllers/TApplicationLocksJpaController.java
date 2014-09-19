/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TApplicationLocks;

/**
 *
 * @author LU01007
 */
public class TApplicationLocksJpaController implements Serializable {

    public TApplicationLocksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TApplicationLocks TApplicationLocks) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TApplicationLocks);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTApplicationLocks(TApplicationLocks.getAlckNsq()) != null) {
                throw new PreexistingEntityException("TApplicationLocks " + TApplicationLocks + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TApplicationLocks TApplicationLocks) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TApplicationLocks = em.merge(TApplicationLocks);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TApplicationLocks.getAlckNsq();
                if (findTApplicationLocks(id) == null) {
                    throw new NonexistentEntityException("The tApplicationLocks with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TApplicationLocks TApplicationLocks;
            try {
                TApplicationLocks = em.getReference(TApplicationLocks.class, id);
                TApplicationLocks.getAlckNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TApplicationLocks with id " + id + " no longer exists.", enfe);
            }
            em.remove(TApplicationLocks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TApplicationLocks> findTApplicationLocksEntities() {
        return findTApplicationLocksEntities(true, -1, -1);
    }

    public List<TApplicationLocks> findTApplicationLocksEntities(int maxResults, int firstResult) {
        return findTApplicationLocksEntities(false, maxResults, firstResult);
    }

    private List<TApplicationLocks> findTApplicationLocksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TApplicationLocks.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TApplicationLocks findTApplicationLocks(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TApplicationLocks.class, id);
        } finally {
            em.close();
        }
    }

    public int getTApplicationLocksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TApplicationLocks> rt = cq.from(TApplicationLocks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
