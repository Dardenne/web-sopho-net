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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "T_CHAPTER_TITLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TChapterTitles.findAll", query = "SELECT t FROM TChapterTitles t"),
    @NamedQuery(name = "TChapterTitles.findByChtlNsq", query = "SELECT t FROM TChapterTitles t WHERE t.chtlNsq = :chtlNsq"),
    @NamedQuery(name = "TChapterTitles.findByChtlChapterTitleLib", query = "SELECT t FROM TChapterTitles t WHERE t.chtlChapterTitleLib = :chtlChapterTitleLib"),
    @NamedQuery(name = "TChapterTitles.findByChtlChapterOrderNum", query = "SELECT t FROM TChapterTitles t WHERE t.chtlChapterOrderNum = :chtlChapterOrderNum"),
    @NamedQuery(name = "TChapterTitles.findByChtlIsPublicNum", query = "SELECT t FROM TChapterTitles t WHERE t.chtlIsPublicNum = :chtlIsPublicNum"),
    @NamedQuery(name = "TChapterTitles.findByChtlRequestedDeletionNum", query = "SELECT t FROM TChapterTitles t WHERE t.chtlRequestedDeletionNum = :chtlRequestedDeletionNum"),
    @NamedQuery(name = "TChapterTitles.findByChtlDeletionMsgLib", query = "SELECT t FROM TChapterTitles t WHERE t.chtlDeletionMsgLib = :chtlDeletionMsgLib"),
    @NamedQuery(name = "TChapterTitles.findByChtlScopeIdLib", query = "SELECT t FROM TChapterTitles t WHERE t.chtlScopeIdLib = :chtlScopeIdLib")})
