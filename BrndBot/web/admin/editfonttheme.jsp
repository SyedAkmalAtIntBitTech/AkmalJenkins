<%-- 
    Document   : editfonttheme
    Created on : Jul 2, 2015, 2:08:15 PM
    Author     : intbit
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
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
<!--        <script src="../js/colorthemefunctions.js" type="text/javascript"></script>-->

        <title>font theme</title>
        <style>
            .box{width:80px;}

        </style>
        <script>

            function colorthemeController($scope, $http) {
                var brand_id;
                var font_theme_id;
                var font_name_array = [];
                var font_size_array = [];
                var font_style_array = [];

                function validate() {

                    font_theme_id = document.getElementById("fontthemeid").value;
                    var x = document.getElementById("brand").selectedIndex;
                    brand_id = document.getElementsByTagName("option")[x].value;
                    if (brand_id == 0) {
                        alert("No brand selected, please select any one brand");
                        document.getElementById("brand").focus();
                        return false;
                    }

                    var font_name = document.getElementById("font_name");

                    var len = font_name.options.length;

                    var a = 0;
                    for (var i = 1; i < font_name.options.length; i++) {
                        if (font_name.options[i].selected === true) {
                            font_name_array[a] = parseInt(font_name.options[i].value);
                            a = a + 1;
                        }
                    }

                    if (a === 0) {
                        alert("No font name selected, please select any five fonts");
                        document.getElementById("font_name").focus();
                        return false;
                    } else if (a !== 5) {
                        alert("please select five fonts");
                        document.getElementById("font_name").focus();
                        return false;
                    }
                    var font_size = document.getElementById("font_size");

                    var len = font_size.options.length;

                    var b = 0;

                    for (var i = 1; i < font_size.options.length; i++) {
                        if (font_size.options[i].selected === true) {
                            font_size_array[b] = parseInt(font_size.options[i].value);
                            b = b + 1;
                        }
                    }

                    if (b === 0) {
                        alert("No font size selected, please select five sizes");
                        document.getElementById("font_size").focus();
                        return false;
                    } else if (b !== 5) {
                        alert("please select five sizes");
                        document.getElementById("font_size").focus();
                        return false;
                    }
                    var font_style = document.getElementById("font_style");

                    var len = font_style.options.length;

                    var c = 0;
                    for (var i = 1; i < font_style.options.length; i++) {
                        if (font_style.options[i].selected === true) {
                            font_style_array[c] = parseInt(font_style.options[i].value);
                            c = c + 1;
                        }
                    }

                    if (c === 0) {
                        alert("No font style selected, please select five styles");
                        document.getElementById("font_style").focus();
                        return false;
                    } else if (c !== 5) {
                        alert("please select five styles");
                        document.getElementById("font_style").focus();
                        return false;
                    }

                    return true;
                }

                $scope.updateFontTheme = function () {

                    if (validate()) {
                        var fonttheme = {"font_theme_id": font_theme_id, "brand_id": brand_id.toString(), "font_name_array": font_name_array,
                            "font_size_array": font_size_array, "font_style_array": font_style_array, "type": "update"};

                        $http({
                            method: 'POST',
                            url: getHost() + 'ServletFontTheme',
                            headers: {'Content-Type': 'application/json'},
                            data: fonttheme
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "true") {
                                window.open(getHost() + 'admin/fonttheme.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        })
                                .error(function (data, status) {
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                                    alert("request not succesful");
                                });
                    }
                };
            }


        </script>
    </head>
<jsp:include page="checksession.jsp" />
    <jsp:declaration>
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer number = 1;
    </jsp:declaration>

    <%
        String font_theme_id = request.getParameter("font_theme_id");
    %>

    <body ng-app>
        <%@include file="menus.jsp" %>
        <div class="jumbotron"  align="center" ng-controller="colorthemeController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 500px; width: 600px;">
                <form ng-model="colortheme">
                    <div>
                        <div class="col-md-3 col-md-offset-5">
                            <p text-center >Font Theme Family</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        <input type="hidden" name="fontthemeid" id="fontthemeid" value="<%= font_theme_id%>"/>
                        Select brand: <select name="brand" id="brand" style="width:180px; margin-top: 20px;">
                            <option value="0">--select--</option>
                            <%
                                Connection connection = null;
                                try {
                                connection = ConnectionManager.getInstance().getConnection();
                                query_string = "select * from tbl_brand_personality Order By id ASC";
                                prepared_statement = connection.prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("brand_name")%></option>
                            <%
                                }
                                }catch (Exception e){
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                }finally {
                                    result_set.close();
                                    prepared_statement.close();
                                    ConnectionManager.getInstance().closeConnection(connection);
                                }
                            %>
                        </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select fonts:   <select name="font_name" id="font_name" style="width:180px;" multiple="true" ng-model="fonttheme.font">
                            <option value="0">--select--</option>
                            <%
                                try{
                                    connection = ConnectionManager.getInstance().getConnection();
                                    
                                query_string = "select * from tbl_font_family Order By id ASC";
                                prepared_statement = connection.prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("font_name")%> </option>
                            <%
                                }
                                }catch (Exception e){
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                }finally {
                                    result_set.close();
                                    prepared_statement.close();
                                    ConnectionManager.getInstance().closeConnection(connection);
                                }
                            %>
                        </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select sizes: <select name="font_size" id="font_size" style="width:180px;" multiple="true" ng-model="fonttheme.size">
                            <option value="0">--select--</option>
                            <%
                                try{
                                    connection = ConnectionManager.getInstance().getConnection();
                                query_string = "select * from tbl_font_size Order By id ASC";
                                prepared_statement = connection.prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("font_size")%> </option>
                            <%
                                }
                                }catch (Exception e){
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                }finally {
                                    result_set.close();
                                    prepared_statement.close();
                                    ConnectionManager.getInstance().closeConnection(connection);
                                }
                            %>
                        </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select styles: <select name="font_style" id="font_style" style="width:180px;" multiple="true" ng-model="fonttheme.style">
                            <option value="0">--select--</option>
                            <%
                                try{
                                    connection = ConnectionManager.getInstance().getConnection();
                                    query_string = "select * from tbl_font_style Order By id ASC";
                                    prepared_statement = connection.prepareStatement(query_string);
                                    result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("font_style")%> </option>
                            <%
                                }
                                }catch (Exception e){
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                }finally {
                                    result_set.close();
                                    prepared_statement.close();
                                    ConnectionManager.getInstance().closeConnection(connection);
                                }
                            %>
                        </select><br>
                    </div><br>    

                    <div style="position: absolute; float:left; left:550px; top: 580px;">
                        <div>
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="updateFontTheme()">Update</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
        </div>                        
    </body>
</html>
