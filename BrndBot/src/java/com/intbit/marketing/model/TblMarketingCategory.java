package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblMarketingCategory generated by hbm2java
 */
@Entity
@Table(name="tbl_marketing_category"
    ,schema="public"
)
public class TblMarketingCategory  implements java.io.Serializable {


     private int id;
     private TblOrganization tblOrganization;
     private String name;
     private Integer categoryOrder;
     private byte[] image;
     private Set tblMarketingActions = new HashSet(0);
     private Set tblMarketingCategoryUsersLookups = new HashSet(0);
     private Set tblMarketingPrograms = new HashSet(0);

    public TblMarketingCategory() {
    }

	
    public TblMarketingCategory(int id) {
        this.id = id;
    }
    public TblMarketingCategory(int id, TblOrganization tblOrganization, String name, Integer categoryOrder, byte[] image, Set tblMarketingActions, Set tblMarketingCategoryUsersLookups, Set tblMarketingPrograms) {
       this.id = id;
       this.tblOrganization = tblOrganization;
       this.name = name;
       this.categoryOrder = categoryOrder;
       this.image = image;
       this.tblMarketingActions = tblMarketingActions;
       this.tblMarketingCategoryUsersLookups = tblMarketingCategoryUsersLookups;
       this.tblMarketingPrograms = tblMarketingPrograms;
    }
   
     @Id 

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="organization_id")
    public TblOrganization getTblOrganization() {
        return this.tblOrganization;
    }
    
    public void setTblOrganization(TblOrganization tblOrganization) {
        this.tblOrganization = tblOrganization;
    }

    
    @Column(name="name", length=150)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="category_order")
    public Integer getCategoryOrder() {
        return this.categoryOrder;
    }
    
    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    
    @Column(name="image")
    public byte[] getImage() {
        return this.image;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }






}


