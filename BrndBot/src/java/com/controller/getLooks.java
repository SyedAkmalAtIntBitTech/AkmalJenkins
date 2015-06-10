/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import static com.controller.sqlMethods.con;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.organization;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.look;

/**
 *
 * @author intbit
 */
public class getLooks extends HttpServlet {

    sqlMethods SM = new sqlMethods();
    public static Integer ll1 = 4;

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
        String Query = "", Query1 = "";
        PreparedStatement ps;
        ResultSet rs;
        JSONObject JO = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONArray arr1 = new JSONArray();
        SM.session = request.getSession(true);
        try {
            SM.setConnection();
//                Integer lval = (Integer)SM.session.getAttribute("limit");
            // Integer l1 = Integer.parseInt(lval);
//                Integer l1 = lval;
/*                if (l1 == 4){
             }else{
             Query = "Select * from tbl_look limit 4 OFFSET "+l1+"";
             } */
            Query = "Select * from tbl_look limit 4";
            ps = SM.con.prepareStatement(Query);
            rs = ps.executeQuery();
            while (rs.next()) {
                look lk = new look();
                int id = rs.getInt("id");
                String look_name = rs.getString("look_name");
                lk.setId(id);
                lk.setLook_name(look_name);
                arr.add(lk);
            }
            rs.close();  
            ps.close();
            JO.put("first", arr);
            // Query1 = "Select * from tbl_look limit "+l1+" OFFSET "+l1+"";
            // System.out.println(Query1);
/*                Integer l2 = 0;
             if (l1 == 4){
             l2 = l1;
             }else{
             l2 = l1 + 4;
             } */
//                Query1 = "Select * from tbl_look limit 4 OFFSET "+l2+"";
            Query1 = "Select * from tbl_look limit 4 OFFSET 4";
            ps = SM.con.prepareStatement(Query1);
            rs = ps.executeQuery();
            while (rs.next()) {
                look lk = new look();
                int id = rs.getInt("id");
                String look_name = rs.getString("look_name");
                lk.setId(id);
                lk.setLook_name(look_name);
                arr1.add(lk);
            }
            rs.close();
            ps.close();
            JO.put("second", arr1);
//            l2 = l2 + 4;
//            SM.session.setAttribute("limit", l2);
            String json = new Gson().toJson(JO);
            response.setContentType("application/json");
            response.getWriter().write(json);

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
