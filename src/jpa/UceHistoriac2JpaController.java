
package jpa;

import entidades_EJB.UceHistoriac2;
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
public class UceHistoriac2JpaController implements Serializable {

    public UceHistoriac2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceHistoriac2 uceHistoriac2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceHistoriac2 uceHistoriac2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceHistoriac2 = em.merge(uceHistoriac2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceHistoriac2.getId();
                if (findUceHistoriac2(id) == null) {
                    throw new NonexistentEntityException("The uceHistoriac2 with id " + id + " no longer exists.");
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
            UceHistoriac2 uceHistoriac2;
            try {
                uceHistoriac2 = em.getReference(UceHistoriac2.class, id);
                uceHistoriac2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceHistoriac2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceHistoriac2> findUceHistoriac2Entities() {
        return findUceHistoriac2Entities(true, -1, -1);
    }

    public List<UceHistoriac2> findUceHistoriac2Entities(int maxResults, int firstResult) {
        return findUceHistoriac2Entities(false, maxResults, firstResult);
    }

    private List<UceHistoriac2> findUceHistoriac2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceHistoriac2.class));
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

    public UceHistoriac2 findUceHistoriac2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceHistoriac2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceHistoriac2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceHistoriac2> rt = cq.from(UceHistoriac2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
