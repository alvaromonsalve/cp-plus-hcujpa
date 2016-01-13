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
@Table(name = "enfu_signos_vitales_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuSignosVitalesDet.findAll", query = "SELECT e FROM EnfuSignosVitalesDet e"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findById", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByFc", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.fc = :fc"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByTas", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.tas = :tas"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByTad", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.tad = :tad"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByFr", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.fr = :fr"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByT", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.t = :t"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByLlc", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.llc = :llc"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByTam", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.tam = :tam"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByFiO2", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.fiO2 = :fiO2"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findBySatO2", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.satO2 = :satO2"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByFechaIngresoDatos", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.fechaIngresoDatos = :fechaIngresoDatos"),
    @NamedQuery(name = "EnfuSignosVitalesDet.findByEstado", query = "SELECT e FROM EnfuSignosVitalesDet e WHERE e.estado = :estado")})
public class EnfuSignosVitalesDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fc")
    private Integer fc;
    @Column(name = "tas")
    private Integer tas;
    @Column(name = "tad")
    private Integer tad;
    @Column(name = "fr")
    private Integer fr;
    @Column(name = "t")
    private Float t;
    @Column(name = "llc")
    private Integer llc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tam")
    private Float tam;
    @Column(name = "fiO2")
    private Integer fiO2;
    @Column(name = "satO2")
    private Integer satO2;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsNotas idFactsNotas;

    public EnfuSignosVitalesDet() {
    }

    public EnfuSignosVitalesDet(Integer id) {
        this.id = id;
    }

    public EnfuSignosVitalesDet(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFc() {
        return fc;
    }

    public void setFc(Integer fc) {
        this.fc = fc;
    }

    public Integer getTas() {
        return tas;
    }

    public void setTas(Integer tas) {
        this.tas = tas;
    }

    public Integer getTad() {
        return tad;
    }

    public void setTad(Integer tad) {
        this.tad = tad;
    }

    public Integer getFr() {
        return fr;
    }

    public void setFr(Integer fr) {
        this.fr = fr;
    }

    public Float getT() {
        return t;
    }

    public void setT(Float t) {
        this.t = t;
    }

    public Integer getLlc() {
        return llc;
    }

    public void setLlc(Integer llc) {
        this.llc = llc;
    }

    public Float getTam() {
        return tam;
    }

    public void setTam(Float tam) {
        this.tam = tam;
    }

    public Integer getFiO2() {
        return fiO2;
    }

    public void setFiO2(Integer fiO2) {
        this.fiO2 = fiO2;
    }

    public Integer getSatO2() {
        return satO2;
    }

    public void setSatO2(Integer satO2) {
        this.satO2 = satO2;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public EnfuFactsNotas getIdFactsNotas() {
        return idFactsNotas;
    }

    public void setIdFactsNotas(EnfuFactsNotas idFactsNotas) {
        this.idFactsNotas = idFactsNotas;
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
        if (!(object instanceof EnfuSignosVitalesDet)) {
            return false;
        }
        EnfuSignosVitalesDet other = (EnfuSignosVitalesDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuSignosVitalesDet[ id=" + id + " ]";
    }
    
}
