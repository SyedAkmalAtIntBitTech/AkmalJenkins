/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

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
@Table(name = "scheduled_entity_list")
public class ScheduledEntityList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduled_entity_list_id")
    private Integer scheduledEntityListId;
    @Column(name = "entity_id")
    private Integer entityId;
    @Column(name = "schedule_title")
    private String scheduleTitle;
    @Column(name = "schedule_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleTime;
    @Column(name = "entity_type")
    private String entityType;
    @Column(name = "status")
    private String status;
    @Column(name = "schedule_desc")
    private String scheduleDesc;
    @Column(name = "is_recuring")
    private Boolean isRecuring;
    @Column(name = "days")
    private Integer days;
    @Column(name = "till_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tillDate;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_company_marketing_program_id", referencedColumnName = "company_marketing_program_id")
    @ManyToOne
    private CompanyMarketingProgram fkCompanyMarketingProgramId;
    @JoinColumn(name = "fk_recuring_email_id", referencedColumnName = "recuring_email_template_id")
    @ManyToOne
    private RecuringEmailTemplate fkRecuringEmailId;

    public ScheduledEntityList() {
    }

    public ScheduledEntityList(Integer scheduledEntityListId) {
        this.scheduledEntityListId = scheduledEntityListId;
    }

    public Integer getScheduledEntityListId() {
        return scheduledEntityListId;
    }

    public void setScheduledEntityListId(Integer scheduledEntityListId) {
        this.scheduledEntityListId = scheduledEntityListId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduleDesc() {
        return scheduleDesc;
    }

    public void setScheduleDesc(String scheduleDesc) {
        this.scheduleDesc = scheduleDesc;
    }

    public Boolean getIsRecuring() {
        return isRecuring;
    }

    public void setIsRecuring(Boolean isRecuring) {
        this.isRecuring = isRecuring;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getTillDate() {
        return tillDate;
    }

    public void setTillDate(Date tillDate) {
        this.tillDate = tillDate;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public CompanyMarketingProgram getFkCompanyMarketingProgramId() {
        return fkCompanyMarketingProgramId;
    }

    public void setFkCompanyMarketingProgramId(CompanyMarketingProgram fkCompanyMarketingProgramId) {
        this.fkCompanyMarketingProgramId = fkCompanyMarketingProgramId;
    }

    public RecuringEmailTemplate getFkRecuringEmailId() {
        return fkRecuringEmailId;
    }

    public void setFkRecuringEmailId(RecuringEmailTemplate fkRecuringEmailId) {
        this.fkRecuringEmailId = fkRecuringEmailId;
    }

}