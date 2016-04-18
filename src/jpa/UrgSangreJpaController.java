/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UrgSangre;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgSangreProcedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UrgSangreCie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgSangreJpaController implements Serializable {

    public UrgSangreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgSangre urgSangre) {
        if (urgSangre.getUrgSangreProcedimientosList() == null) {
            urgSangre.setUrgSangreProcedimientosList(new ArrayList<UrgSangreProcedimientos>());
        }
        if (urgSangre.getUrgSangreCie10List() == null) {
            urgSangre.setUrgSangreCie10List(new ArrayList<UrgSangreCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UrgSangreProcedimientos> attachedUrgSangreProcedimientosList = new ArrayList<UrgSangreProcedimientos>();
            for (UrgSangreProcedimientos urgSangreProcedimientosListUrgSangreProcedimientosToAttach : urgSangre.getUrgSangreProcedimientosList()) {
                urgSangreProcedimientosListUrgSangreProcedimientosToAttach = em.getReference(urgSangreProcedimientosListUrgSangreProcedimientosToAttach.getClass(), urgSangreProcedimientosListUrgSangreProcedimientosToAttach.getId());
                attachedUrgSangreProcedimientosList.add(urgSangreProcedimientosListUrgSangreProcedimientosToAttach);
            }
            urgSangre.setUrgSangreProcedimientosList(attachedUrgSangreProcedimientosList);
            List<UrgSangreCie10> attachedUrgSangreCie10List = new ArrayList<UrgSangreCie10>();
            for (UrgSangreCie10 urgSangreCie10ListUrgSangreCie10ToAttach : urgSangre.getUrgSangreCie10List()) {
                urgSangreCie10ListUrgSangreCie10ToAttach = em.getReference(urgSangreCie10ListUrgSangreCie10ToAttach.getClass(), urgSangreCie10ListUrgSangreCie10ToAttach.getId());
                attachedUrgSangreCie10List.add(urgSangreCie10ListUrgSangreCie10ToAttach);
            }
            urgSangre.setUrgSangreCie10List(attachedUrgSangreCie10List);
            em.persist(urgSangre);
            for (UrgSangreProcedimientos urgSangreProcedimientosListUrgSangreProcedimientos : urgSangre.getUrgSangreProcedimientosList()) {
                UrgSangre oldIdsangreOfUrgSangreProcedimientosListUrgSangreProcedimientos = urgSangreProcedimientosListUrgSangreProcedimientos.getIdsangre();
                urgSangreProcedimientosListUrgSangreProcedimientos.setIdsangre(urgSangre);
                urgSangreProcedimientosListUrgSangreProcedimientos = em.merge(urgSangreProcedimientosListUrgSangreProcedimientos);
                if (oldIdsangreOfUrgSangreProcedimientosListUrgSangreProcedimientos != null) {
                    oldIdsangreOfUrgSangreProcedimientosListUrgSangreProcedimientos.getUrgSangreProcedimientosList().remove(urgSangreProcedimientosListUrgSangreProcedimientos);
                    oldIdsangreOfUrgSangreProcedimientosListUrgSangreProcedimientos = em.merge(oldIdsangreOfUrgSangreProcedimientosListUrgSangreProcedimientos);
                }
            }
            for (UrgSangreCie10 urgSangreCie10ListUrgSangreCie10 : urgSangre.getUrgSangreCie10List()) {
                UrgSangre oldIdsangreOfUrgSangreCie10ListUrgSangreCie10 = urgSangreCie10ListUrgSangreCie10.getIdsangre();
                urgSangreCie10ListUrgSangreCie10.setIdsangre(urgSangre);
                urgSangreCie10ListUrgSangreCie10 = em.merge(urgSangreCie10ListUrgSangreCie10);
                if (oldIdsangreOfUrgSangreCie10ListUrgSangreCie10 != null) {
                    oldIdsangreOfUrgSangreCie10ListUrgSangreCie10.getUrgSangreCie10List().remove(urgSangreCie10ListUrgSangreCie10);
                    oldIdsangreOfUrgSangreCie10ListUrgSangreCie10 = em.merge(oldIdsangreOfUrgSangreCie10ListUrgSangreCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgSangre urgSangre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgSangre persistentUrgSangre = em.find(UrgSangre.class, urgSangre.getId());
            List<UrgSangreProcedimientos> urgSangreProcedimientosListOld = persistentUrgSangre.getUrgSangreProcedimientosList();
            List<UrgSangreProcedimientos> urgSangreProcedimientosListNew = urgSangre.getUrgSangreProcedimientosList();
            List<UrgSangreCie10> urgSangreCie10ListOld = persistentUrgSangre.getUrgSangreCie10List();
            List<UrgSangreCie10> urgSangreCie10ListNew = urgSangre.getUrgSangreCie10List();
            List<String> illegalOrphanMessages = null;
            for (UrgSangreProcedimientos urgSangreProcedimientosListOldUrgSangreProcedimientos : urgSangreProcedimientosListOld) {
                if (!urgSangreProcedimientosListNew.contains(urgSangreProcedimientosListOldUrgSangreProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgSangreProcedimientos " + urgSangreProcedimientosListOldUrgSangreProcedimientos + " since its idsangre field is not nullable.");
                }
            }
            for (UrgSangreCie10 urgSangreCie10ListOldUrgSangreCie10 : urgSangreCie10ListOld) {
                if (!urgSangreCie10ListNew.contains(urgSangreCie10ListOldUrgSangreCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgSangreCie10 " + urgSangreCie10ListOldUrgSangreCie10 + " since its idsangre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgSangreProcedimientos> attachedUrgSangreProcedimientosListNew = new ArrayList<UrgSangreProcedimientos>();
            for (UrgSangreProcedimientos urgSangreProcedimientosListNewUrgSangreProcedimientosToAttach : urgSangreProcedimientosListNew) {
                urgSangreProcedimientosListNewUrgSangreProcedimientosToAttach = em.getReference(urgSangreProcedimientosListNewUrgSangreProcedimientosToAttach.getClass(), urgSangreProcedimientosListNewUrgSangreProcedimientosToAttach.getId());
                attachedUrgSangreProcedimientosListNew.add(urgSangreProcedimientosListNewUrgSangreProcedimientosToAttach);
            }
            urgSangreProcedimientosListNew = attachedUrgSangreProcedimientosListNew;
            urgSangre.setUrgSangreProcedimientosList(urgSangreProcedimientosListNew);
            List<UrgSangreCie10> attachedUrgSangreCie10ListNew = new ArrayList<UrgSangreCie10>();
            for (UrgSangreCie10 urgSangreCie10ListNewUrgSangreCie10ToAttach : urgSangreCie10ListNew) {
                urgSangreCie10ListNewUrgSangreCie10ToAttach = em.getReference(urgSangreCie10ListNewUrgSangreCie10ToAttach.getClass(), urgSangreCie10ListNewUrgSangreCie10ToAttach.getId());
                attachedUrgSangreCie10ListNew.add(urgSangreCie10ListNewUrgSangreCie10ToAttach);
            }
            urgSangreCie10ListNew = attachedUrgSangreCie10ListNew;
            urgSangre.setUrgSangreCie10List(urgSangreCie10ListNew);
            urgSangre = em.merge(urgSangre);
            for (UrgSangreProcedimientos urgSangreProcedimientosListNewUrgSangreProcedimientos : urgSangreProcedimientosListNew) {
                if (!urgSangreProcedimientosListOld.contains(urgSangreProcedimientosListNewUrgSangreProcedimientos)) {
                    UrgSangre oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos = urgSangreProcedimientosListNewUrgSangreProcedimientos.getIdsangre();
                    urgSangreProcedimientosListNewUrgSangreProcedimientos.setIdsangre(urgSangre);
                    urgSangreProcedimientosListNewUrgSangreProcedimientos = em.merge(urgSangreProcedimientosListNewUrgSangreProcedimientos);
                    if (oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos != null && !oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos.equals(urgSangre)) {
                        oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos.getUrgSangreProcedimientosList().remove(urgSangreProcedimientosListNewUrgSangreProcedimientos);
                        oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos = em.merge(oldIdsangreOfUrgSangreProcedimientosListNewUrgSangreProcedimientos);
                    }
                }
            }
            for (UrgSangreCie10 urgSangreCie10ListNewUrgSangreCie10 : urgSangreCie10ListNew) {
                if (!urgSangreCie10ListOld.contains(urgSangreCie10ListNewUrgSangreCie10)) {
                    UrgSangre oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10 = urgSangreCie10ListNewUrgSangreCie10.getIdsangre();
                    urgSangreCie10ListNewUrgSangreCie10.setIdsangre(urgSangre);
                    urgSangreCie10ListNewUrgSangreCie10 = em.merge(urgSangreCie10ListNewUrgSangreCie10);
                    if (oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10 != null && !oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10.equals(urgSangre)) {
                        oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10.getUrgSangreCie10List().remove(urgSangreCie10ListNewUrgSangreCie10);
                        oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10 = em.merge(oldIdsangreOfUrgSangreCie10ListNewUrgSangreCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgSangre.getId();
                if (findUrgSangre(id) == null) {
                    throw new NonexistentEntityException("The urgSangre with id " + id + " no longer exists.");
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
            UrgSangre urgSangre;
            try {
                urgSangre = em.getReference(UrgSangre.class, id);
                urgSangre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgSangre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UrgSangreProcedimientos> urgSangreProcedimientosListOrphanCheck = urgSangre.getUrgSangreProcedimientosList();
            for (UrgSangreProcedimientos urgSangreProcedimientosListOrphanCheckUrgSangreProcedimientos : urgSangreProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgSangre (" + urgSangre + ") cannot be destroyed since the UrgSangreProcedimientos " + urgSangreProcedimientosListOrphanCheckUrgSangreProcedimientos + " in its urgSangreProcedimientosList field has a non-nullable idsangre field.");
            }
            List<UrgSangreCie10> urgSangreCie10ListOrphanCheck = urgSangre.getUrgSangreCie10List();
            for (UrgSangreCie10 urgSangreCie10ListOrphanCheckUrgSangreCie10 : urgSangreCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgSangre (" + urgSangre + ") cannot be destroyed since the UrgSangreCie10 " + urgSangreCie10ListOrphanCheckUrgSangreCie10 + " in its urgSangreCie10List field has a non-nullable idsangre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgSangre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgSangre> findUrgSangreEntities() {
        return findUrgSangreEntities(true, -1, -1);
    }

    public List<UrgSangre> findUrgSangreEntities(int maxResults, int firstResult) {
        return findUrgSangreEntities(false, maxResults, firstResult);
    }

    private List<UrgSangre> findUrgSangreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgSangre.class));
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

    public UrgSangre findUrgSangre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgSangre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgSangreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgSangre> rt = cq.from(UrgSangre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
