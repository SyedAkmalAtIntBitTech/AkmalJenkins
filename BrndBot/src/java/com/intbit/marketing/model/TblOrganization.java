package com.intbit.marketing.model;
// Generated 19 Oct, 2015 6:43:25 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblOrganization generated by hbm2java
 */
@Entity
@Table(name="tbl_organization"
    ,schema="public"
)
public class TblOrganization  implements java.io.Serializable {


     private int id;
     private String organizationName;
     private Set tblMarketingCategories = new HashSet(0);

    public TblOrganization() {
    }

	
    public TblOrganization(int id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
    }
    public TblOrganization(int id, String organizationName, Set tblMarketingCategories) {
       this.id = id;
       this.organizationName = organizationName;
       this.tblMarketingCategories = tblMarketingCategories;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="organization_name", nullable=false, length=30)
    public String getOrganizationName() {
        return this.organizationName;
    }
    
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tblOrganization")
    public Set getTblMarketingCategories() {
        return this.tblMarketingCategories;
    }
    
    public void setTblMarketingCategories(Set tblMarketingCategories) {
        this.tblMarketingCategories = tblMarketingCategories;
    }




}


