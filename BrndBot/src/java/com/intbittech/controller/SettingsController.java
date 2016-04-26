/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.BrndBotBaseHttpServlet;
import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.IConstants;
import com.controller.SqlMethods;
import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.OrganizationDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sandeep
 */
@Controller
public class SettingsController extends BrndBotBaseHttpServlet {
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "/getColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColors(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        TransactionResponse transactionResponse = new TransactionResponse();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        List<String> colorArray = new ArrayList<>();
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            String query_string = "Select company_preferences from company_preferences where fk_company_id=" + companyId + "";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                PGobject pgobject = (PGobject) result_set.getObject("company_preferences");
                pgobject.setType("json");
                String obj = pgobject.getValue();
                colorArray.add(obj);
            }
            genericResponse.setDetails(colorArray);
        } catch (Throwable throwable) {
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        } finally {
            //close connection if required
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/setColors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   
    public ResponseEntity<ContainerResponse> setColors(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        StringBuilder string_builder = new StringBuilder();
        List<String> colorArray = new ArrayList<>();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_builder.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject json_user_preferences = null;

            String str = string_builder.toString();
            String str_new = str.replace("&quot;", "\"");
            json_user_preferences = (JSONObject) parser.parse(str_new);
            SqlMethods sql_methods = new SqlMethods();
            JSONObject json_user_preferences_from_database = sql_methods.getJSONUserPreferences(companyId);

            json_user_preferences_from_database.put(IConstants.kColor1, json_user_preferences.get(IConstants.kColor1));
            json_user_preferences_from_database.put(IConstants.kColor2, json_user_preferences.get(IConstants.kColor2));
            json_user_preferences_from_database.put(IConstants.kColor3, json_user_preferences.get(IConstants.kColor3));
            json_user_preferences_from_database.put(IConstants.kColor4, json_user_preferences.get(IConstants.kColor4));

            sql_methods.updateJSONUserPreference(companyId, json_user_preferences_from_database);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_color_update",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
              genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

}
