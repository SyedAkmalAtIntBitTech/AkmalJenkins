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
        <script src="../js/fontsSizes.js" type="text/javascript"></script>
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
        <title>fonts size</title>
    </head>
    <%@include file="checksession.jsp" %>

    <body ng-app  class="container">
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="fontsSizeController" >
            <form class="form-horizontal" name="formFonts" ng-controller="fontsSizeController">

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-left">Font Sizes</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <input type="text"  class="form-control simplebox" id="fontsize" name="fontsize" ng-model="fonts.fontsize"/>
                    </div>
                </div>
                <br>
                <div  class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button type="submit" class="btn btn-info" ng-click="createNewFontSize()">Save</button><br><br><br>
                    </div>
                </div>

            </form>

            <div>

                <div>&nbsp;</div>
                <table border="1">
                    <tr>
                        <td>ID Number </td>
                        <td>Organization Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        try {

                            query_string = "select * from tbl_font_size Order By id ASC";
                            sqlmethods.setDatabaseConnection();
                            prepared_statement = sqlmethods.con.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();
                            number = 1;
                            while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getInt("id")%>" id="<%= result_set.getInt("id")%>" value="<%= result_set.getString("font_size")%>" /></td>
                        <td><button class="btn btn-info" id="change" name="edit" value="edit" ng-click="editFontSize(<%=result_set.getInt("id")%>)">edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteFontSize(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                                 number = number + 1;
                            }
                            result_set.close();
                            prepared_statement.close();
                            sqlmethods.con.close();
                        } catch (Exception e) {
                            System.out.println(e.getCause());
                            out.println(e.getMessage());
                        }
                    %>
                </table>
            </div>
            <br>

        </div>
    </body>
</html>
