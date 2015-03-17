
package jpa;

import entidades.HospHistoriac;
import entidades.HospPruebasComplement;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospPruebasComplementJpaController implements Serializable {

    public HospPruebasComplementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospPruebasComplement hospPruebasComplement) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospHistoriac idHosphistoriac = hospPruebasComplement.getIdHosphistoriac();
            if (idHosphistoriac != null) {
                idHosphistoriac = em.getReference(idHosphistoriac.getClass(), idHosphistoriac.getId());
                hospPruebasComplement.setIdHosphistoriac(idHosphistoriac);
            }
            em.persist(hospPruebasComplement);
            if (idHosphistoriac != null) {
                idHosphistoriac.getHospPruebasComplements().add(hospPruebasComplement);
                idHosphistoriac = em.merge(idHosphistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospPruebasComplement hospPruebasComplement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospPruebasComplement persistentHospPruebasComplement = em.find(HospPruebasComplement.class, hospPruebasComplement.getId());
            HospHistoriac idHosphistoriacOld = persistentHospPruebasComplement.getIdHosphistoriac();
            HospHistoriac idHosphistoriacNew = hospPruebasComplement.getIdHosphistoriac();
            if (idHosphistoriacNew != null) {
                idHosphistoriacNew = em.getReference(idHosphistoriacNew.getClass(), idHosphistoriacNew.getId());
                hospPruebasComplement.setIdHosphistoriac(idHosphistoriacNew);
            }
            hospPruebasComplement = em.merge(hospPruebasComplement);
            if (idHosphistoriacOld != null && !idHosphistoriacOld.equals(idHosphistoriacNew)) {
                idHosphistoriacOld.getHospPruebasComplements().remove(hospPruebasComplement);
                idHosphistoriacOld = em.merge(idHosphistoriacOld);
            }
            if (idHosphistoriacNew != null && !idHosphistoriacNew.equals(idHosphistoriacOld)) {
                idHosphistoriacNew.getHospPruebasComplements().add(hospPruebasComplement);
                idHosphistoriacNew = em.merge(idHosphistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospPruebasComplement.getId();
                if (findHospPruebasComplement(id) == null) {
                    throw new NonexistentEntityException("The hospPruebasComplement with id " + id + " no longer exists.");
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
            HospPruebasComplement hospPruebasComplement;
            try {
                hospPruebasComplement = em.getReference(HospPruebasComplement.class, id);
                hospPruebasComplement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospPruebasComplement with id " + id + " no longer exists.", enfe);
            }
            HospHistoriac idHosphistoriac = hospPruebasComplement.getIdHosphistoriac();
            if (idHosphistoriac != null) {
                idHosphistoriac.getHospPruebasComplements().remove(hospPruebasComplement);
                idHosphistoriac = em.merge(idHosphistoriac);
            }
            em.remove(hospPruebasComplement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospPruebasComplement> findHospPruebasComplementEntities() {
        return findHospPruebasComplementEntities(true, -1, -1);
    }

    public List<HospPruebasComplement> findHospPruebasComplementEntities(int maxResults, int firstResult) {
        return findHospPruebasComplementEntities(false, maxResults, firstResult);
    }

    private List<HospPruebasComplement> findHospPruebasComplementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospPruebasComplement.class));
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

    public HospPruebasComplement findHospPruebasComplement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospPruebasComplement.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospPruebasComplementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospPruebasComplement> rt = cq.from(HospPruebasComplement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //codigo no Auto-generado


    public List<HospPruebasComplement> getListHospPruebasComplement(HospHistoriac hospHistoriac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM HospPruebasComplement i WHERE i.idHosphistoriac = :idHosphistoriac");
            q.setParameter("idHosphistoriac", hospHistoriac);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
