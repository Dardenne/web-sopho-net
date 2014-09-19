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
@Table(name = "T_PRINTABLE_DOCUMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPrintableDocuments.findAll", query = "SELECT t FROM TPrintableDocuments t"),
    @NamedQuery(name = "TPrintableDocuments.findByPdocDtypeLib", query = "SELECT t FROM TPrintableDocuments t WHERE t.pdocDtypeLib = :pdocDtypeLib"),
    @NamedQuery(name = "TPrintableDocuments.findByPdocNsq", query = "SELECT t FROM TPrintableDocuments t WHERE t.pdocNsq = :pdocNsq"),
    @NamedQuery(name = "TPrintableDocuments.findByPdocFileNameLib", query = "SELECT t FROM TPrintableDocuments t WHERE t.pdocFileNameLib = :pdocFileNameLib"),
    @NamedQuery(name = "TPrintableDocuments.findByPdocIsPublicNum", query = "SELECT t FROM TPrintableDocuments t WHERE t.pdocIsPublicNum = :pdocIsPublicNum")})
public class TPrintableDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "PDOC_DTYPE_LIB")
    private String pdocDtypeLib;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PDOC_NSQ")
    private BigDecimal pdocNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PDOC_FILE_NAME_LIB")
    private String pdocFileNameLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PDOC_IS_PUBLIC_NUM")
    private short pdocIsPublicNum;
    @JoinColumn(name = "PDOC_BINR_NSQ", referencedColumnName = "BINR_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TBinaryContents pdocBinrNsq;
    @JoinColumn(name = "PDOC_DOCU_NSQ", referencedColumnName = "DOCU_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocuments pdocDocuNsq;
    @JoinColumn(name = "PDOC_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocumentLanguages pdocDlanNsq;
    @JoinColumn(name = "PDOC_VERS_NSQ", referencedColumnName = "VERS_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TVersions pdocVersNsq;

    public TPrintableDocuments() {
    }

    public TPrintableDocuments(BigDecimal pdocNsq) {
        this.pdocNsq = pdocNsq;
    }

    public TPrintableDocuments(BigDecimal pdocNsq, String pdocDtypeLib, String pdocFileNameLib, short pdocIsPublicNum) {
        this.pdocNsq = pdocNsq;
        this.pdocDtypeLib = pdocDtypeLib;
        this.pdocFileNameLib = pdocFileNameLib;
        this.pdocIsPublicNum = pdocIsPublicNum;
    }

    public String getPdocDtypeLib() {
        return pdocDtypeLib;
    }

    public void setPdocDtypeLib(String pdocDtypeLib) {
        this.pdocDtypeLib = pdocDtypeLib;
    }

    public BigDecimal getPdocNsq() {
        return pdocNsq;
    }

    public void setPdocNsq(BigDecimal pdocNsq) {
        this.pdocNsq = pdocNsq;
    }

    public String getPdocFileNameLib() {
        return pdocFileNameLib;
    }

    public void setPdocFileNameLib(String pdocFileNameLib) {
        this.pdocFileNameLib = pdocFileNameLib;
    }

    public short getPdocIsPublicNum() {
        return pdocIsPublicNum;
    }

    public void setPdocIsPublicNum(short pdocIsPublicNum) {
        this.pdocIsPublicNum = pdocIsPublicNum;
    }

    public TBinaryContents getPdocBinrNsq() {
        return pdocBinrNsq;
    }

    public void setPdocBinrNsq(TBinaryContents pdocBinrNsq) {
        this.pdocBinrNsq = pdocBinrNsq;
    }

    public TDocuments getPdocDocuNsq() {
        return pdocDocuNsq;
    }

    public void setPdocDocuNsq(TDocuments pdocDocuNsq) {
        this.pdocDocuNsq = pdocDocuNsq;
    }

    public TDocumentLanguages getPdocDlanNsq() {
        return pdocDlanNsq;
    }

    public void setPdocDlanNsq(TDocumentLanguages pdocDlanNsq) {
        this.pdocDlanNsq = pdocDlanNsq;
    }

    public TVersions getPdocVersNsq() {
        return pdocVersNsq;
    }

    public void setPdocVersNsq(TVersions pdocVersNsq) {
        this.pdocVersNsq = pdocVersNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdocNsq != null ? pdocNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPrintableDocuments)) {
            return false;
        }
        TPrintableDocuments other = (TPrintableDocuments) object;
        if ((this.pdocNsq == null && other.pdocNsq != null) || (this.pdocNsq != null && !this.pdocNsq.equals(other.pdocNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TPrintableDocuments[ pdocNsq=" + pdocNsq + " ]";
    }
    
}
