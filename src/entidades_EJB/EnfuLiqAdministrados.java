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
@Table(name = "enfu_liq_administrados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuLiqAdministrados.findAll", query = "SELECT e FROM EnfuLiqAdministrados e"),
    @NamedQuery(name = "EnfuLiqAdministrados.findById", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuLiqAdministrados.findBySonda", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.sonda = :sonda"),
    @NamedQuery(name = "EnfuLiqAdministrados.findByOral", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.oral = :oral"),
    @NamedQuery(name = "EnfuLiqAdministrados.findByParenteral", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.parenteral = :parenteral"),
    @NamedQuery(name = "EnfuLiqAdministrados.findByTotal", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.total = :total"),
    @NamedQuery(name = "EnfuLiqAdministrados.findByUsr", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuLiqAdministrados.findByEstado", query = "SELECT e FROM EnfuLiqAdministrados e WHERE e.estado = :estado")})
public class EnfuLiqAdministrados implements Serializable {
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sonda")
    private Double sonda;
    @Column(name = "oral")
    private Double oral;
    @Column(name = "parenteral")
    private Double parenteral;
    @Column(name = "total")
    private Double total;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_facts_liquidos", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsLiquidos idFactsLiquidos;

    public EnfuLiqAdministrados() {
    }

    public EnfuLiqAdministrados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSonda() {
        return sonda;
    }

    public void setSonda(Double sonda) {
        this.sonda = sonda;
    }

    public Double getOral() {
        return oral;
    }

    public void setOral(Double oral) {
        this.oral = oral;
    }

    public Double getParenteral() {
        return parenteral;
    }

    public void setParenteral(Double parenteral) {
        this.parenteral = parenteral;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public EnfuFactsLiquidos getIdFactsLiquidos() {
        return idFactsLiquidos;
    }

    public void setIdFactsLiquidos(EnfuFactsLiquidos idFactsLiquidos) {
        this.idFactsLiquidos = idFactsLiquidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
        if (!(object instanceof EnfuLiqAdministrados)) {
            return false;
        }
        EnfuLiqAdministrados other = (EnfuLiqAdministrados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuLiqAdministrados[ id=" + id + " ]";
    }
    
}
