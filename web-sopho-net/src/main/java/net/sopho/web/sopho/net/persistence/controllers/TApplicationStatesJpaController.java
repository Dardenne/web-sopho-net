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
import net.sopho.web.sopho.net.persistence.entities.TApplicationStates;

/**
 *
 * @author LU01007
 */
public class TApplicationStatesJpaController implements Serializable {

    public TApplicationStatesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TApplicationStates TApplicationStates) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TApplicationStates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTApplicationStates(TApplicationStates.getApplNsq()) != null) {
                throw new PreexistingEntityException("TApplicationStates " + TApplicationStates + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TApplicationStates TApplicationStates) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TApplicationStates = em.merge(TApplicationStates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TApplicationStates.getApplNsq();
                if (findTApplicationStates(id) == null) {
                    throw new NonexistentEntityException("The tApplicationStates with id " + id + " no longer exists.");
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
            TApplicationStates TApplicationStates;
            try {
                TApplicationStates = em.getReference(TApplicationStates.class, id);
                TApplicationStates.getApplNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TApplicationStates with id " + id + " no longer exists.", enfe);
            }
            em.remove(TApplicationStates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TApplicationStates> findTApplicationStatesEntities() {
        return findTApplicationStatesEntities(true, -1, -1);
    }

    public List<TApplicationStates> findTApplicationStatesEntities(int maxResults, int firstResult) {
        return findTApplicationStatesEntities(false, maxResults, firstResult);
    }

    private List<TApplicationStates> findTApplicationStatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TApplicationStates.class));
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

    public TApplicationStates findTApplicationStates(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TApplicationStates.class, id);
        } finally {
            em.close();
        }
    }

    public int getTApplicationStatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TApplicationStates> rt = cq.from(TApplicationStates.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
