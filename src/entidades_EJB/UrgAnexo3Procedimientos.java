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
 * @author Juan Camilo
 */
@Entity
@Table(name = "urg_anexo3_procedimientos")
@NamedQueries({
    @NamedQuery(name = "UrgAnexo3Procedimientos.findAll", query = "SELECT u FROM UrgAnexo3Procedimientos u")})
public class UrgAnexo3Procedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcups")
    private int idcups;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idanexo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UrgAnexo3 idanexo;

    public UrgAnexo3Procedimientos() {
    }

    public UrgAnexo3Procedimientos(Integer id) {
        this.id = id;
    }

    public UrgAnexo3Procedimientos(Integer id, int idcups, int estado, Date fecha) {
        this.id = id;
        this.idcups = idcups;
        this.estado = estado;
        this.fecha = fecha;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public UrgAnexo3 getIdanexo() {
        return idanexo;
    }

    public void setIdanexo(UrgAnexo3 idanexo) {
        this.idanexo = idanexo;
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
        if (!(object instanceof UrgAnexo3Procedimientos)) {
            return false;
        }
        UrgAnexo3Procedimientos other = (UrgAnexo3Procedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UrgAnexo3Procedimientos[ id=" + id + " ]";
    }
    
}
