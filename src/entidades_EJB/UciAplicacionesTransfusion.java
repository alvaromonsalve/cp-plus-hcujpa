/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uci_aplicaciones_transfusion")
public class UciAplicacionesTransfusion implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAplicacionTransfusion")
    private List<UciAplicacionesTransfusionSignos> uciAplicacionesTransfusionSignosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAplicacionTransfusion")
    private List<UciAplicacionesTransfusionSellos> uciAplicacionesTransfusionSellosList;
    @JoinColumn(name = "id_transfusion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciSolicitudTransfusiones idTransfusion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Column(name = "componente")
    private Integer componente;
    @Column(name = "bolsa")
    private Integer bolsa;
    @Lob
    @Column(name = "reacciones_adversas")
    private String reaccionesAdversas;
    @Lob
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Lob
    @Column(name = "tratamiento")
    private String tratamiento;
    @Lob
    @Column(name = "inspeccion")
    private String inspeccion;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Integer estado;

    public UciAplicacionesTransfusion() {
    }

    public UciAplicacionesTransfusion(Integer id) {
        this.id = id;
    }

    public UciAplicacionesTransfusion(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getComponente() {
        return componente;
    }

    public void setComponente(Integer componente) {
        this.componente = componente;
    }

    public Integer getBolsa() {
        return bolsa;
    }

    public void setBolsa(Integer bolsa) {
        this.bolsa = bolsa;
    }

    public String getReaccionesAdversas() {
        return reaccionesAdversas;
    }

    public void setReaccionesAdversas(String reaccionesAdversas) {
        this.reaccionesAdversas = reaccionesAdversas;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        this.inspeccion = inspeccion;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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
        if (!(object instanceof UciAplicacionesTransfusion)) {
            return false;
        }
        UciAplicacionesTransfusion other = (UciAplicacionesTransfusion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciAplicacionesTransfusion[ id=" + id + " ]";
    }

    @XmlTransient
    public List<UciAplicacionesTransfusionSignos> getUciAplicacionesTransfusionSignosList() {
        return uciAplicacionesTransfusionSignosList;
    }

    public void setUciAplicacionesTransfusionSignosList(List<UciAplicacionesTransfusionSignos> uciAplicacionesTransfusionSignosList) {
        this.uciAplicacionesTransfusionSignosList = uciAplicacionesTransfusionSignosList;
    }

    @XmlTransient
    public List<UciAplicacionesTransfusionSellos> getUciAplicacionesTransfusionSellosList() {
        return uciAplicacionesTransfusionSellosList;
    }

    public void setUciAplicacionesTransfusionSellosList(List<UciAplicacionesTransfusionSellos> uciAplicacionesTransfusionSellosList) {
        this.uciAplicacionesTransfusionSellosList = uciAplicacionesTransfusionSellosList;
    }

    public UciSolicitudTransfusiones getIdTransfusion() {
        return idTransfusion;
    }

    public void setIdTransfusion(UciSolicitudTransfusiones idTransfusion) {
        this.idTransfusion = idTransfusion;
    }
    
}
