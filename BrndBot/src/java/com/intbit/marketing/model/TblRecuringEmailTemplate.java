package com.intbit.marketing.model;
// Generated Oct 28, 2015 12:01:09 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TblRecuringEmailTemplate generated by hbm2java
 */
@Entity
@Table(name="tbl_recuring_email_template"
    ,schema="public"
)
public class TblRecuringEmailTemplate  implements java.io.Serializable {


     private int id;
     private String template;

    public TblRecuringEmailTemplate() {
    }

	
    public TblRecuringEmailTemplate(int id) {
        this.id = id;
    }
    public TblRecuringEmailTemplate(int id, String template) {
       this.id = id;
       this.template = template;
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

    
    @Column(name="template")
    public String getTemplate() {
        return this.template;
    }
    
    public void setTemplate(String template) {
        this.template = template;
    }




}


