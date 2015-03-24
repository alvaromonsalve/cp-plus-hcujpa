package jpa;

import entidades.UciHistoriac;
import entidades.UciInterconsulta;
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
public class UciInterconsultaJpaController implements Serializable {

    public UciInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInterconsulta uciInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInterconsulta uciInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciInterconsulta = em.merge(uciInterconsulta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInterconsulta.getId();
                if (findUciInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The uciInterconsulta with id " + id + " no longer exists.");
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
            UciInterconsulta uciInterconsulta;
            try {
                uciInterconsulta = em.getReference(UciInterconsulta.class, id);
                uciInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInterconsulta with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInterconsulta> findUciInterconsultaEntities() {
        return findUciInterconsultaEntities(true, -1, -1);
    }

    public List<UciInterconsulta> findUciInterconsultaEntities(int maxResults, int firstResult) {
        return findUciInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<UciInterconsulta> findUciInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInterconsulta.class));
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

    public UciInterconsulta findUciInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInterconsulta> rt = cq.from(UciInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public UciInterconsulta findUciInterconsulta_HC(UciHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UciInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se");
            q.setParameter("ih", ih.getId());
            q.setParameter("se", se.getId());
            return (UciInterconsulta)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }

    public List<UciInterconsulta> listUciInterconsultaOtras_HC(UciHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UciInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades <> 28 "
                    + "AND i.idEspecialidades <> 22 AND i.idEspecialidades <> 14 AND i.idEspecialidades <> 10 AND i.idEspecialidades <> 3");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<UciInterconsulta> listUciInterconsulta(UciHistoriac ih){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM UciInterconsulta i WHERE i.idHistoriac = :ih");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

   public Long CountUciInterconsultas(UciHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(i) FROM UciInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se")
            .setParameter("ih", ih.getId())
            .setParameter("se", se.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

}
