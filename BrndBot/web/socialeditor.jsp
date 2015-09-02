<%-- 
    Document   : socialeditor
    Created on : Jul 10, 2015, 10:03:32 AM
    Author     : intbit
--%>
<%@page import="com.intbit.AppConstants"%>
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
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
        <!--
        <script src="js/jquery.easy-confirm-dialog.js" type="text/javascript"></script>
        <script src="js/jquery.blend.min.js" type="text/javascript"></script>-->
          <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"><!--
            <script src="//code.jquery.com/jquery-1.10.2.js"></script>
-->            
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link href="css/crop.css" rel="stylesheet" type="text/css"/>
        <link href="css/example.css" rel="stylesheet" type="text/css"/>
        <link href="css/imagecropper.css" rel="stylesheet" type="text/css"/>
        <style>
            body{
                font-family: proxima nova;
            }
            .socialimage{
                width: 100px;
                height: 100px;
                margin-left:  5px;
            } 
               #slider{
                width:150px;height: 5px;
                position: relative;
                left:60px;
                top:-18px;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default
            {
               width: 20px;
               height:14px;
/*                border-radius: 20px;*/
               background-color: #FFF;
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

            #cropper_popup{
                position: fixed;
                top: 40%;
                left: 50%;
                z-index: 2147483583;
                width: 460px;
                margin: -250px 0 0 -280px;
                background-color: #F4F0F8;
                border: 1px solid #999;
                border: 1px solid rgba(0, 0, 0, 0.3);
                *border: 1px solid #999;
                border-radius: 6px;
                box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
                background-clip: padding-box;
                display:none;
                position: fixed;
                height:605px;
                font-family:Verdana;
                font-size:10pt;
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
            body{
                overflow-x: hidden;
            }
        </style>

        <%!
            StringBuffer string_buffer = new StringBuffer();
            String mindbody_data_id = "";
        %> 
        <%
            try {
                if (!request.getParameter("id").equals("null")){
                    mindbody_data_id = (String) request.getParameter("id");
                }
//                String msg = request.getParameter("msg");
//              JOptionPane.showMessageDialog(null,"name cannot be blank "+msg);

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        %>
        <!--        <script src="js/socialeditor.js" type="text/javascript"></script>-->
       
        <script src="//use.typekit.net/wnn8jyx.js"></script>
            <script>try{Typekit.load({ async: true });}catch(e){}</script>
        <script>
            
            $(document).ready(function () {

            $("#fontname").change(function () {
//            alert($(this).val());
                var text = $("#fontname").find('option:selected').text();
                var font_family_name = $("#fontname").val();
                var font = font_family_name.split(",");
//                var google_key_word = font[0].split(' ').join('+')
//
//                var ss = document.createElement("link");
//                ss.type = "text/css";
//                ss.rel = "stylesheet";
//                ss.href = "https://fonts.googleapis.com/css?family="+ google_key_word;
//                document.getElementsByTagName("head")[0].appendChild(ss);
//
//                var font_path = global_host_address + "DownloadFonts?file_name="+ font[1];
//                var styles = "@font-face {"+
//                             "font-family:"+ text + ";"+
//                             "src: url("+font_path+");"
//                $('<style type="text/css">'+ styles +'</style>').appendTo(document.head);

                var font_name = font[0].split('+').join(' ');
                $("#" + selectedTextareaId).css("font-family", font[0]);

            });
            });
        </script>
        <script>
                    var jsondata;
                    var selectedDivId;
                    var mindbodydataId = $("#mindbodydata").val();
                    angular.module("myapp", [])

                    .controller("MyController", function($scope, $http) {
                    $http({
                            method : 'GET',
                            url : 'GetUserPreferences'
                    }).success(function(data, status, headers, config) {
//                        alert(JSON.stringify(data.user_colors));
                            $scope.user_preferences_colors = data.user_colors;

                            $scope.user_preferences_font_sizes = data.user_font_sizes;
                            $scope.user_preferences_font_names = data.user_font_names;
                            var i = 0;
                            var font_object;
                            var font_family_name;
                            var font_name;
                            for (i; i<= data.user_font_names.length; i++){
                                font_object = data.user_font_names[i];
                                font_name = font_object.font_name;
                                font_family_name = font_object.font_family_name;
                                
                                var font = font_family_name.split(",");
                                var google_key_word = font[0].split(' ').join('+')

                                var ss = document.createElement("link");
                                ss.type = "text/css";
                                ss.rel = "stylesheet";
                                ss.href = "https://fonts.googleapis.com/css?family="+ google_key_word;
                                document.getElementsByTagName("head")[0].appendChild(ss);

                                var font_path = global_host_address + "DownloadFonts?file_name="+ font[1];
                                var styles = "@font-face {"+
                                             "font-family:"+ font_name + ";"+
                                             "src: url("+font_path+");"
                                $('<style type="text/css">'+ styles +'</style>').appendTo(document.head);


                            }
                            
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
                                            url : 'GetLayoutStyles?editorType=social'
                                    }).success(function(data, status, headers, config) {
//                                            alert(JSON.stringify(data));
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
//                                        alert(JSON.stringify(data));
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
                         var layout_mapper_url = "";
                   if (mindbodydataId != ""){
                       layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId +'&editor_type=social';
                   }else{
                       layout_mapper_url = 'GenericAnnouncementServlet?editor_type=social';
                   } 
                            $.ajax({
                                    type: 'GET',
                                    url: layout_mapper_url,
                                    data: {get_param: 'value'},
                                    dataType: 'json',
                                    success: function (data) {
                                    jsondata = data;
                                            $.ajax({
                                            type: "GET",
                                                    url: global_host_address + "DownloadXml?file_name="+layout+".xml",
                                                    dataType: "xml",
                                                    success: function (xml) {
                                                    $(".preview").empty();
                                                            $(xml).find('layout').each(function () {
                                                            height = $(this).find('container').attr("Height");
                                                            width = $(this).find('container').attr("Width");
                                                            $(".preview").css("width", width + "px");
                                                            $(".preview").css("height", height + "px");
                                                    }

                                                    );
                                                            var count=1;
                                                            var blockcount=1;
                                                            $(".imagename").find('option').remove().end();
                                                            $(".blockname").find('option').remove().end();
                                                            $(xml).find('element').each(function () {
                                                            var tag = $(this).attr("tag");
                                                            type = $(this).attr("type");
                                                            var h = "";
                                                            var t = "";
                                                            var elementdata;
                                                            $(jsondata).each(function (i, val) {

                                                    $.each(val, function (k, v) {
//                            alert(k + " : " + v+ ":"+ type);
                                                    if (type.trim() == k.trim()) {
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
                                                            var width = $(this).attr("width");
                                                            var height = $(this).attr("height");
                                                   if (tag === "text")
                                                   {
                                                       
                                                        var colorName=$(this).attr("font-color-name");
                                                        for(var i=1;i<=6; i++)
                                                        {
                                                            if(colorName == "Font-Color-"+i)
                                                            {
                                                              fontcolor= $("#shapecolorbox"+i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                                                            }

                                                        }
//                                                            fontcolor = $(this).attr("font-color");
                                                            fontsize = $(this).attr("font-size");
                                                            fontstyle = $(this).attr("font-style");
                                                            var fontweight = $(this).attr("font-weight");
                                                            var font = $(this).attr("font-family");
                                                            var font_family_name = font.split("+").join(" ");
                                                            var letterspacing = $(this).attr("letter-spacing");
                                                            var lineheight = $(this).attr("line-height");
                                                            var textalign = $(this).attr("text-align");
                                                            var webkittransform = $(this).attr("webkit-transform");
                                                            var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");
                                                        $(".preview").append("<div><textarea class=textAreas onclick=getTectId("+ type +") id=" + type + ">" + elementdata + "</textarea>");
                                                        $("#" + type).css("color", "" + fontcolor)
                                                                .css("position", "absolute")
                                                                .css("overflow", "hidden")
                                                                .css("margin-left", "" + left + "px")
                                                                .css("margin-top", "" + top + "px")
                                                                .css("width", "" + width)
                                                                .css("height", "" + height)
                                                                .css("font-size", "" + fontsize)
                                                                .css("font-style", "" + fontstyle)
                                                                .css("font-family", "" + font_family_name)
                                                                .css("font-weight", "" + fontweight)
                                                                .css("letter-spacing", "" + letterspacing)
                                                                .css("line-height", "" + lineheight)
                                                                .css("background-color","inherit")
                                                                .css("border","0px")
                                                                .css("opacity", "" + opacity)
                                                                .css("text-align", "" + textalign)
                                                                .css("text-shadow", "" + dropshadow)
                                                                .css("webkit-transform", "rotate(" + webkittransform + "deg)");
                                                        //$("#" + type).autogrow();
                                                        
                                                        //resize of text to fit bound - By Syed Ilyas 26/8/2015
                                                        var tempfontsize = parseInt(fontsize.replace("px",""));
                                                        var tempHeight = parseInt(height.replace("px",""));
                                                        if($("#" + type).get(0).scrollHeight > tempHeight)
                                                        {
                                                            $("#" + type).css("line-height", "initial");
                                                        while ( $("#" + type).get(0).scrollHeight > tempHeight) {
                                                               tempfontsize = tempfontsize - 1;
                                                              $("#" + type).css("font-size", "" + tempfontsize +"px");
                                                        }
                                                         var xxyy = parseInt(tempfontsize);
                                                        xxyy = Math.round(xxyy * 1.2);
                                                         $("#" + type).css("line-height",""+xxyy+"px");
                                                        }
                                                        //resize end
                                                        
                                                    }

                                               if (tag === "image")
                                               {
                                                    var background_image = $(this).attr("background-image");
                                                    var blendmode = $(this).attr("background-blend-mode");
//                                                   
                                                    $(".imagename").append("<option name="+background_image+" value="+ type +">Image "+count+"</option>");
                                                        count++;
                                                   $(".preview").append("<div onclick=getImageid(" + type + ") id=" + type + " ></div>");
                                                    $("#" + type)
                                                            .css("color", "" + fontcolor)
                                                            .css("margin-left", "" + left + "px")
                                                            .css("margin-top", "" + top + "px")
                                                            .css("background-blend-mode", "" + blendmode)
                                                            .css("opacity", "" + opacity)
                                                            .css("width", "" + width)
                                                            .css("height", "" + height)
                                                            .css("background", ""+background_image)
                                                            .css("background-repeat", "no-repeat")
                                                            .css("background-position", "center center")
                                                            .css("position", "absolute"); 
                                                    }

                                                    if (tag === "logo")
                                                    {
                                                    var background_image = $(this).attr("background-image");
                                                    var blendmode = $(this).attr("background-blend-mode");
                                                    $(".preview").append("<div onclick=getImageid(" + type + ") id=" + type + " ></div>");
                                                    $("#" + type)
                                                            .css("color", "" + fontcolor)
                                                            .css("margin-left", "" + left + "px")
                                                            .css("margin-top", "" + top + "px")
                                                            .css("background-blend-mode", "" + blendmode)
                                                            .css("opacity", "" + opacity)
                                                            .css("width", "" + width)
                                                            .css("height", "" + height)
                                                            .css("background", ""+background_image)
                                                            .css("background-repeat", "no-repeat")
                                                            .css("background-position", "center center")

                                                            .css("position", "absolute"); 
                                                    }

                                                    if (tag === "button")
                                                    {
                                                       var imageSrc= $(this).attr("src");    
                                                            $(".preview").append("<div><img src='" + elementdata + "'id=" + type + " alt='button'/>");
                                                             $("#" + type) .css("position", "absolute")
                                                                           .css("margin-left", "" + left + "px")
                                                                           .css("margin-top", "" + top + "px")
                                                                           .attr("src", "imageSrc");
                                                    }

                                                    if (tag === "block")
                                                    {
                                                        var borderRadius = $(this).attr("border-radius");
                                                        var colorName=$(this).attr("color-name");
                                                        var backgroundcolor;
                                                        for(var i=1;i<=6; i++)
                                                        {
                                                            if(colorName == "Color-"+i)
                                                            {
                                                              backgroundcolor= $("#shapecolorbox"+i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                                                            }

                                                        }
                                                        $(".blockname").append("<option value="+type+">Block "+blockcount+"</option>")
                                                        blockcount++;
                                                            var width = $(this).attr("width");
                                                            var height = $(this).attr("height");
                                                            var drop_shadow=$(this).attr("Drop-shadow-color");
                                                            var h_shadow =  $(this).attr("H-shadow"); 
                                                            var v_shadow=$(this).attr("V-shadow");
                                                            var Blur=$(this).attr("blur");
                                                            $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                                                            $("#" + type).css("background-color", "" + backgroundcolor)
                                                                    .css("margin-left", "" + left + "px")
                                                                    .css("margin-top", "" + top + "px")
                                                                    .css("width", "" + width)
                                                                    .css("border-radius", "" + borderRadius)
                                                                    .css("position", "absolute")
                                                                    .css("height", "" + height)
                                                                    .css("-webkit-filter","drop-shadow("+drop_shadow+" "+h_shadow+" " +v_shadow+" " +Blur+")")
                                                                    .css("opacity", "" + opacity);
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
        <script src="js/crop.js" type="text/javascript"></script>

    </head>
    <body ng-app="myapp">
        <div ng-controller="MyController" class="container" id="container"> 
            <div class="row">
                 <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <!-- Page content -->
            <div id="page-content-wrapper">
                <!-- Keep all page content within the page-content inset div! -->
                <div class="page-content inset">
                    <div class="row">

                        <!--              preview container-->
                        <div class="col-md-5 col-md-offset-0 prev">

                            <p class="edit SP1">EDIT THIS POST </p>&nbsp;&nbsp; <p id="edtgb" class="BT2"><a href="selectpromotemedia.jsp">go back</a></p>
                            <div class="preview" style="zoom: 0.5;">


                                <!--  {{mindbody_data}}-->
                                <!--
                                        NOTE: To change the aspect ratio, look in crop.css
                                        The class 'default' links the div to the init(); function
                                --> 

                            </div>
                            <div class="span3 col-md-offset-0" >

                                <input id="continue" class="button button--moema button--text-thick button--text-upper button--size-s" type="button" value="CONTINUE"><br><br>
                                <script>
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
                                                <li class="paginationclass" ng-repeat="images in datalistimages">
                                                    <div>
                                                        <img id="{{images.id}}" class="img-responsive lookchooser5" src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}"  onclick="showImageName('{{images.user_id}}','{{images.image_name}}')" width=50 height=50 />
                                                    </div> 
                                                </li>
                                            </ul>

<!--                                            <div class="pagination pagination-centered" ng-show="datalistimages.length">
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
                                            </div>-->
                                        </div>
                                        <input id="selectimage" name="selectimage" type="Button" value="select"/>  
                                        <input type="hidden" name="image_name" id="image_name"/>
                                        <input id="closeimagespopup" type="Button" value="close"/>  

                                </div>
                            </div>
                            <div id="popup" name="popup">
                                <div id="content">
                                    <form action="">
<!--                                    System Directory : <input type="file" class="uploadfile" id="uploadfile" name="uploadfile" > <br> -->
                                    User Directory : <input type="button" id="UserUploadedImages" name="UserUploadedImages" value="Click" ng-click="showImages()" > <br> 

                                    <input id="closepopup" type="Button" value="close"/>  

                                    </form>
                                </div>   
                            </div>
                            <div id="cropper_popup" class="cropper_popup" name="cropper_popup">
                                <div class="imagecropper_header">
                                    <div class="imagecropper_close" onclick="closeCropper()">×</div>
                                    <h3 class="imagecropper_title">Cropping image</h3>

                                </div>
                                <div class="crop_image">
<!--                                        <button class="cropButton">Crop</button>-->


                
<!--                                <input id=closepopup onclick=closeCropper() type="Button" value="close"/>-->
                                </div>   
                                <div class="imagecropper_footer">
                                    <input type="button" class="imagecropper_no" onclick="closeCropper()" value="Skip"/>
                                    <button class="imagecropper_ok cropButton">Crop</button>
                                </div>
                            </div>
  
                        </div>

                        <!--        editor container      -->
                        <div class="col-md-3 col-md-offset-1">
                            <div class="well lead editor" id="editor">
                                <ul>
                                    <li id="tabs-1">
                                        <div id="textcontainer">
                                            <p id="text3" class="SS2">TEXT</p> 
                                            <ul id="textmodification">
                                                <li><p id="editorheadere" class="SS1">font color</p>
                                                    <div class="blankcolor-box1" id="picker" ></div>
                                                    
                                                  
                                                    
                                                </li>
                                                <!--                                                <li><p id="editorheadere">font size</p><div class="glyphicon glyphicon-font"><br></div></li>
                                                                                                <li><p id="editorheadere">font style</p><select></select></li>-->
                                                <li>
                                                    <p id="editorheadere" class="SS1">font size</p>
<!--                                                    <select  id="fontsize" style="margin: 2px;width:80px; font-size: 15px;color: #3f4042;background-color: #ccc;border-radius:5px; ">
                                                        <option style="background:#FFF;" ng-repeat ="sizes in user_preferences_font_sizes" value="{{sizes}}">{{sizes}}</option>
                                                    </select> -->
                                                    <img id="minusFont" src="images/fontsize.png" width="20px"  height="20px" alt=""/> <img src="images/fontsize.png" width="25px"  height="25px" id="plusFont" alt=""/>
                                                </li>

                                                <li>
                                                    <p id="editorheadere" class="SS1">font style</p>


                                                    <select id="fontname" style="margin:2px;font-size:15px;width:80px;color: #3f4042;background-color: #ccc;border-radius:5px;">
                                                        <option style="background:#FFF;" ng-repeat ="names in user_preferences_font_names" value="{{ names.font_family_name}}">{{ names.font_name}} </option>

                                                    </select>
                                                </li>
                                                <li> 
                                                    <ul id="pickColorForText" style="display:none;left:-26px;position:relative;margin-top: -50px;">
                                                        <li><br><p class="editpal">your palette</p></li>
                                                        <li><p class="editpal custom-color-box-text" style="margin-left:150px;position:relative;top:20px;">custom</p></li>
                                                        <li id="fcolcontainer">
                                                            <ul id="colorpalette ">
                                                                   <li><div class="blankcolor-box-text" id="textcolorbox1" style="left:-14px;background-color: {{user_preferences_colors.color1}}"></div></li>
                                                                    <li><div class="blankcolor-box-text" id="textcolorbox2" style="background-color: {{user_preferences_colors.color2}}"></div></li>
                                                                    <li><div class="blankcolor-box-text" id="textcolorbox3" style="background-color: {{user_preferences_colors.color3}}"></div></li>
                                                                    <li><div class="blankcolor-box-text" id="textcolorbox4" style="background-color: {{user_preferences_colors.color4}}"></div></li>
                                                                    <li> <div class="blankcolor-box-text" id="textcolorbox5" style="background-color: {{user_preferences_colors.color5}}"></div></li>
                                                                    <li><div class="blankcolor-box-text" id="textcolorbox6" style="background-color: {{user_preferences_colors.color6}}"></div></li>
                                                                </ul>
                                                            </li>
                                                            
                                                        </ul>
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

                                        <input type="hidden" id="mindbodydata" name="mindbodydata" value='<%= mindbody_data_id %>'>
                                        <input type="hidden" id='clickid'>

                                        <div id="shapecontainer">
                                            <p  id="text3" class="SS2">SHAPES</p>
                                            <ul id="shapemodificatoin">

                                                <li><select class="blockname LE1" id="editorhead"></select></li>
                                                <li><div class="headblankcolor-box" id="selectedshapecolorbox" style="background-color: {{user_preferences_colors.color1}}"></div></li><br>
                                                <li><p class="editpal">your palette</p></li>
                                                <li id="colcontainer">
                                                    <ul id="colorpalette">
                                                       <li><div class="blankcolor-box" id="shapecolorbox1" style="left:-14px;background-color: {{user_preferences_colors.color1}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox2" style="background-color: {{user_preferences_colors.color2}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox3" style="background-color: {{user_preferences_colors.color3}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox4" style="background-color: {{user_preferences_colors.color4}}"></div></li>
                                                        <li> <div class="blankcolor-box" id="shapecolorbox5" style="background-color: {{user_preferences_colors.color5}}"></div></li>
                                                        <li><div class="blankcolor-box" id="shapecolorbox6" style="background-color: {{user_preferences_colors.color6}}"></div></li>
                                                    </ul>
                                                </li>
                                                <li><p class="editpal">pick from theme</p></li>
                                                <li><p class="editpal custom-color-box" style="margin-left: 120px;">custom</p></li>
                                                <li><p class="editpal" id="">opacity</p><div id="slider"></div></li>

                                            </ul>
                                        </div>

                                        <div id="imagecontainer">
                                            <p  id="text3" class="SS2">IMAGE</p>
                                            <ul id="imagemodification">
                                                <li><select class="imagename LE1" id="editorhead"> </select></li>
                                                <li><label id="openImageDialog" class="btn  newupload">change</label></li>
                                                <li><p  class="btn" onclick="imageEdit()">edit</p></li>
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
<!--                                                <p>CROP</p>-->


                                                    <!--
                                                            NOTE: To change the aspect ratio, look in crop.css
                                                            The class 'default' links the div to the innit(); function
                                                    -->

                                                <br><br>
                                            <input type="button" id="done" class="btn btn-primary" onclick="saveImageEdit()" value="DONE"> 
                                        </div>

                                    </li>
                                    <li id="tabs-2">
                                        <div id="stylecontainer">
                                            <div>
                                                <div style="height:500px; overflow-y: scroll;">
                                                    <ul>
                                                        <!--{{datalists}}-->
                                                        <li class="paginationclass" ng-repeat="styles in datalists">
                                                            <div>
                                                                <img id="{{styles.id}}" class="img-responsive lookchooser5" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{styles.image_file_name}}"  onclick="showText('{{styles.id}}','{{styles.layout_file_name}}')" width=250 height=150 />
                                                                <!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                                            </div> 
                                                            <div><p id=''></p></div>
                                                            <div></div><p>&nbsp;</p>
                                                        </li>
                                                    </ul>

<!--                                                    <div class="pagination pagination-centered" ng-show="datalists.length">
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
                                                    </div>-->
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
                    <ul class="sidebar-nav">
                        <li id="edt" class="hov" onclick="hle();"><a href="#tabs-1" id="text"><img class="optbtn" src="images/sidebar/Icons_editButton.svg" alt="" width="43" height="40"><p id="text1">EDIT</p></a></li>
                        <li id="stl" class="hov" onclick="hls();"><a href="#tabs-2" id="style"><img class="optbtn" src="images/sidebar/Icons_styleButton.svg" alt="" width="40" height="40" ng-click="showStyles()"><p id="text1">STYLE</p></a></li>                  
                    </ul>
                </div>
            </div>

        </div> 
                                        <script>
                                            function hle(){
                                                document.getElementById('edt').style.backgroundColor = '#fff';
                                                document.getElementById('stl').style.backgroundColor = 'transparent';
                                            }
                                            function hls(){
                                                document.getElementById('edt').style.backgroundColor = 'transparent';
                                                document.getElementById('stl').style.backgroundColor = '#fff';
                                            }
                                            $('.category_list li').click(function(){
    $('.highlight').removeClass('highlight');
$(this).addClass('highlight');

});
                                        </script>     


<!--     <script>

                // create new object crop
                // you may change the "one" variable to anything
                var one = new CROP();

                // link the .default class to the crop function
                one.init('.default');

                // load image into crop
                one.loadImg('images/logo.png');

                // send coordinates for processing
                // you may call the coordinates with the function coordinates(one);
                $(document).on('click', 'button', function() {

                        $.ajax({
                                type: "post",
                                dataType: "json",
                                url: "save.php",
                                data: $.param(coordinates(one))
                        })
                        .done(function(data) {

                                $('.example .output').remove();
                                $('.example').append('<img src="'+data.url+'" class="output" />')
                                $('.example .output').delay('4000').fadeOut('slow');

                        });

                });

        </script>-->
        <script>
                        $("#menu-toggle").click(function (e) {
                            e.preventDefault();
                        $("#wrapper").toggleClass("active");
                        });
        </script>

        <script>

                        $("#continue").click(function (){
                            var PreviewWidth=$(".preview").css("width");
                            var PreviewhHeight=$(".preview").css("height");
//                            alert($(".preview").children());

                                $.ajax({
                                   type: "POST",
                                   url: "ConvertHtmlToImageServlet",                                   
                                   data:{
                                       htmlString:$(".preview").html(),
                                       containerWidth: PreviewWidth,
                                       containerHeight: PreviewhHeight
                                   },
                                   success: function (responseText) {
                                           var image=responseText;
//                                          alert(image);
                                           document.location.href = "selectpromotesocialmedia.jsp?image="+image;

                                   }

                                   });

                                  // document.location.href = "selectpromotesocialmedia.jsp";
                       });                           

        </script> 
       
<script>    
    var selectedImageId;
    function getImageid(Id){
        selectedImageId=Id.id;
        $('.imagename').val(""+selectedImageId).trigger('change');
        
    }
     window.onload = function () {
    //get elements
    var f = 1,
            cvrt1 = document.getElementById('convert1'),
            cvrt2 = document.getElementById('convert2'),
            cvrt3 = document.getElementById('convert3'),
            cvrt4 = document.getElementById('convert4'),
            cvrt5 = document.getElementById('convert5'),
            cvrt6 = document.getElementById('convert6');

    //button click event
    cvrt1.onclick = function () {
         var image_Id= $('.imagename option:selected').val();
        
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "grayscale(100%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt2.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "textured(100%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt3.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "brightness(150%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt4.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "grayscale(100%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt5.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "sepia(100%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt6.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        if (f) {
            $("#"+image_Id).css("-webkit-filter", "Statue(100%)");
            f = 0;
        }
        else {
            $("#"+image_Id).css("-webkit-filter", "");

            f = 1;

        }
    };
};


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
                                    ctx.drawImage(img, x, y, w, h, 0, 0, width, height);
//                                  alert( img.src);
                                            // display canvas image
//                                            $('canvas').addClass('output').show().delay('4000').fadeOut('slow');
                                            // save the image to server
                                            var canvas = document.getElementById("canvas");                 
                                            var dataURL =canvas.toDataURL("image/jpeg");   
//                                            alert(dataURL);
                                            var cropped_image = {"image": "image"};                                      
                                                $.ajax({
                                                    url: global_host_address + 'CropImage',
                                                    method: 'post',
                                                    data: { image: dataURL},
                                                    success: function (responseText) {
                                                     alert(responseText);
                                                         var image_Id= $('.imagename option:selected').val();
                                                        $("#"+image_Id).css("background","url(images/temp_image/"+responseText+")").css("background-repeat","no-repeat").css("background-position", "center center");
//                                                        $("#cropper_popup").hide();
                                                         $("#cropper_popup").hide();
                                                    }
                                                });                                                                          
                                    }

                            });
                                    //  on click of .upload class, open .uploadfile (input file)
                                    // --------------------------------------------------------------------------


                                    // on input[type="file"] change
                                oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

                                        var i = 1;
                                        var id;
                                        one = new CROP();
                                    $("#selectimage").click(function(){
                                        var image_file = global_host_address + $("#image_name").val();
                       
//                                        alert(selectedImageId);
                                        $("#"+selectedImageId).css("background","url("+image_file+")").css("background-repeat","no-repeat").css("-webkit-background-size","contain");
                                        $("#imagespopup").hide();
                                    });

                                    function imageEdit() {
                                            $("#textcontainer").hide();
                                            $("#shapecontainer").hide();
                                            $("#imagecontainer").hide();
//                                        $("body :not(#cropImageContainer)").fadeTo("slow",0.4);
                                           
                                            $("#filtercontainer").show();
                                            $("#cropImageContainer").show();

                
                                        var image_file=$(".imagename option:selected").attr("name").replace("url(","").replace(")","");
//                                        alert(image_file);
                                        id = "image" + i;
                                        $("#cropper_popup").show();
//                                        $('#cropper_popup').draggable();
//                                        $("#cropper_popup").resizable();

//                                        $('.crop_image').html('<div class="default"><div class="cropMain"></div><input id=closepopup onclick=closeCropper() type="Button" value="close"/>  </div>');

                                        $('.crop_image').html('<div class="default"><div class="cropMain"></div><div class="cropSlider"></div></div>');

                                            i = i + 1;

                                        one.init('.crop_image');
                                        // load image into crop
                                        one.loadImg(image_file);
                                        $("#imagespopup").hide();

                                            }



                                    $('.uploadfile').change(function() {
                                        $("#cropper_popup").show();
//                                        $('#cropper_popup').draggable();
//                                        $("#cropper_popup").resizable();
                                       loadImageFile( $('.uploadfile').val());
                                    // resets input file
                                        $('.uploadfile').wrap('<form>').closest('form').get(0).reset();
                                        $('.uploadfile').unwrap();
                                        $("#popup").hide();
                                    });
                                    //  get input type=file IMG through base64 and send it to the cropper
                                    // --------------------------------------------------------------------------

//                                    oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

//                                    function loadImageFileFromUser(image_file) {
//
//                                    if (document.getElementById("uploadfile").files.length === 0) return;
//                                            var oFile = document.getElementById("uploadfile").files[0];
//                                            if (!rFilter.test(oFile.type)) {
//                                            return;
//                                            }
//
//                                    oFReader.readAsDataURL(oFile);
//                                    }

                            function loadImageFile() {

                            if (document.getElementById("uploadfile").files.length === 0) return;
                                    var oFile = document.getElementById("uploadfile").files[0];
                                    if (!rFilter.test(oFile.type)) {
                                    return;
                                    }
                            oFReader.readAsDataURL(oFile);
//                            alert(oFile.valueOf());
                            $("#"+selectedImageId).css("backgroung-image","url("+oFile+")");
                            }

                            oFReader.onload = function (oFREvent) {
                                        $('.crop_image').html('<div class="default"><div class="cropMain"></div><div class="cropSlider"></div></div>');                                     
                                        $('.crop_image').draggable();
                                        $(".crop_image").resizable();

                                    one = new CROP();
                                    // link the .default class to the crop function
                                    one.init('.crop_image');
                                    // load image into crop
                                    one.loadImg(oFREvent.target.result);
                            };

        </script>  

        <script>

                            //  get input type=file IMG through base64 and send it to the cropper
                            // --------------------------------------------------------------------------
                            function closeCropper(){
                                    $("#popup").hide();
                                    $("#cropper_popup").hide();
                            }

                            $("#openImageDialog").click(function(){
                                $('.default').hide();
                                $("#popup").show();
                            });
                            $("#closepopup").click(function(){
                                    $("#popup").hide();
                                    $("#cropper_popup").hide();
                            });
                            $("#UserUploadedImages").click(function(){
                                    $("#popup").hide();
                                    $("#imagespopup").show();
                            });
                            $("#closeimagespopup").click(function(){
                                    $("#imagespopup").hide();
                            });
                            $("#close_cropper_popup").click(function(){                                
                                    $("#cropper_popup").hide();
                            });


        </script>  
    </body>
</html>

