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
import net.sopho.web.sopho.net.persistence.entities.TChapterDescriptionsTrans;
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;

/**
 *
 * @author LU01007
 */
public class TChapterDescriptionsTransJpaController implements Serializable {

    public TChapterDescriptionsTransJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TChapterDescriptionsTrans TChapterDescriptionsTrans) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterTitles chdtChtlNsq = TChapterDescriptionsTrans.getChdtChtlNsq();
            if (chdtChtlNsq != null) {
                chdtChtlNsq = em.getReference(chdtChtlNsq.getClass(), chdtChtlNsq.getChtlNsq());
                TChapterDescriptionsTrans.setChdtChtlNsq(chdtChtlNsq);
            }
            TDocumentLanguages chdtDlanNsq = TChapterDescriptionsTrans.getChdtDlanNsq();
            if (chdtDlanNsq != null) {
                chdtDlanNsq = em.getReference(chdtDlanNsq.getClass(), chdtDlanNsq.getDlanNsq());
                TChapterDescriptionsTrans.setChdtDlanNsq(chdtDlanNsq);
            }
            em.persist(TChapterDescriptionsTrans);
            if (chdtChtlNsq != null) {
                chdtChtlNsq.getTChapterDescriptionsTransCollection().add(TChapterDescriptionsTrans);
                chdtChtlNsq = em.merge(chdtChtlNsq);
            }
            if (chdtDlanNsq != null) {
                chdtDlanNsq.getTChapterDescriptionsTransCollection().add(TChapterDescriptionsTrans);
                chdtDlanNsq = em.merge(chdtDlanNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTChapterDescriptionsTrans(TChapterDescriptionsTrans.getChdtNsq()) != null) {
                throw new PreexistingEntityException("TChapterDescriptionsTrans " + TChapterDescriptionsTrans + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TChapterDescriptionsTrans TChapterDescriptionsTrans) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterDescriptionsTrans persistentTChapterDescriptionsTrans = em.find(TChapterDescriptionsTrans.class, TChapterDescriptionsTrans.getChdtNsq());
            TChapterTitles chdtChtlNsqOld = persistentTChapterDescriptionsTrans.getChdtChtlNsq();
            TChapterTitles chdtChtlNsqNew = TChapterDescriptionsTrans.getChdtChtlNsq();
            TDocumentLanguages chdtDlanNsqOld = persistentTChapterDescriptionsTrans.getChdtDlanNsq();
            TDocumentLanguages chdtDlanNsqNew = TChapterDescriptionsTrans.getChdtDlanNsq();
            if (chdtChtlNsqNew != null) {
                chdtChtlNsqNew = em.getReference(chdtChtlNsqNew.getClass(), chdtChtlNsqNew.getChtlNsq());
                TChapterDescriptionsTrans.setChdtChtlNsq(chdtChtlNsqNew);
            }
            if (chdtDlanNsqNew != null) {
                chdtDlanNsqNew = em.getReference(chdtDlanNsqNew.getClass(), chdtDlanNsqNew.getDlanNsq());
                TChapterDescriptionsTrans.setChdtDlanNsq(chdtDlanNsqNew);
            }
            TChapterDescriptionsTrans = em.merge(TChapterDescriptionsTrans);
            if (chdtChtlNsqOld != null && !chdtChtlNsqOld.equals(chdtChtlNsqNew)) {
                chdtChtlNsqOld.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTrans);
                chdtChtlNsqOld = em.merge(chdtChtlNsqOld);
            }
            if (chdtChtlNsqNew != null && !chdtChtlNsqNew.equals(chdtChtlNsqOld)) {
                chdtChtlNsqNew.getTChapterDescriptionsTransCollection().add(TChapterDescriptionsTrans);
                chdtChtlNsqNew = em.merge(chdtChtlNsqNew);
            }
            if (chdtDlanNsqOld != null && !chdtDlanNsqOld.equals(chdtDlanNsqNew)) {
                chdtDlanNsqOld.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTrans);
                chdtDlanNsqOld = em.merge(chdtDlanNsqOld);
            }
            if (chdtDlanNsqNew != null && !chdtDlanNsqNew.equals(chdtDlanNsqOld)) {
                chdtDlanNsqNew.getTChapterDescriptionsTransCollection().add(TChapterDescriptionsTrans);
                chdtDlanNsqNew = em.merge(chdtDlanNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TChapterDescriptionsTrans.getChdtNsq();
                if (findTChapterDescriptionsTrans(id) == null) {
                    throw new NonexistentEntityException("The tChapterDescriptionsTrans with id " + id + " no longer exists.");
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
            TChapterDescriptionsTrans TChapterDescriptionsTrans;
            try {
                TChapterDescriptionsTrans = em.getReference(TChapterDescriptionsTrans.class, id);
                TChapterDescriptionsTrans.getChdtNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TChapterDescriptionsTrans with id " + id + " no longer exists.", enfe);
            }
            TChapterTitles chdtChtlNsq = TChapterDescriptionsTrans.getChdtChtlNsq();
            if (chdtChtlNsq != null) {
                chdtChtlNsq.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTrans);
                chdtChtlNsq = em.merge(chdtChtlNsq);
            }
            TDocumentLanguages chdtDlanNsq = TChapterDescriptionsTrans.getChdtDlanNsq();
            if (chdtDlanNsq != null) {
                chdtDlanNsq.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTrans);
                chdtDlanNsq = em.merge(chdtDlanNsq);
            }
            em.remove(TChapterDescriptionsTrans);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TChapterDescriptionsTrans> findTChapterDescriptionsTransEntities() {
        return findTChapterDescriptionsTransEntities(true, -1, -1);
    }

    public List<TChapterDescriptionsTrans> findTChapterDescriptionsTransEntities(int maxResults, int firstResult) {
        return findTChapterDescriptionsTransEntities(false, maxResults, firstResult);
    }

    private List<TChapterDescriptionsTrans> findTChapterDescriptionsTransEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TChapterDescriptionsTrans.class));
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

    public TChapterDescriptionsTrans findTChapterDescriptionsTrans(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TChapterDescriptionsTrans.class, id);
        } finally {
            em.close();
        }
    }

    public int getTChapterDescriptionsTransCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TChapterDescriptionsTrans> rt = cq.from(TChapterDescriptionsTrans.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
