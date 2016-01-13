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
import javax.persistence.Lob;
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
@Table(name = "enfu_med_suministrados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuMedSuministrados.findAll", query = "SELECT e FROM EnfuMedSuministrados e"),
    @NamedQuery(name = "EnfuMedSuministrados.findById", query = "SELECT e FROM EnfuMedSuministrados e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuMedSuministrados.findByEstado", query = "SELECT e FROM EnfuMedSuministrados e WHERE e.estado = :estado")})
public class EnfuMedSuministrados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsNotas idFactsNotas;

    public EnfuMedSuministrados() {
    }

    public EnfuMedSuministrados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof EnfuMedSuministrados)) {
            return false;
        }
        EnfuMedSuministrados other = (EnfuMedSuministrados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuMedSuministrados[ id=" + id + " ]";
    }
    
}
