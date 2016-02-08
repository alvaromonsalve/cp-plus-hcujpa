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
@Table(name = "ce_valoracion_desc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeValoracionDesc.findAll", query = "SELECT c FROM CeValoracionDesc c"),
    @NamedQuery(name = "CeValoracionDesc.findById", query = "SELECT c FROM CeValoracionDesc c WHERE c.id = :id"),
    @NamedQuery(name = "CeValoracionDesc.findByIdConfigCups", query = "SELECT c FROM CeValoracionDesc c WHERE c.idConfigCups = :idConfigCups"),
    @NamedQuery(name = "CeValoracionDesc.findByEstado", query = "SELECT c FROM CeValoracionDesc c WHERE c.estado = :estado")})
public class CeValoracionDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_config_cups")
    private Integer idConfigCups;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_ce_valoracion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeValoracion idCeValoracion;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StaticEspecialidades idEspecialidad;
    
    
    public CeValoracionDesc() {
    }

    public CeValoracionDesc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdConfigCups() {
        return idConfigCups;
    }

    public void setIdConfigCups(Integer idConfigCups) {
        this.idConfigCups = idConfigCups;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public CeValoracion getIdCeValoracion() {
        return idCeValoracion;
    }

    public void setIdCeValoracion(CeValoracion idCeValoracion) {
        this.idCeValoracion = idCeValoracion;
    }
    
    public StaticEspecialidades getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(StaticEspecialidades idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
        if (!(object instanceof CeValoracionDesc)) {
            return false;
        }
        CeValoracionDesc other = (CeValoracionDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeValoracionDesc[ id=" + id + " ]";
    }
    
}
