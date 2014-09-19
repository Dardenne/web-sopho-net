/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

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
@Table(name = "T_CHAPTER_DESCRIPTIONS_TRANS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TChapterDescriptionsTrans.findAll", query = "SELECT t FROM TChapterDescriptionsTrans t"),
    @NamedQuery(name = "TChapterDescriptionsTrans.findByChdtNsq", query = "SELECT t FROM TChapterDescriptionsTrans t WHERE t.chdtNsq = :chdtNsq"),
    @NamedQuery(name = "TChapterDescriptionsTrans.findByChdtDescriptionLib", query = "SELECT t FROM TChapterDescriptionsTrans t WHERE t.chdtDescriptionLib = :chdtDescriptionLib")})
public class TChapterDescriptionsTrans implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHDT_NSQ")
    private BigDecimal chdtNsq;
    @Size(max = 255)
    @Column(name = "CHDT_DESCRIPTION_LIB")
    private String chdtDescriptionLib;
    @JoinColumn(name = "CHDT_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TChapterTitles chdtChtlNsq;
    @JoinColumn(name = "CHDT_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages chdtDlanNsq;

    public TChapterDescriptionsTrans() {
    }

    public TChapterDescriptionsTrans(BigDecimal chdtNsq) {
        this.chdtNsq = chdtNsq;
    }

    public BigDecimal getChdtNsq() {
        return chdtNsq;
    }

    public void setChdtNsq(BigDecimal chdtNsq) {
        this.chdtNsq = chdtNsq;
    }

    public String getChdtDescriptionLib() {
        return chdtDescriptionLib;
    }

    public void setChdtDescriptionLib(String chdtDescriptionLib) {
        this.chdtDescriptionLib = chdtDescriptionLib;
    }

    public TChapterTitles getChdtChtlNsq() {
        return chdtChtlNsq;
    }

    public void setChdtChtlNsq(TChapterTitles chdtChtlNsq) {
        this.chdtChtlNsq = chdtChtlNsq;
    }

    public TDocumentLanguages getChdtDlanNsq() {
        return chdtDlanNsq;
    }

    public void setChdtDlanNsq(TDocumentLanguages chdtDlanNsq) {
        this.chdtDlanNsq = chdtDlanNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chdtNsq != null ? chdtNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TChapterDescriptionsTrans)) {
            return false;
        }
        TChapterDescriptionsTrans other = (TChapterDescriptionsTrans) object;
        if ((this.chdtNsq == null && other.chdtNsq != null) || (this.chdtNsq != null && !this.chdtNsq.equals(other.chdtNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TChapterDescriptionsTrans[ chdtNsq=" + chdtNsq + " ]";
    }
    
}
