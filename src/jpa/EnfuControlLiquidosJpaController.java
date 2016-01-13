/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuApLiquidos;
import entidades_EJB.EnfuControlLiquidos;
import entidades_EJB.InfoHistoriac;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuControlLiquidosJpaController implements Serializable {

    public EnfuControlLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuControlLiquidos enfuControlLiquidos) {
        if (enfuControlLiquidos.getEnfuApLiquidosList() == null) {
            enfuControlLiquidos.setEnfuApLiquidosList(new ArrayList<EnfuApLiquidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EnfuApLiquidos> attachedEnfuApLiquidosList = new ArrayList<EnfuApLiquidos>();
            for (EnfuApLiquidos enfuApLiquidosListEnfuApLiquidosToAttach : enfuControlLiquidos.getEnfuApLiquidosList()) {
                enfuApLiquidosListEnfuApLiquidosToAttach = em.getReference(enfuApLiquidosListEnfuApLiquidosToAttach.getClass(), enfuApLiquidosListEnfuApLiquidosToAttach.getId());
                attachedEnfuApLiquidosList.add(enfuApLiquidosListEnfuApLiquidosToAttach);
            }
            enfuControlLiquidos.setEnfuApLiquidosList(attachedEnfuApLiquidosList);
            em.persist(enfuControlLiquidos);
            for (EnfuApLiquidos enfuApLiquidosListEnfuApLiquidos : enfuControlLiquidos.getEnfuApLiquidosList()) {
                EnfuControlLiquidos oldIdControlesOfEnfuApLiquidosListEnfuApLiquidos = enfuApLiquidosListEnfuApLiquidos.getIdControles();
                enfuApLiquidosListEnfuApLiquidos.setIdControles(enfuControlLiquidos);
                enfuApLiquidosListEnfuApLiquidos = em.merge(enfuApLiquidosListEnfuApLiquidos);
                if (oldIdControlesOfEnfuApLiquidosListEnfuApLiquidos != null) {
                    oldIdControlesOfEnfuApLiquidosListEnfuApLiquidos.getEnfuApLiquidosList().remove(enfuApLiquidosListEnfuApLiquidos);
                    oldIdControlesOfEnfuApLiquidosListEnfuApLiquidos = em.merge(oldIdControlesOfEnfuApLiquidosListEnfuApLiquidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuControlLiquidos enfuControlLiquidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuControlLiquidos persistentEnfuControlLiquidos = em.find(EnfuControlLiquidos.class, enfuControlLiquidos.getId());
            List<EnfuApLiquidos> enfuApLiquidosListOld = persistentEnfuControlLiquidos.getEnfuApLiquidosList();
            List<EnfuApLiquidos> enfuApLiquidosListNew = enfuControlLiquidos.getEnfuApLiquidosList();
            List<String> illegalOrphanMessages = null;
            for (EnfuApLiquidos enfuApLiquidosListOldEnfuApLiquidos : enfuApLiquidosListOld) {
                if (!enfuApLiquidosListNew.contains(enfuApLiquidosListOldEnfuApLiquidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuApLiquidos " + enfuApLiquidosListOldEnfuApLiquidos + " since its idControles field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EnfuApLiquidos> attachedEnfuApLiquidosListNew = new ArrayList<EnfuApLiquidos>();
            for (EnfuApLiquidos enfuApLiquidosListNewEnfuApLiquidosToAttach : enfuApLiquidosListNew) {
                enfuApLiquidosListNewEnfuApLiquidosToAttach = em.getReference(enfuApLiquidosListNewEnfuApLiquidosToAttach.getClass(), enfuApLiquidosListNewEnfuApLiquidosToAttach.getId());
                attachedEnfuApLiquidosListNew.add(enfuApLiquidosListNewEnfuApLiquidosToAttach);
            }
            enfuApLiquidosListNew = attachedEnfuApLiquidosListNew;
            enfuControlLiquidos.setEnfuApLiquidosList(enfuApLiquidosListNew);
            enfuControlLiquidos = em.merge(enfuControlLiquidos);
            for (EnfuApLiquidos enfuApLiquidosListNewEnfuApLiquidos : enfuApLiquidosListNew) {
                if (!enfuApLiquidosListOld.contains(enfuApLiquidosListNewEnfuApLiquidos)) {
                    EnfuControlLiquidos oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos = enfuApLiquidosListNewEnfuApLiquidos.getIdControles();
                    enfuApLiquidosListNewEnfuApLiquidos.setIdControles(enfuControlLiquidos);
                    enfuApLiquidosListNewEnfuApLiquidos = em.merge(enfuApLiquidosListNewEnfuApLiquidos);
                    if (oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos != null && !oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos.equals(enfuControlLiquidos)) {
                        oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos.getEnfuApLiquidosList().remove(enfuApLiquidosListNewEnfuApLiquidos);
                        oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos = em.merge(oldIdControlesOfEnfuApLiquidosListNewEnfuApLiquidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuControlLiquidos.getId();
                if (findEnfuControlLiquidos(id) == null) {
                    throw new NonexistentEntityException("The enfuControlLiquidos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuControlLiquidos enfuControlLiquidos;
            try {
                enfuControlLiquidos = em.getReference(EnfuControlLiquidos.class, id);
                enfuControlLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuControlLiquidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EnfuApLiquidos> enfuApLiquidosListOrphanCheck = enfuControlLiquidos.getEnfuApLiquidosList();
            for (EnfuApLiquidos enfuApLiquidosListOrphanCheckEnfuApLiquidos : enfuApLiquidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuControlLiquidos (" + enfuControlLiquidos + ") cannot be destroyed since the EnfuApLiquidos " + enfuApLiquidosListOrphanCheckEnfuApLiquidos + " in its enfuApLiquidosList field has a non-nullable idControles field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enfuControlLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuControlLiquidos> findEnfuControlLiquidosEntities() {
        return findEnfuControlLiquidosEntities(true, -1, -1);
    }

    public List<EnfuControlLiquidos> findEnfuControlLiquidosEntities(int maxResults, int firstResult) {
        return findEnfuControlLiquidosEntities(false, maxResults, firstResult);
    }

    private List<EnfuControlLiquidos> findEnfuControlLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuControlLiquidos.class));
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

    public EnfuControlLiquidos findEnfuControlLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuControlLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuControlLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuControlLiquidos> rt = cq.from(EnfuControlLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuControlLiquidos> find_EnfuControl_Liquidos(int usuario, InfoHistoriac historia) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM EnfuControlLiquidos i WHERE i.idHistoria=:hc AND i.usr=:u_suario AND i.estado='1'");
            Q.setParameter("u_suario", usuario);
            Q.setParameter("hc", historia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return Q.getResultList();
    }
}
