/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

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
import javax.persistence.Lob;
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
@Table(name = "T_BINARY_CONTENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBinaryContents.findAll", query = "SELECT t FROM TBinaryContents t"),
    @NamedQuery(name = "TBinaryContents.findByBinrNsq", query = "SELECT t FROM TBinaryContents t WHERE t.binrNsq = :binrNsq"),
    @NamedQuery(name = "TBinaryContents.findByBinrNameLib", query = "SELECT t FROM TBinaryContents t WHERE t.binrNameLib = :binrNameLib")})
public class TBinaryContents implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BINR_NSQ")
    private BigDecimal binrNsq;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "BINR_CONTENT_BLB")
    private Serializable binrContentBlb;
    @Size(max = 255)
    @Column(name = "BINR_NAME_LIB")
    private String binrNameLib;
    @OneToMany(mappedBy = "anexBinrNsq", fetch = FetchType.LAZY)
    private Collection<TAnnexes> tAnnexesCollection;
    @OneToMany(mappedBy = "chcoBinrNsqXmlContent", fetch = FetchType.LAZY)
    private Collection<TChapterContents> tChapterContentsCollection;
    @OneToMany(mappedBy = "chcoBinrNsqPdfFile", fetch = FetchType.LAZY)
    private Collection<TChapterContents> tChapterContentsCollection1;
    @JoinColumn(name = "BINR_DOCU_NSQ", referencedColumnName = "DOCU_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocuments binrDocuNsq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chimBinrNsq", fetch = FetchType.LAZY)
    private Collection<TChapterImages> tChapterImagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipaBinrNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pdocBinrNsq", fetch = FetchType.LAZY)
    private Collection<TPrintableDocuments> tPrintableDocumentsCollection;

    public TBinaryContents() {
    }

    public TBinaryContents(BigDecimal binrNsq) {
        this.binrNsq = binrNsq;
    }

    public TBinaryContents(BigDecimal binrNsq, Serializable binrContentBlb) {
        this.binrNsq = binrNsq;
        this.binrContentBlb = binrContentBlb;
    }

    public BigDecimal getBinrNsq() {
        return binrNsq;
    }

    public void setBinrNsq(BigDecimal binrNsq) {
        this.binrNsq = binrNsq;
    }

    public Serializable getBinrContentBlb() {
        return binrContentBlb;
    }

    public void setBinrContentBlb(Serializable binrContentBlb) {
        this.binrContentBlb = binrContentBlb;
    }

    public String getBinrNameLib() {
        return binrNameLib;
    }

    public void setBinrNameLib(String binrNameLib) {
        this.binrNameLib = binrNameLib;
    }

    @XmlTransient
    public Collection<TAnnexes> getTAnnexesCollection() {
        return tAnnexesCollection;
    }

    public void setTAnnexesCollection(Collection<TAnnexes> tAnnexesCollection) {
        this.tAnnexesCollection = tAnnexesCollection;
    }

    @XmlTransient
    public Collection<TChapterContents> getTChapterContentsCollection() {
        return tChapterContentsCollection;
    }

    public void setTChapterContentsCollection(Collection<TChapterContents> tChapterContentsCollection) {
        this.tChapterContentsCollection = tChapterContentsCollection;
    }

    @XmlTransient
    public Collection<TChapterContents> getTChapterContentsCollection1() {
        return tChapterContentsCollection1;
    }

    public void setTChapterContentsCollection1(Collection<TChapterContents> tChapterContentsCollection1) {
        this.tChapterContentsCollection1 = tChapterContentsCollection1;
    }

    public TDocuments getBinrDocuNsq() {
        return binrDocuNsq;
    }

    public void setBinrDocuNsq(TDocuments binrDocuNsq) {
        this.binrDocuNsq = binrDocuNsq;
    }

    @XmlTransient
    public Collection<TChapterImages> getTChapterImagesCollection() {
        return tChapterImagesCollection;
    }

    public void setTChapterImagesCollection(Collection<TChapterImages> tChapterImagesCollection) {
        this.tChapterImagesCollection = tChapterImagesCollection;
    }

    @XmlTransient
    public Collection<TZipArchives> getTZipArchivesCollection() {
        return tZipArchivesCollection;
    }

    public void setTZipArchivesCollection(Collection<TZipArchives> tZipArchivesCollection) {
        this.tZipArchivesCollection = tZipArchivesCollection;
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
        hash += (binrNsq != null ? binrNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBinaryContents)) {
            return false;
        }
        TBinaryContents other = (TBinaryContents) object;
        if ((this.binrNsq == null && other.binrNsq != null) || (this.binrNsq != null && !this.binrNsq.equals(other.binrNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TBinaryContents[ binrNsq=" + binrNsq + " ]";
    }
    
}
