
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Email Editor</title>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-6.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="css/slat.css"/>
    <link rel="shortcut icon" href="images/favicon.png"/>   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
    <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/dashboard.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="css/froala_editor.css" rel="stylesheet" type="text/css"/>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
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
    <link rel="stylesheet" href="css/plugins/image_manager.css">
    <script src="js/froala_editor.min_editor.js" type="text/javascript"></script>
    <!--        <script src="js/plugins/code_view.min.js" type="text/javascript"></script>-->
    <script type="text/javascript" src="js/emaileditor_new.js"></script>
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
    <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
    <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
    
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
            sql_methods.session.setAttribute("email_subject", email_subject);
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
                    
        $(document).ready(function(){
//            $("li").click(function(){
//                alert();
//                var selectedID=this.id;
//                $("#"+selectedID).removeClass("style-slat");
//                $("#"+selectedID).addClass("style-slat-active");
//                alert(selectedID);
//            });
            $("#stylediv li:nth-child(1)").removeClass("style-slat");
            $("#stylediv li:nth-child(1)").addClass("style-slat-active");
        });
                    
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
            
//            $("#deleteBlock").easyconfirm(); 

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
                    var allLayoutFilename = [];
                    $(jsondataDefault).each(function (i, val) {
                    var i = 0;
                    $.each(val, function (k, v)
                    {
                        allLayoutFilename[i] = v;
                        i++;
                    });
                });
                showText(allLayoutFilename[0]);
                angular.element(document.getElementById('MyController')).scope().getEmailDrafts();
//                $('#edit').froalaEditor('html.insert','<div id=defaultblock1 onclick=selecterBlockId(defaultblock1,temp_block_id);></div>"', true);
//                $(".fr-element").append("<div id=defaultblock1 onclick=selecterBlockId('defaultblock1'," + temp_block_id + ");></div>");
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
                    
