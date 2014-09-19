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
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TVersionComments;
import net.sopho.web.sopho.net.persistence.entities.TVersions;

/**
 *
 * @author LU01007
 */
public class TVersionCommentsJpaController implements Serializable {

    public TVersionCommentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TVersionComments TVersionComments) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentLanguages vercDlanNsq = TVersionComments.getVercDlanNsq();
            if (vercDlanNsq != null) {
                vercDlanNsq = em.getReference(vercDlanNsq.getClass(), vercDlanNsq.getDlanNsq());
                TVersionComments.setVercDlanNsq(vercDlanNsq);
            }
            TVersions vercVersNsq = TVersionComments.getVercVersNsq();
            if (vercVersNsq != null) {
                vercVersNsq = em.getReference(vercVersNsq.getClass(), vercVersNsq.getVersNsq());
                TVersionComments.setVercVersNsq(vercVersNsq);
            }
            em.persist(TVersionComments);
            if (vercDlanNsq != null) {
                vercDlanNsq.getTVersionCommentsCollection().add(TVersionComments);
                vercDlanNsq = em.merge(vercDlanNsq);
            }
            if (vercVersNsq != null) {
                vercVersNsq.getTVersionCommentsCollection().add(TVersionComments);
                vercVersNsq = em.merge(vercVersNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTVersionComments(TVersionComments.getVercNsq()) != null) {
                throw new PreexistingEntityException("TVersionComments " + TVersionComments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TVersionComments TVersionComments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TVersionComments persistentTVersionComments = em.find(TVersionComments.class, TVersionComments.getVercNsq());
            TDocumentLanguages vercDlanNsqOld = persistentTVersionComments.getVercDlanNsq();
            TDocumentLanguages vercDlanNsqNew = TVersionComments.getVercDlanNsq();
            TVersions vercVersNsqOld = persistentTVersionComments.getVercVersNsq();
            TVersions vercVersNsqNew = TVersionComments.getVercVersNsq();
            if (vercDlanNsqNew != null) {
                vercDlanNsqNew = em.getReference(vercDlanNsqNew.getClass(), vercDlanNsqNew.getDlanNsq());
                TVersionComments.setVercDlanNsq(vercDlanNsqNew);
            }
            if (vercVersNsqNew != null) {
                vercVersNsqNew = em.getReference(vercVersNsqNew.getClass(), vercVersNsqNew.getVersNsq());
                TVersionComments.setVercVersNsq(vercVersNsqNew);
            }
            TVersionComments = em.merge(TVersionComments);
            if (vercDlanNsqOld != null && !vercDlanNsqOld.equals(vercDlanNsqNew)) {
                vercDlanNsqOld.getTVersionCommentsCollection().remove(TVersionComments);
                vercDlanNsqOld = em.merge(vercDlanNsqOld);
            }
            if (vercDlanNsqNew != null && !vercDlanNsqNew.equals(vercDlanNsqOld)) {
                vercDlanNsqNew.getTVersionCommentsCollection().add(TVersionComments);
                vercDlanNsqNew = em.merge(vercDlanNsqNew);
            }
            if (vercVersNsqOld != null && !vercVersNsqOld.equals(vercVersNsqNew)) {
                vercVersNsqOld.getTVersionCommentsCollection().remove(TVersionComments);
                vercVersNsqOld = em.merge(vercVersNsqOld);
            }
            if (vercVersNsqNew != null && !vercVersNsqNew.equals(vercVersNsqOld)) {
                vercVersNsqNew.getTVersionCommentsCollection().add(TVersionComments);
                vercVersNsqNew = em.merge(vercVersNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TVersionComments.getVercNsq();
                if (findTVersionComments(id) == null) {
                    throw new NonexistentEntityException("The tVersionComments with id " + id + " no longer exists.");
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
            TVersionComments TVersionComments;
            try {
                TVersionComments = em.getReference(TVersionComments.class, id);
                TVersionComments.getVercNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TVersionComments with id " + id + " no longer exists.", enfe);
            }
            TDocumentLanguages vercDlanNsq = TVersionComments.getVercDlanNsq();
            if (vercDlanNsq != null) {
                vercDlanNsq.getTVersionCommentsCollection().remove(TVersionComments);
                vercDlanNsq = em.merge(vercDlanNsq);
            }
            TVersions vercVersNsq = TVersionComments.getVercVersNsq();
            if (vercVersNsq != null) {
                vercVersNsq.getTVersionCommentsCollection().remove(TVersionComments);
                vercVersNsq = em.merge(vercVersNsq);
            }
            em.remove(TVersionComments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TVersionComments> findTVersionCommentsEntities() {
        return findTVersionCommentsEntities(true, -1, -1);
    }

    public List<TVersionComments> findTVersionCommentsEntities(int maxResults, int firstResult) {
        return findTVersionCommentsEntities(false, maxResults, firstResult);
    }

    private List<TVersionComments> findTVersionCommentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TVersionComments.class));
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

    public TVersionComments findTVersionComments(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TVersionComments.class, id);
        } finally {
            em.close();
        }
    }

    public int getTVersionCommentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TVersionComments> rt = cq.from(TVersionComments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
