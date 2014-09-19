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
@Table(name = "T_EXTERNAL_LINKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TExternalLinks.findAll", query = "SELECT t FROM TExternalLinks t"),
    @NamedQuery(name = "TExternalLinks.findByElnkNsq", query = "SELECT t FROM TExternalLinks t WHERE t.elnkNsq = :elnkNsq"),
    @NamedQuery(name = "TExternalLinks.findByElnkCommentLib", query = "SELECT t FROM TExternalLinks t WHERE t.elnkCommentLib = :elnkCommentLib"),
    @NamedQuery(name = "TExternalLinks.findByElnkIsPublicNum", query = "SELECT t FROM TExternalLinks t WHERE t.elnkIsPublicNum = :elnkIsPublicNum"),
    @NamedQuery(name = "TExternalLinks.findByElnkPhraseLib", query = "SELECT t FROM TExternalLinks t WHERE t.elnkPhraseLib = :elnkPhraseLib"),
    @NamedQuery(name = "TExternalLinks.findByElnkUrlLib", query = "SELECT t FROM TExternalLinks t WHERE t.elnkUrlLib = :elnkUrlLib")})
public class TExternalLinks implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELNK_NSQ")
    private BigDecimal elnkNsq;
    @Size(max = 1000)
    @Column(name = "ELNK_COMMENT_LIB")
    private String elnkCommentLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELNK_IS_PUBLIC_NUM")
    private short elnkIsPublicNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ELNK_PHRASE_LIB")
    private String elnkPhraseLib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ELNK_URL_LIB")
    private String elnkUrlLib;
    @JoinColumn(name = "ELNK_DLAN_NSQ", referencedColumnName = "DLAN_NSQ")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDocumentLanguages elnkDlanNsq;

    public TExternalLinks() {
    }

    public TExternalLinks(BigDecimal elnkNsq) {
        this.elnkNsq = elnkNsq;
    }

    public TExternalLinks(BigDecimal elnkNsq, short elnkIsPublicNum, String elnkPhraseLib, String elnkUrlLib) {
        this.elnkNsq = elnkNsq;
        this.elnkIsPublicNum = elnkIsPublicNum;
        this.elnkPhraseLib = elnkPhraseLib;
        this.elnkUrlLib = elnkUrlLib;
    }

    public BigDecimal getElnkNsq() {
        return elnkNsq;
    }

    public void setElnkNsq(BigDecimal elnkNsq) {
        this.elnkNsq = elnkNsq;
    }

    public String getElnkCommentLib() {
        return elnkCommentLib;
    }

    public void setElnkCommentLib(String elnkCommentLib) {
        this.elnkCommentLib = elnkCommentLib;
    }

    public short getElnkIsPublicNum() {
        return elnkIsPublicNum;
    }

    public void setElnkIsPublicNum(short elnkIsPublicNum) {
        this.elnkIsPublicNum = elnkIsPublicNum;
    }

    public String getElnkPhraseLib() {
        return elnkPhraseLib;
    }

    public void setElnkPhraseLib(String elnkPhraseLib) {
        this.elnkPhraseLib = elnkPhraseLib;
    }

    public String getElnkUrlLib() {
        return elnkUrlLib;
    }

    public void setElnkUrlLib(String elnkUrlLib) {
        this.elnkUrlLib = elnkUrlLib;
    }

    public TDocumentLanguages getElnkDlanNsq() {
        return elnkDlanNsq;
    }

    public void setElnkDlanNsq(TDocumentLanguages elnkDlanNsq) {
        this.elnkDlanNsq = elnkDlanNsq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elnkNsq != null ? elnkNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TExternalLinks)) {
            return false;
        }
        TExternalLinks other = (TExternalLinks) object;
        if ((this.elnkNsq == null && other.elnkNsq != null) || (this.elnkNsq != null && !this.elnkNsq.equals(other.elnkNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TExternalLinks[ elnkNsq=" + elnkNsq + " ]";
    }
    
}
