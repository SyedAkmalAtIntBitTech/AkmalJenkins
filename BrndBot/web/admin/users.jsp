<%-- 
    Document   : state
    Created on : Jan 25, 2015, 12:47:28 PM
    Author     : Syed
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%@include file="checksession.jsp" %>

<%
String org_id=request.getParameter("org_id");  

String buffer="<select id='users'><option value='0'>Select</option>";
String buffer1="<select id='categories'><option value='0'>Select</option>";
Connection connection = null;
Statement stmt = null;
ResultSet rs = null;

 try{
     connection = ConnectionManager.getInstance().getConnection();
    stmt = connection.createStatement();

    rs = stmt.executeQuery("Select * from tbl_user_login_details where organizationid="+org_id+"");

        while(rs.next()){

            buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";

        }
            
         buffer=buffer+"</select>";  
         stmt.close();

        Statement stmt1 = connection.createStatement();

        ResultSet rs1 = stmt1.executeQuery("Select * from tbl_category where organization_id="+org_id+"");

        while(rs1.next()){

            buffer1=buffer1+"<option value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>";

        }
        
        buffer1=buffer1+"</select>";
        stmt1.close();
//        Statement stmt2 = SM.getConnection().createStatement();
        System.out.println(buffer1);
        response.getWriter().println(buffer+","+buffer1);
//        SM.closeConnection();
 }catch(Exception ex){
     System.out.println(ex.getCause());
     out.println(ex.getSuppressed());
     ex.printStackTrace();
 }finally{
     if ( rs != null){
         rs.close();
     }
     if ( stmt != null){
         stmt.close();
     }
     ConnectionManager.getInstance().closeConnection(connection);
  }

 %>