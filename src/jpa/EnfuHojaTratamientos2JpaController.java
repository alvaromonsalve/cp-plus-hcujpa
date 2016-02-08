/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuHojaTratamientos2;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuHojaTratamientos2JpaController implements Serializable {

    public EnfuHojaTratamientos2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuHojaTratamientos2 enfuHojaTratamientos2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuHojaTratamientos2 enfuHojaTratamientos2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuHojaTratamientos2 = em.merge(enfuHojaTratamientos2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuHojaTratamientos2.getId();
                if (findEnfuHojaTratamientos2(id) == null) {
                    throw new NonexistentEntityException("The enfuHojaTratamientos2 with id " + id + " no longer exists.");
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
            EnfuHojaTratamientos2 enfuHojaTratamientos2;
            try {
                enfuHojaTratamientos2 = em.getReference(EnfuHojaTratamientos2.class, id);
                enfuHojaTratamientos2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuHojaTratamientos2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuHojaTratamientos2> findEnfuHojaTratamientos2Entities() {
        return findEnfuHojaTratamientos2Entities(true, -1, -1);
    }

    public List<EnfuHojaTratamientos2> findEnfuHojaTratamientos2Entities(int maxResults, int firstResult) {
        return findEnfuHojaTratamientos2Entities(false, maxResults, firstResult);
    }

    private List<EnfuHojaTratamientos2> findEnfuHojaTratamientos2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuHojaTratamientos2.class));
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

    public EnfuHojaTratamientos2 findEnfuHojaTratamientos2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuHojaTratamientos2.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuHojaTratamientos2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuHojaTratamientos2> rt = cq.from(EnfuHojaTratamientos2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuHojaTratamientos2> getTratamientos2(int h, int e, int i) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT t FROM EnfuHojaTratamientos2 t WHERE t.idHistoria.id=:hist AND t.idIngOEvo=:ev AND t.identificador=:ident AND t.estado='1' ");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("hist", h);
        Q.setParameter("ev", e);
        Q.setParameter("ident", i);
        return Q.getResultList();
    }

    public Object countHojaTratamiento2(int h) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT COUNT(N) FROM EnfuHojaTratamientos2 N WHERE N.idHistoria.id=:ht AND N.estado='1'");
            q.setParameter("ht", h);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
