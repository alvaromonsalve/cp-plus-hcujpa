/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades.AclReporte;
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
public class AclReporteJpaController implements Serializable {

    public AclReporteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AclReporte aclReporte) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aclReporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AclReporte aclReporte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aclReporte = em.merge(aclReporte);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aclReporte.getId();
                if (findAclReporte(id) == null) {
                    throw new NonexistentEntityException("The aclReporte with id " + id + " no longer exists.");
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
            AclReporte aclReporte;
            try {
                aclReporte = em.getReference(AclReporte.class, id);
                aclReporte.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aclReporte with id " + id + " no longer exists.", enfe);
            }
            em.remove(aclReporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AclReporte> findAclReporteEntities() {
        return findAclReporteEntities(true, -1, -1);
    }

    public List<AclReporte> findAclReporteEntities(int maxResults, int firstResult) {
        return findAclReporteEntities(false, maxResults, firstResult);
    }

    private List<AclReporte> findAclReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AclReporte.class));
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

    public AclReporte findAclReporte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AclReporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getAclReporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AclReporte> rt = cq.from(AclReporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    //Codigo no Auto-generado    
    public List<AclReporte> findAclReportesEstadoA(){
        EntityManager em = getEntityManager();        
        try {
            return em.createQuery("SELECT acl FROM AclReporte acl WHERE acl.estado = 1")
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();            
        } finally {
            em.close();
        }
    }
}
