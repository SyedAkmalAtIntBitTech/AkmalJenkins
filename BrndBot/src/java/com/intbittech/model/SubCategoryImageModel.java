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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "sub_category_image_model")
public class SubCategoryImageModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_image_model_id")
    private Integer subCategoryImageModelId;
    @JoinColumn(name = "fk_image_model_id", referencedColumnName = "image_model_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ImageModel fkImageModelId;
    @JoinColumn(name = "fk_sub_category_id", referencedColumnName = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory fkSubCategoryId;

    public SubCategoryImageModel() {
    }

    public SubCategoryImageModel(Integer subCategoryImageModelId) {
        this.subCategoryImageModelId = subCategoryImageModelId;
    }

    public Integer getSubCategoryImageModelId() {
        return subCategoryImageModelId;
    }

    public void setSubCategoryImageModelId(Integer subCategoryImageModelId) {
        this.subCategoryImageModelId = subCategoryImageModelId;
    }

    public ImageModel getFkImageModelId() {
        return fkImageModelId;
    }

    public void setFkImageModelId(ImageModel fkImageModelId) {
        this.fkImageModelId = fkImageModelId;
    }

    public SubCategory getFkSubCategoryId() {
        return fkSubCategoryId;
    }

    public void setFkSubCategoryId(SubCategory fkSubCategoryId) {
        this.fkSubCategoryId = fkSubCategoryId;
    }
}
