/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuLogs.findAll", query = "SELECT e FROM EnfuLogs e"),
    @NamedQuery(name = "EnfuLogs.findById", query = "SELECT e FROM EnfuLogs e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuLogs.findByAccion", query = "SELECT e FROM EnfuLogs e WHERE e.accion = :accion"),
    @NamedQuery(name = "EnfuLogs.findByUsuario", query = "SELECT e FROM EnfuLogs e WHERE e.usuario = :usuario"),
    @NamedQuery(name = "EnfuLogs.findByIndicador", query = "SELECT e FROM EnfuLogs e WHERE e.indicador = :indicador"),
    @NamedQuery(name = "EnfuLogs.findByEstado", query = "SELECT e FROM EnfuLogs e WHERE e.estado = :estado")})
public class EnfuLogs implements Serializable {
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

    public EnfuLogs() {
    }

    public EnfuLogs(Integer id) {
        this.id = id;
    }

    public EnfuLogs(Integer id, int accion, int usuario, int indicador, char estado) {
        this.id = id;
        this.accion = accion;
        this.usuario = usuario;
        this.indicador = indicador;
        this.estado = estado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnfuLogs)) {
            return false;
        }
        EnfuLogs other = (EnfuLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuLogs[ id=" + id + " ]";
    }
    
}
