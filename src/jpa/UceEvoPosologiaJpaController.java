
package jpa;

import entidades_EJB.UceEvoPosologia;
import entidades_EJB.UceEvolucion;
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
public class UceEvoPosologiaJpaController implements Serializable {

    public UceEvoPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoPosologia uceEvoPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoPosologia uceEvoPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceEvoPosologia = em.merge(uceEvoPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoPosologia.getId();
                if (findUceEvoPosologia(id) == null) {
                    throw new NonexistentEntityException("The uce with id " + id + " no longer exists.");
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
            UceEvoPosologia uciEvoPosologia;
            try {
                uciEvoPosologia = em.getReference(UceEvoPosologia.class, id);
                uciEvoPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoPosologia> findUceEvoPosologiaEntities() {
        return findUceEvoPosologiaEntities(true, -1, -1);
    }

    public List<UceEvoPosologia> findUceEvoPosologiaEntities(int maxResults, int firstResult) {
        return findUceEvoPosologiaEntities(false, maxResults, firstResult);
    }

    private List<UceEvoPosologia> findUceEvoPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoPosologia.class));
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

    public UceEvoPosologia findUceEvoPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoPosologia> rt = cq.from(UceEvoPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
   public List<UceEvoPosologia> ListFindUcePosologia(UceEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UceEvoPosologia h WHERE h.idUceEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
