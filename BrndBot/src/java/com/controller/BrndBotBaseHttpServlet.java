/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AR
 */
public class BrndBotBaseHttpServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(BrndBotBaseHttpServlet.class));

    SqlMethods sql_methods;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public SqlMethods getSqlMethodsInstance() {
        return sql_methods;
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
        sql_methods = new SqlMethods();
    }
}
