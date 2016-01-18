/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author JUDMEZ
 */
@Entity
@Table(name = "hosp_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospLiquidos.findAll", query = "SELECT h FROM HospLiquidos h"),
    @NamedQuery(name = "HospLiquidos.findById", query = "SELECT h FROM HospLiquidos h WHERE h.id = :id"),
    @NamedQuery(name = "HospLiquidos.findByFecha", query = "SELECT h FROM HospLiquidos h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HospLiquidos.findByHora", query = "SELECT h FROM HospLiquidos h WHERE h.hora = :hora"),
    @NamedQuery(name = "HospLiquidos.findByCantIndicada", query = "SELECT h FROM HospLiquidos h WHERE h.cantIndicada = :cantIndicada"),
    @NamedQuery(name = "HospLiquidos.findByCantidadSum", query = "SELECT h FROM HospLiquidos h WHERE h.cantidadSum = :cantidadSum"),
    @NamedQuery(name = "HospLiquidos.findBySonda", query = "SELECT h FROM HospLiquidos h WHERE h.sonda = :sonda"),
    @NamedQuery(name = "HospLiquidos.findByOral", query = "SELECT h FROM HospLiquidos h WHERE h.oral = :oral"),
    @NamedQuery(name = "HospLiquidos.findByParenteral", query = "SELECT h FROM HospLiquidos h WHERE h.parenteral = :parenteral"),
    @NamedQuery(name = "HospLiquidos.findByTotalA", query = "SELECT h FROM HospLiquidos h WHERE h.totalA = :totalA"),
    @NamedQuery(name = "HospLiquidos.findByMatfecal", query = "SELECT h FROM HospLiquidos h WHERE h.matfecal = :matfecal"),
    @NamedQuery(name = "HospLiquidos.findByOrina", query = "SELECT h FROM HospLiquidos h WHERE h.orina = :orina"),
    @NamedQuery(name = "HospLiquidos.findByVomito", query = "SELECT h FROM HospLiquidos h WHERE h.vomito = :vomito"),
    @NamedQuery(name = "HospLiquidos.findByDrenajes", query = "SELECT h FROM HospLiquidos h WHERE h.drenajes = :drenajes"),
    @NamedQuery(name = "HospLiquidos.findByTotalE", query = "SELECT h FROM HospLiquidos h WHERE h.totalE = :totalE"),
    @NamedQuery(name = "HospLiquidos.findByEstado", query = "SELECT h FROM HospLiquidos h WHERE h.estado = :estado"),
    @NamedQuery(name = "HospLiquidos.findByUsr", query = "SELECT h FROM HospLiquidos h WHERE h.usr = :usr"),
    @NamedQuery(name = "HospLiquidos.findByFechaIngreso", query = "SELECT h FROM HospLiquidos h WHERE h.fechaIngreso = :fechaIngreso")})
public class HospLiquidos implements Serializable {
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
    private HospFactsLiquidos idControlO;

    public HospLiquidos() {
    }

    public HospLiquidos(Integer id) {
        this.id = id;
    }

    public HospLiquidos(Integer id, Date fechaIngreso) {
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

    public HospFactsLiquidos getIdControlO() {
        return idControlO;
    }

    public void setIdControlO(HospFactsLiquidos idControlO) {
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
        if (!(object instanceof HospLiquidos)) {
            return false;
        }
        HospLiquidos other = (HospLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospLiquidos[ id=" + id + " ]";
    }
    
}
