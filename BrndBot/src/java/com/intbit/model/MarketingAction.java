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
@Table(name = "marketing_action", schema = "public"
)
@TypeDefs({
    @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class MarketingAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marketing_action_id")
    private Integer marketingActionId;
    @Column(name = "json_template")
    @Type(type = "StringJsonObject")
    private Object jsonTemplate;
    @JoinColumn(name = "fk_marketing_program_id", referencedColumnName = "marketing_program_id")
    @ManyToOne
    private MarketingProgram fkMarketingProgramId;

    public MarketingAction() {
    }

    public MarketingAction(Integer marketingActionId) {
        this.marketingActionId = marketingActionId;
    }

    public Integer getMarketingActionId() {
        return marketingActionId;
    }

    public void setMarketingActionId(Integer marketingActionId) {
        this.marketingActionId = marketingActionId;
    }

    public Object getJsonTemplate() {
        return jsonTemplate;
    }

    public void setJsonTemplate(Object jsonTemplate) {
        this.jsonTemplate = jsonTemplate;
    }

    public MarketingProgram getFkMarketingProgramId() {
        return fkMarketingProgramId;
    }

    public void setFkMarketingProgramId(MarketingProgram fkMarketingProgramId) {
        this.fkMarketingProgramId = fkMarketingProgramId;
    }
}
