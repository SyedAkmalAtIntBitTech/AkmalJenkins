<%-- 
    Document   : emaileditornew
    Created on : Oct 21, 2015, 9:13:55 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Editor</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
        <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

        <link href="css/froala_editor.css" rel="stylesheet" type="text/css"/>
        <link href="css/froala_style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/plugins/code_view.css">
        <link rel="stylesheet" href="css/plugins/colors.css">
        <link rel="stylesheet" href="css/plugins/emoticons.css">
        <link rel="stylesheet" href="css/plugins/image_manager.css">
        <link rel="stylesheet" href="css/plugins/image.css">
        <link rel="stylesheet" href="css/plugins/line_breaker.css">
        <link rel="stylesheet" href="css/plugins/table.css">
        <link rel="stylesheet" href="css/plugins/char_counter.css">
        <link rel="stylesheet" href="css/plugins/video.css">
        <link rel="stylesheet" href="css/plugins/fullscreen.css">
        <link rel="stylesheet" href="css/plugins/file.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">
    
            <%!            SqlMethods sql_methods = new SqlMethods();
            StringBuffer string_buffer = new StringBuffer();
            String mindbody_data_id = "";
            String logoImageName = null;
        %> 
        <%            try {
                sql_methods.session = request.getSession();
                user_id = (Integer) sql_methods.session.getAttribute("UID");
                logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
                if (!request.getParameter("id").equals("null")) {
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
            var rendomIframeFilename="";
                    $(document).ready(function () {
                        rendomIframeFilename=event.timeStamp;
//                        alert(rendomIframeFilename);
                   $("#loadingGif").hide();
                   $(".boxclose").click(function (){
                        $("#tabs-4").hide();
//                       $("#tabs-4").css("width", "830px").css("position","fixed").css("margin-left","-460px").hide("slide", { direction: "right" }, 1000);;
                       $("#tabs-1").show();

                   });
                   $(".boxclosegallery").click(function (){
                       $("#imageGallery").hide();
                       $("#tabs-1").show();
                   });
                    document.getElementById('edtimg').src = "images/sidebar/Icons_editButton_blue_new.svg";
                    document.getElementById('edt').style.backgroundColor = '#fff';
                    document.getElementById('stl').style.backgroundColor = 'transparent';
                    document.getElementById('blk').style.backgroundColor = 'transparent';
                    var numitems = $("#imageGallery li").length;
                    $("ul#imageGallery").css("column-count", numitems / 2);
                   
        $("#fontname").change(function () {
                     var text = $("#fontname").find('option:selected').text();
                    var font_family_name = $("#fontname").val();
                    var font_name = font_family_name.split('+').join(' ');
                $("#" + selectedTextareaId).css("font-family", font_name);
            });
            });</script>

        <script>
                    
                    
                    var jsondata;
                    var selectedDivId;
                    var block_clicked = "false";
                    var block_id = "0";
                    var blockIdSelected = "defaultblock1";
                    var mindbodydataId = $("#mindbodydata").val();
                    var temp_style_id;
                    var temp_style_layout;
                    var temp_block_id;
                    var temp_mind_body_query;
                    //$("#previewpopup").hide();

$(document).ready(function() {
            $('#continueblock').prop('disabled', true);
                    $("#preview").click(function(){
            $.ajax({
            url: getHost() + "PreviewServlet",
                    method: "post",
                    data:{
                        htmlString: $(".dataForEmail").html(),
                        iframeName: rendomIframeFilename
                    },
                    success: function (responseText) {

                    //show popup showing
                    $("#previewcontent").empty();
                            $("#previewcontent").append(responseText);
                            //$("#previewpopup").show();
                            $(".clickpreview").click();
                    }
            });
            });
//                            $("#closepreview").click(function(){
//                                $("#previewpopup").hide();
//                            });

            });
                    angular.module("myapp", [])

                    .controller("MyController", function($scope, $http) {

                    $http({
                    method : 'GET',
                            url : 'GetUserPreferences'
                    }).success(function(data, status, headers, config) {
                    $scope.user_preferences_colors = data.user_colors;
                            $scope.user_preferences_font_sizes = data.user_font_sizes;
                            $scope.user_preferences_font_names = data.user_font_names;
                            var i = 0;
                            var font_object;
                            var font_family_name;
                            var font_name;
                             $("#fontname").empty();
                            for (i; i <= data.user_font_names.length; i++){
                            font_object = data.user_font_names[i];
                            font_name = font_object.font_name;
                            font_family_name = font_object.font_family_name;
                            var font = font_family_name.split(",");
                            var google_key_word = font[0].split(' ').join('+');
                            $("#fontname").append("<option value="+google_key_word+">"+font_name+"</option>");
                            var ss = document.createElement("link");
                            ss.type = "text/css";
                            ss.rel = "stylesheet";
                            ss.href = "https://fonts.googleapis.com/css?family=" + google_key_word;
                            document.getElementsByTagName("head")[0].appendChild(ss);
                            var font_path = global_host_address + "DownloadFonts?file_name=" + font[1];
                            var styles = "@font-face {" +
                            "font-family:" + font_name + ";" +
                            "src: url(" + font_path + ");"
                            $('<style type="text/css">' + styles + '</style>').appendTo(document.head);
                    }
                    if (data === error){
                         alert(data);
                    }
                    }).error(function(data, status, headers, config) {
                             alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });
                            $scope.showStylesAfterData = function(){

                            blockIdSelected = $(selectedBlockId).attr("id").toString();
                                    var arr = blockIdSelected.split('SSS');
                                    block_id = arr[0].replace("block", "");
                            };
                            $scope.showStyles = function(){
                                alert("style click");
                                    var queryurl;
                                    $scope.curPage = 0;
                                    $scope.pageSize = 2;
                                    if (block_clicked == "true" || blockIdSelected != "defaultblock1")
                            {
                            queryurl = 'GetLayoutStyles?editorType=email&query=block&block_id=' + block_id;
                            }
                            else
                            {
                            queryurl = 'GetLayoutStyles?editorType=email';
                            }

                            $http({
                            method : 'GET',
                                    url : queryurl
                            }).success(function(data, status, headers, config) {
                            $scope.datalistsstyles = data;
                                    document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton_blue_new.svg";
                                    document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton.svg";
                                    document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
                                    document.getElementById('edt').style.backgroundColor = 'transparent';
                                    document.getElementById('stl').style.backgroundColor = '#fff';
                                    document.getElementById('blk').style.backgroundColor = 'transparent';
                                    $scope.numberOfPages = function() {
                                    return Math.ceil($scope.datalistsstyles.length / $scope.pageSize);
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
                            $scope.showBlocks = function(){
                            $('body').scrollTop(0);
                                    $scope.curPage = 0;
                                    $scope.pageSize = 2;
                                    $http({
                                    method : 'GET',
                                            url : 'GetBlocks'
                                    }).success(function(data, status, headers, config) {
                            $scope.datalists = data;
                                    document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton.svg";
                                    document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton_blue_new.svg";
                                    document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
                                    document.getElementById('edt').style.backgroundColor = 'transparent';
                                    document.getElementById('stl').style.backgroundColor = 'transparent';
                                    document.getElementById('blk').style.backgroundColor = '#fff';
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
                            $scope.showImageOfBlock = function(id, mind_body_query){
                            $http.get('GetLayoutStyles?editorType=email&query=block&block_id=' + id).success(function(data, status){
                            var jsondataDefault = data;
                                    var allLayoutFilename = [];
                                    $(jsondataDefault).each(function (i, val) {
                            var i = 0;
                                    $.each(val, function (k, v) {
                                    allLayoutFilename[i] = v;
                                            i++;
                                    });
                            });
                                    $("#" + id).attr('src', '/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=' + allLayoutFilename[2]);
                                    $("#" + id).attr('onclick', "showSomething('" + id + "','" + allLayoutFilename[0] + "','" + allLayoutFilename[1] + "','" + mind_body_query + "')");
                            }).error();
                            };
                            $scope.showDataTemp = function(){
                                
                            $scope.showData(temp_block_id, temp_mind_body_query);
                            }
                    $scope.showData = function(id, mind_body_query){

                            block_clicked = "true";
                            blockIdSelected = "";
                            block_id = id;
                            if (mind_body_query == "null")
                            {
                                  mindbodydataId = "0";
                                    //$scope.showStyles();
                                    showText(temp_style_id, temp_style_layout);
                                    $("#tabs-1").show();
                                    $("#filtercontainer").hide();
                                    $("#tabs-2").hide();
                                    $("#tabs-3").hide();
                                    $("#tabs-4").hide();
                                    $("#tabs-5").hide();
                            }
                    else
                    {
                            $("#tabs-1").hide();
                            $("#tabs-2").hide();
                            $("#tabs-3").hide();
                            $("#tabs-5").hide();
                         $("#tabs-4").show().css("width", "830px").css("height", "680px").css("position","fixed").css("margin-left","-460px").css("top","45px");
                         $("#loadingGifformindbody").show();
                          $scope.curPage = 0;
                          $scope.pageSize = 4;
                            $http({
                            method : 'GET',
                                    url : 'MindBodyDataServlet?mindbody_query=' + mind_body_query
                            }).success(function(data, status, headers, config) {

                            $scope.datalists = data;
                            $scope.numberOfPages = function() {
                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                            };
                            if (data === error){
                    alert(data);
                    }

//                            $("#loadingGif").hide();
//                            $("#tabs-4").css("width", "430px").show("slide", { direction: "right" }, 1000);

                            $("#loadingGifformindbody").hide();
                            $("#tabs-4").css("width", "830px").css("position","fixed").css("margin-left","-460px").css("top","45px").show("slide", { direction: "right" }, 1000);
                            
                    }).error(function(data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });
                    }
                    };
                            $scope.select_category_details = function(id) {

                            mindbodydataId = id;
                                    //$scope.showStyles();
                                    showText(temp_style_id, temp_style_layout);
                                    $("#tabs-1").show();
                                    $("#filtercontainer").hide();
                                    $("#tabs-2").hide();
                                    $("#tabs-3").hide();
                                    $("#tabs-4").hide();
                                    $("#tabs-5").hide();
                            }

                    $scope.showImages = function(){
                            $("#imageGallery").show();
                             $("#popup").hide();
                            $("#tabs-1").hide();
                            $("#tabs-2").hide();
                            $("#tabs-3").hide();
                            $("#tabs-4").hide();
                            $("#tabs-5").show().css("width", "430px").show("slide", { direction: "right" }, 1000);
                            $("#imagespopup").show();
                            $scope.curPage = 0;
                            $scope.pageSize = 100;
                            $http({
                            method : 'GET',
                                    url : 'GetUserImages'
                            }).success(function(data, status, headers, config) {
//                                    alert(JSON.stringify(data));
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
//                    function checkBlock(id, mind_body_query){
//                    if (mind_body_query == null)
//                    {
//                    //add block
//                    }
//                    else
//                    {
//                        alert("in jquery check block");
//                        
//                        angular.element('#MyController').scope().showData();
//                    
//                    }
//
//
//                    }



                    function showSomething(block_id_temp, id, style, mind_body_query){
                    temp_style_id = id;
                            temp_style_layout = style;
                            temp_block_id = block_id_temp;
                            temp_mind_body_query = mind_body_query;
                            $('.blockchooser').removeClass('border-highlight');
                            $("#" + block_id_temp).addClass('border-highlight');
                            //$("#continueblock").attr('ng-click',"showData('"+block_id_temp+"','"+ mind_body_query +"')");
                            $('#continueblock').prop('disabled', false);
                    }
            //var countBlock = 1;
            function showText(id, layout){
                 //hiding filter Container 
                  $("#filtercontainer").hide();
                  var currentBlockID = "";
                  var currentMindbodyQuery = "";
//                  alert($(selectedBlockId).attr("id"));
                  if(block_clicked== "true")
                  {
                    currentBlockID = temp_block_id;
                    currentMindbodyQuery = temp_mind_body_query;
                  }
                  else
                  {
                    if ($(selectedBlockId).attr("id").indexOf("SSS") >= 0)
                    {
                        var arrBlocks = $(selectedBlockId).attr("id").split("SSS");
                        currentBlockID = arrBlocks[0].replace("block","");
                        currentMindbodyQuery = $(selectedBlockId).attr("mbquery");
                        
                       
                    }
                  }
//                  alert(currentBlockID);
//                alert(temp_block_id+","+ temp_mind_body_query+","+temp_block_id);
//                alert(block_clicked +"=="+ "true" +"||"+ blockIdSelected +"!="+ "defaultblock1");
//            alert(id+":"+layout+":"+mindbodydataId);
            var layoutfilename = layout;
                    var layout_mapper_url = "";
                    $("#clickid").val(layout);
                    if ((mindbodydataId != "") && (mindbodydataId != "0")) {
                        if(block_clicked == "true"||$(selectedBlockId).attr("id") != "defaultblock1")
                            layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email&query=block&block_id="+currentBlockID+"&mindbody_query="+currentMindbodyQuery;
                        else
                            layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email";
                    } else {
                    layout_mapper_url = 'GenericAnnouncementServlet?model_mapper_id=' + id + "&editor_type=email";
                    }

            $.ajax({
            type: 'GET',
                    url: layout_mapper_url,
                    data: {get_param: 'value'},
                    dataType: 'json',
                    success: function (data) {

                    displayElement(id, layout, data);
                    }
            });
            }

            function displayElement(id, layout, data){
            var random_number = Math.floor(Math.random() * 200) + 1;
                    if (blockIdSelected == "defaultblock1")
                    blockId = "defaultblock1";
                    else if (blockIdSelected.indexOf("SSS") >= 0)
                    blockId = blockIdSelected;
                    else
                    blockId = "block" + block_id + "SSS" + random_number;
                    jsondata = data;
                    $.ajax({
                    type: "GET",
                            url: global_host_address + "DownloadXml?file_name=" + layout + ".xml",
                            dataType: "xml",
                            success: function (xml) {

                            if (block_clicked == "true")
                                    $(".preview").append("<div onclick=getBlockId(" + blockId + ") mbquery='"+ temp_mind_body_query +"' id='" + blockId + "' blockdetails='" + id + "' name='" + mindbodydataId + "'></div>");
                                    else
                                    $(".preview #" + blockId).empty();
//                                    alert(blockId);
                                    selectedBlockId = document.getElementById(""+blockId);
                                    
                                    block_clicked = "false";
                                    //  $(".preview").empty();
                                    $(xml).find('layout').each(function () {
                                     height = $(this).find('container').attr("Height");
                                    width = $(this).find('container').attr("Width");
                                    $(".preview #" + blockId).css("width", width + "px");
                                    $(".preview #" + blockId).css("height", height + "px");
                                    $(".dataForEmail").css("width", width + "px").css("max-width","549px");
//                                  $(".dataForEmail").css("height", height + "px");                                    
                                    $(".preview #" + blockId).css("position", "relative");
                                    $(".preview #" + blockId).attr("blockdetails", id);
                            }

                            );
                                    var count = 1;
                                    var blockcount = 1;
                                    var textcount = 1;
                                    $(".imagename").find('option').remove().end();
                                    $(".blockname").find('option').remove().end();
                                    $(xml).find('element').each(function () {
                            var tag = $(this).attr("tag");
                                    type = $(this).attr("type");
                                    var h = "";
                                    var t = "";
                                    var elementdata;
                                    if (jsondata == null)
                            {

                            elementdata = type;
                            }
                            else{

                            $(jsondata).each(function (i, val) {

                            $.each(val, function (k, v) {
                            //                               alert(k + " : " + v+ ":"+ type);
                            if (type.trim() == k.trim()) {
                            elementdata = v;
                            }

                            });
                            });
                            }
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
                                    var colorName = $(this).attr("font-color-name");
                                    fontsize = $(this).attr("font-size");
                                    fontstyle = $(this).attr("font-style");
                                    var fontweight = $(this).attr("font-weight");
                                    var letterspacing = $(this).attr("letter-spacing");
                                    var lineheight = $(this).attr("line-height");
                                    var textalign = $(this).attr("text-align");
                                    var font = $(this).attr("font-family");
                                    var font_family_name = font.split("+").join(" ");
                                    var webkittransform = $(this).attr("webkit-transform");
                                    var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");
                                    for (var i = 1; i <= 6; i++)
                            {
                            if (colorName == "Font-Color-" + i)
                            {
                            fontcolor = $("#shapecolorbox" + i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                            }

                            }
//                                                fontcolor = $(this).attr("font-color");
                            textcount++;
                                if(typeof(elementdata) === "undefined")
                                           {
                                             elementdata= $(this).attr("defaulttext");
                                           }                            
                                    $(".preview #" + blockId).append("<textarea orginial-size='" + fontsize + "' onkeyup=textAreaKeyUp(event,'" + type + "EEE" + blockId + "') class=textAreas onclick=getTectId(" + type + "EEE" + blockId + ") id=" + type + "EEE" + blockId + ">" + elementdata + "</textarea>");
                                    $("#" + type + "EEE" + blockId).css("color", "" + fontcolor)
                                    .css("position", "absolute")
                                    .css("overflow", "hidden")
                                    .css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .css("width", "" + width)

                                    .css("height", "" + height)

                                    .css("font-style", "" + fontstyle)
                                    .css("font-weight", "" + fontweight)
                                    .css("font-family", "" + font_family_name)
                                    .css("letter-spacing", "" + letterspacing)
                                    .css("opacity", "" + opacity)
                                    .css("text-align", "" + textalign)
                                    .css("text-shadow", "" + dropshadow)
                                    .css("webkit-transform", "rotate(" + webkittransform + "deg)")
                                    .css("resize", "none")
                                    .css("background-color", "inherit")
                                    .css("border", "none")
                                    .css("focus", "none")
                                    .css("line-height", "" + lineheight);
                                    //$("#" + type + "EEE" + blockId).autogrow();

                                    //resize of text to fit bound - By Syed Ilyas 26/8/2015
                                    var tempfontsize = parseInt(fontsize.replace("px", ""));
                                    var tempHeight = parseInt(height.replace("px", ""));
                                    $("#" + type + "EEE" + blockId).css("font-size", "" + tempfontsize + "px");
//                                    alert($("#" + type + "EEE" + blockId).attr("id"));
                                    if ($("#" + type + "EEE" + blockId).get(0).scrollHeight > tempHeight)
                            {
                            $("#" + type + "EEE" + blockId).css("line-height", "initial");
                                    while ($("#" + type + "EEE" + blockId).get(0).scrollHeight > tempHeight) {
                            tempfontsize = tempfontsize - 1;
                                    $("#" + type + "EEE" + blockId).css("font-size", "" + tempfontsize + "px");
                            }
//                            var xxyy = parseInt(tempfontsize);
//                                    xxyy = Math.round(xxyy * 1.2);
//                                    $("#" + type + "EEE" + blockId).css("line-height", "" + xxyy + "px");
                            }
                            //resize end

                            var addThis = $("#" + type + "EEE" + blockId).get(0).scrollHeight - $("#" + type + "EEE" + blockId).height();
                                    $("#" + type + "EEE" + blockId).attr("add-this", addThis);
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
                                    $(".imagename").append("<option name=" + background_image + " value=" + type + "EEE" + blockId + ">Image " + count + "</option>");
                                    count++;
                                    $(".preview #" + blockId).append("<div class=images onclick=getImageid(" + type + "EEE" + blockId + ") id=" + type + "EEE" + blockId + " ></div>");
                                    $("#" + type + "EEE" + blockId)
                                    .css("color", "" + fontcolor)
                                    .css("margin-left", "" + left + "px")
                                    .css("margin-top", "" + top + "px")
                                    .css("background-blend-mode", "" + blendmode)
                                    .css("opacity", "" + opacity)
                                    .css("width", "" + width)
                                    .css("position", "absolute")
                                    .css("height", "" + height)
                                    .css("background", "" + background_image)
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
                                    var userId = $("#userid").val();
                                    var userLogonmae = $("#userlogo").val();
                                    var blendmode = $(this).attr("Blend");
                                    var background_color=$(this).attr("blend-background-color");
                                    $(".preview #" + blockId).append("<div onclick=getImageid(" + type + "EEE" + blockId + ") id=" + type + "EEE" + blockId + " ></div>");
                                    $("#" + type + "EEE" + blockId)
                                    .css("color", "" + fontcolor)
                                    .css("margin-left", "" + left + "px")
                                    .css("margin-top", "" + top + "px")
                                    .css("background-blend-mode", "" + blendmode)
                                    .css("opacity", "" + opacity)
                                    .css("width", "" + width)
                                    .css("height", "" + height)
                                    .css("background", "url('/BrndBot/DownloadImage?image_type=USER_LOGO&user_id=" + userId + "&image_name=" + userLogonmae + "')")
                                    .css("background-repeat", "no-repeat")
                                    .css("background-position", "center center")
                                    .css("background-color", ""+background_color)
                                    .css("background-size","contain")
                                    .css("position", "absolute")
                                    .css("webkit-filter",""+ filter);
                            }

                            if (tag === "button")
                            {
                            var src_image = $(this).attr("src").replace("../", "");
                                    $(".preview #" + blockId).append("<div><a href=\"#\" data-reveal-id=\"myModal\"><img src='" + src_image + "' buttonLink='" + elementdata + "' id=" + type + "EEE" + blockId + " onclick=getButtonid('" + type + "EEE" + blockId + "') alt='button'/></a>");
                                    $("#" + type + "EEE" + blockId).css("left", "" + left + "px")
                                    .css("position", "absolute")
                                    .css("top", "" + top + "px")
                                    .attr("src", src_image);
                            }

                            if (tag === "block")
                            {
                            var borderRadius = $(this).attr("border-radius");
                                    var colorName = $(this).attr("color-name");
                                    var backgroundcolor;
                                    var width = $(this).attr("width");
                                    var height = $(this).attr("height");
//                                      var backgroundcolor = $(this).attr("background-color");                                                          
                                    var drop_shadow = $(this).attr("Drop-shadow-color");
                                    var h_shadow = $(this).attr("H-shadow");
                                    var v_shadow = $(this).attr("V-shadow");
                                    var Blur = $(this).attr("blur");
                                    for (var i = 1; i <= 6; i++)
                            {
                            if (colorName == "Color-" + i)
                            {
                            backgroundcolor = $("#shapecolorbox" + i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                            }
                            }
                            $(".blockname").append("<option value=" + type + "EEE" + blockId + ">Shape " + blockcount + "</option>");
                                    blockcount++;
                                    $(".preview #" + blockId).append("<div class=block onclick=getDivId(" + type + "EEE" + blockId + ") id=" + type + "EEE" + blockId + "></div>");
                                    $("#" + type + "EEE" + blockId).css("background-color", "" + backgroundcolor)
                                    .css("margin-left", "" + left + "px")
                                    .css("margin-top", "" + top + "px")
                                    .css("width", "" + width)
                                    .css("border-radius", "" + borderRadius)
                                    .css("position", "absolute")
                                    .css("height", "" + height)
                                    .css("-webkit-filter", "drop-shadow(" + drop_shadow + " " + h_shadow + " " + v_shadow + " " + Blur + ")")
                                    .css("opacity", "" + opacity);
                            }

                            });
                                    if (count == 1){$("#imagecontainer").hide(); }
                            if (blockcount == 1){$("#shapecontainer").hide(); }
                            if (textcount == 1){$("#textcontainer").hide(); }
                            if (count != 1){$("#imagecontainer").show(); }
                            if (blockcount != 1){$("#shapecontainer").show(); }
                            if (textcount != 1){$("#textcontainer").show(); }
                            },
                            error: function (e)
                            {
                            alert("error in xml file read");
                            }
                    });
            }

        </script>
    </head>
    <body ng-app="myapp">
         <input type="hidden" id='userid' value=<%= user_id%>>
         <div class="row" ng-controller="MyController">
            <div class="col-sm-1 col-md-1 col-lg-1 halfcol"><jsp:include page="leftmenu.html"/></div>
            <div class="col-sm-7 col-md-7 col-lg-7">
                        <div class="row">
                            <div class="col-sm-12 col-md-12 col-lg-12 bgcolor"> 
                        <style>
                            #edit{
                        position: relative;
                        top:0px;
                        font-family:"proxima-nova";
                        font-weight:500;
                        left: 0em; 
                        color: #2D4444;

                        }
                        </style>
                        
<!--                            <div class="emleditorhead fontpnr">Froala Editor</div> -->
                            <div id="editor">
                                <div id='edit' style="margin-top: 30px;">
                                </div>
                            </div>
<!--                            <div class="framediv">
                                <iframe class="frm" src=""></iframe>
                            </div>    -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-sm-6">
                                <div class="btmdiv">
                                    <div class="row">
                                        <div class="col-lg-7 col-md-7 col-sm-7">
                                            <div class="editemail fontpnr">Edit this Email</div>
                                        </div>   
                                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1 col-md-offset-1">
                                            <div class="mobileprev fontpnr">Mobile Preview</div>
                                        </div>
                                        <div class="col-lg-1 col-md-1 col-sm-1">
                                            <div class="emledtrsavebtn"><input class="emailedtrsave fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="save"></div>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </div> 
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="blockselection">     
                    <div class="row">
                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="selblock fontpnr">Select a Block</div>
                        </div>
                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="addblkdiv"><input class="addblkbtn fontpns " type="button" value="Add Block"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="selblklinediv"><hr class="selblkline"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <ul id="blklist" class="blocklist fontpnr">
                                <li ng-repeat="blocks in datalists"> 
                                    <div id="{{blocks.block_id}}" ng-init="showImageOfBlock(blocks.block_id, blocks.mindbody_query)">{{blocks.block_name}}</div>
                                </li>
                               
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-1 col-md-1 col-lg-1">
                <div class="blockstyletab">      
                    <ul class="righttabs fontpnr">
                        <li id="styletab" ng-click="showStyles()">
                            <image src='images/sidebar/Icons_styleButton.svg' class="styleimg"/>
                            <p>STYLE</p>
                        </li>
                        <li id="blocktab"  ng-click="showBlocks()">
                            <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                            <p>BLOCK</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
            <script>
                $("#blklist").click(function (){
                    $(".addblkbtn").css("background-color","#0f76a6").css("color","#f6f7f7");
                });
                $("#styletab").click(function(){
                    $("#styletab").css("background-color","#ffffff").css("color","#19587c");
                    $("#blocktab").css("background-color","transparent").css("color","#19587c");
                });
                $("#blocktab").click(function(){
                    $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
                     $("#styletab").css("background-color","transparent").css("color","#19587c");
                });
               $( document ).ready(function() {
                $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
                });
                
            </script>
<!--         <script type="text/javascript" src="js/froala_editor.min.js" ></script>-->
         <script src="js/froala_editor.min_Email.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/plugins/align.min.js"></script>
        <script type="text/javascript" src="js/plugins/code_view.min.js"></script>
        <script type="text/javascript" src="js/plugins/colors.min.js" ></script>
        <script type="text/javascript" src="js/plugins/emoticons.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
        <script type="text/javascript" src="js/plugins/image.min.js"></script>
        <script type="text/javascript" src="js/plugins/file.min.js"></script>
<!--        <script type="text/javascript" src="js/plugins/image_manager.min.js"></script>-->
<script src="js/plugins/image_manager.min_editor.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/plugins/line_breaker.min.js"></script>
        <script type="text/javascript" src="js/plugins/link.min.js"></script>
        <script type="text/javascript" src="js/plugins/lists.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_format.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/video.min.js"></script>
        <script type="text/javascript" src="js/plugins/table.min.js"></script>
        <script type="text/javascript" src="js/plugins/url.min.js"></script>
        <script type="text/javascript" src="js/plugins/entities.min.js"></script>
        <script type="text/javascript" src="js/plugins/char_counter.min.js"></script>
        <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/save.min.js"></script>
        <script type="text/javascript" src="js/plugins/fullscreen.min.js"></script>
        <script type="text/javascript" src="js/plugins/quote.min.js"></script>
        <script>
                   $(function () {
                       $('#edit').froalaEditor();                    
                    });
        </script>
    </body>
</html>
