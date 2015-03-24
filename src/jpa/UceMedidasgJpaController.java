/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.UceHistoriac;
import entidades.UceMedidasg;
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
public class UceMedidasgJpaController implements Serializable {

    public UceMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceMedidasg uceMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceMedidasg uceMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceMedidasg = em.merge(uceMedidasg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceMedidasg.getId();
                if (findUceMedidasg(id) == null) {
                    throw new NonexistentEntityException("The uceMedidasg with id " + id + " no longer exists.");
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
            UceMedidasg uceMedidasg;
            try {
                uceMedidasg = em.getReference(UceMedidasg.class, id);
                uceMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceMedidasg with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceMedidasg> findUceMedidasgEntities() {
        return findUceMedidasgEntities(true, -1, -1);
    }

    public List<UceMedidasg> findUceMedidasgEntities(int maxResults, int firstResult) {
        return findUceMedidasgEntities(false, maxResults, firstResult);
    }

    private List<UceMedidasg> findUceMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceMedidasg.class));
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

    public UceMedidasg findUceMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoMedidasgUceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceMedidasg> rt = cq.from(UceMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //Codigo no Auto-generado
    
   public List<UceMedidasg> ListFindUceMedidasG(UceHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UceMedidasg i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
}
