
package jpa;

import entidades.HospEvoMedidasg;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HospEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospEvoMedidasgJpaController implements Serializable {

    public HospEvoMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoMedidasg hospEvoMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvolucion idHospEvolucion = hospEvoMedidasg.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                hospEvoMedidasg.setIdHospEvolucion(idHospEvolucion);
            }
            em.persist(hospEvoMedidasg);
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoMedidasgs().add(hospEvoMedidasg);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoMedidasg hospEvoMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvoMedidasg persistentHospEvoMedidasg = em.find(HospEvoMedidasg.class, hospEvoMedidasg.getId());
            HospEvolucion idHospEvolucionOld = persistentHospEvoMedidasg.getIdHospEvolucion();
            HospEvolucion idHospEvolucionNew = hospEvoMedidasg.getIdHospEvolucion();
            if (idHospEvolucionNew != null) {
                idHospEvolucionNew = em.getReference(idHospEvolucionNew.getClass(), idHospEvolucionNew.getId());
                hospEvoMedidasg.setIdHospEvolucion(idHospEvolucionNew);
            }
            hospEvoMedidasg = em.merge(hospEvoMedidasg);
            if (idHospEvolucionOld != null && !idHospEvolucionOld.equals(idHospEvolucionNew)) {
                idHospEvolucionOld.getHospEvoMedidasgs().remove(hospEvoMedidasg);
                idHospEvolucionOld = em.merge(idHospEvolucionOld);
            }
            if (idHospEvolucionNew != null && !idHospEvolucionNew.equals(idHospEvolucionOld)) {
                idHospEvolucionNew.getHospEvoMedidasgs().add(hospEvoMedidasg);
                idHospEvolucionNew = em.merge(idHospEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoMedidasg.getId();
                if (findHospEvoMedidasg(id) == null) {
                    throw new NonexistentEntityException("The hospEvoMedidasg with id " + id + " no longer exists.");
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
            HospEvoMedidasg hospEvoMedidasg;
            try {
                hospEvoMedidasg = em.getReference(HospEvoMedidasg.class, id);
                hospEvoMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoMedidasg with id " + id + " no longer exists.", enfe);
            }
            HospEvolucion idHospEvolucion = hospEvoMedidasg.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoMedidasgs().remove(hospEvoMedidasg);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(hospEvoMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoMedidasg> findHospEvoMedidasgEntities() {
        return findHospEvoMedidasgEntities(true, -1, -1);
    }

    public List<HospEvoMedidasg> findHospEvoMedidasgEntities(int maxResults, int firstResult) {
        return findHospEvoMedidasgEntities(false, maxResults, firstResult);
    }

    private List<HospEvoMedidasg> findHospEvoMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoMedidasg.class));
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

    public HospEvoMedidasg findHospEvoMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoMedidasgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoMedidasg> rt = cq.from(HospEvoMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

   public List<HospEvoMedidasg> ListFindHospEvoMedidasG(HospEvolucion evol){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HospEvoMedidasg h WHERE h.idHospEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evol)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
