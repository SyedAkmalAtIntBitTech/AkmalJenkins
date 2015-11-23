package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblEmailsenthistory generated by hbm2java
 */
@Entity
@Table(name="tbl_emailsenthistory"
    ,schema="public"
)
public class TblEmailsenthistory  implements java.io.Serializable {


     private int id;
     private Integer userId;
     private Date timesent;
     private String contenthtml;
     private String emailaddress;
     private String emaillistname;
     private String emailTag;
     private String subject;
     private Serializable toemailaddresses;

    public TblEmailsenthistory() {
    }

	
    public TblEmailsenthistory(int id) {
        this.id = id;
    }
    public TblEmailsenthistory(int id, Integer userId, Date timesent, String contenthtml, String emailaddress, String emaillistname, String emailTag, String subject, Serializable toemailaddresses) {
       this.id = id;
       this.userId = userId;
       this.timesent = timesent;
       this.contenthtml = contenthtml;
       this.emailaddress = emailaddress;
       this.emaillistname = emaillistname;
       this.emailTag = emailTag;
       this.subject = subject;
       this.toemailaddresses = toemailaddresses;
    }
   
     @Id 

    
//    @GenericGenerator(name="id" , strategy="increment")
//    @GeneratedValue(generator="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timesent", length=35)
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

    
    @Column(name="emailaddress", length=150)
    public String getEmailaddress() {
        return this.emailaddress;
    }
    
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    
    @Column(name="emaillistname", length=150)
    public String getEmaillistname() {
        return this.emaillistname;
    }
    
    public void setEmaillistname(String emaillistname) {
        this.emaillistname = emaillistname;
    }

    
    @Column(name="email_tag", length=1000)
    public String getEmailTag() {
        return this.emailTag;
    }
    
    public void setEmailTag(String emailTag) {
        this.emailTag = emailTag;
    }

    
    @Column(name="subject", length=1000)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    @Column(name="toemailaddresses")
    public Serializable getToemailaddresses() {
        return this.toemailaddresses;
    }
    
    public void setToemailaddresses(Serializable toemailaddresses) {
        this.toemailaddresses = toemailaddresses;
    }




}


