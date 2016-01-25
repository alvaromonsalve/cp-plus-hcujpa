/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsLiquidos;
import entidades_EJB.UceHistoriac;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceLiquidos;
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
public class UceFactsLiquidosJpaController implements Serializable {

    public UceFactsLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceFactsLiquidos uceFactsLiquidos) {
        if (uceFactsLiquidos.getUceLiquidosList() == null) {
            uceFactsLiquidos.setUceLiquidosList(new ArrayList<UceLiquidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceLiquidos> attachedUceLiquidosList = new ArrayList<UceLiquidos>();
            for (UceLiquidos uceLiquidosListUceLiquidosToAttach : uceFactsLiquidos.getUceLiquidosList()) {
                uceLiquidosListUceLiquidosToAttach = em.getReference(uceLiquidosListUceLiquidosToAttach.getClass(), uceLiquidosListUceLiquidosToAttach.getId());
                attachedUceLiquidosList.add(uceLiquidosListUceLiquidosToAttach);
            }
            uceFactsLiquidos.setUceLiquidosList(attachedUceLiquidosList);
            em.persist(uceFactsLiquidos);
            for (UceLiquidos uceLiquidosListUceLiquidos : uceFactsLiquidos.getUceLiquidosList()) {
                UceFactsLiquidos oldIdControlOOfUceLiquidosListUceLiquidos = uceLiquidosListUceLiquidos.getIdControlO();
                uceLiquidosListUceLiquidos.setIdControlO(uceFactsLiquidos);
                uceLiquidosListUceLiquidos = em.merge(uceLiquidosListUceLiquidos);
                if (oldIdControlOOfUceLiquidosListUceLiquidos != null) {
                    oldIdControlOOfUceLiquidosListUceLiquidos.getUceLiquidosList().remove(uceLiquidosListUceLiquidos);
                    oldIdControlOOfUceLiquidosListUceLiquidos = em.merge(oldIdControlOOfUceLiquidosListUceLiquidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceFactsLiquidos uceFactsLiquidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceFactsLiquidos persistentUceFactsLiquidos = em.find(UceFactsLiquidos.class, uceFactsLiquidos.getId());
            List<UceLiquidos> uceLiquidosListOld = persistentUceFactsLiquidos.getUceLiquidosList();
            List<UceLiquidos> uceLiquidosListNew = uceFactsLiquidos.getUceLiquidosList();
            List<String> illegalOrphanMessages = null;
            for (UceLiquidos uceLiquidosListOldUceLiquidos : uceLiquidosListOld) {
                if (!uceLiquidosListNew.contains(uceLiquidosListOldUceLiquidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceLiquidos " + uceLiquidosListOldUceLiquidos + " since its idControlO field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceLiquidos> attachedUceLiquidosListNew = new ArrayList<UceLiquidos>();
            for (UceLiquidos uceLiquidosListNewUceLiquidosToAttach : uceLiquidosListNew) {
                uceLiquidosListNewUceLiquidosToAttach = em.getReference(uceLiquidosListNewUceLiquidosToAttach.getClass(), uceLiquidosListNewUceLiquidosToAttach.getId());
                attachedUceLiquidosListNew.add(uceLiquidosListNewUceLiquidosToAttach);
            }
            uceLiquidosListNew = attachedUceLiquidosListNew;
            uceFactsLiquidos.setUceLiquidosList(uceLiquidosListNew);
            uceFactsLiquidos = em.merge(uceFactsLiquidos);
            for (UceLiquidos uceLiquidosListNewUceLiquidos : uceLiquidosListNew) {
                if (!uceLiquidosListOld.contains(uceLiquidosListNewUceLiquidos)) {
                    UceFactsLiquidos oldIdControlOOfUceLiquidosListNewUceLiquidos = uceLiquidosListNewUceLiquidos.getIdControlO();
                    uceLiquidosListNewUceLiquidos.setIdControlO(uceFactsLiquidos);
                    uceLiquidosListNewUceLiquidos = em.merge(uceLiquidosListNewUceLiquidos);
                    if (oldIdControlOOfUceLiquidosListNewUceLiquidos != null && !oldIdControlOOfUceLiquidosListNewUceLiquidos.equals(uceFactsLiquidos)) {
                        oldIdControlOOfUceLiquidosListNewUceLiquidos.getUceLiquidosList().remove(uceLiquidosListNewUceLiquidos);
                        oldIdControlOOfUceLiquidosListNewUceLiquidos = em.merge(oldIdControlOOfUceLiquidosListNewUceLiquidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceFactsLiquidos.getId();
                if (findUceFactsLiquidos(id) == null) {
                    throw new NonexistentEntityException("The uceFactsLiquidos with id " + id + " no longer exists.");
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
            UceFactsLiquidos uceFactsLiquidos;
            try {
                uceFactsLiquidos = em.getReference(UceFactsLiquidos.class, id);
                uceFactsLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceFactsLiquidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceLiquidos> uceLiquidosListOrphanCheck = uceFactsLiquidos.getUceLiquidosList();
            for (UceLiquidos uceLiquidosListOrphanCheckUceLiquidos : uceLiquidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceFactsLiquidos (" + uceFactsLiquidos + ") cannot be destroyed since the UceLiquidos " + uceLiquidosListOrphanCheckUceLiquidos + " in its uceLiquidosList field has a non-nullable idControlO field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceFactsLiquidos> findUceFactsLiquidosEntities() {
        return findUceFactsLiquidosEntities(true, -1, -1);
    }

    public List<UceFactsLiquidos> findUceFactsLiquidosEntities(int maxResults, int firstResult) {
        return findUceFactsLiquidosEntities(false, maxResults, firstResult);
    }

    private List<UceFactsLiquidos> findUceFactsLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceFactsLiquidos.class));
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

    public UceFactsLiquidos findUceFactsLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceFactsLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceFactsLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceFactsLiquidos> rt = cq.from(UceFactsLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceFactsLiquidos> getControlesLiquidos(UceHistoriac hc) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT c FROM UceFactsLiquidos c WHERE c.idHistoria=:h AND c.estado='1'");
        q.setParameter("h", hc);
        return q.getResultList();
    }
}
