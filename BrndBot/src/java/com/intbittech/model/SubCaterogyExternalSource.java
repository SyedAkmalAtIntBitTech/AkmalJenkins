/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "sub_caterogy_external_source")
public class SubCaterogyExternalSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_caterogy_external_source_id")
    private Integer subCaterogyExternalSourceId;
    @JoinColumn(name = "fk_external_source_id", referencedColumnName = "external_source_id")
    @ManyToOne
    private ExternalSource fkExternalSourceId;
    @JoinColumn(name = "fk_external_source_keyword_id", referencedColumnName = "external_source_keyword_id")
    @ManyToOne
    private ExternalSourceKeyword fkExternalSourceKeywordId;
    @JoinColumn(name = "fk_sub_caterogy_id", referencedColumnName = "sub_category_id")
    @ManyToOne
    private SubCategory fkSubCaterogyId;

    public SubCaterogyExternalSource() {
    }

    public SubCaterogyExternalSource(Integer subCaterogyExternalSourceId) {
        this.subCaterogyExternalSourceId = subCaterogyExternalSourceId;
    }

    public Integer getSubCaterogyExternalSourceId() {
        return subCaterogyExternalSourceId;
    }

    public void setSubCaterogyExternalSourceId(Integer subCaterogyExternalSourceId) {
        this.subCaterogyExternalSourceId = subCaterogyExternalSourceId;
    }

    public ExternalSource getFkExternalSourceId() {
        return fkExternalSourceId;
    }

    public void setFkExternalSourceId(ExternalSource fkExternalSourceId) {
        this.fkExternalSourceId = fkExternalSourceId;
    }

    public ExternalSourceKeyword getFkExternalSourceKeywordId() {
        return fkExternalSourceKeywordId;
    }

    public void setFkExternalSourceKeywordId(ExternalSourceKeyword fkExternalSourceKeywordId) {
        this.fkExternalSourceKeywordId = fkExternalSourceKeywordId;
    }

    public SubCategory getFkSubCaterogyId() {
        return fkSubCaterogyId;
    }

    public void setFkSubCaterogyId(SubCategory fkSubCaterogyId) {
        this.fkSubCaterogyId = fkSubCaterogyId;
    }
}
