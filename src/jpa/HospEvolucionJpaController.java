package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospEvoMedidasg;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospEvoInterconsulta;
import entidades_EJB.HospEvoPosologia;
import entidades_EJB.HospEvoProcedimiento;
import entidades_EJB.HospEvoEgreso;
import entidades_EJB.HospEvolucion;
import entidades_EJB.HospHistoriac;
import entidades_EJB.StaticEspecialidades;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospEvolucionJpaController implements Serializable {

    public HospEvolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvolucion hospEvolucion) {
        if (hospEvolucion.getHospEvoMedidasgs() == null) {
            hospEvolucion.setHospEvoMedidasgs(new ArrayList<HospEvoMedidasg>());
        }
        if (hospEvolucion.getHospEvoInterconsultas() == null) {
            hospEvolucion.setHospEvoInterconsultas(new ArrayList<HospEvoInterconsulta>());
        }
        if (hospEvolucion.getHospEvoPosologias() == null) {
            hospEvolucion.setHospEvoPosologias(new ArrayList<HospEvoPosologia>());
        }
        if (hospEvolucion.getHospEvoProcedimientos() == null) {
            hospEvolucion.setHospEvoProcedimientos(new ArrayList<HospEvoProcedimiento>());
        }
        if (hospEvolucion.getHospEvoEgreso() == null) {
            hospEvolucion.setHospEvoEgreso(new ArrayList<HospEvoEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospEvoMedidasg> attachedHospEvoMedidasgs = new ArrayList<HospEvoMedidasg>();
            for (HospEvoMedidasg hospEvoMedidasgsHospEvoMedidasgToAttach : hospEvolucion.getHospEvoMedidasgs()) {
                hospEvoMedidasgsHospEvoMedidasgToAttach = em.getReference(hospEvoMedidasgsHospEvoMedidasgToAttach.getClass(), hospEvoMedidasgsHospEvoMedidasgToAttach.getId());
                attachedHospEvoMedidasgs.add(hospEvoMedidasgsHospEvoMedidasgToAttach);
            }
            hospEvolucion.setHospEvoMedidasgs(attachedHospEvoMedidasgs);
            List<HospEvoInterconsulta> attachedHospEvoInterconsultas = new ArrayList<HospEvoInterconsulta>();
            for (HospEvoInterconsulta hospEvoInterconsultasHospEvoInterconsultaToAttach : hospEvolucion.getHospEvoInterconsultas()) {
                hospEvoInterconsultasHospEvoInterconsultaToAttach = em.getReference(hospEvoInterconsultasHospEvoInterconsultaToAttach.getClass(), hospEvoInterconsultasHospEvoInterconsultaToAttach.getId());
                attachedHospEvoInterconsultas.add(hospEvoInterconsultasHospEvoInterconsultaToAttach);
            }
            hospEvolucion.setHospEvoInterconsultas(attachedHospEvoInterconsultas);
            List<HospEvoPosologia> attachedHospEvoPosologias = new ArrayList<HospEvoPosologia>();
            for (HospEvoPosologia hospEvoPosologiasHospEvoPosologiaToAttach : hospEvolucion.getHospEvoPosologias()) {
                hospEvoPosologiasHospEvoPosologiaToAttach = em.getReference(hospEvoPosologiasHospEvoPosologiaToAttach.getClass(), hospEvoPosologiasHospEvoPosologiaToAttach.getId());
                attachedHospEvoPosologias.add(hospEvoPosologiasHospEvoPosologiaToAttach);
            }
            hospEvolucion.setHospEvoPosologias(attachedHospEvoPosologias);
            List<HospEvoProcedimiento> attachedHospEvoProcedimientos = new ArrayList<HospEvoProcedimiento>();
            for (HospEvoProcedimiento hospEvoProcedimientosHospEvoProcedimientoToAttach : hospEvolucion.getHospEvoProcedimientos()) {
                hospEvoProcedimientosHospEvoProcedimientoToAttach = em.getReference(hospEvoProcedimientosHospEvoProcedimientoToAttach.getClass(), hospEvoProcedimientosHospEvoProcedimientoToAttach.getId());
                attachedHospEvoProcedimientos.add(hospEvoProcedimientosHospEvoProcedimientoToAttach);
            }
            hospEvolucion.setHospEvoProcedimientos(attachedHospEvoProcedimientos);
            List<HospEvoEgreso> attachedHospEvoEgreso = new ArrayList<HospEvoEgreso>();
            for (HospEvoEgreso hospEvoEgresoHospEvoEgresoToAttach : hospEvolucion.getHospEvoEgreso()) {
                hospEvoEgresoHospEvoEgresoToAttach = em.getReference(hospEvoEgresoHospEvoEgresoToAttach.getClass(), hospEvoEgresoHospEvoEgresoToAttach.getId());
                attachedHospEvoEgreso.add(hospEvoEgresoHospEvoEgresoToAttach);
            }
            hospEvolucion.setHospEvoEgreso(attachedHospEvoEgreso);
            em.persist(hospEvolucion);
            for (HospEvoMedidasg hospEvoMedidasgsHospEvoMedidasg : hospEvolucion.getHospEvoMedidasgs()) {
                HospEvolucion oldIdHospEvolucionOfHospEvoMedidasgsHospEvoMedidasg = hospEvoMedidasgsHospEvoMedidasg.getIdHospEvolucion();
                hospEvoMedidasgsHospEvoMedidasg.setIdHospEvolucion(hospEvolucion);
                hospEvoMedidasgsHospEvoMedidasg = em.merge(hospEvoMedidasgsHospEvoMedidasg);
                if (oldIdHospEvolucionOfHospEvoMedidasgsHospEvoMedidasg != null) {
                    oldIdHospEvolucionOfHospEvoMedidasgsHospEvoMedidasg.getHospEvoMedidasgs().remove(hospEvoMedidasgsHospEvoMedidasg);
                    oldIdHospEvolucionOfHospEvoMedidasgsHospEvoMedidasg = em.merge(oldIdHospEvolucionOfHospEvoMedidasgsHospEvoMedidasg);
                }
            }
            for (HospEvoInterconsulta hospEvoInterconsultasHospEvoInterconsulta : hospEvolucion.getHospEvoInterconsultas()) {
                HospEvolucion oldIdHospEvolucionOfHospEvoInterconsultasHospEvoInterconsulta = hospEvoInterconsultasHospEvoInterconsulta.getIdHospEvolucion();
                hospEvoInterconsultasHospEvoInterconsulta.setIdHospEvolucion(hospEvolucion);
                hospEvoInterconsultasHospEvoInterconsulta = em.merge(hospEvoInterconsultasHospEvoInterconsulta);
                if (oldIdHospEvolucionOfHospEvoInterconsultasHospEvoInterconsulta != null) {
                    oldIdHospEvolucionOfHospEvoInterconsultasHospEvoInterconsulta.getHospEvoInterconsultas().remove(hospEvoInterconsultasHospEvoInterconsulta);
                    oldIdHospEvolucionOfHospEvoInterconsultasHospEvoInterconsulta = em.merge(oldIdHospEvolucionOfHospEvoInterconsultasHospEvoInterconsulta);
                }
            }
            for (HospEvoPosologia hospEvoPosologiasHospEvoPosologia : hospEvolucion.getHospEvoPosologias()) {
                HospEvolucion oldIdHospEvolucionOfHospEvoPosologiasHospEvoPosologia = hospEvoPosologiasHospEvoPosologia.getIdHospEvolucion();
                hospEvoPosologiasHospEvoPosologia.setIdHospEvolucion(hospEvolucion);
                hospEvoPosologiasHospEvoPosologia = em.merge(hospEvoPosologiasHospEvoPosologia);
                if (oldIdHospEvolucionOfHospEvoPosologiasHospEvoPosologia != null) {
                    oldIdHospEvolucionOfHospEvoPosologiasHospEvoPosologia.getHospEvoPosologias().remove(hospEvoPosologiasHospEvoPosologia);
                    oldIdHospEvolucionOfHospEvoPosologiasHospEvoPosologia = em.merge(oldIdHospEvolucionOfHospEvoPosologiasHospEvoPosologia);
                }
            }
            for (HospEvoProcedimiento hospEvoProcedimientosHospEvoProcedimiento : hospEvolucion.getHospEvoProcedimientos()) {
                HospEvolucion oldIdHospEvolucionOfHospEvoProcedimientosHospEvoProcedimiento = hospEvoProcedimientosHospEvoProcedimiento.getIdHospEvolucion();
                hospEvoProcedimientosHospEvoProcedimiento.setIdHospEvolucion(hospEvolucion);
                hospEvoProcedimientosHospEvoProcedimiento = em.merge(hospEvoProcedimientosHospEvoProcedimiento);
                if (oldIdHospEvolucionOfHospEvoProcedimientosHospEvoProcedimiento != null) {
                    oldIdHospEvolucionOfHospEvoProcedimientosHospEvoProcedimiento.getHospEvoProcedimientos().remove(hospEvoProcedimientosHospEvoProcedimiento);
                    oldIdHospEvolucionOfHospEvoProcedimientosHospEvoProcedimiento = em.merge(oldIdHospEvolucionOfHospEvoProcedimientosHospEvoProcedimiento);
                }
            }
            for (HospEvoEgreso hospEvoEgresoHospEvoEgreso : hospEvolucion.getHospEvoEgreso()) {
                HospEvolucion oldIdHospEvolucionOfHospEvoEgresoHospEvoEgreso = hospEvoEgresoHospEvoEgreso.getIdHospEvolucion();
                hospEvoEgresoHospEvoEgreso.setIdHospEvolucion(hospEvolucion);
                hospEvoEgresoHospEvoEgreso = em.merge(hospEvoEgresoHospEvoEgreso);
                if (oldIdHospEvolucionOfHospEvoEgresoHospEvoEgreso != null) {
                    oldIdHospEvolucionOfHospEvoEgresoHospEvoEgreso.getHospEvoEgreso().remove(hospEvoEgresoHospEvoEgreso);
                    oldIdHospEvolucionOfHospEvoEgresoHospEvoEgreso = em.merge(oldIdHospEvolucionOfHospEvoEgresoHospEvoEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvolucion hospEvolucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEvolucion persistentHospEvolucion = em.find(HospEvolucion.class, hospEvolucion.getId());
            List<HospEvoMedidasg> hospEvoMedidasgsOld = persistentHospEvolucion.getHospEvoMedidasgs();
            List<HospEvoMedidasg> hospEvoMedidasgsNew = hospEvolucion.getHospEvoMedidasgs();
            List<HospEvoInterconsulta> hospEvoInterconsultasOld = persistentHospEvolucion.getHospEvoInterconsultas();
            List<HospEvoInterconsulta> hospEvoInterconsultasNew = hospEvolucion.getHospEvoInterconsultas();
            List<HospEvoPosologia> hospEvoPosologiasOld = persistentHospEvolucion.getHospEvoPosologias();
            List<HospEvoPosologia> hospEvoPosologiasNew = hospEvolucion.getHospEvoPosologias();
            List<HospEvoProcedimiento> hospEvoProcedimientosOld = persistentHospEvolucion.getHospEvoProcedimientos();
            List<HospEvoProcedimiento> hospEvoProcedimientosNew = hospEvolucion.getHospEvoProcedimientos();
            List<HospEvoEgreso> hospEvoEgresoOld = persistentHospEvolucion.getHospEvoEgreso();
            List<HospEvoEgreso> hospEvoEgresoNew = hospEvolucion.getHospEvoEgreso();
            List<String> illegalOrphanMessages = null;
            for (HospEvoMedidasg hospEvoMedidasgsOldHospEvoMedidasg : hospEvoMedidasgsOld) {
                if (!hospEvoMedidasgsNew.contains(hospEvoMedidasgsOldHospEvoMedidasg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospEvoMedidasg " + hospEvoMedidasgsOldHospEvoMedidasg + " since its idHospEvolucion field is not nullable.");
                }
            }
            for (HospEvoInterconsulta hospEvoInterconsultasOldHospEvoInterconsulta : hospEvoInterconsultasOld) {
                if (!hospEvoInterconsultasNew.contains(hospEvoInterconsultasOldHospEvoInterconsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospEvoInterconsulta " + hospEvoInterconsultasOldHospEvoInterconsulta + " since its idHospEvolucion field is not nullable.");
                }
            }
            for (HospEvoPosologia hospEvoPosologiasOldHospEvoPosologia : hospEvoPosologiasOld) {
                if (!hospEvoPosologiasNew.contains(hospEvoPosologiasOldHospEvoPosologia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospEvoPosologia " + hospEvoPosologiasOldHospEvoPosologia + " since its idHospEvolucion field is not nullable.");
                }
            }
            for (HospEvoProcedimiento hospEvoProcedimientosOldHospEvoProcedimiento : hospEvoProcedimientosOld) {
                if (!hospEvoProcedimientosNew.contains(hospEvoProcedimientosOldHospEvoProcedimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospEvoProcedimiento " + hospEvoProcedimientosOldHospEvoProcedimiento + " since its idHospEvolucion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospEvoMedidasg> attachedHospEvoMedidasgsNew = new ArrayList<HospEvoMedidasg>();
            for (HospEvoMedidasg hospEvoMedidasgsNewHospEvoMedidasgToAttach : hospEvoMedidasgsNew) {
                hospEvoMedidasgsNewHospEvoMedidasgToAttach = em.getReference(hospEvoMedidasgsNewHospEvoMedidasgToAttach.getClass(), hospEvoMedidasgsNewHospEvoMedidasgToAttach.getId());
                attachedHospEvoMedidasgsNew.add(hospEvoMedidasgsNewHospEvoMedidasgToAttach);
            }
            hospEvoMedidasgsNew = attachedHospEvoMedidasgsNew;
            hospEvolucion.setHospEvoMedidasgs(hospEvoMedidasgsNew);
            List<HospEvoInterconsulta> attachedHospEvoInterconsultasNew = new ArrayList<HospEvoInterconsulta>();
            for (HospEvoInterconsulta hospEvoInterconsultasNewHospEvoInterconsultaToAttach : hospEvoInterconsultasNew) {
                hospEvoInterconsultasNewHospEvoInterconsultaToAttach = em.getReference(hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getClass(), hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getId());
                attachedHospEvoInterconsultasNew.add(hospEvoInterconsultasNewHospEvoInterconsultaToAttach);
            }
            hospEvoInterconsultasNew = attachedHospEvoInterconsultasNew;
            hospEvolucion.setHospEvoInterconsultas(hospEvoInterconsultasNew);
            List<HospEvoPosologia> attachedHospEvoPosologiasNew = new ArrayList<HospEvoPosologia>();
            for (HospEvoPosologia hospEvoPosologiasNewHospEvoPosologiaToAttach : hospEvoPosologiasNew) {
                hospEvoPosologiasNewHospEvoPosologiaToAttach = em.getReference(hospEvoPosologiasNewHospEvoPosologiaToAttach.getClass(), hospEvoPosologiasNewHospEvoPosologiaToAttach.getId());
                attachedHospEvoPosologiasNew.add(hospEvoPosologiasNewHospEvoPosologiaToAttach);
            }
            hospEvoPosologiasNew = attachedHospEvoPosologiasNew;
            hospEvolucion.setHospEvoPosologias(hospEvoPosologiasNew);
            List<HospEvoProcedimiento> attachedHospEvoProcedimientosNew = new ArrayList<HospEvoProcedimiento>();
            for (HospEvoProcedimiento hospEvoProcedimientosNewHospEvoProcedimientoToAttach : hospEvoProcedimientosNew) {
                hospEvoProcedimientosNewHospEvoProcedimientoToAttach = em.getReference(hospEvoProcedimientosNewHospEvoProcedimientoToAttach.getClass(), hospEvoProcedimientosNewHospEvoProcedimientoToAttach.getId());
                attachedHospEvoProcedimientosNew.add(hospEvoProcedimientosNewHospEvoProcedimientoToAttach);
            }
            hospEvoProcedimientosNew = attachedHospEvoProcedimientosNew;
            hospEvolucion.setHospEvoProcedimientos(hospEvoProcedimientosNew);
            List<HospEvoEgreso> attachedHospEvoEgresoNew = new ArrayList<HospEvoEgreso>();
            for (HospEvoEgreso hospEvoEgresoNewHospEvoEgresoToAttach : hospEvoEgresoNew) {
                hospEvoEgresoNewHospEvoEgresoToAttach = em.getReference(hospEvoEgresoNewHospEvoEgresoToAttach.getClass(), hospEvoEgresoNewHospEvoEgresoToAttach.getId());
                attachedHospEvoEgresoNew.add(hospEvoEgresoNewHospEvoEgresoToAttach);
            }
            hospEvoEgresoNew = attachedHospEvoEgresoNew;
            hospEvolucion.setHospEvoEgreso(hospEvoEgresoNew);
            hospEvolucion = em.merge(hospEvolucion);
            for (HospEvoMedidasg hospEvoMedidasgsNewHospEvoMedidasg : hospEvoMedidasgsNew) {
                if (!hospEvoMedidasgsOld.contains(hospEvoMedidasgsNewHospEvoMedidasg)) {
                    HospEvolucion oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg = hospEvoMedidasgsNewHospEvoMedidasg.getIdHospEvolucion();
                    hospEvoMedidasgsNewHospEvoMedidasg.setIdHospEvolucion(hospEvolucion);
                    hospEvoMedidasgsNewHospEvoMedidasg = em.merge(hospEvoMedidasgsNewHospEvoMedidasg);
                    if (oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg != null && !oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg.equals(hospEvolucion)) {
                        oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg.getHospEvoMedidasgs().remove(hospEvoMedidasgsNewHospEvoMedidasg);
                        oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg = em.merge(oldIdHospEvolucionOfHospEvoMedidasgsNewHospEvoMedidasg);
                    }
                }
            }
            for (HospEvoInterconsulta hospEvoInterconsultasNewHospEvoInterconsulta : hospEvoInterconsultasNew) {
                if (!hospEvoInterconsultasOld.contains(hospEvoInterconsultasNewHospEvoInterconsulta)) {
                    HospEvolucion oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta = hospEvoInterconsultasNewHospEvoInterconsulta.getIdHospEvolucion();
                    hospEvoInterconsultasNewHospEvoInterconsulta.setIdHospEvolucion(hospEvolucion);
                    hospEvoInterconsultasNewHospEvoInterconsulta = em.merge(hospEvoInterconsultasNewHospEvoInterconsulta);
                    if (oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta != null && !oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta.equals(hospEvolucion)) {
                        oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta.getHospEvoInterconsultas().remove(hospEvoInterconsultasNewHospEvoInterconsulta);
                        oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta = em.merge(oldIdHospEvolucionOfHospEvoInterconsultasNewHospEvoInterconsulta);
                    }
                }
            }
            for (HospEvoPosologia hospEvoPosologiasNewHospEvoPosologia : hospEvoPosologiasNew) {
                if (!hospEvoPosologiasOld.contains(hospEvoPosologiasNewHospEvoPosologia)) {
                    HospEvolucion oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia = hospEvoPosologiasNewHospEvoPosologia.getIdHospEvolucion();
                    hospEvoPosologiasNewHospEvoPosologia.setIdHospEvolucion(hospEvolucion);
                    hospEvoPosologiasNewHospEvoPosologia = em.merge(hospEvoPosologiasNewHospEvoPosologia);
                    if (oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia != null && !oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia.equals(hospEvolucion)) {
                        oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia.getHospEvoPosologias().remove(hospEvoPosologiasNewHospEvoPosologia);
                        oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia = em.merge(oldIdHospEvolucionOfHospEvoPosologiasNewHospEvoPosologia);
                    }
                }
            }
            for (HospEvoProcedimiento hospEvoProcedimientosNewHospEvoProcedimiento : hospEvoProcedimientosNew) {
                if (!hospEvoProcedimientosOld.contains(hospEvoProcedimientosNewHospEvoProcedimiento)) {
                    HospEvolucion oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento = hospEvoProcedimientosNewHospEvoProcedimiento.getIdHospEvolucion();
                    hospEvoProcedimientosNewHospEvoProcedimiento.setIdHospEvolucion(hospEvolucion);
                    hospEvoProcedimientosNewHospEvoProcedimiento = em.merge(hospEvoProcedimientosNewHospEvoProcedimiento);
                    if (oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento != null && !oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento.equals(hospEvolucion)) {
                        oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento.getHospEvoProcedimientos().remove(hospEvoProcedimientosNewHospEvoProcedimiento);
                        oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento = em.merge(oldIdHospEvolucionOfHospEvoProcedimientosNewHospEvoProcedimiento);
                    }
                }
            }
            for (HospEvoEgreso hospEvoEgresoOldHospEvoEgreso : hospEvoEgresoOld) {
                if (!hospEvoEgresoNew.contains(hospEvoEgresoOldHospEvoEgreso)) {
                    hospEvoEgresoOldHospEvoEgreso.setIdHospEvolucion(null);
                    hospEvoEgresoOldHospEvoEgreso = em.merge(hospEvoEgresoOldHospEvoEgreso);
                }
            }
            for (HospEvoEgreso hospEvoEgresoNewHospEvoEgreso : hospEvoEgresoNew) {
                if (!hospEvoEgresoOld.contains(hospEvoEgresoNewHospEvoEgreso)) {
                    HospEvolucion oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso = hospEvoEgresoNewHospEvoEgreso.getIdHospEvolucion();
                    hospEvoEgresoNewHospEvoEgreso.setIdHospEvolucion(hospEvolucion);
                    hospEvoEgresoNewHospEvoEgreso = em.merge(hospEvoEgresoNewHospEvoEgreso);
                    if (oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso != null && !oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso.equals(hospEvolucion)) {
                        oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso.getHospEvoEgreso().remove(hospEvoEgresoNewHospEvoEgreso);
                        oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso = em.merge(oldIdHospEvolucionOfHospEvoEgresoNewHospEvoEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvolucion.getId();
                if (findHospEvolucion(id) == null) {
                    throw new NonexistentEntityException("The hospEvolucion with id " + id + " no longer exists.");
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
            HospEvolucion hospEvolucion;
            try {
                hospEvolucion = em.getReference(HospEvolucion.class, id);
                hospEvolucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvolucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospEvoMedidasg> hospEvoMedidasgsOrphanCheck = hospEvolucion.getHospEvoMedidasgs();
            for (HospEvoMedidasg hospEvoMedidasgsOrphanCheckHospEvoMedidasg : hospEvoMedidasgsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospEvolucion (" + hospEvolucion + ") cannot be destroyed since the HospEvoMedidasg " + hospEvoMedidasgsOrphanCheckHospEvoMedidasg + " in its hospEvoMedidasgs field has a non-nullable idHospEvolucion field.");
            }
            List<HospEvoInterconsulta> hospEvoInterconsultasOrphanCheck = hospEvolucion.getHospEvoInterconsultas();
            for (HospEvoInterconsulta hospEvoInterconsultasOrphanCheckHospEvoInterconsulta : hospEvoInterconsultasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospEvolucion (" + hospEvolucion + ") cannot be destroyed since the HospEvoInterconsulta " + hospEvoInterconsultasOrphanCheckHospEvoInterconsulta + " in its hospEvoInterconsultas field has a non-nullable idHospEvolucion field.");
            }
            List<HospEvoPosologia> hospEvoPosologiasOrphanCheck = hospEvolucion.getHospEvoPosologias();
            for (HospEvoPosologia hospEvoPosologiasOrphanCheckHospEvoPosologia : hospEvoPosologiasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospEvolucion (" + hospEvolucion + ") cannot be destroyed since the HospEvoPosologia " + hospEvoPosologiasOrphanCheckHospEvoPosologia + " in its hospEvoPosologias field has a non-nullable idHospEvolucion field.");
            }
            List<HospEvoProcedimiento> hospEvoProcedimientosOrphanCheck = hospEvolucion.getHospEvoProcedimientos();
            for (HospEvoProcedimiento hospEvoProcedimientosOrphanCheckHospEvoProcedimiento : hospEvoProcedimientosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospEvolucion (" + hospEvolucion + ") cannot be destroyed since the HospEvoProcedimiento " + hospEvoProcedimientosOrphanCheckHospEvoProcedimiento + " in its hospEvoProcedimientos field has a non-nullable idHospEvolucion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospEvoEgreso> hospEvoEgreso = hospEvolucion.getHospEvoEgreso();
            for (HospEvoEgreso hospEvoEgresoHospEvoEgreso : hospEvoEgreso) {
                hospEvoEgresoHospEvoEgreso.setIdHospEvolucion(null);
                hospEvoEgresoHospEvoEgreso = em.merge(hospEvoEgresoHospEvoEgreso);
            }
            em.remove(hospEvolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvolucion> findHospEvolucionEntities() {
        return findHospEvolucionEntities(true, -1, -1);
    }

    public List<HospEvolucion> findHospEvolucionEntities(int maxResults, int firstResult) {
        return findHospEvolucionEntities(false, maxResults, firstResult);
    }

    private List<HospEvolucion> findHospEvolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvolucion.class));
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

    public HospEvolucion findHospEvolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvolucion> rt = cq.from(HospEvolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-generado
    public List<HospEvolucion> FindHospEvolucions(HospHistoriac ihc) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HospEvolucion h WHERE h.idHospHistoriac = :hc AND h.estado <> '0' ORDER BY h.fechaEvo ASC")
                    .setParameter("hc", ihc)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Long CountInterconsultas(HospHistoriac ihc, StaticEspecialidades se) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HospEvolucion h WHERE h.idHospHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1'")
                    .setParameter("ih", ihc)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long CountInterconsultasGeneradas(HospHistoriac ihc, StaticEspecialidades se) {
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HospEvolucion h WHERE h.idHospHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1' AND h.tipo = '1'")
                    .setParameter("ih", ihc)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Object getHcuEvolucionCount(HospHistoriac historia) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(e.id) FROM HospEvolucion e WHERE e.idHospHistoriac.id=:idh AND e.estado='4'");
        Q.setParameter("idh", historia.getId());
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }

    public HospEvolucion getEntidadEvolucionEgreso(HospHistoriac h) {
        HospEvolucion evo = null;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT ev FROM HospEvolucion ev WHERE (ev.idHospHistoriac.id=:hh AND ev.estado='4')");
        Q.setParameter("hh", h.getId());
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            evo = (HospEvolucion) results.get(0);
        } else {
            evo = null;
        }
        return evo;
    }

    public List<HospEvolucion> getEvoluciones(HospHistoriac h) {
        EntityManager em = getEntityManager();
        Query Q = em.createQuery("SELECT e FROM HospEvolucion e WHERE e.idHospHistoriac.id=:ht AND (e.estado='2' OR e.estado='4') ");
        Q.setParameter("ht", h.getId());
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

}
