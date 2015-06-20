/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import pojos.TblCategory;

/**
 *
 * @author intbit
 */
public class getUserCategories extends HttpServlet {
sqlMethods SM = new sqlMethods();
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
        PreparedStatement ps;
        ResultSet rs;
        SM.session = request.getSession(true);
        JSONArray jarr = new JSONArray();
        try {
            /* TODO output your page here. You may use following sample code. */
            Integer user_id = (Integer)SM.session.getAttribute("UID");
            
            SM.setConnection();
            Integer org_id = SM.getOrganizationID(user_id);
//            Integer org_id = 11;
//            String org_name = SM.getOrganizationName(org_id);
//            SM.session.setAttribute("org_name", org_name);
            String Query = "Select * from tbl_category where organization_id='"+org_id+"' Order By id ASC";
            ps = SM.con.prepareStatement(Query);
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                TblCategory TC = new TblCategory();
                TC.setId(rs.getInt("id"));
                TC.setOrganizationId(rs.getInt("organization_Id"));
                TC.setCategoryName(rs.getString("category_name"));
                TC.setUserId(rs.getString("user_id"));
                TC.setImage_name(rs.getString("image_name"));
                jarr.add(TC);
            }
            
            rs.close();
            ps.close();
            SM.con.close();
            String json = new Gson().toJson(jarr);
            response.setContentType("application/json");
            response.getWriter().write(json);
            
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
