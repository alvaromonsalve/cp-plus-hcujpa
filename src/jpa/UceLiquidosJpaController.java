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
import entidades_EJB.UceFactsLiquidos;
import entidades_EJB.UceLiquidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UceLiquidosJpaController implements Serializable {

    public UceLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceLiquidos uceLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceFactsLiquidos idControlO = uceLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO = em.getReference(idControlO.getClass(), idControlO.getId());
                uceLiquidos.setIdControlO(idControlO);
            }
            em.persist(uceLiquidos);
            if (idControlO != null) {
                idControlO.getUceLiquidosList().add(uceLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceLiquidos uceLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceLiquidos persistentUceLiquidos = em.find(UceLiquidos.class, uceLiquidos.getId());
            UceFactsLiquidos idControlOOld = persistentUceLiquidos.getIdControlO();
            UceFactsLiquidos idControlONew = uceLiquidos.getIdControlO();
            if (idControlONew != null) {
                idControlONew = em.getReference(idControlONew.getClass(), idControlONew.getId());
                uceLiquidos.setIdControlO(idControlONew);
            }
            uceLiquidos = em.merge(uceLiquidos);
            if (idControlOOld != null && !idControlOOld.equals(idControlONew)) {
                idControlOOld.getUceLiquidosList().remove(uceLiquidos);
                idControlOOld = em.merge(idControlOOld);
            }
            if (idControlONew != null && !idControlONew.equals(idControlOOld)) {
                idControlONew.getUceLiquidosList().add(uceLiquidos);
                idControlONew = em.merge(idControlONew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceLiquidos.getId();
                if (findUceLiquidos(id) == null) {
                    throw new NonexistentEntityException("The uceLiquidos with id " + id + " no longer exists.");
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
            UceLiquidos uceLiquidos;
            try {
                uceLiquidos = em.getReference(UceLiquidos.class, id);
                uceLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceLiquidos with id " + id + " no longer exists.", enfe);
            }
            UceFactsLiquidos idControlO = uceLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO.getUceLiquidosList().remove(uceLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.remove(uceLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceLiquidos> findUceLiquidosEntities() {
        return findUceLiquidosEntities(true, -1, -1);
    }

    public List<UceLiquidos> findUceLiquidosEntities(int maxResults, int firstResult) {
        return findUceLiquidosEntities(false, maxResults, firstResult);
    }

    private List<UceLiquidos> findUceLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceLiquidos.class));
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

    public UceLiquidos findUceLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceLiquidos> rt = cq.from(UceLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceLiquidos> getLiquidos(int control) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT l FROM UceLiquidos l WHERE l.idControlO.id=:idc AND l.estado='1'");
        Q.setParameter("idc", control);
        return Q.getResultList();
    }
}
