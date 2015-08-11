<%-- 
    Document   : emailpreview
    Created on : 6 Aug, 2015, 3:57:37 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link href="css/leftnavbar.css" rel="stylesheet" type="text/css"/>
        <script src="js/leftmenuhamburger.js" type="text/javascript"></script>
        <link href="css/emailpreview.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.css" />
        <style type="text/css">
            a.fancybox div {
                border: none;
                box-shadow: 0 1px 7px rgba(0,0,0,0.6);
                -o-transform: scale(1,1); -ms-transform: scale(1,1); -moz-transform: scale(1,1); -webkit-transform: scale(1,1); transform: scale(1,1); -o-transition: all 0.2s ease-in-out; -ms-transition: all 0.2s ease-in-out; -moz-transition: all 0.2s ease-in-out; -webkit-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out;
            } 
            a.fancybox:hover div {
                position: relative; z-index: 999; -o-transform: scale(1.03,1.03); -ms-transform: scale(1.03,1.03); -moz-transform: scale(1.03,1.03); -webkit-transform: scale(1.03,1.03); transform: scale(1.03,1.03);
            }
        </style>
        <title>email preview</title>
        <style>
/*            div
{
    width: 100vw; 
    height: 56.25vw;  100/56.25 = 1.778 
    background: pink;
    max-height: 100vh;
    max-width: 177.78vh;  16/9 = 1.778 
    margin: auto;
    position: absolute;
    top:0;bottom:0;  vertical center 
    left:0;right:0;  horizontal center 
}*/
            #iphone{
                width: 25px;
                height: 50px;
            }
            #imac{
                width: 50px;
                height: 50px;
            }
            #ipad{
                width: 90px;
                height: 50px;
            }
            .images li{
                display: inline-table;
                margin-right: 10px;
                margin-top: 150px;
            }

            .vlightbox {
                display:-moz-inline-stack;
                display:none;
                zoom:1;
                *display:none;
                position:relative;
                vertical-align:top;
                margin:3px;
                width:160px;
                font-family:Trebuchet,Tahoma,Arial,sans-serif;
                font-size:11px;
                font-weight:normal;
                text-decoration:none;
                text-align:center;
                opacity:0.87;
            }
            .vlightbox {
                display:-moz-inline-stack;
                display:inline-block;
                zoom:1;
                *display:inline;
                position:relative;
                vertical-align:top;
                margin:3px;
                width:160px;
                font-family:Trebuchet,Tahoma,Arial,sans-serif;
                font-size:11px;
                font-weight:normal;
                text-decoration:none;
                text-align:center;
                opacity:0.87;
            }
            .content{
                position: relative;
                top: 90px;
                margin-left: 60px;
                 width: 300px;
                height: 200px;
                zoom: 0.5;
            }
            #popup{
                width: 500px;
/*                height: 500px;*/
            }
        </style>
        <script type="text/javascript">
            var started;
            function showLightBox()
            {
                if (started)
                    return;
                started = setTimeout(function () {
                    Lightbox.start(document.getElementById('firstImage'));
                    started;
                }, 500);
            }
            function stopShowLightBox() {
                if (started) {
                    clearTimeout(started);
                    started = 0;
                }
            }
        </script>

    </head>
    <%!
//    String emailSubject="";
//    String emailList="";
//    String htmlData="";
//    SqlMethods sqlmethods = new SqlMethods();
%>
    <%
//     sqlmethods.session = request.getSession(true);
//    emailSubject=(String)sqlmethods.session.getAttribute("email_subject");
//    emailList = (String)sqlmethods.session.getAttribute("email_list");
//    htmlData=request.getParameter("htmlString");

    %>

    <body>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.pack.min.js"></script>
        <script type="text/javascript">
//                    $(function ($) {
//                        var addToAll = false;
//                        var gallery = true;
//                        var titlePosition = 'over';
//                        $(addToAll ? 'img' : 'img.fancybox').each(function () {
//                            var $this = $(this);
//                            var title = $this.attr('title');
//                            var src = $this.attr('data-big') || $this.attr('src');
//                            var a = $('<a href="#" class="fancybox"></a>').attr('href', src).attr('title', title);
//                            $this.wrap(a);
//                        });
//                        if (gallery)
//                            $('a.fancybox').attr('rel', 'fancyboxgallery');
//                        $('a.fancybox').fancybox({
//                            titlePosition: titlePosition
//                        });
//                    });
//                    $.noConflict();
                    
                    
                    
                    $(function ($) {
                        var addToAll = false;
                        var gallery = true;
                        var titlePosition = 'over';
                        $(addToAll ? 'div' : 'div.fancybox').each(function () {
                            var $this = $(this);
                            var title = $this.attr('title');
                        
                            var src = $this.attr('data-big') || $this.css("background-image").replace("url(","").replace(")","");
                            alert(src);
                            var a = $('<a href="#" class="fancybox"></a>').attr('href', src).attr("append","sandeep");
                            $this.wrap(a);
                        });
                        if (gallery)
                            $('a.fancybox').attr('rel', 'fancyboxgallery');
                        $('a.fancybox').fancybox({
                            titlePosition: titlePosition
                        });
                    });
                    $.noConflict();
                    
        </script>

        <div class="row">
            <div id="sidebar-wrapper" class="col-md-1">
                <nav class="navbar navbar-default " role="navigation">
                    <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
                    <button class="hamburger">&#9776;</button>
                    <button class="cross">&#9776;</button>
                    <ul class="nav nav-stacked menu">
                        <li><a href="dashboard.jsp"><span class="glyphicon glyphicon-home"></span></a><p id="text1">HOME</p></li>
                        <li><a href="emaillists.jsp"><span class="glyphicon glyphicon-envelope"></span></a><p id="text1">EMAIL</p></li>
                        <li><a href="social.html"><span class="glyphicon glyphicon-comment"></span></a><p id="text1">SOCIAL</p></li>
                        <li><a href="imagegallery.jsp"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
                        <li><a href="setting.html"><span class="glyphicon glyphicon-cog"></span></a><br></li> 
                        <li><br><a href="signout.jsp"><p id="text2">LOG OUT</p></a><br><br></li> 
                    </ul>
                    <!-- /.navbar-collapse -->
                </nav>
            </div>
            <div class="col-md-6 col-md-offset-1">
                <p id="text1">EDIT THIS EMAIL</p>
                <p id="text2">go back</p>
                <form class="form-horizontal" id="emailform">
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">                            
                            <!--                                 <input id="subject" class="form-control simplebox" type="text" value= >-->
                            <label>SUBJECT</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="formaddress" class="form-control simplebox" type="text" required>
                            <label>FROM ADDRESS</label><br>
                        </div>
                    </div>
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <!--                                <input id="toaddress" class="form-control simplebox" type="text" value=>-->
                            <label>TO ADDRESS</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="email" class="form-control simplebox" type="text" required>
                            <label>EMAIL</label><br><br>
                        </div>
                    </div>
                    <div  class="form-group">
                        <div class="col-md-3 col-md-offset-5">
                            <br><br><button type="submit"  class="btn btn-info" >SEND</button><br><br><br>
                        </div>
                        <div class="col-md-2">
                            <br><br> <button type="button"  class="btn btn-info" >SCHEDULE</button><br><br><br>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4">
