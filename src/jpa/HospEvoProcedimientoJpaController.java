package jpa;

import entidades_EJB.HospEvoProcedimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospEvoProcedimientoJpaController implements Serializable {

    public HospEvoProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoProcedimiento hospEvoProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvolucion idHospEvolucion = hospEvoProcedimiento.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                hospEvoProcedimiento.setIdHospEvolucion(idHospEvolucion);
            }
            em.persist(hospEvoProcedimiento);
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoProcedimientos().add(hospEvoProcedimiento);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoProcedimiento hospEvoProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvoProcedimiento persistentHospEvoProcedimiento = em.find(HospEvoProcedimiento.class, hospEvoProcedimiento.getId());
            HospEvolucion idHospEvolucionOld = persistentHospEvoProcedimiento.getIdHospEvolucion();
            HospEvolucion idHospEvolucionNew = hospEvoProcedimiento.getIdHospEvolucion();
            if (idHospEvolucionNew != null) {
                idHospEvolucionNew = em.getReference(idHospEvolucionNew.getClass(), idHospEvolucionNew.getId());
                hospEvoProcedimiento.setIdHospEvolucion(idHospEvolucionNew);
            }
            hospEvoProcedimiento = em.merge(hospEvoProcedimiento);
            if (idHospEvolucionOld != null && !idHospEvolucionOld.equals(idHospEvolucionNew)) {
                idHospEvolucionOld.getHospEvoProcedimientos().remove(hospEvoProcedimiento);
                idHospEvolucionOld = em.merge(idHospEvolucionOld);
            }
            if (idHospEvolucionNew != null && !idHospEvolucionNew.equals(idHospEvolucionOld)) {
                idHospEvolucionNew.getHospEvoProcedimientos().add(hospEvoProcedimiento);
                idHospEvolucionNew = em.merge(idHospEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoProcedimiento.getId();
                if (findHospEvoProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hospEvoProcedimiento with id " + id + " no longer exists.");
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
            HospEvoProcedimiento hospEvoProcedimiento;
            try {
                hospEvoProcedimiento = em.getReference(HospEvoProcedimiento.class, id);
                hospEvoProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoProcedimiento with id " + id + " no longer exists.", enfe);
            }
            HospEvolucion idHospEvolucion = hospEvoProcedimiento.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoProcedimientos().remove(hospEvoProcedimiento);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(hospEvoProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoProcedimiento> findHospEvoProcedimientoEntities() {
        return findHospEvoProcedimientoEntities(true, -1, -1);
    }

    public List<HospEvoProcedimiento> findHospEvoProcedimientoEntities(int maxResults, int firstResult) {
        return findHospEvoProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HospEvoProcedimiento> findHospEvoProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoProcedimiento.class));
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

    public HospEvoProcedimiento findHospEvoProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoProcedimiento> rt = cq.from(HospEvoProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
    public List<HospEvoProcedimiento> ListFindInfoProcedimientoEvo(HospEvolucion evo) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM HospEvoProcedimiento h WHERE h.idHospEvolucion = :evo AND h.estado = '1'")
                    .setParameter("evo", evo)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<HospEvoProcedimiento> ListFindInfoProcedimientoEvo(HospEvolucion evo, Integer ConfigCups) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM HospEvoProcedimiento h WHERE h.idHospEvolucion = :evo AND h.estado = '1' AND h.idConfigCups.idEstructuraCups.id = :cc")
                    .setParameter("evo", evo)
                    .setParameter("cc", ConfigCups)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Object countEvoluciones(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(e.id) FROM HospEvoProcedimiento e WHERE e.idHospEvolucion.idHospHistoriac.id=:hist AND e.idConfigCups.id='6621'");
        Q.setParameter("hist", h);
        return Q.getSingleResult();
    }

    public List<HospEvoProcedimiento> getProcedimientos(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT e FROM HospEvoProcedimiento e WHERE (e.idHospEvolucion.idHospHistoriac.id=:hist AND e.idConfigCups.id='6621')");
        Q.setParameter("hist", h);
        return Q.getResultList();
    }

}
