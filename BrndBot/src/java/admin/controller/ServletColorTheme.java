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
public class ServletColorTheme extends BrndBotBaseHttpServlet {
    ColorThemes colorthemes;
    String color1,color2,color3,color4,color5,color6;
    String CC = "CC";
    Integer cl = 1;
    
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            colorthemes = new ColorThemes();
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
            
            String type = (String) joUser.get("type");
            if(type.equals("delete")){
                String color_theme_id = (String) joUser.get("color_theme_id");
                colorthemes.delete(Integer.parseInt(color_theme_id));
            }else if (type.equals("add")){
            String brand = (String) joUser.get("brand_id");
            JSONArray color = (JSONArray) joUser.get("colors");
            
            for(int i =0; i< color.size(); i++){
                CC = "CC" + i;

                if (CC.equals("CC0")){
                    color1 = (String)color.get(i);
                }
                if (CC.equals("CC1")){
                    color2 = (String)color.get(i);
                }
                if (CC.equals("CC2")){
                    color3 = (String)color.get(i);
                }
                if (CC.equals("CC3")){
                    color4 = (String)color.get(i);
                }
                if (CC.equals("CC4")){
                    color5 = (String)color.get(i);
                }
                if (CC.equals("CC5")){
                    color6 = (String)color.get(i);
                }
            }
            
            String theme = colorthemes.getThemeNumber();
            colorthemes.addColorTheme(Integer.parseInt(brand) , color1, color2, color3, color4, color5, color6, theme);
            }else if (type.equals("update")){
                
            String theme_id = (String) joUser.get("theme_id");
            String brand = (String) joUser.get("brand_id");
            JSONArray color = (JSONArray) joUser.get("colors");
            
            for(int i =0; i< color.size(); i++){
                CC = "CC" + i;

                if (CC.equals("CC0")){
                    color1 = (String)color.get(i);
                }
                if (CC.equals("CC1")){
                    color2 = (String)color.get(i);
                }
                if (CC.equals("CC2")){
                    color3 = (String)color.get(i);
                }
                if (CC.equals("CC3")){
                    color4 = (String)color.get(i);
                }
                if (CC.equals("CC4")){
                    color5 = (String)color.get(i);
                }
                if (CC.equals("CC5")){
                    color6 = (String)color.get(i);
                }
            }
            
            String theme = colorthemes.getThemeNumber();
            colorthemes.editTheme(Integer.parseInt(theme_id), Integer.parseInt(brand) , color1, color2, color3, color4, color5, color6);
            }            
            
            out.write("true");
        }catch (Exception e){
            out.write(getSqlMethodsInstance().error);
            logger.log(Level.SEVERE, "", e);
        }finally {
            out.close();
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
