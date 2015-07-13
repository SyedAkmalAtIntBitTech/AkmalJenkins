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
public class FontThemes {
    public String query_string;
    public PreparedStatement prepared_statement;
    public ResultSet result_set;
    SqlMethods sqlmethods = new SqlMethods();
    
    public boolean checkAvailability(String brand_name)throws SQLException{
        boolean check = false;
        try{
            query_string = "select * from tbl_brand_color_theme where brand_name='"+brand_name+"'";

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
    
    public String getThemeNumber(){
        String theme = "theme";
        try{
            query_string = "select Max(theme_name) from tbl_brand_color_theme";
            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            
            result_set = prepared_statement.executeQuery();
            
            if (result_set.next() == false){
                    return theme + "1";
            }else{
                String id = result_set.getString(1);
                String EID = id.substring(5);
                int NID = Integer.parseInt(EID) +1;
                theme = theme + NID;
            }
            result_set.close();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return theme;
    }
    
    public void addFontTheme(Integer brand_id, Integer font_id1, Integer font_id2, Integer font_id3, Integer font_id4, Integer font_id5, Integer font_size1, Integer font_size2, Integer font_size3, Integer font_size4, Integer font_size5, Integer font_style1, Integer font_style2, Integer font_style3, Integer font_style4, Integer font_style5)throws SQLException{
        try{
            query_string = "Insert into tbl_brand_font_family (brand_id, font_id1, font_id2, font_id3, font_id4, font_id5, font_size1, font_size2, font_size3, font_size4, font_size5, font_styles1, font_styles2, font_styles3, font_styles4, font_styles5) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            prepared_statement = sqlmethods.con.prepareStatement(query_string);
            prepared_statement.setInt(1, brand_id);
            prepared_statement.setInt(2, font_id1);
            prepared_statement.setInt(3, font_id2);
            prepared_statement.setInt(4, font_id3);
            prepared_statement.setInt(5, font_id4);
            prepared_statement.setInt(6, font_id5);
            prepared_statement.setInt(7, font_size1);
            prepared_statement.setInt(8, font_size2);
            prepared_statement.setInt(9, font_size3);
            prepared_statement.setInt(10, font_size4);
            prepared_statement.setInt(11, font_size5);
            prepared_statement.setInt(12, font_style1);
            prepared_statement.setInt(13, font_style2);
            prepared_statement.setInt(14, font_style3);
            prepared_statement.setInt(15, font_style4);
            prepared_statement.setInt(16, font_style5);
            
            prepared_statement.executeUpdate();
            prepared_statement.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        }
    
        public void editFontTheme(Integer font_theme_id, Integer brand_id, Integer font_id1, Integer font_id2, Integer font_id3, Integer font_id4, Integer font_id5, Integer font_size1, Integer font_size2, Integer font_size3, Integer font_size4, Integer font_size5, Integer font_style1, Integer font_style2, Integer font_style3, Integer font_style4, Integer font_style5)throws SQLException{
            try{
                 query_string = "UPDATE tbl_brand_font_family"
                    + " SET brand_id='" + brand_id + "', font_id1="+font_id1+",font_id2="+font_id2+",font_id3="+font_id3+",font_id4="+font_id4+",font_id5="+font_id5+","
                    + " font_size1="+font_size1+", font_size2="+font_size2+", font_size3="+font_size3+", font_size4="+font_size4+", font_size5="+font_size5+", "
                    + " font_styles1="+font_style1+", font_styles2="+font_style2+", font_styles3="+font_style3+", font_styles4="+font_style4+", font_styles5="+font_style5+""

                    + " WHERE id='" + font_theme_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }

        public void delete(Integer font_theme_id)throws SQLException{
            try{
                 query_string = "Delete From tbl_brand_font_family"
                    + " WHERE id='" + font_theme_id + "'";
                 
                 prepared_statement = sqlmethods.con.prepareStatement(query_string);
                 prepared_statement.executeUpdate();
                 prepared_statement.close();
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }
    
}