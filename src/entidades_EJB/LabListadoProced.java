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
import javax.persistence.Lob;
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
@Table(name = "lab_listado_proced")
@NamedQueries({
    @NamedQuery(name = "LabListadoProced.findAll", query = "SELECT l FROM LabListadoProced l")})
public class LabListadoProced implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tiporegistro")
    private short tiporegistro;
    @Basic(optional = false)
    @Column(name = "idregistro")
    private int idregistro;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "idusuario")
    private int idusuario;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @Column(name = "fecha_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDato;

    public LabListadoProced() {
    }

    public LabListadoProced(Integer id) {
        this.id = id;
    }

    public LabListadoProced(Integer id, short tiporegistro, int idregistro, String observacion, int idusuario, short estado, Date fechaDato) {
        this.id = id;
        this.tiporegistro = tiporegistro;
        this.idregistro = idregistro;
        this.observacion = observacion;
        this.idusuario = idusuario;
        this.estado = estado;
        this.fechaDato = fechaDato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTiporegistro() {
        return tiporegistro;
    }

    public void setTiporegistro(short tiporegistro) {
        this.tiporegistro = tiporegistro;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFechaDato() {
        return fechaDato;
    }

    public void setFechaDato(Date fechaDato) {
        this.fechaDato = fechaDato;
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
        if (!(object instanceof LabListadoProced)) {
            return false;
        }
        LabListadoProced other = (LabListadoProced) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.LabListadoProced[ id=" + id + " ]";
    }
    
}
