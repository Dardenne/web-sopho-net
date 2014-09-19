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
import net.sopho.web.sopho.net.persistence.entities.TChapterDescriptionsTrans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import net.sopho.web.sopho.net.persistence.entities.TExternalLinks;
import net.sopho.web.sopho.net.persistence.entities.TVersionComments;
import net.sopho.web.sopho.net.persistence.entities.TKeywordTranslations;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;
import net.sopho.web.sopho.net.persistence.entities.TLabelBundles;
import net.sopho.web.sopho.net.persistence.entities.TDocumentlangsAnnexlangs;
import net.sopho.web.sopho.net.persistence.entities.TPrintableDocuments;

/**
 *
 * @author LU01007
 */
public class TDocumentLanguagesJpaController implements Serializable {

    public TDocumentLanguagesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDocumentLanguages TDocumentLanguages) throws PreexistingEntityException, Exception {
        if (TDocumentLanguages.getTChapterDescriptionsTransCollection() == null) {
            TDocumentLanguages.setTChapterDescriptionsTransCollection(new ArrayList<TChapterDescriptionsTrans>());
        }
        if (TDocumentLanguages.getTDocumentsCollection() == null) {
            TDocumentLanguages.setTDocumentsCollection(new ArrayList<TDocuments>());
        }
        if (TDocumentLanguages.getTExternalLinksCollection() == null) {
            TDocumentLanguages.setTExternalLinksCollection(new ArrayList<TExternalLinks>());
        }
        if (TDocumentLanguages.getTVersionCommentsCollection() == null) {
            TDocumentLanguages.setTVersionCommentsCollection(new ArrayList<TVersionComments>());
        }
        if (TDocumentLanguages.getTKeywordTranslationsCollection() == null) {
            TDocumentLanguages.setTKeywordTranslationsCollection(new ArrayList<TKeywordTranslations>());
        }
        if (TDocumentLanguages.getTZipArchivesCollection() == null) {
            TDocumentLanguages.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        if (TDocumentLanguages.getTLabelBundlesCollection() == null) {
            TDocumentLanguages.setTLabelBundlesCollection(new ArrayList<TLabelBundles>());
        }
        if (TDocumentLanguages.getTDocumentlangsAnnexlangsCollection() == null) {
            TDocumentLanguages.setTDocumentlangsAnnexlangsCollection(new ArrayList<TDocumentlangsAnnexlangs>());
        }
        if (TDocumentLanguages.getTPrintableDocumentsCollection() == null) {
            TDocumentLanguages.setTPrintableDocumentsCollection(new ArrayList<TPrintableDocuments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TChapterDescriptionsTrans> attachedTChapterDescriptionsTransCollection = new ArrayList<TChapterDescriptionsTrans>();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach : TDocumentLanguages.getTChapterDescriptionsTransCollection()) {
                TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach = em.getReference(TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach.getClass(), TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach.getChdtNsq());
                attachedTChapterDescriptionsTransCollection.add(TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach);
            }
            TDocumentLanguages.setTChapterDescriptionsTransCollection(attachedTChapterDescriptionsTransCollection);
            Collection<TDocuments> attachedTDocumentsCollection = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionTDocumentsToAttach : TDocumentLanguages.getTDocumentsCollection()) {
                TDocumentsCollectionTDocumentsToAttach = em.getReference(TDocumentsCollectionTDocumentsToAttach.getClass(), TDocumentsCollectionTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollection.add(TDocumentsCollectionTDocumentsToAttach);
            }
            TDocumentLanguages.setTDocumentsCollection(attachedTDocumentsCollection);
            Collection<TExternalLinks> attachedTExternalLinksCollection = new ArrayList<TExternalLinks>();
            for (TExternalLinks TExternalLinksCollectionTExternalLinksToAttach : TDocumentLanguages.getTExternalLinksCollection()) {
                TExternalLinksCollectionTExternalLinksToAttach = em.getReference(TExternalLinksCollectionTExternalLinksToAttach.getClass(), TExternalLinksCollectionTExternalLinksToAttach.getElnkNsq());
                attachedTExternalLinksCollection.add(TExternalLinksCollectionTExternalLinksToAttach);
            }
            TDocumentLanguages.setTExternalLinksCollection(attachedTExternalLinksCollection);
            Collection<TVersionComments> attachedTVersionCommentsCollection = new ArrayList<TVersionComments>();
            for (TVersionComments TVersionCommentsCollectionTVersionCommentsToAttach : TDocumentLanguages.getTVersionCommentsCollection()) {
                TVersionCommentsCollectionTVersionCommentsToAttach = em.getReference(TVersionCommentsCollectionTVersionCommentsToAttach.getClass(), TVersionCommentsCollectionTVersionCommentsToAttach.getVercNsq());
                attachedTVersionCommentsCollection.add(TVersionCommentsCollectionTVersionCommentsToAttach);
            }
            TDocumentLanguages.setTVersionCommentsCollection(attachedTVersionCommentsCollection);
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollection = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslationsToAttach : TDocumentLanguages.getTKeywordTranslationsCollection()) {
                TKeywordTranslationsCollectionTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollection.add(TKeywordTranslationsCollectionTKeywordTranslationsToAttach);
            }
            TDocumentLanguages.setTKeywordTranslationsCollection(attachedTKeywordTranslationsCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TDocumentLanguages.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TDocumentLanguages.setTZipArchivesCollection(attachedTZipArchivesCollection);
            Collection<TLabelBundles> attachedTLabelBundlesCollection = new ArrayList<TLabelBundles>();
            for (TLabelBundles TLabelBundlesCollectionTLabelBundlesToAttach : TDocumentLanguages.getTLabelBundlesCollection()) {
                TLabelBundlesCollectionTLabelBundlesToAttach = em.getReference(TLabelBundlesCollectionTLabelBundlesToAttach.getClass(), TLabelBundlesCollectionTLabelBundlesToAttach.getLabuNsq());
                attachedTLabelBundlesCollection.add(TLabelBundlesCollectionTLabelBundlesToAttach);
            }
            TDocumentLanguages.setTLabelBundlesCollection(attachedTLabelBundlesCollection);
            Collection<TDocumentlangsAnnexlangs> attachedTDocumentlangsAnnexlangsCollection = new ArrayList<TDocumentlangsAnnexlangs>();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach : TDocumentLanguages.getTDocumentlangsAnnexlangsCollection()) {
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach = em.getReference(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach.getClass(), TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach.getDoanNsq());
                attachedTDocumentlangsAnnexlangsCollection.add(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangsToAttach);
            }
            TDocumentLanguages.setTDocumentlangsAnnexlangsCollection(attachedTDocumentlangsAnnexlangsCollection);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollection = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocumentsToAttach : TDocumentLanguages.getTPrintableDocumentsCollection()) {
                TPrintableDocumentsCollectionTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollection.add(TPrintableDocumentsCollectionTPrintableDocumentsToAttach);
            }
            TDocumentLanguages.setTPrintableDocumentsCollection(attachedTPrintableDocumentsCollection);
            em.persist(TDocumentLanguages);
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionTChapterDescriptionsTrans : TDocumentLanguages.getTChapterDescriptionsTransCollection()) {
                TDocumentLanguages oldChdtDlanNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans = TChapterDescriptionsTransCollectionTChapterDescriptionsTrans.getChdtDlanNsq();
                TChapterDescriptionsTransCollectionTChapterDescriptionsTrans.setChdtDlanNsq(TDocumentLanguages);
                TChapterDescriptionsTransCollectionTChapterDescriptionsTrans = em.merge(TChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                if (oldChdtDlanNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans != null) {
                    oldChdtDlanNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                    oldChdtDlanNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans = em.merge(oldChdtDlanNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                }
            }
            for (TDocuments TDocumentsCollectionTDocuments : TDocumentLanguages.getTDocumentsCollection()) {
                TDocumentLanguages oldDocuDlanNsqOfTDocumentsCollectionTDocuments = TDocumentsCollectionTDocuments.getDocuDlanNsq();
                TDocumentsCollectionTDocuments.setDocuDlanNsq(TDocumentLanguages);
                TDocumentsCollectionTDocuments = em.merge(TDocumentsCollectionTDocuments);
                if (oldDocuDlanNsqOfTDocumentsCollectionTDocuments != null) {
                    oldDocuDlanNsqOfTDocumentsCollectionTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionTDocuments);
                    oldDocuDlanNsqOfTDocumentsCollectionTDocuments = em.merge(oldDocuDlanNsqOfTDocumentsCollectionTDocuments);
                }
            }
            for (TExternalLinks TExternalLinksCollectionTExternalLinks : TDocumentLanguages.getTExternalLinksCollection()) {
                TDocumentLanguages oldElnkDlanNsqOfTExternalLinksCollectionTExternalLinks = TExternalLinksCollectionTExternalLinks.getElnkDlanNsq();
                TExternalLinksCollectionTExternalLinks.setElnkDlanNsq(TDocumentLanguages);
                TExternalLinksCollectionTExternalLinks = em.merge(TExternalLinksCollectionTExternalLinks);
                if (oldElnkDlanNsqOfTExternalLinksCollectionTExternalLinks != null) {
                    oldElnkDlanNsqOfTExternalLinksCollectionTExternalLinks.getTExternalLinksCollection().remove(TExternalLinksCollectionTExternalLinks);
                    oldElnkDlanNsqOfTExternalLinksCollectionTExternalLinks = em.merge(oldElnkDlanNsqOfTExternalLinksCollectionTExternalLinks);
                }
            }
            for (TVersionComments TVersionCommentsCollectionTVersionComments : TDocumentLanguages.getTVersionCommentsCollection()) {
                TDocumentLanguages oldVercDlanNsqOfTVersionCommentsCollectionTVersionComments = TVersionCommentsCollectionTVersionComments.getVercDlanNsq();
                TVersionCommentsCollectionTVersionComments.setVercDlanNsq(TDocumentLanguages);
                TVersionCommentsCollectionTVersionComments = em.merge(TVersionCommentsCollectionTVersionComments);
                if (oldVercDlanNsqOfTVersionCommentsCollectionTVersionComments != null) {
                    oldVercDlanNsqOfTVersionCommentsCollectionTVersionComments.getTVersionCommentsCollection().remove(TVersionCommentsCollectionTVersionComments);
                    oldVercDlanNsqOfTVersionCommentsCollectionTVersionComments = em.merge(oldVercDlanNsqOfTVersionCommentsCollectionTVersionComments);
                }
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionTKeywordTranslations : TDocumentLanguages.getTKeywordTranslationsCollection()) {
                TDocumentLanguages oldKytrDlanNsqOfTKeywordTranslationsCollectionTKeywordTranslations = TKeywordTranslationsCollectionTKeywordTranslations.getKytrDlanNsq();
                TKeywordTranslationsCollectionTKeywordTranslations.setKytrDlanNsq(TDocumentLanguages);
                TKeywordTranslationsCollectionTKeywordTranslations = em.merge(TKeywordTranslationsCollectionTKeywordTranslations);
                if (oldKytrDlanNsqOfTKeywordTranslationsCollectionTKeywordTranslations != null) {
                    oldKytrDlanNsqOfTKeywordTranslationsCollectionTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionTKeywordTranslations);
                    oldKytrDlanNsqOfTKeywordTranslationsCollectionTKeywordTranslations = em.merge(oldKytrDlanNsqOfTKeywordTranslationsCollectionTKeywordTranslations);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TDocumentLanguages.getTZipArchivesCollection()) {
                TDocumentLanguages oldZipaDlanNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaDlanNsq();
                TZipArchivesCollectionTZipArchives.setZipaDlanNsq(TDocumentLanguages);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaDlanNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaDlanNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaDlanNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaDlanNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            for (TLabelBundles TLabelBundlesCollectionTLabelBundles : TDocumentLanguages.getTLabelBundlesCollection()) {
                TDocumentLanguages oldLabuDlanNsqOfTLabelBundlesCollectionTLabelBundles = TLabelBundlesCollectionTLabelBundles.getLabuDlanNsq();
                TLabelBundlesCollectionTLabelBundles.setLabuDlanNsq(TDocumentLanguages);
                TLabelBundlesCollectionTLabelBundles = em.merge(TLabelBundlesCollectionTLabelBundles);
                if (oldLabuDlanNsqOfTLabelBundlesCollectionTLabelBundles != null) {
                    oldLabuDlanNsqOfTLabelBundlesCollectionTLabelBundles.getTLabelBundlesCollection().remove(TLabelBundlesCollectionTLabelBundles);
                    oldLabuDlanNsqOfTLabelBundlesCollectionTLabelBundles = em.merge(oldLabuDlanNsqOfTLabelBundlesCollectionTLabelBundles);
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs : TDocumentLanguages.getTDocumentlangsAnnexlangsCollection()) {
                TDocumentLanguages oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.getDoanDlanNsq();
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.setDoanDlanNsq(TDocumentLanguages);
                TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = em.merge(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                if (oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs != null) {
                    oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                    oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs = em.merge(oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionTDocumentlangsAnnexlangs);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TDocumentLanguages.getTPrintableDocumentsCollection()) {
                TDocumentLanguages oldPdocDlanNsqOfTPrintableDocumentsCollectionTPrintableDocuments = TPrintableDocumentsCollectionTPrintableDocuments.getPdocDlanNsq();
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocDlanNsq(TDocumentLanguages);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
                if (oldPdocDlanNsqOfTPrintableDocumentsCollectionTPrintableDocuments != null) {
                    oldPdocDlanNsqOfTPrintableDocumentsCollectionTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionTPrintableDocuments);
                    oldPdocDlanNsqOfTPrintableDocumentsCollectionTPrintableDocuments = em.merge(oldPdocDlanNsqOfTPrintableDocumentsCollectionTPrintableDocuments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDocumentLanguages(TDocumentLanguages.getDlanNsq()) != null) {
                throw new PreexistingEntityException("TDocumentLanguages " + TDocumentLanguages + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDocumentLanguages TDocumentLanguages) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDocumentLanguages persistentTDocumentLanguages = em.find(TDocumentLanguages.class, TDocumentLanguages.getDlanNsq());
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionOld = persistentTDocumentLanguages.getTChapterDescriptionsTransCollection();
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionNew = TDocumentLanguages.getTChapterDescriptionsTransCollection();
            Collection<TDocuments> TDocumentsCollectionOld = persistentTDocumentLanguages.getTDocumentsCollection();
            Collection<TDocuments> TDocumentsCollectionNew = TDocumentLanguages.getTDocumentsCollection();
            Collection<TExternalLinks> TExternalLinksCollectionOld = persistentTDocumentLanguages.getTExternalLinksCollection();
            Collection<TExternalLinks> TExternalLinksCollectionNew = TDocumentLanguages.getTExternalLinksCollection();
            Collection<TVersionComments> TVersionCommentsCollectionOld = persistentTDocumentLanguages.getTVersionCommentsCollection();
            Collection<TVersionComments> TVersionCommentsCollectionNew = TDocumentLanguages.getTVersionCommentsCollection();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOld = persistentTDocumentLanguages.getTKeywordTranslationsCollection();
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionNew = TDocumentLanguages.getTKeywordTranslationsCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTDocumentLanguages.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TDocumentLanguages.getTZipArchivesCollection();
            Collection<TLabelBundles> TLabelBundlesCollectionOld = persistentTDocumentLanguages.getTLabelBundlesCollection();
            Collection<TLabelBundles> TLabelBundlesCollectionNew = TDocumentLanguages.getTLabelBundlesCollection();
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionOld = persistentTDocumentLanguages.getTDocumentlangsAnnexlangsCollection();
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionNew = TDocumentLanguages.getTDocumentlangsAnnexlangsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionOld = persistentTDocumentLanguages.getTPrintableDocumentsCollection();
            Collection<TPrintableDocuments> TPrintableDocumentsCollectionNew = TDocumentLanguages.getTPrintableDocumentsCollection();
            List<String> illegalOrphanMessages = null;
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionOld) {
                if (!TChapterDescriptionsTransCollectionNew.contains(TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TChapterDescriptionsTrans " + TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans + " since its chdtDlanNsq field is not nullable.");
                }
            }
            for (TDocuments TDocumentsCollectionOldTDocuments : TDocumentsCollectionOld) {
                if (!TDocumentsCollectionNew.contains(TDocumentsCollectionOldTDocuments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TDocuments " + TDocumentsCollectionOldTDocuments + " since its docuDlanNsq field is not nullable.");
                }
            }
            for (TExternalLinks TExternalLinksCollectionOldTExternalLinks : TExternalLinksCollectionOld) {
                if (!TExternalLinksCollectionNew.contains(TExternalLinksCollectionOldTExternalLinks)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TExternalLinks " + TExternalLinksCollectionOldTExternalLinks + " since its elnkDlanNsq field is not nullable.");
                }
            }
            for (TVersionComments TVersionCommentsCollectionOldTVersionComments : TVersionCommentsCollectionOld) {
                if (!TVersionCommentsCollectionNew.contains(TVersionCommentsCollectionOldTVersionComments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TVersionComments " + TVersionCommentsCollectionOldTVersionComments + " since its vercDlanNsq field is not nullable.");
                }
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionOldTKeywordTranslations : TKeywordTranslationsCollectionOld) {
                if (!TKeywordTranslationsCollectionNew.contains(TKeywordTranslationsCollectionOldTKeywordTranslations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TKeywordTranslations " + TKeywordTranslationsCollectionOldTKeywordTranslations + " since its kytrDlanNsq field is not nullable.");
                }
            }
            for (TLabelBundles TLabelBundlesCollectionOldTLabelBundles : TLabelBundlesCollectionOld) {
                if (!TLabelBundlesCollectionNew.contains(TLabelBundlesCollectionOldTLabelBundles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TLabelBundles " + TLabelBundlesCollectionOldTLabelBundles + " since its labuDlanNsq field is not nullable.");
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionOld) {
                if (!TDocumentlangsAnnexlangsCollectionNew.contains(TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TDocumentlangsAnnexlangs " + TDocumentlangsAnnexlangsCollectionOldTDocumentlangsAnnexlangs + " since its doanDlanNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TChapterDescriptionsTrans> attachedTChapterDescriptionsTransCollectionNew = new ArrayList<TChapterDescriptionsTrans>();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach : TChapterDescriptionsTransCollectionNew) {
                TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach = em.getReference(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach.getClass(), TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach.getChdtNsq());
                attachedTChapterDescriptionsTransCollectionNew.add(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach);
            }
            TChapterDescriptionsTransCollectionNew = attachedTChapterDescriptionsTransCollectionNew;
            TDocumentLanguages.setTChapterDescriptionsTransCollection(TChapterDescriptionsTransCollectionNew);
            Collection<TDocuments> attachedTDocumentsCollectionNew = new ArrayList<TDocuments>();
            for (TDocuments TDocumentsCollectionNewTDocumentsToAttach : TDocumentsCollectionNew) {
                TDocumentsCollectionNewTDocumentsToAttach = em.getReference(TDocumentsCollectionNewTDocumentsToAttach.getClass(), TDocumentsCollectionNewTDocumentsToAttach.getDocuNsq());
                attachedTDocumentsCollectionNew.add(TDocumentsCollectionNewTDocumentsToAttach);
            }
            TDocumentsCollectionNew = attachedTDocumentsCollectionNew;
            TDocumentLanguages.setTDocumentsCollection(TDocumentsCollectionNew);
            Collection<TExternalLinks> attachedTExternalLinksCollectionNew = new ArrayList<TExternalLinks>();
            for (TExternalLinks TExternalLinksCollectionNewTExternalLinksToAttach : TExternalLinksCollectionNew) {
                TExternalLinksCollectionNewTExternalLinksToAttach = em.getReference(TExternalLinksCollectionNewTExternalLinksToAttach.getClass(), TExternalLinksCollectionNewTExternalLinksToAttach.getElnkNsq());
                attachedTExternalLinksCollectionNew.add(TExternalLinksCollectionNewTExternalLinksToAttach);
            }
            TExternalLinksCollectionNew = attachedTExternalLinksCollectionNew;
            TDocumentLanguages.setTExternalLinksCollection(TExternalLinksCollectionNew);
            Collection<TVersionComments> attachedTVersionCommentsCollectionNew = new ArrayList<TVersionComments>();
            for (TVersionComments TVersionCommentsCollectionNewTVersionCommentsToAttach : TVersionCommentsCollectionNew) {
                TVersionCommentsCollectionNewTVersionCommentsToAttach = em.getReference(TVersionCommentsCollectionNewTVersionCommentsToAttach.getClass(), TVersionCommentsCollectionNewTVersionCommentsToAttach.getVercNsq());
                attachedTVersionCommentsCollectionNew.add(TVersionCommentsCollectionNewTVersionCommentsToAttach);
            }
            TVersionCommentsCollectionNew = attachedTVersionCommentsCollectionNew;
            TDocumentLanguages.setTVersionCommentsCollection(TVersionCommentsCollectionNew);
            Collection<TKeywordTranslations> attachedTKeywordTranslationsCollectionNew = new ArrayList<TKeywordTranslations>();
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach : TKeywordTranslationsCollectionNew) {
                TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach = em.getReference(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getClass(), TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach.getKytrNsq());
                attachedTKeywordTranslationsCollectionNew.add(TKeywordTranslationsCollectionNewTKeywordTranslationsToAttach);
            }
            TKeywordTranslationsCollectionNew = attachedTKeywordTranslationsCollectionNew;
            TDocumentLanguages.setTKeywordTranslationsCollection(TKeywordTranslationsCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TDocumentLanguages.setTZipArchivesCollection(TZipArchivesCollectionNew);
            Collection<TLabelBundles> attachedTLabelBundlesCollectionNew = new ArrayList<TLabelBundles>();
            for (TLabelBundles TLabelBundlesCollectionNewTLabelBundlesToAttach : TLabelBundlesCollectionNew) {
                TLabelBundlesCollectionNewTLabelBundlesToAttach = em.getReference(TLabelBundlesCollectionNewTLabelBundlesToAttach.getClass(), TLabelBundlesCollectionNewTLabelBundlesToAttach.getLabuNsq());
                attachedTLabelBundlesCollectionNew.add(TLabelBundlesCollectionNewTLabelBundlesToAttach);
            }
            TLabelBundlesCollectionNew = attachedTLabelBundlesCollectionNew;
            TDocumentLanguages.setTLabelBundlesCollection(TLabelBundlesCollectionNew);
            Collection<TDocumentlangsAnnexlangs> attachedTDocumentlangsAnnexlangsCollectionNew = new ArrayList<TDocumentlangsAnnexlangs>();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach : TDocumentlangsAnnexlangsCollectionNew) {
                TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach = em.getReference(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach.getClass(), TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach.getDoanNsq());
                attachedTDocumentlangsAnnexlangsCollectionNew.add(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangsToAttach);
            }
            TDocumentlangsAnnexlangsCollectionNew = attachedTDocumentlangsAnnexlangsCollectionNew;
            TDocumentLanguages.setTDocumentlangsAnnexlangsCollection(TDocumentlangsAnnexlangsCollectionNew);
            Collection<TPrintableDocuments> attachedTPrintableDocumentsCollectionNew = new ArrayList<TPrintableDocuments>();
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach : TPrintableDocumentsCollectionNew) {
                TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach = em.getReference(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getClass(), TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach.getPdocNsq());
                attachedTPrintableDocumentsCollectionNew.add(TPrintableDocumentsCollectionNewTPrintableDocumentsToAttach);
            }
            TPrintableDocumentsCollectionNew = attachedTPrintableDocumentsCollectionNew;
            TDocumentLanguages.setTPrintableDocumentsCollection(TPrintableDocumentsCollectionNew);
            TDocumentLanguages = em.merge(TDocumentLanguages);
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionNew) {
                if (!TChapterDescriptionsTransCollectionOld.contains(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans)) {
                    TDocumentLanguages oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.getChdtDlanNsq();
                    TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.setChdtDlanNsq(TDocumentLanguages);
                    TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = em.merge(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                    if (oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans != null && !oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.equals(TDocumentLanguages)) {
                        oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                        oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = em.merge(oldChdtDlanNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                    }
                }
            }
            for (TDocuments TDocumentsCollectionNewTDocuments : TDocumentsCollectionNew) {
                if (!TDocumentsCollectionOld.contains(TDocumentsCollectionNewTDocuments)) {
                    TDocumentLanguages oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments = TDocumentsCollectionNewTDocuments.getDocuDlanNsq();
                    TDocumentsCollectionNewTDocuments.setDocuDlanNsq(TDocumentLanguages);
                    TDocumentsCollectionNewTDocuments = em.merge(TDocumentsCollectionNewTDocuments);
                    if (oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments != null && !oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments.equals(TDocumentLanguages)) {
                        oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments.getTDocumentsCollection().remove(TDocumentsCollectionNewTDocuments);
                        oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments = em.merge(oldDocuDlanNsqOfTDocumentsCollectionNewTDocuments);
                    }
                }
            }
            for (TExternalLinks TExternalLinksCollectionNewTExternalLinks : TExternalLinksCollectionNew) {
                if (!TExternalLinksCollectionOld.contains(TExternalLinksCollectionNewTExternalLinks)) {
                    TDocumentLanguages oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks = TExternalLinksCollectionNewTExternalLinks.getElnkDlanNsq();
                    TExternalLinksCollectionNewTExternalLinks.setElnkDlanNsq(TDocumentLanguages);
                    TExternalLinksCollectionNewTExternalLinks = em.merge(TExternalLinksCollectionNewTExternalLinks);
                    if (oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks != null && !oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks.equals(TDocumentLanguages)) {
                        oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks.getTExternalLinksCollection().remove(TExternalLinksCollectionNewTExternalLinks);
                        oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks = em.merge(oldElnkDlanNsqOfTExternalLinksCollectionNewTExternalLinks);
                    }
                }
            }
            for (TVersionComments TVersionCommentsCollectionNewTVersionComments : TVersionCommentsCollectionNew) {
                if (!TVersionCommentsCollectionOld.contains(TVersionCommentsCollectionNewTVersionComments)) {
                    TDocumentLanguages oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments = TVersionCommentsCollectionNewTVersionComments.getVercDlanNsq();
                    TVersionCommentsCollectionNewTVersionComments.setVercDlanNsq(TDocumentLanguages);
                    TVersionCommentsCollectionNewTVersionComments = em.merge(TVersionCommentsCollectionNewTVersionComments);
                    if (oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments != null && !oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments.equals(TDocumentLanguages)) {
                        oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments.getTVersionCommentsCollection().remove(TVersionCommentsCollectionNewTVersionComments);
                        oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments = em.merge(oldVercDlanNsqOfTVersionCommentsCollectionNewTVersionComments);
                    }
                }
            }
            for (TKeywordTranslations TKeywordTranslationsCollectionNewTKeywordTranslations : TKeywordTranslationsCollectionNew) {
                if (!TKeywordTranslationsCollectionOld.contains(TKeywordTranslationsCollectionNewTKeywordTranslations)) {
                    TDocumentLanguages oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = TKeywordTranslationsCollectionNewTKeywordTranslations.getKytrDlanNsq();
                    TKeywordTranslationsCollectionNewTKeywordTranslations.setKytrDlanNsq(TDocumentLanguages);
                    TKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(TKeywordTranslationsCollectionNewTKeywordTranslations);
                    if (oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations != null && !oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.equals(TDocumentLanguages)) {
                        oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations.getTKeywordTranslationsCollection().remove(TKeywordTranslationsCollectionNewTKeywordTranslations);
                        oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations = em.merge(oldKytrDlanNsqOfTKeywordTranslationsCollectionNewTKeywordTranslations);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    TZipArchivesCollectionOldTZipArchives.setZipaDlanNsq(null);
                    TZipArchivesCollectionOldTZipArchives = em.merge(TZipArchivesCollectionOldTZipArchives);
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TDocumentLanguages oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaDlanNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaDlanNsq(TDocumentLanguages);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives.equals(TDocumentLanguages)) {
                        oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaDlanNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            for (TLabelBundles TLabelBundlesCollectionNewTLabelBundles : TLabelBundlesCollectionNew) {
                if (!TLabelBundlesCollectionOld.contains(TLabelBundlesCollectionNewTLabelBundles)) {
                    TDocumentLanguages oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles = TLabelBundlesCollectionNewTLabelBundles.getLabuDlanNsq();
                    TLabelBundlesCollectionNewTLabelBundles.setLabuDlanNsq(TDocumentLanguages);
                    TLabelBundlesCollectionNewTLabelBundles = em.merge(TLabelBundlesCollectionNewTLabelBundles);
                    if (oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles != null && !oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles.equals(TDocumentLanguages)) {
                        oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles.getTLabelBundlesCollection().remove(TLabelBundlesCollectionNewTLabelBundles);
                        oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles = em.merge(oldLabuDlanNsqOfTLabelBundlesCollectionNewTLabelBundles);
                    }
                }
            }
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionNew) {
                if (!TDocumentlangsAnnexlangsCollectionOld.contains(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs)) {
                    TDocumentLanguages oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.getDoanDlanNsq();
                    TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.setDoanDlanNsq(TDocumentLanguages);
                    TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = em.merge(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                    if (oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs != null && !oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.equals(TDocumentLanguages)) {
                        oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs.getTDocumentlangsAnnexlangsCollection().remove(TDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                        oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs = em.merge(oldDoanDlanNsqOfTDocumentlangsAnnexlangsCollectionNewTDocumentlangsAnnexlangs);
                    }
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionOldTPrintableDocuments : TPrintableDocumentsCollectionOld) {
                if (!TPrintableDocumentsCollectionNew.contains(TPrintableDocumentsCollectionOldTPrintableDocuments)) {
                    TPrintableDocumentsCollectionOldTPrintableDocuments.setPdocDlanNsq(null);
                    TPrintableDocumentsCollectionOldTPrintableDocuments = em.merge(TPrintableDocumentsCollectionOldTPrintableDocuments);
                }
            }
            for (TPrintableDocuments TPrintableDocumentsCollectionNewTPrintableDocuments : TPrintableDocumentsCollectionNew) {
                if (!TPrintableDocumentsCollectionOld.contains(TPrintableDocumentsCollectionNewTPrintableDocuments)) {
                    TDocumentLanguages oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = TPrintableDocumentsCollectionNewTPrintableDocuments.getPdocDlanNsq();
                    TPrintableDocumentsCollectionNewTPrintableDocuments.setPdocDlanNsq(TDocumentLanguages);
                    TPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(TPrintableDocumentsCollectionNewTPrintableDocuments);
                    if (oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments != null && !oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.equals(TDocumentLanguages)) {
                        oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments.getTPrintableDocumentsCollection().remove(TPrintableDocumentsCollectionNewTPrintableDocuments);
                        oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments = em.merge(oldPdocDlanNsqOfTPrintableDocumentsCollectionNewTPrintableDocuments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TDocumentLanguages.getDlanNsq();
                if (findTDocumentLanguages(id) == null) {
                    throw new NonexistentEntityException("The tDocumentLanguages with id " + id + " no longer exists.");
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
            TDocumentLanguages TDocumentLanguages;
            try {
                TDocumentLanguages = em.getReference(TDocumentLanguages.class, id);
                TDocumentLanguages.getDlanNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDocumentLanguages with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionOrphanCheck = TDocumentLanguages.getTChapterDescriptionsTransCollection();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionOrphanCheckTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TChapterDescriptionsTrans " + TChapterDescriptionsTransCollectionOrphanCheckTChapterDescriptionsTrans + " in its TChapterDescriptionsTransCollection field has a non-nullable chdtDlanNsq field.");
            }
            Collection<TDocuments> TDocumentsCollectionOrphanCheck = TDocumentLanguages.getTDocumentsCollection();
            for (TDocuments TDocumentsCollectionOrphanCheckTDocuments : TDocumentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TDocuments " + TDocumentsCollectionOrphanCheckTDocuments + " in its TDocumentsCollection field has a non-nullable docuDlanNsq field.");
            }
            Collection<TExternalLinks> TExternalLinksCollectionOrphanCheck = TDocumentLanguages.getTExternalLinksCollection();
            for (TExternalLinks TExternalLinksCollectionOrphanCheckTExternalLinks : TExternalLinksCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TExternalLinks " + TExternalLinksCollectionOrphanCheckTExternalLinks + " in its TExternalLinksCollection field has a non-nullable elnkDlanNsq field.");
            }
            Collection<TVersionComments> TVersionCommentsCollectionOrphanCheck = TDocumentLanguages.getTVersionCommentsCollection();
            for (TVersionComments TVersionCommentsCollectionOrphanCheckTVersionComments : TVersionCommentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TVersionComments " + TVersionCommentsCollectionOrphanCheckTVersionComments + " in its TVersionCommentsCollection field has a non-nullable vercDlanNsq field.");
            }
            Collection<TKeywordTranslations> TKeywordTranslationsCollectionOrphanCheck = TDocumentLanguages.getTKeywordTranslationsCollection();
            for (TKeywordTranslations TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations : TKeywordTranslationsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TKeywordTranslations " + TKeywordTranslationsCollectionOrphanCheckTKeywordTranslations + " in its TKeywordTranslationsCollection field has a non-nullable kytrDlanNsq field.");
            }
            Collection<TLabelBundles> TLabelBundlesCollectionOrphanCheck = TDocumentLanguages.getTLabelBundlesCollection();
            for (TLabelBundles TLabelBundlesCollectionOrphanCheckTLabelBundles : TLabelBundlesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TLabelBundles " + TLabelBundlesCollectionOrphanCheckTLabelBundles + " in its TLabelBundlesCollection field has a non-nullable labuDlanNsq field.");
            }
            Collection<TDocumentlangsAnnexlangs> TDocumentlangsAnnexlangsCollectionOrphanCheck = TDocumentLanguages.getTDocumentlangsAnnexlangsCollection();
            for (TDocumentlangsAnnexlangs TDocumentlangsAnnexlangsCollectionOrphanCheckTDocumentlangsAnnexlangs : TDocumentlangsAnnexlangsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TDocumentLanguages (" + TDocumentLanguages + ") cannot be destroyed since the TDocumentlangsAnnexlangs " + TDocumentlangsAnnexlangsCollectionOrphanCheckTDocumentlangsAnnexlangs + " in its TDocumentlangsAnnexlangsCollection field has a non-nullable doanDlanNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TZipArchives> TZipArchivesCollection = TDocumentLanguages.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionTZipArchives : TZipArchivesCollection) {
                TZipArchivesCollectionTZipArchives.setZipaDlanNsq(null);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
            }
            Collection<TPrintableDocuments> TPrintableDocumentsCollection = TDocumentLanguages.getTPrintableDocumentsCollection();
            for (TPrintableDocuments TPrintableDocumentsCollectionTPrintableDocuments : TPrintableDocumentsCollection) {
                TPrintableDocumentsCollectionTPrintableDocuments.setPdocDlanNsq(null);
                TPrintableDocumentsCollectionTPrintableDocuments = em.merge(TPrintableDocumentsCollectionTPrintableDocuments);
            }
            em.remove(TDocumentLanguages);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDocumentLanguages> findTDocumentLanguagesEntities() {
        return findTDocumentLanguagesEntities(true, -1, -1);
    }

    public List<TDocumentLanguages> findTDocumentLanguagesEntities(int maxResults, int firstResult) {
        return findTDocumentLanguagesEntities(false, maxResults, firstResult);
    }

    private List<TDocumentLanguages> findTDocumentLanguagesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDocumentLanguages.class));
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

    public TDocumentLanguages findTDocumentLanguages(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDocumentLanguages.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDocumentLanguagesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDocumentLanguages> rt = cq.from(TDocumentLanguages.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
