/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFinalizacionesAiremedico;
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
public class UceFinalizacionesAiremedicoJpaController implements Serializable {

    public UceFinalizacionesAiremedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceFinalizacionesAiremedico uceFinalizacionesAiremedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceFinalizacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceFinalizacionesAiremedico uceFinalizacionesAiremedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceFinalizacionesAiremedico = em.merge(uceFinalizacionesAiremedico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceFinalizacionesAiremedico.getId();
                if (findUceFinalizacionesAiremedico(id) == null) {
                    throw new NonexistentEntityException("The uceFinalizacionesAiremedico with id " + id + " no longer exists.");
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
            UceFinalizacionesAiremedico uceFinalizacionesAiremedico;
            try {
                uceFinalizacionesAiremedico = em.getReference(UceFinalizacionesAiremedico.class, id);
                uceFinalizacionesAiremedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceFinalizacionesAiremedico with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceFinalizacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceFinalizacionesAiremedico> findUceFinalizacionesAiremedicoEntities() {
        return findUceFinalizacionesAiremedicoEntities(true, -1, -1);
    }

    public List<UceFinalizacionesAiremedico> findUceFinalizacionesAiremedicoEntities(int maxResults, int firstResult) {
        return findUceFinalizacionesAiremedicoEntities(false, maxResults, firstResult);
    }

    private List<UceFinalizacionesAiremedico> findUceFinalizacionesAiremedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceFinalizacionesAiremedico.class));
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

    public UceFinalizacionesAiremedico findUceFinalizacionesAiremedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceFinalizacionesAiremedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceFinalizacionesAiremedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceFinalizacionesAiremedico> rt = cq.from(UceFinalizacionesAiremedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceFinalizacionesAiremedico> getFinalizados(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UceFinalizacionesAiremedico o WHERE o.idAplicacion.idHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<UceFinalizacionesAiremedico> getFinalizacion(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UceFinalizacionesAiremedico o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object getCountFinalizacionO(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM UceFinalizacionesAiremedico o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
}
