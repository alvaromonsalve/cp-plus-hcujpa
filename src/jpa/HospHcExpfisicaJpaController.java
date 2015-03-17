package jpa;

import entidades.HospHcExpfisica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HospHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospHcExpfisicaJpaController implements Serializable {

    public HospHcExpfisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospHcExpfisica hospHcExpfisica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospHistoriac idHosphistoriac = hospHcExpfisica.getIdHosphistoriac();
            if (idHosphistoriac != null) {
                idHosphistoriac = em.getReference(idHosphistoriac.getClass(), idHosphistoriac.getId());
                hospHcExpfisica.setIdHosphistoriac(idHosphistoriac);
            }
            em.persist(hospHcExpfisica);
            if (idHosphistoriac != null) {
                HospHcExpfisica oldHospHcExpfisicaOfIdHosphistoriac = idHosphistoriac.getHospHcExpfisica();
                if (oldHospHcExpfisicaOfIdHosphistoriac != null) {
                    oldHospHcExpfisicaOfIdHosphistoriac.setIdHosphistoriac(null);
                    oldHospHcExpfisicaOfIdHosphistoriac = em.merge(oldHospHcExpfisicaOfIdHosphistoriac);
                }
                idHosphistoriac.setHospHcExpfisica(hospHcExpfisica);
                idHosphistoriac = em.merge(idHosphistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospHcExpfisica hospHcExpfisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospHcExpfisica persistentHospHcExpfisica = em.find(HospHcExpfisica.class, hospHcExpfisica.getId());
            HospHistoriac idHosphistoriacOld = persistentHospHcExpfisica.getIdHosphistoriac();
            HospHistoriac idHosphistoriacNew = hospHcExpfisica.getIdHosphistoriac();
            if (idHosphistoriacNew != null) {
                idHosphistoriacNew = em.getReference(idHosphistoriacNew.getClass(), idHosphistoriacNew.getId());
                hospHcExpfisica.setIdHosphistoriac(idHosphistoriacNew);
            }
            hospHcExpfisica = em.merge(hospHcExpfisica);
            if (idHosphistoriacOld != null && !idHosphistoriacOld.equals(idHosphistoriacNew)) {
                idHosphistoriacOld.setHospHcExpfisica(null);
                idHosphistoriacOld = em.merge(idHosphistoriacOld);
            }
            if (idHosphistoriacNew != null && !idHosphistoriacNew.equals(idHosphistoriacOld)) {
                HospHcExpfisica oldHospHcExpfisicaOfIdHosphistoriac = idHosphistoriacNew.getHospHcExpfisica();
                if (oldHospHcExpfisicaOfIdHosphistoriac != null) {
                    oldHospHcExpfisicaOfIdHosphistoriac.setIdHosphistoriac(null);
                    oldHospHcExpfisicaOfIdHosphistoriac = em.merge(oldHospHcExpfisicaOfIdHosphistoriac);
                }
                idHosphistoriacNew.setHospHcExpfisica(hospHcExpfisica);
                idHosphistoriacNew = em.merge(idHosphistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospHcExpfisica.getId();
                if (findHospHcExpfisica(id) == null) {
                    throw new NonexistentEntityException("The hospHcExpfisica with id " + id + " no longer exists.");
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
            HospHcExpfisica hospHcExpfisica;
            try {
                hospHcExpfisica = em.getReference(HospHcExpfisica.class, id);
                hospHcExpfisica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospHcExpfisica with id " + id + " no longer exists.", enfe);
            }
            HospHistoriac idHosphistoriac = hospHcExpfisica.getIdHosphistoriac();
            if (idHosphistoriac != null) {
                idHosphistoriac.setHospHcExpfisica(null);
                idHosphistoriac = em.merge(idHosphistoriac);
            }
            em.remove(hospHcExpfisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospHcExpfisica> findHospHcExpfisicaEntities() {
        return findHospHcExpfisicaEntities(true, -1, -1);
    }

    public List<HospHcExpfisica> findHospHcExpfisicaEntities(int maxResults, int firstResult) {
        return findHospHcExpfisicaEntities(false, maxResults, firstResult);
    }

    private List<HospHcExpfisica> findHospHcExpfisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospHcExpfisica.class));
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

    public HospHcExpfisica findHospHcExpfisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospHcExpfisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospHcExpfisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospHcExpfisica> rt = cq.from(HospHcExpfisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
