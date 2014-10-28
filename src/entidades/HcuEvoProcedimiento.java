/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "hcu_evo_procedimiento")
@NamedQueries({
    @NamedQuery(name = "HcuEvoProcedimiento.findAll", query = "SELECT h FROM HcuEvoProcedimiento h")})
public class HcuEvoProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evolucion",referencedColumnName = "id")
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private HcuEvolucion idHcuEvolucion;
    @JoinColumn(name = "id_config_cups",referencedColumnName = "id")
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private ConfigCups idConfigCups;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;

    public HcuEvoProcedimiento() {
    }

    public HcuEvoProcedimiento(Integer id) {
        this.id = id;
    }

    public HcuEvoProcedimiento(Integer id, HcuEvolucion idHcuEvolucion, ConfigCups idConfigCups, int idUsuario) {
        this.id = id;
        this.idHcuEvolucion = idHcuEvolucion;
        this.idConfigCups = idConfigCups;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HcuEvolucion getIdHcuEvolucion() {
        return idHcuEvolucion;
    }

    public void setIdHcuEvolucion(HcuEvolucion idHcuEvolucion) {
        this.idHcuEvolucion = idHcuEvolucion;
    }

    public ConfigCups getIdConfigCups() {
        return idConfigCups;
    }

    public void setIdConfigCups(ConfigCups idConfigCups) {
        this.idConfigCups = idConfigCups;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof HcuEvoProcedimiento)) {
            return false;
        }
        HcuEvoProcedimiento other = (HcuEvoProcedimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuEvoProcedimiento[ id=" + id + " ]";
    }
    
}