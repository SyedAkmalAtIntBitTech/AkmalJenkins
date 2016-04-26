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
    @Column(name = "email_list")
    @Type(type = "StringJsonObject")
    private String emailList;
    @Column(name = "company_preferences")
    @Type(type = "StringJsonObject")
    private String companyPreferences;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;

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

    public String getEmailList() {
        return emailList;
    }

    public void setEmailList(String emailList) {
        this.emailList = emailList;
    }

    public String getCompanyPreferences() {
        return companyPreferences;
    }

    public void setCompanyPreferences(String companyPreferences) {
        this.companyPreferences = companyPreferences;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

}
