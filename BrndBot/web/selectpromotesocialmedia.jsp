<%-- 
    Document   : selectpromotesocialmedia
    Created on : 17 Jul, 2015, 11:58:23 AM
    Author     : sandeep-kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/socialmedia.js" type="text/javascript"></script>
        <title>social media</title>

        <style>
               .socialimage{
                   width: 100px;
                   height: 100px;
                   margin-left:  5px;
               } 
              #popup
               {
                   display:none;
                   position: fixed;
                   width:550px;
                   height:400px;
                   top: 40%;
                   left: 50%;
                   margin-left:-155px;
                   margin-top:-110px;
                   border:5px solid #686868 ;
                   background-color:#CDCDFF;
                   padding:30px;
                   z-index:102;
                   font-family:Verdana;
                   font-size:10pt;
                   border-radius:10px;
                   -webkit-border-radius:20px;
                   -moz-border-radius:20px;
                   font-weight:bold;
               }
               #content
               {
                   height:auto;
                   width:300px;
                   height:500px;
                   margin:5px auto;
               }
               #popupclose
               {
                   margin:35px 0 0 80px;
                   width:50px;

               }
                
             
        </style>
    </head>
     </head>

    </head>
    
    <body>
        <div class="row">
                <div id="sidebar-wrapper" class="col-md-1">
                    <nav class="navbar navbar-default " role="navigation">
                        <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
                    </nav>
                </div><!--/end left column-->

                  <div class="col-md-10">
                      <p id="text3">Great!<br> 
                          Which social media platform(s)<br>
                    would you like to post it on?</p> 
                        <ul id="promotebuttonlist">
                            <li><img class="socialimage" src="images/fb_icon.png" /> <input type="checkbox" id="facebook" name="social" value="facebook"> </li>
                            <li><img class="socialimage" src="images/twitter.jpeg" > <input type="checkbox" id="twitter" name="social" value="twitter"><br> </li>
                        <li><div class="col-md-4 col-md-offset-6">
                          <a href="socialmediapreview.html"><input type="button" class="btn btn-primary" value="Continue"></a>
                        </div>
                    </li>
                        </ul>  
                       
                 </div>
                
             
                <div id="popup">
                    <div id="content">
                        <input type="button" id="close" name="close" value="close">   
                        <div id="managepages"></div>

                    </div>               
                </div>
                
    
        
           </div>
    </body>
</html>
