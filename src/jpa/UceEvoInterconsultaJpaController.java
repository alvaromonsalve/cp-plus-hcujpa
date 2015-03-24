
package jpa;

import entidades.UceEvoInterconsulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UceEvolucion;
import entidades.StaticEspecialidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UceEvoInterconsultaJpaController implements Serializable {

    public UceEvoInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoInterconsulta uceEvoInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvolucion idUceEvolucion = uceEvoInterconsulta.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion = em.getReference(idUceEvolucion.getClass(), idUceEvolucion.getId());
                uceEvoInterconsulta.setIdUceEvolucion(idUceEvolucion);
            }
            em.persist(uceEvoInterconsulta);
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoInterconsultas().add(uceEvoInterconsulta);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoInterconsulta uceEvoInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvoInterconsulta persistentUceEvoInterconsulta = em.find(UceEvoInterconsulta.class, uceEvoInterconsulta.getId());
            UceEvolucion idUceEvolucionOld = persistentUceEvoInterconsulta.getIdUceEvolucion();
            UceEvolucion idUceEvolucionNew = uceEvoInterconsulta.getIdUceEvolucion();
            if (idUceEvolucionNew != null) {
                idUceEvolucionNew = em.getReference(idUceEvolucionNew.getClass(), idUceEvolucionNew.getId());
                uceEvoInterconsulta.setIdUceEvolucion(idUceEvolucionNew);
            }
            uceEvoInterconsulta = em.merge(uceEvoInterconsulta);
            if (idUceEvolucionOld != null && !idUceEvolucionOld.equals(idUceEvolucionNew)) {
                idUceEvolucionOld.getUceEvoInterconsultas().remove(uceEvoInterconsulta);
                idUceEvolucionOld = em.merge(idUceEvolucionOld);
            }
            if (idUceEvolucionNew != null && !idUceEvolucionNew.equals(idUceEvolucionOld)) {
                idUceEvolucionNew.getUceEvoInterconsultas().add(uceEvoInterconsulta);
                idUceEvolucionNew = em.merge(idUceEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoInterconsulta.getId();
                if (findUceEvoInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The uceEvoInterconsulta with id " + id + " no longer exists.");
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
            UceEvoInterconsulta uceEvoInterconsulta;
            try {
                uceEvoInterconsulta = em.getReference(UceEvoInterconsulta.class, id);
                uceEvoInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoInterconsulta with id " + id + " no longer exists.", enfe);
            }
            UceEvolucion idUceEvolucion = uceEvoInterconsulta.getIdUceEvolucion();
            if (idUceEvolucion != null) {
                idUceEvolucion.getUceEvoInterconsultas().remove(uceEvoInterconsulta);
                idUceEvolucion = em.merge(idUceEvolucion);
            }
            em.remove(uceEvoInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoInterconsulta> findUceEvoInterconsultaEntities() {
        return findUceEvoInterconsultaEntities(true, -1, -1);
    }

    public List<UceEvoInterconsulta> findUceEvoInterconsultaEntities(int maxResults, int firstResult) {
        return findUceEvoInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<UceEvoInterconsulta> findUceEvoInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoInterconsulta.class));
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

    public UceEvoInterconsulta findUceEvoInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoInterconsulta> rt = cq.from(UceEvoInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public UceEvoInterconsulta findUceInterconsulta_EVO(UceEvolucion evo,StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            return (UceEvoInterconsulta) em.createQuery("SELECT h FROM UceEvoInterconsulta h WHERE h.idUceEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado ='1'")
                    .setParameter("evo", evo)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        }catch(Exception ex){ return null;
        } finally {
            em.close();
        }
    }

        public List<UceEvoInterconsulta> listInterconsultaOtrasEvo(UceEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UceEvoInterconsulta h WHERE h.idUceEvolucion = :evo "
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

    public Long CountInterconsultas(UceEvolucion evo, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UcepEvoInterconsulta h WHERE h.idUceEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado='2'")
            .setParameter("evo", evo)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }



}
