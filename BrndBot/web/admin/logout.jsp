<%-- 
    Document   : logout
    Created on : Jul 8, 2015, 2:24:19 PM
    Author     : intbit
--%>

<%@page import="admin.controller.ServletLogin"%>
<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    SqlMethods sql_methods = new SqlMethods();
    RequestDispatcher request_dispatcher;
    %>
    
    <%
        try{
            sql_methods.admin_session = request.getSession(true);
            sql_methods.admin_session.invalidate();
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            sql_methods.closeConnection();
        }
            
    %>
