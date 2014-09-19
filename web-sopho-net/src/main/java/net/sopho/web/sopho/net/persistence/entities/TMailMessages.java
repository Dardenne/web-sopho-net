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
import javax.persistence.Lob;
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
@Table(name = "T_MAIL_MESSAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMailMessages.findAll", query = "SELECT t FROM TMailMessages t"),
    @NamedQuery(name = "TMailMessages.findByMmsgNsq", query = "SELECT t FROM TMailMessages t WHERE t.mmsgNsq = :mmsgNsq"),
    @NamedQuery(name = "TMailMessages.findByMmsgAddressLib", query = "SELECT t FROM TMailMessages t WHERE t.mmsgAddressLib = :mmsgAddressLib"),
    @NamedQuery(name = "TMailMessages.findByMmsgSendDat", query = "SELECT t FROM TMailMessages t WHERE t.mmsgSendDat = :mmsgSendDat"),
    @NamedQuery(name = "TMailMessages.findByMmsgSubjectLib", query = "SELECT t FROM TMailMessages t WHERE t.mmsgSubjectLib = :mmsgSubjectLib")})
public class TMailMessages implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MMSG_NSQ")
    private BigDecimal mmsgNsq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MMSG_ADDRESS_LIB")
    private String mmsgAddressLib;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "MMSG_MESSAGE_CLB")
    private String mmsgMessageClb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MMSG_SEND_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mmsgSendDat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MMSG_SUBJECT_LIB")
    private String mmsgSubjectLib;

    public TMailMessages() {
    }

    public TMailMessages(BigDecimal mmsgNsq) {
        this.mmsgNsq = mmsgNsq;
    }

    public TMailMessages(BigDecimal mmsgNsq, String mmsgAddressLib, String mmsgMessageClb, Date mmsgSendDat, String mmsgSubjectLib) {
        this.mmsgNsq = mmsgNsq;
        this.mmsgAddressLib = mmsgAddressLib;
        this.mmsgMessageClb = mmsgMessageClb;
        this.mmsgSendDat = mmsgSendDat;
        this.mmsgSubjectLib = mmsgSubjectLib;
    }

    public BigDecimal getMmsgNsq() {
        return mmsgNsq;
    }

    public void setMmsgNsq(BigDecimal mmsgNsq) {
        this.mmsgNsq = mmsgNsq;
    }

    public String getMmsgAddressLib() {
        return mmsgAddressLib;
    }

    public void setMmsgAddressLib(String mmsgAddressLib) {
        this.mmsgAddressLib = mmsgAddressLib;
    }

    public String getMmsgMessageClb() {
        return mmsgMessageClb;
    }

    public void setMmsgMessageClb(String mmsgMessageClb) {
        this.mmsgMessageClb = mmsgMessageClb;
    }

    public Date getMmsgSendDat() {
        return mmsgSendDat;
    }

    public void setMmsgSendDat(Date mmsgSendDat) {
        this.mmsgSendDat = mmsgSendDat;
    }

    public String getMmsgSubjectLib() {
        return mmsgSubjectLib;
    }

    public void setMmsgSubjectLib(String mmsgSubjectLib) {
        this.mmsgSubjectLib = mmsgSubjectLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mmsgNsq != null ? mmsgNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMailMessages)) {
            return false;
        }
        TMailMessages other = (TMailMessages) object;
        if ((this.mmsgNsq == null && other.mmsgNsq != null) || (this.mmsgNsq != null && !this.mmsgNsq.equals(other.mmsgNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TMailMessages[ mmsgNsq=" + mmsgNsq + " ]";
    }
    
}
