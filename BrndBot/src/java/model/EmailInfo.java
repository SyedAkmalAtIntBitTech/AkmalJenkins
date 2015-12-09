/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.intbit.AppConstants;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author development
 */
public class EmailInfo {
    String emailAddress;
    String firstName;
    String lastName;
    String addedDate;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public EmailInfo() {
    }

    public EmailInfo(String emailAddress, String addedDate) {
        this.emailAddress = emailAddress;
        this.addedDate = addedDate;
//        UUID uid = UUID.fromString("Email");
        UUID uniqueKey = UUID.randomUUID();
        this.id = uniqueKey.toString();
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public EmailInfo(String emailAddress, String firstName, String lastName, String addedDate) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addedDate = addedDate;
        UUID uniqueKey = UUID.randomUUID();
        this.id = uniqueKey.toString();        
    }
    
    public EmailInfo(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }    
    
    public JSONObject getEmailInfoJSONObject() throws ParseException {
        String str_model = (String) AppConstants.GSON.toJson(this);
        JSONParser json_parser = new JSONParser();
        JSONObject json_object = (JSONObject) json_parser.parse(str_model);
        return json_object;
    }
    
    public static EmailInfo fromJSON(String jsonString){
        return AppConstants.GSON.fromJson(jsonString, EmailInfo.class);
    }
}
