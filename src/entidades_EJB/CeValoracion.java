/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_valoracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeValoracion.findAll", query = "SELECT c FROM CeValoracion c"),
    @NamedQuery(name = "CeValoracion.findById", query = "SELECT c FROM CeValoracion c WHERE c.id = :id"),
    @NamedQuery(name = "CeValoracion.findByUsr", query = "SELECT c FROM CeValoracion c WHERE c.usr = :usr"),
    @NamedQuery(name = "CeValoracion.findByFechaIngresoDatos", query = "SELECT c FROM CeValoracion c WHERE c.fechaIngresoDatos = :fechaIngresoDatos"),
    @NamedQuery(name = "CeValoracion.findByTipo", query = "SELECT c FROM CeValoracion c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CeValoracion.findByEstado", query = "SELECT c FROM CeValoracion c WHERE c.estado = :estado")})
public class CeValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @Column(name = "tipo")
    private Character tipo;
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCeValoracion")
    private List<CeValoracionDesc> ceValoracionDescList;
    @JoinColumn(name = "id_historiace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoriace;
    
    public CeValoracion() {
    }

    public CeValoracion(Integer id) {
        this.id = id;
    }

    public CeValoracion(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<CeValoracionDesc> getCeValoracionDescList() {
        return ceValoracionDescList;
    }

    public void setCeValoracionDescList(List<CeValoracionDesc> ceValoracionDescList) {
        this.ceValoracionDescList = ceValoracionDescList;
    }

      public CeHistoriac getIdHistoriace() {
        return idHistoriace;
    }

    public void setIdHistoriace(CeHistoriac idHistoriace) {
        this.idHistoriace = idHistoriace;
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
        if (!(object instanceof CeValoracion)) {
            return false;
        }
        CeValoracion other = (CeValoracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeValoracion[ id=" + id + " ]";
    }
    
}
