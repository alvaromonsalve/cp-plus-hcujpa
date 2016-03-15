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
import entidades_EJB.UciInfquirurgicoSoat1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoSoat1JpaController implements Serializable {

    public UciInfquirurgicoSoat1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgicoSoat1 uciInfquirurgicoSoat1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico idinforme = uciInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme = em.getReference(idinforme.getClass(), idinforme.getId());
                uciInfquirurgicoSoat1.setIdinforme(idinforme);
            }
            em.persist(uciInfquirurgicoSoat1);
            if (idinforme != null) {
                idinforme.getUciInfquirurgicoSoat1List().add(uciInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgicoSoat1 uciInfquirurgicoSoat1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgicoSoat1 persistentUciInfquirurgicoSoat1 = em.find(UciInfquirurgicoSoat1.class, uciInfquirurgicoSoat1.getId());
            UciInfquirurgico idinformeOld = persistentUciInfquirurgicoSoat1.getIdinforme();
            UciInfquirurgico idinformeNew = uciInfquirurgicoSoat1.getIdinforme();
            if (idinformeNew != null) {
                idinformeNew = em.getReference(idinformeNew.getClass(), idinformeNew.getId());
                uciInfquirurgicoSoat1.setIdinforme(idinformeNew);
            }
            uciInfquirurgicoSoat1 = em.merge(uciInfquirurgicoSoat1);
            if (idinformeOld != null && !idinformeOld.equals(idinformeNew)) {
                idinformeOld.getUciInfquirurgicoSoat1List().remove(uciInfquirurgicoSoat1);
                idinformeOld = em.merge(idinformeOld);
            }
            if (idinformeNew != null && !idinformeNew.equals(idinformeOld)) {
                idinformeNew.getUciInfquirurgicoSoat1List().add(uciInfquirurgicoSoat1);
                idinformeNew = em.merge(idinformeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgicoSoat1.getId();
                if (findUciInfquirurgicoSoat1(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgicoSoat1 with id " + id + " no longer exists.");
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
            UciInfquirurgicoSoat1 uciInfquirurgicoSoat1;
            try {
                uciInfquirurgicoSoat1 = em.getReference(UciInfquirurgicoSoat1.class, id);
                uciInfquirurgicoSoat1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgicoSoat1 with id " + id + " no longer exists.", enfe);
            }
            UciInfquirurgico idinforme = uciInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme.getUciInfquirurgicoSoat1List().remove(uciInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.remove(uciInfquirurgicoSoat1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgicoSoat1> findUciInfquirurgicoSoat1Entities() {
        return findUciInfquirurgicoSoat1Entities(true, -1, -1);
    }

    public List<UciInfquirurgicoSoat1> findUciInfquirurgicoSoat1Entities(int maxResults, int firstResult) {
        return findUciInfquirurgicoSoat1Entities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgicoSoat1> findUciInfquirurgicoSoat1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgicoSoat1.class));
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

    public UciInfquirurgicoSoat1 findUciInfquirurgicoSoat1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgicoSoat1.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoSoat1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgicoSoat1> rt = cq.from(UciInfquirurgicoSoat1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
