/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.HospSangre;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospSangreProcedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospTransfusiones;
import entidades_EJB.HospSangreCie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospSangreJpaController implements Serializable {

    public HospSangreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospSangre hospSangre) {
        if (hospSangre.getHospSangreProcedimientosList() == null) {
            hospSangre.setHospSangreProcedimientosList(new ArrayList<HospSangreProcedimientos>());
        }
        if (hospSangre.getHospTransfusionesList() == null) {
            hospSangre.setHospTransfusionesList(new ArrayList<HospTransfusiones>());
        }
        if (hospSangre.getHospSangreCie10List() == null) {
            hospSangre.setHospSangreCie10List(new ArrayList<HospSangreCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospSangreProcedimientos> attachedHospSangreProcedimientosList = new ArrayList<HospSangreProcedimientos>();
            for (HospSangreProcedimientos hospSangreProcedimientosListHospSangreProcedimientosToAttach : hospSangre.getHospSangreProcedimientosList()) {
                hospSangreProcedimientosListHospSangreProcedimientosToAttach = em.getReference(hospSangreProcedimientosListHospSangreProcedimientosToAttach.getClass(), hospSangreProcedimientosListHospSangreProcedimientosToAttach.getId());
                attachedHospSangreProcedimientosList.add(hospSangreProcedimientosListHospSangreProcedimientosToAttach);
            }
            hospSangre.setHospSangreProcedimientosList(attachedHospSangreProcedimientosList);
            List<HospTransfusiones> attachedHospTransfusionesList = new ArrayList<HospTransfusiones>();
            for (HospTransfusiones hospTransfusionesListHospTransfusionesToAttach : hospSangre.getHospTransfusionesList()) {
                hospTransfusionesListHospTransfusionesToAttach = em.getReference(hospTransfusionesListHospTransfusionesToAttach.getClass(), hospTransfusionesListHospTransfusionesToAttach.getId());
                attachedHospTransfusionesList.add(hospTransfusionesListHospTransfusionesToAttach);
            }
            hospSangre.setHospTransfusionesList(attachedHospTransfusionesList);
            List<HospSangreCie10> attachedHospSangreCie10List = new ArrayList<HospSangreCie10>();
            for (HospSangreCie10 hospSangreCie10ListHospSangreCie10ToAttach : hospSangre.getHospSangreCie10List()) {
                hospSangreCie10ListHospSangreCie10ToAttach = em.getReference(hospSangreCie10ListHospSangreCie10ToAttach.getClass(), hospSangreCie10ListHospSangreCie10ToAttach.getId());
                attachedHospSangreCie10List.add(hospSangreCie10ListHospSangreCie10ToAttach);
            }
            hospSangre.setHospSangreCie10List(attachedHospSangreCie10List);
            em.persist(hospSangre);
            for (HospSangreProcedimientos hospSangreProcedimientosListHospSangreProcedimientos : hospSangre.getHospSangreProcedimientosList()) {
                HospSangre oldIdsangreOfHospSangreProcedimientosListHospSangreProcedimientos = hospSangreProcedimientosListHospSangreProcedimientos.getIdsangre();
                hospSangreProcedimientosListHospSangreProcedimientos.setIdsangre(hospSangre);
                hospSangreProcedimientosListHospSangreProcedimientos = em.merge(hospSangreProcedimientosListHospSangreProcedimientos);
                if (oldIdsangreOfHospSangreProcedimientosListHospSangreProcedimientos != null) {
                    oldIdsangreOfHospSangreProcedimientosListHospSangreProcedimientos.getHospSangreProcedimientosList().remove(hospSangreProcedimientosListHospSangreProcedimientos);
                    oldIdsangreOfHospSangreProcedimientosListHospSangreProcedimientos = em.merge(oldIdsangreOfHospSangreProcedimientosListHospSangreProcedimientos);
                }
            }
            for (HospTransfusiones hospTransfusionesListHospTransfusiones : hospSangre.getHospTransfusionesList()) {
                HospSangre oldIdsangreOfHospTransfusionesListHospTransfusiones = hospTransfusionesListHospTransfusiones.getIdsangre();
                hospTransfusionesListHospTransfusiones.setIdsangre(hospSangre);
                hospTransfusionesListHospTransfusiones = em.merge(hospTransfusionesListHospTransfusiones);
                if (oldIdsangreOfHospTransfusionesListHospTransfusiones != null) {
                    oldIdsangreOfHospTransfusionesListHospTransfusiones.getHospTransfusionesList().remove(hospTransfusionesListHospTransfusiones);
                    oldIdsangreOfHospTransfusionesListHospTransfusiones = em.merge(oldIdsangreOfHospTransfusionesListHospTransfusiones);
                }
            }
            for (HospSangreCie10 hospSangreCie10ListHospSangreCie10 : hospSangre.getHospSangreCie10List()) {
                HospSangre oldIdsangreOfHospSangreCie10ListHospSangreCie10 = hospSangreCie10ListHospSangreCie10.getIdsangre();
                hospSangreCie10ListHospSangreCie10.setIdsangre(hospSangre);
                hospSangreCie10ListHospSangreCie10 = em.merge(hospSangreCie10ListHospSangreCie10);
                if (oldIdsangreOfHospSangreCie10ListHospSangreCie10 != null) {
                    oldIdsangreOfHospSangreCie10ListHospSangreCie10.getHospSangreCie10List().remove(hospSangreCie10ListHospSangreCie10);
                    oldIdsangreOfHospSangreCie10ListHospSangreCie10 = em.merge(oldIdsangreOfHospSangreCie10ListHospSangreCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospSangre hospSangre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangre persistentHospSangre = em.find(HospSangre.class, hospSangre.getId());
            List<HospSangreProcedimientos> hospSangreProcedimientosListOld = persistentHospSangre.getHospSangreProcedimientosList();
            List<HospSangreProcedimientos> hospSangreProcedimientosListNew = hospSangre.getHospSangreProcedimientosList();
            List<HospTransfusiones> hospTransfusionesListOld = persistentHospSangre.getHospTransfusionesList();
            List<HospTransfusiones> hospTransfusionesListNew = hospSangre.getHospTransfusionesList();
            List<HospSangreCie10> hospSangreCie10ListOld = persistentHospSangre.getHospSangreCie10List();
            List<HospSangreCie10> hospSangreCie10ListNew = hospSangre.getHospSangreCie10List();
            List<String> illegalOrphanMessages = null;
            for (HospSangreProcedimientos hospSangreProcedimientosListOldHospSangreProcedimientos : hospSangreProcedimientosListOld) {
                if (!hospSangreProcedimientosListNew.contains(hospSangreProcedimientosListOldHospSangreProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospSangreProcedimientos " + hospSangreProcedimientosListOldHospSangreProcedimientos + " since its idsangre field is not nullable.");
                }
            }
            for (HospTransfusiones hospTransfusionesListOldHospTransfusiones : hospTransfusionesListOld) {
                if (!hospTransfusionesListNew.contains(hospTransfusionesListOldHospTransfusiones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospTransfusiones " + hospTransfusionesListOldHospTransfusiones + " since its idsangre field is not nullable.");
                }
            }
            for (HospSangreCie10 hospSangreCie10ListOldHospSangreCie10 : hospSangreCie10ListOld) {
                if (!hospSangreCie10ListNew.contains(hospSangreCie10ListOldHospSangreCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospSangreCie10 " + hospSangreCie10ListOldHospSangreCie10 + " since its idsangre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospSangreProcedimientos> attachedHospSangreProcedimientosListNew = new ArrayList<HospSangreProcedimientos>();
            for (HospSangreProcedimientos hospSangreProcedimientosListNewHospSangreProcedimientosToAttach : hospSangreProcedimientosListNew) {
                hospSangreProcedimientosListNewHospSangreProcedimientosToAttach = em.getReference(hospSangreProcedimientosListNewHospSangreProcedimientosToAttach.getClass(), hospSangreProcedimientosListNewHospSangreProcedimientosToAttach.getId());
                attachedHospSangreProcedimientosListNew.add(hospSangreProcedimientosListNewHospSangreProcedimientosToAttach);
            }
            hospSangreProcedimientosListNew = attachedHospSangreProcedimientosListNew;
            hospSangre.setHospSangreProcedimientosList(hospSangreProcedimientosListNew);
            List<HospTransfusiones> attachedHospTransfusionesListNew = new ArrayList<HospTransfusiones>();
            for (HospTransfusiones hospTransfusionesListNewHospTransfusionesToAttach : hospTransfusionesListNew) {
                hospTransfusionesListNewHospTransfusionesToAttach = em.getReference(hospTransfusionesListNewHospTransfusionesToAttach.getClass(), hospTransfusionesListNewHospTransfusionesToAttach.getId());
                attachedHospTransfusionesListNew.add(hospTransfusionesListNewHospTransfusionesToAttach);
            }
            hospTransfusionesListNew = attachedHospTransfusionesListNew;
            hospSangre.setHospTransfusionesList(hospTransfusionesListNew);
            List<HospSangreCie10> attachedHospSangreCie10ListNew = new ArrayList<HospSangreCie10>();
            for (HospSangreCie10 hospSangreCie10ListNewHospSangreCie10ToAttach : hospSangreCie10ListNew) {
                hospSangreCie10ListNewHospSangreCie10ToAttach = em.getReference(hospSangreCie10ListNewHospSangreCie10ToAttach.getClass(), hospSangreCie10ListNewHospSangreCie10ToAttach.getId());
                attachedHospSangreCie10ListNew.add(hospSangreCie10ListNewHospSangreCie10ToAttach);
            }
            hospSangreCie10ListNew = attachedHospSangreCie10ListNew;
            hospSangre.setHospSangreCie10List(hospSangreCie10ListNew);
            hospSangre = em.merge(hospSangre);
            for (HospSangreProcedimientos hospSangreProcedimientosListNewHospSangreProcedimientos : hospSangreProcedimientosListNew) {
                if (!hospSangreProcedimientosListOld.contains(hospSangreProcedimientosListNewHospSangreProcedimientos)) {
                    HospSangre oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos = hospSangreProcedimientosListNewHospSangreProcedimientos.getIdsangre();
                    hospSangreProcedimientosListNewHospSangreProcedimientos.setIdsangre(hospSangre);
                    hospSangreProcedimientosListNewHospSangreProcedimientos = em.merge(hospSangreProcedimientosListNewHospSangreProcedimientos);
                    if (oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos != null && !oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos.equals(hospSangre)) {
                        oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos.getHospSangreProcedimientosList().remove(hospSangreProcedimientosListNewHospSangreProcedimientos);
                        oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos = em.merge(oldIdsangreOfHospSangreProcedimientosListNewHospSangreProcedimientos);
                    }
                }
            }
            for (HospTransfusiones hospTransfusionesListNewHospTransfusiones : hospTransfusionesListNew) {
                if (!hospTransfusionesListOld.contains(hospTransfusionesListNewHospTransfusiones)) {
                    HospSangre oldIdsangreOfHospTransfusionesListNewHospTransfusiones = hospTransfusionesListNewHospTransfusiones.getIdsangre();
                    hospTransfusionesListNewHospTransfusiones.setIdsangre(hospSangre);
                    hospTransfusionesListNewHospTransfusiones = em.merge(hospTransfusionesListNewHospTransfusiones);
                    if (oldIdsangreOfHospTransfusionesListNewHospTransfusiones != null && !oldIdsangreOfHospTransfusionesListNewHospTransfusiones.equals(hospSangre)) {
                        oldIdsangreOfHospTransfusionesListNewHospTransfusiones.getHospTransfusionesList().remove(hospTransfusionesListNewHospTransfusiones);
                        oldIdsangreOfHospTransfusionesListNewHospTransfusiones = em.merge(oldIdsangreOfHospTransfusionesListNewHospTransfusiones);
                    }
                }
            }
            for (HospSangreCie10 hospSangreCie10ListNewHospSangreCie10 : hospSangreCie10ListNew) {
                if (!hospSangreCie10ListOld.contains(hospSangreCie10ListNewHospSangreCie10)) {
                    HospSangre oldIdsangreOfHospSangreCie10ListNewHospSangreCie10 = hospSangreCie10ListNewHospSangreCie10.getIdsangre();
                    hospSangreCie10ListNewHospSangreCie10.setIdsangre(hospSangre);
                    hospSangreCie10ListNewHospSangreCie10 = em.merge(hospSangreCie10ListNewHospSangreCie10);
                    if (oldIdsangreOfHospSangreCie10ListNewHospSangreCie10 != null && !oldIdsangreOfHospSangreCie10ListNewHospSangreCie10.equals(hospSangre)) {
                        oldIdsangreOfHospSangreCie10ListNewHospSangreCie10.getHospSangreCie10List().remove(hospSangreCie10ListNewHospSangreCie10);
                        oldIdsangreOfHospSangreCie10ListNewHospSangreCie10 = em.merge(oldIdsangreOfHospSangreCie10ListNewHospSangreCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospSangre.getId();
                if (findHospSangre(id) == null) {
                    throw new NonexistentEntityException("The hospSangre with id " + id + " no longer exists.");
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
            HospSangre hospSangre;
            try {
                hospSangre = em.getReference(HospSangre.class, id);
                hospSangre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospSangre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospSangreProcedimientos> hospSangreProcedimientosListOrphanCheck = hospSangre.getHospSangreProcedimientosList();
            for (HospSangreProcedimientos hospSangreProcedimientosListOrphanCheckHospSangreProcedimientos : hospSangreProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospSangre (" + hospSangre + ") cannot be destroyed since the HospSangreProcedimientos " + hospSangreProcedimientosListOrphanCheckHospSangreProcedimientos + " in its hospSangreProcedimientosList field has a non-nullable idsangre field.");
            }
            List<HospTransfusiones> hospTransfusionesListOrphanCheck = hospSangre.getHospTransfusionesList();
            for (HospTransfusiones hospTransfusionesListOrphanCheckHospTransfusiones : hospTransfusionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospSangre (" + hospSangre + ") cannot be destroyed since the HospTransfusiones " + hospTransfusionesListOrphanCheckHospTransfusiones + " in its hospTransfusionesList field has a non-nullable idsangre field.");
            }
            List<HospSangreCie10> hospSangreCie10ListOrphanCheck = hospSangre.getHospSangreCie10List();
            for (HospSangreCie10 hospSangreCie10ListOrphanCheckHospSangreCie10 : hospSangreCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospSangre (" + hospSangre + ") cannot be destroyed since the HospSangreCie10 " + hospSangreCie10ListOrphanCheckHospSangreCie10 + " in its hospSangreCie10List field has a non-nullable idsangre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospSangre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospSangre> findHospSangreEntities() {
        return findHospSangreEntities(true, -1, -1);
    }

    public List<HospSangre> findHospSangreEntities(int maxResults, int firstResult) {
        return findHospSangreEntities(false, maxResults, firstResult);
    }

    private List<HospSangre> findHospSangreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospSangre.class));
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

    public HospSangre findHospSangre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospSangre.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospSangreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospSangre> rt = cq.from(HospSangre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
