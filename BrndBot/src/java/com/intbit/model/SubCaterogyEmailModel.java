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
@Table(name = "sub_caterogy_email_model")
public class SubCaterogyEmailModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_caterogy_email_model_id")
    private Integer subCaterogyEmailModelId;
    @JoinColumn(name = "fk_email_model_id", referencedColumnName = "email_model_id")
    @ManyToOne
    private EmailModel fkEmailModelId;
    @JoinColumn(name = "fk_sub_caterogy_id", referencedColumnName = "sub_category_id")
    @ManyToOne
    private SubCategory fkSubCaterogyId;

    public SubCaterogyEmailModel() {
    }

    public SubCaterogyEmailModel(Integer subCaterogyEmailModelId) {
        this.subCaterogyEmailModelId = subCaterogyEmailModelId;
    }

    public Integer getSubCaterogyEmailModelId() {
        return subCaterogyEmailModelId;
    }

    public void setSubCaterogyEmailModelId(Integer subCaterogyEmailModelId) {
        this.subCaterogyEmailModelId = subCaterogyEmailModelId;
    }

    public EmailModel getFkEmailModelId() {
        return fkEmailModelId;
    }

    public void setFkEmailModelId(EmailModel fkEmailModelId) {
        this.fkEmailModelId = fkEmailModelId;
    }

    public SubCategory getFkSubCaterogyId() {
        return fkSubCaterogyId;
    }

    public void setFkSubCaterogyId(SubCategory fkSubCaterogyId) {
        this.fkSubCaterogyId = fkSubCaterogyId;
    }
}
