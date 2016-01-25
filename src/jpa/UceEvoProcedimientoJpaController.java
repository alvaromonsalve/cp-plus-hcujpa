package jpa;

import entidades_EJB.UceEvoProcedimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UceEvoProcedimientoJpaController implements Serializable {

    public UceEvoProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoProcedimiento uceEvoProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvolucion idHospEvolucion = uceEvoProcedimiento.getIdUceEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                uceEvoProcedimiento.setIdUceEvolucion(idHospEvolucion);
            }
            em.persist(uceEvoProcedimiento);
            if (idHospEvolucion != null) {
                idHospEvolucion.getUceEvoProcedimientos().add(uceEvoProcedimiento);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoProcedimiento uceEvoProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvoProcedimiento persistentUceEvoProcedimiento = em.find(UceEvoProcedimiento.class, uceEvoProcedimiento.getId());
            UceEvolucion idUceEvolucionOld = persistentUceEvoProcedimiento.getIdUceEvolucion();
            UceEvolucion idUceEvolucionNew = uceEvoProcedimiento.getIdUceEvolucion();
            if (idUceEvolucionNew != null) {
                idUceEvolucionNew = em.getReference(idUceEvolucionNew.getClass(), idUceEvolucionNew.getId());
                uceEvoProcedimiento.setIdUceEvolucion(idUceEvolucionNew);
            }
            uceEvoProcedimiento = em.merge(uceEvoProcedimiento);
            if (idUceEvolucionOld != null && !idUceEvolucionOld.equals(idUceEvolucionNew)) {
                idUceEvolucionOld.getUceEvoProcedimientos().remove(uceEvoProcedimiento);
                idUceEvolucionOld = em.merge(idUceEvolucionOld);
            }
            if (idUceEvolucionNew != null && !idUceEvolucionNew.equals(idUceEvolucionOld)) {
                idUceEvolucionNew.getUceEvoProcedimientos().add(uceEvoProcedimiento);
                idUceEvolucionNew = em.merge(idUceEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoProcedimiento.getId();
                if (findUceEvoProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uceEvoProcedimiento with id " + id + " no longer exists.");
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
            UceEvoProcedimiento uceEvoProcedimiento;
            try {
                uceEvoProcedimiento = em.getReference(UceEvoProcedimiento.class, id);
                uceEvoProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoProcedimiento with id " + id + " no longer exists.", enfe);
            }
            UceEvolucion idUceEvolucion = uceEvoProcedimiento.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoProcedimientos().remove(uceEvoProcedimiento);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.remove(uceEvoProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoProcedimiento> findUceEvoProcedimientoEntities() {
        return findUceEvoProcedimientoEntities(true, -1, -1);
    }

    public List<UceEvoProcedimiento> findUceEvoProcedimientoEntities(int maxResults, int firstResult) {
        return findUceEvoProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UceEvoProcedimiento> findUceEvoProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoProcedimiento.class));
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

    public UceEvoProcedimiento findUceEvoProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoProcedimiento> rt = cq.from(UceEvoProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
    public List<UceEvoProcedimiento> ListFindUceProcedimientoEvo(UceEvolucion evo) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM UceEvoProcedimiento h WHERE h.idUceEvolucion = :evo AND h.estado = '1'")
                    .setParameter("evo", evo)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<UceEvoProcedimiento> ListFindUceProcedimientoEvo(UceEvolucion evo, Integer ConfigCups) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM UceEvoProcedimiento h WHERE h.idUceEvolucion = :evo AND h.estado = '1' AND h.idConfigCups.idEstructuraCups.id = :cc")
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
        Q = em.createQuery("SELECT COUNT(e.id) FROM UceEvoProcedimiento e WHERE e.idUceEvolucion.idUceHistoriac.id=:hist AND e.idConfigCups.id='6621'");
        Q.setParameter("hist", h);
        return Q.getSingleResult();
    }

    public List<UceEvoProcedimiento> getProcedimientos(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT e FROM UceEvoProcedimiento e WHERE e.idUceEvolucion.idUceHistoriac.id=:hist AND e.idConfigCups.id='6621'");
        Q.setParameter("hist", h);
        return Q.getResultList();
    }
}
