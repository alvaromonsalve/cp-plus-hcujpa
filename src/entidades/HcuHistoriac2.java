/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hcu_historiac_2")
@NamedQueries({
    @NamedQuery(name = "HcuHistoriac2.findAll", query = "SELECT h FROM HcuHistoriac2 h")})
public class HcuHistoriac2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_info_historiac")
    @OneToOne
    private int idInfoHistoriac;
    @Basic(optional = false)
    @Column(name = "tiempo_consulta")
    private int tiempoConsulta;
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fIngreso;

    public HcuHistoriac2() {
    }

    public HcuHistoriac2(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdInfoHistoriac() {
        return idInfoHistoriac;
    }

    public void setIdInfoHistoriac(int idInfoHistoriac) {
        this.idInfoHistoriac = idInfoHistoriac;
    }

    public int getTiempoConsulta() {
        return tiempoConsulta;
    }

    public void setTiempoConsulta(int tiempoConsulta) {
        this.tiempoConsulta = tiempoConsulta;
    }

    public Date getFIngreso() {
        return fIngreso;
    }

    public void setFIngreso(Date fIngreso) {
        this.fIngreso = fIngreso;
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
        if (!(object instanceof HcuHistoriac2)) {
            return false;
        }
        HcuHistoriac2 other = (HcuHistoriac2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuHistoriac2[ id=" + id + " ]";
    }

}
