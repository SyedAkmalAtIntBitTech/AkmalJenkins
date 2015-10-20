package com.intbit.marketing.model;
// Generated 19 Oct, 2015 6:43:25 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TblScheduledEmailRecipientsId generated by hbm2java
 */
@Embeddable
public class TblScheduledEmailRecipientsId  implements java.io.Serializable {


     private int scheduledEmailId;
     private String toEmailAddress;

    public TblScheduledEmailRecipientsId() {
    }

    public TblScheduledEmailRecipientsId(int scheduledEmailId, String toEmailAddress) {
       this.scheduledEmailId = scheduledEmailId;
       this.toEmailAddress = toEmailAddress;
    }
   


    @Column(name="scheduled_email_id", nullable=false)
    public int getScheduledEmailId() {
        return this.scheduledEmailId;
    }
    
    public void setScheduledEmailId(int scheduledEmailId) {
        this.scheduledEmailId = scheduledEmailId;
    }


    @Column(name="to_email_address", nullable=false, length=512)
    public String getToEmailAddress() {
        return this.toEmailAddress;
    }
    
    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TblScheduledEmailRecipientsId) ) return false;
		 TblScheduledEmailRecipientsId castOther = ( TblScheduledEmailRecipientsId ) other; 
         
		 return (this.getScheduledEmailId()==castOther.getScheduledEmailId())
 && ( (this.getToEmailAddress()==castOther.getToEmailAddress()) || ( this.getToEmailAddress()!=null && castOther.getToEmailAddress()!=null && this.getToEmailAddress().equals(castOther.getToEmailAddress()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getScheduledEmailId();
         result = 37 * result + ( getToEmailAddress() == null ? 0 : this.getToEmailAddress().hashCode() );
         return result;
   }   


}


