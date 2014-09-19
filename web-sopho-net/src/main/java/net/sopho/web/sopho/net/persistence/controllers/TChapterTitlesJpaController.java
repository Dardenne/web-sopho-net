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
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import net.sopho.web.sopho.net.persistence.entities.TDocumentStructures;
import net.sopho.web.sopho.net.persistence.entities.TUnits;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.IllegalOrphanException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.NonexistentEntityException;
import net.sopho.web.sopho.net.persistence.controllers.exceptions.PreexistingEntityException;
import net.sopho.web.sopho.net.persistence.entities.TChapterDescriptionsTrans;
import net.sopho.web.sopho.net.persistence.entities.TAnnexes;
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;

/**
 *
 * @author LU01007
 */
public class TChapterTitlesJpaController implements Serializable {

    public TChapterTitlesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TChapterTitles TChapterTitles) throws PreexistingEntityException, Exception {
        if (TChapterTitles.getTUnitsCollection() == null) {
            TChapterTitles.setTUnitsCollection(new ArrayList<TUnits>());
        }
        if (TChapterTitles.getTChapterDescriptionsTransCollection() == null) {
            TChapterTitles.setTChapterDescriptionsTransCollection(new ArrayList<TChapterDescriptionsTrans>());
        }
        if (TChapterTitles.getTAnnexesCollection() == null) {
            TChapterTitles.setTAnnexesCollection(new ArrayList<TAnnexes>());
        }
        if (TChapterTitles.getTChapterContentsCollection() == null) {
            TChapterTitles.setTChapterContentsCollection(new ArrayList<TChapterContents>());
        }
        if (TChapterTitles.getTChapterTitlesCollection() == null) {
            TChapterTitles.setTChapterTitlesCollection(new ArrayList<TChapterTitles>());
        }
        if (TChapterTitles.getTZipArchivesCollection() == null) {
            TChapterTitles.setTZipArchivesCollection(new ArrayList<TZipArchives>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterTitles chtlSourceChtlNsq = TChapterTitles.getChtlSourceChtlNsq();
            if (chtlSourceChtlNsq != null) {
                chtlSourceChtlNsq = em.getReference(chtlSourceChtlNsq.getClass(), chtlSourceChtlNsq.getChtlNsq());
                TChapterTitles.setChtlSourceChtlNsq(chtlSourceChtlNsq);
            }
            TDocumentStructures chtlDstrNsq = TChapterTitles.getChtlDstrNsq();
            if (chtlDstrNsq != null) {
                chtlDstrNsq = em.getReference(chtlDstrNsq.getClass(), chtlDstrNsq.getDstrNsq());
                TChapterTitles.setChtlDstrNsq(chtlDstrNsq);
            }
            Collection<TUnits> attachedTUnitsCollection = new ArrayList<TUnits>();
            for (TUnits TUnitsCollectionTUnitsToAttach : TChapterTitles.getTUnitsCollection()) {
                TUnitsCollectionTUnitsToAttach = em.getReference(TUnitsCollectionTUnitsToAttach.getClass(), TUnitsCollectionTUnitsToAttach.getUnitNsq());
                attachedTUnitsCollection.add(TUnitsCollectionTUnitsToAttach);
            }
            TChapterTitles.setTUnitsCollection(attachedTUnitsCollection);
            Collection<TChapterDescriptionsTrans> attachedTChapterDescriptionsTransCollection = new ArrayList<TChapterDescriptionsTrans>();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach : TChapterTitles.getTChapterDescriptionsTransCollection()) {
                TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach = em.getReference(TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach.getClass(), TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach.getChdtNsq());
                attachedTChapterDescriptionsTransCollection.add(TChapterDescriptionsTransCollectionTChapterDescriptionsTransToAttach);
            }
            TChapterTitles.setTChapterDescriptionsTransCollection(attachedTChapterDescriptionsTransCollection);
            Collection<TAnnexes> attachedTAnnexesCollection = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionTAnnexesToAttach : TChapterTitles.getTAnnexesCollection()) {
                TAnnexesCollectionTAnnexesToAttach = em.getReference(TAnnexesCollectionTAnnexesToAttach.getClass(), TAnnexesCollectionTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollection.add(TAnnexesCollectionTAnnexesToAttach);
            }
            TChapterTitles.setTAnnexesCollection(attachedTAnnexesCollection);
            Collection<TChapterContents> attachedTChapterContentsCollection = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionTChapterContentsToAttach : TChapterTitles.getTChapterContentsCollection()) {
                TChapterContentsCollectionTChapterContentsToAttach = em.getReference(TChapterContentsCollectionTChapterContentsToAttach.getClass(), TChapterContentsCollectionTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollection.add(TChapterContentsCollectionTChapterContentsToAttach);
            }
            TChapterTitles.setTChapterContentsCollection(attachedTChapterContentsCollection);
            Collection<TChapterTitles> attachedTChapterTitlesCollection = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionTChapterTitlesToAttach : TChapterTitles.getTChapterTitlesCollection()) {
                TChapterTitlesCollectionTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollection.add(TChapterTitlesCollectionTChapterTitlesToAttach);
            }
            TChapterTitles.setTChapterTitlesCollection(attachedTChapterTitlesCollection);
            Collection<TZipArchives> attachedTZipArchivesCollection = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionTZipArchivesToAttach : TChapterTitles.getTZipArchivesCollection()) {
                TZipArchivesCollectionTZipArchivesToAttach = em.getReference(TZipArchivesCollectionTZipArchivesToAttach.getClass(), TZipArchivesCollectionTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollection.add(TZipArchivesCollectionTZipArchivesToAttach);
            }
            TChapterTitles.setTZipArchivesCollection(attachedTZipArchivesCollection);
            em.persist(TChapterTitles);
            if (chtlSourceChtlNsq != null) {
                chtlSourceChtlNsq.getTChapterTitlesCollection().add(TChapterTitles);
                chtlSourceChtlNsq = em.merge(chtlSourceChtlNsq);
            }
            if (chtlDstrNsq != null) {
                chtlDstrNsq.getTChapterTitlesCollection().add(TChapterTitles);
                chtlDstrNsq = em.merge(chtlDstrNsq);
            }
            for (TUnits TUnitsCollectionTUnits : TChapterTitles.getTUnitsCollection()) {
                TUnitsCollectionTUnits.getTChapterTitlesCollection().add(TChapterTitles);
                TUnitsCollectionTUnits = em.merge(TUnitsCollectionTUnits);
            }
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionTChapterDescriptionsTrans : TChapterTitles.getTChapterDescriptionsTransCollection()) {
                TChapterTitles oldChdtChtlNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans = TChapterDescriptionsTransCollectionTChapterDescriptionsTrans.getChdtChtlNsq();
                TChapterDescriptionsTransCollectionTChapterDescriptionsTrans.setChdtChtlNsq(TChapterTitles);
                TChapterDescriptionsTransCollectionTChapterDescriptionsTrans = em.merge(TChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                if (oldChdtChtlNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans != null) {
                    oldChdtChtlNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                    oldChdtChtlNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans = em.merge(oldChdtChtlNsqOfTChapterDescriptionsTransCollectionTChapterDescriptionsTrans);
                }
            }
            for (TAnnexes TAnnexesCollectionTAnnexes : TChapterTitles.getTAnnexesCollection()) {
                TChapterTitles oldAnexChtlNsqOfTAnnexesCollectionTAnnexes = TAnnexesCollectionTAnnexes.getAnexChtlNsq();
                TAnnexesCollectionTAnnexes.setAnexChtlNsq(TChapterTitles);
                TAnnexesCollectionTAnnexes = em.merge(TAnnexesCollectionTAnnexes);
                if (oldAnexChtlNsqOfTAnnexesCollectionTAnnexes != null) {
                    oldAnexChtlNsqOfTAnnexesCollectionTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionTAnnexes);
                    oldAnexChtlNsqOfTAnnexesCollectionTAnnexes = em.merge(oldAnexChtlNsqOfTAnnexesCollectionTAnnexes);
                }
            }
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterTitles.getTChapterContentsCollection()) {
                TChapterTitles oldChcoChtlNsqOfTChapterContentsCollectionTChapterContents = TChapterContentsCollectionTChapterContents.getChcoChtlNsq();
                TChapterContentsCollectionTChapterContents.setChcoChtlNsq(TChapterTitles);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
                if (oldChcoChtlNsqOfTChapterContentsCollectionTChapterContents != null) {
                    oldChcoChtlNsqOfTChapterContentsCollectionTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionTChapterContents);
                    oldChcoChtlNsqOfTChapterContentsCollectionTChapterContents = em.merge(oldChcoChtlNsqOfTChapterContentsCollectionTChapterContents);
                }
            }
            for (TChapterTitles TChapterTitlesCollectionTChapterTitles : TChapterTitles.getTChapterTitlesCollection()) {
                TChapterTitles oldChtlSourceChtlNsqOfTChapterTitlesCollectionTChapterTitles = TChapterTitlesCollectionTChapterTitles.getChtlSourceChtlNsq();
                TChapterTitlesCollectionTChapterTitles.setChtlSourceChtlNsq(TChapterTitles);
                TChapterTitlesCollectionTChapterTitles = em.merge(TChapterTitlesCollectionTChapterTitles);
                if (oldChtlSourceChtlNsqOfTChapterTitlesCollectionTChapterTitles != null) {
                    oldChtlSourceChtlNsqOfTChapterTitlesCollectionTChapterTitles.getTChapterTitlesCollection().remove(TChapterTitlesCollectionTChapterTitles);
                    oldChtlSourceChtlNsqOfTChapterTitlesCollectionTChapterTitles = em.merge(oldChtlSourceChtlNsqOfTChapterTitlesCollectionTChapterTitles);
                }
            }
            for (TZipArchives TZipArchivesCollectionTZipArchives : TChapterTitles.getTZipArchivesCollection()) {
                TChapterTitles oldZipaChtlNsqOfTZipArchivesCollectionTZipArchives = TZipArchivesCollectionTZipArchives.getZipaChtlNsq();
                TZipArchivesCollectionTZipArchives.setZipaChtlNsq(TChapterTitles);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
                if (oldZipaChtlNsqOfTZipArchivesCollectionTZipArchives != null) {
                    oldZipaChtlNsqOfTZipArchivesCollectionTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionTZipArchives);
                    oldZipaChtlNsqOfTZipArchivesCollectionTZipArchives = em.merge(oldZipaChtlNsqOfTZipArchivesCollectionTZipArchives);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTChapterTitles(TChapterTitles.getChtlNsq()) != null) {
                throw new PreexistingEntityException("TChapterTitles " + TChapterTitles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TChapterTitles TChapterTitles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterTitles persistentTChapterTitles = em.find(TChapterTitles.class, TChapterTitles.getChtlNsq());
            TChapterTitles chtlSourceChtlNsqOld = persistentTChapterTitles.getChtlSourceChtlNsq();
            TChapterTitles chtlSourceChtlNsqNew = TChapterTitles.getChtlSourceChtlNsq();
            TDocumentStructures chtlDstrNsqOld = persistentTChapterTitles.getChtlDstrNsq();
            TDocumentStructures chtlDstrNsqNew = TChapterTitles.getChtlDstrNsq();
            Collection<TUnits> TUnitsCollectionOld = persistentTChapterTitles.getTUnitsCollection();
            Collection<TUnits> TUnitsCollectionNew = TChapterTitles.getTUnitsCollection();
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionOld = persistentTChapterTitles.getTChapterDescriptionsTransCollection();
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionNew = TChapterTitles.getTChapterDescriptionsTransCollection();
            Collection<TAnnexes> TAnnexesCollectionOld = persistentTChapterTitles.getTAnnexesCollection();
            Collection<TAnnexes> TAnnexesCollectionNew = TChapterTitles.getTAnnexesCollection();
            Collection<TChapterContents> TChapterContentsCollectionOld = persistentTChapterTitles.getTChapterContentsCollection();
            Collection<TChapterContents> TChapterContentsCollectionNew = TChapterTitles.getTChapterContentsCollection();
            Collection<TChapterTitles> TChapterTitlesCollectionOld = persistentTChapterTitles.getTChapterTitlesCollection();
            Collection<TChapterTitles> TChapterTitlesCollectionNew = TChapterTitles.getTChapterTitlesCollection();
            Collection<TZipArchives> TZipArchivesCollectionOld = persistentTChapterTitles.getTZipArchivesCollection();
            Collection<TZipArchives> TZipArchivesCollectionNew = TChapterTitles.getTZipArchivesCollection();
            List<String> illegalOrphanMessages = null;
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionOld) {
                if (!TChapterDescriptionsTransCollectionNew.contains(TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TChapterDescriptionsTrans " + TChapterDescriptionsTransCollectionOldTChapterDescriptionsTrans + " since its chdtChtlNsq field is not nullable.");
                }
            }
            for (TAnnexes TAnnexesCollectionOldTAnnexes : TAnnexesCollectionOld) {
                if (!TAnnexesCollectionNew.contains(TAnnexesCollectionOldTAnnexes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TAnnexes " + TAnnexesCollectionOldTAnnexes + " since its anexChtlNsq field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (chtlSourceChtlNsqNew != null) {
                chtlSourceChtlNsqNew = em.getReference(chtlSourceChtlNsqNew.getClass(), chtlSourceChtlNsqNew.getChtlNsq());
                TChapterTitles.setChtlSourceChtlNsq(chtlSourceChtlNsqNew);
            }
            if (chtlDstrNsqNew != null) {
                chtlDstrNsqNew = em.getReference(chtlDstrNsqNew.getClass(), chtlDstrNsqNew.getDstrNsq());
                TChapterTitles.setChtlDstrNsq(chtlDstrNsqNew);
            }
            Collection<TUnits> attachedTUnitsCollectionNew = new ArrayList<TUnits>();
            for (TUnits TUnitsCollectionNewTUnitsToAttach : TUnitsCollectionNew) {
                TUnitsCollectionNewTUnitsToAttach = em.getReference(TUnitsCollectionNewTUnitsToAttach.getClass(), TUnitsCollectionNewTUnitsToAttach.getUnitNsq());
                attachedTUnitsCollectionNew.add(TUnitsCollectionNewTUnitsToAttach);
            }
            TUnitsCollectionNew = attachedTUnitsCollectionNew;
            TChapterTitles.setTUnitsCollection(TUnitsCollectionNew);
            Collection<TChapterDescriptionsTrans> attachedTChapterDescriptionsTransCollectionNew = new ArrayList<TChapterDescriptionsTrans>();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach : TChapterDescriptionsTransCollectionNew) {
                TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach = em.getReference(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach.getClass(), TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach.getChdtNsq());
                attachedTChapterDescriptionsTransCollectionNew.add(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTransToAttach);
            }
            TChapterDescriptionsTransCollectionNew = attachedTChapterDescriptionsTransCollectionNew;
            TChapterTitles.setTChapterDescriptionsTransCollection(TChapterDescriptionsTransCollectionNew);
            Collection<TAnnexes> attachedTAnnexesCollectionNew = new ArrayList<TAnnexes>();
            for (TAnnexes TAnnexesCollectionNewTAnnexesToAttach : TAnnexesCollectionNew) {
                TAnnexesCollectionNewTAnnexesToAttach = em.getReference(TAnnexesCollectionNewTAnnexesToAttach.getClass(), TAnnexesCollectionNewTAnnexesToAttach.getAnexNsq());
                attachedTAnnexesCollectionNew.add(TAnnexesCollectionNewTAnnexesToAttach);
            }
            TAnnexesCollectionNew = attachedTAnnexesCollectionNew;
            TChapterTitles.setTAnnexesCollection(TAnnexesCollectionNew);
            Collection<TChapterContents> attachedTChapterContentsCollectionNew = new ArrayList<TChapterContents>();
            for (TChapterContents TChapterContentsCollectionNewTChapterContentsToAttach : TChapterContentsCollectionNew) {
                TChapterContentsCollectionNewTChapterContentsToAttach = em.getReference(TChapterContentsCollectionNewTChapterContentsToAttach.getClass(), TChapterContentsCollectionNewTChapterContentsToAttach.getChcoNsq());
                attachedTChapterContentsCollectionNew.add(TChapterContentsCollectionNewTChapterContentsToAttach);
            }
            TChapterContentsCollectionNew = attachedTChapterContentsCollectionNew;
            TChapterTitles.setTChapterContentsCollection(TChapterContentsCollectionNew);
            Collection<TChapterTitles> attachedTChapterTitlesCollectionNew = new ArrayList<TChapterTitles>();
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitlesToAttach : TChapterTitlesCollectionNew) {
                TChapterTitlesCollectionNewTChapterTitlesToAttach = em.getReference(TChapterTitlesCollectionNewTChapterTitlesToAttach.getClass(), TChapterTitlesCollectionNewTChapterTitlesToAttach.getChtlNsq());
                attachedTChapterTitlesCollectionNew.add(TChapterTitlesCollectionNewTChapterTitlesToAttach);
            }
            TChapterTitlesCollectionNew = attachedTChapterTitlesCollectionNew;
            TChapterTitles.setTChapterTitlesCollection(TChapterTitlesCollectionNew);
            Collection<TZipArchives> attachedTZipArchivesCollectionNew = new ArrayList<TZipArchives>();
            for (TZipArchives TZipArchivesCollectionNewTZipArchivesToAttach : TZipArchivesCollectionNew) {
                TZipArchivesCollectionNewTZipArchivesToAttach = em.getReference(TZipArchivesCollectionNewTZipArchivesToAttach.getClass(), TZipArchivesCollectionNewTZipArchivesToAttach.getZipaNsq());
                attachedTZipArchivesCollectionNew.add(TZipArchivesCollectionNewTZipArchivesToAttach);
            }
            TZipArchivesCollectionNew = attachedTZipArchivesCollectionNew;
            TChapterTitles.setTZipArchivesCollection(TZipArchivesCollectionNew);
            TChapterTitles = em.merge(TChapterTitles);
            if (chtlSourceChtlNsqOld != null && !chtlSourceChtlNsqOld.equals(chtlSourceChtlNsqNew)) {
                chtlSourceChtlNsqOld.getTChapterTitlesCollection().remove(TChapterTitles);
                chtlSourceChtlNsqOld = em.merge(chtlSourceChtlNsqOld);
            }
            if (chtlSourceChtlNsqNew != null && !chtlSourceChtlNsqNew.equals(chtlSourceChtlNsqOld)) {
                chtlSourceChtlNsqNew.getTChapterTitlesCollection().add(TChapterTitles);
                chtlSourceChtlNsqNew = em.merge(chtlSourceChtlNsqNew);
            }
            if (chtlDstrNsqOld != null && !chtlDstrNsqOld.equals(chtlDstrNsqNew)) {
                chtlDstrNsqOld.getTChapterTitlesCollection().remove(TChapterTitles);
                chtlDstrNsqOld = em.merge(chtlDstrNsqOld);
            }
            if (chtlDstrNsqNew != null && !chtlDstrNsqNew.equals(chtlDstrNsqOld)) {
                chtlDstrNsqNew.getTChapterTitlesCollection().add(TChapterTitles);
                chtlDstrNsqNew = em.merge(chtlDstrNsqNew);
            }
            for (TUnits TUnitsCollectionOldTUnits : TUnitsCollectionOld) {
                if (!TUnitsCollectionNew.contains(TUnitsCollectionOldTUnits)) {
                    TUnitsCollectionOldTUnits.getTChapterTitlesCollection().remove(TChapterTitles);
                    TUnitsCollectionOldTUnits = em.merge(TUnitsCollectionOldTUnits);
                }
            }
            for (TUnits TUnitsCollectionNewTUnits : TUnitsCollectionNew) {
                if (!TUnitsCollectionOld.contains(TUnitsCollectionNewTUnits)) {
                    TUnitsCollectionNewTUnits.getTChapterTitlesCollection().add(TChapterTitles);
                    TUnitsCollectionNewTUnits = em.merge(TUnitsCollectionNewTUnits);
                }
            }
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionNew) {
                if (!TChapterDescriptionsTransCollectionOld.contains(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans)) {
                    TChapterTitles oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.getChdtChtlNsq();
                    TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.setChdtChtlNsq(TChapterTitles);
                    TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = em.merge(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                    if (oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans != null && !oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.equals(TChapterTitles)) {
                        oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans.getTChapterDescriptionsTransCollection().remove(TChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                        oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans = em.merge(oldChdtChtlNsqOfTChapterDescriptionsTransCollectionNewTChapterDescriptionsTrans);
                    }
                }
            }
            for (TAnnexes TAnnexesCollectionNewTAnnexes : TAnnexesCollectionNew) {
                if (!TAnnexesCollectionOld.contains(TAnnexesCollectionNewTAnnexes)) {
                    TChapterTitles oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes = TAnnexesCollectionNewTAnnexes.getAnexChtlNsq();
                    TAnnexesCollectionNewTAnnexes.setAnexChtlNsq(TChapterTitles);
                    TAnnexesCollectionNewTAnnexes = em.merge(TAnnexesCollectionNewTAnnexes);
                    if (oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes != null && !oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes.equals(TChapterTitles)) {
                        oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes.getTAnnexesCollection().remove(TAnnexesCollectionNewTAnnexes);
                        oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes = em.merge(oldAnexChtlNsqOfTAnnexesCollectionNewTAnnexes);
                    }
                }
            }
            for (TChapterContents TChapterContentsCollectionOldTChapterContents : TChapterContentsCollectionOld) {
                if (!TChapterContentsCollectionNew.contains(TChapterContentsCollectionOldTChapterContents)) {
                    TChapterContentsCollectionOldTChapterContents.setChcoChtlNsq(null);
                    TChapterContentsCollectionOldTChapterContents = em.merge(TChapterContentsCollectionOldTChapterContents);
                }
            }
            for (TChapterContents TChapterContentsCollectionNewTChapterContents : TChapterContentsCollectionNew) {
                if (!TChapterContentsCollectionOld.contains(TChapterContentsCollectionNewTChapterContents)) {
                    TChapterTitles oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents = TChapterContentsCollectionNewTChapterContents.getChcoChtlNsq();
                    TChapterContentsCollectionNewTChapterContents.setChcoChtlNsq(TChapterTitles);
                    TChapterContentsCollectionNewTChapterContents = em.merge(TChapterContentsCollectionNewTChapterContents);
                    if (oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents != null && !oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents.equals(TChapterTitles)) {
                        oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents.getTChapterContentsCollection().remove(TChapterContentsCollectionNewTChapterContents);
                        oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents = em.merge(oldChcoChtlNsqOfTChapterContentsCollectionNewTChapterContents);
                    }
                }
            }
            for (TChapterTitles TChapterTitlesCollectionOldTChapterTitles : TChapterTitlesCollectionOld) {
                if (!TChapterTitlesCollectionNew.contains(TChapterTitlesCollectionOldTChapterTitles)) {
                    TChapterTitlesCollectionOldTChapterTitles.setChtlSourceChtlNsq(null);
                    TChapterTitlesCollectionOldTChapterTitles = em.merge(TChapterTitlesCollectionOldTChapterTitles);
                }
            }
            for (TChapterTitles TChapterTitlesCollectionNewTChapterTitles : TChapterTitlesCollectionNew) {
                if (!TChapterTitlesCollectionOld.contains(TChapterTitlesCollectionNewTChapterTitles)) {
                    TChapterTitles oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles = TChapterTitlesCollectionNewTChapterTitles.getChtlSourceChtlNsq();
                    TChapterTitlesCollectionNewTChapterTitles.setChtlSourceChtlNsq(TChapterTitles);
                    TChapterTitlesCollectionNewTChapterTitles = em.merge(TChapterTitlesCollectionNewTChapterTitles);
                    if (oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles != null && !oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles.equals(TChapterTitles)) {
                        oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles.getTChapterTitlesCollection().remove(TChapterTitlesCollectionNewTChapterTitles);
                        oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles = em.merge(oldChtlSourceChtlNsqOfTChapterTitlesCollectionNewTChapterTitles);
                    }
                }
            }
            for (TZipArchives TZipArchivesCollectionOldTZipArchives : TZipArchivesCollectionOld) {
                if (!TZipArchivesCollectionNew.contains(TZipArchivesCollectionOldTZipArchives)) {
                    TZipArchivesCollectionOldTZipArchives.setZipaChtlNsq(null);
                    TZipArchivesCollectionOldTZipArchives = em.merge(TZipArchivesCollectionOldTZipArchives);
                }
            }
            for (TZipArchives TZipArchivesCollectionNewTZipArchives : TZipArchivesCollectionNew) {
                if (!TZipArchivesCollectionOld.contains(TZipArchivesCollectionNewTZipArchives)) {
                    TChapterTitles oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives = TZipArchivesCollectionNewTZipArchives.getZipaChtlNsq();
                    TZipArchivesCollectionNewTZipArchives.setZipaChtlNsq(TChapterTitles);
                    TZipArchivesCollectionNewTZipArchives = em.merge(TZipArchivesCollectionNewTZipArchives);
                    if (oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives != null && !oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives.equals(TChapterTitles)) {
                        oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives.getTZipArchivesCollection().remove(TZipArchivesCollectionNewTZipArchives);
                        oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives = em.merge(oldZipaChtlNsqOfTZipArchivesCollectionNewTZipArchives);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TChapterTitles.getChtlNsq();
                if (findTChapterTitles(id) == null) {
                    throw new NonexistentEntityException("The tChapterTitles with id " + id + " no longer exists.");
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
            TChapterTitles TChapterTitles;
            try {
                TChapterTitles = em.getReference(TChapterTitles.class, id);
                TChapterTitles.getChtlNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TChapterTitles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TChapterDescriptionsTrans> TChapterDescriptionsTransCollectionOrphanCheck = TChapterTitles.getTChapterDescriptionsTransCollection();
            for (TChapterDescriptionsTrans TChapterDescriptionsTransCollectionOrphanCheckTChapterDescriptionsTrans : TChapterDescriptionsTransCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TChapterTitles (" + TChapterTitles + ") cannot be destroyed since the TChapterDescriptionsTrans " + TChapterDescriptionsTransCollectionOrphanCheckTChapterDescriptionsTrans + " in its TChapterDescriptionsTransCollection field has a non-nullable chdtChtlNsq field.");
            }
            Collection<TAnnexes> TAnnexesCollectionOrphanCheck = TChapterTitles.getTAnnexesCollection();
            for (TAnnexes TAnnexesCollectionOrphanCheckTAnnexes : TAnnexesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TChapterTitles (" + TChapterTitles + ") cannot be destroyed since the TAnnexes " + TAnnexesCollectionOrphanCheckTAnnexes + " in its TAnnexesCollection field has a non-nullable anexChtlNsq field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TChapterTitles chtlSourceChtlNsq = TChapterTitles.getChtlSourceChtlNsq();
            if (chtlSourceChtlNsq != null) {
                chtlSourceChtlNsq.getTChapterTitlesCollection().remove(TChapterTitles);
                chtlSourceChtlNsq = em.merge(chtlSourceChtlNsq);
            }
            TDocumentStructures chtlDstrNsq = TChapterTitles.getChtlDstrNsq();
            if (chtlDstrNsq != null) {
                chtlDstrNsq.getTChapterTitlesCollection().remove(TChapterTitles);
                chtlDstrNsq = em.merge(chtlDstrNsq);
            }
            Collection<TUnits> TUnitsCollection = TChapterTitles.getTUnitsCollection();
            for (TUnits TUnitsCollectionTUnits : TUnitsCollection) {
                TUnitsCollectionTUnits.getTChapterTitlesCollection().remove(TChapterTitles);
                TUnitsCollectionTUnits = em.merge(TUnitsCollectionTUnits);
            }
            Collection<TChapterContents> TChapterContentsCollection = TChapterTitles.getTChapterContentsCollection();
            for (TChapterContents TChapterContentsCollectionTChapterContents : TChapterContentsCollection) {
                TChapterContentsCollectionTChapterContents.setChcoChtlNsq(null);
                TChapterContentsCollectionTChapterContents = em.merge(TChapterContentsCollectionTChapterContents);
            }
            Collection<TChapterTitles> TChapterTitlesCollection = TChapterTitles.getTChapterTitlesCollection();
            for (TChapterTitles TChapterTitlesCollectionTChapterTitles : TChapterTitlesCollection) {
                TChapterTitlesCollectionTChapterTitles.setChtlSourceChtlNsq(null);
                TChapterTitlesCollectionTChapterTitles = em.merge(TChapterTitlesCollectionTChapterTitles);
            }
            Collection<TZipArchives> TZipArchivesCollection = TChapterTitles.getTZipArchivesCollection();
            for (TZipArchives TZipArchivesCollectionTZipArchives : TZipArchivesCollection) {
                TZipArchivesCollectionTZipArchives.setZipaChtlNsq(null);
                TZipArchivesCollectionTZipArchives = em.merge(TZipArchivesCollectionTZipArchives);
            }
            em.remove(TChapterTitles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TChapterTitles> findTChapterTitlesEntities() {
        return findTChapterTitlesEntities(true, -1, -1);
    }

    public List<TChapterTitles> findTChapterTitlesEntities(int maxResults, int firstResult) {
        return findTChapterTitlesEntities(false, maxResults, firstResult);
    }

    private List<TChapterTitles> findTChapterTitlesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TChapterTitles.class));
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

    public TChapterTitles findTChapterTitles(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TChapterTitles.class, id);
        } finally {
            em.close();
        }
    }

    public int getTChapterTitlesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TChapterTitles> rt = cq.from(TChapterTitles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
