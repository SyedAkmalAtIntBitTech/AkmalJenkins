<%-- 
    Document   : duplicatelayout
    Created on : 8 Sep, 2015, 3:58:54 PM
    Author     : ilyas
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

        <script src="../js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <script src="../js/jquery.reveal.js" type="text/javascript"></script>
        <link href="../css/reveal.css" rel="stylesheet" type="text/css"/>
        <title>Duplicate Layout</title>
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
        try {
            if (exist1 != "") {
                exist1 = "";
            }
            if ((request.getParameter("exist") != null) && (request.getParameter("exist") != "")) {
                exist = request.getParameter("exist");
                if (exist.equals("exist")) {
                    exist1 = "Record already exist";
                } else if (exist == "") {
                    exist1 = "";
                }
            } else if ((request.getParameter("exist") == null) && (request.getParameter("exist") == "")) {
                exist1 = "";
            }
        } catch (Exception e) {
            out.println(e.getCause());
        }
    %>
    <body  class="container">
        <%@include file="menus.jsp" %>
        <div align="center">
            <div class="jumbotron" style="height: 480px; margin-top: 0px; padding-top: 20px; text-align: center;">
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        Select model to duplicate:<select name="modelname" id="modelname" style="width:180px;">
                            <option value="0">--select--</option>
                            <%
                                Connection connection = null;
                                try {
                                    connection = ConnectionManager.getInstance().getConnection();
                                    query_string = "select * from tbl_model Order By id ASC";
                                    prepared_statement = connection.prepareStatement(query_string);
                                    result_set = prepared_statement.executeQuery();

                                    while (result_set.next()) {
                            %>
                            <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("model_name")%></option>
                            <%
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                } finally {
                                    result_set.close();
                                    prepared_statement.close();
                                }

                            %>
                        </select><br><br>
                        Change Brand Personality:<select name="brandname" id="brandname" style="width:180px;">
                            <option value="0">--select--</option>
                            <%                                    connection = null;
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
                                } catch (Exception e) {
                                    System.out.println(e.getCause());
                                    System.out.println(e.getMessage());
                                } finally {
                                    result_set.close();
                                    prepared_statement.close();
                                    SqlMethods.closeConnection(connection);
                                }

                            %>
                        </select><br><br>
                        <input type="button" class="btn btn-info" id="submit" style="width: 60px;" value="Submit">
                        <div id="currentDetails">
                            
                        <h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(function ()
            {
                $("#submit").click(function () {
                    $.ajax({
                        type: 'GET',
                        url: "/BrndBot/ServletDuplicateLayout",
                        data: {modelid: $("#modelname").val(), brandid: $("#brandname").val()},
                        success: function (data) {
                            alert(data);
                            location.reload();

                        },
                        error: function (xhr, ajaxOptions, thrownError) {

                            alert(thrownError);
                        }

                    });
                });
            });
        </script>
    </body>
</html>
