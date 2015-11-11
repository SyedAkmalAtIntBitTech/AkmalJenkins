package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblColors generated by hbm2java
 */
@Entity
@Table(name="tbl_colors"
    ,schema="public"
)
public class TblColors  implements java.io.Serializable {


     private int id;
     private String colorHex;
     private String colorName;

    public TblColors() {
    }

    public TblColors(int id, String colorHex, String colorName) {
       this.id = id;
       this.colorHex = colorHex;
       this.colorName = colorName;
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

    
    @Column(name="color_hex", nullable=false, length=30)
    public String getColorHex() {
        return this.colorHex;
    }
    
    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    
    @Column(name="color_name", nullable=false, length=20)
    public String getColorName() {
        return this.colorName;
    }
    
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }




}


