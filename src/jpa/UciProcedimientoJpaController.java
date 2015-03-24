
package jpa;

import entidades.UciHistoriac;
import entidades.UciProcedimiento;
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
public class UciProcedimientoJpaController implements Serializable {

    public UciProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciProcedimiento uciProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciProcedimiento uciProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciProcedimiento = em.merge(uciProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciProcedimiento.getId();
                if (findUciProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uciProcedimiento with id " + id + " no longer exists.");
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
            UciProcedimiento uciProcedimiento;
            try {
                uciProcedimiento = em.getReference(UciProcedimiento.class, id);
                uciProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciProcedimiento> findUciProcedimientoEntities() {
        return findUciProcedimientoEntities(true, -1, -1);
    }

    public List<UciProcedimiento> findUciProcedimientoEntities(int maxResults, int firstResult) {
        return findUciProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UciProcedimiento> findUciProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciProcedimiento.class));
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

    public UciProcedimiento findUciProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciProcedimiento> rt = cq.from(UciProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


            //Codigo no Auto-generado

      public List<UciProcedimiento> ListFindUciProcedimiento(UciHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UciProcedimiento i WHERE i.idHistoriac = :hc")
            .setParameter("hc", ihc.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();return results;
        } finally {
            em.close();
        }
   }

}
