/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "uci_orden_procedimiento_desc")
@NamedQueries({
    @NamedQuery(name = "UciOrdenProcedimientoDesc.findAll", query = "SELECT u FROM UciOrdenProcedimientoDesc u")})
public class UciOrdenProcedimientoDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_orden_procedimiento")
    private int idOrdenProcedimiento;
    @Basic(optional = false)
    @Column(name = "id_config_cups")
    private int idConfigCups;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;

    public UciOrdenProcedimientoDesc() {
    }

    public UciOrdenProcedimientoDesc(Integer id) {
        this.id = id;
    }

    public UciOrdenProcedimientoDesc(Integer id, int idOrdenProcedimiento, int idConfigCups, int estado) {
        this.id = id;
        this.idOrdenProcedimiento = idOrdenProcedimiento;
        this.idConfigCups = idConfigCups;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdOrdenProcedimiento() {
        return idOrdenProcedimiento;
    }

    public void setIdOrdenProcedimiento(int idOrdenProcedimiento) {
        this.idOrdenProcedimiento = idOrdenProcedimiento;
    }

    public int getIdConfigCups() {
        return idConfigCups;
    }

    public void setIdConfigCups(int idConfigCups) {
        this.idConfigCups = idConfigCups;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof UciOrdenProcedimientoDesc)) {
            return false;
        }
        UciOrdenProcedimientoDesc other = (UciOrdenProcedimientoDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciOrdenProcedimientoDesc[ id=" + id + " ]";
    }
    
}