//                    $scope.addBlockActive = function(divid,id){
//                         $(".block-button").addClass("hide");
//                         $("#blockdiv li").removeClass("block-slat-active");
//                         $("#blockdiv li").addClass("block-slat");
//                         $("#"+divid).removeClass("block-slat");
//                         $("#"+divid).addClass("block-slat-active");
//                         $("#div2"+id).removeClass("hide");
//                    };
                    
                    $scope.addActive = function(id){
                        $("#stylediv li").removeClass("style-slat-active");
                        $("#stylediv li").addClass("style-slat");
                        $("#"+id).removeClass("style-slat");
                        $("#"+id).addClass("style-slat-active");
                    };
                    
                    $scope.showStylesAfterData = function(){
                        blockIdSelected = $(selectedBlockId).attr("id").toString();
                        var arr = blockIdSelected.split('SSS');
                        block_id = arr[0].replace("block", "");
                    };
                    
                    $scope.showStyles = function(){
                            //alert("..");
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
                        
 /*---------------------------------- show style -------------------------------*/
    
                        var queryurl;
                        queryurl = 'GetLayoutStyles?editorType=email';
                        $http({
                        method : 'GET',
                                url : queryurl
                        }).success(function(data, status, headers, config) {
                            //$scope.datalistsstyles = data;
//                            alert(JSON.stringify(data));
//                            document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton_blue_new.svg";
//                            document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton.svg";
//                            document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
//                            document.getElementById('edt').style.backgroundColor = 'transparent';
//                            document.getElementById('stl').style.backgroundColor = '#fff';
//                            document.getElementById('blk').style.backgroundColor = 'transparent';
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
                            
 /*---------------------------------- Show  Block ------------------------------*/     
 
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
                            //alert(JSON.stringtify(data));
//                            document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton.svg";
//                            document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton_blue_new.svg";
//                            document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
//                            document.getElementById('edt').style.backgroundColor = 'transparent';
//                            document.getElementById('stl').style.backgroundColor = 'transparent';
//                            document.getElementById('blk').style.backgroundColor = '#fff';
                            $scope.numberOfPages = function() {
                                return Math.ceil($scope.datalists.length / $scope.pageSize);
                            };
                            if (data === error){
                                alert(data);
                            }
                        }).error(function(data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        });
        
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
                            alert(JSON.stringtify(data));
//                            document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton.svg";
//                            document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton_blue_new.svg";
//                            document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
//                            document.getElementById('edt').style.backgroundColor = 'transparent';
//                            document.getElementById('stl').style.backgroundColor = 'transparent';
//                            document.getElementById('blk').style.backgroundColor = '#fff';
                            $scope.numberOfPages = function() {
                                return Math.ceil($scope.datalists.length / $scope.pageSize);
                            };
                            if (data === error){
                                alert(data);
                            }
                        }).error(function(data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        });
                    };
                    
                    $scope.showImageOfBlock = function(id, mind_body_query){
                            $(".block-button").addClass("hide");
                            $("#blockdiv li").removeClass("block-slat-active");
                            $("#blockdiv li").addClass("block-slat");
                            $("#"+id).removeClass("block-slat");
                            $("#"+id).addClass("block-slat-active");
                            $("#div2"+id).removeClass("hide");
                            hlt();
                            $("#stylelist").css("display", "none");
                            $("#blklist").css("display", "block");
                            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                            $(":button").removeAttr("disabled");
                            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                            $http.get('GetLayoutStyles?editorType=email&query=block&block_id=' + id).success(function(data, status){
                            var jsondataDefault = data;
                                //alert(JSON.stringify(data));
                                ///alert(id);
                                var allLayoutFilename = [];
                                $(jsondataDefault).each(function (i, val)
                                {
                                    var i = 0;
                                    $.each(val, function (k, v) 
                                    {
                                        allLayoutFilename[i] = v;
                                        i++;
                                    });
                                });
                                temp_block_id = id;
                                temp_style_id = allLayoutFilename[0];
                                temp_style_layout = allLayoutFilename[1];                
                                temp_mind_body_query = mind_body_query;
                                //$("#" + id).attr('onclick', "showSomething('" + id + "','" + allLayoutFilename[0] + "','" + allLayoutFilename[1] + "','" + mind_body_query + "')");
//                                alert("smtng...block id.."+temp_block_id);
//                                alert("smtng...style id.."+temp_style_id);
//                                alert("smtng...style.."+temp_style_layout);
//                                alert("smtng...mind_body_query.."+temp_mind_body_query);
                            }).error();
                                $("#addblkbtn").prop("disabled", true);
                            };
                    
                    $scope.showDataTemp = function(){
                        $scope.showData(temp_block_id, temp_mind_body_query);
                        $("#blockdivheader").hide();
                        $("#styledivheader").show();
                        $("#blockdiv").hide();
                        $("#stylediv").show();
                        $("#blocktab").removeClass("emailSideBar-tab-active");
                        $("#blocktab").addClass("emailSideBar-tab");
                        $("#styletab").removeClass("emailSideBar-tab");
                        $("#styletab").addClass("emailSideBar-tab-active");                        
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
                            if (data === error){
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
                temp_block_id = block_id_temp;
                temp_style_id = id;
                temp_style_layout = style;                
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
//            alert(layout_mapper_url);
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
//                alert(selectblockid);
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

</head>    
<body ng-app="myapp">
     <div id="boxes" >
            <div id="dialog" class="window" >
            </div>
            <div id="mask"></div>
        </div>
    <input type="hidden" id='userid' value="<%= user_id%>"/>
    <input type="hidden" id='draftid' value="<%= draft_id%>"/>
    <input type="hidden" value="<%=email_subject%>" id="email_subject"/>
    <!--SideNav-->
    <div class="content-main" ng-controller="MyController">        
    <!--Top Nav-->   
    <div class="top-nav-full">
        <div class="page-title-bar col-1of1"> 
            <a class=" exit-button-icon" href="emailsubjects.jsp?id=<%= mindbody_data_id %>&mediatype=email">    
                <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                </div>
            </a>
            <div class="page-title-with-back page-title-font">Email Editor</div>
            <div class="page-cta-container">
                <a href="" class="gray-button fleft pushright">
                    <div class="nounderline md-button" onclick="show('iphone');">  Mobile Preview</div>    
                </a>
                <a href="" class="gray-button fleft ">
                    <div class=" md-button" id="saveToDraft">  Save as Draft</div>    
                </a>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="emailEditor-page-background fleft">
            <div class="emailEditor-leftCol ">
                 <script>
                    $("#addblkbtn").click(function (){
                    $("#tabs-4").css("display", "block");
                            $("#clsbtn").css("display", "none");
                    });
                            $("#boxclose").click(function (){
                    $("#blocktab").click();
                            $("#tabs-4").hide();
                    });

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
                    });        
                            $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick']); 
                            
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
                            });        
                            
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
                                    $("body").css("overflow","none");
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
                    
                            function hlt(){
                            var $li = $('#blklistid li').click(function() {
                            $li.removeClass('border-highlight');
                                    $(this).addClass('border-highlight');
                            });
                            };
                $(document).ready(function(){
                                                    
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
                        if (draft_id == "0")
                        {
                            $.ajax({
                            url: getHost() + "saveEmailDrafts.do",
                            method: "post",
                            data:{
                            bodyString : $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                            },
                            success: function (responseText) {
                                if (responseText != "0"){
                                document.location.href = "emaillistselection.jsp?draftid=" + responseText + "&subject=" + email_subject;
                                } else 
                                {
                                    alert("There was a problem while saving the draft! Please try again later.");
                                }
                            }
                            });
                        } 
                        else 
                        {
                        $.ajax({
                            url: getHost() + "updateEmailDraft.do",
                            method: "post",
                            data:{
                            draftid: draft_id,
                                    bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                            },
                            success: function (responseText) {
                                if (responseText == "true")
                                {
                                    document.location.href = "emaillistselection.jsp?draftid=" + draft_id + "&subject=" + email_subject;
                                } else
                                {
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
                    
    $("#styletab").click(function (){
        $("#blockdivheader").hide();
        $("#styledivheader").show();
        $("#blockdiv").hide();
        $("#stylediv").show();
        $("#blocktab").removeClass("emailSideBar-tab-active");
        $("#blocktab").addClass("emailSideBar-tab");
        $("#styletab").removeClass("emailSideBar-tab");
        $("#styletab").addClass("emailSideBar-tab-active");
    });
    $("#blocktab").click(function (){
        $("#blockdivheader").show();
        $("#styledivheader").hide();
        $("#blockdiv").show();
        $("#stylediv").hide();
        $("#styletab").removeClass("emailSideBar-tab-active");
        $("#styletab").addClass("emailSideBar-tab");
        $("#blocktab").removeClass("emailSideBar-tab");
        $("#blocktab").addClass("emailSideBar-tab-active");
    });
                });
        </script>   
                <div id="editor">
                    <div id='edit' style="margin-top: 5px;">
                    </div>
                </div>
            </div>
            <div class="emailEditor-rightCol fright">
                <div class="emailSideBar-Header">
                    <div class="col-1of2 fleft">
                        <div class="emailSideBar-tab-active" id="blocktab" ng-init="showBlocks()" ng-click="showBlocks()">
                         Add Block
                        </div>
                    </div>
                    <div class="col-1of2 fleft">
                        <div class="emailSideBar-tab" id="styletab" ng-init="showStyles()" ng-click="showStyles()">
                         Change Style
                        </div>
                    </div>
                </div>
                <div class="email-Block-Selection">
                    <div class="email-Block-Header" id="blockdivheader">Select a block to add:</div>
                    <div class="email-Block-Header" id="styledivheader">Select a style for this block:</div>
                    <div class="block-selection-divider"></div>
                    <ul class="block-list" id="blockdiv"  >
<!--                        <li class="block-slat-active" >
                            <div class="block-name">Header Block</div>
                            <div class="block-button" ng-click="showDataTemp()">Add Block</div>                            
                        </li>-->
                        <li class="block-slat" ng-repeat="blocks in datalists" id="{{blocks.block_id}}" ng-click="showImageOfBlock(blocks.block_id, blocks.mindbody_query)">
                            <div class="block-name" id="blklist----{{blocks.block_id}}" >{{blocks.block_name}}</div>                            
                            <div class="block-button hide" ng-click="showDataTemp()" id="div2{{blocks.block_id}}">Add Block</div>
                        </li>
                    </ul>
                    
                    <ul class="block-list" id="stylediv">
                        <li ng-repeat="styles in datalistsstyles" class="style-slat" id="stylelistid{{styles.id}}" ng-click="addActive('stylelistid'+styles.id)">
                            <div class="block-name">
                                <img id="{{styles.id}}" class="img-responsive lookchooser5 ptr" src="{{styles.image_url}}" onclick="showText('{{styles.id}}')" width="100%" style="height:175px;"/>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
               <div class="bottom-continue-button button-text-1" id="saveButton">Continue</div>
            </div>
        </div>
         </div>
</div>
    </body>
</html>