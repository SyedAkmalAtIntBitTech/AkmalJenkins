/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "image_model")
public class ImageModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_model_id")
    private Integer imageModelId;
    @Column(name = "image_model_name")
    private String imageModelName;
    @Column(name = "layout_file_name")
    private String layoutFileName;
    @Column(name = "model_file_name")
    private String modelFileName;
    @Column(name = "image_file_name")
    private String imageFileName;

    public ImageModel() {
    }

    public ImageModel(Integer imageModelId) {
        this.imageModelId = imageModelId;
    }

    public Integer getImageModelId() {
        return imageModelId;
    }

    public void setImageModelId(Integer imageModelId) {
        this.imageModelId = imageModelId;
    }

    public String getImageModelName() {
        return imageModelName;
    }

    public void setImageModelName(String imageModelName) {
        this.imageModelName = imageModelName;
    }

    public String getLayoutFileName() {
        return layoutFileName;
    }

    public void setLayoutFileName(String layoutFileName) {
        this.layoutFileName = layoutFileName;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

}
