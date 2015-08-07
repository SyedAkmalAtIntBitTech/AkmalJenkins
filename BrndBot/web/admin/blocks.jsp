<%-- 
    Document   : displayblockss
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
        <script src="../js/blocks.js" type="text/javascript"></script>
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
        <title>Display blocks</title>
    </head>
<%@include file="checksession.jsp" %>
    <%!
        Integer num = 1;
    %>
    <body ng-app  class="container">
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="blocksController" >
            <div class="jumbotron" style="height: 220px; margin-top: 0px; padding-top: 20px;">
            <form class="form-horizontal" name="formblocks1" ng-controller="blocksController">

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-left">Add block</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <input type="text"  class="form-control simplebox" id="name" name="name" ng-model="blocks.name"/>
                        <br>
                    Select mindbody query: <select id="mindbodyquery" name="mindbodyquery" >
                        <option value="0">--select--</option>
                        <option value="promote work shop">promote work shop</option>
                        <option value="promote class">promote class</option>
                        <option value="promote event">promote event</option>
                        <option value="promote staff">promote staff</option>
                    </select><br>
                    Select brand personality: <select name="brand" id="brand" style="width:180px;">
                        <option value="0">--select--</option>
                        <%
                        try {
                            SqlMethods sqlmethods = new SqlMethods();
                            query_string = "select * from tbl_brand_personality Order By id ASC";
                            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();

                            while (result_set.next()) {
                        %>
                                <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("brand_name")%></option>
                        <%
                            }
                        }catch (Exception e) {
                            
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            sqlmethods.closeConnection();
                        }
                        %>
                    </select><br>
                    
                    </div>
                </div>
                <div class="group">
                </div>
                
                <br>
                <div  class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button type="submit" class="btn btn-info" ng-click="createNewblock()">Save</button><br><br><br>
                    </div>
                </div>

            </form>
            </div>
            <div class="jumbotron">
               
                <div>&nbsp;</div>
                <table border="1">
                    <tr>
                        <td>ID Number </td>
                        <td>block Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        try{
                        query_string = "select * from tbl_blocks Order By id ASC";
                        
                        prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        num = 1;
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td align="left">&nbsp;<%= num %></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getInt("id")%>" id="<%= result_set.getInt("id")%>" value="<%= result_set.getString("name")%>" /></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" ng-click="changeblock(<%=result_set.getInt("id")%>)">edit</button></td>
                        <td><button class="btn btn-info" id="blocks" name="blocks" value="delete" ng-click="deleteblock(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                        num = num +1;
                    }
                        result_set.close();
                        prepared_statement.close();
                    }catch (Exception e){
                        System.out.println(e.getCause());
                        System.out.println(e.getMessage());
                        out.println(sqlmethods.error);
                    }finally {
                        sqlmethods.closeConnection();
                    }
                    %>
                </table>
            </div>
            <br>

        </div>
</body>
</html>
