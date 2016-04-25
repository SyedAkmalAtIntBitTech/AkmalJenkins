<%-- 
    Document   : checksession
    Created on : Sep 10, 2015, 2:32:53 PM
    Author     : development
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    SqlMethods sqlmethods = new SqlMethods();
    String checked = "false";
    Integer user_id = 0;
    String company = "";
%>

<%
    try {
        sqlmethods.session = request.getSession(true);
        checked = (String) sqlmethods.session.getAttribute("Checked");
        if (checked == null || checked == "false") {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            user_id = (Integer) sqlmethods.session.getAttribute("UID");
            company = (String) sqlmethods.session.getAttribute("company");
        }
    } catch (Exception e) {
        out.println(sqlmethods.error);
    }finally {
        sqlmethods.closeConnection();
    }
%>