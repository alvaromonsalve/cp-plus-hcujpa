
package jpa;

import entidades_EJB.UciEvoInterconsulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciEvolucion;
import entidades_EJB.StaticEspecialidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UciEvoInterconsultaJpaController implements Serializable {

    public UciEvoInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoInterconsulta uciEvoInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvolucion idUciEvolucion = uciEvoInterconsulta.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion = em.getReference(idUciEvolucion.getClass(), idUciEvolucion.getId());
                uciEvoInterconsulta.setIdUciEvolucion(idUciEvolucion);
            }
            em.persist(uciEvoInterconsulta);
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoInterconsultas().add(uciEvoInterconsulta);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoInterconsulta uciEvoInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvoInterconsulta persistentUciEvoInterconsulta = em.find(UciEvoInterconsulta.class, uciEvoInterconsulta.getId());
            UciEvolucion idUciEvolucionOld = persistentUciEvoInterconsulta.getIdUciEvolucion();
            UciEvolucion idUciEvolucionNew = uciEvoInterconsulta.getIdUciEvolucion();
            if (idUciEvolucionNew != null) {
                idUciEvolucionNew = em.getReference(idUciEvolucionNew.getClass(), idUciEvolucionNew.getId());
                uciEvoInterconsulta.setIdUciEvolucion(idUciEvolucionNew);
            }
            uciEvoInterconsulta = em.merge(uciEvoInterconsulta);
            if (idUciEvolucionOld != null && !idUciEvolucionOld.equals(idUciEvolucionNew)) {
                idUciEvolucionOld.getUciEvoInterconsultas().remove(uciEvoInterconsulta);
                idUciEvolucionOld = em.merge(idUciEvolucionOld);
            }
            if (idUciEvolucionNew != null && !idUciEvolucionNew.equals(idUciEvolucionOld)) {
                idUciEvolucionNew.getUciEvoInterconsultas().add(uciEvoInterconsulta);
                idUciEvolucionNew = em.merge(idUciEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoInterconsulta.getId();
                if (findUciEvoInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The uciEvoInterconsulta with id " + id + " no longer exists.");
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
            UciEvoInterconsulta uciEvoInterconsulta;
            try {
                uciEvoInterconsulta = em.getReference(UciEvoInterconsulta.class, id);
                uciEvoInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoInterconsulta with id " + id + " no longer exists.", enfe);
            }
            UciEvolucion idUciEvolucion = uciEvoInterconsulta.getIdUciEvolucion();
            if (idUciEvolucion != null) {
                idUciEvolucion.getUciEvoInterconsultas().remove(uciEvoInterconsulta);
                idUciEvolucion = em.merge(idUciEvolucion);
            }
            em.remove(uciEvoInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoInterconsulta> findUciEvoInterconsultaEntities() {
        return findUciEvoInterconsultaEntities(true, -1, -1);
    }

    public List<UciEvoInterconsulta> findUciEvoInterconsultaEntities(int maxResults, int firstResult) {
        return findUciEvoInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<UciEvoInterconsulta> findUciEvoInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoInterconsulta.class));
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

    public UciEvoInterconsulta findUciEvoInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoInterconsulta> rt = cq.from(UciEvoInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public UciEvoInterconsulta findUciInterconsulta_EVO(UciEvolucion evo,StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            return (UciEvoInterconsulta) em.createQuery("SELECT h FROM UciEvoInterconsulta h WHERE h.idUciEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado ='1'")
                    .setParameter("evo", evo)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        }catch(Exception ex){ return null;
        } finally {
            em.close();
        }
    }

        public List<UciEvoInterconsulta> listInterconsultaOtrasEvo(UciEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UciEvoInterconsulta h WHERE h.idUciEvolucion = :evo "
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

    public Long CountInterconsultas(UciEvolucion evo, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UcipEvoInterconsulta h WHERE h.idUciEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado='2'")
            .setParameter("evo", evo)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }



}
