/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author Alvaro Monsalve
*/
@Entity
@Table(name = "Config_login")
@NamedQueries({
    @NamedQuery(name = "Configlogin.findAll", query = "SELECT c FROM Configlogin c")})
public class Configlogin implements Serializable {
    @OneToMany(mappedBy = "idLogin", fetch = FetchType.LAZY)
    private List<Configdecripcionlogin> configdecripcionloginList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "loGin")
    private String loGin;
    @Column(name = "pasSword")
    private String pasSword;
    @Column(name = "estado")
    private short estado;

    public Configlogin() {
    }

    public Configlogin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoGin() {
        return loGin;
    }

    public void setLoGin(String loGin) {
        this.loGin = loGin;
    }

    public String getPasSword() {
        return pasSword;
    }

    public void setPasSword(String pasSword) {
        this.pasSword = pasSword;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
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
        if (!(object instanceof Configlogin)) {
            return false;
        }
        Configlogin other = (Configlogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Configlogin[ id=" + id + " ]";
    }

    public List<Configdecripcionlogin> getConfigdecripcionloginList() {
        return configdecripcionloginList;
    }

    public void setConfigdecripcionloginList(List<Configdecripcionlogin> configdecripcionloginList) {
        this.configdecripcionloginList = configdecripcionloginList;
    }
    
}