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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import net.sopho.web.sopho.net.persistence.entities.TDocumentStructures;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;

/**
 *
 * @author LU01007
 */
public class TDocumentStructuresJpaController implements Serializable {

    public TDocumentStructuresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDocumentStructures TDocumentStructures) throws PreexistingEntityException, Exception {
        if (TDocumentStructures.getTDocumentsCollection() == null) {
            TDocumentStructures.setTDocumentsCollection(new ArrayList<TDocuments>());
        }
        if (TDocumentStructures.getTChapterTitlesCollection() == null) {
            TDocumentStructures.setTChapterTitlesCollection(new ArrayList<TChapterTitles>());
        }
        if (TDocumentStructures.getTZipArchivesCollection() == null) {
            TDocumentStructures.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TDocuments> attachedTDocumentsCollection = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionTDocumentsToAttach : TDocumentStructures.getTDocumentsCollection()) {
                TDocumentsCollectionTDocumentsToAttach = em.getReference(TDocumentsCollectionTDocumentsToAttach.getClass(), TDocumentsCollectionTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollection.add(TDocumentsCollectionTDocumentsToAttach);
            }
            TDocumentStructures.setTDocumentsCollection(attachedTDocumentsCollection);
            Collection<TChapterTitles> attachedTChapterTitlesCollection = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionTChapterTitlesToAttach : TDocumentStructures.getTChapterTitlesCollection()) {
                TChapterTitlesCollectionTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollection.add(TChapterTitlesCollectionTChapterTitlesToAttach);
            }
            TDocumentStructures.setTChapterTitlesCollection(attachedTChapterTitlesCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TDocumentStructures.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TDocumentStructures.setTZipArchivesCollection(attachedTZipArchivesCollection);
            em.persist(TDocumentStructures);
            for (TDocuments TDocumentsCollectionTDocuments : TDocumentStructures.getTDocumentsCollection()) {
                TDocumentStructures oldDocuDstrNsqOfTDocumentsCollectionTDocuments = TDocumentsCollectionTDocuments.getDocuDstrNsq();
                TDocumentsCollectionTDocuments.setDocuDstrNsq(TDocumentStructures);
                TDocumentsCollectionTDocuments = em.merge(TDocumentsCollectionTDocuments);
                if (oldDocuDstrNsqOfTDocumentsCollectionTDocuments != null) {
                    oldDocuDstrNsqOfTDocumentsCollectionTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionTDocuments);
                    oldDocuDstrNsqOfTDocumentsCollectionTDocuments = em.merge(oldDocuDstrNsqOfTDocumentsCollectionTDocuments);
                }
            }
            for (TChapterTitles TChapterTitlesCollectionTChapterTitles : TDocumentStructures.getTChapterTitlesCollection()) {
                TDocumentStructures oldChtlDstrNsqOfTChapterTitlesCollectionTChapterTitles = TChapterTitlesCollectionTChapterTitles.getChtlDstrNsq();
                TChapterTitlesCollectionTChapterTitles.setChtlDstrNsq(TDocumentStructures);
                TChapterTitlesCollectionTChapterTitles = em.merge(TChapterTitlesCollectionTChapterTitles);
                if (oldChtlDstrNsqOfTChapterTitlesCollectionTChapterTitles != null) {
                    oldChtlDstrNsqOfTChapterTitlesCollectionTChapterTitles.getTChapterTitlesCollection().remove(TChapterTitlesCollectionTChapterTitles);
                    oldChtlDstrNsqOfTChapterTitlesCollectionTChapterTitles = em.merge(oldChtlDstrNsqOfTChapterTitlesCollectionTChapterTitles);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TDocumentStructures.getTZipArchivesCollection()) {
                TDocumentStructures oldZipaDstrNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaDstrNsq();
                TZipArchivesCollectionTZipArchives.setZipaDstrNsq(TDocumentStructures);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaDstrNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaDstrNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaDstrNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaDstrNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDocumentStructures(TDocumentStructures.getDstrNsq()) != null) {
                throw new PreexistingEntityException("TDocumentStructures " + TDocumentStructures + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDocumentStructures TDocumentStructures) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentStructures persistentTDocumentStructures = em.find(TDocumentStructures.class, TDocumentStructures.getDstrNsq());
            Collection<TDocuments> TDocumentsCollectionOld = persistentTDocumentStructures.getTDocumentsCollection();
            Collection<TDocuments> TDocumentsCollectionNew = TDocumentStructures.getTDocumentsCollection();
            Collection<TChapterTitles> TChapterTitlesCollectionOld = persistentTDocumentStructures.getTChapterTitlesCollection();
            Collection<TChapterTitles> TChapterTitlesCollectionNew = TDocumentStructures.getTChapterTitlesCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTDocumentStructures.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TDocumentStructures.getTZipArchivesCollection();
            List<String> illegalOrphanMessages = null;
            for (TDocuments TDocumentsCollectionOldTDocuments : TDocumentsCollectionOld) {
                if (!TDocumentsCollectionNew.contains(TDocumentsCollectionOldTDocuments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TDocuments " + TDocumentsCollectionOldTDocuments + " since its docuDstrNsq field is not nullable.");
                }
            }
            for (TChapterTitles TChapterTitlesCollectionOldTChapterTitles : TChapterTitlesCollectionOld) {
                if (!TChapterTitlesCollectionNew.contains(TChapterTitlesCollectionOldTChapterTitles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TChapterTitles " + TChapterTitlesCollectionOldTChapterTitles + " since its chtlDstrNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TDocuments> attachedTDocumentsCollectionNew = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionNewTDocumentsToAttach : TDocumentsCollectionNew) {
                TDocumentsCollectionNewTDocumentsToAttach = em.getReference(TDocumentsCollectionNewTDocumentsToAttach.getClass(), TDocumentsCollectionNewTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollectionNew.add(TDocumentsCollectionNewTDocumentsToAttach);
            }
            TDocumentsCollectionNew = attachedTDocumentsCollectionNew;
            TDocumentStructures.setTDocumentsCollection(TDocumentsCollectionNew);
            Collection<TChapterTitles> attachedTChapterTitlesCollectionNew = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitlesToAttach : TChapterTitlesCollectionNew) {
                TChapterTitlesCollectionNewTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionNewTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionNewTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollectionNew.add(TChapterTitlesCollectionNewTChapterTitlesToAttach);
            }
            TChapterTitlesCollectionNew = attachedTChapterTitlesCollectionNew;
            TDocumentStructures.setTChapterTitlesCollection(TChapterTitlesCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TDocumentStructures.setTZipArchivesCollection(TZipArchivesCollectionNew);
            TDocumentStructures = em.merge(TDocumentStructures);
            for (TDocuments TDocumentsCollectionNewTDocuments : TDocumentsCollectionNew) {
                if (!TDocumentsCollectionOld.contains(TDocumentsCollectionNewTDocuments)) {
                    TDocumentStructures oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments = TDocumentsCollectionNewTDocuments.getDocuDstrNsq();
                    TDocumentsCollectionNewTDocuments.setDocuDstrNsq(TDocumentStructures);
                    TDocumentsCollectionNewTDocuments = em.merge(TDocumentsCollectionNewTDocuments);
                    if (oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments != null && !oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments.equals(TDocumentStructures)) {
                        oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionNewTDocuments);
                        oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments = em.merge(oldDocuDstrNsqOfTDocumentsCollectionNewTDocuments);
                    }
                }
            }
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitles : TChapterTitlesCollectionNew) {
                if (!TChapterTitlesCollectionOld.contains(TChapterTitlesCollectionNewTChapterTitles)) {
                    TDocumentStructures oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles = TChapterTitlesCollectionNewTChapterTitles.getChtlDstrNsq();
                    TChapterTitlesCollectionNewTChapterTitles.setChtlDstrNsq(TDocumentStructures);
                    TChapterTitlesCollectionNewTChapterTitles = em.merge(TChapterTitlesCollectionNewTChapterTitles);
                    if (oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles != null && !oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles.equals(TDocumentStructures)) {
                        oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles.getTChapterTitlesCollection().remove(TChapterTitlesCollectionNewTChapterTitles);
                        oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles = em.merge(oldChtlDstrNsqOfTChapterTitlesCollectionNewTChapterTitles);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    TZipArchivesCollectionOldTZipArchives.setZipaDstrNsq(null);
                    TZipArchivesCollectionOldTZipArchives = em.merge(TZipArchivesCollectionOldTZipArchives);
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TDocumentStructures oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaDstrNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaDstrNsq(TDocumentStructures);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives.equals(TDocumentStructures)) {
                        oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaDstrNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TDocumentStructures.getDstrNsq();
                if (findTDocumentStructures(id) == null) {
                    throw new NonexistentEntityException("The tDocumentStructures with id " + id + " no longer exists.");
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
            TDocumentStructures TDocumentStructures;
            try {
                TDocumentStructures = em.getReference(TDocumentStructures.class, id);
                TDocumentStructures.getDstrNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDocumentStructures with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TDocuments> TDocumentsCollectionOrphanCheck = TDocumentStructures.getTDocumentsCollection();
            for (TDocuments TDocumentsCollectionOrphanCheckTDocuments : TDocumentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentStructures (" + TDocumentStructures + ") cannot be destroyed since the TDocuments " + TDocumentsCollectionOrphanCheckTDocuments + " in its TDocumentsCollection field has a non-nullable docuDstrNsq field.");
            }
            Collection<TChapterTitles> TChapterTitlesCollectionOrphanCheck = TDocumentStructures.getTChapterTitlesCollection();
            for (TChapterTitles TChapterTitlesCollectionOrphanCheckTChapterTitles : TChapterTitlesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentStructures (" + TDocumentStructures + ") cannot be destroyed since the TChapterTitles " + TChapterTitlesCollectionOrphanCheckTChapterTitles + " in its TChapterTitlesCollection field has a non-nullable chtlDstrNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TZipArchives> TZipArchivesCollection = TDocumentStructures.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionTZipArchives : TZipArchivesCollection) {
                TZipArchivesCollectionTZipArchives.setZipaDstrNsq(null);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
            }
            em.remove(TDocumentStructures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDocumentStructures> findTDocumentStructuresEntities() {
        return findTDocumentStructuresEntities(true, -1, -1);
    }

    public List<TDocumentStructures> findTDocumentStructuresEntities(int maxResults, int firstResult) {
        return findTDocumentStructuresEntities(false, maxResults, firstResult);
    }

    private List<TDocumentStructures> findTDocumentStructuresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDocumentStructures.class));
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

    public TDocumentStructures findTDocumentStructures(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDocumentStructures.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDocumentStructuresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDocumentStructures> rt = cq.from(TDocumentStructures.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
