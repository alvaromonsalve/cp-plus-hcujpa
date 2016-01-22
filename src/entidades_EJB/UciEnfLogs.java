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
@Table(name = "uci_enf_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospEnfLogs.findAll", query = "SELECT h FROM HospEnfLogs h"),
    @NamedQuery(name = "HospEnfLogs.findById", query = "SELECT h FROM HospEnfLogs h WHERE h.id = :id"),
    @NamedQuery(name = "HospEnfLogs.findByAccion", query = "SELECT h FROM HospEnfLogs h WHERE h.accion = :accion"),
    @NamedQuery(name = "HospEnfLogs.findByUsuario", query = "SELECT h FROM HospEnfLogs h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HospEnfLogs.findByIndicador", query = "SELECT h FROM HospEnfLogs h WHERE h.indicador = :indicador"),
    @NamedQuery(name = "HospEnfLogs.findByEstado", query = "SELECT h FROM HospEnfLogs h WHERE h.estado = :estado"),
    @NamedQuery(name = "HospEnfLogs.findByFechaIng", query = "SELECT h FROM HospEnfLogs h WHERE h.fechaIng = :fechaIng")})
public class UciEnfLogs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "accion")
    private int accion;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "indicador")
    private int indicador;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @Basic(optional = false)
    @Column(name = "fecha_ing")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIng;

    public UciEnfLogs() {
    }

    public UciEnfLogs(Integer id) {
        this.id = id;
    }

    public UciEnfLogs(Integer id, int accion, int usuario, int indicador, char estado, Date fechaIng) {
        this.id = id;
        this.accion = accion;
        this.usuario = usuario;
        this.indicador = indicador;
        this.estado = estado;
        this.fechaIng = fechaIng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
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
        if (!(object instanceof UciEnfLogs)) {
            return false;
        }
        UciEnfLogs other = (UciEnfLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospEnfLogs[ id=" + id + " ]";
    }
    
}
