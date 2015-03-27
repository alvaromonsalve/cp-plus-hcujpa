
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciEvoMedidasg;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciEvoInterconsulta;
import entidades_EJB.UciEvoPosologia;
import entidades_EJB.UciEvoProcedimiento;
import entidades_EJB.UciEvoEgreso;
import entidades_EJB.UciEvolucion;
import entidades_EJB.UciHistoriac;
import entidades_EJB.StaticEspecialidades;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UciEvolucionJpaController implements Serializable {

    public UciEvolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvolucion uciEvolucion) {
        if (uciEvolucion.getUciEvoMedidasgs() == null) {
            uciEvolucion.setUciEvoMedidasgs(new ArrayList<UciEvoMedidasg>());
        }
        if (uciEvolucion.getUciEvoInterconsultas() == null) {
            uciEvolucion.setUciEvoInterconsultas(new ArrayList<UciEvoInterconsulta>());
        }
        if (uciEvolucion.getUciEvoPosologias() == null) {
            uciEvolucion.setUciEvoPosologias(new ArrayList<UciEvoPosologia>());
        }
        if (uciEvolucion.getUciEvoProcedimientos() == null) {
            uciEvolucion.setUciEvoProcedimientos(new ArrayList<UciEvoProcedimiento>());
        }
        if (uciEvolucion.getUciEvoEgreso() == null) {
            uciEvolucion.setUciEvoEgreso(new ArrayList<UciEvoEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciEvoMedidasg> attachedUciEvoMedidasgs = new ArrayList<UciEvoMedidasg>();
            for (UciEvoMedidasg uciEvoMedidasgsEvoMedidasgToAttach : uciEvolucion.getUciEvoMedidasgs()) {
                uciEvoMedidasgsEvoMedidasgToAttach = em.getReference(uciEvoMedidasgsEvoMedidasgToAttach.getClass(), uciEvoMedidasgsEvoMedidasgToAttach.getId());
                attachedUciEvoMedidasgs.add(uciEvoMedidasgsEvoMedidasgToAttach);
            }
            uciEvolucion.setUciEvoMedidasgs(attachedUciEvoMedidasgs);
            List<UciEvoInterconsulta> attachedUciEvoInterconsultas = new ArrayList<UciEvoInterconsulta>();
            for (UciEvoInterconsulta uciEvoInterconsultasEvoInterconsultaToAttach : uciEvolucion.getUciEvoInterconsultas()) {
                uciEvoInterconsultasEvoInterconsultaToAttach = em.getReference(uciEvoInterconsultasEvoInterconsultaToAttach.getClass(), uciEvoInterconsultasEvoInterconsultaToAttach.getId());
                attachedUciEvoInterconsultas.add(uciEvoInterconsultasEvoInterconsultaToAttach);
            }
            uciEvolucion.setUciEvoInterconsultas(attachedUciEvoInterconsultas);
            List<UciEvoPosologia> attachedUciEvoPosologias = new ArrayList<UciEvoPosologia>();
            for (UciEvoPosologia UciEvoPosologiasEvoPosologiaToAttach : uciEvolucion.getUciEvoPosologias()) {
                UciEvoPosologiasEvoPosologiaToAttach = em.getReference(UciEvoPosologiasEvoPosologiaToAttach.getClass(), UciEvoPosologiasEvoPosologiaToAttach.getId());
                attachedUciEvoPosologias.add(UciEvoPosologiasEvoPosologiaToAttach);
            }
            uciEvolucion.setUciEvoPosologias(attachedUciEvoPosologias);
            List<UciEvoProcedimiento> attachedUciEvoProcedimientos = new ArrayList<UciEvoProcedimiento>();
            for (UciEvoProcedimiento uciEvoProcedimientosEvoProcedimientoToAttach : uciEvolucion.getUciEvoProcedimientos()) {
                uciEvoProcedimientosEvoProcedimientoToAttach = em.getReference(uciEvoProcedimientosEvoProcedimientoToAttach.getClass(), uciEvoProcedimientosEvoProcedimientoToAttach.getId());
                attachedUciEvoProcedimientos.add(uciEvoProcedimientosEvoProcedimientoToAttach);
            }
            uciEvolucion.setUciEvoProcedimientos(attachedUciEvoProcedimientos);
            List<UciEvoEgreso> attachedUciEvoEgreso = new ArrayList<UciEvoEgreso>();
            for (UciEvoEgreso uciEvoEgresoEvoEgresoToAttach : uciEvolucion.getUciEvoEgreso()) {
                uciEvoEgresoEvoEgresoToAttach = em.getReference(uciEvoEgresoEvoEgresoToAttach.getClass(), uciEvoEgresoEvoEgresoToAttach.getId());
                attachedUciEvoEgreso.add(uciEvoEgresoEvoEgresoToAttach);
            }
            uciEvolucion.setUciEvoEgreso(attachedUciEvoEgreso);
            em.persist(uciEvolucion);
            for (UciEvoMedidasg uciEvoMedidasgsEvoMedidasg : uciEvolucion.getUciEvoMedidasgs()) {
                UciEvolucion oldIdUciEvolucionOfUciEvoMedidasgUciEvoMedidasg = uciEvoMedidasgsEvoMedidasg.getIdUciEvolucion();
                uciEvoMedidasgsEvoMedidasg.setIdUciEvolucion(uciEvolucion);
                uciEvoMedidasgsEvoMedidasg = em.merge(uciEvoMedidasgsEvoMedidasg);
                if (oldIdUciEvolucionOfUciEvoMedidasgUciEvoMedidasg != null) {
                    oldIdUciEvolucionOfUciEvoMedidasgUciEvoMedidasg.getUciEvoMedidasgs().remove(uciEvoMedidasgsEvoMedidasg);
                    oldIdUciEvolucionOfUciEvoMedidasgUciEvoMedidasg = em.merge(oldIdUciEvolucionOfUciEvoMedidasgUciEvoMedidasg);
                }
            }
            for (UciEvoInterconsulta uciEvoInterconsultasEvoInterconsulta : uciEvolucion.getUciEvoInterconsultas()) {
                UciEvolucion oldIdUciEvolucionOfUciEvoInterconsultasUciEvoInterconsulta = uciEvoInterconsultasEvoInterconsulta.getIdUciEvolucion();
                uciEvoInterconsultasEvoInterconsulta.setIdUciEvolucion(uciEvolucion);
                uciEvoInterconsultasEvoInterconsulta = em.merge(uciEvoInterconsultasEvoInterconsulta);
                if (oldIdUciEvolucionOfUciEvoInterconsultasUciEvoInterconsulta != null) {
                    oldIdUciEvolucionOfUciEvoInterconsultasUciEvoInterconsulta.getUciEvoInterconsultas().remove(uciEvoInterconsultasEvoInterconsulta);
                    oldIdUciEvolucionOfUciEvoInterconsultasUciEvoInterconsulta = em.merge(oldIdUciEvolucionOfUciEvoInterconsultasUciEvoInterconsulta);
                }
            }
            for (UciEvoPosologia uciEvoPosologiasUciEvoPosologia : uciEvolucion.getUciEvoPosologias()) {
                UciEvolucion oldIdUciEvolucionOfUciEvoPosologiasUciEvoPosologia = uciEvoPosologiasUciEvoPosologia.getIdUciEvolucion();
                uciEvoPosologiasUciEvoPosologia.setIdUciEvolucion(uciEvolucion);
                uciEvoPosologiasUciEvoPosologia = em.merge(uciEvoPosologiasUciEvoPosologia);
                if (oldIdUciEvolucionOfUciEvoPosologiasUciEvoPosologia != null) {
                    oldIdUciEvolucionOfUciEvoPosologiasUciEvoPosologia.getUciEvoPosologias().remove(uciEvoPosologiasUciEvoPosologia);
                    oldIdUciEvolucionOfUciEvoPosologiasUciEvoPosologia = em.merge(oldIdUciEvolucionOfUciEvoPosologiasUciEvoPosologia);
                }
            }
            for (UciEvoProcedimiento uciEvoProcedimientosUciEvoProcedimiento : uciEvolucion.getUciEvoProcedimientos()) {
                UciEvolucion oldIdUciEvolucionOfUciEvoProcedimientosUciEvoProcedimiento = uciEvoProcedimientosUciEvoProcedimiento.getIdUciEvolucion();
                uciEvoProcedimientosUciEvoProcedimiento.setIdUciEvolucion(uciEvolucion);
                uciEvoProcedimientosUciEvoProcedimiento = em.merge(uciEvoProcedimientosUciEvoProcedimiento);
                if (oldIdUciEvolucionOfUciEvoProcedimientosUciEvoProcedimiento != null) {
                    oldIdUciEvolucionOfUciEvoProcedimientosUciEvoProcedimiento.getUciEvoProcedimientos().remove(uciEvoProcedimientosUciEvoProcedimiento);
                    oldIdUciEvolucionOfUciEvoProcedimientosUciEvoProcedimiento = em.merge(oldIdUciEvolucionOfUciEvoProcedimientosUciEvoProcedimiento);
                }
            }
            for (UciEvoEgreso uciEvoEgresoUciEvoEgreso : uciEvolucion.getUciEvoEgreso()) {
                UciEvolucion oldIdUciEvolucionOfUciEvoEgresoUciEvoEgreso = uciEvoEgresoUciEvoEgreso.getIdUciEvolucion();
                uciEvoEgresoUciEvoEgreso.setIdUciEvolucion(uciEvolucion);
                uciEvoEgresoUciEvoEgreso = em.merge(uciEvoEgresoUciEvoEgreso);
                if (oldIdUciEvolucionOfUciEvoEgresoUciEvoEgreso != null) {
                    oldIdUciEvolucionOfUciEvoEgresoUciEvoEgreso.getUciEvoEgreso().remove(uciEvoEgresoUciEvoEgreso);
                    oldIdUciEvolucionOfUciEvoEgresoUciEvoEgreso = em.merge(oldIdUciEvolucionOfUciEvoEgresoUciEvoEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvolucion uciEvolucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciEvolucion persistentoUciEvolucion = em.find(UciEvolucion.class, uciEvolucion.getId());
            List<UciEvoMedidasg> uciEvoMedidasgsOld = persistentoUciEvolucion.getUciEvoMedidasgs();
            List<UciEvoMedidasg> uciEvoMedidasgsNew = uciEvolucion.getUciEvoMedidasgs();
            List<UciEvoInterconsulta> uciEvoInterconsultasOld = persistentoUciEvolucion.getUciEvoInterconsultas();
            List<UciEvoInterconsulta> uciEvoInterconsultasNew = uciEvolucion.getUciEvoInterconsultas();
            List<UciEvoPosologia> uciEvoPosologiasOld = persistentoUciEvolucion.getUciEvoPosologias();
            List<UciEvoPosologia> uciEvoPosologiasNew = uciEvolucion.getUciEvoPosologias();
            List<UciEvoProcedimiento> uciEvoProcedimientosOld = persistentoUciEvolucion.getUciEvoProcedimientos();
            List<UciEvoProcedimiento> uciEvoProcedimientosNew = uciEvolucion.getUciEvoProcedimientos();
            List<UciEvoEgreso> uciEvoEgresoOld = persistentoUciEvolucion.getUciEvoEgreso();
            List<UciEvoEgreso> uciEvoEgresoNew = uciEvolucion.getUciEvoEgreso();
            List<String> illegalOrphanMessages = null;
            for (UciEvoMedidasg uciEvoMedidasgsOldUciEvoMedidasg : uciEvoMedidasgsOld) {
                if (!uciEvoMedidasgsNew.contains(uciEvoMedidasgsOldUciEvoMedidasg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciEvoMedidasg " + uciEvoMedidasgsOldUciEvoMedidasg + " since its idUciEvolucion field is not nullable.");
                }
            }
            for (UciEvoInterconsulta uciEvoInterconsultasOldUciEvoInterconsulta : uciEvoInterconsultasOld) {
                if (!uciEvoInterconsultasNew.contains(uciEvoInterconsultasOldUciEvoInterconsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciEvoInterconsulta " + uciEvoInterconsultasOldUciEvoInterconsulta + " since its idUciEvolucion field is not nullable.");
                }
            }
            for (UciEvoPosologia uciEvoPosologiasOldUciEvoPosologia : uciEvoPosologiasOld) {
                if (!uciEvoPosologiasNew.contains(uciEvoPosologiasOldUciEvoPosologia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciEvoPosologia " + uciEvoPosologiasOldUciEvoPosologia + " since its idUciEvolucion field is not nullable.");
                }
            }
            for (UciEvoProcedimiento uciEvoProcedimientosOldUciEvoProcedimiento : uciEvoProcedimientosOld) {
                if (!uciEvoProcedimientosNew.contains(uciEvoProcedimientosOldUciEvoProcedimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciEvoProcedimiento " + uciEvoProcedimientosOldUciEvoProcedimiento + " since its idUciEvolucion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciEvoMedidasg> attachedUciEvoMedidasgsNew = new ArrayList<UciEvoMedidasg>();
            for (UciEvoMedidasg uciEvoMedidasgsNewUciEvoMedidasgToAttach : uciEvoMedidasgsNew) {
                uciEvoMedidasgsNewUciEvoMedidasgToAttach = em.getReference(uciEvoMedidasgsNewUciEvoMedidasgToAttach.getClass(), uciEvoMedidasgsNewUciEvoMedidasgToAttach.getId());
                attachedUciEvoMedidasgsNew.add(uciEvoMedidasgsNewUciEvoMedidasgToAttach);
            }
            uciEvoMedidasgsNew = attachedUciEvoMedidasgsNew;
            uciEvolucion.setUciEvoMedidasgs(uciEvoMedidasgsNew);
            List<UciEvoInterconsulta> attachedUciEvoInterconsultasNew = new ArrayList<UciEvoInterconsulta>();
            for (UciEvoInterconsulta hospEvoInterconsultasNewHospEvoInterconsultaToAttach : uciEvoInterconsultasNew) {
                hospEvoInterconsultasNewHospEvoInterconsultaToAttach = em.getReference(hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getClass(), hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getId());
                attachedUciEvoInterconsultasNew.add(hospEvoInterconsultasNewHospEvoInterconsultaToAttach);
            }
            uciEvoInterconsultasNew = attachedUciEvoInterconsultasNew;
            uciEvolucion.setUciEvoInterconsultas(uciEvoInterconsultasNew);
            List<UciEvoPosologia> attachedUciEvoPosologiasNew = new ArrayList<UciEvoPosologia>();
            for (UciEvoPosologia uciEvoPosologiasNewUciEvoPosologiaToAttach : uciEvoPosologiasNew) {
                uciEvoPosologiasNewUciEvoPosologiaToAttach = em.getReference(uciEvoPosologiasNewUciEvoPosologiaToAttach.getClass(), uciEvoPosologiasNewUciEvoPosologiaToAttach.getId());
                attachedUciEvoPosologiasNew.add(uciEvoPosologiasNewUciEvoPosologiaToAttach);
            }
            uciEvoPosologiasNew = attachedUciEvoPosologiasNew;
            uciEvolucion.setUciEvoPosologias(uciEvoPosologiasNew);
            List<UciEvoProcedimiento> attachedUciEvoProcedimientosNew = new ArrayList<UciEvoProcedimiento>();
            for (UciEvoProcedimiento uciEvoProcedimientosNewUciEvoProcedimientoToAttach : uciEvoProcedimientosNew) {
                uciEvoProcedimientosNewUciEvoProcedimientoToAttach = em.getReference(uciEvoProcedimientosNewUciEvoProcedimientoToAttach.getClass(), uciEvoProcedimientosNewUciEvoProcedimientoToAttach.getId());
                attachedUciEvoProcedimientosNew.add(uciEvoProcedimientosNewUciEvoProcedimientoToAttach);
            }
            uciEvoProcedimientosNew = attachedUciEvoProcedimientosNew;
            uciEvolucion.setUciEvoProcedimientos(uciEvoProcedimientosNew);
            List<UciEvoEgreso> attachedUciEvoEgresoNew = new ArrayList<UciEvoEgreso>();
            for (UciEvoEgreso uciEvoEgresoNewUciEvoEgresoToAttach : uciEvoEgresoNew) {
                uciEvoEgresoNewUciEvoEgresoToAttach = em.getReference(uciEvoEgresoNewUciEvoEgresoToAttach.getClass(), uciEvoEgresoNewUciEvoEgresoToAttach.getId());
                attachedUciEvoEgresoNew.add(uciEvoEgresoNewUciEvoEgresoToAttach);
            }
            uciEvoEgresoNew = attachedUciEvoEgresoNew;
            uciEvolucion.setUciEvoEgreso(uciEvoEgresoNew);
            uciEvolucion = em.merge(uciEvolucion);
            for (UciEvoMedidasg uciEvoMedidasgsNewUciEvoMedidasg : uciEvoMedidasgsNew) {
                if (!uciEvoMedidasgsOld.contains(uciEvoMedidasgsNewUciEvoMedidasg)) {
                    UciEvolucion oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg = uciEvoMedidasgsNewUciEvoMedidasg.getIdUciEvolucion();
                    uciEvoMedidasgsNewUciEvoMedidasg.setIdUciEvolucion(uciEvolucion);
                    uciEvoMedidasgsNewUciEvoMedidasg = em.merge(uciEvoMedidasgsNewUciEvoMedidasg);
                    if (oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg != null && !oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg.equals(uciEvolucion)) {
                        oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg.getUciEvoMedidasgs().remove(uciEvoMedidasgsNewUciEvoMedidasg);
                        oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg = em.merge(oldIdUciEvolucionOfUciEvoMedidasgsNewUciEvoMedidasg);
                    }
                }
            }
            for (UciEvoInterconsulta uciEvoInterconsultasNewUciEvoInterconsulta : uciEvoInterconsultasNew) {
                if (!uciEvoInterconsultasOld.contains(uciEvoInterconsultasNewUciEvoInterconsulta)) {
                    UciEvolucion oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta = uciEvoInterconsultasNewUciEvoInterconsulta.getIdUciEvolucion();
                    uciEvoInterconsultasNewUciEvoInterconsulta.setIdUciEvolucion(uciEvolucion);
                    uciEvoInterconsultasNewUciEvoInterconsulta = em.merge(uciEvoInterconsultasNewUciEvoInterconsulta);
                    if (oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta != null && !oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta.equals(uciEvolucion)) {
                        oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta.getUciEvoInterconsultas().remove(uciEvoInterconsultasNewUciEvoInterconsulta);
                        oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta = em.merge(oldIdUciEvolucionOfUciEvoInterconsultasNewUciEvoInterconsulta);
                    }
                }
            }
            for (UciEvoPosologia uciEvoPosologiasNewUciEvoPosologia : uciEvoPosologiasNew) {
                if (!uciEvoPosologiasOld.contains(uciEvoPosologiasNewUciEvoPosologia)) {
                    UciEvolucion oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia = uciEvoPosologiasNewUciEvoPosologia.getIdUciEvolucion();
                    uciEvoPosologiasNewUciEvoPosologia.setIdUciEvolucion(uciEvolucion);
                    uciEvoPosologiasNewUciEvoPosologia = em.merge(uciEvoPosologiasNewUciEvoPosologia);
                    if (oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia != null && !oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia.equals(uciEvolucion)) {
                        oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia.getUciEvoPosologias().remove(uciEvoPosologiasNewUciEvoPosologia);
                        oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia = em.merge(oldIdUciEvolucionOfUciEvoPosologiasNewUciEvoPosologia);
                    }
                }
            }
            for (UciEvoProcedimiento uciEvoProcedimientosNewUciEvoProcedimiento : uciEvoProcedimientosNew) {
                if (!uciEvoProcedimientosOld.contains(uciEvoProcedimientosNewUciEvoProcedimiento)) {
                    UciEvolucion oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento = uciEvoProcedimientosNewUciEvoProcedimiento.getIdUciEvolucion();
                    uciEvoProcedimientosNewUciEvoProcedimiento.setIdUciEvolucion(uciEvolucion);
                    uciEvoProcedimientosNewUciEvoProcedimiento = em.merge(uciEvoProcedimientosNewUciEvoProcedimiento);
                    if (oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento != null && !oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento.equals(uciEvolucion)) {
                        oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento.getUciEvoProcedimientos().remove(uciEvoProcedimientosNewUciEvoProcedimiento);
                        oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento = em.merge(oldIdUciEvolucionOfUciEvoProcedimientosNewUciEvoProcedimiento);
                    }
                }
            }
            for (UciEvoEgreso uciEvoEgresoOldUciEvoEgreso : uciEvoEgresoOld) {
                if (!uciEvoEgresoNew.contains(uciEvoEgresoOldUciEvoEgreso)) {
                    uciEvoEgresoOldUciEvoEgreso.setIdUciEvolucion(null);
                    uciEvoEgresoOldUciEvoEgreso = em.merge(uciEvoEgresoOldUciEvoEgreso);
                }
            }
            for (UciEvoEgreso uciEvoEgresoNewUciEvoEgreso : uciEvoEgresoNew) {
                if (!uciEvoEgresoOld.contains(uciEvoEgresoNewUciEvoEgreso)) {
                    UciEvolucion oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso = uciEvoEgresoNewUciEvoEgreso.getIdUciEvolucion();
                    uciEvoEgresoNewUciEvoEgreso.setIdUciEvolucion(uciEvolucion);
                    uciEvoEgresoNewUciEvoEgreso = em.merge(uciEvoEgresoNewUciEvoEgreso);
                    if (oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso != null && !oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso.equals(uciEvolucion)) {
                        oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso.getUciEvoEgreso().remove(uciEvoEgresoNewUciEvoEgreso);
                        oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso = em.merge(oldIdUciEvolucionOfUciEvoEgresoNewUciEvoEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvolucion.getId();
                if (findUciEvolucion(id) == null) {
                    throw new NonexistentEntityException("The uciEvolucion with id " + id + " no longer exists.");
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
            UciEvolucion uciEvolucion;
            try {
                uciEvolucion = em.getReference(UciEvolucion.class, id);
                uciEvolucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvolucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciEvoMedidasg> uciEvoMedidasgsOrphanCheck = uciEvolucion.getUciEvoMedidasgs();
            for (UciEvoMedidasg uciEvoMedidasgsOrphanCheckUciEvoMedidasg : uciEvoMedidasgsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciEvolucion (" + uciEvolucion + ") cannot be destroyed since the UciEvoMedidasg " + uciEvoMedidasgsOrphanCheckUciEvoMedidasg + " in its hospEvoMedidasgs field has a non-nullable idHospEvolucion field.");
            }
            List<UciEvoInterconsulta> uciEvoInterconsultasOrphanCheck = uciEvolucion.getUciEvoInterconsultas();
            for (UciEvoInterconsulta uciEvoInterconsultasOrphanCheckUciEvoInterconsulta : uciEvoInterconsultasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciEvolucion (" + uciEvolucion + ") cannot be destroyed since the UciEvoInterconsulta " + uciEvoInterconsultasOrphanCheckUciEvoInterconsulta + " in its hospEvoInterconsultas field has a non-nullable idHospEvolucion field.");
            }
            List<UciEvoPosologia> uciEvoPosologiasOrphanCheck = uciEvolucion.getUciEvoPosologias();
            for (UciEvoPosologia uciEvoPosologiasOrphanCheckUciEvoPosologia : uciEvoPosologiasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciEvolucion (" + uciEvolucion + ") cannot be destroyed since the UciEvoPosologia " + uciEvoPosologiasOrphanCheckUciEvoPosologia + " in its hospEvoPosologias field has a non-nullable idHospEvolucion field.");
            }
            List<UciEvoProcedimiento> uciEvoProcedimientosOrphanCheck = uciEvolucion.getUciEvoProcedimientos();
            for (UciEvoProcedimiento uciEvoProcedimientosOrphanCheckUciEvoProcedimiento : uciEvoProcedimientosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciEvolucion (" + uciEvolucion + ") cannot be destroyed since the UciEvoProcedimiento " + uciEvoProcedimientosOrphanCheckUciEvoProcedimiento + " in its hospEvoProcedimientos field has a non-nullable idHospEvolucion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciEvoEgreso> uciEvoEgreso = uciEvolucion.getUciEvoEgreso();
            for (UciEvoEgreso uciEvoEgresoUciEvoEgreso : uciEvoEgreso) {
                uciEvoEgresoUciEvoEgreso.setIdUciEvolucion(null);
                uciEvoEgresoUciEvoEgreso = em.merge(uciEvoEgresoUciEvoEgreso);
            }
            em.remove(uciEvolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvolucion> findUciEvolucionEntities() {
        return findUciEvolucionEntities(true, -1, -1);
    }

    public List<UciEvolucion> findUciEvolucionEntities(int maxResults, int firstResult) {
        return findUciEvolucionEntities(false, maxResults, firstResult);
    }

    private List<UciEvolucion> findUciEvolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvolucion.class));
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

    public UciEvolucion findUciEvolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvolucion> rt = cq.from(UciEvolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

        //Codigo no Auto-generado
    public List<UciEvolucion> FindUciEvolucions(UciHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UciEvolucion h WHERE h.idUciHistoriac = :hc AND h.estado <> '0' ORDER BY h.fechaEvo ASC")
            .setParameter("hc", ihc)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

    public Long CountInterconsultas(UciHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UciEvolucion h WHERE h.idUciHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

    public Long CountInterconsultasGeneradas(UciHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UciEvolucion h WHERE h.idUciHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1' AND h.tipo = '1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

}
