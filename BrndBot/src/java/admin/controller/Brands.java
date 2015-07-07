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
public class Brands {
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

    public String getFileName(Integer brand_id){
        String fileName = "";
        try{
             query_string = "Select * from tbl_brand_personality where id="+brand_id+"";
             
             prepared_statement = sqlmethods.con.prepareStatement(query_string);
             result_set = prepared_statement.executeQuery();
             
             if (result_set.next()){
                 fileName = result_set.getString("image");
             }
             result_set.close();
             prepared_statement.close();
             
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return fileName;
    }
    
    public void addBrands(String brand_name, Integer look_id, String image)throws SQLException{
        try{
            query_string = "Insert into tbl_brand_personality (brand_name, look_id, image) values(?,?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setString(1, brand_name);
            prepared_statement.setInt(2, look_id);
            prepared_statement.setString(3, image);

            prepared_statement.executeUpdate();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        }
    
        public void editBrands(Integer brand_id, String brand_name, Integer look_id, String image)throws SQLException{
            try{
                 query_string = "UPDATE tbl_brand_personality"
                    + " SET brand_name='" + brand_name + "', look_id="+look_id+", image='"+image+ "'  WHERE id='" + brand_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

        public void deleteBrands(Integer org_id)throws SQLException{
            try{
                 query_string = "Delete From tbl_brand_personality"
                    + " WHERE id='" + org_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

}
