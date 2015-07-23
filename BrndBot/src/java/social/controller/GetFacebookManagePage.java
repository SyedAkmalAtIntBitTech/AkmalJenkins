/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author sandeep-kumar
 */
public class GetFacebookManagePage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Facebook facebook;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FacebookException {

        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("213240565487592", "823a21d2cc734a2de158daf9d57650e8");
        facebook.setOAuthPermissions("publish_actions, publish_pages,manage_pages");
        response.sendRedirect(facebook.getOAuthAuthorizationURL("http://localhost:8084/BrndBot/GetFacebookManagePage"));
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1><a href='" + facebook.getOAuthAuthorizationURL("http://localhost:8084/BrndBot/selectpromotesocialmedia.jsp") + "'>click me!</a></h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            String fbCode = request.getParameter("code");
            if (fbCode == null) {
                processRequest(request, response);
            } else {
                System.out.println(fbCode);
                PrintWriter out = response.getWriter();
                out.println(facebook.getOAuthAccessToken(fbCode));
                // facebook.setOAuthAccessToken(facebook.getOAuthAccessToken(fbCode));
                ResponseList<Account> accounts = facebook.getAccounts();
                out.println(accounts.size());

                JSONObject obj = new JSONObject();
                JSONArray jsonarray = new JSONArray();
                String managepagedata[];
                for (int i = 0; i < accounts.size(); i++) {
                    Account yourPageAccount = accounts.get(i);  // if index 0 is your page account.
                    String pageAccessToken = yourPageAccount.getAccessToken();
                    String pageId = yourPageAccount.getId();
                    String profilepicture = facebook.getPagePictureURL(pageId).toString();
                    System.out.println(yourPageAccount.getName() + " - " + pageAccessToken);
                    facebook.setOAuthAccessToken(new AccessToken(pageAccessToken));

                    jsonarray.add(yourPageAccount.getName());
                    jsonarray.add(pageAccessToken);
                    jsonarray.add(profilepicture);

//               jsonarray.add(pageId);

//                obj.put("pagename",yourPageAccount.getName());
//               obj.put("Accesstoken", pageAccessToken);
//               jsonarray.add(i, request);

                }

                request.setAttribute("objkey", jsonarray);

                RequestDispatcher rd = request
                        .getRequestDispatcher("selectpromotesocialmedia.jsp");   // jsp to which i
                // want to send data
                rd.forward(request, response);
               

            }
        } catch (FacebookException ex) {
            Logger.getLogger(GetFacebookManagePage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (FacebookException ex) {
            Logger.getLogger(GetFacebookManagePage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
