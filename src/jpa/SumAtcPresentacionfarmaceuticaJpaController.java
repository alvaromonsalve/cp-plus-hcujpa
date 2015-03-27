/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.SumAtcPresentacionfarmaceutica;
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
 * @author Alvaro Monsalve
 */
public class SumAtcPresentacionfarmaceuticaJpaController implements Serializable {

    public SumAtcPresentacionfarmaceuticaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcPresentacionfarmaceutica sumAtcPresentacionfarmaceutica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sumAtcPresentacionfarmaceutica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcPresentacionfarmaceutica sumAtcPresentacionfarmaceutica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sumAtcPresentacionfarmaceutica = em.merge(sumAtcPresentacionfarmaceutica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcPresentacionfarmaceutica.getId();
                if (findSumAtcPresentacionfarmaceutica(id) == null) {
                    throw new NonexistentEntityException("The sumAtcPresentacionfarmaceutica with id " + id + " no longer exists.");
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
            SumAtcPresentacionfarmaceutica sumAtcPresentacionfarmaceutica;
            try {
                sumAtcPresentacionfarmaceutica = em.getReference(SumAtcPresentacionfarmaceutica.class, id);
                sumAtcPresentacionfarmaceutica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcPresentacionfarmaceutica with id " + id + " no longer exists.", enfe);
            }
            em.remove(sumAtcPresentacionfarmaceutica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcPresentacionfarmaceutica> findSumAtcPresentacionfarmaceuticaEntities() {
        return findSumAtcPresentacionfarmaceuticaEntities(true, -1, -1);
    }

    public List<SumAtcPresentacionfarmaceutica> findSumAtcPresentacionfarmaceuticaEntities(int maxResults, int firstResult) {
        return findSumAtcPresentacionfarmaceuticaEntities(false, maxResults, firstResult);
    }

    private List<SumAtcPresentacionfarmaceutica> findSumAtcPresentacionfarmaceuticaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcPresentacionfarmaceutica.class));
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

    public SumAtcPresentacionfarmaceutica findSumAtcPresentacionfarmaceutica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcPresentacionfarmaceutica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcPresentacionfarmaceuticaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcPresentacionfarmaceutica> rt = cq.from(SumAtcPresentacionfarmaceutica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
