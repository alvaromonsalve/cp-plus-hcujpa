/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.ConfigCamas;
import entidades.InfoCamas;
import entidades.InfoHistoriac;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class InfoCamasJpaController implements Serializable {

    public InfoCamasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoCamas infoCamas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCamas idConfigCamas = infoCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas = em.getReference(idConfigCamas.getClass(), idConfigCamas.getId());
                infoCamas.setIdConfigCamas(idConfigCamas);
            }
            InfoHistoriac idInfoHistoriac = infoCamas.getIdInfoHistoriac();
            if (idInfoHistoriac != null) {
                idInfoHistoriac = em.getReference(idInfoHistoriac.getClass(), idInfoHistoriac.getId());
                infoCamas.setIdInfoHistoriac(idInfoHistoriac);
            }
            em.persist(infoCamas);
            if (idConfigCamas != null) {
                idConfigCamas.getInfoCamasList().add(infoCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            if (idInfoHistoriac != null) {
                idInfoHistoriac.getInfoCamasList().add(infoCamas);
                idInfoHistoriac = em.merge(idInfoHistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoCamas infoCamas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoCamas persistentInfoCamas = em.find(InfoCamas.class, infoCamas.getId());
            ConfigCamas idConfigCamasOld = persistentInfoCamas.getIdConfigCamas();
            ConfigCamas idConfigCamasNew = infoCamas.getIdConfigCamas();
            InfoHistoriac idInfoHistoriacOld = persistentInfoCamas.getIdInfoHistoriac();
            InfoHistoriac idInfoHistoriacNew = infoCamas.getIdInfoHistoriac();
            if (idConfigCamasNew != null) {
                idConfigCamasNew = em.getReference(idConfigCamasNew.getClass(), idConfigCamasNew.getId());
                infoCamas.setIdConfigCamas(idConfigCamasNew);
            }
            if (idInfoHistoriacNew != null) {
                idInfoHistoriacNew = em.getReference(idInfoHistoriacNew.getClass(), idInfoHistoriacNew.getId());
                infoCamas.setIdInfoHistoriac(idInfoHistoriacNew);
            }
            infoCamas = em.merge(infoCamas);
            if (idConfigCamasOld != null && !idConfigCamasOld.equals(idConfigCamasNew)) {
                idConfigCamasOld.getInfoCamasList().remove(infoCamas);
                idConfigCamasOld = em.merge(idConfigCamasOld);
            }
            if (idConfigCamasNew != null && !idConfigCamasNew.equals(idConfigCamasOld)) {
                idConfigCamasNew.getInfoCamasList().add(infoCamas);
                idConfigCamasNew = em.merge(idConfigCamasNew);
            }
            if (idInfoHistoriacOld != null && !idInfoHistoriacOld.equals(idInfoHistoriacNew)) {
                idInfoHistoriacOld.getInfoCamasList().remove(infoCamas);
                idInfoHistoriacOld = em.merge(idInfoHistoriacOld);
            }
            if (idInfoHistoriacNew != null && !idInfoHistoriacNew.equals(idInfoHistoriacOld)) {
                idInfoHistoriacNew.getInfoCamasList().add(infoCamas);
                idInfoHistoriacNew = em.merge(idInfoHistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoCamas.getId();
                if (findInfoCamas(id) == null) {
                    throw new NonexistentEntityException("The infoCamas with id " + id + " no longer exists.");
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
            InfoCamas infoCamas;
            try {
                infoCamas = em.getReference(InfoCamas.class, id);
                infoCamas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoCamas with id " + id + " no longer exists.", enfe);
            }
            ConfigCamas idConfigCamas = infoCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas.getInfoCamasList().remove(infoCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            InfoHistoriac idInfoHistoriac = infoCamas.getIdInfoHistoriac();
            if (idInfoHistoriac != null) {
                idInfoHistoriac.getInfoCamasList().remove(infoCamas);
                idInfoHistoriac = em.merge(idInfoHistoriac);
            }
            em.remove(infoCamas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoCamas> findInfoCamasEntities() {
        return findInfoCamasEntities(true, -1, -1);
    }

    public List<InfoCamas> findInfoCamasEntities(int maxResults, int firstResult) {
        return findInfoCamasEntities(false, maxResults, firstResult);
    }

    private List<InfoCamas> findInfoCamasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoCamas.class));
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

    public InfoCamas findInfoCamas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoCamas.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoCamasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoCamas> rt = cq.from(InfoCamas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-Generado
    public List<InfoCamas> findInfoCamasEntities(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM InfoCamas i WHERE i.estado = :estado" )
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }    
    
    public InfoCamas findCamasHcu(InfoHistoriac ih){
        EntityManager em = getEntityManager();        
        try {
            List results = em.createQuery("SELECT i FROM InfoCamas i WHERE i.idInfoHistoriac = :ih AND i.estado = 1")
            .setParameter("ih", ih)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
            if (results.isEmpty()) return null;
            else if (results.size() == 1) return (InfoCamas) results.get(0);
            else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varias camas asignadas a esta historia clinica activas.\n", InfoCamasJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }
    
}
