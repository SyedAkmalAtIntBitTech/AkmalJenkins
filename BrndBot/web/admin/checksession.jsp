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
        HttpServletRequest request;
        HttpServletResponse response;
        String checked = "false";
        Integer user_id = 0;
        String company = "";
        SqlMethods sqlmethods = new SqlMethods();
        
        Integer number = 1;
    %>

    <%
        try{
           SqlMethods sqlmethods = new SqlMethods();
           sqlmethods.admin_session = request.getSession(true);
           checked = (String)sqlmethods.admin_session.getAttribute("AdminChecked");
           if (checked == null || checked.equals("false")){
               response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
           } 
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
     %>

