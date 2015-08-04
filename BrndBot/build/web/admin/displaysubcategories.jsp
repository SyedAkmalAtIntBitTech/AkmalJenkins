<%-- 
    Document   : displaysubcategories
    Created on : Jul 23, 2015, 12:34:07 PM
    Author     : intbit
--%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%@include file="checksession.jsp" %>

<%!
    SqlMethods SM =new SqlMethods();
%>

<%
String category_id=request.getParameter("category_id");

String buffer="<select id='subcategories'><option value='0'>Select</option>";

 try{

        SM.setDatabaseConnection();
        Statement stmt = SM.con.createStatement();

        ResultSet rs = stmt.executeQuery("Select * from tbl_sub_category where category_id='"+category_id+"'");

        while(rs.next()){
            buffer=buffer+"<option value='"+rs.getString("id")+"'>"+rs.getString("sub_category_name")+"</option>";
        }
            
         buffer=buffer+"</select>";  
         stmt.close();
         SM.con.close();
        System.out.println(buffer);
        response.getWriter().println(buffer);

 }
 catch(Exception e){

     System.out.println(e.getCause());
     System.out.println(e.getMessage());

 }

 %>