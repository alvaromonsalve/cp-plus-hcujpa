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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "temp_procedimientos")
@NamedQueries({
    @NamedQuery(name = "TempProcedimientos.findAll", query = "SELECT t FROM TempProcedimientos t")})
public class TempProcedimientos implements Serializable {

    @Basic(optional = false)
    @Column(name = "idtem")
    private int idtem;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cup")
    private int cup;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;

    public TempProcedimientos() {
    }

    public TempProcedimientos(Integer id) {
        this.id = id;
    }

    public TempProcedimientos(Integer id, int cup) {
        this.id = id;
        this.cup = cup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCup() {
        return cup;
    }

    public void setCup(int cup) {
        this.cup = cup;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
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
        if (!(object instanceof TempProcedimientos)) {
            return false;
        }
        TempProcedimientos other = (TempProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.TempProcedimientos[ id=" + id + " ]";
    }

    public int getIdtem() {
        return idtem;
    }

    public void setIdtem(int idtem) {
        this.idtem = idtem;
    }

}
