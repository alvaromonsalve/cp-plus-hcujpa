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
@Table(name = "uci_sangre")
@NamedQueries({
    @NamedQuery(name = "UciSangre.findAll", query = "SELECT u FROM UciSangre u")})
public class UciSangre implements Serializable {

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
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "rh")
    private String rh;
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
    private UciEvolucion idUciEvolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsangre", fetch = FetchType.LAZY)
    private List<UciSangreCie10> uciSangreCie10List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsangre", fetch = FetchType.LAZY)
    private List<UciSangreProcedimientos> uciSangreProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsangre", fetch = FetchType.LAZY)
    private List<UciTransfusiones> uciTransfusionesList;

    public UciSangre() {
    }

    public UciSangre(Integer id) {
        this.id = id;
    }

    public UciSangre(Integer id, String motivo, int cantidad, int tipo, String rh, int usuario, int estado, Date fecha) {
        this.id = id;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.rh = rh;
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

    public UciEvolucion getIdUciEvolucion() {
        return idUciEvolucion;
    }

    public void setIdUciEvolucion(UciEvolucion idUciEvolucion) {
        this.idUciEvolucion = idUciEvolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
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

    public List<UciSangreCie10> getUciSangreCie10List() {
        return uciSangreCie10List;
    }

    public void setUciSangreCie10List(List<UciSangreCie10> uciSangreCie10List) {
        this.uciSangreCie10List = uciSangreCie10List;
    }

    public List<UciSangreProcedimientos> getUciSangreProcedimientosList() {
        return uciSangreProcedimientosList;
    }

    public void setUciSangreProcedimientosList(List<UciSangreProcedimientos> uciSangreProcedimientosList) {
        this.uciSangreProcedimientosList = uciSangreProcedimientosList;
    }

    public List<UciTransfusiones> getUciTransfusionesList() {
        return uciTransfusionesList;
    }

    public void setUciTransfusionesList(List<UciTransfusiones> uciTransfusionesList) {
        this.uciTransfusionesList = uciTransfusionesList;
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
        if (!(object instanceof UciSangre)) {
            return false;
        }
        UciSangre other = (UciSangre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciSangre[ id=" + id + " ]";
    }

}
