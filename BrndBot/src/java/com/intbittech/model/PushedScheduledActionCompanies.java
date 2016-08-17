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
@Table(name = "pushed_scheduled_action_companies")
public class PushedScheduledActionCompanies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pushed_scheduled_action_companies_id")
    private Integer pushedScheduledActionCompaniesId;
    @Column(name = "pushed_scheduled_action_entity_list_id")
    private Integer pushed_scheduled_action_entity_list_id;
    @JoinColumn(name = "fk_channel_id", referencedColumnName = "channel_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Channel fkChannelId;

    public PushedScheduledActionCompanies() {
    }

    public PushedScheduledActionCompanies(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Channel getFkChannelId() {
        return fkChannelId;
    }

    public void setFkChannelId(Channel fkChannelId) {
        this.fkChannelId = fkChannelId;
    }
}
