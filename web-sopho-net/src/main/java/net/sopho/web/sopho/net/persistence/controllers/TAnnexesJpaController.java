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
import net.sopho.web.sopho.net.persistence.entities.TAnnexGroups;
import net.sopho.web.sopho.net.persistence.entities.TAnnexLanguages;
import net.sopho.web.sopho.net.persistence.entities.TAnnexes;
import net.sopho.web.sopho.net.persistence.entities.TBinaryContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterTitles;

/**
 *
 * @author LU01007
 */
public class TAnnexesJpaController implements Serializable {

    public TAnnexesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TAnnexes TAnnexes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexGroups anexAgrpNsq = TAnnexes.getAnexAgrpNsq();
            if (anexAgrpNsq != null) {
                anexAgrpNsq = em.getReference(anexAgrpNsq.getClass(), anexAgrpNsq.getAgrpNsq());
                TAnnexes.setAnexAgrpNsq(anexAgrpNsq);
            }
            TAnnexLanguages anexAlanNsq = TAnnexes.getAnexAlanNsq();
            if (anexAlanNsq != null) {
                anexAlanNsq = em.getReference(anexAlanNsq.getClass(), anexAlanNsq.getAlanNsq());
                TAnnexes.setAnexAlanNsq(anexAlanNsq);
            }
            TBinaryContents anexBinrNsq = TAnnexes.getAnexBinrNsq();
            if (anexBinrNsq != null) {
                anexBinrNsq = em.getReference(anexBinrNsq.getClass(), anexBinrNsq.getBinrNsq());
                TAnnexes.setAnexBinrNsq(anexBinrNsq);
            }
            TChapterTitles anexChtlNsq = TAnnexes.getAnexChtlNsq();
            if (anexChtlNsq != null) {
                anexChtlNsq = em.getReference(anexChtlNsq.getClass(), anexChtlNsq.getChtlNsq());
                TAnnexes.setAnexChtlNsq(anexChtlNsq);
            }
            em.persist(TAnnexes);
            if (anexAgrpNsq != null) {
                anexAgrpNsq.getTAnnexesCollection().add(TAnnexes);
                anexAgrpNsq = em.merge(anexAgrpNsq);
            }
            if (anexAlanNsq != null) {
                anexAlanNsq.getTAnnexesCollection().add(TAnnexes);
                anexAlanNsq = em.merge(anexAlanNsq);
            }
            if (anexBinrNsq != null) {
                anexBinrNsq.getTAnnexesCollection().add(TAnnexes);
                anexBinrNsq = em.merge(anexBinrNsq);
            }
            if (anexChtlNsq != null) {
                anexChtlNsq.getTAnnexesCollection().add(TAnnexes);
                anexChtlNsq = em.merge(anexChtlNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTAnnexes(TAnnexes.getAnexNsq()) != null) {
                throw new PreexistingEntityException("TAnnexes " + TAnnexes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TAnnexes TAnnexes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TAnnexes persistentTAnnexes = em.find(TAnnexes.class, TAnnexes.getAnexNsq());
            TAnnexGroups anexAgrpNsqOld = persistentTAnnexes.getAnexAgrpNsq();
            TAnnexGroups anexAgrpNsqNew = TAnnexes.getAnexAgrpNsq();
            TAnnexLanguages anexAlanNsqOld = persistentTAnnexes.getAnexAlanNsq();
            TAnnexLanguages anexAlanNsqNew = TAnnexes.getAnexAlanNsq();
            TBinaryContents anexBinrNsqOld = persistentTAnnexes.getAnexBinrNsq();
            TBinaryContents anexBinrNsqNew = TAnnexes.getAnexBinrNsq();
            TChapterTitles anexChtlNsqOld = persistentTAnnexes.getAnexChtlNsq();
            TChapterTitles anexChtlNsqNew = TAnnexes.getAnexChtlNsq();
            if (anexAgrpNsqNew != null) {
                anexAgrpNsqNew = em.getReference(anexAgrpNsqNew.getClass(), anexAgrpNsqNew.getAgrpNsq());
                TAnnexes.setAnexAgrpNsq(anexAgrpNsqNew);
            }
            if (anexAlanNsqNew != null) {
                anexAlanNsqNew = em.getReference(anexAlanNsqNew.getClass(), anexAlanNsqNew.getAlanNsq());
                TAnnexes.setAnexAlanNsq(anexAlanNsqNew);
            }
            if (anexBinrNsqNew != null) {
                anexBinrNsqNew = em.getReference(anexBinrNsqNew.getClass(), anexBinrNsqNew.getBinrNsq());
                TAnnexes.setAnexBinrNsq(anexBinrNsqNew);
            }
            if (anexChtlNsqNew != null) {
                anexChtlNsqNew = em.getReference(anexChtlNsqNew.getClass(), anexChtlNsqNew.getChtlNsq());
                TAnnexes.setAnexChtlNsq(anexChtlNsqNew);
            }
            TAnnexes = em.merge(TAnnexes);
            if (anexAgrpNsqOld != null && !anexAgrpNsqOld.equals(anexAgrpNsqNew)) {
                anexAgrpNsqOld.getTAnnexesCollection().remove(TAnnexes);
                anexAgrpNsqOld = em.merge(anexAgrpNsqOld);
            }
            if (anexAgrpNsqNew != null && !anexAgrpNsqNew.equals(anexAgrpNsqOld)) {
                anexAgrpNsqNew.getTAnnexesCollection().add(TAnnexes);
                anexAgrpNsqNew = em.merge(anexAgrpNsqNew);
            }
            if (anexAlanNsqOld != null && !anexAlanNsqOld.equals(anexAlanNsqNew)) {
                anexAlanNsqOld.getTAnnexesCollection().remove(TAnnexes);
                anexAlanNsqOld = em.merge(anexAlanNsqOld);
            }
            if (anexAlanNsqNew != null && !anexAlanNsqNew.equals(anexAlanNsqOld)) {
                anexAlanNsqNew.getTAnnexesCollection().add(TAnnexes);
                anexAlanNsqNew = em.merge(anexAlanNsqNew);
            }
            if (anexBinrNsqOld != null && !anexBinrNsqOld.equals(anexBinrNsqNew)) {
                anexBinrNsqOld.getTAnnexesCollection().remove(TAnnexes);
                anexBinrNsqOld = em.merge(anexBinrNsqOld);
            }
            if (anexBinrNsqNew != null && !anexBinrNsqNew.equals(anexBinrNsqOld)) {
                anexBinrNsqNew.getTAnnexesCollection().add(TAnnexes);
                anexBinrNsqNew = em.merge(anexBinrNsqNew);
            }
            if (anexChtlNsqOld != null && !anexChtlNsqOld.equals(anexChtlNsqNew)) {
                anexChtlNsqOld.getTAnnexesCollection().remove(TAnnexes);
                anexChtlNsqOld = em.merge(anexChtlNsqOld);
            }
            if (anexChtlNsqNew != null && !anexChtlNsqNew.equals(anexChtlNsqOld)) {
                anexChtlNsqNew.getTAnnexesCollection().add(TAnnexes);
                anexChtlNsqNew = em.merge(anexChtlNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TAnnexes.getAnexNsq();
                if (findTAnnexes(id) == null) {
                    throw new NonexistentEntityException("The tAnnexes with id " + id + " no longer exists.");
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
            TAnnexes TAnnexes;
            try {
                TAnnexes = em.getReference(TAnnexes.class, id);
                TAnnexes.getAnexNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TAnnexes with id " + id + " no longer exists.", enfe);
            }
            TAnnexGroups anexAgrpNsq = TAnnexes.getAnexAgrpNsq();
            if (anexAgrpNsq != null) {
                anexAgrpNsq.getTAnnexesCollection().remove(TAnnexes);
                anexAgrpNsq = em.merge(anexAgrpNsq);
            }
            TAnnexLanguages anexAlanNsq = TAnnexes.getAnexAlanNsq();
            if (anexAlanNsq != null) {
                anexAlanNsq.getTAnnexesCollection().remove(TAnnexes);
                anexAlanNsq = em.merge(anexAlanNsq);
            }
            TBinaryContents anexBinrNsq = TAnnexes.getAnexBinrNsq();
            if (anexBinrNsq != null) {
                anexBinrNsq.getTAnnexesCollection().remove(TAnnexes);
                anexBinrNsq = em.merge(anexBinrNsq);
            }
            TChapterTitles anexChtlNsq = TAnnexes.getAnexChtlNsq();
            if (anexChtlNsq != null) {
                anexChtlNsq.getTAnnexesCollection().remove(TAnnexes);
                anexChtlNsq = em.merge(anexChtlNsq);
            }
            em.remove(TAnnexes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TAnnexes> findTAnnexesEntities() {
        return findTAnnexesEntities(true, -1, -1);
    }

    public List<TAnnexes> findTAnnexesEntities(int maxResults, int firstResult) {
        return findTAnnexesEntities(false, maxResults, firstResult);
    }

    private List<TAnnexes> findTAnnexesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TAnnexes.class));
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

    public TAnnexes findTAnnexes(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TAnnexes.class, id);
        } finally {
            em.close();
        }
    }

    public int getTAnnexesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TAnnexes> rt = cq.from(TAnnexes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
