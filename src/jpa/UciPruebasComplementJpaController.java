
package jpa;

import entidades.UciHistoriac;
import entidades.UciPruebasComplement;
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
public class UciPruebasComplementJpaController implements Serializable {

    public UciPruebasComplementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciPruebasComplement uciPruebasComplement) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciHistoriac idUcihistoriac = uciPruebasComplement.getIdUcihistoriac();
            if (idUcihistoriac != null) {
                idUcihistoriac = em.getReference(idUcihistoriac.getClass(), idUcihistoriac.getId());
                uciPruebasComplement.setIdHosphistoriac(idUcihistoriac);
            }
            em.persist(uciPruebasComplement);
            if (idUcihistoriac != null) {
                idUcihistoriac.getUciPruebasComplements().add(uciPruebasComplement);
                idUcihistoriac = em.merge(idUcihistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciPruebasComplement uciPruebasComplement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciPruebasComplement persistentUciPruebasComplement = em.find(UciPruebasComplement.class, uciPruebasComplement.getId());
            UciHistoriac idUcihistoriacOld = persistentUciPruebasComplement.getIdUcihistoriac();
            UciHistoriac idUcihistoriacNew = uciPruebasComplement.getIdUcihistoriac();
            if (idUcihistoriacNew != null) {
                idUcihistoriacNew = em.getReference(idUcihistoriacNew.getClass(), idUcihistoriacNew.getId());
                uciPruebasComplement.setIdHosphistoriac(idUcihistoriacNew);
            }
            uciPruebasComplement = em.merge(uciPruebasComplement);
            if (idUcihistoriacOld != null && !idUcihistoriacOld.equals(idUcihistoriacNew)) {
                idUcihistoriacOld.getUciPruebasComplements().remove(uciPruebasComplement);
                idUcihistoriacOld = em.merge(idUcihistoriacOld);
            }
            if (idUcihistoriacNew != null && !idUcihistoriacNew.equals(idUcihistoriacOld)) {
                idUcihistoriacNew.getUciPruebasComplements().add(uciPruebasComplement);
                idUcihistoriacNew = em.merge(idUcihistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciPruebasComplement.getId();
                if (findUciPruebasComplement(id) == null) {
                    throw new NonexistentEntityException("The uciPruebasComplement with id " + id + " no longer exists.");
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
            UciPruebasComplement uciPruebasComplement;
            try {
                uciPruebasComplement = em.getReference(UciPruebasComplement.class, id);
                uciPruebasComplement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciPruebasComplement with id " + id + " no longer exists.", enfe);
            }
            UciHistoriac idUcihistoriac = uciPruebasComplement.getIdUcihistoriac();
            if (idUcihistoriac != null) {
                idUcihistoriac.getUciPruebasComplements().remove(uciPruebasComplement);
                idUcihistoriac = em.merge(idUcihistoriac);
            }
            em.remove(uciPruebasComplement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciPruebasComplement> findUciPruebasComplementEntities() {
        return findUciPruebasComplementEntities(true, -1, -1);
    }

    public List<UciPruebasComplement> findUciPruebasComplementEntities(int maxResults, int firstResult) {
        return findUciPruebasComplementEntities(false, maxResults, firstResult);
    }

    private List<UciPruebasComplement> findUciPruebasComplementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciPruebasComplement.class));
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

    public UciPruebasComplement findUciPruebasComplement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciPruebasComplement.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciPruebasComplementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciPruebasComplement> rt = cq.from(UciPruebasComplement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //codigo no Auto-generado


    public List<UciPruebasComplement> getListUcePruebasComplement(UciHistoriac uciHistoriac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UciPruebasComplement i WHERE i.idUcihistoriac = :idUcihistoriac");
            q.setParameter("idUcihistoriac", uciHistoriac);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
