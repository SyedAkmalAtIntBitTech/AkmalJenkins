<%-- 
    Document   : editlook
    Created on : Jun 29, 2015, 3:19:39 PM
    Author     : intbit
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
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
        <script src="../js/lookfunctions.js" type="text/javascript"></script>
    </head>
<jsp:include page="checksession.jsp" />
    <jsp:declaration>
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer organization_id = 0;
        Integer number = 1;

    </jsp:declaration>

    <%
        String look_id = request.getParameter("look_id");
        String look_name = request.getParameter("look_name");
        String organization_selected_id = request.getParameter("organization_id");
        String image_file_name = request.getParameter("image_file_name");
    %>
    
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
    
    <body class="container">
       <%@include file="menus.jsp" %>
        <div>
            <form name="formLooks" action="<%= application.getContextPath() %>/ServletChangeLooks" enctype="multipart/form-data" method="post"  onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-center">Add New Looks</p>
                    </div>
                </div>

                <div>
                    <div class="col-md-3 col-md-offset-5">
                            <input type="hidden" name="lookid" id="lookid" value="<%= look_id %>"/>
                            <%= exist1 %>
                            <input type="text"  class="form-control simplebox" id="lookname" name="lookname" value="<%= look_name %>"/>
                       
    Select organization: <select name="organization" id="organization" style="width:180px;">
                                    <option value="0">--select--</option>
                                    <%
                                    Connection connection = null;
                                    try{
                                        connection = ConnectionManager.getInstance().getConnection();
                                        query_string = "select * from tbl_organization Order By id ASC";
                                        prepared_statement = connection.prepareStatement(query_string);
                                        result_set = prepared_statement.executeQuery();

                                        while (result_set.next()) {

                                                organization_id = result_set.getInt("id");
                                                if (organization_id == Integer.parseInt(organization_selected_id)){
                                           %>
                                                    <option value="<%= result_set.getInt("id")%>" selected><%= result_set.getString("organization_name")%></option>
                                           <%
                                                }else {
                                            %>
                                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("organization_name")%></option>
                                            <%
                                            }
                                        }
                                    }catch (Exception e){
                                        System.out.println(e.getCause());
                                        System.out.println(e.getMessage());
                                    }finally {
//                                        result_set.close();
                                        prepared_statement.close();
                                        ConnectionManager.getInstance().closeConnection(connection);
                                    }

                                    %>
                                </select><br><br>
                                  <p>Uploaded image : </p><p><%= image_file_name %></p>
                            Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
                        <!--  <label>Organization Name:</label>-->
                    </div><br>
                </div>

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Update</button>
                    </div>
                </div>

            </form>

        </div>    
    </body>
</html>
