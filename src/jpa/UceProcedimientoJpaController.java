package jpa;

import entidades_EJB.UceHistoriac;
import entidades_EJB.UceProcedimiento;
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
public class UceProcedimientoJpaController implements Serializable {

    public UceProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceProcedimiento uceProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceProcedimiento uceProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceProcedimiento = em.merge(uceProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceProcedimiento.getId();
                if (findUceProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uceProcedimiento with id " + id + " no longer exists.");
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
            UceProcedimiento uceProcedimiento;
            try {
                uceProcedimiento = em.getReference(UceProcedimiento.class, id);
                uceProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceProcedimiento> findUceProcedimientoEntities() {
        return findUceProcedimientoEntities(true, -1, -1);
    }

    public List<UceProcedimiento> findUceProcedimientoEntities(int maxResults, int firstResult) {
        return findUceProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UceProcedimiento> findUceProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceProcedimiento.class));
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

    public UceProcedimiento findUceProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceProcedimiento> rt = cq.from(UceProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
    public List<UceProcedimiento> ListFindUceProcedimiento(UceHistoriac ihc) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UceProcedimiento i WHERE i.idHistoriac = :hc")
                    .setParameter("hc", ihc.getId())
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public List<UceProcedimiento> getHCU(int r) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT a FROM UceProcedimiento a WHERE a.idHistoriac=:i AND a.estado='0'");
        Q.setParameter("i", r);
        return Q.getResultList();
    }

    public Object countProcedimiento(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT COUNT(c.id) FROM UceProcedimiento c WHERE c.idCups='6621' AND c.idHistoriac=:his AND c.estado='0'");
        Q.setParameter("his", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }

    public List<UceProcedimiento> getProcedimiento(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c FROM UceProcedimiento c WHERE c.idCups='6621' AND c.idHistoriac=:his AND c.estado='0'");
        Q.setParameter("his", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

}
