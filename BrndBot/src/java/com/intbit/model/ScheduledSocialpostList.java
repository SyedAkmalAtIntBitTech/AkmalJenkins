/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

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
    @Column(name = "token_data")
    @Type(type = "StringJsonObject")
    private Object tokenData;
    @Column(name = "meta_data")
    @Type(type = "StringJsonObject")
    private Object metaData;
    @Column(name = "type")
    private String type;
    @Column(name = "image_type")
    private String imageType;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_scheduled_entity_list_id", referencedColumnName = "scheduled_entity_list_id")
    @ManyToOne
    private ScheduledEntityList fkScheduledEntityListId;

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

    public Object getTokenData() {
        return tokenData;
    }

    public void setTokenData(Object tokenData) {
        this.tokenData = tokenData;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
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

    public ScheduledEntityList getFkScheduledEntityListId() {
        return fkScheduledEntityListId;
    }

    public void setFkScheduledEntityListId(ScheduledEntityList fkScheduledEntityListId) {
        this.fkScheduledEntityListId = fkScheduledEntityListId;
    }

}
