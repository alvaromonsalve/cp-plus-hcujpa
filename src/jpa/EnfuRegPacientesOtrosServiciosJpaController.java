/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.EnfuRegPacientesOtrosServicios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuRegPacientesOtrosServiciosJpaController implements Serializable {

    public EnfuRegPacientesOtrosServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuRegPacientesOtrosServicios enfuRegPacientesOtrosServicios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuRegPacientesOtrosServicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuRegPacientesOtrosServicios enfuRegPacientesOtrosServicios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuRegPacientesOtrosServicios = em.merge(enfuRegPacientesOtrosServicios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuRegPacientesOtrosServicios.getId();
                if (findEnfuRegPacientesOtrosServicios(id) == null) {
                    throw new NonexistentEntityException("The enfuRegPacientesOtrosServicios with id " + id + " no longer exists.");
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
            EnfuRegPacientesOtrosServicios enfuRegPacientesOtrosServicios;
            try {
                enfuRegPacientesOtrosServicios = em.getReference(EnfuRegPacientesOtrosServicios.class, id);
                enfuRegPacientesOtrosServicios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuRegPacientesOtrosServicios with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuRegPacientesOtrosServicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuRegPacientesOtrosServicios> findEnfuRegPacientesOtrosServiciosEntities() {
        return findEnfuRegPacientesOtrosServiciosEntities(true, -1, -1);
    }

    public List<EnfuRegPacientesOtrosServicios> findEnfuRegPacientesOtrosServiciosEntities(int maxResults, int firstResult) {
        return findEnfuRegPacientesOtrosServiciosEntities(false, maxResults, firstResult);
    }

    private List<EnfuRegPacientesOtrosServicios> findEnfuRegPacientesOtrosServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuRegPacientesOtrosServicios.class));
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

    public EnfuRegPacientesOtrosServicios findEnfuRegPacientesOtrosServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuRegPacientesOtrosServicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuRegPacientesOtrosServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuRegPacientesOtrosServicios> rt = cq.from(EnfuRegPacientesOtrosServicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
