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
 * @author Juan Camilo
 */
@Entity
@Table(name = "uci_evo_orden_procedimiento_desc")
@NamedQueries({
    @NamedQuery(name = "UciEvoOrdenProcedimientoDesc.findAll", query = "SELECT u FROM UciEvoOrdenProcedimientoDesc u")})
public class UciEvoOrdenProcedimientoDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_config_cups")
    private int idConfigCups;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "id_evu_orden_procedimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UciEvoOrdenProcedimiento idEvuOrdenProcedimiento;

    public UciEvoOrdenProcedimientoDesc() {
    }

    public UciEvoOrdenProcedimientoDesc(Integer id) {
        this.id = id;
    }

    public UciEvoOrdenProcedimientoDesc(Integer id, int idConfigCups, int estado) {
        this.id = id;
        this.idConfigCups = idConfigCups;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UciEvoOrdenProcedimiento getIdEvuOrdenProcedimiento() {
        return idEvuOrdenProcedimiento;
    }

    public void setIdEvuOrdenProcedimiento(UciEvoOrdenProcedimiento idEvuOrdenProcedimiento) {
        this.idEvuOrdenProcedimiento = idEvuOrdenProcedimiento;
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
        if (!(object instanceof UciEvoOrdenProcedimientoDesc)) {
            return false;
        }
        UciEvoOrdenProcedimientoDesc other = (UciEvoOrdenProcedimientoDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciEvoOrdenProcedimientoDesc[ id=" + id + " ]";
    }
    
}
