<%-- 
    Document   : editlook
    Created on : Jun 29, 2015, 3:19:39 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Look</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/configurations.js"></script>
        <script src="../js/adminfunctions.js" type="text/javascript"></script>
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
        
    </head>
    <%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        SqlMethods sqlmethods = new SqlMethods();
        
        Integer num = 1;
    %>
    <% 
        String look_id = request.getParameter("look_id");
        String look_name = request.getParameter("look_name");
    %>
    <body>
                <div>
                        <form name="formLooks" action="<%= application.getContextPath() %>/ServletChangeLooks" enctype="multipart/form-data" method="post">

                        <div>
                            <div class="col-md-3 col-md-offset-5">
                                <p class="text-center">Add New Looks</p>
                            </div>
                        </div>

                        <div>
                            <div class="col-md-3 col-md-offset-5">
                                <input type="hidden" name="lookid" id="lookid" value="<%= look_id %>"/>
                                Look Name: <input type="text"  class="form-control simplebox" id="lookname" name="lookname" value="<%= look_name %>"/>
                                Attach Image:<input type="file" name="filesToUpload[]"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
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
    </body>
</html>
