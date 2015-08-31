/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import static com.controller.BrndBotBaseHttpServlet.logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.intbit.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ilyas
 */
public class ServletListAdminBackgroundImages extends BrndBotBaseHttpServlet {

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String imageBasePath = "";
            imageBasePath = AppConstants.ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME;

            File backgroundImagesFolder = new File(imageBasePath);
            if (!backgroundImagesFolder.exists()) {
                backgroundImagesFolder.mkdirs();
            }
            File[] listOfImageFiles = backgroundImagesFolder.listFiles();

            JSONArray json_arr = new JSONArray();
            for (int i = 0; i < listOfImageFiles.length; i++) {
                JSONObject json_ob = new JSONObject();
                if (listOfImageFiles[i].isFile()) {
                    json_ob.put("id", "" + i);
                    json_ob.put("filename", listOfImageFiles[i].getName());

                    json_arr.add(json_ob);

                }
            }

            out.write(new Gson().toJson(json_arr));

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while reading admin background images:", e.getMessage()));

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
