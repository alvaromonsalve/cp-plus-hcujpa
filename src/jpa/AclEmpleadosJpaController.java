/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades.AclEmpleados;
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
public class AclEmpleadosJpaController implements Serializable {

    public AclEmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AclEmpleados aclEmpleados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aclEmpleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AclEmpleados aclEmpleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aclEmpleados = em.merge(aclEmpleados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aclEmpleados.getId();
                if (findAclEmpleados(id) == null) {
                    throw new NonexistentEntityException("The aclEmpleados with id " + id + " no longer exists.");
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
            AclEmpleados aclEmpleados;
            try {
                aclEmpleados = em.getReference(AclEmpleados.class, id);
                aclEmpleados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aclEmpleados with id " + id + " no longer exists.", enfe);
            }
            em.remove(aclEmpleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AclEmpleados> findAclEmpleadosEntities() {
        return findAclEmpleadosEntities(true, -1, -1);
    }

    public List<AclEmpleados> findAclEmpleadosEntities(int maxResults, int firstResult) {
        return findAclEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<AclEmpleados> findAclEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AclEmpleados.class));
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

    public AclEmpleados findAclEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AclEmpleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getAclEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AclEmpleados> rt = cq.from(AclEmpleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado    
    public List<AclEmpleados> findAclEmpleadosEstadoA(){
        EntityManager em = getEntityManager();        
        try {
            return em.createQuery("SELECT acl FROM AclEmpleados acl WHERE acl.estado = 1")
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();            
        } finally {
            em.close();
        }
    }

}
