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
import net.sopho.web.sopho.net.persistence.entities.TMailMessages;

/**
 *
 * @author LU01007
 */
public class TMailMessagesJpaController implements Serializable {

    public TMailMessagesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TMailMessages TMailMessages) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TMailMessages);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTMailMessages(TMailMessages.getMmsgNsq()) != null) {
                throw new PreexistingEntityException("TMailMessages " + TMailMessages + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TMailMessages TMailMessages) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMailMessages = em.merge(TMailMessages);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TMailMessages.getMmsgNsq();
                if (findTMailMessages(id) == null) {
                    throw new NonexistentEntityException("The tMailMessages with id " + id + " no longer exists.");
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
            TMailMessages TMailMessages;
            try {
                TMailMessages = em.getReference(TMailMessages.class, id);
                TMailMessages.getMmsgNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TMailMessages with id " + id + " no longer exists.", enfe);
            }
            em.remove(TMailMessages);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TMailMessages> findTMailMessagesEntities() {
        return findTMailMessagesEntities(true, -1, -1);
    }

    public List<TMailMessages> findTMailMessagesEntities(int maxResults, int firstResult) {
        return findTMailMessagesEntities(false, maxResults, firstResult);
    }

    private List<TMailMessages> findTMailMessagesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TMailMessages.class));
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

    public TMailMessages findTMailMessages(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TMailMessages.class, id);
        } finally {
            em.close();
        }
    }

    public int getTMailMessagesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TMailMessages> rt = cq.from(TMailMessages.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
