
package jpa;

import entidades_EJB.HospEvoEgreso;
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
 * @author Administrador
 */
public class HospEvoEgresoJpaController implements Serializable {

    public HospEvoEgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoEgreso hospEvoEgreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvolucion idHospEvolucion = hospEvoEgreso.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                hospEvoEgreso.setIdHospEvolucion(idHospEvolucion);
            }
            em.persist(hospEvoEgreso);
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoEgreso().add(hospEvoEgreso);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoEgreso hospEvoEgreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvoEgreso persistentHospEvoEgreso = em.find(HospEvoEgreso.class, hospEvoEgreso.getId());
            HospEvolucion idHospEvolucionOld = persistentHospEvoEgreso.getIdHospEvolucion();
            HospEvolucion idHospEvolucionNew = hospEvoEgreso.getIdHospEvolucion();
            if (idHospEvolucionNew != null) {
                idHospEvolucionNew = em.getReference(idHospEvolucionNew.getClass(), idHospEvolucionNew.getId());
                hospEvoEgreso.setIdHospEvolucion(idHospEvolucionNew);
            }
            hospEvoEgreso = em.merge(hospEvoEgreso);
            if (idHospEvolucionOld != null && !idHospEvolucionOld.equals(idHospEvolucionNew)) {
                idHospEvolucionOld.getHospEvoEgreso().remove(hospEvoEgreso);
                idHospEvolucionOld = em.merge(idHospEvolucionOld);
            }
            if (idHospEvolucionNew != null && !idHospEvolucionNew.equals(idHospEvolucionOld)) {
                idHospEvolucionNew.getHospEvoEgreso().add(hospEvoEgreso);
                idHospEvolucionNew = em.merge(idHospEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoEgreso.getId();
                if (findHospEvoEgreso(id) == null) {
                    throw new NonexistentEntityException("The hospEvoEgreso with id " + id + " no longer exists.");
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
            HospEvoEgreso hospEvoEgreso;
            try {
                hospEvoEgreso = em.getReference(HospEvoEgreso.class, id);
                hospEvoEgreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoEgreso with id " + id + " no longer exists.", enfe);
            }
            HospEvolucion idHospEvolucion = hospEvoEgreso.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoEgreso().remove(hospEvoEgreso);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(hospEvoEgreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoEgreso> findHospEvoEgresoEntities() {
        return findHospEvoEgresoEntities(true, -1, -1);
    }

    public List<HospEvoEgreso> findHospEvoEgresoEntities(int maxResults, int firstResult) {
        return findHospEvoEgresoEntities(false, maxResults, firstResult);
    }

    private List<HospEvoEgreso> findHospEvoEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoEgreso.class));
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

    public HospEvoEgreso findHospEvoEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoEgreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoEgreso> rt = cq.from(HospEvoEgreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
