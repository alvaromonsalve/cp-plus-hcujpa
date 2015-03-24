
package jpa;

import entidades.UciEvoProcedimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UciEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UciEvoProcedimientoJpaController implements Serializable {

    public UciEvoProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoProcedimiento uciEvoProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvolucion idHospEvolucion = uciEvoProcedimiento.getIdUciEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                uciEvoProcedimiento.setIdUciEvolucion(idHospEvolucion);
            }
            em.persist(uciEvoProcedimiento);
            if (idHospEvolucion != null) {
                idHospEvolucion.getUciEvoProcedimientos().add(uciEvoProcedimiento);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoProcedimiento uciEvoProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvoProcedimiento persistentUciEvoProcedimiento = em.find(UciEvoProcedimiento.class, uciEvoProcedimiento.getId());
            UciEvolucion idUciEvolucionOld = persistentUciEvoProcedimiento.getIdUciEvolucion();
            UciEvolucion idUciEvolucionNew = uciEvoProcedimiento.getIdUciEvolucion();
            if (idUciEvolucionNew != null) {
                idUciEvolucionNew = em.getReference(idUciEvolucionNew.getClass(), idUciEvolucionNew.getId());
                uciEvoProcedimiento.setIdUciEvolucion(idUciEvolucionNew);
            }
            uciEvoProcedimiento = em.merge(uciEvoProcedimiento);
            if (idUciEvolucionOld != null && !idUciEvolucionOld.equals(idUciEvolucionNew)) {
                idUciEvolucionOld.getUciEvoProcedimientos().remove(uciEvoProcedimiento);
                idUciEvolucionOld = em.merge(idUciEvolucionOld);
            }
            if (idUciEvolucionNew != null && !idUciEvolucionNew.equals(idUciEvolucionOld)) {
                idUciEvolucionNew.getUciEvoProcedimientos().add(uciEvoProcedimiento);
                idUciEvolucionNew = em.merge(idUciEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoProcedimiento.getId();
                if (findUciEvoProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uciEvoProcedimiento with id " + id + " no longer exists.");
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
            UciEvoProcedimiento uciEvoProcedimiento;
            try {
                uciEvoProcedimiento = em.getReference(UciEvoProcedimiento.class, id);
                uciEvoProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoProcedimiento with id " + id + " no longer exists.", enfe);
            }
            UciEvolucion idUciEvolucion = uciEvoProcedimiento.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoProcedimientos().remove(uciEvoProcedimiento);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.remove(uciEvoProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoProcedimiento> findUciEvoProcedimientoEntities() {
        return findUciEvoProcedimientoEntities(true, -1, -1);
    }

    public List<UciEvoProcedimiento> findUciEvoProcedimientoEntities(int maxResults, int firstResult) {
        return findUciEvoProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UciEvoProcedimiento> findUciEvoProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoProcedimiento.class));
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

    public UciEvoProcedimiento findUciEvoProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoProcedimiento> rt = cq.from(UciEvoProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

   //Codigo no Auto-generado
   public List<UciEvoProcedimiento> ListFindUceProcedimientoEvo(UciEvolucion evo){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM UciEvoProcedimiento h WHERE h.idUciEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

      public List<UciEvoProcedimiento> ListFindUceProcedimientoEvo(UciEvolucion evo, Integer ConfigCups){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM UciEvoProcedimiento h WHERE h.idUciEvolucion = :evo AND h.estado = '1' AND h.idConfigCups.idEstructuraCups.id = :cc")
            .setParameter("evo", evo)
            .setParameter("cc", ConfigCups)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
