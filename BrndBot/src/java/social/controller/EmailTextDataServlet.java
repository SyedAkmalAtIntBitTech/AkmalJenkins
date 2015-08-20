/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.GetEmailLists;
import com.controller.IConstants;
import com.controller.SqlMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 *
 * @author sandeep-kumar
 */
public class EmailTextDataServlet extends BrndBotBaseHttpServlet {
    SqlMethods sqlmethods = new SqlMethods();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        
        try {
             sqlmethods.session = request.getSession();
//            String html_data = request.getParameter("htmlString");
//            sqlmethods.session.setAttribute("htmldata", html_data);
             String emailSubject=request.getParameter("email_subject");
             String emaillist=request.getParameter("email_addresses");
             String emaillistname = request.getParameter("email_list");
             
             Integer user_id = (Integer)sqlmethods.session.getAttribute("UID");
             sqlmethods.session.setAttribute("email_subject", emailSubject);
             sqlmethods.session.setAttribute("email_list", emaillist);
             Boolean result = updateEmailListPreference(user_id, emaillistname, emaillist);
             
        }catch (Exception e){
            Logger.getLogger(GetEmailLists.class.getName()).log(Level.SEVERE, null, e.getCause());
            Logger.getLogger(GetEmailLists.class.getName()).log(Level.SEVERE, null, e.getMessage());
            logger.log(Level.SEVERE, "", e);
        }
    }

    
 private Boolean updateEmailListPreference(Integer user_id, String emailListName, String emailAddresses) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);

        if (emailListArrayJSON.length() == 0){
                org.json.simple.JSONArray json_user_email_addresses_array = new org.json.simple.JSONArray();
                JSONObject json_user_preferences_email = new JSONObject();
                JSONObject json_user_preferences_listnames = new JSONObject();
                String array_email_list[] = emailAddresses.split(",");

                for (int i = 0; i< array_email_list.length; i++){
                    json_user_email_addresses_array.add(array_email_list[i]);
                }

                json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
                json_user_preferences_email.put(IConstants.kEmailAddressesKey, emailAddresses);
                emailListArrayJSON.put(json_user_preferences_email);
                return AddEmailListUserPreference(user_id, emailListArrayJSON);
        }else{
                org.json.simple.JSONArray json_user_email_addresses_array = new org.json.simple.JSONArray();
                JSONObject json_user_preferences_email = new JSONObject();
                org.json.JSONObject emailListJSONObject = new org.json.JSONObject();
                String array_email_list[] = emailAddresses.split(",");

                for (int i = 0; i< array_email_list.length; i++){
                    json_user_email_addresses_array.add(array_email_list[i]);
                }

                Boolean foundEmailListToUpdate = false;
                for (int i = 0; i < emailListArrayJSON.length(); i++) {
                    emailListJSONObject = emailListArrayJSON.getJSONObject(i);
                    String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
                    if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                        if (emailListName.equals(currentListName)) {
                            emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                            emailListArrayJSON.put(i, emailListJSONObject);
                            foundEmailListToUpdate = true;
                            break;
                        }
                    }
                }
                
                if(!foundEmailListToUpdate)
                {
                    emailListJSONObject = new org.json.JSONObject();
                    emailListJSONObject.put(IConstants.kEmailListNameKey, emailListName);
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                   emailListArrayJSON.put(emailListJSONObject);
                }
                
                return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
        }
    }
 
    private Boolean AddEmailListUserPreference(Integer user_id, JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }

    private Boolean updateEmailListUserPreference(Integer user_id, org.json.JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}