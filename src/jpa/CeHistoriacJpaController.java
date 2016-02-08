/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.CmPrincipalCitas;
import entidades_EJB.CeAnexo3;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.CeCtc;
import entidades_EJB.CeHistoriac;
import entidades_EJB.InfoPaciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeHistoriacJpaController implements Serializable {

    public CeHistoriacJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeHistoriac ceHistoriac) {
        if (ceHistoriac.getCeAnexo3List() == null) {
            ceHistoriac.setCeAnexo3List(new ArrayList<CeAnexo3>());
        }
        if (ceHistoriac.getCeCtcList() == null) {
            ceHistoriac.setCeCtcList(new ArrayList<CeCtc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CmPrincipalCitas idCita = ceHistoriac.getIdCita();
            if (idCita != null) {
                idCita = em.getReference(idCita.getClass(), idCita.getId());
                ceHistoriac.setIdCita(idCita);
            }
            List<CeAnexo3> attachedCeAnexo3List = new ArrayList<CeAnexo3>();
            for (CeAnexo3 ceAnexo3ListCeAnexo3ToAttach : ceHistoriac.getCeAnexo3List()) {
                ceAnexo3ListCeAnexo3ToAttach = em.getReference(ceAnexo3ListCeAnexo3ToAttach.getClass(), ceAnexo3ListCeAnexo3ToAttach.getId());
                attachedCeAnexo3List.add(ceAnexo3ListCeAnexo3ToAttach);
            }
            ceHistoriac.setCeAnexo3List(attachedCeAnexo3List);
            List<CeCtc> attachedCeCtcList = new ArrayList<CeCtc>();
            for (CeCtc ceCtcListCeCtcToAttach : ceHistoriac.getCeCtcList()) {
                ceCtcListCeCtcToAttach = em.getReference(ceCtcListCeCtcToAttach.getClass(), ceCtcListCeCtcToAttach.getId());
                attachedCeCtcList.add(ceCtcListCeCtcToAttach);
            }
            ceHistoriac.setCeCtcList(attachedCeCtcList);
            em.persist(ceHistoriac);
            if (idCita != null) {
                idCita.getCeHistoriacList().add(ceHistoriac);
                idCita = em.merge(idCita);
            }
            for (CeAnexo3 ceAnexo3ListCeAnexo3 : ceHistoriac.getCeAnexo3List()) {
                CeHistoriac oldIdHistoriaOfCeAnexo3ListCeAnexo3 = ceAnexo3ListCeAnexo3.getIdHistoria();
                ceAnexo3ListCeAnexo3.setIdHistoria(ceHistoriac);
                ceAnexo3ListCeAnexo3 = em.merge(ceAnexo3ListCeAnexo3);
                if (oldIdHistoriaOfCeAnexo3ListCeAnexo3 != null) {
                    oldIdHistoriaOfCeAnexo3ListCeAnexo3.getCeAnexo3List().remove(ceAnexo3ListCeAnexo3);
                    oldIdHistoriaOfCeAnexo3ListCeAnexo3 = em.merge(oldIdHistoriaOfCeAnexo3ListCeAnexo3);
                }
            }
            for (CeCtc ceCtcListCeCtc : ceHistoriac.getCeCtcList()) {
                CeHistoriac oldIdHistoriaOfCeCtcListCeCtc = ceCtcListCeCtc.getIdHistoria();
                ceCtcListCeCtc.setIdHistoria(ceHistoriac);
                ceCtcListCeCtc = em.merge(ceCtcListCeCtc);
                if (oldIdHistoriaOfCeCtcListCeCtc != null) {
                    oldIdHistoriaOfCeCtcListCeCtc.getCeCtcList().remove(ceCtcListCeCtc);
                    oldIdHistoriaOfCeCtcListCeCtc = em.merge(oldIdHistoriaOfCeCtcListCeCtc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeHistoriac ceHistoriac) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeHistoriac persistentCeHistoriac = em.find(CeHistoriac.class, ceHistoriac.getId());
            CmPrincipalCitas idCitaOld = persistentCeHistoriac.getIdCita();
            CmPrincipalCitas idCitaNew = ceHistoriac.getIdCita();
            List<CeAnexo3> ceAnexo3ListOld = persistentCeHistoriac.getCeAnexo3List();
            List<CeAnexo3> ceAnexo3ListNew = ceHistoriac.getCeAnexo3List();
            List<CeCtc> ceCtcListOld = persistentCeHistoriac.getCeCtcList();
            List<CeCtc> ceCtcListNew = ceHistoriac.getCeCtcList();
            List<String> illegalOrphanMessages = null;
            for (CeAnexo3 ceAnexo3ListOldCeAnexo3 : ceAnexo3ListOld) {
                if (!ceAnexo3ListNew.contains(ceAnexo3ListOldCeAnexo3)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CeAnexo3 " + ceAnexo3ListOldCeAnexo3 + " since its idHistoria field is not nullable.");
                }
            }
            for (CeCtc ceCtcListOldCeCtc : ceCtcListOld) {
                if (!ceCtcListNew.contains(ceCtcListOldCeCtc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CeCtc " + ceCtcListOldCeCtc + " since its idHistoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCitaNew != null) {
                idCitaNew = em.getReference(idCitaNew.getClass(), idCitaNew.getId());
                ceHistoriac.setIdCita(idCitaNew);
            }
            List<CeAnexo3> attachedCeAnexo3ListNew = new ArrayList<CeAnexo3>();
            for (CeAnexo3 ceAnexo3ListNewCeAnexo3ToAttach : ceAnexo3ListNew) {
                ceAnexo3ListNewCeAnexo3ToAttach = em.getReference(ceAnexo3ListNewCeAnexo3ToAttach.getClass(), ceAnexo3ListNewCeAnexo3ToAttach.getId());
                attachedCeAnexo3ListNew.add(ceAnexo3ListNewCeAnexo3ToAttach);
            }
            ceAnexo3ListNew = attachedCeAnexo3ListNew;
            ceHistoriac.setCeAnexo3List(ceAnexo3ListNew);
            List<CeCtc> attachedCeCtcListNew = new ArrayList<CeCtc>();
            for (CeCtc ceCtcListNewCeCtcToAttach : ceCtcListNew) {
                ceCtcListNewCeCtcToAttach = em.getReference(ceCtcListNewCeCtcToAttach.getClass(), ceCtcListNewCeCtcToAttach.getId());
                attachedCeCtcListNew.add(ceCtcListNewCeCtcToAttach);
            }
            ceCtcListNew = attachedCeCtcListNew;
            ceHistoriac.setCeCtcList(ceCtcListNew);
            ceHistoriac = em.merge(ceHistoriac);
            if (idCitaOld != null && !idCitaOld.equals(idCitaNew)) {
                idCitaOld.getCeHistoriacList().remove(ceHistoriac);
                idCitaOld = em.merge(idCitaOld);
            }
            if (idCitaNew != null && !idCitaNew.equals(idCitaOld)) {
                idCitaNew.getCeHistoriacList().add(ceHistoriac);
                idCitaNew = em.merge(idCitaNew);
            }
            for (CeAnexo3 ceAnexo3ListNewCeAnexo3 : ceAnexo3ListNew) {
                if (!ceAnexo3ListOld.contains(ceAnexo3ListNewCeAnexo3)) {
                    CeHistoriac oldIdHistoriaOfCeAnexo3ListNewCeAnexo3 = ceAnexo3ListNewCeAnexo3.getIdHistoria();
                    ceAnexo3ListNewCeAnexo3.setIdHistoria(ceHistoriac);
                    ceAnexo3ListNewCeAnexo3 = em.merge(ceAnexo3ListNewCeAnexo3);
                    if (oldIdHistoriaOfCeAnexo3ListNewCeAnexo3 != null && !oldIdHistoriaOfCeAnexo3ListNewCeAnexo3.equals(ceHistoriac)) {
                        oldIdHistoriaOfCeAnexo3ListNewCeAnexo3.getCeAnexo3List().remove(ceAnexo3ListNewCeAnexo3);
                        oldIdHistoriaOfCeAnexo3ListNewCeAnexo3 = em.merge(oldIdHistoriaOfCeAnexo3ListNewCeAnexo3);
                    }
                }
            }
            for (CeCtc ceCtcListNewCeCtc : ceCtcListNew) {
                if (!ceCtcListOld.contains(ceCtcListNewCeCtc)) {
                    CeHistoriac oldIdHistoriaOfCeCtcListNewCeCtc = ceCtcListNewCeCtc.getIdHistoria();
                    ceCtcListNewCeCtc.setIdHistoria(ceHistoriac);
                    ceCtcListNewCeCtc = em.merge(ceCtcListNewCeCtc);
                    if (oldIdHistoriaOfCeCtcListNewCeCtc != null && !oldIdHistoriaOfCeCtcListNewCeCtc.equals(ceHistoriac)) {
                        oldIdHistoriaOfCeCtcListNewCeCtc.getCeCtcList().remove(ceCtcListNewCeCtc);
                        oldIdHistoriaOfCeCtcListNewCeCtc = em.merge(oldIdHistoriaOfCeCtcListNewCeCtc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceHistoriac.getId();
                if (findCeHistoriac(id) == null) {
                    throw new NonexistentEntityException("The ceHistoriac with id " + id + " no longer exists.");
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
            CeHistoriac ceHistoriac;
            try {
                ceHistoriac = em.getReference(CeHistoriac.class, id);
                ceHistoriac.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceHistoriac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CeAnexo3> ceAnexo3ListOrphanCheck = ceHistoriac.getCeAnexo3List();
            for (CeAnexo3 ceAnexo3ListOrphanCheckCeAnexo3 : ceAnexo3ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CeHistoriac (" + ceHistoriac + ") cannot be destroyed since the CeAnexo3 " + ceAnexo3ListOrphanCheckCeAnexo3 + " in its ceAnexo3List field has a non-nullable idHistoria field.");
            }
            List<CeCtc> ceCtcListOrphanCheck = ceHistoriac.getCeCtcList();
            for (CeCtc ceCtcListOrphanCheckCeCtc : ceCtcListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CeHistoriac (" + ceHistoriac + ") cannot be destroyed since the CeCtc " + ceCtcListOrphanCheckCeCtc + " in its ceCtcList field has a non-nullable idHistoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CmPrincipalCitas idCita = ceHistoriac.getIdCita();
            if (idCita != null) {
                idCita.getCeHistoriacList().remove(ceHistoriac);
                idCita = em.merge(idCita);
            }
            em.remove(ceHistoriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeHistoriac> findCeHistoriacEntities() {
        return findCeHistoriacEntities(true, -1, -1);
    }

    public List<CeHistoriac> findCeHistoriacEntities(int maxResults, int firstResult) {
        return findCeHistoriacEntities(false, maxResults, firstResult);
    }

    private List<CeHistoriac> findCeHistoriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeHistoriac.class));
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

    public CeHistoriac findCeHistoriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeHistoriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeHistoriacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeHistoriac> rt = cq.from(CeHistoriac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeHistoriac> getConsultas(InfoPaciente ident) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT ce FROM CeHistoriac ce WHERE ce.idCita.idPaciente=:dto AND ce.estado='2'");
        Q.setParameter("dto", ident);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object counHistoriaCE(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(ceh.id) FROM CeHistoriac ceh WHERE ceh.idCita.id=:idc AND ceh.estado='1'");
        Q.setParameter("idc", h);
        return Q.getSingleResult();
    }

    public CeHistoriac getEntidadHistoria(int i) {
        EntityManager em = getEntityManager();
        CeHistoriac hi = null;
        Query Q = null;
        Q = em.createQuery("SELECT h FROM CeHistoriac h WHERE h.idCita.id=:c AND h.estado='1'");
        Q.setParameter("c", i);
        List<CeHistoriac> gH = Q.getResultList();
        if (!gH.isEmpty()) {
            hi = (CeHistoriac) gH.get(0);
        } else {
            hi = null;
        }
        return hi;
    }
//    public Object validarHistoria(int i) {
//        EntityManager em = getEntityManager();
//        CeHistoriac hi = null;
//        Query Q = null;
//        Q = em.createQuery("SELECT COUNT(h.id) FROM CeHistoriac h WHERE h.idCita.id=:pa AND h.estado='1'");
//        Q.setParameter("pa", i);
//        return Q.getSingleResult();
//    }

    public CeHistoriac getEntidadHistoriaFin(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT h FROM CeHistoriac h WHERE h.idCita.id=:c AND h.estado='2' ");
        Q.setParameter("c", i);
        return (CeHistoriac) Q.getSingleResult();
    }
}
