
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intbit
 */
public class Authentication extends BrndBotBaseHttpServlet {

    GenerateHashPassword generate_hash_password = new GenerateHashPassword();

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
        StringBuffer string_buffer = new StringBuffer();
        RequestDispatcher request_dispatcher;
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
    
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession(true);
        getSqlMethodsInstance().session.setMaxInactiveInterval(0);

        try {

            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            joUser = (JSONObject) parser.parse(string_buffer.toString());
            String User_id = (String) joUser.get("emailid");
            String password = (String) joUser.get("password");

            String hash_password = generate_hash_password.hashPass(password);

            check = getSqlMethodsInstance().checkAvailability(User_id, hash_password);
            Integer UID = getSqlMethodsInstance().getUserID(User_id);
            String company = getSqlMethodsInstance().getCompanyName(UID);
            String logo_file_name= getSqlMethodsInstance().getLogofileName(UID);
            
            getSqlMethodsInstance().session.setAttribute("company", company);

            response.setContentType("text/html");
            if (check) {
                getSqlMethodsInstance().session.setAttribute("UID", UID);

                getSqlMethodsInstance().session.setAttribute("ImageFileName", logo_file_name);
                getSqlMethodsInstance().session.setAttribute("Checked", "true");
                out.write("true");
            } else {
                getSqlMethodsInstance().session.setAttribute("Checked", "false");
                out.write("false");
            }
        } catch (ClassNotFoundException | ParseException | SQLException | IOException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while login:", getSqlMethodsInstance().error));
            out.write(getSqlMethodsInstance().error);
        }
        finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
            getSqlMethodsInstance().closeConnection();
        }
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
        try {
            processRequest(request, response);
        } catch (Exception x) {
        }
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
