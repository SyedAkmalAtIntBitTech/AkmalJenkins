<%-- 
    Document   : socialeditor
    Created on : Jul 10, 2015, 10:03:32 AM
    Author     : intbit
--%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>socialeditor</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <script>
            var head1 = "Head1";
            var i = 1;
            function getImage(){
                alert("text");
                
                for (i = 1; i <=5; i++){
                    
//                        var style_name = "styleimages" + i;
//                        $("#stylesimages").append("<div id="+style_name+" width='250' height='250'></div>");

                        $.ajax({
                        type: "GET",
                        url: "xml/yyy.xml",
                        dataType: "xml",
                        success: function (xml) {
                            $(xml).find('layout').each(function () {
                                
                                 height = $(this).find('container').attr("Height");
                                 width = $(this).find('container').attr("Width");
                                $("#stylesimages").css("width",width+"px");

                            }

                            );
                            $(xml).find('element').each(function () {
                                var tag = $(this).attr("tag");
                                type = $(this).attr("type");
                                var h = "";
                                var t = "";
                                if (type === "header1"){
                                    h = "yoga power";
                                }
                                else if(type === "body1"){
                                     t = "teacher1";
                                }
                                var fontcolor;
                                var fontsize;
                                var fontstyle;
                                var left = $(this).attr("x-co-ordinates");
                                var top = $(this).attr("y-co-ordinates");
                                var opacity=$(this).attr("opacity");
                                if (tag === "text")
                                {


                                     fontcolor = $(this).attr("font-color");
                                     fontsize=$(this).attr("font-size");
                                     fontstyle=$(this).attr("font-style");
                                    var fontweight=$(this).attr("font-weight"); 
                                    var letterspacing=$(this).attr("letter-spacing");
                                    var lineheight=$(this).attr("line-height");

                                    var textalign=$(this).attr("text-align");

                                    var webkittransform=$(this).attr("webkit-transform") ; 
                                    var dropshadow=$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+" "+$(this).attr("text-shadow");
                //                    alert($(this).attr("text-shadow"));
                                    $("#stylesimages").append("<div><textarea class=textAreas onclick=getTectId() id=" + type + ">" + type + "</textarea>");
                                    $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                                 .css("font-size",""+fontsize).css("font-style",""+fontstyle).css("font-weight",""+fontweight)
                                                 .css("letter-spacing",""+letterspacing).css("line-height",""+lineheight)
                                                 .css("opacity",""+opacity).css("text-align",""+textalign)
                                                 .css("text-shadow",""+dropshadow).css("webkit-transform","rotate("+webkittransform+"deg)");
                                }

                                if (tag === "image")
                                {
                                   var blendmode=$(this).attr("background-blend-mode");
                                    var width=$(this).attr("width");
                                    var height=$(this).attr("height");
                //                    alert("image");
                                    $("#styleimages").append("<div><img src='#' id=" + type +  " alt='image'/>");
                                     $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                             .css("background-blend-mode", "" + blendmode).css("opacity", "" + opacity)
                                            .css("width", "" + width).css("height", "" + height)
                                            .attr("src","images/default.png");
                                }

                                if(tag==="button")
                                {
                                  alert("button");  
                                  $("#styleimages").append("<div><img src='#' id=" + type+  " alt='button'/>");
                                   $("#" + type).css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                           .attr("src","buttons/button1.png");
                                }

                                if(tag==="block")
                                {
                //                  alert("block");
                                  var width=$(this).attr("width");
                                  var height=$(this).attr("height");
                                 var backgroundcolor = $(this).attr("background-color");
                //                 alert(backgroundcolor);
                                   $("#styleimages").append("<div id=" + type+"></div>");
                                    $("#" + type).css("background-color", ""+backgroundcolor).css("margin-left", "" + left + "px")
                                                 .css("margin-top", "" + top + "px").css("width",""+width)
                                                 .css("height",""+height);
                                }

                            }

                            );

                        },
                        error: function (e)
                        {
                            alert("error in xml file read");
                        }
                    });
                    
                    
//                    $("#stylesimages").append("<div ><img src='images/logo.png' id="+i+"/></div>");
                }
                
            }
        </script>
        <script src="js/socialeditor.js" type="text/javascript"></script>
     
       
    </head>
    <body>
        <div class="container" id="container"> 
            <div class="row">
                <div id="sidebar-wrapper" class="col-md-1">
                    <nav class="navbar navbar-default " role="navigation">
                        <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
                        <button class="hamburger">&#9776;</button>
                        <button class="cross">&#9776;</button>
                        <ul class="nav nav-stacked menu">
                            <li><a href="dashboard.html"><span class="glyphicon glyphicon-home"></span></a><p id="text1">HOME</p></li>
                            <li><a href="email.html"><span class="glyphicon glyphicon-envelope"></span></a><p id="text1">EMAIL</p></li>
                            <li><a href="social.html"><span class="glyphicon glyphicon-comment"></span></a><p id="text1">SOCIAL</p></li>
                            <li><a href="imagegallery.html"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
                            <li><a href="setting.html"><span class="glyphicon glyphicon-cog"></span></a><br></li> 
                            <li><br><p id="text2">LOG OUT</p><br><br></li> 
                        </ul>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div><!--/end left column-->

           
            </div>

            <!-- Page content -->
            <div id="page-content-wrapper">
                <!-- Keep all page content within the page-content inset div! -->
                <div class="page-content inset">
                    <div class="row">

                        <!--              preview container-->
                        <div class="col-md-7">
                            <p>EDIT THIS POST </p><p>go back</p> 
                            <div class="preview">
                                
                            </div><br><br><br><br><br><br>
                            <div class="span3 col-md-offset-0" >
                               
                                <input id="continue" type="button" class="btn btn-primary" value="CONTINUE"><br><br>

                            </div>
                              <div id="popup">
                                <div id="content">
                                    <input type="file" id="uploadImage" name="uploadImage">  
                                    <input id="popupclose" type="Button" value="close"/>  
                                </div>   

                            </div>
                        </div>

                           

                        <!--        editor container      -->
                        <div class="col-md-5">
                            <div class="well lead editor" id="editor">
                                <ul>
                                    <li id="tabs-1">
                                        <div id="textcontainer">
                                            <p>TEXT</p> 
                                             <ul id="textmodification">
                                                <li><p id="editorheadere">font color</p><div class="blankcolor-box" id="picker"></div></li>
                                                <li><p id="editorheadere">font size</p><div class="glyphicon glyphicon-font"><br></div></li>
                                                <li><p id="editorheadere">font style</p><select></select></li>
                                                <li><div class="glyphicon glyphicon-align-justify alignButton" id="justify"></div></li>
                                                <li><div class="glyphicon glyphicon-align-left alignButton" id="left"></div></li>
                                                <li><div class="glyphicon glyphicon-align-center alignButton" id="center"></div></li>
                                                <li><div class="glyphicon glyphicon-align-right alignButton" id="right"></div></li>
                                                 <li><div class="glyphicon glyphicon-plus" id="plus"></div></li>
                                                 <li><div class="glyphicon glyphicon-minus" id="minus"></div></li>
                                            </ul>
                                            
                                        </div>
                                        <div>
                                            <p>SHAPES</p>
                                            <ul id="shapemodificatoin">
                                                <li> <p id="editorheadere">Header background<p></li>
                                                <li><div class="blankcolor-box" id="shapecolorbox"></div></li>
                                            </ul>
                                        </div>
                                        <div>
                                            <p>IMAGE</p>
                                            <ul id="imagemodification">
                                                <li><p id="editorheadere">Teacher Image</p></li>
                                                <li><p id="editorheadere" onclick="uploadimage()">change</p></li>
                                                <li><p id="editorheadere">edit</p></li>
                                            </ul>
                                        </div>
                                    
                                    
                                    </li>
                                    <li id="tabs-2">
                                        <div id="stylecontainer">
                                            <div id="stylesimages">
                                                <img src="" id="image1" name="image1"/>
                                                
                                            </div>
                                            
                                        </div>
                                        
                                        
                                    </li>
                                </ul>

                            </div>
                        </div> 
                    </div>
                </div>
            </div>


            <div id="sidebar-wrapper1">
                <div id="tabs">
                    <ul class="sidebar-nav" id="sidebar">     
                        <li><a href="#tabs-1" id="text"><span class="glyphicon glyphicon-edit"></span><p id="text1">EDIT</p></a></li>
                        <li><a href="#tabs-2" id="style" ><span class="glyphicon glyphicon-th"></span><p id="text1" onclick="getImage()">STYLE</p></a></li>                  
                    </ul>
                </div>
            </div> 

        </div>   

        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("active");
            });
        </script>
        <script>
            $(".cross").hide();
            $(".menu").hide();
            $(".hamburger").click(function () {
                $(".menu").slideToggle("slow", function () {
                    $(".hamburger").hide();
                    $(".cross").show();
                });
            });

            $(".cross").click(function () {
                $(".menu").slideToggle("slow", function () {
                    $(".cross").hide();
                    $(".hamburger").show();
                });
            });

        </script>  

    </body>
</html>

