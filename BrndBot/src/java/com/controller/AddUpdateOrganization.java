package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author AR
 */
public class AddUpdateOrganization extends BrndBotBaseHttpServlet {

    RequestDispatcher request_dispatcher;

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
        PrintWriter out = response.getWriter();
        PreparedStatement preparestatement = null;
        StringBuffer string_buffer = new StringBuffer();
        getSqlMethodsInstance().session = request.getSession(true);

        try {

            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            joUser = (JSONObject) parser.parse(string_buffer.toString());

            String Company = (String) joUser.get("company");
            String organization_id = (String) joUser.get("organization");

            logger.log(Level.INFO, Company);

            String emailid = (String) getSqlMethodsInstance().session.getAttribute("EmailID");
            Integer idno = getSqlMethodsInstance().getUserID(emailid);

            String org_name = getSqlMethodsInstance().getOrganizationName(Integer.parseInt(organization_id));
            
            getSqlMethodsInstance().session.setAttribute("org_id", organization_id);
            getSqlMethodsInstance().session.setAttribute("org_name", org_name);
            getSqlMethodsInstance().session.setAttribute("company", Company);

            getSqlMethodsInstance().updateUsersOrg(idno, Integer.parseInt(organization_id), Company);
            out.write("true");
        } catch (ParseException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));
            out.write(getSqlMethodsInstance().error);
        } catch (IOException | SQLException | NumberFormatException | ClassNotFoundException e) {
            logger.log(Level.SEVERE,  util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));
            out.write(getSqlMethodsInstance().error);
        }finally {
            out.close();
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
