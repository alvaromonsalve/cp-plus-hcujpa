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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uci_solicitud_transfusiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciSolicitudTransfusiones.findAll", query = "SELECT u FROM UciSolicitudTransfusiones u"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findById", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.id = :id"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByIdentificador", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.identificador = :identificador"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByTipo", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByCantidad", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.cantidad = :cantidad"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByGrupoSanguineo", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.grupoSanguineo = :grupoSanguineo"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByFactor", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.factor = :factor"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByUsr", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.usr = :usr"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByFechaIngresoDatos", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.fechaIngresoDatos = :fechaIngresoDatos"),
    @NamedQuery(name = "UciSolicitudTransfusiones.findByEstado", query = "SELECT u FROM UciSolicitudTransfusiones u WHERE u.estado = :estado")})
public class UciSolicitudTransfusiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "identificador")
    private int identificador;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "cantidad")
    private String cantidad;
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;
    @Column(name = "factor")
    private String factor;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_historia_c", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idHistoriaC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransfusion")
    private List<UciAplicacionesTransfusion> uciAplicacionesTransfusionList;

    public UciSolicitudTransfusiones() {
    }

    public UciSolicitudTransfusiones(Integer id) {
        this.id = id;
    }

    public UciSolicitudTransfusiones(Integer id, int identificador, Date fechaIngresoDatos) {
        this.id = id;
        this.identificador = identificador;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public UciHistoriac getIdHistoriaC() {
        return idHistoriaC;
    }

    public void setIdHistoriaC(UciHistoriac idHistoriaC) {
        this.idHistoriaC = idHistoriaC;
    }

    @XmlTransient
    public List<UciAplicacionesTransfusion> getUciAplicacionesTransfusionList() {
        return uciAplicacionesTransfusionList;
    }

    public void setUciAplicacionesTransfusionList(List<UciAplicacionesTransfusion> uciAplicacionesTransfusionList) {
        this.uciAplicacionesTransfusionList = uciAplicacionesTransfusionList;
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
        if (!(object instanceof UciSolicitudTransfusiones)) {
            return false;
        }
        UciSolicitudTransfusiones other = (UciSolicitudTransfusiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciSolicitudTransfusiones[ id=" + id + " ]";
    }
    
}
