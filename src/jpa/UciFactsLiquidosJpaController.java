/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsLiquidos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciLiquidos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UciFactsLiquidosJpaController implements Serializable {

    public UciFactsLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciFactsLiquidos uciFactsLiquidos) {
        if (uciFactsLiquidos.getUciLiquidosList() == null) {
            uciFactsLiquidos.setUciLiquidosList(new ArrayList<UciLiquidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciLiquidos> attachedUciLiquidosList = new ArrayList<UciLiquidos>();
            for (UciLiquidos uciLiquidosListUciLiquidosToAttach : uciFactsLiquidos.getUciLiquidosList()) {
                uciLiquidosListUciLiquidosToAttach = em.getReference(uciLiquidosListUciLiquidosToAttach.getClass(), uciLiquidosListUciLiquidosToAttach.getId());
                attachedUciLiquidosList.add(uciLiquidosListUciLiquidosToAttach);
            }
            uciFactsLiquidos.setUciLiquidosList(attachedUciLiquidosList);
            em.persist(uciFactsLiquidos);
            for (UciLiquidos uciLiquidosListUciLiquidos : uciFactsLiquidos.getUciLiquidosList()) {
                UciFactsLiquidos oldIdControlOOfUciLiquidosListUciLiquidos = uciLiquidosListUciLiquidos.getIdControlO();
                uciLiquidosListUciLiquidos.setIdControlO(uciFactsLiquidos);
                uciLiquidosListUciLiquidos = em.merge(uciLiquidosListUciLiquidos);
                if (oldIdControlOOfUciLiquidosListUciLiquidos != null) {
                    oldIdControlOOfUciLiquidosListUciLiquidos.getUciLiquidosList().remove(uciLiquidosListUciLiquidos);
                    oldIdControlOOfUciLiquidosListUciLiquidos = em.merge(oldIdControlOOfUciLiquidosListUciLiquidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciFactsLiquidos uciFactsLiquidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciFactsLiquidos persistentUciFactsLiquidos = em.find(UciFactsLiquidos.class, uciFactsLiquidos.getId());
            List<UciLiquidos> uciLiquidosListOld = persistentUciFactsLiquidos.getUciLiquidosList();
            List<UciLiquidos> uciLiquidosListNew = uciFactsLiquidos.getUciLiquidosList();
            List<String> illegalOrphanMessages = null;
            for (UciLiquidos uciLiquidosListOldUciLiquidos : uciLiquidosListOld) {
                if (!uciLiquidosListNew.contains(uciLiquidosListOldUciLiquidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciLiquidos " + uciLiquidosListOldUciLiquidos + " since its idControlO field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciLiquidos> attachedUciLiquidosListNew = new ArrayList<UciLiquidos>();
            for (UciLiquidos uciLiquidosListNewUciLiquidosToAttach : uciLiquidosListNew) {
                uciLiquidosListNewUciLiquidosToAttach = em.getReference(uciLiquidosListNewUciLiquidosToAttach.getClass(), uciLiquidosListNewUciLiquidosToAttach.getId());
                attachedUciLiquidosListNew.add(uciLiquidosListNewUciLiquidosToAttach);
            }
            uciLiquidosListNew = attachedUciLiquidosListNew;
            uciFactsLiquidos.setUciLiquidosList(uciLiquidosListNew);
            uciFactsLiquidos = em.merge(uciFactsLiquidos);
            for (UciLiquidos uciLiquidosListNewUciLiquidos : uciLiquidosListNew) {
                if (!uciLiquidosListOld.contains(uciLiquidosListNewUciLiquidos)) {
                    UciFactsLiquidos oldIdControlOOfUciLiquidosListNewUciLiquidos = uciLiquidosListNewUciLiquidos.getIdControlO();
                    uciLiquidosListNewUciLiquidos.setIdControlO(uciFactsLiquidos);
                    uciLiquidosListNewUciLiquidos = em.merge(uciLiquidosListNewUciLiquidos);
                    if (oldIdControlOOfUciLiquidosListNewUciLiquidos != null && !oldIdControlOOfUciLiquidosListNewUciLiquidos.equals(uciFactsLiquidos)) {
                        oldIdControlOOfUciLiquidosListNewUciLiquidos.getUciLiquidosList().remove(uciLiquidosListNewUciLiquidos);
                        oldIdControlOOfUciLiquidosListNewUciLiquidos = em.merge(oldIdControlOOfUciLiquidosListNewUciLiquidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciFactsLiquidos.getId();
                if (findUciFactsLiquidos(id) == null) {
                    throw new NonexistentEntityException("The uciFactsLiquidos with id " + id + " no longer exists.");
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
            UciFactsLiquidos uciFactsLiquidos;
            try {
                uciFactsLiquidos = em.getReference(UciFactsLiquidos.class, id);
                uciFactsLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciFactsLiquidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciLiquidos> uciLiquidosListOrphanCheck = uciFactsLiquidos.getUciLiquidosList();
            for (UciLiquidos uciLiquidosListOrphanCheckUciLiquidos : uciLiquidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciFactsLiquidos (" + uciFactsLiquidos + ") cannot be destroyed since the UciLiquidos " + uciLiquidosListOrphanCheckUciLiquidos + " in its uciLiquidosList field has a non-nullable idControlO field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciFactsLiquidos> findUciFactsLiquidosEntities() {
        return findUciFactsLiquidosEntities(true, -1, -1);
    }

    public List<UciFactsLiquidos> findUciFactsLiquidosEntities(int maxResults, int firstResult) {
        return findUciFactsLiquidosEntities(false, maxResults, firstResult);
    }

    private List<UciFactsLiquidos> findUciFactsLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciFactsLiquidos.class));
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

    public UciFactsLiquidos findUciFactsLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciFactsLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciFactsLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciFactsLiquidos> rt = cq.from(UciFactsLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
