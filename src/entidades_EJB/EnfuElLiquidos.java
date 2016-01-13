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
@Table(name = "enfu_el_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuElLiquidos.findAll", query = "SELECT e FROM EnfuElLiquidos e"),
    @NamedQuery(name = "EnfuElLiquidos.findById", query = "SELECT e FROM EnfuElLiquidos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuElLiquidos.findByMateriaFecal", query = "SELECT e FROM EnfuElLiquidos e WHERE e.materiaFecal = :materiaFecal"),
    @NamedQuery(name = "EnfuElLiquidos.findByOrina", query = "SELECT e FROM EnfuElLiquidos e WHERE e.orina = :orina"),
    @NamedQuery(name = "EnfuElLiquidos.findByVomito", query = "SELECT e FROM EnfuElLiquidos e WHERE e.vomito = :vomito"),
    @NamedQuery(name = "EnfuElLiquidos.findByDrenajes", query = "SELECT e FROM EnfuElLiquidos e WHERE e.drenajes = :drenajes")})
public class EnfuElLiquidos implements Serializable {
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "materia_fecal")
    private Double materiaFecal;
    @Column(name = "orina")
    private Double orina;
    @Column(name = "vomito")
    private Double vomito;
    @Column(name = "drenajes")
    private Double drenajes;
    @JoinColumn(name = "id_controles", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuControlLiquidos idControles;

    public EnfuElLiquidos() {
    }

    public EnfuElLiquidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMateriaFecal() {
        return materiaFecal;
    }

    public void setMateriaFecal(Double materiaFecal) {
        this.materiaFecal = materiaFecal;
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
        if (!(object instanceof EnfuElLiquidos)) {
            return false;
        }
        EnfuElLiquidos other = (EnfuElLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entidades.EnfuElLiquidos[ id=" + id + " ]";
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
    
}
