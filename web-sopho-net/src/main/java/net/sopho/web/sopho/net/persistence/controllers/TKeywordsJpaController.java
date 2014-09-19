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
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import net.sopho.web.sopho.net.persistence.entities.TKeywordTranslations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TKeywords;

/**
 *
 * @author LU01007
 */
public class TKeywordsJpaController implements Serializable {

    public TKeywordsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TKeywords TKeywords) throws PreexistingEntityException, Exception {
        if (TKeywords.getTKeywordTranslationsCollection() == null) {
            TKeywords.setTKeywordTranslationsCollection(new ArrayList<TKeywordTranslations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocuments kywdDocuNsq = TKeywords.getKywdDocuNsq();
            if (kywdDocuNsq != null) {
                kywdDocuNsq = em.getReference(kywdDocuNsq.getClass(), kywdDocuNsq.getDocuNsq());
                TKeywords.setKywdDocuNsq(kywdDocuNsq);
            }
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollection = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslationsToAttach : TKeywords.getTKeywordTranslationsCollection()) {
                TKeywordTranslationsCollectionTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollection.add(TKeywordTranslationsCollectionTKeywordTranslationsToAttach);
            }
            TKeywords.setTKeywordTranslationsCollection(attachedTKeywordTranslationsCollection);
            em.persist(TKeywords);
            if (kywdDocuNsq != null) {
                kywdDocuNsq.getTKeywordsCollection().add(TKeywords);
                kywdDocuNsq = em.merge(kywdDocuNsq);
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslations : TKeywords.getTKeywordTranslationsCollection()) {
                TKeywords oldKytrKywdNsqOfTKeywordTranslationsCollectionTKeywordTranslations = TKeywordTranslationsCollectionTKeywordTranslations.getKytrKywdNsq();
                TKeywordTranslationsCollectionTKeywordTranslations.setKytrKywdNsq(TKeywords);
                TKeywordTranslationsCollectionTKeywordTranslations = em.merge(TKeywordTranslationsCollectionTKeywordTranslations);
                if (oldKytrKywdNsqOfTKeywordTranslationsCollectionTKeywordTranslations != null) {
                    oldKytrKywdNsqOfTKeywordTranslationsCollectionTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionTKeywordTranslations);
                    oldKytrKywdNsqOfTKeywordTranslationsCollectionTKeywordTranslations = em.merge(oldKytrKywdNsqOfTKeywordTranslationsCollectionTKeywordTranslations);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTKeywords(TKeywords.getKywdNsq()) != null) {
                throw new PreexistingEntityException("TKeywords " + TKeywords + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TKeywords TKeywords) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TKeywords persistentTKeywords = em.find(TKeywords.class, TKeywords.getKywdNsq());
            TDocuments kywdDocuNsqOld = persistentTKeywords.getKywdDocuNsq();
            TDocuments kywdDocuNsqNew = TKeywords.getKywdDocuNsq();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOld = persistentTKeywords.getTKeywordTranslationsCollection();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionNew = TKeywords.getTKeywordTranslationsCollection();
            List<String> illegalOrphanMessages = null;
            for (TKeywordTranslations TKeywordTranslationsCollectionOldTKeywordTranslations : TKeywordTranslationsCollectionOld) {
                if (!TKeywordTranslationsCollectionNew.contains(TKeywordTranslationsCollectionOldTKeywordTranslations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TKeywordTranslations " + TKeywordTranslationsCollectionOldTKeywordTranslations + " since its kytrKywdNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (kywdDocuNsqNew != null) {
                kywdDocuNsqNew = em.getReference(kywdDocuNsqNew.getClass(), kywdDocuNsqNew.getDocuNsq());
                TKeywords.setKywdDocuNsq(kywdDocuNsqNew);
            }
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollectionNew = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach : TKeywordTranslationsCollectionNew) {
                TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollectionNew.add(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach);
            }
            TKeywordTranslationsCollectionNew = attachedTKeywordTranslationsCollectionNew;
            TKeywords.setTKeywordTranslationsCollection(TKeywordTranslationsCollectionNew);
            TKeywords = em.merge(TKeywords);
            if (kywdDocuNsqOld != null && !kywdDocuNsqOld.equals(kywdDocuNsqNew)) {
                kywdDocuNsqOld.getTKeywordsCollection().remove(TKeywords);
                kywdDocuNsqOld = em.merge(kywdDocuNsqOld);
            }
            if (kywdDocuNsqNew != null && !kywdDocuNsqNew.equals(kywdDocuNsqOld)) {
                kywdDocuNsqNew.getTKeywordsCollection().add(TKeywords);
                kywdDocuNsqNew = em.merge(kywdDocuNsqNew);
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslations : TKeywordTranslationsCollectionNew) {
                if (!TKeywordTranslationsCollectionOld.contains(TKeywordTranslationsCollectionNewTKeywordTranslations)) {
                    TKeywords oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = TKeywordTranslationsCollectionNewTKeywordTranslations.getKytrKywdNsq();
                    TKeywordTranslationsCollectionNewTKeywordTranslations.setKytrKywdNsq(TKeywords);
                    TKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(TKeywordTranslationsCollectionNewTKeywordTranslations);
                    if (oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations != null && !oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.equals(TKeywords)) {
                        oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionNewTKeywordTranslations);
                        oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(oldKytrKywdNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TKeywords.getKywdNsq();
                if (findTKeywords(id) == null) {
                    throw new NonexistentEntityException("The tKeywords with id " + id + " no longer exists.");
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
            TKeywords TKeywords;
            try {
                TKeywords = em.getReference(TKeywords.class, id);
                TKeywords.getKywdNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TKeywords with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOrphanCheck = TKeywords.getTKeywordTranslationsCollection();
            for (TKeywordTranslations TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations : TKeywordTranslationsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TKeywords (" + TKeywords + ") cannot be destroyed since the TKeywordTranslations " + TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations + " in its TKeywordTranslationsCollection field has a non-nullable kytrKywdNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TDocuments kywdDocuNsq = TKeywords.getKywdDocuNsq();
            if (kywdDocuNsq != null) {
                kywdDocuNsq.getTKeywordsCollection().remove(TKeywords);
                kywdDocuNsq = em.merge(kywdDocuNsq);
            }
            em.remove(TKeywords);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TKeywords> findTKeywordsEntities() {
        return findTKeywordsEntities(true, -1, -1);
    }

    public List<TKeywords> findTKeywordsEntities(int maxResults, int firstResult) {
        return findTKeywordsEntities(false, maxResults, firstResult);
    }

    private List<TKeywords> findTKeywordsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TKeywords.class));
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

    public TKeywords findTKeywords(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TKeywords.class, id);
        } finally {
            em.close();
        }
    }

    public int getTKeywordsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TKeywords> rt = cq.from(TKeywords.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
