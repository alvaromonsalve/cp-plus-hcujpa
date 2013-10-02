/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HcuAnexo3;
import entidades.HcuAnexo3Det;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HcuAnexo3DetJpaController implements Serializable {

    public HcuAnexo3DetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuAnexo3Det hcuAnexo3Det) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuAnexo3 idHcuAnexo3 = hcuAnexo3Det.getIdHcuAnexo3();
            if (idHcuAnexo3 != null) {
                idHcuAnexo3 = em.getReference(idHcuAnexo3.getClass(), idHcuAnexo3.getId());
                hcuAnexo3Det.setIdHcuAnexo3(idHcuAnexo3);
            }
            em.persist(hcuAnexo3Det);
            if (idHcuAnexo3 != null) {
                idHcuAnexo3.getHcuAnexo3DetList().add(hcuAnexo3Det);
                idHcuAnexo3 = em.merge(idHcuAnexo3);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuAnexo3Det hcuAnexo3Det) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuAnexo3Det persistentHcuAnexo3Det = em.find(HcuAnexo3Det.class, hcuAnexo3Det.getId());
            HcuAnexo3 idHcuAnexo3Old = persistentHcuAnexo3Det.getIdHcuAnexo3();
            HcuAnexo3 idHcuAnexo3New = hcuAnexo3Det.getIdHcuAnexo3();
            if (idHcuAnexo3New != null) {
                idHcuAnexo3New = em.getReference(idHcuAnexo3New.getClass(), idHcuAnexo3New.getId());
                hcuAnexo3Det.setIdHcuAnexo3(idHcuAnexo3New);
            }
            hcuAnexo3Det = em.merge(hcuAnexo3Det);
            if (idHcuAnexo3Old != null && !idHcuAnexo3Old.equals(idHcuAnexo3New)) {
                idHcuAnexo3Old.getHcuAnexo3DetList().remove(hcuAnexo3Det);
                idHcuAnexo3Old = em.merge(idHcuAnexo3Old);
            }
            if (idHcuAnexo3New != null && !idHcuAnexo3New.equals(idHcuAnexo3Old)) {
                idHcuAnexo3New.getHcuAnexo3DetList().add(hcuAnexo3Det);
                idHcuAnexo3New = em.merge(idHcuAnexo3New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuAnexo3Det.getId();
                if (findHcuAnexo3Det(id) == null) {
                    throw new NonexistentEntityException("The hcuAnexo3Det with id " + id + " no longer exists.");
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
            HcuAnexo3Det hcuAnexo3Det;
            try {
                hcuAnexo3Det = em.getReference(HcuAnexo3Det.class, id);
                hcuAnexo3Det.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuAnexo3Det with id " + id + " no longer exists.", enfe);
            }
            HcuAnexo3 idHcuAnexo3 = hcuAnexo3Det.getIdHcuAnexo3();
            if (idHcuAnexo3 != null) {
                idHcuAnexo3.getHcuAnexo3DetList().remove(hcuAnexo3Det);
                idHcuAnexo3 = em.merge(idHcuAnexo3);
            }
            em.remove(hcuAnexo3Det);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuAnexo3Det> findHcuAnexo3DetEntities() {
        return findHcuAnexo3DetEntities(true, -1, -1);
    }

    public List<HcuAnexo3Det> findHcuAnexo3DetEntities(int maxResults, int firstResult) {
        return findHcuAnexo3DetEntities(false, maxResults, firstResult);
    }

    private List<HcuAnexo3Det> findHcuAnexo3DetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuAnexo3Det.class));
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

    public HcuAnexo3Det findHcuAnexo3Det(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuAnexo3Det.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuAnexo3DetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuAnexo3Det> rt = cq.from(HcuAnexo3Det.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
