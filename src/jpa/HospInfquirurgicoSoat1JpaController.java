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
import entidades_EJB.HospInfquirurgico;
import entidades_EJB.HospInfquirurgicoSoat1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoSoat1JpaController implements Serializable {

    public HospInfquirurgicoSoat1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgicoSoat1 hospInfquirurgicoSoat1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico idinforme = hospInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme = em.getReference(idinforme.getClass(), idinforme.getId());
                hospInfquirurgicoSoat1.setIdinforme(idinforme);
            }
            em.persist(hospInfquirurgicoSoat1);
            if (idinforme != null) {
                idinforme.getHospInfquirurgicoSoat1List().add(hospInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgicoSoat1 hospInfquirurgicoSoat1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgicoSoat1 persistentHospInfquirurgicoSoat1 = em.find(HospInfquirurgicoSoat1.class, hospInfquirurgicoSoat1.getId());
            HospInfquirurgico idinformeOld = persistentHospInfquirurgicoSoat1.getIdinforme();
            HospInfquirurgico idinformeNew = hospInfquirurgicoSoat1.getIdinforme();
            if (idinformeNew != null) {
                idinformeNew = em.getReference(idinformeNew.getClass(), idinformeNew.getId());
                hospInfquirurgicoSoat1.setIdinforme(idinformeNew);
            }
            hospInfquirurgicoSoat1 = em.merge(hospInfquirurgicoSoat1);
            if (idinformeOld != null && !idinformeOld.equals(idinformeNew)) {
                idinformeOld.getHospInfquirurgicoSoat1List().remove(hospInfquirurgicoSoat1);
                idinformeOld = em.merge(idinformeOld);
            }
            if (idinformeNew != null && !idinformeNew.equals(idinformeOld)) {
                idinformeNew.getHospInfquirurgicoSoat1List().add(hospInfquirurgicoSoat1);
                idinformeNew = em.merge(idinformeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgicoSoat1.getId();
                if (findHospInfquirurgicoSoat1(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgicoSoat1 with id " + id + " no longer exists.");
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
            HospInfquirurgicoSoat1 hospInfquirurgicoSoat1;
            try {
                hospInfquirurgicoSoat1 = em.getReference(HospInfquirurgicoSoat1.class, id);
                hospInfquirurgicoSoat1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgicoSoat1 with id " + id + " no longer exists.", enfe);
            }
            HospInfquirurgico idinforme = hospInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme.getHospInfquirurgicoSoat1List().remove(hospInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.remove(hospInfquirurgicoSoat1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgicoSoat1> findHospInfquirurgicoSoat1Entities() {
        return findHospInfquirurgicoSoat1Entities(true, -1, -1);
    }

    public List<HospInfquirurgicoSoat1> findHospInfquirurgicoSoat1Entities(int maxResults, int firstResult) {
        return findHospInfquirurgicoSoat1Entities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgicoSoat1> findHospInfquirurgicoSoat1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgicoSoat1.class));
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

    public HospInfquirurgicoSoat1 findHospInfquirurgicoSoat1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgicoSoat1.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoSoat1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgicoSoat1> rt = cq.from(HospInfquirurgicoSoat1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
