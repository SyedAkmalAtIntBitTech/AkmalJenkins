<%-- 
    Document   : editpersonality
    Created on : Jun 30, 2015, 4:54:42 PM
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
        <script src="../js/configurations.js"></script>
<!--        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link href="../css/main1.css" rel="stylesheet" type="text/css"/>-->

        <title>JSP Page</title>
    </head>
    <%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        SqlMethods sqlmethods = new SqlMethods();
        
        Integer number = 1;
    %>
    <% 
        String brand_id = request.getParameter("brand_id");
        String brand_name = request.getParameter("brand_name");
        String look_id = request.getParameter("look_id");
    %>

    <body>
    <centre>
        <div style="float: left; border: 1px solid;margin-left: 400px; margin-top: 100px; height: 300px;" >
            
                <form name="formpersonality" action="<%= application.getContextPath() %>/ServletEditPersonality" enctype="multipart/form-data" method="post">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Edit Brand Personality</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 10px;">
                        <input type="hidden" id="brandid" name="brandid" value="<%= brand_id %>"/>
                        Brand:<input type="text" id="brandname" name="brandname" value="<%= brand_name %>"/><br>
                        Select Look: <select name="look" id="look" style="width:180px;">
                                                <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_look Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                            Integer id = result_set.getInt("id");
                            if (id ==  Integer.parseInt(look_id)){
                    %>
                                                <option value="<%= result_set.getInt("id") %>" selected><%= result_set.getString("look_name") %></option>
                     <%
                            }else{
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("look_name") %></option>
                    <%
                            }
                        }
                    %>
                                         </select><br>
                    </div><br>    
                <div style="float:left; left:0px; padding-left: 10px; padding-top: 20px;">
                    <div>
                        Attach Image:<input type="file" name="filesToUpload[]"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
                    </div><br>
                </div>

                <div style="position: absolute; float:left; left:550px; top: 300px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            
        </div>
        </centre>
    </body>
</html>
