/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import com.intbittech.externalcontent.ExternalSourceProcessedData;
import java.util.HashMap;
import org.json.JSONException;
import org.json.simple.JSONArray;
/**
 *
 * @author intbit
 */
public class MindBodyProcessedData extends ExternalSourceProcessedData {
    private HashMap<String, Object> data_hash_map;
    private JSONArray jsonData;
    private JSONArray columnHeader;
    private String title;

    public HashMap< String, Object> getData_hash_map() {
        return data_hash_map;
    }

    public void setData_hash_map(HashMap<String, Object> data_hash_map) {
        this.data_hash_map = data_hash_map;
    }

    public JSONArray getJsonData() {
        return jsonData;
    }

    public void setJsonData(JSONArray jsonData) {
        this.jsonData = jsonData;
    }
    
    public JSONArray getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(JSONArray columnHeader) {
        this.columnHeader = columnHeader;
    }
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     *
     * @return
     * @throws JSONException
     */
    @Override
    public org.json.simple.JSONObject getJSON () throws JSONException {
        org.json.simple.JSONObject object = new org.json.simple.JSONObject();
        object.put("mindbody_data", getJsonData());
        object.put("title", getTitle());
        object.put("column_header", getColumnHeader());
        return object;
    }
    
}
