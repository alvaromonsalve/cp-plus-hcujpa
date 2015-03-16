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
import entidades.InvSumGeneral;
import entidades.SumSuministro;
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
public class SumSuministroJpaController implements Serializable {

    public SumSuministroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumSuministro sumSuministro) {
        if (sumSuministro.getInvSumGeneralList() == null) {
            sumSuministro.setInvSumGeneralList(new ArrayList<InvSumGeneral>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InvSumGeneral> attachedInvSumGeneralList = new ArrayList<InvSumGeneral>();
            for (InvSumGeneral invSumGeneralListInvSumGeneralToAttach : sumSuministro.getInvSumGeneralList()) {
                invSumGeneralListInvSumGeneralToAttach = em.getReference(invSumGeneralListInvSumGeneralToAttach.getClass(), invSumGeneralListInvSumGeneralToAttach.getId());
                attachedInvSumGeneralList.add(invSumGeneralListInvSumGeneralToAttach);
            }
            sumSuministro.setInvSumGeneralList(attachedInvSumGeneralList);
            em.persist(sumSuministro);
            for (InvSumGeneral invSumGeneralListInvSumGeneral : sumSuministro.getInvSumGeneralList()) {
                SumSuministro oldIdSuministroOfInvSumGeneralListInvSumGeneral = invSumGeneralListInvSumGeneral.getIdSuministro();
                invSumGeneralListInvSumGeneral.setIdSuministro(sumSuministro);
                invSumGeneralListInvSumGeneral = em.merge(invSumGeneralListInvSumGeneral);
                if (oldIdSuministroOfInvSumGeneralListInvSumGeneral != null) {
                    oldIdSuministroOfInvSumGeneralListInvSumGeneral.getInvSumGeneralList().remove(invSumGeneralListInvSumGeneral);
                    oldIdSuministroOfInvSumGeneralListInvSumGeneral = em.merge(oldIdSuministroOfInvSumGeneralListInvSumGeneral);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumSuministro sumSuministro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumSuministro persistentSumSuministro = em.find(SumSuministro.class, sumSuministro.getId());
            List<InvSumGeneral> invSumGeneralListOld = persistentSumSuministro.getInvSumGeneralList();
            List<InvSumGeneral> invSumGeneralListNew = sumSuministro.getInvSumGeneralList();
            List<String> illegalOrphanMessages = null;
            for (InvSumGeneral invSumGeneralListOldInvSumGeneral : invSumGeneralListOld) {
                if (!invSumGeneralListNew.contains(invSumGeneralListOldInvSumGeneral)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvSumGeneral " + invSumGeneralListOldInvSumGeneral + " since its idSuministro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InvSumGeneral> attachedInvSumGeneralListNew = new ArrayList<InvSumGeneral>();
            for (InvSumGeneral invSumGeneralListNewInvSumGeneralToAttach : invSumGeneralListNew) {
                invSumGeneralListNewInvSumGeneralToAttach = em.getReference(invSumGeneralListNewInvSumGeneralToAttach.getClass(), invSumGeneralListNewInvSumGeneralToAttach.getId());
                attachedInvSumGeneralListNew.add(invSumGeneralListNewInvSumGeneralToAttach);
            }
            invSumGeneralListNew = attachedInvSumGeneralListNew;
            sumSuministro.setInvSumGeneralList(invSumGeneralListNew);
            sumSuministro = em.merge(sumSuministro);
            for (InvSumGeneral invSumGeneralListNewInvSumGeneral : invSumGeneralListNew) {
                if (!invSumGeneralListOld.contains(invSumGeneralListNewInvSumGeneral)) {
                    SumSuministro oldIdSuministroOfInvSumGeneralListNewInvSumGeneral = invSumGeneralListNewInvSumGeneral.getIdSuministro();
                    invSumGeneralListNewInvSumGeneral.setIdSuministro(sumSuministro);
                    invSumGeneralListNewInvSumGeneral = em.merge(invSumGeneralListNewInvSumGeneral);
                    if (oldIdSuministroOfInvSumGeneralListNewInvSumGeneral != null && !oldIdSuministroOfInvSumGeneralListNewInvSumGeneral.equals(sumSuministro)) {
                        oldIdSuministroOfInvSumGeneralListNewInvSumGeneral.getInvSumGeneralList().remove(invSumGeneralListNewInvSumGeneral);
                        oldIdSuministroOfInvSumGeneralListNewInvSumGeneral = em.merge(oldIdSuministroOfInvSumGeneralListNewInvSumGeneral);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumSuministro.getId();
                if (findSumSuministro(id) == null) {
                    throw new NonexistentEntityException("The sumSuministro with id " + id + " no longer exists.");
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
            SumSuministro sumSuministro;
            try {
                sumSuministro = em.getReference(SumSuministro.class, id);
                sumSuministro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumSuministro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InvSumGeneral> invSumGeneralListOrphanCheck = sumSuministro.getInvSumGeneralList();
            for (InvSumGeneral invSumGeneralListOrphanCheckInvSumGeneral : invSumGeneralListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SumSuministro (" + sumSuministro + ") cannot be destroyed since the InvSumGeneral " + invSumGeneralListOrphanCheckInvSumGeneral + " in its invSumGeneralList field has a non-nullable idSuministro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sumSuministro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumSuministro> findSumSuministroEntities() {
        return findSumSuministroEntities(true, -1, -1);
    }

    public List<SumSuministro> findSumSuministroEntities(int maxResults, int firstResult) {
        return findSumSuministroEntities(false, maxResults, firstResult);
    }

    private List<SumSuministro> findSumSuministroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumSuministro.class));
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

    public SumSuministro findSumSuministro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumSuministro.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumSuministroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumSuministro> rt = cq.from(SumSuministro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
       //Codigo no Auto-generado
   public List<SumSuministro> ListfindFiltro(String like){
        EntityManager em = getEntityManager();
        try {

            Query q = em.createQuery("SELECT s FROM SumSuministro s "
                    + "WHERE (s.idPricipioactivo.nombre LIKE :suministro OR s.suministro LIKE :suministro ) AND s.estado = '1' "
                    + "ORDER BY s.idPricipioactivo.nombre");
            q.setParameter("suministro", "%"+like+"%");
            return q.getResultList();
        } finally {
            em.close();
        }
   }
}