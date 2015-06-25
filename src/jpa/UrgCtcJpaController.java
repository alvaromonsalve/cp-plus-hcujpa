/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UrgCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgCtcCie10;
import java.util.HashSet;
import java.util.Set;
import entidades_EJB.UrgCtcProcedimientos;
import entidades_EJB.UrgCtcMedicamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgCtcJpaController implements Serializable {

    public UrgCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgCtc urgCtc) {
        if (urgCtc.getUrgCtcCie10Set() == null) {
            urgCtc.setUrgCtcCie10Set(new HashSet<UrgCtcCie10>());
        }
        if (urgCtc.getUrgCtcProcedimientosSet() == null) {
            urgCtc.setUrgCtcProcedimientosSet(new HashSet<UrgCtcProcedimientos>());
        }
        if (urgCtc.getUrgCtcMedicamentoSet() == null) {
            urgCtc.setUrgCtcMedicamentoSet(new HashSet<UrgCtcMedicamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<UrgCtcCie10> attachedUrgCtcCie10Set = new HashSet<UrgCtcCie10>();
            for (UrgCtcCie10 urgCtcCie10SetUrgCtcCie10ToAttach : urgCtc.getUrgCtcCie10Set()) {
                urgCtcCie10SetUrgCtcCie10ToAttach = em.getReference(urgCtcCie10SetUrgCtcCie10ToAttach.getClass(), urgCtcCie10SetUrgCtcCie10ToAttach.getId());
                attachedUrgCtcCie10Set.add(urgCtcCie10SetUrgCtcCie10ToAttach);
            }
            urgCtc.setUrgCtcCie10Set(attachedUrgCtcCie10Set);
            Set<UrgCtcProcedimientos> attachedUrgCtcProcedimientosSet = new HashSet<UrgCtcProcedimientos>();
            for (UrgCtcProcedimientos urgCtcProcedimientosSetUrgCtcProcedimientosToAttach : urgCtc.getUrgCtcProcedimientosSet()) {
                urgCtcProcedimientosSetUrgCtcProcedimientosToAttach = em.getReference(urgCtcProcedimientosSetUrgCtcProcedimientosToAttach.getClass(), urgCtcProcedimientosSetUrgCtcProcedimientosToAttach.getId());
                attachedUrgCtcProcedimientosSet.add(urgCtcProcedimientosSetUrgCtcProcedimientosToAttach);
            }
            urgCtc.setUrgCtcProcedimientosSet(attachedUrgCtcProcedimientosSet);
            Set<UrgCtcMedicamento> attachedUrgCtcMedicamentoSet = new HashSet<UrgCtcMedicamento>();
            for (UrgCtcMedicamento urgCtcMedicamentoSetUrgCtcMedicamentoToAttach : urgCtc.getUrgCtcMedicamentoSet()) {
                urgCtcMedicamentoSetUrgCtcMedicamentoToAttach = em.getReference(urgCtcMedicamentoSetUrgCtcMedicamentoToAttach.getClass(), urgCtcMedicamentoSetUrgCtcMedicamentoToAttach.getId());
                attachedUrgCtcMedicamentoSet.add(urgCtcMedicamentoSetUrgCtcMedicamentoToAttach);
            }
            urgCtc.setUrgCtcMedicamentoSet(attachedUrgCtcMedicamentoSet);
            em.persist(urgCtc);
            for (UrgCtcCie10 urgCtcCie10SetUrgCtcCie10 : urgCtc.getUrgCtcCie10Set()) {
                UrgCtc oldIdctcOfUrgCtcCie10SetUrgCtcCie10 = urgCtcCie10SetUrgCtcCie10.getIdctc();
                urgCtcCie10SetUrgCtcCie10.setIdctc(urgCtc);
                urgCtcCie10SetUrgCtcCie10 = em.merge(urgCtcCie10SetUrgCtcCie10);
                if (oldIdctcOfUrgCtcCie10SetUrgCtcCie10 != null) {
                    oldIdctcOfUrgCtcCie10SetUrgCtcCie10.getUrgCtcCie10Set().remove(urgCtcCie10SetUrgCtcCie10);
                    oldIdctcOfUrgCtcCie10SetUrgCtcCie10 = em.merge(oldIdctcOfUrgCtcCie10SetUrgCtcCie10);
                }
            }
            for (UrgCtcProcedimientos urgCtcProcedimientosSetUrgCtcProcedimientos : urgCtc.getUrgCtcProcedimientosSet()) {
                UrgCtc oldIdctcOfUrgCtcProcedimientosSetUrgCtcProcedimientos = urgCtcProcedimientosSetUrgCtcProcedimientos.getIdctc();
                urgCtcProcedimientosSetUrgCtcProcedimientos.setIdctc(urgCtc);
                urgCtcProcedimientosSetUrgCtcProcedimientos = em.merge(urgCtcProcedimientosSetUrgCtcProcedimientos);
                if (oldIdctcOfUrgCtcProcedimientosSetUrgCtcProcedimientos != null) {
                    oldIdctcOfUrgCtcProcedimientosSetUrgCtcProcedimientos.getUrgCtcProcedimientosSet().remove(urgCtcProcedimientosSetUrgCtcProcedimientos);
                    oldIdctcOfUrgCtcProcedimientosSetUrgCtcProcedimientos = em.merge(oldIdctcOfUrgCtcProcedimientosSetUrgCtcProcedimientos);
                }
            }
            for (UrgCtcMedicamento urgCtcMedicamentoSetUrgCtcMedicamento : urgCtc.getUrgCtcMedicamentoSet()) {
                UrgCtc oldIdctcOfUrgCtcMedicamentoSetUrgCtcMedicamento = urgCtcMedicamentoSetUrgCtcMedicamento.getIdctc();
                urgCtcMedicamentoSetUrgCtcMedicamento.setIdctc(urgCtc);
                urgCtcMedicamentoSetUrgCtcMedicamento = em.merge(urgCtcMedicamentoSetUrgCtcMedicamento);
                if (oldIdctcOfUrgCtcMedicamentoSetUrgCtcMedicamento != null) {
                    oldIdctcOfUrgCtcMedicamentoSetUrgCtcMedicamento.getUrgCtcMedicamentoSet().remove(urgCtcMedicamentoSetUrgCtcMedicamento);
                    oldIdctcOfUrgCtcMedicamentoSetUrgCtcMedicamento = em.merge(oldIdctcOfUrgCtcMedicamentoSetUrgCtcMedicamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgCtc urgCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtc persistentUrgCtc = em.find(UrgCtc.class, urgCtc.getId());
            Set<UrgCtcCie10> urgCtcCie10SetOld = persistentUrgCtc.getUrgCtcCie10Set();
            Set<UrgCtcCie10> urgCtcCie10SetNew = urgCtc.getUrgCtcCie10Set();
            Set<UrgCtcProcedimientos> urgCtcProcedimientosSetOld = persistentUrgCtc.getUrgCtcProcedimientosSet();
            Set<UrgCtcProcedimientos> urgCtcProcedimientosSetNew = urgCtc.getUrgCtcProcedimientosSet();
            Set<UrgCtcMedicamento> urgCtcMedicamentoSetOld = persistentUrgCtc.getUrgCtcMedicamentoSet();
            Set<UrgCtcMedicamento> urgCtcMedicamentoSetNew = urgCtc.getUrgCtcMedicamentoSet();
            List<String> illegalOrphanMessages = null;
            for (UrgCtcCie10 urgCtcCie10SetOldUrgCtcCie10 : urgCtcCie10SetOld) {
                if (!urgCtcCie10SetNew.contains(urgCtcCie10SetOldUrgCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgCtcCie10 " + urgCtcCie10SetOldUrgCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (UrgCtcProcedimientos urgCtcProcedimientosSetOldUrgCtcProcedimientos : urgCtcProcedimientosSetOld) {
                if (!urgCtcProcedimientosSetNew.contains(urgCtcProcedimientosSetOldUrgCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgCtcProcedimientos " + urgCtcProcedimientosSetOldUrgCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            for (UrgCtcMedicamento urgCtcMedicamentoSetOldUrgCtcMedicamento : urgCtcMedicamentoSetOld) {
                if (!urgCtcMedicamentoSetNew.contains(urgCtcMedicamentoSetOldUrgCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgCtcMedicamento " + urgCtcMedicamentoSetOldUrgCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<UrgCtcCie10> attachedUrgCtcCie10SetNew = new HashSet<UrgCtcCie10>();
            for (UrgCtcCie10 urgCtcCie10SetNewUrgCtcCie10ToAttach : urgCtcCie10SetNew) {
                urgCtcCie10SetNewUrgCtcCie10ToAttach = em.getReference(urgCtcCie10SetNewUrgCtcCie10ToAttach.getClass(), urgCtcCie10SetNewUrgCtcCie10ToAttach.getId());
                attachedUrgCtcCie10SetNew.add(urgCtcCie10SetNewUrgCtcCie10ToAttach);
            }
            urgCtcCie10SetNew = attachedUrgCtcCie10SetNew;
            urgCtc.setUrgCtcCie10Set(urgCtcCie10SetNew);
            Set<UrgCtcProcedimientos> attachedUrgCtcProcedimientosSetNew = new HashSet<UrgCtcProcedimientos>();
            for (UrgCtcProcedimientos urgCtcProcedimientosSetNewUrgCtcProcedimientosToAttach : urgCtcProcedimientosSetNew) {
                urgCtcProcedimientosSetNewUrgCtcProcedimientosToAttach = em.getReference(urgCtcProcedimientosSetNewUrgCtcProcedimientosToAttach.getClass(), urgCtcProcedimientosSetNewUrgCtcProcedimientosToAttach.getId());
                attachedUrgCtcProcedimientosSetNew.add(urgCtcProcedimientosSetNewUrgCtcProcedimientosToAttach);
            }
            urgCtcProcedimientosSetNew = attachedUrgCtcProcedimientosSetNew;
            urgCtc.setUrgCtcProcedimientosSet(urgCtcProcedimientosSetNew);
            Set<UrgCtcMedicamento> attachedUrgCtcMedicamentoSetNew = new HashSet<UrgCtcMedicamento>();
            for (UrgCtcMedicamento urgCtcMedicamentoSetNewUrgCtcMedicamentoToAttach : urgCtcMedicamentoSetNew) {
                urgCtcMedicamentoSetNewUrgCtcMedicamentoToAttach = em.getReference(urgCtcMedicamentoSetNewUrgCtcMedicamentoToAttach.getClass(), urgCtcMedicamentoSetNewUrgCtcMedicamentoToAttach.getId());
                attachedUrgCtcMedicamentoSetNew.add(urgCtcMedicamentoSetNewUrgCtcMedicamentoToAttach);
            }
            urgCtcMedicamentoSetNew = attachedUrgCtcMedicamentoSetNew;
            urgCtc.setUrgCtcMedicamentoSet(urgCtcMedicamentoSetNew);
            urgCtc = em.merge(urgCtc);
            for (UrgCtcCie10 urgCtcCie10SetNewUrgCtcCie10 : urgCtcCie10SetNew) {
                if (!urgCtcCie10SetOld.contains(urgCtcCie10SetNewUrgCtcCie10)) {
                    UrgCtc oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10 = urgCtcCie10SetNewUrgCtcCie10.getIdctc();
                    urgCtcCie10SetNewUrgCtcCie10.setIdctc(urgCtc);
                    urgCtcCie10SetNewUrgCtcCie10 = em.merge(urgCtcCie10SetNewUrgCtcCie10);
                    if (oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10 != null && !oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10.equals(urgCtc)) {
                        oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10.getUrgCtcCie10Set().remove(urgCtcCie10SetNewUrgCtcCie10);
                        oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10 = em.merge(oldIdctcOfUrgCtcCie10SetNewUrgCtcCie10);
                    }
                }
            }
            for (UrgCtcProcedimientos urgCtcProcedimientosSetNewUrgCtcProcedimientos : urgCtcProcedimientosSetNew) {
                if (!urgCtcProcedimientosSetOld.contains(urgCtcProcedimientosSetNewUrgCtcProcedimientos)) {
                    UrgCtc oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos = urgCtcProcedimientosSetNewUrgCtcProcedimientos.getIdctc();
                    urgCtcProcedimientosSetNewUrgCtcProcedimientos.setIdctc(urgCtc);
                    urgCtcProcedimientosSetNewUrgCtcProcedimientos = em.merge(urgCtcProcedimientosSetNewUrgCtcProcedimientos);
                    if (oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos != null && !oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos.equals(urgCtc)) {
                        oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos.getUrgCtcProcedimientosSet().remove(urgCtcProcedimientosSetNewUrgCtcProcedimientos);
                        oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos = em.merge(oldIdctcOfUrgCtcProcedimientosSetNewUrgCtcProcedimientos);
                    }
                }
            }
            for (UrgCtcMedicamento urgCtcMedicamentoSetNewUrgCtcMedicamento : urgCtcMedicamentoSetNew) {
                if (!urgCtcMedicamentoSetOld.contains(urgCtcMedicamentoSetNewUrgCtcMedicamento)) {
                    UrgCtc oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento = urgCtcMedicamentoSetNewUrgCtcMedicamento.getIdctc();
                    urgCtcMedicamentoSetNewUrgCtcMedicamento.setIdctc(urgCtc);
                    urgCtcMedicamentoSetNewUrgCtcMedicamento = em.merge(urgCtcMedicamentoSetNewUrgCtcMedicamento);
                    if (oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento != null && !oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento.equals(urgCtc)) {
                        oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento.getUrgCtcMedicamentoSet().remove(urgCtcMedicamentoSetNewUrgCtcMedicamento);
                        oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento = em.merge(oldIdctcOfUrgCtcMedicamentoSetNewUrgCtcMedicamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgCtc.getId();
                if (findUrgCtc(id) == null) {
                    throw new NonexistentEntityException("The urgCtc with id " + id + " no longer exists.");
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
            UrgCtc urgCtc;
            try {
                urgCtc = em.getReference(UrgCtc.class, id);
                urgCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<UrgCtcCie10> urgCtcCie10SetOrphanCheck = urgCtc.getUrgCtcCie10Set();
            for (UrgCtcCie10 urgCtcCie10SetOrphanCheckUrgCtcCie10 : urgCtcCie10SetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgCtc (" + urgCtc + ") cannot be destroyed since the UrgCtcCie10 " + urgCtcCie10SetOrphanCheckUrgCtcCie10 + " in its urgCtcCie10Set field has a non-nullable idctc field.");
            }
            Set<UrgCtcProcedimientos> urgCtcProcedimientosSetOrphanCheck = urgCtc.getUrgCtcProcedimientosSet();
            for (UrgCtcProcedimientos urgCtcProcedimientosSetOrphanCheckUrgCtcProcedimientos : urgCtcProcedimientosSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgCtc (" + urgCtc + ") cannot be destroyed since the UrgCtcProcedimientos " + urgCtcProcedimientosSetOrphanCheckUrgCtcProcedimientos + " in its urgCtcProcedimientosSet field has a non-nullable idctc field.");
            }
            Set<UrgCtcMedicamento> urgCtcMedicamentoSetOrphanCheck = urgCtc.getUrgCtcMedicamentoSet();
            for (UrgCtcMedicamento urgCtcMedicamentoSetOrphanCheckUrgCtcMedicamento : urgCtcMedicamentoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgCtc (" + urgCtc + ") cannot be destroyed since the UrgCtcMedicamento " + urgCtcMedicamentoSetOrphanCheckUrgCtcMedicamento + " in its urgCtcMedicamentoSet field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgCtc> findUrgCtcEntities() {
        return findUrgCtcEntities(true, -1, -1);
    }

    public List<UrgCtc> findUrgCtcEntities(int maxResults, int firstResult) {
        return findUrgCtcEntities(false, maxResults, firstResult);
    }

    private List<UrgCtc> findUrgCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgCtc.class));
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

    public UrgCtc findUrgCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgCtc> rt = cq.from(UrgCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
