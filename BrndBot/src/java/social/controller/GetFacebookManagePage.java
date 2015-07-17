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
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        facebook.setOAuthAppId("1572856859649560", "997c5f81d6c69922e49f61121a3af3e9");
        facebook.setOAuthPermissions("publish_actions");
        //facebook.setOAuthAccessToken(new AccessToken("213240565487592|R5CFrBQpaEOU-XBcmxfFqJ515x4"));
        //facebook.postStatusMessage("Hello World from Facebook4J.");
        response.setContentType("text/html;charset=UTF-8");
    
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><a href='" + facebook.getOAuthAuthorizationURL("http://localhost:8084/BrndBot/GetFacebookManagePage") + "'>click me!</a></h1>");
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
                PrintWriter out = response.getWriter();
                out.println(facebook.getOAuthAccessToken(fbCode));
                // facebook.setOAuthAccessToken(facebook.getOAuthAccessToken(fbCode));
                ResponseList<Account> accounts = facebook.getAccounts();
                out.println(accounts.size());
                Account yourPageAccount = accounts.get(5);  // if index 0 is your page account.
                String pageAccessToken = yourPageAccount.getAccessToken();
                out.println(yourPageAccount.getName() + " - " + pageAccessToken);
                facebook.setOAuthAccessToken(new AccessToken(pageAccessToken));

                PostUpdate post = new PostUpdate("Testing 123")
                        .picture(new URL("http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg"))
                        .name("Tiger1")
                        .caption("Tiger")
                        .link(new URL("http://www.yahoo.com"))
                        .description("Tiger description.");
                facebook.postFeed(post);

//facebook.postStatusMessage("Hello World from Facebook4J. 123");
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
