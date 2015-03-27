
package jpa;

import entidades_EJB.HospHistoriac;
import entidades_EJB.HospProcedimiento;
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
public class HospProcedimientoJpaController implements Serializable {

    public HospProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospProcedimiento hospProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospProcedimiento hospProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospProcedimiento = em.merge(hospProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospProcedimiento.getId();
                if (findHospProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hospProcedimiento with id " + id + " no longer exists.");
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
            HospProcedimiento hospProcedimiento;
            try {
                hospProcedimiento = em.getReference(HospProcedimiento.class, id);
                hospProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospProcedimiento> findHospProcedimientoEntities() {
        return findHospProcedimientoEntities(true, -1, -1);
    }

    public List<HospProcedimiento> findHospProcedimientoEntities(int maxResults, int firstResult) {
        return findHospProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HospProcedimiento> findHospProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospProcedimiento.class));
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

    public HospProcedimiento findHospProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospProcedimiento> rt = cq.from(HospProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


            //Codigo no Auto-generado

      public List<HospProcedimiento> ListFindHospProcedimiento(HospHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM HospProcedimiento i WHERE i.idHistoriac = :hc")
            .setParameter("hc", ihc.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();return results;
        } finally {
            em.close();
        }
   }

}
