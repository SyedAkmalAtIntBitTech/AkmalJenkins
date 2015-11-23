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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblBrandPersonality generated by hbm2java
 */
@Entity
@Table(name="tbl_brand_personality"
    ,schema="public"
)
public class TblBrandPersonality  implements java.io.Serializable {


     private int id;
     private String brandName;
     private Integer lookId;
     private String image;
     private Set tblUserPreferenceses = new HashSet(0);

    public TblBrandPersonality() {
    }

	
    public TblBrandPersonality(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }
    public TblBrandPersonality(int id, String brandName, Integer lookId, String image, Set tblUserPreferenceses) {
       this.id = id;
       this.brandName = brandName;
       this.lookId = lookId;
       this.image = image;
       this.tblUserPreferenceses = tblUserPreferenceses;
    }
   
     @Id 

    
//    @GenericGenerator(name="id" , strategy="increment")
//    @GeneratedValue(generator="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="brand_name", nullable=false, length=30)
    public String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    @Column(name="look_id")
    public Integer getLookId() {
        return this.lookId;
    }
    
    public void setLookId(Integer lookId) {
        this.lookId = lookId;
    }

    
    @Column(name="image", length=120)
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }






}


