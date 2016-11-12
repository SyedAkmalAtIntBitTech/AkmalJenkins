/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.intbittech.modelmappers.MarketingActionsObjectDetails;
import com.intbittech.modelmappers.TaskDetails;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 *
 * @author AR
 */
public class StringUtility {

    public static String safeBoolean(boolean valid) {
        return valid ? "true" : "false";
    }

    public static boolean isEmpty(final String value) {
        return (value == null || (value.trim().length() == 0) || value.equalsIgnoreCase("null"));
    }
    
    public static String objectListToJsonString(MarketingActionsObjectDetails marketingActionsObjectDetails) throws IOException {
        
        final StringWriter stringWriter =new StringWriter();
    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(stringWriter, marketingActionsObjectDetails);
    return stringWriter.toString();
        
    }
    
    public static String objectListToJsonString(Object object) throws IOException {

        if (object != null){
            final StringWriter stringWriter =new StringWriter();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(stringWriter, object);

            return stringWriter.toString();
            
        }
        return null;
    }    
    public static List jsonStringToObjectList(String jsonString) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jFactory = new JsonFactory();
        jsonString = "["+jsonString+"]";
        JsonParser jsonParser= jFactory.createParser(jsonString);
        List<MarketingActionsObjectDetails> myObjectList = mapper.readValue(jsonParser, mapper.getTypeFactory().constructCollectionType(List.class, MarketingActionsObjectDetails.class));
        return myObjectList;
    }

    public static TaskDetails stringToTaskDetails(String content){
        Gson gson = new Gson(); 
        TaskDetails object = null;
        if (content != null){
            object = gson.fromJson(content, TaskDetails.class);                
            return object;
        }
        return object;
    }
    public static Object stringToObject(String content){
        Gson gson = new Gson(); 
        Object object = null;
        if (content != null){
            object = gson.fromJson(content, Object.class);                
            return object;
        }
        return object;
    }    
    public static JSONObject stringToJSONObject(String content){
        Gson gson = new Gson(); 
        JSONObject object = null;
        if (content != null){
            object = gson.fromJson(content, JSONObject.class);                
            return object;
        }
        return object;
    }    

    public static Boolean safeBoolean(String value) {
        if (isEmpty(value)) {
            return false;
        } else if(value.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

}
