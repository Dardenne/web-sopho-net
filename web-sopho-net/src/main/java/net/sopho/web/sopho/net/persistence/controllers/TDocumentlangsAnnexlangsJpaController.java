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
import net.sopho.web.sopho.net.persistence.entities.TAnnexLanguages;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TDocumentlangsAnnexlangs;

/**
 *
 * @author LU01007
 */
public class TDocumentlangsAnnexlangsJpaController implements Serializable {

    public TDocumentlangsAnnexlangsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDocumentlangsAnnexlangs TDocumentlangsAnnexlangs) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexLanguages doanAlanNsq = TDocumentlangsAnnexlangs.getDoanAlanNsq();
            if (doanAlanNsq != null) {
                doanAlanNsq = em.getReference(doanAlanNsq.getClass(), doanAlanNsq.getAlanNsq());
                TDocumentlangsAnnexlangs.setDoanAlanNsq(doanAlanNsq);
            }
            TDocumentLanguages doanDlanNsq = TDocumentlangsAnnexlangs.getDoanDlanNsq();
            if (doanDlanNsq != null) {
                doanDlanNsq = em.getReference(doanDlanNsq.getClass(), doanDlanNsq.getDlanNsq());
                TDocumentlangsAnnexlangs.setDoanDlanNsq(doanDlanNsq);
            }
            em.persist(TDocumentlangsAnnexlangs);
            if (doanAlanNsq != null) {
                doanAlanNsq.getTDocumentlangsAnnexlangsCollection().add(TDocumentlangsAnnexlangs);
                doanAlanNsq = em.merge(doanAlanNsq);
            }
            if (doanDlanNsq != null) {
                doanDlanNsq.getTDocumentlangsAnnexlangsCollection().add(TDocumentlangsAnnexlangs);
                doanDlanNsq = em.merge(doanDlanNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDocumentlangsAnnexlangs(TDocumentlangsAnnexlangs.getDoanNsq()) != null) {
                throw new PreexistingEntityException("TDocumentlangsAnnexlangs " + TDocumentlangsAnnexlangs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDocumentlangsAnnexlangs TDocumentlangsAnnexlangs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentlangsAnnexlangs persistentTDocumentlangsAnnexlangs = em.find(TDocumentlangsAnnexlangs.class, TDocumentlangsAnnexlangs.getDoanNsq());
            TAnnexLanguages doanAlanNsqOld = persistentTDocumentlangsAnnexlangs.getDoanAlanNsq();
            TAnnexLanguages doanAlanNsqNew = TDocumentlangsAnnexlangs.getDoanAlanNsq();
            TDocumentLanguages doanDlanNsqOld = persistentTDocumentlangsAnnexlangs.getDoanDlanNsq();
            TDocumentLanguages doanDlanNsqNew = TDocumentlangsAnnexlangs.getDoanDlanNsq();
            if (doanAlanNsqNew != null) {
                doanAlanNsqNew = em.getReference(doanAlanNsqNew.getClass(), doanAlanNsqNew.getAlanNsq());
                TDocumentlangsAnnexlangs.setDoanAlanNsq(doanAlanNsqNew);
            }
            if (doanDlanNsqNew != null) {
                doanDlanNsqNew = em.getReference(doanDlanNsqNew.getClass(), doanDlanNsqNew.getDlanNsq());
                TDocumentlangsAnnexlangs.setDoanDlanNsq(doanDlanNsqNew);
            }
            TDocumentlangsAnnexlangs = em.merge(TDocumentlangsAnnexlangs);
            if (doanAlanNsqOld != null && !doanAlanNsqOld.equals(doanAlanNsqNew)) {
                doanAlanNsqOld.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangs);
                doanAlanNsqOld = em.merge(doanAlanNsqOld);
            }
            if (doanAlanNsqNew != null && !doanAlanNsqNew.equals(doanAlanNsqOld)) {
                doanAlanNsqNew.getTDocumentlangsAnnexlangsCollection().add(TDocumentlangsAnnexlangs);
                doanAlanNsqNew = em.merge(doanAlanNsqNew);
            }
            if (doanDlanNsqOld != null && !doanDlanNsqOld.equals(doanDlanNsqNew)) {
                doanDlanNsqOld.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangs);
                doanDlanNsqOld = em.merge(doanDlanNsqOld);
            }
            if (doanDlanNsqNew != null && !doanDlanNsqNew.equals(doanDlanNsqOld)) {
                doanDlanNsqNew.getTDocumentlangsAnnexlangsCollection().add(TDocumentlangsAnnexlangs);
                doanDlanNsqNew = em.merge(doanDlanNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TDocumentlangsAnnexlangs.getDoanNsq();
                if (findTDocumentlangsAnnexlangs(id) == null) {
                    throw new NonexistentEntityException("The tDocumentlangsAnnexlangs with id " + id + " no longer exists.");
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
            TDocumentlangsAnnexlangs TDocumentlangsAnnexlangs;
            try {
                TDocumentlangsAnnexlangs = em.getReference(TDocumentlangsAnnexlangs.class, id);
                TDocumentlangsAnnexlangs.getDoanNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDocumentlangsAnnexlangs with id " + id + " no longer exists.", enfe);
            }
            TAnnexLanguages doanAlanNsq = TDocumentlangsAnnexlangs.getDoanAlanNsq();
            if (doanAlanNsq != null) {
                doanAlanNsq.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangs);
                doanAlanNsq = em.merge(doanAlanNsq);
            }
            TDocumentLanguages doanDlanNsq = TDocumentlangsAnnexlangs.getDoanDlanNsq();
            if (doanDlanNsq != null) {
                doanDlanNsq.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangs);
                doanDlanNsq = em.merge(doanDlanNsq);
            }
            em.remove(TDocumentlangsAnnexlangs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDocumentlangsAnnexlangs> findTDocumentlangsAnnexlangsEntities() {
        return findTDocumentlangsAnnexlangsEntities(true, -1, -1);
    }

    public List<TDocumentlangsAnnexlangs> findTDocumentlangsAnnexlangsEntities(int maxResults, int firstResult) {
        return findTDocumentlangsAnnexlangsEntities(false, maxResults, firstResult);
    }

    private List<TDocumentlangsAnnexlangs> findTDocumentlangsAnnexlangsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDocumentlangsAnnexlangs.class));
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

    public TDocumentlangsAnnexlangs findTDocumentlangsAnnexlangs(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDocumentlangsAnnexlangs.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDocumentlangsAnnexlangsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDocumentlangsAnnexlangs> rt = cq.from(TDocumentlangsAnnexlangs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
