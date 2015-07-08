<%-- 
    Document   : editfontsfamily
    Created on : Jul 4, 2015, 11:06:53 AM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/configurations.js"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link href="../css/main1.css" rel="stylesheet" type="text/css"/>
        <script src="../js/fontsfamily.js" type="text/javascript"></script>
        <title>fonts family</title>
    </head>
    <%@include file="checksession.jsp" %>
    
    <%
        String font_id = request.getParameter("font_id");
        String font_name = request.getParameter("font_name");
     %>
    <body class="container" ng-app>
       <%@include file="menus.jsp" %>
        <div align="center" ng-controller="fontController">
            <div style="margin-bottom: 20px;">
                <form name="formfontfamily" action="<%= application.getContextPath() %>/ServletUpdateFonts" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-center">Add New Fonts</p>
                    </div>
                </div>
                    
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <input type="hidden" id="fontid" name="fontid" value="<%= font_id %>"/>  
                        Font Name:  <input type="text"  class="form-control simplebox" id="fontname" name="fontname" value="<%= font_name %>"/>
                        Attach Font: <input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="fontfileName" />
                        <!--  <label>Organization Name:</label>-->
                    </div><br>
                </div>

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Update</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
