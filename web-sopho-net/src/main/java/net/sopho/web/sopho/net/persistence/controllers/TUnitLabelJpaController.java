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
import net.sopho.web.sopho.net.persistence.entities.TLabelBundles;
import net.sopho.web.sopho.net.persistence.entities.TUnitLabel;
import net.sopho.web.sopho.net.persistence.entities.TUnits;

/**
 *
 * @author LU01007
 */
public class TUnitLabelJpaController implements Serializable {

    public TUnitLabelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TUnitLabel TUnitLabel) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TLabelBundles unlaLabuNsq = TUnitLabel.getUnlaLabuNsq();
            if (unlaLabuNsq != null) {
                unlaLabuNsq = em.getReference(unlaLabuNsq.getClass(), unlaLabuNsq.getLabuNsq());
                TUnitLabel.setUnlaLabuNsq(unlaLabuNsq);
            }
            TUnits unlaUnitNsq = TUnitLabel.getUnlaUnitNsq();
            if (unlaUnitNsq != null) {
                unlaUnitNsq = em.getReference(unlaUnitNsq.getClass(), unlaUnitNsq.getUnitNsq());
                TUnitLabel.setUnlaUnitNsq(unlaUnitNsq);
            }
            em.persist(TUnitLabel);
            if (unlaLabuNsq != null) {
                unlaLabuNsq.getTUnitLabelCollection().add(TUnitLabel);
                unlaLabuNsq = em.merge(unlaLabuNsq);
            }
            if (unlaUnitNsq != null) {
                unlaUnitNsq.getTUnitLabelCollection().add(TUnitLabel);
                unlaUnitNsq = em.merge(unlaUnitNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTUnitLabel(TUnitLabel.getUnlaNsq()) != null) {
                throw new PreexistingEntityException("TUnitLabel " + TUnitLabel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TUnitLabel TUnitLabel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUnitLabel persistentTUnitLabel = em.find(TUnitLabel.class, TUnitLabel.getUnlaNsq());
            TLabelBundles unlaLabuNsqOld = persistentTUnitLabel.getUnlaLabuNsq();
            TLabelBundles unlaLabuNsqNew = TUnitLabel.getUnlaLabuNsq();
            TUnits unlaUnitNsqOld = persistentTUnitLabel.getUnlaUnitNsq();
            TUnits unlaUnitNsqNew = TUnitLabel.getUnlaUnitNsq();
            if (unlaLabuNsqNew != null) {
                unlaLabuNsqNew = em.getReference(unlaLabuNsqNew.getClass(), unlaLabuNsqNew.getLabuNsq());
                TUnitLabel.setUnlaLabuNsq(unlaLabuNsqNew);
            }
            if (unlaUnitNsqNew != null) {
                unlaUnitNsqNew = em.getReference(unlaUnitNsqNew.getClass(), unlaUnitNsqNew.getUnitNsq());
                TUnitLabel.setUnlaUnitNsq(unlaUnitNsqNew);
            }
            TUnitLabel = em.merge(TUnitLabel);
            if (unlaLabuNsqOld != null && !unlaLabuNsqOld.equals(unlaLabuNsqNew)) {
                unlaLabuNsqOld.getTUnitLabelCollection().remove(TUnitLabel);
                unlaLabuNsqOld = em.merge(unlaLabuNsqOld);
            }
            if (unlaLabuNsqNew != null && !unlaLabuNsqNew.equals(unlaLabuNsqOld)) {
                unlaLabuNsqNew.getTUnitLabelCollection().add(TUnitLabel);
                unlaLabuNsqNew = em.merge(unlaLabuNsqNew);
            }
            if (unlaUnitNsqOld != null && !unlaUnitNsqOld.equals(unlaUnitNsqNew)) {
                unlaUnitNsqOld.getTUnitLabelCollection().remove(TUnitLabel);
                unlaUnitNsqOld = em.merge(unlaUnitNsqOld);
            }
            if (unlaUnitNsqNew != null && !unlaUnitNsqNew.equals(unlaUnitNsqOld)) {
                unlaUnitNsqNew.getTUnitLabelCollection().add(TUnitLabel);
                unlaUnitNsqNew = em.merge(unlaUnitNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TUnitLabel.getUnlaNsq();
                if (findTUnitLabel(id) == null) {
                    throw new NonexistentEntityException("The tUnitLabel with id " + id + " no longer exists.");
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
            TUnitLabel TUnitLabel;
            try {
                TUnitLabel = em.getReference(TUnitLabel.class, id);
                TUnitLabel.getUnlaNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TUnitLabel with id " + id + " no longer exists.", enfe);
            }
            TLabelBundles unlaLabuNsq = TUnitLabel.getUnlaLabuNsq();
            if (unlaLabuNsq != null) {
                unlaLabuNsq.getTUnitLabelCollection().remove(TUnitLabel);
                unlaLabuNsq = em.merge(unlaLabuNsq);
            }
            TUnits unlaUnitNsq = TUnitLabel.getUnlaUnitNsq();
            if (unlaUnitNsq != null) {
                unlaUnitNsq.getTUnitLabelCollection().remove(TUnitLabel);
                unlaUnitNsq = em.merge(unlaUnitNsq);
            }
            em.remove(TUnitLabel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TUnitLabel> findTUnitLabelEntities() {
        return findTUnitLabelEntities(true, -1, -1);
    }

    public List<TUnitLabel> findTUnitLabelEntities(int maxResults, int firstResult) {
        return findTUnitLabelEntities(false, maxResults, firstResult);
    }

    private List<TUnitLabel> findTUnitLabelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TUnitLabel.class));
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

    public TUnitLabel findTUnitLabel(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TUnitLabel.class, id);
        } finally {
            em.close();
        }
    }

    public int getTUnitLabelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TUnitLabel> rt = cq.from(TUnitLabel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
