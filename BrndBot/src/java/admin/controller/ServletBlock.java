/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletBlock extends BrndBotBaseHttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        StringBuffer string_buffer = new StringBuffer();
        Blocks block = null;

        try {
            block = new Blocks();
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject joBlocks = null;
            joBlocks = (JSONObject) parser.parse(string_buffer.toString());
            String type = (String) joBlocks.get("type");
            if (type.equals("add")) {
                String name = (String) joBlocks.get("name");
                String mindbodyquery = (String) joBlocks.get("mindbodyquery");
                String brand_id = (String) joBlocks.get("brand_id");
                String sub_category_id = (String) joBlocks.get("sub_category_id");

                boolean check = block.checkAvailability(name);
                if (check) {
                    out.write("false");
                } else {
                    block.addBlock(name, mindbodyquery, Integer.parseInt(brand_id), Integer.parseInt(sub_category_id));
                    out.write("true");
                }
            } else if (type.equals("edit")) {
                String block_id = (String)joBlocks.get("block_id");
                String name = (String) joBlocks.get("name");
                String mindbodyquery = (String) joBlocks.get("mindbodyquery");
                String brand_id = (String) joBlocks.get("brand_id");
                String sub_category_id = (String) joBlocks.get("sub_category_id");
                boolean check = block.checkAvailability(name);
                if (check) {
                    out.write("false");
                } else {
                    block.changeBlock(Integer.parseInt(block_id), name, mindbodyquery, Integer.parseInt(brand_id), Integer.parseInt(sub_category_id));
                    out.write("true");
                }
            } else if (type.equals("delete")) {
                String block_id = (String) joBlocks.get("block_id");
                block.deleteBlock(Integer.parseInt(block_id));
                out.write("true");
            }
            
        }catch (Exception ex){
            logger.log(Level.SEVERE, "", ex);
            out.write(getSqlMethodsInstance().error);
        }
        finally {
            out.close();
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
