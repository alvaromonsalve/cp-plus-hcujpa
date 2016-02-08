/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "arch_ubicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchUbicaciones.findAll", query = "SELECT a FROM ArchUbicaciones a"),
    @NamedQuery(name = "ArchUbicaciones.findById", query = "SELECT a FROM ArchUbicaciones a WHERE a.id = :id"),
    @NamedQuery(name = "ArchUbicaciones.findByIdentificador", query = "SELECT a FROM ArchUbicaciones a WHERE a.identificador = :identificador"),
    @NamedQuery(name = "ArchUbicaciones.findByPaciente", query = "SELECT a FROM ArchUbicaciones a WHERE a.paciente = :paciente"),
    @NamedQuery(name = "ArchUbicaciones.findByEstado", query = "SELECT a FROM ArchUbicaciones a WHERE a.estado = :estado"),
    @NamedQuery(name = "ArchUbicaciones.findByUsr", query = "SELECT a FROM ArchUbicaciones a WHERE a.usr = :usr"),
    @NamedQuery(name = "ArchUbicaciones.findByFechaIngresoDatos", query = "SELECT a FROM ArchUbicaciones a WHERE a.fechaIngresoDatos = :fechaIngresoDatos")})
public class ArchUbicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "identificador")
    private String identificador;
    @Basic(optional = false)
    @Column(name = "paciente")
    private String paciente;
    @Basic(optional=false)
    @Column(name="id_ubicacion")
    private int idUbicacion;
    @Lob
    @Column(name = "ruta")
    private String ruta;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;

    public ArchUbicaciones() {
    }

    public ArchUbicaciones(Integer id) {
        this.id = id;
    }

    public ArchUbicaciones(Integer id, String identificador, String paciente, Date fechaIngresoDatos) {
        this.id = id;
        this.identificador = identificador;
        this.paciente = paciente;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
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

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof ArchUbicaciones)) {
            return false;
        }
        ArchUbicaciones other = (ArchUbicaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_AR.ArchUbicaciones[ id=" + id + " ]";
    }
    
}
