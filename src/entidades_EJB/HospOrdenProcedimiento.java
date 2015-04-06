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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "hosp_orden_procedimiento")
@NamedQueries({
    @NamedQuery(name = "HospOrdenProcedimiento.findAll", query = "SELECT h FROM HospOrdenProcedimiento h")})
public class HospOrdenProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "id_hcu")
    private int idHcu;
    @Basic(optional = false)
    @Column(name = "id_descripcion_login")
    private int idDescripcionLogin;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;

    public HospOrdenProcedimiento() {
    }

    public HospOrdenProcedimiento(Integer id) {
        this.id = id;
    }

    public HospOrdenProcedimiento(Integer id, int idHcu, int idDescripcionLogin, int tipo, int estado) {
        this.id = id;
        this.idHcu = idHcu;
        this.idDescripcionLogin = idDescripcionLogin;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdHcu() {
        return idHcu;
    }

    public void setIdHcu(int idHcu) {
        this.idHcu = idHcu;
    }

    public int getIdDescripcionLogin() {
        return idDescripcionLogin;
    }

    public void setIdDescripcionLogin(int idDescripcionLogin) {
        this.idDescripcionLogin = idDescripcionLogin;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
        if (!(object instanceof HospOrdenProcedimiento)) {
            return false;
        }
        HospOrdenProcedimiento other = (HospOrdenProcedimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospOrdenProcedimiento[ id=" + id + " ]";
    }
    
}
