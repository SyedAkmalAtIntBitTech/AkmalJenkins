package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * TblScheduledEmailList generated by hbm2java
 */
@Entity
@Table(name="tbl_scheduled_email_list"
    ,schema="public"
)
@TypeDefs( {@TypeDef( name= "StringJsonObject", typeClass = StringJsonUserType.class)})
public class TblScheduledEmailList  implements java.io.Serializable {

     private int id;
     private TblUserLoginDetails tblUserLoginDetails;
     private String subject;
     private String body;
     private String fromAddress;
     private String emailListName;
     private String fromName;
     private String toEmailAddresses;
     private String replyToEmailAddress;
     private TblScheduledEntityList tblScheduledEntityList;
    
    public TblScheduledEmailList() {
    }

    public TblScheduledEmailList(int id) {
        this.id = id;
    }
    public TblScheduledEmailList(int id, TblUserLoginDetails tblUserLoginDetails, String subject, String body, String fromAddress, String emailListName, String fromName, String toEmailAddresses, String replyToEmailAddress,TblScheduledEntityList tblScheduledEntityList) {
       this.id = id;
       this.tblUserLoginDetails = tblUserLoginDetails;
       this.subject = subject;
       this.body = body;
       this.fromAddress = fromAddress;
       this.emailListName = emailListName;
       this.fromName = fromName;
       this.toEmailAddresses = toEmailAddresses;
       this.replyToEmailAddress = replyToEmailAddress;
       this.tblScheduledEntityList = tblScheduledEntityList;
      
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public TblUserLoginDetails getTblUserLoginDetails() {
        return this.tblUserLoginDetails;
    }
    
    public void setTblUserLoginDetails(TblUserLoginDetails tblUserLoginDetails) {
        this.tblUserLoginDetails = tblUserLoginDetails;
    }

    
    @Column(name="subject", length=2048)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    @Column(name="body")
    public String getBody() {
        return this.body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }

    
    @Column(name="from_address", length=250)
    public String getFromAddress() {
        return this.fromAddress;
    }
    
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    
    @Column(name="email_list_name", length=250)
    public String getEmailListName() {
        return this.emailListName;
    }
    
    public void setEmailListName(String emailListName) {
        this.emailListName = emailListName;
    }

    
    @Column(name="from_name", length=1024)
    public String getFromName() {
        return this.fromName;
    }
    
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    
    @Column(name="to_email_addresses")
    @Type(type = "StringJsonObject")
    public String getToEmailAddresses() {
        return this.toEmailAddresses;
    }
    
    public void setToEmailAddresses(String toEmailAddresses) {
        this.toEmailAddresses = toEmailAddresses;
    }

    
    @Column(name="reply_to_email_address", length=150)
    public String getReplyToEmailAddress() {
        return this.replyToEmailAddress;
    }
    
    public void setReplyToEmailAddress(String replyToEmailAddress) {
        this.replyToEmailAddress = replyToEmailAddress;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entity_list_id")
    public TblScheduledEntityList getTblScheduledEntityList() {
        return this.tblScheduledEntityList;
    }
    
    public void setTblScheduledEntityList(TblScheduledEntityList tblScheduledEntityList) {
        this.tblScheduledEntityList = tblScheduledEntityList;
    }



}

