/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_VERSIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TVersions.findAll", query = "SELECT t FROM TVersions t"),
    @NamedQuery(name = "TVersions.findByVersDtypeLib", query = "SELECT t FROM TVersions t WHERE t.versDtypeLib = :versDtypeLib"),
    @NamedQuery(name = "TVersions.findByVersNsq", query = "SELECT t FROM TVersions t WHERE t.versNsq = :versNsq"),
    @NamedQuery(name = "TVersions.findByVersValidFromDat", query = "SELECT t FROM TVersions t WHERE t.versValidFromDat = :versValidFromDat"),
    @NamedQuery(name = "TVersions.findByVersVersionNum", query = "SELECT t FROM TVersions t WHERE t.versVersionNum = :versVersionNum"),
    @NamedQuery(name = "TVersions.findByVersHiddenNum", query = "SELECT t FROM TVersions t WHERE t.versHiddenNum = :versHiddenNum"),
    @NamedQuery(name = "TVersions.findByVersValidToDat", query = "SELECT t FROM TVersions t WHERE t.versValidToDat = :versValidToDat")})
public class TVersions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "VERS_DTYPE_LIB")
    private String versDtypeLib;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERS_NSQ")
    private BigDecimal versNsq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERS_VALID_FROM_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date versValidFromDat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERS_VERSION_NUM")
    private long versVersionNum;
    @Column(name = "VERS_HIDDEN_NUM")
    private Short versHiddenNum;
    @Column(name = "VERS_VALID_TO_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date versValidToDat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docuVersNsq", fetch = FetchType.LAZY)
    private Collection<TDocuments> tDocumentsCollection;
    @JoinColumn(name = "VERS_MAVN_NSQ", referencedColumnName = "MAVN_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TMajorVersionNumbers versMavnNsq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vercVersNsq", fetch = FetchType.LAZY)
    private Collection<TVersionComments> tVersionCommentsCollection;
    @OneToMany(mappedBy = "zipaVersNsq", fetch = FetchType.LAZY)
    private Collection<TZipArchives> tZipArchivesCollection;
    @OneToMany(mappedBy = "pdocVersNsq", fetch = FetchType.LAZY)
    private Collection<TPrintableDocuments> tPrintableDocumentsCollection;

    public TVersions() {
    }

    public TVersions(BigDecimal versNsq) {
        this.versNsq = versNsq;
    }

    public TVersions(BigDecimal versNsq, String versDtypeLib, Date versValidFromDat, long versVersionNum) {
        this.versNsq = versNsq;
        this.versDtypeLib = versDtypeLib;
        this.versValidFromDat = versValidFromDat;
        this.versVersionNum = versVersionNum;
    }

    public String getVersDtypeLib() {
        return versDtypeLib;
    }

    public void setVersDtypeLib(String versDtypeLib) {
        this.versDtypeLib = versDtypeLib;
    }

    public BigDecimal getVersNsq() {
        return versNsq;
    }

    public void setVersNsq(BigDecimal versNsq) {
        this.versNsq = versNsq;
    }

    public Date getVersValidFromDat() {
        return versValidFromDat;
    }

    public void setVersValidFromDat(Date versValidFromDat) {
        this.versValidFromDat = versValidFromDat;
    }

    public long getVersVersionNum() {
        return versVersionNum;
    }

    public void setVersVersionNum(long versVersionNum) {
        this.versVersionNum = versVersionNum;
    }

    public Short getVersHiddenNum() {
        return versHiddenNum;
    }

    public void setVersHiddenNum(Short versHiddenNum) {
        this.versHiddenNum = versHiddenNum;
    }

    public Date getVersValidToDat() {
        return versValidToDat;
    }

    public void setVersValidToDat(Date versValidToDat) {
        this.versValidToDat = versValidToDat;
    }

    @XmlTransient
    public Collection<TDocuments> getTDocumentsCollection() {
        return tDocumentsCollection;
    }

    public void setTDocumentsCollection(Collection<TDocuments> tDocumentsCollection) {
        this.tDocumentsCollection = tDocumentsCollection;
    }

    public TMajorVersionNumbers getVersMavnNsq() {
        return versMavnNsq;
    }

    public void setVersMavnNsq(TMajorVersionNumbers versMavnNsq) {
        this.versMavnNsq = versMavnNsq;
    }

    @XmlTransient
    public Collection<TVersionComments> getTVersionCommentsCollection() {
        return tVersionCommentsCollection;
    }

    public void setTVersionCommentsCollection(Collection<TVersionComments> tVersionCommentsCollection) {
        this.tVersionCommentsCollection = tVersionCommentsCollection;
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
        hash += (versNsq != null ? versNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TVersions)) {
            return false;
        }
        TVersions other = (TVersions) object;
        if ((this.versNsq == null && other.versNsq != null) || (this.versNsq != null && !this.versNsq.equals(other.versNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TVersions[ versNsq=" + versNsq + " ]";
    }
    
}
