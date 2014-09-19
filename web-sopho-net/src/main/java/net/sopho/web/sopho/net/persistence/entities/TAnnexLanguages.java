/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "T_ANNEX_LANGUAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAnnexLanguages.findAll", query = "SELECT t FROM TAnnexLanguages t"),
    @NamedQuery(name = "TAnnexLanguages.findByAlanNsq", query = "SELECT t FROM TAnnexLanguages t WHERE t.alanNsq = :alanNsq"),
    @NamedQuery(name = "TAnnexLanguages.findByAlanAcronymLib", query = "SELECT t FROM TAnnexLanguages t WHERE t.alanAcronymLib = :alanAcronymLib"),
    @NamedQuery(name = "TAnnexLanguages.findByAlanLanguageLib", query = "SELECT t FROM TAnnexLanguages t WHERE t.alanLanguageLib = :alanLanguageLib"),
    @NamedQuery(name = "TAnnexLanguages.findByAlanOrderNum", query = "SELECT t FROM TAnnexLanguages t WHERE t.alanOrderNum = :alanOrderNum")})
public class TAnnexLanguages implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALAN_NSQ")
    private BigDecimal alanNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ALAN_ACRONYM_LIB")
    private String alanAcronymLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ALAN_LANGUAGE_LIB")
    private String alanLanguageLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALAN_ORDER_NUM")
    private BigInteger alanOrderNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anexAlanNsq", fetch = FetchType.LAZY)
    private Collection<TAnnexes> tAnnexesCollection;
    @OneToMany(mappedBy = "zipaAlanNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doanAlanNsq", fetch = FetchType.LAZY)
    private Collection<TDocumentlangsAnnexlangs> tDocumentlangsAnnexlangsCollection;

    public TAnnexLanguages() {
    }

    public TAnnexLanguages(BigDecimal alanNsq) {
        this.alanNsq = alanNsq;
    }

    public TAnnexLanguages(BigDecimal alanNsq, String alanAcronymLib, String alanLanguageLib, BigInteger alanOrderNum) {
        this.alanNsq = alanNsq;
        this.alanAcronymLib = alanAcronymLib;
        this.alanLanguageLib = alanLanguageLib;
        this.alanOrderNum = alanOrderNum;
    }

    public BigDecimal getAlanNsq() {
        return alanNsq;
    }

    public void setAlanNsq(BigDecimal alanNsq) {
        this.alanNsq = alanNsq;
    }

    public String getAlanAcronymLib() {
        return alanAcronymLib;
    }

    public void setAlanAcronymLib(String alanAcronymLib) {
        this.alanAcronymLib = alanAcronymLib;
    }

    public String getAlanLanguageLib() {
        return alanLanguageLib;
    }

    public void setAlanLanguageLib(String alanLanguageLib) {
        this.alanLanguageLib = alanLanguageLib;
    }

    public BigInteger getAlanOrderNum() {
        return alanOrderNum;
    }

    public void setAlanOrderNum(BigInteger alanOrderNum) {
        this.alanOrderNum = alanOrderNum;
    }

    @XmlTransient
    public Collection<TAnnexes> getTAnnexesCollection() {
        return tAnnexesCollection;
    }

    public void setTAnnexesCollection(Collection<TAnnexes> tAnnexesCollection) {
        this.tAnnexesCollection = tAnnexesCollection;
    }

    @XmlTransient
    public Collection<TZipArchives> getTZipArchivesCollection() {
        return tZipArchivesCollection;
    }

    public void setTZipArchivesCollection(Collection<TZipArchives> tZipArchivesCollection) {
        this.tZipArchivesCollection = tZipArchivesCollection;
    }

    @XmlTransient
    public Collection<TDocumentlangsAnnexlangs> getTDocumentlangsAnnexlangsCollection() {
        return tDocumentlangsAnnexlangsCollection;
    }

    public void setTDocumentlangsAnnexlangsCollection(Collection<TDocumentlangsAnnexlangs> tDocumentlangsAnnexlangsCollection) {
        this.tDocumentlangsAnnexlangsCollection = tDocumentlangsAnnexlangsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alanNsq != null ? alanNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAnnexLanguages)) {
            return false;
        }
        TAnnexLanguages other = (TAnnexLanguages) object;
        if ((this.alanNsq == null && other.alanNsq != null) || (this.alanNsq != null && !this.alanNsq.equals(other.alanNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TAnnexLanguages[ alanNsq=" + alanNsq + " ]";
    }
    
}
