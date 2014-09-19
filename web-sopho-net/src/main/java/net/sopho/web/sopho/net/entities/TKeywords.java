/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_KEYWORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TKeywords.findAll", query = "SELECT t FROM TKeywords t"),
    @NamedQuery(name = "TKeywords.findByKywdNsq", query = "SELECT t FROM TKeywords t WHERE t.kywdNsq = :kywdNsq"),
    @NamedQuery(name = "TKeywords.findByKywdKeywordLib", query = "SELECT t FROM TKeywords t WHERE t.kywdKeywordLib = :kywdKeywordLib")})
public class TKeywords implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KYWD_NSQ")
    private BigDecimal kywdNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "KYWD_KEYWORD_LIB")
    private String kywdKeywordLib;
    @JoinColumn(name = "KYWD_DOCU_NSQ", referencedColumnName = "DOCU_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocuments kywdDocuNsq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kytrKywdNsq", fetch = FetchType.LAZY)
    private Collection<TKeywordTranslations> tKeywordTranslationsCollection;

    public TKeywords() {
    }

    public TKeywords(BigDecimal kywdNsq) {
        this.kywdNsq = kywdNsq;
    }

    public TKeywords(BigDecimal kywdNsq, String kywdKeywordLib) {
        this.kywdNsq = kywdNsq;
        this.kywdKeywordLib = kywdKeywordLib;
    }

    public BigDecimal getKywdNsq() {
        return kywdNsq;
    }

    public void setKywdNsq(BigDecimal kywdNsq) {
        this.kywdNsq = kywdNsq;
    }

    public String getKywdKeywordLib() {
        return kywdKeywordLib;
    }

    public void setKywdKeywordLib(String kywdKeywordLib) {
        this.kywdKeywordLib = kywdKeywordLib;
    }

    public TDocuments getKywdDocuNsq() {
        return kywdDocuNsq;
    }

    public void setKywdDocuNsq(TDocuments kywdDocuNsq) {
        this.kywdDocuNsq = kywdDocuNsq;
    }

    @XmlTransient
    public Collection<TKeywordTranslations> getTKeywordTranslationsCollection() {
        return tKeywordTranslationsCollection;
    }

    public void setTKeywordTranslationsCollection(Collection<TKeywordTranslations> tKeywordTranslationsCollection) {
        this.tKeywordTranslationsCollection = tKeywordTranslationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kywdNsq != null ? kywdNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TKeywords)) {
            return false;
        }
        TKeywords other = (TKeywords) object;
        if ((this.kywdNsq == null && other.kywdNsq != null) || (this.kywdNsq != null && !this.kywdNsq.equals(other.kywdNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TKeywords[ kywdNsq=" + kywdNsq + " ]";
    }
    
}
