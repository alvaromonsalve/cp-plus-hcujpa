
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
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hosp_medidasg")
@NamedQueries({
    @NamedQuery(name = "HospMedidasg.findAll", query = "SELECT i FROM HospMedidasg i")})
public class HospMedidasg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_historiac")
    private int idHistoriac;
    @Column(name = "medidag")
    private String medidag;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;

    public HospMedidasg() {
    }

    public HospMedidasg(Integer id) {
        this.id = id;
    }

    public HospMedidasg(Integer id, int idHistoriac, String observacion, int idUsuario) {
        this.id = id;
        this.idHistoriac = idHistoriac;
        this.observacion = observacion;
        this.idUsuario = idUsuario;
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

    public String getMedidag() {
        return medidag;
    }

    public void setMedidag(String medidag) {
        this.medidag = medidag;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof HospMedidasg)) {
            return false;
        }
        HospMedidasg other = (HospMedidasg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HospMedidasg[ id=" + id + " ]";
    }

}
