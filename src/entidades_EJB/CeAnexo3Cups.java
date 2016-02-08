/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_anexo3_cups")
public class CeAnexo3Cups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_cups")
    private int idCups;
    @Column(name = "cantidad")
    private String cantidad;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_anexo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeAnexo3 idAnexo;

    public CeAnexo3Cups() {
    }

    public CeAnexo3Cups(Integer id) {
        this.id = id;
    }

    public CeAnexo3Cups(Integer id, int idCups) {
        this.id = id;
        this.idCups = idCups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCups() {
        return idCups;
    }

    public void setIdCups(int idCups) {
        this.idCups = idCups;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public CeAnexo3 getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(CeAnexo3 idAnexo) {
        this.idAnexo = idAnexo;
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
        if (!(object instanceof CeAnexo3Cups)) {
            return false;
        }
        CeAnexo3Cups other = (CeAnexo3Cups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.CeAnexo3Cups[ id=" + id + " ]";
    }
    
}
