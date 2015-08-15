/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class GetColorsFromLogo extends BrndBotBaseHttpServlet {

    GetColorFromImage getcolorsfromimages = new GetColorFromImage();

    ArrayList<String> list = new ArrayList<String>();

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
        JSONObject json = new JSONObject();
        JSONArray jarr = new JSONArray();
        String filePath;
        String fileName, fieldName, uploadPath;

        getSqlMethodsInstance().session = request.getSession(true);
        try {
            
            uploadPath = AppConstants.USER_LOGO;

//            uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "Customers";

            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            uploadPath = uploadPath + File.separator + user_id + File.separator + "logo";
            
            String FileName = (String) getSqlMethodsInstance().session.getAttribute("ImageFileName");

            String FilePath = uploadPath + File.separator + FileName;
            list = getcolorsfromimages.getColors(FilePath);

            Integer j = 1;
            if (list.size() == 0){
                out.write(getSqlMethodsInstance().error);
            }else{
                for (int i = 0; i < list.size(); i++) {
                    TblColors colors = new TblColors();
                    colors.setColorHex(list.get(i));
                    colors.setId("col" + j);
                    colors.setColorName("col" + j);
                    j = j + 1;
                    jarr.add(colors);
                }

                System.out.println(jarr);

                json.put("Colors", jarr);
                String jsonn = new Gson().toJson(json);
                response.setContentType("application/json");
                out.write(jsonn);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
