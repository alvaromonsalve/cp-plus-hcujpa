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
@Table(name = "enfu_liq_eliminados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuLiqEliminados.findAll", query = "SELECT e FROM EnfuLiqEliminados e"),
    @NamedQuery(name = "EnfuLiqEliminados.findById", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuLiqEliminados.findByMatfecal", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.matfecal = :matfecal"),
    @NamedQuery(name = "EnfuLiqEliminados.findByOrina", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.orina = :orina"),
    @NamedQuery(name = "EnfuLiqEliminados.findByVomito", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.vomito = :vomito"),
    @NamedQuery(name = "EnfuLiqEliminados.findByDrenajes", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.drenajes = :drenajes"),
    @NamedQuery(name = "EnfuLiqEliminados.findByTotal", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.total = :total"),
    @NamedQuery(name = "EnfuLiqEliminados.findByUsr", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuLiqEliminados.findByEstado", query = "SELECT e FROM EnfuLiqEliminados e WHERE e.estado = :estado")})
public class EnfuLiqEliminados implements Serializable {
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
    @Column(name = "matfecal")
    private Double matfecal;
    @Column(name = "orina")
    private Double orina;
    @Column(name = "vomito")
    private Double vomito;
    @Column(name = "drenajes")
    private Double drenajes;
    @Column(name = "total")
    private Double total;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_facts_liquidos", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsLiquidos idFactsLiquidos;

    public EnfuLiqEliminados() {
    }

    public EnfuLiqEliminados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMatfecal() {
        return matfecal;
    }

    public void setMatfecal(Double matfecal) {
        this.matfecal = matfecal;
    }

    public Double getOrina() {
        return orina;
    }

    public void setOrina(Double orina) {
        this.orina = orina;
    }

    public Double getVomito() {
        return vomito;
    }

    public void setVomito(Double vomito) {
        this.vomito = vomito;
    }

    public Double getDrenajes() {
        return drenajes;
    }

    public void setDrenajes(Double drenajes) {
        this.drenajes = drenajes;
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
        if (!(object instanceof EnfuLiqEliminados)) {
            return false;
        }
        EnfuLiqEliminados other = (EnfuLiqEliminados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuLiqEliminados[ id=" + id + " ]";
    }
    
}
