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
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TUnitLabel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TLabelBundles;

/**
 *
 * @author LU01007
 */
public class TLabelBundlesJpaController implements Serializable {

    public TLabelBundlesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TLabelBundles TLabelBundles) throws PreexistingEntityException, Exception {
        if (TLabelBundles.getTUnitLabelCollection() == null) {
            TLabelBundles.setTUnitLabelCollection(new ArrayList<TUnitLabel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentLanguages labuDlanNsq = TLabelBundles.getLabuDlanNsq();
            if (labuDlanNsq != null) {
                labuDlanNsq = em.getReference(labuDlanNsq.getClass(), labuDlanNsq.getDlanNsq());
                TLabelBundles.setLabuDlanNsq(labuDlanNsq);
            }
            Collection<TUnitLabel> attachedTUnitLabelCollection = new ArrayList<TUnitLabel>();
            for (TUnitLabel TUnitLabelCollectionTUnitLabelToAttach : TLabelBundles.getTUnitLabelCollection()) {
                TUnitLabelCollectionTUnitLabelToAttach = em.getReference(TUnitLabelCollectionTUnitLabelToAttach.getClass(), TUnitLabelCollectionTUnitLabelToAttach.getUnlaNsq());
                attachedTUnitLabelCollection.add(TUnitLabelCollectionTUnitLabelToAttach);
            }
            TLabelBundles.setTUnitLabelCollection(attachedTUnitLabelCollection);
            em.persist(TLabelBundles);
            if (labuDlanNsq != null) {
                labuDlanNsq.getTLabelBundlesCollection().add(TLabelBundles);
                labuDlanNsq = em.merge(labuDlanNsq);
            }
            for (TUnitLabel TUnitLabelCollectionTUnitLabel : TLabelBundles.getTUnitLabelCollection()) {
                TLabelBundles oldUnlaLabuNsqOfTUnitLabelCollectionTUnitLabel = TUnitLabelCollectionTUnitLabel.getUnlaLabuNsq();
                TUnitLabelCollectionTUnitLabel.setUnlaLabuNsq(TLabelBundles);
                TUnitLabelCollectionTUnitLabel = em.merge(TUnitLabelCollectionTUnitLabel);
                if (oldUnlaLabuNsqOfTUnitLabelCollectionTUnitLabel != null) {
                    oldUnlaLabuNsqOfTUnitLabelCollectionTUnitLabel.getTUnitLabelCollection().remove(TUnitLabelCollectionTUnitLabel);
                    oldUnlaLabuNsqOfTUnitLabelCollectionTUnitLabel = em.merge(oldUnlaLabuNsqOfTUnitLabelCollectionTUnitLabel);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTLabelBundles(TLabelBundles.getLabuNsq()) != null) {
                throw new PreexistingEntityException("TLabelBundles " + TLabelBundles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TLabelBundles TLabelBundles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TLabelBundles persistentTLabelBundles = em.find(TLabelBundles.class, TLabelBundles.getLabuNsq());
            TDocumentLanguages labuDlanNsqOld = persistentTLabelBundles.getLabuDlanNsq();
            TDocumentLanguages labuDlanNsqNew = TLabelBundles.getLabuDlanNsq();
            Collection<TUnitLabel> TUnitLabelCollectionOld = persistentTLabelBundles.getTUnitLabelCollection();
            Collection<TUnitLabel> TUnitLabelCollectionNew = TLabelBundles.getTUnitLabelCollection();
            List<String> illegalOrphanMessages = null;
            for (TUnitLabel TUnitLabelCollectionOldTUnitLabel : TUnitLabelCollectionOld) {
                if (!TUnitLabelCollectionNew.contains(TUnitLabelCollectionOldTUnitLabel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TUnitLabel " + TUnitLabelCollectionOldTUnitLabel + " since its unlaLabuNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (labuDlanNsqNew != null) {
                labuDlanNsqNew = em.getReference(labuDlanNsqNew.getClass(), labuDlanNsqNew.getDlanNsq());
                TLabelBundles.setLabuDlanNsq(labuDlanNsqNew);
            }
            Collection<TUnitLabel> attachedTUnitLabelCollectionNew = new ArrayList<TUnitLabel>();
            for (TUnitLabel TUnitLabelCollectionNewTUnitLabelToAttach : TUnitLabelCollectionNew) {
                TUnitLabelCollectionNewTUnitLabelToAttach = em.getReference(TUnitLabelCollectionNewTUnitLabelToAttach.getClass(), TUnitLabelCollectionNewTUnitLabelToAttach.getUnlaNsq());
                attachedTUnitLabelCollectionNew.add(TUnitLabelCollectionNewTUnitLabelToAttach);
            }
            TUnitLabelCollectionNew = attachedTUnitLabelCollectionNew;
            TLabelBundles.setTUnitLabelCollection(TUnitLabelCollectionNew);
            TLabelBundles = em.merge(TLabelBundles);
            if (labuDlanNsqOld != null && !labuDlanNsqOld.equals(labuDlanNsqNew)) {
                labuDlanNsqOld.getTLabelBundlesCollection().remove(TLabelBundles);
                labuDlanNsqOld = em.merge(labuDlanNsqOld);
            }
            if (labuDlanNsqNew != null && !labuDlanNsqNew.equals(labuDlanNsqOld)) {
                labuDlanNsqNew.getTLabelBundlesCollection().add(TLabelBundles);
                labuDlanNsqNew = em.merge(labuDlanNsqNew);
            }
            for (TUnitLabel TUnitLabelCollectionNewTUnitLabel : TUnitLabelCollectionNew) {
                if (!TUnitLabelCollectionOld.contains(TUnitLabelCollectionNewTUnitLabel)) {
                    TLabelBundles oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel = TUnitLabelCollectionNewTUnitLabel.getUnlaLabuNsq();
                    TUnitLabelCollectionNewTUnitLabel.setUnlaLabuNsq(TLabelBundles);
                    TUnitLabelCollectionNewTUnitLabel = em.merge(TUnitLabelCollectionNewTUnitLabel);
                    if (oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel != null && !oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel.equals(TLabelBundles)) {
                        oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel.getTUnitLabelCollection().remove(TUnitLabelCollectionNewTUnitLabel);
                        oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel = em.merge(oldUnlaLabuNsqOfTUnitLabelCollectionNewTUnitLabel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TLabelBundles.getLabuNsq();
                if (findTLabelBundles(id) == null) {
                    throw new NonexistentEntityException("The tLabelBundles with id " + id + " no longer exists.");
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
            TLabelBundles TLabelBundles;
            try {
                TLabelBundles = em.getReference(TLabelBundles.class, id);
                TLabelBundles.getLabuNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TLabelBundles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TUnitLabel> TUnitLabelCollectionOrphanCheck = TLabelBundles.getTUnitLabelCollection();
            for (TUnitLabel TUnitLabelCollectionOrphanCheckTUnitLabel : TUnitLabelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TLabelBundles (" + TLabelBundles + ") cannot be destroyed since the TUnitLabel " + TUnitLabelCollectionOrphanCheckTUnitLabel + " in its TUnitLabelCollection field has a non-nullable unlaLabuNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TDocumentLanguages labuDlanNsq = TLabelBundles.getLabuDlanNsq();
            if (labuDlanNsq != null) {
                labuDlanNsq.getTLabelBundlesCollection().remove(TLabelBundles);
                labuDlanNsq = em.merge(labuDlanNsq);
            }
            em.remove(TLabelBundles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TLabelBundles> findTLabelBundlesEntities() {
        return findTLabelBundlesEntities(true, -1, -1);
    }

    public List<TLabelBundles> findTLabelBundlesEntities(int maxResults, int firstResult) {
        return findTLabelBundlesEntities(false, maxResults, firstResult);
    }

    private List<TLabelBundles> findTLabelBundlesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TLabelBundles.class));
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

    public TLabelBundles findTLabelBundles(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TLabelBundles.class, id);
        } finally {
            em.close();
        }
    }

    public int getTLabelBundlesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TLabelBundles> rt = cq.from(TLabelBundles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
