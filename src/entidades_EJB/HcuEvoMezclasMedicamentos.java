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
 * @author Administrador
 */
@Entity
@Table(name = "hcu_evo_mezclas_medicamentos")
@NamedQueries({
    @NamedQuery(name = "HcuEvoMezclasMedicamentos.findAll", query = "SELECT h FROM HcuEvoMezclasMedicamentos h")})
public class HcuEvoMezclasMedicamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evolucion",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HcuEvolucion idHcuEvolucion;
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idHcuEvoMezclasMedicamentos", fetch = FetchType.LAZY)
    private List<HcuEvoMezclasMedicamentosDesc> HcuEvoMezclasMedicamentosDescs;

    public HcuEvoMezclasMedicamentos() {
    }

    public HcuEvoMezclasMedicamentos(Integer id) {
        this.id = id;
    }

    public HcuEvoMezclasMedicamentos(Integer id, HcuEvolucion idHcuEvolucion, String via, int estado, int usuario, Date fDigita) {
        this.id = id;
        this.idHcuEvolucion = idHcuEvolucion;
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

    public HcuEvolucion getIdHcuEvolucion() {
        return idHcuEvolucion;
    }

    public void setIdHcuEvolucion(HcuEvolucion idHcuEvolucion) {
        this.idHcuEvolucion = idHcuEvolucion;
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

    public List<HcuEvoMezclasMedicamentosDesc> getHcuEvoMezclasMedicamentosDescs() {
        return HcuEvoMezclasMedicamentosDescs;
    }

    public void setHcuEvoMezclasMedicamentosDescs(List<HcuEvoMezclasMedicamentosDesc> HcuEvoMezclasMedicamentosDescs) {
        this.HcuEvoMezclasMedicamentosDescs = HcuEvoMezclasMedicamentosDescs;
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
        if (!(object instanceof HcuEvoMezclasMedicamentos)) {
            return false;
        }
        HcuEvoMezclasMedicamentos other = (HcuEvoMezclasMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuEvoMezclasMedicamentos[ id=" + id + " ]";
    }
    
}
