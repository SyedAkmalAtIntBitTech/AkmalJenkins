package com.intbittech.social;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.intbittech.utility.IConstants;
import com.controller.SqlMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbit.ConnectionManager;
import com.intbittech.modelmappers.TwitterDataDetails;
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
public class CompanyPreferencesTwitter {

    private static final Logger logger = Logger.getLogger(CompanyPreferencesTwitter.class.getName());

    SqlMethods sql_methods;
    PreparedStatement prepared_statement;
    ResultSet result_set;

    public CompanyPreferencesTwitter() throws NamingException {
        this.sql_methods = new SqlMethods();
    }

    public void updatePreference(Integer companyId, String twitter_access_token, String twitter_access_token_secret, String user_name) throws SQLException {

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();
            String query = "Select company_preferences from company_preferences where fk_company_id=" + companyId + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            TwitterDataDetails twitter = new TwitterDataDetails();
            twitter.setTwitterLoggedIn("true");
            twitter.setTwitterAccessToken(twitter_access_token);
            twitter.setTwitterAccessTokenSecret(twitter_access_token_secret);
            twitter.setTwitterUserName(user_name);
            ObjectMapper mapper = new ObjectMapper();
            String twitterDetailsJson = mapper.writeValueAsString(twitter);

            json_objectFromTable.put(IConstants.kTwitterKey, twitterDetailsJson);
            result_set.close();
            prepared_statement.close();

            query = "UPDATE company_preferences"
                    + " SET company_preferences=?"
                    + " WHERE fk_company_id=" + companyId + "";

            PGobject jsonObject = new PGobject();

            jsonObject.setType("json");
            jsonObject.setValue(json_objectFromTable.toJSONString());

            prepared_statement = connection.prepareStatement(query);

            prepared_statement.setObject(1, jsonObject);

            prepared_statement.executeUpdate();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            prepared_statement.close();
        }
    }

    public JSONObject getCompanyPreferenceForAccessToken(Integer companyId) throws SQLException {
        String twitter_data = "";
        String twitter_access_token = "";
        String twitter_access_token_secret = "", twitter_user_name = "";
        JSONObject json_fb_to_read = new JSONObject();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();

            String query = "Select company_preferences from company_preferences where fk_company_id=" + companyId + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);

            if (json_objectFromTable.get(IConstants.kTwitterKey) != null) {
                json_fb_to_read = (JSONObject) json_objectFromTable.get(IConstants.kTwitterKey);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            result_set.close();
            prepared_statement.close();
        }
        return json_fb_to_read;
    }

    public void deletePreferences(Integer companyId) throws SQLException {
        String access_token = "";
        String user_profile_name = "", default_page_name = "";
        JSONObject json_fb_to_read = new JSONObject();
        JSONObject json_fb = new JSONObject();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            PGobject pgobject = new PGobject();
            JSONObject json_objectFromTable = new JSONObject();
            JSONParser parser = new JSONParser();

            String query = "Select company_preferences from company_preferences where fk_company_id=" + companyId + "";

            prepared_statement = connection.prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(1);
            }

            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_objectFromTable = (JSONObject) parser.parse(obj);
            if (json_objectFromTable.get(IConstants.kTwitterKey) != null) {
                json_objectFromTable.remove(IConstants.kTwitterKey);
            }

            result_set.close();
            prepared_statement.close();

            query = "UPDATE company_preferences"
                    + " SET company_preferences=?"
                    + " WHERE fk_company_id=" + companyId + "";

            PGobject jsonObject = new PGobject();

            jsonObject.setType("json");
            jsonObject.setValue(json_objectFromTable.toJSONString());

            prepared_statement = connection.prepareStatement(query);

            prepared_statement.setObject(1, jsonObject);
            prepared_statement.executeUpdate();
            prepared_statement.close();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            result_set.close();
            prepared_statement.close();
        }

    }

}
