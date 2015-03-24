package jpa;

import entidades.UciHcExpfisica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UciHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UciHcExpfisicaJpaController implements Serializable {

    public UciHcExpfisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciHcExpfisica uciHcExpfisica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciHistoriac idUcihistoriac = uciHcExpfisica.getIdUcihistoriac();
            if (idUcihistoriac != null) {
                idUcihistoriac = em.getReference(idUcihistoriac.getClass(), idUcihistoriac.getId());
                uciHcExpfisica.setIdUcihistoriac(idUcihistoriac);
            }
            em.persist(uciHcExpfisica);
            if (idUcihistoriac != null) {
                UciHcExpfisica oldUciHcExpfisicaOfIdUcihistoriac = idUcihistoriac.getUciHcExpfisica();
                if (oldUciHcExpfisicaOfIdUcihistoriac != null) {
                    oldUciHcExpfisicaOfIdUcihistoriac.setIdUcihistoriac(null);
                    oldUciHcExpfisicaOfIdUcihistoriac = em.merge(oldUciHcExpfisicaOfIdUcihistoriac);
                }
                idUcihistoriac.setUciHcExpfisica(uciHcExpfisica);
                idUcihistoriac = em.merge(idUcihistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciHcExpfisica uciHcExpfisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciHcExpfisica persistentUciHcExpfisica = em.find(UciHcExpfisica.class, uciHcExpfisica.getId());
            UciHistoriac idUcihistoriacOld = persistentUciHcExpfisica.getIdUcihistoriac();
            UciHistoriac idUcihistoriacNew = uciHcExpfisica.getIdUcihistoriac();
            if (idUcihistoriacNew != null) {
                idUcihistoriacNew = em.getReference(idUcihistoriacNew.getClass(), idUcihistoriacNew.getId());
                uciHcExpfisica.setIdUcihistoriac(idUcihistoriacNew);
            }
            uciHcExpfisica = em.merge(uciHcExpfisica);
            if (idUcihistoriacOld != null && !idUcihistoriacOld.equals(idUcihistoriacNew)) {
                idUcihistoriacOld.setUciHcExpfisica(null);
                idUcihistoriacOld = em.merge(idUcihistoriacOld);
            }
            if (idUcihistoriacNew != null && !idUcihistoriacNew.equals(idUcihistoriacOld)) {
                UciHcExpfisica oldUciHcExpfisicaOfIdUcihistoriac = idUcihistoriacNew.getUciHcExpfisica();
                if (oldUciHcExpfisicaOfIdUcihistoriac != null) {
                    oldUciHcExpfisicaOfIdUcihistoriac.setIdUcihistoriac(null);
                    oldUciHcExpfisicaOfIdUcihistoriac = em.merge(oldUciHcExpfisicaOfIdUcihistoriac);
                }
                idUcihistoriacNew.setUciHcExpfisica(uciHcExpfisica);
                idUcihistoriacNew = em.merge(idUcihistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciHcExpfisica.getId();
                if (findUciHcExpfisica(id) == null) {
                    throw new NonexistentEntityException("The uciHcExpfisica with id " + id + " no longer exists.");
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
            UciHcExpfisica uciHcExpfisica;
            try {
                uciHcExpfisica = em.getReference(UciHcExpfisica.class, id);
                uciHcExpfisica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciHcExpfisica with id " + id + " no longer exists.", enfe);
            }
            UciHistoriac idUcihistoriac = uciHcExpfisica.getIdUcihistoriac();
            if (idUcihistoriac != null) {
                idUcihistoriac.setUciHcExpfisica(null);
                idUcihistoriac = em.merge(idUcihistoriac);
            }
            em.remove(uciHcExpfisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciHcExpfisica> findUciHcExpfisicaEntities() {
        return findUciHcExpfisicaEntities(true, -1, -1);
    }

    public List<UciHcExpfisica> findUciHcExpfisicaEntities(int maxResults, int firstResult) {
        return findUciHcExpfisicaEntities(false, maxResults, firstResult);
    }

    private List<UciHcExpfisica> findUciHcExpfisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciHcExpfisica.class));
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

    public UciHcExpfisica findUciHcExpfisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciHcExpfisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciHcExpfisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciHcExpfisica> rt = cq.from(UciHcExpfisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
