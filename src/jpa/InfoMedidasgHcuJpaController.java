/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.InfoHistoriac;
import entidades_EJB.InfoMedidasgHcu;
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
public class InfoMedidasgHcuJpaController implements Serializable {

    public InfoMedidasgHcuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoMedidasgHcu infoMedidasgHcu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoMedidasgHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoMedidasgHcu infoMedidasgHcu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoMedidasgHcu = em.merge(infoMedidasgHcu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoMedidasgHcu.getId();
                if (findInfoMedidasgHcu(id) == null) {
                    throw new NonexistentEntityException("The infoMedidasgHcu with id " + id + " no longer exists.");
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
            InfoMedidasgHcu infoMedidasgHcu;
            try {
                infoMedidasgHcu = em.getReference(InfoMedidasgHcu.class, id);
                infoMedidasgHcu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoMedidasgHcu with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoMedidasgHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoMedidasgHcu> findInfoMedidasgHcuEntities() {
        return findInfoMedidasgHcuEntities(true, -1, -1);
    }

    public List<InfoMedidasgHcu> findInfoMedidasgHcuEntities(int maxResults, int firstResult) {
        return findInfoMedidasgHcuEntities(false, maxResults, firstResult);
    }

    private List<InfoMedidasgHcu> findInfoMedidasgHcuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoMedidasgHcu.class));
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

    public InfoMedidasgHcu findInfoMedidasgHcu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoMedidasgHcu.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoMedidasgHcuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoMedidasgHcu> rt = cq.from(InfoMedidasgHcu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //Codigo no Auto-generado
    
   public List<InfoMedidasgHcu> ListFindInfoMedidasGHcu(InfoHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM InfoMedidasgHcu i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
}
