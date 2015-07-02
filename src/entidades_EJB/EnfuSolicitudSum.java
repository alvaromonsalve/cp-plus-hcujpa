
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "enfu_solicitud_sum")
@NamedQueries({
    @NamedQuery(name = "EnfuSolicitudSum.findAll", query = "SELECT e FROM EnfuSolicitudSum e")})
public class EnfuSolicitudSum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHcu;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Configdecripcionlogin idUsuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnfuSolicitudSum")
    private List<EnfuSolicitudSumDesc> enfuSolicitudSumDescList;

    public EnfuSolicitudSum() {
    }

    public EnfuSolicitudSum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InfoHistoriac getIdHcu() {
        return idHcu;
    }

    public void setIdHcu(InfoHistoriac idHcu) {
        this.idHcu = idHcu;
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

    public Configdecripcionlogin getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Configdecripcionlogin idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFDigita() {
        return fDigita;
    }

    public void setFDigita(Date fDigita) {
        this.fDigita = fDigita;
    }

    public List<EnfuSolicitudSumDesc> getEnfuSolicitudSumDescList() {
        return enfuSolicitudSumDescList;
    }

    public void setEnfuSolicitudSumDescList(List<EnfuSolicitudSumDesc> enfuSolicitudSumDescList) {
        this.enfuSolicitudSumDescList = enfuSolicitudSumDescList;
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
        if (!(object instanceof EnfuSolicitudSum)) {
            return false;
        }
        EnfuSolicitudSum other = (EnfuSolicitudSum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.EnfuSolicitudSum[ id=" + id + " ]";
    }

}
