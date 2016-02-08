/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CeIncapacidades;
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
public class CeIncapacidadesJpaController implements Serializable {

    public CeIncapacidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeIncapacidades ceIncapacidades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceIncapacidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeIncapacidades ceIncapacidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceIncapacidades = em.merge(ceIncapacidades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceIncapacidades.getId();
                if (findCeIncapacidades(id) == null) {
                    throw new NonexistentEntityException("The ceIncapacidades with id " + id + " no longer exists.");
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
            CeIncapacidades ceIncapacidades;
            try {
                ceIncapacidades = em.getReference(CeIncapacidades.class, id);
                ceIncapacidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceIncapacidades with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceIncapacidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeIncapacidades> findCeIncapacidadesEntities() {
        return findCeIncapacidadesEntities(true, -1, -1);
    }

    public List<CeIncapacidades> findCeIncapacidadesEntities(int maxResults, int firstResult) {
        return findCeIncapacidadesEntities(false, maxResults, firstResult);
    }

    private List<CeIncapacidades> findCeIncapacidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeIncapacidades.class));
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

    public CeIncapacidades findCeIncapacidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeIncapacidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeIncapacidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeIncapacidades> rt = cq.from(CeIncapacidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public CeIncapacidades getEntidadIncapacidad(CeHistoriac historia) {
        CeIncapacidades in = null;
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT i FROM CeIncapacidades i WHERE i.idHistoriace=:ht AND i.estado='1'");
        q.setParameter("ht", historia);
        List<CeIncapacidades> listIn = q.getResultList();
        if (!listIn.isEmpty()) {
            in = (CeIncapacidades) listIn.get(0);
        } else {
            in = null;
        }
        return in;
    }
}
