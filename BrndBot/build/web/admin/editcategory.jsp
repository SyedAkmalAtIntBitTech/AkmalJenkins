<%-- 
    Document   : editcategory
    Created on : Jul 3, 2015, 10:51:45 AM
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
        <script src="../js/categoriesfunctions.js" type="text/javascript"></script>
        <title>categories</title>
        
    </head>
        <%@include file="checksession.jsp" %>

    <%
            String category_id = request.getParameter("category_id");
            String category_name = request.getParameter("category_name");
            String organization_id = request.getParameter("organization_id");
    %>
    <body ng-app>
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="categoryController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form name="formCategories" action="<%= application.getContextPath() %>/ServletEditCategories" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Categories</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                                      <input type="hidden" name="category_id" id="category_id" value="<%= category_id %>"/>
                        Category:<input type="text" id="category_name" name="category_name" value="<%= category_name %>"/><br>
                        Select organization: <select name="organization" id="organization" style="width:180px;">
                                    <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_organization Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                            
                            Integer org_id = result_set.getInt("id");
                            
                                    if ( org_id == Integer.parseInt(organization_id)){
                    %>
                                        <option value="<%= result_set.getInt("id") %>" selected><%= result_set.getString("organization_name") %></option>
                    <%
                                    }else {
                    %>
                                        <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("organization_name") %></option>
                    <%
                                    }
                        }
                        result_set.close();
                        prepared_statement.close();
                        sqlmethods.con.close();
                    %>
                        </select><br>
                    </div><br>    
                <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                    <div>
                        Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="categories.fileName" />
                    </div><br>
                </div>

                <div style="position: absolute; float:left; left:550px; top: 300px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Update</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
    </body>
</html>
