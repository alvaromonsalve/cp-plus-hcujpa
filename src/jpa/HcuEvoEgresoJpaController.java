/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HcuEvoEgreso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HcuEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvoEgresoJpaController implements Serializable {

    public HcuEvoEgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoEgreso hcuEvoEgreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion idHcuEvolucion = hcuEvoEgreso.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion = em.getReference(idHcuEvolucion.getClass(), idHcuEvolucion.getId());
                hcuEvoEgreso.setIdHcuEvolucion(idHcuEvolucion);
            }
            em.persist(hcuEvoEgreso);
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoEgreso().add(hcuEvoEgreso);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoEgreso hcuEvoEgreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoEgreso persistentHcuEvoEgreso = em.find(HcuEvoEgreso.class, hcuEvoEgreso.getId());
            HcuEvolucion idHcuEvolucionOld = persistentHcuEvoEgreso.getIdHcuEvolucion();
            HcuEvolucion idHcuEvolucionNew = hcuEvoEgreso.getIdHcuEvolucion();
            if (idHcuEvolucionNew != null) {
                idHcuEvolucionNew = em.getReference(idHcuEvolucionNew.getClass(), idHcuEvolucionNew.getId());
                hcuEvoEgreso.setIdHcuEvolucion(idHcuEvolucionNew);
            }
            hcuEvoEgreso = em.merge(hcuEvoEgreso);
            if (idHcuEvolucionOld != null && !idHcuEvolucionOld.equals(idHcuEvolucionNew)) {
                idHcuEvolucionOld.getHcuEvoEgreso().remove(hcuEvoEgreso);
                idHcuEvolucionOld = em.merge(idHcuEvolucionOld);
            }
            if (idHcuEvolucionNew != null && !idHcuEvolucionNew.equals(idHcuEvolucionOld)) {
                idHcuEvolucionNew.getHcuEvoEgreso().add(hcuEvoEgreso);
                idHcuEvolucionNew = em.merge(idHcuEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoEgreso.getId();
                if (findHcuEvoEgreso(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoEgreso with id " + id + " no longer exists.");
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
            HcuEvoEgreso hcuEvoEgreso;
            try {
                hcuEvoEgreso = em.getReference(HcuEvoEgreso.class, id);
                hcuEvoEgreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoEgreso with id " + id + " no longer exists.", enfe);
            }
            HcuEvolucion idHcuEvolucion = hcuEvoEgreso.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoEgreso().remove(hcuEvoEgreso);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.remove(hcuEvoEgreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoEgreso> findHcuEvoEgresoEntities() {
        return findHcuEvoEgresoEntities(true, -1, -1);
    }

    public List<HcuEvoEgreso> findHcuEvoEgresoEntities(int maxResults, int firstResult) {
        return findHcuEvoEgresoEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoEgreso> findHcuEvoEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoEgreso.class));
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

    public HcuEvoEgreso findHcuEvoEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoEgreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoEgreso> rt = cq.from(HcuEvoEgreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public HcuEvoEgreso getEntidadHcuEvoEgreso(int evo) {
        HcuEvoEgreso e = null;
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT e FROM HcuEvoEgreso e WHERE e.idHcuEvolucion.id=:evo AND e.incapacidad='1'  AND e.estado='1'");
        q.setParameter("evo", evo);
        List result = q.getResultList();
        if (!result.isEmpty()) {
            e = (HcuEvoEgreso) result.get(0);
        } else {
            e = null;
        }
        return e;
    }

    public HcuEvoEgreso getEntidadHcuEvoEgresoReco(int evo) {
        HcuEvoEgreso e = null;
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT e FROM HcuEvoEgreso e WHERE e.idHcuEvolucion.id=:evo AND e.estado='1'");
        q.setParameter("evo", evo);
        List result = q.getResultList();
        if (!result.isEmpty()) {
            e = (HcuEvoEgreso) result.get(0);
        } else {
            e = null;
        }
        return e;
    }
}
