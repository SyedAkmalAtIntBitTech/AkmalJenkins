package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblSocialposthistory generated by hbm2java
 */
@Entity
@Table(name="tbl_socialposthistory"
    ,schema="public"
)
public class TblSocialposthistory  implements java.io.Serializable {


     private int id;
     private Integer userId;
     private Date timesent;
     private String contenthtml;
     private Boolean twitter;
     private Boolean facebook;
     private byte[] pdffiles;
     private byte[] imagefilename;

    public TblSocialposthistory() {
    }

	
    public TblSocialposthistory(int id) {
        this.id = id;
    }
    public TblSocialposthistory(int id, Integer userId, Date timesent, String contenthtml, Boolean twitter, Boolean facebook, byte[] pdffiles, byte[] imagefilename) {
       this.id = id;
       this.userId = userId;
       this.timesent = timesent;
       this.contenthtml = contenthtml;
       this.twitter = twitter;
       this.facebook = facebook;
       this.pdffiles = pdffiles;
       this.imagefilename = imagefilename;
    }
   
     @Id 
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="user_id")
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="timesent", length=13)
    public Date getTimesent() {
        return this.timesent;
    }
    
    public void setTimesent(Date timesent) {
        this.timesent = timesent;
    }

    
    @Column(name="contenthtml")
    public String getContenthtml() {
        return this.contenthtml;
    }
    
    public void setContenthtml(String contenthtml) {
        this.contenthtml = contenthtml;
    }

    
    @Column(name="twitter")
    public Boolean getTwitter() {
        return this.twitter;
    }
    
    public void setTwitter(Boolean twitter) {
        this.twitter = twitter;
    }

    
    @Column(name="facebook")
    public Boolean getFacebook() {
        return this.facebook;
    }
    
    public void setFacebook(Boolean facebook) {
        this.facebook = facebook;
    }

    
    @Column(name="pdffiles")
    public byte[] getPdffiles() {
        return this.pdffiles;
    }
    
    public void setPdffiles(byte[] pdffiles) {
        this.pdffiles = pdffiles;
    }

    
    @Column(name="imagefilename")
    public byte[] getImagefilename() {
        return this.imagefilename;
    }
    
    public void setImagefilename(byte[] imagefilename) {
        this.imagefilename = imagefilename;
    }




}


