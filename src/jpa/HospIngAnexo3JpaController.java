/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospIngAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospIngAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospIngAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngAnexo3JpaController implements Serializable {

    public HospIngAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngAnexo3 hospIngAnexo3) {
        if (hospIngAnexo3.getHospIngAnexo3ProcedimientosList() == null) {
            hospIngAnexo3.setHospIngAnexo3ProcedimientosList(new ArrayList<HospIngAnexo3Procedimientos>());
        }
        if (hospIngAnexo3.getHospIngAnexo3Cie10List() == null) {
            hospIngAnexo3.setHospIngAnexo3Cie10List(new ArrayList<HospIngAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospIngAnexo3Procedimientos> attachedHospIngAnexo3ProcedimientosList = new ArrayList<HospIngAnexo3Procedimientos>();
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListHospIngAnexo3ProcedimientosToAttach : hospIngAnexo3.getHospIngAnexo3ProcedimientosList()) {
                hospIngAnexo3ProcedimientosListHospIngAnexo3ProcedimientosToAttach = em.getReference(hospIngAnexo3ProcedimientosListHospIngAnexo3ProcedimientosToAttach.getClass(), hospIngAnexo3ProcedimientosListHospIngAnexo3ProcedimientosToAttach.getId());
                attachedHospIngAnexo3ProcedimientosList.add(hospIngAnexo3ProcedimientosListHospIngAnexo3ProcedimientosToAttach);
            }
            hospIngAnexo3.setHospIngAnexo3ProcedimientosList(attachedHospIngAnexo3ProcedimientosList);
            List<HospIngAnexo3Cie10> attachedHospIngAnexo3Cie10List = new ArrayList<HospIngAnexo3Cie10>();
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListHospIngAnexo3Cie10ToAttach : hospIngAnexo3.getHospIngAnexo3Cie10List()) {
                hospIngAnexo3Cie10ListHospIngAnexo3Cie10ToAttach = em.getReference(hospIngAnexo3Cie10ListHospIngAnexo3Cie10ToAttach.getClass(), hospIngAnexo3Cie10ListHospIngAnexo3Cie10ToAttach.getId());
                attachedHospIngAnexo3Cie10List.add(hospIngAnexo3Cie10ListHospIngAnexo3Cie10ToAttach);
            }
            hospIngAnexo3.setHospIngAnexo3Cie10List(attachedHospIngAnexo3Cie10List);
            em.persist(hospIngAnexo3);
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos : hospIngAnexo3.getHospIngAnexo3ProcedimientosList()) {
                HospIngAnexo3 oldIdanexoOfHospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos = hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos.getIdanexo();
                hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos.setIdanexo(hospIngAnexo3);
                hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos = em.merge(hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos);
                if (oldIdanexoOfHospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos != null) {
                    oldIdanexoOfHospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos.getHospIngAnexo3ProcedimientosList().remove(hospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos);
                    oldIdanexoOfHospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos = em.merge(oldIdanexoOfHospIngAnexo3ProcedimientosListHospIngAnexo3Procedimientos);
                }
            }
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListHospIngAnexo3Cie10 : hospIngAnexo3.getHospIngAnexo3Cie10List()) {
                HospIngAnexo3 oldIdanexoOfHospIngAnexo3Cie10ListHospIngAnexo3Cie10 = hospIngAnexo3Cie10ListHospIngAnexo3Cie10.getIdanexo();
                hospIngAnexo3Cie10ListHospIngAnexo3Cie10.setIdanexo(hospIngAnexo3);
                hospIngAnexo3Cie10ListHospIngAnexo3Cie10 = em.merge(hospIngAnexo3Cie10ListHospIngAnexo3Cie10);
                if (oldIdanexoOfHospIngAnexo3Cie10ListHospIngAnexo3Cie10 != null) {
                    oldIdanexoOfHospIngAnexo3Cie10ListHospIngAnexo3Cie10.getHospIngAnexo3Cie10List().remove(hospIngAnexo3Cie10ListHospIngAnexo3Cie10);
                    oldIdanexoOfHospIngAnexo3Cie10ListHospIngAnexo3Cie10 = em.merge(oldIdanexoOfHospIngAnexo3Cie10ListHospIngAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngAnexo3 hospIngAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngAnexo3 persistentHospIngAnexo3 = em.find(HospIngAnexo3.class, hospIngAnexo3.getId());
            List<HospIngAnexo3Procedimientos> hospIngAnexo3ProcedimientosListOld = persistentHospIngAnexo3.getHospIngAnexo3ProcedimientosList();
            List<HospIngAnexo3Procedimientos> hospIngAnexo3ProcedimientosListNew = hospIngAnexo3.getHospIngAnexo3ProcedimientosList();
            List<HospIngAnexo3Cie10> hospIngAnexo3Cie10ListOld = persistentHospIngAnexo3.getHospIngAnexo3Cie10List();
            List<HospIngAnexo3Cie10> hospIngAnexo3Cie10ListNew = hospIngAnexo3.getHospIngAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListOldHospIngAnexo3Procedimientos : hospIngAnexo3ProcedimientosListOld) {
                if (!hospIngAnexo3ProcedimientosListNew.contains(hospIngAnexo3ProcedimientosListOldHospIngAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospIngAnexo3Procedimientos " + hospIngAnexo3ProcedimientosListOldHospIngAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListOldHospIngAnexo3Cie10 : hospIngAnexo3Cie10ListOld) {
                if (!hospIngAnexo3Cie10ListNew.contains(hospIngAnexo3Cie10ListOldHospIngAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospIngAnexo3Cie10 " + hospIngAnexo3Cie10ListOldHospIngAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospIngAnexo3Procedimientos> attachedHospIngAnexo3ProcedimientosListNew = new ArrayList<HospIngAnexo3Procedimientos>();
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListNewHospIngAnexo3ProcedimientosToAttach : hospIngAnexo3ProcedimientosListNew) {
                hospIngAnexo3ProcedimientosListNewHospIngAnexo3ProcedimientosToAttach = em.getReference(hospIngAnexo3ProcedimientosListNewHospIngAnexo3ProcedimientosToAttach.getClass(), hospIngAnexo3ProcedimientosListNewHospIngAnexo3ProcedimientosToAttach.getId());
                attachedHospIngAnexo3ProcedimientosListNew.add(hospIngAnexo3ProcedimientosListNewHospIngAnexo3ProcedimientosToAttach);
            }
            hospIngAnexo3ProcedimientosListNew = attachedHospIngAnexo3ProcedimientosListNew;
            hospIngAnexo3.setHospIngAnexo3ProcedimientosList(hospIngAnexo3ProcedimientosListNew);
            List<HospIngAnexo3Cie10> attachedHospIngAnexo3Cie10ListNew = new ArrayList<HospIngAnexo3Cie10>();
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10ToAttach : hospIngAnexo3Cie10ListNew) {
                hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10ToAttach = em.getReference(hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10ToAttach.getClass(), hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10ToAttach.getId());
                attachedHospIngAnexo3Cie10ListNew.add(hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10ToAttach);
            }
            hospIngAnexo3Cie10ListNew = attachedHospIngAnexo3Cie10ListNew;
            hospIngAnexo3.setHospIngAnexo3Cie10List(hospIngAnexo3Cie10ListNew);
            hospIngAnexo3 = em.merge(hospIngAnexo3);
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos : hospIngAnexo3ProcedimientosListNew) {
                if (!hospIngAnexo3ProcedimientosListOld.contains(hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos)) {
                    HospIngAnexo3 oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos = hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos.getIdanexo();
                    hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos.setIdanexo(hospIngAnexo3);
                    hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos = em.merge(hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos);
                    if (oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos != null && !oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos.equals(hospIngAnexo3)) {
                        oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos.getHospIngAnexo3ProcedimientosList().remove(hospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos);
                        oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos = em.merge(oldIdanexoOfHospIngAnexo3ProcedimientosListNewHospIngAnexo3Procedimientos);
                    }
                }
            }
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10 : hospIngAnexo3Cie10ListNew) {
                if (!hospIngAnexo3Cie10ListOld.contains(hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10)) {
                    HospIngAnexo3 oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10 = hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10.getIdanexo();
                    hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10.setIdanexo(hospIngAnexo3);
                    hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10 = em.merge(hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10);
                    if (oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10 != null && !oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10.equals(hospIngAnexo3)) {
                        oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10.getHospIngAnexo3Cie10List().remove(hospIngAnexo3Cie10ListNewHospIngAnexo3Cie10);
                        oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10 = em.merge(oldIdanexoOfHospIngAnexo3Cie10ListNewHospIngAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngAnexo3.getId();
                if (findHospIngAnexo3(id) == null) {
                    throw new NonexistentEntityException("The hospIngAnexo3 with id " + id + " no longer exists.");
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
            HospIngAnexo3 hospIngAnexo3;
            try {
                hospIngAnexo3 = em.getReference(HospIngAnexo3.class, id);
                hospIngAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospIngAnexo3Procedimientos> hospIngAnexo3ProcedimientosListOrphanCheck = hospIngAnexo3.getHospIngAnexo3ProcedimientosList();
            for (HospIngAnexo3Procedimientos hospIngAnexo3ProcedimientosListOrphanCheckHospIngAnexo3Procedimientos : hospIngAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospIngAnexo3 (" + hospIngAnexo3 + ") cannot be destroyed since the HospIngAnexo3Procedimientos " + hospIngAnexo3ProcedimientosListOrphanCheckHospIngAnexo3Procedimientos + " in its hospIngAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<HospIngAnexo3Cie10> hospIngAnexo3Cie10ListOrphanCheck = hospIngAnexo3.getHospIngAnexo3Cie10List();
            for (HospIngAnexo3Cie10 hospIngAnexo3Cie10ListOrphanCheckHospIngAnexo3Cie10 : hospIngAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospIngAnexo3 (" + hospIngAnexo3 + ") cannot be destroyed since the HospIngAnexo3Cie10 " + hospIngAnexo3Cie10ListOrphanCheckHospIngAnexo3Cie10 + " in its hospIngAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospIngAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngAnexo3> findHospIngAnexo3Entities() {
        return findHospIngAnexo3Entities(true, -1, -1);
    }

    public List<HospIngAnexo3> findHospIngAnexo3Entities(int maxResults, int firstResult) {
        return findHospIngAnexo3Entities(false, maxResults, firstResult);
    }

    private List<HospIngAnexo3> findHospIngAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngAnexo3.class));
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

    public HospIngAnexo3 findHospIngAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngAnexo3> rt = cq.from(HospIngAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
