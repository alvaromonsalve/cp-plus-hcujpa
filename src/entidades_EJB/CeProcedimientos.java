/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_procedimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeProcedimientos.findAll", query = "SELECT c FROM CeProcedimientos c"),
    @NamedQuery(name = "CeProcedimientos.findById", query = "SELECT c FROM CeProcedimientos c WHERE c.id = :id"),
    @NamedQuery(name = "CeProcedimientos.findByEstado", query = "SELECT c FROM CeProcedimientos c WHERE c.estado = :estado"),
    @NamedQuery(name = "CeProcedimientos.findByUsr", query = "SELECT c FROM CeProcedimientos c WHERE c.usr = :usr")})
public class CeProcedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    
    @JoinColumn(name = "id_historiace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoriace;
    @JoinColumn(name = "id_cups", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigCups idCups;

    public CeProcedimientos() {
    }

    public CeProcedimientos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
    
     public CeHistoriac getIdHistoriace() {
        return idHistoriace;
    }

    public void setIdHistoriace(CeHistoriac idHistoriace) {
        this.idHistoriace = idHistoriace;
    }

    public ConfigCups getIdCups() {
        return idCups;
    }

    public void setIdCups(ConfigCups idCups) {
        this.idCups = idCups;
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
        if (!(object instanceof CeProcedimientos)) {
            return false;
        }
        CeProcedimientos other = (CeProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeProcedimientos[ id=" + id + " ]";
    }
    
}
