
package jpa;

import entidades_EJB.UceHistoriac;
import entidades_EJB.UcePruebasComplement;
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
public class UcePruebasComplementJpaController implements Serializable {

    public UcePruebasComplementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UcePruebasComplement ucePruebasComplement) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceHistoriac idUcehistoriac = ucePruebasComplement.getIdUcehistoriac();
            if (idUcehistoriac != null) {
                idUcehistoriac = em.getReference(idUcehistoriac.getClass(), idUcehistoriac.getId());
                ucePruebasComplement.setIdHosphistoriac(idUcehistoriac);
            }
            em.persist(ucePruebasComplement);
            if (idUcehistoriac != null) {
                idUcehistoriac.getUcePruebasComplements().add(ucePruebasComplement);
                idUcehistoriac = em.merge(idUcehistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UcePruebasComplement ucePruebasComplement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UcePruebasComplement persistentUcePruebasComplement = em.find(UcePruebasComplement.class, ucePruebasComplement.getId());
            UceHistoriac idUcehistoriacOld = persistentUcePruebasComplement.getIdUcehistoriac();
            UceHistoriac idUcehistoriacNew = ucePruebasComplement.getIdUcehistoriac();
            if (idUcehistoriacNew != null) {
                idUcehistoriacNew = em.getReference(idUcehistoriacNew.getClass(), idUcehistoriacNew.getId());
                ucePruebasComplement.setIdHosphistoriac(idUcehistoriacNew);
            }
            ucePruebasComplement = em.merge(ucePruebasComplement);
            if (idUcehistoriacOld != null && !idUcehistoriacOld.equals(idUcehistoriacNew)) {
                idUcehistoriacOld.getUcePruebasComplements().remove(ucePruebasComplement);
                idUcehistoriacOld = em.merge(idUcehistoriacOld);
            }
            if (idUcehistoriacNew != null && !idUcehistoriacNew.equals(idUcehistoriacOld)) {
                idUcehistoriacNew.getUcePruebasComplements().add(ucePruebasComplement);
                idUcehistoriacNew = em.merge(idUcehistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ucePruebasComplement.getId();
                if (findUcePruebasComplement(id) == null) {
                    throw new NonexistentEntityException("The ucePruebasComplement with id " + id + " no longer exists.");
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
            UcePruebasComplement ucePruebasComplement;
            try {
                ucePruebasComplement = em.getReference(UcePruebasComplement.class, id);
                ucePruebasComplement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ucePruebasComplement with id " + id + " no longer exists.", enfe);
            }
            UceHistoriac idUcehistoriac = ucePruebasComplement.getIdUcehistoriac();
            if (idUcehistoriac != null) {
                idUcehistoriac.getUcePruebasComplements().remove(ucePruebasComplement);
                idUcehistoriac = em.merge(idUcehistoriac);
            }
            em.remove(ucePruebasComplement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UcePruebasComplement> findUcePruebasComplementEntities() {
        return findUcePruebasComplementEntities(true, -1, -1);
    }

    public List<UcePruebasComplement> findUcePruebasComplementEntities(int maxResults, int firstResult) {
        return findUcePruebasComplementEntities(false, maxResults, firstResult);
    }

    private List<UcePruebasComplement> findUcePruebasComplementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UcePruebasComplement.class));
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

    public UcePruebasComplement findUcePruebasComplement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UcePruebasComplement.class, id);
        } finally {
            em.close();
        }
    }

    public int getUcePruebasComplementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UcePruebasComplement> rt = cq.from(UcePruebasComplement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //codigo no Auto-generado


    public List<UcePruebasComplement> getListUcePruebasComplement(UceHistoriac uceHistoriac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM UcePruebasComplement i WHERE i.idUcehistoriac = :idUcehistoriac");
            q.setParameter("idUcehistoriac", uceHistoriac);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
