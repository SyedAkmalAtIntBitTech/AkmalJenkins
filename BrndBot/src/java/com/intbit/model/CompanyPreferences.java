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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "company_preferences", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class CompanyPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_preferences_id")
    private Integer companyPreferencesId;
    @Column(name = "company_location")
    private String companyLocation;
    @Column(name = "mindbody_email_list")
    @Type(type = "StringJsonObject")
    private Object mindbodyEmailList;
    @Column(name = "company_preferences")
    @Type(type = "StringJsonObject")
    private Object companyPreferences;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_color_id", referencedColumnName = "global_colors_id")
    @ManyToOne
    private GlobalColors fkColorId;

    public CompanyPreferences() {
    }

    public CompanyPreferences(Integer companyPreferencesId) {
        this.companyPreferencesId = companyPreferencesId;
    }

    public Integer getCompanyPreferencesId() {
        return companyPreferencesId;
    }

    public void setCompanyPreferencesId(Integer companyPreferencesId) {
        this.companyPreferencesId = companyPreferencesId;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public Object getMindbodyEmailList() {
        return mindbodyEmailList;
    }

    public void setMindbodyEmailList(Object mindbodyEmailList) {
        this.mindbodyEmailList = mindbodyEmailList;
    }

    public Object getCompanyPreferences() {
        return companyPreferences;
    }

    public void setCompanyPreferences(Object companyPreferences) {
        this.companyPreferences = companyPreferences;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public GlobalColors getFkColorId() {
        return fkColorId;
    }

    public void setFkColorId(GlobalColors fkColorId) {
        this.fkColorId = fkColorId;
    }

}
