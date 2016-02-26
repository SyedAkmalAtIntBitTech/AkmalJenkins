/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "social_post_history")
public class SocialPostHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "social_post_history_id")
    private Integer socialPostHistoryId;
    @Column(name = "time_sent")
    @Temporal(TemporalType.DATE)
    private Date timeSent;
    @Column(name = "twitter")
    private Boolean twitter;
    @Column(name = "facebook")
    private Boolean facebook;
    @Column(name = "pdf_file_name")
    private String pdfFileName;
    @Column(name = "image_file_name")
    private String imageFileName;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;

    public SocialPostHistory() {
    }

    public SocialPostHistory(Integer socialPostHistoryId) {
        this.socialPostHistoryId = socialPostHistoryId;
    }

    public Integer getSocialPostHistoryId() {
        return socialPostHistoryId;
    }

    public void setSocialPostHistoryId(Integer socialPostHistoryId) {
        this.socialPostHistoryId = socialPostHistoryId;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public Boolean getTwitter() {
        return twitter;
    }

    public void setTwitter(Boolean twitter) {
        this.twitter = twitter;
    }

    public Boolean getFacebook() {
        return facebook;
    }

    public void setFacebook(Boolean facebook) {
        this.facebook = facebook;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

}
