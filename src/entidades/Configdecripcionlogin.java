/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package entidades;

import java.io.Serializable;
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

/**
*
* @author Alvaro Monsalve
*/
@Entity
@Table(name = "Config_decripcion_login")
@NamedQueries({
    @NamedQuery(name = "Configdecripcionlogin.findAll", query = "SELECT c FROM Configdecripcionlogin c")})
public class Configdecripcionlogin implements Serializable {
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "celular")
    private String celular;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "ext")
    private String ext;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    @ManyToOne
    private Configlogin idLogin;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "ruta_firma")
    private String ruta_firma;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="idConfigdecripcionlogin", fetch = FetchType.LAZY)
    private List<InfoHistoriac> infoHistoriac;

    public List<InfoHistoriac> getInfoHistoriac() {
        return infoHistoriac;
    }

    public void setInfoHistoriac(List<InfoHistoriac> infoHistoriac) {
        this.infoHistoriac = infoHistoriac;
    }

    public Configdecripcionlogin() {
    }

    public Configdecripcionlogin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getRuta_firma() {
        return ruta_firma;
    }

    public void setRuta_firma(String ruta_firma) {
        this.ruta_firma = ruta_firma;
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
        if (!(object instanceof Configdecripcionlogin)) {
            return false;
        }
        Configdecripcionlogin other = (Configdecripcionlogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Configdecripcionlogin[ id=" + id + " ]";
    }

    public Configlogin getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Configlogin idLogin) {
        this.idLogin = idLogin;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
}
    
}