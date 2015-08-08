/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletColors extends BrndBotBaseHttpServlet {

    Colors colors;

     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            colors = new Colors();
        } catch (NamingException ex) {
            Logger.getLogger(BrndBotBaseHttpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs /** Processes requests for
     * both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        super.processRequest(request, response);
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
            if (type.equals("add")) {
                String color_hex = (String) joFonts.get("color_hex");
                String color_name = (String) joFonts.get("color_name");

                boolean check = colors.checkAvailability(color_hex, color_name);
                if (check) {
                    out.write("false");
                } else {
                    colors.add(color_hex, color_name);
                    out.write("true");
                }
            } else if (type.equals("edit")) {

                String color_id = (String) joFonts.get("color_id");
                String color_hex = (String) joFonts.get("color_hex");
                String color_name = (String) joFonts.get("color_name");
                boolean check = colors.checkAvailability(color_hex, color_name);
                if (check) {
                    out.write("false");
                } else {
                    colors.edit(Integer.parseInt(color_id), color_hex, color_name);
                    out.write("true");
                }
            } else if (type.equals("delete")) {
                String color_id = (String) joFonts.get("color_id");
                colors.delete(Integer.parseInt(color_id));
                out.write("true");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            out.close();
            getSqlMethodsInstance().closeConnection();
            colors.sqlmethods.closeConnection();
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
