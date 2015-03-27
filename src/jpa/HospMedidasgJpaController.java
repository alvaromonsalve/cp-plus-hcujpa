/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospHistoriac;
import entidades_EJB.HospMedidasg;
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
 * @author AlvaroVirtual
 */
public class HospMedidasgJpaController implements Serializable {

    public HospMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospMedidasg hospMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospMedidasg hospMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospMedidasg = em.merge(hospMedidasg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospMedidasg.getId();
                if (findHospMedidasg(id) == null) {
                    throw new NonexistentEntityException("The hospMedidasg with id " + id + " no longer exists.");
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
            HospMedidasg hospMedidasg;
            try {
                hospMedidasg = em.getReference(HospMedidasg.class, id);
                hospMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospMedidasg with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospMedidasg> findHospMedidasgEntities() {
        return findHospMedidasgEntities(true, -1, -1);
    }

    public List<HospMedidasg> findHospMedidasgEntities(int maxResults, int firstResult) {
        return findHospMedidasgEntities(false, maxResults, firstResult);
    }

    private List<HospMedidasg> findHospMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospMedidasg.class));
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

    public HospMedidasg findHospMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoMedidasgHcuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospMedidasg> rt = cq.from(HospMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //Codigo no Auto-generado
    
   public List<HospMedidasg> ListFindHospMedidasG(HospHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM HospMedidasg i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
}
