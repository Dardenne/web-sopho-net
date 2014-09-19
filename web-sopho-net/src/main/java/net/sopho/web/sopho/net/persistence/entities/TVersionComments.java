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
@Table(name = "T_VERSION_COMMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TVersionComments.findAll", query = "SELECT t FROM TVersionComments t"),
    @NamedQuery(name = "TVersionComments.findByVercNsq", query = "SELECT t FROM TVersionComments t WHERE t.vercNsq = :vercNsq"),
    @NamedQuery(name = "TVersionComments.findByVercCommentLib", query = "SELECT t FROM TVersionComments t WHERE t.vercCommentLib = :vercCommentLib"),
    @NamedQuery(name = "TVersionComments.findByVercLastUpdatedDat", query = "SELECT t FROM TVersionComments t WHERE t.vercLastUpdatedDat = :vercLastUpdatedDat")})
public class TVersionComments implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERC_NSQ")
    private BigDecimal vercNsq;
    @Size(max = 1000)
    @Column(name = "VERC_COMMENT_LIB")
    private String vercCommentLib;
    @Column(name = "VERC_LAST_UPDATED_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vercLastUpdatedDat;
    @JoinColumn(name = "VERC_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages vercDlanNsq;
    @JoinColumn(name = "VERC_VERS_NSQ", referencedColumnName = "VERS_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TVersions vercVersNsq;

    public TVersionComments() {
    }

    public TVersionComments(BigDecimal vercNsq) {
        this.vercNsq = vercNsq;
    }

    public BigDecimal getVercNsq() {
        return vercNsq;
    }

    public void setVercNsq(BigDecimal vercNsq) {
        this.vercNsq = vercNsq;
    }

    public String getVercCommentLib() {
        return vercCommentLib;
    }

    public void setVercCommentLib(String vercCommentLib) {
        this.vercCommentLib = vercCommentLib;
    }

    public Date getVercLastUpdatedDat() {
        return vercLastUpdatedDat;
    }

    public void setVercLastUpdatedDat(Date vercLastUpdatedDat) {
        this.vercLastUpdatedDat = vercLastUpdatedDat;
    }

    public TDocumentLanguages getVercDlanNsq() {
        return vercDlanNsq;
    }

    public void setVercDlanNsq(TDocumentLanguages vercDlanNsq) {
        this.vercDlanNsq = vercDlanNsq;
    }

    public TVersions getVercVersNsq() {
        return vercVersNsq;
    }

    public void setVercVersNsq(TVersions vercVersNsq) {
        this.vercVersNsq = vercVersNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vercNsq != null ? vercNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TVersionComments)) {
            return false;
        }
        TVersionComments other = (TVersionComments) object;
        if ((this.vercNsq == null && other.vercNsq != null) || (this.vercNsq != null && !this.vercNsq.equals(other.vercNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TVersionComments[ vercNsq=" + vercNsq + " ]";
    }
    
}
