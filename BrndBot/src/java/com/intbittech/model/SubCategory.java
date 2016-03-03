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
@Table(name = "sub_category")
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_id")
    private Integer subCategoryId;
    @Column(name = "sub_category_name")
    private String subCategoryName;
    @JoinColumn(name = "fk_category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Category fkCategoryId;

    public SubCategory() {
    }

    public SubCategory(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Category getFkCategoryId() {
        return fkCategoryId;
    }

    public void setFkCategoryId(Category fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }
}
