<%-- 
    Document   : socialeditor
    Created on : Jul 10, 2015, 10:03:32 AM
    Author     : intbit
--%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.io.File"%>
<%@page import="javax.swing.JFileChooser"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.BufferedReader"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>social editor</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>

        <link rel="icon" href="data:,">
        <link href="css/crop.css" rel="stylesheet" type="text/css"/>
        <link href="css/example.css" rel="stylesheet" type="text/css"/>
        <script src="js/crop.js" type="text/javascript"></script>
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
                width: auto;
                height:300px;
                top: 30%;
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
                overflow: auto;
            }
            #imagespopup{

                display:none;
                position: fixed;
                width: 450px;
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
                text-align: center;
                overflow:auto;
            }
            #content
            {
/*                height:auto;*/
                width:300px;
                height:300px;
                margin:5px auto;
            }
            #popupclose
            {
                margin:35px 0 0 80px;
                width:50px;

            }
            tr,td{
                margin-left: 2px;
                padding-right: 5px;
            }
        </style>

        <%!
            StringBuffer string_buffer = new StringBuffer();
            String mindbody_data_id;
        %> 
        <%
            try {

                mindbody_data_id = (String) request.getParameter("id");
//                String msg = request.getParameter("msg");
//              JOptionPane.showMessageDialog(null,"name cannot be blank "+msg);

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        %>
        <!--        <script src="js/socialeditor.js" type="text/javascript"></script>-->

        <script>
                    var jsondata;
                    var mindbodydataId = $("#mindbodydata").val();
                    angular.module("myapp", [])

                    .controller("MyController", function($scope, $http) {

                    $http({
                    method : 'GET',
                            url : 'GetUserPreferences'
                    }).success(function(data, status, headers, config) {
                    $scope.user_preferences_colors = data.user_colors;
                            $scope.user_preferences_font_names = data.user_font_names;
                            $scope.user_preferences_font_sizes = data.user_font_sizes;
                            if (data === error){
                    alert(data);
                    }
                    }).error(function(data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });
                            $scope.showStyles = function(){

                            $scope.curPage = 0;
                                    $scope.pageSize = 2;
                                    $http({
                                    method : 'GET',
                                            url : 'GetLayoutStyles'
                                    }).success(function(data, status, headers, config) {

                                    $scope.datalists = data;
                                    $scope.numberOfPages = function() {
                                    return Math.ceil($scope.datalists.length / $scope.pageSize);
                                    };
                                    if (data === error){
                            alert(data);
                            }
                            }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                            $scope.showImages = function(){
                                    $("#popup").hide();
                                    $("#imagespopup").show();
                                    $scope.curPage = 0;
                                    $scope.pageSize = 4;
                                    $http({
                                    method : 'GET',
                                            url : 'GetUserImages'
                                    }).success(function(data, status, headers, config) {

                                    $scope.datalistimages = data;
                                    $scope.numberOfPages = function() {
                                    return Math.ceil($scope.datalistimages.length / $scope.pageSize);
                                    };
                                    if (data === error){
                            alert(data);
                            }
                            }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                    });
                    angular.module('myapp').filter('pagination', function()
            {
            return function(input, start)
            {
            start = + start;
                    return input.slice(start);
            };
            });
                    function showText(id, layout){
//    alert(id+""+layout);

                    layoutfilename = layout;
                            $("#clickid").val(layout);
                            $.ajax({
                            type: 'GET',
                                    url: 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id,
                                    data: {get_param: 'value'},
                                    dataType: 'json',
                                    success: function (data) {
                                    jsondata = data;
                                            $.ajax({
                                            type: "GET",
                                                    url: "images/xml/" + layout + ".xml",
                                                    dataType: "xml",
                                                    success: function (xml) {
                                                    $(".preview").empty();
                                                            $(xml).find('layout').each(function () {
                                                            height = $(this).find('container').attr("Height");
                                                            width = $(this).find('container').attr("Width");
                                                            $(".preview").css("width", width + "px");
                                                    }

                                                    );
                                                            $(xml).find('element').each(function () {
                                                            var tag = $(this).attr("tag");
                                                            type = $(this).attr("type");
                                                            var h = "";
                                                            var t = "";
                                                            var elementdata;
                                                            $(jsondata).each(function (i, val) {

                                                    $.each(val, function (k, v) {
//                                alert(k + " : " + v+ ":"+ type);
                                                    if (type.trim() == k.trim()) {
                                                    alert();
                                                            elementdata = v;
                                                    }

                                                    });
                                                    });
                                                            var fontcolor;
                                                            var fontsize;
                                                            var fontstyle;
                                                            var left = $(this).attr("x-co-ordinates");
                                                            var top = $(this).attr("y-co-ordinates");
                                                            var opacity = $(this).attr("opacity");
                                                            if (tag === "text")
                                                    {

                                                    fontcolor = $(this).attr("font-color");
                                                            fontsize = $(this).attr("font-size");
                                                            fontstyle = $(this).attr("font-style");
                                                            var fontweight = $(this).attr("font-weight");
                                                            var letterspacing = $(this).attr("letter-spacing");
                                                            var lineheight = $(this).attr("line-height");
                                                            var textalign = $(this).attr("text-align");
                                                            var webkittransform = $(this).attr("webkit-transform");
                                                            var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");
//                    alert($(this).attr("text-shadow"));
                                                            $(".preview").append("<div><textarea class=textAreas onclick=getTectId() id=" + type + ">" + elementdata + "</textarea>");
                                                            $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                                            .css("font-size", "" + fontsize).css("font-style", "" + fontstyle).css("font-weight", "" + fontweight)
                                                            .css("letter-spacing", "" + letterspacing).css("line-height", "" + lineheight)
                                                            .css("opacity", "" + opacity).css("text-align", "" + textalign)
                                                            .css("text-shadow", "" + dropshadow).css("webkit-transform", "rotate(" + webkittransform + "deg)");
                                                    }

                                                    if (tag === "image")
                                                    {
                                                    var blendmode = $(this).attr("background-blend-mode");
                                                            var width = $(this).attr("width");
                                                            var height = $(this).attr("height");
//                    alert("image");
                                                            $(".preview").append("<div><img src='" + elementdata + "' id=" + type + " alt='image'/>");
                                                            $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                                            .css("background-blend-mode", "" + blendmode).css("opacity", "" + opacity)
                                                            .css("width", "" + width).css("height", "" + height)
                                                            .attr("src", "images/default.png");
                                                    }

                                                    if (tag === "button")
                                                    {

                                                    $(".preview").append("<div><img src='" + elementdata + "'id=" + type + " alt='button'/>");
                                                            $("#" + type).css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                                            .attr("src", "buttons/button1.png");
                                                    }

                                                    if (tag === "block")
                                                    {
//                  alert("block");
                                                    var width = $(this).attr("width");
                                                            var height = $(this).attr("height");
                                                            var backgroundcolor = $(this).attr("background-color");
//                 alert(backgroundcolor);
                                                            $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                                                            $("#" + type).css("background-color", "" + backgroundcolor).css("margin-left", "" + left + "px")
                                                            .css("margin-top", "" + top + "px").css("width", "" + width)
                                                            .css("height", "" + height);
                                                    }

                                                    }

                                                    );
                                                    },
                                                    error: function (e)
                                                    {
                                                    alert("error in xml file read");
                                                    }
                                            });
                                    }
                            });
                    }


        </script>
        <script src="js/socialeditor.js" type="text/javascript"></script>
    </head>
    <body ng-app="myapp">
        <div ng-controller="MyController" class="container" id="container"> 
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
                            <li><a href="imagegallery.jsp"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
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
                                <!--  {{mindbody_data}}-->
                                <!--
                                        NOTE: To change the aspect ratio, look in crop.css
                                        The class 'default' links the div to the init(); function
                                --> 

                            </div><br><br><br><br><br><br>
                            <div class="span3 col-md-offset-0" >

                                <input id="continue" type="button" class="btn btn-primary" value="CONTINUE"><br><br>
                                <script>
                                            $("#continue").click(function (){
                                            document.location.href = "selectpromotesocialmedia.jsp";
                                            });
                                            function showImageName(user_id, image_name){
                                                var image_path = "images/Gallery/" + user_id +"/" + image_name;
                                                $("#image_name").val(image_path);
                                            }
                                </script>
                            </div>

                            <div id="imagespopup">
                                <div id="content">
                                        <div>
                                            <ul>
                                                <li class="paginationclass" ng-repeat="images in datalistimages | pagination: curPage * pageSize | limitTo: pageSize">
                                                    <div>
                                                        <img id="{{images.id}}" class="img-responsive lookchooser5" src="images/Gallery/{{images.user_id}}/{{images.image_name}}"  onclick="showImageName('{{images.user_id}}','{{images.image_name}}')" width=50 height=50 />
                                                    </div> 
                                                </li>
                                            </ul>

                                            <div class="pagination pagination-centered" ng-show="datalistimages.length">
                                                <ul class="pagination-controle pagination">
                                                    <li>
                                                        <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
                                                                ng-click="curPage = curPage - 1"> &lt; PREV</button>
                                                    </li>
                                                    <li>
                                                        <span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
                                                    </li>
                                                    <li>
                                                        <button type="button" class="btn btn-primary"
                                                                ng-disabled="curPage >= datalistimages.length / pageSize - 1"
                                                                ng-click="curPage = curPage + 1">NEXT &gt;</button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <input id="selectimage" name="selectimage" type="Button" value="select"/>  
                                        <input type="text" name="image_name" id="image_name"/>
                                        <input id="closeimagespopup" type="Button" value="close"/>  
                                
                                </div>
                            </div>
                                    <div id="popup" name="popup">
                                        <div id="content">
                                            System Directory : <input type="file" class="uploadfile" id="uploadfile" name="uploadfile" > <br> 
                                            User Directory : <input type="button" id="UserUploadedImages" name="UserUploadedImages" value="Click" ng-click="showImages()" > <br> 
                                            
                                            <input id="closepopup" type="Button" value="close"/>  
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
                                                <li><p id="editorheadere">font color</p><div class="color-box blankcolor-box" id="picker"></div></li>
                                                <!--                                                <li><p id="editorheadere">font size</p><div class="glyphicon glyphicon-font"><br></div></li>
                                                                                                <li><p id="editorheadere">font style</p><select></select></li>-->
                                                <li>
                                                    <p id="editorheadere">font size:</p>
                                                    <select  id="fontsize" style="margin: 2px; font-size: 15px; ">
                                                        <option ng-repeat ="sizes in user_preferences_font_sizes" value="{{sizes}}">{{sizes}}</option>
                                                    </select>
                                                </li>

                                                <li>
                                                    <p id="editorheadere">font Name:</p>
                                                    <select id="fontname" style="margin: 2px;font-size: 15px; ">
                                                        <option ng-repeat ="names in user_preferences_font_names" value="{{names}}">{{names}} </option>
                                                    </select>
                                                </li>
                                                <li><div class="glyphicon glyphicon-indent-right alignButton" id="hidealignbutton"></div></li>
                                                <li><div class="glyphicon glyphicon-align-justify alignButton" id="justify"></div></li>
                                                <li><div class="glyphicon glyphicon-align-left alignButton" id="left"></div></li>
                                                <li><div class="glyphicon glyphicon-align-center alignButton" id="center"></div></li>
                                                <li><div class="glyphicon glyphicon-align-right alignButton" id="right"></div></li>
                                                <li><div class="glyphicon glyphicon-plus" id="plus"></div></li>
                                                <li><div class="glyphicon glyphicon-minus" id="minus"></div></li>
                                            </ul>

                                        </div>

                                        <input type="hidden" id="mindbodydata" value=<%= mindbody_data_id%>>
                                        <input type="hidden" id='clickid'>

                                        <div id="shapecontainer">
                                            <p>SHAPES</p>
                                            <ul id="shapemodificatoin">
                                                <li> <p id="editorheadere">Header background<p></li>
                                                <li><div class="blankcolor-box" id="selectedshapecolorbox" style="background-color: {{user_preferences_colors.color1}}"></div></li>
                                                <li>
                                                    <ul id="colorpalette">
                                                        <li><div class="blankcolor-box" id="shapecolorbox1" style="background-color: {{user_preferences_colors.color1}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox2" style="background-color: {{user_preferences_colors.color2}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox3" style="background-color: {{user_preferences_colors.color3}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox4" style="background-color: {{user_preferences_colors.color4}}"></div></li>
                                                        <li> <div class="blankcolor-box" id="shapecolorbox5" style="background-color: {{user_preferences_colors.color5}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox6" style="background-color: {{user_preferences_colors.color6}}"></div></li>
                                                        <li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </div>

                                        <div id="imagecontainer">
                                            <p>IMAGE</p>
                                            <ul id="imagemodification">
                                                <li><p id="editorheadere">Image Name</p></li>
                                                <li><label id="openImageDialog" class="btn  newupload">change</label></li>
                                                <li><p id="editorheadere" onclick="imageEdit()">edit</p></li>
                                                <li></li>
                                            </ul>
                                        </div>

                                        <div id="filtercontainer" style="display: none">
                                            <p>IMAGE FILTER</p>
                                            <ul id="filterImageList">
                                                <li><img class="imageFilter " id="convert1" src="images/Blackandwhite.jpg" alt="" ><p id="filtername">Black <br>And White</p> </li>
                                                <li><img class="imageFilter" id="convert2" src="images/Blackandwhite.jpg" alt=""> <p id="filtername">Textured</p></li>
                                                <li><img class="imageFilter" id="convert3" src="images/Blackandwhite.jpg" alt=""> <p id="filtername">Light</p></li>
                                                <li><img class="imageFilter" id="convert4" src="images/Blackandwhite.jpg" alt=""><p id="filtername">Heroic</p> </li>
                                                <li><img class="imageFilter" id="convert5" src="images/Blackandwhite.jpg" alt=""><p id="filtername">Statue</p> </li>
                                                <li><img class="imageFilter" id="convert6" src="images/Blackandwhite.jpg" alt=""><p id="filtername">Workout</p> </li>
                                            </ul>
                                        </div>
                                        <div id="cropImageContainer" style="display: none">
                                            <p>CROP</p>
                                            <div class="default">

                                                <div class="cropSlider"></div>

                                                <button class="cropButton">Crop</button> </div><br><br>
                                            <input type="button" id="done" class="btn btn-primary" onclick="saveImageEdit()" value="DONE"> 
                                        </div>

                                    </li>
                                    <li id="tabs-2">
                                        <div id="stylecontainer">
                                            <div>
                                                <div>
                                                    <ul>
                                                        <!--{{datalists}}-->
                                                        <li class="paginationclass" ng-repeat="styles in datalists| pagination: curPage * pageSize | limitTo: pageSize">
                                                            <div>
                                                                <img id="{{styles.id}}" class="img-responsive lookchooser5" src="images/Layout-styles/{{styles.layout_file_name}}.jpeg"  onclick="showText('{{styles.id}}','{{styles.layout_file_name}}')" width=250 height=150 />
                                                                <!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                                            </div> 
                                                            <div><p id=''></p></div>
                                                            <div></div><p>&nbsp;</p>
                                                        </li>
                                                    </ul>

                                                    <div class="pagination pagination-centered" ng-show="datalists.length">
                                                        <ul class="pagination-controle pagination">
                                                            <li>
                                                                <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
                                                                        ng-click="curPage = curPage - 1"> &lt; PREV</button>
                                                            </li>
                                                            <li>
                                                                <span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
                                                            </li>
                                                            <li>
                                                                <button type="button" class="btn btn-primary"
                                                                        ng-disabled="curPage >= datalists.length / pageSize - 1"
                                                                        ng-click="curPage = curPage + 1">NEXT &gt;</button>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>

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
                        <li><a href="#tabs-2" id="style" ><span class="glyphicon glyphicon-th" ng-click="showStyles()"><p id="text1" >STYLE</p></span></a></li>                  
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
                                    //  cropper settings
                                    // --------------------------------------------------------------------------

                                    // create new object crop
                                    // you may change the "one" variable to anything
//		var one = new CROP();
//
//		// link the .default class to the crop function
//		one.init('.default');
//
//		// load image into crop
//		one.loadImg('img/one.jpg');


                                    //  on click of button, crop the image
                                    // --------------------------------------------------------------------------

                                    $('body').on("click", "button", function() {

                            // grab width and height of .crop-img for canvas
                            var width = $('.crop-container').width() - 80, // new image width
                                    height = $('.crop-container').height() - 80; // new image height

                                    $('canvas').remove();
                                    $('.default').after('<canvas width="' + width + '" height="' + height + '" id="canvas"/>');
                                    var ctx = document.getElementById('canvas').getContext('2d'),
                                    img = new Image,
                                    w = coordinates(one).w,
                                    h = coordinates(one).h,
                                    x = coordinates(one).x,
                                    y = coordinates(one).y;
                                    img.src = coordinates(one).image;
                                    img.onload = function() {

                                    // draw image
//                    alert("crop test");
                                    ctx.drawImage(img, x, y, w, h, 0, 0, width, height);
                                    alert( img.src);
                                            // display canvas image
                                            $('canvas').addClass('output').show().delay('4000').fadeOut('slow');
                                            // save the image to server
                                            $.ajax({
                                                    type: "POST",
                                                    dataType: "json",
                                                    url: "/CropImageServlet",
                                                    data: { image: canvas.toDataURL() }
                                            })
                                            .done(function(data) {

                                            // You can pull the image URL using data.url, e.g.:
                                            // $('body').append('<img src="'+data.url+'" />');
                                            
                                            })
                                    }
//                                    alert(data+""+data.url);

                            });
                                    //  on click of .upload class, open .uploadfile (input file)
                                    // --------------------------------------------------------------------------

//		$('body').on("click", ".newupload", function() {
//		    $('.uploadfile').click();
//		});

                                    // on input[type="file"] change
                                    
                                    $("#selectimage").click(function(){
                                        var image_file = global_host_address + $("#image_name").val();
                                        alert(image_file);
                                        $('.preview').html('<div class="default"><div class="cropMain"></div>');
                                        one = new CROP();
                                    // link the .default class to the crop function
                                        one.init('.default');
                                        // load image into crop
                                        one.loadImg(image_file);
                                        $("#imagespopup").hide();
                                    });
                                    $('.uploadfile').change(function() {

                                        loadImageFile();
                                    // resets input file
                                        $('.uploadfile').wrap('<form>').closest('form').get(0).reset();
                                        $('.uploadfile').unwrap();
                                        $("#popup").hide();
                                    });
                                    //  get input type=file IMG through base64 and send it to the cropper
                                    // --------------------------------------------------------------------------

                                    oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
                                    
                                    function loadImageFileFromUser(image_file) {

                                    if (document.getElementById("uploadfile").files.length === 0) return;
                                            var oFile = document.getElementById("uploadfile").files[0];
                                            if (!rFilter.test(oFile.type)) {
                                            return;
                                            }

                                    oFReader.readAsDataURL(oFile);
                                    }
                                    
                                    function loadImageFile() {

                                    if (document.getElementById("uploadfile").files.length === 0) return;
                                            var oFile = document.getElementById("uploadfile").files[0];
                                            if (!rFilter.test(oFile.type)) {
                                            return;
                                            }

                                    oFReader.readAsDataURL(oFile);
                                    }

                            oFReader.onload = function (oFREvent) {
                            $('.preview').html('<div class="default"><div class="cropMain"></div>');
                                    alert("test");
                                    // create new object crop
                                    // you may change the "one" variable to anything
                                    one = new CROP();
                                    // link the .default class to the crop function
                                    one.init('.default');
                                    // load image into crop
                                    one.loadImg(oFREvent.target.result);
                            };

        </script>  
        
        <script>

                            //  get input type=file IMG through base64 and send it to the cropper
                            // --------------------------------------------------------------------------
                            
                            $("#openImageDialog").click(function(){
                                $('.default').hide();
                                $("#popup").show();
                            });
                            $("#closepopup").click(function(){
                                    $("#popup").hide();
                            });
                                    $("#UserUploadedImages").click(function(){
                            $("#popup").hide();
                                    $("#imagespopup").show();
                            });
                            $("#closeimagespopup").click(function(){
                                    $("#imagespopup").hide();
                            });


        </script>  
<!--<script>
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
    </script>-->
    </body>
</html>

