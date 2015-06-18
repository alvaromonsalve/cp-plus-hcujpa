/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceAnexo3Cie10;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceAnexo3Procedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceAnexo3JpaController implements Serializable {

    public UceAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceAnexo3 uceAnexo3) {
        if (uceAnexo3.getUceAnexo3Cie10List() == null) {
            uceAnexo3.setUceAnexo3Cie10List(new ArrayList<UceAnexo3Cie10>());
        }
        if (uceAnexo3.getUceAnexo3ProcedimientosList() == null) {
            uceAnexo3.setUceAnexo3ProcedimientosList(new ArrayList<UceAnexo3Procedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceAnexo3Cie10> attachedUceAnexo3Cie10List = new ArrayList<UceAnexo3Cie10>();
            for (UceAnexo3Cie10 uceAnexo3Cie10ListUceAnexo3Cie10ToAttach : uceAnexo3.getUceAnexo3Cie10List()) {
                uceAnexo3Cie10ListUceAnexo3Cie10ToAttach = em.getReference(uceAnexo3Cie10ListUceAnexo3Cie10ToAttach.getClass(), uceAnexo3Cie10ListUceAnexo3Cie10ToAttach.getId());
                attachedUceAnexo3Cie10List.add(uceAnexo3Cie10ListUceAnexo3Cie10ToAttach);
            }
            uceAnexo3.setUceAnexo3Cie10List(attachedUceAnexo3Cie10List);
            List<UceAnexo3Procedimientos> attachedUceAnexo3ProcedimientosList = new ArrayList<UceAnexo3Procedimientos>();
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListUceAnexo3ProcedimientosToAttach : uceAnexo3.getUceAnexo3ProcedimientosList()) {
                uceAnexo3ProcedimientosListUceAnexo3ProcedimientosToAttach = em.getReference(uceAnexo3ProcedimientosListUceAnexo3ProcedimientosToAttach.getClass(), uceAnexo3ProcedimientosListUceAnexo3ProcedimientosToAttach.getId());
                attachedUceAnexo3ProcedimientosList.add(uceAnexo3ProcedimientosListUceAnexo3ProcedimientosToAttach);
            }
            uceAnexo3.setUceAnexo3ProcedimientosList(attachedUceAnexo3ProcedimientosList);
            em.persist(uceAnexo3);
            for (UceAnexo3Cie10 uceAnexo3Cie10ListUceAnexo3Cie10 : uceAnexo3.getUceAnexo3Cie10List()) {
                UceAnexo3 oldIdanexoOfUceAnexo3Cie10ListUceAnexo3Cie10 = uceAnexo3Cie10ListUceAnexo3Cie10.getIdanexo();
                uceAnexo3Cie10ListUceAnexo3Cie10.setIdanexo(uceAnexo3);
                uceAnexo3Cie10ListUceAnexo3Cie10 = em.merge(uceAnexo3Cie10ListUceAnexo3Cie10);
                if (oldIdanexoOfUceAnexo3Cie10ListUceAnexo3Cie10 != null) {
                    oldIdanexoOfUceAnexo3Cie10ListUceAnexo3Cie10.getUceAnexo3Cie10List().remove(uceAnexo3Cie10ListUceAnexo3Cie10);
                    oldIdanexoOfUceAnexo3Cie10ListUceAnexo3Cie10 = em.merge(oldIdanexoOfUceAnexo3Cie10ListUceAnexo3Cie10);
                }
            }
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListUceAnexo3Procedimientos : uceAnexo3.getUceAnexo3ProcedimientosList()) {
                UceAnexo3 oldIdanexoOfUceAnexo3ProcedimientosListUceAnexo3Procedimientos = uceAnexo3ProcedimientosListUceAnexo3Procedimientos.getIdanexo();
                uceAnexo3ProcedimientosListUceAnexo3Procedimientos.setIdanexo(uceAnexo3);
                uceAnexo3ProcedimientosListUceAnexo3Procedimientos = em.merge(uceAnexo3ProcedimientosListUceAnexo3Procedimientos);
                if (oldIdanexoOfUceAnexo3ProcedimientosListUceAnexo3Procedimientos != null) {
                    oldIdanexoOfUceAnexo3ProcedimientosListUceAnexo3Procedimientos.getUceAnexo3ProcedimientosList().remove(uceAnexo3ProcedimientosListUceAnexo3Procedimientos);
                    oldIdanexoOfUceAnexo3ProcedimientosListUceAnexo3Procedimientos = em.merge(oldIdanexoOfUceAnexo3ProcedimientosListUceAnexo3Procedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceAnexo3 uceAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceAnexo3 persistentUceAnexo3 = em.find(UceAnexo3.class, uceAnexo3.getId());
            List<UceAnexo3Cie10> uceAnexo3Cie10ListOld = persistentUceAnexo3.getUceAnexo3Cie10List();
            List<UceAnexo3Cie10> uceAnexo3Cie10ListNew = uceAnexo3.getUceAnexo3Cie10List();
            List<UceAnexo3Procedimientos> uceAnexo3ProcedimientosListOld = persistentUceAnexo3.getUceAnexo3ProcedimientosList();
            List<UceAnexo3Procedimientos> uceAnexo3ProcedimientosListNew = uceAnexo3.getUceAnexo3ProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (UceAnexo3Cie10 uceAnexo3Cie10ListOldUceAnexo3Cie10 : uceAnexo3Cie10ListOld) {
                if (!uceAnexo3Cie10ListNew.contains(uceAnexo3Cie10ListOldUceAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceAnexo3Cie10 " + uceAnexo3Cie10ListOldUceAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListOldUceAnexo3Procedimientos : uceAnexo3ProcedimientosListOld) {
                if (!uceAnexo3ProcedimientosListNew.contains(uceAnexo3ProcedimientosListOldUceAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceAnexo3Procedimientos " + uceAnexo3ProcedimientosListOldUceAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceAnexo3Cie10> attachedUceAnexo3Cie10ListNew = new ArrayList<UceAnexo3Cie10>();
            for (UceAnexo3Cie10 uceAnexo3Cie10ListNewUceAnexo3Cie10ToAttach : uceAnexo3Cie10ListNew) {
                uceAnexo3Cie10ListNewUceAnexo3Cie10ToAttach = em.getReference(uceAnexo3Cie10ListNewUceAnexo3Cie10ToAttach.getClass(), uceAnexo3Cie10ListNewUceAnexo3Cie10ToAttach.getId());
                attachedUceAnexo3Cie10ListNew.add(uceAnexo3Cie10ListNewUceAnexo3Cie10ToAttach);
            }
            uceAnexo3Cie10ListNew = attachedUceAnexo3Cie10ListNew;
            uceAnexo3.setUceAnexo3Cie10List(uceAnexo3Cie10ListNew);
            List<UceAnexo3Procedimientos> attachedUceAnexo3ProcedimientosListNew = new ArrayList<UceAnexo3Procedimientos>();
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListNewUceAnexo3ProcedimientosToAttach : uceAnexo3ProcedimientosListNew) {
                uceAnexo3ProcedimientosListNewUceAnexo3ProcedimientosToAttach = em.getReference(uceAnexo3ProcedimientosListNewUceAnexo3ProcedimientosToAttach.getClass(), uceAnexo3ProcedimientosListNewUceAnexo3ProcedimientosToAttach.getId());
                attachedUceAnexo3ProcedimientosListNew.add(uceAnexo3ProcedimientosListNewUceAnexo3ProcedimientosToAttach);
            }
            uceAnexo3ProcedimientosListNew = attachedUceAnexo3ProcedimientosListNew;
            uceAnexo3.setUceAnexo3ProcedimientosList(uceAnexo3ProcedimientosListNew);
            uceAnexo3 = em.merge(uceAnexo3);
            for (UceAnexo3Cie10 uceAnexo3Cie10ListNewUceAnexo3Cie10 : uceAnexo3Cie10ListNew) {
                if (!uceAnexo3Cie10ListOld.contains(uceAnexo3Cie10ListNewUceAnexo3Cie10)) {
                    UceAnexo3 oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10 = uceAnexo3Cie10ListNewUceAnexo3Cie10.getIdanexo();
                    uceAnexo3Cie10ListNewUceAnexo3Cie10.setIdanexo(uceAnexo3);
                    uceAnexo3Cie10ListNewUceAnexo3Cie10 = em.merge(uceAnexo3Cie10ListNewUceAnexo3Cie10);
                    if (oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10 != null && !oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10.equals(uceAnexo3)) {
                        oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10.getUceAnexo3Cie10List().remove(uceAnexo3Cie10ListNewUceAnexo3Cie10);
                        oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10 = em.merge(oldIdanexoOfUceAnexo3Cie10ListNewUceAnexo3Cie10);
                    }
                }
            }
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos : uceAnexo3ProcedimientosListNew) {
                if (!uceAnexo3ProcedimientosListOld.contains(uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos)) {
                    UceAnexo3 oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos = uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos.getIdanexo();
                    uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos.setIdanexo(uceAnexo3);
                    uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos = em.merge(uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos);
                    if (oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos != null && !oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos.equals(uceAnexo3)) {
                        oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos.getUceAnexo3ProcedimientosList().remove(uceAnexo3ProcedimientosListNewUceAnexo3Procedimientos);
                        oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos = em.merge(oldIdanexoOfUceAnexo3ProcedimientosListNewUceAnexo3Procedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceAnexo3.getId();
                if (findUceAnexo3(id) == null) {
                    throw new NonexistentEntityException("The uceAnexo3 with id " + id + " no longer exists.");
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
            UceAnexo3 uceAnexo3;
            try {
                uceAnexo3 = em.getReference(UceAnexo3.class, id);
                uceAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceAnexo3Cie10> uceAnexo3Cie10ListOrphanCheck = uceAnexo3.getUceAnexo3Cie10List();
            for (UceAnexo3Cie10 uceAnexo3Cie10ListOrphanCheckUceAnexo3Cie10 : uceAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceAnexo3 (" + uceAnexo3 + ") cannot be destroyed since the UceAnexo3Cie10 " + uceAnexo3Cie10ListOrphanCheckUceAnexo3Cie10 + " in its uceAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            List<UceAnexo3Procedimientos> uceAnexo3ProcedimientosListOrphanCheck = uceAnexo3.getUceAnexo3ProcedimientosList();
            for (UceAnexo3Procedimientos uceAnexo3ProcedimientosListOrphanCheckUceAnexo3Procedimientos : uceAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceAnexo3 (" + uceAnexo3 + ") cannot be destroyed since the UceAnexo3Procedimientos " + uceAnexo3ProcedimientosListOrphanCheckUceAnexo3Procedimientos + " in its uceAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceAnexo3> findUceAnexo3Entities() {
        return findUceAnexo3Entities(true, -1, -1);
    }

    public List<UceAnexo3> findUceAnexo3Entities(int maxResults, int firstResult) {
        return findUceAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UceAnexo3> findUceAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceAnexo3.class));
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

    public UceAnexo3 findUceAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceAnexo3> rt = cq.from(UceAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
