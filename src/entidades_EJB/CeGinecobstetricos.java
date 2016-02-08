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
import javax.persistence.Lob;
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
@Table(name = "ce_ginecobstetricos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeGinecobstetricos.findAll", query = "SELECT c FROM CeGinecobstetricos c"),
    @NamedQuery(name = "CeGinecobstetricos.findById", query = "SELECT c FROM CeGinecobstetricos c WHERE c.id = :id"),
    @NamedQuery(name = "CeGinecobstetricos.findByFum", query = "SELECT c FROM CeGinecobstetricos c WHERE c.fum = :fum"),
    @NamedQuery(name = "CeGinecobstetricos.findByCesareas", query = "SELECT c FROM CeGinecobstetricos c WHERE c.cesareas = :cesareas"),
    @NamedQuery(name = "CeGinecobstetricos.findByPartos", query = "SELECT c FROM CeGinecobstetricos c WHERE c.partos = :partos"),
    @NamedQuery(name = "CeGinecobstetricos.findByAbortos", query = "SELECT c FROM CeGinecobstetricos c WHERE c.abortos = :abortos"),
    @NamedQuery(name = "CeGinecobstetricos.findByGestaciones", query = "SELECT c FROM CeGinecobstetricos c WHERE c.gestaciones = :gestaciones"),
    @NamedQuery(name = "CeGinecobstetricos.findByEstado", query = "SELECT c FROM CeGinecobstetricos c WHERE c.estado = :estado")})
public class CeGinecobstetricos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fum")
    @Temporal(TemporalType.DATE)
    private Date fum;
    @Column(name = "cesareas")
    private Integer cesareas;
    @Column(name = "partos")
    private Integer partos;
    @Column(name = "abortos")
    private Integer abortos;
    @Column(name = "gestaciones")
    private Integer gestaciones;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @JoinColumn(name = "idhistoriace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idhistoriace;

    public CeGinecobstetricos() {
    }

    public CeGinecobstetricos(Integer id) {
        this.id = id;
    }

    public CeGinecobstetricos(Integer id, char estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFum() {
        return fum;
    }

    public void setFum(Date fum) {
        this.fum = fum;
    }

    public Integer getCesareas() {
        return cesareas;
    }

    public void setCesareas(Integer cesareas) {
        this.cesareas = cesareas;
    }

    public Integer getPartos() {
        return partos;
    }

    public void setPartos(Integer partos) {
        this.partos = partos;
    }

    public Integer getAbortos() {
        return abortos;
    }

    public void setAbortos(Integer abortos) {
        this.abortos = abortos;
    }

    public Integer getGestaciones() {
        return gestaciones;
    }

    public void setGestaciones(Integer gestaciones) {
        this.gestaciones = gestaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
        public CeHistoriac getIdhistoriace() {
        return idhistoriace;
    }

    public void setIdhistoriace(CeHistoriac idhistoriace) {
        this.idhistoriace = idhistoriace;
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
        if (!(object instanceof CeGinecobstetricos)) {
            return false;
        }
        CeGinecobstetricos other = (CeGinecobstetricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeGinecobstetricos[ id=" + id + " ]";
    }
    
}
