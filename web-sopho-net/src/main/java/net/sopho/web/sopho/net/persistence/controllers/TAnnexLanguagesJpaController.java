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
import net.sopho.web.sopho.net.persistence.entities.TAnnexLanguages;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;
import net.sopho.web.sopho.net.persistence.entities.TDocumentlangsAnnexlangs;

/**
 *
 * @author LU01007
 */
public class TAnnexLanguagesJpaController implements Serializable {

    public TAnnexLanguagesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TAnnexLanguages TAnnexLanguages) throws PreexistingEntityException, Exception {
        if (TAnnexLanguages.getTAnnexesCollection() == null) {
            TAnnexLanguages.setTAnnexesCollection(new ArrayList<TAnnexes>());
        }
        if (TAnnexLanguages.getTZipArchivesCollection() == null) {
            TAnnexLanguages.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        if (TAnnexLanguages.getTDocumentlangsAnnexlangsCollection() == null) {
            TAnnexLanguages.setTDocumentlangsAnnexlangsCollection(new ArrayList<TDocumentlangsAnnexlangs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TAnnexes> attachedTAnnexesCollection = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionTAnnexesToAttach : TAnnexLanguages.getTAnnexesCollection()) {
                TAnnexesCollectionTAnnexesToAttach = em.getReference(TAnnexesCollectionTAnnexesToAttach.getClass(), TAnnexesCollectionTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollection.add(TAnnexesCollectionTAnnexesToAttach);
            }
            TAnnexLanguages.setTAnnexesCollection(attachedTAnnexesCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TAnnexLanguages.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TAnnexLanguages.setTZipArchivesCollection(attachedTZipArchivesCollection);
            Collection<TDocumentlangsAnnexlangs> attachedTDocumentlangsAnnexlangsCollection = new ArrayList<TDocumentlangsAnnexlangs>();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach : TAnnexLanguages.getTDocumentlangsAnnexlangsCollection()) {
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach = em.getReference(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach.getClass(), TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach.getDoanNsq());
                attachedTDocumentlangsAnnexlangsCollection.add(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach);
            }
            TAnnexLanguages.setTDocumentlangsAnnexlangsCollection(attachedTDocumentlangsAnnexlangsCollection);
            em.persist(TAnnexLanguages);
            for (TAnnexes TAnnexesCollectionTAnnexes : TAnnexLanguages.getTAnnexesCollection()) {
                TAnnexLanguages oldAnexAlanNsqOfTAnnexesCollectionTAnnexes = TAnnexesCollectionTAnnexes.getAnexAlanNsq();
                TAnnexesCollectionTAnnexes.setAnexAlanNsq(TAnnexLanguages);
                TAnnexesCollectionTAnnexes = em.merge(TAnnexesCollectionTAnnexes);
                if (oldAnexAlanNsqOfTAnnexesCollectionTAnnexes != null) {
                    oldAnexAlanNsqOfTAnnexesCollectionTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionTAnnexes);
                    oldAnexAlanNsqOfTAnnexesCollectionTAnnexes = em.merge(oldAnexAlanNsqOfTAnnexesCollectionTAnnexes);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TAnnexLanguages.getTZipArchivesCollection()) {
                TAnnexLanguages oldZipaAlanNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaAlanNsq();
                TZipArchivesCollectionTZipArchives.setZipaAlanNsq(TAnnexLanguages);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaAlanNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaAlanNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaAlanNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaAlanNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs : TAnnexLanguages.getTDocumentlangsAnnexlangsCollection()) {
                TAnnexLanguages oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.getDoanAlanNsq();
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.setDoanAlanNsq(TAnnexLanguages);
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = em.merge(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                if (oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs != null) {
                    oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                    oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = em.merge(oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTAnnexLanguages(TAnnexLanguages.getAlanNsq()) != null) {
                throw new PreexistingEntityException("TAnnexLanguages " + TAnnexLanguages + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TAnnexLanguages TAnnexLanguages) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexLanguages persistentTAnnexLanguages = em.find(TAnnexLanguages.class, TAnnexLanguages.getAlanNsq());
            Collection<TAnnexes> TAnnexesCollectionOld = persistentTAnnexLanguages.getTAnnexesCollection();
            Collection<TAnnexes> TAnnexesCollectionNew = TAnnexLanguages.getTAnnexesCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTAnnexLanguages.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TAnnexLanguages.getTZipArchivesCollection();
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionOld = persistentTAnnexLanguages.getTDocumentlangsAnnexlangsCollection();
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionNew = TAnnexLanguages.getTDocumentlangsAnnexlangsCollection();
            List<String> illegalOrphanMessages = null;
            for (TAnnexes TAnnexesCollectionOldTAnnexes : TAnnexesCollectionOld) {
                if (!TAnnexesCollectionNew.contains(TAnnexesCollectionOldTAnnexes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TAnnexes " + TAnnexesCollectionOldTAnnexes + " since its anexAlanNsq field is not nullable.");
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionOld) {
                if (!TDocumentlangsAnnexlangsCollectionNew.contains(TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TDocumentlangsAnnexlangs " + TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs + " since its doanAlanNsq field is not nullable.");
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
            TAnnexLanguages.setTAnnexesCollection(TAnnexesCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TAnnexLanguages.setTZipArchivesCollection(TZipArchivesCollectionNew);
            Collection<TDocumentlangsAnnexlangs> attachedTDocumentlangsAnnexlangsCollectionNew = new ArrayList<TDocumentlangsAnnexlangs>();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach : TDocumentlangsAnnexlangsCollectionNew) {
                TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach = em.getReference(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach.getClass(), TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach.getDoanNsq());
                attachedTDocumentlangsAnnexlangsCollectionNew.add(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach);
            }
            TDocumentlangsAnnexlangsCollectionNew = attachedTDocumentlangsAnnexlangsCollectionNew;
            TAnnexLanguages.setTDocumentlangsAnnexlangsCollection(TDocumentlangsAnnexlangsCollectionNew);
            TAnnexLanguages = em.merge(TAnnexLanguages);
            for (TAnnexes TAnnexesCollectionNewTAnnexes : TAnnexesCollectionNew) {
                if (!TAnnexesCollectionOld.contains(TAnnexesCollectionNewTAnnexes)) {
                    TAnnexLanguages oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes = TAnnexesCollectionNewTAnnexes.getAnexAlanNsq();
                    TAnnexesCollectionNewTAnnexes.setAnexAlanNsq(TAnnexLanguages);
                    TAnnexesCollectionNewTAnnexes = em.merge(TAnnexesCollectionNewTAnnexes);
                    if (oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes != null && !oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes.equals(TAnnexLanguages)) {
                        oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionNewTAnnexes);
                        oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes = em.merge(oldAnexAlanNsqOfTAnnexesCollectionNewTAnnexes);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    TZipArchivesCollectionOldTZipArchives.setZipaAlanNsq(null);
                    TZipArchivesCollectionOldTZipArchives = em.merge(TZipArchivesCollectionOldTZipArchives);
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TAnnexLanguages oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaAlanNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaAlanNsq(TAnnexLanguages);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives.equals(TAnnexLanguages)) {
                        oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaAlanNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionNew) {
                if (!TDocumentlangsAnnexlangsCollectionOld.contains(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs)) {
                    TAnnexLanguages oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.getDoanAlanNsq();
                    TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.setDoanAlanNsq(TAnnexLanguages);
                    TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = em.merge(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                    if (oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs != null && !oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.equals(TAnnexLanguages)) {
                        oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                        oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = em.merge(oldDoanAlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TAnnexLanguages.getAlanNsq();
                if (findTAnnexLanguages(id) == null) {
                    throw new NonexistentEntityException("The tAnnexLanguages with id " + id + " no longer exists.");
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
            TAnnexLanguages TAnnexLanguages;
            try {
                TAnnexLanguages = em.getReference(TAnnexLanguages.class, id);
                TAnnexLanguages.getAlanNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TAnnexLanguages with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TAnnexes> TAnnexesCollectionOrphanCheck = TAnnexLanguages.getTAnnexesCollection();
            for (TAnnexes TAnnexesCollectionOrphanCheckTAnnexes : TAnnexesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TAnnexLanguages (" + TAnnexLanguages + ") cannot be destroyed since the TAnnexes " + TAnnexesCollectionOrphanCheckTAnnexes + " in its TAnnexesCollection field has a non-nullable anexAlanNsq field.");
            }
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionOrphanCheck = TAnnexLanguages.getTDocumentlangsAnnexlangsCollection();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionOrphanCheckTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TAnnexLanguages (" + TAnnexLanguages + ") cannot be destroyed since the TDocumentlangsAnnexlangs " + TDocumentlangsAnnexlangsCollectionOrphanCheckTDocumentlangsAnnexlangs + " in its TDocumentlangsAnnexlangsCollection field has a non-nullable doanAlanNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TZipArchives> TZipArchivesCollection = TAnnexLanguages.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionTZipArchives : TZipArchivesCollection) {
                TZipArchivesCollectionTZipArchives.setZipaAlanNsq(null);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
            }
            em.remove(TAnnexLanguages);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TAnnexLanguages> findTAnnexLanguagesEntities() {
        return findTAnnexLanguagesEntities(true, -1, -1);
    }

    public List<TAnnexLanguages> findTAnnexLanguagesEntities(int maxResults, int firstResult) {
        return findTAnnexLanguagesEntities(false, maxResults, firstResult);
    }

    private List<TAnnexLanguages> findTAnnexLanguagesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TAnnexLanguages.class));
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

    public TAnnexLanguages findTAnnexLanguages(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TAnnexLanguages.class, id);
        } finally {
            em.close();
        }
    }

    public int getTAnnexLanguagesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TAnnexLanguages> rt = cq.from(TAnnexLanguages.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
