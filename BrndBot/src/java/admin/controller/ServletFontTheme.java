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
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletFontTheme extends BrndBotBaseHttpServlet {

    RequestDispatcher request_dispatcher;
    FontThemes fontthemes;
    Long font1, font2, font3, font4, font5, font_size1, font_size2, font_size3, font_size4, font_size5, font_style1, font_style2, font_style3, font_style4, font_style5;
    String CC = "CC";
    Integer cl = 1;

     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            fontthemes = new FontThemes();
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
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            JSONObject joFontTheme = null;
            joFontTheme = (JSONObject) parser.parse(string_buffer.toString());

            String type = (String) joFontTheme.get("type");
            if (type.equals("delete")) {
                String font_theme_id = (String) joFontTheme.get("color_theme_id");
                fontthemes.delete(Integer.parseInt(font_theme_id));
            } else if (type.equals("add")) {
                String brand = (String) joFontTheme.get("brand_id");
                JSONArray font_name_array = (JSONArray) joFontTheme.get("font_name_array");
                JSONArray font_size_array = (JSONArray) joFontTheme.get("font_size_array");
                JSONArray font_style_array = (JSONArray) joFontTheme.get("font_style_array");

                for (int i = 0; i < font_name_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font1 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font2 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font3 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font4 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font5 = (Long) font_name_array.get(i);
                    }

                }

                for (int i = 0; i < font_size_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font_size1 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font_size2 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font_size3 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font_size4 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font_size5 = (Long) font_size_array.get(i);
                    }

                }

                for (int i = 0; i < font_style_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font_style1 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font_style2 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font_style3 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font_style4 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font_style5 = (Long) font_style_array.get(i);
                    }

                }

                fontthemes.addFontTheme(Integer.parseInt(brand), font1.intValue(), font2.intValue(), font3.intValue(), font4.intValue(), font5.intValue(), font_size1.intValue(), font_size2.intValue(), font_size3.intValue(), font_size4.intValue(), font_size5.intValue(), font_style1.intValue(), font_style2.intValue(), font_style3.intValue(), font_style4.intValue(), font_style5.intValue());

            } else if (type.equals("update")) {

                String font_theme_id = (String) joFontTheme.get("font_theme_id");
                String brand = (String) joFontTheme.get("brand_id");
                JSONArray font_name_array = (JSONArray) joFontTheme.get("font_name_array");
                JSONArray font_size_array = (JSONArray) joFontTheme.get("font_size_array");
                JSONArray font_style_array = (JSONArray) joFontTheme.get("font_style_array");

                for (int i = 0; i < font_name_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font1 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font2 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font3 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font4 = (Long) font_name_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font5 = (Long) font_name_array.get(i);
                    }

                }

                for (int i = 0; i < font_size_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font_size1 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font_size2 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font_size3 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font_size4 = (Long) font_size_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font_size5 = (Long) font_size_array.get(i);
                    }

                }

                for (int i = 0; i < font_style_array.size(); i++) {
                    CC = "CC" + i;

                    if (CC.equals("CC0")) {
                        font_style1 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC1")) {
                        font_style2 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC2")) {
                        font_style3 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC3")) {
                        font_style4 = (Long) font_style_array.get(i);
                    }
                    if (CC.equals("CC4")) {
                        font_style5 = (Long) font_style_array.get(i);
                    }

                }

                fontthemes.editFontTheme(Integer.parseInt(font_theme_id), Integer.parseInt(brand), font1.intValue(), font2.intValue(), font3.intValue(), font4.intValue(), font5.intValue(), font_size1.intValue(), font_size2.intValue(), font_size3.intValue(), font_size4.intValue(), font_size5.intValue(), font_style1.intValue(), font_style2.intValue(), font_style3.intValue(), font_style4.intValue(), font_style5.intValue());
            }

            out.write("true");
        } catch (Exception e) {
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
