/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "hcu_mezclas_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HcuMezclasMedicamentos.findAll", query = "SELECT h FROM HcuMezclasMedicamentos h"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findById", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.id = :id"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findByIdHistoriac", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.idHistoriac = :idHistoriac"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findByVia", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.via = :via"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findByEstado", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.estado = :estado"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findByUsuario", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HcuMezclasMedicamentos.findByFDigita", query = "SELECT h FROM HcuMezclasMedicamentos h WHERE h.fDigita = :fDigita")})
public class HcuMezclasMedicamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name="id_historiac",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private InfoHistoriac idHistoriac;
    @Basic(optional = false)
    @Column(name = "via")
    private String via;
    @Lob
    @Column(name = "administracion")
    private String administracion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idHcuMezclasMedicamentos", fetch = FetchType.LAZY)
    private List<HcuMezclasMedicamentosDesc> hcuMezclasMedicamentosDescList;

    public HcuMezclasMedicamentos() {
    }

    public HcuMezclasMedicamentos(Integer id) {
        this.id = id;
    }

    public HcuMezclasMedicamentos(Integer id, InfoHistoriac idHistoriac, String via, int estado, int usuario, Date fDigita) {
        this.id = id;
        this.idHistoriac = idHistoriac;
        this.via = via;
        this.estado = estado;
        this.usuario = usuario;
        this.fDigita = fDigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InfoHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(InfoHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFDigita() {
        return fDigita;
    }

    public void setFDigita(Date fDigita) {
        this.fDigita = fDigita;
    }
    
    public List<HcuMezclasMedicamentosDesc> getHcuMezclasMedicamentosDescList() {
        return hcuMezclasMedicamentosDescList;
    }

    public void setHcuMezclasMedicamentosDescList(List<HcuMezclasMedicamentosDesc> hcuMezclasMedicamentosDescList) {
        this.hcuMezclasMedicamentosDescList = hcuMezclasMedicamentosDescList;
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
        if (!(object instanceof HcuMezclasMedicamentos)) {
            return false;
        }
        HcuMezclasMedicamentos other = (HcuMezclasMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mezcla de Medicamentos [ id=" + id + " ]";
    }
    
}
