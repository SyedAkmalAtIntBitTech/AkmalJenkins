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
public class ServletFontStyles extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    FontStyles fonts = new FontStyles();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                String font_style = (String) joFonts.get("font_style");
                boolean check = fonts.checkAvailability(font_style);
                if (check) {
                    out.write("false");
                } else {
                    fonts.addFontSize(font_style);
                    out.write("true");
                }
            } else if (type.equals("edit")) {
                String font_id = (String) joFonts.get("font_id");
                String font_style = (String) joFonts.get("font_style");
                boolean check = fonts.checkAvailability(font_style);
                if (check) {
                    out.write("false");
                } else {
                    fonts.editFontSize(Integer.parseInt(font_id), font_style);
                    out.write("true");
                }
            } else if (type.equals("delete")) {
                String font_id = (String) joFonts.get("font_id");
                fonts.delete(Integer.parseInt(font_id));
                out.write("true");
            }
            sqlmethods.con.close();
        } catch (Exception e) {
            out.write(sqlmethods.error);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
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
