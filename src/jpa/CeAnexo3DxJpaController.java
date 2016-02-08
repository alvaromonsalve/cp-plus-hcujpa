/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeAnexo3Dx;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeAnexo3DxJpaController implements Serializable {

    public CeAnexo3DxJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeAnexo3Dx ceAnexo3Dx) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceAnexo3Dx);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeAnexo3Dx ceAnexo3Dx) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceAnexo3Dx = em.merge(ceAnexo3Dx);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceAnexo3Dx.getId();
                if (findCeAnexo3Dx(id) == null) {
                    throw new NonexistentEntityException("The ceAnexo3Dx with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeAnexo3Dx ceAnexo3Dx;
            try {
                ceAnexo3Dx = em.getReference(CeAnexo3Dx.class, id);
                ceAnexo3Dx.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceAnexo3Dx with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceAnexo3Dx);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeAnexo3Dx> findCeAnexo3DxEntities() {
        return findCeAnexo3DxEntities(true, -1, -1);
    }

    public List<CeAnexo3Dx> findCeAnexo3DxEntities(int maxResults, int firstResult) {
        return findCeAnexo3DxEntities(false, maxResults, firstResult);
    }

    private List<CeAnexo3Dx> findCeAnexo3DxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeAnexo3Dx.class));
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

    public CeAnexo3Dx findCeAnexo3Dx(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeAnexo3Dx.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeAnexo3DxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeAnexo3Dx> rt = cq.from(CeAnexo3Dx.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
