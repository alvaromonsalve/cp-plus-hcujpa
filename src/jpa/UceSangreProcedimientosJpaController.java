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
import entidades_EJB.UceSangre;
import entidades_EJB.UceSangreProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceSangreProcedimientosJpaController implements Serializable {

    public UceSangreProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceSangreProcedimientos uceSangreProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangre idsangre = uceSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uceSangreProcedimientos.setIdsangre(idsangre);
            }
            em.persist(uceSangreProcedimientos);
            if (idsangre != null) {
                idsangre.getUceSangreProcedimientosList().add(uceSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceSangreProcedimientos uceSangreProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangreProcedimientos persistentUceSangreProcedimientos = em.find(UceSangreProcedimientos.class, uceSangreProcedimientos.getId());
            UceSangre idsangreOld = persistentUceSangreProcedimientos.getIdsangre();
            UceSangre idsangreNew = uceSangreProcedimientos.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uceSangreProcedimientos.setIdsangre(idsangreNew);
            }
            uceSangreProcedimientos = em.merge(uceSangreProcedimientos);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUceSangreProcedimientosList().remove(uceSangreProcedimientos);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUceSangreProcedimientosList().add(uceSangreProcedimientos);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceSangreProcedimientos.getId();
                if (findUceSangreProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uceSangreProcedimientos with id " + id + " no longer exists.");
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
            UceSangreProcedimientos uceSangreProcedimientos;
            try {
                uceSangreProcedimientos = em.getReference(UceSangreProcedimientos.class, id);
                uceSangreProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceSangreProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UceSangre idsangre = uceSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre.getUceSangreProcedimientosList().remove(uceSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.remove(uceSangreProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceSangreProcedimientos> findUceSangreProcedimientosEntities() {
        return findUceSangreProcedimientosEntities(true, -1, -1);
    }

    public List<UceSangreProcedimientos> findUceSangreProcedimientosEntities(int maxResults, int firstResult) {
        return findUceSangreProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UceSangreProcedimientos> findUceSangreProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceSangreProcedimientos.class));
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

    public UceSangreProcedimientos findUceSangreProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceSangreProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceSangreProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceSangreProcedimientos> rt = cq.from(UceSangreProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
