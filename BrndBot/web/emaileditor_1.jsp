<%-- 
    Document   : emaileditornew
    Created on : Oct 21, 2015, 9:13:55 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BrndBot - Email Editor</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
        <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <script src="js/popup.js" type="text/javascript"></script>
        <!--<script src="js/emaileditor.js" type="text/javascript"></script>-->
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

        <link rel="shortcut icon" href="images/favicon.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">
        <style>
            
            #emaileditorexternalpopup{
                position: fixed;
                z-index: 999999;
                height: 100%;
                /*overflow-y: auto;*/
                max-height: 100%;
                width: 900px;
                right: 0px;
                margin-right: -900px;
            }
            .fr-popup.fr-active{
                /*top:490px !important;*/
                /*position:relative !important;*/
            }
            body {
                margin: 0; 
                height: 100%;
                overflow: hidden;
            }
            .datafromindbody:hover{
                background-color: #00A37A;
                color:#fff;
            }
            .fr-box .fr-basic .fr-element {
                /*                max-height: 480px;*/
                max-height: 690px;
            } 

            #mask {
                position: absolute;
                left: 0;
                top: 0;
                z-index: 9000;
                background-color: #ffffff;
                display: none;
            }

            #boxes .window {
                position: absolute;
                left: 0;
                top: 0;
                width: 0px;
                height: 0px;
                display: none;
                z-index: 9999;
                padding: 20px;
                border-radius: 15px;
                text-align: center;
            }
            #boxes #dialog {
                width: 250px;
                height: 300px;
                padding: 10px;
                background-color: #ffffff;
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
            #avpw_rghtArrow,#avpw_lftArrow
            {
                display:none !important;
            }

            #avpw_main_frames,#avpw_main_overlay,#avpw_main_resize,
            #avpw_main_orientation,#avpw_main_focus,#avpw_main_brightness,
            #avpw_main_contrast,#avpw_main_saturation,#avpw_main_warmth,
            #avpw_main_sharpness,#avpw_main_colorsplash,#avpw_main_drawing,
            #avpw_main_textwithfont,#avpw_main_redeye,#avpw_main_whiten,#avpw_main_blemish
            {
                display:none;
            }
            .fr-view table td, .fr-view table th{
                border:none;
            }
            .fr-wrapper{
                /*                    min-height: 550px !important;
                    height: auto !important;
                     max-height: 546px !important;*/
                /*min-height: 0% !important;*/
                height: auto !important;
                max-height: 100% !important;
                position: relative !important;
            }
            li.border-highlight {
                /*width: 250px;*/
                /*height: inherit;*/
                color: #f6f7f7;
                background-color: #0f76a6; 
            }
            
        </style>
        <%!            
            SqlMethods sql_methods = new SqlMethods();
            StringBuffer string_buffer = new StringBuffer();
            String mindbody_data_id = "";
            String logoImageName = null;
            String draft_id = "0";
            String email_subject = "";
        %>
        <% email_subject = request.getParameter("subject"); %>
        <%
            try {
                sql_methods.session = request.getSession(true);
                draft_id = "0";
                user_id = (Integer) sql_methods.session.getAttribute("UID");
                logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
                if (!request.getParameter("id").equals("null")) {
                    mindbody_data_id = (String) request.getParameter("id");
                } else {
                    mindbody_data_id = "";
                }
                if (!request.getParameter("draftid").equals("null")) {
                    draft_id = (String) request.getParameter("draftid");
                    out.println();
                }
//                String msg = request.getParameter("msg");
//              JOptionPane.showMessageDialog(null,"name cannot be blank "+msg);
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        %>

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
                    var styleHtml = "";
                    var BlockHtml = "";
                    var rendomIframeFilename = "";
                    var draft_id = 0;
                    console.log(draft_id);
                    $(document).ready(function () {
                        mindbodydataId = $("#mindbodydata").val();
                        draft_id = <%= draft_id%>;
                        console.log(draft_id);
                    $("#addblkbtn").prop('disabled', true);
                    $(".selectrow").css("display", "none");
                        rendomIframeFilename = event.timeStamp;
                        selecterBlockId('defaultblock1', temp_block_id);
                    $("#sortUpBlock").click(function () {
            var current = $("#" + addblockid);
                    current.prev().before(current);
            });
//     $("#deleteBlock").easyconfirm(); 
                    $("#deleteBlock").click(function () {
            new $.flavr({
                    content     : 'Are you sure you want to delete this style?',
                    dialog      : 'confirm',
                    onConfirm   : function($container){
                    var tempSelectedBlockId = addblockid;
                            $("#" + tempSelectedBlockId).remove();
                            $(".imagename").find('option').remove().end();
                            $(".blockname").find('option').remove().end();
                    },
                    onCancel    : function($container){

                    }
            });
            });
                    $("#sortDownBlock").click(function () {
            var current = $("#" + addblockid);
                    current.next().after(current);
            });
                    $.ajax({
                    type: 'POST',
                            url: "GetLayoutStyles?editorType=email",
                            dataType: 'json',
                            success: function (data) {
                            var jsondataDefault = data;
//                                    alert(JSON.stringify(data));
                                    var allLayoutFilename = [];
                                    $(jsondataDefault).each(function (i, val) {
                            var i = 0;
                                    $.each(val, function (k, v) {
                                    allLayoutFilename[i] = v;
                                            i++;
                                    });
                            });
                                    showText(allLayoutFilename[0]);
                                    angular.element(document.getElementById('MyController')).scope().getEmailDrafts();
//                                    $('#edit').froalaEditor('html.insert','<div id=defaultblock1 onclick=selecterBlockId(defaultblock1,temp_block_id);></div>"', true);
//                                    $(".fr-element").append("<div id=defaultblock1 onclick=selecterBlockId('defaultblock1'," + temp_block_id + ");></div>");
                            }
                    });
            });
                    angular.module("myapp", [])

                    .controller("MyController", function($scope, $http) {

                    $scope.getEmailDrafts = function(){

                    if (draft_id != ""){

                    $http({
                    method : 'GET',
                            url : getHost() + 'getEmailDraft.do?draftid=' + draft_id
                    }).success(function(data, status) {
                    if (data == ""){
                    $scope.emaildraftsstatus = "No email drafts present";
                    } else {

                    $scope.htmlbody = data.htmlbody;
                            $('#edit').froalaEditor('html.set', '' + data.htmlbody);
                    }

                    }).error(function(data, status) {
                    alert("No data available! Problem fetching the data.");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });
                    }

                    };
                            $scope.showStylesAfterData = function(){

                            blockIdSelected = $(selectedBlockId).attr("id").toString();
                                    var arr = blockIdSelected.split('SSS');
                                    block_id = arr[0].replace("block", "");
                            };
                            
                            $scope.showStyles = function(){
                            $(".selectrow").css("display", "none");
                                    document.getElementById("addblkbtn").style.backgroundColor = "#e3e3e3";
                                    document.getElementById("addblkbtn").style.color = "#9c9da1";
                                    $("#stylelist").css("display", "block");
                                    $("#selectstyleid").css("display", "block");
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
//                            alert(queryurl);
                            $http({
                            method : 'GET',
                                    url : queryurl
                            }).success(function(data, status, headers, config) {
                            $scope.datalistsstyles = data;
//                                    alert(JSON.stringify(data));
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
                            alert("No data available! Problem fetching the data.");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                            $scope.showBlocks = function(){
                            $("#addblkbtn").prop("disabled", true);
                                    $(".selectrow").css("display", "block");
                                    $("#stylelist").css("display", "none");
                                    $("#selectstyleid").css("display", "none");
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
//                                    alert(JSON.stringtify(data));
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
                            alert("No data available! Problem fetching the data.");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                            $scope.showImageOfBlock = function(id, mind_body_query){

                                hlt();
                                $("#stylelist").css("display", "none");
                                $("#blklist").css("display", "block");
                                $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                                $(":button").removeAttr("disabled");
                                $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                                $http.get('GetLayoutStyles?editorType=email&query=block&block_id=' + id).success(function(data, status){
                                    var jsondataDefault = data;
                                    //alert(JSON.stringify(data));
                                    var allLayoutFilename = [];
                                    $(jsondataDefault).each(function (i, val) {
                                        var i = 0;
                                        $.each(val, function (k, v) {
                                            allLayoutFilename[i] = v;
                                            i++;
                                        });
                                    });
                                $("#" + id).attr('onclick', "showSomething('" + id + "','" + allLayoutFilename[0] + "','" + allLayoutFilename[1] + "','" + mind_body_query + "')");
                                }).error();
                                $("#addblkbtn").prop("disabled", true);
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
//                            $(".fr-element").append("<div id=" + addblockid + " onclick=selecterBlockId('" + addblockid + "','" + temp_block_id + "');></div>")
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
                                $("#tabs-4").show().css("width", "830px").css("height", "100%").css("position", "fixed").css("margin-left", "-600px").css("top", "0px");
                                $("#loadingGifformindbody").show();
                                $scope.curPage = 0;
                                $scope.pageSize = 4;
                                $http({
                                method : 'GET',
                                        url : 'MindBodyDataServlet?mindbody_query=' + mind_body_query
                                }).success(function(data, status, headers, config) {
                                    //                    alert(JSON.stringify(data));
                                    $scope.datalists = data;
                                    $scope.numberOfPages = function() {
                                    return Math.ceil($scope.datalists.length / $scope.pageSize);
                                    };
                                    if (data === error)
                                    {
                                        alert(data);
                                    }
                                    $("#loadingGifformindbody").hide();
                                    $("#clsbtn").css("display", "block");
                                    $("#addblkbtn").prop('disabled', true).css("background-color", "#e3e3e3").css("color", "#9c9da1");
                                    $("#tabs-4").css("width", "830px").css("position", "fixed").css("margin-left", "-600px").css("top", "0px").show("slide", { direction: "right" }, 1000);
                                }).error(function(data, status, headers, config) {
                                alert("No data available! Problem fetching the data.");
                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.
                                });
                            }
                    $scope.showStyles();
                    };
                            $scope.select_category_details = function(id) {
                            mindbodydataId = id;
                                    $("#stylelist").css("display", "block");
                                    $scope.showStyles();
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
            function showSomething(block_id_temp, id, style, mind_body_query){
                $("#addblkbtn").prop('disabled', false);
                hlt();
                addblock();
                temp_style_id = id;
                temp_style_layout = style;
                temp_block_id = block_id_temp;
                temp_mind_body_query = mind_body_query;
                $('.listblock').removeClass('border-highlight');
                $("#" + block_id_temp).addClass('border-highlight');
                $('#continueblock').prop('disabled', false);
            }
            function showText(id){
            //hiding filter Container 
            var layout_mapper_url = "";
            if (block_clicked == "true"){
                currentBlockID = temp_block_id;
                currentMindbodyQuery = temp_mind_body_query;
            }
            if ((mindbodydataId != "") && (mindbodydataId != "0") && (typeof (mindbodydataId) !== "undefined")){
            if (block_clicked == "true"){
            layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email&query=block&block_id=" + currentBlockID + "&mindbody_query=" + currentMindbodyQuery;
            }
            else{
            layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + id + "&editor_type=email";
            }
            } else {
            layout_mapper_url = 'GenericAnnouncementServlet?model_mapper_id=' + id + "&editor_type=email";
            }
            //alert(temp_block_id);
            $.ajax({
            type: 'GET',
                    url: layout_mapper_url,
                    data: {get_param: 'value'},
                    dataType: 'json',
                    success: function (data) {
                    $.ajax({
                    method : 'POST',
                            url: "GetEmaiLayoutHtmlServlet?id=" + id,
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(data),
                            success: function (data) {
                            if (block_clicked === "false"){
                            var editorHtml = $('#edit').froalaEditor('html.get');
                                    if (editorHtml.contains('id="defaultblock1"')){
                            var jHtmlObject = jQuery(editorHtml);
                                    var editor = jQuery("<p>").append(jHtmlObject);
                                    editor.find("#defaultblock1").remove();
                                    editorHtml = editor.html();
                            }
                            styleHtml = '<div id=defaultblock1 onclick="selecterBlockId(defaultblock1,0)">' + data.htmldata + '</div>';
                                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
//                                                   $("#defaultblock1").empty().append(data.htmldata);
                            }
                            else{
                            var editorHtml = $('#edit').froalaEditor('html.get'); 
                            if (editorHtml.contains('id="' + addblockid + '"')){  
                                var jHtmlObject = jQuery(editorHtml);
                                var editor = jQuery("<p>").append(jHtmlObject);
                                editor.find("#" + addblockid).remove();
                                editorHtml = editor.html();
                            }
                            BlockHtml = '<div id=' + addblockid + ' onclick=selecterBlockId(' + addblockid + ',' + temp_block_id + ')>' + data.htmldata + '</div>';
                                    $('#edit').froalaEditor('html.set', '' + editorHtml + '' + BlockHtml + '');
//                                            $("#" + defaultblock1).append(data.htmldata);
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
                var selectblockid = selectblock.id;
                temp_block_id =blockid;
//              alert(selectblockid);
                $("img").click(function(){
                    uploadImageToEditor(this.id);
                });
                MoveBlock(selectblock.id);
                if (selectblock == "defaultblock1" || selectblockid == "defaultblock1")
                {
                    block_clicked = "false";
                    blockIdSelected = "defaultblock1";
                    addblockid = selectblockid;
                }
                else{
                    block_clicked = "true";
                    blockIdSelected = "";
                    block_id = blockid;
                    addblockid = selectblockid;
                }
                $("#styletab").trigger("click");
            }
            $("#selcatdet").click(function (){
                $("#blocktab").click();
                $("#tabs-4").hide();
            });    </script>
         <jsp:include page="emaileditormindbodypopup.jsp"/> 
        </head>
    <body ng-app="myapp">
        <div id="boxes">
            <div id="dialog" class="window">
            </div>
            <div id="mask"></div>
        </div>
        <input type="hidden" id='userid' value="<%= user_id%>">
        <div class="row" ng-controller="MyController">
            <input type="hidden" value="<%=email_subject%>" id="email_subject"/>
            <div class="col-sm-1 col-md-1 col-lg-1 halfcol"><%@ include file="navbar2.jsp"%></div>
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
                                max-height: 600px;
                                /*                                overflow: auto;*/
                            }
                        </style>
                        <div id="editor">
                            <div id='edit' style="margin-top: 5px;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-6 col-sm-6">
                        <div class="btmdiv">
                            <div class="row">
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <input type="hidden" id="mindbodydata" value='<%= mindbody_data_id%>'>
                                    <div class="editemail fontpnr">Edit this Email</div>
                                </div>   

                                <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1 col-md-offset-1 display-none">
                                    <div class="mobileprev fontpnr" id="iphone" class="img-responsive ptr" onclick="show('iphone');">Mobile Preview</div>
                                    <!--                                    <div class="glyphicon glyphicon-arrow-up ptr" id="sortUpBlock"></div><br /><br />
                                                                        <div class="glyphicon glyphicon-trash ptr" id="deleteBlock"></div><br /><br />
                                                                        <div class="glyphicon glyphicon-arrow-down ptr" id="sortDownBlock"></div>-->
                                    <p id="button"></p>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1">
                                    <div class="emledtrsavebtn"><input id="saveToDraft" class="emailedtrsavetodraft fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="Save to Draft"></div>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1"></div>
                                <div class="col-lg-1 col-md-1 col-sm-1"></div>
                                <div class="col-lg-2 col-md-2 col-sm-2">
                                    <div class="emledtrsavebtn"><input id="saveButton" class="emailedtrsave fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="Continue"></div>
                                </div>
                            </div>
                        </div>                                
                    </div>
                </div> 
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="blockselection" >     
                    <div class="row selectrow" id="selectblockid" style="display: none;">

                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="selblock fontpnr">Select a Block</div>
                        </div>
                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="addblkdiv"><input id="addblkbtn" ng-click="showDataTemp()" class="addblkbtn fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="Add Block" disabled></div>
                        </div> 
                        <div class="row">
                            <div class="selblklinediv"><hr class="selblkline"></div>
                        </div>
                    </div>
                    <div class="row selectrow1" id="selectstyleid">

                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="selblock fontpnr">Select a Style</div>
                        </div>
                        <div class="row">
                            <div class="selblklinediv1"><hr class="selblkline"></div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <ul id="blklistid" class="blocklist fontpnr" value="blklist" style="display: none;">
                                <li ng-repeat="blocks in datalists" id="blklist" class="listblock" > 
                                    <div  id="{{blocks.block_id}}" ng-init="showImageOfBlock(blocks.block_id, blocks.mindbody_query)">{{blocks.block_name}}</div>
                                </li>
                            </ul>
                            <ul id="stylelist" class="stylelist fontpnr" style="display: none;">
                                <li ng-repeat="styles in datalistsstyles" id="stylelistid{{styles.id}}" ng-click="addActive(this.id)">
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
                        <li id="blocktab" ng-click="showBlocks()">
                            <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                            <p>BLOCK</p>
                        </li>
                        <li id="tabs-4" style="background:#FFFFFF; position:absolute;z-index: 10;overflow-y: scroll;display:none;">
                            <a class="boxclose" id="boxclose"><img id="clsbtn" src="images/CloseIcon.svg" height="15" width="15" style="display:none;"/></a>
                            <div id="loadingGifformindbody" style="position: absolute; top:240px;left: 335px;" > <img src="images/YogaLoadingGif.gif" /></div>
                            <div ng-controller="MyController" id="MyController" > 
                                <p class="selclasstopromote fontpnr">{{datalists.title}}</p><br>
                                <ul class="dataheaderfromindbody">
                                    <div class="mindbodyHeaderData fontpns" >
                                        <li>{{datalists.column_header[0]}}</li>
                                        <li class="teach">{{datalists.column_header[1]}}</li>
                                        <li>{{datalists.column_header[2]}}</li>
                                    </div>
                                </ul>


                                <div  class="tab-pane active" id="picktheme">
                                    <div>

                                        <div class="mindbodydatacontainer" >

                                            <ul class="datafromindbody" ng-repeat="jsonclass in datalists.mindbody_data">
                                                <!--                                    {{jsonclass}}-->
                                                <div class='mindbodyOneRowData fontpnr' id="selcatdet" ng-click="select_category_details(jsonclass.id)" >
                                                    <li class="frstcol">{{jsonclass.column1}}</li>
                                                    <li>{{jsonclass.column2}}</li>
                                                    <li>{{jsonclass.column3}}</li>
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
                            $("#addblkbtn").click(function (){
                    $("#tabs-4").css("display", "block");
                            $("#clsbtn").css("display", "none");
                    });
                            $("#boxclose").click(function (){
                    $("#blocktab").click();
                            $("#tabs-4").hide();
                    });
//            
//            $("#blklist").click(function (){
//                
//                $(this).css("background-color","#0f76a6").css("color","#f4f4f4");   
//                });
                            $("#styletab").click(function(){
                    $("#addblkbtn").prop('disabled', true);
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
                    });        </script>
        <link rel="stylesheet" href="css/plugins/image_manager.css">
        <script src="js/froala_editor.min_editor.js" type="text/javascript"></script>
        <!--        <script src="js/plugins/code_view.min.js" type="text/javascript"></script>-->
        <script type="text/javascript" src="js/plugins/align.min.js"></script>
        <script type="text/javascript" src="js/plugins/colors.min_editor.js" ></script>
        <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
        <script src="js/plugins/image.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/plugins/image.min_editor.js"></script>

        <script type="text/javascript" src="js/plugins/file.min.js"></script>
        <script type="text/javascript" src="js/plugins/image_manager.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/table.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/url.min.js"></script>
        <script type="text/javascript" src="js/plugins/entities.min.js"></script>
        <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/save.min.js"></script>
        <script type="text/javascript" src="js/plugins/quote.min.js"></script>
        <script type="text/javascript" src="js/plugins/link.min.js"></script>

        <script>
                            $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick']);        </script>

        <script>
                            $(function () {
                            var urlList11;
                                    $.ajax({
                                    url:'getAllUserMarketingProgramsBySessionUserId.do',
                                            method:'Get',
                                            dataType: 'json',
                                            contentType: 'application/json',
                                            mimeType: 'application/json',
                                            success: function (responseText) {
                                            urlList11 = responseText
                                                    $('#edit').froalaEditor({key: FroalaLicenseKey, linkList: urlList11});
                                            }
                                    });
                            });        </script>


        <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
        <script>
                            function show(id) {
                            var getId = id;
                                    var dynamicStyle, dynamicWidth, dynamicHeight;
                                    var imageUrl = "images/Phone.svg";
                                    var id = '#dialog';
                                    //Get the screen height and width
                                    var maskHeight = $(document).height();
                                    var maskWidth = $(window).width();
                                    //Set heigth and width to mask to fill up the whole screen
                                    $('#mask').css({'width':maskWidth, 'height':maskHeight});
                                    //transition effect
                                    $('#mask').fadeIn(500);
                                    $('#mask').fadeTo("slow", 0.95);
                                    //Get the window height and width
                                    var winH = $(window).height();
                                    var winW = $(window).width();
                                    //Set the popup window to center
                                    $(id).css('top', winH / 2 - $(id).height() / 2);
                                    $(id).css('left', winW / 2 - $(id).width() / 2);
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
                                    $.ajax({
                                    url: getHost() + "PreviewServlet",
                                            method: "post",
                                            data: {
                                            htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                    iframeName: rendomIframeFilename
                                            },
                                            success: function (responseText) {
//                                        alert(responseText);
                                            if (getId === "iphone"){
                                            $('.window').css("top", "110px");
                                                    dynamicWidth = "275";
                                                    dynamicHeight = "459";
                                                    $(".window").empty();
                                                    $(".window").append("<div id=imageDivPopup style='width:" + dynamicWidth + "px;height:" + dynamicHeight + "px;'></div>");
                                                    $("#imageDivPopup").css("background-image", "url(" + imageUrl + ")").css("background-size", "100% 100%");
//                                        $("#imageDivPopup").append("<div style='width:"+(dynamicWidth-20)+"px;height:"+(dynamicHeight-60)+"px;margin-left:10px;position:relative;top:28px;overflow:scroll;'>"+responseText+"</div>");
                                                    $("#imageDivPopup").append("<iframe style='width:640px;height:1024px;position:relative;top:-295px;left:-183px;-webkit-transform: scale(0.3495);background-color:#FFF;' src='/BrndBot/DownloadHtmlServlet?file_name=" + rendomIframeFilename + ".html'></iframe>");
                                            }
                                            }
                                    });
                            }


        </script> 
        <script>

                    $("#saveToDraft").click(function (){

                    $.ajax({
                    url: getHost() + "PreviewServlet",
                            method: "post",
                            data:{
                            htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    iframeName: rendomIframeFilename
                            },
                            success: function (responseText) {
                            $("#previewcontent").empty();
                                    $("#previewcontent").append(responseText);
                                    if (draft_id == "0"){
                            $.ajax({
                            url: getHost() + "saveEmailDrafts.do",
                                    method: "post",
                                    data:{
                                    bodyString : $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    },
                                    success: function (responseText) {
                                    if (responseText != "0"){
                                    alert("Draft saved successfully.");
                                            document.location.href = "dashboard.jsp";
                                    } else {
                                    alert("There was a problem while saving the draft! Please try again later.");
                                    }
                                    }

                            });
                            } else {
                            $.ajax({
                            url: getHost() + "updateEmailDraft.do",
                                    method: "post",
                                    data:{
                                    draftid: draft_id,
                                            bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    },
                                    success: function (responseText) {
                                    if (responseText == "true"){
                                    alert("Draft updated successfully.");
                                            document.location.href = "dashboard.jsp";
                                    } else {
                                    alert("There was a problem while saving the draft! Please try again later.");
                                    }
                                    }

                            });
                            }
                            }
                    });
                    });
                            $("#saveButton").click(function (){
                                var email_subject = $("#email_subject").val();
                            $.ajax({
                                    url: getHost() + "PreviewServlet",
                                    method: "post",
                                    data:{
                                    htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                            iframeName: rendomIframeFilename
                                    },
                                    success: function (responseText) {
                                    $("#previewcontent").empty();
                                            $("#previewcontent").append(responseText);
                                            $.ajax({
                                                    url: getHost() + "SaveKeyValueSessionServlet",
                                                    method: "post",
                                                    data:{
                                                            process:"save",
                                                            sessionKey:"htmldata",
                                                            sessionValue: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            sessionIframeKey:"iframeName",
                                                            sessionIframevalue:"" + rendomIframeFilename
                                                    },
                                                    success: function (responseText) {
                                                    // added by Syed Ilyas 16 dec 2015 - saves draft
                                                    if (draft_id == "0"){
                                                    $.ajax({
                                                    url: getHost() + "saveEmailDrafts.do",
                                                            method: "post",
                                                            data:{
                                                            bodyString : $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            },
                                                            success: function (responseText) {
                                                            if (responseText != "0"){
                                                            document.location.href = "emaillistselection.jsp?draftid=" + responseText + "&subject=" + email_subject;
                                                            } else {
                                                            alert("There was a problem while saving the draft! Please try again later.");
                                                            }
                                                            }

                                                    });
                                                    } else {
                                                    $.ajax({
                                                    url: getHost() + "updateEmailDraft.do",
                                                            method: "post",
                                                            data:{
                                                            draftid: draft_id,
                                                                    bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            },
                                                            success: function (responseText) {
                                                            if (responseText == "true"){
                                                            document.location.href = "emaillistselection.jsp?draftid=" + draft_id + "&subject=" + email_subject;
                                                            } else {
                                                            alert("There was a problem while saving the draft! Please try again later.");
                                                            }
                                                            }
                                                    });
                                                    }
                                                    }
                                            });
                                    }
                            });
                    });
                            function hlt(){
                            var $li = $('#blklistid li').click(function() {
                            $li.removeClass('border-highlight');
                                    $(this).addClass('border-highlight');
                            });
                            }
        </script>   
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <p>Some text in the modal.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <div id="light" class="white_content closepopup">
                <a href = "javascript:void(0)" style="text-decoration:none;">
                    <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                        <p style="margin-top:-7px;"><img src="images/Icons/yourPlan.svg" height="25" width="25" /></p>
                    </div>
                </a>
        </div>
    </body>
</html>