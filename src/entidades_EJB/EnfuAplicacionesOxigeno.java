/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "enfu_aplicaciones_oxigeno")
@NamedQueries({
    @NamedQuery(name = "EnfuAplicacionesOxigeno.findAll", query = "SELECT u FROM EnfuAplicacionesOxigeno u")})
public class EnfuAplicacionesOxigeno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_inicio")
    private String horaInicio;
    @JoinColumn(name = "medico")
    private CmProfesionales cmProfesionales;
    @Column(name = "sis_O2")
    private Integer sisO2;
    @Column(name = "fiO2")
    private String fiO2;
    @Column(name = "lts")
    private String lts;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idInfoHistoriac;

    public InfoHistoriac getIdInfoHistoriac() {
        return idInfoHistoriac;
    }

    public void setIdInfoHistoriac(InfoHistoriac idInfoHistoriac) {
        this.idInfoHistoriac = idInfoHistoriac;
    }

    public EnfuAplicacionesOxigeno() {
    }

    public EnfuAplicacionesOxigeno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public CmProfesionales getCmProfesionales() {
        return cmProfesionales;
    }

    public void setCmProfesionales(CmProfesionales cmProfesionales) {
        this.cmProfesionales = cmProfesionales;
    }
    public Integer getSisO2() {
        return sisO2;
    }

    public void setSisO2(Integer sisO2) {
        this.sisO2 = sisO2;
    }

    public String getFiO2() {
        return fiO2;
    }

    public void setFiO2(String fiO2) {
        this.fiO2 = fiO2;
    }

    public String getLts() {
        return lts;
    }

    public void setLts(String lts) {
        this.lts = lts;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnfuAplicacionesOxigeno)) {
            return false;
        }
        EnfuAplicacionesOxigeno other = (EnfuAplicacionesOxigeno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.EnfuAplicacionesOxigeno[ id=" + id + " ]";
    }

}
