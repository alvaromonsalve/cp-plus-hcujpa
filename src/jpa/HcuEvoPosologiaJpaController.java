/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.HcuEvoPosologia;
import entidades_EJB.HcuEvolucion;
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
 * @author Administrador
 */
public class HcuEvoPosologiaJpaController implements Serializable {

    public HcuEvoPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoPosologia hcuEvoPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hcuEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoPosologia hcuEvoPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hcuEvoPosologia = em.merge(hcuEvoPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoPosologia.getId();
                if (findHcuEvoPosologia(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoPosologia with id " + id + " no longer exists.");
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
            HcuEvoPosologia hcuEvoPosologia;
            try {
                hcuEvoPosologia = em.getReference(HcuEvoPosologia.class, id);
                hcuEvoPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(hcuEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoPosologia> findHcuEvoPosologiaEntities() {
        return findHcuEvoPosologiaEntities(true, -1, -1);
    }

    public List<HcuEvoPosologia> findHcuEvoPosologiaEntities(int maxResults, int firstResult) {
        return findHcuEvoPosologiaEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoPosologia> findHcuEvoPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoPosologia.class));
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

    public HcuEvoPosologia findHcuEvoPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoPosologia> rt = cq.from(HcuEvoPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado    
   public List<HcuEvoPosologia> ListFindInfoPosologia(HcuEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuEvoPosologia h WHERE h.idHcuEvolucion = :evo AND h.estado='1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
