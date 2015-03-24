/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.UciHistoriac;
import entidades.UciMedidasg;
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
public class UciMedidasgJpaController implements Serializable {

    public UciMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciMedidasg uciMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciMedidasg uciMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciMedidasg = em.merge(uciMedidasg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciMedidasg.getId();
                if (findUciMedidasg(id) == null) {
                    throw new NonexistentEntityException("The uciMedidasg with id " + id + " no longer exists.");
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
            UciMedidasg uciMedidasg;
            try {
                uciMedidasg = em.getReference(UciMedidasg.class, id);
                uciMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciMedidasg with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciMedidasg> findUciMedidasgEntities() {
        return findUciMedidasgEntities(true, -1, -1);
    }

    public List<UciMedidasg> findUciMedidasgEntities(int maxResults, int firstResult) {
        return findUciMedidasgEntities(false, maxResults, firstResult);
    }

    private List<UciMedidasg> findUciMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciMedidasg.class));
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

    public UciMedidasg findUciMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoMedidasgUciCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciMedidasg> rt = cq.from(UciMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //Codigo no Auto-generado
    
   public List<UciMedidasg> ListFindUciMedidasG(UciHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UciMedidasg i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
}
