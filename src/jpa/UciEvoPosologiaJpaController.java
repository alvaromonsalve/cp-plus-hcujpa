
package jpa;

import entidades.UciEvoPosologia;
import entidades.UciEvolucion;
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
public class UciEvoPosologiaJpaController implements Serializable {

    public UciEvoPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoPosologia uciEvoPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoPosologia uciEvoPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciEvoPosologia = em.merge(uciEvoPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoPosologia.getId();
                if (findUciEvoPosologia(id) == null) {
                    throw new NonexistentEntityException("The uci with id " + id + " no longer exists.");
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
            UciEvoPosologia uciEvoPosologia;
            try {
                uciEvoPosologia = em.getReference(UciEvoPosologia.class, id);
                uciEvoPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoPosologia> findUciEvoPosologiaEntities() {
        return findUciEvoPosologiaEntities(true, -1, -1);
    }

    public List<UciEvoPosologia> findUciEvoPosologiaEntities(int maxResults, int firstResult) {
        return findUciEvoPosologiaEntities(false, maxResults, firstResult);
    }

    private List<UciEvoPosologia> findUciEvoPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoPosologia.class));
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

    public UciEvoPosologia findUciEvoPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoPosologia> rt = cq.from(UciEvoPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
   public List<UciEvoPosologia> ListFindUciPosologia(UciEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UciEvoPosologia h WHERE h.idUciEvolucion = :evo AND h.estado='1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
