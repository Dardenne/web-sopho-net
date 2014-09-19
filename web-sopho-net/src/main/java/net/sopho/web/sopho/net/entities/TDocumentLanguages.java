/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

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
@Table(name = "T_DOCUMENT_LANGUAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocumentLanguages.findAll", query = "SELECT t FROM TDocumentLanguages t"),
    @NamedQuery(name = "TDocumentLanguages.findByDlanNsq", query = "SELECT t FROM TDocumentLanguages t WHERE t.dlanNsq = :dlanNsq"),
    @NamedQuery(name = "TDocumentLanguages.findByDlanAcronymLib", query = "SELECT t FROM TDocumentLanguages t WHERE t.dlanAcronymLib = :dlanAcronymLib"),
    @NamedQuery(name = "TDocumentLanguages.findByDlanLanguageLib", query = "SELECT t FROM TDocumentLanguages t WHERE t.dlanLanguageLib = :dlanLanguageLib"),
    @NamedQuery(name = "TDocumentLanguages.findByDlanIsLeadingNum", query = "SELECT t FROM TDocumentLanguages t WHERE t.dlanIsLeadingNum = :dlanIsLeadingNum"),
    @NamedQuery(name = "TDocumentLanguages.findByDlanOrderNum", query = "SELECT t FROM TDocumentLanguages t WHERE t.dlanOrderNum = :dlanOrderNum")})
