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
import javax.persistence.Lob;
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
@Table(name = "ce_recomendaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeRecomendaciones.findAll", query = "SELECT c FROM CeRecomendaciones c"),
    @NamedQuery(name = "CeRecomendaciones.findById", query = "SELECT c FROM CeRecomendaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CeRecomendaciones.findByEstado", query = "SELECT c FROM CeRecomendaciones c WHERE c.estado = :estado"),
    @NamedQuery(name = "CeRecomendaciones.findByUsr", query = "SELECT c FROM CeRecomendaciones c WHERE c.usr = :usr"),
    @NamedQuery(name = "CeRecomendaciones.findByFechaIngresoDatos", query = "SELECT c FROM CeRecomendaciones c WHERE c.fechaIngresoDatos = :fechaIngresoDatos")})
public class CeRecomendaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "recomendacion")
    private String recomendacion;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    
    @JoinColumn(name = "id_historiace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoriace;

    public CeRecomendaciones() {
    }

    public CeRecomendaciones(Integer id) {
        this.id = id;
    }

    public CeRecomendaciones(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
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
        if (!(object instanceof CeRecomendaciones)) {
            return false;
        }
        CeRecomendaciones other = (CeRecomendaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeRecomendaciones[ id=" + id + " ]";
    }
    
}
