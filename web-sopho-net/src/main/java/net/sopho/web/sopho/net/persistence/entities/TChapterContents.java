/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sopho.web.sopho.net.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LU01007
 */
@Entity
@Table(name = "T_CHAPTER_CONTENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TChapterContents.findAll", query = "SELECT t FROM TChapterContents t"),
    @NamedQuery(name = "TChapterContents.findByChcoDtypeLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoDtypeLib = :chcoDtypeLib"),
    @NamedQuery(name = "TChapterContents.findByChcoNsq", query = "SELECT t FROM TChapterContents t WHERE t.chcoNsq = :chcoNsq"),
    @NamedQuery(name = "TChapterContents.findByChcoNodeNumberLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoNodeNumberLib = :chcoNodeNumberLib"),
    @NamedQuery(name = "TChapterContents.findByChcoOrderNum", query = "SELECT t FROM TChapterContents t WHERE t.chcoOrderNum = :chcoOrderNum"),
    @NamedQuery(name = "TChapterContents.findByChcoTitleLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoTitleLib = :chcoTitleLib"),
    @NamedQuery(name = "TChapterContents.findByChcoUploadedFileNameLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoUploadedFileNameLib = :chcoUploadedFileNameLib"),
    @NamedQuery(name = "TChapterContents.findByChcoUploadDat", query = "SELECT t FROM TChapterContents t WHERE t.chcoUploadDat = :chcoUploadDat"),
    @NamedQuery(name = "TChapterContents.findByChcoVersionNum", query = "SELECT t FROM TChapterContents t WHERE t.chcoVersionNum = :chcoVersionNum"),
    @NamedQuery(name = "TChapterContents.findByChcoIsAnnxListNum", query = "SELECT t FROM TChapterContents t WHERE t.chcoIsAnnxListNum = :chcoIsAnnxListNum"),
    @NamedQuery(name = "TChapterContents.findByChcoNextNodeNumberLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoNextNodeNumberLib = :chcoNextNodeNumberLib"),
    @NamedQuery(name = "TChapterContents.findByChcoPrevNodeNumberLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoPrevNodeNumberLib = :chcoPrevNodeNumberLib"),
    @NamedQuery(name = "TChapterContents.findByChcoNextPubNodeNumberLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoNextPubNodeNumberLib = :chcoNextPubNodeNumberLib"),
    @NamedQuery(name = "TChapterContents.findByChcoPrevPubNodeNumberLib", query = "SELECT t FROM TChapterContents t WHERE t.chcoPrevPubNodeNumberLib = :chcoPrevPubNodeNumberLib"),
    @NamedQuery(name = "TChapterContents.findByChcoIsEmptyNum", query = "SELECT t FROM TChapterContents t WHERE t.chcoIsEmptyNum = :chcoIsEmptyNum")})
