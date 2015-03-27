
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceEvoMedidasg;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceEvoInterconsulta;
import entidades_EJB.UceEvoPosologia;
import entidades_EJB.UceEvoProcedimiento;
import entidades_EJB.UceEvoEgreso;
import entidades_EJB.UceEvolucion;
import entidades_EJB.UceHistoriac;
import entidades_EJB.StaticEspecialidades;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UceEvolucionJpaController implements Serializable {

    public UceEvolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvolucion uceEvolucion) {
        if (uceEvolucion.getUceEvoMedidasgs() == null) {
            uceEvolucion.setUceEvoMedidasgs(new ArrayList<UceEvoMedidasg>());
        }
        if (uceEvolucion.getUceEvoInterconsultas() == null) {
            uceEvolucion.setUceEvoInterconsultas(new ArrayList<UceEvoInterconsulta>());
        }
        if (uceEvolucion.getUceEvoPosologias() == null) {
            uceEvolucion.setUceEvoPosologias(new ArrayList<UceEvoPosologia>());
        }
        if (uceEvolucion.getUceEvoProcedimientos() == null) {
            uceEvolucion.setUceEvoProcedimientos(new ArrayList<UceEvoProcedimiento>());
        }
        if (uceEvolucion.getUceEvoEgreso() == null) {
            uceEvolucion.setUceEvoEgreso(new ArrayList<UceEvoEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceEvoMedidasg> attachedUceEvoMedidasgs = new ArrayList<UceEvoMedidasg>();
            for (UceEvoMedidasg uceEvoMedidasgsEvoMedidasgToAttach : uceEvolucion.getUceEvoMedidasgs()) {
                uceEvoMedidasgsEvoMedidasgToAttach = em.getReference(uceEvoMedidasgsEvoMedidasgToAttach.getClass(), uceEvoMedidasgsEvoMedidasgToAttach.getId());
                attachedUceEvoMedidasgs.add(uceEvoMedidasgsEvoMedidasgToAttach);
            }
            uceEvolucion.setUceEvoMedidasgs(attachedUceEvoMedidasgs);
            List<UceEvoInterconsulta> attachedUceEvoInterconsultas = new ArrayList<UceEvoInterconsulta>();
            for (UceEvoInterconsulta uceEvoInterconsultasEvoInterconsultaToAttach : uceEvolucion.getUceEvoInterconsultas()) {
                uceEvoInterconsultasEvoInterconsultaToAttach = em.getReference(uceEvoInterconsultasEvoInterconsultaToAttach.getClass(), uceEvoInterconsultasEvoInterconsultaToAttach.getId());
                attachedUceEvoInterconsultas.add(uceEvoInterconsultasEvoInterconsultaToAttach);
            }
            uceEvolucion.setUceEvoInterconsultas(attachedUceEvoInterconsultas);
            List<UceEvoPosologia> attachedUceEvoPosologias = new ArrayList<UceEvoPosologia>();
            for (UceEvoPosologia UceEvoPosologiasEvoPosologiaToAttach : uceEvolucion.getUceEvoPosologias()) {
                UceEvoPosologiasEvoPosologiaToAttach = em.getReference(UceEvoPosologiasEvoPosologiaToAttach.getClass(), UceEvoPosologiasEvoPosologiaToAttach.getId());
                attachedUceEvoPosologias.add(UceEvoPosologiasEvoPosologiaToAttach);
            }
            uceEvolucion.setUceEvoPosologias(attachedUceEvoPosologias);
            List<UceEvoProcedimiento> attachedUceEvoProcedimientos = new ArrayList<UceEvoProcedimiento>();
            for (UceEvoProcedimiento uceEvoProcedimientosEvoProcedimientoToAttach : uceEvolucion.getUceEvoProcedimientos()) {
                uceEvoProcedimientosEvoProcedimientoToAttach = em.getReference(uceEvoProcedimientosEvoProcedimientoToAttach.getClass(), uceEvoProcedimientosEvoProcedimientoToAttach.getId());
                attachedUceEvoProcedimientos.add(uceEvoProcedimientosEvoProcedimientoToAttach);
            }
            uceEvolucion.setUceEvoProcedimientos(attachedUceEvoProcedimientos);
            List<UceEvoEgreso> attachedUceEvoEgreso = new ArrayList<UceEvoEgreso>();
            for (UceEvoEgreso uceEvoEgresoEvoEgresoToAttach : uceEvolucion.getUceEvoEgreso()) {
                uceEvoEgresoEvoEgresoToAttach = em.getReference(uceEvoEgresoEvoEgresoToAttach.getClass(), uceEvoEgresoEvoEgresoToAttach.getId());
                attachedUceEvoEgreso.add(uceEvoEgresoEvoEgresoToAttach);
            }
            uceEvolucion.setUceEvoEgreso(attachedUceEvoEgreso);
            em.persist(uceEvolucion);
            for (UceEvoMedidasg uceEvoMedidasgsEvoMedidasg : uceEvolucion.getUceEvoMedidasgs()) {
                UceEvolucion oldIdUceEvolucionOfUceEvoMedidasgUceEvoMedidasg = uceEvoMedidasgsEvoMedidasg.getIdUceEvolucion();
                uceEvoMedidasgsEvoMedidasg.setIdUceEvolucion(uceEvolucion);
                uceEvoMedidasgsEvoMedidasg = em.merge(uceEvoMedidasgsEvoMedidasg);
                if (oldIdUceEvolucionOfUceEvoMedidasgUceEvoMedidasg != null) {
                    oldIdUceEvolucionOfUceEvoMedidasgUceEvoMedidasg.getUceEvoMedidasgs().remove(uceEvoMedidasgsEvoMedidasg);
                    oldIdUceEvolucionOfUceEvoMedidasgUceEvoMedidasg = em.merge(oldIdUceEvolucionOfUceEvoMedidasgUceEvoMedidasg);
                }
            }
            for (UceEvoInterconsulta uceEvoInterconsultasEvoInterconsulta : uceEvolucion.getUceEvoInterconsultas()) {
                UceEvolucion oldIdUceEvolucionOfUceEvoInterconsultasUceEvoInterconsulta = uceEvoInterconsultasEvoInterconsulta.getIdUceEvolucion();
                uceEvoInterconsultasEvoInterconsulta.setIdUceEvolucion(uceEvolucion);
                uceEvoInterconsultasEvoInterconsulta = em.merge(uceEvoInterconsultasEvoInterconsulta);
                if (oldIdUceEvolucionOfUceEvoInterconsultasUceEvoInterconsulta != null) {
                    oldIdUceEvolucionOfUceEvoInterconsultasUceEvoInterconsulta.getUceEvoInterconsultas().remove(uceEvoInterconsultasEvoInterconsulta);
                    oldIdUceEvolucionOfUceEvoInterconsultasUceEvoInterconsulta = em.merge(oldIdUceEvolucionOfUceEvoInterconsultasUceEvoInterconsulta);
                }
            }
            for (UceEvoPosologia uceEvoPosologiasUceEvoPosologia : uceEvolucion.getUceEvoPosologias()) {
                UceEvolucion oldIdUceEvolucionOfUceEvoPosologiasUceEvoPosologia = uceEvoPosologiasUceEvoPosologia.getIdUceEvolucion();
                uceEvoPosologiasUceEvoPosologia.setIdUceEvolucion(uceEvolucion);
                uceEvoPosologiasUceEvoPosologia = em.merge(uceEvoPosologiasUceEvoPosologia);
                if (oldIdUceEvolucionOfUceEvoPosologiasUceEvoPosologia != null) {
                    oldIdUceEvolucionOfUceEvoPosologiasUceEvoPosologia.getUceEvoPosologias().remove(uceEvoPosologiasUceEvoPosologia);
                    oldIdUceEvolucionOfUceEvoPosologiasUceEvoPosologia = em.merge(oldIdUceEvolucionOfUceEvoPosologiasUceEvoPosologia);
                }
            }
            for (UceEvoProcedimiento uceEvoProcedimientosUceEvoProcedimiento : uceEvolucion.getUceEvoProcedimientos()) {
                UceEvolucion oldIdUceEvolucionOfUceEvoProcedimientosUceEvoProcedimiento = uceEvoProcedimientosUceEvoProcedimiento.getIdUceEvolucion();
                uceEvoProcedimientosUceEvoProcedimiento.setIdUceEvolucion(uceEvolucion);
                uceEvoProcedimientosUceEvoProcedimiento = em.merge(uceEvoProcedimientosUceEvoProcedimiento);
                if (oldIdUceEvolucionOfUceEvoProcedimientosUceEvoProcedimiento != null) {
                    oldIdUceEvolucionOfUceEvoProcedimientosUceEvoProcedimiento.getUceEvoProcedimientos().remove(uceEvoProcedimientosUceEvoProcedimiento);
                    oldIdUceEvolucionOfUceEvoProcedimientosUceEvoProcedimiento = em.merge(oldIdUceEvolucionOfUceEvoProcedimientosUceEvoProcedimiento);
                }
            }
            for (UceEvoEgreso uceEvoEgresoUceEvoEgreso : uceEvolucion.getUceEvoEgreso()) {
                UceEvolucion oldIdUceEvolucionOfUceEvoEgresoUceEvoEgreso = uceEvoEgresoUceEvoEgreso.getIdUceEvolucion();
                uceEvoEgresoUceEvoEgreso.setIdUceEvolucion(uceEvolucion);
                uceEvoEgresoUceEvoEgreso = em.merge(uceEvoEgresoUceEvoEgreso);
                if (oldIdUceEvolucionOfUceEvoEgresoUceEvoEgreso != null) {
                    oldIdUceEvolucionOfUceEvoEgresoUceEvoEgreso.getUceEvoEgreso().remove(uceEvoEgresoUceEvoEgreso);
                    oldIdUceEvolucionOfUceEvoEgresoUceEvoEgreso = em.merge(oldIdUceEvolucionOfUceEvoEgresoUceEvoEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvolucion uceEvolucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceEvolucion persistentoUceEvolucion = em.find(UceEvolucion.class, uceEvolucion.getId());
            List<UceEvoMedidasg> uceEvoMedidasgsOld = persistentoUceEvolucion.getUceEvoMedidasgs();
            List<UceEvoMedidasg> uceEvoMedidasgsNew = uceEvolucion.getUceEvoMedidasgs();
            List<UceEvoInterconsulta> uceEvoInterconsultasOld = persistentoUceEvolucion.getUceEvoInterconsultas();
            List<UceEvoInterconsulta> uceEvoInterconsultasNew = uceEvolucion.getUceEvoInterconsultas();
            List<UceEvoPosologia> uceEvoPosologiasOld = persistentoUceEvolucion.getUceEvoPosologias();
            List<UceEvoPosologia> uceEvoPosologiasNew = uceEvolucion.getUceEvoPosologias();
            List<UceEvoProcedimiento> uceEvoProcedimientosOld = persistentoUceEvolucion.getUceEvoProcedimientos();
            List<UceEvoProcedimiento> uceEvoProcedimientosNew = uceEvolucion.getUceEvoProcedimientos();
            List<UceEvoEgreso> uceEvoEgresoOld = persistentoUceEvolucion.getUceEvoEgreso();
            List<UceEvoEgreso> uceEvoEgresoNew = uceEvolucion.getUceEvoEgreso();
            List<String> illegalOrphanMessages = null;
            for (UceEvoMedidasg uceEvoMedidasgsOldUceEvoMedidasg : uceEvoMedidasgsOld) {
                if (!uceEvoMedidasgsNew.contains(uceEvoMedidasgsOldUceEvoMedidasg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceEvoMedidasg " + uceEvoMedidasgsOldUceEvoMedidasg + " since its idUceEvolucion field is not nullable.");
                }
            }
            for (UceEvoInterconsulta uceEvoInterconsultasOldUceEvoInterconsulta : uceEvoInterconsultasOld) {
                if (!uceEvoInterconsultasNew.contains(uceEvoInterconsultasOldUceEvoInterconsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceEvoInterconsulta " + uceEvoInterconsultasOldUceEvoInterconsulta + " since its idUceEvolucion field is not nullable.");
                }
            }
            for (UceEvoPosologia uceEvoPosologiasOldUceEvoPosologia : uceEvoPosologiasOld) {
                if (!uceEvoPosologiasNew.contains(uceEvoPosologiasOldUceEvoPosologia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceEvoPosologia " + uceEvoPosologiasOldUceEvoPosologia + " since its idUceEvolucion field is not nullable.");
                }
            }
            for (UceEvoProcedimiento uceEvoProcedimientosOldUceEvoProcedimiento : uceEvoProcedimientosOld) {
                if (!uceEvoProcedimientosNew.contains(uceEvoProcedimientosOldUceEvoProcedimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceEvoProcedimiento " + uceEvoProcedimientosOldUceEvoProcedimiento + " since its idUceEvolucion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceEvoMedidasg> attachedUceEvoMedidasgsNew = new ArrayList<UceEvoMedidasg>();
            for (UceEvoMedidasg uceEvoMedidasgsNewUceEvoMedidasgToAttach : uceEvoMedidasgsNew) {
                uceEvoMedidasgsNewUceEvoMedidasgToAttach = em.getReference(uceEvoMedidasgsNewUceEvoMedidasgToAttach.getClass(), uceEvoMedidasgsNewUceEvoMedidasgToAttach.getId());
                attachedUceEvoMedidasgsNew.add(uceEvoMedidasgsNewUceEvoMedidasgToAttach);
            }
            uceEvoMedidasgsNew = attachedUceEvoMedidasgsNew;
            uceEvolucion.setUceEvoMedidasgs(uceEvoMedidasgsNew);
            List<UceEvoInterconsulta> attachedUceEvoInterconsultasNew = new ArrayList<UceEvoInterconsulta>();
            for (UceEvoInterconsulta hospEvoInterconsultasNewHospEvoInterconsultaToAttach : uceEvoInterconsultasNew) {
                hospEvoInterconsultasNewHospEvoInterconsultaToAttach = em.getReference(hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getClass(), hospEvoInterconsultasNewHospEvoInterconsultaToAttach.getId());
                attachedUceEvoInterconsultasNew.add(hospEvoInterconsultasNewHospEvoInterconsultaToAttach);
            }
            uceEvoInterconsultasNew = attachedUceEvoInterconsultasNew;
            uceEvolucion.setUceEvoInterconsultas(uceEvoInterconsultasNew);
            List<UceEvoPosologia> attachedUceEvoPosologiasNew = new ArrayList<UceEvoPosologia>();
            for (UceEvoPosologia uceEvoPosologiasNewUceEvoPosologiaToAttach : uceEvoPosologiasNew) {
                uceEvoPosologiasNewUceEvoPosologiaToAttach = em.getReference(uceEvoPosologiasNewUceEvoPosologiaToAttach.getClass(), uceEvoPosologiasNewUceEvoPosologiaToAttach.getId());
                attachedUceEvoPosologiasNew.add(uceEvoPosologiasNewUceEvoPosologiaToAttach);
            }
            uceEvoPosologiasNew = attachedUceEvoPosologiasNew;
            uceEvolucion.setUceEvoPosologias(uceEvoPosologiasNew);
            List<UceEvoProcedimiento> attachedUceEvoProcedimientosNew = new ArrayList<UceEvoProcedimiento>();
            for (UceEvoProcedimiento uceEvoProcedimientosNewUceEvoProcedimientoToAttach : uceEvoProcedimientosNew) {
                uceEvoProcedimientosNewUceEvoProcedimientoToAttach = em.getReference(uceEvoProcedimientosNewUceEvoProcedimientoToAttach.getClass(), uceEvoProcedimientosNewUceEvoProcedimientoToAttach.getId());
                attachedUceEvoProcedimientosNew.add(uceEvoProcedimientosNewUceEvoProcedimientoToAttach);
            }
            uceEvoProcedimientosNew = attachedUceEvoProcedimientosNew;
            uceEvolucion.setUceEvoProcedimientos(uceEvoProcedimientosNew);
            List<UceEvoEgreso> attachedUceEvoEgresoNew = new ArrayList<UceEvoEgreso>();
            for (UceEvoEgreso uceEvoEgresoNewUceEvoEgresoToAttach : uceEvoEgresoNew) {
                uceEvoEgresoNewUceEvoEgresoToAttach = em.getReference(uceEvoEgresoNewUceEvoEgresoToAttach.getClass(), uceEvoEgresoNewUceEvoEgresoToAttach.getId());
                attachedUceEvoEgresoNew.add(uceEvoEgresoNewUceEvoEgresoToAttach);
            }
            uceEvoEgresoNew = attachedUceEvoEgresoNew;
            uceEvolucion.setUceEvoEgreso(uceEvoEgresoNew);
            uceEvolucion = em.merge(uceEvolucion);
            for (UceEvoMedidasg uceEvoMedidasgsNewUceEvoMedidasg : uceEvoMedidasgsNew) {
                if (!uceEvoMedidasgsOld.contains(uceEvoMedidasgsNewUceEvoMedidasg)) {
                    UceEvolucion oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg = uceEvoMedidasgsNewUceEvoMedidasg.getIdUceEvolucion();
                    uceEvoMedidasgsNewUceEvoMedidasg.setIdUceEvolucion(uceEvolucion);
                    uceEvoMedidasgsNewUceEvoMedidasg = em.merge(uceEvoMedidasgsNewUceEvoMedidasg);
                    if (oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg != null && !oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg.equals(uceEvolucion)) {
                        oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg.getUceEvoMedidasgs().remove(uceEvoMedidasgsNewUceEvoMedidasg);
                        oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg = em.merge(oldIdUceEvolucionOfUceEvoMedidasgsNewUceEvoMedidasg);
                    }
                }
            }
            for (UceEvoInterconsulta uceEvoInterconsultasNewUceEvoInterconsulta : uceEvoInterconsultasNew) {
                if (!uceEvoInterconsultasOld.contains(uceEvoInterconsultasNewUceEvoInterconsulta)) {
                    UceEvolucion oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta = uceEvoInterconsultasNewUceEvoInterconsulta.getIdUceEvolucion();
                    uceEvoInterconsultasNewUceEvoInterconsulta.setIdUceEvolucion(uceEvolucion);
                    uceEvoInterconsultasNewUceEvoInterconsulta = em.merge(uceEvoInterconsultasNewUceEvoInterconsulta);
                    if (oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta != null && !oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta.equals(uceEvolucion)) {
                        oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta.getUceEvoInterconsultas().remove(uceEvoInterconsultasNewUceEvoInterconsulta);
                        oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta = em.merge(oldIdUceEvolucionOfUceEvoInterconsultasNewUceEvoInterconsulta);
                    }
                }
            }
            for (UceEvoPosologia uceEvoPosologiasNewUceEvoPosologia : uceEvoPosologiasNew) {
                if (!uceEvoPosologiasOld.contains(uceEvoPosologiasNewUceEvoPosologia)) {
                    UceEvolucion oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia = uceEvoPosologiasNewUceEvoPosologia.getIdUceEvolucion();
                    uceEvoPosologiasNewUceEvoPosologia.setIdUceEvolucion(uceEvolucion);
                    uceEvoPosologiasNewUceEvoPosologia = em.merge(uceEvoPosologiasNewUceEvoPosologia);
                    if (oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia != null && !oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia.equals(uceEvolucion)) {
                        oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia.getUceEvoPosologias().remove(uceEvoPosologiasNewUceEvoPosologia);
                        oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia = em.merge(oldIdUceEvolucionOfUceEvoPosologiasNewUceEvoPosologia);
                    }
                }
            }
            for (UceEvoProcedimiento uceEvoProcedimientosNewUceEvoProcedimiento : uceEvoProcedimientosNew) {
                if (!uceEvoProcedimientosOld.contains(uceEvoProcedimientosNewUceEvoProcedimiento)) {
                    UceEvolucion oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento = uceEvoProcedimientosNewUceEvoProcedimiento.getIdUceEvolucion();
                    uceEvoProcedimientosNewUceEvoProcedimiento.setIdUceEvolucion(uceEvolucion);
                    uceEvoProcedimientosNewUceEvoProcedimiento = em.merge(uceEvoProcedimientosNewUceEvoProcedimiento);
                    if (oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento != null && !oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento.equals(uceEvolucion)) {
                        oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento.getUceEvoProcedimientos().remove(uceEvoProcedimientosNewUceEvoProcedimiento);
                        oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento = em.merge(oldIdUceEvolucionOfUceEvoProcedimientosNewUceEvoProcedimiento);
                    }
                }
            }
            for (UceEvoEgreso uceEvoEgresoOldUceEvoEgreso : uceEvoEgresoOld) {
                if (!uceEvoEgresoNew.contains(uceEvoEgresoOldUceEvoEgreso)) {
                    uceEvoEgresoOldUceEvoEgreso.setIdUceEvolucion(null);
                    uceEvoEgresoOldUceEvoEgreso = em.merge(uceEvoEgresoOldUceEvoEgreso);
                }
            }
            for (UceEvoEgreso uceEvoEgresoNewUceEvoEgreso : uceEvoEgresoNew) {
                if (!uceEvoEgresoOld.contains(uceEvoEgresoNewUceEvoEgreso)) {
                    UceEvolucion oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso = uceEvoEgresoNewUceEvoEgreso.getIdUceEvolucion();
                    uceEvoEgresoNewUceEvoEgreso.setIdUceEvolucion(uceEvolucion);
                    uceEvoEgresoNewUceEvoEgreso = em.merge(uceEvoEgresoNewUceEvoEgreso);
                    if (oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso != null && !oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso.equals(uceEvolucion)) {
                        oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso.getUceEvoEgreso().remove(uceEvoEgresoNewUceEvoEgreso);
                        oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso = em.merge(oldIdUceEvolucionOfUceEvoEgresoNewUceEvoEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvolucion.getId();
                if (findUceEvolucion(id) == null) {
                    throw new NonexistentEntityException("The uceEvolucion with id " + id + " no longer exists.");
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
            UceEvolucion uceEvolucion;
            try {
                uceEvolucion = em.getReference(UceEvolucion.class, id);
                uceEvolucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvolucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceEvoMedidasg> uceEvoMedidasgsOrphanCheck = uceEvolucion.getUceEvoMedidasgs();
            for (UceEvoMedidasg uceEvoMedidasgsOrphanCheckUceEvoMedidasg : uceEvoMedidasgsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceEvolucion (" + uceEvolucion + ") cannot be destroyed since the UceEvoMedidasg " + uceEvoMedidasgsOrphanCheckUceEvoMedidasg + " in its hospEvoMedidasgs field has a non-nullable idHospEvolucion field.");
            }
            List<UceEvoInterconsulta> uceEvoInterconsultasOrphanCheck = uceEvolucion.getUceEvoInterconsultas();
            for (UceEvoInterconsulta uceEvoInterconsultasOrphanCheckUceEvoInterconsulta : uceEvoInterconsultasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceEvolucion (" + uceEvolucion + ") cannot be destroyed since the UceEvoInterconsulta " + uceEvoInterconsultasOrphanCheckUceEvoInterconsulta + " in its hospEvoInterconsultas field has a non-nullable idHospEvolucion field.");
            }
            List<UceEvoPosologia> uceEvoPosologiasOrphanCheck = uceEvolucion.getUceEvoPosologias();
            for (UceEvoPosologia uceEvoPosologiasOrphanCheckUceEvoPosologia : uceEvoPosologiasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceEvolucion (" + uceEvolucion + ") cannot be destroyed since the UceEvoPosologia " + uceEvoPosologiasOrphanCheckUceEvoPosologia + " in its hospEvoPosologias field has a non-nullable idHospEvolucion field.");
            }
            List<UceEvoProcedimiento> uceEvoProcedimientosOrphanCheck = uceEvolucion.getUceEvoProcedimientos();
            for (UceEvoProcedimiento uceEvoProcedimientosOrphanCheckUceEvoProcedimiento : uceEvoProcedimientosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceEvolucion (" + uceEvolucion + ") cannot be destroyed since the UceEvoProcedimiento " + uceEvoProcedimientosOrphanCheckUceEvoProcedimiento + " in its hospEvoProcedimientos field has a non-nullable idHospEvolucion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceEvoEgreso> uceEvoEgreso = uceEvolucion.getUceEvoEgreso();
            for (UceEvoEgreso uceEvoEgresoUceEvoEgreso : uceEvoEgreso) {
                uceEvoEgresoUceEvoEgreso.setIdUceEvolucion(null);
                uceEvoEgresoUceEvoEgreso = em.merge(uceEvoEgresoUceEvoEgreso);
            }
            em.remove(uceEvolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvolucion> findUceEvolucionEntities() {
        return findUceEvolucionEntities(true, -1, -1);
    }

    public List<UceEvolucion> findUceEvolucionEntities(int maxResults, int firstResult) {
        return findUceEvolucionEntities(false, maxResults, firstResult);
    }

    private List<UceEvolucion> findUceEvolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvolucion.class));
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

    public UceEvolucion findUceEvolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvolucion> rt = cq.from(UceEvolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

        //Codigo no Auto-generado
    public List<UceEvolucion> FindUceEvolucions(UceHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM UceEvolucion h WHERE h.idUceHistoriac = :hc AND h.estado <> '0' ORDER BY h.fechaEvo ASC")
            .setParameter("hc", ihc)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }

    public Long CountInterconsultas(UceHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UceEvolucion h WHERE h.idUceHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

    public Long CountInterconsultasGeneradas(UceHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM UceEvolucion h WHERE h.idUceHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1' AND h.tipo = '1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }

}
