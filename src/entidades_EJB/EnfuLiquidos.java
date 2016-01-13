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
@Table(name = "enfu_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuLiquidos.findAll", query = "SELECT e FROM EnfuLiquidos e"),
    @NamedQuery(name = "EnfuLiquidos.findById", query = "SELECT e FROM EnfuLiquidos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuLiquidos.findByFecha", query = "SELECT e FROM EnfuLiquidos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuLiquidos.findByHora", query = "SELECT e FROM EnfuLiquidos e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuLiquidos.findByCantIndicada", query = "SELECT e FROM EnfuLiquidos e WHERE e.cantIndicada = :cantIndicada"),
    @NamedQuery(name = "EnfuLiquidos.findByCantidadSum", query = "SELECT e FROM EnfuLiquidos e WHERE e.cantidadSum = :cantidadSum"),
    @NamedQuery(name = "EnfuLiquidos.findBySonda", query = "SELECT e FROM EnfuLiquidos e WHERE e.sonda = :sonda"),
    @NamedQuery(name = "EnfuLiquidos.findByOral", query = "SELECT e FROM EnfuLiquidos e WHERE e.oral = :oral"),
    @NamedQuery(name = "EnfuLiquidos.findByParenteral", query = "SELECT e FROM EnfuLiquidos e WHERE e.parenteral = :parenteral"),
    @NamedQuery(name = "EnfuLiquidos.findByTotalA", query = "SELECT e FROM EnfuLiquidos e WHERE e.totalA = :totalA"),
    @NamedQuery(name = "EnfuLiquidos.findByMatfecal", query = "SELECT e FROM EnfuLiquidos e WHERE e.matfecal = :matfecal"),
    @NamedQuery(name = "EnfuLiquidos.findByOrina", query = "SELECT e FROM EnfuLiquidos e WHERE e.orina = :orina"),
    @NamedQuery(name = "EnfuLiquidos.findByVomito", query = "SELECT e FROM EnfuLiquidos e WHERE e.vomito = :vomito"),
    @NamedQuery(name = "EnfuLiquidos.findByDrenajes", query = "SELECT e FROM EnfuLiquidos e WHERE e.drenajes = :drenajes"),
    @NamedQuery(name = "EnfuLiquidos.findByTotalE", query = "SELECT e FROM EnfuLiquidos e WHERE e.totalE = :totalE"),
    @NamedQuery(name = "EnfuLiquidos.findByEstado", query = "SELECT e FROM EnfuLiquidos e WHERE e.estado = :estado"),
    @NamedQuery(name = "EnfuLiquidos.findByUsr", query = "SELECT e FROM EnfuLiquidos e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuLiquidos.findByFechaIngreso", query = "SELECT e FROM EnfuLiquidos e WHERE e.fechaIngreso = :fechaIngreso")})
public class EnfuLiquidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "cant_indicada")
    private String cantIndicada;
    @Column(name = "cantidad_sum")
    private String cantidadSum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sonda")
    private Double sonda;
    @Column(name = "oral")
    private Double oral;
    @Column(name = "parenteral")
    private Double parenteral;
    @Column(name = "total_a")
    private Double totalA;
    @Column(name = "matfecal")
    private Double matfecal;
    @Column(name = "orina")
    private Double orina;
    @Column(name = "vomito")
    private Double vomito;
    @Column(name = "drenajes")
    private Double drenajes;
    @Column(name = "total_e")
    private Double totalE;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @JoinColumn(name = "id_control_o", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsLiquidos idControlO;
        
    public EnfuLiquidos() {
    }

    public EnfuLiquidos(Integer id) {
        this.id = id;
    }

    public EnfuLiquidos(Integer id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
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

    public String getCantIndicada() {
        return cantIndicada;
    }

    public void setCantIndicada(String cantIndicada) {
        this.cantIndicada = cantIndicada;
    }

    public String getCantidadSum() {
        return cantidadSum;
    }

    public void setCantidadSum(String cantidadSum) {
        this.cantidadSum = cantidadSum;
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

    public Double getTotalA() {
        return totalA;
    }

    public void setTotalA(Double totalA) {
        this.totalA = totalA;
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

    public Double getTotalE() {
        return totalE;
    }

    public void setTotalE(Double totalE) {
        this.totalE = totalE;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public EnfuFactsLiquidos getIdControlO() {
        return idControlO;
    }

    public void setIdControlO(EnfuFactsLiquidos idControlO) {
        this.idControlO = idControlO;
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
        if (!(object instanceof EnfuLiquidos)) {
            return false;
        }
        EnfuLiquidos other = (EnfuLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuLiquidos[ id=" + id + " ]";
    }
    
}
