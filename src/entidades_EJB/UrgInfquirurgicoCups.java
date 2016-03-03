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
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "urg_infquirurgico_cups")
@NamedQueries({
    @NamedQuery(name = "UrgInfquirurgicoCups.findAll", query = "SELECT u FROM UrgInfquirurgicoCups u")})
public class UrgInfquirurgicoCups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcups")
    private int idcups;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idquirurgico", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UrgInfquirurgico idquirurgico;

    public UrgInfquirurgicoCups() {
    }

    public UrgInfquirurgicoCups(Integer id) {
        this.id = id;
    }

    public UrgInfquirurgicoCups(Integer id, int idcups, int estado) {
        this.id = id;
        this.idcups = idcups;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcups() {
        return idcups;
    }

    public void setIdcups(int idcups) {
        this.idcups = idcups;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public UrgInfquirurgico getIdquirurgico() {
        return idquirurgico;
    }

    public void setIdquirurgico(UrgInfquirurgico idquirurgico) {
        this.idquirurgico = idquirurgico;
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
        if (!(object instanceof UrgInfquirurgicoCups)) {
            return false;
        }
        UrgInfquirurgicoCups other = (UrgInfquirurgicoCups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UrgInfquirurgicoCups[ id=" + id + " ]";
    }

}
