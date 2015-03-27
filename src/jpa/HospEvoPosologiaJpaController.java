
package jpa;

import entidades_EJB.HospEvoPosologia;
import entidades_EJB.HospEvolucion;
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
public class HospEvoPosologiaJpaController implements Serializable {

    public HospEvoPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoPosologia hospEvoPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoPosologia hospEvoPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospEvoPosologia = em.merge(hospEvoPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoPosologia.getId();
                if (findHospEvoPosologia(id) == null) {
                    throw new NonexistentEntityException("The hospEvoPosologia with id " + id + " no longer exists.");
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
            HospEvoPosologia hospEvoPosologia;
            try {
                hospEvoPosologia = em.getReference(HospEvoPosologia.class, id);
                hospEvoPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospEvoPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoPosologia> findHospEvoPosologiaEntities() {
        return findHospEvoPosologiaEntities(true, -1, -1);
    }

    public List<HospEvoPosologia> findHospEvoPosologiaEntities(int maxResults, int firstResult) {
        return findHospEvoPosologiaEntities(false, maxResults, firstResult);
    }

    private List<HospEvoPosologia> findHospEvoPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoPosologia.class));
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

    public HospEvoPosologia findHospEvoPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoPosologia> rt = cq.from(HospEvoPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
   public List<HospEvoPosologia> ListFindInfoPosologia(HospEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HospEvoPosologia h WHERE h.idHospEvolucion = :evo AND h.estado='1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
