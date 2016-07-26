/*
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author Sandeep Kumar at IntBit Technologies.
 */

@Entity
@Table(name = "company_invite", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyInvite.findAll", query = "SELECT c FROM CompanyInvite c"),
    @NamedQuery(name = "CompanyInvite.findById", query = "SELECT c FROM CompanyInvite c WHERE c.id = :id"),
    @NamedQuery(name = "CompanyInvite.findByTaskCode", query = "SELECT c FROM CompanyInvite c WHERE c.taskCode = :taskCode"),
    @NamedQuery(name = "CompanyInvite.findByCreatedDateTime", query = "SELECT c FROM CompanyInvite c WHERE c.createdDateTime = :createdDateTime"),
    @NamedQuery(name = "CompanyInvite.findByMessage", query = "SELECT c FROM CompanyInvite c WHERE c.message = :message"),
    @NamedQuery(name = "CompanyInvite.findByIsUsed", query = "SELECT c FROM CompanyInvite c WHERE c.isUsed = :isUsed")})
public class CompanyInvite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "task_code")
    private String taskCode;
    @Column(name = "created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Column(name = "message")
    private String message;
    @Column(name = "is_used")
    private Boolean isUsed;
    @Column(name = "task")
    @Type(type = "StringJsonObject")
    private String task;
    @JoinColumn(name = "invite_sent_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users inviteSentBy;
    @JoinColumn(name = "invite_sent_to", referencedColumnName = "user_id")
    @ManyToOne
    private Users inviteSentTo;

    public CompanyInvite() {
    }

    public CompanyInvite(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Users getInviteSentBy() {
        return inviteSentBy;
    }

    public void setInviteSentBy(Users inviteSentBy) {
        this.inviteSentBy = inviteSentBy;
    }

    public Users getInviteSentTo() {
        return inviteSentTo;
    }

    public void setInviteSentTo(Users inviteSentTo) {
        this.inviteSentTo = inviteSentTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyInvite)) {
            return false;
        }
        CompanyInvite other = (CompanyInvite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbittech.model.CompanyInvite[ id=" + id + " ]";
    }
    
}
