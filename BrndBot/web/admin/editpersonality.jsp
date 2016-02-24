<%-- 
    Document   : editpersonality
    Created on : Jun 30, 2015, 4:54:42 PM
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
        <script src="../js/brandfunctions.js" type="text/javascript"></script>
        <title>BrndBot - Edit Personality</title>
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
        String brand_id = request.getParameter("brand_id");
        String brand_name = request.getParameter("brand_name");
        String look_id = request.getParameter("look_id");
    %>

    <body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Brand Bot</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Fonts <span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="fontsSize.jsp">Fonts Size</a></li>
              <li><a href="fontsfamily.jsp">Fonts Family</a></li>
              <li><a href="fontsstyle.jsp">Fonts Style</a></li>
              <li><a href="fonttheme.jsp">Font theme</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Colors<span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="colors.jsp">Colors</a></li>
              <li><a href="colortheme.jsp">Color theme</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Category<span class="caret"></span></a>
          <ul class="dropdown-menu">
              <li><a href="categories.jsp">Category</a></li>
              <li><a href="subcategories.jsp">Sub category</a></li>
          </ul>
        </li>
        <li><a href="organizations.jsp">Organization</a></li>
        <li><a href="brandpersonality.jsp">Brand Personality</a></li>
        <li><a href="looks.jsp">Looks</a></li>
        <li><a href="model.jsp">Layout mapper</a></li>
      </ul>
    </div>
  </div>
</nav>
        
    <centre>
                
<!--             <div style="float: left; border: 1px solid;margin-left: 400px; margin-top: 100px; height: 300px;" >-->
            <div style="float: left; border: 1px solid; margin-left: 400px; margin-top: 0px; height: 300px;">
    
                <form name="formpersonality" action="<%= application.getContextPath() %>/ServletEditPersonality" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Edit Brand Personality</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 10px;">
                                 <input type="hidden" id="brandid" name="brandid" value="<%= brand_id %>"/>
                        Brand:<input type="text" id="brandname" name="brandname" value="<%= brand_name %>"/><br>
                        Select Look: <select name="look" id="look" style="width:180px;">
                                                <option value="0">--select--</option>
                    <%
                    Connection connection = null;
                    try{
                        connection = ConnectionManager.getInstance().getConnection();
                        
                        query_string = "select * from tbl_look Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                            Integer id = result_set.getInt("id");
                            if (id ==  Integer.parseInt(look_id)){
                    %>
                                                <option value="<%= result_set.getInt("id") %>" selected><%= result_set.getString("look_name") %></option>
                     <%
                            }else{
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("look_name") %></option>
                    <%
                            }
                        }
                    }finally{
//                        result_set.close();
                        prepared_statement.close();
                        ConnectionManager.getInstance().closeConnection(connection);
                    }
                    %>
                                         </select><br>
                    </div><br>    
                <div style=" float:left; left:0px; padding-left: 10px; padding-top: 20px;">
                    <div>
                        Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
                    </div><br>
                </div>

                <div style="position: relative; float:left; left:0px; top: 0px;">
                    <div>
                        <button id="" type="submit" class="btn btn-info">Update</button>
                        <button id="" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
</div>
            
<!--        </div>-->
                                         
        </centre>
    </body>
</html>
