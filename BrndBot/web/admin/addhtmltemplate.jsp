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
        <script src="../js/htmltemplate.js" type="text/javascript"></script>
        <title>Looks</title>
        
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
    <body ng-app class="container" >
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="htmlController" >
            <div class="jumbotron" style="height: 480px; margin-top: 0px; padding-top: 20px; text-align: center;">
                <form class="form-horizontal" name="formhtmltemplate">

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <%= exist1 %>
    Select model name:<select name="modelname" id="modelname" style="width:180px;">
                            <option value="0">--select--</option>
                            <%
                            Connection connection = null;
                            try{
                                connection = ConnectionManager.getInstance().getConnection();
                                query_string = "select * from tbl_model Order By id ASC";
                                prepared_statement = connection.prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                   <option value="<%= result_set.getString("model_name") %>"><%= result_set.getString("model_name") %></option>
                            <%
                                }
                            }catch (Exception e){
                                System.out.println(e.getCause());
                                System.out.println(e.getMessage());
                            }finally {
                                result_set.close();
                                prepared_statement.close();
                            }

                            %>
                      </select><br>
                        
                        Attach Image:<input type="file" style="border: 1px solid;" name="filesToUpload" id="filesToUpload" class="upload"/><br>
                        <!--  <label>Organization Name:</label>-->
                        <button type="button" id="uploadhtml" onclick="upload()" name="uploadhtml">Upload HTML</button><br><br>
                        HTML Content: <textArea name="html_content" id="html_content" maxlength="3000" cols="50" rows="10"></textArea> 
                    </div><br>
                </div>

                    <div style="float:left; left:20px; padding-left: 430px;">
                    </div><br><br>    

                        
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button id="Servicecontinue" type="button" ng-click="createNewtemplate()" class="btn btn-info">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
            <div class="jumbotron" style="margin-top: 0px;" align="centre">
               
                <div class="group"><p class="text-center">Display Looks</p></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Look Name</td>
                        <td>Look Image</td>
                        <td>Organization ID</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                    try{
                        query_string = "select * from tbl_look Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        num = 1;
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td align="left">&nbsp;<%= num %></td>
                        <td><%= result_set.getString("look_name")%></td>
                        <td><a href="/BrndBot/DownloadImage?image_type=LOOKS&image_name=<%= result_set.getString("file_name")%>"><%= result_set.getString("file_name")%></a></td>
                        <td><%= result_set.getString("organization_id")%></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" ng-click="changeLooks(<%=result_set.getInt("id")%>,'<%=result_set.getString("look_name")%>','<%=result_set.getString("organization_id")%>' ,'<%=result_set.getString("file_name")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="organization" name="organization" value="delete" ng-click="deleteLooks(<%=result_set.getInt("id")%>)">delete</button></td>
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

                <script>
        
            function upload() {
                alert("text");
                var fileUpload = document.getElementById("filesToUpload");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.html|.htm)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                            var rows = e.target.result.valueOf();

                            if ($('#html_content').val() == "") {
                                $('#html_content').val(rows);
                            }
                        }
                        reader.readAsText(fileUpload.files[0]);

                    } else {
                        alert("This browser does not support HTML5.");
                    }
                } else {
                    alert("Please upload a valid CSV file.");
                }

            }
    
    
        var fl = document.getElementById('filesToUpload');

        fl.onchange = function (e) {
            var ext = this.value.match(/\.(.+)$/)[1];
            switch (ext)
            {
                case 'html':
                break;
                default:
                    alert('This type of image is not allowed');
                    this.value = '';
            }
        };

        function fileSelect(evt) {
            if (window.File && window.FileReader && window.FileList && window.Blob) {
                var files = evt.target.files;

                var result = '';
                var file;
                for (var i = 0; file = files[i]; i++) {
                    // if the file is not an image, continue
                    if (!file.type.match('image.*')) {
                        continue;

                    }
                    //            alert("only .png file");

                    reader = new FileReader();
                    reader.onload = (function (tFile) {
                        return function (evt) {
                            var div = document.createElement('div');
                            div.innerHTML = '<img style="width: 90px;" src="' + evt.target.result + '" />';
                            document.getElementById('logoimage').src = evt.target.result;
                            document.getElementById("Servicecontinue").disabled = false;
                        };
                    }(file));
                    reader.readAsDataURL(file);
                }
            } else {
                alert('The File APIs are not fully supported in this browser.');
            }
        }

        document.getElementById('filesToUpload').addEventListener('change', fileSelect, false);
    </script>
                
</body>
</html>
