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
import javax.persistence.Id;
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
@Table(name = "T_APPLICATION_LOCKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TApplicationLocks.findAll", query = "SELECT t FROM TApplicationLocks t"),
    @NamedQuery(name = "TApplicationLocks.findByAlckNsq", query = "SELECT t FROM TApplicationLocks t WHERE t.alckNsq = :alckNsq"),
    @NamedQuery(name = "TApplicationLocks.findByAlckLockNameLib", query = "SELECT t FROM TApplicationLocks t WHERE t.alckLockNameLib = :alckLockNameLib"),
    @NamedQuery(name = "TApplicationLocks.findByAlckLockedByLoginLib", query = "SELECT t FROM TApplicationLocks t WHERE t.alckLockedByLoginLib = :alckLockedByLoginLib"),
    @NamedQuery(name = "TApplicationLocks.findByAlckLockedByNameLib", query = "SELECT t FROM TApplicationLocks t WHERE t.alckLockedByNameLib = :alckLockedByNameLib"),
    @NamedQuery(name = "TApplicationLocks.findByAlckLockedBySurnameLib", query = "SELECT t FROM TApplicationLocks t WHERE t.alckLockedBySurnameLib = :alckLockedBySurnameLib"),
    @NamedQuery(name = "TApplicationLocks.findByAlckSessionIdLib", query = "SELECT t FROM TApplicationLocks t WHERE t.alckSessionIdLib = :alckSessionIdLib"),
    @NamedQuery(name = "TApplicationLocks.findByAlckLockTime", query = "SELECT t FROM TApplicationLocks t WHERE t.alckLockTime = :alckLockTime")})
public class TApplicationLocks implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALCK_NSQ")
    private BigDecimal alckNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ALCK_LOCK_NAME_LIB")
    private String alckLockNameLib;
    @Size(max = 255)
    @Column(name = "ALCK_LOCKED_BY_LOGIN_LIB")
    private String alckLockedByLoginLib;
    @Size(max = 400)
    @Column(name = "ALCK_LOCKED_BY_NAME_LIB")
    private String alckLockedByNameLib;
    @Size(max = 400)
    @Column(name = "ALCK_LOCKED_BY_SURNAME_LIB")
    private String alckLockedBySurnameLib;
    @Size(max = 255)
    @Column(name = "ALCK_SESSION_ID_LIB")
    private String alckSessionIdLib;
    @Column(name = "ALCK_LOCK_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date alckLockTime;

    public TApplicationLocks() {
    }

    public TApplicationLocks(BigDecimal alckNsq) {
        this.alckNsq = alckNsq;
    }

    public TApplicationLocks(BigDecimal alckNsq, String alckLockNameLib) {
        this.alckNsq = alckNsq;
        this.alckLockNameLib = alckLockNameLib;
    }

    public BigDecimal getAlckNsq() {
        return alckNsq;
    }

    public void setAlckNsq(BigDecimal alckNsq) {
        this.alckNsq = alckNsq;
    }

    public String getAlckLockNameLib() {
        return alckLockNameLib;
    }

    public void setAlckLockNameLib(String alckLockNameLib) {
        this.alckLockNameLib = alckLockNameLib;
    }

    public String getAlckLockedByLoginLib() {
        return alckLockedByLoginLib;
    }

    public void setAlckLockedByLoginLib(String alckLockedByLoginLib) {
        this.alckLockedByLoginLib = alckLockedByLoginLib;
    }

    public String getAlckLockedByNameLib() {
        return alckLockedByNameLib;
    }

    public void setAlckLockedByNameLib(String alckLockedByNameLib) {
        this.alckLockedByNameLib = alckLockedByNameLib;
    }

    public String getAlckLockedBySurnameLib() {
        return alckLockedBySurnameLib;
    }

    public void setAlckLockedBySurnameLib(String alckLockedBySurnameLib) {
        this.alckLockedBySurnameLib = alckLockedBySurnameLib;
    }

    public String getAlckSessionIdLib() {
        return alckSessionIdLib;
    }

    public void setAlckSessionIdLib(String alckSessionIdLib) {
        this.alckSessionIdLib = alckSessionIdLib;
    }

    public Date getAlckLockTime() {
        return alckLockTime;
    }

    public void setAlckLockTime(Date alckLockTime) {
        this.alckLockTime = alckLockTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alckNsq != null ? alckNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TApplicationLocks)) {
            return false;
        }
        TApplicationLocks other = (TApplicationLocks) object;
        if ((this.alckNsq == null && other.alckNsq != null) || (this.alckNsq != null && !this.alckNsq.equals(other.alckNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TApplicationLocks[ alckNsq=" + alckNsq + " ]";
    }
    
}
