/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFinalizacionesAiremedico;
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
public class UciFinalizacionesAiremedicoJpaController implements Serializable {

    public UciFinalizacionesAiremedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciFinalizacionesAiremedico uciFinalizacionesAiremedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciFinalizacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciFinalizacionesAiremedico uciFinalizacionesAiremedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciFinalizacionesAiremedico = em.merge(uciFinalizacionesAiremedico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciFinalizacionesAiremedico.getId();
                if (findUciFinalizacionesAiremedico(id) == null) {
                    throw new NonexistentEntityException("The uciFinalizacionesAiremedico with id " + id + " no longer exists.");
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
            UciFinalizacionesAiremedico uciFinalizacionesAiremedico;
            try {
                uciFinalizacionesAiremedico = em.getReference(UciFinalizacionesAiremedico.class, id);
                uciFinalizacionesAiremedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciFinalizacionesAiremedico with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciFinalizacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciFinalizacionesAiremedico> findUciFinalizacionesAiremedicoEntities() {
        return findUciFinalizacionesAiremedicoEntities(true, -1, -1);
    }

    public List<UciFinalizacionesAiremedico> findUciFinalizacionesAiremedicoEntities(int maxResults, int firstResult) {
        return findUciFinalizacionesAiremedicoEntities(false, maxResults, firstResult);
    }

    private List<UciFinalizacionesAiremedico> findUciFinalizacionesAiremedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciFinalizacionesAiremedico.class));
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

    public UciFinalizacionesAiremedico findUciFinalizacionesAiremedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciFinalizacionesAiremedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciFinalizacionesAiremedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciFinalizacionesAiremedico> rt = cq.from(UciFinalizacionesAiremedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciFinalizacionesAiremedico> getFinalizados(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UciFinalizacionesAiremedico o WHERE o.idAplicacion.idHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<UciFinalizacionesAiremedico> getFinalizacion(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UciFinalizacionesAiremedico o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object getCountFinalizacionO(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM UciFinalizacionesAiremedico o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
}
