
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Email Editor</title>
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
    <link href="css/emaileditor.css" rel="stylesheet" type="text/css"/>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/dashboard.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="css/froala_editor.css" rel="stylesheet" type="text/css"/>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <link href="css/froala_style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/plugins/code_view.css"/>
    <link rel="stylesheet" href="css/plugins/colors.css"/>
    <link rel="stylesheet" href="css/plugins/emoticons.css"/>
    <link rel="stylesheet" href="css/plugins/image_manager.css"/>
    <link rel="stylesheet" href="css/plugins/image.css"/>
    <link rel="stylesheet" href="css/plugins/line_breaker.css"/>
    <link rel="stylesheet" href="css/plugins/table.css"/>
    <link rel="stylesheet" href="css/plugins/char_counter.css"/>
    <link rel="stylesheet" href="css/plugins/video.css">
    <link rel="stylesheet" href="css/plugins/fullscreen.css"/>
    <link rel="stylesheet" href="css/plugins/file.css">
    <link rel="shortcut icon" href="images/favicon.png"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css"> 
    <link rel="stylesheet" href="css/plugins/image_manager.css">
    <script src="js/froala_editor.min_editor.js" type="text/javascript"></script>
<!--<script src="js/plugins/code_view.min.js" type="text/javascript"></script>-->
    <!--<script type="text/javascript" src="js/emaileditor_new.js"></script>-->
    <script type="text/javascript" src="js/plugins/align.min.js"></script>
    <script type="text/javascript" src="js/plugins/colors.min_editor.js" ></script>
    <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
    <script type="text/javascript" src="js/plugins/font_family.min_editor.js"></script>
    <script type="text/javascript" src="js/plugins/image.min.js"></script>
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
    <%@include file="loadingoverlay.jsp" %>  
    <%!            
//        SqlMethods sql_methods = new SqlMethods();
        StringBuffer string_buffer = new StringBuffer();
        String mindbody_data_id = "";
        String logoImageName = null;
        String draft_id = "0";
        String email_subject = "";
        String user_id="";
    %>
    <% 
         String categoryId= request.getParameter("categoryId");
         String subCategoryId= request.getParameter("subCategoryId");
         String emailSubject= request.getParameter("emailSubject");
         String mindbodyId=request.getParameter("mindbodyId");
         String LookupId=request.getParameter("LookupId");
         String draft_id=request.getParameter("draftId");
//        email_subject = request.getParameter("subject"); %>
    <%
