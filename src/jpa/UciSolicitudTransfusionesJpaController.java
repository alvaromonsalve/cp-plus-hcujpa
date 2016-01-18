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
import entidades_EJB.UciAplicacionesTransfusion;
import entidades_EJB.UciSolicitudTransfusiones;
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
public class UciSolicitudTransfusionesJpaController implements Serializable {

    public UciSolicitudTransfusionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciSolicitudTransfusiones uciSolicitudTransfusiones) {
        if (uciSolicitudTransfusiones.getUciAplicacionesTransfusionList() == null) {
            uciSolicitudTransfusiones.setUciAplicacionesTransfusionList(new ArrayList<UciAplicacionesTransfusion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciAplicacionesTransfusion> attachedUciAplicacionesTransfusionList = new ArrayList<UciAplicacionesTransfusion>();
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListUciAplicacionesTransfusionToAttach : uciSolicitudTransfusiones.getUciAplicacionesTransfusionList()) {
                uciAplicacionesTransfusionListUciAplicacionesTransfusionToAttach = em.getReference(uciAplicacionesTransfusionListUciAplicacionesTransfusionToAttach.getClass(), uciAplicacionesTransfusionListUciAplicacionesTransfusionToAttach.getId());
                attachedUciAplicacionesTransfusionList.add(uciAplicacionesTransfusionListUciAplicacionesTransfusionToAttach);
            }
            uciSolicitudTransfusiones.setUciAplicacionesTransfusionList(attachedUciAplicacionesTransfusionList);
            em.persist(uciSolicitudTransfusiones);
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListUciAplicacionesTransfusion : uciSolicitudTransfusiones.getUciAplicacionesTransfusionList()) {
                UciSolicitudTransfusiones oldIdTransfusionOfUciAplicacionesTransfusionListUciAplicacionesTransfusion = uciAplicacionesTransfusionListUciAplicacionesTransfusion.getIdTransfusion();
                uciAplicacionesTransfusionListUciAplicacionesTransfusion.setIdTransfusion(uciSolicitudTransfusiones);
                uciAplicacionesTransfusionListUciAplicacionesTransfusion = em.merge(uciAplicacionesTransfusionListUciAplicacionesTransfusion);
                if (oldIdTransfusionOfUciAplicacionesTransfusionListUciAplicacionesTransfusion != null) {
                    oldIdTransfusionOfUciAplicacionesTransfusionListUciAplicacionesTransfusion.getUciAplicacionesTransfusionList().remove(uciAplicacionesTransfusionListUciAplicacionesTransfusion);
                    oldIdTransfusionOfUciAplicacionesTransfusionListUciAplicacionesTransfusion = em.merge(oldIdTransfusionOfUciAplicacionesTransfusionListUciAplicacionesTransfusion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciSolicitudTransfusiones uciSolicitudTransfusiones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSolicitudTransfusiones persistentUciSolicitudTransfusiones = em.find(UciSolicitudTransfusiones.class, uciSolicitudTransfusiones.getId());
            List<UciAplicacionesTransfusion> uciAplicacionesTransfusionListOld = persistentUciSolicitudTransfusiones.getUciAplicacionesTransfusionList();
            List<UciAplicacionesTransfusion> uciAplicacionesTransfusionListNew = uciSolicitudTransfusiones.getUciAplicacionesTransfusionList();
            List<String> illegalOrphanMessages = null;
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListOldUciAplicacionesTransfusion : uciAplicacionesTransfusionListOld) {
                if (!uciAplicacionesTransfusionListNew.contains(uciAplicacionesTransfusionListOldUciAplicacionesTransfusion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciAplicacionesTransfusion " + uciAplicacionesTransfusionListOldUciAplicacionesTransfusion + " since its idTransfusion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciAplicacionesTransfusion> attachedUciAplicacionesTransfusionListNew = new ArrayList<UciAplicacionesTransfusion>();
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListNewUciAplicacionesTransfusionToAttach : uciAplicacionesTransfusionListNew) {
                uciAplicacionesTransfusionListNewUciAplicacionesTransfusionToAttach = em.getReference(uciAplicacionesTransfusionListNewUciAplicacionesTransfusionToAttach.getClass(), uciAplicacionesTransfusionListNewUciAplicacionesTransfusionToAttach.getId());
                attachedUciAplicacionesTransfusionListNew.add(uciAplicacionesTransfusionListNewUciAplicacionesTransfusionToAttach);
            }
            uciAplicacionesTransfusionListNew = attachedUciAplicacionesTransfusionListNew;
            uciSolicitudTransfusiones.setUciAplicacionesTransfusionList(uciAplicacionesTransfusionListNew);
            uciSolicitudTransfusiones = em.merge(uciSolicitudTransfusiones);
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListNewUciAplicacionesTransfusion : uciAplicacionesTransfusionListNew) {
                if (!uciAplicacionesTransfusionListOld.contains(uciAplicacionesTransfusionListNewUciAplicacionesTransfusion)) {
                    UciSolicitudTransfusiones oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion = uciAplicacionesTransfusionListNewUciAplicacionesTransfusion.getIdTransfusion();
                    uciAplicacionesTransfusionListNewUciAplicacionesTransfusion.setIdTransfusion(uciSolicitudTransfusiones);
                    uciAplicacionesTransfusionListNewUciAplicacionesTransfusion = em.merge(uciAplicacionesTransfusionListNewUciAplicacionesTransfusion);
                    if (oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion != null && !oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion.equals(uciSolicitudTransfusiones)) {
                        oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion.getUciAplicacionesTransfusionList().remove(uciAplicacionesTransfusionListNewUciAplicacionesTransfusion);
                        oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion = em.merge(oldIdTransfusionOfUciAplicacionesTransfusionListNewUciAplicacionesTransfusion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciSolicitudTransfusiones.getId();
                if (findUciSolicitudTransfusiones(id) == null) {
                    throw new NonexistentEntityException("The uciSolicitudTransfusiones with id " + id + " no longer exists.");
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
            UciSolicitudTransfusiones uciSolicitudTransfusiones;
            try {
                uciSolicitudTransfusiones = em.getReference(UciSolicitudTransfusiones.class, id);
                uciSolicitudTransfusiones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciSolicitudTransfusiones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciAplicacionesTransfusion> uciAplicacionesTransfusionListOrphanCheck = uciSolicitudTransfusiones.getUciAplicacionesTransfusionList();
            for (UciAplicacionesTransfusion uciAplicacionesTransfusionListOrphanCheckUciAplicacionesTransfusion : uciAplicacionesTransfusionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciSolicitudTransfusiones (" + uciSolicitudTransfusiones + ") cannot be destroyed since the UciAplicacionesTransfusion " + uciAplicacionesTransfusionListOrphanCheckUciAplicacionesTransfusion + " in its uciAplicacionesTransfusionList field has a non-nullable idTransfusion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciSolicitudTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciSolicitudTransfusiones> findUciSolicitudTransfusionesEntities() {
        return findUciSolicitudTransfusionesEntities(true, -1, -1);
    }

    public List<UciSolicitudTransfusiones> findUciSolicitudTransfusionesEntities(int maxResults, int firstResult) {
        return findUciSolicitudTransfusionesEntities(false, maxResults, firstResult);
    }

    private List<UciSolicitudTransfusiones> findUciSolicitudTransfusionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciSolicitudTransfusiones.class));
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

    public UciSolicitudTransfusiones findUciSolicitudTransfusiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciSolicitudTransfusiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciSolicitudTransfusionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciSolicitudTransfusiones> rt = cq.from(UciSolicitudTransfusiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
