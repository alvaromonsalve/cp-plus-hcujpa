/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UceSangre;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceSangreCie10;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceTransfusiones;
import entidades_EJB.UceSangreProcedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceSangreJpaController implements Serializable {

    public UceSangreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceSangre uceSangre) {
        if (uceSangre.getUceSangreCie10List() == null) {
            uceSangre.setUceSangreCie10List(new ArrayList<UceSangreCie10>());
        }
        if (uceSangre.getUceTransfusionesList() == null) {
            uceSangre.setUceTransfusionesList(new ArrayList<UceTransfusiones>());
        }
        if (uceSangre.getUceSangreProcedimientosList() == null) {
            uceSangre.setUceSangreProcedimientosList(new ArrayList<UceSangreProcedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceSangreCie10> attachedUceSangreCie10List = new ArrayList<UceSangreCie10>();
            for (UceSangreCie10 uceSangreCie10ListUceSangreCie10ToAttach : uceSangre.getUceSangreCie10List()) {
                uceSangreCie10ListUceSangreCie10ToAttach = em.getReference(uceSangreCie10ListUceSangreCie10ToAttach.getClass(), uceSangreCie10ListUceSangreCie10ToAttach.getId());
                attachedUceSangreCie10List.add(uceSangreCie10ListUceSangreCie10ToAttach);
            }
            uceSangre.setUceSangreCie10List(attachedUceSangreCie10List);
            List<UceTransfusiones> attachedUceTransfusionesList = new ArrayList<UceTransfusiones>();
            for (UceTransfusiones uceTransfusionesListUceTransfusionesToAttach : uceSangre.getUceTransfusionesList()) {
                uceTransfusionesListUceTransfusionesToAttach = em.getReference(uceTransfusionesListUceTransfusionesToAttach.getClass(), uceTransfusionesListUceTransfusionesToAttach.getId());
                attachedUceTransfusionesList.add(uceTransfusionesListUceTransfusionesToAttach);
            }
            uceSangre.setUceTransfusionesList(attachedUceTransfusionesList);
            List<UceSangreProcedimientos> attachedUceSangreProcedimientosList = new ArrayList<UceSangreProcedimientos>();
            for (UceSangreProcedimientos uceSangreProcedimientosListUceSangreProcedimientosToAttach : uceSangre.getUceSangreProcedimientosList()) {
                uceSangreProcedimientosListUceSangreProcedimientosToAttach = em.getReference(uceSangreProcedimientosListUceSangreProcedimientosToAttach.getClass(), uceSangreProcedimientosListUceSangreProcedimientosToAttach.getId());
                attachedUceSangreProcedimientosList.add(uceSangreProcedimientosListUceSangreProcedimientosToAttach);
            }
            uceSangre.setUceSangreProcedimientosList(attachedUceSangreProcedimientosList);
            em.persist(uceSangre);
            for (UceSangreCie10 uceSangreCie10ListUceSangreCie10 : uceSangre.getUceSangreCie10List()) {
                UceSangre oldIdsangreOfUceSangreCie10ListUceSangreCie10 = uceSangreCie10ListUceSangreCie10.getIdsangre();
                uceSangreCie10ListUceSangreCie10.setIdsangre(uceSangre);
                uceSangreCie10ListUceSangreCie10 = em.merge(uceSangreCie10ListUceSangreCie10);
                if (oldIdsangreOfUceSangreCie10ListUceSangreCie10 != null) {
                    oldIdsangreOfUceSangreCie10ListUceSangreCie10.getUceSangreCie10List().remove(uceSangreCie10ListUceSangreCie10);
                    oldIdsangreOfUceSangreCie10ListUceSangreCie10 = em.merge(oldIdsangreOfUceSangreCie10ListUceSangreCie10);
                }
            }
            for (UceTransfusiones uceTransfusionesListUceTransfusiones : uceSangre.getUceTransfusionesList()) {
                UceSangre oldIdsangreOfUceTransfusionesListUceTransfusiones = uceTransfusionesListUceTransfusiones.getIdsangre();
                uceTransfusionesListUceTransfusiones.setIdsangre(uceSangre);
                uceTransfusionesListUceTransfusiones = em.merge(uceTransfusionesListUceTransfusiones);
                if (oldIdsangreOfUceTransfusionesListUceTransfusiones != null) {
                    oldIdsangreOfUceTransfusionesListUceTransfusiones.getUceTransfusionesList().remove(uceTransfusionesListUceTransfusiones);
                    oldIdsangreOfUceTransfusionesListUceTransfusiones = em.merge(oldIdsangreOfUceTransfusionesListUceTransfusiones);
                }
            }
            for (UceSangreProcedimientos uceSangreProcedimientosListUceSangreProcedimientos : uceSangre.getUceSangreProcedimientosList()) {
                UceSangre oldIdsangreOfUceSangreProcedimientosListUceSangreProcedimientos = uceSangreProcedimientosListUceSangreProcedimientos.getIdsangre();
                uceSangreProcedimientosListUceSangreProcedimientos.setIdsangre(uceSangre);
                uceSangreProcedimientosListUceSangreProcedimientos = em.merge(uceSangreProcedimientosListUceSangreProcedimientos);
                if (oldIdsangreOfUceSangreProcedimientosListUceSangreProcedimientos != null) {
                    oldIdsangreOfUceSangreProcedimientosListUceSangreProcedimientos.getUceSangreProcedimientosList().remove(uceSangreProcedimientosListUceSangreProcedimientos);
                    oldIdsangreOfUceSangreProcedimientosListUceSangreProcedimientos = em.merge(oldIdsangreOfUceSangreProcedimientosListUceSangreProcedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceSangre uceSangre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangre persistentUceSangre = em.find(UceSangre.class, uceSangre.getId());
            List<UceSangreCie10> uceSangreCie10ListOld = persistentUceSangre.getUceSangreCie10List();
            List<UceSangreCie10> uceSangreCie10ListNew = uceSangre.getUceSangreCie10List();
            List<UceTransfusiones> uceTransfusionesListOld = persistentUceSangre.getUceTransfusionesList();
            List<UceTransfusiones> uceTransfusionesListNew = uceSangre.getUceTransfusionesList();
            List<UceSangreProcedimientos> uceSangreProcedimientosListOld = persistentUceSangre.getUceSangreProcedimientosList();
            List<UceSangreProcedimientos> uceSangreProcedimientosListNew = uceSangre.getUceSangreProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (UceSangreCie10 uceSangreCie10ListOldUceSangreCie10 : uceSangreCie10ListOld) {
                if (!uceSangreCie10ListNew.contains(uceSangreCie10ListOldUceSangreCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceSangreCie10 " + uceSangreCie10ListOldUceSangreCie10 + " since its idsangre field is not nullable.");
                }
            }
            for (UceTransfusiones uceTransfusionesListOldUceTransfusiones : uceTransfusionesListOld) {
                if (!uceTransfusionesListNew.contains(uceTransfusionesListOldUceTransfusiones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceTransfusiones " + uceTransfusionesListOldUceTransfusiones + " since its idsangre field is not nullable.");
                }
            }
            for (UceSangreProcedimientos uceSangreProcedimientosListOldUceSangreProcedimientos : uceSangreProcedimientosListOld) {
                if (!uceSangreProcedimientosListNew.contains(uceSangreProcedimientosListOldUceSangreProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceSangreProcedimientos " + uceSangreProcedimientosListOldUceSangreProcedimientos + " since its idsangre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceSangreCie10> attachedUceSangreCie10ListNew = new ArrayList<UceSangreCie10>();
            for (UceSangreCie10 uceSangreCie10ListNewUceSangreCie10ToAttach : uceSangreCie10ListNew) {
                uceSangreCie10ListNewUceSangreCie10ToAttach = em.getReference(uceSangreCie10ListNewUceSangreCie10ToAttach.getClass(), uceSangreCie10ListNewUceSangreCie10ToAttach.getId());
                attachedUceSangreCie10ListNew.add(uceSangreCie10ListNewUceSangreCie10ToAttach);
            }
            uceSangreCie10ListNew = attachedUceSangreCie10ListNew;
            uceSangre.setUceSangreCie10List(uceSangreCie10ListNew);
            List<UceTransfusiones> attachedUceTransfusionesListNew = new ArrayList<UceTransfusiones>();
            for (UceTransfusiones uceTransfusionesListNewUceTransfusionesToAttach : uceTransfusionesListNew) {
                uceTransfusionesListNewUceTransfusionesToAttach = em.getReference(uceTransfusionesListNewUceTransfusionesToAttach.getClass(), uceTransfusionesListNewUceTransfusionesToAttach.getId());
                attachedUceTransfusionesListNew.add(uceTransfusionesListNewUceTransfusionesToAttach);
            }
            uceTransfusionesListNew = attachedUceTransfusionesListNew;
            uceSangre.setUceTransfusionesList(uceTransfusionesListNew);
            List<UceSangreProcedimientos> attachedUceSangreProcedimientosListNew = new ArrayList<UceSangreProcedimientos>();
            for (UceSangreProcedimientos uceSangreProcedimientosListNewUceSangreProcedimientosToAttach : uceSangreProcedimientosListNew) {
                uceSangreProcedimientosListNewUceSangreProcedimientosToAttach = em.getReference(uceSangreProcedimientosListNewUceSangreProcedimientosToAttach.getClass(), uceSangreProcedimientosListNewUceSangreProcedimientosToAttach.getId());
                attachedUceSangreProcedimientosListNew.add(uceSangreProcedimientosListNewUceSangreProcedimientosToAttach);
            }
            uceSangreProcedimientosListNew = attachedUceSangreProcedimientosListNew;
            uceSangre.setUceSangreProcedimientosList(uceSangreProcedimientosListNew);
            uceSangre = em.merge(uceSangre);
            for (UceSangreCie10 uceSangreCie10ListNewUceSangreCie10 : uceSangreCie10ListNew) {
                if (!uceSangreCie10ListOld.contains(uceSangreCie10ListNewUceSangreCie10)) {
                    UceSangre oldIdsangreOfUceSangreCie10ListNewUceSangreCie10 = uceSangreCie10ListNewUceSangreCie10.getIdsangre();
                    uceSangreCie10ListNewUceSangreCie10.setIdsangre(uceSangre);
                    uceSangreCie10ListNewUceSangreCie10 = em.merge(uceSangreCie10ListNewUceSangreCie10);
                    if (oldIdsangreOfUceSangreCie10ListNewUceSangreCie10 != null && !oldIdsangreOfUceSangreCie10ListNewUceSangreCie10.equals(uceSangre)) {
                        oldIdsangreOfUceSangreCie10ListNewUceSangreCie10.getUceSangreCie10List().remove(uceSangreCie10ListNewUceSangreCie10);
                        oldIdsangreOfUceSangreCie10ListNewUceSangreCie10 = em.merge(oldIdsangreOfUceSangreCie10ListNewUceSangreCie10);
                    }
                }
            }
            for (UceTransfusiones uceTransfusionesListNewUceTransfusiones : uceTransfusionesListNew) {
                if (!uceTransfusionesListOld.contains(uceTransfusionesListNewUceTransfusiones)) {
                    UceSangre oldIdsangreOfUceTransfusionesListNewUceTransfusiones = uceTransfusionesListNewUceTransfusiones.getIdsangre();
                    uceTransfusionesListNewUceTransfusiones.setIdsangre(uceSangre);
                    uceTransfusionesListNewUceTransfusiones = em.merge(uceTransfusionesListNewUceTransfusiones);
                    if (oldIdsangreOfUceTransfusionesListNewUceTransfusiones != null && !oldIdsangreOfUceTransfusionesListNewUceTransfusiones.equals(uceSangre)) {
                        oldIdsangreOfUceTransfusionesListNewUceTransfusiones.getUceTransfusionesList().remove(uceTransfusionesListNewUceTransfusiones);
                        oldIdsangreOfUceTransfusionesListNewUceTransfusiones = em.merge(oldIdsangreOfUceTransfusionesListNewUceTransfusiones);
                    }
                }
            }
            for (UceSangreProcedimientos uceSangreProcedimientosListNewUceSangreProcedimientos : uceSangreProcedimientosListNew) {
                if (!uceSangreProcedimientosListOld.contains(uceSangreProcedimientosListNewUceSangreProcedimientos)) {
                    UceSangre oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos = uceSangreProcedimientosListNewUceSangreProcedimientos.getIdsangre();
                    uceSangreProcedimientosListNewUceSangreProcedimientos.setIdsangre(uceSangre);
                    uceSangreProcedimientosListNewUceSangreProcedimientos = em.merge(uceSangreProcedimientosListNewUceSangreProcedimientos);
                    if (oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos != null && !oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos.equals(uceSangre)) {
                        oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos.getUceSangreProcedimientosList().remove(uceSangreProcedimientosListNewUceSangreProcedimientos);
                        oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos = em.merge(oldIdsangreOfUceSangreProcedimientosListNewUceSangreProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceSangre.getId();
                if (findUceSangre(id) == null) {
                    throw new NonexistentEntityException("The uceSangre with id " + id + " no longer exists.");
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
            UceSangre uceSangre;
            try {
                uceSangre = em.getReference(UceSangre.class, id);
                uceSangre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceSangre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceSangreCie10> uceSangreCie10ListOrphanCheck = uceSangre.getUceSangreCie10List();
            for (UceSangreCie10 uceSangreCie10ListOrphanCheckUceSangreCie10 : uceSangreCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceSangre (" + uceSangre + ") cannot be destroyed since the UceSangreCie10 " + uceSangreCie10ListOrphanCheckUceSangreCie10 + " in its uceSangreCie10List field has a non-nullable idsangre field.");
            }
            List<UceTransfusiones> uceTransfusionesListOrphanCheck = uceSangre.getUceTransfusionesList();
            for (UceTransfusiones uceTransfusionesListOrphanCheckUceTransfusiones : uceTransfusionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceSangre (" + uceSangre + ") cannot be destroyed since the UceTransfusiones " + uceTransfusionesListOrphanCheckUceTransfusiones + " in its uceTransfusionesList field has a non-nullable idsangre field.");
            }
            List<UceSangreProcedimientos> uceSangreProcedimientosListOrphanCheck = uceSangre.getUceSangreProcedimientosList();
            for (UceSangreProcedimientos uceSangreProcedimientosListOrphanCheckUceSangreProcedimientos : uceSangreProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceSangre (" + uceSangre + ") cannot be destroyed since the UceSangreProcedimientos " + uceSangreProcedimientosListOrphanCheckUceSangreProcedimientos + " in its uceSangreProcedimientosList field has a non-nullable idsangre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceSangre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceSangre> findUceSangreEntities() {
        return findUceSangreEntities(true, -1, -1);
    }

    public List<UceSangre> findUceSangreEntities(int maxResults, int firstResult) {
        return findUceSangreEntities(false, maxResults, firstResult);
    }

    private List<UceSangre> findUceSangreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceSangre.class));
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

    public UceSangre findUceSangre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceSangre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceSangreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceSangre> rt = cq.from(UceSangre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
