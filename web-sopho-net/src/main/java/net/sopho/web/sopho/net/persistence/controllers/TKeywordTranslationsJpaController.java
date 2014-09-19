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
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TKeywordTranslations;
import net.sopho.web.sopho.net.persistence.entities.TKeywords;

/**
 *
 * @author LU01007
 */
public class TKeywordTranslationsJpaController implements Serializable {

    public TKeywordTranslationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TKeywordTranslations TKeywordTranslations) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterContents kytrChcoNsq = TKeywordTranslations.getKytrChcoNsq();
            if (kytrChcoNsq != null) {
                kytrChcoNsq = em.getReference(kytrChcoNsq.getClass(), kytrChcoNsq.getChcoNsq());
                TKeywordTranslations.setKytrChcoNsq(kytrChcoNsq);
            }
            TDocumentLanguages kytrDlanNsq = TKeywordTranslations.getKytrDlanNsq();
            if (kytrDlanNsq != null) {
                kytrDlanNsq = em.getReference(kytrDlanNsq.getClass(), kytrDlanNsq.getDlanNsq());
                TKeywordTranslations.setKytrDlanNsq(kytrDlanNsq);
            }
            TKeywords kytrKywdNsq = TKeywordTranslations.getKytrKywdNsq();
            if (kytrKywdNsq != null) {
                kytrKywdNsq = em.getReference(kytrKywdNsq.getClass(), kytrKywdNsq.getKywdNsq());
                TKeywordTranslations.setKytrKywdNsq(kytrKywdNsq);
            }
            em.persist(TKeywordTranslations);
            if (kytrChcoNsq != null) {
                kytrChcoNsq.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrChcoNsq = em.merge(kytrChcoNsq);
            }
            if (kytrDlanNsq != null) {
                kytrDlanNsq.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrDlanNsq = em.merge(kytrDlanNsq);
            }
            if (kytrKywdNsq != null) {
                kytrKywdNsq.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrKywdNsq = em.merge(kytrKywdNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTKeywordTranslations(TKeywordTranslations.getKytrNsq()) != null) {
                throw new PreexistingEntityException("TKeywordTranslations " + TKeywordTranslations + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TKeywordTranslations TKeywordTranslations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TKeywordTranslations persistentTKeywordTranslations = em.find(TKeywordTranslations.class, TKeywordTranslations.getKytrNsq());
            TChapterContents kytrChcoNsqOld = persistentTKeywordTranslations.getKytrChcoNsq();
            TChapterContents kytrChcoNsqNew = TKeywordTranslations.getKytrChcoNsq();
            TDocumentLanguages kytrDlanNsqOld = persistentTKeywordTranslations.getKytrDlanNsq();
            TDocumentLanguages kytrDlanNsqNew = TKeywordTranslations.getKytrDlanNsq();
            TKeywords kytrKywdNsqOld = persistentTKeywordTranslations.getKytrKywdNsq();
            TKeywords kytrKywdNsqNew = TKeywordTranslations.getKytrKywdNsq();
            if (kytrChcoNsqNew != null) {
                kytrChcoNsqNew = em.getReference(kytrChcoNsqNew.getClass(), kytrChcoNsqNew.getChcoNsq());
                TKeywordTranslations.setKytrChcoNsq(kytrChcoNsqNew);
            }
            if (kytrDlanNsqNew != null) {
                kytrDlanNsqNew = em.getReference(kytrDlanNsqNew.getClass(), kytrDlanNsqNew.getDlanNsq());
                TKeywordTranslations.setKytrDlanNsq(kytrDlanNsqNew);
            }
            if (kytrKywdNsqNew != null) {
                kytrKywdNsqNew = em.getReference(kytrKywdNsqNew.getClass(), kytrKywdNsqNew.getKywdNsq());
                TKeywordTranslations.setKytrKywdNsq(kytrKywdNsqNew);
            }
            TKeywordTranslations = em.merge(TKeywordTranslations);
            if (kytrChcoNsqOld != null && !kytrChcoNsqOld.equals(kytrChcoNsqNew)) {
                kytrChcoNsqOld.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrChcoNsqOld = em.merge(kytrChcoNsqOld);
            }
            if (kytrChcoNsqNew != null && !kytrChcoNsqNew.equals(kytrChcoNsqOld)) {
                kytrChcoNsqNew.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrChcoNsqNew = em.merge(kytrChcoNsqNew);
            }
            if (kytrDlanNsqOld != null && !kytrDlanNsqOld.equals(kytrDlanNsqNew)) {
                kytrDlanNsqOld.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrDlanNsqOld = em.merge(kytrDlanNsqOld);
            }
            if (kytrDlanNsqNew != null && !kytrDlanNsqNew.equals(kytrDlanNsqOld)) {
                kytrDlanNsqNew.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrDlanNsqNew = em.merge(kytrDlanNsqNew);
            }
            if (kytrKywdNsqOld != null && !kytrKywdNsqOld.equals(kytrKywdNsqNew)) {
                kytrKywdNsqOld.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrKywdNsqOld = em.merge(kytrKywdNsqOld);
            }
            if (kytrKywdNsqNew != null && !kytrKywdNsqNew.equals(kytrKywdNsqOld)) {
                kytrKywdNsqNew.getTKeywordTranslationsCollection().add(TKeywordTranslations);
                kytrKywdNsqNew = em.merge(kytrKywdNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TKeywordTranslations.getKytrNsq();
                if (findTKeywordTranslations(id) == null) {
                    throw new NonexistentEntityException("The tKeywordTranslations with id " + id + " no longer exists.");
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
            TKeywordTranslations TKeywordTranslations;
            try {
                TKeywordTranslations = em.getReference(TKeywordTranslations.class, id);
                TKeywordTranslations.getKytrNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TKeywordTranslations with id " + id + " no longer exists.", enfe);
            }
            TChapterContents kytrChcoNsq = TKeywordTranslations.getKytrChcoNsq();
            if (kytrChcoNsq != null) {
                kytrChcoNsq.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrChcoNsq = em.merge(kytrChcoNsq);
            }
            TDocumentLanguages kytrDlanNsq = TKeywordTranslations.getKytrDlanNsq();
            if (kytrDlanNsq != null) {
                kytrDlanNsq.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrDlanNsq = em.merge(kytrDlanNsq);
            }
            TKeywords kytrKywdNsq = TKeywordTranslations.getKytrKywdNsq();
            if (kytrKywdNsq != null) {
                kytrKywdNsq.getTKeywordTranslationsCollection().remove(TKeywordTranslations);
                kytrKywdNsq = em.merge(kytrKywdNsq);
            }
            em.remove(TKeywordTranslations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TKeywordTranslations> findTKeywordTranslationsEntities() {
        return findTKeywordTranslationsEntities(true, -1, -1);
    }

    public List<TKeywordTranslations> findTKeywordTranslationsEntities(int maxResults, int firstResult) {
        return findTKeywordTranslationsEntities(false, maxResults, firstResult);
    }

    private List<TKeywordTranslations> findTKeywordTranslationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TKeywordTranslations.class));
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

    public TKeywordTranslations findTKeywordTranslations(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TKeywordTranslations.class, id);
        } finally {
            em.close();
        }
    }

    public int getTKeywordTranslationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TKeywordTranslations> rt = cq.from(TKeywordTranslations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
