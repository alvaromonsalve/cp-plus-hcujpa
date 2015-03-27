
package jpa;

import entidades_EJB.UceHistoriac;
import entidades_EJB.UcePosologia;
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
public class UcePosologiaJpaController implements Serializable {

    public UcePosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UcePosologia ucePosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ucePosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UcePosologia ucePosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ucePosologia = em.merge(ucePosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ucePosologia.getId();
                if (findUcePosologia(id) == null) {
                    throw new NonexistentEntityException("The ucePosologia with id " + id + " no longer exists.");
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
            UcePosologia ucePosologia;
            try {
                ucePosologia = em.getReference(UcePosologia.class, id);
                ucePosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ucePosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(ucePosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UcePosologia> findUcePosologiaEntities() {
        return findUcePosologiaEntities(true, -1, -1);
    }

    public List<UcePosologia> findUcePosologiaEntities(int maxResults, int firstResult) {
        return findUcePosologiaEntities(false, maxResults, firstResult);
    }

    private List<UcePosologia> findUcePosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UcePosologia.class));
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

    public UcePosologia findUcePosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UcePosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getUcePosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UcePosologia> rt = cq.from(UcePosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

  //Codigo no Auto-generado
   public List<UcePosologia> ListFindUcePosologia(UceHistoriac ihc){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM UcePosologia i WHERE i.idHistoriac = :hc");
            q.setParameter("hc", ihc);
            return q.getResultList();
        } finally {
            em.close();
        }
   }

}
