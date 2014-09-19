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
@Table(name = "T_DOCUMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocuments.findAll", query = "SELECT t FROM TDocuments t"),
    @NamedQuery(name = "TDocuments.findByDocuNsq", query = "SELECT t FROM TDocuments t WHERE t.docuNsq = :docuNsq"),
    @NamedQuery(name = "TDocuments.findByDocuMessageLib", query = "SELECT t FROM TDocuments t WHERE t.docuMessageLib = :docuMessageLib"),
    @NamedQuery(name = "TDocuments.findByDocuVersionMessageLib", query = "SELECT t FROM TDocuments t WHERE t.docuVersionMessageLib = :docuVersionMessageLib")})
public class TDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCU_NSQ")
    private BigDecimal docuNsq;
    @Size(max = 1000)
    @Column(name = "DOCU_MESSAGE_LIB")
    private String docuMessageLib;
    @Size(max = 1000)
    @Column(name = "DOCU_VERSION_MESSAGE_LIB")
    private String docuVersionMessageLib;
    @OneToMany(mappedBy = "chcoDocuNsq", fetch = FetchType.LAZY)
    private Collection<TChapterContents> tChapterContentsCollection;
    @JoinColumn(name = "DOCU_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages docuDlanNsq;
    @JoinColumn(name = "DOCU_DSTR_NSQ", referencedColumnName = "DSTR_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentStructures docuDstrNsq;
    @JoinColumn(name = "DOCU_VERS_NSQ", referencedColumnName = "VERS_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TVersions docuVersNsq;
    @OneToMany(mappedBy = "binrDocuNsq", fetch = FetchType.LAZY)
    private Collection<TBinaryContents> tBinaryContentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kywdDocuNsq", fetch = FetchType.LAZY)
    private Collection<TKeywords> tKeywordsCollection;
    @OneToMany(mappedBy = "pdocDocuNsq", fetch = FetchType.LAZY)
    private Collection<TPrintableDocuments> tPrintableDocumentsCollection;

    public TDocuments() {
    }

    public TDocuments(BigDecimal docuNsq) {
        this.docuNsq = docuNsq;
    }

    public BigDecimal getDocuNsq() {
        return docuNsq;
    }

    public void setDocuNsq(BigDecimal docuNsq) {
        this.docuNsq = docuNsq;
    }

    public String getDocuMessageLib() {
        return docuMessageLib;
    }

    public void setDocuMessageLib(String docuMessageLib) {
        this.docuMessageLib = docuMessageLib;
    }

    public String getDocuVersionMessageLib() {
        return docuVersionMessageLib;
    }

    public void setDocuVersionMessageLib(String docuVersionMessageLib) {
        this.docuVersionMessageLib = docuVersionMessageLib;
    }

    @XmlTransient
    public Collection<TChapterContents> getTChapterContentsCollection() {
        return tChapterContentsCollection;
    }

    public void setTChapterContentsCollection(Collection<TChapterContents> tChapterContentsCollection) {
        this.tChapterContentsCollection = tChapterContentsCollection;
    }

    public TDocumentLanguages getDocuDlanNsq() {
        return docuDlanNsq;
    }

    public void setDocuDlanNsq(TDocumentLanguages docuDlanNsq) {
        this.docuDlanNsq = docuDlanNsq;
    }

    public TDocumentStructures getDocuDstrNsq() {
        return docuDstrNsq;
    }

    public void setDocuDstrNsq(TDocumentStructures docuDstrNsq) {
        this.docuDstrNsq = docuDstrNsq;
    }

    public TVersions getDocuVersNsq() {
        return docuVersNsq;
    }

    public void setDocuVersNsq(TVersions docuVersNsq) {
        this.docuVersNsq = docuVersNsq;
    }

    @XmlTransient
    public Collection<TBinaryContents> getTBinaryContentsCollection() {
        return tBinaryContentsCollection;
    }

    public void setTBinaryContentsCollection(Collection<TBinaryContents> tBinaryContentsCollection) {
        this.tBinaryContentsCollection = tBinaryContentsCollection;
    }

    @XmlTransient
    public Collection<TKeywords> getTKeywordsCollection() {
        return tKeywordsCollection;
    }

    public void setTKeywordsCollection(Collection<TKeywords> tKeywordsCollection) {
        this.tKeywordsCollection = tKeywordsCollection;
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
        hash += (docuNsq != null ? docuNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocuments)) {
            return false;
        }
        TDocuments other = (TDocuments) object;
        if ((this.docuNsq == null && other.docuNsq != null) || (this.docuNsq != null && !this.docuNsq.equals(other.docuNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TDocuments[ docuNsq=" + docuNsq + " ]";
    }
    
}
