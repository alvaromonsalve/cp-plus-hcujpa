/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UrgIngAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgIngAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UrgIngAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngAnexo3JpaController implements Serializable {

    public UrgIngAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngAnexo3 urgIngAnexo3) {
        if (urgIngAnexo3.getUrgIngAnexo3ProcedimientosList() == null) {
            urgIngAnexo3.setUrgIngAnexo3ProcedimientosList(new ArrayList<UrgIngAnexo3Procedimientos>());
        }
        if (urgIngAnexo3.getUrgIngAnexo3Cie10List() == null) {
            urgIngAnexo3.setUrgIngAnexo3Cie10List(new ArrayList<UrgIngAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UrgIngAnexo3Procedimientos> attachedUrgIngAnexo3ProcedimientosList = new ArrayList<UrgIngAnexo3Procedimientos>();
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListUrgIngAnexo3ProcedimientosToAttach : urgIngAnexo3.getUrgIngAnexo3ProcedimientosList()) {
                urgIngAnexo3ProcedimientosListUrgIngAnexo3ProcedimientosToAttach = em.getReference(urgIngAnexo3ProcedimientosListUrgIngAnexo3ProcedimientosToAttach.getClass(), urgIngAnexo3ProcedimientosListUrgIngAnexo3ProcedimientosToAttach.getId());
                attachedUrgIngAnexo3ProcedimientosList.add(urgIngAnexo3ProcedimientosListUrgIngAnexo3ProcedimientosToAttach);
            }
            urgIngAnexo3.setUrgIngAnexo3ProcedimientosList(attachedUrgIngAnexo3ProcedimientosList);
            List<UrgIngAnexo3Cie10> attachedUrgIngAnexo3Cie10List = new ArrayList<UrgIngAnexo3Cie10>();
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListUrgIngAnexo3Cie10ToAttach : urgIngAnexo3.getUrgIngAnexo3Cie10List()) {
                urgIngAnexo3Cie10ListUrgIngAnexo3Cie10ToAttach = em.getReference(urgIngAnexo3Cie10ListUrgIngAnexo3Cie10ToAttach.getClass(), urgIngAnexo3Cie10ListUrgIngAnexo3Cie10ToAttach.getId());
                attachedUrgIngAnexo3Cie10List.add(urgIngAnexo3Cie10ListUrgIngAnexo3Cie10ToAttach);
            }
            urgIngAnexo3.setUrgIngAnexo3Cie10List(attachedUrgIngAnexo3Cie10List);
            em.persist(urgIngAnexo3);
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos : urgIngAnexo3.getUrgIngAnexo3ProcedimientosList()) {
                UrgIngAnexo3 oldIdanexoOfUrgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos = urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos.getIdanexo();
                urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos.setIdanexo(urgIngAnexo3);
                urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos = em.merge(urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos);
                if (oldIdanexoOfUrgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos != null) {
                    oldIdanexoOfUrgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos.getUrgIngAnexo3ProcedimientosList().remove(urgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos);
                    oldIdanexoOfUrgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos = em.merge(oldIdanexoOfUrgIngAnexo3ProcedimientosListUrgIngAnexo3Procedimientos);
                }
            }
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListUrgIngAnexo3Cie10 : urgIngAnexo3.getUrgIngAnexo3Cie10List()) {
                UrgIngAnexo3 oldIdanexoOfUrgIngAnexo3Cie10ListUrgIngAnexo3Cie10 = urgIngAnexo3Cie10ListUrgIngAnexo3Cie10.getIdanexo();
                urgIngAnexo3Cie10ListUrgIngAnexo3Cie10.setIdanexo(urgIngAnexo3);
                urgIngAnexo3Cie10ListUrgIngAnexo3Cie10 = em.merge(urgIngAnexo3Cie10ListUrgIngAnexo3Cie10);
                if (oldIdanexoOfUrgIngAnexo3Cie10ListUrgIngAnexo3Cie10 != null) {
                    oldIdanexoOfUrgIngAnexo3Cie10ListUrgIngAnexo3Cie10.getUrgIngAnexo3Cie10List().remove(urgIngAnexo3Cie10ListUrgIngAnexo3Cie10);
                    oldIdanexoOfUrgIngAnexo3Cie10ListUrgIngAnexo3Cie10 = em.merge(oldIdanexoOfUrgIngAnexo3Cie10ListUrgIngAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngAnexo3 urgIngAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngAnexo3 persistentUrgIngAnexo3 = em.find(UrgIngAnexo3.class, urgIngAnexo3.getId());
            List<UrgIngAnexo3Procedimientos> urgIngAnexo3ProcedimientosListOld = persistentUrgIngAnexo3.getUrgIngAnexo3ProcedimientosList();
            List<UrgIngAnexo3Procedimientos> urgIngAnexo3ProcedimientosListNew = urgIngAnexo3.getUrgIngAnexo3ProcedimientosList();
            List<UrgIngAnexo3Cie10> urgIngAnexo3Cie10ListOld = persistentUrgIngAnexo3.getUrgIngAnexo3Cie10List();
            List<UrgIngAnexo3Cie10> urgIngAnexo3Cie10ListNew = urgIngAnexo3.getUrgIngAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListOldUrgIngAnexo3Procedimientos : urgIngAnexo3ProcedimientosListOld) {
                if (!urgIngAnexo3ProcedimientosListNew.contains(urgIngAnexo3ProcedimientosListOldUrgIngAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgIngAnexo3Procedimientos " + urgIngAnexo3ProcedimientosListOldUrgIngAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListOldUrgIngAnexo3Cie10 : urgIngAnexo3Cie10ListOld) {
                if (!urgIngAnexo3Cie10ListNew.contains(urgIngAnexo3Cie10ListOldUrgIngAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgIngAnexo3Cie10 " + urgIngAnexo3Cie10ListOldUrgIngAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgIngAnexo3Procedimientos> attachedUrgIngAnexo3ProcedimientosListNew = new ArrayList<UrgIngAnexo3Procedimientos>();
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListNewUrgIngAnexo3ProcedimientosToAttach : urgIngAnexo3ProcedimientosListNew) {
                urgIngAnexo3ProcedimientosListNewUrgIngAnexo3ProcedimientosToAttach = em.getReference(urgIngAnexo3ProcedimientosListNewUrgIngAnexo3ProcedimientosToAttach.getClass(), urgIngAnexo3ProcedimientosListNewUrgIngAnexo3ProcedimientosToAttach.getId());
                attachedUrgIngAnexo3ProcedimientosListNew.add(urgIngAnexo3ProcedimientosListNewUrgIngAnexo3ProcedimientosToAttach);
            }
            urgIngAnexo3ProcedimientosListNew = attachedUrgIngAnexo3ProcedimientosListNew;
            urgIngAnexo3.setUrgIngAnexo3ProcedimientosList(urgIngAnexo3ProcedimientosListNew);
            List<UrgIngAnexo3Cie10> attachedUrgIngAnexo3Cie10ListNew = new ArrayList<UrgIngAnexo3Cie10>();
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10ToAttach : urgIngAnexo3Cie10ListNew) {
                urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10ToAttach = em.getReference(urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10ToAttach.getClass(), urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10ToAttach.getId());
                attachedUrgIngAnexo3Cie10ListNew.add(urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10ToAttach);
            }
            urgIngAnexo3Cie10ListNew = attachedUrgIngAnexo3Cie10ListNew;
            urgIngAnexo3.setUrgIngAnexo3Cie10List(urgIngAnexo3Cie10ListNew);
            urgIngAnexo3 = em.merge(urgIngAnexo3);
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos : urgIngAnexo3ProcedimientosListNew) {
                if (!urgIngAnexo3ProcedimientosListOld.contains(urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos)) {
                    UrgIngAnexo3 oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos = urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos.getIdanexo();
                    urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos.setIdanexo(urgIngAnexo3);
                    urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos = em.merge(urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos);
                    if (oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos != null && !oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos.equals(urgIngAnexo3)) {
                        oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos.getUrgIngAnexo3ProcedimientosList().remove(urgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos);
                        oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos = em.merge(oldIdanexoOfUrgIngAnexo3ProcedimientosListNewUrgIngAnexo3Procedimientos);
                    }
                }
            }
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10 : urgIngAnexo3Cie10ListNew) {
                if (!urgIngAnexo3Cie10ListOld.contains(urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10)) {
                    UrgIngAnexo3 oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10 = urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10.getIdanexo();
                    urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10.setIdanexo(urgIngAnexo3);
                    urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10 = em.merge(urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10);
                    if (oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10 != null && !oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10.equals(urgIngAnexo3)) {
                        oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10.getUrgIngAnexo3Cie10List().remove(urgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10);
                        oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10 = em.merge(oldIdanexoOfUrgIngAnexo3Cie10ListNewUrgIngAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngAnexo3.getId();
                if (findUrgIngAnexo3(id) == null) {
                    throw new NonexistentEntityException("The urgIngAnexo3 with id " + id + " no longer exists.");
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
            UrgIngAnexo3 urgIngAnexo3;
            try {
                urgIngAnexo3 = em.getReference(UrgIngAnexo3.class, id);
                urgIngAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UrgIngAnexo3Procedimientos> urgIngAnexo3ProcedimientosListOrphanCheck = urgIngAnexo3.getUrgIngAnexo3ProcedimientosList();
            for (UrgIngAnexo3Procedimientos urgIngAnexo3ProcedimientosListOrphanCheckUrgIngAnexo3Procedimientos : urgIngAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgIngAnexo3 (" + urgIngAnexo3 + ") cannot be destroyed since the UrgIngAnexo3Procedimientos " + urgIngAnexo3ProcedimientosListOrphanCheckUrgIngAnexo3Procedimientos + " in its urgIngAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<UrgIngAnexo3Cie10> urgIngAnexo3Cie10ListOrphanCheck = urgIngAnexo3.getUrgIngAnexo3Cie10List();
            for (UrgIngAnexo3Cie10 urgIngAnexo3Cie10ListOrphanCheckUrgIngAnexo3Cie10 : urgIngAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgIngAnexo3 (" + urgIngAnexo3 + ") cannot be destroyed since the UrgIngAnexo3Cie10 " + urgIngAnexo3Cie10ListOrphanCheckUrgIngAnexo3Cie10 + " in its urgIngAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgIngAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngAnexo3> findUrgIngAnexo3Entities() {
        return findUrgIngAnexo3Entities(true, -1, -1);
    }

    public List<UrgIngAnexo3> findUrgIngAnexo3Entities(int maxResults, int firstResult) {
        return findUrgIngAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UrgIngAnexo3> findUrgIngAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngAnexo3.class));
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

    public UrgIngAnexo3 findUrgIngAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngAnexo3> rt = cq.from(UrgIngAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
