<%-- 
    Document   : displaysubcategories
    Created on : Jul 23, 2015, 12:34:07 PM
    Author     : intbit
--%>
<%@page import="com.intbit.ConnectionManager"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%@include file="checksession.jsp" %>

<%
String brand_id=request.getParameter("brand_id");

String buffer="<select id='brand'><option value='0'>Select</option>";
Connection connection = null;
Statement stmt = null;
ResultSet rs = null;
 try{
    connection = ConnectionManager.getInstance().getConnection();
    stmt = connection.createStatement();

    rs = stmt.executeQuery("Select * from tbl_blocks where brand_id='"+brand_id+"'");

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
     if ( rs != null){
         rs.close();
     }
     if ( stmt != null){
         stmt.close();
     }
     ConnectionManager.getInstance().closeConnection(connection);
 }

 %>