<!--
                <ul class="images">
                    <li><div id="iphone" class="img-responsive fancybox" onMouseOver="javascript:showLightBox()" onMouseOut="javascript:stopShowLightBox()" style="cursor: pointer;background-image: url('images/iphone 6 screen.png');"></div></li>
                    <li><img id="imac" class="img-responsive fancybox" src="images/IMAC.png"></li>
                    <li ><img id="ipad" class="img-responsive fancybox" src="images/IPAD3.png"></li>
                
                </ul>-->
                
                  <ul class="images">
                        <li><div id="iphone" class="img-responsive " onclick="show('iphone');" style="background-image: url('images/iphone 6 screen.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                        <li><div id="imac" class="img-responsive" onclick="show('imac');"  style="background-image: url('images/imac27.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                        <li><div id="ipad" class="img-responsive" onclick="show('ipad');"  style="background-image: url('images/IPAD3.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                
                </ul>
                
<!--                <a class="vlightbox" href="images/Blackandwhite.jpg" title="2" id="firstImage"> </a>-->

        <div class="iphoneshow img-responsive"   id="popup" style="background-repeat: no-repeat; -webkit-background-size: contain; display: none;">
           <div class="content">  
                       <textarea class="textAreas" onclick="getTectId(header1)" id="header1" style="color: rgb(0, 0, 255);font-size: 14px; font-style: italic; font-weight: bold; letter-spacing: 5px; line-height: 20px; opacity: 0.800000011920929; text-align: left; text-shadow: rgb(53, 28, 73) 4px 10px 1px; -webkit-transform: rotate(15deg); border: none; resize: none;width: 300px;height: auto;">
                        text area</textarea><textarea class="textAreas" onclick="getTectId(header1)" id="header1" style="color: rgb(0, 0, 255);font-size: 14px; font-style: italic; font-weight: bold; letter-spacing: 5px; line-height: 20px; opacity: 0.800000011920929; text-align: left; text-shadow: rgb(53, 28, 73) 4px 10px 1px; -webkit-transform: rotate(15deg); border: none; resize: none;width: 300px;height: auto;">
                        text area</textarea><textarea class="textAreas" onclick="getTectId(header1)" id="header1" style="color: rgb(0, 0, 255);font-size: 14px; font-style: italic; font-weight: bold; letter-spacing: 5px; line-height: 20px; opacity: 0.800000011920929; text-align: left; text-shadow: rgb(53, 28, 73) 4px 10px 1px; -webkit-transform: rotate(15deg); border: none; resize: none;width: 300px;height: auto;">
                        text area</textarea>
                        <div class="img-responsive" onclick="getImageid(header2)" id="header2" style=" opacity: 1; width: 300px; height: 200px; background: url(http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg) no-repeat; -webkit-background-size: contain;"></div>
                        <div class="img-responsive" onclick="getImageid(header2)" id="header2" style=" opacity: 1; width: 300px; height: 200px; background: url(http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg) no-repeat; -webkit-background-size: contain;"></div>

                </div>
         </div>
                       <script>
                       
                           function show(id){
                            var imageUrl= $("#"+id).css("background-image");
                             if(id==="imac")
                             {
                                $(".iphoneshow").css("background-image",imageUrl)
                                                .css("display",'block')
                                                .css("height","900px"); 
                                $(".content").css("zoom","0.4");        
                                    
                             }
                              else if(id==="iphone"){  
                               $(".iphoneshow").css("background-image",imageUrl).css("display",'block').css("height","450px");
                           } 
                           else{
                                $(".iphoneshow").css("background-image",imageUrl).css("display",'block').css("height","500px");
                           }
                           }
                          
                          document.ready(function (){
                           $("div").click(function(){
                               alert("clicked");
                                    $(".iphoneshow").css("display",'block');
                                                     
                           });
                           });
                       </script> 
                       
                       
    </body>
</html>
