/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletOrganization extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    Organization organization = new Organization();
    Fonts fonts = new Fonts();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer string_buffer = new StringBuffer();

        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject joFonts = null;
            joFonts = (JSONObject) parser.parse(string_buffer.toString());
            String type = (String) joFonts.get("type");
            sqlmethods.setDatabaseConnection();
            if (type.equals("add")) {
                String organization_name = (String) joFonts.get("organization_name");
                boolean check = organization.checkAvailability(organization_name);
                if (check) {
                    out.write("false");
                } else {
                    organization.addOrganization(organization_name);
                    out.write("true");
                }
            } else if (type.equals("edit")) {
                String organization_id = (String) joFonts.get("organization_id");
                String organization_name = (String) joFonts.get("organization_name");
                boolean check = organization.checkAvailability(organization_name);
                if (check) {
                    out.write("false");
                } else {
                    organization.changeOrganization(Integer.parseInt(organization_id), organization_name);
                    out.write("true");
                }
            } else if (type.equals("delete")) {
                String organization_id = (String) joFonts.get("organization_id");
                organization.deleteOrganization(Integer.parseInt(organization_id));
                out.write("true");
            }

        } catch (Exception e) {
            out.write(sqlmethods.error);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            try {
                out.close();
                sqlmethods.con.close();
            }catch (Exception e){}
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
