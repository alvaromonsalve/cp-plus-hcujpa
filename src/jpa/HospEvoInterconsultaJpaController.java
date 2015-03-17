
package jpa;

import entidades.HospEvoInterconsulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HospEvolucion;
import entidades.StaticEspecialidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospEvoInterconsultaJpaController implements Serializable {

    public HospEvoInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoInterconsulta hospEvoInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvolucion idHospEvolucion = hospEvoInterconsulta.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion = em.getReference(idHospEvolucion.getClass(), idHospEvolucion.getId());
                hospEvoInterconsulta.setIdHospEvolucion(idHospEvolucion);
            }
            em.persist(hospEvoInterconsulta);
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoInterconsultas().add(hospEvoInterconsulta);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoInterconsulta hospEvoInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvoInterconsulta persistentHospEvoInterconsulta = em.find(HospEvoInterconsulta.class, hospEvoInterconsulta.getId());
            HospEvolucion idHospEvolucionOld = persistentHospEvoInterconsulta.getIdHospEvolucion();
            HospEvolucion idHospEvolucionNew = hospEvoInterconsulta.getIdHospEvolucion();
            if (idHospEvolucionNew != null) {
                idHospEvolucionNew = em.getReference(idHospEvolucionNew.getClass(), idHospEvolucionNew.getId());
                hospEvoInterconsulta.setIdHospEvolucion(idHospEvolucionNew);
            }
            hospEvoInterconsulta = em.merge(hospEvoInterconsulta);
            if (idHospEvolucionOld != null && !idHospEvolucionOld.equals(idHospEvolucionNew)) {
                idHospEvolucionOld.getHospEvoInterconsultas().remove(hospEvoInterconsulta);
                idHospEvolucionOld = em.merge(idHospEvolucionOld);
            }
            if (idHospEvolucionNew != null && !idHospEvolucionNew.equals(idHospEvolucionOld)) {
                idHospEvolucionNew.getHospEvoInterconsultas().add(hospEvoInterconsulta);
                idHospEvolucionNew = em.merge(idHospEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoInterconsulta.getId();
                if (findHospEvoInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The hospEvoInterconsulta with id " + id + " no longer exists.");
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
            HospEvoInterconsulta hospEvoInterconsulta;
            try {
                hospEvoInterconsulta = em.getReference(HospEvoInterconsulta.class, id);
                hospEvoInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoInterconsulta with id " + id + " no longer exists.", enfe);
            }
            HospEvolucion idHospEvolucion = hospEvoInterconsulta.getIdHospEvolucion();
            if (idHospEvolucion != null) {
                idHospEvolucion.getHospEvoInterconsultas().remove(hospEvoInterconsulta);
                idHospEvolucion = em.merge(idHospEvolucion);
            }
            em.remove(hospEvoInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoInterconsulta> findHospEvoInterconsultaEntities() {
        return findHospEvoInterconsultaEntities(true, -1, -1);
    }

    public List<HospEvoInterconsulta> findHospEvoInterconsultaEntities(int maxResults, int firstResult) {
        return findHospEvoInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<HospEvoInterconsulta> findHospEvoInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoInterconsulta.class));
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

    public HospEvoInterconsulta findHospEvoInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoInterconsulta> rt = cq.from(HospEvoInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public HospEvoInterconsulta findEvoInterconsulta_EVO(HospEvolucion evo,StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            return (HospEvoInterconsulta) em.createQuery("SELECT h FROM HospEvoInterconsulta h WHERE h.idHospEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado ='1'")
                    .setParameter("evo", evo)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        }catch(Exception ex){ return null;
        } finally {
            em.close();
        }
    }

        public List<HospEvoInterconsulta> listInterconsultaOtrasEvo(HospEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HospEvoInterconsulta h WHERE h.idHospEvolucion = :evo "
                    + "AND h.idStaticEspecialidades.id <> 28 AND h.idStaticEspecialidades.id <> 22 "
                    + "AND h.idStaticEspecialidades.id <> 14 AND h.idStaticEspecialidades.id <> 10 "
                    + "AND h.idStaticEspecialidades.id <> 39 "
                    + "AND h.idStaticEspecialidades.id <> 3 AND h.estado = '1'")
                    .setParameter("evo", evo)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Long CountInterconsultas(HospEvolucion evo, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HospEvoInterconsulta h WHERE h.idHospEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado='2'")
            .setParameter("evo", evo)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }



}
