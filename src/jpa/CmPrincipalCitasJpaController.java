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
import entidades_EJB.CeHistoriac;
import entidades_EJB.CmEspPprofesional;
import entidades_EJB.CmPrincipalCitas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CmPrincipalCitasJpaController implements Serializable {

    public CmPrincipalCitasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CmPrincipalCitas cmPrincipalCitas) {
        if (cmPrincipalCitas.getCeHistoriacList() == null) {
            cmPrincipalCitas.setCeHistoriacList(new ArrayList<CeHistoriac>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CeHistoriac> attachedCeHistoriacList = new ArrayList<CeHistoriac>();
            for (CeHistoriac ceHistoriacListCeHistoriacToAttach : cmPrincipalCitas.getCeHistoriacList()) {
                ceHistoriacListCeHistoriacToAttach = em.getReference(ceHistoriacListCeHistoriacToAttach.getClass(), ceHistoriacListCeHistoriacToAttach.getId());
                attachedCeHistoriacList.add(ceHistoriacListCeHistoriacToAttach);
            }
            cmPrincipalCitas.setCeHistoriacList(attachedCeHistoriacList);
            em.persist(cmPrincipalCitas);
            for (CeHistoriac ceHistoriacListCeHistoriac : cmPrincipalCitas.getCeHistoriacList()) {
                CmPrincipalCitas oldIdCitaOfCeHistoriacListCeHistoriac = ceHistoriacListCeHistoriac.getIdCita();
                ceHistoriacListCeHistoriac.setIdCita(cmPrincipalCitas);
                ceHistoriacListCeHistoriac = em.merge(ceHistoriacListCeHistoriac);
                if (oldIdCitaOfCeHistoriacListCeHistoriac != null) {
                    oldIdCitaOfCeHistoriacListCeHistoriac.getCeHistoriacList().remove(ceHistoriacListCeHistoriac);
                    oldIdCitaOfCeHistoriacListCeHistoriac = em.merge(oldIdCitaOfCeHistoriacListCeHistoriac);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CmPrincipalCitas cmPrincipalCitas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CmPrincipalCitas persistentCmPrincipalCitas = em.find(CmPrincipalCitas.class, cmPrincipalCitas.getId());
            List<CeHistoriac> ceHistoriacListOld = persistentCmPrincipalCitas.getCeHistoriacList();
            List<CeHistoriac> ceHistoriacListNew = cmPrincipalCitas.getCeHistoriacList();
            List<String> illegalOrphanMessages = null;
            for (CeHistoriac ceHistoriacListOldCeHistoriac : ceHistoriacListOld) {
                if (!ceHistoriacListNew.contains(ceHistoriacListOldCeHistoriac)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CeHistoriac " + ceHistoriacListOldCeHistoriac + " since its idCita field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CeHistoriac> attachedCeHistoriacListNew = new ArrayList<CeHistoriac>();
            for (CeHistoriac ceHistoriacListNewCeHistoriacToAttach : ceHistoriacListNew) {
                ceHistoriacListNewCeHistoriacToAttach = em.getReference(ceHistoriacListNewCeHistoriacToAttach.getClass(), ceHistoriacListNewCeHistoriacToAttach.getId());
                attachedCeHistoriacListNew.add(ceHistoriacListNewCeHistoriacToAttach);
            }
            ceHistoriacListNew = attachedCeHistoriacListNew;
            cmPrincipalCitas.setCeHistoriacList(ceHistoriacListNew);
            cmPrincipalCitas = em.merge(cmPrincipalCitas);
            for (CeHistoriac ceHistoriacListNewCeHistoriac : ceHistoriacListNew) {
                if (!ceHistoriacListOld.contains(ceHistoriacListNewCeHistoriac)) {
                    CmPrincipalCitas oldIdCitaOfCeHistoriacListNewCeHistoriac = ceHistoriacListNewCeHistoriac.getIdCita();
                    ceHistoriacListNewCeHistoriac.setIdCita(cmPrincipalCitas);
                    ceHistoriacListNewCeHistoriac = em.merge(ceHistoriacListNewCeHistoriac);
                    if (oldIdCitaOfCeHistoriacListNewCeHistoriac != null && !oldIdCitaOfCeHistoriacListNewCeHistoriac.equals(cmPrincipalCitas)) {
                        oldIdCitaOfCeHistoriacListNewCeHistoriac.getCeHistoriacList().remove(ceHistoriacListNewCeHistoriac);
                        oldIdCitaOfCeHistoriacListNewCeHistoriac = em.merge(oldIdCitaOfCeHistoriacListNewCeHistoriac);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cmPrincipalCitas.getId();
                if (findCmPrincipalCitas(id) == null) {
                    throw new NonexistentEntityException("The cmPrincipalCitas with id " + id + " no longer exists.");
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
            CmPrincipalCitas cmPrincipalCitas;
            try {
                cmPrincipalCitas = em.getReference(CmPrincipalCitas.class, id);
                cmPrincipalCitas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cmPrincipalCitas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CeHistoriac> ceHistoriacListOrphanCheck = cmPrincipalCitas.getCeHistoriacList();
            for (CeHistoriac ceHistoriacListOrphanCheckCeHistoriac : ceHistoriacListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CmPrincipalCitas (" + cmPrincipalCitas + ") cannot be destroyed since the CeHistoriac " + ceHistoriacListOrphanCheckCeHistoriac + " in its ceHistoriacList field has a non-nullable idCita field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cmPrincipalCitas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CmPrincipalCitas> findCmPrincipalCitasEntities() {
        return findCmPrincipalCitasEntities(true, -1, -1);
    }

    public List<CmPrincipalCitas> findCmPrincipalCitasEntities(int maxResults, int firstResult) {
        return findCmPrincipalCitasEntities(false, maxResults, firstResult);
    }

    private List<CmPrincipalCitas> findCmPrincipalCitasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CmPrincipalCitas.class));
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

    public CmPrincipalCitas findCmPrincipalCitas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CmPrincipalCitas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCmPrincipalCitasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CmPrincipalCitas> rt = cq.from(CmPrincipalCitas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CmPrincipalCitas> getCitas(CmEspPprofesional e, Date f) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c FROM CmPrincipalCitas c WHERE c.idEspecialidad=:ee AND c.fechaCita=:fe AND (c.estado='1' OR c.estado='3' OR c.estado='5') ORDER BY c.horaCita ASC");
        Q.setParameter("ee", e);
        Q.setParameter("fe", f);
        return Q.getResultList();
    }

    public List<CmPrincipalCitas> ListaCitasAsignadas(Date fecha, int profesional, String especialidad) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c  FROM CmPrincipalCitas c WHERE c.fechaCita=:fc AND c.idEspecialidad.idProfesional.id=:idp  AND c.idEspecialidad.idEspecialidad.especialidad=:esp AND c.estado='1' ORDER BY c.horaCita ASC")
                .setParameter("fc", fecha)
                .setParameter("idp", profesional)
                .setParameter("esp", especialidad);
        return Q.getResultList();
    }

    public List<CmPrincipalCitas> ListaCitasAsignadas2(Date fecha, int profesional, String especialidad) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c  FROM CmPrincipalCitas c WHERE c.fechaCita=:fc AND c.idEspecialidad.idProfesional.id=:idp  AND c.idEspecialidad.idEspecialidad.especialidad=:esp AND c.estado='5' ORDER BY c.horaCita ASC")
                .setParameter("fc", fecha)
                .setParameter("idp", profesional)
                .setParameter("esp", especialidad);
        return Q.getResultList();
    }

    public List<CmPrincipalCitas> ListaCitasAsignadas3(Date fecha, int profesional, String especialidad) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c  FROM CmPrincipalCitas c WHERE c.fechaCita=:fc AND c.idEspecialidad.idProfesional.id=:idp  AND c.idEspecialidad.idEspecialidad.especialidad=:esp AND c.estado='3' ORDER BY c.horaCita ASC");
        Q.setParameter("fc", fecha);
        Q.setParameter("idp", profesional);
        Q.setParameter("esp", especialidad);
        return Q.getResultList();
    }
}
