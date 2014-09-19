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
@Table(name = "T_DOCUMENT_STRUCTURES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocumentStructures.findAll", query = "SELECT t FROM TDocumentStructures t"),
    @NamedQuery(name = "TDocumentStructures.findByDstrNsq", query = "SELECT t FROM TDocumentStructures t WHERE t.dstrNsq = :dstrNsq"),
    @NamedQuery(name = "TDocumentStructures.findByDstrStateLib", query = "SELECT t FROM TDocumentStructures t WHERE t.dstrStateLib = :dstrStateLib"),
    @NamedQuery(name = "TDocumentStructures.findByDstrDraftStateLib", query = "SELECT t FROM TDocumentStructures t WHERE t.dstrDraftStateLib = :dstrDraftStateLib"),
    @NamedQuery(name = "TDocumentStructures.findByDstrFreezeStateNum", query = "SELECT t FROM TDocumentStructures t WHERE t.dstrFreezeStateNum = :dstrFreezeStateNum"),
    @NamedQuery(name = "TDocumentStructures.findByDstrVersionIncreaseNum", query = "SELECT t FROM TDocumentStructures t WHERE t.dstrVersionIncreaseNum = :dstrVersionIncreaseNum")})
public class TDocumentStructures implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DSTR_NSQ")
    private BigDecimal dstrNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DSTR_STATE_LIB")
    private String dstrStateLib;
    @Size(max = 25)
    @Column(name = "DSTR_DRAFT_STATE_LIB")
    private String dstrDraftStateLib;
    @Column(name = "DSTR_FREEZE_STATE_NUM")
    private Short dstrFreezeStateNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DSTR_VERSION_INCREASE_NUM")
    private short dstrVersionIncreaseNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docuDstrNsq", fetch = FetchType.LAZY)
    private Collection<TDocuments> tDocumentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chtlDstrNsq", fetch = FetchType.LAZY)
    private Collection<TChapterTitles> tChapterTitlesCollection;
    @OneToMany(mappedBy = "zipaDstrNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;

    public TDocumentStructures() {
    }

    public TDocumentStructures(BigDecimal dstrNsq) {
        this.dstrNsq = dstrNsq;
    }

    public TDocumentStructures(BigDecimal dstrNsq, String dstrStateLib, short dstrVersionIncreaseNum) {
        this.dstrNsq = dstrNsq;
        this.dstrStateLib = dstrStateLib;
        this.dstrVersionIncreaseNum = dstrVersionIncreaseNum;
    }

    public BigDecimal getDstrNsq() {
        return dstrNsq;
    }

    public void setDstrNsq(BigDecimal dstrNsq) {
        this.dstrNsq = dstrNsq;
    }

    public String getDstrStateLib() {
        return dstrStateLib;
    }

    public void setDstrStateLib(String dstrStateLib) {
        this.dstrStateLib = dstrStateLib;
    }

    public String getDstrDraftStateLib() {
        return dstrDraftStateLib;
    }

    public void setDstrDraftStateLib(String dstrDraftStateLib) {
        this.dstrDraftStateLib = dstrDraftStateLib;
    }

    public Short getDstrFreezeStateNum() {
        return dstrFreezeStateNum;
    }

    public void setDstrFreezeStateNum(Short dstrFreezeStateNum) {
        this.dstrFreezeStateNum = dstrFreezeStateNum;
    }

    public short getDstrVersionIncreaseNum() {
        return dstrVersionIncreaseNum;
    }

    public void setDstrVersionIncreaseNum(short dstrVersionIncreaseNum) {
        this.dstrVersionIncreaseNum = dstrVersionIncreaseNum;
    }

    @XmlTransient
    public Collection<TDocuments> getTDocumentsCollection() {
        return tDocumentsCollection;
    }

    public void setTDocumentsCollection(Collection<TDocuments> tDocumentsCollection) {
        this.tDocumentsCollection = tDocumentsCollection;
    }

    @XmlTransient
    public Collection<TChapterTitles> getTChapterTitlesCollection() {
        return tChapterTitlesCollection;
    }

    public void setTChapterTitlesCollection(Collection<TChapterTitles> tChapterTitlesCollection) {
        this.tChapterTitlesCollection = tChapterTitlesCollection;
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
        hash += (dstrNsq != null ? dstrNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocumentStructures)) {
            return false;
        }
        TDocumentStructures other = (TDocumentStructures) object;
        if ((this.dstrNsq == null && other.dstrNsq != null) || (this.dstrNsq != null && !this.dstrNsq.equals(other.dstrNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TDocumentStructures[ dstrNsq=" + dstrNsq + " ]";
    }
    
}
