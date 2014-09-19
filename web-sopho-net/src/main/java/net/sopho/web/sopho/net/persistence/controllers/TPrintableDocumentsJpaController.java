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
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TDocuments;
import net.sopho.web.sopho.net.persistence.entities.TDocumentLanguages;
import net.sopho.web.sopho.net.persistence.entities.TPrintableDocuments;
import net.sopho.web.sopho.net.persistence.entities.TVersions;

/**
 *
 * @author LU01007
 */
public class TPrintableDocumentsJpaController implements Serializable {

    public TPrintableDocumentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TPrintableDocuments TPrintableDocuments) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBinaryContents pdocBinrNsq = TPrintableDocuments.getPdocBinrNsq();
            if (pdocBinrNsq != null) {
                pdocBinrNsq = em.getReference(pdocBinrNsq.getClass(), pdocBinrNsq.getBinrNsq());
                TPrintableDocuments.setPdocBinrNsq(pdocBinrNsq);
            }
            TDocuments pdocDocuNsq = TPrintableDocuments.getPdocDocuNsq();
            if (pdocDocuNsq != null) {
                pdocDocuNsq = em.getReference(pdocDocuNsq.getClass(), pdocDocuNsq.getDocuNsq());
                TPrintableDocuments.setPdocDocuNsq(pdocDocuNsq);
            }
            TDocumentLanguages pdocDlanNsq = TPrintableDocuments.getPdocDlanNsq();
            if (pdocDlanNsq != null) {
                pdocDlanNsq = em.getReference(pdocDlanNsq.getClass(), pdocDlanNsq.getDlanNsq());
                TPrintableDocuments.setPdocDlanNsq(pdocDlanNsq);
            }
            TVersions pdocVersNsq = TPrintableDocuments.getPdocVersNsq();
            if (pdocVersNsq != null) {
                pdocVersNsq = em.getReference(pdocVersNsq.getClass(), pdocVersNsq.getVersNsq());
                TPrintableDocuments.setPdocVersNsq(pdocVersNsq);
            }
            em.persist(TPrintableDocuments);
            if (pdocBinrNsq != null) {
                pdocBinrNsq.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocBinrNsq = em.merge(pdocBinrNsq);
            }
            if (pdocDocuNsq != null) {
                pdocDocuNsq.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocDocuNsq = em.merge(pdocDocuNsq);
            }
            if (pdocDlanNsq != null) {
                pdocDlanNsq.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocDlanNsq = em.merge(pdocDlanNsq);
            }
            if (pdocVersNsq != null) {
                pdocVersNsq.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocVersNsq = em.merge(pdocVersNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTPrintableDocuments(TPrintableDocuments.getPdocNsq()) != null) {
                throw new PreexistingEntityException("TPrintableDocuments " + TPrintableDocuments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TPrintableDocuments TPrintableDocuments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TPrintableDocuments persistentTPrintableDocuments = em.find(TPrintableDocuments.class, TPrintableDocuments.getPdocNsq());
            TBinaryContents pdocBinrNsqOld = persistentTPrintableDocuments.getPdocBinrNsq();
            TBinaryContents pdocBinrNsqNew = TPrintableDocuments.getPdocBinrNsq();
            TDocuments pdocDocuNsqOld = persistentTPrintableDocuments.getPdocDocuNsq();
            TDocuments pdocDocuNsqNew = TPrintableDocuments.getPdocDocuNsq();
            TDocumentLanguages pdocDlanNsqOld = persistentTPrintableDocuments.getPdocDlanNsq();
            TDocumentLanguages pdocDlanNsqNew = TPrintableDocuments.getPdocDlanNsq();
            TVersions pdocVersNsqOld = persistentTPrintableDocuments.getPdocVersNsq();
            TVersions pdocVersNsqNew = TPrintableDocuments.getPdocVersNsq();
            if (pdocBinrNsqNew != null) {
                pdocBinrNsqNew = em.getReference(pdocBinrNsqNew.getClass(), pdocBinrNsqNew.getBinrNsq());
                TPrintableDocuments.setPdocBinrNsq(pdocBinrNsqNew);
            }
            if (pdocDocuNsqNew != null) {
                pdocDocuNsqNew = em.getReference(pdocDocuNsqNew.getClass(), pdocDocuNsqNew.getDocuNsq());
                TPrintableDocuments.setPdocDocuNsq(pdocDocuNsqNew);
            }
            if (pdocDlanNsqNew != null) {
                pdocDlanNsqNew = em.getReference(pdocDlanNsqNew.getClass(), pdocDlanNsqNew.getDlanNsq());
                TPrintableDocuments.setPdocDlanNsq(pdocDlanNsqNew);
            }
            if (pdocVersNsqNew != null) {
                pdocVersNsqNew = em.getReference(pdocVersNsqNew.getClass(), pdocVersNsqNew.getVersNsq());
                TPrintableDocuments.setPdocVersNsq(pdocVersNsqNew);
            }
            TPrintableDocuments = em.merge(TPrintableDocuments);
            if (pdocBinrNsqOld != null && !pdocBinrNsqOld.equals(pdocBinrNsqNew)) {
                pdocBinrNsqOld.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocBinrNsqOld = em.merge(pdocBinrNsqOld);
            }
            if (pdocBinrNsqNew != null && !pdocBinrNsqNew.equals(pdocBinrNsqOld)) {
                pdocBinrNsqNew.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocBinrNsqNew = em.merge(pdocBinrNsqNew);
            }
            if (pdocDocuNsqOld != null && !pdocDocuNsqOld.equals(pdocDocuNsqNew)) {
                pdocDocuNsqOld.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocDocuNsqOld = em.merge(pdocDocuNsqOld);
            }
            if (pdocDocuNsqNew != null && !pdocDocuNsqNew.equals(pdocDocuNsqOld)) {
                pdocDocuNsqNew.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocDocuNsqNew = em.merge(pdocDocuNsqNew);
            }
            if (pdocDlanNsqOld != null && !pdocDlanNsqOld.equals(pdocDlanNsqNew)) {
                pdocDlanNsqOld.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocDlanNsqOld = em.merge(pdocDlanNsqOld);
            }
            if (pdocDlanNsqNew != null && !pdocDlanNsqNew.equals(pdocDlanNsqOld)) {
                pdocDlanNsqNew.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocDlanNsqNew = em.merge(pdocDlanNsqNew);
            }
            if (pdocVersNsqOld != null && !pdocVersNsqOld.equals(pdocVersNsqNew)) {
                pdocVersNsqOld.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocVersNsqOld = em.merge(pdocVersNsqOld);
            }
            if (pdocVersNsqNew != null && !pdocVersNsqNew.equals(pdocVersNsqOld)) {
                pdocVersNsqNew.getTPrintableDocumentsCollection().add(TPrintableDocuments);
                pdocVersNsqNew = em.merge(pdocVersNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TPrintableDocuments.getPdocNsq();
                if (findTPrintableDocuments(id) == null) {
                    throw new NonexistentEntityException("The tPrintableDocuments with id " + id + " no longer exists.");
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
            TPrintableDocuments TPrintableDocuments;
            try {
                TPrintableDocuments = em.getReference(TPrintableDocuments.class, id);
                TPrintableDocuments.getPdocNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TPrintableDocuments with id " + id + " no longer exists.", enfe);
            }
            TBinaryContents pdocBinrNsq = TPrintableDocuments.getPdocBinrNsq();
            if (pdocBinrNsq != null) {
                pdocBinrNsq.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocBinrNsq = em.merge(pdocBinrNsq);
            }
            TDocuments pdocDocuNsq = TPrintableDocuments.getPdocDocuNsq();
            if (pdocDocuNsq != null) {
                pdocDocuNsq.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocDocuNsq = em.merge(pdocDocuNsq);
            }
            TDocumentLanguages pdocDlanNsq = TPrintableDocuments.getPdocDlanNsq();
            if (pdocDlanNsq != null) {
                pdocDlanNsq.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocDlanNsq = em.merge(pdocDlanNsq);
            }
            TVersions pdocVersNsq = TPrintableDocuments.getPdocVersNsq();
            if (pdocVersNsq != null) {
                pdocVersNsq.getTPrintableDocumentsCollection().remove(TPrintableDocuments);
                pdocVersNsq = em.merge(pdocVersNsq);
            }
            em.remove(TPrintableDocuments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TPrintableDocuments> findTPrintableDocumentsEntities() {
        return findTPrintableDocumentsEntities(true, -1, -1);
    }

    public List<TPrintableDocuments> findTPrintableDocumentsEntities(int maxResults, int firstResult) {
        return findTPrintableDocumentsEntities(false, maxResults, firstResult);
    }

    private List<TPrintableDocuments> findTPrintableDocumentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TPrintableDocuments.class));
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

    public TPrintableDocuments findTPrintableDocuments(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TPrintableDocuments.class, id);
        } finally {
            em.close();
        }
    }

    public int getTPrintableDocumentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TPrintableDocuments> rt = cq.from(TPrintableDocuments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
