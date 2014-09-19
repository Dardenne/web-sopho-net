/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_ANNEXES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAnnexes.findAll", query = "SELECT t FROM TAnnexes t"),
    @NamedQuery(name = "TAnnexes.findByAnexNsq", query = "SELECT t FROM TAnnexes t WHERE t.anexNsq = :anexNsq"),
    @NamedQuery(name = "TAnnexes.findByAnexDescriptionLib", query = "SELECT t FROM TAnnexes t WHERE t.anexDescriptionLib = :anexDescriptionLib"),
    @NamedQuery(name = "TAnnexes.findByAnexFileNameLib", query = "SELECT t FROM TAnnexes t WHERE t.anexFileNameLib = :anexFileNameLib"),
    @NamedQuery(name = "TAnnexes.findByAnexMimeTypeLib", query = "SELECT t FROM TAnnexes t WHERE t.anexMimeTypeLib = :anexMimeTypeLib"),
    @NamedQuery(name = "TAnnexes.findByAnexNameLib", query = "SELECT t FROM TAnnexes t WHERE t.anexNameLib = :anexNameLib"),
    @NamedQuery(name = "TAnnexes.findByAnexUploadDat", query = "SELECT t FROM TAnnexes t WHERE t.anexUploadDat = :anexUploadDat"),
    @NamedQuery(name = "TAnnexes.findByAnexHasSourceNum", query = "SELECT t FROM TAnnexes t WHERE t.anexHasSourceNum = :anexHasSourceNum")})
public class TAnnexes implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANEX_NSQ")
    private BigDecimal anexNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ANEX_DESCRIPTION_LIB")
    private String anexDescriptionLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ANEX_FILE_NAME_LIB")
    private String anexFileNameLib;
    @Size(max = 255)
    @Column(name = "ANEX_MIME_TYPE_LIB")
    private String anexMimeTypeLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ANEX_NAME_LIB")
    private String anexNameLib;
    @Column(name = "ANEX_UPLOAD_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anexUploadDat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANEX_HAS_SOURCE_NUM")
    private short anexHasSourceNum;
    @JoinColumn(name = "ANEX_AGRP_NSQ", referencedColumnName = "AGRP_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TAnnexGroups anexAgrpNsq;
    @JoinColumn(name = "ANEX_ALAN_NSQ", referencedColumnName = "ALAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TAnnexLanguages anexAlanNsq;
    @JoinColumn(name = "ANEX_BINR_NSQ", referencedColumnName = "BINR_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TBinaryContents anexBinrNsq;
    @JoinColumn(name = "ANEX_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TChapterTitles anexChtlNsq;

    public TAnnexes() {
    }

    public TAnnexes(BigDecimal anexNsq) {
        this.anexNsq = anexNsq;
    }

    public TAnnexes(BigDecimal anexNsq, String anexDescriptionLib, String anexFileNameLib, String anexNameLib, short anexHasSourceNum) {
        this.anexNsq = anexNsq;
        this.anexDescriptionLib = anexDescriptionLib;
        this.anexFileNameLib = anexFileNameLib;
        this.anexNameLib = anexNameLib;
        this.anexHasSourceNum = anexHasSourceNum;
    }

    public BigDecimal getAnexNsq() {
        return anexNsq;
    }

    public void setAnexNsq(BigDecimal anexNsq) {
        this.anexNsq = anexNsq;
    }

    public String getAnexDescriptionLib() {
        return anexDescriptionLib;
    }

    public void setAnexDescriptionLib(String anexDescriptionLib) {
        this.anexDescriptionLib = anexDescriptionLib;
    }

    public String getAnexFileNameLib() {
        return anexFileNameLib;
    }

    public void setAnexFileNameLib(String anexFileNameLib) {
        this.anexFileNameLib = anexFileNameLib;
    }

    public String getAnexMimeTypeLib() {
        return anexMimeTypeLib;
    }

    public void setAnexMimeTypeLib(String anexMimeTypeLib) {
        this.anexMimeTypeLib = anexMimeTypeLib;
    }

    public String getAnexNameLib() {
        return anexNameLib;
    }

    public void setAnexNameLib(String anexNameLib) {
        this.anexNameLib = anexNameLib;
    }

    public Date getAnexUploadDat() {
        return anexUploadDat;
    }

    public void setAnexUploadDat(Date anexUploadDat) {
        this.anexUploadDat = anexUploadDat;
    }

    public short getAnexHasSourceNum() {
        return anexHasSourceNum;
    }

    public void setAnexHasSourceNum(short anexHasSourceNum) {
        this.anexHasSourceNum = anexHasSourceNum;
    }

    public TAnnexGroups getAnexAgrpNsq() {
        return anexAgrpNsq;
    }

    public void setAnexAgrpNsq(TAnnexGroups anexAgrpNsq) {
        this.anexAgrpNsq = anexAgrpNsq;
    }

    public TAnnexLanguages getAnexAlanNsq() {
        return anexAlanNsq;
    }

    public void setAnexAlanNsq(TAnnexLanguages anexAlanNsq) {
        this.anexAlanNsq = anexAlanNsq;
    }

    public TBinaryContents getAnexBinrNsq() {
        return anexBinrNsq;
    }

    public void setAnexBinrNsq(TBinaryContents anexBinrNsq) {
        this.anexBinrNsq = anexBinrNsq;
    }

    public TChapterTitles getAnexChtlNsq() {
        return anexChtlNsq;
    }

    public void setAnexChtlNsq(TChapterTitles anexChtlNsq) {
        this.anexChtlNsq = anexChtlNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anexNsq != null ? anexNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAnnexes)) {
            return false;
        }
        TAnnexes other = (TAnnexes) object;
        if ((this.anexNsq == null && other.anexNsq != null) || (this.anexNsq != null && !this.anexNsq.equals(other.anexNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TAnnexes[ anexNsq=" + anexNsq + " ]";
    }
    
}
