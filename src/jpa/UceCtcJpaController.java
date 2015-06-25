/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceCtcCie10;
import java.util.HashSet;
import java.util.Set;
import entidades_EJB.UceCtcProcedimientos;
import entidades_EJB.UceCtcMedicamento;
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
public class UceCtcJpaController implements Serializable {

    public UceCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCtc uceCtc) {
        if (uceCtc.getUceCtcCie10Set() == null) {
            uceCtc.setUceCtcCie10Set(new HashSet<UceCtcCie10>());
        }
        if (uceCtc.getUceCtcProcedimientosSet() == null) {
            uceCtc.setUceCtcProcedimientosSet(new HashSet<UceCtcProcedimientos>());
        }
        if (uceCtc.getUceCtcMedicamentoSet() == null) {
            uceCtc.setUceCtcMedicamentoSet(new HashSet<UceCtcMedicamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<UceCtcCie10> attachedUceCtcCie10Set = new HashSet<UceCtcCie10>();
            for (UceCtcCie10 uceCtcCie10SetUceCtcCie10ToAttach : uceCtc.getUceCtcCie10Set()) {
                uceCtcCie10SetUceCtcCie10ToAttach = em.getReference(uceCtcCie10SetUceCtcCie10ToAttach.getClass(), uceCtcCie10SetUceCtcCie10ToAttach.getId());
                attachedUceCtcCie10Set.add(uceCtcCie10SetUceCtcCie10ToAttach);
            }
            uceCtc.setUceCtcCie10Set(attachedUceCtcCie10Set);
            Set<UceCtcProcedimientos> attachedUceCtcProcedimientosSet = new HashSet<UceCtcProcedimientos>();
            for (UceCtcProcedimientos uceCtcProcedimientosSetUceCtcProcedimientosToAttach : uceCtc.getUceCtcProcedimientosSet()) {
                uceCtcProcedimientosSetUceCtcProcedimientosToAttach = em.getReference(uceCtcProcedimientosSetUceCtcProcedimientosToAttach.getClass(), uceCtcProcedimientosSetUceCtcProcedimientosToAttach.getId());
                attachedUceCtcProcedimientosSet.add(uceCtcProcedimientosSetUceCtcProcedimientosToAttach);
            }
            uceCtc.setUceCtcProcedimientosSet(attachedUceCtcProcedimientosSet);
            Set<UceCtcMedicamento> attachedUceCtcMedicamentoSet = new HashSet<UceCtcMedicamento>();
            for (UceCtcMedicamento uceCtcMedicamentoSetUceCtcMedicamentoToAttach : uceCtc.getUceCtcMedicamentoSet()) {
                uceCtcMedicamentoSetUceCtcMedicamentoToAttach = em.getReference(uceCtcMedicamentoSetUceCtcMedicamentoToAttach.getClass(), uceCtcMedicamentoSetUceCtcMedicamentoToAttach.getId());
                attachedUceCtcMedicamentoSet.add(uceCtcMedicamentoSetUceCtcMedicamentoToAttach);
            }
            uceCtc.setUceCtcMedicamentoSet(attachedUceCtcMedicamentoSet);
            em.persist(uceCtc);
            for (UceCtcCie10 uceCtcCie10SetUceCtcCie10 : uceCtc.getUceCtcCie10Set()) {
                UceCtc oldIdctcOfUceCtcCie10SetUceCtcCie10 = uceCtcCie10SetUceCtcCie10.getIdctc();
                uceCtcCie10SetUceCtcCie10.setIdctc(uceCtc);
                uceCtcCie10SetUceCtcCie10 = em.merge(uceCtcCie10SetUceCtcCie10);
                if (oldIdctcOfUceCtcCie10SetUceCtcCie10 != null) {
                    oldIdctcOfUceCtcCie10SetUceCtcCie10.getUceCtcCie10Set().remove(uceCtcCie10SetUceCtcCie10);
                    oldIdctcOfUceCtcCie10SetUceCtcCie10 = em.merge(oldIdctcOfUceCtcCie10SetUceCtcCie10);
                }
            }
            for (UceCtcProcedimientos uceCtcProcedimientosSetUceCtcProcedimientos : uceCtc.getUceCtcProcedimientosSet()) {
                UceCtc oldIdctcOfUceCtcProcedimientosSetUceCtcProcedimientos = uceCtcProcedimientosSetUceCtcProcedimientos.getIdctc();
                uceCtcProcedimientosSetUceCtcProcedimientos.setIdctc(uceCtc);
                uceCtcProcedimientosSetUceCtcProcedimientos = em.merge(uceCtcProcedimientosSetUceCtcProcedimientos);
                if (oldIdctcOfUceCtcProcedimientosSetUceCtcProcedimientos != null) {
                    oldIdctcOfUceCtcProcedimientosSetUceCtcProcedimientos.getUceCtcProcedimientosSet().remove(uceCtcProcedimientosSetUceCtcProcedimientos);
                    oldIdctcOfUceCtcProcedimientosSetUceCtcProcedimientos = em.merge(oldIdctcOfUceCtcProcedimientosSetUceCtcProcedimientos);
                }
            }
            for (UceCtcMedicamento uceCtcMedicamentoSetUceCtcMedicamento : uceCtc.getUceCtcMedicamentoSet()) {
                UceCtc oldIdctcOfUceCtcMedicamentoSetUceCtcMedicamento = uceCtcMedicamentoSetUceCtcMedicamento.getIdctc();
                uceCtcMedicamentoSetUceCtcMedicamento.setIdctc(uceCtc);
                uceCtcMedicamentoSetUceCtcMedicamento = em.merge(uceCtcMedicamentoSetUceCtcMedicamento);
                if (oldIdctcOfUceCtcMedicamentoSetUceCtcMedicamento != null) {
                    oldIdctcOfUceCtcMedicamentoSetUceCtcMedicamento.getUceCtcMedicamentoSet().remove(uceCtcMedicamentoSetUceCtcMedicamento);
                    oldIdctcOfUceCtcMedicamentoSetUceCtcMedicamento = em.merge(oldIdctcOfUceCtcMedicamentoSetUceCtcMedicamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCtc uceCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtc persistentUceCtc = em.find(UceCtc.class, uceCtc.getId());
            Set<UceCtcCie10> uceCtcCie10SetOld = persistentUceCtc.getUceCtcCie10Set();
            Set<UceCtcCie10> uceCtcCie10SetNew = uceCtc.getUceCtcCie10Set();
            Set<UceCtcProcedimientos> uceCtcProcedimientosSetOld = persistentUceCtc.getUceCtcProcedimientosSet();
            Set<UceCtcProcedimientos> uceCtcProcedimientosSetNew = uceCtc.getUceCtcProcedimientosSet();
            Set<UceCtcMedicamento> uceCtcMedicamentoSetOld = persistentUceCtc.getUceCtcMedicamentoSet();
            Set<UceCtcMedicamento> uceCtcMedicamentoSetNew = uceCtc.getUceCtcMedicamentoSet();
            List<String> illegalOrphanMessages = null;
            for (UceCtcCie10 uceCtcCie10SetOldUceCtcCie10 : uceCtcCie10SetOld) {
                if (!uceCtcCie10SetNew.contains(uceCtcCie10SetOldUceCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceCtcCie10 " + uceCtcCie10SetOldUceCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (UceCtcProcedimientos uceCtcProcedimientosSetOldUceCtcProcedimientos : uceCtcProcedimientosSetOld) {
                if (!uceCtcProcedimientosSetNew.contains(uceCtcProcedimientosSetOldUceCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceCtcProcedimientos " + uceCtcProcedimientosSetOldUceCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            for (UceCtcMedicamento uceCtcMedicamentoSetOldUceCtcMedicamento : uceCtcMedicamentoSetOld) {
                if (!uceCtcMedicamentoSetNew.contains(uceCtcMedicamentoSetOldUceCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceCtcMedicamento " + uceCtcMedicamentoSetOldUceCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<UceCtcCie10> attachedUceCtcCie10SetNew = new HashSet<UceCtcCie10>();
            for (UceCtcCie10 uceCtcCie10SetNewUceCtcCie10ToAttach : uceCtcCie10SetNew) {
                uceCtcCie10SetNewUceCtcCie10ToAttach = em.getReference(uceCtcCie10SetNewUceCtcCie10ToAttach.getClass(), uceCtcCie10SetNewUceCtcCie10ToAttach.getId());
                attachedUceCtcCie10SetNew.add(uceCtcCie10SetNewUceCtcCie10ToAttach);
            }
            uceCtcCie10SetNew = attachedUceCtcCie10SetNew;
            uceCtc.setUceCtcCie10Set(uceCtcCie10SetNew);
            Set<UceCtcProcedimientos> attachedUceCtcProcedimientosSetNew = new HashSet<UceCtcProcedimientos>();
            for (UceCtcProcedimientos uceCtcProcedimientosSetNewUceCtcProcedimientosToAttach : uceCtcProcedimientosSetNew) {
                uceCtcProcedimientosSetNewUceCtcProcedimientosToAttach = em.getReference(uceCtcProcedimientosSetNewUceCtcProcedimientosToAttach.getClass(), uceCtcProcedimientosSetNewUceCtcProcedimientosToAttach.getId());
                attachedUceCtcProcedimientosSetNew.add(uceCtcProcedimientosSetNewUceCtcProcedimientosToAttach);
            }
            uceCtcProcedimientosSetNew = attachedUceCtcProcedimientosSetNew;
            uceCtc.setUceCtcProcedimientosSet(uceCtcProcedimientosSetNew);
            Set<UceCtcMedicamento> attachedUceCtcMedicamentoSetNew = new HashSet<UceCtcMedicamento>();
            for (UceCtcMedicamento uceCtcMedicamentoSetNewUceCtcMedicamentoToAttach : uceCtcMedicamentoSetNew) {
                uceCtcMedicamentoSetNewUceCtcMedicamentoToAttach = em.getReference(uceCtcMedicamentoSetNewUceCtcMedicamentoToAttach.getClass(), uceCtcMedicamentoSetNewUceCtcMedicamentoToAttach.getId());
                attachedUceCtcMedicamentoSetNew.add(uceCtcMedicamentoSetNewUceCtcMedicamentoToAttach);
            }
            uceCtcMedicamentoSetNew = attachedUceCtcMedicamentoSetNew;
            uceCtc.setUceCtcMedicamentoSet(uceCtcMedicamentoSetNew);
            uceCtc = em.merge(uceCtc);
            for (UceCtcCie10 uceCtcCie10SetNewUceCtcCie10 : uceCtcCie10SetNew) {
                if (!uceCtcCie10SetOld.contains(uceCtcCie10SetNewUceCtcCie10)) {
                    UceCtc oldIdctcOfUceCtcCie10SetNewUceCtcCie10 = uceCtcCie10SetNewUceCtcCie10.getIdctc();
                    uceCtcCie10SetNewUceCtcCie10.setIdctc(uceCtc);
                    uceCtcCie10SetNewUceCtcCie10 = em.merge(uceCtcCie10SetNewUceCtcCie10);
                    if (oldIdctcOfUceCtcCie10SetNewUceCtcCie10 != null && !oldIdctcOfUceCtcCie10SetNewUceCtcCie10.equals(uceCtc)) {
                        oldIdctcOfUceCtcCie10SetNewUceCtcCie10.getUceCtcCie10Set().remove(uceCtcCie10SetNewUceCtcCie10);
                        oldIdctcOfUceCtcCie10SetNewUceCtcCie10 = em.merge(oldIdctcOfUceCtcCie10SetNewUceCtcCie10);
                    }
                }
            }
            for (UceCtcProcedimientos uceCtcProcedimientosSetNewUceCtcProcedimientos : uceCtcProcedimientosSetNew) {
                if (!uceCtcProcedimientosSetOld.contains(uceCtcProcedimientosSetNewUceCtcProcedimientos)) {
                    UceCtc oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos = uceCtcProcedimientosSetNewUceCtcProcedimientos.getIdctc();
                    uceCtcProcedimientosSetNewUceCtcProcedimientos.setIdctc(uceCtc);
                    uceCtcProcedimientosSetNewUceCtcProcedimientos = em.merge(uceCtcProcedimientosSetNewUceCtcProcedimientos);
                    if (oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos != null && !oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos.equals(uceCtc)) {
                        oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos.getUceCtcProcedimientosSet().remove(uceCtcProcedimientosSetNewUceCtcProcedimientos);
                        oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos = em.merge(oldIdctcOfUceCtcProcedimientosSetNewUceCtcProcedimientos);
                    }
                }
            }
            for (UceCtcMedicamento uceCtcMedicamentoSetNewUceCtcMedicamento : uceCtcMedicamentoSetNew) {
                if (!uceCtcMedicamentoSetOld.contains(uceCtcMedicamentoSetNewUceCtcMedicamento)) {
                    UceCtc oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento = uceCtcMedicamentoSetNewUceCtcMedicamento.getIdctc();
                    uceCtcMedicamentoSetNewUceCtcMedicamento.setIdctc(uceCtc);
                    uceCtcMedicamentoSetNewUceCtcMedicamento = em.merge(uceCtcMedicamentoSetNewUceCtcMedicamento);
                    if (oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento != null && !oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento.equals(uceCtc)) {
                        oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento.getUceCtcMedicamentoSet().remove(uceCtcMedicamentoSetNewUceCtcMedicamento);
                        oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento = em.merge(oldIdctcOfUceCtcMedicamentoSetNewUceCtcMedicamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCtc.getId();
                if (findUceCtc(id) == null) {
                    throw new NonexistentEntityException("The uceCtc with id " + id + " no longer exists.");
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
            UceCtc uceCtc;
            try {
                uceCtc = em.getReference(UceCtc.class, id);
                uceCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<UceCtcCie10> uceCtcCie10SetOrphanCheck = uceCtc.getUceCtcCie10Set();
            for (UceCtcCie10 uceCtcCie10SetOrphanCheckUceCtcCie10 : uceCtcCie10SetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceCtc (" + uceCtc + ") cannot be destroyed since the UceCtcCie10 " + uceCtcCie10SetOrphanCheckUceCtcCie10 + " in its uceCtcCie10Set field has a non-nullable idctc field.");
            }
            Set<UceCtcProcedimientos> uceCtcProcedimientosSetOrphanCheck = uceCtc.getUceCtcProcedimientosSet();
            for (UceCtcProcedimientos uceCtcProcedimientosSetOrphanCheckUceCtcProcedimientos : uceCtcProcedimientosSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceCtc (" + uceCtc + ") cannot be destroyed since the UceCtcProcedimientos " + uceCtcProcedimientosSetOrphanCheckUceCtcProcedimientos + " in its uceCtcProcedimientosSet field has a non-nullable idctc field.");
            }
            Set<UceCtcMedicamento> uceCtcMedicamentoSetOrphanCheck = uceCtc.getUceCtcMedicamentoSet();
            for (UceCtcMedicamento uceCtcMedicamentoSetOrphanCheckUceCtcMedicamento : uceCtcMedicamentoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceCtc (" + uceCtc + ") cannot be destroyed since the UceCtcMedicamento " + uceCtcMedicamentoSetOrphanCheckUceCtcMedicamento + " in its uceCtcMedicamentoSet field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCtc> findUceCtcEntities() {
        return findUceCtcEntities(true, -1, -1);
    }

    public List<UceCtc> findUceCtcEntities(int maxResults, int firstResult) {
        return findUceCtcEntities(false, maxResults, firstResult);
    }

    private List<UceCtc> findUceCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCtc.class));
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

    public UceCtc findUceCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCtc> rt = cq.from(UceCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
