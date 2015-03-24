package jpa;

import entidades.UceHistoriac;
import entidades.UceInterconsulta;
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
public class UceInterconsultaJpaController implements Serializable {

    public UceInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInterconsulta uceInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInterconsulta uceInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceInterconsulta = em.merge(uceInterconsulta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInterconsulta.getId();
                if (findUceInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The uceInterconsulta with id " + id + " no longer exists.");
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
            UceInterconsulta uceInterconsulta;
            try {
                uceInterconsulta = em.getReference(UceInterconsulta.class, id);
                uceInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInterconsulta with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInterconsulta> findUceInterconsultaEntities() {
        return findUceInterconsultaEntities(true, -1, -1);
    }

    public List<UceInterconsulta> findUceInterconsultaEntities(int maxResults, int firstResult) {
        return findUceInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<UceInterconsulta> findUceInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInterconsulta.class));
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

    public UceInterconsulta findUceInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInterconsulta> rt = cq.from(UceInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado

    public UceInterconsulta findUceInterconsulta_HC(UceHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UceInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se");
            q.setParameter("ih", ih.getId());
            q.setParameter("se", se.getId());
            return (UceInterconsulta)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }

    public List<UceInterconsulta> listUceInterconsultaOtras_HC(UceHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UceInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades <> 28 "
                    + "AND i.idEspecialidades <> 22 AND i.idEspecialidades <> 14 AND i.idEspecialidades <> 10 AND i.idEspecialidades <> 3");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<UceInterconsulta> listUceInterconsulta(UceHistoriac ih){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM UceInterconsulta i WHERE i.idHistoriac = :ih");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

   public Long CountUceInterconsultas(UceHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(i) FROM UceInterconsulta i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se")
            .setParameter("ih", ih.getId())
            .setParameter("se", se.getId())
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

}
