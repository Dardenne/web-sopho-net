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
import net.sopho.web.sopho.net.persistence.entities.TAnnexes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TAnnexGroups;

/**
 *
 * @author LU01007
 */
public class TAnnexGroupsJpaController implements Serializable {

    public TAnnexGroupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TAnnexGroups TAnnexGroups) throws PreexistingEntityException, Exception {
        if (TAnnexGroups.getTAnnexesCollection() == null) {
            TAnnexGroups.setTAnnexesCollection(new ArrayList<TAnnexes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TAnnexes> attachedTAnnexesCollection = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionTAnnexesToAttach : TAnnexGroups.getTAnnexesCollection()) {
                TAnnexesCollectionTAnnexesToAttach = em.getReference(TAnnexesCollectionTAnnexesToAttach.getClass(), TAnnexesCollectionTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollection.add(TAnnexesCollectionTAnnexesToAttach);
            }
            TAnnexGroups.setTAnnexesCollection(attachedTAnnexesCollection);
            em.persist(TAnnexGroups);
            for (TAnnexes TAnnexesCollectionTAnnexes : TAnnexGroups.getTAnnexesCollection()) {
                TAnnexGroups oldAnexAgrpNsqOfTAnnexesCollectionTAnnexes = TAnnexesCollectionTAnnexes.getAnexAgrpNsq();
                TAnnexesCollectionTAnnexes.setAnexAgrpNsq(TAnnexGroups);
                TAnnexesCollectionTAnnexes = em.merge(TAnnexesCollectionTAnnexes);
                if (oldAnexAgrpNsqOfTAnnexesCollectionTAnnexes != null) {
                    oldAnexAgrpNsqOfTAnnexesCollectionTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionTAnnexes);
                    oldAnexAgrpNsqOfTAnnexesCollectionTAnnexes = em.merge(oldAnexAgrpNsqOfTAnnexesCollectionTAnnexes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTAnnexGroups(TAnnexGroups.getAgrpNsq()) != null) {
                throw new PreexistingEntityException("TAnnexGroups " + TAnnexGroups + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TAnnexGroups TAnnexGroups) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexGroups persistentTAnnexGroups = em.find(TAnnexGroups.class, TAnnexGroups.getAgrpNsq());
            Collection<TAnnexes> TAnnexesCollectionOld = persistentTAnnexGroups.getTAnnexesCollection();
            Collection<TAnnexes> TAnnexesCollectionNew = TAnnexGroups.getTAnnexesCollection();
            List<String> illegalOrphanMessages = null;
            for (TAnnexes TAnnexesCollectionOldTAnnexes : TAnnexesCollectionOld) {
                if (!TAnnexesCollectionNew.contains(TAnnexesCollectionOldTAnnexes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TAnnexes " + TAnnexesCollectionOldTAnnexes + " since its anexAgrpNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TAnnexes> attachedTAnnexesCollectionNew = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionNewTAnnexesToAttach : TAnnexesCollectionNew) {
                TAnnexesCollectionNewTAnnexesToAttach = em.getReference(TAnnexesCollectionNewTAnnexesToAttach.getClass(), TAnnexesCollectionNewTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollectionNew.add(TAnnexesCollectionNewTAnnexesToAttach);
            }
            TAnnexesCollectionNew = attachedTAnnexesCollectionNew;
            TAnnexGroups.setTAnnexesCollection(TAnnexesCollectionNew);
            TAnnexGroups = em.merge(TAnnexGroups);
            for (TAnnexes TAnnexesCollectionNewTAnnexes : TAnnexesCollectionNew) {
                if (!TAnnexesCollectionOld.contains(TAnnexesCollectionNewTAnnexes)) {
                    TAnnexGroups oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes = TAnnexesCollectionNewTAnnexes.getAnexAgrpNsq();
                    TAnnexesCollectionNewTAnnexes.setAnexAgrpNsq(TAnnexGroups);
                    TAnnexesCollectionNewTAnnexes = em.merge(TAnnexesCollectionNewTAnnexes);
                    if (oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes != null && !oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes.equals(TAnnexGroups)) {
                        oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionNewTAnnexes);
                        oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes = em.merge(oldAnexAgrpNsqOfTAnnexesCollectionNewTAnnexes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TAnnexGroups.getAgrpNsq();
                if (findTAnnexGroups(id) == null) {
                    throw new NonexistentEntityException("The tAnnexGroups with id " + id + " no longer exists.");
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
            TAnnexGroups TAnnexGroups;
            try {
                TAnnexGroups = em.getReference(TAnnexGroups.class, id);
                TAnnexGroups.getAgrpNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TAnnexGroups with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TAnnexes> TAnnexesCollectionOrphanCheck = TAnnexGroups.getTAnnexesCollection();
            for (TAnnexes TAnnexesCollectionOrphanCheckTAnnexes : TAnnexesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TAnnexGroups (" + TAnnexGroups + ") cannot be destroyed since the TAnnexes " + TAnnexesCollectionOrphanCheckTAnnexes + " in its TAnnexesCollection field has a non-nullable anexAgrpNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(TAnnexGroups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TAnnexGroups> findTAnnexGroupsEntities() {
        return findTAnnexGroupsEntities(true, -1, -1);
    }

    public List<TAnnexGroups> findTAnnexGroupsEntities(int maxResults, int firstResult) {
        return findTAnnexGroupsEntities(false, maxResults, firstResult);
    }

    private List<TAnnexGroups> findTAnnexGroupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TAnnexGroups.class));
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

    public TAnnexGroups findTAnnexGroups(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TAnnexGroups.class, id);
        } finally {
            em.close();
        }
    }

    public int getTAnnexGroupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TAnnexGroups> rt = cq.from(TAnnexGroups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
