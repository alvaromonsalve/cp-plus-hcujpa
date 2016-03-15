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
import entidades_EJB.UceInfquirurgico;
import entidades_EJB.UceInfquirurgicoSoat2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoSoat2JpaController implements Serializable {

    public UceInfquirurgicoSoat2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgicoSoat2 uceInfquirurgicoSoat2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico idquirurgico = uceInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                uceInfquirurgicoSoat2.setIdquirurgico(idquirurgico);
            }
            em.persist(uceInfquirurgicoSoat2);
            if (idquirurgico != null) {
                idquirurgico.getUceInfquirurgicoSoat2List().add(uceInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgicoSoat2 uceInfquirurgicoSoat2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgicoSoat2 persistentUceInfquirurgicoSoat2 = em.find(UceInfquirurgicoSoat2.class, uceInfquirurgicoSoat2.getId());
            UceInfquirurgico idquirurgicoOld = persistentUceInfquirurgicoSoat2.getIdquirurgico();
            UceInfquirurgico idquirurgicoNew = uceInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                uceInfquirurgicoSoat2.setIdquirurgico(idquirurgicoNew);
            }
            uceInfquirurgicoSoat2 = em.merge(uceInfquirurgicoSoat2);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUceInfquirurgicoSoat2List().remove(uceInfquirurgicoSoat2);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUceInfquirurgicoSoat2List().add(uceInfquirurgicoSoat2);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgicoSoat2.getId();
                if (findUceInfquirurgicoSoat2(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgicoSoat2 with id " + id + " no longer exists.");
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
            UceInfquirurgicoSoat2 uceInfquirurgicoSoat2;
            try {
                uceInfquirurgicoSoat2 = em.getReference(UceInfquirurgicoSoat2.class, id);
                uceInfquirurgicoSoat2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgicoSoat2 with id " + id + " no longer exists.", enfe);
            }
            UceInfquirurgico idquirurgico = uceInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUceInfquirurgicoSoat2List().remove(uceInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(uceInfquirurgicoSoat2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgicoSoat2> findUceInfquirurgicoSoat2Entities() {
        return findUceInfquirurgicoSoat2Entities(true, -1, -1);
    }

    public List<UceInfquirurgicoSoat2> findUceInfquirurgicoSoat2Entities(int maxResults, int firstResult) {
        return findUceInfquirurgicoSoat2Entities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgicoSoat2> findUceInfquirurgicoSoat2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgicoSoat2.class));
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

    public UceInfquirurgicoSoat2 findUceInfquirurgicoSoat2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgicoSoat2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoSoat2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgicoSoat2> rt = cq.from(UceInfquirurgicoSoat2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
