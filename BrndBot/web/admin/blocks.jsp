<%-- 
    Document   : displayblockss
    Created on : Jun 27, 2015, 11:31:52 AM
    Author     : intbit
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.intbit.ConnectionManager"%>
<%@page import="javax.naming.NamingException"%>
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
<jsp:include page="checksession.jsp" />
<jsp:declaration>
        Logger logger = Logger.getLogger("blocks.jsp");
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string, name, mindbody_query, brand_id, sub_category_id;
        
        Integer number = 1, id = 0;


</jsp:declaration>
    <body ng-app  class="container">
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="blocksController" >
            <div class="jumbotron" style="height: 320px; margin-top: 0px; padding-top: 20px;">
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
                        <option value="null">--select--</option>
                        <option value="promote new workshop">promote new workshop</option>
                        <option value="promote todays workshop">promote todays workshop</option>
                        <option value="promote upcoming workshops">promote upcoming workshops</option>
                        
                        <option value="promote new class">promote new class</option>
                        <option value="promote todays class">promote todays class</option>
                        
                        <option value="promote event">promote event</option>
                        <option value="promote new staff">promote new staff</option>
                    </select><br>
                    Select brand personality: <select name="brand" id="brand" style="width:180px;">
                        <option value="0">--select--</option>
                        <%
                            Connection conn = null;
                        try {
                            conn = ConnectionManager.getInstance().getConnection();
                            
                            query_string = "select * from tbl_brand_personality Order By id ASC";
                            prepared_statement = conn.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();

                            while (result_set.next()) {
                        %>
                                <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("brand_name")%></option>
                        <%
                            }
                        }catch (Exception e) {
                            logger.log(Level.SEVERE, "", e);
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(conn);
                        }
                        %>
                    </select><br>
                    Select sub category: <select name="sub_categories" id="sub_categories" style="width:180px;">
                        <option value="0">--select--</option>
                        <%
                        try {
                            conn = ConnectionManager.getInstance().getConnection();
                            
                            query_string = "select * from tbl_sub_category Order By id ASC";
                            prepared_statement = conn.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();

                            while (result_set.next()) {
                        %>
                                <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("sub_category_name")%></option>
                        <%
                            }
                        }catch (Exception e) {
                            logger.log(Level.SEVERE, "", e);
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(conn);
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
                        <td>mindbody query</td>
                        <td>brand id</td>
                        <td>sub category</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        try{
                            conn = ConnectionManager.getInstance().getConnection();
                        query_string = "select * from tbl_blocks Order By id ASC";
                        
                        prepared_statement = conn.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        num = 1;
                        while (result_set.next()) {
                                id = result_set.getInt("id");
                                name = result_set.getString("name");
                                mindbody_query = result_set.getString("mindbody_query");
                                brand_id = result_set.getString("brand_id");
                                sub_category_id = result_set.getString("sub_category_id");
                    %>
                    <tr>
                        <td align="left">&nbsp;<%= num %></td>
                        <td><%= result_set.getString("name")%></td>
                        <td><%= result_set.getString("mindbody_query")%></td>
                        
                        <td><%= result_set.getString("brand_id")%></td>
                        <td><%= result_set.getString("sub_category_id")%></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" onclick="editBlock(<%= id %>, '<%=name %>','<%= mindbody_query %>','<%= brand_id %>', '<%= sub_category_id %>')">edit</button></td>
                        <td><button class="btn btn-info" id="blocks" name="blocks" value="delete" ng-click="deleteblock(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                        num = num +1;
                    }
                        result_set.close();
                        prepared_statement.close();
                    }catch (Exception e){
                        logger.log(Level.SEVERE, "", e);
                        //out.println(sql_methods.error);
                    }finally {
                            ConnectionManager.getInstance().closeConnection(conn);
                    }
                    %>
                </table>
            </div>
            <br>

        </div>
</body>
</html>
