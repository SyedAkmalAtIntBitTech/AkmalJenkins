<%-- 
    Document   : socialeditor
    Created on : Jul 10, 2015, 10:03:32 AM
    Author     : intbit
--%>
<%@page import="com.controller.SqlMethods"%>
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
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
       
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
        
        <script src="js/jquery.reveal.js" type="text/javascript"></script>
        <link href="css/reveal.css" rel="stylesheet" type="text/css"/>
        <link href="css/imagefilter.css" rel="stylesheet" type="text/css"/>
        <script src="js/ajaxfileupload.js" type="text/javascript"></script>
        
        <!-- For svg --> 
        <script src="js/svg.js" type="text/javascript"></script>
        
        <style>           
a.boxclose{
    float:right;
    margin-top:8px;
    margin-right:30px;
    cursor:pointer;
    background-image: url(images/CloseIcon.svg);
    width: 25px;
    height: 25px;
}
            #mask {
  position: absolute;
  left: 0;
  top: 0;
  z-index: 9000;
  background-color:white;
  display: none;
}

#boxes .window {
  position: absolute;
  left: 0;
  top: 0;
  width: 440px;
  height: 200px;
  display: none;
  z-index: 9999;
  padding: 20px;
  border-radius: 15px;
  text-align: center;
}

#boxes #dialog {
  width: 750px;
  height: 300px;
  padding: 10px;
  background-color: white;
  font-family: 'Segoe UI Light', sans-serif;
  font-size: 15pt;
}

#popupfoot {
  font-size: 16pt;
  position: absolute;
  bottom: 0px;
  width: 250px;
  left: 250px;
}
            @font-face {
                font-family: Glyphter;
                src: url(fonts/Glyphter.ttf);
            }
            @font-face {
                font-family: Glyphter2;
                src: url(fonts/Glyphter2.ttf);
            }
            body{
                font-family: "proxima nova",sans-serif;
            }
            .cursorpointer:hover{
                cursor: pointer;
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
                top:-9px;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default
            {
               width: 20px;
               height:14px;
/*                border-radius: 20px;*/
               background-color: #FFF;
            }
            .noUi-origin .noUi-origin-lower{
     background-color:red;
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
#imageGallery ul {
      width: 450px;
  }
#imageGallery  li {
    margin-top: 10px;
    margin-right: 10px;
    display: inline-block;
    float: top;
  }
  #imageGallery  li img{
     top: 0px;
  }
  
ul::-webkit-scrollbar {
    width: 10px;
    height: 200px;
}
ul::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    border-radius: 10px;
}

ul::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 2px rgba(0,0,0,0.7);
}
    .preview{
    position: relative;
    width: 500px;
    height: 300px;
    margin-left: -165px;
    background-color: #ffffff;
    border: 1px solid #E3E4E8;
    outline: #E3E4E8 solid 16px;
    border-color: #E3E4E8;
}
            
