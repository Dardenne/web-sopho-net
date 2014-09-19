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
@Table(name = "T_CHAPTER_IMAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TChapterImages.findAll", query = "SELECT t FROM TChapterImages t"),
    @NamedQuery(name = "TChapterImages.findByChimNsq", query = "SELECT t FROM TChapterImages t WHERE t.chimNsq = :chimNsq"),
    @NamedQuery(name = "TChapterImages.findByChimNameLib", query = "SELECT t FROM TChapterImages t WHERE t.chimNameLib = :chimNameLib"),
    @NamedQuery(name = "TChapterImages.findByChimExtensionLib", query = "SELECT t FROM TChapterImages t WHERE t.chimExtensionLib = :chimExtensionLib")})
public class TChapterImages implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHIM_NSQ")
    private BigDecimal chimNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHIM_NAME_LIB")
    private String chimNameLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CHIM_EXTENSION_LIB")
    private String chimExtensionLib;
    @JoinColumn(name = "CHIM_BINR_NSQ", referencedColumnName = "BINR_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TBinaryContents chimBinrNsq;
    @JoinColumn(name = "CHIM_CHCO_NSQ", referencedColumnName = "CHCO_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TChapterContents chimChcoNsq;

    public TChapterImages() {
    }

    public TChapterImages(BigDecimal chimNsq) {
        this.chimNsq = chimNsq;
    }

    public TChapterImages(BigDecimal chimNsq, String chimNameLib, String chimExtensionLib) {
        this.chimNsq = chimNsq;
        this.chimNameLib = chimNameLib;
        this.chimExtensionLib = chimExtensionLib;
    }

    public BigDecimal getChimNsq() {
        return chimNsq;
    }

    public void setChimNsq(BigDecimal chimNsq) {
        this.chimNsq = chimNsq;
    }

    public String getChimNameLib() {
        return chimNameLib;
    }

    public void setChimNameLib(String chimNameLib) {
        this.chimNameLib = chimNameLib;
    }

    public String getChimExtensionLib() {
        return chimExtensionLib;
    }

    public void setChimExtensionLib(String chimExtensionLib) {
        this.chimExtensionLib = chimExtensionLib;
    }

    public TBinaryContents getChimBinrNsq() {
        return chimBinrNsq;
    }

    public void setChimBinrNsq(TBinaryContents chimBinrNsq) {
        this.chimBinrNsq = chimBinrNsq;
    }

    public TChapterContents getChimChcoNsq() {
        return chimChcoNsq;
    }

    public void setChimChcoNsq(TChapterContents chimChcoNsq) {
        this.chimChcoNsq = chimChcoNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chimNsq != null ? chimNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TChapterImages)) {
            return false;
        }
        TChapterImages other = (TChapterImages) object;
        if ((this.chimNsq == null && other.chimNsq != null) || (this.chimNsq != null && !this.chimNsq.equals(other.chimNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TChapterImages[ chimNsq=" + chimNsq + " ]";
    }
    
}
