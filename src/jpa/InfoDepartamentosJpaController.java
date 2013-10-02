/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoDepartamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.InfoPais;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class InfoDepartamentosJpaController implements Serializable {

    public InfoDepartamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoDepartamentos infoDepartamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoPais infoPais = infoDepartamentos.getInfoPais();
            if (infoPais != null) {
                infoPais = em.getReference(infoPais.getClass(), infoPais.getId());
                infoDepartamentos.setInfoPais(infoPais);
            }
            em.persist(infoDepartamentos);
            if (infoPais != null) {
                infoPais.getInfoDepartamentosList().add(infoDepartamentos);
                infoPais = em.merge(infoPais);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoDepartamentos infoDepartamentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoDepartamentos persistentInfoDepartamentos = em.find(InfoDepartamentos.class, infoDepartamentos.getId());
            InfoPais infoPaisOld = persistentInfoDepartamentos.getInfoPais();
            InfoPais infoPaisNew = infoDepartamentos.getInfoPais();
            if (infoPaisNew != null) {
                infoPaisNew = em.getReference(infoPaisNew.getClass(), infoPaisNew.getId());
                infoDepartamentos.setInfoPais(infoPaisNew);
            }
            infoDepartamentos = em.merge(infoDepartamentos);
            if (infoPaisOld != null && !infoPaisOld.equals(infoPaisNew)) {
                infoPaisOld.getInfoDepartamentosList().remove(infoDepartamentos);
                infoPaisOld = em.merge(infoPaisOld);
            }
            if (infoPaisNew != null && !infoPaisNew.equals(infoPaisOld)) {
                infoPaisNew.getInfoDepartamentosList().add(infoDepartamentos);
                infoPaisNew = em.merge(infoPaisNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoDepartamentos.getId();
                if (findInfoDepartamentos(id) == null) {
                    throw new NonexistentEntityException("The infoDepartamentos with id " + id + " no longer exists.");
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
            InfoDepartamentos infoDepartamentos;
            try {
                infoDepartamentos = em.getReference(InfoDepartamentos.class, id);
                infoDepartamentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoDepartamentos with id " + id + " no longer exists.", enfe);
            }
            InfoPais infoPais = infoDepartamentos.getInfoPais();
            if (infoPais != null) {
                infoPais.getInfoDepartamentosList().remove(infoDepartamentos);
                infoPais = em.merge(infoPais);
            }
            em.remove(infoDepartamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoDepartamentos> findInfoDepartamentosEntities() {
        return findInfoDepartamentosEntities(true, -1, -1);
    }

    public List<InfoDepartamentos> findInfoDepartamentosEntities(int maxResults, int firstResult) {
        return findInfoDepartamentosEntities(false, maxResults, firstResult);
    }

    private List<InfoDepartamentos> findInfoDepartamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoDepartamentos.class));
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

    public InfoDepartamentos findInfoDepartamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoDepartamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoDepartamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoDepartamentos> rt = cq.from(InfoDepartamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
