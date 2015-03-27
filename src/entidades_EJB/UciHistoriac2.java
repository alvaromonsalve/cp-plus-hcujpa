
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "uci_historiac_2")
@NamedQueries({
    @NamedQuery(name = "UciHistoriac2.findAll", query = "SELECT h FROM UciHistoriac2 h")})
public class UciHistoriac2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_info_historiac")
    @OneToOne
    private int idUciHistoriac;
    @Basic(optional = false)
    @Column(name = "tiempo_consulta")
    private int tiempoConsulta;
    @Column(name = "n_abortos")
    private String NAbortos;
    @Column(name = "n_cesarias")
    private String NCesarias;
    @Column(name = "fum")
    @Temporal(TemporalType.DATE)
    private Date Fum;
    @Column(name = "n_partos")
    private String NPartos;
    @Column(name = "n_gestas")
    private String NGestas;
    @Column(name = "obs_ant_gine")
    private String ObsAntGine;
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fIngreso;

    public UciHistoriac2() {
    }

    public UciHistoriac2(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUciHistoriac() {
        return idUciHistoriac;
    }

    public void setIdUciHistoriac(int idUciHistoriac) {
        this.idUciHistoriac = idUciHistoriac;
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

    public String getNAbortos() {
        return NAbortos;
    }

    public void setNAbortos(String NAbortos) {
        this.NAbortos = NAbortos;
    }

    public String getNCesarias() {
        return NCesarias;
    }

    public void setNCesarias(String NCesarias) {
        this.NCesarias = NCesarias;
    }

    public Date getFum() {
        return Fum;
    }

    public void setFum(Date Fum) {
        this.Fum = Fum;
    }

    public String getNPartos() {
        return NPartos;
    }

    public void setNPartos(String NPartos) {
        this.NPartos = NPartos;
    }

    public String getNGestas() {
        return NGestas;
    }

    public void setNGestas(String NGestas) {
        this.NGestas = NGestas;
    }

    public String getObsAntGine() {
        return ObsAntGine;
    }

    public void setObsAntGine(String ObsAntGine) {
        this.ObsAntGine = ObsAntGine;
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
        if (!(object instanceof UciHistoriac2)) {
            return false;
        }
        UciHistoriac2 other = (UciHistoriac2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UciHistoriac2[ id=" + id + " ]";
    }

}
