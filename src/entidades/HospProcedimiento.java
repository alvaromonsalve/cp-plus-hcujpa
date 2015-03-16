
package entidades;

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
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hosp_procedimiento")
@NamedQueries({
    @NamedQuery(name = "HospProcedimiento.findAll", query = "SELECT i FROM HospProcedimiento i")})
public class HospProcedimiento implements Serializable {
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_historiac")
    private int idHistoriac;
    @Basic(optional = false)
    @Column(name = "id_cups")
    private int idCups;
    @Lob
    @Column(name = "observacion")
    private String observacion;

    public HospProcedimiento() {
    }

    public HospProcedimiento(Integer id) {
        this.id = id;
    }

    public HospProcedimiento(Integer id, int idHistoriac, int idCups) {
        this.id = id;
        this.idHistoriac = idHistoriac;
        this.idCups = idCups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(int idHistoriac) {
        this.idHistoriac = idHistoriac;
    }

    public int getIdCups() {
        return idCups;
    }

    public void setIdCups(int idCups) {
        this.idCups = idCups;
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
        if (!(object instanceof HospProcedimiento)) {
            return false;
        }
        HospProcedimiento other = (HospProcedimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HospProcedimiento[ id=" + id + " ]";
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

}
