package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblUserImages generated by hbm2java
 */
@Entity
@Table(name="tbl_user_images"
    ,schema="public"
)
public class TblUserImages  implements java.io.Serializable {


     private int id;
     private int userId;
     private String imageName;

    public TblUserImages() {
    }

    public TblUserImages(int id, int userId, String imageName) {
       this.id = id;
       this.userId = userId;
       this.imageName = imageName;
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

    
    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Column(name="image_name", nullable=false, length=120)
    public String getImageName() {
        return this.imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }




}


