/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CmEspPprofesional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.CmProfesionales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class CmEspPprofesionalJpaController implements Serializable {

    public CmEspPprofesionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CmEspPprofesional cmEspPprofesional) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CmProfesionales idProfesional = cmEspPprofesional.getIdProfesional();
            if (idProfesional != null) {
                idProfesional = em.getReference(idProfesional.getClass(), idProfesional.getId());
                cmEspPprofesional.setIdProfesional(idProfesional);
            }
            em.persist(cmEspPprofesional);
            if (idProfesional != null) {
                idProfesional.getCmEspPprofesionalList().add(cmEspPprofesional);
                idProfesional = em.merge(idProfesional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CmEspPprofesional cmEspPprofesional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CmEspPprofesional persistentCmEspPprofesional = em.find(CmEspPprofesional.class, cmEspPprofesional.getId());
            CmProfesionales idProfesionalOld = persistentCmEspPprofesional.getIdProfesional();
            CmProfesionales idProfesionalNew = cmEspPprofesional.getIdProfesional();
            if (idProfesionalNew != null) {
                idProfesionalNew = em.getReference(idProfesionalNew.getClass(), idProfesionalNew.getId());
                cmEspPprofesional.setIdProfesional(idProfesionalNew);
            }
            cmEspPprofesional = em.merge(cmEspPprofesional);
            if (idProfesionalOld != null && !idProfesionalOld.equals(idProfesionalNew)) {
                idProfesionalOld.getCmEspPprofesionalList().remove(cmEspPprofesional);
                idProfesionalOld = em.merge(idProfesionalOld);
            }
            if (idProfesionalNew != null && !idProfesionalNew.equals(idProfesionalOld)) {
                idProfesionalNew.getCmEspPprofesionalList().add(cmEspPprofesional);
                idProfesionalNew = em.merge(idProfesionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cmEspPprofesional.getId();
                if (findCmEspPprofesional(id) == null) {
                    throw new NonexistentEntityException("The cmEspPprofesional with id " + id + " no longer exists.");
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
            CmEspPprofesional cmEspPprofesional;
            try {
                cmEspPprofesional = em.getReference(CmEspPprofesional.class, id);
                cmEspPprofesional.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cmEspPprofesional with id " + id + " no longer exists.", enfe);
            }
            CmProfesionales idProfesional = cmEspPprofesional.getIdProfesional();
            if (idProfesional != null) {
                idProfesional.getCmEspPprofesionalList().remove(cmEspPprofesional);
                idProfesional = em.merge(idProfesional);
            }
            em.remove(cmEspPprofesional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CmEspPprofesional> findCmEspPprofesionalEntities() {
        return findCmEspPprofesionalEntities(true, -1, -1);
    }

    public List<CmEspPprofesional> findCmEspPprofesionalEntities(int maxResults, int firstResult) {
        return findCmEspPprofesionalEntities(false, maxResults, firstResult);
    }

    private List<CmEspPprofesional> findCmEspPprofesionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CmEspPprofesional.class));
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

    public CmEspPprofesional findCmEspPprofesional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CmEspPprofesional.class, id);
        } finally {
            em.close();
        }
    }

    public int getCmEspPprofesionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CmEspPprofesional> rt = cq.from(CmEspPprofesional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CmEspPprofesional> getProfesionales() {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT ep FROM CmEspPprofesional ep WHERE ep.idProfesional.estado='1'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<CmEspPprofesional> getProfesionalesESP(CmProfesionales p) {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT ep FROM CmEspPprofesional ep WHERE ep.idProfesional=:pro AND ep.idProfesional.estado='1'");
            Q.setParameter("pro", p);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<CmEspPprofesional> getProfesionalesESP(int p) {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT ep FROM CmEspPprofesional ep WHERE ep.idProfesional.id=:pro AND ep.idProfesional.estado='1'");
            Q.setParameter("pro", p);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<CmEspPprofesional> getProfesionalesESP2(CmProfesionales p) {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT ep FROM CmEspPprofesional ep WHERE ep.idProfesional=:pro AND ep.idProfesional.estado='1'");
            Q.setParameter("pro", p);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<CmEspPprofesional> getProfesionalesESP3(CmProfesionales p) {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT ep FROM CmEspPprofesional ep WHERE ep.idProfesional=:pro AND ep.idProfesional.estado='1'");
            Q.setParameter("pro", p);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
}
