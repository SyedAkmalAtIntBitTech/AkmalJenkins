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
public class Layout {
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
    
    public void addLayouts(Integer organization_id, Integer user_id, Integer category_id, String layout, String model, boolean email, boolean social )throws SQLException{
        try{
            query_string = "Insert into tbl_model (organization_id, user_id, category_id, layout, model, email, social) values(?,?,?,?,?,?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setInt(2, user_id);
            prepared_statement.setInt(3, category_id);
            prepared_statement.setString(4, layout);
            prepared_statement.setString(5, model);
            prepared_statement.setBoolean(6, email);
            prepared_statement.setBoolean(7, social);

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
