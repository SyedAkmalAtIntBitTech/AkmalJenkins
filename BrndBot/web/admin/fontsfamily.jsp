<%-- 
    Document   : displayorganizations
    Created on : Jun 27, 2015, 11:31:52 AM
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
        <script src="../js/fontsfamily.js" type="text/javascript"></script>
        <title>fonts family</title>
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
        try{
            if (exist1 != ""){
                exist1 = "";
            }
            if ((request.getParameter("exist") != null) && (request.getParameter("exist") != "")){
                    exist = request.getParameter("exist");
                    if (exist.equals("exist")){
                        exist1 = "Record already exist";
                    }else if (exist == "" ) {
                        exist1 = "";
                    }
            }else if ((request.getParameter("exist") == null) && (request.getParameter("exist") == "")) {
                    exist1 = "";
            }
        }catch (Exception e){
            out.println(e.getCause());
        }        
    %>
    
    
    
    <body class="container" ng-app>
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="fontController">
            <div class="jumbotron" style="height: 280px; margin-top: 0px; padding-top: 20px; text-align: center;">
                <form name="formfontfamily" action="<%= application.getContextPath() %>/ServletUploadFonts" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-center">Add New Font:</p>
                    </div>
                </div>
                    
                    <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <%= exist1 %>
                        <input type="text"  class="form-control simplebox" id="fontname" name="fontname"/><br>
                        Attach Font: <input type="file" style="border: 1px solid;" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="fontfileName" /><br>
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

        <div class="jumbotron" style="margin-top: 20px;">
               
                <div>&nbsp;</div>
                <table border="1">
                    <tr>
                        <td>ID Number </td>
                        <td>Font Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                    Connection connection = null;
                    try {
                        connection = ConnectionManager.getInstance().getConnection();
                        query_string = "select * from tbl_font_family Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        num =1;
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= num %></td>
                        <td><%= result_set.getString("font_name")%></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" ng-click="editFont(<%=result_set.getInt("id")%>,'<%= result_set.getString("font_name")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteFont(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                        num = num +1;
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
                </table>
            </div>
            <br>

        </div>
</body>
</html>
