package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblUserMarketingProgram generated by hbm2java
 */
@Entity
@Table(name="tbl_user_marketing_program"
    ,schema="public"
)
public class TblUserMarketingProgram  implements java.io.Serializable {


     private int id;
     private TblMarketingProgram tblMarketingProgram;
     private TblUserLoginDetails tblUserLoginDetails;
     private String name;
     private Date dateEvent;
     private Date createDate;
     private String status;
     private Set tblScheduledEntityLists = new HashSet(0);
     private String url;
     private String linkName;
     
    public TblUserMarketingProgram() {
    }

	
    public TblUserMarketingProgram(int id) {
        this.id = id;
    }
    public TblUserMarketingProgram(int id, TblMarketingProgram tblMarketingProgram, TblUserLoginDetails tblUserLoginDetails, String name, Date dateEvent, Date createDate, String status, Set tblScheduledEntityLists) {
       this.id = id;
       this.tblMarketingProgram = tblMarketingProgram;
       this.tblUserLoginDetails = tblUserLoginDetails;
       this.name = name;
       this.dateEvent = dateEvent;
       this.createDate = createDate;
       this.status = status;
       this.tblScheduledEntityLists = tblScheduledEntityLists;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="marketing_category_program_id")
    public TblMarketingProgram getTblMarketingProgram() {
        return this.tblMarketingProgram;
    }
    
    public void setTblMarketingProgram(TblMarketingProgram tblMarketingProgram) {
        this.tblMarketingProgram = tblMarketingProgram;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public TblUserLoginDetails getTblUserLoginDetails() {
        return this.tblUserLoginDetails;
    }
    
    public void setTblUserLoginDetails(TblUserLoginDetails tblUserLoginDetails) {
        this.tblUserLoginDetails = tblUserLoginDetails;
    }

    
    @Column(name="name", length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="url", nullable=false, length=150)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="link_name", nullable=false, length=50)
    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_event", length=29)
    public Date getDateEvent() {
        return this.dateEvent;
    }
    
    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=29)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="status")
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}


