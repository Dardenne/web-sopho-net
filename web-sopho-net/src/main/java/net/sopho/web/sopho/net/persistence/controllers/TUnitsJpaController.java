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
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TUnitLabel;
import net.sopho.web.sopho.net.persistence.entities.TUnits;

/**
 *
 * @author LU01007
 */
public class TUnitsJpaController implements Serializable {

    public TUnitsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TUnits TUnits) throws PreexistingEntityException, Exception {
        if (TUnits.getTChapterTitlesCollection() == null) {
            TUnits.setTChapterTitlesCollection(new ArrayList<TChapterTitles>());
        }
        if (TUnits.getTUnitLabelCollection() == null) {
            TUnits.setTUnitLabelCollection(new ArrayList<TUnitLabel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TChapterTitles> attachedTChapterTitlesCollection = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionTChapterTitlesToAttach : TUnits.getTChapterTitlesCollection()) {
                TChapterTitlesCollectionTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollection.add(TChapterTitlesCollectionTChapterTitlesToAttach);
            }
            TUnits.setTChapterTitlesCollection(attachedTChapterTitlesCollection);
            Collection<TUnitLabel> attachedTUnitLabelCollection = new ArrayList<TUnitLabel>();
            for (TUnitLabel TUnitLabelCollectionTUnitLabelToAttach : TUnits.getTUnitLabelCollection()) {
                TUnitLabelCollectionTUnitLabelToAttach = em.getReference(TUnitLabelCollectionTUnitLabelToAttach.getClass(), TUnitLabelCollectionTUnitLabelToAttach.getUnlaNsq());
                attachedTUnitLabelCollection.add(TUnitLabelCollectionTUnitLabelToAttach);
            }
            TUnits.setTUnitLabelCollection(attachedTUnitLabelCollection);
            em.persist(TUnits);
            for (TChapterTitles TChapterTitlesCollectionTChapterTitles : TUnits.getTChapterTitlesCollection()) {
                TChapterTitlesCollectionTChapterTitles.getTUnitsCollection().add(TUnits);
                TChapterTitlesCollectionTChapterTitles = em.merge(TChapterTitlesCollectionTChapterTitles);
            }
            for (TUnitLabel TUnitLabelCollectionTUnitLabel : TUnits.getTUnitLabelCollection()) {
                TUnits oldUnlaUnitNsqOfTUnitLabelCollectionTUnitLabel = TUnitLabelCollectionTUnitLabel.getUnlaUnitNsq();
                TUnitLabelCollectionTUnitLabel.setUnlaUnitNsq(TUnits);
                TUnitLabelCollectionTUnitLabel = em.merge(TUnitLabelCollectionTUnitLabel);
                if (oldUnlaUnitNsqOfTUnitLabelCollectionTUnitLabel != null) {
                    oldUnlaUnitNsqOfTUnitLabelCollectionTUnitLabel.getTUnitLabelCollection().remove(TUnitLabelCollectionTUnitLabel);
                    oldUnlaUnitNsqOfTUnitLabelCollectionTUnitLabel = em.merge(oldUnlaUnitNsqOfTUnitLabelCollectionTUnitLabel);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTUnits(TUnits.getUnitNsq()) != null) {
                throw new PreexistingEntityException("TUnits " + TUnits + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TUnits TUnits) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUnits persistentTUnits = em.find(TUnits.class, TUnits.getUnitNsq());
            Collection<TChapterTitles> TChapterTitlesCollectionOld = persistentTUnits.getTChapterTitlesCollection();
            Collection<TChapterTitles> TChapterTitlesCollectionNew = TUnits.getTChapterTitlesCollection();
            Collection<TUnitLabel> TUnitLabelCollectionOld = persistentTUnits.getTUnitLabelCollection();
            Collection<TUnitLabel> TUnitLabelCollectionNew = TUnits.getTUnitLabelCollection();
            List<String> illegalOrphanMessages = null;
            for (TUnitLabel TUnitLabelCollectionOldTUnitLabel : TUnitLabelCollectionOld) {
                if (!TUnitLabelCollectionNew.contains(TUnitLabelCollectionOldTUnitLabel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TUnitLabel " + TUnitLabelCollectionOldTUnitLabel + " since its unlaUnitNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TChapterTitles> attachedTChapterTitlesCollectionNew = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitlesToAttach : TChapterTitlesCollectionNew) {
                TChapterTitlesCollectionNewTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionNewTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionNewTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollectionNew.add(TChapterTitlesCollectionNewTChapterTitlesToAttach);
            }
            TChapterTitlesCollectionNew = attachedTChapterTitlesCollectionNew;
            TUnits.setTChapterTitlesCollection(TChapterTitlesCollectionNew);
            Collection<TUnitLabel> attachedTUnitLabelCollectionNew = new ArrayList<TUnitLabel>();
            for (TUnitLabel TUnitLabelCollectionNewTUnitLabelToAttach : TUnitLabelCollectionNew) {
                TUnitLabelCollectionNewTUnitLabelToAttach = em.getReference(TUnitLabelCollectionNewTUnitLabelToAttach.getClass(), TUnitLabelCollectionNewTUnitLabelToAttach.getUnlaNsq());
                attachedTUnitLabelCollectionNew.add(TUnitLabelCollectionNewTUnitLabelToAttach);
            }
            TUnitLabelCollectionNew = attachedTUnitLabelCollectionNew;
            TUnits.setTUnitLabelCollection(TUnitLabelCollectionNew);
            TUnits = em.merge(TUnits);
            for (TChapterTitles TChapterTitlesCollectionOldTChapterTitles : TChapterTitlesCollectionOld) {
                if (!TChapterTitlesCollectionNew.contains(TChapterTitlesCollectionOldTChapterTitles)) {
                    TChapterTitlesCollectionOldTChapterTitles.getTUnitsCollection().remove(TUnits);
                    TChapterTitlesCollectionOldTChapterTitles = em.merge(TChapterTitlesCollectionOldTChapterTitles);
                }
            }
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitles : TChapterTitlesCollectionNew) {
                if (!TChapterTitlesCollectionOld.contains(TChapterTitlesCollectionNewTChapterTitles)) {
                    TChapterTitlesCollectionNewTChapterTitles.getTUnitsCollection().add(TUnits);
                    TChapterTitlesCollectionNewTChapterTitles = em.merge(TChapterTitlesCollectionNewTChapterTitles);
                }
            }
            for (TUnitLabel TUnitLabelCollectionNewTUnitLabel : TUnitLabelCollectionNew) {
                if (!TUnitLabelCollectionOld.contains(TUnitLabelCollectionNewTUnitLabel)) {
                    TUnits oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel = TUnitLabelCollectionNewTUnitLabel.getUnlaUnitNsq();
                    TUnitLabelCollectionNewTUnitLabel.setUnlaUnitNsq(TUnits);
                    TUnitLabelCollectionNewTUnitLabel = em.merge(TUnitLabelCollectionNewTUnitLabel);
                    if (oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel != null && !oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel.equals(TUnits)) {
                        oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel.getTUnitLabelCollection().remove(TUnitLabelCollectionNewTUnitLabel);
                        oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel = em.merge(oldUnlaUnitNsqOfTUnitLabelCollectionNewTUnitLabel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TUnits.getUnitNsq();
                if (findTUnits(id) == null) {
                    throw new NonexistentEntityException("The tUnits with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUnits TUnits;
            try {
                TUnits = em.getReference(TUnits.class, id);
                TUnits.getUnitNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TUnits with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TUnitLabel> TUnitLabelCollectionOrphanCheck = TUnits.getTUnitLabelCollection();
            for (TUnitLabel TUnitLabelCollectionOrphanCheckTUnitLabel : TUnitLabelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TUnits (" + TUnits + ") cannot be destroyed since the TUnitLabel " + TUnitLabelCollectionOrphanCheckTUnitLabel + " in its TUnitLabelCollection field has a non-nullable unlaUnitNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TChapterTitles> TChapterTitlesCollection = TUnits.getTChapterTitlesCollection();
            for (TChapterTitles TChapterTitlesCollectionTChapterTitles : TChapterTitlesCollection) {
                TChapterTitlesCollectionTChapterTitles.getTUnitsCollection().remove(TUnits);
                TChapterTitlesCollectionTChapterTitles = em.merge(TChapterTitlesCollectionTChapterTitles);
            }
            em.remove(TUnits);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TUnits> findTUnitsEntities() {
        return findTUnitsEntities(true, -1, -1);
    }

    public List<TUnits> findTUnitsEntities(int maxResults, int firstResult) {
        return findTUnitsEntities(false, maxResults, firstResult);
    }

    private List<TUnits> findTUnitsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TUnits.class));
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

    public TUnits findTUnits(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TUnits.class, id);
        } finally {
            em.close();
        }
    }

    public int getTUnitsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TUnits> rt = cq.from(TUnits.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
