<%-- 
    Document   : signout
    Created on : Jun 18, 2015, 1:06:00 PM
    Author     : intbit
--%>

<%@page import="com.controller.sqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    sqlMethods SM = new sqlMethods();
    RequestDispatcher rd;
    %>
    
    <%
        try{
            SM.session = request.getSession(true);
            SM.session.invalidate();
            rd = request.getRequestDispatcher("/login.jsp");
            
            rd.forward(request, response);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
            
    %>