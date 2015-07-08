<%-- 
    Document   : displayfonts
    Created on : Jul 6, 2015, 6:11:32 PM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%!
    SqlMethods SM =new SqlMethods();
%>
<%
Integer user_id=Integer.parseInt(request.getParameter("user_id"));  

 String buffer1="<select id='fontsizes'><option value='none'>Select</option>";  
 String buffer2="<select id='fontnames'><option value='none'>Select</option>";  

 try{

    SM.setDatabaseConnection();
    Statement stmt = SM.con.createStatement();  

//    ResultSet rs = stmt.executeQuery("Select * from tbl_user_login_details where organizationid="+org_id+"");  
//
//        while(rs.next()){
//
////            buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";  
//
//        }  
////     buffer=buffer+"</select>";  
//     stmt.close();
//     
//    Statement stmt1 = SM.con.createStatement();  
//
//    ResultSet rs1 = stmt1.executeQuery("Select * from tbl_category where organization_id="+org_id+"");  
//
//        while(rs1.next()){
//
//            buffer1=buffer1+"<option value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>";  
//
//        }  
//     buffer1=buffer1+"</select>";  
//     stmt1.close();
     Statement stmt2 = SM.con.createStatement();
     ResultSet rs2 = stmt2.executeQuery("Select a.brand_id From tbl_user_preferences a where a.user_id="+user_id);
     
/*---------------------------------- script to get the font names from the database ----------------------------*/
        while (rs2.next()){
            Integer brand_id = rs2.getInt(1);
            Statement stmt3 = SM.con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id="+brand_id+"");
            
            if (rs3.next()){
                    Integer font_id1 = rs3.getInt("font_id1");
                    Statement stmt4 = SM.con.createStatement();
                    ResultSet rs4 = stmt4.executeQuery("Select * From tbl_font_family where id="+font_id1+"");
                        if (rs4.next()){
                            String font_name1 = rs4.getString("font_name");
                            buffer1=buffer1+"<option value='"+font_id1+"'>"+font_name1+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_id2 = rs3.getInt("font_id2");
                    Statement stmt5 = SM.con.createStatement();
                    ResultSet rs5 = stmt5.executeQuery("Select * From tbl_font_family where id="+font_id2+"");
                        if (rs5.next()){
                            String font_name2 = rs5.getString("font_name");
                            buffer1=buffer1+"<option value='"+font_id2+"'>"+font_name2+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_id3 = rs3.getInt("font_id3");
                    
                    Statement stmt6 = SM.con.createStatement();
                    ResultSet rs6 = stmt6.executeQuery("Select * From tbl_font_family where id="+font_id3+"");
                        if (rs6.next()){
                            String font_name3 = rs6.getString("font_name");
                            buffer1=buffer1+"<option value='"+font_id3+"'>"+font_name3+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_id4 = rs3.getInt("font_id4");
                    
                    Statement stmt7 = SM.con.createStatement();
                    ResultSet rs7 = stmt7.executeQuery("Select * From tbl_font_family where id="+font_id4+"");
                        if (rs7.next()){
                            String font_name4 = rs7.getString("font_name");
                            buffer1=buffer1+"<option value='"+font_id4+"'>"+font_name4+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_id5 = rs3.getInt("font_id5");
                    
                    Statement stmt8 = SM.con.createStatement();
                    ResultSet rs8 = stmt8.executeQuery("Select * From tbl_font_family where id="+font_id5+"");
                        if (rs8.next()){
                            String font_name5 = rs8.getString("font_name");
                            buffer1=buffer1+"<option value='"+font_id5+"'>"+font_name5+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    buffer1=buffer1+"</select>";  

/*-------------------------------script to get the font sizes from the database ----------------------------*/
                    
                    Integer font_size_id1 = rs3.getInt("font_size1");
                    Statement stmt9 = SM.con.createStatement();
                    
                    ResultSet rs9 = stmt9.executeQuery("Select * From tbl_font_size where id="+font_size_id1+"");
                        if (rs9.next()){
                            String font_size1 = rs9.getString("font_size");
                            buffer2=buffer2+"<option value="+font_size_id1+">"+font_size1+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id2 = rs3.getInt("font_size2");
                    Statement stmt10 = SM.con.createStatement();
                    ResultSet rs10 = stmt10.executeQuery("Select * From tbl_font_size where id="+font_size_id2+"");
                        if (rs10.next()){
                            String font_size2 = rs10.getString("font_size");
                            buffer2=buffer2+"<option value="+font_size_id2+">"+font_size2+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id3 = rs3.getInt("font_size3");
                    Statement stmt11 = SM.con.createStatement();
                    ResultSet rs11 = stmt11.executeQuery("Select * From tbl_font_size where id="+font_size_id3+"");
                        if (rs11.next()){
                            String font_size3 = rs11.getString("font_size");
                            buffer2=buffer2+"<option value="+font_size_id3+">"+font_size3+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id4 = rs3.getInt("font_size4");
                    Statement stmt12 = SM.con.createStatement();
                    ResultSet rs12 = stmt12.executeQuery("Select * From tbl_font_size where id="+font_size_id4+"");
                        if (rs12.next()){
                            String font_size4 = rs12.getString("font_size");
                            buffer2=buffer2+"<option value="+font_size_id4+">"+font_size4+"</option>";
                            
                        }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id5 = rs3.getInt("font_size5");
                    Statement stmt13 = SM.con.createStatement();
                    ResultSet rs13 = stmt13.executeQuery("Select * From tbl_font_size where id="+font_size_id5+"");
                        if (rs13.next()){
                            String font_size5 = rs13.getString("font_size");
                            buffer2=buffer2+"<option value="+font_size_id5+">"+font_size5+"</option>";
                        }
                    rs4.close();
                    stmt4.close();
                    buffer2=buffer2+"</select>";  
                    
            }
        }
     
 response.getWriter().println(buffer1+","+buffer2); 

 }
 catch(Exception e){

     System.out.println(e.getCause());
     System.out.println(e.getMessage());

 }

 %>