public class TChapterTitles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHTL_NSQ")
    private BigDecimal chtlNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHTL_CHAPTER_TITLE_LIB")
    private String chtlChapterTitleLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHTL_CHAPTER_ORDER_NUM")
    private long chtlChapterOrderNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHTL_IS_PUBLIC_NUM")
    private short chtlIsPublicNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHTL_REQUESTED_DELETION_NUM")
    private short chtlRequestedDeletionNum;
    @Size(max = 1000)
    @Column(name = "CHTL_DELETION_MSG_LIB")
    private String chtlDeletionMsgLib;
    @Size(max = 80)
    @Column(name = "CHTL_SCOPE_ID_LIB")
    private String chtlScopeIdLib;
    @JoinTable(name = "T_CHAPTER_RESPONSIBLE_UNITS", joinColumns = {
        @JoinColumn(name = "CHRU_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")}, inverseJoinColumns = {
        @JoinColumn(name = "CHRU_UNIT_NSQ", referencedColumnName = "UNIT_NSQ")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<TUnits> tUnitsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chdtChtlNsq", fetch = FetchType.LAZY)
    private Collection<TChapterDescriptionsTrans> tChapterDescriptionsTransCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anexChtlNsq", fetch = FetchType.LAZY)
    private Collection<TAnnexes> tAnnexesCollection;
    @OneToMany(mappedBy = "chcoChtlNsq", fetch = FetchType.LAZY)
    private Collection<TChapterContents> tChapterContentsCollection;
    @OneToMany(mappedBy = "chtlSourceChtlNsq", fetch = FetchType.LAZY)
    private Collection<TChapterTitles> tChapterTitlesCollection;
    @JoinColumn(name = "CHTL_SOURCE_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TChapterTitles chtlSourceChtlNsq;
    @JoinColumn(name = "CHTL_DSTR_NSQ", referencedColumnName = "DSTR_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentStructures chtlDstrNsq;
    @OneToMany(mappedBy = "zipaChtlNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;

    public TChapterTitles() {
    }

    public TChapterTitles(BigDecimal chtlNsq) {
        this.chtlNsq = chtlNsq;
    }

    public TChapterTitles(BigDecimal chtlNsq, String chtlChapterTitleLib, long chtlChapterOrderNum, short chtlIsPublicNum, short chtlRequestedDeletionNum) {
        this.chtlNsq = chtlNsq;
        this.chtlChapterTitleLib = chtlChapterTitleLib;
        this.chtlChapterOrderNum = chtlChapterOrderNum;
        this.chtlIsPublicNum = chtlIsPublicNum;
        this.chtlRequestedDeletionNum = chtlRequestedDeletionNum;
    }

    public BigDecimal getChtlNsq() {
        return chtlNsq;
    }

    public void setChtlNsq(BigDecimal chtlNsq) {
        this.chtlNsq = chtlNsq;
    }

    public String getChtlChapterTitleLib() {
        return chtlChapterTitleLib;
    }

    public void setChtlChapterTitleLib(String chtlChapterTitleLib) {
        this.chtlChapterTitleLib = chtlChapterTitleLib;
    }

    public long getChtlChapterOrderNum() {
        return chtlChapterOrderNum;
    }

    public void setChtlChapterOrderNum(long chtlChapterOrderNum) {
        this.chtlChapterOrderNum = chtlChapterOrderNum;
    }

    public short getChtlIsPublicNum() {
        return chtlIsPublicNum;
    }

    public void setChtlIsPublicNum(short chtlIsPublicNum) {
        this.chtlIsPublicNum = chtlIsPublicNum;
    }

    public short getChtlRequestedDeletionNum() {
        return chtlRequestedDeletionNum;
    }

    public void setChtlRequestedDeletionNum(short chtlRequestedDeletionNum) {
        this.chtlRequestedDeletionNum = chtlRequestedDeletionNum;
    }

    public String getChtlDeletionMsgLib() {
        return chtlDeletionMsgLib;
    }

    public void setChtlDeletionMsgLib(String chtlDeletionMsgLib) {
        this.chtlDeletionMsgLib = chtlDeletionMsgLib;
    }

    public String getChtlScopeIdLib() {
        return chtlScopeIdLib;
    }

    public void setChtlScopeIdLib(String chtlScopeIdLib) {
        this.chtlScopeIdLib = chtlScopeIdLib;
    }

    @XmlTransient
    public Collection<TUnits> getTUnitsCollection() {
        return tUnitsCollection;
    }

    public void setTUnitsCollection(Collection<TUnits> tUnitsCollection) {
        this.tUnitsCollection = tUnitsCollection;
    }

    @XmlTransient
    public Collection<TChapterDescriptionsTrans> getTChapterDescriptionsTransCollection() {
        return tChapterDescriptionsTransCollection;
    }

    public void setTChapterDescriptionsTransCollection(Collection<TChapterDescriptionsTrans> tChapterDescriptionsTransCollection) {
        this.tChapterDescriptionsTransCollection = tChapterDescriptionsTransCollection;
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
    public Collection<TChapterTitles> getTChapterTitlesCollection() {
        return tChapterTitlesCollection;
    }

    public void setTChapterTitlesCollection(Collection<TChapterTitles> tChapterTitlesCollection) {
        this.tChapterTitlesCollection = tChapterTitlesCollection;
    }

    public TChapterTitles getChtlSourceChtlNsq() {
        return chtlSourceChtlNsq;
    }

    public void setChtlSourceChtlNsq(TChapterTitles chtlSourceChtlNsq) {
        this.chtlSourceChtlNsq = chtlSourceChtlNsq;
    }

    public TDocumentStructures getChtlDstrNsq() {
        return chtlDstrNsq;
    }

    public void setChtlDstrNsq(TDocumentStructures chtlDstrNsq) {
        this.chtlDstrNsq = chtlDstrNsq;
    }

    @XmlTransient
    public Collection<TZipArchives> getTZipArchivesCollection() {
        return tZipArchivesCollection;
    }

    public void setTZipArchivesCollection(Collection<TZipArchives> tZipArchivesCollection) {
        this.tZipArchivesCollection = tZipArchivesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chtlNsq != null ? chtlNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TChapterTitles)) {
            return false;
        }
        TChapterTitles other = (TChapterTitles) object;
        if ((this.chtlNsq == null && other.chtlNsq != null) || (this.chtlNsq != null && !this.chtlNsq.equals(other.chtlNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TChapterTitles[ chtlNsq=" + chtlNsq + " ]";
    }
    
}
