
package jpa;

import entidades.HospHistoriac;
import entidades.HospPosologia;
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
public class HospPosologiaJpaController implements Serializable {

    public HospPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospPosologia hospPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospPosologia hospPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospPosologia = em.merge(hospPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospPosologia.getId();
                if (findHospPosologia(id) == null) {
                    throw new NonexistentEntityException("The hospPosologia with id " + id + " no longer exists.");
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
            HospPosologia hospPosologia;
            try {
                hospPosologia = em.getReference(HospPosologia.class, id);
                hospPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospPosologia> findHospPosologiaEntities() {
        return findHospPosologiaEntities(true, -1, -1);
    }

    public List<HospPosologia> findHospPosologiaEntities(int maxResults, int firstResult) {
        return findHospPosologiaEntities(false, maxResults, firstResult);
    }

    private List<HospPosologia> findHospPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospPosologia.class));
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

    public HospPosologia findHospPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospPosologia> rt = cq.from(HospPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

  //Codigo no Auto-generado
   public List<HospPosologia> ListFindHospPosologia(HospHistoriac ihc){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM HospPosologia i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc);
            return q.getResultList();
        } finally {
            em.close();
        }
   }

}
