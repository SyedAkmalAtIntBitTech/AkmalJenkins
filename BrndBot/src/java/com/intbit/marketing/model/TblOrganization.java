package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

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

        @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")

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





}


