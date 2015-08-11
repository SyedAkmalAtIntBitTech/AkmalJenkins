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
String category_id=request.getParameter("category_id");

String buffer="<select id='subcategories'><option value='0'>Select</option>";
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;
 try{
        conn = ConnectionManager.getInstance().getConnection();
        
        stmt = conn.createStatement();

        rs = stmt.executeQuery("Select * from tbl_sub_category where category_id='"+category_id+"'");

        while(rs.next()){
            buffer=buffer+"<option value='"+rs.getString("id")+"'>"+rs.getString("sub_category_name")+"</option>";
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
     if ( rs != null ){
         rs.close();
     }
     if ( stmt != null ){
         stmt.close();
     }
     ConnectionManager.getInstance().closeConnection(conn);
 }

 %>