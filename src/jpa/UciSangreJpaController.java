/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UciSangre;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciSangreCie10;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciSangreProcedimientos;
import entidades_EJB.UciTransfusiones;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciSangreJpaController implements Serializable {

    public UciSangreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciSangre uciSangre) {
        if (uciSangre.getUciSangreCie10List() == null) {
            uciSangre.setUciSangreCie10List(new ArrayList<UciSangreCie10>());
        }
        if (uciSangre.getUciSangreProcedimientosList() == null) {
            uciSangre.setUciSangreProcedimientosList(new ArrayList<UciSangreProcedimientos>());
        }
        if (uciSangre.getUciTransfusionesList() == null) {
            uciSangre.setUciTransfusionesList(new ArrayList<UciTransfusiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciSangreCie10> attachedUciSangreCie10List = new ArrayList<UciSangreCie10>();
            for (UciSangreCie10 uciSangreCie10ListUciSangreCie10ToAttach : uciSangre.getUciSangreCie10List()) {
                uciSangreCie10ListUciSangreCie10ToAttach = em.getReference(uciSangreCie10ListUciSangreCie10ToAttach.getClass(), uciSangreCie10ListUciSangreCie10ToAttach.getId());
                attachedUciSangreCie10List.add(uciSangreCie10ListUciSangreCie10ToAttach);
            }
            uciSangre.setUciSangreCie10List(attachedUciSangreCie10List);
            List<UciSangreProcedimientos> attachedUciSangreProcedimientosList = new ArrayList<UciSangreProcedimientos>();
            for (UciSangreProcedimientos uciSangreProcedimientosListUciSangreProcedimientosToAttach : uciSangre.getUciSangreProcedimientosList()) {
                uciSangreProcedimientosListUciSangreProcedimientosToAttach = em.getReference(uciSangreProcedimientosListUciSangreProcedimientosToAttach.getClass(), uciSangreProcedimientosListUciSangreProcedimientosToAttach.getId());
                attachedUciSangreProcedimientosList.add(uciSangreProcedimientosListUciSangreProcedimientosToAttach);
            }
            uciSangre.setUciSangreProcedimientosList(attachedUciSangreProcedimientosList);
            List<UciTransfusiones> attachedUciTransfusionesList = new ArrayList<UciTransfusiones>();
            for (UciTransfusiones uciTransfusionesListUciTransfusionesToAttach : uciSangre.getUciTransfusionesList()) {
                uciTransfusionesListUciTransfusionesToAttach = em.getReference(uciTransfusionesListUciTransfusionesToAttach.getClass(), uciTransfusionesListUciTransfusionesToAttach.getId());
                attachedUciTransfusionesList.add(uciTransfusionesListUciTransfusionesToAttach);
            }
            uciSangre.setUciTransfusionesList(attachedUciTransfusionesList);
            em.persist(uciSangre);
            for (UciSangreCie10 uciSangreCie10ListUciSangreCie10 : uciSangre.getUciSangreCie10List()) {
                UciSangre oldIdsangreOfUciSangreCie10ListUciSangreCie10 = uciSangreCie10ListUciSangreCie10.getIdsangre();
                uciSangreCie10ListUciSangreCie10.setIdsangre(uciSangre);
                uciSangreCie10ListUciSangreCie10 = em.merge(uciSangreCie10ListUciSangreCie10);
                if (oldIdsangreOfUciSangreCie10ListUciSangreCie10 != null) {
                    oldIdsangreOfUciSangreCie10ListUciSangreCie10.getUciSangreCie10List().remove(uciSangreCie10ListUciSangreCie10);
                    oldIdsangreOfUciSangreCie10ListUciSangreCie10 = em.merge(oldIdsangreOfUciSangreCie10ListUciSangreCie10);
                }
            }
            for (UciSangreProcedimientos uciSangreProcedimientosListUciSangreProcedimientos : uciSangre.getUciSangreProcedimientosList()) {
                UciSangre oldIdsangreOfUciSangreProcedimientosListUciSangreProcedimientos = uciSangreProcedimientosListUciSangreProcedimientos.getIdsangre();
                uciSangreProcedimientosListUciSangreProcedimientos.setIdsangre(uciSangre);
                uciSangreProcedimientosListUciSangreProcedimientos = em.merge(uciSangreProcedimientosListUciSangreProcedimientos);
                if (oldIdsangreOfUciSangreProcedimientosListUciSangreProcedimientos != null) {
                    oldIdsangreOfUciSangreProcedimientosListUciSangreProcedimientos.getUciSangreProcedimientosList().remove(uciSangreProcedimientosListUciSangreProcedimientos);
                    oldIdsangreOfUciSangreProcedimientosListUciSangreProcedimientos = em.merge(oldIdsangreOfUciSangreProcedimientosListUciSangreProcedimientos);
                }
            }
            for (UciTransfusiones uciTransfusionesListUciTransfusiones : uciSangre.getUciTransfusionesList()) {
                UciSangre oldIdsangreOfUciTransfusionesListUciTransfusiones = uciTransfusionesListUciTransfusiones.getIdsangre();
                uciTransfusionesListUciTransfusiones.setIdsangre(uciSangre);
                uciTransfusionesListUciTransfusiones = em.merge(uciTransfusionesListUciTransfusiones);
                if (oldIdsangreOfUciTransfusionesListUciTransfusiones != null) {
                    oldIdsangreOfUciTransfusionesListUciTransfusiones.getUciTransfusionesList().remove(uciTransfusionesListUciTransfusiones);
                    oldIdsangreOfUciTransfusionesListUciTransfusiones = em.merge(oldIdsangreOfUciTransfusionesListUciTransfusiones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciSangre uciSangre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangre persistentUciSangre = em.find(UciSangre.class, uciSangre.getId());
            List<UciSangreCie10> uciSangreCie10ListOld = persistentUciSangre.getUciSangreCie10List();
            List<UciSangreCie10> uciSangreCie10ListNew = uciSangre.getUciSangreCie10List();
            List<UciSangreProcedimientos> uciSangreProcedimientosListOld = persistentUciSangre.getUciSangreProcedimientosList();
            List<UciSangreProcedimientos> uciSangreProcedimientosListNew = uciSangre.getUciSangreProcedimientosList();
            List<UciTransfusiones> uciTransfusionesListOld = persistentUciSangre.getUciTransfusionesList();
            List<UciTransfusiones> uciTransfusionesListNew = uciSangre.getUciTransfusionesList();
            List<String> illegalOrphanMessages = null;
            for (UciSangreCie10 uciSangreCie10ListOldUciSangreCie10 : uciSangreCie10ListOld) {
                if (!uciSangreCie10ListNew.contains(uciSangreCie10ListOldUciSangreCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciSangreCie10 " + uciSangreCie10ListOldUciSangreCie10 + " since its idsangre field is not nullable.");
                }
            }
            for (UciSangreProcedimientos uciSangreProcedimientosListOldUciSangreProcedimientos : uciSangreProcedimientosListOld) {
                if (!uciSangreProcedimientosListNew.contains(uciSangreProcedimientosListOldUciSangreProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciSangreProcedimientos " + uciSangreProcedimientosListOldUciSangreProcedimientos + " since its idsangre field is not nullable.");
                }
            }
            for (UciTransfusiones uciTransfusionesListOldUciTransfusiones : uciTransfusionesListOld) {
                if (!uciTransfusionesListNew.contains(uciTransfusionesListOldUciTransfusiones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciTransfusiones " + uciTransfusionesListOldUciTransfusiones + " since its idsangre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciSangreCie10> attachedUciSangreCie10ListNew = new ArrayList<UciSangreCie10>();
            for (UciSangreCie10 uciSangreCie10ListNewUciSangreCie10ToAttach : uciSangreCie10ListNew) {
                uciSangreCie10ListNewUciSangreCie10ToAttach = em.getReference(uciSangreCie10ListNewUciSangreCie10ToAttach.getClass(), uciSangreCie10ListNewUciSangreCie10ToAttach.getId());
                attachedUciSangreCie10ListNew.add(uciSangreCie10ListNewUciSangreCie10ToAttach);
            }
            uciSangreCie10ListNew = attachedUciSangreCie10ListNew;
            uciSangre.setUciSangreCie10List(uciSangreCie10ListNew);
            List<UciSangreProcedimientos> attachedUciSangreProcedimientosListNew = new ArrayList<UciSangreProcedimientos>();
            for (UciSangreProcedimientos uciSangreProcedimientosListNewUciSangreProcedimientosToAttach : uciSangreProcedimientosListNew) {
                uciSangreProcedimientosListNewUciSangreProcedimientosToAttach = em.getReference(uciSangreProcedimientosListNewUciSangreProcedimientosToAttach.getClass(), uciSangreProcedimientosListNewUciSangreProcedimientosToAttach.getId());
                attachedUciSangreProcedimientosListNew.add(uciSangreProcedimientosListNewUciSangreProcedimientosToAttach);
            }
            uciSangreProcedimientosListNew = attachedUciSangreProcedimientosListNew;
            uciSangre.setUciSangreProcedimientosList(uciSangreProcedimientosListNew);
            List<UciTransfusiones> attachedUciTransfusionesListNew = new ArrayList<UciTransfusiones>();
            for (UciTransfusiones uciTransfusionesListNewUciTransfusionesToAttach : uciTransfusionesListNew) {
                uciTransfusionesListNewUciTransfusionesToAttach = em.getReference(uciTransfusionesListNewUciTransfusionesToAttach.getClass(), uciTransfusionesListNewUciTransfusionesToAttach.getId());
                attachedUciTransfusionesListNew.add(uciTransfusionesListNewUciTransfusionesToAttach);
            }
            uciTransfusionesListNew = attachedUciTransfusionesListNew;
            uciSangre.setUciTransfusionesList(uciTransfusionesListNew);
            uciSangre = em.merge(uciSangre);
            for (UciSangreCie10 uciSangreCie10ListNewUciSangreCie10 : uciSangreCie10ListNew) {
                if (!uciSangreCie10ListOld.contains(uciSangreCie10ListNewUciSangreCie10)) {
                    UciSangre oldIdsangreOfUciSangreCie10ListNewUciSangreCie10 = uciSangreCie10ListNewUciSangreCie10.getIdsangre();
                    uciSangreCie10ListNewUciSangreCie10.setIdsangre(uciSangre);
                    uciSangreCie10ListNewUciSangreCie10 = em.merge(uciSangreCie10ListNewUciSangreCie10);
                    if (oldIdsangreOfUciSangreCie10ListNewUciSangreCie10 != null && !oldIdsangreOfUciSangreCie10ListNewUciSangreCie10.equals(uciSangre)) {
                        oldIdsangreOfUciSangreCie10ListNewUciSangreCie10.getUciSangreCie10List().remove(uciSangreCie10ListNewUciSangreCie10);
                        oldIdsangreOfUciSangreCie10ListNewUciSangreCie10 = em.merge(oldIdsangreOfUciSangreCie10ListNewUciSangreCie10);
                    }
                }
            }
            for (UciSangreProcedimientos uciSangreProcedimientosListNewUciSangreProcedimientos : uciSangreProcedimientosListNew) {
                if (!uciSangreProcedimientosListOld.contains(uciSangreProcedimientosListNewUciSangreProcedimientos)) {
                    UciSangre oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos = uciSangreProcedimientosListNewUciSangreProcedimientos.getIdsangre();
                    uciSangreProcedimientosListNewUciSangreProcedimientos.setIdsangre(uciSangre);
                    uciSangreProcedimientosListNewUciSangreProcedimientos = em.merge(uciSangreProcedimientosListNewUciSangreProcedimientos);
                    if (oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos != null && !oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos.equals(uciSangre)) {
                        oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos.getUciSangreProcedimientosList().remove(uciSangreProcedimientosListNewUciSangreProcedimientos);
                        oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos = em.merge(oldIdsangreOfUciSangreProcedimientosListNewUciSangreProcedimientos);
                    }
                }
            }
            for (UciTransfusiones uciTransfusionesListNewUciTransfusiones : uciTransfusionesListNew) {
                if (!uciTransfusionesListOld.contains(uciTransfusionesListNewUciTransfusiones)) {
                    UciSangre oldIdsangreOfUciTransfusionesListNewUciTransfusiones = uciTransfusionesListNewUciTransfusiones.getIdsangre();
                    uciTransfusionesListNewUciTransfusiones.setIdsangre(uciSangre);
                    uciTransfusionesListNewUciTransfusiones = em.merge(uciTransfusionesListNewUciTransfusiones);
                    if (oldIdsangreOfUciTransfusionesListNewUciTransfusiones != null && !oldIdsangreOfUciTransfusionesListNewUciTransfusiones.equals(uciSangre)) {
                        oldIdsangreOfUciTransfusionesListNewUciTransfusiones.getUciTransfusionesList().remove(uciTransfusionesListNewUciTransfusiones);
                        oldIdsangreOfUciTransfusionesListNewUciTransfusiones = em.merge(oldIdsangreOfUciTransfusionesListNewUciTransfusiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciSangre.getId();
                if (findUciSangre(id) == null) {
                    throw new NonexistentEntityException("The uciSangre with id " + id + " no longer exists.");
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
            UciSangre uciSangre;
            try {
                uciSangre = em.getReference(UciSangre.class, id);
                uciSangre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciSangre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciSangreCie10> uciSangreCie10ListOrphanCheck = uciSangre.getUciSangreCie10List();
            for (UciSangreCie10 uciSangreCie10ListOrphanCheckUciSangreCie10 : uciSangreCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciSangre (" + uciSangre + ") cannot be destroyed since the UciSangreCie10 " + uciSangreCie10ListOrphanCheckUciSangreCie10 + " in its uciSangreCie10List field has a non-nullable idsangre field.");
            }
            List<UciSangreProcedimientos> uciSangreProcedimientosListOrphanCheck = uciSangre.getUciSangreProcedimientosList();
            for (UciSangreProcedimientos uciSangreProcedimientosListOrphanCheckUciSangreProcedimientos : uciSangreProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciSangre (" + uciSangre + ") cannot be destroyed since the UciSangreProcedimientos " + uciSangreProcedimientosListOrphanCheckUciSangreProcedimientos + " in its uciSangreProcedimientosList field has a non-nullable idsangre field.");
            }
            List<UciTransfusiones> uciTransfusionesListOrphanCheck = uciSangre.getUciTransfusionesList();
            for (UciTransfusiones uciTransfusionesListOrphanCheckUciTransfusiones : uciTransfusionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciSangre (" + uciSangre + ") cannot be destroyed since the UciTransfusiones " + uciTransfusionesListOrphanCheckUciTransfusiones + " in its uciTransfusionesList field has a non-nullable idsangre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciSangre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciSangre> findUciSangreEntities() {
        return findUciSangreEntities(true, -1, -1);
    }

    public List<UciSangre> findUciSangreEntities(int maxResults, int firstResult) {
        return findUciSangreEntities(false, maxResults, firstResult);
    }

    private List<UciSangre> findUciSangreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciSangre.class));
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

    public UciSangre findUciSangre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciSangre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciSangreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciSangre> rt = cq.from(UciSangre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
