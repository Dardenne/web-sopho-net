/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "T_APPLICATION_STATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TApplicationStates.findAll", query = "SELECT t FROM TApplicationStates t"),
    @NamedQuery(name = "TApplicationStates.findByApplNsq", query = "SELECT t FROM TApplicationStates t WHERE t.applNsq = :applNsq"),
    @NamedQuery(name = "TApplicationStates.findByApplHitCountNum", query = "SELECT t FROM TApplicationStates t WHERE t.applHitCountNum = :applHitCountNum"),
    @NamedQuery(name = "TApplicationStates.findByApplCurrentNum", query = "SELECT t FROM TApplicationStates t WHERE t.applCurrentNum = :applCurrentNum")})
public class TApplicationStates implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "APPL_NSQ")
    private BigDecimal applNsq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APPL_HIT_COUNT_NUM")
    private BigInteger applHitCountNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APPL_CURRENT_NUM")
    private short applCurrentNum;

    public TApplicationStates() {
    }

    public TApplicationStates(BigDecimal applNsq) {
        this.applNsq = applNsq;
    }

    public TApplicationStates(BigDecimal applNsq, BigInteger applHitCountNum, short applCurrentNum) {
        this.applNsq = applNsq;
        this.applHitCountNum = applHitCountNum;
        this.applCurrentNum = applCurrentNum;
    }

    public BigDecimal getApplNsq() {
        return applNsq;
    }

    public void setApplNsq(BigDecimal applNsq) {
        this.applNsq = applNsq;
    }

    public BigInteger getApplHitCountNum() {
        return applHitCountNum;
    }

    public void setApplHitCountNum(BigInteger applHitCountNum) {
        this.applHitCountNum = applHitCountNum;
    }

    public short getApplCurrentNum() {
        return applCurrentNum;
    }

    public void setApplCurrentNum(short applCurrentNum) {
        this.applCurrentNum = applCurrentNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applNsq != null ? applNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TApplicationStates)) {
            return false;
        }
        TApplicationStates other = (TApplicationStates) object;
        if ((this.applNsq == null && other.applNsq != null) || (this.applNsq != null && !this.applNsq.equals(other.applNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TApplicationStates[ applNsq=" + applNsq + " ]";
    }
    
}
