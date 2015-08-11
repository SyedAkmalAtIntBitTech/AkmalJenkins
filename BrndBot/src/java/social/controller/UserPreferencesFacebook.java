/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

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

    private SqlMethods sqlMethods;

    public UserPreferencesFacebook() throws NamingException {
    }

    public void updatePreference(Integer user_id, String access_token) throws SQLException {
        sqlMethods = new SqlMethods();
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();
            PreparedStatement prepared_statement;
            ResultSet result_set;

            String query = "Select user_preferences from tbl_user_preferences where user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            json_objectFromTable.put("FacebookLoggedIn", "true");
            json_objectFromTable.put("access_token", access_token);

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
        }
    }

//    public Integer getUserPreferencesForBrandid(Integer user_id) throws SQLException {
//        Integer brand_id = 0;
//        try {
//
//            String query = "Select brand_id from tbl_user_preferences where user_id=" + user_id + "";
//
//            prepared_statement = getSqlMethodsInstance().con.prepareStatement(query);
//
//            result_set = prepared_statement.executeQuery();
//
//            if (result_set.next()) {
//                brand_id = result_set.getInt(1);
//            }
//
//            result_set.close();
//            prepared_statement.close();
//
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//        return brand_id;
//        
//    }
//    
//    public JSONObject getUserPreferencesForColors(Integer user_id) throws SQLException {
//        JSONObject json_colors = new JSONObject();
//        try {
//            PGobject pgobject = new PGobject();
//            JSONObject json_objectFromTable = new JSONObject();
//            JSONParser parser = new JSONParser();
//
//            String query = "Select user_preferences from tbl_user_preferences where user_id=" + user_id + "";
//
//            prepared_statement = getSqlMethodsInstance().con.prepareStatement(query);
//
//            result_set = prepared_statement.executeQuery();
//
//            if (result_set.next()) {
//                pgobject = (PGobject) result_set.getObject(1);
//            }
//
//            pgobject.setType("json");
//            String obj = pgobject.getValue();
//
//            json_colors = (JSONObject) parser.parse(obj);
//
//            result_set.close();
//            prepared_statement.close();
//
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//        return json_colors;
//        
//    }

    public String getUserPreferenceForAccessToken(Integer user_id) throws SQLException {
        String access_token = "";
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
            String FBLoggedIn = (String)json_objectFromTable.get("FacebookLoggedIn");
            if (json_objectFromTable.get("FacebookLoggedIn").equals("true")) {
                access_token = (String) json_objectFromTable.get("access_token");
            }


        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlMethods.close(result_set, prepared_statement);
        }
        return access_token;
    }

}
