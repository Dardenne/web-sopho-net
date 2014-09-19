/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_KEYWORD_TRANSLATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TKeywordTranslations.findAll", query = "SELECT t FROM TKeywordTranslations t"),
    @NamedQuery(name = "TKeywordTranslations.findByKytrNsq", query = "SELECT t FROM TKeywordTranslations t WHERE t.kytrNsq = :kytrNsq"),
    @NamedQuery(name = "TKeywordTranslations.findByKytrTranslationLib", query = "SELECT t FROM TKeywordTranslations t WHERE t.kytrTranslationLib = :kytrTranslationLib")})
public class TKeywordTranslations implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KYTR_NSQ")
    private BigDecimal kytrNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "KYTR_TRANSLATION_LIB")
    private String kytrTranslationLib;
    @JoinColumn(name = "KYTR_CHCO_NSQ", referencedColumnName = "CHCO_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TChapterContents kytrChcoNsq;
    @JoinColumn(name = "KYTR_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages kytrDlanNsq;
    @JoinColumn(name = "KYTR_KYWD_NSQ", referencedColumnName = "KYWD_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TKeywords kytrKywdNsq;

    public TKeywordTranslations() {
    }

    public TKeywordTranslations(BigDecimal kytrNsq) {
        this.kytrNsq = kytrNsq;
    }

    public TKeywordTranslations(BigDecimal kytrNsq, String kytrTranslationLib) {
        this.kytrNsq = kytrNsq;
        this.kytrTranslationLib = kytrTranslationLib;
    }

    public BigDecimal getKytrNsq() {
        return kytrNsq;
    }

    public void setKytrNsq(BigDecimal kytrNsq) {
        this.kytrNsq = kytrNsq;
    }

    public String getKytrTranslationLib() {
        return kytrTranslationLib;
    }

    public void setKytrTranslationLib(String kytrTranslationLib) {
        this.kytrTranslationLib = kytrTranslationLib;
    }

    public TChapterContents getKytrChcoNsq() {
        return kytrChcoNsq;
    }

    public void setKytrChcoNsq(TChapterContents kytrChcoNsq) {
        this.kytrChcoNsq = kytrChcoNsq;
    }

    public TDocumentLanguages getKytrDlanNsq() {
        return kytrDlanNsq;
    }

    public void setKytrDlanNsq(TDocumentLanguages kytrDlanNsq) {
        this.kytrDlanNsq = kytrDlanNsq;
    }

    public TKeywords getKytrKywdNsq() {
        return kytrKywdNsq;
    }

    public void setKytrKywdNsq(TKeywords kytrKywdNsq) {
        this.kytrKywdNsq = kytrKywdNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kytrNsq != null ? kytrNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TKeywordTranslations)) {
            return false;
        }
        TKeywordTranslations other = (TKeywordTranslations) object;
        if ((this.kytrNsq == null && other.kytrNsq != null) || (this.kytrNsq != null && !this.kytrNsq.equals(other.kytrNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TKeywordTranslations[ kytrNsq=" + kytrNsq + " ]";
    }
    
}
