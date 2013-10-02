/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoHcExpfisica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.InfoHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class InfoHcExpfisicaJpaController implements Serializable {

    public InfoHcExpfisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoHcExpfisica infoHcExpfisica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoHistoriac idInfohistoriac = infoHcExpfisica.getIdInfohistoriac();
            if (idInfohistoriac != null) {
                idInfohistoriac = em.getReference(idInfohistoriac.getClass(), idInfohistoriac.getId());
                infoHcExpfisica.setIdInfohistoriac(idInfohistoriac);
            }
            em.persist(infoHcExpfisica);
            if (idInfohistoriac != null) {
                InfoHcExpfisica oldInfoHcExpfisicaOfIdInfohistoriac = idInfohistoriac.getInfoHcExpfisica();
                if (oldInfoHcExpfisicaOfIdInfohistoriac != null) {
                    oldInfoHcExpfisicaOfIdInfohistoriac.setIdInfohistoriac(null);
                    oldInfoHcExpfisicaOfIdInfohistoriac = em.merge(oldInfoHcExpfisicaOfIdInfohistoriac);
                }
                idInfohistoriac.setInfoHcExpfisica(infoHcExpfisica);
                idInfohistoriac = em.merge(idInfohistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoHcExpfisica infoHcExpfisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoHcExpfisica persistentInfoHcExpfisica = em.find(InfoHcExpfisica.class, infoHcExpfisica.getId());
            InfoHistoriac idInfohistoriacOld = persistentInfoHcExpfisica.getIdInfohistoriac();
            InfoHistoriac idInfohistoriacNew = infoHcExpfisica.getIdInfohistoriac();
            if (idInfohistoriacNew != null) {
                idInfohistoriacNew = em.getReference(idInfohistoriacNew.getClass(), idInfohistoriacNew.getId());
                infoHcExpfisica.setIdInfohistoriac(idInfohistoriacNew);
            }
            infoHcExpfisica = em.merge(infoHcExpfisica);
            if (idInfohistoriacOld != null && !idInfohistoriacOld.equals(idInfohistoriacNew)) {
                idInfohistoriacOld.setInfoHcExpfisica(null);
                idInfohistoriacOld = em.merge(idInfohistoriacOld);
            }
            if (idInfohistoriacNew != null && !idInfohistoriacNew.equals(idInfohistoriacOld)) {
                InfoHcExpfisica oldInfoHcExpfisicaOfIdInfohistoriac = idInfohistoriacNew.getInfoHcExpfisica();
                if (oldInfoHcExpfisicaOfIdInfohistoriac != null) {
                    oldInfoHcExpfisicaOfIdInfohistoriac.setIdInfohistoriac(null);
                    oldInfoHcExpfisicaOfIdInfohistoriac = em.merge(oldInfoHcExpfisicaOfIdInfohistoriac);
                }
                idInfohistoriacNew.setInfoHcExpfisica(infoHcExpfisica);
                idInfohistoriacNew = em.merge(idInfohistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoHcExpfisica.getId();
                if (findInfoHcExpfisica(id) == null) {
                    throw new NonexistentEntityException("The infoHcExpfisica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoHcExpfisica infoHcExpfisica;
            try {
                infoHcExpfisica = em.getReference(InfoHcExpfisica.class, id);
                infoHcExpfisica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoHcExpfisica with id " + id + " no longer exists.", enfe);
            }
            InfoHistoriac idInfohistoriac = infoHcExpfisica.getIdInfohistoriac();
            if (idInfohistoriac != null) {
                idInfohistoriac.setInfoHcExpfisica(null);
                idInfohistoriac = em.merge(idInfohistoriac);
            }
            em.remove(infoHcExpfisica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoHcExpfisica> findInfoHcExpfisicaEntities() {
        return findInfoHcExpfisicaEntities(true, -1, -1);
    }

    public List<InfoHcExpfisica> findInfoHcExpfisicaEntities(int maxResults, int firstResult) {
        return findInfoHcExpfisicaEntities(false, maxResults, firstResult);
    }

    private List<InfoHcExpfisica> findInfoHcExpfisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoHcExpfisica.class));
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

    public InfoHcExpfisica findInfoHcExpfisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoHcExpfisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoHcExpfisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoHcExpfisica> rt = cq.from(InfoHcExpfisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
