/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uci_finalizaciones_airemedico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciFinalizacionesAiremedico.findAll", query = "SELECT u FROM UciFinalizacionesAiremedico u"),
    @NamedQuery(name = "UciFinalizacionesAiremedico.findById", query = "SELECT u FROM UciFinalizacionesAiremedico u WHERE u.id = :id"),
    @NamedQuery(name = "UciFinalizacionesAiremedico.findByFecha", query = "SELECT u FROM UciFinalizacionesAiremedico u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UciFinalizacionesAiremedico.findByHoraFin", query = "SELECT u FROM UciFinalizacionesAiremedico u WHERE u.horaFin = :horaFin"),
    @NamedQuery(name = "UciFinalizacionesAiremedico.findByUsr", query = "SELECT u FROM UciFinalizacionesAiremedico u WHERE u.usr = :usr"),
    @NamedQuery(name = "UciFinalizacionesAiremedico.findByEstado", query = "SELECT u FROM UciFinalizacionesAiremedico u WHERE u.estado = :estado")})
public class UciFinalizacionesAiremedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_fin")
    private String horaFin;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_aplicacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciAplicacionesAiremedico idAplicacion;

    public UciFinalizacionesAiremedico() {
    }

    public UciFinalizacionesAiremedico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public UciAplicacionesAiremedico getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(UciAplicacionesAiremedico idAplicacion) {
        this.idAplicacion = idAplicacion;
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
        if (!(object instanceof UciFinalizacionesAiremedico)) {
            return false;
        }
        UciFinalizacionesAiremedico other = (UciFinalizacionesAiremedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesUCI.UciFinalizacionesAiremedico[ id=" + id + " ]";
    }
    
}
