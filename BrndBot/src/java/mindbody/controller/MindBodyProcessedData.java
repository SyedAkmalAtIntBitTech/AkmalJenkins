/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import java.util.HashMap;

/**
 *
 * @author intbit
 */
public class MindBodyProcessedData {
    private HashMap<String, Object> data_hash_map;
    private String jsonDisplayString;

    public MindBodyProcessedData(HashMap<String, Object> data_hash_map, String jsonDisplayString) {
        this.data_hash_map = data_hash_map;
        this.jsonDisplayString = jsonDisplayString;
    }

    public HashMap< String, Object> getData_hash_map() {
        return data_hash_map;
    }

    public void setData_hash_map(HashMap<String, Object> data_hash_map) {
        this.data_hash_map = data_hash_map;
    }

    public String getJsonDisplayString() {
        return jsonDisplayString;
    }

    public void setJsonDisplayString(String jsonDisplayString) {
        this.jsonDisplayString = jsonDisplayString;
    }
    
}
