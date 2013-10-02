/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.StaticEspecialidades;
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
public class StaticEspecialidadesJpaController implements Serializable {

    public StaticEspecialidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StaticEspecialidades staticEspecialidades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(staticEspecialidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StaticEspecialidades staticEspecialidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            staticEspecialidades = em.merge(staticEspecialidades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = staticEspecialidades.getId();
                if (findStaticEspecialidades(id) == null) {
                    throw new NonexistentEntityException("The staticEspecialidades with id " + id + " no longer exists.");
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
            StaticEspecialidades staticEspecialidades;
            try {
                staticEspecialidades = em.getReference(StaticEspecialidades.class, id);
                staticEspecialidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The staticEspecialidades with id " + id + " no longer exists.", enfe);
            }
            em.remove(staticEspecialidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StaticEspecialidades> findStaticEspecialidadesEntities() {
        return findStaticEspecialidadesEntities(true, -1, -1);
    }

    public List<StaticEspecialidades> findStaticEspecialidadesEntities(int maxResults, int firstResult) {
        return findStaticEspecialidadesEntities(false, maxResults, firstResult);
    }

    private List<StaticEspecialidades> findStaticEspecialidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StaticEspecialidades.class));
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

    public StaticEspecialidades findStaticEspecialidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StaticEspecialidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getStaticEspecialidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StaticEspecialidades> rt = cq.from(StaticEspecialidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
