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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uci_infquirurgico_dxpost")
@NamedQueries({
    @NamedQuery(name = "UciInfquirurgicoDxpost.findAll", query = "SELECT u FROM UciInfquirurgicoDxpost u")})
public class UciInfquirurgicoDxpost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "iddx")
    private int iddx;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idinfquirurgico", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciInfquirurgico idinfquirurgico;

    public UciInfquirurgicoDxpost() {
    }

    public UciInfquirurgicoDxpost(Integer id) {
        this.id = id;
    }

    public UciInfquirurgicoDxpost(Integer id, int iddx, int estado) {
        this.id = id;
        this.iddx = iddx;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIddx() {
        return iddx;
    }

    public void setIddx(int iddx) {
        this.iddx = iddx;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public UciInfquirurgico getIdinfquirurgico() {
        return idinfquirurgico;
    }

    public void setIdinfquirurgico(UciInfquirurgico idinfquirurgico) {
        this.idinfquirurgico = idinfquirurgico;
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
        if (!(object instanceof UciInfquirurgicoDxpost)) {
            return false;
        }
        UciInfquirurgicoDxpost other = (UciInfquirurgicoDxpost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciInfquirurgicoDxpost[ id=" + id + " ]";
    }

}
