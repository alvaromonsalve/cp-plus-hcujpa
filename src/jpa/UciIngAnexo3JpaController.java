/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciIngAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciIngAnexo3Cie10;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciIngAnexo3Procedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngAnexo3JpaController implements Serializable {

    public UciIngAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngAnexo3 uciIngAnexo3) {
        if (uciIngAnexo3.getUciIngAnexo3Cie10List() == null) {
            uciIngAnexo3.setUciIngAnexo3Cie10List(new ArrayList<UciIngAnexo3Cie10>());
        }
        if (uciIngAnexo3.getUciIngAnexo3ProcedimientosList() == null) {
            uciIngAnexo3.setUciIngAnexo3ProcedimientosList(new ArrayList<UciIngAnexo3Procedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciIngAnexo3Cie10> attachedUciIngAnexo3Cie10List = new ArrayList<UciIngAnexo3Cie10>();
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListUciIngAnexo3Cie10ToAttach : uciIngAnexo3.getUciIngAnexo3Cie10List()) {
                uciIngAnexo3Cie10ListUciIngAnexo3Cie10ToAttach = em.getReference(uciIngAnexo3Cie10ListUciIngAnexo3Cie10ToAttach.getClass(), uciIngAnexo3Cie10ListUciIngAnexo3Cie10ToAttach.getId());
                attachedUciIngAnexo3Cie10List.add(uciIngAnexo3Cie10ListUciIngAnexo3Cie10ToAttach);
            }
            uciIngAnexo3.setUciIngAnexo3Cie10List(attachedUciIngAnexo3Cie10List);
            List<UciIngAnexo3Procedimientos> attachedUciIngAnexo3ProcedimientosList = new ArrayList<UciIngAnexo3Procedimientos>();
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListUciIngAnexo3ProcedimientosToAttach : uciIngAnexo3.getUciIngAnexo3ProcedimientosList()) {
                uciIngAnexo3ProcedimientosListUciIngAnexo3ProcedimientosToAttach = em.getReference(uciIngAnexo3ProcedimientosListUciIngAnexo3ProcedimientosToAttach.getClass(), uciIngAnexo3ProcedimientosListUciIngAnexo3ProcedimientosToAttach.getId());
                attachedUciIngAnexo3ProcedimientosList.add(uciIngAnexo3ProcedimientosListUciIngAnexo3ProcedimientosToAttach);
            }
            uciIngAnexo3.setUciIngAnexo3ProcedimientosList(attachedUciIngAnexo3ProcedimientosList);
            em.persist(uciIngAnexo3);
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListUciIngAnexo3Cie10 : uciIngAnexo3.getUciIngAnexo3Cie10List()) {
                UciIngAnexo3 oldIdanexoOfUciIngAnexo3Cie10ListUciIngAnexo3Cie10 = uciIngAnexo3Cie10ListUciIngAnexo3Cie10.getIdanexo();
                uciIngAnexo3Cie10ListUciIngAnexo3Cie10.setIdanexo(uciIngAnexo3);
                uciIngAnexo3Cie10ListUciIngAnexo3Cie10 = em.merge(uciIngAnexo3Cie10ListUciIngAnexo3Cie10);
                if (oldIdanexoOfUciIngAnexo3Cie10ListUciIngAnexo3Cie10 != null) {
                    oldIdanexoOfUciIngAnexo3Cie10ListUciIngAnexo3Cie10.getUciIngAnexo3Cie10List().remove(uciIngAnexo3Cie10ListUciIngAnexo3Cie10);
                    oldIdanexoOfUciIngAnexo3Cie10ListUciIngAnexo3Cie10 = em.merge(oldIdanexoOfUciIngAnexo3Cie10ListUciIngAnexo3Cie10);
                }
            }
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos : uciIngAnexo3.getUciIngAnexo3ProcedimientosList()) {
                UciIngAnexo3 oldIdanexoOfUciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos = uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos.getIdanexo();
                uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos.setIdanexo(uciIngAnexo3);
                uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos = em.merge(uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos);
                if (oldIdanexoOfUciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos != null) {
                    oldIdanexoOfUciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos.getUciIngAnexo3ProcedimientosList().remove(uciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos);
                    oldIdanexoOfUciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos = em.merge(oldIdanexoOfUciIngAnexo3ProcedimientosListUciIngAnexo3Procedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngAnexo3 uciIngAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngAnexo3 persistentUciIngAnexo3 = em.find(UciIngAnexo3.class, uciIngAnexo3.getId());
            List<UciIngAnexo3Cie10> uciIngAnexo3Cie10ListOld = persistentUciIngAnexo3.getUciIngAnexo3Cie10List();
            List<UciIngAnexo3Cie10> uciIngAnexo3Cie10ListNew = uciIngAnexo3.getUciIngAnexo3Cie10List();
            List<UciIngAnexo3Procedimientos> uciIngAnexo3ProcedimientosListOld = persistentUciIngAnexo3.getUciIngAnexo3ProcedimientosList();
            List<UciIngAnexo3Procedimientos> uciIngAnexo3ProcedimientosListNew = uciIngAnexo3.getUciIngAnexo3ProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListOldUciIngAnexo3Cie10 : uciIngAnexo3Cie10ListOld) {
                if (!uciIngAnexo3Cie10ListNew.contains(uciIngAnexo3Cie10ListOldUciIngAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciIngAnexo3Cie10 " + uciIngAnexo3Cie10ListOldUciIngAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListOldUciIngAnexo3Procedimientos : uciIngAnexo3ProcedimientosListOld) {
                if (!uciIngAnexo3ProcedimientosListNew.contains(uciIngAnexo3ProcedimientosListOldUciIngAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciIngAnexo3Procedimientos " + uciIngAnexo3ProcedimientosListOldUciIngAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciIngAnexo3Cie10> attachedUciIngAnexo3Cie10ListNew = new ArrayList<UciIngAnexo3Cie10>();
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10ToAttach : uciIngAnexo3Cie10ListNew) {
                uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10ToAttach = em.getReference(uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10ToAttach.getClass(), uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10ToAttach.getId());
                attachedUciIngAnexo3Cie10ListNew.add(uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10ToAttach);
            }
            uciIngAnexo3Cie10ListNew = attachedUciIngAnexo3Cie10ListNew;
            uciIngAnexo3.setUciIngAnexo3Cie10List(uciIngAnexo3Cie10ListNew);
            List<UciIngAnexo3Procedimientos> attachedUciIngAnexo3ProcedimientosListNew = new ArrayList<UciIngAnexo3Procedimientos>();
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListNewUciIngAnexo3ProcedimientosToAttach : uciIngAnexo3ProcedimientosListNew) {
                uciIngAnexo3ProcedimientosListNewUciIngAnexo3ProcedimientosToAttach = em.getReference(uciIngAnexo3ProcedimientosListNewUciIngAnexo3ProcedimientosToAttach.getClass(), uciIngAnexo3ProcedimientosListNewUciIngAnexo3ProcedimientosToAttach.getId());
                attachedUciIngAnexo3ProcedimientosListNew.add(uciIngAnexo3ProcedimientosListNewUciIngAnexo3ProcedimientosToAttach);
            }
            uciIngAnexo3ProcedimientosListNew = attachedUciIngAnexo3ProcedimientosListNew;
            uciIngAnexo3.setUciIngAnexo3ProcedimientosList(uciIngAnexo3ProcedimientosListNew);
            uciIngAnexo3 = em.merge(uciIngAnexo3);
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10 : uciIngAnexo3Cie10ListNew) {
                if (!uciIngAnexo3Cie10ListOld.contains(uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10)) {
                    UciIngAnexo3 oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10 = uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10.getIdanexo();
                    uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10.setIdanexo(uciIngAnexo3);
                    uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10 = em.merge(uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10);
                    if (oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10 != null && !oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10.equals(uciIngAnexo3)) {
                        oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10.getUciIngAnexo3Cie10List().remove(uciIngAnexo3Cie10ListNewUciIngAnexo3Cie10);
                        oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10 = em.merge(oldIdanexoOfUciIngAnexo3Cie10ListNewUciIngAnexo3Cie10);
                    }
                }
            }
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos : uciIngAnexo3ProcedimientosListNew) {
                if (!uciIngAnexo3ProcedimientosListOld.contains(uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos)) {
                    UciIngAnexo3 oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos = uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos.getIdanexo();
                    uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos.setIdanexo(uciIngAnexo3);
                    uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos = em.merge(uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos);
                    if (oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos != null && !oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos.equals(uciIngAnexo3)) {
                        oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos.getUciIngAnexo3ProcedimientosList().remove(uciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos);
                        oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos = em.merge(oldIdanexoOfUciIngAnexo3ProcedimientosListNewUciIngAnexo3Procedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngAnexo3.getId();
                if (findUciIngAnexo3(id) == null) {
                    throw new NonexistentEntityException("The uciIngAnexo3 with id " + id + " no longer exists.");
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
            UciIngAnexo3 uciIngAnexo3;
            try {
                uciIngAnexo3 = em.getReference(UciIngAnexo3.class, id);
                uciIngAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciIngAnexo3Cie10> uciIngAnexo3Cie10ListOrphanCheck = uciIngAnexo3.getUciIngAnexo3Cie10List();
            for (UciIngAnexo3Cie10 uciIngAnexo3Cie10ListOrphanCheckUciIngAnexo3Cie10 : uciIngAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciIngAnexo3 (" + uciIngAnexo3 + ") cannot be destroyed since the UciIngAnexo3Cie10 " + uciIngAnexo3Cie10ListOrphanCheckUciIngAnexo3Cie10 + " in its uciIngAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            List<UciIngAnexo3Procedimientos> uciIngAnexo3ProcedimientosListOrphanCheck = uciIngAnexo3.getUciIngAnexo3ProcedimientosList();
            for (UciIngAnexo3Procedimientos uciIngAnexo3ProcedimientosListOrphanCheckUciIngAnexo3Procedimientos : uciIngAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciIngAnexo3 (" + uciIngAnexo3 + ") cannot be destroyed since the UciIngAnexo3Procedimientos " + uciIngAnexo3ProcedimientosListOrphanCheckUciIngAnexo3Procedimientos + " in its uciIngAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciIngAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngAnexo3> findUciIngAnexo3Entities() {
        return findUciIngAnexo3Entities(true, -1, -1);
    }

    public List<UciIngAnexo3> findUciIngAnexo3Entities(int maxResults, int firstResult) {
        return findUciIngAnexo3Entities(false, maxResults, firstResult);
    }

    private List<UciIngAnexo3> findUciIngAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngAnexo3.class));
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

    public UciIngAnexo3 findUciIngAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngAnexo3> rt = cq.from(UciIngAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
