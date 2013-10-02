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
import entidades.HcuMezclasMedicamentos;
import entidades.HcuMezclasMedicamentosDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuMezclasMedicamentosDescJpaController implements Serializable {

    public HcuMezclasMedicamentosDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuMezclasMedicamentos idHcuMezclasMedicamentos = hcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
            if (idHcuMezclasMedicamentos != null) {
                idHcuMezclasMedicamentos = em.getReference(idHcuMezclasMedicamentos.getClass(), idHcuMezclasMedicamentos.getId());
                hcuMezclasMedicamentosDesc.setIdHcuMezclasMedicamentos(idHcuMezclasMedicamentos);
            }
            em.persist(hcuMezclasMedicamentosDesc);
            if (idHcuMezclasMedicamentos != null) {
                idHcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList().add(hcuMezclasMedicamentosDesc);
                idHcuMezclasMedicamentos = em.merge(idHcuMezclasMedicamentos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuMezclasMedicamentosDesc persistentHcuMezclasMedicamentosDesc = em.find(HcuMezclasMedicamentosDesc.class, hcuMezclasMedicamentosDesc.getId());
            HcuMezclasMedicamentos idHcuMezclasMedicamentosOld = persistentHcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
            HcuMezclasMedicamentos idHcuMezclasMedicamentosNew = hcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
            if (idHcuMezclasMedicamentosNew != null) {
                idHcuMezclasMedicamentosNew = em.getReference(idHcuMezclasMedicamentosNew.getClass(), idHcuMezclasMedicamentosNew.getId());
                hcuMezclasMedicamentosDesc.setIdHcuMezclasMedicamentos(idHcuMezclasMedicamentosNew);
            }
            hcuMezclasMedicamentosDesc = em.merge(hcuMezclasMedicamentosDesc);
            if (idHcuMezclasMedicamentosOld != null && !idHcuMezclasMedicamentosOld.equals(idHcuMezclasMedicamentosNew)) {
                idHcuMezclasMedicamentosOld.getHcuMezclasMedicamentosDescList().remove(hcuMezclasMedicamentosDesc);
                idHcuMezclasMedicamentosOld = em.merge(idHcuMezclasMedicamentosOld);
            }
            if (idHcuMezclasMedicamentosNew != null && !idHcuMezclasMedicamentosNew.equals(idHcuMezclasMedicamentosOld)) {
                idHcuMezclasMedicamentosNew.getHcuMezclasMedicamentosDescList().add(hcuMezclasMedicamentosDesc);
                idHcuMezclasMedicamentosNew = em.merge(idHcuMezclasMedicamentosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuMezclasMedicamentosDesc.getId();
                if (findHcuMezclasMedicamentosDesc(id) == null) {
                    throw new NonexistentEntityException("The hcuMezclasMedicamentosDesc with id " + id + " no longer exists.");
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
            HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDesc;
            try {
                hcuMezclasMedicamentosDesc = em.getReference(HcuMezclasMedicamentosDesc.class, id);
                hcuMezclasMedicamentosDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuMezclasMedicamentosDesc with id " + id + " no longer exists.", enfe);
            }
            HcuMezclasMedicamentos idHcuMezclasMedicamentos = hcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
            if (idHcuMezclasMedicamentos != null) {
                idHcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList().remove(hcuMezclasMedicamentosDesc);
                idHcuMezclasMedicamentos = em.merge(idHcuMezclasMedicamentos);
            }
            em.remove(hcuMezclasMedicamentosDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuMezclasMedicamentosDesc> findHcuMezclasMedicamentosDescEntities() {
        return findHcuMezclasMedicamentosDescEntities(true, -1, -1);
    }

    public List<HcuMezclasMedicamentosDesc> findHcuMezclasMedicamentosDescEntities(int maxResults, int firstResult) {
        return findHcuMezclasMedicamentosDescEntities(false, maxResults, firstResult);
    }

    private List<HcuMezclasMedicamentosDesc> findHcuMezclasMedicamentosDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuMezclasMedicamentosDesc.class));
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

    public HcuMezclasMedicamentosDesc findHcuMezclasMedicamentosDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuMezclasMedicamentosDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuMezclasMedicamentosDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuMezclasMedicamentosDesc> rt = cq.from(HcuMezclasMedicamentosDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
