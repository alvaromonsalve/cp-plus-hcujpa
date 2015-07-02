/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "uce_ing_anexo3_cie10")
@NamedQueries({
    @NamedQuery(name = "UceIngAnexo3Cie10.findAll", query = "SELECT u FROM UceIngAnexo3Cie10 u")})
public class UceIngAnexo3Cie10 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idcie10")
    private int idcie10;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idanexo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UceIngAnexo3 idanexo;

    public UceIngAnexo3Cie10() {
    }

    public UceIngAnexo3Cie10(Integer id) {
        this.id = id;
    }

    public UceIngAnexo3Cie10(Integer id, int idcie10, int estado, Date fecha) {
        this.id = id;
        this.idcie10 = idcie10;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcie10() {
        return idcie10;
    }

    public void setIdcie10(int idcie10) {
        this.idcie10 = idcie10;
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

    public UceIngAnexo3 getIdanexo() {
        return idanexo;
    }

    public void setIdanexo(UceIngAnexo3 idanexo) {
        this.idanexo = idanexo;
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
        if (!(object instanceof UceIngAnexo3Cie10)) {
            return false;
        }
        UceIngAnexo3Cie10 other = (UceIngAnexo3Cie10) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UceIngAnexo3Cie10[ id=" + id + " ]";
    }
    
}
