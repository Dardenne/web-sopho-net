/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import net.sopho.web.sopho.net.persistence.entities.TVersions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TMajorVersionNumbers;

/**
 *
 * @author LU01007
 */
public class TMajorVersionNumbersJpaController implements Serializable {

    public TMajorVersionNumbersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TMajorVersionNumbers TMajorVersionNumbers) throws PreexistingEntityException, Exception {
        if (TMajorVersionNumbers.getTVersionsCollection() == null) {
            TMajorVersionNumbers.setTVersionsCollection(new ArrayList<TVersions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TVersions> attachedTVersionsCollection = new ArrayList<TVersions>();
            for (TVersions TVersionsCollectionTVersionsToAttach : TMajorVersionNumbers.getTVersionsCollection()) {
                TVersionsCollectionTVersionsToAttach = em.getReference(TVersionsCollectionTVersionsToAttach.getClass(), TVersionsCollectionTVersionsToAttach.getVersNsq());
                attachedTVersionsCollection.add(TVersionsCollectionTVersionsToAttach);
            }
            TMajorVersionNumbers.setTVersionsCollection(attachedTVersionsCollection);
            em.persist(TMajorVersionNumbers);
            for (TVersions TVersionsCollectionTVersions : TMajorVersionNumbers.getTVersionsCollection()) {
                TMajorVersionNumbers oldVersMavnNsqOfTVersionsCollectionTVersions = TVersionsCollectionTVersions.getVersMavnNsq();
                TVersionsCollectionTVersions.setVersMavnNsq(TMajorVersionNumbers);
                TVersionsCollectionTVersions = em.merge(TVersionsCollectionTVersions);
                if (oldVersMavnNsqOfTVersionsCollectionTVersions != null) {
                    oldVersMavnNsqOfTVersionsCollectionTVersions.getTVersionsCollection().remove(TVersionsCollectionTVersions);
                    oldVersMavnNsqOfTVersionsCollectionTVersions = em.merge(oldVersMavnNsqOfTVersionsCollectionTVersions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTMajorVersionNumbers(TMajorVersionNumbers.getMavnNsq()) != null) {
                throw new PreexistingEntityException("TMajorVersionNumbers " + TMajorVersionNumbers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TMajorVersionNumbers TMajorVersionNumbers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMajorVersionNumbers persistentTMajorVersionNumbers = em.find(TMajorVersionNumbers.class, TMajorVersionNumbers.getMavnNsq());
            Collection<TVersions> TVersionsCollectionOld = persistentTMajorVersionNumbers.getTVersionsCollection();
            Collection<TVersions> TVersionsCollectionNew = TMajorVersionNumbers.getTVersionsCollection();
            Collection<TVersions> attachedTVersionsCollectionNew = new ArrayList<TVersions>();
            for (TVersions TVersionsCollectionNewTVersionsToAttach : TVersionsCollectionNew) {
                TVersionsCollectionNewTVersionsToAttach = em.getReference(TVersionsCollectionNewTVersionsToAttach.getClass(), TVersionsCollectionNewTVersionsToAttach.getVersNsq());
                attachedTVersionsCollectionNew.add(TVersionsCollectionNewTVersionsToAttach);
            }
            TVersionsCollectionNew = attachedTVersionsCollectionNew;
            TMajorVersionNumbers.setTVersionsCollection(TVersionsCollectionNew);
            TMajorVersionNumbers = em.merge(TMajorVersionNumbers);
            for (TVersions TVersionsCollectionOldTVersions : TVersionsCollectionOld) {
                if (!TVersionsCollectionNew.contains(TVersionsCollectionOldTVersions)) {
                    TVersionsCollectionOldTVersions.setVersMavnNsq(null);
                    TVersionsCollectionOldTVersions = em.merge(TVersionsCollectionOldTVersions);
                }
            }
            for (TVersions TVersionsCollectionNewTVersions : TVersionsCollectionNew) {
                if (!TVersionsCollectionOld.contains(TVersionsCollectionNewTVersions)) {
                    TMajorVersionNumbers oldVersMavnNsqOfTVersionsCollectionNewTVersions = TVersionsCollectionNewTVersions.getVersMavnNsq();
                    TVersionsCollectionNewTVersions.setVersMavnNsq(TMajorVersionNumbers);
                    TVersionsCollectionNewTVersions = em.merge(TVersionsCollectionNewTVersions);
                    if (oldVersMavnNsqOfTVersionsCollectionNewTVersions != null && !oldVersMavnNsqOfTVersionsCollectionNewTVersions.equals(TMajorVersionNumbers)) {
                        oldVersMavnNsqOfTVersionsCollectionNewTVersions.getTVersionsCollection().remove(TVersionsCollectionNewTVersions);
                        oldVersMavnNsqOfTVersionsCollectionNewTVersions = em.merge(oldVersMavnNsqOfTVersionsCollectionNewTVersions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TMajorVersionNumbers.getMavnNsq();
                if (findTMajorVersionNumbers(id) == null) {
                    throw new NonexistentEntityException("The tMajorVersionNumbers with id " + id + " no longer exists.");
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
            TMajorVersionNumbers TMajorVersionNumbers;
            try {
                TMajorVersionNumbers = em.getReference(TMajorVersionNumbers.class, id);
                TMajorVersionNumbers.getMavnNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TMajorVersionNumbers with id " + id + " no longer exists.", enfe);
            }
            Collection<TVersions> TVersionsCollection = TMajorVersionNumbers.getTVersionsCollection();
            for (TVersions TVersionsCollectionTVersions : TVersionsCollection) {
                TVersionsCollectionTVersions.setVersMavnNsq(null);
                TVersionsCollectionTVersions = em.merge(TVersionsCollectionTVersions);
            }
            em.remove(TMajorVersionNumbers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TMajorVersionNumbers> findTMajorVersionNumbersEntities() {
        return findTMajorVersionNumbersEntities(true, -1, -1);
    }

    public List<TMajorVersionNumbers> findTMajorVersionNumbersEntities(int maxResults, int firstResult) {
        return findTMajorVersionNumbersEntities(false, maxResults, firstResult);
    }

    private List<TMajorVersionNumbers> findTMajorVersionNumbersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TMajorVersionNumbers.class));
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

    public TMajorVersionNumbers findTMajorVersionNumbers(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TMajorVersionNumbers.class, id);
        } finally {
            em.close();
        }
    }

    public int getTMajorVersionNumbersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TMajorVersionNumbers> rt = cq.from(TMajorVersionNumbers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
