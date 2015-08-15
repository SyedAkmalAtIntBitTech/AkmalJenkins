package social.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.controller.SqlMethods;
import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author intbit
 */
public class UserPreferencesTwitter {
    SqlMethods sql_methods;
    PreparedStatement prepared_statement;
    ResultSet result_set;
    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(UserPreferencesTwitter.class));

    public UserPreferencesTwitter() throws NamingException {
        this.sql_methods = new SqlMethods();
    }

    public void updatePreference(Integer user_id, String twitter_access_token, String twitter_access_token_secret) throws SQLException {

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
            json_objectFromTable.put("TwitterLoggedIn", "true");
            json_objectFromTable.put("twitter_access_token", twitter_access_token);
            json_objectFromTable.put("twitter_access_token_secret", twitter_access_token_secret);

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

    public String getUserPreferenceForAccessToken(Integer user_id) throws SQLException {
        String twitter_data="";
        String twitter_access_token = "";
        String twitter_access_token_secret = "";

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
            String TwitterLoggedIn = (String)json_objectFromTable.get("TwitterLoggedIn");
            if (TwitterLoggedIn.equals("true")) {
                twitter_access_token = (String) json_objectFromTable.get("twitter_access_token");
                twitter_access_token_secret = (String) json_objectFromTable.get("twitter_access_token_secret");
                twitter_data = twitter_access_token +","+ twitter_access_token_secret;
            }

        } catch (Exception e) {
                                 logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }finally {
            sql_methods.close(result_set, prepared_statement);
        }
        return twitter_data;
    }

}
