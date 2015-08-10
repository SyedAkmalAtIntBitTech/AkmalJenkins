<%-- 
    Document   : selectpromotemedia
    Created on : 29 Jul, 2015, 11:48:01 AM
    Author     : sandeep-kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%! String minbodyid; %>
        <% minbodyid= request.getParameter("id");%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>promote media</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
         <script src="js/configurations.js"></script>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        
        <script>
            function selected_media(selectedmedia){
                  var configuration = global_host_address + selectedmedia +".jsp" + "?id=" +<%= minbodyid%>;
                    window.open(configuration, "_self");
            }
        </script>
     
    </head>
    <body>
      

        <div > 
               <div class="row">
                    <jsp:include page="mainmenu.html"/><!--/end left column-->
    
                    <div class="col-md-10">
                        <p id="text3">How would you like to promote it?</p> 
                        <ul id="promotebuttonlist">
                            <li><a onclick="selected_media('socialeditor')"><span id="social" class="glyphicon glyphicon-comment"></span></a><p id="promotebutton">Social</p></li>
                                <li><a onclick="selected_media('emailsubject')" ><span id="email" class="glyphicon glyphicon-envelope"></span></a><p id="promotebutton">Email</p></li>
<!--                                <li><a ><span id="print" class="glyphicon glyphicon-print"></span></a><p id="promotebutton">Print</p></li>-->
                        </ul>    
                        </div>
        
                    </div>      

            
                 </div>   
    </body>
</html>
