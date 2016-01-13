/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_signos_vitales_mat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuSignosVitalesMat.findAll", query = "SELECT e FROM EnfuSignosVitalesMat e"),
    @NamedQuery(name = "EnfuSignosVitalesMat.findById", query = "SELECT e FROM EnfuSignosVitalesMat e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuSignosVitalesMat.findByFcf", query = "SELECT e FROM EnfuSignosVitalesMat e WHERE e.fcf = :fcf"),
    @NamedQuery(name = "EnfuSignosVitalesMat.findBySensorio", query = "SELECT e FROM EnfuSignosVitalesMat e WHERE e.sensorio = :sensorio"),
    @NamedQuery(name = "EnfuSignosVitalesMat.findByEstado", query = "SELECT e FROM EnfuSignosVitalesMat e WHERE e.estado = :estado")})
public class EnfuSignosVitalesMat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fcf")
    private int fcf;
    @Column(name = "sensorio")
    private Character sensorio;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsNotas idFactsNotas;

    public EnfuSignosVitalesMat() {
    }

    public EnfuSignosVitalesMat(Integer id) {
        this.id = id;
    }

    public EnfuSignosVitalesMat(Integer id, int fcf, char estado) {
        this.id = id;
        this.fcf = fcf;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFcf() {
        return fcf;
    }

    public void setFcf(int fcf) {
        this.fcf = fcf;
    }

    public Character getSensorio() {
        return sensorio;
    }

    public void setSensorio(Character sensorio) {
        this.sensorio = sensorio;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
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
        if (!(object instanceof EnfuSignosVitalesMat)) {
            return false;
        }
        EnfuSignosVitalesMat other = (EnfuSignosVitalesMat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuSignosVitalesMat[ id=" + id + " ]";
    }
    
}
