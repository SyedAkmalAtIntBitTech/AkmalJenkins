package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
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
 * TblMarketingAction generated by hbm2java
 */
@Entity
@Table(name="tbl_marketing_action"
    ,schema="public"
)
public class TblMarketingAction  implements java.io.Serializable {


     private int id;
     private TblMarketingCategory tblMarketingCategory;
     private TblMarketingProgram tblMarketingProgram;
     private Serializable jsonTemplate;

    public TblMarketingAction() {
    }

	
    public TblMarketingAction(int id, TblMarketingProgram tblMarketingProgram) {
        this.id = id;
        this.tblMarketingProgram = tblMarketingProgram;
    }
    public TblMarketingAction(int id, TblMarketingCategory tblMarketingCategory, TblMarketingProgram tblMarketingProgram, Serializable jsonTemplate) {
       this.id = id;
       this.tblMarketingCategory = tblMarketingCategory;
       this.tblMarketingProgram = tblMarketingProgram;
       this.jsonTemplate = jsonTemplate;
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
    @JoinColumn(name="marketing_category_id")
    public TblMarketingCategory getTblMarketingCategory() {
        return this.tblMarketingCategory;
    }
    
    public void setTblMarketingCategory(TblMarketingCategory tblMarketingCategory) {
        this.tblMarketingCategory = tblMarketingCategory;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="marketing_program_id", nullable=false)
    public TblMarketingProgram getTblMarketingProgram() {
        return this.tblMarketingProgram;
    }
    
    public void setTblMarketingProgram(TblMarketingProgram tblMarketingProgram) {
        this.tblMarketingProgram = tblMarketingProgram;
    }

    
    @Column(name="json_template")
    public Serializable getJsonTemplate() {
        return this.jsonTemplate;
    }
    
    public void setJsonTemplate(Serializable jsonTemplate) {
        this.jsonTemplate = jsonTemplate;
    }




}


