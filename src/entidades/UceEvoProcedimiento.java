
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
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "uce_evo_procedimiento")
@NamedQueries({
    @NamedQuery(name = "UceEvoProcedimiento.findAll", query = "SELECT h FROM UceEvoProcedimiento h")})
public class UceEvoProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evolucion",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private UceEvolucion idUceEvolucion;
    @JoinColumn(name = "id_config_cups",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private ConfigCups idConfigCups;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;

    public UceEvoProcedimiento() {
    }

    public UceEvoProcedimiento(Integer id) {
        this.id = id;
    }

    public UceEvoProcedimiento(Integer id, UceEvolucion idUceEvolucion, ConfigCups idConfigCups, int idUsuario) {
        this.id = id;
        this.idUceEvolucion = idUceEvolucion;
        this.idConfigCups = idConfigCups;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UceEvolucion getIdUceEvolucion() {
        return idUceEvolucion;
    }

    public void setIdUceEvolucion(UceEvolucion idUceEvolucion) {
        this.idUceEvolucion = idUceEvolucion;
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
        if (!(object instanceof UceEvoProcedimiento)) {
            return false;
        }
        UceEvoProcedimiento other = (UceEvoProcedimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UceEvoProcedimiento[ id=" + id + " ]";
    }

}
