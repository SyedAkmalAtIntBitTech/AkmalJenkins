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
        <title>categories</title>
        <script src="../js/categoriesfunctions.js" type="text/javascript"></script>
    </head>
    <%@include file="checksession.jsp" %>
    <%!
        Integer num = 1;
        String exist = "";
        String exist1 = "";
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


    <body ng-app class="container">
    <%@include file="menus.jsp" %>
        <div class="jumbotron" align="center" ng-controller="categoryController" >
            <div  style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form name="formCategories" action="<%= application.getContextPath()%>/ServletAddCategories" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                    <div>
                        <div class="col-md-3 col-md-offset-5">
                            <p text-center >Categories:</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                        <%= exist1 %>
                         <input type="text" id="category_name" name="category_name" value=""/><br>
                        Select organization: <select name="organization" id="organization" style="width:180px;">
                            <option value="0">--select--</option>
                            <%
                                query_string = "select * from tbl_organization Order By id ASC";
                                prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("organization_name")%></option>
                            <%
                                }
                                result_set.close();
                                prepared_statement.close();
                                sqlmethods.closeConnection();
                            %>
                        </select><br>
                    </div><br>    
                    <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                        <div>
                            Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="categories.fileName" />
                        </div><br>
                    </div>

                    <div style="position: absolute; float:left; left:550px; top: 400px;">
                        <div>
                            <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>
                </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">

                <div>&nbsp;Display Categories<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Organization ID</td>
                        <td>Category Name</td>
                        <td>Image Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        query_string = "select * from tbl_category Order By id ASC";
                        prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;

                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number%></td>
                        <td><%= result_set.getString("organization_id")%></td>
                        <td><%= result_set.getString("category_name")%></td>
                        <td><a href="../images/Organizations/Categories/<%= result_set.getString("organization_id")%>/<%= result_set.getString("image_name")%>"><%= result_set.getString("image_name")%></a></td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editCategory(<%=result_set.getInt("id")%>, '<%=result_set.getString("category_name")%>', '<%=result_set.getString("organization_id")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="deleteCategory(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
                        }
                        result_set.close();
                        prepared_statement.close();
                        sqlmethods.getConnection().close();
                    %>
                </table>
            </div>
            <br>

        </div>
        <script>
                    var fl = document.getElementById('filesToUpload');
                    fl.onchange = function (e) {
                    var ext = this.value.match(/\.(.+)$/)[1];
                            switch (ext)
                    {
                    case 'jpg':
                            case 'png':
                            case 'jpeg':
                            case 'JPG':
                            case 'PNG':
                            case 'JPEG':
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