//        try {
//            sql_methods.session = request.getSession(true);          
//            sql_methods.session.setAttribute("email_subject", email_subject);
//            draft_id = "0";
//            user_id = (Integer) sql_methods.session.getAttribute("UID");
//            logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
//            if (!request.getParameter("id").equals("null")) {
//                mindbody_data_id = (String) request.getParameter("id");
//            } else {
//                mindbody_data_id = "";
//            }
//            if (!request.getParameter("draftid").equals("null")) {
//                draft_id = (String) request.getParameter("draftid");
//                out.println();
//            }
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }

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
        var prevSliderDialog="#emaileditorexternalpopup";
        var sliderDialog="#emaileditorexternalpopup";
        var externalKeywordId="";
        var externalDataId="";
        
        
        function showOverlay(){
            $("#loadoverlaydiv").show();
        }
        function hideOverlay(){
            $("#loadoverlaydiv").hide();
        }
        
        $(document).ready(function(){
             $('#edit').froalaEditor({key: FroalaLicenseKey});
            $('#edit').froalaEditor().show();
            $("#closePrev").click(function(){
                $("#email_previewdiv").hide();
                $('#fade').hide();                
            });
                    
            $("#mindbodyclose").click(function(){
                $("#fade").hide();
                $('#slider-button').click();                
            });            
            
//            $slider=2;
            $a=0;
            $edit=0;
            $('#slider-button').click(function () {
                if ($('#slider-button').css("margin-right") == "788px")
                {
                    $(sliderDialog).animate({"margin-right": '-=900px'});
                    $('#slider-button').animate({"margin-right": '-=788px'});
                }
                else
                {
                    $(sliderDialog).animate({"margin-right": '+=900px'});
                    $('#slider-button').animate({"margin-right": '+=788px'});
                }     
            });
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
            var subCategoryId=$("#subCategoryIdTag").val();
            $.ajax({
                type: 'GET',
//                url: "GetLayoutStyles?editorType=email",
                  url: getHost()+"/getAllEmailModelsBySubCategoryId?subCategoryId="+subCategoryId,
                dataType: 'json',
                success: function (data) {
//                    alert(JSON.stringify(data));
                    var blockList=data.d.details.reverse();
                    var emailModelId=blockList[0].modelId;
                    showText(emailModelId);
                    angular.element(document.getElementById('MyController')).scope().getEmailDrafts();
//                    $('#edit').froalaEditor('html.insert','<div id=defaultblock1 onclick=selecterBlockId(defaultblock1,temp_block_id);></div>"', true);
//                    $(".fr-element").append("<div id=defaultblock1 onclick=selecterBlockId('defaultblock1'," + temp_block_id + ");></div>");
                },error: function (data){
                    alert(JSON.stringify(data));
                }
            });
            });
                    angular.module("myapp", [])

            .controller("MyController", function($scope, $http) {                        

                    $scope.getEmailDrafts = function(){
                    if (draft_id !== "" && draft_id !==null && draft_id !=="null"){
                    $http({
                        method : 'GET',
                        url : getHost() + 'getEmailDraft?draftid=' + draft_id
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
                            showOverlay();
                            var subCategoryId=$("#subCategoryIdTag").val();
                            var queryurl;
                            $scope.curPage = 0;
                            $scope.pageSize = 2;
                            if (block_clicked == "true" || blockIdSelected != "defaultblock1")
                            {
                                queryurl = getHost() +'getAllEmailBlockModelsByBlockId?emailBlockId='+ block_id;
                            }
                            else
                            {
                                queryurl = getHost() +'getAllEmailModelsBySubCategoryId?subCategoryId='+subCategoryId;
                            }
                            $http({
                            method : 'GET',
                                    url :  queryurl
                            }).success(function(data, status, headers, config) {
                                $scope.datalistsstyles = data.d.details;
//                            alert("queryurl "+queryurl+"......................\n"+JSON.stringify(data.d.details)+"...style...");
                                $scope.numberOfPages = function() {
                                return Math.ceil($scope.datalistsstyles.length / $scope.pageSize);
                            };
                            hideOverlay();
                            if (data === error){
                                alert(data);
                                hideOverlay();
                            }

                            }).error(function(data, status, headers, config) {
                                hideOverlay();
                                alert("No data available! Problem fetching the data.");
                            });
                        };
                        
                    $scope.showBlocks = function(){
                        showOverlay();
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
                                url : getHost()+'getAllBlocksForCompany'
                        }).success(function(data, status, headers, config) {
                            hideOverlay();
//                            alert(JSON.stringify(data.d.details)+".....blocks.....");
                            $scope.datalists = data.d.details;
//                            alert(JSON.stringtify(data));
//                            document.getElementById('stlimg').src = "images/sidebar/Icons_styleButton.svg";
//                            document.getElementById('blkimg').src = "images/sidebar/Icons_blockButton_blue_new.svg";
//                            document.getElementById('edtimg').src = "images/sidebar/Icons_editButton.svg";
//                            document.getElementById('edt').style.backgroundColor = 'transparent';
//                            document.getElementById('stl').style.backgroundColor = 'transparent';
//                            document.getElementById('blk').style.backgroundColor = '#fff';
                            $scope.numberOfPages = function() {
                                return Math.ceil($scope.datalists.length / $scope.pageSize);
//                                hideOverlay();
                            };
                            if (data === error){
                                alert(data);
                                hideOverlay();
                            }                            
//                            hideOverlay();
                        }).error(function(data, status, headers, config) {
                            hideOverlay();
                            alert("No data available! Problem fetching the data.");
                        });
                    };
                    
                    $scope.showImageOfBlock = function(id, mind_body_query){
                        showOverlay();
//                        alert(id);
//                        alert(mind_body_query);
                        $(".block-button").addClass("hide");
                        $("#blockdiv li").removeClass("block-slat-active");
                        $("#blockdiv li").addClass("block-slat");
                        $("#"+id).removeClass("block-slat");
                        $("#"+id).addClass("block-slat-active");
                        $("#div2"+id).removeClass("hide");
                        $("#stylelist").css("display", "none");
                        $("#blklist").css("display", "block");
                        $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                        $(":button").removeAttr("disabled");
                        $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                        temp_block_id = id;
                        temp_mind_body_query = mind_body_query;
                        $http.get(getHost()+'getAllEmailBlockModelsByBlockId?emailBlockId=' + id).success(function(data, status){
                        var jsondataDefault = data;
                        hideOverlay();
                           
                            ///alert(id);
//                            var allLayoutFilename = [];
//                            $(jsondataDefault).each(function (i, val)
//                            {
//                                var i = 0;
//                                $.each(val, function (k, v) 
//                                {
//                                    allLayoutFilename[i] = v;
//                                    i++;
//                                });
//                            });
                            temp_style_id = data.d.details[0].emailBlockModelLookupId;
//                            temp_style_layout = allLayoutFilename[1];  
                            //$("#" + id).attr('onclick', "showSomething('" + id + "','" + allLayoutFilename[0] + "','" + allLayoutFilename[1] + "','" + mind_body_query + "')");
//                           alert("smtng...block id.."+temp_block_id);
//                           alert("smtng...style id.."+temp_style_id);
//                           alert("smtng...style.."+temp_style_layout);
//                           alert("smtng...mind_body_query.."+temp_mind_body_query);
                        }).error(function(error){
                            alert(JSON.stringify(error));
                            hideOverlay();
                        });                        
                        $("#addblkbtn").prop("disabled", true);
                     };
                    
                    $scope.showDataTemp = function(id){
//                        alert(temp_block_id+" temp_mind_body_query .."+temp_mind_body_query);
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
                        if (mind_body_query == 0)
                        {
                            mindbodydataId = "0";
                            showText(temp_style_id);
                        }
                        else
                        {
                            $("#fade").show();
                            $('#slider-button').click();
                            $(".scrollydiv").hide();
                            $("#loadingGifformindbody").show();
                            $scope.curPage = 0;
                            $scope.pageSize = 4;
                            $http({
                            method : 'GET',
                            url :getHost()+ '/externalContent/isActivated?externalSourceKeywordLookupId='+mind_body_query
                            }).success(function(data, status, headers, config) {
                                var minddata= JSON.stringify(data.d.details);
                                if(minddata === "[true]"){
                                    $http({
                                        method : 'GET',
                                        url :getHost()+ '/externalContent/getListData/'+mind_body_query
                                    }).success(function(data, status, headers, config) {
                                            var parseData=JSON.parse(data.d.details);
                                            $scope.datalists2 = parseData;
//                                            $scope.numberOfPages = function() 
//                                            {if
//                                                return Math.ceil($scope.datalists2.length / $scope.pageSize);
//                                            };
//                                            if (data === error)
//                                            {
//                                                alert(data);
//                                            }
                                            $("#loadingGifformindbody").hide();
                                            $(".scrollydiv").show();
                                            $("#clsbtn").css("display", "block");
                                            $("#addblkbtn").prop('disabled', true).css("background-color", "#e3e3e3").css("color", "#9c9da1");
                                    }).error(function(data, status, headers, config) {
                                        alert("No data available, problem fetching the data");
                                    });
                                }
                            }).error(function(data, status, headers, config) {
                                alert("No data available, problem fetching the data");
                            });
                        }
                        $scope.showStyles();
                    };
                    
                    $scope.select_category_details = function(id) {
                        mindbodydataId = id;
                        $("#stylelist").css("display", "block");
                        $scope.showStyles();
                        showText(temp_style_id);
                        $("#filtercontainer").hide();
                        $("#fade").hide();
                        $("#slider-button").click();
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
                var layout_mapper_url = "";
                if (block_clicked == "true"){
                    currentBlockID = temp_block_id;
                    currentMindbodyQuery = temp_mind_body_query;
                }   
                if ((mindbodydataId === "") || (mindbodydataId === null) || (mindbodydataId === "null")|| (mindbodydataId === "0") || (typeof (mindbodydataId) === "undefined")){
                    layout_mapper_url = getHost()+"externalContent/getLayoutEmailModelById?emailModelId=" + id+"&isBlock="+block_clicked+"&externalDataId=0";
                } 
                else 
                {
                    layout_mapper_url = getHost()+"externalContent/getLayoutEmailModelById?emailModelId=" + id+"&isBlock="+block_clicked+"&externalDataId="+mindbodydataId;
                }
                
//                alert("layout_mapper_url... "+layout_mapper_url);

                 $.ajax({
                    method : 'GET',
                    url :layout_mapper_url
                    }).success(function(data, status, headers, config) {
                        var emailData=JSON.parse(data.d.details);
//                        alert(JSON.stringify(data)+" ...... show text121 ....");
                            if (block_clicked === "false"){
                            var editorHtml = $('#edit').froalaEditor('html.get');
                                    if (editorHtml.contains('id="defaultblock1"')){
                            var jHtmlObject = jQuery(editorHtml);
                                    var editor = jQuery("<p>").append(jHtmlObject);
                                    editor.find("#defaultblock1").remove();
                                    editorHtml = editor.html();
                            }
                            styleHtml = '<div id=defaultblock1 onclick="selecterBlockId(defaultblock1,0)">' + emailData.htmldata + '</div>';
                                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
    //                                                   $("#defaultblock1").empty().append(data.htmldata);
                            }
                            else{
                                var editorHtml = $('#edit').froalaEditor('html.get');
                                if (editorHtml.contains('id="' + addblockid + '"')){
                                    var jHtmlObject = jQuery(editorHtml);
                                    var editor = jQuery("<p>").append(jHtmlObject);
                                    BlockHtml = '<div id=' + addblockid + ' onclick=selecterBlockId(' + addblockid + ',' + temp_block_id + ')>' + emailData.htmldata + '</div>';
                                    editor.find("#" + addblockid).replaceWith(BlockHtml);
                                    editorHtml = editor.html();
                                    $('#edit').froalaEditor('html.set', '' + editorHtml + '');
                                }
                                else
                                {
                                    BlockHtml = '<div id=' + addblockid + ' onclick=selecterBlockId(' + addblockid + ',' + temp_block_id + ')>' + emailData.htmldata + '</div>';
                                    $('#edit').froalaEditor('html.set', '' + editorHtml + '' + BlockHtml + '');
                                }
                            }
                    }).error(function(error) {
                        alert("..");
                        alert(JSON.stringify(error))
                    });

                
//                
//                $.ajax({
//                        method : 'GET',
//                        url: layout_mapper_url,
//                        dataType: 'json',
//                        contentType: 'application/json',
//                        mimeType: 'application/json',
//                        data: JSON.stringify(data),
//                        success: function (data) {
//                            alert(JSON.stringify(data)+" ...... show text ....");
//                            if (block_clicked === "false"){
//                            var editorHtml = $('#edit').froalaEditor('html.get');
//                                    if (editorHtml.contains('id="defaultblock1"')){
//                            var jHtmlObject = jQuery(editorHtml);
//                                    var editor = jQuery("<p>").append(jHtmlObject);
//                                    editor.find("#defaultblock1").remove();
//                                    editorHtml = editor.html();
//                            }
//                            styleHtml = '<div id=defaultblock1 onclick="selecterBlockId(defaultblock1,0)">' + data.d.details.htmldata + '</div>';
//                                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
//    //                                                   $("#defaultblock1").empty().append(data.htmldata);
//                            }
//                            else{
//                                var editorHtml = $('#edit').froalaEditor('html.get');
//                                if (editorHtml.contains('id="' + addblockid + '"')){
//                                    var jHtmlObject = jQuery(editorHtml);
//                                    var editor = jQuery("<p>").append(jHtmlObject);
//                                    BlockHtml = '<div id=' + addblockid + ' onclick=selecterBlockId(' + addblockid + ',' + temp_block_id + ')>' + data.d.details.htmldata + '</div>';
//                                    editor.find("#" + addblockid).replaceWith(BlockHtml);
//                                    editorHtml = editor.html();
//                                    $('#edit').froalaEditor('html.set', '' + editorHtml + '');
//                                }
//                                else
//                                {
//                                    BlockHtml = '<div id=' + addblockid + ' onclick=selecterBlockId(' + addblockid + ',' + temp_block_id + ')>' + data.d.details.htmldata + '</div>';
//                                    $('#edit').froalaEditor('html.set', '' + editorHtml + '' + BlockHtml + '');
//                                }
//                            }
//                        },
//                        error: function (error) {
//                            alert(JSON.stringify(error))
//                        }
//                });
            }

            function addblock(){
            document.getElementById("addblkbtn").style.backgroundColor = "#0f76a6";
                    document.getElementById("addblkbtn").style.color = "#f6f7f7";
            }

            function selecterBlockId(selectblock, blockid){
            var selectblockid = selectblock.id;
            temp_block_id =blockid;
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
<div id="fade" class="black_overlay"></div>
<%--<jsp:include page="emailpreview.jsp" />--%>  
<body ng-app="myapp"> 
    <div id="boxes" >
        <div id="dialog" class="window" >
        </div>
        <div id="mask"></div>
    </div> 
    <input type="hidden" id='subCategoryIdTag' value="<%=subCategoryId%>"/>
    <input type="hidden" id='userid' value="<%= user_id%>"/>
    <input type="hidden" id='draftid' value="<%= draft_id%>"/>
    <input type="hidden" id='mindbodydata' value="<%= mindbodyId%>"/>
    <input type="hidden" id='categoryIdTag' value="<%= categoryId%>"/>
    <input type="hidden" id='LookupId' value="<%= LookupId%>"/>
    <input type="hidden" value="<%=email_subject%>" id="email_subject"/>
    <input type="hidden" value="<%=emailSubject%>" id="emailSubjectTag"/>
    <!--SideNav-->
    <div class="content-main" ng-controller="MyController">   
    <jsp:include page="emaileditormindbodypopup.jsp"/>    
    <!--Top Nav-->   
    <div class="top-nav-full">
        <div class="page-title-bar col-1of1"> 
            <a class=" exit-button-icon" href="emailsubjects?categoryId=<%=categoryId%>&subCategoryId=<%=categoryId%>&mindbodyId={{mindbody.id}}&LookupId=<%=LookupId%>&emailSubject=<%=emailSubject%>">    
                <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                </div>
            </a>
            <div class="page-title-with-back page-title-font">Email Editor</div>
            <div class="page-cta-container">
                <a href="" class="gray-button fleft pushright">
                    <!--<div class="nounderline md-button" onclick="show('iphone');">  Mobile Preview</div>-->  
                    <div class="nounderline md-button" id="emailpreview">  Preview Email</div>  
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
                    $("#emailpreview").click(function(){              
                        $("#email_previewdiv").show();
                        var sendData = {
                                    htmlString: $('#edit').froalaEditor('html.get'),
                                    iframeName: rendomIframeFilename.toString()
                                };
                                
                        $.ajax({
                                method: "POST",
                                url: getHost() + "email/previewServlet",                                
                                data: JSON.stringify(sendData),
                                success: function (responseText) {
//                                    alert(JSON.stringify(responseText.d.details));
                                    $("#dynamictable5").empty();
                                    $("#dynamictable6").empty();
                                    var iframePath = getHost() +"download/HTML?fileName="+rendomIframeFilename+".html";
                                    $("#dynamictable5").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                    $("#dynamictable6").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                }
                        }).error(function (error){alert(JSON.stringify(error));});
                        $("#fade").show();
                    });
                    
                    $("#addblkbtn").click(function (){
                    $("#tabs-4").css("display", "none");
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
                                    url:'getAllUserMarketingProgramsBySessionUserId',
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
                                    $('#mask').css({'height':maskHeight});
                                    $('#mask').css('width','100%');
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
    $("#saveToDraft").click(function (){
//        $("#saveToDraft").unbind('click');
        var email_subject = $("#emailSubjectTag").val();
        var lookupId = $("#LookupId").val();
        var mindbodyData = $("#mindbodydata").val();  
        var categoryId = $("#categoryIdTag").val();
        var subCategoryId = $("#subCategoryIdTag").val(); 
        $.ajax({
            url: getHost() + "/email/previewServlet",
            method: "post",
            data:JSON.stringify({
                htmlString: $('#edit').froalaEditor('html.get'),
                iframeName: rendomIframeFilename.toString()
            }),
            success: function (responseText) {
                $("#previewcontent").empty();
                $("#previewcontent").append(responseText.d.details);
//                var LookupId=$("#LookupId").val();
                if (draft_id == "0" || draft_id == "null" || draft_id == null){
                $.ajax({
                    url: getHost() + "saveEmailDrafts",
                    method: "post",
                    data:JSON.stringify({
                    bodyString : $('#edit').froalaEditor('html.get'),
                    lookupId : lookupId,
                    mindbodyData : mindbodyData,
                    categoryId : categoryId,
                    subCategoryId : subCategoryId,
                    emailSubject : email_subject
//                        mindbodyId: mindbodydata,
//                        lookupId: LookupId 
                    }),
                    success: function (responseText) {
                        if (responseText != "0"){
                        alert("Draft saved successfully.");
                        $("#saveToDraft").bind('click');
                                document.location.href = "dashboard";
                        } else {
                            alert("There was a problem while saving the draft! Please try again later.");
                        }
                    }
                });
            } else {
//                    alert(draft_id+".."+lookupId+".."+mindbodyData+".."+categoryId+".."+subCategoryId+".."+email_subject);
                $.ajax({
                    url: getHost() + "updateEmailDraft",
                    method: "post",
                    data:JSON.stringify({
                        draftId: draft_id.toString(),
                        bodyString :$('#edit').froalaEditor('html.get'), 
                        lookupId : lookupId,
                        mindbodyData : mindbodyData,
                        categoryId : categoryId,
                        subCategoryId : subCategoryId,
                        emailSubject : email_subject
                    }),
                    success: function (responseText) {
                    if (responseText == "true"){
                    alert("Draft updated successfully.");
                            document.location.href = "dashboard";
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
//        alert($("#emailSubjectTag").val());
        var email_subject = $("#emailSubjectTag").val();
        var lookupId = $("#LookupId").val();
        var mindbodyData = $("#mindbodydata").val();  
        var categoryId = $("#categoryIdTag").val();
        var subCategoryId = $("#subCategoryIdTag").val(); 

            var sendData=JSON.stringify({
            htmlString: $('#edit').froalaEditor('html.get'),
            iframeName: rendomIframeFilename.toString()
            });
        $.ajax({
            url: getHost() + "/email/previewServlet",
            method: "POST",
            data: sendData,
            success: function (responseText) {
            $("#previewcontent").empty();
                $("#previewcontent").append(responseText.d.details);               
                // added by Syed Ilyas 16 dec 2015 - saves draft
                if (draft_id == "0" || draft_id == "null" || draft_id == null)
                {
                    $.ajax({
                    url: getHost() + "saveEmailDrafts",
                    method: "post",
                    data:JSON.stringify({
                    bodyString : $('#edit').froalaEditor('html.get'), 
                    lookupId : lookupId,
                    mindbodyData : mindbodyData,
                    categoryId : categoryId,
                    subCategoryId : subCategoryId,
                    emailSubject : email_subject
                    }),
                    success: function (responseText) {
                        if (responseText != "0"){
                        document.location.href = "emaillistselection?draftid=" + responseText + "&subject=" + email_subject+"&iframeName="+rendomIframeFilename+"&categoryId="+categoryId+"&subCategoryId="+subCategoryId+"&emailSubject="+email_subject+"&mindbodyId="+mindbodydata+"&LookupId="+LookupId;                        } else 
                        {
                            alert("There was a problem while saving the draft! Please try again later.");
                        }
                    }
                    });
                } 
                else 
                {
                    $.ajax({
                        url: getHost() + "updateEmailDraft",
                        method: "post",
                        data:JSON.stringify({
                            draftId: draft_id.toString(),
                            bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                            lookupId : lookupId,
                            mindbodyData : mindbodyData,
                            categoryId : categoryId,
                            subCategoryId : subCategoryId,
                            emailSubject : email_subject
                        }),
                        success: function (responseText) {
                            if (responseText == "true")
                            {
                                document.location.href = "emaillistselection?draftid=" + draft_id + "&subject=" + email_subject+"&iframeName="+rendomIframeFilename;
                            } else
                            {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        }
                    });
                }
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
                    <div id='edit' class="editorclass" style="margin-top: 5px;">
                    </div>
                </div>
            </div>
            <div class="emailEditor-rightCol fright rightcoloumn">
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
                <div class="email-Block-Selection right-email-select">
                    <div class="email-Block-Header" id="blockdivheader">Select a block to add:</div>
                    <div class="email-Block-Header" id="styledivheader">Select a style for this block:</div>
                    <div class="block-selection-divider"></div>
                    <ul class="block-list" id="blockdiv"  >
<!--                        <li class="block-slat-active" >
                            <div class="block-name">Header Block</div>
                            <div class="block-button" ng-click="showDataTemp()">Add Block</div>                            
                        </li>-->
                        <li class="block-slat" ng-repeat="blocks in datalists" id="{{blocks.emailBlockId}}" ng-click="showImageOfBlock(blocks.emailBlockId, blocks.externalSourceKeywordLookupId)">
                            <div class="block-name" id="blklist----{{blocks.emailBlockId}}" >{{blocks.emailBlockName}}</div>                            
                            <div class="block-button hide" ng-click="showDataTemp(blocks.emailBlockId)" id="div2{{blocks.emailBlockId}}">Add Block</div>
                        </li>
                    </ul>
                    
                    <ul class="block-list" id="stylediv">
                        <li ng-repeat="styles in datalistsstyles.slice().reverse()" class="style-slat" id="stylelistid{{styles.modelId}}" ng-click="addActive('stylelistid'+styles.modelId)">
                            <div class="block-name">
                                <img id="{{styles.modelId}}" class="img-responsive lookchooser5 ptr" src="/BrndBot/downloadImage?imageName={{styles.imageFileName}}&imageType=EMAIL_TEMPLATE_IMAGE&companyId=0" onclick="showText('{{styles.modelId}}')" width="100%" />
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>  
    
    <div id="fade" class="black_overlay" ></div>
    <!--Top Nav-->  
    <div id="email_previewdiv">
        <div class="pop-up-background popup-screen">
            <div class="pop-up-container-emailPreview"> 
                <a class=" exit-button-detail-ep link svg" href="" id="closePrev">
                    <img type="image/svg+xml" src="images/close.svg" class="closeemailpreview" style="cursor:pointer;"> </img>
                </a>
                <div class="pop-up-title-emailpreview "> 
                    <div class="emailPreview-header fleft">Email Preview</div>
                </div>
                <!--inner-->
                <div class="pop-up-inner-ep height400">
                    <div class="emailPreviews col-1of1 fleft"> 
                        <div class="emailPreview-desktop-col  fleft">
                            <div class="emailPreview-headers">Desktop Preview</div>
                            <!--<div class="iphoneshow img-responsive" id="deskpreview" style="display: block; height: 300px; width: 295px; margin-left: 215px; margin-top: 50px; border-color: transparent; background-color: rgb(255, 255, 255); background-size: contain; background-repeat: no-repeat;">-->
                                <div class="content">  
                                    <div id="dynamictable5" style="position: relative; width: 100%; border: none; overflow: scroll; height: 400px;background-color: rgb(255, 255, 255);" src="">
                                    
                                    </div>                   
                                </div>
                            <!--</div>-->
                            <div class="desktopshow" style="display:none;">
                                <iframe id='dynamictable' class="desktoppreview" src=''></iframe> 
                            </div>                        
                        </div>
                        <div class="emailPreview-mobile-col fleft">
                            <div class="emailPreview-headers">Mobile Preview</div>
                            <div class="iphoneshow img-responsive" id="mobpreview" style="display: block; height: 370px; width: 100%; margin-left: 6px; margin-top: 0px; border-color: transparent; background-color: rgb(255, 255, 255); background-size: contain; background-repeat: no-repeat;">
                                <div class="content">  
                                    <div  id="dynamictable6" style="position: relative; width: 100%; height: 400px; overflow: scroll; border: none; background-color: rgb(255, 255, 255);">
                                    
                                    </div>                
                                </div>
                            <!--</div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>           
    <!--CTA Bar-->
    <div class="bottom-cta-bar" id="bottomdiv">
        <div class="bottom-cta-button-container-lg">
            <a href=""><!--emaillistselection-->
                <div class="bottom-continue-button button-text-1" id="saveButton">Continue</div>
            </a>
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