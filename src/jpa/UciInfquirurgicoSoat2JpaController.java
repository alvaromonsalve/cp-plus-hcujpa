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
import entidades_EJB.UciInfquirurgico;
import entidades_EJB.UciInfquirurgicoSoat2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoSoat2JpaController implements Serializable {

    public UciInfquirurgicoSoat2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgicoSoat2 uciInfquirurgicoSoat2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico idquirurgico = uciInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                uciInfquirurgicoSoat2.setIdquirurgico(idquirurgico);
            }
            em.persist(uciInfquirurgicoSoat2);
            if (idquirurgico != null) {
                idquirurgico.getUciInfquirurgicoSoat2List().add(uciInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgicoSoat2 uciInfquirurgicoSoat2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgicoSoat2 persistentUciInfquirurgicoSoat2 = em.find(UciInfquirurgicoSoat2.class, uciInfquirurgicoSoat2.getId());
            UciInfquirurgico idquirurgicoOld = persistentUciInfquirurgicoSoat2.getIdquirurgico();
            UciInfquirurgico idquirurgicoNew = uciInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                uciInfquirurgicoSoat2.setIdquirurgico(idquirurgicoNew);
            }
            uciInfquirurgicoSoat2 = em.merge(uciInfquirurgicoSoat2);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUciInfquirurgicoSoat2List().remove(uciInfquirurgicoSoat2);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUciInfquirurgicoSoat2List().add(uciInfquirurgicoSoat2);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgicoSoat2.getId();
                if (findUciInfquirurgicoSoat2(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgicoSoat2 with id " + id + " no longer exists.");
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
            UciInfquirurgicoSoat2 uciInfquirurgicoSoat2;
            try {
                uciInfquirurgicoSoat2 = em.getReference(UciInfquirurgicoSoat2.class, id);
                uciInfquirurgicoSoat2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgicoSoat2 with id " + id + " no longer exists.", enfe);
            }
            UciInfquirurgico idquirurgico = uciInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUciInfquirurgicoSoat2List().remove(uciInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(uciInfquirurgicoSoat2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgicoSoat2> findUciInfquirurgicoSoat2Entities() {
        return findUciInfquirurgicoSoat2Entities(true, -1, -1);
    }

    public List<UciInfquirurgicoSoat2> findUciInfquirurgicoSoat2Entities(int maxResults, int firstResult) {
        return findUciInfquirurgicoSoat2Entities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgicoSoat2> findUciInfquirurgicoSoat2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgicoSoat2.class));
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

    public UciInfquirurgicoSoat2 findUciInfquirurgicoSoat2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgicoSoat2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoSoat2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgicoSoat2> rt = cq.from(UciInfquirurgicoSoat2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
