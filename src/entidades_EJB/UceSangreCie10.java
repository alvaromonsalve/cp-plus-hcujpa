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
import javax.persistence.FetchType;
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

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uce_sangre_cie10")
@NamedQueries({
    @NamedQuery(name = "UceSangreCie10.findAll", query = "SELECT u FROM UceSangreCie10 u")})
public class UceSangreCie10 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcie")
    private int idcie;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idsangre", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UceSangre idsangre;

    public UceSangreCie10() {
    }

    public UceSangreCie10(Integer id) {
        this.id = id;
    }

    public UceSangreCie10(Integer id, int idcie, int estado, Date fecha) {
        this.id = id;
        this.idcie = idcie;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcie() {
        return idcie;
    }

    public void setIdcie(int idcie) {
        this.idcie = idcie;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UceSangre getIdsangre() {
        return idsangre;
    }

    public void setIdsangre(UceSangre idsangre) {
        this.idsangre = idsangre;
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
        if (!(object instanceof UceSangreCie10)) {
            return false;
        }
        UceSangreCie10 other = (UceSangreCie10) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UceSangreCie10[ id=" + id + " ]";
    }

}
