package entidades;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hosp_evo_medidasg")
@NamedQueries({
    @NamedQuery(name = "HospEvoMedidasg.findAll", query = "SELECT h FROM HospEvoMedidasg h")})
public class HospEvoMedidasg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hosp_evolucion",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospEvolucion idHospEvolucion;
    @Column(name = "medidag")
    private String medidag;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;

    public HospEvoMedidasg() {
    }

    public HospEvoMedidasg(Integer id) {
        this.id = id;
    }

    public HospEvoMedidasg(Integer id, HospEvolucion idHospEvolucion, String observacion, int idUsuario) {
        this.id = id;
        this.idHospEvolucion = idHospEvolucion;
        this.observacion = observacion;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HospEvolucion getIdHospEvolucion() {
        return idHospEvolucion;
    }

    public void setIdHospEvolucion(HospEvolucion idHospEvolucion) {
        this.idHospEvolucion = idHospEvolucion;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof HospEvoMedidasg)) {
            return false;
        }
        HospEvoMedidasg other = (HospEvoMedidasg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HospEvoMedidasg[ id=" + id + " ]";
    }

}
