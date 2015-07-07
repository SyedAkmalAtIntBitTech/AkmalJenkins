/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author intbit
 */
public class Categories {
    public String query_string;
    public PreparedStatement prepared_statement;
    public ResultSet result_set;
    SqlMethods sqlmethods = new SqlMethods();
    
    public boolean checkAvailability(String brand_name)throws SQLException{
        boolean check = false;
        try{
            query_string = "select * from tbl_brand_personality where brand_name='"+brand_name+"'";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
        if (result_set.next()){
            check = true;
        }
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return check;
    }

    public Integer getOrganizationID(Integer category_id){
        Integer Organization_id = 0;
        try{
             query_string = "Select * from tbl_category where id="+category_id+"";
             
             prepared_statement = sqlmethods.con.prepareStatement(query_string);
             result_set = prepared_statement.executeQuery();
             
             if (result_set.next()){
                 Organization_id = result_set.getInt("organization_id");
             }
             result_set.close();
             prepared_statement.close();
             
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return Organization_id;
    }

    public String getFileName(Integer category_id){
        String fileName = "";
        try{
             query_string = "Select * from tbl_category where id="+category_id+"";
             
             prepared_statement = sqlmethods.con.prepareStatement(query_string);
             result_set = prepared_statement.executeQuery();
             
             if (result_set.next()){
                 fileName = result_set.getString("image_name");
             }
             result_set.close();
             prepared_statement.close();
             
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return fileName;
    }
    
    public void addCategories(Integer organization_id, String category_name, String image_name)throws SQLException{
        try{
            query_string = "Insert into tbl_category (organization_id, category_name, image_name) values(?,?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setString(2, category_name);
            prepared_statement.setString(3, image_name);

            prepared_statement.executeUpdate();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        }
    
        public void editCategories(Integer category_id, Integer organization_id, String category_name, String image_name)throws SQLException{
            try{
                 query_string = "UPDATE tbl_category"
                    + " SET organization_id=" + organization_id + ", category_name='"+category_name+"', image_name='"+image_name+ "' WHERE id='" + category_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

        public void delete(Integer cat_id)throws SQLException{
            try{
                 query_string = "Delete From tbl_category"
                    + " WHERE id='" + cat_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

    
}
