
package jpa;

import entidades_EJB.UceEvoEgreso;
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
 * @author Administrador
 */
public class UceEvoEgresoJpaController implements Serializable {

    public UceEvoEgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoEgreso uceEvoEgreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvolucion idUceEvolucion = uceEvoEgreso.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion = em.getReference(idUceEvolucion.getClass(), idUceEvolucion.getId());
                uceEvoEgreso.setIdUceEvolucion(idUceEvolucion);
            }
            em.persist(uceEvoEgreso);
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoEgreso().add(uceEvoEgreso);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoEgreso uceEvoEgreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvoEgreso persistentUceEvoEgreso = em.find(UceEvoEgreso.class, uceEvoEgreso.getId());
            UceEvolucion idUceEvolucionOld = persistentUceEvoEgreso.getIdUceEvolucion();
            UceEvolucion idUceEvolucionNew = uceEvoEgreso.getIdUceEvolucion();
            if (idUceEvolucionNew != null) {
                idUceEvolucionNew = em.getReference(idUceEvolucionNew.getClass(), idUceEvolucionNew.getId());
                uceEvoEgreso.setIdUceEvolucion(idUceEvolucionNew);
            }
            uceEvoEgreso = em.merge(uceEvoEgreso);
            if (idUceEvolucionOld != null && !idUceEvolucionOld.equals(idUceEvolucionNew)) {
                idUceEvolucionOld.getUceEvoEgreso().remove(uceEvoEgreso);
                idUceEvolucionOld = em.merge(idUceEvolucionOld);
            }
            if (idUceEvolucionNew != null && !idUceEvolucionNew.equals(idUceEvolucionOld)) {
                idUceEvolucionNew.getUceEvoEgreso().add(uceEvoEgreso);
                idUceEvolucionNew = em.merge(idUceEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoEgreso.getId();
                if (findUceEvoEgreso(id) == null) {
                    throw new NonexistentEntityException("The uceEvoEgreso with id " + id + " no longer exists.");
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
            UceEvoEgreso uceEvoEgreso;
            try {
                uceEvoEgreso = em.getReference(UceEvoEgreso.class, id);
                uceEvoEgreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoEgreso with id " + id + " no longer exists.", enfe);
            }
            UceEvolucion idUceEvolucion = uceEvoEgreso.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoEgreso().remove(uceEvoEgreso);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.remove(uceEvoEgreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoEgreso> findUceEvoEgresoEntities() {
        return findUceEvoEgresoEntities(true, -1, -1);
    }

    public List<UceEvoEgreso> findUceEvoEgresoEntities(int maxResults, int firstResult) {
        return findUceEvoEgresoEntities(false, maxResults, firstResult);
    }

    private List<UceEvoEgreso> findUceEvoEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoEgreso.class));
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

    public UceEvoEgreso findUceEvoEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoEgreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoEgreso> rt = cq.from(UceEvoEgreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
