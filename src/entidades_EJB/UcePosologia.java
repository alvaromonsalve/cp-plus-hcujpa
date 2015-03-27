
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "uce_posologia")
@NamedQueries({
    @NamedQuery(name = "UcePosologia.findAll", query = "SELECT i FROM UcePosologia i")})
public class UcePosologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;
    @JoinColumn(name="id_historiac",referencedColumnName="id")
    @ManyToOne(optional = false)
    private UceHistoriac idHistoriac;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis_n")
    private Float dosisN;
    @Column(name = "cantidad")
    private Float cantidad;
    @Lob
    @Column(name = "cantidad_texto")
    private String cantidadTexto;
    @Column(name = "dosis_u")
    private String dosisU;
    @Column(name = "via")
    private String via;
    @Lob
    @Column(name = "administracion")
    private String administracion;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "fdigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdigita;

    public UcePosologia() {
    }

    public UcePosologia(Integer id) {
        this.id = id;
    }

    public UcePosologia(Integer id, SumSuministro idSuministro, UceHistoriac idHistoriac, int usuario, Date fdigita) {
        this.id = id;
        this.idSuministro = idSuministro;
        this.idHistoriac = idHistoriac;
        this.usuario = usuario;
        this.fdigita = fdigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
    }

    public UceHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(UceHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getDosisN() {
        return dosisN;
    }

    public void setDosisN(Float dosisN) {
        this.dosisN = dosisN;
    }

    public String getCantidadTexto() {
        return cantidadTexto;
    }

    public void setCantidadTexto(String cantidadTexto) {
        this.cantidadTexto = cantidadTexto;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UcePosologia)) {
            return false;
        }
        UcePosologia other = (UcePosologia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdSuministro().getIdPricipioactivo().getNombre();
    }

}
