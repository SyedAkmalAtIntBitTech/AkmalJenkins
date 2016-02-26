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
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "print_model")
public class PrintModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "print_model_id")
    private Integer printModelId;
    @Column(name = "print_model_name")
    private String printModelName;
    @Column(name = "layout_file_name")
    private String layoutFileName;
    @Column(name = "model_file_name")
    private String modelFileName;
    @Column(name = "image_file_name")
    private String imageFileName;

    public PrintModel() {
    }

    public PrintModel(Integer printModelId) {
        this.printModelId = printModelId;
    }

    public Integer getPrintModelId() {
        return printModelId;
    }

    public void setPrintModelId(Integer printModelId) {
        this.printModelId = printModelId;
    }

    public String getPrintModelName() {
        return printModelName;
    }

    public void setPrintModelName(String printModelName) {
        this.printModelName = printModelName;
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
