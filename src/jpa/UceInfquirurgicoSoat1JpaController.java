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
import entidades_EJB.UceInfquirurgicoSoat1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoSoat1JpaController implements Serializable {

    public UceInfquirurgicoSoat1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgicoSoat1 uceInfquirurgicoSoat1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico idinforme = uceInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme = em.getReference(idinforme.getClass(), idinforme.getId());
                uceInfquirurgicoSoat1.setIdinforme(idinforme);
            }
            em.persist(uceInfquirurgicoSoat1);
            if (idinforme != null) {
                idinforme.getUceInfquirurgicoSoat1List().add(uceInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgicoSoat1 uceInfquirurgicoSoat1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgicoSoat1 persistentUceInfquirurgicoSoat1 = em.find(UceInfquirurgicoSoat1.class, uceInfquirurgicoSoat1.getId());
            UceInfquirurgico idinformeOld = persistentUceInfquirurgicoSoat1.getIdinforme();
            UceInfquirurgico idinformeNew = uceInfquirurgicoSoat1.getIdinforme();
            if (idinformeNew != null) {
                idinformeNew = em.getReference(idinformeNew.getClass(), idinformeNew.getId());
                uceInfquirurgicoSoat1.setIdinforme(idinformeNew);
            }
            uceInfquirurgicoSoat1 = em.merge(uceInfquirurgicoSoat1);
            if (idinformeOld != null && !idinformeOld.equals(idinformeNew)) {
                idinformeOld.getUceInfquirurgicoSoat1List().remove(uceInfquirurgicoSoat1);
                idinformeOld = em.merge(idinformeOld);
            }
            if (idinformeNew != null && !idinformeNew.equals(idinformeOld)) {
                idinformeNew.getUceInfquirurgicoSoat1List().add(uceInfquirurgicoSoat1);
                idinformeNew = em.merge(idinformeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgicoSoat1.getId();
                if (findUceInfquirurgicoSoat1(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgicoSoat1 with id " + id + " no longer exists.");
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
            UceInfquirurgicoSoat1 uceInfquirurgicoSoat1;
            try {
                uceInfquirurgicoSoat1 = em.getReference(UceInfquirurgicoSoat1.class, id);
                uceInfquirurgicoSoat1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgicoSoat1 with id " + id + " no longer exists.", enfe);
            }
            UceInfquirurgico idinforme = uceInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme.getUceInfquirurgicoSoat1List().remove(uceInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.remove(uceInfquirurgicoSoat1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgicoSoat1> findUceInfquirurgicoSoat1Entities() {
        return findUceInfquirurgicoSoat1Entities(true, -1, -1);
    }

    public List<UceInfquirurgicoSoat1> findUceInfquirurgicoSoat1Entities(int maxResults, int firstResult) {
        return findUceInfquirurgicoSoat1Entities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgicoSoat1> findUceInfquirurgicoSoat1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgicoSoat1.class));
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

    public UceInfquirurgicoSoat1 findUceInfquirurgicoSoat1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgicoSoat1.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoSoat1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgicoSoat1> rt = cq.from(UceInfquirurgicoSoat1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
