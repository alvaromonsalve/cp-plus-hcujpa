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
import entidades_EJB.HospIngAnexo3;
import entidades_EJB.HospIngAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngAnexo3Cie10JpaController implements Serializable {

    public HospIngAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngAnexo3Cie10 hospIngAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngAnexo3 idanexo = hospIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                hospIngAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(hospIngAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getHospIngAnexo3Cie10List().add(hospIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngAnexo3Cie10 hospIngAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngAnexo3Cie10 persistentHospIngAnexo3Cie10 = em.find(HospIngAnexo3Cie10.class, hospIngAnexo3Cie10.getId());
            HospIngAnexo3 idanexoOld = persistentHospIngAnexo3Cie10.getIdanexo();
            HospIngAnexo3 idanexoNew = hospIngAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                hospIngAnexo3Cie10.setIdanexo(idanexoNew);
            }
            hospIngAnexo3Cie10 = em.merge(hospIngAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getHospIngAnexo3Cie10List().remove(hospIngAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getHospIngAnexo3Cie10List().add(hospIngAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngAnexo3Cie10.getId();
                if (findHospIngAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The hospIngAnexo3Cie10 with id " + id + " no longer exists.");
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
            HospIngAnexo3Cie10 hospIngAnexo3Cie10;
            try {
                hospIngAnexo3Cie10 = em.getReference(HospIngAnexo3Cie10.class, id);
                hospIngAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            HospIngAnexo3 idanexo = hospIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getHospIngAnexo3Cie10List().remove(hospIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(hospIngAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngAnexo3Cie10> findHospIngAnexo3Cie10Entities() {
        return findHospIngAnexo3Cie10Entities(true, -1, -1);
    }

    public List<HospIngAnexo3Cie10> findHospIngAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findHospIngAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<HospIngAnexo3Cie10> findHospIngAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngAnexo3Cie10.class));
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

    public HospIngAnexo3Cie10 findHospIngAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngAnexo3Cie10> rt = cq.from(HospIngAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
