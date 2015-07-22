package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.postgresql.util.PGobject;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class SqlMethods {

    public static Connection con;
    HttpServletRequest request;
    HttpServletResponse response;
    public HttpSession session;
    public HttpSession admin_session;
    
    public static Integer limit = 4;
    public Integer upper_limit = 4;
    public String query_string = "";
    public PreparedStatement prepared_statement;
    public ResultSet result_set;
    private static String database_port_address = "jdbc:postgresql://localhost:5432/brnd";
    private static String database_user_id = "postgres";
    private static String database_user_password = "postgres";
    public String error = "system failure error";
    public static String k_mind_body = "Mind_Body_Data";

    public void setDatabaseConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(database_port_address, database_user_id, database_user_password);

        } catch (Exception e) {
            
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
        try {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = con.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                company_name = result_set.getString("company_name");
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return company_name;
    }
    
    public void AddImages(Integer user_id, String image_name)throws SQLException{
        try{
            PreparedStatement prepared_statement;
            ResultSet result_set;

            String query = "Insert Into tbl_user_images (user_id, image_name) values (?,?)";
            prepared_statement = con.prepareStatement(query);

            prepared_statement.setInt(1, user_id);
            prepared_statement.setString(2, image_name);

            prepared_statement.executeQuery();

            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    public void deleteImages(Integer image_id)throws SQLException{
        try{
              query_string = "Delete From tbl_user_images"
                    + " WHERE id='" + image_id + "'";
                 
                 prepared_statement = con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
    
    public Integer getOrganizationID(Integer userId) throws ClassNotFoundException, SQLException {
        Integer org_id = 0;
        try {
            query_string = "select * from tbl_user_login_details where id=" + userId + "";

            prepared_statement = con.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                org_id = result_set.getInt("organizationid");
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return org_id;
    }

    public String getOrganizationName(Integer orgID) throws ClassNotFoundException, SQLException {
        String org_name = "";
        try {
            query_string = "Select * from tbl_organization where id=" + orgID + "";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                org_name = result_set.getString("organization_name");
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return org_name;
    }

    public List getOrganization() throws ClassNotFoundException, SQLException {
        List AL = new ArrayList();

        try {
            query_string = "Select * from tbl_organization";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                AL.add(result_set.getString("organization_name"));
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
        }
        return AL;
    }

    public TblColors getColors(String id, Integer number) throws ClassNotFoundException, SQLException {
        TblColors TC = new TblColors();
        try {
            query_string = "Select * from tbl_colors where id=" + id + "";
            prepared_statement = con.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                TC.setId("color" + number);
                TC.setColorName(result_set.getString("color_name"));
                TC.setColorHex(result_set.getString("color_hex"));
            }
            prepared_statement.close();
            result_set.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return TC;
    }

    public boolean checkForDuplicateUser(String UserName) throws ClassNotFoundException, SQLException {
        boolean checked = false;
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
                Integer UID = result_set.getInt("id");
            }

            result_set.close();
            prepared_statement.close();
        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
        }
        return checked;
    }

    public boolean checkAvailability(String UserName, String Password) throws ClassNotFoundException, SQLException {
        boolean checked = false;
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "' and password='" + Password + "'";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

            result_set.close();
            prepared_statement.close();
        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
        }
        return checked;
    }

    public boolean checkEmailAvailability(String UserName) throws ClassNotFoundException, SQLException {
        boolean checked = false;
        try {

            query_string = "Select * from tbl_user_login_details where user_name='" + UserName + "'";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

            result_set.close();
            prepared_statement.close();
        } catch (SQLException e) {
            System.out.println(e.getCause() + "," + e.getMessage() + "," + e.getStackTrace());
        }
        return checked;
    }

    public void addUserPreferences(Integer user_id, Integer brand_id, Integer font_theme_id, String location, Integer look_id, JSONObject json_object) {
        PGobject pg_object = new PGobject();

        try {
            query_string = "Insert Into tbl_user_preferences(user_id,brand_id,font_theme_id,location,look_id, user_preferences) Values(?,?,?,?,?,?)";
            prepared_statement = con.prepareStatement(query_string);

            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, brand_id);
            prepared_statement.setInt(3, font_theme_id);
            prepared_statement.setString(4, location);
            prepared_statement.setInt(5, look_id);
            
            pg_object.setType("json");
            pg_object.setValue(json_object.toJSONString());
            prepared_statement.setObject(6, pg_object);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    
    public int getFontthemeid(String brndid) throws SQLException {
        Integer IDNO = 0;
        try {
            query_string = "Select id from tbl_brand_font_family where brand_id='" + brndid + "'";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                IDNO = result_set.getInt(1);
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return IDNO;
    }

    public void addUsers(String User_id, String password) throws SQLException {
        try {
            query_string = "Insert Into tbl_user_login_details(user_name,password,organizationid,logo_name,company_name) Values(?,?,?,?,?)";
            prepared_statement = con.prepareStatement(query_string);

            prepared_statement.setString(1, User_id);
            prepared_statement.setString(2, password);
            prepared_statement.setInt(3, 0);
            prepared_statement.setString(4, "");
            prepared_statement.setString(5, "");

            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void updateUsers(int idno, String fileName) throws SQLException {
        try {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET logo_name='" + fileName + "'  WHERE id='" + idno + "'";

            prepared_statement = con.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void updateUsersOrg(int idno, int Org_id, String Company_name) throws SQLException {
        try {
            query_string = "UPDATE tbl_user_login_details"
                    + "   SET organizationid ='" + Org_id + "', company_name='" + Company_name + "' WHERE id='" + idno + "'";

            prepared_statement = con.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void setUserDetails(Integer userid, String randvalue, Date expdate, Date exptime) throws SQLException {

        try {

            Date expdatee = new Date();

            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            long time3 = System.currentTimeMillis();

            query_string = "Insert into tbl_forgot_Password(userid, randomlink, expdate, exptime) Values (?,?,?,?)";

            prepared_statement = con.prepareStatement(query_string);
            prepared_statement.setString(1, String.valueOf(userid));
            prepared_statement.setString(2, randvalue);
            prepared_statement.setDate(3, sdat);
            prepared_statement.setLong(4, time3);
            prepared_statement.executeQuery();

            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }

    public void resetPassword(String id, String password) {
        try {
            query_string = "UPDATE tbl_user_login_details"
                    + " SET password ='" + password + "' WHERE id=" + id + "";

            prepared_statement = con.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();

            query_string = "Delete From tbl_forgot_password where userid='" + id + "'";
            prepared_statement = con.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    public String checkResetStatus(String hash) {
        Date expdatee = new Date();
        String userid = "false";
        try {
            java.sql.Date sdat = new java.sql.Date(expdatee.getYear(), expdatee.getMonth(), expdatee.getDate());

            java.sql.Time time = new Time(System.currentTimeMillis());
            long time3 = System.currentTimeMillis();

            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "' and expdate='" + sdat + "'";

            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {

                long time2 = result_set.getLong("exptime");
                time2 = time2 + 600000;
                if (time3 <= time2) {
                    userid = result_set.getString("userid");
                }

            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return userid;
    }

    public String getUserIdbyHash(String hash) {
        String userid = "";
        try {
            query_string = "Select * from tbl_forgot_password where randomlink='" + hash + "'";
            prepared_statement = con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                userid = result_set.getString("userid");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return userid;
    }

    public int getUserID(String email) throws SQLException {
        int ID = 0;
        try {
            query_string = "Select id from tbl_user_login_details where user_name ='" + email + "'";
            prepared_statement = con.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                ID = result_set.getInt(1);
            }
            result_set.close();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return ID;
    }

    public void setLookID() throws IOException {
        System.out.println("LookID" + ":" + "LookID");
    }

}
