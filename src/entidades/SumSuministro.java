
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "sum_suministro")
@NamedQueries({
    @NamedQuery(name = "SumSuministro.findAll", query = "SELECT s FROM SumSuministro s"),
    @NamedQuery(name="SumSuministro.findFiltro",query = "SELECT s FROM SumSuministro s WHERE s.idPricipioactivo.nombre LIKE :suministro OR s.suministro LIKE :suministro")})

public class SumSuministro implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuministro")
    private List<InvSumGeneral> invSumGeneralList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "suministro")
    private String suministro;
    @JoinColumn(name =  "id_pricipioactivo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumAtcPrincipioactivo idPricipioactivo;
    @Column(name = "unidadmedida")
    private String unidadmedida;
    @JoinColumn(name = "id_presentacionfarmaceutica", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumAtcPresentacionfarmaceutica idPresentacionfarmaceutica;
    @Column(name = "viaadministracion")
    private String viaadministracion;
    @Column(name = "presentacioncomercial")
    private String presentacioncomercial;
    @Column(name = "concentracion")
    private String concentracion;
    @JoinColumn(name = "id_laboratorio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumAtcLaboratorio idLaboratorio;
    @Column(name = "registro_invima")
    private String registroInvima;
    @Column(name = "categoria")
    private Short categoria;
    @Column(name = "Pos")
    private Boolean pos;
    @Column(name = "estado")
    private Short estado;

    public SumSuministro() {
    }

    public SumSuministro(Integer id) {
        this.id = id;
    }

    public SumSuministro(Integer id, SumAtcPrincipioactivo idPricipioactivo, SumAtcPresentacionfarmaceutica idPresentacionfarmaceutica, SumAtcLaboratorio idLaboratorio) {
        this.id = id;
        this.idPricipioactivo = idPricipioactivo;
        this.idPresentacionfarmaceutica = idPresentacionfarmaceutica;
        this.idLaboratorio = idLaboratorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuministro() {
        return suministro;
    }

    public void setSuministro(String suministro) {
        this.suministro = suministro;
    }

    public SumAtcPrincipioactivo getIdPricipioactivo() {
        return idPricipioactivo;
    }

    public void setIdPricipioactivo(SumAtcPrincipioactivo idPricipioactivo) {
        this.idPricipioactivo = idPricipioactivo;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public SumAtcPresentacionfarmaceutica getIdPresentacionfarmaceutica() {
        return idPresentacionfarmaceutica;
    }

    public void setIdPresentacionfarmaceutica(SumAtcPresentacionfarmaceutica idPresentacionfarmaceutica) {
        this.idPresentacionfarmaceutica = idPresentacionfarmaceutica;
    }

    public String getViaadministracion() {
        return viaadministracion;
    }

    public void setViaadministracion(String viaadministracion) {
        this.viaadministracion = viaadministracion;
    }

    public String getPresentacioncomercial() {
        return presentacioncomercial;
    }

    public void setPresentacioncomercial(String presentacioncomercial) {
        this.presentacioncomercial = presentacioncomercial;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public SumAtcLaboratorio getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(SumAtcLaboratorio idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(String registroInvima) {
        this.registroInvima = registroInvima;
    }

    public Short getCategoria() {
        return categoria;
    }

    public void setCategoria(Short categoria) {
        this.categoria = categoria;
    }

    public Boolean getPos() {
        return pos;
    }

    public void setPos(Boolean pos) {
        this.pos = pos;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
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
        if (!(object instanceof SumSuministro)) {
            return false;
        }
        SumSuministro other = (SumSuministro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getSuministro();
    }

    public List<InvSumGeneral> getInvSumGeneralList() {
        return invSumGeneralList;
    }

    public void setInvSumGeneralList(List<InvSumGeneral> invSumGeneralList) {
        this.invSumGeneralList = invSumGeneralList;
    }
    
}
