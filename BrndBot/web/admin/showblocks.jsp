<%-- 
    Document   : displaysubcategories
    Created on : Jul 23, 2015, 12:34:07 PM
    Author     : intbit
--%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%@include file="checksession.jsp" %>
<%!
    SqlMethods sql_methods =new SqlMethods();
%>


<%
String brand_id=request.getParameter("brand_id");

String buffer="<select id='brand'><option value='0'>Select</option>";

 try{

        Statement stmt = sql_methods.getConnection().createStatement();

        ResultSet rs = stmt.executeQuery("Select * from tbl_blocks where brand_id='"+brand_id+"'");

        while(rs.next()){
            buffer=buffer+"<option value='"+rs.getString("id")+"'>"+rs.getString("name")+"</option>";
        }
            
         buffer=buffer+"</select>";  
         stmt.close();
         rs.close();
        System.out.println(buffer);
        response.getWriter().println(buffer);

 }
 catch(Exception e){
     System.out.println(e.getCause());
     System.out.println(e.getMessage());

 } finally {
     
//     sqlmethods.closeConnection();
 }

 %>