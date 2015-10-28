package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.util.Date;
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
 * TblScheduledEntityList generated by hbm2java
 */
@Entity
@Table(name="tbl_scheduled_entity_list"
    ,schema="public"
)
public class TblScheduledEntityList  implements java.io.Serializable {


     private int id;
     private TblUserMarketingProgram tblUserMarketingProgram;
     private Integer entityId;
     private String scheduleTitle;
     private Date scheduleTime;
     private String entityType;
     private String status;
     private Integer userId;
     private String scheduleDesc;
     private Boolean isRecuring;

    public TblScheduledEntityList() {
    }

	
    public TblScheduledEntityList(int id) {
        this.id = id;
    }
    public TblScheduledEntityList(int id, TblUserMarketingProgram tblUserMarketingProgram, Integer entityId, String scheduleTitle, Date scheduleTime, String entityType, String status, Integer userId, String scheduleDesc, Boolean isRecuring) {
       this.id = id;
       this.tblUserMarketingProgram = tblUserMarketingProgram;
       this.entityId = entityId;
       this.scheduleTitle = scheduleTitle;
       this.scheduleTime = scheduleTime;
       this.entityType = entityType;
       this.status = status;
       this.userId = userId;
       this.scheduleDesc = scheduleDesc;
       this.isRecuring = isRecuring;
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
    @JoinColumn(name="user_marketing_program_id")
    public TblUserMarketingProgram getTblUserMarketingProgram() {
        return this.tblUserMarketingProgram;
    }
    
    public void setTblUserMarketingProgram(TblUserMarketingProgram tblUserMarketingProgram) {
        this.tblUserMarketingProgram = tblUserMarketingProgram;
    }

    
    @Column(name="entity_id")
    public Integer getEntityId() {
        return this.entityId;
    }
    
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    
    @Column(name="schedule_title", length=1024)
    public String getScheduleTitle() {
        return this.scheduleTitle;
    }
    
    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="schedule_time", length=35)
    public Date getScheduleTime() {
        return this.scheduleTime;
    }
    
    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    
    @Column(name="entity_type", length=250)
    public String getEntityType() {
        return this.entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    
    @Column(name="status", length=50)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="user_id")
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    @Column(name="schedule_desc", length=2048)
    public String getScheduleDesc() {
        return this.scheduleDesc;
    }
    
    public void setScheduleDesc(String scheduleDesc) {
        this.scheduleDesc = scheduleDesc;
    }

    
    @Column(name="is_recuring")
    public Boolean getIsRecuring() {
        return this.isRecuring;
    }
    
    public void setIsRecuring(Boolean isRecuring) {
        this.isRecuring = isRecuring;
    }




}


