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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "arch_lugares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchLugares.findAll", query = "SELECT a FROM ArchLugares a"),
    @NamedQuery(name = "ArchLugares.findByRgId", query = "SELECT a FROM ArchLugares a WHERE a.rgId = :rgId"),
    @NamedQuery(name = "ArchLugares.findByParentId", query = "SELECT a FROM ArchLugares a WHERE a.parentId = :parentId"),
    @NamedQuery(name = "ArchLugares.findByRgName", query = "SELECT a FROM ArchLugares a WHERE a.rgName = :rgName"),
    @NamedQuery(name = "ArchLugares.findByEstado", query = "SELECT a FROM ArchLugares a WHERE a.estado = :estado")})
public class ArchLugares implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rgId")
    private Integer rgId;
    @Basic(optional = false)
    @Column(name = "parentId")
    private int parentId;
    @Basic(optional = false)
    @Column(name = "rgName")
    private String rgName;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;

    public ArchLugares() {
    }

    public ArchLugares(Integer rgId) {
        this.rgId = rgId;
    }

    public ArchLugares(Integer rgId, int parentId, String rgName, char estado) {
        this.rgId = rgId;
        this.parentId = parentId;
        this.rgName = rgName;
        this.estado = estado;
    }

    public Integer getRgId() {
        return rgId;
    }

    public void setRgId(Integer rgId) {
        this.rgId = rgId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRgName() {
        return rgName;
    }

    public void setRgName(String rgName) {
        this.rgName = rgName;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rgId != null ? rgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchLugares)) {
            return false;
        }
        ArchLugares other = (ArchLugares) object;
        if ((this.rgId == null && other.rgId != null) || (this.rgId != null && !this.rgId.equals(other.rgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_AR.ArchLugares[ rgId=" + rgId + " ]";
    }
    
}
