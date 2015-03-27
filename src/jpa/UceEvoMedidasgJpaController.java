
package jpa;

import entidades_EJB.UceEvoMedidasg;
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
public class UceEvoMedidasgJpaController implements Serializable {

    public UceEvoMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoMedidasg uceEvoMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvolucion idUceEvolucion = uceEvoMedidasg.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion = em.getReference(idUceEvolucion.getClass(), idUceEvolucion.getId());
                uceEvoMedidasg.setIdUceEvolucion(idUceEvolucion);
            }
            em.persist(uceEvoMedidasg);
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoMedidasgs().add(uceEvoMedidasg);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoMedidasg uceEvoMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvoMedidasg persistentUceEvoMedidasg = em.find(UceEvoMedidasg.class, uceEvoMedidasg.getId());
            UceEvolucion idUceEvolucionOld = persistentUceEvoMedidasg.getIdUceEvolucion();
            UceEvolucion idUceEvolucionNew = uceEvoMedidasg.getIdUceEvolucion();
            if (idUceEvolucionNew != null) {
                idUceEvolucionNew = em.getReference(idUceEvolucionNew.getClass(), idUceEvolucionNew.getId());
                uceEvoMedidasg.setIdUceEvolucion(idUceEvolucionNew);
            }
            uceEvoMedidasg = em.merge(uceEvoMedidasg);
            if (idUceEvolucionOld != null && !idUceEvolucionOld.equals(idUceEvolucionNew)) {
                idUceEvolucionOld.getUceEvoMedidasgs().remove(uceEvoMedidasg);
                idUceEvolucionOld = em.merge(idUceEvolucionOld);
            }
            if (idUceEvolucionNew != null && !idUceEvolucionNew.equals(idUceEvolucionOld)) {
                idUceEvolucionNew.getUceEvoMedidasgs().add(uceEvoMedidasg);
                idUceEvolucionNew = em.merge(idUceEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoMedidasg.getId();
                if (findUceEvoMedidasg(id) == null) {
                    throw new NonexistentEntityException("The uceEvoMedidasg with id " + id + " no longer exists.");
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
            UceEvoMedidasg uceEvoMedidasg;
            try {
                uceEvoMedidasg = em.getReference(UceEvoMedidasg.class, id);
                uceEvoMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoMedidasg with id " + id + " no longer exists.", enfe);
            }
            UceEvolucion idHospEvolucion = uceEvoMedidasg.getIdUceEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getUceEvoMedidasgs().remove(uceEvoMedidasg);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(uceEvoMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoMedidasg> findUceEvoMedidasgEntities() {
        return findUceEvoMedidasgEntities(true, -1, -1);
    }

    public List<UceEvoMedidasg> findUceEvoMedidasgEntities(int maxResults, int firstResult) {
        return findUceEvoMedidasgEntities(false, maxResults, firstResult);
    }

    private List<UceEvoMedidasg> findUceEvoMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoMedidasg.class));
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

    public UceEvoMedidasg findUceEvoMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoMedidasgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoMedidasg> rt = cq.from(UceEvoMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

   public List<UceEvoMedidasg> ListFindUceEvoMedidasG(UceEvolucion evol){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UceEvoMedidasg h WHERE h.idUceEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evol)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
