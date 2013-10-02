/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SumSuministro;
import entidades.InvDatosCompra;
import entidades.InvSumGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InvSumGeneralJpaController implements Serializable {

    public InvSumGeneralJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvSumGeneral invSumGeneral) {
        if (invSumGeneral.getInvDatosCompraList() == null) {
            invSumGeneral.setInvDatosCompraList(new ArrayList<InvDatosCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumSuministro idSuministro = invSumGeneral.getIdSuministro();
            if (idSuministro != null) {
                idSuministro = em.getReference(idSuministro.getClass(), idSuministro.getId());
                invSumGeneral.setIdSuministro(idSuministro);
            }
            List<InvDatosCompra> attachedInvDatosCompraList = new ArrayList<InvDatosCompra>();
            for (InvDatosCompra invDatosCompraListInvDatosCompraToAttach : invSumGeneral.getInvDatosCompraList()) {
                invDatosCompraListInvDatosCompraToAttach = em.getReference(invDatosCompraListInvDatosCompraToAttach.getClass(), invDatosCompraListInvDatosCompraToAttach.getId());
                attachedInvDatosCompraList.add(invDatosCompraListInvDatosCompraToAttach);
            }
            invSumGeneral.setInvDatosCompraList(attachedInvDatosCompraList);
            em.persist(invSumGeneral);
            if (idSuministro != null) {
                idSuministro.getInvSumGeneralList().add(invSumGeneral);
                idSuministro = em.merge(idSuministro);
            }
            for (InvDatosCompra invDatosCompraListInvDatosCompra : invSumGeneral.getInvDatosCompraList()) {
                InvSumGeneral oldIdSumgeneralOfInvDatosCompraListInvDatosCompra = invDatosCompraListInvDatosCompra.getIdSumgeneral();
                invDatosCompraListInvDatosCompra.setIdSumgeneral(invSumGeneral);
                invDatosCompraListInvDatosCompra = em.merge(invDatosCompraListInvDatosCompra);
                if (oldIdSumgeneralOfInvDatosCompraListInvDatosCompra != null) {
                    oldIdSumgeneralOfInvDatosCompraListInvDatosCompra.getInvDatosCompraList().remove(invDatosCompraListInvDatosCompra);
                    oldIdSumgeneralOfInvDatosCompraListInvDatosCompra = em.merge(oldIdSumgeneralOfInvDatosCompraListInvDatosCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvSumGeneral invSumGeneral) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvSumGeneral persistentInvSumGeneral = em.find(InvSumGeneral.class, invSumGeneral.getId());
            SumSuministro idSuministroOld = persistentInvSumGeneral.getIdSuministro();
            SumSuministro idSuministroNew = invSumGeneral.getIdSuministro();
            List<InvDatosCompra> invDatosCompraListOld = persistentInvSumGeneral.getInvDatosCompraList();
            List<InvDatosCompra> invDatosCompraListNew = invSumGeneral.getInvDatosCompraList();
            List<String> illegalOrphanMessages = null;
            for (InvDatosCompra invDatosCompraListOldInvDatosCompra : invDatosCompraListOld) {
                if (!invDatosCompraListNew.contains(invDatosCompraListOldInvDatosCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvDatosCompra " + invDatosCompraListOldInvDatosCompra + " since its idSumgeneral field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idSuministroNew != null) {
                idSuministroNew = em.getReference(idSuministroNew.getClass(), idSuministroNew.getId());
                invSumGeneral.setIdSuministro(idSuministroNew);
            }
            List<InvDatosCompra> attachedInvDatosCompraListNew = new ArrayList<InvDatosCompra>();
            for (InvDatosCompra invDatosCompraListNewInvDatosCompraToAttach : invDatosCompraListNew) {
                invDatosCompraListNewInvDatosCompraToAttach = em.getReference(invDatosCompraListNewInvDatosCompraToAttach.getClass(), invDatosCompraListNewInvDatosCompraToAttach.getId());
                attachedInvDatosCompraListNew.add(invDatosCompraListNewInvDatosCompraToAttach);
            }
            invDatosCompraListNew = attachedInvDatosCompraListNew;
            invSumGeneral.setInvDatosCompraList(invDatosCompraListNew);
            invSumGeneral = em.merge(invSumGeneral);
            if (idSuministroOld != null && !idSuministroOld.equals(idSuministroNew)) {
                idSuministroOld.getInvSumGeneralList().remove(invSumGeneral);
                idSuministroOld = em.merge(idSuministroOld);
            }
            if (idSuministroNew != null && !idSuministroNew.equals(idSuministroOld)) {
                idSuministroNew.getInvSumGeneralList().add(invSumGeneral);
                idSuministroNew = em.merge(idSuministroNew);
            }
            for (InvDatosCompra invDatosCompraListNewInvDatosCompra : invDatosCompraListNew) {
                if (!invDatosCompraListOld.contains(invDatosCompraListNewInvDatosCompra)) {
                    InvSumGeneral oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra = invDatosCompraListNewInvDatosCompra.getIdSumgeneral();
                    invDatosCompraListNewInvDatosCompra.setIdSumgeneral(invSumGeneral);
                    invDatosCompraListNewInvDatosCompra = em.merge(invDatosCompraListNewInvDatosCompra);
                    if (oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra != null && !oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra.equals(invSumGeneral)) {
                        oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra.getInvDatosCompraList().remove(invDatosCompraListNewInvDatosCompra);
                        oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra = em.merge(oldIdSumgeneralOfInvDatosCompraListNewInvDatosCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invSumGeneral.getId();
                if (findInvSumGeneral(id) == null) {
                    throw new NonexistentEntityException("The invSumGeneral with id " + id + " no longer exists.");
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
            InvSumGeneral invSumGeneral;
            try {
                invSumGeneral = em.getReference(InvSumGeneral.class, id);
                invSumGeneral.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invSumGeneral with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InvDatosCompra> invDatosCompraListOrphanCheck = invSumGeneral.getInvDatosCompraList();
            for (InvDatosCompra invDatosCompraListOrphanCheckInvDatosCompra : invDatosCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InvSumGeneral (" + invSumGeneral + ") cannot be destroyed since the InvDatosCompra " + invDatosCompraListOrphanCheckInvDatosCompra + " in its invDatosCompraList field has a non-nullable idSumgeneral field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SumSuministro idSuministro = invSumGeneral.getIdSuministro();
            if (idSuministro != null) {
                idSuministro.getInvSumGeneralList().remove(invSumGeneral);
                idSuministro = em.merge(idSuministro);
            }
            em.remove(invSumGeneral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvSumGeneral> findInvSumGeneralEntities() {
        return findInvSumGeneralEntities(true, -1, -1);
    }

    public List<InvSumGeneral> findInvSumGeneralEntities(int maxResults, int firstResult) {
        return findInvSumGeneralEntities(false, maxResults, firstResult);
    }

    private List<InvSumGeneral> findInvSumGeneralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvSumGeneral.class));
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

    public InvSumGeneral findInvSumGeneral(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvSumGeneral.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvSumGeneralCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvSumGeneral> rt = cq.from(InvSumGeneral.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
       //Codigo no Auto-generado
   public Long CountExistenciasInv(SumSuministro sum){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(i) FROM InvSumGeneral i WHERE i.idSuministro = :sum AND i.cantidad > 0");
            q.setParameter("sum",sum);
            return (Long)q.getSingleResult();
        } finally {
            em.close();
        }
   }
    
}
