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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_DOCUMENTLANGS_ANNEXLANGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDocumentlangsAnnexlangs.findAll", query = "SELECT t FROM TDocumentlangsAnnexlangs t"),
    @NamedQuery(name = "TDocumentlangsAnnexlangs.findByDoanNsq", query = "SELECT t FROM TDocumentlangsAnnexlangs t WHERE t.doanNsq = :doanNsq")})
public class TDocumentlangsAnnexlangs implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOAN_NSQ")
    private BigDecimal doanNsq;
    @JoinColumn(name = "DOAN_ALAN_NSQ", referencedColumnName = "ALAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TAnnexLanguages doanAlanNsq;
    @JoinColumn(name = "DOAN_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages doanDlanNsq;

    public TDocumentlangsAnnexlangs() {
    }

    public TDocumentlangsAnnexlangs(BigDecimal doanNsq) {
        this.doanNsq = doanNsq;
    }

    public BigDecimal getDoanNsq() {
        return doanNsq;
    }

    public void setDoanNsq(BigDecimal doanNsq) {
        this.doanNsq = doanNsq;
    }

    public TAnnexLanguages getDoanAlanNsq() {
        return doanAlanNsq;
    }

    public void setDoanAlanNsq(TAnnexLanguages doanAlanNsq) {
        this.doanAlanNsq = doanAlanNsq;
    }

    public TDocumentLanguages getDoanDlanNsq() {
        return doanDlanNsq;
    }

    public void setDoanDlanNsq(TDocumentLanguages doanDlanNsq) {
        this.doanDlanNsq = doanDlanNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doanNsq != null ? doanNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDocumentlangsAnnexlangs)) {
            return false;
        }
        TDocumentlangsAnnexlangs other = (TDocumentlangsAnnexlangs) object;
        if ((this.doanNsq == null && other.doanNsq != null) || (this.doanNsq != null && !this.doanNsq.equals(other.doanNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TDocumentlangsAnnexlangs[ doanNsq=" + doanNsq + " ]";
    }
    
}