public class TDocumentLanguages implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DLAN_NSQ")
    private BigDecimal dlanNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DLAN_ACRONYM_LIB")
    private String dlanAcronymLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DLAN_LANGUAGE_LIB")
    private String dlanLanguageLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DLAN_IS_LEADING_NUM")
    private short dlanIsLeadingNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DLAN_ORDER_NUM")
    private BigInteger dlanOrderNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chdtDlanNsq", fetch = FetchType.LAZY)
    private Collection<TChapterDescriptionsTrans> tChapterDescriptionsTransCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docuDlanNsq", fetch = FetchType.LAZY)
    private Collection<TDocuments> tDocumentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elnkDlanNsq", fetch = FetchType.LAZY)
    private Collection<TExternalLinks> tExternalLinksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vercDlanNsq", fetch = FetchType.LAZY)
    private Collection<TVersionComments> tVersionCommentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kytrDlanNsq", fetch = FetchType.LAZY)
    private Collection<TKeywordTranslations> tKeywordTranslationsCollection;
    @OneToMany(mappedBy = "zipaDlanNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "labuDlanNsq", fetch = FetchType.LAZY)
    private Collection<TLabelBundles> tLabelBundlesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doanDlanNsq", fetch = FetchType.LAZY)
    private Collection<TDocumentlangsAnnexlangs> tDocumentlangsAnnexlangsCollection;
    @OneToMany(mappedBy = "pdocDlanNsq", fetch = FetchType.LAZY)
    private Collection<TPrintableDocuments> tPrintableDocumentsCollection;

    public TDocumentLanguages() {
    }

    public TDocumentLanguages(BigDecimal dlanNsq) {
        this.dlanNsq = dlanNsq;
    }

    public TDocumentLanguages(BigDecimal dlanNsq, String dlanAcronymLib, String dlanLanguageLib, short dlanIsLeadingNum, BigInteger dlanOrderNum) {
        this.dlanNsq = dlanNsq;
        this.dlanAcronymLib = dlanAcronymLib;
        this.dlanLanguageLib = dlanLanguageLib;
        this.dlanIsLeadingNum = dlanIsLeadingNum;
        this.dlanOrderNum = dlanOrderNum;
    }

    public BigDecimal getDlanNsq() {
        return dlanNsq;
    }

    public void setDlanNsq(BigDecimal dlanNsq) {
        this.dlanNsq = dlanNsq;
    }

    public String getDlanAcronymLib() {
        return dlanAcronymLib;
    }

    public void setDlanAcronymLib(String dlanAcronymLib) {
        this.dlanAcronymLib = dlanAcronymLib;
    }

    public String getDlanLanguageLib() {
        return dlanLanguageLib;
    }

    public void setDlanLanguageLib(String dlanLanguageLib) {
        this.dlanLanguageLib = dlanLanguageLib;
    }

    public short getDlanIsLeadingNum() {
        return dlanIsLeadingNum;
    }

    public void setDlanIsLeadingNum(short dlanIsLeadingNum) {
        this.dlanIsLeadingNum = dlanIsLeadingNum;
    }

    public BigInteger getDlanOrderNum() {
        return dlanOrderNum;
    }

    public void setDlanOrderNum(BigInteger dlanOrderNum) {
        this.dlanOrderNum = dlanOrderNum;
    }

    @XmlTransient
    public Collection<TChapterDescriptionsTrans> getTChapterDescriptionsTransCollection() {
        return tChapterDescriptionsTransCollection;
    }

    public void setTChapterDescriptionsTransCollection(Collection<TChapterDescriptionsTrans> tChapterDescriptionsTransCollection) {
        this.tChapterDescriptionsTransCollection = tChapterDescriptionsTransCollection;
    }

    @XmlTransient
    public Collection<TDocuments> getTDocumentsCollection() {
        return tDocumentsCollection;
    }

    public void setTDocumentsCollection(Collection<TDocuments> tDocumentsCollection) {
        this.tDocumentsCollection = tDocumentsCollection;
    }

    @XmlTransient
    public Collection<TExternalLinks> getTExternalLinksCollection() {
        return tExternalLinksCollection;
    }

    public void setTExternalLinksCollection(Collection<TExternalLinks> tExternalLinksCollection) {
        this.tExternalLinksCollection = tExternalLinksCollection;
    }

    @XmlTransient
    public Collection<TVersionComments> getTVersionCommentsCollection() {
        return tVersionCommentsCollection;
    }

    public void setTVersionCommentsCollection(Collection<TVersionComments> tVersionCommentsCollection) {
        this.tVersionCommentsCollection = tVersionCommentsCollection;
    }

    @XmlTransient
    public Collection<TKeywordTranslations> getTKeywordTranslationsCollection() {
        return tKeywordTranslationsCollection;
    }

    public void setTKeywordTranslationsCollection(Collection<TKeywordTranslations> tKeywordTranslationsCollection) {
        this.tKeywordTranslationsCollection = tKeywordTranslationsCollection;
    }

    @XmlTransient
    public Collection<TZipArchives> getTZipArchivesCollection() {
        return tZipArchivesCollection;
    }

    public void setTZipArchivesCollection(Collection<TZipArchives> tZipArchivesCollection) {
        this.tZipArchivesCollection = tZipArchivesCollection;
    }

    @XmlTransient
    public Collection<TLabelBundles> getTLabelBundlesCollection() {
        return tLabelBundlesCollection;
    }

    public void setTLabelBundlesCollection(Collection<TLabelBundles> tLabelBundlesCollection) {
        this.tLabelBundlesCollection = tLabelBundlesCollection;
    }

    @XmlTransient
    public Collection<TDocumentlangsAnnexlangs> getTDocumentlangsAnnexlangsCollection() {
        return tDocumentlangsAnnexlangsCollection;
    }

    public void setTDocumentlangsAnnexlangsCollection(Collection<TDocumentlangsAnnexlangs> tDocumentlangsAnnexlangsCollection) {
        this.tDocumentlangsAnnexlangsCollection = tDocumentlangsAnnexlangsCollection;
    }

    @XmlTransient
    public Collection<TPrintableDocuments> getTPrintableDocumentsCollection() {
        return tPrintableDocumentsCollection;
    }

    public void setTPrintableDocumentsCollection(Collection<TPrintableDocuments> tPrintableDocumentsCollection) {
        this.tPrintableDocumentsCollection = tPrintableDocumentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dlanNsq != null ? dlanNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocumentLanguages)) {
            return false;
        }
        TDocumentLanguages other = (TDocumentLanguages) object;
        if ((this.dlanNsq == null && other.dlanNsq != null) || (this.dlanNsq != null && !this.dlanNsq.equals(other.dlanNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TDocumentLanguages[ dlanNsq=" + dlanNsq + " ]";
    }
    
}