/*   #editor::-webkit-scrollbar {
     width: 10px;
     height: 200px;
    }
    #editor::-webkit-scrollbar-track {
     -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
     border-radius: 10px;
    }

    #editor::-webkit-scrollbar-thumb {
     border-radius: 10px;
     -webkit-box-shadow: inset 0 0 2px rgba(0,0,0,0.7);
    }*/


        </style>

        <%!
            SqlMethods sql_methods = new SqlMethods();
            StringBuffer string_buffer = new StringBuffer();
            String mindbody_data_id = "";
            String logoImageName=null;
        %> 
        <%
            try {
                sql_methods.session = request.getSession();
                 user_id = (Integer)sql_methods.session.getAttribute("UID");
                 logoImageName =(String)sql_methods.session.getAttribute("ImageFileName");
                if (!request.getParameter("id").equals("null")){
                    mindbody_data_id = (String) request.getParameter("id");
                }
                else
                    mindbody_data_id = "";
//                String msg = request.getParameter("msg");
//              JOptionPane.showMessageDialog(null,"name cannot be blank "+msg);

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        %>
        
        
        
        
        
        <script>
            
            $(document).ready(function () {
                document.getElementById('edtimg').src="images/sidebar/Icons_editButton_blue_new.svg";
                         document.getElementById('edt').style.backgroundColor = '#fff';
                                                document.getElementById('stl').style.backgroundColor = 'transparent';
            $("#fontname").change(function () {
//            alert($(this).val());
                var text = $("#fontname").find('option:selected').text();
                var font_family_name = $("#fontname").val();

                var font_name = font_family_name.split('+').join(' ');
                $("#" + selectedTextareaId).css("font-family", font_name);

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
//                        alert(JSON.stringify(data.user_font_names));
                            $scope.user_preferences_colors = data.user_colors;

                            $scope.user_preferences_font_sizes = data.user_font_sizes;
                            $scope.user_preferences_font_names = data.user_font_names;
                            var i = 0;
                            var font_object;
                            var font_family_name;
                            var font_name;
                             $("#fontname").empty();
                            for (i; i<= data.user_font_names.length; i++){
                                font_object = data.user_font_names[i];
                                font_name = font_object.font_name;
                                font_family_name = font_object.font_family_name;
                                var font = font_family_name.split(",");                     
                                var google_key_word = font[0].split(' ').join('+');
                                $("#fontname").append("<option value="+google_key_word+">"+font_name+"</option>");
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
                                                document.getElementById('stlimg').src="images/sidebar/Icons_styleButton_blue_new.svg";
                                                document.getElementById('edtimg').src="images/sidebar/Icons_editButton.svg";
                                                document.getElementById('edt').style.backgroundColor = 'transparent';
                                                document.getElementById('stl').style.backgroundColor = '#fff';
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
                                    $("#tabs-1").hide();
                                    $("#tabs-2").hide();         
                                    $("#tabs-3").show().css("width", "430px").show("slide", { direction: "right" }, 1000);                                                                                                                                                                                                                                             
                                    $("#imageGallery").show();
                                    $scope.curPage = 0;
                                    $scope.pageSize = 100;
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
                     //hiding filter Container 
                     $("#filtercontainer").hide();
                         var layout_mapper_url = "";

                   if (mindbodydataId != ""){
                       layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId +'&editor_type=social&model_mapper_id='+id;
                   }else{
                       layout_mapper_url = 'GenericAnnouncementServlet?editor_type=social&model_mapper_id='+id;
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
                                                            var textcount=1;
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
                                                            var filter;
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
                                                        textcount++;
                                                        if(typeof(elementdata) === "undefined")
                                                         {
                                                            elementdata= $(this).attr("defaulttext");
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
                                                       
                                                        $(".preview").append("<div><textarea class=textAreas orginial-size='" + fontsize + "' onkeyup=textAreaKeyUp(event,'" + type + "')  onclick=getTectId(" + type + ") id=" + type + ">" + elementdata + "</textarea>");
                                                        $("#" + type).css("color", "" + fontcolor)
                                                                .css("position", "absolute")
                                                                .css("overflow", "hidden")
                                                                .css("resize", "none") 
                                                                .css("margin-left", "" + left + "px")
                                                                .css("margin-top", "" + top + "px")
                                                                .css("width", "" + width)
                                                                .css("height", "" + height)
                                                                .css("font-size", "" + fontsize)                                                             
                                                                .css("font-family", "" + font_family_name)
                                                                .css("font-style", "" + fontstyle)
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
                                                        var addThis = $("#" + type).get(0).scrollHeight - $("#" + type).height();
                                                        $("#" + type).attr("add-this",addThis);
                                                    }

                                               if (tag === "image")
                                               {                                                
                                                    if($(this).attr("filterEnable")== "true"){
                                                          filter="blur("+$(this).attr('blur')+") grayscale("+$(this).attr('grayscale')+") sepia("+$(this).attr('sepia')+") saturate("+$(this).attr('saturate')+") hue-rotate("+$(this).attr('huerotate')+") invert("+$(this).attr('invert')+") brightness("+$(this).attr('brightness')+") contrast("+$(this).attr('contrast')+")";
                                                       }
                                                    else
                                                    {
                                                         filter="drop-shadow("+$(this).attr("Drop-shadow-color")+" "+$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+")";
                                                    }
                                                   
                                                    var blendmode = $(this).attr("Blend");
                                                    var background_image = $(this).attr("background-image");
                                                    var background_color=$(this).attr("blend-background-color");
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
                                                            .css("background-position", "50% 50%")
                                                            .css("-webkit-background-size", "cover")
                                                            .css("background-color", ""+background_color)
                                                            .css("position", "absolute")
                                                            .css("webkit-filter",""+ filter);
                                                    }

                                                    if (tag === "logo")
                                                    {
                                                    if($(this).attr("filterEnable")== "true"){
                                                          filter="blur("+$(this).attr('blur')+") grayscale("+$(this).attr('grayscale')+") sepia("+$(this).attr('sepia')+") saturate("+$(this).attr('saturate')+") hue-rotate("+$(this).attr('huerotate')+") invert("+$(this).attr('invert')+") brightness("+$(this).attr('brightness')+") contrast("+$(this).attr('contrast')+")";
                                                       }
                                                    else
                                                    {
                                                         filter="drop-shadow("+$(this).attr("Drop-shadow-color")+" "+$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+")";
                                                    }
                                                     var userId=$("#userid").val();
                                                    var userLogonmae = $("#userlogo").val();
                                                    var blendmode = $(this).attr("Blend");
                                                    var background_color=$(this).attr("blend-background-color");
                                                    $(".preview").append("<div onclick=getImageid(" + type + ") id=" + type + " ></div>");
                                                    $("#" + type)
                                                            .css("color", "" + fontcolor)
                                                            .css("margin-left", "" + left + "px")
                                                            .css("margin-top", "" + top + "px")
                                                            .css("background-blend-mode", "" + blendmode)
                                                            .css("opacity", "" + opacity)
                                                            .css("width", "" + width)
                                                            .css("height", "" + height)
                                                            .css("background", "url('/BrndBot/DownloadImage?image_type=USER_LOGO&user_id="+userId+"&image_name="+userLogonmae+"')")
                                                            .css("background-repeat", "no-repeat")
                                                            .css("background-position", "center center")
                                                            .css("background-color", ""+ background_color)
                                                            .css("background-size","contain")
                                                            .css("position", "absolute")
                                                            .css("webkit-filter",""+ filter); 
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
                                                        $(".blockname").append("<option value="+type+">Shape "+blockcount+"</option>")
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
                                                    if (tag === "svg")
                                                    {

                                                        var colorName = $(this).attr("color-name");
                                                        var borderRadius = $(this).attr("border-radius");
                                                        var backgroundcolor;

                                                        $(".blockname").append("<option value=" + type + ">Shape " + blockcount + "</option>");
                                                        blockcount++;

                                                        for (var i = 1; i <= 6; i++)
                                                        {
                                                            if (colorName === "Color-" + i)
                                                            {
                                                                backgroundcolor = $("#shapecolorbox" + i).css("background-color");
                                                            }

                                                        }
                                                        var width = $(this).attr("width");
                                                        var height = $(this).attr("height");
                                                        var drop_shadow = $(this).attr("Drop-shadow-color");
                                                        var h_shadow = $(this).attr("H-shadow");
                                                        var v_shadow = $(this).attr("V-shadow");
                                                        var Blur = $(this).attr("blur");
                                                        var filename = $(this).attr("filename");
                                                        $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                                                        var draw = SVG(type);
                                                        $("#" + type)
                                                                //.css("background-color", "" + backgroundcolor)
                                                                .css("margin-left", "" + left + "px")
                                                                .css("margin-top", "" + top + "px")
                                                                .css("width", "" + width)
                                                                //.css("border-radius", "" + borderRadius)
                                                                .css("position", "absolute")
                                                                .css("height", "" + height)
                                                                //.css("-webkit-filter", "drop-shadow(" + drop_shadow + " " + h_shadow + " " + v_shadow + " " + Blur + ")")
                                                                //.css("opacity", "" + opacity)
                                                                ;
                                                        $.get('/BrndBot/DownloadSVGServlet?file_name='+filename, function(data) {
                                                        var svg1 = draw.svg(data);
                                                        var realsvg = svg1.last();  

                                                        svg1.attr("viewBox",realsvg.attr("viewBox"));
                                                        svg1.attr("enable-background",realsvg.attr("enable-background"));
                                                        svg1.attr("x",realsvg.attr("x"));
                                                        svg1.attr("y",realsvg.attr("y"));
                                                        svg1.attr("xml:space",realsvg.attr("xml:space"));
                                                        svg1.attr("width",width);
                                                        svg1.attr("height",height);
                                                        svg1.opacity(opacity);
                                                        svg1.style("fill",backgroundcolor);
                                                        svg1.style("-webkit-filter", "drop-shadow(" + drop_shadow + " " + h_shadow + " " + v_shadow + " " + Blur + ")");
                                                        svg1.each(function(i, children) {
                                                            this.style("fill",backgroundcolor);
                                                            this.opacity(opacity);
                                                        }, true); 
                                                       },"html");


                                                    }

                                                    } );
                                                    if(count==1 ){$("#imagecontainer").hide();}                                                   
                                                    if(blockcount==1){$("#shapecontainer").hide();}
                                                    if(textcount==1){$("#textcontainer").hide();}
                                                    if(count!=1){$("#imagecontainer").show();}
                                                    if(blockcount!=1){$("#shapecontainer").show();}
                                                    if(textcount!=1){$("#textcontainer").show();}
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
        
        <script src="js/crop.js" type="text/javascript"></script>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body ng-app="myapp">
        <input type="hidden" id='userlogo' value=<%= logoImageName%>>
        <input type="hidden" id='userid' value=<%= user_id%>>
        <script src="js/socialeditor.js" type="text/javascript"></script>
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
                            <a href="#" data-reveal-id="fileupload_popup" class="fileclick" style="display:none;">Click Me For A Modal</a>
                            <div id="fileupload_popup" class="reveal-modal" style="left:90%;">
                                <a class="close-reveal-modal">abc&#215;</a>
                                <center>
                                <input id="myFile" type="file" name="myFile">
                                <input type="button" id="upload" style="margin-left: 35%;" class="button button--moema button--text-thick button--text-upper button--size-s" value="upload">
                                <input type="button" id="image1" ng-click="showImages()" value="image1" style="display: none;"><br />
                                </center>
                            </div>

                            <p class="edit SP1">EDIT THIS POST </p>&nbsp;&nbsp; <p id="edtgb" class="BT2" style="position:relative;top: -12px;"><a href="selectpromotemedia.jsp">go back</a></p>
                            <div class="preview" style="zoom: 0.5;position:relative;left:180px;top:-200px;">


                                <!--  {{mindbody_data}}-->
                                <!--
                                        NOTE: To change the aspect ratio, look in crop.css
                                        The class 'default' links the div to the init(); function
                                --> 
                                
                            </div>
                            <div class="span3 col-md-offset-0" >
                                <input id="continue" class="button button--moema button--text-upper button--size-s" type="button" value="CONTINUE" style="margin-top: -7%;"><br><br>
                                <script>
                                    function showImageName(user_id, image_name){
                                        var image_path = "DownloadImage?image_type=GALLERY&image_name="+image_name+"&user_id="+user_id+"";   
                                        var img = encodeURI(global_host_address + image_path);
                                                    $("#" + $(".imagename").val()).css("background", "url("+img+")").css("background-repeat", "no-repeat").css("background-position", "50% 50%").css("-webkit-background-size", "cover");
                                                    $("#imagespopup").hide(); 
                                                    $(".imagename option:selected").attr("name","url(" + global_host_address +""+image_path+ ")");
                                                    $("#tabs-1").show();
                                                    $("#tabs-2").hide();
                                                    $("#tabs-3").hide();                                                 
                                    }
                                </script>
                                
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
                            
                            <a href="#" data-reveal-id="cropper_popup1" class="clickthis" style="display:none;">Click Me For A Modal</a>
                            <div id="cropper_popup1" class="reveal-modal" name="cropper_popup" style="top:10px;left:90%;">
                                <a class="close-reveal-modal">&#215;</a>
                                <div class="imagecropper_header" style="text-align: center;">
                                    
                                    <h3 class="imagecropper_title">Cropping image</h3>

                                </div>
                                <div class="crop_image">
<!--                                        <button class="cropButton">Crop</button>-->


                
<!--                                <input id=closepopup onclick=closeCropper() type="Button" value="close"/>-->
                                </div>   
                                
                                    <input type="button" class="imagecropper_no" onclick="closeCropper()" value="Skip"/>
                                    <button class="imagecropper_ok cropButton">Crop</button>
                               
                            </div>
  
                        </div>

                        <!--        editor container      -->
                        <div class="col-md-3 col-md-offset-1" >
                            <div class="well lead editor" id="editor" style="height:550px;top:35px;left:45px;overflow-y:scroll;width:366px;overflow-x:hidden;border:1px #FFF solid;box-shadow: inset 0 1px 1px rgba(0,0,0,0);">                       
                                <ul>
                                    <li id="tabs-1">
                                        <div id="textcontainer">
                                            <p id="text3" class="SS2">TEXT</p> 
                                            <ul id="textmodification">
                                                <li  style="position:relative;left:-9px;"><p id="editorheadere" class="editorheadere SS1">font color</p>
                                                    <div class="blankcolor-box1 ptr" id="picker" ></div>
                                                    
                                                  
                                                    
                                                </li>
                                                <!--                                                <li><p id="editorheadere">font size</p><div class="glyphicon glyphicon-font"><br></div></li>
                                                                                                <li><p id="editorheadere">font style</p><select></select></li>-->
                                                <li>
                                                    <p id="editorheadere" class="editorheadere SS1">font size</p>
<!--                                                    <select  id="fontsize" style="margin: 2px;width:80px; font-size: 15px;color: #3f4042;background-color: #ccc;border-radius:5px; ">
                                                        <option style="background:#FFF;" ng-repeat ="sizes in user_preferences_font_sizes" value="{{sizes}}">{{sizes}}</option>
                                                    </select> -->
<!--                                                <div style="width:40px;height:30px;text-align:center">
                                                    <div class="cursorpointer" id="minusFont" style="margin-top:5px;width:20px;height:30px;float:left;font-size: 16px; color: #5E5E5E">A</div>
                                                    <div class="cursorpointer" id="plusFont" style="width:20px;height:30px;float:left;font-size: 25px; color: #5E5E5E">A</div>
                                                </div>-->
                                                    <img id="minusFont" src="images/LittleA.svg" class="cursorpointer" width="20px"  height="20px" alt=""/> <img src="images/BigA.svg" width="25px"  height="25px" class="cursorpointer" id="plusFont" alt=""/>
                                                </li>

                                                <li style="width:120px;">
                                                    <p id="editorheadere" class="editorheadere SS1">font style</p>
                                                    <select id="fontname" class="LE1 editordropdown">
<!--                                                        <option style="background:#FFF;" ng-repeat ="names in user_preferences_font_names" value="{{ names.font_family_name}}">{{ names.font_name}} </option>-->

                                                    </select>
                                                </li>
                                                <li> 
                                                    <ul id="pickColorForText" style="display:none;left:-13px;position:relative;margin-top:-80px;">
                                                        <li><p class="editpal">your palette</p></li>
                                                        <li><p class="editcus custom-color-box-text ptr" style="margin-left:120px;position:relative;">custom</p></li>
                                                        <li id="fcolcontainer">
                                                            <ul id="colorpalette" style="position:relative;left:0px;">
                                                                   <li><div class="blankcolor-box-text ptr" id="textcolorbox1" style="background-color: {{user_preferences_colors.color1}}"></div></li>
                                                                    <li><div class="blankcolor-box-text ptr" id="textcolorbox2" style="background-color: {{user_preferences_colors.color2}}"></div></li>
                                                                    <li><div class="blankcolor-box-text ptr" id="textcolorbox3" style="background-color: {{user_preferences_colors.color3}}"></div></li>
                                                                    <li><div class="blankcolor-box-text ptr" id="textcolorbox4" style="background-color: {{user_preferences_colors.color4}}"></div></li>
                                                                    <li> <div class="blankcolor-box-text ptr" id="textcolorbox5" style="background-color: {{user_preferences_colors.color5}}"></div></li>
                                                                    <li><div class="blankcolor-box-text ptr" id="textcolorbox6" style="background-color: {{user_preferences_colors.color6}}"></div></li>
                                                                </ul>
                                                            </li>
                                                            
                                                        </ul>
                                                </li>
                                               <li style="left:-20px;top:-2px;"><div class="cursorpointer" id="hidealignbutton"><img src="images/LineOptionButton.svg" height="40px" width="24px;"></div></li>
                                               <li style="left:-20px;"><div class="alignButton cursorpointer" id="justify" style="font-family: Glyphter2;">j</div></li>
                                               <li style="left:-20px;"><div class="alignButton cursorpointer" id="left" style="font-family: Glyphter2;">B</div></li>
                                               <li style="left:-20px;"><div class="alignButton cursorpointer" id="center" style="font-family: Glyphter2;">C</div></li>
                                               <li style="left:-20px;"><div class="alignButton cursorpointer" id="right" style="font-family: Glyphter2;">D</div></li>
                                               <li style="left:-20px;"><img class="cursorpointer" id="plus" src="images/Plus.svg"  width="15px;" style="position:relative;top:-5px;" ><img class="cursorpointer" id="minus" src="images/Minus.svg"  width="15px;" style="position:relative;top:8px;left:-15px;"></li>
                                               <li style="left:-50px;"><img class="cursorpointer" id="lineHeightImage" src='images/LineHeightButton.svg' width="25px"></li>
                                            </ul>

                                        </div>

                                        <input type="hidden" id="mindbodydata" name="mindbodydata" value='<%= mindbody_data_id %>'>
                                        <input type="hidden" id='clickid'>

                                        <div id="shapecontainer">
                                            <p  id="text3" class="SS2">SHAPES</p>
                                            <ul id="shapemodificatoin">

                                                        <li>
                                                            <select class="blockname LE1 editordropdown" id="editorhead">
                                                                <option value="select">Select</option>
                                                            </select>
                                                        </li>
                                                <li><div class="headblankcolor-box ptr" id="selectedshapecolorbox" style="left:-30px;background-color: {{user_preferences_colors.color1}}"></div></li><br>
                                                <li><ul id="openCustomColor">
                                                <li><p class="editpal">your palette</p></li>
                                                <li id="colcontainer">
                                                    <ul id="colorpalette">
                                                       <li><div class="blankcolor-box ptr" id="shapecolorbox1" style="left:-14px;background-color: {{user_preferences_colors.color1}}"></div></li>
                                                        <li><div class="blankcolor-box ptr" id="shapecolorbox2" style="background-color: {{user_preferences_colors.color2}}"></div></li>
                                                        <li><div class="blankcolor-box ptr" id="shapecolorbox3" style="background-color: {{user_preferences_colors.color3}}"></div></li>
                                                        <li><div class="blankcolor-box ptr" id="shapecolorbox4" style="background-color: {{user_preferences_colors.color4}}"></div></li>
                                                        <li> <div class="blankcolor-box ptr" id="shapecolorbox5" style="background-color: {{user_preferences_colors.color5}}"></div></li>
                                                        <li><div class="blankcolor-box ptr" id="shapecolorbox6" style="background-color: {{user_preferences_colors.color6}}"></div></li>
                                                    </ul>
                                                </li>
                                                
                                                <li><p class="editpal custom-color-box ptr" style="margin-right: 120px;">custom</p></li>
                                                <li><p class="editpal" id="blockopacity">opacity</p><div class="ptr" id="slider"></div></li>
                                                    </ul>   
                                            </ul>
                                        </div>

                                        <div id="imagecontainer">
                                            <p  id="text3" class="SS2">IMAGE</p>
                                            <ul id="imagemodification">
                                                <li>
                                                    <select class="imagename LE1 editordropdown" id="editorhead">
                                                        <option value="select">Select</option>
                                                    </select>
                                                </li>
                                                <li><label id="openImageDialog" class="btn editorheadere newupload"  ng-click="showImages()" >change</label></li>
                                                <li><p  class="btn editorheadere" onclick="showfilter()">edit</p></li>
                                                <li></li>
                                            </ul>
                                        </div>

                                        <div id="filtercontainer" style="display: none">
                                            <p>IMAGE FILTER</p>
                                            <ul id="filterImageList">
                                                <li><img class="imageFilter ptr " id="convert1" src="images/Blackandwhite.jpg" alt="" ><p class="filtername">Still</p> </li>
                                                <li><img class="imageFilter ptr" id="convert2" src="images/Blackandwhite.jpg" alt=""> <p class="filtername">Peace</p></li>
                                                <li><img class="imageFilter ptr" id="convert3" src="images/Blackandwhite.jpg" alt=""> <p class="filtername">Sunrise</p></li>
                                                <li><img class="imageFilter ptr" id="convert4" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Strength</p> </li>
                                                <li><img class="imageFilter ptr" id="convert5" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Vivid</p> </li>
                                                <li><img class="imageFilter ptr" id="convert6" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Intense</p> </li>
<!--                                                <li><img class="imageFilter" id="convert7" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Vibrant</p> </li>
                                                <li><img class="imageFilter" id="convert8" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Vintage</p> </li>
                                                <li><img class="imageFilter" id="convert9" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Shade</p> </li>
                                                <li><img class="imageFilter" id="convert10" src="images/Blackandwhite.jpg" alt=""><p class="filtername">Fade</p> </li>-->
                                            </ul>
                                           <p  class="btn" onclick="imageEdit()">CROP</p>
                                            <p  class="btn" onclick="saveImageEdit()">DONE</p>
                                        </div>
                                        <div id="cropImageContainer" style="display: none">
                                                    <!--
                                                            NOTE: To change the aspect ratio, look in crop.css
                                                            The class 'default' links the div to the innit(); function
                                                    -->

                                                <br><br>
                                        </div>

                                    </li>
                                    <li id="tabs-2">
                                        <div id="stylecontainer">
                                            
                                            <div>

                                                 <p id="text3" class="SS2">SELECT A STYLE</p>
                                                <div style="height:500px;">
                                                    <ul>
                                                        <!--{{datalists}}-->
                                                        <li class="paginationclass" ng-repeat="styles in datalists">
                                                            <div>
                                                                <img id="{{styles.id}}" class="img-responsive lookchooser5 ptr" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{styles.image_file_name}}"  onclick="showText('{{styles.id}}','{{styles.layout_file_name}}')" width="275"  />
                                                                <!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                                            </div> 
                                                            <div><p id=''></p></div>
                                                            <div></div><p style="height:5px;">&nbsp;</p>
                                                        </li>
                                                    </ul>

                                                </div>

                                            </div>

                                        </div>

                                    </li>
                                    <li id="tabs-3">
                                          <ul id="imageGallery" style="height: 500px;width: 300px;position: relative;right: 80px;left:0px;">
                                            <p class="SH1">PLEASE SELECT AN IMAGE FROM THE GALLERY</p>
                                             <a class="boxclose" id="boxclose"></a>
                                               <p class="BT2 ptr" id="galleryupload">upload image</p>
                                                <li class="paginationclass" ng-repeat="images in datalistimages| pagination: curPage * pageSize | limitTo: pageSize">                                                          
                                                          <img id="{{images.id}}" class="img-responsive lookchooser5 ptr" src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}"  onclick="showImageName('{{images.user_id}}','{{images.image_name}}')" width="200px"/>                                                            
                                                </li>
                                            </ul>
<!--                                               <input id="closeimagespopup" type="Button" value="close"/>  -->
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
                        <li id="edt" class="hov"  onclick="hle();"><a href="#tabs-1" id="text"><img id="edtimg" class="optbtn" src="images/sidebar/Icons_editButton.svg" alt="" width="43" height="40"  ><p id="text1" >EDIT</p></a></li>
                        <li id="stl" class="hov" ng-click="showStyles()"><a href="#tabs-2" id="style"><img id="stlimg" class="optbtn" src="images/sidebar/Icons_styleButton.svg" alt="" width="40" height="40"><p id="text1">STYLE</p></a></li>                  
                    </ul>
                </div>
            </div>
                                        <div id="boxes">
                                            <div id="dialog" class="window"><br><br>
                                              <img id="loadingGif" src="images/YogaLoadingGif.gif" />
                                            </div>
                                            <div id="mask"></div>
                                        </div>
        </div>
    <script>
          function fadepopup() {	
                var id = '#dialog';

                //Get the screen height and width
                var maskHeight = $(document).height();
                var maskWidth = $(window).width();

                //Set heigth and width to mask to fill up the whole screen
                $('#mask').css({'width':maskWidth,'height':maskHeight});

                //transition effect
                $('#mask').fadeIn(500);	
                $('#mask').fadeTo("slow",0.95);	

                //Get the window height and width
                var winH = $(window).height();
                var winW = $(window).width();

                //Set the popup window to center
                $(id).css('top',  winH/2-$(id).height()/2);
                $(id).css('left', winW/2-$(id).width()/2);

                //transition effect
                $(id).fadeIn(2000); 	

                //if close button is clicked
                $('.window .close').click(function (e) {
                //Cancel the link behavior
                e.preventDefault();

                $('#mask').hide();
                $('.window').hide();
                });

                //if mask is clicked
                $('#mask').click(function () {
                $(this).hide();
                $('.window').hide();
                });

                }
        </script>
                                        <script>
                                            function act(){
                                                document.getElementById('edt').style.backgroundColor = '#555';
                                            }
    
                                            function hle(){
                                                 document.getElementById('edtimg').src="images/sidebar/Icons_editButton_blue_new.svg";
                                                 document.getElementById('stlimg').src="images/sidebar/Icons_styleButton.svg";
                                                   document.getElementById('edt').style.backgroundColor = '#fff';
                                                document.getElementById('stl').style.backgroundColor = 'transparent';
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
                            fadepopup();
//                            $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","300px").css("left","500px");
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
                                           $('#loadingGif').remove();
                                           var image=responseText;
//                                          alert(image);
                                           document.location.href = "selectpromotesocialmedia.jsp?image="+image;
                                                            $('#mask').hide();
                                                        $('.window').hide();
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
         $("#"+image_Id).removeClass("ig-valencia")
                       .removeClass("ig-toaster")
                       .removeClass("ig-sutro")
                       .removeClass("ig-kelvin")
                       .removeClass("ig-brannan");
        $("#"+image_Id).toggleClass("ig-willow");
//        if (f) {
//            $("#"+image_Id).css("-webkit-filter", "grayscale(100%)");
//            f = 0;
//        }
//        else {
//            $("#"+image_Id).css("-webkit-filter", "");
//
//            f = 1;
//
//        }
    };
    cvrt2.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        $("#"+image_Id).removeClass("ig-toaster")
                       .removeClass("ig-sutro")
                       .removeClass("ig-kelvin")
                       .removeClass("ig-brannan")
                       .removeClass("ig-willow");
 
        $("#"+image_Id).toggleClass("ig-valencia");
    };
    cvrt3.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        $("#"+image_Id).removeClass("ig-valencia")
                       .removeClass("ig-sutro")
                       .removeClass("ig-kelvin")
                       .removeClass("ig-brannan")
                       .removeClass("ig-willow");
               
        $("#"+image_Id).toggleClass("ig-toaster");
    };
    cvrt4.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        $("#"+image_Id).removeClass("ig-valencia")
                       .removeClass("ig-toaster")
                       .removeClass("ig-kelvin")
                       .removeClass("ig-brannan")
                       .removeClass("ig-willow");
        $("#"+image_Id).toggleClass("ig-sutro");
    };
    cvrt5.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        $("#"+image_Id).removeClass("ig-valencia")
                       .removeClass("ig-toaster")
                       .removeClass("ig-sutro")
                       .removeClass("ig-brannan")
                       .removeClass("ig-willow");
        $("#"+image_Id).toggleClass("ig-kelvin");
    };
    cvrt6.onclick = function () {
        var image_Id= $('.imagename option:selected').val();
        $("#"+image_Id).removeClass("ig-valencia")
                       .removeClass("ig-toaster")
                       .removeClass("ig-sutro")
                       .removeClass("ig-kelvin")
                       .removeClass("ig-willow");
        $("#"+image_Id).toggleClass("ig-brannan");
    };
    $(".boxclose").click(function (){
        $("#imageGallery").hide();
        $("#tabs-1").css("display","block");
         $("#imagecontainerZ\n\
 ").css("display","block");
        
    });
    
};
function showfilter(){
     $("#textcontainer").hide();
     $("#shapecontainer").hide();
     $("#imagecontainer").hide();
     $("#filtercontainer").show();
}

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
                                    $('.default').hide();
                                    $("#cropper_popup1").hide();
                                    // grab width and height of .crop-img for canvas
                                    var width = $('.crop-container').width(), // new image width
                                      height = $('.crop-container').height(); // new image height

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
                                            $('canvas').addClass('output').hide().delay('4000').fadeOut('slow');
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
                                                         var image_Id= $('.imagename option:selected').val();
                                                        $("#"+image_Id).css("background","url(images/temp_image/"+responseText+")").css("background-repeat","no-repeat").css("background-position", "50% 50%").css("-webkit-background-size", "cover");
