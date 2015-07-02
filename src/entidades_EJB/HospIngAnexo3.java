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
 * @author Juan Camilo
 */
@Entity
@Table(name = "hosp_ing_anexo3")
@NamedQueries({
    @NamedQuery(name = "HospIngAnexo3.findAll", query = "SELECT h FROM HospIngAnexo3 h")})
public class HospIngAnexo3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "cama")
    private String cama;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fechadigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadigita;
    @JoinColumn(name = "idhistoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospHistoriac idHospHistoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<HospIngAnexo3Procedimientos> hospIngAnexo3ProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<HospIngAnexo3Cie10> hospIngAnexo3Cie10List;

    public HospIngAnexo3() {
    }

    public HospIngAnexo3(Integer id) {
        this.id = id;
    }

    public HospIngAnexo3(Integer id, String justificacion, int prioridad, int estado, Date fechadigita) {
        this.id = id;
        this.justificacion = justificacion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechadigita = fechadigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HospHistoriac getIdHospHistoria() {
        return idHospHistoria;
    }

    public void setIdHospHistoria(HospHistoriac idHospHistoria) {
        this.idHospHistoria = idHospHistoria;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechadigita() {
        return fechadigita;
    }

    public void setFechadigita(Date fechadigita) {
        this.fechadigita = fechadigita;
    }

    public List<HospIngAnexo3Procedimientos> getHospIngAnexo3ProcedimientosList() {
        return hospIngAnexo3ProcedimientosList;
    }

    public void setHospIngAnexo3ProcedimientosList(List<HospIngAnexo3Procedimientos> hospIngAnexo3ProcedimientosList) {
        this.hospIngAnexo3ProcedimientosList = hospIngAnexo3ProcedimientosList;
    }

    public List<HospIngAnexo3Cie10> getHospIngAnexo3Cie10List() {
        return hospIngAnexo3Cie10List;
    }

    public void setHospIngAnexo3Cie10List(List<HospIngAnexo3Cie10> hospIngAnexo3Cie10List) {
        this.hospIngAnexo3Cie10List = hospIngAnexo3Cie10List;
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
        if (!(object instanceof HospIngAnexo3)) {
            return false;
        }
        HospIngAnexo3 other = (HospIngAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospIngAnexo3[ id=" + id + " ]";
    }
    
}
