/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_hc_expfisica")
@NamedQueries({
    @NamedQuery(name = "InfoHcExpfisica.findAll", query = "SELECT i FROM InfoHcExpfisica i")})
public class InfoHcExpfisica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ta")
    private String ta;
    @Column(name = "tam")
    private String tam;
    @Column(name = "fc")
    private String fc;
    @Column(name = "fr")
    private String fr;
    @Column(name = "t")
    private String t;
    @Column(name = "sao2")
    private String sao2;
    @Column(name = "pvc")
    private String pvc;
    @Column(name = "pic")
    private String pic;
    @Column(name = "peso")
    private String peso;
    @Column(name = "talla")
    private String talla;
    @Lob
    @Column(name = "otros")
    private String otros;
    @Lob
    @Column(name = "aspectogeneral")
    private String aspectogeneral;
    @Lob
    @Column(name = "cara")
    private String cara;
    @Lob
    @Column(name = "cardio")
    private String cardio;
    @Lob
    @Column(name = "respiratorio")
    private String respiratorio;
    @Lob
    @Column(name = "gastro")
    private String gastro;
    @Lob
    @Column(name = "renal")
    private String renal;
    @Lob
    @Column(name = "hemato")
    private String hemato;
    @Lob
    @Column(name = "endo")
    private String endo;
    @Lob
    @Column(name = "osteo")
    private String osteo;
    @JoinColumn(name = "id_infohistoriac", referencedColumnName = "id")
    @OneToOne(optional = true)
    private InfoHistoriac idInfohistoriac;

    public InfoHcExpfisica() {
    }

    public InfoHcExpfisica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getSao2() {
        return sao2;
    }

    public void setSao2(String sao2) {
        this.sao2 = sao2;
    }

    public String getPvc() {
        return pvc;
    }

    public void setPvc(String pvc) {
        this.pvc = pvc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getAspectogeneral() {
        return aspectogeneral;
    }

    public void setAspectogeneral(String aspectogeneral) {
        this.aspectogeneral = aspectogeneral;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }

    public String getCardio() {
        return cardio;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    public String getRespiratorio() {
        return respiratorio;
    }

    public void setRespiratorio(String respiratorio) {
        this.respiratorio = respiratorio;
    }

    public String getGastro() {
        return gastro;
    }

    public void setGastro(String gastro) {
        this.gastro = gastro;
    }

    public String getRenal() {
        return renal;
    }

    public void setRenal(String renal) {
        this.renal = renal;
    }

    public String getHemato() {
        return hemato;
    }

    public void setHemato(String hemato) {
        this.hemato = hemato;
    }

    public String getEndo() {
        return endo;
    }

    public void setEndo(String endo) {
        this.endo = endo;
    }

    public String getOsteo() {
        return osteo;
    }

    public void setOsteo(String osteo) {
        this.osteo = osteo;
    }

    public InfoHistoriac getIdInfohistoriac() {
        return idInfohistoriac;
    }

    public void setIdInfohistoriac(InfoHistoriac idInfohistoriac) {
        this.idInfohistoriac = idInfohistoriac;
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
        if (!(object instanceof InfoHcExpfisica)) {
            return false;
        }
        InfoHcExpfisica other = (InfoHcExpfisica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoHcExpfisica[ id=" + id + " ]";
    }
    
}
