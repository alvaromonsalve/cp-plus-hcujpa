/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeExpFisica;
import entidades_EJB.CeHistoriac;
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
public class CeExpFisicaJpaController implements Serializable {

    public CeExpFisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeExpFisica ceExpFisica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceExpFisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeExpFisica ceExpFisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceExpFisica = em.merge(ceExpFisica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceExpFisica.getId();
                if (findCeExpFisica(id) == null) {
                    throw new NonexistentEntityException("The ceExpFisica with id " + id + " no longer exists.");
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
            CeExpFisica ceExpFisica;
            try {
                ceExpFisica = em.getReference(CeExpFisica.class, id);
                ceExpFisica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceExpFisica with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceExpFisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeExpFisica> findCeExpFisicaEntities() {
        return findCeExpFisicaEntities(true, -1, -1);
    }

    public List<CeExpFisica> findCeExpFisicaEntities(int maxResults, int firstResult) {
        return findCeExpFisicaEntities(false, maxResults, firstResult);
    }

    private List<CeExpFisica> findCeExpFisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeExpFisica.class));
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

    public CeExpFisica findCeExpFisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeExpFisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeExpFisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeExpFisica> rt = cq.from(CeExpFisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Object countExploracion(int h) {
        EntityManager em = getEntityManager();
        Query Q = em.createQuery("SELECT COUNT(e) FROM CeExpFisica e WHERE e.idHistoriace=:h AND e.estado='1'");
        Q.setParameter("hi", h);
        return Q.getSingleResult();
    }

    public CeExpFisica getEntidadExploracion(CeHistoriac his) {
        CeExpFisica ex = null;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT ex FROM CeExpFisica ex WHERE (ex.idHistoriace=:h AND ex.estado='1')");
        Q.setParameter("h", his);
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            ex = (CeExpFisica) results.get(0);
        } else {
            ex = null;
        }
        return ex;
    }

}
