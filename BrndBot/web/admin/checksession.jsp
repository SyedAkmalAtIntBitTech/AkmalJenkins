<%-- 
    Document   : checksession
    Created on : Jul 8, 2015, 2:44:42 PM
    Author     : intbit
--%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        SqlMethods sqlmethods = new SqlMethods();
        HttpServletRequest request;
        HttpServletResponse response;
        String checked = "false";
        Integer user_id = 0;
        String company = "";
        
        Integer number = 1;
    %>

    <%
        try{
           sqlmethods.admin_session = request.getSession(true);
           checked = (String)sqlmethods.admin_session.getAttribute("AdminChecked");
           if (checked == null || checked.equals("false")){
               response.sendRedirect(request.getContextPath()+"/admin/adminlogin.jsp");
           } 
        }catch (Exception e){
            out.println(sqlmethods.error);
        } 
     %>

