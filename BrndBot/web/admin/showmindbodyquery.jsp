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
String block_id=request.getParameter("block_id");

//String buffer="<select id='brand'><option value='0'>Select</option>";
String buffer= null;
Statement stmt = null;
ResultSet rs = null;
Connection connection = null;
 try{
     connection = ConnectionManager.getInstance().getConnection();
     
    stmt = connection.createStatement();

    rs = stmt.executeQuery("Select * from tbl_blocks where id='"+block_id+"'");

        if(rs.next()){
            buffer = rs.getString("mindbody_query");
        }
            
         stmt.close();
         rs.close();
        System.out.println(buffer);
        response.getWriter().println(buffer.trim());
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