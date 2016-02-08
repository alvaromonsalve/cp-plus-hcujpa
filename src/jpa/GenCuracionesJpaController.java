/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.GenCuraciones;
import entidades_EJB.InfoPaciente;
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
public class GenCuracionesJpaController implements Serializable {

    public GenCuracionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GenCuraciones genCuraciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(genCuraciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GenCuraciones genCuraciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            genCuraciones = em.merge(genCuraciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = genCuraciones.getId();
                if (findGenCuraciones(id) == null) {
                    throw new NonexistentEntityException("The genCuraciones with id " + id + " no longer exists.");
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
            GenCuraciones genCuraciones;
            try {
                genCuraciones = em.getReference(GenCuraciones.class, id);
                genCuraciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genCuraciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(genCuraciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GenCuraciones> findGenCuracionesEntities() {
        return findGenCuracionesEntities(true, -1, -1);
    }

    public List<GenCuraciones> findGenCuracionesEntities(int maxResults, int firstResult) {
        return findGenCuracionesEntities(false, maxResults, firstResult);
    }

    private List<GenCuraciones> findGenCuracionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GenCuraciones.class));
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

    public GenCuraciones findGenCuraciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GenCuraciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenCuracionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GenCuraciones> rt = cq.from(GenCuraciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<GenCuraciones> getCuraciones(InfoPaciente pa) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM GenCuraciones c WHERE c.idPaciente.numDoc=:doc AND c.estado='1'")
                    .setParameter("doc", pa.getNumDoc())
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
