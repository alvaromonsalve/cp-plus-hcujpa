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
@Table(name = "hosp_glasgow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospGlasgow.findAll", query = "SELECT h FROM HospGlasgow h"),
    @NamedQuery(name = "HospGlasgow.findById", query = "SELECT h FROM HospGlasgow h WHERE h.id = :id"),
    @NamedQuery(name = "HospGlasgow.findByAo", query = "SELECT h FROM HospGlasgow h WHERE h.ao = :ao"),
    @NamedQuery(name = "HospGlasgow.findByRv", query = "SELECT h FROM HospGlasgow h WHERE h.rv = :rv"),
    @NamedQuery(name = "HospGlasgow.findByRm", query = "SELECT h FROM HospGlasgow h WHERE h.rm = :rm"),
    @NamedQuery(name = "HospGlasgow.findByEstado", query = "SELECT h FROM HospGlasgow h WHERE h.estado = :estado"),
    @NamedQuery(name = "HospGlasgow.findByFechaIngresoDatos", query = "SELECT h FROM HospGlasgow h WHERE h.fechaIngresoDatos = :fechaIngresoDatos")})
public class HospGlasgow implements Serializable {
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
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fechaIngresoDatos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospFactsNotas idFactsNotas;

    public HospGlasgow() {
    }

    public HospGlasgow(Integer id) {
        this.id = id;
    }

    public HospGlasgow(Integer id, int ao, int rv, int rm, Character estado, Date fechaIngresoDatos) {
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public HospFactsNotas getIdFactsNotas() {
        return idFactsNotas;
    }

    public void setIdFactsNotas(HospFactsNotas idFactsNotas) {
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
        if (!(object instanceof HospGlasgow)) {
            return false;
        }
        HospGlasgow other = (HospGlasgow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospGlasgow[ id=" + id + " ]";
    }
    
}
