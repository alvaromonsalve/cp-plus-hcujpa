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
@Table(name = "enfu_glasgow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuGlasgow.findAll", query = "SELECT e FROM EnfuGlasgow e"),
    @NamedQuery(name = "EnfuGlasgow.findById", query = "SELECT e FROM EnfuGlasgow e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuGlasgow.findByAo", query = "SELECT e FROM EnfuGlasgow e WHERE e.ao = :ao"),
    @NamedQuery(name = "EnfuGlasgow.findByRv", query = "SELECT e FROM EnfuGlasgow e WHERE e.rv = :rv"),
    @NamedQuery(name = "EnfuGlasgow.findByRm", query = "SELECT e FROM EnfuGlasgow e WHERE e.rm = :rm"),
    @NamedQuery(name = "EnfuGlasgow.findByEstado", query = "SELECT e FROM EnfuGlasgow e WHERE e.estado = :estado"),
    @NamedQuery(name = "EnfuGlasgow.findByFechaIngresoDatos", query = "SELECT e FROM EnfuGlasgow e WHERE e.fechaIngresoDatos = :fechaIngresoDatos")})
public class EnfuGlasgow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "AO")
    private int ao;
    @Basic(optional = false)
    @Column(name = "RV")
    private int rv;
    @Basic(optional = false)
    @Column(name = "RM")
    private int rm;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @Basic(optional = false)
    @Column(name = "fechaIngresoDatos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsNotas idFactsNotas;

    public EnfuGlasgow() {
    }

    public EnfuGlasgow(Integer id) {
        this.id = id;
    }

    public EnfuGlasgow(Integer id, int ao, int rv, int rm, char estado, Date fechaIngresoDatos) {
        this.id = id;
        this.ao = ao;
        this.rv = rv;
        this.rm = rm;
        this.estado = estado;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAo() {
        return ao;
    }

    public void setAo(int ao) {
        this.ao = ao;
    }

    public int getRv() {
        return rv;
    }

    public void setRv(int rv) {
        this.rv = rv;
    }

    public int getRm() {
        return rm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
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
        if (!(object instanceof EnfuGlasgow)) {
            return false;
        }
        EnfuGlasgow other = (EnfuGlasgow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuGlasgow[ id=" + id + " ]";
    }
    
}
