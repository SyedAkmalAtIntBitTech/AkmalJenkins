package com.controller;

import com.divtohtml.DivHTMLModel;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;
import pojos.TblBrandColorTheme;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class SqlMethods {

    HttpServletRequest request;
    HttpServletResponse response;
    public HttpSession session;
    public HttpSession admin_session;

    public static Integer limit = 4;
    public Integer upper_limit = 4;
    public String error = "system failure error";
    public static final String k_mind_body = "Mind_Body_Data";
    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(SqlMethods.class));

    private static final ConnectionManager connectionManager 
            = ConnectionManager.getInstance();
    
    public static Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    public void close(ResultSet rs, Statement ps) {
        if (rs != null) {
            try {
                rs.close();

            } catch (SQLException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
            }
        }
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
        connectionManager.closeConnection(connection);
    }
    
    public void closeConnection() {
//        /*
//        this is no longer relevent because we have to invoke 
//        close() on the Connection object. 
//        One can use 
//        connectionManager.closeConnection(connection) 
//        OR
//        SqlMethods.closeConnection(connection)
//        */
//        
////        try {
////            if (getConnection() != null) {
////                getConnection().close();
////        }
////        } catch (SQLException ex) {
////            Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, ex);
////        }
    }

    public void setUpperLimit() {
        try {
            session = request.getSession(true);
            Integer ll = (Integer) session.getAttribute("limit");

            upper_limit = ll;
            response.sendRedirect(request.getContextPath() + "/chooseLook.jsp");
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }
    }

    public Integer getLimit() {
        return upper_limit;
    }

    public void setLowerLimit() {
        try {
            session = request.getSession(true);
            Integer ll = (Integer) session.getAttribute("limit");

            upper_limit = ll - 16;
            if (upper_limit == 0) {
                setInitLimit();
            }
            response.sendRedirect(request.getContextPath() + "/chooseLook.jsp");
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }

    }

    public void setInitLimit() {
        this.upper_limit = limit;
    }

    public String getCompanyName(Integer userId) throws ClassNotFoundException, SQLException {
        String company_name = "";
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                company_name = result_set.getString("company_name");
            }
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return company_name;
    }

    public void AddImages(Integer user_id, String image_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Insert Into tbl_user_images (user_id, image_name) values (?,?)";
            prepared_statement = connection.prepareStatement(query_string);

            prepared_statement.setInt(1, user_id);
            prepared_statement.setString(2, image_name);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void deleteImages(Integer image_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_user_images"
                    + " WHERE id='" + image_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public Integer getOrganizationID(Integer userId) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Integer org_id = 0;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                org_id = result_set.getInt("organizationid");
            }
        } catch (Exception e) {
                     logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return org_id;
    }

    public String getOrganizationName(Integer orgID) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        String org_name = "";
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_organization where id=" + orgID + "";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                org_name = result_set.getString("organization_name");
            }
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return org_name;
    }

    public List getOrganization() throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        List AL = new ArrayList();

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_organization";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                AL.add(result_set.getString("organization_name"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
        } finally {
            close(result_set, prepared_statement);
        }
        return AL;
    }

    public TblColors getColors(String id, Integer number) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        TblColors TC = new TblColors();
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_colors where id=" + id + "";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                TC.setId("color" + number);
                TC.setColorName(result_set.getString("color_name"));
                TC.setColorHex(result_set.getString("color_hex"));
            }
        } catch (Exception e) {
                      logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return TC;
    }

    public boolean checkForDuplicateUser(String UserName) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean checked = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
                Integer UID = result_set.getInt("id");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
        } finally {
            close(result_set, prepared_statement);
        }
        return checked;
    }

    public boolean checkAvailability(String UserName, String Password) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean checked = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "' and password='" + Password + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
        } finally {
            close(result_set, prepared_statement);
        }
        return checked;
    }

    public boolean checkEmailAvailability(String UserName) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean checked = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));
        } finally {
            close(result_set, prepared_statement);
        }
        return checked;
    }

    public void addUserPreferences(Integer user_id, Integer brand_id, Integer font_theme_id, String location, Integer look_id, JSONObject json_object) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pg_object = new PGobject();

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert Into tbl_user_preferences(user_id,brand_id,font_theme_id,location,look_id, user_preferences) Values(?,?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(query_string);

            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, brand_id);
            prepared_statement.setInt(3, font_theme_id);
            prepared_statement.setString(4, location);
            prepared_statement.setInt(5, look_id);

            pg_object.setType("json");
            pg_object.setValue(json_object.toJSONString());
