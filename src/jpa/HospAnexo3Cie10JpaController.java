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
import entidades_EJB.HospAnexo3;
import entidades_EJB.HospAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospAnexo3Cie10JpaController implements Serializable {

    public HospAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospAnexo3Cie10 hospAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospAnexo3 idanexo = hospAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                hospAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(hospAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getHospAnexo3Cie10List().add(hospAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospAnexo3Cie10 hospAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospAnexo3Cie10 persistentHospAnexo3Cie10 = em.find(HospAnexo3Cie10.class, hospAnexo3Cie10.getId());
            HospAnexo3 idanexoOld = persistentHospAnexo3Cie10.getIdanexo();
            HospAnexo3 idanexoNew = hospAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                hospAnexo3Cie10.setIdanexo(idanexoNew);
            }
            hospAnexo3Cie10 = em.merge(hospAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getHospAnexo3Cie10List().remove(hospAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getHospAnexo3Cie10List().add(hospAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospAnexo3Cie10.getId();
                if (findHospAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The hospAnexo3Cie10 with id " + id + " no longer exists.");
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
            HospAnexo3Cie10 hospAnexo3Cie10;
            try {
                hospAnexo3Cie10 = em.getReference(HospAnexo3Cie10.class, id);
                hospAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            HospAnexo3 idanexo = hospAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getHospAnexo3Cie10List().remove(hospAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(hospAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospAnexo3Cie10> findHospAnexo3Cie10Entities() {
        return findHospAnexo3Cie10Entities(true, -1, -1);
    }

    public List<HospAnexo3Cie10> findHospAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findHospAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<HospAnexo3Cie10> findHospAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospAnexo3Cie10.class));
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

    public HospAnexo3Cie10 findHospAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospAnexo3Cie10> rt = cq.from(HospAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
