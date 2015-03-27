
package jpa;

import entidades_EJB.HospHistoriac2;
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
public class HospHistoriac2JpaController implements Serializable {

    public HospHistoriac2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospHistoriac2 hospHistoriac2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospHistoriac2 hospHistoriac2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospHistoriac2 = em.merge(hospHistoriac2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospHistoriac2.getId();
                if (findHospHistoriac2(id) == null) {
                    throw new NonexistentEntityException("The hospHistoriac2 with id " + id + " no longer exists.");
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
            HospHistoriac2 hospHistoriac2;
            try {
                hospHistoriac2 = em.getReference(HospHistoriac2.class, id);
                hospHistoriac2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospHistoriac2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospHistoriac2> findHospHistoriac2Entities() {
        return findHospHistoriac2Entities(true, -1, -1);
    }

    public List<HospHistoriac2> findHospHistoriac2Entities(int maxResults, int firstResult) {
        return findHospHistoriac2Entities(false, maxResults, firstResult);
    }

    private List<HospHistoriac2> findHospHistoriac2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospHistoriac2.class));
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

    public HospHistoriac2 findHospHistoriac2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospHistoriac2.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospHistoriac2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospHistoriac2> rt = cq.from(HospHistoriac2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
