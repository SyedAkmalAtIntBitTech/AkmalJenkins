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
        <script src="../js/colorsfunctions.js" type="text/javascript"></script>
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
        <title>colors</title>
    </head>
<%@include file="checksession.jsp" %>

    <%!
        Integer hash = 1;
        String hashv = "hash";
    %>
    <body ng-app  class="container">
        <%@include file="menus.jsp" %>
        <div class="jumbotron">
        <div align="center" ng-controller="colorsController" >
            <form class="form-horizontal" name="formFonts" ng-controller="colorsController">

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-left">Colors</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        Color Hex value:
                        <input type="text"  class="form-control simplebox" id="color_hex" name="color_hex" ng-model="colors.color_hex"/>
                        <!--                        <label>Organization Name:</label>-->
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        Color Name:
                        <input type="text"  class="form-control simplebox" id="color_name" name="color_name" ng-model="colors.color_name"/><br>
                        <!--                        <label>Organization Name:</label>-->
                    </div>
                </div>
                <br>
                <div  class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button type="submit" class="btn btn-info" ng-click="createColors()">Save</button><br><br><br>
                    </div>
                </div>

            </form>
            
            <div>
               
                <div>&nbsp;</div>
                <table border="1">
                    <tr>
                        <td>ID Number </td>
                        <td>Color Hexa decimal</td>
                        <td>Color Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        try{
                        query_string = "select * from tbl_colors Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;
                        hash = 1;
                        hashv = hashv + hash;
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><input class="simplebox" type="text" name="<%= hashv %>" id="<%= hashv %>" value="<%=  result_set.getString("color_hex") %>" /></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getString("color_name")%>" id="<%= result_set.getString("color_name")%>" value="<%= result_set.getString("color_name")%>" /></td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editColor(<%=result_set.getInt("id")%>,'<%= hashv %>','<%= result_set.getString("color_name")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteColor(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
                            hash = hash + 1;
                            hashv = "hash" + hash;
                        }
                        }catch (Exception e){
                            out.println(sqlmethods.error);
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
