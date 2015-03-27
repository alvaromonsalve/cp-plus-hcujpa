package jpa;

import entidades_EJB.UceHcExpfisica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UceHcExpfisicaJpaController implements Serializable {

    public UceHcExpfisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceHcExpfisica uceHcExpfisica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceHistoriac idUcehistoriac = uceHcExpfisica.getIdUcehistoriac();
            if (idUcehistoriac != null) {
                idUcehistoriac = em.getReference(idUcehistoriac.getClass(), idUcehistoriac.getId());
                uceHcExpfisica.setIdUcehistoriac(idUcehistoriac);
            }
            em.persist(uceHcExpfisica);
            if (idUcehistoriac != null) {
                UceHcExpfisica oldUceHcExpfisicaOfIdUcehistoriac = idUcehistoriac.getUceHcExpfisica();
                if (oldUceHcExpfisicaOfIdUcehistoriac != null) {
                    oldUceHcExpfisicaOfIdUcehistoriac.setIdUcehistoriac(null);
                    oldUceHcExpfisicaOfIdUcehistoriac = em.merge(oldUceHcExpfisicaOfIdUcehistoriac);
                }
                idUcehistoriac.setUceHcExpfisica(uceHcExpfisica);
                idUcehistoriac = em.merge(idUcehistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceHcExpfisica uceHcExpfisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceHcExpfisica persistentUceHcExpfisica = em.find(UceHcExpfisica.class, uceHcExpfisica.getId());
            UceHistoriac idUcehistoriacOld = persistentUceHcExpfisica.getIdUcehistoriac();
            UceHistoriac idUcehistoriacNew = uceHcExpfisica.getIdUcehistoriac();
            if (idUcehistoriacNew != null) {
                idUcehistoriacNew = em.getReference(idUcehistoriacNew.getClass(), idUcehistoriacNew.getId());
                uceHcExpfisica.setIdUcehistoriac(idUcehistoriacNew);
            }
            uceHcExpfisica = em.merge(uceHcExpfisica);
            if (idUcehistoriacOld != null && !idUcehistoriacOld.equals(idUcehistoriacNew)) {
                idUcehistoriacOld.setUceHcExpfisica(null);
                idUcehistoriacOld = em.merge(idUcehistoriacOld);
            }
            if (idUcehistoriacNew != null && !idUcehistoriacNew.equals(idUcehistoriacOld)) {
                UceHcExpfisica oldUceHcExpfisicaOfIdUcehistoriac = idUcehistoriacNew.getUceHcExpfisica();
                if (oldUceHcExpfisicaOfIdUcehistoriac != null) {
                    oldUceHcExpfisicaOfIdUcehistoriac.setIdUcehistoriac(null);
                    oldUceHcExpfisicaOfIdUcehistoriac = em.merge(oldUceHcExpfisicaOfIdUcehistoriac);
                }
                idUcehistoriacNew.setUceHcExpfisica(uceHcExpfisica);
                idUcehistoriacNew = em.merge(idUcehistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceHcExpfisica.getId();
                if (findUceHcExpfisica(id) == null) {
                    throw new NonexistentEntityException("The uceHcExpfisica with id " + id + " no longer exists.");
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
            UceHcExpfisica uceHcExpfisica;
            try {
                uceHcExpfisica = em.getReference(UceHcExpfisica.class, id);
                uceHcExpfisica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceHcExpfisica with id " + id + " no longer exists.", enfe);
            }
            UceHistoriac idUcehistoriac = uceHcExpfisica.getIdUcehistoriac();
            if (idUcehistoriac != null) {
                idUcehistoriac.setUceHcExpfisica(null);
                idUcehistoriac = em.merge(idUcehistoriac);
            }
            em.remove(uceHcExpfisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceHcExpfisica> findUceHcExpfisicaEntities() {
        return findUceHcExpfisicaEntities(true, -1, -1);
    }

    public List<UceHcExpfisica> findUceHcExpfisicaEntities(int maxResults, int firstResult) {
        return findUceHcExpfisicaEntities(false, maxResults, firstResult);
    }

    private List<UceHcExpfisica> findUceHcExpfisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceHcExpfisica.class));
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

    public UceHcExpfisica findUceHcExpfisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceHcExpfisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceHcExpfisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceHcExpfisica> rt = cq.from(UceHcExpfisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
