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
import entidades.SumAtcSubgrupofarmacologico;
import entidades.SumAtcPrincipioactivo;
import entidades.SumAtcSubgrupoquimico;
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
public class SumAtcSubgrupoquimicoJpaController implements Serializable {

    public SumAtcSubgrupoquimicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcSubgrupoquimico sumAtcSubgrupoquimico) {
        if (sumAtcSubgrupoquimico.getSumAtcPrincipioactivoList() == null) {
            sumAtcSubgrupoquimico.setSumAtcPrincipioactivoList(new ArrayList<SumAtcPrincipioactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSubgrupofarmacologico idSubgrupofarmacologico = sumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
            if (idSubgrupofarmacologico != null) {
                idSubgrupofarmacologico = em.getReference(idSubgrupofarmacologico.getClass(), idSubgrupofarmacologico.getId());
                sumAtcSubgrupoquimico.setIdSubgrupofarmacologico(idSubgrupofarmacologico);
            }
            List<SumAtcPrincipioactivo> attachedSumAtcPrincipioactivoList = new ArrayList<SumAtcPrincipioactivo>();
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListSumAtcPrincipioactivoToAttach : sumAtcSubgrupoquimico.getSumAtcPrincipioactivoList()) {
                sumAtcPrincipioactivoListSumAtcPrincipioactivoToAttach = em.getReference(sumAtcPrincipioactivoListSumAtcPrincipioactivoToAttach.getClass(), sumAtcPrincipioactivoListSumAtcPrincipioactivoToAttach.getId());
                attachedSumAtcPrincipioactivoList.add(sumAtcPrincipioactivoListSumAtcPrincipioactivoToAttach);
            }
            sumAtcSubgrupoquimico.setSumAtcPrincipioactivoList(attachedSumAtcPrincipioactivoList);
            em.persist(sumAtcSubgrupoquimico);
            if (idSubgrupofarmacologico != null) {
                idSubgrupofarmacologico.getSumAtcSubgrupoquimicoList().add(sumAtcSubgrupoquimico);
                idSubgrupofarmacologico = em.merge(idSubgrupofarmacologico);
            }
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListSumAtcPrincipioactivo : sumAtcSubgrupoquimico.getSumAtcPrincipioactivoList()) {
                SumAtcSubgrupoquimico oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListSumAtcPrincipioactivo = sumAtcPrincipioactivoListSumAtcPrincipioactivo.getIdSubgrupoquimico();
                sumAtcPrincipioactivoListSumAtcPrincipioactivo.setIdSubgrupoquimico(sumAtcSubgrupoquimico);
                sumAtcPrincipioactivoListSumAtcPrincipioactivo = em.merge(sumAtcPrincipioactivoListSumAtcPrincipioactivo);
                if (oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListSumAtcPrincipioactivo != null) {
                    oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListSumAtcPrincipioactivo.getSumAtcPrincipioactivoList().remove(sumAtcPrincipioactivoListSumAtcPrincipioactivo);
                    oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListSumAtcPrincipioactivo = em.merge(oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListSumAtcPrincipioactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcSubgrupoquimico sumAtcSubgrupoquimico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSubgrupoquimico persistentSumAtcSubgrupoquimico = em.find(SumAtcSubgrupoquimico.class, sumAtcSubgrupoquimico.getId());
            SumAtcSubgrupofarmacologico idSubgrupofarmacologicoOld = persistentSumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
            SumAtcSubgrupofarmacologico idSubgrupofarmacologicoNew = sumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
            List<SumAtcPrincipioactivo> sumAtcPrincipioactivoListOld = persistentSumAtcSubgrupoquimico.getSumAtcPrincipioactivoList();
            List<SumAtcPrincipioactivo> sumAtcPrincipioactivoListNew = sumAtcSubgrupoquimico.getSumAtcPrincipioactivoList();
            List<String> illegalOrphanMessages = null;
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListOldSumAtcPrincipioactivo : sumAtcPrincipioactivoListOld) {
                if (!sumAtcPrincipioactivoListNew.contains(sumAtcPrincipioactivoListOldSumAtcPrincipioactivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SumAtcPrincipioactivo " + sumAtcPrincipioactivoListOldSumAtcPrincipioactivo + " since its idSubgrupoquimico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idSubgrupofarmacologicoNew != null) {
                idSubgrupofarmacologicoNew = em.getReference(idSubgrupofarmacologicoNew.getClass(), idSubgrupofarmacologicoNew.getId());
                sumAtcSubgrupoquimico.setIdSubgrupofarmacologico(idSubgrupofarmacologicoNew);
            }
            List<SumAtcPrincipioactivo> attachedSumAtcPrincipioactivoListNew = new ArrayList<SumAtcPrincipioactivo>();
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListNewSumAtcPrincipioactivoToAttach : sumAtcPrincipioactivoListNew) {
                sumAtcPrincipioactivoListNewSumAtcPrincipioactivoToAttach = em.getReference(sumAtcPrincipioactivoListNewSumAtcPrincipioactivoToAttach.getClass(), sumAtcPrincipioactivoListNewSumAtcPrincipioactivoToAttach.getId());
                attachedSumAtcPrincipioactivoListNew.add(sumAtcPrincipioactivoListNewSumAtcPrincipioactivoToAttach);
            }
            sumAtcPrincipioactivoListNew = attachedSumAtcPrincipioactivoListNew;
            sumAtcSubgrupoquimico.setSumAtcPrincipioactivoList(sumAtcPrincipioactivoListNew);
            sumAtcSubgrupoquimico = em.merge(sumAtcSubgrupoquimico);
            if (idSubgrupofarmacologicoOld != null && !idSubgrupofarmacologicoOld.equals(idSubgrupofarmacologicoNew)) {
                idSubgrupofarmacologicoOld.getSumAtcSubgrupoquimicoList().remove(sumAtcSubgrupoquimico);
                idSubgrupofarmacologicoOld = em.merge(idSubgrupofarmacologicoOld);
            }
            if (idSubgrupofarmacologicoNew != null && !idSubgrupofarmacologicoNew.equals(idSubgrupofarmacologicoOld)) {
                idSubgrupofarmacologicoNew.getSumAtcSubgrupoquimicoList().add(sumAtcSubgrupoquimico);
                idSubgrupofarmacologicoNew = em.merge(idSubgrupofarmacologicoNew);
            }
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListNewSumAtcPrincipioactivo : sumAtcPrincipioactivoListNew) {
                if (!sumAtcPrincipioactivoListOld.contains(sumAtcPrincipioactivoListNewSumAtcPrincipioactivo)) {
                    SumAtcSubgrupoquimico oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo = sumAtcPrincipioactivoListNewSumAtcPrincipioactivo.getIdSubgrupoquimico();
                    sumAtcPrincipioactivoListNewSumAtcPrincipioactivo.setIdSubgrupoquimico(sumAtcSubgrupoquimico);
                    sumAtcPrincipioactivoListNewSumAtcPrincipioactivo = em.merge(sumAtcPrincipioactivoListNewSumAtcPrincipioactivo);
                    if (oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo != null && !oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo.equals(sumAtcSubgrupoquimico)) {
                        oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo.getSumAtcPrincipioactivoList().remove(sumAtcPrincipioactivoListNewSumAtcPrincipioactivo);
                        oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo = em.merge(oldIdSubgrupoquimicoOfSumAtcPrincipioactivoListNewSumAtcPrincipioactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcSubgrupoquimico.getId();
                if (findSumAtcSubgrupoquimico(id) == null) {
                    throw new NonexistentEntityException("The sumAtcSubgrupoquimico with id " + id + " no longer exists.");
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
            SumAtcSubgrupoquimico sumAtcSubgrupoquimico;
            try {
                sumAtcSubgrupoquimico = em.getReference(SumAtcSubgrupoquimico.class, id);
                sumAtcSubgrupoquimico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcSubgrupoquimico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SumAtcPrincipioactivo> sumAtcPrincipioactivoListOrphanCheck = sumAtcSubgrupoquimico.getSumAtcPrincipioactivoList();
            for (SumAtcPrincipioactivo sumAtcPrincipioactivoListOrphanCheckSumAtcPrincipioactivo : sumAtcPrincipioactivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SumAtcSubgrupoquimico (" + sumAtcSubgrupoquimico + ") cannot be destroyed since the SumAtcPrincipioactivo " + sumAtcPrincipioactivoListOrphanCheckSumAtcPrincipioactivo + " in its sumAtcPrincipioactivoList field has a non-nullable idSubgrupoquimico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SumAtcSubgrupofarmacologico idSubgrupofarmacologico = sumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
            if (idSubgrupofarmacologico != null) {
                idSubgrupofarmacologico.getSumAtcSubgrupoquimicoList().remove(sumAtcSubgrupoquimico);
                idSubgrupofarmacologico = em.merge(idSubgrupofarmacologico);
            }
            em.remove(sumAtcSubgrupoquimico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcSubgrupoquimico> findSumAtcSubgrupoquimicoEntities() {
        return findSumAtcSubgrupoquimicoEntities(true, -1, -1);
    }

    public List<SumAtcSubgrupoquimico> findSumAtcSubgrupoquimicoEntities(int maxResults, int firstResult) {
        return findSumAtcSubgrupoquimicoEntities(false, maxResults, firstResult);
    }

    private List<SumAtcSubgrupoquimico> findSumAtcSubgrupoquimicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcSubgrupoquimico.class));
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

    public SumAtcSubgrupoquimico findSumAtcSubgrupoquimico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcSubgrupoquimico.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcSubgrupoquimicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcSubgrupoquimico> rt = cq.from(SumAtcSubgrupoquimico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