//            Object obj = new Object();
//            obj = json_object.toJSONString();
            prepared_statement.setObject(6, pg_object);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public Boolean updateJSONUserPreference(Integer user_id, JSONObject userPreferenceJSON) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pg_object = new PGobject();
        Boolean returnResult = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Update tbl_user_preferences SET user_preferences=? where user_id=?";
            prepared_statement = connection.prepareStatement(query_string);

            pg_object.setType("json");
            pg_object.setValue(userPreferenceJSON.toString());
            prepared_statement.setObject(1, pg_object);
            prepared_statement.setInt(2, user_id);
            if (prepared_statement.executeUpdate() == 1) {
                returnResult = true;
            }
        } catch (Exception e) {
                      logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return returnResult;
    }

    public int getFontthemeid(String brndid) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Integer IDNO = 0;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select id from tbl_brand_font_family where brand_id='" + brndid + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                IDNO = result_set.getInt(1);
            }
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return IDNO;
    }

    public void addUsers(String User_id, String password) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert Into tbl_user_login_details(user_name,password,organizationid,logo_name,company_name) Values(?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(query_string);

            prepared_statement.setString(1, User_id);
            prepared_statement.setString(2, password);
            prepared_statement.setInt(3, 0);
            prepared_statement.setString(4, "");
            prepared_statement.setString(5, "");

            prepared_statement.executeUpdate();
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void updateUsers(int idno, String fileName) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET logo_name='" + fileName + "'  WHERE id='" + idno + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
                      logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void updateUsersOrg(int idno, int Org_id, String Company_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET organizationid ='" + Org_id + "', company_name='" + Company_name + "' WHERE id='" + idno + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
                      logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void setUserDetails(Integer userid, String randvalue, Date expdate, Date exptime) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            Date expdatee = new Date();

            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            long time3 = System.currentTimeMillis();

            query_string = "Insert into tbl_forgot_Password(userid, randomlink, expdate, exptime) Values (?,?,?,?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setString(1, String.valueOf(userid));
            prepared_statement.setString(2, randvalue);
            prepared_statement.setDate(3, sdat);
            prepared_statement.setLong(4, time3);

            prepared_statement.executeUpdate();

        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }

    }

    public void resetPassword(String id, String password) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_user_login_details"
                    + " SET password ='" + password + "' WHERE id=" + id + "";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();

            query_string = "Delete From tbl_forgot_password where userid='" + id + "'";
            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public String checkResetStatus(String hash) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Date expdatee = new Date();
        String userid = "false";
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            java.sql.Date sdat = new java.sql.Date(expdatee.getYear(), expdatee.getMonth(), expdatee.getDate());

            java.sql.Time time = new Time(System.currentTimeMillis());
            long time3 = System.currentTimeMillis();

            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "' and expdate='" + sdat + "'";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {

                long time2 = result_set.getLong("exptime");
                time2 = time2 + 600000;
                if (time3 <= time2) {
                    userid = result_set.getString("userid");
                }

            }
        } catch (Exception e) {
                   logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return userid;
    }

    public String getUserIdbyHash(String hash) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        String userid = "";
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                userid = result_set.getString("userid");
            }
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return userid;
    }

    public int getUserID(String email) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        int ID = 0;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select id from tbl_user_login_details where user_name ='" + email + "'";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                ID = result_set.getInt(1);
            }
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return ID;
    }

    public void setLookID() throws IOException {
        logger.log(Level.INFO, "LookID" + ":" + "LookID");
    }

    public String getMapperFile(Integer user_id, Integer organization_id, Integer category_id, Integer sub_category_id, Integer model_mapper_id, Integer block_id, String editorType) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Boolean isEmail = false;
        if (editorType.equals("email")) {
            isEmail = true;
        }
        String mapper_file_name = "";
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            if (model_mapper_id == 0) {
                query_string = "Select * from tbl_model where category_id=" + category_id + " and (user_id=" + user_id + " or user_id=0) and organization_id=" + organization_id + " and sub_category_id=" + sub_category_id + " and social=" + !isEmail + " and email="+isEmail +" and block_id="+ block_id+ " order by id ASC";

                Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Catch the ResultSet object
                result_set = sta.executeQuery(query_string);

                // Check ResultSet's scrollability
                if (result_set.getType() == ResultSet.FETCH_FORWARD) {
                    logger.log(Level.INFO, "ResultSet non-scrollable.");
                } else {
                    logger.log(Level.INFO, "ResultSet scrollable.");
                }

                if (result_set.first()) {
                    mapper_file_name = result_set.getString("model_file_name");
                }

            } else {

                query_string = "Select model_file_name from tbl_model where id=" + model_mapper_id + "";

                prepared_statement = connection.prepareStatement(query_string);
                result_set = prepared_statement.executeQuery();
                if (result_set.next()) {
                    mapper_file_name = result_set.getString(1);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return mapper_file_name;
    }

    public JSONObject getJSONUserPreferences(Integer user_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject userPreferencesJSONObject = new org.json.simple.JSONObject();

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(IConstants.kUserPreferencesTableKey);
            }
            pgobject.setType("json");
            String obj = pgobject.getValue();
            userPreferencesJSONObject = (org.json.simple.JSONObject) parser.parse(obj);

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return userPreferencesJSONObject;
    }

    public JSONArray getEmailListsPreferences(Integer user_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject userPreferencesJSONObject = new org.json.simple.JSONObject();
        JSONArray emailListJSONArray = new JSONArray();

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(IConstants.kUserPreferencesTableKey);
            }
            pgobject.setType("json");
            String obj = pgobject.getValue();
            userPreferencesJSONObject = (org.json.simple.JSONObject) parser.parse(obj);
            org.json.simple.JSONArray emailLists = (org.json.simple.JSONArray) userPreferencesJSONObject.get(IConstants.kEmailAddressUserPreferenceKey);

            if (userPreferencesJSONObject != null && emailLists != null) {
                for (int i = 0; i < emailLists.size(); i++) {
                    org.json.simple.JSONObject emailJSONObject = (org.json.simple.JSONObject) emailLists.get(i);
                    emailListJSONArray.put(emailJSONObject);
                }
            }

        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));


        } finally {
            close(result_set, prepared_statement);
        }
        return emailListJSONArray;
    }
    
    public Integer getStudioID(Integer user_id)throws SQLException{
        Integer studio_id = 0;
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            query_string = "Select location from tbl_user_preferences where user_id="+user_id;

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                studio_id = Integer.parseInt(result_set.getString(1));
            }
        }catch(Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }finally {
            close(result_set, prepared_statement);
        }
        return studio_id;
    }


    public org.json.simple.JSONArray getColorsAndThemeName() throws SQLException, ClassNotFoundException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        org.json.simple.JSONArray jsonarray = new org.json.simple.JSONArray();
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select color1, color2, color3, color4, color5, color6, theme_name from tbl_brand_color_theme order by id Asc";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            JSONObject json1 = new JSONObject();

            Integer num = 1;
            Integer num1 = 0;
            num1 = num;
            Integer themeNum = 1;
            String theme = "";
            while (result_set.next()) {
                TblBrandColorTheme color_theme = new TblBrandColorTheme();
                TblColors colors = new TblColors();
                org.json.simple.JSONArray jarr = new org.json.simple.JSONArray();

                colors = getColors(String.valueOf(result_set.getInt(1)), num1);
                jarr.add(colors);

                if (themeNum == 5) {
                    themeNum = 1;
                }

                num1 = num1 + 1;
                colors = getColors(String.valueOf(result_set.getInt(2)), num1);
                jarr.add(colors);

                num1 = num1 + 1;
                colors = getColors(String.valueOf(result_set.getInt(3)), num1);
                jarr.add(colors);

                num1 = num1 + 1;
                colors = getColors(String.valueOf(result_set.getInt(4)), num1);
                jarr.add(colors);
                num1 = num1 + 1;

                colors = getColors(String.valueOf(result_set.getInt(5)), num1);
                jarr.add(colors);
                num1 = num1 + 1;

                colors = getColors(String.valueOf(result_set.getInt(6)), num1);
                jarr.add(colors);
                if (num1 == 24) {
                    num1 = 0;
                }
                num1 = num1 + 1;
                theme = "theme" + themeNum;

                color_theme.setId(theme);
                color_theme.setTheme_name(result_set.getString(7));
                jarr.add(color_theme);
                json1.put(result_set.getString(7), jarr);
                jsonarray.add(jarr);
                themeNum++;
            }
        }finally{
            close(result_set, prepared_statement);
        }
        return jsonarray;

    }

    Integer getBrandID(Integer user_id) {
        Integer brand_id = 0;
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            query_string = "Select brand_id from tbl_user_preferences where user_id="+user_id;

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                brand_id = Integer.parseInt(result_set.getString(1));
            }
        }catch(Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }finally {
            close(result_set, prepared_statement);
        }
        return brand_id;
    }
    
    public Integer getLookID(Integer user_id) {
        Integer look_id = 0;
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            query_string = "Select look_id from tbl_user_preferences where user_id="+user_id;

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                look_id = Integer.parseInt(result_set.getString(1));
            }
        }catch(Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }finally {
            close(result_set, prepared_statement);
        }
        return look_id;
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    public void setEmailSentHistory(Integer userid, String contenthtml, String emailaddress, String emaillistname) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()){

            query_string = "Insert into tbl_emailsenthistory(user_id, timesent, contenthtml, emailaddress, emaillistname) Values (?,?,?,?,?)";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.setInt(1, userid);
            prepared_statement.setTimestamp(2, getCurrentTimeStamp());
            prepared_statement.setString(3, contenthtml);
            prepared_statement.setString(4, emailaddress);
            prepared_statement.setString(5, emaillistname);

            prepared_statement.executeUpdate();

        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }

    }
    public void setSocialPostHistory(Integer userid, String contenthtml, boolean twitter, boolean facebook, String imagefilename) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

         try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Insert into tbl_socialposthistory(user_id, timesent, contenthtml, twitter, facebook, imagefilename) Values (?,?,?,?,?,?)";

            prepared_statement = getConnection().prepareStatement(query_string);
            
            prepared_statement.setInt(1, userid);
            prepared_statement.setTimestamp(2, getCurrentTimeStamp());
            prepared_statement.setString(3, contenthtml);
            prepared_statement.setBoolean(4, twitter);
            prepared_statement.setBoolean(5, facebook);
            prepared_statement.setString(6, imagefilename);

            prepared_statement.executeUpdate();

        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }

    }
    
    public JSONObject getUserPreferencesBrand(Integer user_id){
        String query_string = "", brand_name = "", image = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Integer brand_id = 0;
        JSONObject json_brand = new JSONObject();
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select brand_id from tbl_user_preferences where user_id="+user_id+"";
    
            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                brand_id  = result_set.getInt(1);
            }
            
            result_set.close();
            prepared_statement.close();
            
            query_string = "Select brand_name,image from tbl_brand_personality where id="+brand_id+"";
            
            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();

            if (result_set.next()){
                brand_name  = result_set.getString(1);
                image = result_set.getString(2);
                json_brand.put("brand_name", brand_name);
                json_brand.put("image_name", image);
                
            }
            
         }catch (Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
         return json_brand;
    }

    public JSONObject getUserPreferencesLook(Integer user_id){
        String query_string = "", look_name = "", image = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Integer look_id = 0;
        JSONObject json_look = new JSONObject();
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select look_id from tbl_user_preferences where user_id="+user_id+"";
    
            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                look_id  = result_set.getInt(1);
            }
            
            result_set.close();
            prepared_statement.close();
            
            query_string = "Select look_name, file_name from tbl_look where id="+look_id+"";
            
            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();

            if (result_set.next()){
                look_name  = result_set.getString(1);
                image = result_set.getString(2);
                json_look.put("look_name", look_name);
                json_look.put("image_name", image);
                
            }
            
         }catch (Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
         return json_look;
    }
    
    
    public void updateBrandPersonality(Integer user_id, Integer brand_id){
        String query_string = "", brand_name = "", image = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        JSONObject json_brand = new JSONObject();
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {
             
              query_string = "UPDATE tbl_user_preferences"
                    + " SET brand_id ='" + brand_id + "' WHERE user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
              
         }catch (Exception e){
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }
    public void updateLooks(Integer user_id, Integer look_id){
        String query_string = "", brand_name = "", image = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        JSONObject json_brand = new JSONObject();
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {
             
              query_string = "UPDATE tbl_user_preferences"
                    + " SET look_id ='" + look_id + "' WHERE user_id=" + user_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
              
         }catch (Exception e){
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    public String getLogofileName(int UID) {
        String query_string = "", brand_name = "", image = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        JSONObject json_brand = new JSONObject();
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {
             
            query_string = "Select logo_name from tbl_user_login_details where id="+UID;

            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();

            if (result_set.next()){
                image  = result_set.getString(1);
            }
              
         }catch (Exception e){
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return image;
    }

    public ArrayList<DivHTMLModel> getHTMLforDivHTMLModelList(ArrayList<DivHTMLModel> list) {
        ArrayList<DivHTMLModel> newList = new ArrayList<>(list.size());
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        StringBuilder queryParmeter = new StringBuilder();
        StringBuilder orderByParameter = new StringBuilder();
        
        queryParmeter.append("(");
        for (int i = 0; i < list.size(); i++) {
            queryParmeter.append("?");
            queryParmeter.append(",");
            orderByParameter.append("id="+list.get(i).getModelId() + " DESC");
            orderByParameter.append(",");
        }
        queryParmeter.substring(0, queryParmeter.length()-1);
        orderByParameter.substring(0, orderByParameter.length()-1);
        queryParmeter.append(")");
         try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
             
            query_string = "Select id,html_file_name from tbl_model_name_value where id in "+queryParmeter+" ORDER BY "+orderByParameter;
            prepared_statement = connection.prepareStatement(query_string);
            for (int i = 0; i < list.size(); i++) {
                prepared_statement.setInt(1, Integer.parseInt(list.get(i).getModelId()));
            }
            
            prepared_statement = connection.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();

            int i = 0;
            if (result_set.next()){
                DivHTMLModel model = list.get(i++);
                String html_file_name = result_set.getString(1);
                model.setHtmlFileName(html_file_name);
                String htmlPath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + html_file_name;
                File file = new File(htmlPath);
                model.setHtmlFileContent(FileUtils.readFileToString(file, "UTF-8"));
                newList.add(model);
            }
              
         }catch (Exception e){
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return newList;
    }

}
