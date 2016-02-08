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
@Table(name = "ce_ctc_procedimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeCtcProcedimientos.findAll", query = "SELECT c FROM CeCtcProcedimientos c"),
    @NamedQuery(name = "CeCtcProcedimientos.findById", query = "SELECT c FROM CeCtcProcedimientos c WHERE c.id = :id"),
    @NamedQuery(name = "CeCtcProcedimientos.findByIdcups", query = "SELECT c FROM CeCtcProcedimientos c WHERE c.idcups = :idcups"),
    @NamedQuery(name = "CeCtcProcedimientos.findByTipo", query = "SELECT c FROM CeCtcProcedimientos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CeCtcProcedimientos.findByEstado", query = "SELECT c FROM CeCtcProcedimientos c WHERE c.estado = :estado")})
public class CeCtcProcedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcups")
    private int idcups;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idctc", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeCtc idctc;

    public CeCtcProcedimientos() {
    }

    public CeCtcProcedimientos(Integer id) {
        this.id = id;
    }

    public CeCtcProcedimientos(Integer id, int idcups, int tipo, int estado) {
        this.id = id;
        this.idcups = idcups;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcups() {
        return idcups;
    }

    public void setIdcups(int idcups) {
        this.idcups = idcups;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public CeCtc getIdctc() {
        return idctc;
    }

    public void setIdctc(CeCtc idctc) {
        this.idctc = idctc;
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
        if (!(object instanceof CeCtcProcedimientos)) {
            return false;
        }
        CeCtcProcedimientos other = (CeCtcProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeCtcProcedimientos[ id=" + id + " ]";
    }
    
}
