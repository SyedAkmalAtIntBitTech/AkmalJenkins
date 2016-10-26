/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "scheduled_socialpost_list", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class ScheduledSocialpostList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduled_socialpost_list_id")
    private Integer scheduledSocialpostListId;
    @Column(name = "image_name")
    private String imageName;

    @Column(name = "meta_data")
    @Type(type = "StringJsonObject")
    private String metaData;
    @Column(name = "type")
    private String type;
    @Column(name = "image_type")
    private String imageType;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;

    public ScheduledSocialpostList() {
    }

    public ScheduledSocialpostList(Integer scheduledSocialpostListId) {
        this.scheduledSocialpostListId = scheduledSocialpostListId;
    }

    public Integer getScheduledSocialpostListId() {
        return scheduledSocialpostListId;
    }

    public void setScheduledSocialpostListId(Integer scheduledSocialpostListId) {
        this.scheduledSocialpostListId = scheduledSocialpostListId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

}
