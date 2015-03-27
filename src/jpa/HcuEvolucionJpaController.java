
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HcuEvoMedidasg;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HcuEvoInterconsulta;
import entidades_EJB.HcuEvoPosologia;
import entidades_EJB.HcuEvoMezclasMedicamentos;
import entidades_EJB.HcuEvoProcedimiento;
import entidades_EJB.HcuEvoEgreso;
import entidades_EJB.HcuEvolucion;
import entidades_EJB.InfoHistoriac;
import entidades_EJB.StaticEspecialidades;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvolucionJpaController implements Serializable {

    public HcuEvolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvolucion hcuEvolucion) {
        if (hcuEvolucion.getHcuEvoMedidasgs() == null) {
            hcuEvolucion.setHcuEvoMedidasgs(new ArrayList<HcuEvoMedidasg>());
        }
        if (hcuEvolucion.getHcuEvoInterconsultas() == null) {
            hcuEvolucion.setHcuEvoInterconsultas(new ArrayList<HcuEvoInterconsulta>());
        }
        if (hcuEvolucion.getHcuEvoPosologias() == null) {
            hcuEvolucion.setHcuEvoPosologias(new ArrayList<HcuEvoPosologia>());
        }
        if (hcuEvolucion.getHcuEvoMezclasMedicamentoses() == null) {
            hcuEvolucion.setHcuEvoMezclasMedicamentoses(new ArrayList<HcuEvoMezclasMedicamentos>());
        }
        if (hcuEvolucion.getHcuEvoProcedimientos() == null) {
            hcuEvolucion.setHcuEvoProcedimientos(new ArrayList<HcuEvoProcedimiento>());
        }
        if (hcuEvolucion.getHcuEvoEgreso() == null) {
            hcuEvolucion.setHcuEvoEgreso(new ArrayList<HcuEvoEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HcuEvoMedidasg> attachedHcuEvoMedidasgs = new ArrayList<HcuEvoMedidasg>();
            for (HcuEvoMedidasg hcuEvoMedidasgsHcuEvoMedidasgToAttach : hcuEvolucion.getHcuEvoMedidasgs()) {
                hcuEvoMedidasgsHcuEvoMedidasgToAttach = em.getReference(hcuEvoMedidasgsHcuEvoMedidasgToAttach.getClass(), hcuEvoMedidasgsHcuEvoMedidasgToAttach.getId());
                attachedHcuEvoMedidasgs.add(hcuEvoMedidasgsHcuEvoMedidasgToAttach);
            }
            hcuEvolucion.setHcuEvoMedidasgs(attachedHcuEvoMedidasgs);
            List<HcuEvoInterconsulta> attachedHcuEvoInterconsultas = new ArrayList<HcuEvoInterconsulta>();
            for (HcuEvoInterconsulta hcuEvoInterconsultasHcuEvoInterconsultaToAttach : hcuEvolucion.getHcuEvoInterconsultas()) {
                hcuEvoInterconsultasHcuEvoInterconsultaToAttach = em.getReference(hcuEvoInterconsultasHcuEvoInterconsultaToAttach.getClass(), hcuEvoInterconsultasHcuEvoInterconsultaToAttach.getId());
                attachedHcuEvoInterconsultas.add(hcuEvoInterconsultasHcuEvoInterconsultaToAttach);
            }
            hcuEvolucion.setHcuEvoInterconsultas(attachedHcuEvoInterconsultas);
            List<HcuEvoPosologia> attachedHcuEvoPosologias = new ArrayList<HcuEvoPosologia>();
            for (HcuEvoPosologia hcuEvoPosologiasHcuEvoPosologiaToAttach : hcuEvolucion.getHcuEvoPosologias()) {
                hcuEvoPosologiasHcuEvoPosologiaToAttach = em.getReference(hcuEvoPosologiasHcuEvoPosologiaToAttach.getClass(), hcuEvoPosologiasHcuEvoPosologiaToAttach.getId());
                attachedHcuEvoPosologias.add(hcuEvoPosologiasHcuEvoPosologiaToAttach);
            }
            hcuEvolucion.setHcuEvoPosologias(attachedHcuEvoPosologias);
            List<HcuEvoMezclasMedicamentos> attachedHcuEvoMezclasMedicamentoses = new ArrayList<HcuEvoMezclasMedicamentos>();
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentosToAttach : hcuEvolucion.getHcuEvoMezclasMedicamentoses()) {
                hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentosToAttach = em.getReference(hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentosToAttach.getClass(), hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentosToAttach.getId());
                attachedHcuEvoMezclasMedicamentoses.add(hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentosToAttach);
            }
            hcuEvolucion.setHcuEvoMezclasMedicamentoses(attachedHcuEvoMezclasMedicamentoses);
            List<HcuEvoProcedimiento> attachedHcuEvoProcedimientos = new ArrayList<HcuEvoProcedimiento>();
            for (HcuEvoProcedimiento hcuEvoProcedimientosHcuEvoProcedimientoToAttach : hcuEvolucion.getHcuEvoProcedimientos()) {
                hcuEvoProcedimientosHcuEvoProcedimientoToAttach = em.getReference(hcuEvoProcedimientosHcuEvoProcedimientoToAttach.getClass(), hcuEvoProcedimientosHcuEvoProcedimientoToAttach.getId());
                attachedHcuEvoProcedimientos.add(hcuEvoProcedimientosHcuEvoProcedimientoToAttach);
            }
            hcuEvolucion.setHcuEvoProcedimientos(attachedHcuEvoProcedimientos);
            List<HcuEvoEgreso> attachedHcuEvoEgreso = new ArrayList<HcuEvoEgreso>();
            for (HcuEvoEgreso hcuEvoEgresoHcuEvoEgresoToAttach : hcuEvolucion.getHcuEvoEgreso()) {
                hcuEvoEgresoHcuEvoEgresoToAttach = em.getReference(hcuEvoEgresoHcuEvoEgresoToAttach.getClass(), hcuEvoEgresoHcuEvoEgresoToAttach.getId());
                attachedHcuEvoEgreso.add(hcuEvoEgresoHcuEvoEgresoToAttach);
            }
            hcuEvolucion.setHcuEvoEgreso(attachedHcuEvoEgreso);
            em.persist(hcuEvolucion);
            for (HcuEvoMedidasg hcuEvoMedidasgsHcuEvoMedidasg : hcuEvolucion.getHcuEvoMedidasgs()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoMedidasgsHcuEvoMedidasg = hcuEvoMedidasgsHcuEvoMedidasg.getIdHcuEvolucion();
                hcuEvoMedidasgsHcuEvoMedidasg.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoMedidasgsHcuEvoMedidasg = em.merge(hcuEvoMedidasgsHcuEvoMedidasg);
                if (oldIdHcuEvolucionOfHcuEvoMedidasgsHcuEvoMedidasg != null) {
                    oldIdHcuEvolucionOfHcuEvoMedidasgsHcuEvoMedidasg.getHcuEvoMedidasgs().remove(hcuEvoMedidasgsHcuEvoMedidasg);
                    oldIdHcuEvolucionOfHcuEvoMedidasgsHcuEvoMedidasg = em.merge(oldIdHcuEvolucionOfHcuEvoMedidasgsHcuEvoMedidasg);
                }
            }
            for (HcuEvoInterconsulta hcuEvoInterconsultasHcuEvoInterconsulta : hcuEvolucion.getHcuEvoInterconsultas()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoInterconsultasHcuEvoInterconsulta = hcuEvoInterconsultasHcuEvoInterconsulta.getIdHcuEvolucion();
                hcuEvoInterconsultasHcuEvoInterconsulta.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoInterconsultasHcuEvoInterconsulta = em.merge(hcuEvoInterconsultasHcuEvoInterconsulta);
                if (oldIdHcuEvolucionOfHcuEvoInterconsultasHcuEvoInterconsulta != null) {
                    oldIdHcuEvolucionOfHcuEvoInterconsultasHcuEvoInterconsulta.getHcuEvoInterconsultas().remove(hcuEvoInterconsultasHcuEvoInterconsulta);
                    oldIdHcuEvolucionOfHcuEvoInterconsultasHcuEvoInterconsulta = em.merge(oldIdHcuEvolucionOfHcuEvoInterconsultasHcuEvoInterconsulta);
                }
            }
            for (HcuEvoPosologia hcuEvoPosologiasHcuEvoPosologia : hcuEvolucion.getHcuEvoPosologias()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoPosologiasHcuEvoPosologia = hcuEvoPosologiasHcuEvoPosologia.getIdHcuEvolucion();
                hcuEvoPosologiasHcuEvoPosologia.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoPosologiasHcuEvoPosologia = em.merge(hcuEvoPosologiasHcuEvoPosologia);
                if (oldIdHcuEvolucionOfHcuEvoPosologiasHcuEvoPosologia != null) {
                    oldIdHcuEvolucionOfHcuEvoPosologiasHcuEvoPosologia.getHcuEvoPosologias().remove(hcuEvoPosologiasHcuEvoPosologia);
                    oldIdHcuEvolucionOfHcuEvoPosologiasHcuEvoPosologia = em.merge(oldIdHcuEvolucionOfHcuEvoPosologiasHcuEvoPosologia);
                }
            }
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos : hcuEvolucion.getHcuEvoMezclasMedicamentoses()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos = hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos.getIdHcuEvolucion();
                hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos = em.merge(hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos);
                if (oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos != null) {
                    oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos.getHcuEvoMezclasMedicamentoses().remove(hcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos);
                    oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos = em.merge(oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesHcuEvoMezclasMedicamentos);
                }
            }
            for (HcuEvoProcedimiento hcuEvoProcedimientosHcuEvoProcedimiento : hcuEvolucion.getHcuEvoProcedimientos()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoProcedimientosHcuEvoProcedimiento = hcuEvoProcedimientosHcuEvoProcedimiento.getIdHcuEvolucion();
                hcuEvoProcedimientosHcuEvoProcedimiento.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoProcedimientosHcuEvoProcedimiento = em.merge(hcuEvoProcedimientosHcuEvoProcedimiento);
                if (oldIdHcuEvolucionOfHcuEvoProcedimientosHcuEvoProcedimiento != null) {
                    oldIdHcuEvolucionOfHcuEvoProcedimientosHcuEvoProcedimiento.getHcuEvoProcedimientos().remove(hcuEvoProcedimientosHcuEvoProcedimiento);
                    oldIdHcuEvolucionOfHcuEvoProcedimientosHcuEvoProcedimiento = em.merge(oldIdHcuEvolucionOfHcuEvoProcedimientosHcuEvoProcedimiento);
                }
            }
            for (HcuEvoEgreso hcuEvoEgresoHcuEvoEgreso : hcuEvolucion.getHcuEvoEgreso()) {
                HcuEvolucion oldIdHcuEvolucionOfHcuEvoEgresoHcuEvoEgreso = hcuEvoEgresoHcuEvoEgreso.getIdHcuEvolucion();
                hcuEvoEgresoHcuEvoEgreso.setIdHcuEvolucion(hcuEvolucion);
                hcuEvoEgresoHcuEvoEgreso = em.merge(hcuEvoEgresoHcuEvoEgreso);
                if (oldIdHcuEvolucionOfHcuEvoEgresoHcuEvoEgreso != null) {
                    oldIdHcuEvolucionOfHcuEvoEgresoHcuEvoEgreso.getHcuEvoEgreso().remove(hcuEvoEgresoHcuEvoEgreso);
                    oldIdHcuEvolucionOfHcuEvoEgresoHcuEvoEgreso = em.merge(oldIdHcuEvolucionOfHcuEvoEgresoHcuEvoEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvolucion hcuEvolucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion persistentHcuEvolucion = em.find(HcuEvolucion.class, hcuEvolucion.getId());
            List<HcuEvoMedidasg> hcuEvoMedidasgsOld = persistentHcuEvolucion.getHcuEvoMedidasgs();
            List<HcuEvoMedidasg> hcuEvoMedidasgsNew = hcuEvolucion.getHcuEvoMedidasgs();
            List<HcuEvoInterconsulta> hcuEvoInterconsultasOld = persistentHcuEvolucion.getHcuEvoInterconsultas();
            List<HcuEvoInterconsulta> hcuEvoInterconsultasNew = hcuEvolucion.getHcuEvoInterconsultas();
            List<HcuEvoPosologia> hcuEvoPosologiasOld = persistentHcuEvolucion.getHcuEvoPosologias();
            List<HcuEvoPosologia> hcuEvoPosologiasNew = hcuEvolucion.getHcuEvoPosologias();
            List<HcuEvoMezclasMedicamentos> hcuEvoMezclasMedicamentosesOld = persistentHcuEvolucion.getHcuEvoMezclasMedicamentoses();
            List<HcuEvoMezclasMedicamentos> hcuEvoMezclasMedicamentosesNew = hcuEvolucion.getHcuEvoMezclasMedicamentoses();
            List<HcuEvoProcedimiento> hcuEvoProcedimientosOld = persistentHcuEvolucion.getHcuEvoProcedimientos();
            List<HcuEvoProcedimiento> hcuEvoProcedimientosNew = hcuEvolucion.getHcuEvoProcedimientos();
            List<HcuEvoEgreso> hcuEvoEgresoOld = persistentHcuEvolucion.getHcuEvoEgreso();
            List<HcuEvoEgreso> hcuEvoEgresoNew = hcuEvolucion.getHcuEvoEgreso();
            List<String> illegalOrphanMessages = null;
            for (HcuEvoMedidasg hcuEvoMedidasgsOldHcuEvoMedidasg : hcuEvoMedidasgsOld) {
                if (!hcuEvoMedidasgsNew.contains(hcuEvoMedidasgsOldHcuEvoMedidasg)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoMedidasg " + hcuEvoMedidasgsOldHcuEvoMedidasg + " since its idHcuEvolucion field is not nullable.");
                }
            }
            for (HcuEvoInterconsulta hcuEvoInterconsultasOldHcuEvoInterconsulta : hcuEvoInterconsultasOld) {
                if (!hcuEvoInterconsultasNew.contains(hcuEvoInterconsultasOldHcuEvoInterconsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoInterconsulta " + hcuEvoInterconsultasOldHcuEvoInterconsulta + " since its idHcuEvolucion field is not nullable.");
                }
            }
            for (HcuEvoPosologia hcuEvoPosologiasOldHcuEvoPosologia : hcuEvoPosologiasOld) {
                if (!hcuEvoPosologiasNew.contains(hcuEvoPosologiasOldHcuEvoPosologia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoPosologia " + hcuEvoPosologiasOldHcuEvoPosologia + " since its idHcuEvolucion field is not nullable.");
                }
            }
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesOldHcuEvoMezclasMedicamentos : hcuEvoMezclasMedicamentosesOld) {
                if (!hcuEvoMezclasMedicamentosesNew.contains(hcuEvoMezclasMedicamentosesOldHcuEvoMezclasMedicamentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoMezclasMedicamentos " + hcuEvoMezclasMedicamentosesOldHcuEvoMezclasMedicamentos + " since its idHcuEvolucion field is not nullable.");
                }
            }
            for (HcuEvoProcedimiento hcuEvoProcedimientosOldHcuEvoProcedimiento : hcuEvoProcedimientosOld) {
                if (!hcuEvoProcedimientosNew.contains(hcuEvoProcedimientosOldHcuEvoProcedimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoProcedimiento " + hcuEvoProcedimientosOldHcuEvoProcedimiento + " since its idHcuEvolucion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuEvoMedidasg> attachedHcuEvoMedidasgsNew = new ArrayList<HcuEvoMedidasg>();
            for (HcuEvoMedidasg hcuEvoMedidasgsNewHcuEvoMedidasgToAttach : hcuEvoMedidasgsNew) {
                hcuEvoMedidasgsNewHcuEvoMedidasgToAttach = em.getReference(hcuEvoMedidasgsNewHcuEvoMedidasgToAttach.getClass(), hcuEvoMedidasgsNewHcuEvoMedidasgToAttach.getId());
                attachedHcuEvoMedidasgsNew.add(hcuEvoMedidasgsNewHcuEvoMedidasgToAttach);
            }
            hcuEvoMedidasgsNew = attachedHcuEvoMedidasgsNew;
            hcuEvolucion.setHcuEvoMedidasgs(hcuEvoMedidasgsNew);
            List<HcuEvoInterconsulta> attachedHcuEvoInterconsultasNew = new ArrayList<HcuEvoInterconsulta>();
            for (HcuEvoInterconsulta hcuEvoInterconsultasNewHcuEvoInterconsultaToAttach : hcuEvoInterconsultasNew) {
                hcuEvoInterconsultasNewHcuEvoInterconsultaToAttach = em.getReference(hcuEvoInterconsultasNewHcuEvoInterconsultaToAttach.getClass(), hcuEvoInterconsultasNewHcuEvoInterconsultaToAttach.getId());
                attachedHcuEvoInterconsultasNew.add(hcuEvoInterconsultasNewHcuEvoInterconsultaToAttach);
            }
            hcuEvoInterconsultasNew = attachedHcuEvoInterconsultasNew;
            hcuEvolucion.setHcuEvoInterconsultas(hcuEvoInterconsultasNew);
            List<HcuEvoPosologia> attachedHcuEvoPosologiasNew = new ArrayList<HcuEvoPosologia>();
            for (HcuEvoPosologia hcuEvoPosologiasNewHcuEvoPosologiaToAttach : hcuEvoPosologiasNew) {
                hcuEvoPosologiasNewHcuEvoPosologiaToAttach = em.getReference(hcuEvoPosologiasNewHcuEvoPosologiaToAttach.getClass(), hcuEvoPosologiasNewHcuEvoPosologiaToAttach.getId());
                attachedHcuEvoPosologiasNew.add(hcuEvoPosologiasNewHcuEvoPosologiaToAttach);
            }
            hcuEvoPosologiasNew = attachedHcuEvoPosologiasNew;
            hcuEvolucion.setHcuEvoPosologias(hcuEvoPosologiasNew);
            List<HcuEvoMezclasMedicamentos> attachedHcuEvoMezclasMedicamentosesNew = new ArrayList<HcuEvoMezclasMedicamentos>();
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentosToAttach : hcuEvoMezclasMedicamentosesNew) {
                hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentosToAttach = em.getReference(hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentosToAttach.getClass(), hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentosToAttach.getId());
                attachedHcuEvoMezclasMedicamentosesNew.add(hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentosToAttach);
            }
            hcuEvoMezclasMedicamentosesNew = attachedHcuEvoMezclasMedicamentosesNew;
            hcuEvolucion.setHcuEvoMezclasMedicamentoses(hcuEvoMezclasMedicamentosesNew);
            List<HcuEvoProcedimiento> attachedHcuEvoProcedimientosNew = new ArrayList<HcuEvoProcedimiento>();
            for (HcuEvoProcedimiento hcuEvoProcedimientosNewHcuEvoProcedimientoToAttach : hcuEvoProcedimientosNew) {
                hcuEvoProcedimientosNewHcuEvoProcedimientoToAttach = em.getReference(hcuEvoProcedimientosNewHcuEvoProcedimientoToAttach.getClass(), hcuEvoProcedimientosNewHcuEvoProcedimientoToAttach.getId());
                attachedHcuEvoProcedimientosNew.add(hcuEvoProcedimientosNewHcuEvoProcedimientoToAttach);
            }
            hcuEvoProcedimientosNew = attachedHcuEvoProcedimientosNew;
            hcuEvolucion.setHcuEvoProcedimientos(hcuEvoProcedimientosNew);
            List<HcuEvoEgreso> attachedHcuEvoEgresoNew = new ArrayList<HcuEvoEgreso>();
            for (HcuEvoEgreso hcuEvoEgresoNewHcuEvoEgresoToAttach : hcuEvoEgresoNew) {
                hcuEvoEgresoNewHcuEvoEgresoToAttach = em.getReference(hcuEvoEgresoNewHcuEvoEgresoToAttach.getClass(), hcuEvoEgresoNewHcuEvoEgresoToAttach.getId());
                attachedHcuEvoEgresoNew.add(hcuEvoEgresoNewHcuEvoEgresoToAttach);
            }
            hcuEvoEgresoNew = attachedHcuEvoEgresoNew;
            hcuEvolucion.setHcuEvoEgreso(hcuEvoEgresoNew);
            hcuEvolucion = em.merge(hcuEvolucion);
            for (HcuEvoMedidasg hcuEvoMedidasgsNewHcuEvoMedidasg : hcuEvoMedidasgsNew) {
                if (!hcuEvoMedidasgsOld.contains(hcuEvoMedidasgsNewHcuEvoMedidasg)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg = hcuEvoMedidasgsNewHcuEvoMedidasg.getIdHcuEvolucion();
                    hcuEvoMedidasgsNewHcuEvoMedidasg.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoMedidasgsNewHcuEvoMedidasg = em.merge(hcuEvoMedidasgsNewHcuEvoMedidasg);
                    if (oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg != null && !oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg.getHcuEvoMedidasgs().remove(hcuEvoMedidasgsNewHcuEvoMedidasg);
                        oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg = em.merge(oldIdHcuEvolucionOfHcuEvoMedidasgsNewHcuEvoMedidasg);
                    }
                }
            }
            for (HcuEvoInterconsulta hcuEvoInterconsultasNewHcuEvoInterconsulta : hcuEvoInterconsultasNew) {
                if (!hcuEvoInterconsultasOld.contains(hcuEvoInterconsultasNewHcuEvoInterconsulta)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta = hcuEvoInterconsultasNewHcuEvoInterconsulta.getIdHcuEvolucion();
                    hcuEvoInterconsultasNewHcuEvoInterconsulta.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoInterconsultasNewHcuEvoInterconsulta = em.merge(hcuEvoInterconsultasNewHcuEvoInterconsulta);
                    if (oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta != null && !oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta.getHcuEvoInterconsultas().remove(hcuEvoInterconsultasNewHcuEvoInterconsulta);
                        oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta = em.merge(oldIdHcuEvolucionOfHcuEvoInterconsultasNewHcuEvoInterconsulta);
                    }
                }
            }
            for (HcuEvoPosologia hcuEvoPosologiasNewHcuEvoPosologia : hcuEvoPosologiasNew) {
                if (!hcuEvoPosologiasOld.contains(hcuEvoPosologiasNewHcuEvoPosologia)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia = hcuEvoPosologiasNewHcuEvoPosologia.getIdHcuEvolucion();
                    hcuEvoPosologiasNewHcuEvoPosologia.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoPosologiasNewHcuEvoPosologia = em.merge(hcuEvoPosologiasNewHcuEvoPosologia);
                    if (oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia != null && !oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia.getHcuEvoPosologias().remove(hcuEvoPosologiasNewHcuEvoPosologia);
                        oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia = em.merge(oldIdHcuEvolucionOfHcuEvoPosologiasNewHcuEvoPosologia);
                    }
                }
            }
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos : hcuEvoMezclasMedicamentosesNew) {
                if (!hcuEvoMezclasMedicamentosesOld.contains(hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos = hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos.getIdHcuEvolucion();
                    hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos = em.merge(hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos);
                    if (oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos != null && !oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos.getHcuEvoMezclasMedicamentoses().remove(hcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos);
                        oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos = em.merge(oldIdHcuEvolucionOfHcuEvoMezclasMedicamentosesNewHcuEvoMezclasMedicamentos);
                    }
                }
            }
            for (HcuEvoProcedimiento hcuEvoProcedimientosNewHcuEvoProcedimiento : hcuEvoProcedimientosNew) {
                if (!hcuEvoProcedimientosOld.contains(hcuEvoProcedimientosNewHcuEvoProcedimiento)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento = hcuEvoProcedimientosNewHcuEvoProcedimiento.getIdHcuEvolucion();
                    hcuEvoProcedimientosNewHcuEvoProcedimiento.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoProcedimientosNewHcuEvoProcedimiento = em.merge(hcuEvoProcedimientosNewHcuEvoProcedimiento);
                    if (oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento != null && !oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento.getHcuEvoProcedimientos().remove(hcuEvoProcedimientosNewHcuEvoProcedimiento);
                        oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento = em.merge(oldIdHcuEvolucionOfHcuEvoProcedimientosNewHcuEvoProcedimiento);
                    }
                }
            }
            for (HcuEvoEgreso hcuEvoEgresoOldHcuEvoEgreso : hcuEvoEgresoOld) {
                if (!hcuEvoEgresoNew.contains(hcuEvoEgresoOldHcuEvoEgreso)) {
                    hcuEvoEgresoOldHcuEvoEgreso.setIdHcuEvolucion(null);
                    hcuEvoEgresoOldHcuEvoEgreso = em.merge(hcuEvoEgresoOldHcuEvoEgreso);
                }
            }
            for (HcuEvoEgreso hcuEvoEgresoNewHcuEvoEgreso : hcuEvoEgresoNew) {
                if (!hcuEvoEgresoOld.contains(hcuEvoEgresoNewHcuEvoEgreso)) {
                    HcuEvolucion oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso = hcuEvoEgresoNewHcuEvoEgreso.getIdHcuEvolucion();
                    hcuEvoEgresoNewHcuEvoEgreso.setIdHcuEvolucion(hcuEvolucion);
                    hcuEvoEgresoNewHcuEvoEgreso = em.merge(hcuEvoEgresoNewHcuEvoEgreso);
                    if (oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso != null && !oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso.equals(hcuEvolucion)) {
                        oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso.getHcuEvoEgreso().remove(hcuEvoEgresoNewHcuEvoEgreso);
                        oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso = em.merge(oldIdHcuEvolucionOfHcuEvoEgresoNewHcuEvoEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvolucion.getId();
                if (findHcuEvolucion(id) == null) {
                    throw new NonexistentEntityException("The hcuEvolucion with id " + id + " no longer exists.");
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
            HcuEvolucion hcuEvolucion;
            try {
                hcuEvolucion = em.getReference(HcuEvolucion.class, id);
                hcuEvolucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvolucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HcuEvoMedidasg> hcuEvoMedidasgsOrphanCheck = hcuEvolucion.getHcuEvoMedidasgs();
            for (HcuEvoMedidasg hcuEvoMedidasgsOrphanCheckHcuEvoMedidasg : hcuEvoMedidasgsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvolucion (" + hcuEvolucion + ") cannot be destroyed since the HcuEvoMedidasg " + hcuEvoMedidasgsOrphanCheckHcuEvoMedidasg + " in its hcuEvoMedidasgs field has a non-nullable idHcuEvolucion field.");
            }
            List<HcuEvoInterconsulta> hcuEvoInterconsultasOrphanCheck = hcuEvolucion.getHcuEvoInterconsultas();
            for (HcuEvoInterconsulta hcuEvoInterconsultasOrphanCheckHcuEvoInterconsulta : hcuEvoInterconsultasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvolucion (" + hcuEvolucion + ") cannot be destroyed since the HcuEvoInterconsulta " + hcuEvoInterconsultasOrphanCheckHcuEvoInterconsulta + " in its hcuEvoInterconsultas field has a non-nullable idHcuEvolucion field.");
            }
            List<HcuEvoPosologia> hcuEvoPosologiasOrphanCheck = hcuEvolucion.getHcuEvoPosologias();
            for (HcuEvoPosologia hcuEvoPosologiasOrphanCheckHcuEvoPosologia : hcuEvoPosologiasOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvolucion (" + hcuEvolucion + ") cannot be destroyed since the HcuEvoPosologia " + hcuEvoPosologiasOrphanCheckHcuEvoPosologia + " in its hcuEvoPosologias field has a non-nullable idHcuEvolucion field.");
            }
            List<HcuEvoMezclasMedicamentos> hcuEvoMezclasMedicamentosesOrphanCheck = hcuEvolucion.getHcuEvoMezclasMedicamentoses();
            for (HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentosesOrphanCheckHcuEvoMezclasMedicamentos : hcuEvoMezclasMedicamentosesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvolucion (" + hcuEvolucion + ") cannot be destroyed since the HcuEvoMezclasMedicamentos " + hcuEvoMezclasMedicamentosesOrphanCheckHcuEvoMezclasMedicamentos + " in its hcuEvoMezclasMedicamentoses field has a non-nullable idHcuEvolucion field.");
            }
            List<HcuEvoProcedimiento> hcuEvoProcedimientosOrphanCheck = hcuEvolucion.getHcuEvoProcedimientos();
            for (HcuEvoProcedimiento hcuEvoProcedimientosOrphanCheckHcuEvoProcedimiento : hcuEvoProcedimientosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvolucion (" + hcuEvolucion + ") cannot be destroyed since the HcuEvoProcedimiento " + hcuEvoProcedimientosOrphanCheckHcuEvoProcedimiento + " in its hcuEvoProcedimientos field has a non-nullable idHcuEvolucion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuEvoEgreso> hcuEvoEgreso = hcuEvolucion.getHcuEvoEgreso();
            for (HcuEvoEgreso hcuEvoEgresoHcuEvoEgreso : hcuEvoEgreso) {
                hcuEvoEgresoHcuEvoEgreso.setIdHcuEvolucion(null);
                hcuEvoEgresoHcuEvoEgreso = em.merge(hcuEvoEgresoHcuEvoEgreso);
            }
            em.remove(hcuEvolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvolucion> findHcuEvolucionEntities() {
        return findHcuEvolucionEntities(true, -1, -1);
    }

    public List<HcuEvolucion> findHcuEvolucionEntities(int maxResults, int firstResult) {
        return findHcuEvolucionEntities(false, maxResults, firstResult);
    }

    private List<HcuEvolucion> findHcuEvolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvolucion.class));
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

    public HcuEvolucion findHcuEvolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvolucion> rt = cq.from(HcuEvolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        //Codigo no Auto-generado    
    public List<HcuEvolucion> FindHcuEvolucions(InfoHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuEvolucion h WHERE h.idInfoHistoriac = :hc AND h.estado <> '0' ORDER BY h.fechaEvo ASC")
            .setParameter("hc", ihc)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
    public Long CountInterconsultas(InfoHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HcuEvolucion h WHERE h.idInfoHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }    
    
    public Long CountInterconsultasGeneradas(InfoHistoriac ihc, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HcuEvolucion h WHERE h.idInfoHistoriac = :ih AND h.idStaticEspecialidades = :se AND h.estado='1' AND h.tipo = '1'")
            .setParameter("ih", ihc)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }   
    
}

