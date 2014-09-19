/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "T_LABEL_BUNDLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLabelBundles.findAll", query = "SELECT t FROM TLabelBundles t"),
    @NamedQuery(name = "TLabelBundles.findByLabuNsq", query = "SELECT t FROM TLabelBundles t WHERE t.labuNsq = :labuNsq"),
    @NamedQuery(name = "TLabelBundles.findByLabuKeyLib", query = "SELECT t FROM TLabelBundles t WHERE t.labuKeyLib = :labuKeyLib"),
    @NamedQuery(name = "TLabelBundles.findByLabuValueLib", query = "SELECT t FROM TLabelBundles t WHERE t.labuValueLib = :labuValueLib")})
public class TLabelBundles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LABU_NSQ")
    private BigDecimal labuNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LABU_KEY_LIB")
    private String labuKeyLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "LABU_VALUE_LIB")
    private String labuValueLib;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unlaLabuNsq", fetch = FetchType.LAZY)
    private Collection<TUnitLabel> tUnitLabelCollection;
    @JoinColumn(name = "LABU_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages labuDlanNsq;

    public TLabelBundles() {
    }

    public TLabelBundles(BigDecimal labuNsq) {
        this.labuNsq = labuNsq;
    }

    public TLabelBundles(BigDecimal labuNsq, String labuKeyLib, String labuValueLib) {
        this.labuNsq = labuNsq;
        this.labuKeyLib = labuKeyLib;
        this.labuValueLib = labuValueLib;
    }

    public BigDecimal getLabuNsq() {
        return labuNsq;
    }

    public void setLabuNsq(BigDecimal labuNsq) {
        this.labuNsq = labuNsq;
    }

    public String getLabuKeyLib() {
        return labuKeyLib;
    }

    public void setLabuKeyLib(String labuKeyLib) {
        this.labuKeyLib = labuKeyLib;
    }

    public String getLabuValueLib() {
        return labuValueLib;
    }

    public void setLabuValueLib(String labuValueLib) {
        this.labuValueLib = labuValueLib;
    }

    @XmlTransient
    public Collection<TUnitLabel> getTUnitLabelCollection() {
        return tUnitLabelCollection;
    }

    public void setTUnitLabelCollection(Collection<TUnitLabel> tUnitLabelCollection) {
        this.tUnitLabelCollection = tUnitLabelCollection;
    }

    public TDocumentLanguages getLabuDlanNsq() {
        return labuDlanNsq;
    }

    public void setLabuDlanNsq(TDocumentLanguages labuDlanNsq) {
        this.labuDlanNsq = labuDlanNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labuNsq != null ? labuNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLabelBundles)) {
            return false;
        }
        TLabelBundles other = (TLabelBundles) object;
        if ((this.labuNsq == null && other.labuNsq != null) || (this.labuNsq != null && !this.labuNsq.equals(other.labuNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TLabelBundles[ labuNsq=" + labuNsq + " ]";
    }
    
}
