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
import entidades_EJB.HospInfquirurgicoSoat2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoSoat2JpaController implements Serializable {

    public HospInfquirurgicoSoat2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgicoSoat2 hospInfquirurgicoSoat2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico idquirurgico = hospInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                hospInfquirurgicoSoat2.setIdquirurgico(idquirurgico);
            }
            em.persist(hospInfquirurgicoSoat2);
            if (idquirurgico != null) {
                idquirurgico.getHospInfquirurgicoSoat2List().add(hospInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgicoSoat2 hospInfquirurgicoSoat2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgicoSoat2 persistentHospInfquirurgicoSoat2 = em.find(HospInfquirurgicoSoat2.class, hospInfquirurgicoSoat2.getId());
            HospInfquirurgico idquirurgicoOld = persistentHospInfquirurgicoSoat2.getIdquirurgico();
            HospInfquirurgico idquirurgicoNew = hospInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                hospInfquirurgicoSoat2.setIdquirurgico(idquirurgicoNew);
            }
            hospInfquirurgicoSoat2 = em.merge(hospInfquirurgicoSoat2);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getHospInfquirurgicoSoat2List().remove(hospInfquirurgicoSoat2);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getHospInfquirurgicoSoat2List().add(hospInfquirurgicoSoat2);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgicoSoat2.getId();
                if (findHospInfquirurgicoSoat2(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgicoSoat2 with id " + id + " no longer exists.");
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
            HospInfquirurgicoSoat2 hospInfquirurgicoSoat2;
            try {
                hospInfquirurgicoSoat2 = em.getReference(HospInfquirurgicoSoat2.class, id);
                hospInfquirurgicoSoat2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgicoSoat2 with id " + id + " no longer exists.", enfe);
            }
            HospInfquirurgico idquirurgico = hospInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getHospInfquirurgicoSoat2List().remove(hospInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(hospInfquirurgicoSoat2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgicoSoat2> findHospInfquirurgicoSoat2Entities() {
        return findHospInfquirurgicoSoat2Entities(true, -1, -1);
    }

    public List<HospInfquirurgicoSoat2> findHospInfquirurgicoSoat2Entities(int maxResults, int firstResult) {
        return findHospInfquirurgicoSoat2Entities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgicoSoat2> findHospInfquirurgicoSoat2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgicoSoat2.class));
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

    public HospInfquirurgicoSoat2 findHospInfquirurgicoSoat2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgicoSoat2.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoSoat2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgicoSoat2> rt = cq.from(HospInfquirurgicoSoat2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
