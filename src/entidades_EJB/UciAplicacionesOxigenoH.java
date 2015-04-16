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
@Table(name = "uci_aplicaciones_oxigeno")
@NamedQueries({
    @NamedQuery(name = "UciAplicacionesOxigenoH.findAll", query = "SELECT u FROM UciAplicacionesOxigenoH u")})
public class UciAplicacionesOxigenoH implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "medico")
    private int medico;
    @Column(name = "sis_O2")
    private Integer sisO2;
    @Column(name = "fiO2")
    private Integer fiO2;
    @Column(name = "lts")
    private Integer lts;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idUciHistoriac;

    public UciHistoriac getIdUciHistoriac() {
        return idUciHistoriac;
    }

    public void setIdUciHistoriac(UciHistoriac idUciHistoriac) {
        this.idUciHistoriac = idUciHistoriac;
    }

    public UciAplicacionesOxigenoH() {
    }

    public UciAplicacionesOxigenoH(Integer id) {
        this.id = id;
    }

    public UciAplicacionesOxigenoH(Integer id, int medico) {
        this.id = id;
        this.medico = medico;
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

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public Integer getSisO2() {
        return sisO2;
    }

    public void setSisO2(Integer sisO2) {
        this.sisO2 = sisO2;
    }

    public Integer getFiO2() {
        return fiO2;
    }

    public void setFiO2(Integer fiO2) {
        this.fiO2 = fiO2;
    }

    public Integer getLts() {
        return lts;
    }

    public void setLts(Integer lts) {
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
        if (!(object instanceof UciAplicacionesOxigenoH)) {
            return false;
        }
        UciAplicacionesOxigenoH other = (UciAplicacionesOxigenoH) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciAplicacionesOxigenoH[ id=" + id + " ]";
    }
    
}
