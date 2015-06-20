/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author intbit
 */
public class TblSubCategories {
    private Integer id;
    private String external_source;
    private String external_source_keyword;
    private String sub_category_name;
    public String category_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternal_source() {
        return external_source;
    }

    public void setExternal_source(String external_source) {
        this.external_source = external_source;
    }

    public String getExternal_source_keyword() {
        return external_source_keyword;
    }

    public void setExternal_source_keyword(String external_source_keyword) {
        this.external_source_keyword = external_source_keyword;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    
    
}
