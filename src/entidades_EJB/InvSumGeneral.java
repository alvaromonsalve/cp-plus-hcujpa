
package entidades_EJB;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "inv_sum_general")
@NamedQueries({
    @NamedQuery(name = "InvSumGeneral.findAll", query = "SELECT i FROM InvSumGeneral i")})
public class InvSumGeneral implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSumgeneral", fetch = FetchType.LAZY)
    private List<InvDatosCompra> invDatosCompraList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lote")
    private String lote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_compra")
    private BigDecimal valorCompra;
    @Column(name = "valor_venta")
    private BigDecimal valorVenta;
    @Column(name = "fcompra")
    @Temporal(TemporalType.DATE)
    private Date fcompra;
    @Column(name = "fvencimiento")
    @Temporal(TemporalType.DATE)
    private Date fvencimiento;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "fdigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdigita;
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;

    public InvSumGeneral() {
    }

    public InvSumGeneral(Integer id) {
        this.id = id;
    }

    public InvSumGeneral(Integer id, int usuario, Date fdigita) {
        this.id = id;
        this.usuario = usuario;
        this.fdigita = fdigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(BigDecimal valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Date getFcompra() {
        return fcompra;
    }

    public void setFcompra(Date fcompra) {
        this.fcompra = fcompra;
    }

    public Date getFvencimiento() {
        return fvencimiento;
    }

    public void setFvencimiento(Date fvencimiento) {
        this.fvencimiento = fvencimiento;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFdigita() {
        return fdigita;
    }

    public void setFdigita(Date fdigita) {
        this.fdigita = fdigita;
    }

    public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
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
        if (!(object instanceof InvSumGeneral)) {
            return false;
        }
        InvSumGeneral other = (InvSumGeneral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InvSumGeneral[ id=" + id + " ]";
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public List<InvDatosCompra> getInvDatosCompraList() {
        return invDatosCompraList;
    }

    public void setInvDatosCompraList(List<InvDatosCompra> invDatosCompraList) {
        this.invDatosCompraList = invDatosCompraList;
    }
    
}
