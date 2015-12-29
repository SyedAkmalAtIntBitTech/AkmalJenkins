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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author development
 */
public class ServletUserBrand extends BrndBotBaseHttpServlet{
    UserBrands userbrands;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            userbrands  = new UserBrands();
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
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer string_buffer = new StringBuffer();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject json_organization = null;
            json_organization = (JSONObject) parser.parse(string_buffer.toString());
            String type = (String) json_organization.get("type");
            if (type.equals("add")) {
                String user_id = (String) json_organization.get("users_id");
                String brand_id = (String) json_organization.get("brand_id");
                String organization_id = (String) json_organization.get("organization_id");

                boolean check = userbrands.checkAvailability(Integer.parseInt(organization_id));
                if (check) {
                    out.write("false");
                } else {
                    userbrands.addUserBrands(Integer.parseInt(user_id), 
                                             Integer.parseInt(brand_id),
                                             Integer.parseInt(organization_id));
                    out.write("true");
                }
            } else if (type.equals("edit")) {
                String id = (String)json_organization.get("id");
                String user_id = (String) json_organization.get("users_id");
                String brand_id = (String) json_organization.get("brand_id");
                String organization_id = (String) json_organization.get("organization_id");
                boolean check = userbrands.checkAvailability(Integer.parseInt(organization_id));
                if (check) {
                    out.write("false");
                } else {
                    userbrands.changeUserBrands(Integer.parseInt(id), user_id, brand_id, organization_id);
                    out.write("true");
                }
            } else if (type.equals("delete")) {
                Long user_brand_id = (Long) json_organization.get("user_brand");
                userbrands.deleteUserBrand(user_brand_id.intValue());
                out.write("true");
            }
            
        }catch (Exception e){
            logger.log(Level.SEVERE, "", e);
        }finally {
            try {
                out.close();
            }catch (Exception e){
                logger.log(Level.SEVERE, "", e);
            }
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
