/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
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

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "urg_ing_ctc_cie10")
@NamedQueries({
    @NamedQuery(name = "UrgIngCtcCie10.findAll", query = "SELECT u FROM UrgIngCtcCie10 u")})
public class UrgIngCtcCie10 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcie10")
    private int idcie10;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idctc", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UrgIngCtc idctc;

    public UrgIngCtcCie10() {
    }

    public UrgIngCtcCie10(Integer id) {
        this.id = id;
    }

    public UrgIngCtcCie10(Integer id, int idcie10, int estado) {
        this.id = id;
        this.idcie10 = idcie10;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcie10() {
        return idcie10;
    }

    public void setIdcie10(int idcie10) {
        this.idcie10 = idcie10;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public UrgIngCtc getIdctc() {
        return idctc;
    }

    public void setIdctc(UrgIngCtc idctc) {
        this.idctc = idctc;
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
        if (!(object instanceof UrgIngCtcCie10)) {
            return false;
        }
        UrgIngCtcCie10 other = (UrgIngCtcCie10) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UrgIngCtcCie10[ id=" + id + " ]";
    }
    
}
