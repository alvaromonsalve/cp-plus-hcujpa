
package jpa;

import entidades.UciEvoEgreso;
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
 * @author Administrador
 */
public class UciEvoEgresoJpaController implements Serializable {

    public UciEvoEgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoEgreso uciEvoEgreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvolucion idUciEvolucion = uciEvoEgreso.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion = em.getReference(idUciEvolucion.getClass(), idUciEvolucion.getId());
                uciEvoEgreso.setIdUciEvolucion(idUciEvolucion);
            }
            em.persist(uciEvoEgreso);
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoEgreso().add(uciEvoEgreso);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoEgreso uciEvoEgreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvoEgreso persistentUciEvoEgreso = em.find(UciEvoEgreso.class, uciEvoEgreso.getId());
            UciEvolucion idUciEvolucionOld = persistentUciEvoEgreso.getIdUciEvolucion();
            UciEvolucion idUciEvolucionNew = uciEvoEgreso.getIdUciEvolucion();
            if (idUciEvolucionNew != null) {
                idUciEvolucionNew = em.getReference(idUciEvolucionNew.getClass(), idUciEvolucionNew.getId());
                uciEvoEgreso.setIdUciEvolucion(idUciEvolucionNew);
            }
            uciEvoEgreso = em.merge(uciEvoEgreso);
            if (idUciEvolucionOld != null && !idUciEvolucionOld.equals(idUciEvolucionNew)) {
                idUciEvolucionOld.getUciEvoEgreso().remove(uciEvoEgreso);
                idUciEvolucionOld = em.merge(idUciEvolucionOld);
            }
            if (idUciEvolucionNew != null && !idUciEvolucionNew.equals(idUciEvolucionOld)) {
                idUciEvolucionNew.getUciEvoEgreso().add(uciEvoEgreso);
                idUciEvolucionNew = em.merge(idUciEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoEgreso.getId();
                if (findUciEvoEgreso(id) == null) {
                    throw new NonexistentEntityException("The uciEvoEgreso with id " + id + " no longer exists.");
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
            UciEvoEgreso uciEvoEgreso;
            try {
                uciEvoEgreso = em.getReference(UciEvoEgreso.class, id);
                uciEvoEgreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoEgreso with id " + id + " no longer exists.", enfe);
            }
            UciEvolucion idUciEvolucion = uciEvoEgreso.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoEgreso().remove(uciEvoEgreso);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.remove(uciEvoEgreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoEgreso> findUciEvoEgresoEntities() {
        return findUciEvoEgresoEntities(true, -1, -1);
    }

    public List<UciEvoEgreso> findUciEvoEgresoEntities(int maxResults, int firstResult) {
        return findUciEvoEgresoEntities(false, maxResults, firstResult);
    }

    private List<UciEvoEgreso> findUciEvoEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoEgreso.class));
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

    public UciEvoEgreso findUciEvoEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoEgreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoEgreso> rt = cq.from(UciEvoEgreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
