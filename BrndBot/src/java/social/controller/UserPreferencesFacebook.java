/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.controller.SqlMethods;
import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author intbit
 */
public class UserPreferencesFacebook {

    private SqlMethods sqlMethods = new SqlMethods();

    public void updatePreference(Integer user_id, String default_page_access_token, String fb_user_profile_name, String default_page_name ) throws SQLException {
            PreparedStatement prepared_statement = null;
            ResultSet result_set = null;
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONObject json_facebook = new JSONObject();
            JSONParser parser = new JSONParser();

            String query = "Select user_preferences from tbl_user_preferences where user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            
            json_facebook.put("FacebookLoggedIn", "true");
            json_facebook.put("fb_default_page_access_token", default_page_access_token);
            json_facebook.put("fb_user_profile_name", fb_user_profile_name);
            json_facebook.put("fb_default_page_name", default_page_name);
            
            json_objectFromTable.put(IConstants.kFacebookKey, json_facebook);

            result_set.close();
            prepared_statement.close();

            query = "UPDATE tbl_user_preferences"
                    + " SET user_preferences=?"
                    + " WHERE user_id=" + user_id + "";

            PGobject jsonObject = new PGobject();

            jsonObject.setType("json");
            jsonObject.setValue(json_objectFromTable.toJSONString());

            prepared_statement = connection.prepareStatement(query);

            prepared_statement.setObject(1, jsonObject);

            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            prepared_statement.close();
            result_set.close();
        }
    }

    public JSONObject getUserPreferenceForAccessToken(Integer user_id) throws SQLException {
        String access_token = "";
        String user_profile_name = "", default_page_name = "";
        JSONObject json_fb_to_read = new JSONObject();
        JSONObject json_fb = new JSONObject();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();

            String query = "Select user_preferences from tbl_user_preferences where user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            if (json_objectFromTable.get(IConstants.kFacebookKey) != null){
                json_fb_to_read = (JSONObject)json_objectFromTable.get(IConstants.kFacebookKey);
                String FBLoggedIn = (String)json_fb_to_read.get("FacebookLoggedIn");
                if (json_fb_to_read.get("FacebookLoggedIn").equals("true")) {
                    access_token = (String) json_fb_to_read.get("fb_default_page_access_token");
                    user_profile_name = (String)json_fb_to_read.get("fb_user_profile_name");
                    default_page_name = (String)json_fb_to_read.get("fb_default_page_name");
                    json_fb.put("FacebookLoggedIn", "true");
                    json_fb.put("fb_default_page_access_token", access_token);
                    json_fb.put("user_profile_page", user_profile_name);
                    json_fb.put("fb_default_page_name", default_page_name);
                }
            }

            
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            result_set.close();
            prepared_statement.close();
            
        }
            return json_fb;

    }

    public void deletePreferences(Integer user_id)throws SQLException {
        String access_token = "";
        String user_profile_name = "", default_page_name = "";
        JSONObject json_fb_to_read = new JSONObject();
        JSONObject json_fb = new JSONObject();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();

            String query = "Select user_preferences from tbl_user_preferences where user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            if (json_objectFromTable.get(IConstants.kFacebookKey) != null){
                json_objectFromTable.remove(IConstants.kFacebookKey);
            }

            result_set.close();
            prepared_statement.close();

            query = "UPDATE tbl_user_preferences"
                    + " SET user_preferences=?"
                    + " WHERE user_id=" + user_id + "";

            PGobject jsonObject = new PGobject();

            jsonObject.setType("json");
            jsonObject.setValue(json_objectFromTable.toJSONString());

            prepared_statement = connection.prepareStatement(query);

            prepared_statement.setObject(1, jsonObject);
            prepared_statement.executeUpdate();
            prepared_statement.close();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            result_set.close();
            prepared_statement.close();
        }
        
    }

}
