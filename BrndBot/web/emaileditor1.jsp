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
        <style>
            .fr-box.fr-basic .fr-element {
                max-height: 480px;
            } 
        </style>
        <%!                SqlMethods sql_methods = new SqlMethods();
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
                } else {
                    mindbody_data_id = "";
                }
//                String msg = request.getParameter("msg");
//              JOptionPane.showMessageDialog(null,"name cannot be blank "+msg);
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        %>
        <!--     <script>
                    var rendomIframeFilename="";
                            $(document).ready(function () {
                                  
                                $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
                                $("#blocktab").click();
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
        //                    document.getElementById('edtimg').src = "images/sidebar/Icons_editButton_blue_new.svg";
        //                    document.getElementById('edt').style.backgroundColor = '#fff';
        //                    document.getElementById('stl').style.backgroundColor = 'transparent';
        //                    document.getElementById('blk').style.backgroundColor = 'transparent';
                            var numitems = $("#imageGallery li").length;
                            $("ul#imageGallery").css("column-count", numitems / 2);
                           
                $("#fontname").change(function () {
                             var text = $("#fontname").find('option:selected').text();
                            var font_family_name = $("#fontname").val();
                            var font_name = font_family_name.split('+').join(' ');
                        $("#" + selectedTextareaId).css("font-family", font_name);
                    });
                    });</script>
        -->
        <!--        <script>-->


        <!--                    var jsondata;
                            var selectedDivId;
                            var block_clicked = "false";
                            var block_id = "0";
                            var blockIdSelected = "defaultblock1";
                            var mindbodydataId = $("#mindbodydata").val();
                            var temp_style_id;
                            var temp_style_layout;
                            var temp_block_id;
                            var temp_mind_body_query;
                            //$("#previewpopup").hide();-->

        <!--//$(document).ready(function() {
        //                
        //               $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
        //               $("#stylelist").css("display","none");
        //                
        //            $('#continueblock').prop('disabled', true);
        //                    $("#preview").click(function(){
        //            $.ajax({
        //            url: getHost() + "PreviewServlet",
        //                    method: "post",
        //                    data:{
        //                        htmlString: $(".dataForEmail").html(),
        //                        iframeName: rendomIframeFilename
        //                    },
        //                    success: function (responseText) {
        //
        //                    //show popup showing
        //                    $("#previewcontent").empty();
        //                            $("#previewcontent").append(responseText);
        //                            //$("#previewpopup").show();
        //                            $(".clickpreview").click();
        //                    }
        //            });
        //            });
        //            
        //            });</script>-->

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
                    var addblockid;
                    var addBlockcCount = 1;
                    $(document).ready(function () {
            $.ajax({
            type: 'POST',
                    url: "GetLayoutStyles?editorType=email",
                    dataType: 'json',
                    success: function (data) {
                    var jsondataDefault = data;
                            alert(JSON.stringify(data));
                            var allLayoutFilename = [];
                            $(jsondataDefault).each(function (i, val) {
                    var i = 0;
                            $.each(val, function (k, v) {
                            allLayoutFilename[i] = v;
                                    i++;
                            });
                    });
                            showText(allLayoutFilename[0]);
                            $(".fr-element").append("<div id=defaultblock1 onclick=selecterBlockId('defaultblock1'," + temp_block_id + ");></div>");
                    }
            });
            });
                    //$("#previewpopup").hide();

