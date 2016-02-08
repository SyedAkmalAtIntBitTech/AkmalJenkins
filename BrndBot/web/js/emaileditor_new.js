/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
                        draft_id = $("#draftid").val();
                        if(draft_id==="0")
                            draft_id="";
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
                    alert("No data available! Problem fetching the data. 2");
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
                            alert("No data available! Problem fetching the data. 3");
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
                            alert("No data available! Problem fetching the data. 4");
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
                            if (data === error){
                    alert(data);
                    }
                    $("#loadingGifformindbody").hide();
                            $("#clsbtn").css("display", "block");
                            $("#addblkbtn").prop('disabled', true).css("background-color", "#e3e3e3").css("color", "#9c9da1");
                            $("#tabs-4").css("width", "830px").css("position", "fixed").css("margin-left", "-600px").css("top", "0px").show("slide", { direction: "right" }, 1000);
                    }).error(function(data, status, headers, config) {
                    alert("No data available! Problem fetching the data. 1");
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
            });
            
  $(document).ready(function(){
     
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
  });