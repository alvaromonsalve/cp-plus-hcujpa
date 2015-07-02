
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "enfu_solicitud_sum_desc")
@NamedQueries({
    @NamedQuery(name = "EnfuSolicitudSumDesc.findAll", query = "SELECT e FROM EnfuSolicitudSumDesc e")})
public class EnfuSolicitudSumDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_posologia")
    private int idPosologia;
    @Column(name = "cantidad")
    private String cantidad;
    @Column(name = "tipo")
    private Integer tipo;
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
    @JoinColumn(name = "id_enfu_solicitud_sum", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuSolicitudSum idEnfuSolicitudSum;

    public EnfuSolicitudSumDesc() {
    }

    public EnfuSolicitudSumDesc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPosologia() {
        return idPosologia;
    }

    public void setIdPosologia(int idPosologia) {
        this.idPosologia = idPosologia;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public EnfuSolicitudSum getIdEnfuSolicitudSum() {
        return idEnfuSolicitudSum;
    }

    public void setIdEnfuSolicitudSum(EnfuSolicitudSum idEnfuSolicitudSum) {
        this.idEnfuSolicitudSum = idEnfuSolicitudSum;
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
        if (!(object instanceof EnfuSolicitudSumDesc)) {
            return false;
        }
        EnfuSolicitudSumDesc other = (EnfuSolicitudSumDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.EnfuSolicitudSumDesc[ id=" + id + " ]";
    }

}
