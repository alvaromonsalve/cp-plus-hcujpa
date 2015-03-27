
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "info_camas")
@NamedQueries({
    @NamedQuery(name = "InfoCamas.findAll", query = "SELECT i FROM InfoCamas i")})
public class InfoCamas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDato;
    @Column(name = "estado")
    private int estado;
    @Column(name = "id_usuario")
    private int idUsuario;
    @JoinColumn(name = "id_config_camas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigCamas idConfigCamas;
    @JoinColumn(name = "id_info_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idInfoHistoriac;

    public InfoCamas() {
    }

    public InfoCamas(Integer id) {
        this.id = id;
    }

    public InfoCamas(Integer id, Date fechaDato) {
        this.id = id;
        this.fechaDato = fechaDato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaDato() {
        return fechaDato;
    }

    public void setFechaDato(Date fechaDato) {
        this.fechaDato = fechaDato;
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

    public ConfigCamas getIdConfigCamas() {
        return idConfigCamas;
    }

    public void setIdConfigCamas(ConfigCamas idConfigCamas) {
        this.idConfigCamas = idConfigCamas;
    }

    public InfoHistoriac getIdInfoHistoriac() {
        return idInfoHistoriac;
    }

    public void setIdInfoHistoriac(InfoHistoriac idInfoHistoriac) {
        this.idInfoHistoriac = idInfoHistoriac;
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
        if (!(object instanceof InfoCamas)) {
            return false;
        }
        InfoCamas other = (InfoCamas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoCamas[ id=" + id + " ]";
    }
    
}
