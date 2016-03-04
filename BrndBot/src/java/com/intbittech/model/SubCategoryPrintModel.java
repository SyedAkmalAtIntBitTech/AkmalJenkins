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
@Table(name = "sub_category_print_model")
public class SubCategoryPrintModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_print_model_id")
    private Integer subCategoryPrintModelId;
    @JoinColumn(name = "fk_print_model_id", referencedColumnName = "print_model_id")
    @ManyToOne
    private PrintModel fkPrintModelId;
    @JoinColumn(name = "fk_sub_category_id", referencedColumnName = "sub_category_id")
    @ManyToOne
    private SubCategory fkSubCategoryId;

    public SubCategoryPrintModel() {
    }

    public SubCategoryPrintModel(Integer subCategoryPrintModelId) {
        this.subCategoryPrintModelId = subCategoryPrintModelId;
    }

    public Integer getSubCategoryPrintModelId() {
        return subCategoryPrintModelId;
    }

    public void setSubCategoryPrintModelId(Integer subCategoryPrintModelId) {
        this.subCategoryPrintModelId = subCategoryPrintModelId;
    }

    public PrintModel getFkPrintModelId() {
        return fkPrintModelId;
    }

    public void setFkPrintModelId(PrintModel fkPrintModelId) {
        this.fkPrintModelId = fkPrintModelId;
    }

    public SubCategory getFkSubCategoryId() {
        return fkSubCategoryId;
    }

    public void setFkSubCategoryId(SubCategory fkSubCategoryId) {
        this.fkSubCategoryId = fkSubCategoryId;
    }
}
