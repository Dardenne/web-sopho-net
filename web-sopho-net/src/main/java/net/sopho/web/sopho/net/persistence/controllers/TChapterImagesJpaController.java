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
import net.sopho.web.sopho.net.persistence.entities.TChapterContents;
import net.sopho.web.sopho.net.persistence.entities.TChapterImages;

/**
 *
 * @author LU01007
 */
public class TChapterImagesJpaController implements Serializable {

    public TChapterImagesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TChapterImages TChapterImages) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TBinaryContents chimBinrNsq = TChapterImages.getChimBinrNsq();
            if (chimBinrNsq != null) {
                chimBinrNsq = em.getReference(chimBinrNsq.getClass(), chimBinrNsq.getBinrNsq());
                TChapterImages.setChimBinrNsq(chimBinrNsq);
            }
            TChapterContents chimChcoNsq = TChapterImages.getChimChcoNsq();
            if (chimChcoNsq != null) {
                chimChcoNsq = em.getReference(chimChcoNsq.getClass(), chimChcoNsq.getChcoNsq());
                TChapterImages.setChimChcoNsq(chimChcoNsq);
            }
            em.persist(TChapterImages);
            if (chimBinrNsq != null) {
                chimBinrNsq.getTChapterImagesCollection().add(TChapterImages);
                chimBinrNsq = em.merge(chimBinrNsq);
            }
            if (chimChcoNsq != null) {
                chimChcoNsq.getTChapterImagesCollection().add(TChapterImages);
                chimChcoNsq = em.merge(chimChcoNsq);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTChapterImages(TChapterImages.getChimNsq()) != null) {
                throw new PreexistingEntityException("TChapterImages " + TChapterImages + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TChapterImages TChapterImages) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TChapterImages persistentTChapterImages = em.find(TChapterImages.class, TChapterImages.getChimNsq());
            TBinaryContents chimBinrNsqOld = persistentTChapterImages.getChimBinrNsq();
            TBinaryContents chimBinrNsqNew = TChapterImages.getChimBinrNsq();
            TChapterContents chimChcoNsqOld = persistentTChapterImages.getChimChcoNsq();
            TChapterContents chimChcoNsqNew = TChapterImages.getChimChcoNsq();
            if (chimBinrNsqNew != null) {
                chimBinrNsqNew = em.getReference(chimBinrNsqNew.getClass(), chimBinrNsqNew.getBinrNsq());
                TChapterImages.setChimBinrNsq(chimBinrNsqNew);
            }
            if (chimChcoNsqNew != null) {
                chimChcoNsqNew = em.getReference(chimChcoNsqNew.getClass(), chimChcoNsqNew.getChcoNsq());
                TChapterImages.setChimChcoNsq(chimChcoNsqNew);
            }
            TChapterImages = em.merge(TChapterImages);
            if (chimBinrNsqOld != null && !chimBinrNsqOld.equals(chimBinrNsqNew)) {
                chimBinrNsqOld.getTChapterImagesCollection().remove(TChapterImages);
                chimBinrNsqOld = em.merge(chimBinrNsqOld);
            }
            if (chimBinrNsqNew != null && !chimBinrNsqNew.equals(chimBinrNsqOld)) {
                chimBinrNsqNew.getTChapterImagesCollection().add(TChapterImages);
                chimBinrNsqNew = em.merge(chimBinrNsqNew);
            }
            if (chimChcoNsqOld != null && !chimChcoNsqOld.equals(chimChcoNsqNew)) {
                chimChcoNsqOld.getTChapterImagesCollection().remove(TChapterImages);
                chimChcoNsqOld = em.merge(chimChcoNsqOld);
            }
            if (chimChcoNsqNew != null && !chimChcoNsqNew.equals(chimChcoNsqOld)) {
                chimChcoNsqNew.getTChapterImagesCollection().add(TChapterImages);
                chimChcoNsqNew = em.merge(chimChcoNsqNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TChapterImages.getChimNsq();
                if (findTChapterImages(id) == null) {
                    throw new NonexistentEntityException("The tChapterImages with id " + id + " no longer exists.");
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
            TChapterImages TChapterImages;
            try {
                TChapterImages = em.getReference(TChapterImages.class, id);
                TChapterImages.getChimNsq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TChapterImages with id " + id + " no longer exists.", enfe);
            }
            TBinaryContents chimBinrNsq = TChapterImages.getChimBinrNsq();
            if (chimBinrNsq != null) {
                chimBinrNsq.getTChapterImagesCollection().remove(TChapterImages);
                chimBinrNsq = em.merge(chimBinrNsq);
            }
            TChapterContents chimChcoNsq = TChapterImages.getChimChcoNsq();
            if (chimChcoNsq != null) {
                chimChcoNsq.getTChapterImagesCollection().remove(TChapterImages);
                chimChcoNsq = em.merge(chimChcoNsq);
            }
            em.remove(TChapterImages);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TChapterImages> findTChapterImagesEntities() {
        return findTChapterImagesEntities(true, -1, -1);
    }

    public List<TChapterImages> findTChapterImagesEntities(int maxResults, int firstResult) {
        return findTChapterImagesEntities(false, maxResults, firstResult);
    }

    private List<TChapterImages> findTChapterImagesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TChapterImages.class));
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

    public TChapterImages findTChapterImages(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TChapterImages.class, id);
        } finally {
            em.close();
        }
    }

    public int getTChapterImagesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TChapterImages> rt = cq.from(TChapterImages.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
