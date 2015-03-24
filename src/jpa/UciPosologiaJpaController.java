
package jpa;

import entidades.UciHistoriac;
import entidades.UciPosologia;
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
public class UciPosologiaJpaController implements Serializable {

    public UciPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciPosologia uciPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciPosologia uciPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciPosologia = em.merge(uciPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciPosologia.getId();
                if (findUciPosologia(id) == null) {
                    throw new NonexistentEntityException("The uciPosologia with id " + id + " no longer exists.");
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
            UciPosologia uciPosologia;
            try {
                uciPosologia = em.getReference(UciPosologia.class, id);
                uciPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciPosologia> findUciPosologiaEntities() {
        return findUciPosologiaEntities(true, -1, -1);
    }

    public List<UciPosologia> findUciPosologiaEntities(int maxResults, int firstResult) {
        return findUciPosologiaEntities(false, maxResults, firstResult);
    }

    private List<UciPosologia> findUciPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciPosologia.class));
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

    public UciPosologia findUciPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciPosologia> rt = cq.from(UciPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

  //Codigo no Auto-generado
   public List<UciPosologia> ListFindUciPosologia(UciHistoriac ihc){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM UciPosologia i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc);
            return q.getResultList();
        } finally {
            em.close();
        }
   }

}
