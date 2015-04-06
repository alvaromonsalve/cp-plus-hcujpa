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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "hosp_commentproce")
@NamedQueries({
    @NamedQuery(name = "HospCommentproce.findAll", query = "SELECT h FROM HospCommentproce h")})
public class HospCommentproce implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tiporegistro")
    private short tiporegistro;
    @Basic(optional = false)
    @Column(name = "idregistro")
    private int idregistro;
    @Basic(optional = false)
    @Column(name = "idcup")
    private int idcup;
    @Basic(optional = false)
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "idactregitro")
    private int idactregitro;
    @Column(name = "estado")
    private Short estado;

    public HospCommentproce() {
    }

    public HospCommentproce(Integer id) {
        this.id = id;
    }

    public HospCommentproce(Integer id, short tiporegistro, int idregistro, int idcup, String comentario, int idactregitro) {
        this.id = id;
        this.tiporegistro = tiporegistro;
        this.idregistro = idregistro;
        this.idcup = idcup;
        this.comentario = comentario;
        this.idactregitro = idactregitro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTiporegistro() {
        return tiporegistro;
    }

    public void setTiporegistro(short tiporegistro) {
        this.tiporegistro = tiporegistro;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getIdcup() {
        return idcup;
    }

    public void setIdcup(int idcup) {
        this.idcup = idcup;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdactregitro() {
        return idactregitro;
    }

    public void setIdactregitro(int idactregitro) {
        this.idactregitro = idactregitro;
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
        if (!(object instanceof HospCommentproce)) {
            return false;
        }
        HospCommentproce other = (HospCommentproce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospCommentproce[ id=" + id + " ]";
    }
    
}
