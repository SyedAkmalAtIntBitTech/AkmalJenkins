package com.intbit.marketing.model;
// Generated 19 Oct, 2015 6:43:25 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TblScheduledEmailRecipients generated by hbm2java
 */
@Entity
@Table(name="tbl_scheduled_email_recipients"
    ,schema="public"
)
public class TblScheduledEmailRecipients  implements java.io.Serializable {


     private TblScheduledEmailRecipientsId id;

    public TblScheduledEmailRecipients() {
    }

    public TblScheduledEmailRecipients(TblScheduledEmailRecipientsId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="scheduledEmailId", column=@Column(name="scheduled_email_id", nullable=false) ), 
        @AttributeOverride(name="toEmailAddress", column=@Column(name="to_email_address", nullable=false, length=512) ) } )
    public TblScheduledEmailRecipientsId getId() {
        return this.id;
    }
    
    public void setId(TblScheduledEmailRecipientsId id) {
        this.id = id;
    }




}


