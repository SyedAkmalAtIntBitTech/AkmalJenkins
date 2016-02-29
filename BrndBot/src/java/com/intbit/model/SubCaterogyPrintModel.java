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

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "sub_caterogy_print_model")
public class SubCaterogyPrintModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_caterogy_print_model_id")
    private Integer subCaterogyPrintModelId;
    @JoinColumn(name = "fk_print_model_id", referencedColumnName = "print_model_id")
    @ManyToOne
    private PrintModel fkPrintModelId;
    @JoinColumn(name = "fk_sub_caterogy_id", referencedColumnName = "sub_category_id")
    @ManyToOne
    private SubCategory fkSubCaterogyId;

    public SubCaterogyPrintModel() {
    }

    public SubCaterogyPrintModel(Integer subCaterogyPrintModelId) {
        this.subCaterogyPrintModelId = subCaterogyPrintModelId;
    }

    public Integer getSubCaterogyPrintModelId() {
        return subCaterogyPrintModelId;
    }

    public void setSubCaterogyPrintModelId(Integer subCaterogyPrintModelId) {
        this.subCaterogyPrintModelId = subCaterogyPrintModelId;
    }

    public PrintModel getFkPrintModelId() {
        return fkPrintModelId;
    }

    public void setFkPrintModelId(PrintModel fkPrintModelId) {
        this.fkPrintModelId = fkPrintModelId;
    }

    public SubCategory getFkSubCaterogyId() {
        return fkSubCaterogyId;
    }

    public void setFkSubCaterogyId(SubCategory fkSubCaterogyId) {
        this.fkSubCaterogyId = fkSubCaterogyId;
    }
}
