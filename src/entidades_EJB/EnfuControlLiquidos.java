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
@Table(name = "enfu_control_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuControlLiquidos.findAll", query = "SELECT e FROM EnfuControlLiquidos e"),
    @NamedQuery(name = "EnfuControlLiquidos.findById", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuControlLiquidos.findByFecha", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuControlLiquidos.findByHora", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuControlLiquidos.findByLiquido", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.liquido = :liquido"),
    @NamedQuery(name = "EnfuControlLiquidos.findByCantIndicada", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.cantIndicada = :cantIndicada"),
    @NamedQuery(name = "EnfuControlLiquidos.findByDuracion", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.duracion = :duracion"),
    @NamedQuery(name = "EnfuControlLiquidos.findByUsr", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuControlLiquidos.findByEstado", query = "SELECT e FROM EnfuControlLiquidos e WHERE e.estado = :estado")})
public class EnfuControlLiquidos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idControles")
    private List<EnfuElLiquidos> enfuElLiquidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idControles")
    private List<EnfuApLiquidos> enfuApLiquidosList;
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
    @Column(name = "liquido")
    private String liquido;
    @Column(name = "cant_indicada")
    private Double cantIndicada;
    @Column(name = "duracion")
    private String duracion;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHistoria;

    public EnfuControlLiquidos() {
    }

    public EnfuControlLiquidos(Integer id) {
        this.id = id;
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

    public String getLiquido() {
        return liquido;
    }

    public void setLiquido(String liquido) {
        this.liquido = liquido;
    }

    public Double getCantIndicada() {
        return cantIndicada;
    }

    public void setCantIndicada(Double cantIndicada) {
        this.cantIndicada = cantIndicada;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
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

    public InfoHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(InfoHistoriac idHistoria) {
        this.idHistoria = idHistoria;
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
        if (!(object instanceof EnfuControlLiquidos)) {
            return false;
        }
        EnfuControlLiquidos other = (EnfuControlLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuControlLiquidos[ id=" + id + " ]";
    }

    @XmlTransient
    public List<EnfuApLiquidos> getEnfuApLiquidosList() {
        return enfuApLiquidosList;
    }

    public void setEnfuApLiquidosList(List<EnfuApLiquidos> enfuApLiquidosList) {
        this.enfuApLiquidosList = enfuApLiquidosList;
    }

    @XmlTransient
    public List<EnfuElLiquidos> getEnfuElLiquidosList() {
        return enfuElLiquidosList;
    }

    public void setEnfuElLiquidosList(List<EnfuElLiquidos> enfuElLiquidosList) {
        this.enfuElLiquidosList = enfuElLiquidosList;
    }
    
}
