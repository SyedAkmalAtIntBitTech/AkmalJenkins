/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class getColorsFromLogo extends HttpServlet {
sqlMethods SM = new sqlMethods();
getColorFromImage GC = new getColorFromImage();

    String filePath;
    String fileName,fieldName,uploadPath;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        JSONArray jarr = new JSONArray();
        SM.session = request.getSession(true);

        try {
                        uploadPath = getServletContext().getRealPath("") + "/images/Customers";
            
                        Integer user_id = (Integer)SM.session.getAttribute("UID");
                        
                        uploadPath = uploadPath + File.separator + user_id + File.separator + "logo";
                        String FileName = (String)SM.session.getAttribute("ImageFileName");
                        String FilePath = uploadPath + File.separator + FileName;
                        list = GC.getColors(FilePath);
                        Integer j = 1;
                        for(int i=0; i<list.size(); i++){
                            TblColors TC = new TblColors();
                            TC.setColorHex(list.get(i));
                            TC.setId("col"+j);
                            TC.setColorName("col"+j);
                            j = j +1;
                            jarr.add(TC);
                        }
                        
                        System.out.println(jarr);

                        json.put("Colors", jarr);
                        String jsonn = new Gson().toJson(json);
                        response.setContentType("application/json");
                        response.getWriter().write(jsonn);
                        
        
        }catch (Exception e){
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
