package com.controller;

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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
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
    private Connection connection;

    public static Integer limit = 4;
    public Integer upper_limit = 4;
    public String error = "system failure error";
    public static final String k_mind_body = "Mind_Body_Data";

    public SqlMethods() {
         Context envCtx;
        try {
            
            envCtx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource datasource = (DataSource) envCtx.lookup("jdbc/postgres");
            this.connection = datasource.getConnection();

        } catch (NamingException ex) {
            Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BrndBotBaseHttpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return this.connection;
    }

    public void close(ResultSet rs, Statement ps) {
        if (rs != null) {
            try {
                rs.close();

            } catch (SQLException e) {
                Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void closeConnection() {
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpperLimit() {
        try {
            session = request.getSession(true);
            Integer ll = (Integer) session.getAttribute("limit");

            upper_limit = ll;
            response.sendRedirect(request.getContextPath() + "/chooseLook.jsp");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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

        try {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = getConnection().prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                company_name = result_set.getString("company_name");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
        return company_name;
    }

    public void AddImages(Integer user_id, String image_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {

            query_string = "Insert Into tbl_user_images (user_id, image_name) values (?,?)";
            prepared_statement = getConnection().prepareStatement(query_string);

            prepared_statement.setInt(1, user_id);
            prepared_statement.setString(2, image_name);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void deleteImages(Integer image_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Delete From tbl_user_images"
                    + " WHERE id='" + image_id + "'";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public Integer getOrganizationID(Integer userId) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Integer org_id = 0;
        try {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = getConnection().prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                org_id = result_set.getInt("organizationid");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "Select * from tbl_organization where id=" + orgID + "";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                org_name = result_set.getString("organization_name");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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

        try {
            query_string = "Select * from tbl_organization";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                AL.add(result_set.getString("organization_name"));
            }
        } catch (Exception e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
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
        try {
            query_string = "Select * from tbl_colors where id=" + id + "";
            prepared_statement = getConnection().prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                TC.setId("color" + number);
                TC.setColorName(result_set.getString("color_name"));
                TC.setColorHex(result_set.getString("color_hex"));
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
                Integer UID = result_set.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
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
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "' and password='" + Password + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
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
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
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

        try {
            query_string = "Insert Into tbl_user_preferences(user_id,brand_id,font_theme_id,location,look_id, user_preferences) Values(?,?,?,?,?,?)";
            prepared_statement = getConnection().prepareStatement(query_string);

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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "Update tbl_user_preferences SET user_preferences=? where user_id=?";
            prepared_statement = getConnection().prepareStatement(query_string);

            pg_object.setType("json");
            pg_object.setValue(userPreferenceJSON.toString());
            prepared_statement.setObject(1, pg_object);
            prepared_statement.setInt(2, user_id);
            if (prepared_statement.executeUpdate() == 1) {
                returnResult = true;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "Select id from tbl_brand_font_family where brand_id='" + brndid + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                IDNO = result_set.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
        return IDNO;
    }

    public void addUsers(String User_id, String password) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Insert Into tbl_user_login_details(user_name,password,organizationid,logo_name,company_name) Values(?,?,?,?,?)";
            prepared_statement = getConnection().prepareStatement(query_string);

            prepared_statement.setString(1, User_id);
            prepared_statement.setString(2, password);
            prepared_statement.setInt(3, 0);
            prepared_statement.setString(4, "");
            prepared_statement.setString(5, "");

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void updateUsers(int idno, String fileName) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET logo_name='" + fileName + "'  WHERE id='" + idno + "'";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void updateUsersOrg(int idno, int Org_id, String Company_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET organizationid ='" + Org_id + "', company_name='" + Company_name + "' WHERE id='" + idno + "'";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void setUserDetails(Integer userid, String randvalue, Date expdate, Date exptime) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {

            Date expdatee = new Date();

            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            long time3 = System.currentTimeMillis();

            query_string = "Insert into tbl_forgot_Password(userid, randomlink, expdate, exptime) Values (?,?,?,?)";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, String.valueOf(userid));
            prepared_statement.setString(2, randvalue);
            prepared_statement.setDate(3, sdat);
            prepared_statement.setLong(4, time3);

            prepared_statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } finally {
            close(result_set, prepared_statement);
        }

    }

    public void resetPassword(String id, String password) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "UPDATE tbl_user_login_details"
                    + " SET password ='" + password + "' WHERE id=" + id + "";

            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();

            query_string = "Delete From tbl_forgot_password where userid='" + id + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            java.sql.Date sdat = new java.sql.Date(expdatee.getYear(), expdatee.getMonth(), expdatee.getDate());

            java.sql.Time time = new Time(System.currentTimeMillis());
            long time3 = System.currentTimeMillis();

            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "' and expdate='" + sdat + "'";

            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {

                long time2 = result_set.getLong("exptime");
                time2 = time2 + 600000;
                if (time3 <= time2) {
                    userid = result_set.getString("userid");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "'";
            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                userid = result_set.getString("userid");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "Select id from tbl_user_login_details where user_name ='" + email + "'";
            prepared_statement = getConnection().prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                ID = result_set.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
        return ID;
    }

    public void setLookID() throws IOException {
        System.out.println("LookID" + ":" + "LookID");
    }

    public String getMapperFile(Integer user_id, Integer organization_id, Integer category_id, Integer sub_category_id, Integer id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        String mapper_file_name = "";
        try {
            if (id == 0) {
                query_string = "Select * from tbl_model where category_id=" + category_id + " and user_id=" + user_id + " and organization_id=" + organization_id + " and sub_category_id=" + sub_category_id + " and social=" + true + " order by id ASC";

                Statement sta = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Catch the ResultSet object
                result_set = sta.executeQuery(query_string);

                // Check ResultSet's scrollability
                if (result_set.getType() == ResultSet.FETCH_FORWARD) {
                    System.out.println("ResultSet non-scrollable.");
                } else {
                    System.out.println("ResultSet scrollable.");
                }

//                prepared_statement = getConnection().prepareStatement(query_string);
//                result_set = prepared_statement.executeQuery();
                if (result_set.first()) {
                    mapper_file_name = result_set.getString("model_file_name");
                }

            } else {
                query_string = "Select model_file_name from tbl_model where category_id=" + category_id + " and user_id=" + user_id + " and organization_id=" + organization_id + " and sub_category_id=" + sub_category_id + " and id=" + id + "";

                prepared_statement = getConnection().prepareStatement(query_string);
                result_set = prepared_statement.executeQuery();
                if (result_set.next()) {
                    mapper_file_name = result_set.getString(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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

        try {
            query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = getConnection().prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(IConstants.kUserPreferencesTableKey);
            }
            pgobject.setType("json");
            String obj = pgobject.getValue();
            userPreferencesJSONObject = (org.json.simple.JSONObject) parser.parse(obj);

        } catch (Exception e) {

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

        try {
            query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = getConnection().prepareStatement(query_string);

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

        try{
            
            query_string = "Select location from tbl_user_preferences where user_id="+user_id;

            prepared_statement = getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next()){
                studio_id = Integer.parseInt(result_set.getString(1));
            }
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            close(result_set, prepared_statement);
        }
        return studio_id;
    }


    public org.json.simple.JSONArray getColorsAndThemeName() throws SQLException, ClassNotFoundException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        query_string = "Select color1, color2, color3, color4, color5, color6, theme_name from tbl_brand_color_theme order by id Asc";
        prepared_statement = getConnection().prepareStatement(query_string);
        result_set = prepared_statement.executeQuery();
        JSONObject json1 = new JSONObject();
        org.json.simple.JSONArray jsonarray = new org.json.simple.JSONArray();

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
        return jsonarray;

    }
}
