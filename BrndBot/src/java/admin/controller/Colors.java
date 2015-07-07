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
public class Colors {
    public String query_string;
    public PreparedStatement prepared_statement;
    public ResultSet result_set;
    SqlMethods sqlmethods = new SqlMethods();
    
    public boolean checkAvailability(String color_hex, String color_name)throws SQLException{
        boolean check = false;
        try{
            query_string = "select * from tbl_colors where color_hex='"+color_hex+"' and color_name='"+ color_name +"'";

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

    public void add(String color_hex, String color_name)throws SQLException{
        try{
            query_string = "Insert into tbl_colors (color_hex, color_name) values(?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setString(1, color_hex);
            prepared_statement.setString(2, color_name);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        }
    
        public void edit(Integer org_id, String color_hex, String color_name)throws SQLException{
            try{
                 query_string = "UPDATE tbl_colors"
                    + " SET color_hex='" + color_hex + "', color_name='"+color_name+"'  WHERE id='" + org_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

        public void delete(Integer org_id)throws SQLException{
            try{
                 query_string = "Delete From tbl_colors"
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
