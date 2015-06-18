/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UrgAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UrgAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgAnexo3JpaController implements Serializable {

    public UrgAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgAnexo3 urgAnexo3) {
        if (urgAnexo3.getUrgAnexo3ProcedimientosList() == null) {
            urgAnexo3.setUrgAnexo3ProcedimientosList(new ArrayList<UrgAnexo3Procedimientos>());
        }
        if (urgAnexo3.getUrgAnexo3Cie10List() == null) {
            urgAnexo3.setUrgAnexo3Cie10List(new ArrayList<UrgAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UrgAnexo3Procedimientos> attachedUrgAnexo3ProcedimientosList = new ArrayList<UrgAnexo3Procedimientos>();
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListUrgAnexo3ProcedimientosToAttach : urgAnexo3.getUrgAnexo3ProcedimientosList()) {
                urgAnexo3ProcedimientosListUrgAnexo3ProcedimientosToAttach = em.getReference(urgAnexo3ProcedimientosListUrgAnexo3ProcedimientosToAttach.getClass(), urgAnexo3ProcedimientosListUrgAnexo3ProcedimientosToAttach.getId());
                attachedUrgAnexo3ProcedimientosList.add(urgAnexo3ProcedimientosListUrgAnexo3ProcedimientosToAttach);
            }
            urgAnexo3.setUrgAnexo3ProcedimientosList(attachedUrgAnexo3ProcedimientosList);
            List<UrgAnexo3Cie10> attachedUrgAnexo3Cie10List = new ArrayList<UrgAnexo3Cie10>();
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListUrgAnexo3Cie10ToAttach : urgAnexo3.getUrgAnexo3Cie10List()) {
                urgAnexo3Cie10ListUrgAnexo3Cie10ToAttach = em.getReference(urgAnexo3Cie10ListUrgAnexo3Cie10ToAttach.getClass(), urgAnexo3Cie10ListUrgAnexo3Cie10ToAttach.getId());
                attachedUrgAnexo3Cie10List.add(urgAnexo3Cie10ListUrgAnexo3Cie10ToAttach);
            }
            urgAnexo3.setUrgAnexo3Cie10List(attachedUrgAnexo3Cie10List);
            em.persist(urgAnexo3);
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListUrgAnexo3Procedimientos : urgAnexo3.getUrgAnexo3ProcedimientosList()) {
                UrgAnexo3 oldIdanexoOfUrgAnexo3ProcedimientosListUrgAnexo3Procedimientos = urgAnexo3ProcedimientosListUrgAnexo3Procedimientos.getIdanexo();
                urgAnexo3ProcedimientosListUrgAnexo3Procedimientos.setIdanexo(urgAnexo3);
                urgAnexo3ProcedimientosListUrgAnexo3Procedimientos = em.merge(urgAnexo3ProcedimientosListUrgAnexo3Procedimientos);
                if (oldIdanexoOfUrgAnexo3ProcedimientosListUrgAnexo3Procedimientos != null) {
                    oldIdanexoOfUrgAnexo3ProcedimientosListUrgAnexo3Procedimientos.getUrgAnexo3ProcedimientosList().remove(urgAnexo3ProcedimientosListUrgAnexo3Procedimientos);
                    oldIdanexoOfUrgAnexo3ProcedimientosListUrgAnexo3Procedimientos = em.merge(oldIdanexoOfUrgAnexo3ProcedimientosListUrgAnexo3Procedimientos);
                }
            }
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListUrgAnexo3Cie10 : urgAnexo3.getUrgAnexo3Cie10List()) {
                UrgAnexo3 oldIdanexoOfUrgAnexo3Cie10ListUrgAnexo3Cie10 = urgAnexo3Cie10ListUrgAnexo3Cie10.getIdanexo();
                urgAnexo3Cie10ListUrgAnexo3Cie10.setIdanexo(urgAnexo3);
                urgAnexo3Cie10ListUrgAnexo3Cie10 = em.merge(urgAnexo3Cie10ListUrgAnexo3Cie10);
                if (oldIdanexoOfUrgAnexo3Cie10ListUrgAnexo3Cie10 != null) {
                    oldIdanexoOfUrgAnexo3Cie10ListUrgAnexo3Cie10.getUrgAnexo3Cie10List().remove(urgAnexo3Cie10ListUrgAnexo3Cie10);
                    oldIdanexoOfUrgAnexo3Cie10ListUrgAnexo3Cie10 = em.merge(oldIdanexoOfUrgAnexo3Cie10ListUrgAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgAnexo3 urgAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgAnexo3 persistentUrgAnexo3 = em.find(UrgAnexo3.class, urgAnexo3.getId());
            List<UrgAnexo3Procedimientos> urgAnexo3ProcedimientosListOld = persistentUrgAnexo3.getUrgAnexo3ProcedimientosList();
            List<UrgAnexo3Procedimientos> urgAnexo3ProcedimientosListNew = urgAnexo3.getUrgAnexo3ProcedimientosList();
            List<UrgAnexo3Cie10> urgAnexo3Cie10ListOld = persistentUrgAnexo3.getUrgAnexo3Cie10List();
            List<UrgAnexo3Cie10> urgAnexo3Cie10ListNew = urgAnexo3.getUrgAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListOldUrgAnexo3Procedimientos : urgAnexo3ProcedimientosListOld) {
                if (!urgAnexo3ProcedimientosListNew.contains(urgAnexo3ProcedimientosListOldUrgAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgAnexo3Procedimientos " + urgAnexo3ProcedimientosListOldUrgAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListOldUrgAnexo3Cie10 : urgAnexo3Cie10ListOld) {
                if (!urgAnexo3Cie10ListNew.contains(urgAnexo3Cie10ListOldUrgAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgAnexo3Cie10 " + urgAnexo3Cie10ListOldUrgAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgAnexo3Procedimientos> attachedUrgAnexo3ProcedimientosListNew = new ArrayList<UrgAnexo3Procedimientos>();
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListNewUrgAnexo3ProcedimientosToAttach : urgAnexo3ProcedimientosListNew) {
                urgAnexo3ProcedimientosListNewUrgAnexo3ProcedimientosToAttach = em.getReference(urgAnexo3ProcedimientosListNewUrgAnexo3ProcedimientosToAttach.getClass(), urgAnexo3ProcedimientosListNewUrgAnexo3ProcedimientosToAttach.getId());
                attachedUrgAnexo3ProcedimientosListNew.add(urgAnexo3ProcedimientosListNewUrgAnexo3ProcedimientosToAttach);
            }
            urgAnexo3ProcedimientosListNew = attachedUrgAnexo3ProcedimientosListNew;
            urgAnexo3.setUrgAnexo3ProcedimientosList(urgAnexo3ProcedimientosListNew);
            List<UrgAnexo3Cie10> attachedUrgAnexo3Cie10ListNew = new ArrayList<UrgAnexo3Cie10>();
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListNewUrgAnexo3Cie10ToAttach : urgAnexo3Cie10ListNew) {
                urgAnexo3Cie10ListNewUrgAnexo3Cie10ToAttach = em.getReference(urgAnexo3Cie10ListNewUrgAnexo3Cie10ToAttach.getClass(), urgAnexo3Cie10ListNewUrgAnexo3Cie10ToAttach.getId());
                attachedUrgAnexo3Cie10ListNew.add(urgAnexo3Cie10ListNewUrgAnexo3Cie10ToAttach);
            }
            urgAnexo3Cie10ListNew = attachedUrgAnexo3Cie10ListNew;
            urgAnexo3.setUrgAnexo3Cie10List(urgAnexo3Cie10ListNew);
            urgAnexo3 = em.merge(urgAnexo3);
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos : urgAnexo3ProcedimientosListNew) {
                if (!urgAnexo3ProcedimientosListOld.contains(urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos)) {
                    UrgAnexo3 oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos = urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos.getIdanexo();
                    urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos.setIdanexo(urgAnexo3);
                    urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos = em.merge(urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos);
                    if (oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos != null && !oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos.equals(urgAnexo3)) {
                        oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos.getUrgAnexo3ProcedimientosList().remove(urgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos);
                        oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos = em.merge(oldIdanexoOfUrgAnexo3ProcedimientosListNewUrgAnexo3Procedimientos);
                    }
                }
            }
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListNewUrgAnexo3Cie10 : urgAnexo3Cie10ListNew) {
                if (!urgAnexo3Cie10ListOld.contains(urgAnexo3Cie10ListNewUrgAnexo3Cie10)) {
                    UrgAnexo3 oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10 = urgAnexo3Cie10ListNewUrgAnexo3Cie10.getIdanexo();
                    urgAnexo3Cie10ListNewUrgAnexo3Cie10.setIdanexo(urgAnexo3);
                    urgAnexo3Cie10ListNewUrgAnexo3Cie10 = em.merge(urgAnexo3Cie10ListNewUrgAnexo3Cie10);
                    if (oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10 != null && !oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10.equals(urgAnexo3)) {
                        oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10.getUrgAnexo3Cie10List().remove(urgAnexo3Cie10ListNewUrgAnexo3Cie10);
                        oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10 = em.merge(oldIdanexoOfUrgAnexo3Cie10ListNewUrgAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgAnexo3.getId();
                if (findUrgAnexo3(id) == null) {
                    throw new NonexistentEntityException("The urgAnexo3 with id " + id + " no longer exists.");
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
            UrgAnexo3 urgAnexo3;
            try {
                urgAnexo3 = em.getReference(UrgAnexo3.class, id);
                urgAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UrgAnexo3Procedimientos> urgAnexo3ProcedimientosListOrphanCheck = urgAnexo3.getUrgAnexo3ProcedimientosList();
            for (UrgAnexo3Procedimientos urgAnexo3ProcedimientosListOrphanCheckUrgAnexo3Procedimientos : urgAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgAnexo3 (" + urgAnexo3 + ") cannot be destroyed since the UrgAnexo3Procedimientos " + urgAnexo3ProcedimientosListOrphanCheckUrgAnexo3Procedimientos + " in its urgAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<UrgAnexo3Cie10> urgAnexo3Cie10ListOrphanCheck = urgAnexo3.getUrgAnexo3Cie10List();
            for (UrgAnexo3Cie10 urgAnexo3Cie10ListOrphanCheckUrgAnexo3Cie10 : urgAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgAnexo3 (" + urgAnexo3 + ") cannot be destroyed since the UrgAnexo3Cie10 " + urgAnexo3Cie10ListOrphanCheckUrgAnexo3Cie10 + " in its urgAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgAnexo3> findUrgAnexo3Entities() {
        return findUrgAnexo3Entities(true, -1, -1);
    }

    public List<UrgAnexo3> findUrgAnexo3Entities(int maxResults, int firstResult) {
        return findUrgAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UrgAnexo3> findUrgAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgAnexo3.class));
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

    public UrgAnexo3 findUrgAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgAnexo3> rt = cq.from(UrgAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
