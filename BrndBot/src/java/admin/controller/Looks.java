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
public class Looks {
    public String query_string;
    public PreparedStatement prepared_statement;
    public ResultSet result_set;
    SqlMethods sqlmethods = new SqlMethods();
    
    public boolean checkAvailabilities(Integer id, String look_name)throws SQLException{
        boolean check = false;
        try{
            query_string = "select * from tbl_look where id='"+ id +"' and look_name='"+look_name+"'";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            
        if (result_set.next()){
            check = true;
        }else {
            check = false;
        }
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return check;
    }

    public boolean checkAvailability(String look_name)throws SQLException{
        boolean check = false;
        try{
            query_string = "select * from tbl_look where look_name='"+look_name+"'";

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

    public String getFileName(Integer look_id){
        String fileName = "";
        try{
             query_string = "Select * from tbl_look where id="+look_id+"";
             
             prepared_statement = sqlmethods.con.prepareStatement(query_string);
             result_set = prepared_statement.executeQuery();
             
             if (result_set.next()){
                 fileName = result_set.getString("file_name");
             }
             result_set.close();
             prepared_statement.close();
             
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return fileName;
    }
    
    public void addLooks(String look_name, String file_name)throws SQLException{
        try{
            query_string = "Insert into tbl_look (look_name, file_name) values(?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setString(1, look_name);
            prepared_statement.setString(2, file_name);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        }

        public void changeLooks(Integer org_id, String look_name, String file_name)throws SQLException{
            try{
                
                 String query_string1 = "UPDATE tbl_look"
                    + " SET look_name='" + look_name + "', file_name='"+file_name+ "'  WHERE id='" + org_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
                 
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }
    
        public void changeLookImage(Integer org_id, String look_name, String file_name)throws SQLException{
            try{
                
                 query_string = "UPDATE tbl_look"
                    + " SET file_name='"+file_name+ "' WHERE id='" + org_id + "' and look_name='"+look_name+"'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
                 
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

        public void deleteLooks(Integer org_id)throws SQLException{
            try{
                 query_string = "Delete From tbl_look"
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