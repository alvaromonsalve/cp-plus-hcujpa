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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_anexo_3")

public class CeAnexo3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "manejo_integral")
    private String manejoIntegral;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "cama")
    private String cama;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "prioridad")
    private Character prioridad;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fechaingresodatos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingresodatos;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoria;

    public CeAnexo3() {
    }

    public CeAnexo3(Integer id) {
        this.id = id;
    }

    public CeAnexo3(Integer id, Date fechaingresodatos) {
        this.id = id;
        this.fechaingresodatos = fechaingresodatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManejoIntegral() {
        return manejoIntegral;
    }

    public void setManejoIntegral(String manejoIntegral) {
        this.manejoIntegral = manejoIntegral;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Character getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Character prioridad) {
        this.prioridad = prioridad;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaingresodatos() {
        return fechaingresodatos;
    }

    public void setFechaingresodatos(Date fechaingresodatos) {
        this.fechaingresodatos = fechaingresodatos;
    }

    public CeHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(CeHistoriac idHistoria) {
        this.idHistoria = idHistoria;
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
        if (!(object instanceof CeAnexo3)) {
            return false;
        }
        CeAnexo3 other = (CeAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.CeAnexo3[ id=" + id + " ]";
    }
    
}
