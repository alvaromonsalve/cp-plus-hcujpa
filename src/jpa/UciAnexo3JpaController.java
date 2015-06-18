/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciAnexo3JpaController implements Serializable {

    public UciAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAnexo3 uciAnexo3) {
        if (uciAnexo3.getUciAnexo3ProcedimientosList() == null) {
            uciAnexo3.setUciAnexo3ProcedimientosList(new ArrayList<UciAnexo3Procedimientos>());
        }
        if (uciAnexo3.getUciAnexo3Cie10List() == null) {
            uciAnexo3.setUciAnexo3Cie10List(new ArrayList<UciAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciAnexo3Procedimientos> attachedUciAnexo3ProcedimientosList = new ArrayList<UciAnexo3Procedimientos>();
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListUciAnexo3ProcedimientosToAttach : uciAnexo3.getUciAnexo3ProcedimientosList()) {
                uciAnexo3ProcedimientosListUciAnexo3ProcedimientosToAttach = em.getReference(uciAnexo3ProcedimientosListUciAnexo3ProcedimientosToAttach.getClass(), uciAnexo3ProcedimientosListUciAnexo3ProcedimientosToAttach.getId());
                attachedUciAnexo3ProcedimientosList.add(uciAnexo3ProcedimientosListUciAnexo3ProcedimientosToAttach);
            }
            uciAnexo3.setUciAnexo3ProcedimientosList(attachedUciAnexo3ProcedimientosList);
            List<UciAnexo3Cie10> attachedUciAnexo3Cie10List = new ArrayList<UciAnexo3Cie10>();
            for (UciAnexo3Cie10 uciAnexo3Cie10ListUciAnexo3Cie10ToAttach : uciAnexo3.getUciAnexo3Cie10List()) {
                uciAnexo3Cie10ListUciAnexo3Cie10ToAttach = em.getReference(uciAnexo3Cie10ListUciAnexo3Cie10ToAttach.getClass(), uciAnexo3Cie10ListUciAnexo3Cie10ToAttach.getId());
                attachedUciAnexo3Cie10List.add(uciAnexo3Cie10ListUciAnexo3Cie10ToAttach);
            }
            uciAnexo3.setUciAnexo3Cie10List(attachedUciAnexo3Cie10List);
            em.persist(uciAnexo3);
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListUciAnexo3Procedimientos : uciAnexo3.getUciAnexo3ProcedimientosList()) {
                UciAnexo3 oldIdanexoOfUciAnexo3ProcedimientosListUciAnexo3Procedimientos = uciAnexo3ProcedimientosListUciAnexo3Procedimientos.getIdanexo();
                uciAnexo3ProcedimientosListUciAnexo3Procedimientos.setIdanexo(uciAnexo3);
                uciAnexo3ProcedimientosListUciAnexo3Procedimientos = em.merge(uciAnexo3ProcedimientosListUciAnexo3Procedimientos);
                if (oldIdanexoOfUciAnexo3ProcedimientosListUciAnexo3Procedimientos != null) {
                    oldIdanexoOfUciAnexo3ProcedimientosListUciAnexo3Procedimientos.getUciAnexo3ProcedimientosList().remove(uciAnexo3ProcedimientosListUciAnexo3Procedimientos);
                    oldIdanexoOfUciAnexo3ProcedimientosListUciAnexo3Procedimientos = em.merge(oldIdanexoOfUciAnexo3ProcedimientosListUciAnexo3Procedimientos);
                }
            }
            for (UciAnexo3Cie10 uciAnexo3Cie10ListUciAnexo3Cie10 : uciAnexo3.getUciAnexo3Cie10List()) {
                UciAnexo3 oldIdanexoOfUciAnexo3Cie10ListUciAnexo3Cie10 = uciAnexo3Cie10ListUciAnexo3Cie10.getIdanexo();
                uciAnexo3Cie10ListUciAnexo3Cie10.setIdanexo(uciAnexo3);
                uciAnexo3Cie10ListUciAnexo3Cie10 = em.merge(uciAnexo3Cie10ListUciAnexo3Cie10);
                if (oldIdanexoOfUciAnexo3Cie10ListUciAnexo3Cie10 != null) {
                    oldIdanexoOfUciAnexo3Cie10ListUciAnexo3Cie10.getUciAnexo3Cie10List().remove(uciAnexo3Cie10ListUciAnexo3Cie10);
                    oldIdanexoOfUciAnexo3Cie10ListUciAnexo3Cie10 = em.merge(oldIdanexoOfUciAnexo3Cie10ListUciAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAnexo3 uciAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAnexo3 persistentUciAnexo3 = em.find(UciAnexo3.class, uciAnexo3.getId());
            List<UciAnexo3Procedimientos> uciAnexo3ProcedimientosListOld = persistentUciAnexo3.getUciAnexo3ProcedimientosList();
            List<UciAnexo3Procedimientos> uciAnexo3ProcedimientosListNew = uciAnexo3.getUciAnexo3ProcedimientosList();
            List<UciAnexo3Cie10> uciAnexo3Cie10ListOld = persistentUciAnexo3.getUciAnexo3Cie10List();
            List<UciAnexo3Cie10> uciAnexo3Cie10ListNew = uciAnexo3.getUciAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListOldUciAnexo3Procedimientos : uciAnexo3ProcedimientosListOld) {
                if (!uciAnexo3ProcedimientosListNew.contains(uciAnexo3ProcedimientosListOldUciAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciAnexo3Procedimientos " + uciAnexo3ProcedimientosListOldUciAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (UciAnexo3Cie10 uciAnexo3Cie10ListOldUciAnexo3Cie10 : uciAnexo3Cie10ListOld) {
                if (!uciAnexo3Cie10ListNew.contains(uciAnexo3Cie10ListOldUciAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciAnexo3Cie10 " + uciAnexo3Cie10ListOldUciAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciAnexo3Procedimientos> attachedUciAnexo3ProcedimientosListNew = new ArrayList<UciAnexo3Procedimientos>();
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListNewUciAnexo3ProcedimientosToAttach : uciAnexo3ProcedimientosListNew) {
                uciAnexo3ProcedimientosListNewUciAnexo3ProcedimientosToAttach = em.getReference(uciAnexo3ProcedimientosListNewUciAnexo3ProcedimientosToAttach.getClass(), uciAnexo3ProcedimientosListNewUciAnexo3ProcedimientosToAttach.getId());
                attachedUciAnexo3ProcedimientosListNew.add(uciAnexo3ProcedimientosListNewUciAnexo3ProcedimientosToAttach);
            }
            uciAnexo3ProcedimientosListNew = attachedUciAnexo3ProcedimientosListNew;
            uciAnexo3.setUciAnexo3ProcedimientosList(uciAnexo3ProcedimientosListNew);
            List<UciAnexo3Cie10> attachedUciAnexo3Cie10ListNew = new ArrayList<UciAnexo3Cie10>();
            for (UciAnexo3Cie10 uciAnexo3Cie10ListNewUciAnexo3Cie10ToAttach : uciAnexo3Cie10ListNew) {
                uciAnexo3Cie10ListNewUciAnexo3Cie10ToAttach = em.getReference(uciAnexo3Cie10ListNewUciAnexo3Cie10ToAttach.getClass(), uciAnexo3Cie10ListNewUciAnexo3Cie10ToAttach.getId());
                attachedUciAnexo3Cie10ListNew.add(uciAnexo3Cie10ListNewUciAnexo3Cie10ToAttach);
            }
            uciAnexo3Cie10ListNew = attachedUciAnexo3Cie10ListNew;
            uciAnexo3.setUciAnexo3Cie10List(uciAnexo3Cie10ListNew);
            uciAnexo3 = em.merge(uciAnexo3);
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos : uciAnexo3ProcedimientosListNew) {
                if (!uciAnexo3ProcedimientosListOld.contains(uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos)) {
                    UciAnexo3 oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos = uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos.getIdanexo();
                    uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos.setIdanexo(uciAnexo3);
                    uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos = em.merge(uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos);
                    if (oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos != null && !oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos.equals(uciAnexo3)) {
                        oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos.getUciAnexo3ProcedimientosList().remove(uciAnexo3ProcedimientosListNewUciAnexo3Procedimientos);
                        oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos = em.merge(oldIdanexoOfUciAnexo3ProcedimientosListNewUciAnexo3Procedimientos);
                    }
                }
            }
            for (UciAnexo3Cie10 uciAnexo3Cie10ListNewUciAnexo3Cie10 : uciAnexo3Cie10ListNew) {
                if (!uciAnexo3Cie10ListOld.contains(uciAnexo3Cie10ListNewUciAnexo3Cie10)) {
                    UciAnexo3 oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10 = uciAnexo3Cie10ListNewUciAnexo3Cie10.getIdanexo();
                    uciAnexo3Cie10ListNewUciAnexo3Cie10.setIdanexo(uciAnexo3);
                    uciAnexo3Cie10ListNewUciAnexo3Cie10 = em.merge(uciAnexo3Cie10ListNewUciAnexo3Cie10);
                    if (oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10 != null && !oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10.equals(uciAnexo3)) {
                        oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10.getUciAnexo3Cie10List().remove(uciAnexo3Cie10ListNewUciAnexo3Cie10);
                        oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10 = em.merge(oldIdanexoOfUciAnexo3Cie10ListNewUciAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAnexo3.getId();
                if (findUciAnexo3(id) == null) {
                    throw new NonexistentEntityException("The uciAnexo3 with id " + id + " no longer exists.");
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
            UciAnexo3 uciAnexo3;
            try {
                uciAnexo3 = em.getReference(UciAnexo3.class, id);
                uciAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciAnexo3Procedimientos> uciAnexo3ProcedimientosListOrphanCheck = uciAnexo3.getUciAnexo3ProcedimientosList();
            for (UciAnexo3Procedimientos uciAnexo3ProcedimientosListOrphanCheckUciAnexo3Procedimientos : uciAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciAnexo3 (" + uciAnexo3 + ") cannot be destroyed since the UciAnexo3Procedimientos " + uciAnexo3ProcedimientosListOrphanCheckUciAnexo3Procedimientos + " in its uciAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<UciAnexo3Cie10> uciAnexo3Cie10ListOrphanCheck = uciAnexo3.getUciAnexo3Cie10List();
            for (UciAnexo3Cie10 uciAnexo3Cie10ListOrphanCheckUciAnexo3Cie10 : uciAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciAnexo3 (" + uciAnexo3 + ") cannot be destroyed since the UciAnexo3Cie10 " + uciAnexo3Cie10ListOrphanCheckUciAnexo3Cie10 + " in its uciAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAnexo3> findUciAnexo3Entities() {
        return findUciAnexo3Entities(true, -1, -1);
    }

    public List<UciAnexo3> findUciAnexo3Entities(int maxResults, int firstResult) {
        return findUciAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UciAnexo3> findUciAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAnexo3.class));
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

    public UciAnexo3 findUciAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAnexo3> rt = cq.from(UciAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
