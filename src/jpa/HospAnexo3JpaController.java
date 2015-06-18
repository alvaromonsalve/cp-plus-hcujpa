/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospAnexo3;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospAnexo3Procedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospAnexo3Cie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospAnexo3JpaController implements Serializable {

    public HospAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospAnexo3 hospAnexo3) {
        if (hospAnexo3.getHospAnexo3ProcedimientosList() == null) {
            hospAnexo3.setHospAnexo3ProcedimientosList(new ArrayList<HospAnexo3Procedimientos>());
        }
        if (hospAnexo3.getHospAnexo3Cie10List() == null) {
            hospAnexo3.setHospAnexo3Cie10List(new ArrayList<HospAnexo3Cie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospAnexo3Procedimientos> attachedHospAnexo3ProcedimientosList = new ArrayList<HospAnexo3Procedimientos>();
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListHospAnexo3ProcedimientosToAttach : hospAnexo3.getHospAnexo3ProcedimientosList()) {
                hospAnexo3ProcedimientosListHospAnexo3ProcedimientosToAttach = em.getReference(hospAnexo3ProcedimientosListHospAnexo3ProcedimientosToAttach.getClass(), hospAnexo3ProcedimientosListHospAnexo3ProcedimientosToAttach.getId());
                attachedHospAnexo3ProcedimientosList.add(hospAnexo3ProcedimientosListHospAnexo3ProcedimientosToAttach);
            }
            hospAnexo3.setHospAnexo3ProcedimientosList(attachedHospAnexo3ProcedimientosList);
            List<HospAnexo3Cie10> attachedHospAnexo3Cie10List = new ArrayList<HospAnexo3Cie10>();
            for (HospAnexo3Cie10 hospAnexo3Cie10ListHospAnexo3Cie10ToAttach : hospAnexo3.getHospAnexo3Cie10List()) {
                hospAnexo3Cie10ListHospAnexo3Cie10ToAttach = em.getReference(hospAnexo3Cie10ListHospAnexo3Cie10ToAttach.getClass(), hospAnexo3Cie10ListHospAnexo3Cie10ToAttach.getId());
                attachedHospAnexo3Cie10List.add(hospAnexo3Cie10ListHospAnexo3Cie10ToAttach);
            }
            hospAnexo3.setHospAnexo3Cie10List(attachedHospAnexo3Cie10List);
            em.persist(hospAnexo3);
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListHospAnexo3Procedimientos : hospAnexo3.getHospAnexo3ProcedimientosList()) {
                HospAnexo3 oldIdanexoOfHospAnexo3ProcedimientosListHospAnexo3Procedimientos = hospAnexo3ProcedimientosListHospAnexo3Procedimientos.getIdanexo();
                hospAnexo3ProcedimientosListHospAnexo3Procedimientos.setIdanexo(hospAnexo3);
                hospAnexo3ProcedimientosListHospAnexo3Procedimientos = em.merge(hospAnexo3ProcedimientosListHospAnexo3Procedimientos);
                if (oldIdanexoOfHospAnexo3ProcedimientosListHospAnexo3Procedimientos != null) {
                    oldIdanexoOfHospAnexo3ProcedimientosListHospAnexo3Procedimientos.getHospAnexo3ProcedimientosList().remove(hospAnexo3ProcedimientosListHospAnexo3Procedimientos);
                    oldIdanexoOfHospAnexo3ProcedimientosListHospAnexo3Procedimientos = em.merge(oldIdanexoOfHospAnexo3ProcedimientosListHospAnexo3Procedimientos);
                }
            }
            for (HospAnexo3Cie10 hospAnexo3Cie10ListHospAnexo3Cie10 : hospAnexo3.getHospAnexo3Cie10List()) {
                HospAnexo3 oldIdanexoOfHospAnexo3Cie10ListHospAnexo3Cie10 = hospAnexo3Cie10ListHospAnexo3Cie10.getIdanexo();
                hospAnexo3Cie10ListHospAnexo3Cie10.setIdanexo(hospAnexo3);
                hospAnexo3Cie10ListHospAnexo3Cie10 = em.merge(hospAnexo3Cie10ListHospAnexo3Cie10);
                if (oldIdanexoOfHospAnexo3Cie10ListHospAnexo3Cie10 != null) {
                    oldIdanexoOfHospAnexo3Cie10ListHospAnexo3Cie10.getHospAnexo3Cie10List().remove(hospAnexo3Cie10ListHospAnexo3Cie10);
                    oldIdanexoOfHospAnexo3Cie10ListHospAnexo3Cie10 = em.merge(oldIdanexoOfHospAnexo3Cie10ListHospAnexo3Cie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospAnexo3 hospAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospAnexo3 persistentHospAnexo3 = em.find(HospAnexo3.class, hospAnexo3.getId());
            List<HospAnexo3Procedimientos> hospAnexo3ProcedimientosListOld = persistentHospAnexo3.getHospAnexo3ProcedimientosList();
            List<HospAnexo3Procedimientos> hospAnexo3ProcedimientosListNew = hospAnexo3.getHospAnexo3ProcedimientosList();
            List<HospAnexo3Cie10> hospAnexo3Cie10ListOld = persistentHospAnexo3.getHospAnexo3Cie10List();
            List<HospAnexo3Cie10> hospAnexo3Cie10ListNew = hospAnexo3.getHospAnexo3Cie10List();
            List<String> illegalOrphanMessages = null;
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListOldHospAnexo3Procedimientos : hospAnexo3ProcedimientosListOld) {
                if (!hospAnexo3ProcedimientosListNew.contains(hospAnexo3ProcedimientosListOldHospAnexo3Procedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospAnexo3Procedimientos " + hospAnexo3ProcedimientosListOldHospAnexo3Procedimientos + " since its idanexo field is not nullable.");
                }
            }
            for (HospAnexo3Cie10 hospAnexo3Cie10ListOldHospAnexo3Cie10 : hospAnexo3Cie10ListOld) {
                if (!hospAnexo3Cie10ListNew.contains(hospAnexo3Cie10ListOldHospAnexo3Cie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospAnexo3Cie10 " + hospAnexo3Cie10ListOldHospAnexo3Cie10 + " since its idanexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospAnexo3Procedimientos> attachedHospAnexo3ProcedimientosListNew = new ArrayList<HospAnexo3Procedimientos>();
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListNewHospAnexo3ProcedimientosToAttach : hospAnexo3ProcedimientosListNew) {
                hospAnexo3ProcedimientosListNewHospAnexo3ProcedimientosToAttach = em.getReference(hospAnexo3ProcedimientosListNewHospAnexo3ProcedimientosToAttach.getClass(), hospAnexo3ProcedimientosListNewHospAnexo3ProcedimientosToAttach.getId());
                attachedHospAnexo3ProcedimientosListNew.add(hospAnexo3ProcedimientosListNewHospAnexo3ProcedimientosToAttach);
            }
            hospAnexo3ProcedimientosListNew = attachedHospAnexo3ProcedimientosListNew;
            hospAnexo3.setHospAnexo3ProcedimientosList(hospAnexo3ProcedimientosListNew);
            List<HospAnexo3Cie10> attachedHospAnexo3Cie10ListNew = new ArrayList<HospAnexo3Cie10>();
            for (HospAnexo3Cie10 hospAnexo3Cie10ListNewHospAnexo3Cie10ToAttach : hospAnexo3Cie10ListNew) {
                hospAnexo3Cie10ListNewHospAnexo3Cie10ToAttach = em.getReference(hospAnexo3Cie10ListNewHospAnexo3Cie10ToAttach.getClass(), hospAnexo3Cie10ListNewHospAnexo3Cie10ToAttach.getId());
                attachedHospAnexo3Cie10ListNew.add(hospAnexo3Cie10ListNewHospAnexo3Cie10ToAttach);
            }
            hospAnexo3Cie10ListNew = attachedHospAnexo3Cie10ListNew;
            hospAnexo3.setHospAnexo3Cie10List(hospAnexo3Cie10ListNew);
            hospAnexo3 = em.merge(hospAnexo3);
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos : hospAnexo3ProcedimientosListNew) {
                if (!hospAnexo3ProcedimientosListOld.contains(hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos)) {
                    HospAnexo3 oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos = hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos.getIdanexo();
                    hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos.setIdanexo(hospAnexo3);
                    hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos = em.merge(hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos);
                    if (oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos != null && !oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos.equals(hospAnexo3)) {
                        oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos.getHospAnexo3ProcedimientosList().remove(hospAnexo3ProcedimientosListNewHospAnexo3Procedimientos);
                        oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos = em.merge(oldIdanexoOfHospAnexo3ProcedimientosListNewHospAnexo3Procedimientos);
                    }
                }
            }
            for (HospAnexo3Cie10 hospAnexo3Cie10ListNewHospAnexo3Cie10 : hospAnexo3Cie10ListNew) {
                if (!hospAnexo3Cie10ListOld.contains(hospAnexo3Cie10ListNewHospAnexo3Cie10)) {
                    HospAnexo3 oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10 = hospAnexo3Cie10ListNewHospAnexo3Cie10.getIdanexo();
                    hospAnexo3Cie10ListNewHospAnexo3Cie10.setIdanexo(hospAnexo3);
                    hospAnexo3Cie10ListNewHospAnexo3Cie10 = em.merge(hospAnexo3Cie10ListNewHospAnexo3Cie10);
                    if (oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10 != null && !oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10.equals(hospAnexo3)) {
                        oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10.getHospAnexo3Cie10List().remove(hospAnexo3Cie10ListNewHospAnexo3Cie10);
                        oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10 = em.merge(oldIdanexoOfHospAnexo3Cie10ListNewHospAnexo3Cie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospAnexo3.getId();
                if (findHospAnexo3(id) == null) {
                    throw new NonexistentEntityException("The hospAnexo3 with id " + id + " no longer exists.");
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
            HospAnexo3 hospAnexo3;
            try {
                hospAnexo3 = em.getReference(HospAnexo3.class, id);
                hospAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospAnexo3Procedimientos> hospAnexo3ProcedimientosListOrphanCheck = hospAnexo3.getHospAnexo3ProcedimientosList();
            for (HospAnexo3Procedimientos hospAnexo3ProcedimientosListOrphanCheckHospAnexo3Procedimientos : hospAnexo3ProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospAnexo3 (" + hospAnexo3 + ") cannot be destroyed since the HospAnexo3Procedimientos " + hospAnexo3ProcedimientosListOrphanCheckHospAnexo3Procedimientos + " in its hospAnexo3ProcedimientosList field has a non-nullable idanexo field.");
            }
            List<HospAnexo3Cie10> hospAnexo3Cie10ListOrphanCheck = hospAnexo3.getHospAnexo3Cie10List();
            for (HospAnexo3Cie10 hospAnexo3Cie10ListOrphanCheckHospAnexo3Cie10 : hospAnexo3Cie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospAnexo3 (" + hospAnexo3 + ") cannot be destroyed since the HospAnexo3Cie10 " + hospAnexo3Cie10ListOrphanCheckHospAnexo3Cie10 + " in its hospAnexo3Cie10List field has a non-nullable idanexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospAnexo3> findHospAnexo3Entities() {
        return findHospAnexo3Entities(true, -1, -1);
    }

    public List<HospAnexo3> findHospAnexo3Entities(int maxResults, int firstResult) {
        return findHospAnexo3Entities(false, maxResults, firstResult);
    }

    private List<HospAnexo3> findHospAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospAnexo3.class));
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

    public HospAnexo3 findHospAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospAnexo3> rt = cq.from(HospAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
