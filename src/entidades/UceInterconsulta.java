
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
@Table(name = "uce_interconsulta")
@NamedQueries({
    @NamedQuery(name = "UceInterconsulta.findAll", query = "SELECT i FROM UceInterconsulta i")})
    public class UceInterconsulta implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_config_cups")
    private int idConfigCups;
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
    @Column(name = "id_especialidades")
    private int idEspecialidades;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;

    public UceInterconsulta() {
    }

    public UceInterconsulta(Integer id) {
        this.id = id;
    }

    public UceInterconsulta(Integer id, int idHistoriac, int idEspecialidades) {
        this.id = id;
        this.idHistoriac = idHistoriac;
        this.idEspecialidades = idEspecialidades;
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

    public int getIdEspecialidades() {
        return idEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.idEspecialidades = idEspecialidades;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
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
        if (!(object instanceof UceInterconsulta)) {
            return false;
        }
        UceInterconsulta other = (UceInterconsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UceInterconsulta[ id=" + id + " ]";
    }

    public int getIdConfigCups() {
        return idConfigCups;
    }

    public void setIdConfigCups(int idConfigCups) {
        this.idConfigCups = idConfigCups;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
