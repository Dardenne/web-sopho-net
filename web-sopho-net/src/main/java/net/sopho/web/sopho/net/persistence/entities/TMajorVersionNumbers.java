/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_MAJOR_VERSION_NUMBERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMajorVersionNumbers.findAll", query = "SELECT t FROM TMajorVersionNumbers t"),
    @NamedQuery(name = "TMajorVersionNumbers.findByMavnNsq", query = "SELECT t FROM TMajorVersionNumbers t WHERE t.mavnNsq = :mavnNsq"),
    @NamedQuery(name = "TMajorVersionNumbers.findByMavnMajorVersionNum", query = "SELECT t FROM TMajorVersionNumbers t WHERE t.mavnMajorVersionNum = :mavnMajorVersionNum")})
public class TMajorVersionNumbers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAVN_NSQ")
    private BigDecimal mavnNsq;
    @Column(name = "MAVN_MAJOR_VERSION_NUM")
    private BigInteger mavnMajorVersionNum;
    @OneToMany(mappedBy = "versMavnNsq", fetch = FetchType.LAZY)
    private Collection<TVersions> tVersionsCollection;

    public TMajorVersionNumbers() {
    }

    public TMajorVersionNumbers(BigDecimal mavnNsq) {
        this.mavnNsq = mavnNsq;
    }

    public BigDecimal getMavnNsq() {
        return mavnNsq;
    }

    public void setMavnNsq(BigDecimal mavnNsq) {
        this.mavnNsq = mavnNsq;
    }

    public BigInteger getMavnMajorVersionNum() {
        return mavnMajorVersionNum;
    }

    public void setMavnMajorVersionNum(BigInteger mavnMajorVersionNum) {
        this.mavnMajorVersionNum = mavnMajorVersionNum;
    }

    @XmlTransient
    public Collection<TVersions> getTVersionsCollection() {
        return tVersionsCollection;
    }

    public void setTVersionsCollection(Collection<TVersions> tVersionsCollection) {
        this.tVersionsCollection = tVersionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mavnNsq != null ? mavnNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMajorVersionNumbers)) {
            return false;
        }
        TMajorVersionNumbers other = (TMajorVersionNumbers) object;
        if ((this.mavnNsq == null && other.mavnNsq != null) || (this.mavnNsq != null && !this.mavnNsq.equals(other.mavnNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TMajorVersionNumbers[ mavnNsq=" + mavnNsq + " ]";
    }
    
}
