/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.ConnectionManager;
import com.intbit.GetColorsServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import util.Utility;

/**
 *
 * @author sandeep-kumar
 */
public class GetUserColors {
    
     public static JSONArray getColorUserPreferences(Integer user_id) throws ServletException, IOException {
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Object object = new Object();
        JSONObject json_colors = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray userColorsArray = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            String query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                object = result_set.getObject("user_preferences");
            }
            String obj = object.toString();
            json_colors = (JSONObject) parser.parse(obj);

            userColorsArray = new JSONArray();
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color1")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color2")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color3")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color4")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color5")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color6")));

        } catch (SQLException ex) {
            Logger.getLogger(GetColorsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GetColorsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userColorsArray;
    }
    
    
}