//$(document).ready(function() {
//                
//             
//                
////            $('#continueblock').prop('disabled', true);
////                    $("#preview").click(function(){
////            $.ajax({
////            url: getHost() + "PreviewServlet",
////                    method: "post",
////                    data:{
////                        htmlString: $(".dataForEmail").html(),
////                        iframeName: rendomIframeFilename
////                    },
////                    success: function (responseText) {
////
////                    //show popup showing
////                    $("#previewcontent").empty();
////                            $("#previewcontent").append(responseText);
////                            //$("#previewpopup").show();
////                            $(".clickpreview").click();
////                    }
////            });
////            });
////                            $("#closepreview").click(function(){
////                                $("#previewpopup").hide();
////                            });
//
//            });
                    angular.module("myapp", [])

                    .controller("MyController", function($scope, $http) {
                    $scope.showStylesAfterData = function(){

                    blockIdSelected = $(selectedBlockId).attr("id").toString();
                            var arr = blockIdSelected.split('SSS');
                            block_id = arr[0].replace("block", "");
                    };
                            $scope.showStyles = function(){
                            document.getElementById("addblkbtn").style.backgroundColor = "#e3e3e3";
                                    document.getElementById("addblkbtn").style.color = "#9c9da1";
                                    $("#stylelist").css("display", "block");
                                    $("#blklistid").css("display", "none");
                                    $("#styletab").css("background-color", "#ffffff").css("color", "#19587c");
                                    $("#blocktab").css("background-color", "transparent").css("color", "#19587c");
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
                            alert(queryurl);
                                    $http({
                                    method : 'GET',
                                            url : queryurl
                                    }).success(function(data, status, headers, config) {
                            $scope.datalistsstyles = data;
                                    alert(JSON.stringify(data));
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
                            alert("Block clicked");
                                    $("#stylelist").css("display", "none");
                                    $("#blklistid").css("display", "block");
                                    $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                                    $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                                    $('body').scrollTop(0);
                                    $scope.curPage = 0;
                                    $scope.pageSize = 2;
                                    $http({
                                    method : 'GET',
                                            url : 'GetBlocks'
                                    }).success(function(data, status, headers, config) {
                            $scope.datalists = data;
                                    alert(JSON.stringtify(data));
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


                            $("#stylelist").css("display", "none");
                                    $("#blklist").css("display", "block");
                                    $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                                    $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                                    $http.get('GetLayoutStyles?editorType=email&query=block&block_id=' + id).success(function(data, status){
                            var jsondataDefault = data;
                                    alert(JSON.stringify(data));
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
                            addblockid = "block" + addBlockcCount;
                            addBlockcCount++;
                            $(".fr-element").append("<div id=" + addblockid + " onclick=selecterBlockId('" + addblockid + "','" + temp_block_id + "');></div>")
                            if (mind_body_query == "null")
                    {
                    mindbodydataId = "0";
                            //$scope.showStyles();
                            showText(temp_style_id);
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
                            $("#tabs-4").show().css("width", "830px").css("height", "680px").css("position", "fixed").css("margin-left", "-600px").css("top", "0px");
                            $("#loadingGifformindbody").show();
                            $scope.curPage = 0;
                            $scope.pageSize = 4;
                            $http({
                            method : 'GET',
                                    url : 'MindBodyDataServlet?mindbody_query=' + mind_body_query
                            }).success(function(data, status, headers, config) {
                    alert(JSON.stringify(data));
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
                            $("#tabs-4").css("width", "830px").css("position", "fixed").css("margin-left", "-600px").css("top", "0px").show("slide", { direction: "right" }, 1000);
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
                                    showText(temp_style_id);
                                    $("#tabs-1").show();
                                    $("#filtercontainer").hide();
                                    $("#tabs-2").hide();
                                    $("#tabs-3").hide();
                                    $("#tabs-4").hide();
                                    $("#tabs-5").hide();
                            }

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
                            $.ajax({
                            method : 'POST',
                                    url: "GetLayoutStyles?editorType=email&query=block&block_id=" + block_id_temp,
                                    dataType: 'json',
                                    success: function (data) {
                                    alert(JSON.stringify(data));
//                        $(".fr-element").append(data.htmldata)

                                    }
                            });
                    }
            //var countBlock = 1;
            function showText(id){
            //hiding filter Container 
            alert(id);
                    alert("stule image clicked");
                    var layout_mapper_url = "";
                    if (block_clicked == "true")
            {
            currentBlockID = temp_block_id;
                    currentMindbodyQuery = temp_mind_body_query;
            }
            //   $("#clickid").val(layout);
            alert(mindbodydataId);
            if ((mindbodydataId != "") && (mindbodydataId != "0") && (typeof(mindbodydataId) !== "undefined")) 
            {
                if (block_clicked == "true"){
                layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email&query=block&block_id=" + currentBlockID + "&mindbody_query=" + currentMindbodyQuery;
                }
                else{
                layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email";
                }
            } else {
                layout_mapper_url = 'GenericAnnouncementServlet?model_mapper_id=' + id + "&editor_type=email";
            }
            alert(layout_mapper_url);
                    $.ajax({
                    type: 'GET',
                            url: layout_mapper_url,
                            data: {get_param: 'value'},
                            dataType: 'json',
                            success: function (data) {

                            // displayElement(id, layout, data);
                            alert("sucess of layout mapper");
                            alert(id+"......"+JSON.stringify(data));
                            $.ajax({
                            method : 'POST',
                                    url: "GetEmaiLayoutHtmlServlet?id=" + id,
                                    dataType: 'json',
                                    contentType: 'application/json',
                                    mimeType: 'application/json',
                                    data: JSON.stringify(data),  
                                    success: function (data) {
                                    alert(temp_block_id);
                                            if (block_clicked === "false"){                                            
                                            $("#defaultblock1").empty().append(data.htmldata);
                                    }
                                    else{

                                    $("#" + addblockid).empty().append(data.htmldata);
                                    }

                                    }
                            });
                            }
                    });
            }

            function addblock(){
            document.getElementById("addblkbtn").style.backgroundColor = "#0f76a6";
                    document.getElementById("addblkbtn").style.color = "#f6f7f7";
            }

            function selecterBlockId(selectblock, blockid){
            alert(selectblock);
                    if (selectblock == "defaultblock1")
            {
                    block_clicked = "false";
                    blockIdSelected = "defaultblock1";
            }
            else{
                    block_clicked = "true";
                    blockIdSelected = "";
                    block_id = blockid;
                    addblockid = selectblock;
            }

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
                                font-family:"proxima-nova",sans-serif;
                                font-weight:500;
                                left: 0em; 
                                color: #2D4444;
                                max-height: 450px;
                            }
                        </style>

                        <!--                            <div class="emleditorhead fontpnr">Froala Editor</div> -->
                        <div id="editor">
                            <div id='edit' style="margin-top: 5px;">
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
                                    <input type="hidden" id="mindbodydata" value='<%= mindbody_data_id%>'>
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
                            <div class="addblkdiv"><input id="addblkbtn" ng-click="showDataTemp()" class="addblkbtn fontpns " type="button" value="Add Block"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="selblklinediv"><hr class="selblkline"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <ul id="blklistid" class="blocklist fontpnr" value="blklist">
                                <li ng-repeat="blocks in datalists" id="blklist" onclick="addblock();"> 
                                    <div id="{{blocks.block_id}}" ng-init="showImageOfBlock(blocks.block_id, blocks.mindbody_query)">{{blocks.block_name}}</div>
                                </li>
                            </ul>
                            <ul id="stylelist" class="stylelist fontpnr">
                                <li ng-repeat="styles in datalistsstyles" id="stylelistid">
                                    <div><img id="{{styles.id}}" class="img-responsive lookchooser5 ptr" src="{{styles.image_url}}"  onclick="showText('{{styles.id}}')" width="275" style="height:200px;"/><br></div>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-1 col-md-1 col-lg-1" style="z-index: 10">
                <div class="blockstyletab">      
                    <ul class="righttabs fontpnr">
                        <li id="styletab" ng-init="showStyles()" ng-click="showStyles()">
                            <image src='images/sidebar/Icons_styleButton.svg' class="styleimg"/>
                            <p>STYLE</p>
                        </li>
                        <li id="blocktab"  ng-click="showBlocks()">
                            <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                            <p>BLOCK</p>
                        </li>
                        <li id="tabs-4"style="background:#FFFFFF;position:absolute;z-index: 10;overflow-y: scroll">
                            <a class="boxclose" id="boxclose"></a>
                            <div id="loadingGifformindbody" style="position: absolute; top:240px;left: 335px;" > <img src="images/YogaLoadingGif.gif" /></div>
                            <div ng-controller="MyController" id="MyController" > 
                                <p id="text3" style="width: 500px;font-family: 'proxima-nova',sans-serif;font-weight: 600; position: relative;right:-65px;color:#000;margin-top:50px;padding: 5px;">{{datalists.title}}</p><br>
                                <ul class="dataheaderfromindbody">
                                    <div class="mindbodyHeaderData LE2" >
                                        <li style="width: 250px;margin-left:10px;">{{datalists.column_header[0]}}</li>
                                        <li style="width: 230px;left:278px;">{{datalists.column_header[1]}}</li>
                                        <li style="width: 100px;left:413px;">{{datalists.column_header[2]}}</li>
                                    </div>
                                </ul>


                                <div  class="tab-pane active" id="picktheme">
                                    <div>

                                        <div style="width: 700px;height: 530px;overflow-x:hidden; overflow-y: scroll; float: right; margin-right: 90px;" >

                                            <ul class="datafromindbody" ng-repeat="jsonclass in datalists.mindbody_data" style="width: 700px;position: relative;">
                                                <!--                                    {{jsonclass}}-->
                                                <div class='mindbodyOneRowData MH1' ng-click="select_category_details(jsonclass.id)" style="font-weight: 400;font-size: 16px;">
                                                    <li style="width: 250px;height: 0px;">{{jsonclass.column1}}</li>
                                                    <li style="width: 200px;height: 0px;">{{jsonclass.column2}}</li>
                                                    <li style="width: 180px;height: 0px;margin-left: 10px;">{{jsonclass.column3}}</li>
                                                </div>
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
        <script>
                    $("#blklist").click(function (){
            $(".addblkbtn").css("background-color", "#0f76a6").css("color", "#f6f7f7");
            });
                    $("#styletab").click(function(){
            $("#stylelist").css("display", "block");
                    $("#blklist").css("display", "none");
                    $("#styletab").css("background-color", "#ffffff").css("color", "#19587c");
                    $("#blocktab").css("background-color", "transparent").css("color", "#19587c");
            });
                    $("#blocktab").click(function(){
            $("#stylelist").css("display", "none");
                    $("#blklist").css("display", "block");
                    $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                    $("#styletab").css("background-color", "transparent").css("color", "#19587c");
            });</script>
        <script type="text/javascript" src="js/froala_editor.min_Email.js" ></script>
        <script type="text/javascript" src="js/plugins/align.min.js"></script>
        <!--        <script type="text/javascript" src="js/plugins/code_view.min.js"></script>-->
        <script type="text/javascript" src="js/plugins/colors.min_Editor.js" ></script>
        <script type="text/javascript" src="js/plugins/emoticons.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
        <script type="text/javascript" src="js/plugins/image.min.js"></script>
        <script type="text/javascript" src="js/plugins/file.min.js"></script>
        <script type="text/javascript" src="js/plugins/image_manager.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/line_breaker.min.js"></script>
        <script type="text/javascript" src="js/plugins/link.min.js"></script>
        <script type="text/javascript" src="js/plugins/lists.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_format.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_style.min.js"></script>
        <!--        <script type="text/javascript" src="js/plugins/video.min.js"></script>-->
        <script type="text/javascript" src="js/plugins/table.min.js"></script>
        <script type="text/javascript" src="js/plugins/url.min.js"></script>
        <script type="text/javascript" src="js/plugins/entities.min.js"></script>
        <!--        <script type="text/javascript" src="js/plugins/char_counter.min.js"></script>-->
        <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/save.min.js"></script>
        <!--        <script type="text/javascript" src="js/plugins/fullscreen.min.js"></script>-->
        <script type="text/javascript" src="js/plugins/quote.min.js"></script>
        <script>
                    $(function () {
                    $('#edit').froalaEditor();
                    });
        </script>
    </body>
</html>
