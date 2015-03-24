
package jpa;

import entidades.UciHistoriac2;
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
public class UciHistoriac2JpaController implements Serializable {

    public UciHistoriac2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciHistoriac2 uciHistoriac2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciHistoriac2 uciHistoriac2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciHistoriac2 = em.merge(uciHistoriac2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciHistoriac2.getId();
                if (findUciHistoriac2(id) == null) {
                    throw new NonexistentEntityException("The uciHistoriac2 with id " + id + " no longer exists.");
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
            UciHistoriac2 uciHistoriac2;
            try {
                uciHistoriac2 = em.getReference(UciHistoriac2.class, id);
                uciHistoriac2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciHistoriac2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciHistoriac2> findUciHistoriac2Entities() {
        return findUciHistoriac2Entities(true, -1, -1);
    }

    public List<UciHistoriac2> findUciHistoriac2Entities(int maxResults, int firstResult) {
        return findUciHistoriac2Entities(false, maxResults, firstResult);
    }

    private List<UciHistoriac2> findUciHistoriac2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciHistoriac2.class));
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

    public UciHistoriac2 findUciHistoriac2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciHistoriac2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciHistoriac2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciHistoriac2> rt = cq.from(UciHistoriac2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
