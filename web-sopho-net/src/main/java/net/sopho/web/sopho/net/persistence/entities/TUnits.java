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
import javax.persistence.ManyToMany;
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
@Table(name = "T_UNITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUnits.findAll", query = "SELECT t FROM TUnits t"),
    @NamedQuery(name = "TUnits.findByUnitNsq", query = "SELECT t FROM TUnits t WHERE t.unitNsq = :unitNsq"),
    @NamedQuery(name = "TUnits.findByUnitCode", query = "SELECT t FROM TUnits t WHERE t.unitCode = :unitCode")})
public class TUnits implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNIT_NSQ")
    private BigDecimal unitNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "UNIT_CODE")
    private String unitCode;
    @ManyToMany(mappedBy = "tUnitsCollection", fetch = FetchType.LAZY)
    private Collection<TChapterTitles> tChapterTitlesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unlaUnitNsq", fetch = FetchType.LAZY)
    private Collection<TUnitLabel> tUnitLabelCollection;

    public TUnits() {
    }

    public TUnits(BigDecimal unitNsq) {
        this.unitNsq = unitNsq;
    }

    public TUnits(BigDecimal unitNsq, String unitCode) {
        this.unitNsq = unitNsq;
        this.unitCode = unitCode;
    }

    public BigDecimal getUnitNsq() {
        return unitNsq;
    }

    public void setUnitNsq(BigDecimal unitNsq) {
        this.unitNsq = unitNsq;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @XmlTransient
    public Collection<TChapterTitles> getTChapterTitlesCollection() {
        return tChapterTitlesCollection;
    }

    public void setTChapterTitlesCollection(Collection<TChapterTitles> tChapterTitlesCollection) {
        this.tChapterTitlesCollection = tChapterTitlesCollection;
    }

    @XmlTransient
    public Collection<TUnitLabel> getTUnitLabelCollection() {
        return tUnitLabelCollection;
    }

    public void setTUnitLabelCollection(Collection<TUnitLabel> tUnitLabelCollection) {
        this.tUnitLabelCollection = tUnitLabelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitNsq != null ? unitNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUnits)) {
            return false;
        }
        TUnits other = (TUnits) object;
        if ((this.unitNsq == null && other.unitNsq != null) || (this.unitNsq != null && !this.unitNsq.equals(other.unitNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TUnits[ unitNsq=" + unitNsq + " ]";
    }
    
}