//                                                        $("#cropper_popup").hide();
                                                         //$("#cropper_popup").hide();
                                                         $(".close-reveal-modal").click();
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
                                        $("#"+selectedImageId).css("background","url("+image_file+")").css("background-repeat","no-repeat").css("background-position", "50% 50%").css("-webkit-background-size", "cover");
                                        $("#imagespopup").hide();
                                    });

                                    function imageEdit() {
                                            $("#cropper_popup1").show();
                                            $("#textcontainer").hide();
                                            $("#shapecontainer").hide();
                                            $("#imagecontainer").hide();
//                                        $("body :not(#cropImageContainer)").fadeTo("slow",0.4);
                                           var imageId=$(".imagename option:selected").val();
                                           var imageWidth=$("#"+imageId).css("width").replace("px","");
                                           var imageHeight=$("#"+imageId).css("height").replace("px","");
                                           $("#crompIageContainer").show();
                                           
                                                                                  
                                        var image_file=$(".imagename option:selected").attr("name").replace("url(","").replace(")","");
//                                        alert(image_file);
                                        id = "image" + i;
                                       // $("#cropper_popup").show();
                                        $(".clickthis").click();
//                                        $('#cropper_popup').draggable();
//                                        $("#cropper_popup").resizable();

//                                        $('.crop_image').html('<div class="default"><div class="cropMain"></div><input id=closepopup onclick=closeCropper() type="Button" value="close"/>  </div>');

                                        $('.crop_image').html('<div class="default"><div class="cropMain"></div><div class="cropSlider"></div></div>');

                                            i = i + 1;

                                        one.init('.crop_image');
                                        // load image into crop
                                        one.loadImg(image_file);
                                        $("#imagespopup").hide();
                                        if(imageWidth >350 && imageWidth <=700){
                                            $(".default .cropMain").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px").css("zoom","0.7");
                                            $(".crop-container").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px");
                                         }
                                         else if(imageWidth >700 &&  imageWidth <=1050){
                                         $(".default .cropMain").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px").css("zoom","0.5");
                                           $(".crop-container").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px"); 
                                            }
                                        else if(imageWidth >1050 && imageWidth <=1400){
                                         $(".default .cropMain").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px").css("zoom","0.34");
                                          $(".crop-container").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px");  
                                        }
                                        else if(imageWidth >1400 && imageWidth <=1800){
                                         $(".default .cropMain").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px").css("zoom","0.25");
                                          $(".crop-container").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px");  
                                        }    
                                        else if(imageWidth >1800){
                                         $(".default .cropMain").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px").css("zoom","0.2");
                                          $(".crop-container").css("width",""+imageWidth+"px").css("height",""+imageHeight+"px");  
                                        }                                            
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
                                    //$("#cropper_popup").hide();
                                    $(".close-reveal-modal").click();
                            }

                            $("#openImageDialog").click(function(){
                                $('.default').hide();
                                $("#popup").show();
                            });
                            $("#closepopup").click(function(){
                                    $("#popup").hide();
                                    //$("#cropper_popup").hide();
                                    $(".close-reveal-modal").click();
                            });
                            $("#UserUploadedImages").click(function(){
                                    $("#popup").hide();
                                    $("#imagespopup").show();
                            });
                            $("#closeimagespopup").click(function(){
                                    $("#imagespopup").hide();
                            });
                            $("#close_cropper_popup").click(function(){                                
                                    //$("#cropper_popup").hide();
                                    $(".close-reveal-modal").click();
                            });


        </script>  
    </body>
</html>

