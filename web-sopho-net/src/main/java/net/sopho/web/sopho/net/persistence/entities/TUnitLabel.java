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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_UNIT_LABEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUnitLabel.findAll", query = "SELECT t FROM TUnitLabel t"),
    @NamedQuery(name = "TUnitLabel.findByUnlaNsq", query = "SELECT t FROM TUnitLabel t WHERE t.unlaNsq = :unlaNsq")})
public class TUnitLabel implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNLA_NSQ")
    private BigDecimal unlaNsq;
    @JoinColumn(name = "UNLA_LABU_NSQ", referencedColumnName = "LABU_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TLabelBundles unlaLabuNsq;
    @JoinColumn(name = "UNLA_UNIT_NSQ", referencedColumnName = "UNIT_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUnits unlaUnitNsq;

    public TUnitLabel() {
    }

    public TUnitLabel(BigDecimal unlaNsq) {
        this.unlaNsq = unlaNsq;
    }

    public BigDecimal getUnlaNsq() {
        return unlaNsq;
    }

    public void setUnlaNsq(BigDecimal unlaNsq) {
        this.unlaNsq = unlaNsq;
    }

    public TLabelBundles getUnlaLabuNsq() {
        return unlaLabuNsq;
    }

    public void setUnlaLabuNsq(TLabelBundles unlaLabuNsq) {
        this.unlaLabuNsq = unlaLabuNsq;
    }

    public TUnits getUnlaUnitNsq() {
        return unlaUnitNsq;
    }

    public void setUnlaUnitNsq(TUnits unlaUnitNsq) {
        this.unlaUnitNsq = unlaUnitNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unlaNsq != null ? unlaNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUnitLabel)) {
            return false;
        }
        TUnitLabel other = (TUnitLabel) object;
        if ((this.unlaNsq == null && other.unlaNsq != null) || (this.unlaNsq != null && !this.unlaNsq.equals(other.unlaNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TUnitLabel[ unlaNsq=" + unlaNsq + " ]";
    }
    
}
