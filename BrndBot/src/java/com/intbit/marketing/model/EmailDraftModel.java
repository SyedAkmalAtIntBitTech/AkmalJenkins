/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.model;

/**
 *
 * @author development
 */
public class EmailDraftModel {
    private Integer category_id;
    private Integer sub_category_id;
    private String email_subject;
    private String email_addresses;
    private String email_list_name;
    private String html_body_string;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(Integer sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getEmail_subject() {
        return email_subject;
    }

    public void setEmail_subject(String email_subject) {
        this.email_subject = email_subject;
    }

    public String getEmail_addresses() {
        return email_addresses;
    }

    public void setEmail_addresses(String email_addresses) {
        this.email_addresses = email_addresses;
    }

    public String getEmail_list_name() {
        return email_list_name;
    }

    public void setEmail_list_name(String email_list_name) {
        this.email_list_name = email_list_name;
    }

    public String getHtml_body_string() {
        return html_body_string;
    }

    public void setHtml_body_string(String html_body_string) {
        this.html_body_string = html_body_string;
    }

}
