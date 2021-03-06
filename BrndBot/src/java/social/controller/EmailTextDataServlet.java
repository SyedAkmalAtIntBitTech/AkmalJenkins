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
             String emailSubject = request.getParameter("email_subject");
             String email_addresses = request.getParameter("email_addresses");
             String emaillistname = request.getParameter("email_list");
             
             Integer user_id = (Integer)sqlmethods.session.getAttribute("UID");
             sqlmethods.session.setAttribute("email_subject", emailSubject);
             sqlmethods.session.setAttribute("email_addresses", email_addresses);
             sqlmethods.session.setAttribute("email_list", emaillistname);
             
        }catch (Exception e){
            Logger.getLogger(GetEmailLists.class.getName()).log(Level.SEVERE, null, e.getCause());
            Logger.getLogger(GetEmailLists.class.getName()).log(Level.SEVERE, null, e.getMessage());
            logger.log(Level.SEVERE, "", e);
        }
    }

    
    private boolean updateEmailListPreference(Integer user_id, String emailListName, String emailAddresses) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);

        JSONObject json_user_preferences_email = new JSONObject();
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
    }
 
    private Boolean AddEmailListUserPreference(Integer user_id, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }

    private Boolean updateEmailListUserPreference(Integer user_id, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
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