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
@Table(name = "urg_ing_anexo3")
@NamedQueries({
    @NamedQuery(name = "UrgIngAnexo3.findAll", query = "SELECT u FROM UrgIngAnexo3 u")})
public class UrgIngAnexo3 implements Serializable {
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
    private InfoHistoriac idInfoHistoriac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<UrgIngAnexo3Procedimientos> urgIngAnexo3ProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<UrgIngAnexo3Cie10> urgIngAnexo3Cie10List;

    public UrgIngAnexo3() {
    }

    public UrgIngAnexo3(Integer id) {
        this.id = id;
    }

    public UrgIngAnexo3(Integer id, String justificacion, int prioridad, int estado, Date fechadigita) {
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

    public InfoHistoriac getIdInfoHistoriac() {
        return idInfoHistoriac;
    }

    public void setIdInfoHistoriac(InfoHistoriac idInfoHistoriac) {
        this.idInfoHistoriac = idInfoHistoriac;
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

    public List<UrgIngAnexo3Procedimientos> getUrgIngAnexo3ProcedimientosList() {
        return urgIngAnexo3ProcedimientosList;
    }

    public void setUrgIngAnexo3ProcedimientosList(List<UrgIngAnexo3Procedimientos> urgIngAnexo3ProcedimientosList) {
        this.urgIngAnexo3ProcedimientosList = urgIngAnexo3ProcedimientosList;
    }

    public List<UrgIngAnexo3Cie10> getUrgIngAnexo3Cie10List() {
        return urgIngAnexo3Cie10List;
    }

    public void setUrgIngAnexo3Cie10List(List<UrgIngAnexo3Cie10> urgIngAnexo3Cie10List) {
        this.urgIngAnexo3Cie10List = urgIngAnexo3Cie10List;
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
        if (!(object instanceof UrgIngAnexo3)) {
            return false;
        }
        UrgIngAnexo3 other = (UrgIngAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UrgIngAnexo3[ id=" + id + " ]";
    }
    
}
