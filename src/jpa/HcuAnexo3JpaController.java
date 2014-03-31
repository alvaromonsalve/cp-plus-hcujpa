/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.HcuAnexo3;
import entidades.HcuAnexo3Det;
import entidades.InfoHistoriac;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HcuAnexo3JpaController implements Serializable {

    public HcuAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuAnexo3 hcuAnexo3) {
        if (hcuAnexo3.getHcuAnexo3DetList() == null) {
            hcuAnexo3.setHcuAnexo3DetList(new ArrayList<HcuAnexo3Det>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HcuAnexo3Det> attachedHcuAnexo3DetList = new ArrayList<HcuAnexo3Det>();
            for (HcuAnexo3Det hcuAnexo3DetListHcuAnexo3DetToAttach : hcuAnexo3.getHcuAnexo3DetList()) {
                hcuAnexo3DetListHcuAnexo3DetToAttach = em.getReference(hcuAnexo3DetListHcuAnexo3DetToAttach.getClass(), hcuAnexo3DetListHcuAnexo3DetToAttach.getId());
                attachedHcuAnexo3DetList.add(hcuAnexo3DetListHcuAnexo3DetToAttach);
            }
            hcuAnexo3.setHcuAnexo3DetList(attachedHcuAnexo3DetList);
            em.persist(hcuAnexo3);
            for (HcuAnexo3Det hcuAnexo3DetListHcuAnexo3Det : hcuAnexo3.getHcuAnexo3DetList()) {
                HcuAnexo3 oldIdHcuAnexo3OfHcuAnexo3DetListHcuAnexo3Det = hcuAnexo3DetListHcuAnexo3Det.getIdHcuAnexo3();
                hcuAnexo3DetListHcuAnexo3Det.setIdHcuAnexo3(hcuAnexo3);
                hcuAnexo3DetListHcuAnexo3Det = em.merge(hcuAnexo3DetListHcuAnexo3Det);
                if (oldIdHcuAnexo3OfHcuAnexo3DetListHcuAnexo3Det != null) {
                    oldIdHcuAnexo3OfHcuAnexo3DetListHcuAnexo3Det.getHcuAnexo3DetList().remove(hcuAnexo3DetListHcuAnexo3Det);
                    oldIdHcuAnexo3OfHcuAnexo3DetListHcuAnexo3Det = em.merge(oldIdHcuAnexo3OfHcuAnexo3DetListHcuAnexo3Det);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuAnexo3 hcuAnexo3) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuAnexo3 persistentHcuAnexo3 = em.find(HcuAnexo3.class, hcuAnexo3.getId());
            List<HcuAnexo3Det> hcuAnexo3DetListOld = persistentHcuAnexo3.getHcuAnexo3DetList();
            List<HcuAnexo3Det> hcuAnexo3DetListNew = hcuAnexo3.getHcuAnexo3DetList();
            List<String> illegalOrphanMessages = null;
            for (HcuAnexo3Det hcuAnexo3DetListOldHcuAnexo3Det : hcuAnexo3DetListOld) {
                if (!hcuAnexo3DetListNew.contains(hcuAnexo3DetListOldHcuAnexo3Det)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuAnexo3Det " + hcuAnexo3DetListOldHcuAnexo3Det + " since its idHcuAnexo3 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuAnexo3Det> attachedHcuAnexo3DetListNew = new ArrayList<HcuAnexo3Det>();
            for (HcuAnexo3Det hcuAnexo3DetListNewHcuAnexo3DetToAttach : hcuAnexo3DetListNew) {
                hcuAnexo3DetListNewHcuAnexo3DetToAttach = em.getReference(hcuAnexo3DetListNewHcuAnexo3DetToAttach.getClass(), hcuAnexo3DetListNewHcuAnexo3DetToAttach.getId());
                attachedHcuAnexo3DetListNew.add(hcuAnexo3DetListNewHcuAnexo3DetToAttach);
            }
            hcuAnexo3DetListNew = attachedHcuAnexo3DetListNew;
            hcuAnexo3.setHcuAnexo3DetList(hcuAnexo3DetListNew);
            hcuAnexo3 = em.merge(hcuAnexo3);
            for (HcuAnexo3Det hcuAnexo3DetListNewHcuAnexo3Det : hcuAnexo3DetListNew) {
                if (!hcuAnexo3DetListOld.contains(hcuAnexo3DetListNewHcuAnexo3Det)) {
                    HcuAnexo3 oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det = hcuAnexo3DetListNewHcuAnexo3Det.getIdHcuAnexo3();
                    hcuAnexo3DetListNewHcuAnexo3Det.setIdHcuAnexo3(hcuAnexo3);
                    hcuAnexo3DetListNewHcuAnexo3Det = em.merge(hcuAnexo3DetListNewHcuAnexo3Det);
                    if (oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det != null && !oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det.equals(hcuAnexo3)) {
                        oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det.getHcuAnexo3DetList().remove(hcuAnexo3DetListNewHcuAnexo3Det);
                        oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det = em.merge(oldIdHcuAnexo3OfHcuAnexo3DetListNewHcuAnexo3Det);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuAnexo3.getId();
                if (findHcuAnexo3(id) == null) {
                    throw new NonexistentEntityException("The hcuAnexo3 with id " + id + " no longer exists.");
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
            HcuAnexo3 hcuAnexo3;
            try {
                hcuAnexo3 = em.getReference(HcuAnexo3.class, id);
                hcuAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuAnexo3 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HcuAnexo3Det> hcuAnexo3DetListOrphanCheck = hcuAnexo3.getHcuAnexo3DetList();
            for (HcuAnexo3Det hcuAnexo3DetListOrphanCheckHcuAnexo3Det : hcuAnexo3DetListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuAnexo3 (" + hcuAnexo3 + ") cannot be destroyed since the HcuAnexo3Det " + hcuAnexo3DetListOrphanCheckHcuAnexo3Det + " in its hcuAnexo3DetList field has a non-nullable idHcuAnexo3 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hcuAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuAnexo3> findHcuAnexo3Entities() {
        return findHcuAnexo3Entities(true, -1, -1);
    }

    public List<HcuAnexo3> findHcuAnexo3Entities(int maxResults, int firstResult) {
        return findHcuAnexo3Entities(false, maxResults, firstResult);
    }

    private List<HcuAnexo3> findHcuAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuAnexo3.class));
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

    public HcuAnexo3 findHcuAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuAnexo3> rt = cq.from(HcuAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado    
    public HcuAnexo3 findHcuAnexo3(InfoHistoriac ih){
        EntityManager em = getEntityManager();        
        try {
            List results = em.createQuery("SELECT h FROM HcuAnexo3 h WHERE h.idInfoHistoriac = :ih AND h.estado = 1")
            .setParameter("ih", ih)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
            if (results.isEmpty()) return null;
            else if (results.size() == 1) return (HcuAnexo3) results.get(0);
            else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varios Anexo3 activos.\n", HcuAnexo3JpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * @param ih entidad de la historia clinica 
     * @return Int cantidad de anexos3 con estado 1: sin finalizar
     */
    public int getHcuAnexo3Count(InfoHistoriac ih){
         EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(h) FROM HcuAnexo3 h WHERE h.idInfoHistoriac = :ih AND h.estado = 1");
            q.setParameter("ih", ih);
            return ((Long)q.getSingleResult()).intValue();
        }catch(Exception ex){
            return 0;
        } finally {
            em.close();
        }
    }
    
}
