/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.ArchUbicaciones;
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
public class ArchUbicacionesJpaController implements Serializable {

    public ArchUbicacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArchUbicaciones archUbicaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(archUbicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArchUbicaciones archUbicaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            archUbicaciones = em.merge(archUbicaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = archUbicaciones.getId();
                if (findArchUbicaciones(id) == null) {
                    throw new NonexistentEntityException("The archUbicaciones with id " + id + " no longer exists.");
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
            ArchUbicaciones archUbicaciones;
            try {
                archUbicaciones = em.getReference(ArchUbicaciones.class, id);
                archUbicaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The archUbicaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(archUbicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArchUbicaciones> findArchUbicacionesEntities() {
        return findArchUbicacionesEntities(true, -1, -1);
    }

    public List<ArchUbicaciones> findArchUbicacionesEntities(int maxResults, int firstResult) {
        return findArchUbicacionesEntities(false, maxResults, firstResult);
    }

    private List<ArchUbicaciones> findArchUbicacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArchUbicaciones.class));
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

    public ArchUbicaciones findArchUbicaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArchUbicaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getArchUbicacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArchUbicaciones> rt = cq.from(ArchUbicaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Object countUbicaciones(String i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM ArchUbicaciones o WHERE o.identificador=:idf AND o.estado='1'");
        Q.setParameter("idf", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }

    public List<ArchUbicaciones> getHistoria(String i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM ArchUbicaciones o WHERE o.identificador=:idf AND o.estado='1'");
        Q.setParameter("idf", i);
        return Q.getResultList();
    }

    public List<ArchUbicaciones> getPersonas(String i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT p FROM ArchUbicaciones p WHERE p.paciente LIKE :idd AND p.estado='1'");
        Q.setParameter("idd", "%" + i + "%");
        return Q.getResultList();
    }
}
