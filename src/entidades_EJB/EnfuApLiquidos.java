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
@Table(name = "enfu_ap_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuApLiquidos.findAll", query = "SELECT e FROM EnfuApLiquidos e"),
    @NamedQuery(name = "EnfuApLiquidos.findById", query = "SELECT e FROM EnfuApLiquidos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuApLiquidos.findByFecha", query = "SELECT e FROM EnfuApLiquidos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuApLiquidos.findByHora", query = "SELECT e FROM EnfuApLiquidos e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuApLiquidos.findByCantAdm", query = "SELECT e FROM EnfuApLiquidos e WHERE e.cantAdm = :cantAdm"),
    @NamedQuery(name = "EnfuApLiquidos.findBySonda", query = "SELECT e FROM EnfuApLiquidos e WHERE e.sonda = :sonda"),
    @NamedQuery(name = "EnfuApLiquidos.findByOral", query = "SELECT e FROM EnfuApLiquidos e WHERE e.oral = :oral"),
    @NamedQuery(name = "EnfuApLiquidos.findByParenteral", query = "SELECT e FROM EnfuApLiquidos e WHERE e.parenteral = :parenteral"),
    @NamedQuery(name = "EnfuApLiquidos.findByEstado", query = "SELECT e FROM EnfuApLiquidos e WHERE e.estado = :estado")})
public class EnfuApLiquidos implements Serializable {
    @Column(name = "usr")
    private Integer usr;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "cant_adm")
    private Double cantAdm;
    @Column(name = "sonda")
    private Double sonda;
    @Column(name = "oral")
    private Double oral;
    @Column(name = "parenteral")
    private Double parenteral;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_controles", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuControlLiquidos idControles;

    public EnfuApLiquidos() {
    }

    public EnfuApLiquidos(Integer id) {
        this.id = id;
    }

    public EnfuApLiquidos(Integer id, Date hora) {
        this.id = id;
        this.hora = hora;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Double getCantAdm() {
        return cantAdm;
    }

    public void setCantAdm(Double cantAdm) {
        this.cantAdm = cantAdm;
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public EnfuControlLiquidos getIdControles() {
        return idControles;
    }

    public void setIdControles(EnfuControlLiquidos idControles) {
        this.idControles = idControles;
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
        if (!(object instanceof EnfuApLiquidos)) {
            return false;
        }
        EnfuApLiquidos other = (EnfuApLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuApLiquidos[ id=" + id + " ]";
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }
    
}
