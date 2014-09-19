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
@Table(name = "T_ANNEX_GROUPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAnnexGroups.findAll", query = "SELECT t FROM TAnnexGroups t"),
    @NamedQuery(name = "TAnnexGroups.findByAgrpNsq", query = "SELECT t FROM TAnnexGroups t WHERE t.agrpNsq = :agrpNsq"),
    @NamedQuery(name = "TAnnexGroups.findByAgrpCodeLib", query = "SELECT t FROM TAnnexGroups t WHERE t.agrpCodeLib = :agrpCodeLib"),
    @NamedQuery(name = "TAnnexGroups.findByAgrpNameLib", query = "SELECT t FROM TAnnexGroups t WHERE t.agrpNameLib = :agrpNameLib"),
    @NamedQuery(name = "TAnnexGroups.findByAgrpOrderNum", query = "SELECT t FROM TAnnexGroups t WHERE t.agrpOrderNum = :agrpOrderNum")})
public class TAnnexGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGRP_NSQ")
    private BigDecimal agrpNsq;
    @Size(max = 20)
    @Column(name = "AGRP_CODE_LIB")
    private String agrpCodeLib;
    @Size(max = 255)
    @Column(name = "AGRP_NAME_LIB")
    private String agrpNameLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGRP_ORDER_NUM")
    private long agrpOrderNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anexAgrpNsq", fetch = FetchType.LAZY)
    private Collection<TAnnexes> tAnnexesCollection;

    public TAnnexGroups() {
    }

    public TAnnexGroups(BigDecimal agrpNsq) {
        this.agrpNsq = agrpNsq;
    }

    public TAnnexGroups(BigDecimal agrpNsq, long agrpOrderNum) {
        this.agrpNsq = agrpNsq;
        this.agrpOrderNum = agrpOrderNum;
    }

    public BigDecimal getAgrpNsq() {
        return agrpNsq;
    }

    public void setAgrpNsq(BigDecimal agrpNsq) {
        this.agrpNsq = agrpNsq;
    }

    public String getAgrpCodeLib() {
        return agrpCodeLib;
    }

    public void setAgrpCodeLib(String agrpCodeLib) {
        this.agrpCodeLib = agrpCodeLib;
    }

    public String getAgrpNameLib() {
        return agrpNameLib;
    }

    public void setAgrpNameLib(String agrpNameLib) {
        this.agrpNameLib = agrpNameLib;
    }

    public long getAgrpOrderNum() {
        return agrpOrderNum;
    }

    public void setAgrpOrderNum(long agrpOrderNum) {
        this.agrpOrderNum = agrpOrderNum;
    }

    @XmlTransient
    public Collection<TAnnexes> getTAnnexesCollection() {
        return tAnnexesCollection;
    }

    public void setTAnnexesCollection(Collection<TAnnexes> tAnnexesCollection) {
        this.tAnnexesCollection = tAnnexesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agrpNsq != null ? agrpNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAnnexGroups)) {
            return false;
        }
        TAnnexGroups other = (TAnnexGroups) object;
        if ((this.agrpNsq == null && other.agrpNsq != null) || (this.agrpNsq != null && !this.agrpNsq.equals(other.agrpNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TAnnexGroups[ agrpNsq=" + agrpNsq + " ]";
    }
    
}
