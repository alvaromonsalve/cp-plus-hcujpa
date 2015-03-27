/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.InfoHistoriac;
import entidades_EJB.InfoPosologiaHcu;
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
public class InfoPosologiaHcuJpaController implements Serializable {

    public InfoPosologiaHcuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoPosologiaHcu infoPosologiaHcu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoPosologiaHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoPosologiaHcu infoPosologiaHcu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoPosologiaHcu = em.merge(infoPosologiaHcu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoPosologiaHcu.getId();
                if (findInfoPosologiaHcu(id) == null) {
                    throw new NonexistentEntityException("The infoPosologiaHcu with id " + id + " no longer exists.");
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
            InfoPosologiaHcu infoPosologiaHcu;
            try {
                infoPosologiaHcu = em.getReference(InfoPosologiaHcu.class, id);
                infoPosologiaHcu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoPosologiaHcu with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoPosologiaHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoPosologiaHcu> findInfoPosologiaHcuEntities() {
        return findInfoPosologiaHcuEntities(true, -1, -1);
    }

    public List<InfoPosologiaHcu> findInfoPosologiaHcuEntities(int maxResults, int firstResult) {
        return findInfoPosologiaHcuEntities(false, maxResults, firstResult);
    }

    private List<InfoPosologiaHcu> findInfoPosologiaHcuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoPosologiaHcu.class));
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

    public InfoPosologiaHcu findInfoPosologiaHcu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoPosologiaHcu.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoPosologiaHcuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoPosologiaHcu> rt = cq.from(InfoPosologiaHcu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
  //Codigo no Auto-generado    
   public List<InfoPosologiaHcu> ListFindInfoPosologia(InfoHistoriac ihc){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM InfoPosologiaHcu i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc);
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
}
