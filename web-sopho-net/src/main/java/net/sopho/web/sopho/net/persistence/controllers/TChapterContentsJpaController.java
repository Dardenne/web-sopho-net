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
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TChapterImages;
import net.sopho.web.sopho.net.persistence.entities.TKeywordTranslations;

/**
 *
 * @author LU01007
 */
public class TChapterContentsJpaController implements Serializable {

    public TChapterContentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TChapterContents TChapterContents) throws PreexistingEntityException, Exception {
        if (TChapterContents.getTChapterContentsCollection() == null) {
            TChapterContents.setTChapterContentsCollection(new ArrayList<TChapterContents>());
        }
        if (TChapterContents.getTChapterImagesCollection() == null) {
            TChapterContents.setTChapterImagesCollection(new ArrayList<TChapterImages>());
        }
        if (TChapterContents.getTKeywordTranslationsCollection() == null) {
            TChapterContents.setTKeywordTranslationsCollection(new ArrayList<TKeywordTranslations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBinaryContents chcoBinrNsqXmlContent = TChapterContents.getChcoBinrNsqXmlContent();
            if (chcoBinrNsqXmlContent != null) {
                chcoBinrNsqXmlContent = em.getReference(chcoBinrNsqXmlContent.getClass(), chcoBinrNsqXmlContent.getBinrNsq());
                TChapterContents.setChcoBinrNsqXmlContent(chcoBinrNsqXmlContent);
            }
            TBinaryContents chcoBinrNsqPdfFile = TChapterContents.getChcoBinrNsqPdfFile();
            if (chcoBinrNsqPdfFile != null) {
                chcoBinrNsqPdfFile = em.getReference(chcoBinrNsqPdfFile.getClass(), chcoBinrNsqPdfFile.getBinrNsq());
                TChapterContents.setChcoBinrNsqPdfFile(chcoBinrNsqPdfFile);
            }
            TChapterContents chcoParentNsq = TChapterContents.getChcoParentNsq();
            if (chcoParentNsq != null) {
                chcoParentNsq = em.getReference(chcoParentNsq.getClass(), chcoParentNsq.getChcoNsq());
                TChapterContents.setChcoParentNsq(chcoParentNsq);
            }
            TChapterTitles chcoChtlNsq = TChapterContents.getChcoChtlNsq();
            if (chcoChtlNsq != null) {
                chcoChtlNsq = em.getReference(chcoChtlNsq.getClass(), chcoChtlNsq.getChtlNsq());
                TChapterContents.setChcoChtlNsq(chcoChtlNsq);
            }
            TDocuments chcoDocuNsq = TChapterContents.getChcoDocuNsq();
            if (chcoDocuNsq != null) {
                chcoDocuNsq = em.getReference(chcoDocuNsq.getClass(), chcoDocuNsq.getDocuNsq());
                TChapterContents.setChcoDocuNsq(chcoDocuNsq);
            }
            Collection<TChapterContents> attachedTChapterContentsCollection = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionTChapterContentsToAttach : TChapterContents.getTChapterContentsCollection()) {
                TChapterContentsCollectionTChapterContentsToAttach = em.getReference(TChapterContentsCollectionTChapterContentsToAttach.getClass(), TChapterContentsCollectionTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection.add(TChapterContentsCollectionTChapterContentsToAttach);
            }
            TChapterContents.setTChapterContentsCollection(attachedTChapterContentsCollection);
            Collection<TChapterImages> attachedTChapterImagesCollection = new ArrayList<TChapterImages>();
            for (TChapterImages TChapterImagesCollectionTChapterImagesToAttach : TChapterContents.getTChapterImagesCollection()) {
                TChapterImagesCollectionTChapterImagesToAttach = em.getReference(TChapterImagesCollectionTChapterImagesToAttach.getClass(), TChapterImagesCollectionTChapterImagesToAttach.getChimNsq());
                attachedTChapterImagesCollection.add(TChapterImagesCollectionTChapterImagesToAttach);
            }
            TChapterContents.setTChapterImagesCollection(attachedTChapterImagesCollection);
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollection = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslationsToAttach : TChapterContents.getTKeywordTranslationsCollection()) {
                TKeywordTranslationsCollectionTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollection.add(TKeywordTranslationsCollectionTKeywordTranslationsToAttach);
            }
            TChapterContents.setTKeywordTranslationsCollection(attachedTKeywordTranslationsCollection);
            em.persist(TChapterContents);
            if (chcoBinrNsqXmlContent != null) {
                chcoBinrNsqXmlContent.getTChapterContentsCollection().add(TChapterContents);
                chcoBinrNsqXmlContent = em.merge(chcoBinrNsqXmlContent);
            }
            if (chcoBinrNsqPdfFile != null) {
                chcoBinrNsqPdfFile.getTChapterContentsCollection().add(TChapterContents);
                chcoBinrNsqPdfFile = em.merge(chcoBinrNsqPdfFile);
            }
            if (chcoParentNsq != null) {
                chcoParentNsq.getTChapterContentsCollection().add(TChapterContents);
                chcoParentNsq = em.merge(chcoParentNsq);
            }
            if (chcoChtlNsq != null) {
                chcoChtlNsq.getTChapterContentsCollection().add(TChapterContents);
                chcoChtlNsq = em.merge(chcoChtlNsq);
            }
            if (chcoDocuNsq != null) {
                chcoDocuNsq.getTChapterContentsCollection().add(TChapterContents);
                chcoDocuNsq = em.merge(chcoDocuNsq);
            }
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterContents.getTChapterContentsCollection()) {
                TChapterContents oldChcoParentNsqOfTChapterContentsCollectionTChapterContents = TChapterContentsCollectionTChapterContents.getChcoParentNsq();
                TChapterContentsCollectionTChapterContents.setChcoParentNsq(TChapterContents);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
                if (oldChcoParentNsqOfTChapterContentsCollectionTChapterContents != null) {
                    oldChcoParentNsqOfTChapterContentsCollectionTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionTChapterContents);
                    oldChcoParentNsqOfTChapterContentsCollectionTChapterContents = em.merge(oldChcoParentNsqOfTChapterContentsCollectionTChapterContents);
                }
            }
            for (TChapterImages TChapterImagesCollectionTChapterImages : TChapterContents.getTChapterImagesCollection()) {
                TChapterContents oldChimChcoNsqOfTChapterImagesCollectionTChapterImages = TChapterImagesCollectionTChapterImages.getChimChcoNsq();
                TChapterImagesCollectionTChapterImages.setChimChcoNsq(TChapterContents);
                TChapterImagesCollectionTChapterImages = em.merge(TChapterImagesCollectionTChapterImages);
                if (oldChimChcoNsqOfTChapterImagesCollectionTChapterImages != null) {
                    oldChimChcoNsqOfTChapterImagesCollectionTChapterImages.getTChapterImagesCollection().remove(TChapterImagesCollectionTChapterImages);
                    oldChimChcoNsqOfTChapterImagesCollectionTChapterImages = em.merge(oldChimChcoNsqOfTChapterImagesCollectionTChapterImages);
                }
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslations : TChapterContents.getTKeywordTranslationsCollection()) {
                TChapterContents oldKytrChcoNsqOfTKeywordTranslationsCollectionTKeywordTranslations = TKeywordTranslationsCollectionTKeywordTranslations.getKytrChcoNsq();
                TKeywordTranslationsCollectionTKeywordTranslations.setKytrChcoNsq(TChapterContents);
                TKeywordTranslationsCollectionTKeywordTranslations = em.merge(TKeywordTranslationsCollectionTKeywordTranslations);
                if (oldKytrChcoNsqOfTKeywordTranslationsCollectionTKeywordTranslations != null) {
                    oldKytrChcoNsqOfTKeywordTranslationsCollectionTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionTKeywordTranslations);
                    oldKytrChcoNsqOfTKeywordTranslationsCollectionTKeywordTranslations = em.merge(oldKytrChcoNsqOfTKeywordTranslationsCollectionTKeywordTranslations);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTChapterContents(TChapterContents.getChcoNsq()) != null) {
                throw new PreexistingEntityException("TChapterContents " + TChapterContents + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TChapterContents TChapterContents) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterContents persistentTChapterContents = em.find(TChapterContents.class, TChapterContents.getChcoNsq());
            TBinaryContents chcoBinrNsqXmlContentOld = persistentTChapterContents.getChcoBinrNsqXmlContent();
            TBinaryContents chcoBinrNsqXmlContentNew = TChapterContents.getChcoBinrNsqXmlContent();
            TBinaryContents chcoBinrNsqPdfFileOld = persistentTChapterContents.getChcoBinrNsqPdfFile();
            TBinaryContents chcoBinrNsqPdfFileNew = TChapterContents.getChcoBinrNsqPdfFile();
            TChapterContents chcoParentNsqOld = persistentTChapterContents.getChcoParentNsq();
            TChapterContents chcoParentNsqNew = TChapterContents.getChcoParentNsq();
            TChapterTitles chcoChtlNsqOld = persistentTChapterContents.getChcoChtlNsq();
            TChapterTitles chcoChtlNsqNew = TChapterContents.getChcoChtlNsq();
            TDocuments chcoDocuNsqOld = persistentTChapterContents.getChcoDocuNsq();
            TDocuments chcoDocuNsqNew = TChapterContents.getChcoDocuNsq();
            Collection<TChapterContents> TChapterContentsCollectionOld = persistentTChapterContents.getTChapterContentsCollection();
            Collection<TChapterContents> TChapterContentsCollectionNew = TChapterContents.getTChapterContentsCollection();
            Collection<TChapterImages> TChapterImagesCollectionOld = persistentTChapterContents.getTChapterImagesCollection();
            Collection<TChapterImages> TChapterImagesCollectionNew = TChapterContents.getTChapterImagesCollection();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOld = persistentTChapterContents.getTKeywordTranslationsCollection();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionNew = TChapterContents.getTKeywordTranslationsCollection();
            List<String> illegalOrphanMessages = null;
            for (TKeywordTranslations TKeywordTranslationsCollectionOldTKeywordTranslations : TKeywordTranslationsCollectionOld) {
                if (!TKeywordTranslationsCollectionNew.contains(TKeywordTranslationsCollectionOldTKeywordTranslations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TKeywordTranslations " + TKeywordTranslationsCollectionOldTKeywordTranslations + " since its kytrChcoNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (chcoBinrNsqXmlContentNew != null) {
                chcoBinrNsqXmlContentNew = em.getReference(chcoBinrNsqXmlContentNew.getClass(), chcoBinrNsqXmlContentNew.getBinrNsq());
                TChapterContents.setChcoBinrNsqXmlContent(chcoBinrNsqXmlContentNew);
            }
            if (chcoBinrNsqPdfFileNew != null) {
                chcoBinrNsqPdfFileNew = em.getReference(chcoBinrNsqPdfFileNew.getClass(), chcoBinrNsqPdfFileNew.getBinrNsq());
                TChapterContents.setChcoBinrNsqPdfFile(chcoBinrNsqPdfFileNew);
            }
            if (chcoParentNsqNew != null) {
                chcoParentNsqNew = em.getReference(chcoParentNsqNew.getClass(), chcoParentNsqNew.getChcoNsq());
                TChapterContents.setChcoParentNsq(chcoParentNsqNew);
            }
            if (chcoChtlNsqNew != null) {
                chcoChtlNsqNew = em.getReference(chcoChtlNsqNew.getClass(), chcoChtlNsqNew.getChtlNsq());
                TChapterContents.setChcoChtlNsq(chcoChtlNsqNew);
            }
            if (chcoDocuNsqNew != null) {
                chcoDocuNsqNew = em.getReference(chcoDocuNsqNew.getClass(), chcoDocuNsqNew.getDocuNsq());
                TChapterContents.setChcoDocuNsq(chcoDocuNsqNew);
            }
            Collection<TChapterContents> attachedTChapterContentsCollectionNew = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionNewTChapterContentsToAttach : TChapterContentsCollectionNew) {
                TChapterContentsCollectionNewTChapterContentsToAttach = em.getReference(TChapterContentsCollectionNewTChapterContentsToAttach.getClass(), TChapterContentsCollectionNewTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollectionNew.add(TChapterContentsCollectionNewTChapterContentsToAttach);
            }
            TChapterContentsCollectionNew = attachedTChapterContentsCollectionNew;
            TChapterContents.setTChapterContentsCollection(TChapterContentsCollectionNew);
            Collection<TChapterImages> attachedTChapterImagesCollectionNew = new ArrayList<TChapterImages>();
            for (TChapterImages TChapterImagesCollectionNewTChapterImagesToAttach : TChapterImagesCollectionNew) {
                TChapterImagesCollectionNewTChapterImagesToAttach = em.getReference(TChapterImagesCollectionNewTChapterImagesToAttach.getClass(), TChapterImagesCollectionNewTChapterImagesToAttach.getChimNsq());
                attachedTChapterImagesCollectionNew.add(TChapterImagesCollectionNewTChapterImagesToAttach);
            }
            TChapterImagesCollectionNew = attachedTChapterImagesCollectionNew;
            TChapterContents.setTChapterImagesCollection(TChapterImagesCollectionNew);
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollectionNew = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach : TKeywordTranslationsCollectionNew) {
                TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollectionNew.add(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach);
            }
            TKeywordTranslationsCollectionNew = attachedTKeywordTranslationsCollectionNew;
            TChapterContents.setTKeywordTranslationsCollection(TKeywordTranslationsCollectionNew);
            TChapterContents = em.merge(TChapterContents);
            if (chcoBinrNsqXmlContentOld != null && !chcoBinrNsqXmlContentOld.equals(chcoBinrNsqXmlContentNew)) {
                chcoBinrNsqXmlContentOld.getTChapterContentsCollection().remove(TChapterContents);
                chcoBinrNsqXmlContentOld = em.merge(chcoBinrNsqXmlContentOld);
            }
            if (chcoBinrNsqXmlContentNew != null && !chcoBinrNsqXmlContentNew.equals(chcoBinrNsqXmlContentOld)) {
                chcoBinrNsqXmlContentNew.getTChapterContentsCollection().add(TChapterContents);
                chcoBinrNsqXmlContentNew = em.merge(chcoBinrNsqXmlContentNew);
            }
            if (chcoBinrNsqPdfFileOld != null && !chcoBinrNsqPdfFileOld.equals(chcoBinrNsqPdfFileNew)) {
                chcoBinrNsqPdfFileOld.getTChapterContentsCollection().remove(TChapterContents);
                chcoBinrNsqPdfFileOld = em.merge(chcoBinrNsqPdfFileOld);
            }
            if (chcoBinrNsqPdfFileNew != null && !chcoBinrNsqPdfFileNew.equals(chcoBinrNsqPdfFileOld)) {
                chcoBinrNsqPdfFileNew.getTChapterContentsCollection().add(TChapterContents);
                chcoBinrNsqPdfFileNew = em.merge(chcoBinrNsqPdfFileNew);
            }
            if (chcoParentNsqOld != null && !chcoParentNsqOld.equals(chcoParentNsqNew)) {
                chcoParentNsqOld.getTChapterContentsCollection().remove(TChapterContents);
                chcoParentNsqOld = em.merge(chcoParentNsqOld);
            }
            if (chcoParentNsqNew != null && !chcoParentNsqNew.equals(chcoParentNsqOld)) {
                chcoParentNsqNew.getTChapterContentsCollection().add(TChapterContents);
                chcoParentNsqNew = em.merge(chcoParentNsqNew);
            }
            if (chcoChtlNsqOld != null && !chcoChtlNsqOld.equals(chcoChtlNsqNew)) {
                chcoChtlNsqOld.getTChapterContentsCollection().remove(TChapterContents);
                chcoChtlNsqOld = em.merge(chcoChtlNsqOld);
            }
            if (chcoChtlNsqNew != null && !chcoChtlNsqNew.equals(chcoChtlNsqOld)) {
                chcoChtlNsqNew.getTChapterContentsCollection().add(TChapterContents);
                chcoChtlNsqNew = em.merge(chcoChtlNsqNew);
            }
            if (chcoDocuNsqOld != null && !chcoDocuNsqOld.equals(chcoDocuNsqNew)) {
                chcoDocuNsqOld.getTChapterContentsCollection().remove(TChapterContents);
                chcoDocuNsqOld = em.merge(chcoDocuNsqOld);
            }
            if (chcoDocuNsqNew != null && !chcoDocuNsqNew.equals(chcoDocuNsqOld)) {
                chcoDocuNsqNew.getTChapterContentsCollection().add(TChapterContents);
                chcoDocuNsqNew = em.merge(chcoDocuNsqNew);
            }
            for (TChapterContents TChapterContentsCollectionOldTChapterContents : TChapterContentsCollectionOld) {
                if (!TChapterContentsCollectionNew.contains(TChapterContentsCollectionOldTChapterContents)) {
                    TChapterContentsCollectionOldTChapterContents.setChcoParentNsq(null);
                    TChapterContentsCollectionOldTChapterContents = em.merge(TChapterContentsCollectionOldTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollectionNewTChapterContents : TChapterContentsCollectionNew) {
                if (!TChapterContentsCollectionOld.contains(TChapterContentsCollectionNewTChapterContents)) {
                    TChapterContents oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents = TChapterContentsCollectionNewTChapterContents.getChcoParentNsq();
                    TChapterContentsCollectionNewTChapterContents.setChcoParentNsq(TChapterContents);
                    TChapterContentsCollectionNewTChapterContents = em.merge(TChapterContentsCollectionNewTChapterContents);
                    if (oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents != null && !oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents.equals(TChapterContents)) {
                        oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionNewTChapterContents);
                        oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents = em.merge(oldChcoParentNsqOfTChapterContentsCollectionNewTChapterContents);
                    }
                }
            }
            for (TChapterImages TChapterImagesCollectionOldTChapterImages : TChapterImagesCollectionOld) {
                if (!TChapterImagesCollectionNew.contains(TChapterImagesCollectionOldTChapterImages)) {
                    TChapterImagesCollectionOldTChapterImages.setChimChcoNsq(null);
                    TChapterImagesCollectionOldTChapterImages = em.merge(TChapterImagesCollectionOldTChapterImages);
                }
            }
            for (TChapterImages TChapterImagesCollectionNewTChapterImages : TChapterImagesCollectionNew) {
                if (!TChapterImagesCollectionOld.contains(TChapterImagesCollectionNewTChapterImages)) {
                    TChapterContents oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages = TChapterImagesCollectionNewTChapterImages.getChimChcoNsq();
                    TChapterImagesCollectionNewTChapterImages.setChimChcoNsq(TChapterContents);
                    TChapterImagesCollectionNewTChapterImages = em.merge(TChapterImagesCollectionNewTChapterImages);
                    if (oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages != null && !oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages.equals(TChapterContents)) {
                        oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages.getTChapterImagesCollection().remove(TChapterImagesCollectionNewTChapterImages);
                        oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages = em.merge(oldChimChcoNsqOfTChapterImagesCollectionNewTChapterImages);
                    }
                }
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslations : TKeywordTranslationsCollectionNew) {
                if (!TKeywordTranslationsCollectionOld.contains(TKeywordTranslationsCollectionNewTKeywordTranslations)) {
                    TChapterContents oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = TKeywordTranslationsCollectionNewTKeywordTranslations.getKytrChcoNsq();
                    TKeywordTranslationsCollectionNewTKeywordTranslations.setKytrChcoNsq(TChapterContents);
                    TKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(TKeywordTranslationsCollectionNewTKeywordTranslations);
                    if (oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations != null && !oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.equals(TChapterContents)) {
                        oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionNewTKeywordTranslations);
                        oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(oldKytrChcoNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TChapterContents.getChcoNsq();
                if (findTChapterContents(id) == null) {
                    throw new NonexistentEntityException("The tChapterContents with id " + id + " no longer exists.");
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
            TChapterContents TChapterContents;
            try {
                TChapterContents = em.getReference(TChapterContents.class, id);
                TChapterContents.getChcoNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TChapterContents with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOrphanCheck = TChapterContents.getTKeywordTranslationsCollection();
            for (TKeywordTranslations TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations : TKeywordTranslationsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TChapterContents (" + TChapterContents + ") cannot be destroyed since the TKeywordTranslations " + TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations + " in its TKeywordTranslationsCollection field has a non-nullable kytrChcoNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TBinaryContents chcoBinrNsqXmlContent = TChapterContents.getChcoBinrNsqXmlContent();
            if (chcoBinrNsqXmlContent != null) {
                chcoBinrNsqXmlContent.getTChapterContentsCollection().remove(TChapterContents);
                chcoBinrNsqXmlContent = em.merge(chcoBinrNsqXmlContent);
            }
            TBinaryContents chcoBinrNsqPdfFile = TChapterContents.getChcoBinrNsqPdfFile();
            if (chcoBinrNsqPdfFile != null) {
                chcoBinrNsqPdfFile.getTChapterContentsCollection().remove(TChapterContents);
                chcoBinrNsqPdfFile = em.merge(chcoBinrNsqPdfFile);
            }
            TChapterContents chcoParentNsq = TChapterContents.getChcoParentNsq();
            if (chcoParentNsq != null) {
                chcoParentNsq.getTChapterContentsCollection().remove(TChapterContents);
                chcoParentNsq = em.merge(chcoParentNsq);
            }
            TChapterTitles chcoChtlNsq = TChapterContents.getChcoChtlNsq();
            if (chcoChtlNsq != null) {
                chcoChtlNsq.getTChapterContentsCollection().remove(TChapterContents);
                chcoChtlNsq = em.merge(chcoChtlNsq);
            }
            TDocuments chcoDocuNsq = TChapterContents.getChcoDocuNsq();
            if (chcoDocuNsq != null) {
                chcoDocuNsq.getTChapterContentsCollection().remove(TChapterContents);
                chcoDocuNsq = em.merge(chcoDocuNsq);
            }
            Collection<TChapterContents> TChapterContentsCollection = TChapterContents.getTChapterContentsCollection();
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterContentsCollection) {
                TChapterContentsCollectionTChapterContents.setChcoParentNsq(null);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
            }
            Collection<TChapterImages> TChapterImagesCollection = TChapterContents.getTChapterImagesCollection();
            for (TChapterImages TChapterImagesCollectionTChapterImages : TChapterImagesCollection) {
                TChapterImagesCollectionTChapterImages.setChimChcoNsq(null);
                TChapterImagesCollectionTChapterImages = em.merge(TChapterImagesCollectionTChapterImages);
            }
            em.remove(TChapterContents);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TChapterContents> findTChapterContentsEntities() {
        return findTChapterContentsEntities(true, -1, -1);
    }

    public List<TChapterContents> findTChapterContentsEntities(int maxResults, int firstResult) {
        return findTChapterContentsEntities(false, maxResults, firstResult);
    }

    private List<TChapterContents> findTChapterContentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TChapterContents.class));
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

    public TChapterContents findTChapterContents(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TChapterContents.class, id);
        } finally {
            em.close();
        }
    }

    public int getTChapterContentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TChapterContents> rt = cq.from(TChapterContents.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
