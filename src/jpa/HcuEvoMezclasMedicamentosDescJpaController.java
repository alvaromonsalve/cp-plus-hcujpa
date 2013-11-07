/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HcuEvoMezclasMedicamentos;
import entidades.HcuEvoMezclasMedicamentosDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvoMezclasMedicamentosDescJpaController implements Serializable {

    public HcuEvoMezclasMedicamentosDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoMezclasMedicamentosDesc hcuEvoMezclasMedicamentosDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentos = hcuEvoMezclasMedicamentosDesc.getIdHcuEvoMezclasMedicamentos();
            if (idHcuEvoMezclasMedicamentos != null) {
                idHcuEvoMezclasMedicamentos = em.getReference(idHcuEvoMezclasMedicamentos.getClass(), idHcuEvoMezclasMedicamentos.getId());
                hcuEvoMezclasMedicamentosDesc.setIdHcuEvoMezclasMedicamentos(idHcuEvoMezclasMedicamentos);
            }
            em.persist(hcuEvoMezclasMedicamentosDesc);
            if (idHcuEvoMezclasMedicamentos != null) {
                idHcuEvoMezclasMedicamentos.getHcuEvoMezclasMedicamentosDescs().add(hcuEvoMezclasMedicamentosDesc);
                idHcuEvoMezclasMedicamentos = em.merge(idHcuEvoMezclasMedicamentos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoMezclasMedicamentosDesc hcuEvoMezclasMedicamentosDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoMezclasMedicamentosDesc persistentHcuEvoMezclasMedicamentosDesc = em.find(HcuEvoMezclasMedicamentosDesc.class, hcuEvoMezclasMedicamentosDesc.getId());
            HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentosOld = persistentHcuEvoMezclasMedicamentosDesc.getIdHcuEvoMezclasMedicamentos();
            HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentosNew = hcuEvoMezclasMedicamentosDesc.getIdHcuEvoMezclasMedicamentos();
            if (idHcuEvoMezclasMedicamentosNew != null) {
                idHcuEvoMezclasMedicamentosNew = em.getReference(idHcuEvoMezclasMedicamentosNew.getClass(), idHcuEvoMezclasMedicamentosNew.getId());
                hcuEvoMezclasMedicamentosDesc.setIdHcuEvoMezclasMedicamentos(idHcuEvoMezclasMedicamentosNew);
            }
            hcuEvoMezclasMedicamentosDesc = em.merge(hcuEvoMezclasMedicamentosDesc);
            if (idHcuEvoMezclasMedicamentosOld != null && !idHcuEvoMezclasMedicamentosOld.equals(idHcuEvoMezclasMedicamentosNew)) {
                idHcuEvoMezclasMedicamentosOld.getHcuEvoMezclasMedicamentosDescs().remove(hcuEvoMezclasMedicamentosDesc);
                idHcuEvoMezclasMedicamentosOld = em.merge(idHcuEvoMezclasMedicamentosOld);
            }
            if (idHcuEvoMezclasMedicamentosNew != null && !idHcuEvoMezclasMedicamentosNew.equals(idHcuEvoMezclasMedicamentosOld)) {
                idHcuEvoMezclasMedicamentosNew.getHcuEvoMezclasMedicamentosDescs().add(hcuEvoMezclasMedicamentosDesc);
                idHcuEvoMezclasMedicamentosNew = em.merge(idHcuEvoMezclasMedicamentosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoMezclasMedicamentosDesc.getId();
                if (findHcuEvoMezclasMedicamentosDesc(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoMezclasMedicamentosDesc with id " + id + " no longer exists.");
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
            HcuEvoMezclasMedicamentosDesc hcuEvoMezclasMedicamentosDesc;
            try {
                hcuEvoMezclasMedicamentosDesc = em.getReference(HcuEvoMezclasMedicamentosDesc.class, id);
                hcuEvoMezclasMedicamentosDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoMezclasMedicamentosDesc with id " + id + " no longer exists.", enfe);
            }
            HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentos = hcuEvoMezclasMedicamentosDesc.getIdHcuEvoMezclasMedicamentos();
            if (idHcuEvoMezclasMedicamentos != null) {
                idHcuEvoMezclasMedicamentos.getHcuEvoMezclasMedicamentosDescs().remove(hcuEvoMezclasMedicamentosDesc);
                idHcuEvoMezclasMedicamentos = em.merge(idHcuEvoMezclasMedicamentos);
            }
            em.remove(hcuEvoMezclasMedicamentosDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoMezclasMedicamentosDesc> findHcuEvoMezclasMedicamentosDescEntities() {
        return findHcuEvoMezclasMedicamentosDescEntities(true, -1, -1);
    }

    public List<HcuEvoMezclasMedicamentosDesc> findHcuEvoMezclasMedicamentosDescEntities(int maxResults, int firstResult) {
        return findHcuEvoMezclasMedicamentosDescEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoMezclasMedicamentosDesc> findHcuEvoMezclasMedicamentosDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoMezclasMedicamentosDesc.class));
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

    public HcuEvoMezclasMedicamentosDesc findHcuEvoMezclasMedicamentosDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoMezclasMedicamentosDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoMezclasMedicamentosDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoMezclasMedicamentosDesc> rt = cq.from(HcuEvoMezclasMedicamentosDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
