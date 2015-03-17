package jpa;

import entidades.HospHistoriac;
import entidades.HospInterconsulta;
import entidades.StaticEspecialidades;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospInterconsultaJpaController implements Serializable {

    public HospInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInterconsulta hospInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInterconsulta hospInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospInterconsulta = em.merge(hospInterconsulta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInterconsulta.getId();
                if (findHospInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The hospInterconsulta with id " + id + " no longer exists.");
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
            HospInterconsulta hospInterconsulta;
            try {
                hospInterconsulta = em.getReference(HospInterconsulta.class, id);
                hospInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInterconsulta with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInterconsulta> findHospInterconsultaEntities() {
        return findHospInterconsultaEntities(true, -1, -1);
    }

    public List<HospInterconsulta> findHospInterconsultaEntities(int maxResults, int firstResult) {
        return findHospInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<HospInterconsulta> findHospInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInterconsulta.class));
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

    public HospInterconsulta findHospInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInterconsulta> rt = cq.from(HospInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public HospInterconsulta findHospInterconsulta_HC(HospHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM HospInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se");
            q.setParameter("ih", ih.getId());
            q.setParameter("se", se.getId());
            return (HospInterconsulta)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }

    public List<HospInterconsulta> listHospInterconsultaOtras_HC(HospHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM HospInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades <> 28 "
                    + "AND i.idEspecialidades <> 22 AND i.idEspecialidades <> 14 AND i.idEspecialidades <> 10 AND i.idEspecialidades <> 3");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<HospInterconsulta> listHospInterconsulta(HospHistoriac ih){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM HospInterconsulta i WHERE i.idHistoriac = :ih");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

   public Long CountHospInterconsultas(HospHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(i) FROM HospInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se")
            .setParameter("ih", ih.getId())
            .setParameter("se", se.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

}
