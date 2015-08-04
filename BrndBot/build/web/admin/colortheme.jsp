<%-- 
    Document   : colortheme
    Created on : Jul 1, 2015, 9:51:51 AM
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
        <script src="../js/colorthemefunctions.js" type="text/javascript"></script>
        
        <title>color theme</title>
        <style>
            .box{width:80px;}
            
        </style>
    </head>
    <%@include file="checksession.jsp" %>
    
    
    <body ng-app class="container">
        <%@include file="menus.jsp" %>
        <div class="jumbotron">
        <div align="center" ng-controller="colorthemeController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form ng-model="colortheme">
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p>Color Theme</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select brand: <select name="brand" id="brand" style="width:180px; margin-top: 20px;">
                                                <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_brand_personality Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("brand_name") %></option>
                    <%
                        }
                        result_set.close();
                        prepared_statement.close();
                    %>
                                            </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select colors: <select name="color" id="color" style="width:180px;" multiple="true" ng-model="colortheme.color">
                                                <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_colors Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("color_name") %> </option>
                    <%
                        }
                        result_set.close();
                        prepared_statement.close();
                    %>
                                            </select><br>
                    </div><br>    
                    
                <div style="position: absolute; float:left; left:550px; top: 400px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="createColorTheme()">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">
               
                <div>&nbsp;Display Brand Personality<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Brand ID</td>
                        <td>color1</td>
                        <td>color2</td>
                        <td>color3</td>
                        <td>color4</td>
                        <td>color5</td>
                        <td>color6</td>
                        <td>Predefined</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        query_string = "select * from tbl_brand_color_theme Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;
                        
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><%= result_set.getString("brand_id")%></td>
                        <td><%= result_set.getString("color1")%></td>
                        <td><%= result_set.getString("color2")%></td>
                        <td><%= result_set.getString("color3")%></td>
                        <td><%= result_set.getString("color4")%></td>
                        <td><%= result_set.getString("color5")%></td>
                        <td><%= result_set.getString("color6")%></td>
                        <td><%= result_set.getBoolean("predefined")%></td>
                        
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="edit(<%=result_set.getInt("id")%>,'<%=result_set.getString("brand_id")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="delete(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
                        }
                        result_set.close();
                        prepared_statement.close();
                        sqlmethods.con.close();
                    %>
                </table>
            </div>
            <br>

        </div>
        </div>
    </body>
</html>
