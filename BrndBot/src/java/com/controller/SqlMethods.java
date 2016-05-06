package com.controller;

import com.divtohtml.DivHTMLModel;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author intbit
 */
// To-Do ILyas/AR refactor and please check all the method
public class SqlMethods {

    HttpServletRequest request;
    HttpServletResponse response;
    public HttpSession session;
    public HttpSession admin_session;

    public static Integer limit = 4;
    public Integer upper_limit = 4;
    public String error = "system failure error";
    public static final String k_mind_body = "Mind_Body_Data";
    private final static Logger logger = Logger.getLogger(SqlMethods.class);
    private static final ConnectionManager connectionManager
            = ConnectionManager.getInstance();

    public static Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    public HttpSession getSession() {
        this.session = request.getSession(true);
        this.session.setMaxInactiveInterval(0);
        return this.session;
    }

    public void close(ResultSet rs, Statement ps) {
        if (rs != null) {
            try {
                rs.close();

            } catch (SQLException e) {
                logger.error("Exception while result set close: " + e.getMessage());

            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                logger.error("Exception while prepared statement close: " + e.getMessage());
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

    //TODO Ilyas datetime needs testing
    public void AddImages(Integer companyId, String image_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Insert Into company_images (fk_company_id, image_name,created_date) values (?,?,?)";
            prepared_statement = connection.prepareStatement(query_string);

            prepared_statement.setInt(1, companyId);
            prepared_statement.setString(2, image_name);
            prepared_statement.setTimestamp(3, getCurrentTimeStamp());

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Exception in sqlmethods AddImages: " + e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
    }

    public void deleteImages(Integer image_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From company_images"
                    + " WHERE company_images_id='" + image_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Exception in sqlmethods deleteImages: " + e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
    }
//TODO ilyas Check - this is used in many places(used in mindbody servlets)

    public Integer getOrganizationID(Integer userId) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Integer org_id = 0;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from organization_company_lookup where fk_company_id=" + userId + "";

            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                org_id = result_set.getInt("fk_organization_id");
            }
        } catch (Exception e) {
            logger.error("Exception in sqlmethods getOrganizationID: " + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return org_id;
    }

    public boolean checkEmailAvailability(String UserName) throws ClassNotFoundException, SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean checked = false;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select * from users where user_name='" + UserName + "'";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                checked = true;
            }

        } catch (SQLException e) {
            logger.error("Exception in sqlmethods checkEmailAvailability: " + e.getMessage());
        } finally {
            close(result_set, prepared_statement);
        }
        return checked;
    }

    public Boolean updateJSONUserPreference(Integer companyId, JSONObject userPreferenceJSON) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pg_object = new PGobject();
        Boolean returnResult = false;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Update company_preferences SET company_preferences=? where fk_company_id=?";
            prepared_statement = connection.prepareStatement(query_string);

            pg_object.setType("json");
            pg_object.setValue(userPreferenceJSON.toString());
            prepared_statement.setObject(1, pg_object, Types.OTHER);
            prepared_statement.setInt(2, companyId);
            if (prepared_statement.executeUpdate() == 1) {
                returnResult = true;
            }
        } catch (Exception e) {
            logger.error("Exception in sqlmethods updateJSONUserPreference: " + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return returnResult;
    }

    //TODO Ilyas - not used any where - check again and delete - AR re did the servlet associated with this - ForgotSendEmail
    public void setUserDetails(Integer userid, String randvalue, Date expdate, Date exptime) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

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
//            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }

    }

    //TODO Ilyas - not used any where - check again and delete - AR re did the servlet associated with this - ResetUserPassword
    public void resetPassword(String id, String password) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_user_login_details"
                    + " SET password ='" + password + "' WHERE id=" + id + "";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();

            query_string = "Delete From tbl_forgot_password where userid='" + id + "'";
            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();

        } catch (Exception e) {
//            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
    }

    //TODO Ilyas - not used any where - check again and delete - AR re did the servlet associated with this - ResetUserPassword
    public String checkResetStatus(String hash) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Date expdatee = new Date();
        String userid = "false";
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
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
//            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } finally {
            close(result_set, prepared_statement);
        }
        return userid;
    }

    //TODO Ilyas/AR - used in mindbody refactor when needed
    public String getMapperFile(Integer user_id, Integer organization_id, Integer category_id, Integer sub_category_id, Integer model_mapper_id, Integer block_id, String editorType) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Boolean isEmail = false;
        if (editorType.equals(ScheduledEntityType.Email.toString())) {
            isEmail = true;
        }
        String mapper_file_name = "";
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            if (model_mapper_id == 0) {
                query_string = "Select * from tbl_model where category_id=" + category_id + " and (user_id=" + user_id + " or user_id=0) and organization_id=" + organization_id + " and sub_category_id=" + sub_category_id + " and social=" + !isEmail + " and email=" + isEmail + " and block_id=" + block_id + " order by id ASC";

                Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Catch the ResultSet object
                result_set = sta.executeQuery(query_string);

                // Check ResultSet's scrollability
                if (result_set.getType() == ResultSet.FETCH_FORWARD) {
                    logger.log(Priority.DEBUG, "ResultSet non-scrollable.");
                } else {
                    logger.log(Priority.DEBUG, "ResultSet scrollable.");
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
            logger.error("Exception in SqlMethods - getMapperFile" + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return mapper_file_name;
    }

    public JSONObject getJSONCompanyPreferences(Integer companyId) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject userPreferencesJSONObject = new org.json.simple.JSONObject();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from company_preferences where fk_company_id=" + companyId + "";
            logger.info(query_string);
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                pgobject = (PGobject) result_set.getObject(IConstants.kUserPreferencesTableKey);
            }
            pgobject.setType("json");
            String obj = pgobject.getValue();
            userPreferencesJSONObject = (org.json.simple.JSONObject) parser.parse(obj);

        } catch (Exception e) {
            logger.error("Exception in SqlMethods - getJSONUserPreferences" + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return userPreferencesJSONObject;
    }

    public Integer getStudioID(Integer company) throws SQLException {
        Integer studio_id = 0;
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            query_string = "Select company_location from company_preferences where fk_company_id=" + company;

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                studio_id = Integer.parseInt(result_set.getString(1));
            }
        } catch (Exception e) {
            logger.error("Exception in SqlMethods - getStudioID" + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return studio_id;
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    //TODO Ilyas create appconstants for these
    public void setSocialPostHistory(Integer companyId, String contenthtml,
            boolean twitter, boolean facebook, String image_type,
            String imagefilename, String pdffilename) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String file_image_path = "";

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            if (pdffilename != null) {
                query_string = "Insert into social_post_history(fk_company_id, time_sent, content_html, twitter, facebook, pdf_file_name) Values (?,?,?,?,?,?)";

                prepared_statement = connection.prepareStatement(query_string);

                prepared_statement.setInt(1, companyId);
                prepared_statement.setTimestamp(2, getCurrentTimeStamp());
                prepared_statement.setString(3, contenthtml);
                prepared_statement.setBoolean(4, twitter);
                prepared_statement.setBoolean(5, facebook);

                File pdf_file = new File(AppConstants.PDF_FILES_PATH + File.separator + pdffilename);
                FileInputStream fis = new FileInputStream(pdf_file);
                prepared_statement.setBinaryStream(6, fis, pdf_file.length());
                prepared_statement.executeUpdate();
            }

            if (imagefilename != null && !image_type.equals("url")) {
                query_string = "Insert into social_post_history(fk_company_id, time_sent, content_html, twitter, facebook, image_file_name) Values (?,?,?,?,?,?)";

                prepared_statement = connection.prepareStatement(query_string);

                prepared_statement.setInt(1, companyId);
                prepared_statement.setTimestamp(2, getCurrentTimeStamp());
                prepared_statement.setString(3, contenthtml);
                prepared_statement.setBoolean(4, twitter);
                prepared_statement.setBoolean(5, facebook);
                prepared_statement.setString(6, imagefilename);
               
                if (image_type.equals("layout")) {
                    file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + imagefilename;
                    File file = new File(file_image_path);
                    FileInputStream fis = new FileInputStream(file);
                    prepared_statement.setBinaryStream(6, fis, file.length());
                } else if (image_type.equals("gallery")) {
                    file_image_path = com.intbittech.AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + com.intbittech.AppConstants.GALLERY_FOLDERNAME + File.separator + imagefilename;
                    File file = new File(file_image_path);
                    FileInputStream fis = new FileInputStream(file);
                    //  prepared_statement.setBinaryStream(6, fis, file.length());
                } else if (image_type.equals("url")) {
//                    file_image_path = imagefilename;
//                    File file = new File(file_image_path);
//                    InputStream fiss = new URL(file_image_path).openStream();
//                    prepared_statement.setBinaryStream(6, fiss);
                }
                prepared_statement.executeUpdate();
            }

        } catch (Exception e) {
            logger.error("Exception in SqlMethods - setSocialPostHistory" + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }

    }

    //TODO Ilyas/AR - used in mindbody refactor when needed
    public ArrayList<DivHTMLModel> getHTMLforDivHTMLModelList(ArrayList<DivHTMLModel> list) {
        ArrayList<DivHTMLModel> newList = new ArrayList<>(list.size());
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        StringBuilder queryParmeter = new StringBuilder();
        StringBuilder orderByParameter = new StringBuilder();

        queryParmeter.append("(");
        for (int i = 0; i < list.size(); i++) {
            queryParmeter.append(list.get(i).getModelId());
            orderByParameter.append("id=" + list.get(i).getModelId() + " DESC");
            if (i < list.size() - 1) {
                queryParmeter.append(",");
                orderByParameter.append(",");
            }

        }
        queryParmeter.append(")");

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select html_file_name from tbl_model where id in " + queryParmeter + " ORDER BY " + orderByParameter;
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            int i = 0;
            while (result_set.next()) {
                DivHTMLModel model = list.get(i++);
                String html_file_name = result_set.getString("html_file_name");
                model.setHtmlFileName(html_file_name);
                String htmlPath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + html_file_name;
                File file = new File(htmlPath);
                model.setHtmlFileContent(FileUtils.readFileToString(file, "UTF-8"));
                newList.add(model);
            }

        } catch (Exception e) {
            logger.error("Exception in SqlMethods - getHTMLforDivHTMLModelList" + e.getMessage());

        } finally {
            close(result_set, prepared_statement);
        }
        return newList;
    }

}
