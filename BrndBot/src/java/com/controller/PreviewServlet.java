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

/**
 *
 * @author ilyas
 */
public class PreviewServlet extends BrndBotBaseHttpServlet {

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
        String htmlString = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
"                <tr onclick=\"getBlockId(defaultblock1)\" id=\"defaultblock1\" name=\"72194\" style=\"width: 671px; height: 338px;\">\n" +
"                    <td background=\"http://s4.postimg.org/veg9e2nnx/yogapics.jpg\" style=\"position: absolute; left: 1px; top: 3px; width: 671px; height: 338px; vertical-align: top;background-repeat:no-repeat;background-size: 100% 100%;opacity: 1;\">\n" +
"                        \n" +
"                    <table>\n" +
"                        <tr>\n" +
"                        <td onclick=\"getDivId(defaultblock1colorblock1)\" id=\"defaultblock1colorblock1\" style=\"left: 1px; top: 2px; width: 250px; height: 338px; position: relative; background-color: rgb(210, 78, 78);opacity: 0.9\">\n" +
"                            <table>\n" +
"                                <tr>\n" +
"                                    <td style=\"color: rgb(255, 255, 255); margin-left: 41px; margin-top: 40px; width: 159px; height: 34px; font-size: 12px; font-style: normal; font-weight: 400; letter-spacing: 0px; line-height: 15px; opacity: 1; text-align: left; transform: rotate(0deg); resize: none; border: none; background-color: inherit;\">\n" +
"                                        NEW CLASS\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                            <table>\n" +
"                                <tr>\n" +
"                                    <td style=\"color: rgb(255, 255, 255); margin-left: 40px; margin-top: 35px; width: 259px; height: 64px; font-size: 22px; font-style: normal; font-weight: 400; letter-spacing: 0px; line-height: 15px; opacity: 1; text-align: left; transform: rotate(0deg); resize: none; border: none; background-color: inherit;\">HOT POWER YOGA</td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                            <table>                                \n" +
"                            <tr>\n" +
"                                <td style=\"color: rgb(255, 255, 255); margin-left: 50px; margin-top: 140px; width: 179px; height: 34px; font-size: 14px; font-style: italic; font-weight: 400; letter-spacing: 0px; line-height: 15px; opacity: 1; text-align: left; transform: rotate(0deg); resize: none; border: none; background-color: inherit;\">TEACHER</td>\n" +
"                            </tr>\n" +
"                            </table>\n" +
"                            \n" +
"                            <table>\n" +
"                                <tr>\n" +
"                                    <td style=\"color: rgb(255, 255, 255); margin-left: 51px; margin-top: 30px; width: 239px; height: 34px; font-size: 18px; font-style: normal; font-weight: 400; letter-spacing: 0px; line-height: 15px; opacity: 1; text-align: left; transform: rotate(0deg); resize: none; border: none; background-color: inherit;\">DAYS AND TIME</td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                        </tr>\n" +
"                    </table>\n" +
"                    \n" +
"                    </td>\n" +
"                </tr>\n" +
"            </table>";
        response.getWriter().write("<html><head><title>this is test</title><body>"+htmlString+"</body></head></html>");
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
