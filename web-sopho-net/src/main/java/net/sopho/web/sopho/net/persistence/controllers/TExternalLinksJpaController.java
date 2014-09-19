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
import net.sopho.web.sopho.net.persistence.entities.TExternalLinks;

/**
 *
 * @author LU01007
 */
public class TExternalLinksJpaController implements Serializable {

    public TExternalLinksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TExternalLinks TExternalLinks) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentLanguages elnkDlanNsq = TExternalLinks.getElnkDlanNsq();
            if (elnkDlanNsq != null) {
                elnkDlanNsq = em.getReference(elnkDlanNsq.getClass(), elnkDlanNsq.getDlanNsq());
                TExternalLinks.setElnkDlanNsq(elnkDlanNsq);
            }
            em.persist(TExternalLinks);
            if (elnkDlanNsq != null) {
                elnkDlanNsq.getTExternalLinksCollection().add(TExternalLinks);
                elnkDlanNsq = em.merge(elnkDlanNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTExternalLinks(TExternalLinks.getElnkNsq()) != null) {
                throw new PreexistingEntityException("TExternalLinks " + TExternalLinks + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TExternalLinks TExternalLinks) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TExternalLinks persistentTExternalLinks = em.find(TExternalLinks.class, TExternalLinks.getElnkNsq());
            TDocumentLanguages elnkDlanNsqOld = persistentTExternalLinks.getElnkDlanNsq();
            TDocumentLanguages elnkDlanNsqNew = TExternalLinks.getElnkDlanNsq();
            if (elnkDlanNsqNew != null) {
                elnkDlanNsqNew = em.getReference(elnkDlanNsqNew.getClass(), elnkDlanNsqNew.getDlanNsq());
                TExternalLinks.setElnkDlanNsq(elnkDlanNsqNew);
            }
            TExternalLinks = em.merge(TExternalLinks);
            if (elnkDlanNsqOld != null && !elnkDlanNsqOld.equals(elnkDlanNsqNew)) {
                elnkDlanNsqOld.getTExternalLinksCollection().remove(TExternalLinks);
                elnkDlanNsqOld = em.merge(elnkDlanNsqOld);
            }
            if (elnkDlanNsqNew != null && !elnkDlanNsqNew.equals(elnkDlanNsqOld)) {
                elnkDlanNsqNew.getTExternalLinksCollection().add(TExternalLinks);
                elnkDlanNsqNew = em.merge(elnkDlanNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TExternalLinks.getElnkNsq();
                if (findTExternalLinks(id) == null) {
                    throw new NonexistentEntityException("The tExternalLinks with id " + id + " no longer exists.");
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
            TExternalLinks TExternalLinks;
            try {
                TExternalLinks = em.getReference(TExternalLinks.class, id);
                TExternalLinks.getElnkNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TExternalLinks with id " + id + " no longer exists.", enfe);
            }
            TDocumentLanguages elnkDlanNsq = TExternalLinks.getElnkDlanNsq();
            if (elnkDlanNsq != null) {
                elnkDlanNsq.getTExternalLinksCollection().remove(TExternalLinks);
                elnkDlanNsq = em.merge(elnkDlanNsq);
            }
            em.remove(TExternalLinks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TExternalLinks> findTExternalLinksEntities() {
        return findTExternalLinksEntities(true, -1, -1);
    }

    public List<TExternalLinks> findTExternalLinksEntities(int maxResults, int firstResult) {
        return findTExternalLinksEntities(false, maxResults, firstResult);
    }

    private List<TExternalLinks> findTExternalLinksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TExternalLinks.class));
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

    public TExternalLinks findTExternalLinks(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TExternalLinks.class, id);
        } finally {
            em.close();
        }
    }

    public int getTExternalLinksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TExternalLinks> rt = cq.from(TExternalLinks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
