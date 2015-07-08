<%-- 
    Document   : displayorganizations
    Created on : Jun 27, 2015, 11:31:52 AM
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
    
    <body class="container" ng-app>
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="fontController">
            <div style="margin-bottom: 20px;">
                <form name="formfontfamily" action="<%= application.getContextPath() %>/ServletUploadFonts" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-center">Add New Fonts</p>
                    </div>
                </div>
                    
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        Font Name:  <input type="text"  class="form-control simplebox" id="fontname" name="fontname"/>
                        Attach Font: <input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="fontfileName" />
                        <!--  <label>Organization Name:</label>-->
                    </div><br>
                </div>

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
                <br><br>
<!--            <form class="form-horizontal" name="formFonts" ng-controller="fontsController">

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-left">Fonts Family</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        
                        <input type="text"  class="form-control simplebox" id="fontname" name="fontname" ng-model="fonts.fontname"/>
                                                <label>Organization Name:</label>
                    </div>
                </div>
                <br>
                <div  class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button type="submit" class="btn btn-info" ng-click="createNewFonts()">Save</button><br><br><br>
                    </div>
                </div>

            </form>-->

            <div style="margin-top: 20px;">
               
                <div>&nbsp;</div>
                <table border="1">
                    <tr>
                        <td>ID Number </td>
                        <td>Organization Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        query_string = "select * from tbl_font_family Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= result_set.getInt("id")%></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getInt("id")%>" id="<%= result_set.getInt("id")%>" value="<%= result_set.getString("font_name")%>" /></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" ng-click="editFont(<%=result_set.getInt("id")%>,'<%= result_set.getString("font_name")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteFont(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                        }
                        result_set.close();
                        prepared_statement.close();
                        sqlmethods.con.close();
                    %>
                </table>
            </div>
            <br>

        </div>
</body>
</html>
