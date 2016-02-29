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

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "sub_caterogy_image_model")
public class SubCaterogyImageModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_caterogy_image_model_id")
    private Integer subCaterogyImageModelId;
    @JoinColumn(name = "fk_image_model_id", referencedColumnName = "image_model_id")
    @ManyToOne
    private ImageModel fkImageModelId;
    @JoinColumn(name = "fk_sub_caterogy_id", referencedColumnName = "sub_category_id")
    @ManyToOne
    private SubCategory fkSubCaterogyId;

    public SubCaterogyImageModel() {
    }

    public SubCaterogyImageModel(Integer subCaterogyImageModelId) {
        this.subCaterogyImageModelId = subCaterogyImageModelId;
    }

    public Integer getSubCaterogyImageModelId() {
        return subCaterogyImageModelId;
    }

    public void setSubCaterogyImageModelId(Integer subCaterogyImageModelId) {
        this.subCaterogyImageModelId = subCaterogyImageModelId;
    }

    public ImageModel getFkImageModelId() {
        return fkImageModelId;
    }

    public void setFkImageModelId(ImageModel fkImageModelId) {
        this.fkImageModelId = fkImageModelId;
    }

    public SubCategory getFkSubCaterogyId() {
        return fkSubCaterogyId;
    }

    public void setFkSubCaterogyId(SubCategory fkSubCaterogyId) {
        this.fkSubCaterogyId = fkSubCaterogyId;
    }
}
