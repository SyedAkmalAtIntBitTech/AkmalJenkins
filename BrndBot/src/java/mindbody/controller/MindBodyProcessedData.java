/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author intbit
 */
public class MindBodyProcessedData {
    private HashMap<String, Object> data_hash_map;
    private String jsonData;
    private String title;

    public HashMap< String, Object> getData_hash_map() {
        return data_hash_map;
    }

    public void setData_hash_map(HashMap<String, Object> data_hash_map) {
        this.data_hash_map = data_hash_map;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getJSON () throws JSONException {
        JSONObject object = new JSONObject();
        object.put("data", getJsonData());
        object.put("title", getTitle());
        return object.toString();
    }
    
}
