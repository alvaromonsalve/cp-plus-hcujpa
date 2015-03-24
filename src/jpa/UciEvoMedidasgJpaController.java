
package jpa;

import entidades.UciEvoMedidasg;
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
public class UciEvoMedidasgJpaController implements Serializable {

    public UciEvoMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoMedidasg uciEvoMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvolucion idUciEvolucion = uciEvoMedidasg.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion = em.getReference(idUciEvolucion.getClass(), idUciEvolucion.getId());
                uciEvoMedidasg.setIdUciEvolucion(idUciEvolucion);
            }
            em.persist(uciEvoMedidasg);
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoMedidasgs().add(uciEvoMedidasg);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoMedidasg uciEvoMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvoMedidasg persistentUciEvoMedidasg = em.find(UciEvoMedidasg.class, uciEvoMedidasg.getId());
            UciEvolucion idUciEvolucionOld = persistentUciEvoMedidasg.getIdUciEvolucion();
            UciEvolucion idUciEvolucionNew = uciEvoMedidasg.getIdUciEvolucion();
            if (idUciEvolucionNew != null) {
                idUciEvolucionNew = em.getReference(idUciEvolucionNew.getClass(), idUciEvolucionNew.getId());
                uciEvoMedidasg.setIdUciEvolucion(idUciEvolucionNew);
            }
            uciEvoMedidasg = em.merge(uciEvoMedidasg);
            if (idUciEvolucionOld != null && !idUciEvolucionOld.equals(idUciEvolucionNew)) {
                idUciEvolucionOld.getUciEvoMedidasgs().remove(uciEvoMedidasg);
                idUciEvolucionOld = em.merge(idUciEvolucionOld);
            }
            if (idUciEvolucionNew != null && !idUciEvolucionNew.equals(idUciEvolucionOld)) {
                idUciEvolucionNew.getUciEvoMedidasgs().add(uciEvoMedidasg);
                idUciEvolucionNew = em.merge(idUciEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoMedidasg.getId();
                if (findUciEvoMedidasg(id) == null) {
                    throw new NonexistentEntityException("The uciEvoMedidasg with id " + id + " no longer exists.");
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
            UciEvoMedidasg uciEvoMedidasg;
            try {
                uciEvoMedidasg = em.getReference(UciEvoMedidasg.class, id);
                uciEvoMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoMedidasg with id " + id + " no longer exists.", enfe);
            }
            UciEvolucion idHospEvolucion = uciEvoMedidasg.getIdUciEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getUciEvoMedidasgs().remove(uciEvoMedidasg);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(uciEvoMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoMedidasg> findUciEvoMedidasgEntities() {
        return findUciEvoMedidasgEntities(true, -1, -1);
    }

    public List<UciEvoMedidasg> findUciEvoMedidasgEntities(int maxResults, int firstResult) {
        return findUciEvoMedidasgEntities(false, maxResults, firstResult);
    }

    private List<UciEvoMedidasg> findUciEvoMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoMedidasg.class));
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

    public UciEvoMedidasg findUciEvoMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoMedidasgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoMedidasg> rt = cq.from(UciEvoMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

   public List<UciEvoMedidasg> ListFindUciEvoMedidasG(UciEvolucion evol){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UciEvoMedidasg h WHERE h.idUciEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evol)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

}
