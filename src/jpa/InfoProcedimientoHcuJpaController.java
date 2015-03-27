/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.InfoHistoriac;
import entidades_EJB.InfoProcedimientoHcu;
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
public class InfoProcedimientoHcuJpaController implements Serializable {

    public InfoProcedimientoHcuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoProcedimientoHcu infoProcedimientoHcu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoProcedimientoHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoProcedimientoHcu infoProcedimientoHcu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoProcedimientoHcu = em.merge(infoProcedimientoHcu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoProcedimientoHcu.getId();
                if (findInfoProcedimientoHcu(id) == null) {
                    throw new NonexistentEntityException("The infoProcedimientoHcu with id " + id + " no longer exists.");
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
            InfoProcedimientoHcu infoProcedimientoHcu;
            try {
                infoProcedimientoHcu = em.getReference(InfoProcedimientoHcu.class, id);
                infoProcedimientoHcu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoProcedimientoHcu with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoProcedimientoHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoProcedimientoHcu> findInfoProcedimientoHcuEntities() {
        return findInfoProcedimientoHcuEntities(true, -1, -1);
    }

    public List<InfoProcedimientoHcu> findInfoProcedimientoHcuEntities(int maxResults, int firstResult) {
        return findInfoProcedimientoHcuEntities(false, maxResults, firstResult);
    }

    private List<InfoProcedimientoHcu> findInfoProcedimientoHcuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoProcedimientoHcu.class));
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

    public InfoProcedimientoHcu findInfoProcedimientoHcu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoProcedimientoHcu.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoProcedimientoHcuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoProcedimientoHcu> rt = cq.from(InfoProcedimientoHcu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        
            //Codigo no Auto-generado
    
//   public List<InfoProcedimientoHcu> ListFindInfoProcedimientoHcu(InfoHistoriac ihc){
//        EntityManager em = getEntityManager();
//        em.clear();
//        try {
//            Query q = em.createQuery("SELECT i FROM InfoProcedimientoHcu i WHERE i.idHistoriac = :hc");
//            q.setParameter("hc", ihc.getId());
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//   }
   
      public List<InfoProcedimientoHcu> ListFindInfoProcedimientoHcu(InfoHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM InfoProcedimientoHcu i WHERE i.idHistoriac = :hc")
            .setParameter("hc", ihc.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();return results;
        } finally {
            em.close();
        }
   }
    
}
