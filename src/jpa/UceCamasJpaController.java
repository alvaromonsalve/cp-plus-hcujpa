package jpa;

import entidades_EJB.ConfigCamas;
import entidades_EJB.UceCamas;
import entidades_EJB.UceHistoriac;
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
 * @author Alvaro Monsalve
 */
public class UceCamasJpaController implements Serializable {

    public UceCamasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCamas uceCamas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCamas idConfigCamas = uceCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas = em.getReference(idConfigCamas.getClass(), idConfigCamas.getId());
                uceCamas.setIdConfigCamas(idConfigCamas);
            }
            UceHistoriac idUceHistoriac = uceCamas.getIdUceHistoriac();
            if (idUceHistoriac != null) {
                idUceHistoriac = em.getReference(idUceHistoriac.getClass(), idUceHistoriac.getId());
                uceCamas.setIdUceHistoriac(idUceHistoriac);
            }
            em.persist(uceCamas);
            if (idConfigCamas != null) {
                idConfigCamas.getUceCamasList().add(uceCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            if (idUceHistoriac != null) {
                idUceHistoriac.getUceCamasList().add(uceCamas);
                idUceHistoriac = em.merge(idUceHistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCamas uceCamas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCamas persistentUceCamas = em.find(UceCamas.class, uceCamas.getId());
            ConfigCamas idConfigCamasOld = persistentUceCamas.getIdConfigCamas();
            ConfigCamas idConfigCamasNew = uceCamas.getIdConfigCamas();
            UceHistoriac idUceHistoriacOld = persistentUceCamas.getIdUceHistoriac();
            UceHistoriac idUceHistoriacNew = uceCamas.getIdUceHistoriac();
            if (idConfigCamasNew != null) {
                idConfigCamasNew = em.getReference(idConfigCamasNew.getClass(), idConfigCamasNew.getId());
                uceCamas.setIdConfigCamas(idConfigCamasNew);
            }
            if (idUceHistoriacNew != null) {
                idUceHistoriacNew = em.getReference(idUceHistoriacNew.getClass(), idUceHistoriacNew.getId());
                uceCamas.setIdUceHistoriac(idUceHistoriacNew);
            }
            uceCamas = em.merge(uceCamas);
            if (idConfigCamasOld != null && !idConfigCamasOld.equals(idConfigCamasNew)) {
                idConfigCamasOld.getUceCamasList().remove(uceCamas);
                idConfigCamasOld = em.merge(idConfigCamasOld);
            }
            if (idConfigCamasNew != null && !idConfigCamasNew.equals(idConfigCamasOld)) {
                idConfigCamasNew.getUceCamasList().add(uceCamas);
                idConfigCamasNew = em.merge(idConfigCamasNew);
            }
            if (idUceHistoriacOld != null && !idUceHistoriacOld.equals(idUceHistoriacNew)) {
                idUceHistoriacOld.getUceCamasList().remove(uceCamas);
                idUceHistoriacOld = em.merge(idUceHistoriacOld);
            }
            if (idUceHistoriacNew != null && !idUceHistoriacNew.equals(idUceHistoriacOld)) {
                idUceHistoriacNew.getUceCamasList().add(uceCamas);
                idUceHistoriacNew = em.merge(idUceHistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCamas.getId();
                if (findUceCamas(id) == null) {
                    throw new NonexistentEntityException("The hospCamas with id " + id + " no longer exists.");
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
            UceCamas uceCamas;
            try {
                uceCamas = em.getReference(UceCamas.class, id);
                uceCamas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCamas with id " + id + " no longer exists.", enfe);
            }
            ConfigCamas idConfigCamas = uceCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas.getUceCamasList().remove(uceCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            UceHistoriac idUceHistoriac = uceCamas.getIdUceHistoriac();
            if (idUceHistoriac != null) {
                idUceHistoriac.getUceCamasList().remove(uceCamas);
                idUceHistoriac = em.merge(idUceHistoriac);
            }
            em.remove(uceCamas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCamas> findUceCamasEntities() {
        return findUceCamasEntities(true, -1, -1);
    }

    public List<UceCamas> findUceCamasEntities(int maxResults, int firstResult) {
        return findUceCamasEntities(false, maxResults, firstResult);
    }

    private List<UceCamas> findUceCamasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCamas.class));
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

    public UceCamas findUceCamas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCamas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCamasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCamas> rt = cq.from(UceCamas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-Generado
    public List<UceCamas> findUceCamasEntities(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM UceCamas i WHERE i.estado = :estado" )
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public UceCamas findCamasHcu(UceHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UceCamas i WHERE i.idUceHistoriac = :ih AND i.estado = 1")
            .setParameter("ih", ih)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
            if (results.isEmpty()) return null;
            else if (results.size() == 1) return (UceCamas) results.get(0);
            else {
                JOptionPane.showMessageDialog(null, "Hosprme al administrador del sistema de este error:\n"
                        + "Es posible que existan varias camas asignadas a esta historia clinica activas.\n", HospCamasJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

}
