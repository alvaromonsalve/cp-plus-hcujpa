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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JUDMEZ
 */
@Entity
@Table(name = "hosp_aplicaciones_oxigeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospAplicacionesOxigeno.findAll", query = "SELECT h FROM HospAplicacionesOxigeno h"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findById", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.id = :id"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByFecha", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByHoraInicio", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByMedico", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.medico = :medico"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findBySisO2", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.sisO2 = :sisO2"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByFiO2", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.fiO2 = :fiO2"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByLts", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.lts = :lts"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByUsr", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.usr = :usr"),
    @NamedQuery(name = "HospAplicacionesOxigeno.findByEstado", query = "SELECT h FROM HospAplicacionesOxigeno h WHERE h.estado = :estado")})
public class HospAplicacionesOxigeno implements Serializable {
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
    private HospHistoriac idHistoriac;
    @JoinColumn(name = "medico", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CmProfesionales medico;
    
    public HospAplicacionesOxigeno() {
    }

    public HospAplicacionesOxigeno(Integer id) {
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

    public CmProfesionales getMedico() {
        return medico;
    }

    public void setMedico(CmProfesionales medico) {
        this.medico = medico;
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

    public HospHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(HospHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
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
        if (!(object instanceof HospAplicacionesOxigeno)) {
            return false;
        }
        HospAplicacionesOxigeno other = (HospAplicacionesOxigeno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospAplicacionesOxigeno[ id=" + id + " ]";
    }
    
}
