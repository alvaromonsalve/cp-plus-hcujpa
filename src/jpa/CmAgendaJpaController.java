/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CmAgenda;
import java.io.Serializable;
import java.util.Date;
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
public class CmAgendaJpaController implements Serializable {

    public CmAgendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CmAgenda cmAgenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cmAgenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CmAgenda cmAgenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cmAgenda = em.merge(cmAgenda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cmAgenda.getId();
                if (findCmAgenda(id) == null) {
                    throw new NonexistentEntityException("The cmAgenda with id " + id + " no longer exists.");
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
            CmAgenda cmAgenda;
            try {
                cmAgenda = em.getReference(CmAgenda.class, id);
                cmAgenda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cmAgenda with id " + id + " no longer exists.", enfe);
            }
            em.remove(cmAgenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CmAgenda> findCmAgendaEntities() {
        return findCmAgendaEntities(true, -1, -1);
    }

    public List<CmAgenda> findCmAgendaEntities(int maxResults, int firstResult) {
        return findCmAgendaEntities(false, maxResults, firstResult);
    }

    private List<CmAgenda> findCmAgendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CmAgenda.class));
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

    public CmAgenda findCmAgenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CmAgenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getCmAgendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CmAgenda> rt = cq.from(CmAgenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CmAgenda> getAgendadosporFecha(Date fe) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT a FROM CmAgenda a WHERE a.fecha=:f AND a.estado='1' GROUP BY a.idProfesional.idProfesional.id");
        Q.setParameter("f", fe);
        return Q.getResultList();
    }
}