public class TChapterContents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "CHCO_DTYPE_LIB")
    private String chcoDtypeLib;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHCO_NSQ")
    private BigDecimal chcoNsq;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "CHCO_FORMATTED_CONTENT_CLB")
    private String chcoFormattedContentClb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHCO_NODE_NUMBER_LIB")
    private String chcoNodeNumberLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHCO_ORDER_NUM")
    private short chcoOrderNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHCO_TITLE_LIB")
    private String chcoTitleLib;
    @Size(max = 255)
    @Column(name = "CHCO_UPLOADED_FILE_NAME_LIB")
    private String chcoUploadedFileNameLib;
    @Column(name = "CHCO_UPLOAD_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chcoUploadDat;
    @Column(name = "CHCO_VERSION_NUM")
    private Long chcoVersionNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHCO_IS_ANNX_LIST_NUM")
    private short chcoIsAnnxListNum;
    @Size(max = 255)
    @Column(name = "CHCO_NEXT_NODE_NUMBER_LIB")
    private String chcoNextNodeNumberLib;
    @Size(max = 255)
    @Column(name = "CHCO_PREV_NODE_NUMBER_LIB")
    private String chcoPrevNodeNumberLib;
    @Size(max = 255)
    @Column(name = "CHCO_NEXT_PUB_NODE_NUMBER_LIB")
    private String chcoNextPubNodeNumberLib;
    @Size(max = 255)
    @Column(name = "CHCO_PREV_PUB_NODE_NUMBER_LIB")
    private String chcoPrevPubNodeNumberLib;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHCO_IS_EMPTY_NUM")
    private short chcoIsEmptyNum;
    @JoinColumn(name = "CHCO_BINR_NSQ_XML_CONTENT", referencedColumnName = "BINR_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TBinaryContents chcoBinrNsqXmlContent;
    @JoinColumn(name = "CHCO_BINR_NSQ_PDF_FILE", referencedColumnName = "BINR_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TBinaryContents chcoBinrNsqPdfFile;
    @OneToMany(mappedBy = "chcoParentNsq", fetch = FetchType.LAZY)
    private Collection<TChapterContents> tChapterContentsCollection;
    @JoinColumn(name = "CHCO_PARENT_NSQ", referencedColumnName = "CHCO_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TChapterContents chcoParentNsq;
    @JoinColumn(name = "CHCO_CHTL_NSQ", referencedColumnName = "CHTL_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TChapterTitles chcoChtlNsq;
    @JoinColumn(name = "CHCO_DOCU_NSQ", referencedColumnName = "DOCU_NSQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private TDocuments chcoDocuNsq;
    @OneToMany(mappedBy = "chimChcoNsq", fetch = FetchType.LAZY)
    private Collection<TChapterImages> tChapterImagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kytrChcoNsq", fetch = FetchType.LAZY)
    private Collection<TKeywordTranslations> tKeywordTranslationsCollection;

    public TChapterContents() {
    }

    public TChapterContents(BigDecimal chcoNsq) {
        this.chcoNsq = chcoNsq;
    }

    public TChapterContents(BigDecimal chcoNsq, String chcoDtypeLib, String chcoFormattedContentClb, String chcoNodeNumberLib, short chcoOrderNum, String chcoTitleLib, short chcoIsAnnxListNum, short chcoIsEmptyNum) {
        this.chcoNsq = chcoNsq;
        this.chcoDtypeLib = chcoDtypeLib;
        this.chcoFormattedContentClb = chcoFormattedContentClb;
        this.chcoNodeNumberLib = chcoNodeNumberLib;
        this.chcoOrderNum = chcoOrderNum;
        this.chcoTitleLib = chcoTitleLib;
        this.chcoIsAnnxListNum = chcoIsAnnxListNum;
        this.chcoIsEmptyNum = chcoIsEmptyNum;
    }

    public String getChcoDtypeLib() {
        return chcoDtypeLib;
    }

    public void setChcoDtypeLib(String chcoDtypeLib) {
        this.chcoDtypeLib = chcoDtypeLib;
    }

    public BigDecimal getChcoNsq() {
        return chcoNsq;
    }

    public void setChcoNsq(BigDecimal chcoNsq) {
        this.chcoNsq = chcoNsq;
    }

    public String getChcoFormattedContentClb() {
        return chcoFormattedContentClb;
    }

    public void setChcoFormattedContentClb(String chcoFormattedContentClb) {
        this.chcoFormattedContentClb = chcoFormattedContentClb;
    }

    public String getChcoNodeNumberLib() {
        return chcoNodeNumberLib;
    }

    public void setChcoNodeNumberLib(String chcoNodeNumberLib) {
        this.chcoNodeNumberLib = chcoNodeNumberLib;
    }

    public short getChcoOrderNum() {
        return chcoOrderNum;
    }

    public void setChcoOrderNum(short chcoOrderNum) {
        this.chcoOrderNum = chcoOrderNum;
    }

    public String getChcoTitleLib() {
        return chcoTitleLib;
    }

    public void setChcoTitleLib(String chcoTitleLib) {
        this.chcoTitleLib = chcoTitleLib;
    }

    public String getChcoUploadedFileNameLib() {
        return chcoUploadedFileNameLib;
    }

    public void setChcoUploadedFileNameLib(String chcoUploadedFileNameLib) {
        this.chcoUploadedFileNameLib = chcoUploadedFileNameLib;
    }

    public Date getChcoUploadDat() {
        return chcoUploadDat;
    }

    public void setChcoUploadDat(Date chcoUploadDat) {
        this.chcoUploadDat = chcoUploadDat;
    }

    public Long getChcoVersionNum() {
        return chcoVersionNum;
    }

    public void setChcoVersionNum(Long chcoVersionNum) {
        this.chcoVersionNum = chcoVersionNum;
    }

    public short getChcoIsAnnxListNum() {
        return chcoIsAnnxListNum;
    }

    public void setChcoIsAnnxListNum(short chcoIsAnnxListNum) {
        this.chcoIsAnnxListNum = chcoIsAnnxListNum;
    }

    public String getChcoNextNodeNumberLib() {
        return chcoNextNodeNumberLib;
    }

    public void setChcoNextNodeNumberLib(String chcoNextNodeNumberLib) {
        this.chcoNextNodeNumberLib = chcoNextNodeNumberLib;
    }

    public String getChcoPrevNodeNumberLib() {
        return chcoPrevNodeNumberLib;
    }

    public void setChcoPrevNodeNumberLib(String chcoPrevNodeNumberLib) {
        this.chcoPrevNodeNumberLib = chcoPrevNodeNumberLib;
    }

    public String getChcoNextPubNodeNumberLib() {
        return chcoNextPubNodeNumberLib;
    }

    public void setChcoNextPubNodeNumberLib(String chcoNextPubNodeNumberLib) {
        this.chcoNextPubNodeNumberLib = chcoNextPubNodeNumberLib;
    }

    public String getChcoPrevPubNodeNumberLib() {
        return chcoPrevPubNodeNumberLib;
    }

    public void setChcoPrevPubNodeNumberLib(String chcoPrevPubNodeNumberLib) {
        this.chcoPrevPubNodeNumberLib = chcoPrevPubNodeNumberLib;
    }

    public short getChcoIsEmptyNum() {
        return chcoIsEmptyNum;
    }

    public void setChcoIsEmptyNum(short chcoIsEmptyNum) {
        this.chcoIsEmptyNum = chcoIsEmptyNum;
    }

    public TBinaryContents getChcoBinrNsqXmlContent() {
        return chcoBinrNsqXmlContent;
    }

    public void setChcoBinrNsqXmlContent(TBinaryContents chcoBinrNsqXmlContent) {
        this.chcoBinrNsqXmlContent = chcoBinrNsqXmlContent;
    }

    public TBinaryContents getChcoBinrNsqPdfFile() {
        return chcoBinrNsqPdfFile;
    }

    public void setChcoBinrNsqPdfFile(TBinaryContents chcoBinrNsqPdfFile) {
        this.chcoBinrNsqPdfFile = chcoBinrNsqPdfFile;
    }

    @XmlTransient
    public Collection<TChapterContents> getTChapterContentsCollection() {
        return tChapterContentsCollection;
    }

    public void setTChapterContentsCollection(Collection<TChapterContents> tChapterContentsCollection) {
        this.tChapterContentsCollection = tChapterContentsCollection;
    }

    public TChapterContents getChcoParentNsq() {
        return chcoParentNsq;
    }

    public void setChcoParentNsq(TChapterContents chcoParentNsq) {
        this.chcoParentNsq = chcoParentNsq;
    }

    public TChapterTitles getChcoChtlNsq() {
        return chcoChtlNsq;
    }

    public void setChcoChtlNsq(TChapterTitles chcoChtlNsq) {
        this.chcoChtlNsq = chcoChtlNsq;
    }

    public TDocuments getChcoDocuNsq() {
        return chcoDocuNsq;
    }

    public void setChcoDocuNsq(TDocuments chcoDocuNsq) {
        this.chcoDocuNsq = chcoDocuNsq;
    }

    @XmlTransient
    public Collection<TChapterImages> getTChapterImagesCollection() {
        return tChapterImagesCollection;
    }

    public void setTChapterImagesCollection(Collection<TChapterImages> tChapterImagesCollection) {
        this.tChapterImagesCollection = tChapterImagesCollection;
    }

    @XmlTransient
    public Collection<TKeywordTranslations> getTKeywordTranslationsCollection() {
        return tKeywordTranslationsCollection;
    }

    public void setTKeywordTranslationsCollection(Collection<TKeywordTranslations> tKeywordTranslationsCollection) {
        this.tKeywordTranslationsCollection = tKeywordTranslationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chcoNsq != null ? chcoNsq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TChapterContents)) {
            return false;
        }
        TChapterContents other = (TChapterContents) object;
        if ((this.chcoNsq == null && other.chcoNsq != null) || (this.chcoNsq != null && !this.chcoNsq.equals(other.chcoNsq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sopho.web.sopho.net.entities.TChapterContents[ chcoNsq=" + chcoNsq + " ]";
    }
    
}
