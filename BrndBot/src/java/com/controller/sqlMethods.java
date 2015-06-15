/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.TblColors;
/**
 *
 * @author intbit
 */
public class sqlMethods {
    public static Connection con;
    HttpServletRequest request;
    HttpServletResponse response;
    public HttpSession session;
    public static Integer limit = 4;
    public Integer Upper_limit = 4;

    public void setConnection()throws ClassNotFoundException,SQLException{
                
        try{
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/brnd",
                    "postgres", "123456");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    
    public void setUpperLimit(){
       System.out.println("Testing the functionality");
        session = request.getSession(true);
        Integer ll = (Integer)session.getAttribute("limit");
        
        Upper_limit = ll;
        try{
            response.sendRedirect(request.getContextPath()+ "/chooseLook.jsp");
        }catch (Exception e){}
        }
    
    public Integer getLimit(){
        return Upper_limit;
    }
    
    public void setLowerLimit(){
        session = request.getSession(true);
        Integer ll = (Integer)session.getAttribute("limit");
        
        Upper_limit = ll - 16;
        if (Upper_limit == 0){
            setInitLimit();
        }
        try{
            response.sendRedirect(request.getContextPath()+ "/chooseLook.jsp");
        }catch (Exception e){}
        
    }
    public void setInitLimit(){
        this.Upper_limit = limit;
    }
    
    public List getOrganization()throws ClassNotFoundException, SQLException{
        PreparedStatement ps;
        ResultSet rs;
        List AL = new ArrayList();
        
        try{
                String Query = "Select * from tbl_organization";
                ps = con.prepareStatement(Query);
                rs = ps.executeQuery();
                
                while (rs.next()){
                        AL.add(rs.getString("organization_name"));
                }
                rs.close();
                ps.close();
        }catch (Exception e){
            System.out.println(e.getCause()+","+e.getMessage()+","+e.getStackTrace());
        }
            return AL;
    }
    
    public TblColors getColors(String id)throws  ClassNotFoundException,SQLException{
        PreparedStatement ps1;
        ResultSet rs1;
        String Query1;
        TblColors TC = new TblColors();
        Query1 = "Select * from tbl_colors where id="+id+"";
        ps1 = con.prepareStatement(Query1);

        rs1 = ps1.executeQuery();
        if (rs1.next()){
            TC.setId("color"+id);
            TC.setColorName(rs1.getString("color_name"));
            TC.setColorHex(rs1.getString("color_hex"));
        }   
        ps1.close();
        rs1.close();
        return TC;
    }
    
    public boolean checkAvailability(String UserName,String Password)throws ClassNotFoundException, SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean checked = false;
        try{
            
            String Query = "Select * from tbl_user_login_details where user_name='"+UserName+"' and password='"+Password+"'";
            ps = con.prepareStatement(Query);
            rs = ps.executeQuery();

            if (rs.next()){
                checked = true;
                Integer UID = rs.getInt("id");
            }
            
            rs.close();
            ps.close();
        }catch(SQLException e){
            System.out.println(e.getCause()+","+e.getMessage()+","+e.getStackTrace());
        }
        return checked;
    }
    
    public void addUserPreferences(Integer user_id, Integer brand_id, Integer font_theme_id, String location,Integer look_id, String color1, String color2, String color3, String color4, String color5, String color6){
        PreparedStatement ps;
        try{
            String Query = "Insert Into tbl_user_preferences(user_id,brand_id,font_theme_id,location,look_id,color1,color2,color3,color4,color5,color6) Values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Query);
            
            ps.setInt(1, user_id);
            ps.setInt(2, brand_id);
            ps.setInt(3, font_theme_id);
            ps.setString(4, location);
            ps.setInt(5, look_id);
            ps.setString(6, color1);
            ps.setString(7, color2);
            ps.setString(8, color3);
            ps.setString(9, color4);
            ps.setString(10, color5);
            ps.setString(11, color6);
            
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
    
    public int getFontthemeid(String brndid)throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        Integer IDNO = 0;
        
        String Query = "Select id from tbl_brand_font_family where brand_id='"+brndid+"'";
        ps = con.prepareStatement(Query);
        rs = ps.executeQuery();
        
        if (rs.next()){
            IDNO = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return IDNO;
    }
    
    public void addUsers(String User_id, String password)throws SQLException{
        PreparedStatement ps;
        try{
            String Query = "Insert Into tbl_user_login_details(user_name,password,organizationid,logo_name,company_name) Values(?,?,?,?,?)";
            ps = con.prepareStatement(Query);

            ps.setString(1, User_id);
            ps.setString(2, password);
            ps.setInt(3, 0);
            ps.setString(4, "");
            ps.setString(5, "");

            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    public void updateUsers(int idno, String fileName)throws SQLException{
        PreparedStatement ps;
        try{
            String Query = "UPDATE tbl_user_login_details" +
                                    "   SET logo_name='"+fileName+"'  WHERE id='"+idno+"'";

            ps = con.prepareStatement(Query);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    public void updateUsersOrg(int idno, int Org_id, String Company_name)throws SQLException{
        PreparedStatement ps;
        try{
            String Query = "UPDATE tbl_user_login_details" +
                                    "   SET organizationid ='"+Org_id+"', company_name='"+Company_name+"' WHERE id='"+idno+"'";

            ps = con.prepareStatement(Query);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    public int getUserID(String email)throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        int ID = 0;

                String Query = "Select id from tbl_user_login_details where user_name ='"+email+"'";
                ps = con.prepareStatement(Query);
                
                rs = ps.executeQuery();
                
                if (rs.next()){
                    ID  = rs.getInt(1);
                }
                rs.close();
                ps.close();
                return ID;
    }
    
    public void setLookID()throws IOException{
        System.out.println("LookID"+":"+"LookID");
    }
    
}
