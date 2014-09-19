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
@Table(name = "T_ZIP_ARCHIVES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TZipArchives.findAll", query = "SELECT t FROM TZipArchives t"),
    @NamedQuery(name = "TZipArchives.findByZipaDtypeLib", query = "SELECT t FROM TZipArchives t WHERE t.zipaDtypeLib = :zipaDtypeLib"),
    @NamedQuery(name = "TZipArchives.findByZipaNsq", query = "SELECT t FROM TZipArchives t WHERE t.zipaNsq = :zipaNsq"),
    @NamedQuery(name = "TZipArchives.findByZipaFileNameLib", query = "SELECT t FROM TZipArchives t WHERE t.zipaFileNameLib = :zipaFileNameLib"),
    @NamedQuery(name = "TZipArchives.findByZipaIsPublicNum", query = "SELECT t FROM TZipArchives t WHERE t.zipaIsPublicNum = :zipaIsPublicNum")})
public class TZipArchives implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "ZIPA_DTYPE_LIB")
    private String zipaDtypeLib;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZIPA_NSQ")
    private BigDecimal zipaNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ZIPA_FILE_NAME_LIB")
    private String zipaFileNameLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZIPA_IS_PUBLIC_NUM")
    private short zipaIsPublicNum;
    @JoinColumn(name = "ZIPA_ALAN_NSQ", referencedColumnName = "ALAN_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TAnnexLanguages zipaAlanNsq;
    @JoinColumn(name = "ZIPA_BINR_NSQ", referencedColumnName = "BINR_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TBinaryContents zipaBinrNsq;
    @JoinColumn(name = "ZIPA_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TChapterTitles zipaChtlNsq;
    @JoinColumn(name = "ZIPA_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocumentLanguages zipaDlanNsq;
    @JoinColumn(name = "ZIPA_DSTR_NSQ", referencedColumnName = "DSTR_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocumentStructures zipaDstrNsq;
    @JoinColumn(name = "ZIPA_VERS_NSQ", referencedColumnName = "VERS_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TVersions zipaVersNsq;

    public TZipArchives() {
    }

    public TZipArchives(BigDecimal zipaNsq) {
        this.zipaNsq = zipaNsq;
    }

    public TZipArchives(BigDecimal zipaNsq, String zipaDtypeLib, String zipaFileNameLib, short zipaIsPublicNum) {
        this.zipaNsq = zipaNsq;
        this.zipaDtypeLib = zipaDtypeLib;
        this.zipaFileNameLib = zipaFileNameLib;
        this.zipaIsPublicNum = zipaIsPublicNum;
    }

    public String getZipaDtypeLib() {
        return zipaDtypeLib;
    }

    public void setZipaDtypeLib(String zipaDtypeLib) {
        this.zipaDtypeLib = zipaDtypeLib;
    }

    public BigDecimal getZipaNsq() {
        return zipaNsq;
    }

    public void setZipaNsq(BigDecimal zipaNsq) {
        this.zipaNsq = zipaNsq;
    }

    public String getZipaFileNameLib() {
        return zipaFileNameLib;
    }

    public void setZipaFileNameLib(String zipaFileNameLib) {
        this.zipaFileNameLib = zipaFileNameLib;
    }

    public short getZipaIsPublicNum() {
        return zipaIsPublicNum;
    }

    public void setZipaIsPublicNum(short zipaIsPublicNum) {
        this.zipaIsPublicNum = zipaIsPublicNum;
    }

    public TAnnexLanguages getZipaAlanNsq() {
        return zipaAlanNsq;
    }

    public void setZipaAlanNsq(TAnnexLanguages zipaAlanNsq) {
        this.zipaAlanNsq = zipaAlanNsq;
    }

    public TBinaryContents getZipaBinrNsq() {
        return zipaBinrNsq;
    }

    public void setZipaBinrNsq(TBinaryContents zipaBinrNsq) {
        this.zipaBinrNsq = zipaBinrNsq;
    }

    public TChapterTitles getZipaChtlNsq() {
        return zipaChtlNsq;
    }

    public void setZipaChtlNsq(TChapterTitles zipaChtlNsq) {
        this.zipaChtlNsq = zipaChtlNsq;
    }

    public TDocumentLanguages getZipaDlanNsq() {
        return zipaDlanNsq;
    }

    public void setZipaDlanNsq(TDocumentLanguages zipaDlanNsq) {
        this.zipaDlanNsq = zipaDlanNsq;
    }

    public TDocumentStructures getZipaDstrNsq() {
        return zipaDstrNsq;
    }

    public void setZipaDstrNsq(TDocumentStructures zipaDstrNsq) {
        this.zipaDstrNsq = zipaDstrNsq;
    }

    public TVersions getZipaVersNsq() {
        return zipaVersNsq;
    }

    public void setZipaVersNsq(TVersions zipaVersNsq) {
        this.zipaVersNsq = zipaVersNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zipaNsq != null ? zipaNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TZipArchives)) {
            return false;
        }
        TZipArchives other = (TZipArchives) object;
        if ((this.zipaNsq == null && other.zipaNsq != null) || (this.zipaNsq != null && !this.zipaNsq.equals(other.zipaNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TZipArchives[ zipaNsq=" + zipaNsq + " ]";
    }
    
}
