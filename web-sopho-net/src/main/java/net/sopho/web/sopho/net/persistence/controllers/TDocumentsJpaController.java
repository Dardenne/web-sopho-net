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
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TDocumentStructures;
import net.sopho.web.sopho.net.persistence.entities.TVersions;
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import net.sopho.web.sopho.net.persistence.entities.TKeywords;
import net.sopho.web.sopho.net.persistence.entities.TPrintableDocuments;

/**
 *
 * @author LU01007
 */
public class TDocumentsJpaController implements Serializable {

    public TDocumentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDocuments TDocuments) throws PreexistingEntityException, Exception {
        if (TDocuments.getTChapterContentsCollection() == null) {
            TDocuments.setTChapterContentsCollection(new ArrayList<TChapterContents>());
        }
        if (TDocuments.getTBinaryContentsCollection() == null) {
            TDocuments.setTBinaryContentsCollection(new ArrayList<TBinaryContents>());
        }
        if (TDocuments.getTKeywordsCollection() == null) {
            TDocuments.setTKeywordsCollection(new ArrayList<TKeywords>());
        }
        if (TDocuments.getTPrintableDocumentsCollection() == null) {
            TDocuments.setTPrintableDocumentsCollection(new ArrayList<TPrintableDocuments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentLanguages docuDlanNsq = TDocuments.getDocuDlanNsq();
            if (docuDlanNsq != null) {
                docuDlanNsq = em.getReference(docuDlanNsq.getClass(), docuDlanNsq.getDlanNsq());
                TDocuments.setDocuDlanNsq(docuDlanNsq);
            }
            TDocumentStructures docuDstrNsq = TDocuments.getDocuDstrNsq();
            if (docuDstrNsq != null) {
                docuDstrNsq = em.getReference(docuDstrNsq.getClass(), docuDstrNsq.getDstrNsq());
                TDocuments.setDocuDstrNsq(docuDstrNsq);
            }
            TVersions docuVersNsq = TDocuments.getDocuVersNsq();
            if (docuVersNsq != null) {
                docuVersNsq = em.getReference(docuVersNsq.getClass(), docuVersNsq.getVersNsq());
                TDocuments.setDocuVersNsq(docuVersNsq);
            }
            Collection<TChapterContents> attachedTChapterContentsCollection = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionTChapterContentsToAttach : TDocuments.getTChapterContentsCollection()) {
                TChapterContentsCollectionTChapterContentsToAttach = em.getReference(TChapterContentsCollectionTChapterContentsToAttach.getClass(), TChapterContentsCollectionTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection.add(TChapterContentsCollectionTChapterContentsToAttach);
            }
            TDocuments.setTChapterContentsCollection(attachedTChapterContentsCollection);
            Collection<TBinaryContents> attachedTBinaryContentsCollection = new ArrayList<TBinaryContents>();
            for (TBinaryContents TBinaryContentsCollectionTBinaryContentsToAttach : TDocuments.getTBinaryContentsCollection()) {
                TBinaryContentsCollectionTBinaryContentsToAttach = em.getReference(TBinaryContentsCollectionTBinaryContentsToAttach.getClass(), TBinaryContentsCollectionTBinaryContentsToAttach.getBinrNsq());
                attachedTBinaryContentsCollection.add(TBinaryContentsCollectionTBinaryContentsToAttach);
            }
            TDocuments.setTBinaryContentsCollection(attachedTBinaryContentsCollection);
            Collection<TKeywords> attachedTKeywordsCollection = new ArrayList<TKeywords>();
            for (TKeywords TKeywordsCollectionTKeywordsToAttach : TDocuments.getTKeywordsCollection()) {
                TKeywordsCollectionTKeywordsToAttach = em.getReference(TKeywordsCollectionTKeywordsToAttach.getClass(), TKeywordsCollectionTKeywordsToAttach.getKywdNsq());
                attachedTKeywordsCollection.add(TKeywordsCollectionTKeywordsToAttach);
            }
            TDocuments.setTKeywordsCollection(attachedTKeywordsCollection);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollection = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocumentsToAttach : TDocuments.getTPrintableDocumentsCollection()) {
                TPrintableDocumentsCollectionTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollection.add(TPrintableDocumentsCollectionTPrintableDocumentsToAttach);
            }
            TDocuments.setTPrintableDocumentsCollection(attachedTPrintableDocumentsCollection);
            em.persist(TDocuments);
            if (docuDlanNsq != null) {
                docuDlanNsq.getTDocumentsCollection().add(TDocuments);
                docuDlanNsq = em.merge(docuDlanNsq);
            }
            if (docuDstrNsq != null) {
                docuDstrNsq.getTDocumentsCollection().add(TDocuments);
                docuDstrNsq = em.merge(docuDstrNsq);
            }
            if (docuVersNsq != null) {
                docuVersNsq.getTDocumentsCollection().add(TDocuments);
                docuVersNsq = em.merge(docuVersNsq);
            }
            for (TChapterContents TChapterContentsCollectionTChapterContents : TDocuments.getTChapterContentsCollection()) {
                TDocuments oldChcoDocuNsqOfTChapterContentsCollectionTChapterContents = TChapterContentsCollectionTChapterContents.getChcoDocuNsq();
                TChapterContentsCollectionTChapterContents.setChcoDocuNsq(TDocuments);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
                if (oldChcoDocuNsqOfTChapterContentsCollectionTChapterContents != null) {
                    oldChcoDocuNsqOfTChapterContentsCollectionTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionTChapterContents);
                    oldChcoDocuNsqOfTChapterContentsCollectionTChapterContents = em.merge(oldChcoDocuNsqOfTChapterContentsCollectionTChapterContents);
                }
            }
            for (TBinaryContents TBinaryContentsCollectionTBinaryContents : TDocuments.getTBinaryContentsCollection()) {
                TDocuments oldBinrDocuNsqOfTBinaryContentsCollectionTBinaryContents = TBinaryContentsCollectionTBinaryContents.getBinrDocuNsq();
                TBinaryContentsCollectionTBinaryContents.setBinrDocuNsq(TDocuments);
                TBinaryContentsCollectionTBinaryContents = em.merge(TBinaryContentsCollectionTBinaryContents);
                if (oldBinrDocuNsqOfTBinaryContentsCollectionTBinaryContents != null) {
                    oldBinrDocuNsqOfTBinaryContentsCollectionTBinaryContents.getTBinaryContentsCollection().remove(TBinaryContentsCollectionTBinaryContents);
                    oldBinrDocuNsqOfTBinaryContentsCollectionTBinaryContents = em.merge(oldBinrDocuNsqOfTBinaryContentsCollectionTBinaryContents);
                }
            }
            for (TKeywords TKeywordsCollectionTKeywords : TDocuments.getTKeywordsCollection()) {
                TDocuments oldKywdDocuNsqOfTKeywordsCollectionTKeywords = TKeywordsCollectionTKeywords.getKywdDocuNsq();
                TKeywordsCollectionTKeywords.setKywdDocuNsq(TDocuments);
                TKeywordsCollectionTKeywords = em.merge(TKeywordsCollectionTKeywords);
                if (oldKywdDocuNsqOfTKeywordsCollectionTKeywords != null) {
                    oldKywdDocuNsqOfTKeywordsCollectionTKeywords.getTKeywordsCollection().remove(TKeywordsCollectionTKeywords);
                    oldKywdDocuNsqOfTKeywordsCollectionTKeywords = em.merge(oldKywdDocuNsqOfTKeywordsCollectionTKeywords);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TDocuments.getTPrintableDocumentsCollection()) {
                TDocuments oldPdocDocuNsqOfTPrintableDocumentsCollectionTPrintableDocuments = TPrintableDocumentsCollectionTPrintableDocuments.getPdocDocuNsq();
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocDocuNsq(TDocuments);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
                if (oldPdocDocuNsqOfTPrintableDocumentsCollectionTPrintableDocuments != null) {
                    oldPdocDocuNsqOfTPrintableDocumentsCollectionTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionTPrintableDocuments);
                    oldPdocDocuNsqOfTPrintableDocumentsCollectionTPrintableDocuments = em.merge(oldPdocDocuNsqOfTPrintableDocumentsCollectionTPrintableDocuments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDocuments(TDocuments.getDocuNsq()) != null) {
                throw new PreexistingEntityException("TDocuments " + TDocuments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDocuments TDocuments) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocuments persistentTDocuments = em.find(TDocuments.class, TDocuments.getDocuNsq());
            TDocumentLanguages docuDlanNsqOld = persistentTDocuments.getDocuDlanNsq();
            TDocumentLanguages docuDlanNsqNew = TDocuments.getDocuDlanNsq();
            TDocumentStructures docuDstrNsqOld = persistentTDocuments.getDocuDstrNsq();
            TDocumentStructures docuDstrNsqNew = TDocuments.getDocuDstrNsq();
            TVersions docuVersNsqOld = persistentTDocuments.getDocuVersNsq();
            TVersions docuVersNsqNew = TDocuments.getDocuVersNsq();
            Collection<TChapterContents> TChapterContentsCollectionOld = persistentTDocuments.getTChapterContentsCollection();
            Collection<TChapterContents> TChapterContentsCollectionNew = TDocuments.getTChapterContentsCollection();
            Collection<TBinaryContents> TBinaryContentsCollectionOld = persistentTDocuments.getTBinaryContentsCollection();
            Collection<TBinaryContents> TBinaryContentsCollectionNew = TDocuments.getTBinaryContentsCollection();
            Collection<TKeywords> TKeywordsCollectionOld = persistentTDocuments.getTKeywordsCollection();
            Collection<TKeywords> TKeywordsCollectionNew = TDocuments.getTKeywordsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionOld = persistentTDocuments.getTPrintableDocumentsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionNew = TDocuments.getTPrintableDocumentsCollection();
            List<String> illegalOrphanMessages = null;
            for (TKeywords TKeywordsCollectionOldTKeywords : TKeywordsCollectionOld) {
                if (!TKeywordsCollectionNew.contains(TKeywordsCollectionOldTKeywords)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TKeywords " + TKeywordsCollectionOldTKeywords + " since its kywdDocuNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (docuDlanNsqNew != null) {
                docuDlanNsqNew = em.getReference(docuDlanNsqNew.getClass(), docuDlanNsqNew.getDlanNsq());
                TDocuments.setDocuDlanNsq(docuDlanNsqNew);
            }
            if (docuDstrNsqNew != null) {
                docuDstrNsqNew = em.getReference(docuDstrNsqNew.getClass(), docuDstrNsqNew.getDstrNsq());
                TDocuments.setDocuDstrNsq(docuDstrNsqNew);
            }
            if (docuVersNsqNew != null) {
                docuVersNsqNew = em.getReference(docuVersNsqNew.getClass(), docuVersNsqNew.getVersNsq());
                TDocuments.setDocuVersNsq(docuVersNsqNew);
            }
            Collection<TChapterContents> attachedTChapterContentsCollectionNew = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionNewTChapterContentsToAttach : TChapterContentsCollectionNew) {
                TChapterContentsCollectionNewTChapterContentsToAttach = em.getReference(TChapterContentsCollectionNewTChapterContentsToAttach.getClass(), TChapterContentsCollectionNewTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollectionNew.add(TChapterContentsCollectionNewTChapterContentsToAttach);
            }
            TChapterContentsCollectionNew = attachedTChapterContentsCollectionNew;
            TDocuments.setTChapterContentsCollection(TChapterContentsCollectionNew);
            Collection<TBinaryContents> attachedTBinaryContentsCollectionNew = new ArrayList<TBinaryContents>();
            for (TBinaryContents TBinaryContentsCollectionNewTBinaryContentsToAttach : TBinaryContentsCollectionNew) {
                TBinaryContentsCollectionNewTBinaryContentsToAttach = em.getReference(TBinaryContentsCollectionNewTBinaryContentsToAttach.getClass(), TBinaryContentsCollectionNewTBinaryContentsToAttach.getBinrNsq());
                attachedTBinaryContentsCollectionNew.add(TBinaryContentsCollectionNewTBinaryContentsToAttach);
            }
            TBinaryContentsCollectionNew = attachedTBinaryContentsCollectionNew;
            TDocuments.setTBinaryContentsCollection(TBinaryContentsCollectionNew);
            Collection<TKeywords> attachedTKeywordsCollectionNew = new ArrayList<TKeywords>();
            for (TKeywords TKeywordsCollectionNewTKeywordsToAttach : TKeywordsCollectionNew) {
                TKeywordsCollectionNewTKeywordsToAttach = em.getReference(TKeywordsCollectionNewTKeywordsToAttach.getClass(), TKeywordsCollectionNewTKeywordsToAttach.getKywdNsq());
                attachedTKeywordsCollectionNew.add(TKeywordsCollectionNewTKeywordsToAttach);
            }
            TKeywordsCollectionNew = attachedTKeywordsCollectionNew;
            TDocuments.setTKeywordsCollection(TKeywordsCollectionNew);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollectionNew = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach : TPrintableDocumentsCollectionNew) {
                TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollectionNew.add(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach);
            }
            TPrintableDocumentsCollectionNew = attachedTPrintableDocumentsCollectionNew;
            TDocuments.setTPrintableDocumentsCollection(TPrintableDocumentsCollectionNew);
            TDocuments = em.merge(TDocuments);
            if (docuDlanNsqOld != null && !docuDlanNsqOld.equals(docuDlanNsqNew)) {
                docuDlanNsqOld.getTDocumentsCollection().remove(TDocuments);
                docuDlanNsqOld = em.merge(docuDlanNsqOld);
            }
            if (docuDlanNsqNew != null && !docuDlanNsqNew.equals(docuDlanNsqOld)) {
                docuDlanNsqNew.getTDocumentsCollection().add(TDocuments);
                docuDlanNsqNew = em.merge(docuDlanNsqNew);
            }
            if (docuDstrNsqOld != null && !docuDstrNsqOld.equals(docuDstrNsqNew)) {
                docuDstrNsqOld.getTDocumentsCollection().remove(TDocuments);
                docuDstrNsqOld = em.merge(docuDstrNsqOld);
            }
            if (docuDstrNsqNew != null && !docuDstrNsqNew.equals(docuDstrNsqOld)) {
                docuDstrNsqNew.getTDocumentsCollection().add(TDocuments);
                docuDstrNsqNew = em.merge(docuDstrNsqNew);
            }
            if (docuVersNsqOld != null && !docuVersNsqOld.equals(docuVersNsqNew)) {
                docuVersNsqOld.getTDocumentsCollection().remove(TDocuments);
                docuVersNsqOld = em.merge(docuVersNsqOld);
            }
            if (docuVersNsqNew != null && !docuVersNsqNew.equals(docuVersNsqOld)) {
                docuVersNsqNew.getTDocumentsCollection().add(TDocuments);
                docuVersNsqNew = em.merge(docuVersNsqNew);
            }
            for (TChapterContents TChapterContentsCollectionOldTChapterContents : TChapterContentsCollectionOld) {
                if (!TChapterContentsCollectionNew.contains(TChapterContentsCollectionOldTChapterContents)) {
                    TChapterContentsCollectionOldTChapterContents.setChcoDocuNsq(null);
                    TChapterContentsCollectionOldTChapterContents = em.merge(TChapterContentsCollectionOldTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollectionNewTChapterContents : TChapterContentsCollectionNew) {
                if (!TChapterContentsCollectionOld.contains(TChapterContentsCollectionNewTChapterContents)) {
                    TDocuments oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents = TChapterContentsCollectionNewTChapterContents.getChcoDocuNsq();
                    TChapterContentsCollectionNewTChapterContents.setChcoDocuNsq(TDocuments);
                    TChapterContentsCollectionNewTChapterContents = em.merge(TChapterContentsCollectionNewTChapterContents);
                    if (oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents != null && !oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents.equals(TDocuments)) {
                        oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionNewTChapterContents);
                        oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents = em.merge(oldChcoDocuNsqOfTChapterContentsCollectionNewTChapterContents);
                    }
                }
            }
            for (TBinaryContents TBinaryContentsCollectionOldTBinaryContents : TBinaryContentsCollectionOld) {
                if (!TBinaryContentsCollectionNew.contains(TBinaryContentsCollectionOldTBinaryContents)) {
                    TBinaryContentsCollectionOldTBinaryContents.setBinrDocuNsq(null);
                    TBinaryContentsCollectionOldTBinaryContents = em.merge(TBinaryContentsCollectionOldTBinaryContents);
                }
            }
            for (TBinaryContents TBinaryContentsCollectionNewTBinaryContents : TBinaryContentsCollectionNew) {
                if (!TBinaryContentsCollectionOld.contains(TBinaryContentsCollectionNewTBinaryContents)) {
                    TDocuments oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents = TBinaryContentsCollectionNewTBinaryContents.getBinrDocuNsq();
                    TBinaryContentsCollectionNewTBinaryContents.setBinrDocuNsq(TDocuments);
                    TBinaryContentsCollectionNewTBinaryContents = em.merge(TBinaryContentsCollectionNewTBinaryContents);
                    if (oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents != null && !oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents.equals(TDocuments)) {
                        oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents.getTBinaryContentsCollection().remove(TBinaryContentsCollectionNewTBinaryContents);
                        oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents = em.merge(oldBinrDocuNsqOfTBinaryContentsCollectionNewTBinaryContents);
                    }
                }
            }
            for (TKeywords TKeywordsCollectionNewTKeywords : TKeywordsCollectionNew) {
                if (!TKeywordsCollectionOld.contains(TKeywordsCollectionNewTKeywords)) {
                    TDocuments oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords = TKeywordsCollectionNewTKeywords.getKywdDocuNsq();
                    TKeywordsCollectionNewTKeywords.setKywdDocuNsq(TDocuments);
                    TKeywordsCollectionNewTKeywords = em.merge(TKeywordsCollectionNewTKeywords);
                    if (oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords != null && !oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords.equals(TDocuments)) {
                        oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords.getTKeywordsCollection().remove(TKeywordsCollectionNewTKeywords);
                        oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords = em.merge(oldKywdDocuNsqOfTKeywordsCollectionNewTKeywords);
                    }
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionOldTPrintableDocuments : TPrintableDocumentsCollectionOld) {
                if (!TPrintableDocumentsCollectionNew.contains(TPrintableDocumentsCollectionOldTPrintableDocuments)) {
                    TPrintableDocumentsCollectionOldTPrintableDocuments.setPdocDocuNsq(null);
                    TPrintableDocumentsCollectionOldTPrintableDocuments = em.merge(TPrintableDocumentsCollectionOldTPrintableDocuments);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocuments : TPrintableDocumentsCollectionNew) {
                if (!TPrintableDocumentsCollectionOld.contains(TPrintableDocumentsCollectionNewTPrintableDocuments)) {
                    TDocuments oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = TPrintableDocumentsCollectionNewTPrintableDocuments.getPdocDocuNsq();
                    TPrintableDocumentsCollectionNewTPrintableDocuments.setPdocDocuNsq(TDocuments);
                    TPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(TPrintableDocumentsCollectionNewTPrintableDocuments);
                    if (oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments != null && !oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.equals(TDocuments)) {
                        oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionNewTPrintableDocuments);
                        oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(oldPdocDocuNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TDocuments.getDocuNsq();
                if (findTDocuments(id) == null) {
                    throw new NonexistentEntityException("The tDocuments with id " + id + " no longer exists.");
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
            TDocuments TDocuments;
            try {
                TDocuments = em.getReference(TDocuments.class, id);
                TDocuments.getDocuNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDocuments with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TKeywords> TKeywordsCollectionOrphanCheck = TDocuments.getTKeywordsCollection();
            for (TKeywords TKeywordsCollectionOrphanCheckTKeywords : TKeywordsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocuments (" + TDocuments + ") cannot be destroyed since the TKeywords " + TKeywordsCollectionOrphanCheckTKeywords + " in its TKeywordsCollection field has a non-nullable kywdDocuNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TDocumentLanguages docuDlanNsq = TDocuments.getDocuDlanNsq();
            if (docuDlanNsq != null) {
                docuDlanNsq.getTDocumentsCollection().remove(TDocuments);
                docuDlanNsq = em.merge(docuDlanNsq);
            }
            TDocumentStructures docuDstrNsq = TDocuments.getDocuDstrNsq();
            if (docuDstrNsq != null) {
                docuDstrNsq.getTDocumentsCollection().remove(TDocuments);
                docuDstrNsq = em.merge(docuDstrNsq);
            }
            TVersions docuVersNsq = TDocuments.getDocuVersNsq();
            if (docuVersNsq != null) {
                docuVersNsq.getTDocumentsCollection().remove(TDocuments);
                docuVersNsq = em.merge(docuVersNsq);
            }
            Collection<TChapterContents> TChapterContentsCollection = TDocuments.getTChapterContentsCollection();
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterContentsCollection) {
                TChapterContentsCollectionTChapterContents.setChcoDocuNsq(null);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
            }
            Collection<TBinaryContents> TBinaryContentsCollection = TDocuments.getTBinaryContentsCollection();
            for (TBinaryContents TBinaryContentsCollectionTBinaryContents : TBinaryContentsCollection) {
                TBinaryContentsCollectionTBinaryContents.setBinrDocuNsq(null);
                TBinaryContentsCollectionTBinaryContents = em.merge(TBinaryContentsCollectionTBinaryContents);
            }
            Collection<TPrintableDocuments> TPrintableDocumentsCollection = TDocuments.getTPrintableDocumentsCollection();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TPrintableDocumentsCollection) {
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocDocuNsq(null);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
            }
            em.remove(TDocuments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDocuments> findTDocumentsEntities() {
        return findTDocumentsEntities(true, -1, -1);
    }

    public List<TDocuments> findTDocumentsEntities(int maxResults, int firstResult) {
        return findTDocumentsEntities(false, maxResults, firstResult);
    }

    private List<TDocuments> findTDocumentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDocuments.class));
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

    public TDocuments findTDocuments(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDocuments.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDocumentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDocuments> rt = cq.from(TDocuments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
