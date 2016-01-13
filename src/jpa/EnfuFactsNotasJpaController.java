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
import entidades_EJB.EnfuGlasgow;
import java.util.ArrayList;
import java.util.Collection;
import entidades_EJB.EnfuEspecificacionesGenerales;
import entidades_EJB.EnfuFactsNotas;
import entidades_EJB.EnfuSignosVitalesDet;
import entidades_EJB.EnfuSignosVitalesMat;
import entidades_EJB.EnfuMedSuministrados;
import entidades_EJB.InfoHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuFactsNotasJpaController implements Serializable {

    public EnfuFactsNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuFactsNotas enfuFactsNotas) {
        if (enfuFactsNotas.getEnfuGlasgowCollection() == null) {
            enfuFactsNotas.setEnfuGlasgowCollection(new ArrayList<EnfuGlasgow>());
        }
        if (enfuFactsNotas.getEnfuEspecificacionesGeneralesCollection() == null) {
            enfuFactsNotas.setEnfuEspecificacionesGeneralesCollection(new ArrayList<EnfuEspecificacionesGenerales>());
        }
        if (enfuFactsNotas.getEnfuSignosVitalesDetCollection() == null) {
            enfuFactsNotas.setEnfuSignosVitalesDetCollection(new ArrayList<EnfuSignosVitalesDet>());
        }
        if (enfuFactsNotas.getEnfuSignosVitalesMatCollection() == null) {
            enfuFactsNotas.setEnfuSignosVitalesMatCollection(new ArrayList<EnfuSignosVitalesMat>());
        }
        if (enfuFactsNotas.getEnfuMedSuministradosCollection() == null) {
            enfuFactsNotas.setEnfuMedSuministradosCollection(new ArrayList<EnfuMedSuministrados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<EnfuGlasgow> attachedEnfuGlasgowCollection = new ArrayList<EnfuGlasgow>();
            for (EnfuGlasgow enfuGlasgowCollectionEnfuGlasgowToAttach : enfuFactsNotas.getEnfuGlasgowCollection()) {
                enfuGlasgowCollectionEnfuGlasgowToAttach = em.getReference(enfuGlasgowCollectionEnfuGlasgowToAttach.getClass(), enfuGlasgowCollectionEnfuGlasgowToAttach.getId());
                attachedEnfuGlasgowCollection.add(enfuGlasgowCollectionEnfuGlasgowToAttach);
            }
            enfuFactsNotas.setEnfuGlasgowCollection(attachedEnfuGlasgowCollection);
            Collection<EnfuEspecificacionesGenerales> attachedEnfuEspecificacionesGeneralesCollection = new ArrayList<EnfuEspecificacionesGenerales>();
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGeneralesToAttach : enfuFactsNotas.getEnfuEspecificacionesGeneralesCollection()) {
                enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGeneralesToAttach = em.getReference(enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGeneralesToAttach.getClass(), enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGeneralesToAttach.getId());
                attachedEnfuEspecificacionesGeneralesCollection.add(enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGeneralesToAttach);
            }
            enfuFactsNotas.setEnfuEspecificacionesGeneralesCollection(attachedEnfuEspecificacionesGeneralesCollection);
            Collection<EnfuSignosVitalesDet> attachedEnfuSignosVitalesDetCollection = new ArrayList<EnfuSignosVitalesDet>();
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionEnfuSignosVitalesDetToAttach : enfuFactsNotas.getEnfuSignosVitalesDetCollection()) {
                enfuSignosVitalesDetCollectionEnfuSignosVitalesDetToAttach = em.getReference(enfuSignosVitalesDetCollectionEnfuSignosVitalesDetToAttach.getClass(), enfuSignosVitalesDetCollectionEnfuSignosVitalesDetToAttach.getId());
                attachedEnfuSignosVitalesDetCollection.add(enfuSignosVitalesDetCollectionEnfuSignosVitalesDetToAttach);
            }
            enfuFactsNotas.setEnfuSignosVitalesDetCollection(attachedEnfuSignosVitalesDetCollection);
            Collection<EnfuSignosVitalesMat> attachedEnfuSignosVitalesMatCollection = new ArrayList<EnfuSignosVitalesMat>();
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionEnfuSignosVitalesMatToAttach : enfuFactsNotas.getEnfuSignosVitalesMatCollection()) {
                enfuSignosVitalesMatCollectionEnfuSignosVitalesMatToAttach = em.getReference(enfuSignosVitalesMatCollectionEnfuSignosVitalesMatToAttach.getClass(), enfuSignosVitalesMatCollectionEnfuSignosVitalesMatToAttach.getId());
                attachedEnfuSignosVitalesMatCollection.add(enfuSignosVitalesMatCollectionEnfuSignosVitalesMatToAttach);
            }
            enfuFactsNotas.setEnfuSignosVitalesMatCollection(attachedEnfuSignosVitalesMatCollection);
            Collection<EnfuMedSuministrados> attachedEnfuMedSuministradosCollection = new ArrayList<EnfuMedSuministrados>();
            for (EnfuMedSuministrados enfuMedSuministradosCollectionEnfuMedSuministradosToAttach : enfuFactsNotas.getEnfuMedSuministradosCollection()) {
                enfuMedSuministradosCollectionEnfuMedSuministradosToAttach = em.getReference(enfuMedSuministradosCollectionEnfuMedSuministradosToAttach.getClass(), enfuMedSuministradosCollectionEnfuMedSuministradosToAttach.getId());
                attachedEnfuMedSuministradosCollection.add(enfuMedSuministradosCollectionEnfuMedSuministradosToAttach);
            }
            enfuFactsNotas.setEnfuMedSuministradosCollection(attachedEnfuMedSuministradosCollection);
            em.persist(enfuFactsNotas);
            for (EnfuGlasgow enfuGlasgowCollectionEnfuGlasgow : enfuFactsNotas.getEnfuGlasgowCollection()) {
                EnfuFactsNotas oldIdFactsNotasOfEnfuGlasgowCollectionEnfuGlasgow = enfuGlasgowCollectionEnfuGlasgow.getIdFactsNotas();
                enfuGlasgowCollectionEnfuGlasgow.setIdFactsNotas(enfuFactsNotas);
                enfuGlasgowCollectionEnfuGlasgow = em.merge(enfuGlasgowCollectionEnfuGlasgow);
                if (oldIdFactsNotasOfEnfuGlasgowCollectionEnfuGlasgow != null) {
                    oldIdFactsNotasOfEnfuGlasgowCollectionEnfuGlasgow.getEnfuGlasgowCollection().remove(enfuGlasgowCollectionEnfuGlasgow);
                    oldIdFactsNotasOfEnfuGlasgowCollectionEnfuGlasgow = em.merge(oldIdFactsNotasOfEnfuGlasgowCollectionEnfuGlasgow);
                }
            }
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales : enfuFactsNotas.getEnfuEspecificacionesGeneralesCollection()) {
                EnfuFactsNotas oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales = enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales.getIdFactsNotas();
                enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales.setIdFactsNotas(enfuFactsNotas);
                enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales = em.merge(enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales);
                if (oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales != null) {
                    oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales.getEnfuEspecificacionesGeneralesCollection().remove(enfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales);
                    oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales = em.merge(oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionEnfuEspecificacionesGenerales);
                }
            }
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionEnfuSignosVitalesDet : enfuFactsNotas.getEnfuSignosVitalesDetCollection()) {
                EnfuFactsNotas oldIdFactsNotasOfEnfuSignosVitalesDetCollectionEnfuSignosVitalesDet = enfuSignosVitalesDetCollectionEnfuSignosVitalesDet.getIdFactsNotas();
                enfuSignosVitalesDetCollectionEnfuSignosVitalesDet.setIdFactsNotas(enfuFactsNotas);
                enfuSignosVitalesDetCollectionEnfuSignosVitalesDet = em.merge(enfuSignosVitalesDetCollectionEnfuSignosVitalesDet);
                if (oldIdFactsNotasOfEnfuSignosVitalesDetCollectionEnfuSignosVitalesDet != null) {
                    oldIdFactsNotasOfEnfuSignosVitalesDetCollectionEnfuSignosVitalesDet.getEnfuSignosVitalesDetCollection().remove(enfuSignosVitalesDetCollectionEnfuSignosVitalesDet);
                    oldIdFactsNotasOfEnfuSignosVitalesDetCollectionEnfuSignosVitalesDet = em.merge(oldIdFactsNotasOfEnfuSignosVitalesDetCollectionEnfuSignosVitalesDet);
                }
            }
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionEnfuSignosVitalesMat : enfuFactsNotas.getEnfuSignosVitalesMatCollection()) {
                EnfuFactsNotas oldIdFactsNotasOfEnfuSignosVitalesMatCollectionEnfuSignosVitalesMat = enfuSignosVitalesMatCollectionEnfuSignosVitalesMat.getIdFactsNotas();
                enfuSignosVitalesMatCollectionEnfuSignosVitalesMat.setIdFactsNotas(enfuFactsNotas);
                enfuSignosVitalesMatCollectionEnfuSignosVitalesMat = em.merge(enfuSignosVitalesMatCollectionEnfuSignosVitalesMat);
                if (oldIdFactsNotasOfEnfuSignosVitalesMatCollectionEnfuSignosVitalesMat != null) {
                    oldIdFactsNotasOfEnfuSignosVitalesMatCollectionEnfuSignosVitalesMat.getEnfuSignosVitalesMatCollection().remove(enfuSignosVitalesMatCollectionEnfuSignosVitalesMat);
                    oldIdFactsNotasOfEnfuSignosVitalesMatCollectionEnfuSignosVitalesMat = em.merge(oldIdFactsNotasOfEnfuSignosVitalesMatCollectionEnfuSignosVitalesMat);
                }
            }
            for (EnfuMedSuministrados enfuMedSuministradosCollectionEnfuMedSuministrados : enfuFactsNotas.getEnfuMedSuministradosCollection()) {
                EnfuFactsNotas oldIdFactsNotasOfEnfuMedSuministradosCollectionEnfuMedSuministrados = enfuMedSuministradosCollectionEnfuMedSuministrados.getIdFactsNotas();
                enfuMedSuministradosCollectionEnfuMedSuministrados.setIdFactsNotas(enfuFactsNotas);
                enfuMedSuministradosCollectionEnfuMedSuministrados = em.merge(enfuMedSuministradosCollectionEnfuMedSuministrados);
                if (oldIdFactsNotasOfEnfuMedSuministradosCollectionEnfuMedSuministrados != null) {
                    oldIdFactsNotasOfEnfuMedSuministradosCollectionEnfuMedSuministrados.getEnfuMedSuministradosCollection().remove(enfuMedSuministradosCollectionEnfuMedSuministrados);
                    oldIdFactsNotasOfEnfuMedSuministradosCollectionEnfuMedSuministrados = em.merge(oldIdFactsNotasOfEnfuMedSuministradosCollectionEnfuMedSuministrados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuFactsNotas enfuFactsNotas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas persistentEnfuFactsNotas = em.find(EnfuFactsNotas.class, enfuFactsNotas.getId());
            Collection<EnfuGlasgow> enfuGlasgowCollectionOld = persistentEnfuFactsNotas.getEnfuGlasgowCollection();
            Collection<EnfuGlasgow> enfuGlasgowCollectionNew = enfuFactsNotas.getEnfuGlasgowCollection();
            Collection<EnfuEspecificacionesGenerales> enfuEspecificacionesGeneralesCollectionOld = persistentEnfuFactsNotas.getEnfuEspecificacionesGeneralesCollection();
            Collection<EnfuEspecificacionesGenerales> enfuEspecificacionesGeneralesCollectionNew = enfuFactsNotas.getEnfuEspecificacionesGeneralesCollection();
            Collection<EnfuSignosVitalesDet> enfuSignosVitalesDetCollectionOld = persistentEnfuFactsNotas.getEnfuSignosVitalesDetCollection();
            Collection<EnfuSignosVitalesDet> enfuSignosVitalesDetCollectionNew = enfuFactsNotas.getEnfuSignosVitalesDetCollection();
            Collection<EnfuSignosVitalesMat> enfuSignosVitalesMatCollectionOld = persistentEnfuFactsNotas.getEnfuSignosVitalesMatCollection();
            Collection<EnfuSignosVitalesMat> enfuSignosVitalesMatCollectionNew = enfuFactsNotas.getEnfuSignosVitalesMatCollection();
            Collection<EnfuMedSuministrados> enfuMedSuministradosCollectionOld = persistentEnfuFactsNotas.getEnfuMedSuministradosCollection();
            Collection<EnfuMedSuministrados> enfuMedSuministradosCollectionNew = enfuFactsNotas.getEnfuMedSuministradosCollection();
            List<String> illegalOrphanMessages = null;
            for (EnfuGlasgow enfuGlasgowCollectionOldEnfuGlasgow : enfuGlasgowCollectionOld) {
                if (!enfuGlasgowCollectionNew.contains(enfuGlasgowCollectionOldEnfuGlasgow)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuGlasgow " + enfuGlasgowCollectionOldEnfuGlasgow + " since its idFactsNotas field is not nullable.");
                }
            }
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionOldEnfuEspecificacionesGenerales : enfuEspecificacionesGeneralesCollectionOld) {
                if (!enfuEspecificacionesGeneralesCollectionNew.contains(enfuEspecificacionesGeneralesCollectionOldEnfuEspecificacionesGenerales)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuEspecificacionesGenerales " + enfuEspecificacionesGeneralesCollectionOldEnfuEspecificacionesGenerales + " since its idFactsNotas field is not nullable.");
                }
            }
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionOldEnfuSignosVitalesDet : enfuSignosVitalesDetCollectionOld) {
                if (!enfuSignosVitalesDetCollectionNew.contains(enfuSignosVitalesDetCollectionOldEnfuSignosVitalesDet)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuSignosVitalesDet " + enfuSignosVitalesDetCollectionOldEnfuSignosVitalesDet + " since its idFactsNotas field is not nullable.");
                }
            }
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionOldEnfuSignosVitalesMat : enfuSignosVitalesMatCollectionOld) {
                if (!enfuSignosVitalesMatCollectionNew.contains(enfuSignosVitalesMatCollectionOldEnfuSignosVitalesMat)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuSignosVitalesMat " + enfuSignosVitalesMatCollectionOldEnfuSignosVitalesMat + " since its idFactsNotas field is not nullable.");
                }
            }
            for (EnfuMedSuministrados enfuMedSuministradosCollectionOldEnfuMedSuministrados : enfuMedSuministradosCollectionOld) {
                if (!enfuMedSuministradosCollectionNew.contains(enfuMedSuministradosCollectionOldEnfuMedSuministrados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuMedSuministrados " + enfuMedSuministradosCollectionOldEnfuMedSuministrados + " since its idFactsNotas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<EnfuGlasgow> attachedEnfuGlasgowCollectionNew = new ArrayList<EnfuGlasgow>();
            for (EnfuGlasgow enfuGlasgowCollectionNewEnfuGlasgowToAttach : enfuGlasgowCollectionNew) {
                enfuGlasgowCollectionNewEnfuGlasgowToAttach = em.getReference(enfuGlasgowCollectionNewEnfuGlasgowToAttach.getClass(), enfuGlasgowCollectionNewEnfuGlasgowToAttach.getId());
                attachedEnfuGlasgowCollectionNew.add(enfuGlasgowCollectionNewEnfuGlasgowToAttach);
            }
            enfuGlasgowCollectionNew = attachedEnfuGlasgowCollectionNew;
            enfuFactsNotas.setEnfuGlasgowCollection(enfuGlasgowCollectionNew);
            Collection<EnfuEspecificacionesGenerales> attachedEnfuEspecificacionesGeneralesCollectionNew = new ArrayList<EnfuEspecificacionesGenerales>();
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGeneralesToAttach : enfuEspecificacionesGeneralesCollectionNew) {
                enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGeneralesToAttach = em.getReference(enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGeneralesToAttach.getClass(), enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGeneralesToAttach.getId());
                attachedEnfuEspecificacionesGeneralesCollectionNew.add(enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGeneralesToAttach);
            }
            enfuEspecificacionesGeneralesCollectionNew = attachedEnfuEspecificacionesGeneralesCollectionNew;
            enfuFactsNotas.setEnfuEspecificacionesGeneralesCollection(enfuEspecificacionesGeneralesCollectionNew);
            Collection<EnfuSignosVitalesDet> attachedEnfuSignosVitalesDetCollectionNew = new ArrayList<EnfuSignosVitalesDet>();
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDetToAttach : enfuSignosVitalesDetCollectionNew) {
                enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDetToAttach = em.getReference(enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDetToAttach.getClass(), enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDetToAttach.getId());
                attachedEnfuSignosVitalesDetCollectionNew.add(enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDetToAttach);
            }
            enfuSignosVitalesDetCollectionNew = attachedEnfuSignosVitalesDetCollectionNew;
            enfuFactsNotas.setEnfuSignosVitalesDetCollection(enfuSignosVitalesDetCollectionNew);
            Collection<EnfuSignosVitalesMat> attachedEnfuSignosVitalesMatCollectionNew = new ArrayList<EnfuSignosVitalesMat>();
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMatToAttach : enfuSignosVitalesMatCollectionNew) {
                enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMatToAttach = em.getReference(enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMatToAttach.getClass(), enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMatToAttach.getId());
                attachedEnfuSignosVitalesMatCollectionNew.add(enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMatToAttach);
            }
            enfuSignosVitalesMatCollectionNew = attachedEnfuSignosVitalesMatCollectionNew;
            enfuFactsNotas.setEnfuSignosVitalesMatCollection(enfuSignosVitalesMatCollectionNew);
            Collection<EnfuMedSuministrados> attachedEnfuMedSuministradosCollectionNew = new ArrayList<EnfuMedSuministrados>();
            for (EnfuMedSuministrados enfuMedSuministradosCollectionNewEnfuMedSuministradosToAttach : enfuMedSuministradosCollectionNew) {
                enfuMedSuministradosCollectionNewEnfuMedSuministradosToAttach = em.getReference(enfuMedSuministradosCollectionNewEnfuMedSuministradosToAttach.getClass(), enfuMedSuministradosCollectionNewEnfuMedSuministradosToAttach.getId());
                attachedEnfuMedSuministradosCollectionNew.add(enfuMedSuministradosCollectionNewEnfuMedSuministradosToAttach);
            }
            enfuMedSuministradosCollectionNew = attachedEnfuMedSuministradosCollectionNew;
            enfuFactsNotas.setEnfuMedSuministradosCollection(enfuMedSuministradosCollectionNew);
            enfuFactsNotas = em.merge(enfuFactsNotas);
            for (EnfuGlasgow enfuGlasgowCollectionNewEnfuGlasgow : enfuGlasgowCollectionNew) {
                if (!enfuGlasgowCollectionOld.contains(enfuGlasgowCollectionNewEnfuGlasgow)) {
                    EnfuFactsNotas oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow = enfuGlasgowCollectionNewEnfuGlasgow.getIdFactsNotas();
                    enfuGlasgowCollectionNewEnfuGlasgow.setIdFactsNotas(enfuFactsNotas);
                    enfuGlasgowCollectionNewEnfuGlasgow = em.merge(enfuGlasgowCollectionNewEnfuGlasgow);
                    if (oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow != null && !oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow.equals(enfuFactsNotas)) {
                        oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow.getEnfuGlasgowCollection().remove(enfuGlasgowCollectionNewEnfuGlasgow);
                        oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow = em.merge(oldIdFactsNotasOfEnfuGlasgowCollectionNewEnfuGlasgow);
                    }
                }
            }
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales : enfuEspecificacionesGeneralesCollectionNew) {
                if (!enfuEspecificacionesGeneralesCollectionOld.contains(enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales)) {
                    EnfuFactsNotas oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales = enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales.getIdFactsNotas();
                    enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales.setIdFactsNotas(enfuFactsNotas);
                    enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales = em.merge(enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales);
                    if (oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales != null && !oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales.equals(enfuFactsNotas)) {
                        oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales.getEnfuEspecificacionesGeneralesCollection().remove(enfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales);
                        oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales = em.merge(oldIdFactsNotasOfEnfuEspecificacionesGeneralesCollectionNewEnfuEspecificacionesGenerales);
                    }
                }
            }
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet : enfuSignosVitalesDetCollectionNew) {
                if (!enfuSignosVitalesDetCollectionOld.contains(enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet)) {
                    EnfuFactsNotas oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet = enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet.getIdFactsNotas();
                    enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet.setIdFactsNotas(enfuFactsNotas);
                    enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet = em.merge(enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet);
                    if (oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet != null && !oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet.equals(enfuFactsNotas)) {
                        oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet.getEnfuSignosVitalesDetCollection().remove(enfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet);
                        oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet = em.merge(oldIdFactsNotasOfEnfuSignosVitalesDetCollectionNewEnfuSignosVitalesDet);
                    }
                }
            }
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat : enfuSignosVitalesMatCollectionNew) {
                if (!enfuSignosVitalesMatCollectionOld.contains(enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat)) {
                    EnfuFactsNotas oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat = enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat.getIdFactsNotas();
                    enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat.setIdFactsNotas(enfuFactsNotas);
                    enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat = em.merge(enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat);
                    if (oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat != null && !oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat.equals(enfuFactsNotas)) {
                        oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat.getEnfuSignosVitalesMatCollection().remove(enfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat);
                        oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat = em.merge(oldIdFactsNotasOfEnfuSignosVitalesMatCollectionNewEnfuSignosVitalesMat);
                    }
                }
            }
            for (EnfuMedSuministrados enfuMedSuministradosCollectionNewEnfuMedSuministrados : enfuMedSuministradosCollectionNew) {
                if (!enfuMedSuministradosCollectionOld.contains(enfuMedSuministradosCollectionNewEnfuMedSuministrados)) {
                    EnfuFactsNotas oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados = enfuMedSuministradosCollectionNewEnfuMedSuministrados.getIdFactsNotas();
                    enfuMedSuministradosCollectionNewEnfuMedSuministrados.setIdFactsNotas(enfuFactsNotas);
                    enfuMedSuministradosCollectionNewEnfuMedSuministrados = em.merge(enfuMedSuministradosCollectionNewEnfuMedSuministrados);
                    if (oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados != null && !oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados.equals(enfuFactsNotas)) {
                        oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados.getEnfuMedSuministradosCollection().remove(enfuMedSuministradosCollectionNewEnfuMedSuministrados);
                        oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados = em.merge(oldIdFactsNotasOfEnfuMedSuministradosCollectionNewEnfuMedSuministrados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuFactsNotas.getId();
                if (findEnfuFactsNotas(id) == null) {
                    throw new NonexistentEntityException("The enfuFactsNotas with id " + id + " no longer exists.");
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
            EnfuFactsNotas enfuFactsNotas;
            try {
                enfuFactsNotas = em.getReference(EnfuFactsNotas.class, id);
                enfuFactsNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuFactsNotas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EnfuGlasgow> enfuGlasgowCollectionOrphanCheck = enfuFactsNotas.getEnfuGlasgowCollection();
            for (EnfuGlasgow enfuGlasgowCollectionOrphanCheckEnfuGlasgow : enfuGlasgowCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsNotas (" + enfuFactsNotas + ") cannot be destroyed since the EnfuGlasgow " + enfuGlasgowCollectionOrphanCheckEnfuGlasgow + " in its enfuGlasgowCollection field has a non-nullable idFactsNotas field.");
            }
            Collection<EnfuEspecificacionesGenerales> enfuEspecificacionesGeneralesCollectionOrphanCheck = enfuFactsNotas.getEnfuEspecificacionesGeneralesCollection();
            for (EnfuEspecificacionesGenerales enfuEspecificacionesGeneralesCollectionOrphanCheckEnfuEspecificacionesGenerales : enfuEspecificacionesGeneralesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsNotas (" + enfuFactsNotas + ") cannot be destroyed since the EnfuEspecificacionesGenerales " + enfuEspecificacionesGeneralesCollectionOrphanCheckEnfuEspecificacionesGenerales + " in its enfuEspecificacionesGeneralesCollection field has a non-nullable idFactsNotas field.");
            }
            Collection<EnfuSignosVitalesDet> enfuSignosVitalesDetCollectionOrphanCheck = enfuFactsNotas.getEnfuSignosVitalesDetCollection();
            for (EnfuSignosVitalesDet enfuSignosVitalesDetCollectionOrphanCheckEnfuSignosVitalesDet : enfuSignosVitalesDetCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsNotas (" + enfuFactsNotas + ") cannot be destroyed since the EnfuSignosVitalesDet " + enfuSignosVitalesDetCollectionOrphanCheckEnfuSignosVitalesDet + " in its enfuSignosVitalesDetCollection field has a non-nullable idFactsNotas field.");
            }
            Collection<EnfuSignosVitalesMat> enfuSignosVitalesMatCollectionOrphanCheck = enfuFactsNotas.getEnfuSignosVitalesMatCollection();
            for (EnfuSignosVitalesMat enfuSignosVitalesMatCollectionOrphanCheckEnfuSignosVitalesMat : enfuSignosVitalesMatCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsNotas (" + enfuFactsNotas + ") cannot be destroyed since the EnfuSignosVitalesMat " + enfuSignosVitalesMatCollectionOrphanCheckEnfuSignosVitalesMat + " in its enfuSignosVitalesMatCollection field has a non-nullable idFactsNotas field.");
            }
            Collection<EnfuMedSuministrados> enfuMedSuministradosCollectionOrphanCheck = enfuFactsNotas.getEnfuMedSuministradosCollection();
            for (EnfuMedSuministrados enfuMedSuministradosCollectionOrphanCheckEnfuMedSuministrados : enfuMedSuministradosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsNotas (" + enfuFactsNotas + ") cannot be destroyed since the EnfuMedSuministrados " + enfuMedSuministradosCollectionOrphanCheckEnfuMedSuministrados + " in its enfuMedSuministradosCollection field has a non-nullable idFactsNotas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enfuFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuFactsNotas> findEnfuFactsNotasEntities() {
        return findEnfuFactsNotasEntities(true, -1, -1);
    }

    public List<EnfuFactsNotas> findEnfuFactsNotasEntities(int maxResults, int firstResult) {
        return findEnfuFactsNotasEntities(false, maxResults, firstResult);
    }

    private List<EnfuFactsNotas> findEnfuFactsNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuFactsNotas.class));
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

    public EnfuFactsNotas findEnfuFactsNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuFactsNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuFactsNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuFactsNotas> rt = cq.from(EnfuFactsNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }    

    public List<EnfuFactsNotas> find_Notas(InfoHistoriac hi) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM EnfuFactsNotas i WHERE i.idHistoriac=:h AND i.estado='1'")
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .setParameter("h", hi)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<EnfuFactsNotas> find_Notas2(InfoHistoriac hi) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT i FROM EnfuFactsNotas i WHERE i.idHistoriac=:h");
        Q.setParameter("h", hi);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
