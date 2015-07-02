/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceIngAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceIngAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceIngAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngAnexo3JpaController implements Serializable {

    public UceIngAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngAnexo3 uceIngAnexo3) {
        if (uceIngAnexo3.getUceIngAnexo3ProcedimientosList() == null) {
            uceIngAnexo3.setUceIngAnexo3ProcedimientosList(new ArrayList<UceIngAnexo3Procedimientos>());
        }
        if (uceIngAnexo3.getUceIngAnexo3Cie10List() == null) {
            uceIngAnexo3.setUceIngAnexo3Cie10List(new ArrayList<UceIngAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceIngAnexo3Procedimientos> attachedUceIngAnexo3ProcedimientosList = new ArrayList<UceIngAnexo3Procedimientos>();
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListUceIngAnexo3ProcedimientosToAttach : uceIngAnexo3.getUceIngAnexo3ProcedimientosList()) {
                uceIngAnexo3ProcedimientosListUceIngAnexo3ProcedimientosToAttach = em.getReference(uceIngAnexo3ProcedimientosListUceIngAnexo3ProcedimientosToAttach.getClass(), uceIngAnexo3ProcedimientosListUceIngAnexo3ProcedimientosToAttach.getId());
                attachedUceIngAnexo3ProcedimientosList.add(uceIngAnexo3ProcedimientosListUceIngAnexo3ProcedimientosToAttach);
            }
            uceIngAnexo3.setUceIngAnexo3ProcedimientosList(attachedUceIngAnexo3ProcedimientosList);
            List<UceIngAnexo3Cie10> attachedUceIngAnexo3Cie10List = new ArrayList<UceIngAnexo3Cie10>();
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListUceIngAnexo3Cie10ToAttach : uceIngAnexo3.getUceIngAnexo3Cie10List()) {
                uceIngAnexo3Cie10ListUceIngAnexo3Cie10ToAttach = em.getReference(uceIngAnexo3Cie10ListUceIngAnexo3Cie10ToAttach.getClass(), uceIngAnexo3Cie10ListUceIngAnexo3Cie10ToAttach.getId());
                attachedUceIngAnexo3Cie10List.add(uceIngAnexo3Cie10ListUceIngAnexo3Cie10ToAttach);
            }
            uceIngAnexo3.setUceIngAnexo3Cie10List(attachedUceIngAnexo3Cie10List);
            em.persist(uceIngAnexo3);
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos : uceIngAnexo3.getUceIngAnexo3ProcedimientosList()) {
                UceIngAnexo3 oldIdanexoOfUceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos = uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos.getIdanexo();
                uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos.setIdanexo(uceIngAnexo3);
                uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos = em.merge(uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos);
                if (oldIdanexoOfUceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos != null) {
                    oldIdanexoOfUceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos.getUceIngAnexo3ProcedimientosList().remove(uceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos);
                    oldIdanexoOfUceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos = em.merge(oldIdanexoOfUceIngAnexo3ProcedimientosListUceIngAnexo3Procedimientos);
                }
            }
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListUceIngAnexo3Cie10 : uceIngAnexo3.getUceIngAnexo3Cie10List()) {
                UceIngAnexo3 oldIdanexoOfUceIngAnexo3Cie10ListUceIngAnexo3Cie10 = uceIngAnexo3Cie10ListUceIngAnexo3Cie10.getIdanexo();
                uceIngAnexo3Cie10ListUceIngAnexo3Cie10.setIdanexo(uceIngAnexo3);
                uceIngAnexo3Cie10ListUceIngAnexo3Cie10 = em.merge(uceIngAnexo3Cie10ListUceIngAnexo3Cie10);
                if (oldIdanexoOfUceIngAnexo3Cie10ListUceIngAnexo3Cie10 != null) {
                    oldIdanexoOfUceIngAnexo3Cie10ListUceIngAnexo3Cie10.getUceIngAnexo3Cie10List().remove(uceIngAnexo3Cie10ListUceIngAnexo3Cie10);
                    oldIdanexoOfUceIngAnexo3Cie10ListUceIngAnexo3Cie10 = em.merge(oldIdanexoOfUceIngAnexo3Cie10ListUceIngAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngAnexo3 uceIngAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngAnexo3 persistentUceIngAnexo3 = em.find(UceIngAnexo3.class, uceIngAnexo3.getId());
            List<UceIngAnexo3Procedimientos> uceIngAnexo3ProcedimientosListOld = persistentUceIngAnexo3.getUceIngAnexo3ProcedimientosList();
            List<UceIngAnexo3Procedimientos> uceIngAnexo3ProcedimientosListNew = uceIngAnexo3.getUceIngAnexo3ProcedimientosList();
            List<UceIngAnexo3Cie10> uceIngAnexo3Cie10ListOld = persistentUceIngAnexo3.getUceIngAnexo3Cie10List();
            List<UceIngAnexo3Cie10> uceIngAnexo3Cie10ListNew = uceIngAnexo3.getUceIngAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListOldUceIngAnexo3Procedimientos : uceIngAnexo3ProcedimientosListOld) {
                if (!uceIngAnexo3ProcedimientosListNew.contains(uceIngAnexo3ProcedimientosListOldUceIngAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceIngAnexo3Procedimientos " + uceIngAnexo3ProcedimientosListOldUceIngAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListOldUceIngAnexo3Cie10 : uceIngAnexo3Cie10ListOld) {
                if (!uceIngAnexo3Cie10ListNew.contains(uceIngAnexo3Cie10ListOldUceIngAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceIngAnexo3Cie10 " + uceIngAnexo3Cie10ListOldUceIngAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceIngAnexo3Procedimientos> attachedUceIngAnexo3ProcedimientosListNew = new ArrayList<UceIngAnexo3Procedimientos>();
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListNewUceIngAnexo3ProcedimientosToAttach : uceIngAnexo3ProcedimientosListNew) {
                uceIngAnexo3ProcedimientosListNewUceIngAnexo3ProcedimientosToAttach = em.getReference(uceIngAnexo3ProcedimientosListNewUceIngAnexo3ProcedimientosToAttach.getClass(), uceIngAnexo3ProcedimientosListNewUceIngAnexo3ProcedimientosToAttach.getId());
                attachedUceIngAnexo3ProcedimientosListNew.add(uceIngAnexo3ProcedimientosListNewUceIngAnexo3ProcedimientosToAttach);
            }
            uceIngAnexo3ProcedimientosListNew = attachedUceIngAnexo3ProcedimientosListNew;
            uceIngAnexo3.setUceIngAnexo3ProcedimientosList(uceIngAnexo3ProcedimientosListNew);
            List<UceIngAnexo3Cie10> attachedUceIngAnexo3Cie10ListNew = new ArrayList<UceIngAnexo3Cie10>();
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10ToAttach : uceIngAnexo3Cie10ListNew) {
                uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10ToAttach = em.getReference(uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10ToAttach.getClass(), uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10ToAttach.getId());
                attachedUceIngAnexo3Cie10ListNew.add(uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10ToAttach);
            }
            uceIngAnexo3Cie10ListNew = attachedUceIngAnexo3Cie10ListNew;
            uceIngAnexo3.setUceIngAnexo3Cie10List(uceIngAnexo3Cie10ListNew);
            uceIngAnexo3 = em.merge(uceIngAnexo3);
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos : uceIngAnexo3ProcedimientosListNew) {
                if (!uceIngAnexo3ProcedimientosListOld.contains(uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos)) {
                    UceIngAnexo3 oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos = uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos.getIdanexo();
                    uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos.setIdanexo(uceIngAnexo3);
                    uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos = em.merge(uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos);
                    if (oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos != null && !oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos.equals(uceIngAnexo3)) {
                        oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos.getUceIngAnexo3ProcedimientosList().remove(uceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos);
                        oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos = em.merge(oldIdanexoOfUceIngAnexo3ProcedimientosListNewUceIngAnexo3Procedimientos);
                    }
                }
            }
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10 : uceIngAnexo3Cie10ListNew) {
                if (!uceIngAnexo3Cie10ListOld.contains(uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10)) {
                    UceIngAnexo3 oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10 = uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10.getIdanexo();
                    uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10.setIdanexo(uceIngAnexo3);
                    uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10 = em.merge(uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10);
                    if (oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10 != null && !oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10.equals(uceIngAnexo3)) {
                        oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10.getUceIngAnexo3Cie10List().remove(uceIngAnexo3Cie10ListNewUceIngAnexo3Cie10);
                        oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10 = em.merge(oldIdanexoOfUceIngAnexo3Cie10ListNewUceIngAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngAnexo3.getId();
                if (findUceIngAnexo3(id) == null) {
                    throw new NonexistentEntityException("The uceIngAnexo3 with id " + id + " no longer exists.");
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
            UceIngAnexo3 uceIngAnexo3;
            try {
                uceIngAnexo3 = em.getReference(UceIngAnexo3.class, id);
                uceIngAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceIngAnexo3Procedimientos> uceIngAnexo3ProcedimientosListOrphanCheck = uceIngAnexo3.getUceIngAnexo3ProcedimientosList();
            for (UceIngAnexo3Procedimientos uceIngAnexo3ProcedimientosListOrphanCheckUceIngAnexo3Procedimientos : uceIngAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceIngAnexo3 (" + uceIngAnexo3 + ") cannot be destroyed since the UceIngAnexo3Procedimientos " + uceIngAnexo3ProcedimientosListOrphanCheckUceIngAnexo3Procedimientos + " in its uceIngAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<UceIngAnexo3Cie10> uceIngAnexo3Cie10ListOrphanCheck = uceIngAnexo3.getUceIngAnexo3Cie10List();
            for (UceIngAnexo3Cie10 uceIngAnexo3Cie10ListOrphanCheckUceIngAnexo3Cie10 : uceIngAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceIngAnexo3 (" + uceIngAnexo3 + ") cannot be destroyed since the UceIngAnexo3Cie10 " + uceIngAnexo3Cie10ListOrphanCheckUceIngAnexo3Cie10 + " in its uceIngAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceIngAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngAnexo3> findUceIngAnexo3Entities() {
        return findUceIngAnexo3Entities(true, -1, -1);
    }

    public List<UceIngAnexo3> findUceIngAnexo3Entities(int maxResults, int firstResult) {
        return findUceIngAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UceIngAnexo3> findUceIngAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngAnexo3.class));
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

    public UceIngAnexo3 findUceIngAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngAnexo3> rt = cq.from(UceIngAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
