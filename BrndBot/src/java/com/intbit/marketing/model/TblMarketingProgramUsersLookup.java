package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblMarketingProgramUsersLookup generated by hbm2java
 */
@Entity
@Table(name="tbl_marketing_program_users_lookup"
    ,schema="public"
)
public class TblMarketingProgramUsersLookup  implements java.io.Serializable {


     private int id;
     private TblMarketingProgram tblMarketingProgram;
     private TblUserLoginDetails tblUserLoginDetails;

    public TblMarketingProgramUsersLookup() {
    }

	
    public TblMarketingProgramUsersLookup(int id) {
        this.id = id;
    }
    public TblMarketingProgramUsersLookup(int id, TblMarketingProgram tblMarketingProgram, TblUserLoginDetails tblUserLoginDetails) {
       this.id = id;
       this.tblMarketingProgram = tblMarketingProgram;
       this.tblUserLoginDetails = tblUserLoginDetails;
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




}


