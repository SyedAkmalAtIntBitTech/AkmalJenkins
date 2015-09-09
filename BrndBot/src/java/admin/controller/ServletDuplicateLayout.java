/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import com.intbit.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ilyas
 */
public class ServletDuplicateLayout extends BrndBotBaseHttpServlet {

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
        Integer organization_id = null, user_id = null, category_id = null, subcategory_id = null, block_id = null;
        String layout_file_name = null, model_file_name = null, image_file_name = null, html_file_name = null, model_name = null;
        Boolean email = null, social = null;
        Integer modelID = Integer.parseInt(request.getParameter("modelid"));
        Integer brandID = Integer.parseInt(request.getParameter("brandid"));
        PrintWriter out = response.getWriter();
            String query_string;
           //String query_string = "Update tbl_model Set brand_id="+brandID+" Where id="+modelID;
           SqlMethods sqlmethods = new SqlMethods();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = sqlmethods.getConnection()) {
            query_string = "Select * From tbl_model Where id="+modelID;
            
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            while(result_set.next()){
                
                organization_id = result_set.getInt("organization_id");
                user_id = result_set.getInt("user_id");
                category_id = result_set.getInt("category_id");
                subcategory_id = result_set.getInt("sub_category_id");
                block_id = result_set.getInt("block_id");
                layout_file_name = result_set.getString("layout_file_name");
                model_file_name = result_set.getString("model_file_name");
                image_file_name = result_set.getString("image_file_name");
                html_file_name = result_set.getString("html_file_name");
                model_name = result_set.getString("model_name");
                social = result_set.getBoolean("social");
                email = result_set.getBoolean("email");
            } 
            query_string = "insert into tbl_model (organization_id, user_id, category_id, layout_file_name, model_file_name, email, social, "
                    + "sub_category_id, brand_id, block_id, image_file_name, html_file_name, model_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setInt(2, user_id);
            prepared_statement.setInt(3, category_id);
            prepared_statement.setString(4, layout_file_name);
            prepared_statement.setString(5, model_file_name);
            prepared_statement.setBoolean(6, email);
            prepared_statement.setBoolean(7, social);
            prepared_statement.setInt(8, subcategory_id);
            prepared_statement.setInt(9, brandID);
            prepared_statement.setInt(10, block_id);
            prepared_statement.setString(11, image_file_name);
            prepared_statement.setString(12, html_file_name);
            prepared_statement.setString(13, model_name);
            prepared_statement.executeUpdate();
            
            out.write("Record saved successfully");
        }   
        
        catch(SQLException se)
        {
            logger.log(Level.SEVERE, "", se);
        }
        catch (Exception e) {
            out.write("Oops! something went wrong");
        logger.log(Level.SEVERE, "", e);
        }finally {
            
            out.close();
            sqlmethods.close(result_set, prepared_statement);
            sqlmethods.closeConnection();
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
