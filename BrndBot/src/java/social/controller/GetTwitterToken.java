/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 *
 * @author sandeep-kumar
 */
public class GetTwitterToken extends BrndBotBaseHttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetTwitterToken</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetTwitterToken at " + request.getContextPath() + "</h1>");
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
    
     private String token ="";
    private String tokenSecret = "";
    private Twitter twitter;
    private RequestToken requestToken;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try
         {
            twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("K7TJ3va8cyAeh6oN3Hia91S2o", "IWUt2aDVTHgUc8N0qI0cF1Z1dTAEQ7CSgnBymZNr3BPSmtkNHL");
            //twitter.setOAuthConsumer("G6fPQU023izaVT8RtAurlHmUW", "d6jMSiwI9XqVbNDMQ4XJmIzD9XrKwZC5mKjrbujepTOqgrnMEW");
            requestToken = twitter.getOAuthRequestToken();
            
            response.setContentType("text/plain");
            response.getWriter().write(requestToken.getAuthenticationURL());
  
            }
         catch (TwitterException ex) {
            Logger.getLogger(GetTwitterToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
            Logger.getLogger(GetTwitterToken.class.getName()).log(Level.SEVERE, null, e);
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
//        processRequest(request, response);
        String pin = request.getParameter("pin");
        AccessToken accessToken = null;
        String user_name = "";
         try{
                if(pin.length() > 0){
                  accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                  accessToken = twitter.getOAuthAccessToken();
                }
                twitter4j.User user = twitter.showUser(twitter.getScreenName());
                user_name = user.getName();
             } catch (TwitterException te) {
                if(401 == te.getStatusCode()){
                  logger.log(Level.SEVERE, "Unable to get the access token.", te);
                }else{
                  logger.log(Level.SEVERE, "", te);
                }
            }
        
        token = accessToken.getToken();
        tokenSecret = accessToken.getTokenSecret();   
        response.setContentType("text/plain");
        response.getWriter().write(token+","+tokenSecret+","+user_name);
        
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
