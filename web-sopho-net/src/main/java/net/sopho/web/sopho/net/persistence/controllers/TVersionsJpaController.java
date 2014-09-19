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
import net.sopho.web.sopho.net.persistence.entities.TMajorVersionNumbers;
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TVersionComments;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;
import net.sopho.web.sopho.net.persistence.entities.TPrintableDocuments;
import net.sopho.web.sopho.net.persistence.entities.TVersions;

/**
 *
 * @author LU01007
 */
public class TVersionsJpaController implements Serializable {

    public TVersionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TVersions TVersions) throws PreexistingEntityException, Exception {
        if (TVersions.getTDocumentsCollection() == null) {
            TVersions.setTDocumentsCollection(new ArrayList<TDocuments>());
        }
        if (TVersions.getTVersionCommentsCollection() == null) {
            TVersions.setTVersionCommentsCollection(new ArrayList<TVersionComments>());
        }
        if (TVersions.getTZipArchivesCollection() == null) {
            TVersions.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        if (TVersions.getTPrintableDocumentsCollection() == null) {
            TVersions.setTPrintableDocumentsCollection(new ArrayList<TPrintableDocuments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMajorVersionNumbers versMavnNsq = TVersions.getVersMavnNsq();
            if (versMavnNsq != null) {
                versMavnNsq = em.getReference(versMavnNsq.getClass(), versMavnNsq.getMavnNsq());
                TVersions.setVersMavnNsq(versMavnNsq);
            }
            Collection<TDocuments> attachedTDocumentsCollection = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionTDocumentsToAttach : TVersions.getTDocumentsCollection()) {
                TDocumentsCollectionTDocumentsToAttach = em.getReference(TDocumentsCollectionTDocumentsToAttach.getClass(), TDocumentsCollectionTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollection.add(TDocumentsCollectionTDocumentsToAttach);
            }
            TVersions.setTDocumentsCollection(attachedTDocumentsCollection);
            Collection<TVersionComments> attachedTVersionCommentsCollection = new ArrayList<TVersionComments>();
            for (TVersionComments TVersionCommentsCollectionTVersionCommentsToAttach : TVersions.getTVersionCommentsCollection()) {
                TVersionCommentsCollectionTVersionCommentsToAttach = em.getReference(TVersionCommentsCollectionTVersionCommentsToAttach.getClass(), TVersionCommentsCollectionTVersionCommentsToAttach.getVercNsq());
                attachedTVersionCommentsCollection.add(TVersionCommentsCollectionTVersionCommentsToAttach);
            }
            TVersions.setTVersionCommentsCollection(attachedTVersionCommentsCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TVersions.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TVersions.setTZipArchivesCollection(attachedTZipArchivesCollection);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollection = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocumentsToAttach : TVersions.getTPrintableDocumentsCollection()) {
                TPrintableDocumentsCollectionTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollection.add(TPrintableDocumentsCollectionTPrintableDocumentsToAttach);
            }
            TVersions.setTPrintableDocumentsCollection(attachedTPrintableDocumentsCollection);
            em.persist(TVersions);
            if (versMavnNsq != null) {
                versMavnNsq.getTVersionsCollection().add(TVersions);
                versMavnNsq = em.merge(versMavnNsq);
            }
            for (TDocuments TDocumentsCollectionTDocuments : TVersions.getTDocumentsCollection()) {
                TVersions oldDocuVersNsqOfTDocumentsCollectionTDocuments = TDocumentsCollectionTDocuments.getDocuVersNsq();
                TDocumentsCollectionTDocuments.setDocuVersNsq(TVersions);
                TDocumentsCollectionTDocuments = em.merge(TDocumentsCollectionTDocuments);
                if (oldDocuVersNsqOfTDocumentsCollectionTDocuments != null) {
                    oldDocuVersNsqOfTDocumentsCollectionTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionTDocuments);
                    oldDocuVersNsqOfTDocumentsCollectionTDocuments = em.merge(oldDocuVersNsqOfTDocumentsCollectionTDocuments);
                }
            }
            for (TVersionComments TVersionCommentsCollectionTVersionComments : TVersions.getTVersionCommentsCollection()) {
                TVersions oldVercVersNsqOfTVersionCommentsCollectionTVersionComments = TVersionCommentsCollectionTVersionComments.getVercVersNsq();
                TVersionCommentsCollectionTVersionComments.setVercVersNsq(TVersions);
                TVersionCommentsCollectionTVersionComments = em.merge(TVersionCommentsCollectionTVersionComments);
                if (oldVercVersNsqOfTVersionCommentsCollectionTVersionComments != null) {
                    oldVercVersNsqOfTVersionCommentsCollectionTVersionComments.getTVersionCommentsCollection().remove(TVersionCommentsCollectionTVersionComments);
                    oldVercVersNsqOfTVersionCommentsCollectionTVersionComments = em.merge(oldVercVersNsqOfTVersionCommentsCollectionTVersionComments);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TVersions.getTZipArchivesCollection()) {
                TVersions oldZipaVersNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaVersNsq();
                TZipArchivesCollectionTZipArchives.setZipaVersNsq(TVersions);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaVersNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaVersNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaVersNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaVersNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TVersions.getTPrintableDocumentsCollection()) {
                TVersions oldPdocVersNsqOfTPrintableDocumentsCollectionTPrintableDocuments = TPrintableDocumentsCollectionTPrintableDocuments.getPdocVersNsq();
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocVersNsq(TVersions);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
                if (oldPdocVersNsqOfTPrintableDocumentsCollectionTPrintableDocuments != null) {
                    oldPdocVersNsqOfTPrintableDocumentsCollectionTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionTPrintableDocuments);
                    oldPdocVersNsqOfTPrintableDocumentsCollectionTPrintableDocuments = em.merge(oldPdocVersNsqOfTPrintableDocumentsCollectionTPrintableDocuments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTVersions(TVersions.getVersNsq()) != null) {
                throw new PreexistingEntityException("TVersions " + TVersions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TVersions TVersions) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TVersions persistentTVersions = em.find(TVersions.class, TVersions.getVersNsq());
            TMajorVersionNumbers versMavnNsqOld = persistentTVersions.getVersMavnNsq();
            TMajorVersionNumbers versMavnNsqNew = TVersions.getVersMavnNsq();
            Collection<TDocuments> TDocumentsCollectionOld = persistentTVersions.getTDocumentsCollection();
            Collection<TDocuments> TDocumentsCollectionNew = TVersions.getTDocumentsCollection();
            Collection<TVersionComments> TVersionCommentsCollectionOld = persistentTVersions.getTVersionCommentsCollection();
            Collection<TVersionComments> TVersionCommentsCollectionNew = TVersions.getTVersionCommentsCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTVersions.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TVersions.getTZipArchivesCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionOld = persistentTVersions.getTPrintableDocumentsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionNew = TVersions.getTPrintableDocumentsCollection();
            List<String> illegalOrphanMessages = null;
            for (TDocuments TDocumentsCollectionOldTDocuments : TDocumentsCollectionOld) {
                if (!TDocumentsCollectionNew.contains(TDocumentsCollectionOldTDocuments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TDocuments " + TDocumentsCollectionOldTDocuments + " since its docuVersNsq field is not nullable.");
                }
            }
            for (TVersionComments TVersionCommentsCollectionOldTVersionComments : TVersionCommentsCollectionOld) {
                if (!TVersionCommentsCollectionNew.contains(TVersionCommentsCollectionOldTVersionComments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TVersionComments " + TVersionCommentsCollectionOldTVersionComments + " since its vercVersNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (versMavnNsqNew != null) {
                versMavnNsqNew = em.getReference(versMavnNsqNew.getClass(), versMavnNsqNew.getMavnNsq());
                TVersions.setVersMavnNsq(versMavnNsqNew);
            }
            Collection<TDocuments> attachedTDocumentsCollectionNew = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionNewTDocumentsToAttach : TDocumentsCollectionNew) {
                TDocumentsCollectionNewTDocumentsToAttach = em.getReference(TDocumentsCollectionNewTDocumentsToAttach.getClass(), TDocumentsCollectionNewTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollectionNew.add(TDocumentsCollectionNewTDocumentsToAttach);
            }
            TDocumentsCollectionNew = attachedTDocumentsCollectionNew;
            TVersions.setTDocumentsCollection(TDocumentsCollectionNew);
            Collection<TVersionComments> attachedTVersionCommentsCollectionNew = new ArrayList<TVersionComments>();
            for (TVersionComments TVersionCommentsCollectionNewTVersionCommentsToAttach : TVersionCommentsCollectionNew) {
                TVersionCommentsCollectionNewTVersionCommentsToAttach = em.getReference(TVersionCommentsCollectionNewTVersionCommentsToAttach.getClass(), TVersionCommentsCollectionNewTVersionCommentsToAttach.getVercNsq());
                attachedTVersionCommentsCollectionNew.add(TVersionCommentsCollectionNewTVersionCommentsToAttach);
            }
            TVersionCommentsCollectionNew = attachedTVersionCommentsCollectionNew;
            TVersions.setTVersionCommentsCollection(TVersionCommentsCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TVersions.setTZipArchivesCollection(TZipArchivesCollectionNew);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollectionNew = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach : TPrintableDocumentsCollectionNew) {
                TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollectionNew.add(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach);
            }
            TPrintableDocumentsCollectionNew = attachedTPrintableDocumentsCollectionNew;
            TVersions.setTPrintableDocumentsCollection(TPrintableDocumentsCollectionNew);
            TVersions = em.merge(TVersions);
            if (versMavnNsqOld != null && !versMavnNsqOld.equals(versMavnNsqNew)) {
                versMavnNsqOld.getTVersionsCollection().remove(TVersions);
                versMavnNsqOld = em.merge(versMavnNsqOld);
            }
            if (versMavnNsqNew != null && !versMavnNsqNew.equals(versMavnNsqOld)) {
                versMavnNsqNew.getTVersionsCollection().add(TVersions);
                versMavnNsqNew = em.merge(versMavnNsqNew);
            }
            for (TDocuments TDocumentsCollectionNewTDocuments : TDocumentsCollectionNew) {
                if (!TDocumentsCollectionOld.contains(TDocumentsCollectionNewTDocuments)) {
                    TVersions oldDocuVersNsqOfTDocumentsCollectionNewTDocuments = TDocumentsCollectionNewTDocuments.getDocuVersNsq();
                    TDocumentsCollectionNewTDocuments.setDocuVersNsq(TVersions);
                    TDocumentsCollectionNewTDocuments = em.merge(TDocumentsCollectionNewTDocuments);
                    if (oldDocuVersNsqOfTDocumentsCollectionNewTDocuments != null && !oldDocuVersNsqOfTDocumentsCollectionNewTDocuments.equals(TVersions)) {
                        oldDocuVersNsqOfTDocumentsCollectionNewTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionNewTDocuments);
                        oldDocuVersNsqOfTDocumentsCollectionNewTDocuments = em.merge(oldDocuVersNsqOfTDocumentsCollectionNewTDocuments);
                    }
                }
            }
            for (TVersionComments TVersionCommentsCollectionNewTVersionComments : TVersionCommentsCollectionNew) {
                if (!TVersionCommentsCollectionOld.contains(TVersionCommentsCollectionNewTVersionComments)) {
                    TVersions oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments = TVersionCommentsCollectionNewTVersionComments.getVercVersNsq();
                    TVersionCommentsCollectionNewTVersionComments.setVercVersNsq(TVersions);
                    TVersionCommentsCollectionNewTVersionComments = em.merge(TVersionCommentsCollectionNewTVersionComments);
                    if (oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments != null && !oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments.equals(TVersions)) {
                        oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments.getTVersionCommentsCollection().remove(TVersionCommentsCollectionNewTVersionComments);
                        oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments = em.merge(oldVercVersNsqOfTVersionCommentsCollectionNewTVersionComments);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    TZipArchivesCollectionOldTZipArchives.setZipaVersNsq(null);
                    TZipArchivesCollectionOldTZipArchives = em.merge(TZipArchivesCollectionOldTZipArchives);
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TVersions oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaVersNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaVersNsq(TVersions);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives.equals(TVersions)) {
                        oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaVersNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionOldTPrintableDocuments : TPrintableDocumentsCollectionOld) {
                if (!TPrintableDocumentsCollectionNew.contains(TPrintableDocumentsCollectionOldTPrintableDocuments)) {
                    TPrintableDocumentsCollectionOldTPrintableDocuments.setPdocVersNsq(null);
                    TPrintableDocumentsCollectionOldTPrintableDocuments = em.merge(TPrintableDocumentsCollectionOldTPrintableDocuments);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocuments : TPrintableDocumentsCollectionNew) {
                if (!TPrintableDocumentsCollectionOld.contains(TPrintableDocumentsCollectionNewTPrintableDocuments)) {
                    TVersions oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = TPrintableDocumentsCollectionNewTPrintableDocuments.getPdocVersNsq();
                    TPrintableDocumentsCollectionNewTPrintableDocuments.setPdocVersNsq(TVersions);
                    TPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(TPrintableDocumentsCollectionNewTPrintableDocuments);
                    if (oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments != null && !oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.equals(TVersions)) {
                        oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionNewTPrintableDocuments);
                        oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(oldPdocVersNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TVersions.getVersNsq();
                if (findTVersions(id) == null) {
                    throw new NonexistentEntityException("The tVersions with id " + id + " no longer exists.");
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
            TVersions TVersions;
            try {
                TVersions = em.getReference(TVersions.class, id);
                TVersions.getVersNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TVersions with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TDocuments> TDocumentsCollectionOrphanCheck = TVersions.getTDocumentsCollection();
            for (TDocuments TDocumentsCollectionOrphanCheckTDocuments : TDocumentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TVersions (" + TVersions + ") cannot be destroyed since the TDocuments " + TDocumentsCollectionOrphanCheckTDocuments + " in its TDocumentsCollection field has a non-nullable docuVersNsq field.");
            }
            Collection<TVersionComments> TVersionCommentsCollectionOrphanCheck = TVersions.getTVersionCommentsCollection();
            for (TVersionComments TVersionCommentsCollectionOrphanCheckTVersionComments : TVersionCommentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TVersions (" + TVersions + ") cannot be destroyed since the TVersionComments " + TVersionCommentsCollectionOrphanCheckTVersionComments + " in its TVersionCommentsCollection field has a non-nullable vercVersNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TMajorVersionNumbers versMavnNsq = TVersions.getVersMavnNsq();
            if (versMavnNsq != null) {
                versMavnNsq.getTVersionsCollection().remove(TVersions);
                versMavnNsq = em.merge(versMavnNsq);
            }
            Collection<TZipArchives> TZipArchivesCollection = TVersions.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionTZipArchives : TZipArchivesCollection) {
                TZipArchivesCollectionTZipArchives.setZipaVersNsq(null);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
            }
            Collection<TPrintableDocuments> TPrintableDocumentsCollection = TVersions.getTPrintableDocumentsCollection();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TPrintableDocumentsCollection) {
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocVersNsq(null);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
            }
            em.remove(TVersions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TVersions> findTVersionsEntities() {
        return findTVersionsEntities(true, -1, -1);
    }

    public List<TVersions> findTVersionsEntities(int maxResults, int firstResult) {
        return findTVersionsEntities(false, maxResults, firstResult);
    }

    private List<TVersions> findTVersionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TVersions.class));
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

    public TVersions findTVersions(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TVersions.class, id);
        } finally {
            em.close();
        }
    }

    public int getTVersionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TVersions> rt = cq.from(TVersions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
