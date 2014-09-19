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
import net.sopho.web.sopho.net.persistence.entities.TAnnexLanguages;
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TDocumentStructures;
import net.sopho.web.sopho.net.persistence.entities.TVersions;
import net.sopho.web.sopho.net.persistence.entities.TZipArchives;

/**
 *
 * @author LU01007
 */
public class TZipArchivesJpaController implements Serializable {

    public TZipArchivesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TZipArchives TZipArchives) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexLanguages zipaAlanNsq = TZipArchives.getZipaAlanNsq();
            if (zipaAlanNsq != null) {
                zipaAlanNsq = em.getReference(zipaAlanNsq.getClass(), zipaAlanNsq.getAlanNsq());
                TZipArchives.setZipaAlanNsq(zipaAlanNsq);
            }
            TBinaryContents zipaBinrNsq = TZipArchives.getZipaBinrNsq();
            if (zipaBinrNsq != null) {
                zipaBinrNsq = em.getReference(zipaBinrNsq.getClass(), zipaBinrNsq.getBinrNsq());
                TZipArchives.setZipaBinrNsq(zipaBinrNsq);
            }
            TChapterTitles zipaChtlNsq = TZipArchives.getZipaChtlNsq();
            if (zipaChtlNsq != null) {
                zipaChtlNsq = em.getReference(zipaChtlNsq.getClass(), zipaChtlNsq.getChtlNsq());
                TZipArchives.setZipaChtlNsq(zipaChtlNsq);
            }
            TDocumentLanguages zipaDlanNsq = TZipArchives.getZipaDlanNsq();
            if (zipaDlanNsq != null) {
                zipaDlanNsq = em.getReference(zipaDlanNsq.getClass(), zipaDlanNsq.getDlanNsq());
                TZipArchives.setZipaDlanNsq(zipaDlanNsq);
            }
            TDocumentStructures zipaDstrNsq = TZipArchives.getZipaDstrNsq();
            if (zipaDstrNsq != null) {
                zipaDstrNsq = em.getReference(zipaDstrNsq.getClass(), zipaDstrNsq.getDstrNsq());
                TZipArchives.setZipaDstrNsq(zipaDstrNsq);
            }
            TVersions zipaVersNsq = TZipArchives.getZipaVersNsq();
            if (zipaVersNsq != null) {
                zipaVersNsq = em.getReference(zipaVersNsq.getClass(), zipaVersNsq.getVersNsq());
                TZipArchives.setZipaVersNsq(zipaVersNsq);
            }
            em.persist(TZipArchives);
            if (zipaAlanNsq != null) {
                zipaAlanNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaAlanNsq = em.merge(zipaAlanNsq);
            }
            if (zipaBinrNsq != null) {
                zipaBinrNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaBinrNsq = em.merge(zipaBinrNsq);
            }
            if (zipaChtlNsq != null) {
                zipaChtlNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaChtlNsq = em.merge(zipaChtlNsq);
            }
            if (zipaDlanNsq != null) {
                zipaDlanNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaDlanNsq = em.merge(zipaDlanNsq);
            }
            if (zipaDstrNsq != null) {
                zipaDstrNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaDstrNsq = em.merge(zipaDstrNsq);
            }
            if (zipaVersNsq != null) {
                zipaVersNsq.getTZipArchivesCollection().add(TZipArchives);
                zipaVersNsq = em.merge(zipaVersNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTZipArchives(TZipArchives.getZipaNsq()) != null) {
                throw new PreexistingEntityException("TZipArchives " + TZipArchives + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TZipArchives TZipArchives) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TZipArchives persistentTZipArchives = em.find(TZipArchives.class, TZipArchives.getZipaNsq());
            TAnnexLanguages zipaAlanNsqOld = persistentTZipArchives.getZipaAlanNsq();
            TAnnexLanguages zipaAlanNsqNew = TZipArchives.getZipaAlanNsq();
            TBinaryContents zipaBinrNsqOld = persistentTZipArchives.getZipaBinrNsq();
            TBinaryContents zipaBinrNsqNew = TZipArchives.getZipaBinrNsq();
            TChapterTitles zipaChtlNsqOld = persistentTZipArchives.getZipaChtlNsq();
            TChapterTitles zipaChtlNsqNew = TZipArchives.getZipaChtlNsq();
            TDocumentLanguages zipaDlanNsqOld = persistentTZipArchives.getZipaDlanNsq();
            TDocumentLanguages zipaDlanNsqNew = TZipArchives.getZipaDlanNsq();
            TDocumentStructures zipaDstrNsqOld = persistentTZipArchives.getZipaDstrNsq();
            TDocumentStructures zipaDstrNsqNew = TZipArchives.getZipaDstrNsq();
            TVersions zipaVersNsqOld = persistentTZipArchives.getZipaVersNsq();
            TVersions zipaVersNsqNew = TZipArchives.getZipaVersNsq();
            if (zipaAlanNsqNew != null) {
                zipaAlanNsqNew = em.getReference(zipaAlanNsqNew.getClass(), zipaAlanNsqNew.getAlanNsq());
                TZipArchives.setZipaAlanNsq(zipaAlanNsqNew);
            }
            if (zipaBinrNsqNew != null) {
                zipaBinrNsqNew = em.getReference(zipaBinrNsqNew.getClass(), zipaBinrNsqNew.getBinrNsq());
                TZipArchives.setZipaBinrNsq(zipaBinrNsqNew);
            }
            if (zipaChtlNsqNew != null) {
                zipaChtlNsqNew = em.getReference(zipaChtlNsqNew.getClass(), zipaChtlNsqNew.getChtlNsq());
                TZipArchives.setZipaChtlNsq(zipaChtlNsqNew);
            }
            if (zipaDlanNsqNew != null) {
                zipaDlanNsqNew = em.getReference(zipaDlanNsqNew.getClass(), zipaDlanNsqNew.getDlanNsq());
                TZipArchives.setZipaDlanNsq(zipaDlanNsqNew);
            }
            if (zipaDstrNsqNew != null) {
                zipaDstrNsqNew = em.getReference(zipaDstrNsqNew.getClass(), zipaDstrNsqNew.getDstrNsq());
                TZipArchives.setZipaDstrNsq(zipaDstrNsqNew);
            }
            if (zipaVersNsqNew != null) {
                zipaVersNsqNew = em.getReference(zipaVersNsqNew.getClass(), zipaVersNsqNew.getVersNsq());
                TZipArchives.setZipaVersNsq(zipaVersNsqNew);
            }
            TZipArchives = em.merge(TZipArchives);
            if (zipaAlanNsqOld != null && !zipaAlanNsqOld.equals(zipaAlanNsqNew)) {
                zipaAlanNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaAlanNsqOld = em.merge(zipaAlanNsqOld);
            }
            if (zipaAlanNsqNew != null && !zipaAlanNsqNew.equals(zipaAlanNsqOld)) {
                zipaAlanNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaAlanNsqNew = em.merge(zipaAlanNsqNew);
            }
            if (zipaBinrNsqOld != null && !zipaBinrNsqOld.equals(zipaBinrNsqNew)) {
                zipaBinrNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaBinrNsqOld = em.merge(zipaBinrNsqOld);
            }
            if (zipaBinrNsqNew != null && !zipaBinrNsqNew.equals(zipaBinrNsqOld)) {
                zipaBinrNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaBinrNsqNew = em.merge(zipaBinrNsqNew);
            }
            if (zipaChtlNsqOld != null && !zipaChtlNsqOld.equals(zipaChtlNsqNew)) {
                zipaChtlNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaChtlNsqOld = em.merge(zipaChtlNsqOld);
            }
            if (zipaChtlNsqNew != null && !zipaChtlNsqNew.equals(zipaChtlNsqOld)) {
                zipaChtlNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaChtlNsqNew = em.merge(zipaChtlNsqNew);
            }
            if (zipaDlanNsqOld != null && !zipaDlanNsqOld.equals(zipaDlanNsqNew)) {
                zipaDlanNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaDlanNsqOld = em.merge(zipaDlanNsqOld);
            }
            if (zipaDlanNsqNew != null && !zipaDlanNsqNew.equals(zipaDlanNsqOld)) {
                zipaDlanNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaDlanNsqNew = em.merge(zipaDlanNsqNew);
            }
            if (zipaDstrNsqOld != null && !zipaDstrNsqOld.equals(zipaDstrNsqNew)) {
                zipaDstrNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaDstrNsqOld = em.merge(zipaDstrNsqOld);
            }
            if (zipaDstrNsqNew != null && !zipaDstrNsqNew.equals(zipaDstrNsqOld)) {
                zipaDstrNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaDstrNsqNew = em.merge(zipaDstrNsqNew);
            }
            if (zipaVersNsqOld != null && !zipaVersNsqOld.equals(zipaVersNsqNew)) {
                zipaVersNsqOld.getTZipArchivesCollection().remove(TZipArchives);
                zipaVersNsqOld = em.merge(zipaVersNsqOld);
            }
            if (zipaVersNsqNew != null && !zipaVersNsqNew.equals(zipaVersNsqOld)) {
                zipaVersNsqNew.getTZipArchivesCollection().add(TZipArchives);
                zipaVersNsqNew = em.merge(zipaVersNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TZipArchives.getZipaNsq();
                if (findTZipArchives(id) == null) {
                    throw new NonexistentEntityException("The tZipArchives with id " + id + " no longer exists.");
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
            TZipArchives TZipArchives;
            try {
                TZipArchives = em.getReference(TZipArchives.class, id);
                TZipArchives.getZipaNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TZipArchives with id " + id + " no longer exists.", enfe);
            }
            TAnnexLanguages zipaAlanNsq = TZipArchives.getZipaAlanNsq();
            if (zipaAlanNsq != null) {
                zipaAlanNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaAlanNsq = em.merge(zipaAlanNsq);
            }
            TBinaryContents zipaBinrNsq = TZipArchives.getZipaBinrNsq();
            if (zipaBinrNsq != null) {
                zipaBinrNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaBinrNsq = em.merge(zipaBinrNsq);
            }
            TChapterTitles zipaChtlNsq = TZipArchives.getZipaChtlNsq();
            if (zipaChtlNsq != null) {
                zipaChtlNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaChtlNsq = em.merge(zipaChtlNsq);
            }
            TDocumentLanguages zipaDlanNsq = TZipArchives.getZipaDlanNsq();
            if (zipaDlanNsq != null) {
                zipaDlanNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaDlanNsq = em.merge(zipaDlanNsq);
            }
            TDocumentStructures zipaDstrNsq = TZipArchives.getZipaDstrNsq();
            if (zipaDstrNsq != null) {
                zipaDstrNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaDstrNsq = em.merge(zipaDstrNsq);
            }
            TVersions zipaVersNsq = TZipArchives.getZipaVersNsq();
            if (zipaVersNsq != null) {
                zipaVersNsq.getTZipArchivesCollection().remove(TZipArchives);
                zipaVersNsq = em.merge(zipaVersNsq);
            }
            em.remove(TZipArchives);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TZipArchives> findTZipArchivesEntities() {
        return findTZipArchivesEntities(true, -1, -1);
    }

    public List<TZipArchives> findTZipArchivesEntities(int maxResults, int firstResult) {
        return findTZipArchivesEntities(false, maxResults, firstResult);
    }

    private List<TZipArchives> findTZipArchivesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TZipArchives.class));
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

    public TZipArchives findTZipArchives(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TZipArchives.class, id);
        } finally {
            em.close();
        }
    }

    public int getTZipArchivesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TZipArchives> rt = cq.from(TZipArchives.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
