/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "urg_sangre")
@NamedQueries({
    @NamedQuery(name = "UrgSangre.findAll", query = "SELECT u FROM UrgSangre u")})
public class UrgSangre implements Serializable {

    @Basic(optional = false)
    @Column(name = "rh")
    private String rh;

    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;

    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idevo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HcuEvolucion idHcuEvolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsangre")
    private List<UrgSangreProcedimientos> urgSangreProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsangre")
    private List<UrgSangreCie10> urgSangreCie10List;

    public UrgSangre() {
    }

    public UrgSangre(Integer id) {
        this.id = id;
    }

    public UrgSangre(Integer id, String motivo, int usuario, int estado, Date fecha) {
        this.id = id;
        this.motivo = motivo;
        this.usuario = usuario;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HcuEvolucion getIdHcuEvolucion() {
        return idHcuEvolucion;
    }

    public void setIdHcuEvolucion(HcuEvolucion idHcuEvolucion) {
        this.idHcuEvolucion = idHcuEvolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
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

    public List<UrgSangreProcedimientos> getUrgSangreProcedimientosList() {
        return urgSangreProcedimientosList;
    }

    public void setUrgSangreProcedimientosList(List<UrgSangreProcedimientos> urgSangreProcedimientosList) {
        this.urgSangreProcedimientosList = urgSangreProcedimientosList;
    }

    public List<UrgSangreCie10> getUrgSangreCie10List() {
        return urgSangreCie10List;
    }

    public void setUrgSangreCie10List(List<UrgSangreCie10> urgSangreCie10List) {
        this.urgSangreCie10List = urgSangreCie10List;
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
        if (!(object instanceof UrgSangre)) {
            return false;
        }
        UrgSangre other = (UrgSangre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UrgSangre[ id=" + id + " ]";
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

}
