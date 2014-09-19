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
import net.sopho.web.sopho.net.persistence.entities.TAnnexes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterImages;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;
import net.sopho.web.sopho.net.persistence.entities.TPrintableDocuments;

/**
 *
 * @author LU01007
 */
public class TBinaryContentsJpaController implements Serializable {

    public TBinaryContentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TBinaryContents TBinaryContents) throws PreexistingEntityException, Exception {
        if (TBinaryContents.getTAnnexesCollection() == null) {
            TBinaryContents.setTAnnexesCollection(new ArrayList<TAnnexes>());
        }
        if (TBinaryContents.getTChapterContentsCollection() == null) {
            TBinaryContents.setTChapterContentsCollection(new ArrayList<TChapterContents>());
        }
        if (TBinaryContents.getTChapterContentsCollection1() == null) {
            TBinaryContents.setTChapterContentsCollection1(new ArrayList<TChapterContents>());
        }
        if (TBinaryContents.getTChapterImagesCollection() == null) {
            TBinaryContents.setTChapterImagesCollection(new ArrayList<TChapterImages>());
        }
        if (TBinaryContents.getTZipArchivesCollection() == null) {
            TBinaryContents.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        if (TBinaryContents.getTPrintableDocumentsCollection() == null) {
            TBinaryContents.setTPrintableDocumentsCollection(new ArrayList<TPrintableDocuments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocuments binrDocuNsq = TBinaryContents.getBinrDocuNsq();
            if (binrDocuNsq != null) {
                binrDocuNsq = em.getReference(binrDocuNsq.getClass(), binrDocuNsq.getDocuNsq());
                TBinaryContents.setBinrDocuNsq(binrDocuNsq);
            }
            Collection<TAnnexes> attachedTAnnexesCollection = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionTAnnexesToAttach : TBinaryContents.getTAnnexesCollection()) {
                TAnnexesCollectionTAnnexesToAttach = em.getReference(TAnnexesCollectionTAnnexesToAttach.getClass(), TAnnexesCollectionTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollection.add(TAnnexesCollectionTAnnexesToAttach);
            }
            TBinaryContents.setTAnnexesCollection(attachedTAnnexesCollection);
            Collection<TChapterContents> attachedTChapterContentsCollection = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionTChapterContentsToAttach : TBinaryContents.getTChapterContentsCollection()) {
                TChapterContentsCollectionTChapterContentsToAttach = em.getReference(TChapterContentsCollectionTChapterContentsToAttach.getClass(), TChapterContentsCollectionTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection.add(TChapterContentsCollectionTChapterContentsToAttach);
            }
            TBinaryContents.setTChapterContentsCollection(attachedTChapterContentsCollection);
            Collection<TChapterContents> attachedTChapterContentsCollection1 = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollection1TChapterContentsToAttach : TBinaryContents.getTChapterContentsCollection1()) {
                TChapterContentsCollection1TChapterContentsToAttach = em.getReference(TChapterContentsCollection1TChapterContentsToAttach.getClass(), TChapterContentsCollection1TChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection1.add(TChapterContentsCollection1TChapterContentsToAttach);
            }
            TBinaryContents.setTChapterContentsCollection1(attachedTChapterContentsCollection1);
            Collection<TChapterImages> attachedTChapterImagesCollection = new ArrayList<TChapterImages>();
            for (TChapterImages TChapterImagesCollectionTChapterImagesToAttach : TBinaryContents.getTChapterImagesCollection()) {
                TChapterImagesCollectionTChapterImagesToAttach = em.getReference(TChapterImagesCollectionTChapterImagesToAttach.getClass(), TChapterImagesCollectionTChapterImagesToAttach.getChimNsq());
                attachedTChapterImagesCollection.add(TChapterImagesCollectionTChapterImagesToAttach);
            }
            TBinaryContents.setTChapterImagesCollection(attachedTChapterImagesCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TBinaryContents.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TBinaryContents.setTZipArchivesCollection(attachedTZipArchivesCollection);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollection = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocumentsToAttach : TBinaryContents.getTPrintableDocumentsCollection()) {
                TPrintableDocumentsCollectionTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollection.add(TPrintableDocumentsCollectionTPrintableDocumentsToAttach);
            }
            TBinaryContents.setTPrintableDocumentsCollection(attachedTPrintableDocumentsCollection);
            em.persist(TBinaryContents);
            if (binrDocuNsq != null) {
                binrDocuNsq.getTBinaryContentsCollection().add(TBinaryContents);
                binrDocuNsq = em.merge(binrDocuNsq);
            }
            for (TAnnexes TAnnexesCollectionTAnnexes : TBinaryContents.getTAnnexesCollection()) {
                TBinaryContents oldAnexBinrNsqOfTAnnexesCollectionTAnnexes = TAnnexesCollectionTAnnexes.getAnexBinrNsq();
                TAnnexesCollectionTAnnexes.setAnexBinrNsq(TBinaryContents);
                TAnnexesCollectionTAnnexes = em.merge(TAnnexesCollectionTAnnexes);
                if (oldAnexBinrNsqOfTAnnexesCollectionTAnnexes != null) {
                    oldAnexBinrNsqOfTAnnexesCollectionTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionTAnnexes);
                    oldAnexBinrNsqOfTAnnexesCollectionTAnnexes = em.merge(oldAnexBinrNsqOfTAnnexesCollectionTAnnexes);
                }
            }
            for (TChapterContents TChapterContentsCollectionTChapterContents : TBinaryContents.getTChapterContentsCollection()) {
                TBinaryContents oldChcoBinrNsqXmlContentOfTChapterContentsCollectionTChapterContents = TChapterContentsCollectionTChapterContents.getChcoBinrNsqXmlContent();
                TChapterContentsCollectionTChapterContents.setChcoBinrNsqXmlContent(TBinaryContents);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
                if (oldChcoBinrNsqXmlContentOfTChapterContentsCollectionTChapterContents != null) {
                    oldChcoBinrNsqXmlContentOfTChapterContentsCollectionTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionTChapterContents);
                    oldChcoBinrNsqXmlContentOfTChapterContentsCollectionTChapterContents = em.merge(oldChcoBinrNsqXmlContentOfTChapterContentsCollectionTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollection1TChapterContents : TBinaryContents.getTChapterContentsCollection1()) {
                TBinaryContents oldChcoBinrNsqPdfFileOfTChapterContentsCollection1TChapterContents = TChapterContentsCollection1TChapterContents.getChcoBinrNsqPdfFile();
                TChapterContentsCollection1TChapterContents.setChcoBinrNsqPdfFile(TBinaryContents);
                TChapterContentsCollection1TChapterContents = em.merge(TChapterContentsCollection1TChapterContents);
                if (oldChcoBinrNsqPdfFileOfTChapterContentsCollection1TChapterContents != null) {
                    oldChcoBinrNsqPdfFileOfTChapterContentsCollection1TChapterContents.getTChapterContentsCollection1().remove(TChapterContentsCollection1TChapterContents);
                    oldChcoBinrNsqPdfFileOfTChapterContentsCollection1TChapterContents = em.merge(oldChcoBinrNsqPdfFileOfTChapterContentsCollection1TChapterContents);
                }
            }
            for (TChapterImages TChapterImagesCollectionTChapterImages : TBinaryContents.getTChapterImagesCollection()) {
                TBinaryContents oldChimBinrNsqOfTChapterImagesCollectionTChapterImages = TChapterImagesCollectionTChapterImages.getChimBinrNsq();
                TChapterImagesCollectionTChapterImages.setChimBinrNsq(TBinaryContents);
                TChapterImagesCollectionTChapterImages = em.merge(TChapterImagesCollectionTChapterImages);
                if (oldChimBinrNsqOfTChapterImagesCollectionTChapterImages != null) {
                    oldChimBinrNsqOfTChapterImagesCollectionTChapterImages.getTChapterImagesCollection().remove(TChapterImagesCollectionTChapterImages);
                    oldChimBinrNsqOfTChapterImagesCollectionTChapterImages = em.merge(oldChimBinrNsqOfTChapterImagesCollectionTChapterImages);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TBinaryContents.getTZipArchivesCollection()) {
                TBinaryContents oldZipaBinrNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaBinrNsq();
                TZipArchivesCollectionTZipArchives.setZipaBinrNsq(TBinaryContents);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaBinrNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaBinrNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaBinrNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaBinrNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TBinaryContents.getTPrintableDocumentsCollection()) {
                TBinaryContents oldPdocBinrNsqOfTPrintableDocumentsCollectionTPrintableDocuments = TPrintableDocumentsCollectionTPrintableDocuments.getPdocBinrNsq();
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocBinrNsq(TBinaryContents);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
                if (oldPdocBinrNsqOfTPrintableDocumentsCollectionTPrintableDocuments != null) {
                    oldPdocBinrNsqOfTPrintableDocumentsCollectionTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionTPrintableDocuments);
                    oldPdocBinrNsqOfTPrintableDocumentsCollectionTPrintableDocuments = em.merge(oldPdocBinrNsqOfTPrintableDocumentsCollectionTPrintableDocuments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTBinaryContents(TBinaryContents.getBinrNsq()) != null) {
                throw new PreexistingEntityException("TBinaryContents " + TBinaryContents + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TBinaryContents TBinaryContents) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBinaryContents persistentTBinaryContents = em.find(TBinaryContents.class, TBinaryContents.getBinrNsq());
            TDocuments binrDocuNsqOld = persistentTBinaryContents.getBinrDocuNsq();
            TDocuments binrDocuNsqNew = TBinaryContents.getBinrDocuNsq();
            Collection<TAnnexes> TAnnexesCollectionOld = persistentTBinaryContents.getTAnnexesCollection();
            Collection<TAnnexes> TAnnexesCollectionNew = TBinaryContents.getTAnnexesCollection();
            Collection<TChapterContents> TChapterContentsCollectionOld = persistentTBinaryContents.getTChapterContentsCollection();
            Collection<TChapterContents> TChapterContentsCollectionNew = TBinaryContents.getTChapterContentsCollection();
            Collection<TChapterContents> TChapterContentsCollection1Old = persistentTBinaryContents.getTChapterContentsCollection1();
            Collection<TChapterContents> TChapterContentsCollection1New = TBinaryContents.getTChapterContentsCollection1();
            Collection<TChapterImages> TChapterImagesCollectionOld = persistentTBinaryContents.getTChapterImagesCollection();
            Collection<TChapterImages> TChapterImagesCollectionNew = TBinaryContents.getTChapterImagesCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTBinaryContents.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TBinaryContents.getTZipArchivesCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionOld = persistentTBinaryContents.getTPrintableDocumentsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionNew = TBinaryContents.getTPrintableDocumentsCollection();
            List<String> illegalOrphanMessages = null;
            for (TChapterImages TChapterImagesCollectionOldTChapterImages : TChapterImagesCollectionOld) {
                if (!TChapterImagesCollectionNew.contains(TChapterImagesCollectionOldTChapterImages)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TChapterImages " + TChapterImagesCollectionOldTChapterImages + " since its chimBinrNsq field is not nullable.");
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TZipArchives " + TZipArchivesCollectionOldTZipArchives + " since its zipaBinrNsq field is not nullable.");
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionOldTPrintableDocuments : TPrintableDocumentsCollectionOld) {
                if (!TPrintableDocumentsCollectionNew.contains(TPrintableDocumentsCollectionOldTPrintableDocuments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TPrintableDocuments " + TPrintableDocumentsCollectionOldTPrintableDocuments + " since its pdocBinrNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (binrDocuNsqNew != null) {
                binrDocuNsqNew = em.getReference(binrDocuNsqNew.getClass(), binrDocuNsqNew.getDocuNsq());
                TBinaryContents.setBinrDocuNsq(binrDocuNsqNew);
            }
            Collection<TAnnexes> attachedTAnnexesCollectionNew = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionNewTAnnexesToAttach : TAnnexesCollectionNew) {
                TAnnexesCollectionNewTAnnexesToAttach = em.getReference(TAnnexesCollectionNewTAnnexesToAttach.getClass(), TAnnexesCollectionNewTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollectionNew.add(TAnnexesCollectionNewTAnnexesToAttach);
            }
            TAnnexesCollectionNew = attachedTAnnexesCollectionNew;
            TBinaryContents.setTAnnexesCollection(TAnnexesCollectionNew);
            Collection<TChapterContents> attachedTChapterContentsCollectionNew = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionNewTChapterContentsToAttach : TChapterContentsCollectionNew) {
                TChapterContentsCollectionNewTChapterContentsToAttach = em.getReference(TChapterContentsCollectionNewTChapterContentsToAttach.getClass(), TChapterContentsCollectionNewTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollectionNew.add(TChapterContentsCollectionNewTChapterContentsToAttach);
            }
            TChapterContentsCollectionNew = attachedTChapterContentsCollectionNew;
            TBinaryContents.setTChapterContentsCollection(TChapterContentsCollectionNew);
            Collection<TChapterContents> attachedTChapterContentsCollection1New = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollection1NewTChapterContentsToAttach : TChapterContentsCollection1New) {
                TChapterContentsCollection1NewTChapterContentsToAttach = em.getReference(TChapterContentsCollection1NewTChapterContentsToAttach.getClass(), TChapterContentsCollection1NewTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection1New.add(TChapterContentsCollection1NewTChapterContentsToAttach);
            }
            TChapterContentsCollection1New = attachedTChapterContentsCollection1New;
            TBinaryContents.setTChapterContentsCollection1(TChapterContentsCollection1New);
            Collection<TChapterImages> attachedTChapterImagesCollectionNew = new ArrayList<TChapterImages>();
            for (TChapterImages TChapterImagesCollectionNewTChapterImagesToAttach : TChapterImagesCollectionNew) {
                TChapterImagesCollectionNewTChapterImagesToAttach = em.getReference(TChapterImagesCollectionNewTChapterImagesToAttach.getClass(), TChapterImagesCollectionNewTChapterImagesToAttach.getChimNsq());
                attachedTChapterImagesCollectionNew.add(TChapterImagesCollectionNewTChapterImagesToAttach);
            }
            TChapterImagesCollectionNew = attachedTChapterImagesCollectionNew;
            TBinaryContents.setTChapterImagesCollection(TChapterImagesCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TBinaryContents.setTZipArchivesCollection(TZipArchivesCollectionNew);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollectionNew = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach : TPrintableDocumentsCollectionNew) {
                TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollectionNew.add(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach);
            }
            TPrintableDocumentsCollectionNew = attachedTPrintableDocumentsCollectionNew;
            TBinaryContents.setTPrintableDocumentsCollection(TPrintableDocumentsCollectionNew);
            TBinaryContents = em.merge(TBinaryContents);
            if (binrDocuNsqOld != null && !binrDocuNsqOld.equals(binrDocuNsqNew)) {
                binrDocuNsqOld.getTBinaryContentsCollection().remove(TBinaryContents);
                binrDocuNsqOld = em.merge(binrDocuNsqOld);
            }
            if (binrDocuNsqNew != null && !binrDocuNsqNew.equals(binrDocuNsqOld)) {
                binrDocuNsqNew.getTBinaryContentsCollection().add(TBinaryContents);
                binrDocuNsqNew = em.merge(binrDocuNsqNew);
            }
            for (TAnnexes TAnnexesCollectionOldTAnnexes : TAnnexesCollectionOld) {
                if (!TAnnexesCollectionNew.contains(TAnnexesCollectionOldTAnnexes)) {
                    TAnnexesCollectionOldTAnnexes.setAnexBinrNsq(null);
                    TAnnexesCollectionOldTAnnexes = em.merge(TAnnexesCollectionOldTAnnexes);
                }
            }
            for (TAnnexes TAnnexesCollectionNewTAnnexes : TAnnexesCollectionNew) {
                if (!TAnnexesCollectionOld.contains(TAnnexesCollectionNewTAnnexes)) {
                    TBinaryContents oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes = TAnnexesCollectionNewTAnnexes.getAnexBinrNsq();
                    TAnnexesCollectionNewTAnnexes.setAnexBinrNsq(TBinaryContents);
                    TAnnexesCollectionNewTAnnexes = em.merge(TAnnexesCollectionNewTAnnexes);
                    if (oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes != null && !oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes.equals(TBinaryContents)) {
                        oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionNewTAnnexes);
                        oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes = em.merge(oldAnexBinrNsqOfTAnnexesCollectionNewTAnnexes);
                    }
                }
            }
            for (TChapterContents TChapterContentsCollectionOldTChapterContents : TChapterContentsCollectionOld) {
                if (!TChapterContentsCollectionNew.contains(TChapterContentsCollectionOldTChapterContents)) {
                    TChapterContentsCollectionOldTChapterContents.setChcoBinrNsqXmlContent(null);
                    TChapterContentsCollectionOldTChapterContents = em.merge(TChapterContentsCollectionOldTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollectionNewTChapterContents : TChapterContentsCollectionNew) {
                if (!TChapterContentsCollectionOld.contains(TChapterContentsCollectionNewTChapterContents)) {
                    TBinaryContents oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents = TChapterContentsCollectionNewTChapterContents.getChcoBinrNsqXmlContent();
                    TChapterContentsCollectionNewTChapterContents.setChcoBinrNsqXmlContent(TBinaryContents);
                    TChapterContentsCollectionNewTChapterContents = em.merge(TChapterContentsCollectionNewTChapterContents);
                    if (oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents != null && !oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents.equals(TBinaryContents)) {
                        oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionNewTChapterContents);
                        oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents = em.merge(oldChcoBinrNsqXmlContentOfTChapterContentsCollectionNewTChapterContents);
                    }
                }
            }
            for (TChapterContents TChapterContentsCollection1OldTChapterContents : TChapterContentsCollection1Old) {
                if (!TChapterContentsCollection1New.contains(TChapterContentsCollection1OldTChapterContents)) {
                    TChapterContentsCollection1OldTChapterContents.setChcoBinrNsqPdfFile(null);
                    TChapterContentsCollection1OldTChapterContents = em.merge(TChapterContentsCollection1OldTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollection1NewTChapterContents : TChapterContentsCollection1New) {
                if (!TChapterContentsCollection1Old.contains(TChapterContentsCollection1NewTChapterContents)) {
                    TBinaryContents oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents = TChapterContentsCollection1NewTChapterContents.getChcoBinrNsqPdfFile();
                    TChapterContentsCollection1NewTChapterContents.setChcoBinrNsqPdfFile(TBinaryContents);
                    TChapterContentsCollection1NewTChapterContents = em.merge(TChapterContentsCollection1NewTChapterContents);
                    if (oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents != null && !oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents.equals(TBinaryContents)) {
                        oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents.getTChapterContentsCollection1().remove(TChapterContentsCollection1NewTChapterContents);
                        oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents = em.merge(oldChcoBinrNsqPdfFileOfTChapterContentsCollection1NewTChapterContents);
                    }
                }
            }
            for (TChapterImages TChapterImagesCollectionNewTChapterImages : TChapterImagesCollectionNew) {
                if (!TChapterImagesCollectionOld.contains(TChapterImagesCollectionNewTChapterImages)) {
                    TBinaryContents oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages = TChapterImagesCollectionNewTChapterImages.getChimBinrNsq();
                    TChapterImagesCollectionNewTChapterImages.setChimBinrNsq(TBinaryContents);
                    TChapterImagesCollectionNewTChapterImages = em.merge(TChapterImagesCollectionNewTChapterImages);
                    if (oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages != null && !oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages.equals(TBinaryContents)) {
                        oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages.getTChapterImagesCollection().remove(TChapterImagesCollectionNewTChapterImages);
                        oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages = em.merge(oldChimBinrNsqOfTChapterImagesCollectionNewTChapterImages);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TBinaryContents oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaBinrNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaBinrNsq(TBinaryContents);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives.equals(TBinaryContents)) {
                        oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaBinrNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocuments : TPrintableDocumentsCollectionNew) {
                if (!TPrintableDocumentsCollectionOld.contains(TPrintableDocumentsCollectionNewTPrintableDocuments)) {
                    TBinaryContents oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = TPrintableDocumentsCollectionNewTPrintableDocuments.getPdocBinrNsq();
                    TPrintableDocumentsCollectionNewTPrintableDocuments.setPdocBinrNsq(TBinaryContents);
                    TPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(TPrintableDocumentsCollectionNewTPrintableDocuments);
                    if (oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments != null && !oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.equals(TBinaryContents)) {
                        oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionNewTPrintableDocuments);
                        oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(oldPdocBinrNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TBinaryContents.getBinrNsq();
                if (findTBinaryContents(id) == null) {
                    throw new NonexistentEntityException("The tBinaryContents with id " + id + " no longer exists.");
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
            TBinaryContents TBinaryContents;
            try {
                TBinaryContents = em.getReference(TBinaryContents.class, id);
                TBinaryContents.getBinrNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TBinaryContents with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TChapterImages> TChapterImagesCollectionOrphanCheck = TBinaryContents.getTChapterImagesCollection();
            for (TChapterImages TChapterImagesCollectionOrphanCheckTChapterImages : TChapterImagesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TBinaryContents (" + TBinaryContents + ") cannot be destroyed since the TChapterImages " + TChapterImagesCollectionOrphanCheckTChapterImages + " in its TChapterImagesCollection field has a non-nullable chimBinrNsq field.");
            }
            Collection<TZipArchives> TZipArchivesCollectionOrphanCheck = TBinaryContents.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionOrphanCheckTZipArchives : TZipArchivesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TBinaryContents (" + TBinaryContents + ") cannot be destroyed since the TZipArchives " + TZipArchivesCollectionOrphanCheckTZipArchives + " in its TZipArchivesCollection field has a non-nullable zipaBinrNsq field.");
            }
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionOrphanCheck = TBinaryContents.getTPrintableDocumentsCollection();
            for (TPrintableDocuments TPrintableDocumentsCollectionOrphanCheckTPrintableDocuments : TPrintableDocumentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TBinaryContents (" + TBinaryContents + ") cannot be destroyed since the TPrintableDocuments " + TPrintableDocumentsCollectionOrphanCheckTPrintableDocuments + " in its TPrintableDocumentsCollection field has a non-nullable pdocBinrNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TDocuments binrDocuNsq = TBinaryContents.getBinrDocuNsq();
            if (binrDocuNsq != null) {
                binrDocuNsq.getTBinaryContentsCollection().remove(TBinaryContents);
                binrDocuNsq = em.merge(binrDocuNsq);
            }
            Collection<TAnnexes> TAnnexesCollection = TBinaryContents.getTAnnexesCollection();
            for (TAnnexes TAnnexesCollectionTAnnexes : TAnnexesCollection) {
                TAnnexesCollectionTAnnexes.setAnexBinrNsq(null);
                TAnnexesCollectionTAnnexes = em.merge(TAnnexesCollectionTAnnexes);
            }
            Collection<TChapterContents> TChapterContentsCollection = TBinaryContents.getTChapterContentsCollection();
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterContentsCollection) {
                TChapterContentsCollectionTChapterContents.setChcoBinrNsqXmlContent(null);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
            }
            Collection<TChapterContents> TChapterContentsCollection1 = TBinaryContents.getTChapterContentsCollection1();
            for (TChapterContents TChapterContentsCollection1TChapterContents : TChapterContentsCollection1) {
                TChapterContentsCollection1TChapterContents.setChcoBinrNsqPdfFile(null);
                TChapterContentsCollection1TChapterContents = em.merge(TChapterContentsCollection1TChapterContents);
            }
            em.remove(TBinaryContents);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TBinaryContents> findTBinaryContentsEntities() {
        return findTBinaryContentsEntities(true, -1, -1);
    }

    public List<TBinaryContents> findTBinaryContentsEntities(int maxResults, int firstResult) {
        return findTBinaryContentsEntities(false, maxResults, firstResult);
    }

    private List<TBinaryContents> findTBinaryContentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TBinaryContents.class));
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

    public TBinaryContents findTBinaryContents(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TBinaryContents.class, id);
        } finally {
            em.close();
        }
    }

    public int getTBinaryContentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TBinaryContents> rt = cq.from(TBinaryContents.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
