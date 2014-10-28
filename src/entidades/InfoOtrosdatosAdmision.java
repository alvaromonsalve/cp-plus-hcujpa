
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_Otros_datos_Admision")
@NamedQueries({
    @NamedQuery(name = "InfoOtrosdatosAdmision.findAll", query = "SELECT i FROM InfoOtrosdatosAdmision i")})
public class InfoOtrosdatosAdmision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_admision")
    private Integer idAdmision;
    @Column(name = "materna")
    private String materna;
    @Column(name = "exposicion_dolor")
    private String exposicionDolor;
    @Column(name = "estado")
    private Character estado;

    public InfoOtrosdatosAdmision() {
    }

    public InfoOtrosdatosAdmision(Integer idAdmision) {
        this.idAdmision = idAdmision;
    }

    public Integer getIdAdmision() {
        return idAdmision;
    }

    public void setIdAdmision(Integer idAdmision) {
        this.idAdmision = idAdmision;
    }

    public String getMaterna() {
        return materna;
    }

    public void setMaterna(String materna) {
        this.materna = materna;
    }

    public String getExposicionDolor() {
        return exposicionDolor;
    }

    public void setExposicionDolor(String exposicionDolor) {
        this.exposicionDolor = exposicionDolor;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmision != null ? idAdmision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoOtrosdatosAdmision)) {
            return false;
        }
        InfoOtrosdatosAdmision other = (InfoOtrosdatosAdmision) object;
        if ((this.idAdmision == null && other.idAdmision != null) || (this.idAdmision != null && !this.idAdmision.equals(other.idAdmision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoOtrosdatosAdmision[ idAdmision=" + idAdmision + " ]";
    }
    
}
