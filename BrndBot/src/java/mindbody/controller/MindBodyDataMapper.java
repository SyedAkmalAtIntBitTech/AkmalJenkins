/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.Class;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author intbit
 */
public class MindBodyDataMapper {
    
     public static JSONObject mapEnrollmentData(ClassSchedule mindBody_class) {
        
        JSONObject newObject = new JSONObject();
               
        return newObject;
    }

    public static JSONObject mapClassData(Class mindbody_class, String socialEditorLayoutFileName) throws JSONException {
                JSONObject newObject = new JSONObject();
 
        if (mindbody_class != null) {

            for (int i = 0; i < 5; i++) {
                String element = "header1";
                String option = "ClassName";
                String defaultValue = "sandeep";

                if (option.equalsIgnoreCase("ClassName")) {
                    if(mindbody_class.getClassDescription().getName() != null) {
                        newObject.put(element, mindbody_class.getClassDescription().getName());
                    } else {
                        newObject.put(element, defaultValue);
                    }
                       
                }
                 if (option.equalsIgnoreCase("ClassName")) {
                    if(mindbody_class.getClassDescription().getName() != null) {
                        newObject.put(element, mindbody_class.getClassDescription().getName());
                    } else {
                        newObject.put(element, defaultValue);
                    }
                       
                }
            }

        }

        return newObject;
    }
}
