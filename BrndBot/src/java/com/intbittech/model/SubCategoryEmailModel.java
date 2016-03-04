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
@Table(name = "sub_category_email_model")
public class SubCategoryEmailModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_email_model_id")
    private Integer subCategoryEmailModelId;
    @JoinColumn(name = "fk_email_model_id", referencedColumnName = "email_model_id")
    @ManyToOne
    private EmailModel fkEmailModelId;
    @JoinColumn(name = "fk_sub_category_id", referencedColumnName = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory fkSubCategoryId;

    public SubCategoryEmailModel() {
    }

    public SubCategoryEmailModel(Integer subCategoryEmailModelId) {
        this.subCategoryEmailModelId = subCategoryEmailModelId;
    }

    public Integer getSubCategoryEmailModelId() {
        return subCategoryEmailModelId;
    }

    public void setSubCategoryEmailModelId(Integer subCategoryEmailModelId) {
        this.subCategoryEmailModelId = subCategoryEmailModelId;
    }

    public EmailModel getFkEmailModelId() {
        return fkEmailModelId;
    }

    public void setFkEmailModelId(EmailModel fkEmailModelId) {
        this.fkEmailModelId = fkEmailModelId;
    }

    public SubCategory getFkSubCategoryId() {
        return fkSubCategoryId;
    }

    public void setFkSubCategoryId(SubCategory fkSubCategoryId) {
        this.fkSubCategoryId = fkSubCategoryId;
    }
}
