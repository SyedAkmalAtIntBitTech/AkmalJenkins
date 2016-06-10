emailFlowApp.controller("emailController", ['$scope', '$window', '$location', 'categoryFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', function ($scope, $window, $location, categoryFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService) {
        $scope.emailChannelId = 3;
        $scope.printChannelId = 2;
        $scope.imageChannelId = 1;
        $scope.forward = "";
        $scope.categoryId = "";
        $scope.lookupId = "";
        $scope.externalSourceName = "";
        $scope.subCategoryId = "";
        $scope.mindbodyid = "";
        $scope.emailsubject = "";
        $scope.emailSubjectError = "";
        $scope.sharedData = "";
        $scope.redirect = function (redirect, categoryId, mindbody, lookupId, mindbodyid)
        {
            $scope.lookupId = lookupId;
            if (mindbody === '')
            {
                $scope.categoryId = categoryId;
            }
            if (mindbody === 'Mindbody')
            {
                $scope.externalSourceName = 'mindbody';
                redirect = $scope.forwardone;
                $scope.lookupId = lookupId;
                $scope.subCategoryId = categoryId;
            }
            if (mindbody === 'nonmindbody')
            {
                if (redirect === 'emailexternalsource')
                {
                    redirect = $scope.forwardtwo;
                    $scope.subCategoryId = categoryId;
                }
                $scope.categoryId = categoryId;
            }
            if (mindbodyid !== '')
            {
                $scope.mindbodyid = mindbodyid;
            }
            $location.path("/" + redirect);
        };
        $scope.redirectToEmailFlow = function (forwardone)
        {
            redirectFactory.redirectFlowTo(forwardone);
            $window.location = getHost() + "user/" + forwardone;
            var emailsubject = $scope.emailsubject;
            if (emailsubject === '')
            {
                $scope.emailSubjectError = "Email Subject Required!";
            }
        };
        $scope.getCategories = function (forwardone)
        {
            categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
                $scope.pageName = "emailcategory";
                $scope.header = "Select Category";
                $scope.forwardone = forwardone;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getSubCategories = function (forwardone, forwardtwo)
        {
            $scope.pageName = "emailsubcategory";
            subCategoryFactory.allSubCategoriesGet($scope.categoryId).then(function (data) {
                $scope.pageName = "emailsubcategory";
                $scope.header = "Select Subcategory";
                $scope.forwardone = forwardone;
                $scope.forwardtwo = forwardtwo;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getActive = function (lookupId)
        {
            externalContentFactory.activatedGet(lookupId).then(function (data) {
                var minddata = JSON.stringify(data.d.details);
                if (minddata === "[true]") {
                    externalContentFactory.listDataGet(lookupId).then(function (data) {
                        var parseData = JSON.parse(data.d.details);
                        $scope.mindbodylist = parseData.mindbody_data;
                    });
                }
                else
                {

                }
            });
        };
        $scope.redirectFlow = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };

        $scope.redirectToMarketingProgram = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };

  }]);

        //**************************************** emaileditor js start **********************//

$(document).ready(function () {
        var jsondata;
        var selectedDivId;
        var block_clicked = "false";
        var block_id = "0";
        var blockIdSelected = "defaultblock1";

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
        var prevSliderDialog = "#emaileditorexternalpopup";
        var sliderDialog = "#emaileditorexternalpopup";
        var externalKeywordId = "";
        var externalDataId = "";

        function showOverlay() {
            $("#loadoverlaydiv").show();
        }
        function hideOverlay() {
            $("#loadoverlaydiv").hide();
        }
        function editFooter() {
            $("#emailFooterPopup").show();
        }
        function hideEmailFooterPopup() {
            $("#emailFooterPopup").hide();
        }
        $(document).ready(function () {
            showOverlay();
            $("#closePrev").click(function () {
                $("#email_previewdiv").hide();
                $('#fade').hide();
            });

            $("#mindbodyclose").click(function () {
                $("#fade").hide();
                $('#slider-button').click();
            });

//            $slider=2;
            $a = 0;
            $edit = 0;
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

//                draft_id =$scope.draftid;
            draft_id = 0;
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
                    content: 'Are you sure you want to delete this style?',
                    dialog: 'confirm',
                    onConfirm: function ($container) {
                        var tempSelectedBlockId = addblockid;
                        $("#" + tempSelectedBlockId).remove();
                        $(".imagename").find('option').remove().end();
                        $(".blockname").find('option').remove().end();
                    },
                    onCancel: function ($container) {

                    }
                });
            });

            $("#sortDownBlock").click(function () {
                var current = $("#" + addblockid);
                current.next().after(current);
            });
            var subCategoryId = $("#subCategoryIdTag").val();
            $.ajax({
                type: 'GET',
//                url: "GetLayoutStyles?editorType=email",
                url: getHost() + "/getAllEmailModelsBySubCategoryId?subCategoryId=" + subCategoryId,
                dataType: 'json',
                success: function (data) {
//                    alert(JSON.stringify(data));
                    var blockList = data.d.details.reverse();
                    var emailModelId = blockList[0].modelId;
                    showText(emailModelId);
                    angular.element(document.getElementById('MyController')).scope().getEmailDrafts();
//                    $('#edit').froalaEditor('html.insert','<div id=defaultblock1 onclick=selecterBlockId(defaultblock1,temp_block_id);></div>"', true);
//                    $(".fr-element").append("<div id=defaultblock1 onclick=selecterBlockId('defaultblock1'," + temp_block_id + ");></div>");
                }, error: function (data) {
                    alert(JSON.stringify(data));
                }
            });
        });

        function showSomething(block_id_temp, id, style, mind_body_query) {

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

        function showText(id) {
            var layout_mapper_url = "";
            if (block_clicked == "true") {
                currentBlockID = temp_block_id;
                currentMindbodyQuery = temp_mind_body_query;
            }
            if (($scope.mindbodyid === "") || ($scope.mindbodyid === null) || ($scope.mindbodyid === "null") || ($scope.mindbodyid === "0") || (typeof ($scope.mindbodyid) === "undefined")) {
                layout_mapper_url = getHost() + "externalContent/getLayoutEmailModelById?emailModelId=" + id + "&isBlock=" + block_clicked + "&externalDataId=0";
            }
            else
            {
                layout_mapper_url = getHost() + "externalContent/getLayoutEmailModelById?emailModelId=" + id + "&isBlock=" + block_clicked + "&externalDataId=" + $scope.mindbodyid;
            }

            $.ajax({
                method: 'GET',
                url: layout_mapper_url
            }).success(function (data, status, headers, config) {
                var emailData = JSON.parse(data.d.details);
                if (block_clicked === "false") {
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="defaultblock1"')) {
                        var jHtmlObject = jQuery(editorHtml);
                        var editor = jQuery("<p>").append(jHtmlObject);
                        editor.find("#defaultblock1").remove();
                        editorHtml = editor.html();
                    }
                    styleHtml = '<div id=defaultblock1 onclick="selecterBlockId(defaultblock1,0)">' + emailData.htmldata + '</div>';
                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
                }
                else {
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="' + addblockid + '"')) {
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
            }).error(function (error) {
                alert(JSON.stringify(error))
            });
        }
//function UserFooter( fb, twitter, website, instagram , address) {
//                   var returnFooter = "";
//                           var footer = '<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#EEEEEE\" style=\"border-collapse:collapse;\"><tr><td valign=\"top\"> <center style=\"width: 100%;\"> <div style=\"max-width: 680px;\"> <!--[if (gte mso 9)|(IE)]> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" align=\"center\"> <tr> <td> <![endif]--> <!-- Atom Body: BEGIN --> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#EEEEEE\" width=\"100%\" style=\"max-width: 680px;\"> <tr> <td style=\"padding-top:15px;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\" style=\"max-width: 300px; background-color:#inherit\" class=\"mobile-padding\"> <tr>';
//                           var footerFB = '<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerFB$$$\"><img src=\"" + getHost() +  " images/Fa c ebook_Filled.png" + "\" alt= \ "Facebook Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Facebook </td> </tr> </table> </td>';
//                           var footerTwitter = '<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerTwitter$$$\"><img src=\"" + getHost ( ) + "imag e s/Twitter_Filled.png" + "\"   alt=\"Twitter Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Twitter </td> </tr> </table> </td>';
//                           var footerWebsite = '<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerWebsite$$$\"><img src=\"" + getHost() +  " images/Website_Filled.png"  +  "\" alt=\"Website Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Website </td> </tr> </table> </td>';
//                           var footerInstagram = '<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerInstagram$$$\"><img src=\"" + getHost() + "images/Insta_Filled.png" + "\" alt=\"Instagram Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Instagram </td> </tr> </table> </td>';
//                           var footerMiddle = '</tr> </table> </td> </tr>';
//                           var footerAddress = '<!--HEADER: BEGIN--> <tr> <td style=\"font-family: sans-serif; font-size: 12px; mso-height-rule: exactly; line-height: 120%; text-align:center; color: #555555; padding: 20px 55px 20px 55px;\" class=\"fluid mobile-padding\"> $$$footerAddress$$$ </td> </tr> <!--HEADER: END-->';
//                           var footerClose = '</table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </div> </center> </td></tr></table>';
//                           returnFooter = footer;
//                           if (fb != "")
//                           returnFooter += footerFB.replace("$$$footerFB$$$", fb);
//                           if (twitter != "" && typeof twitter != "undefined")
//                           returnFooter += footerTwitter.replace("$$$footerTwitter$$$", twitter);
//                           if (website != "" && typeof website != "undefined")
//                           returnFooter += footerWebsite.replace("$$$footerWebsite$$$", website);
//                           if (instagram != "" && typeof instagram != "undefined")
//                           returnFooter += footerInstagram.replace("$$$footerInstagram$$$", instagram);
//                           returnFooter += footerMiddle;
//                           if (address != "" && typeof address != "undefined")
//                           returnFooter += footerAddress.replace("$$$footerAddress$$$", address);
//                           returnFooter += footerClose;
//                           return returnFooter;
//                   }
//
        $("#emailpreview").click(function () {
            $("#" + addblockid).css("border", "");
            $.ajax({
                method: 'GET',
                url: getHost() + 'settings/getAllPreferences'
            }).success(function (data, status) {
                var footerData = JSON.parse(data.d.details);
                if (!footerData.userProfile) {
                    $("#emailFooterPopup").show();
                }
                else {
                    if (!footerData.userProfile.address) {
                        $("#emailFooterPopup").show();
                    } else {

                        $("#email_previewdiv").show();
                        var footer = UserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                                footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                                footerData.userProfile.address);
                        var sendData = {
                            htmlString: $('#edit').froalaEditor('html.get') + footer,
                            iframeName: rendomIframeFilename.toString()
                        };
                        $.ajax({
                            method: "POST",
                            url: getHost() + "email/previewServlet",
                            data: JSON.stringify(sendData),
                            success: function (responseText) {
                                $("#dynamictable5").empty();
                                $("#dynamictable6").empty();
                                var iframePath = getHost() + "download/HTML?fileName=" + rendomIframeFilename + ".html";
                                $("#dynamictable5").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                $("#dynamictable6").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                            }
                        }).error(function (error) {
                            alert(JSON.stringify(error));
                        });
                        $("#fade").show();
                    }
                }
            });
        });
        $("#addblkbtn").click(function () {
            $("#tabs-4").css("display", "none");
            $("#clsbtn").css("display", "none");
        });
        $("#boxclose").click(function () {
            $("#blocktab").click();
            $("#tabs-4").hide();
        });
        $("#styletab").click(function () {
            $("#addblkbtn").prop('disabled', true);
            $("#stylelist").css("display", "block");
            $("#blklist").css("display", "none");
            $("#styletab").css("background-color", "#ffffff").css("color", "#19587c");
            $("#blocktab").css("background-color", "transparent").css("color", "#19587c");
        });
        $("#blocktab").click(function () {
            $("#stylelist").css("display", "none");
            $("#blklist").css("display", "block");
            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
        });
//                   $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick']);
//                   $(function () {
//                   $.ajax({
//                   url:getHost() + 'getAllUserMarketingProgramsBySessionUserId',
//                           method:'Get',
//                           dataType: 'json',
//                           contentType: 'application/json',
//                           mimeType: 'application/json',
//                           success: function (responseText) {
//                           $('#edit').froalaEditor({key: FroalaLicenseKey, linkList:responseText});
//                           }
//                   });
//                   });

        function show(id) {
            var getId = id;
            var dynamicStyle, dynamicWidth, dynamicHeight;
            var imageUrl = "images/Phone.svg";
            var id = '#dialog';
            var maskHeight = $(document).height();
            var maskWidth = $(window).width();
            $('#mask').css({'height': maskHeight});
            $('#mask').css('width', '100%');
            $('#mask').fadeIn(500);
            $('#mask').fadeTo("slow", 0.95);
            $("body").css("overflow", "none");
            var winH = $(window).height();
            var winW = $(window).width();
            $(id).css('top', winH / 2 - $(id).height() / 2);
            $(id).css('left', winW / 2 - $(id).width() / 2);
            $(id).fadeIn(2000);
            $('.window .close').click(function (e) {
                e.preventDefault();
                $('#mask').hide();
                $('.window').hide();
            });
            $('#mask').click(function () {
                $(this).hide();
                $('.window').hide();
            });
            $.ajax({
                method: 'GET',
                url: getHost() + 'settings/getAllPreferences'
            }).success(function (data, status) {
                var footerData = JSON.parse(data.d.details);
                var footer = UserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                        footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                        footerData.userProfile.address);
                $.ajax({
                    url: getHost() + "PreviewServlet",
                    method: "post",
                    data: {
                        htmlString: $('#edit').froalaEditor('html.get') + footer, //$(".fr-element").html(),
                        iframeName: rendomIframeFilename
                    },
                    success: function (responseText) {
                        if (getId === "iphone") {
                            $('.window').css("top", "110px");
                            dynamicWidth = "275";
                            dynamicHeight = "459";
                            $(".window").empty();
                            $(".window").append("<div id=imageDivPopup style='width:" + dynamicWidth + "px;height:" + dynamicHeight + "px;'></div>");
                            $("#imageDivPopup").css("background-image", "url(" + imageUrl + ")").css("background-size", "100% 100%");
                            $("#imageDivPopup").append("<iframe style='width:640px;height:1024px;position:relative;top:-295px;left:-183px;-webkit-transform: scale(0.3495);background-color:#FFF;' src='/BrndBot/DownloadHtmlServlet?file_name=" + rendomIframeFilename + ".html'></iframe>");
                        }
                    }
                });
            });
        }
//
        function hlt() {
            var $li = $('#blklistid li').click(function () {
                $li.removeClass('border-highlight');
                $(this).addClass('border-highlight');
            });
        }
        ;
        $(document).ready(function () {
            $("#saveToDraft").click(function () {
                $("#" + addblockid).css("border", "");
                var email_subject = $("#emailSubjectTag").val();
                var lookupId = $("#LookupId").val();
                var mindbodyData = $("#mindbodydata").val();
                var categoryId = $("#categoryIdTag").val();
                var subCategoryId = $("#subCategoryIdTag").val();
                $.ajax({
                    method: 'GET',
                    url: getHost() + 'settings/getAllPreferences'
                }).success(function (data, status) {
                    var footerData = JSON.parse(data.d.details);
                    var footer = UserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                            footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                            footerData.userProfile.address);
                    $.ajax({
                        url: getHost() + "/email/previewServlet",
                        method: "post",
                        data: JSON.stringify({
                            htmlString: $('#edit').froalaEditor('html.get') + footer,
                            iframeName: rendomIframeFilename.toString()
                        }),
                        success: function (responseText) {
                            $("#previewcontent").empty();
                            $("#previewcontent").append(responseText.d.details);
                            if (draft_id == "0" || draft_id == "null" || draft_id == null) {
                                $.ajax({
                                    url: getHost() + "saveEmailDrafts",
                                    method: "post",
                                    data: JSON.stringify({
                                        bodyString: $('#edit').froalaEditor('html.get'),
                                        lookupId: lookupId,
                                        mindbodyData: mindbodyData,
                                        categoryId: categoryId,
                                        subCategoryId: subCategoryId,
                                        emailSubject: email_subject
                                    }),
                                    success: function (responseText) {
                                        if (responseText != "0") {
                                            alert("Draft saved successfully.");
                                            $("#saveToDraft").bind('click');
                                            document.location.href = "dashboard";
                                        } else {
                                            alert("There was a problem while saving the draft! Please try again later.");
                                        }
                                    }
                                });
                            } else {
                                $.ajax({
                                    url: getHost() + "updateEmailDraft",
                                    method: "post",
                                    data: JSON.stringify({
                                        draftId: draft_id.toString(),
                                        bodyString: $('#edit').froalaEditor('html.get'),
                                        lookupId: lookupId,
                                        mindbodyData: mindbodyData,
                                        categoryId: categoryId,
                                        subCategoryId: subCategoryId,
                                        emailSubject: email_subject
                                    }),
                                    success: function (responseText) {
                                        if (responseText == "true") {
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
            });
            $("#saveButton").click(function () {
                $("#" + addblockid).css("border", "");
                var email_subject = $("#emailSubjectTag").val();
                var lookupId = $("#LookupId").val();
                var mindbodyData = $("#mindbodydata").val();
                var categoryId = $("#categoryIdTag").val();
                var subCategoryId = $("#subCategoryIdTag").val();
                $.ajax({
                    method: 'GET',
                    url: getHost() + 'settings/getAllPreferences'
                }).success(function (data, status) {
                    var footerData = JSON.parse(data.d.details);
                    var footer = UserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                            footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                            footerData.userProfile.address);
                    var sendData = JSON.stringify({
                        htmlString: $('#edit').froalaEditor('html.get') + footer,
                        iframeName: rendomIframeFilename.toString()
                    });
                    $.ajax({
                        url: getHost() + "/email/previewServlet",
                        method: "POST",
                        data: sendData,
                        success: function (responseText) {
                            $("#previewcontent").empty();
                            $("#previewcontent").append(responseText.d.details);
                            if (draft_id == "0" || draft_id == "null" || draft_id == null)
                            {
                                $.ajax({
                                    url: getHost() + "saveEmailDrafts",
                                    method: "post",
                                    data: JSON.stringify({
                                        bodyString: $('#edit').froalaEditor('html.get'),
                                        lookupId: lookupId,
                                        mindbodyData: mindbodyData,
                                        categoryId: categoryId,
                                        subCategoryId: subCategoryId,
                                        emailSubject: email_subject
                                    }),
                                    success: function (responseText) {
                                        if (responseText != "0") {
                                            document.location.href = "emaillistselection?draftid=" + responseText + "&subject=" + email_subject + "&iframeName=" + rendomIframeFilename + "&categoryId=" + categoryId + "&subCategoryId=" + subCategoryId + "&emailSubject=" + email_subject + "&mindbodyId=" + mindbodydata + "&LookupId=" + LookupId;
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
                                    url: getHost() + "updateEmailDraft",
                                    method: "post",
                                    data: JSON.stringify({
                                        draftId: draft_id.toString(),
                                        bodyString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                        lookupId: lookupId,
                                        mindbodyData: mindbodyData,
                                        categoryId: categoryId,
                                        subCategoryId: subCategoryId,
                                        emailSubject: email_subject
                                    }),
                                    success: function (responseText) {
                                        if (responseText == "true")
                                        {
                                            document.location.href = "emaillistselection?draftid=" + draft_id + "&subject=" + email_subject + "&iframeName=" + rendomIframeFilename;
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
            });

            $("#styletab").click(function () {
                $("#blockdivheader").hide();
                $("#styledivheader").show();
                $("#blockdiv").hide();
                $("#stylediv").show();
                $("#blocktab").removeClass("emailSideBar-tab-active");
                $("#blocktab").addClass("emailSideBar-tab");
                $("#styletab").removeClass("emailSideBar-tab");
                $("#styletab").addClass("emailSideBar-tab-active");
            });
            $("#blocktab").click(function () {
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

        function addblock() {
            document.getElementById("addblkbtn").style.backgroundColor = "#0f76a6";
            document.getElementById("addblkbtn").style.color = "#f6f7f7";
        }

        function selecterBlockId(selectblock, blockid) {
            var selectblockid = selectblock.id;
            $("#" + selectblockid).css("border", "2px solid #1EADFC").siblings().css("border", "");
            temp_block_id = blockid;
            $("img").click(function () {
                uploadImageToEditor(this.id);
            });
            MoveBlock(selectblock.id);
            if (selectblock == "defaultblock1" || selectblockid == "defaultblock1")
            {
                block_clicked = "false";
                blockIdSelected = "defaultblock1";
                addblockid = selectblockid;
            }
            else {
                block_clicked = "true";
                blockIdSelected = "";
                block_id = blockid;
                addblockid = selectblockid;
            }
            $("#styletab").trigger("click");
        }
        $("#selcatdet").click(function () {
            $("#blocktab").click();
            $("#tabs-4").hide();
        });

        $scope.getEmailDrafts = function () {
            if (draft_id !== "" && draft_id !== null && draft_id !== "null") {
                $http({
                    method: 'GET',
                    url: getHost() + 'getEmailDraft?draftid=' + draft_id
                }).success(function (data, status) {
                    if (data == "") {
                        $scope.emaildraftsstatus = "No email drafts present";
                    } else {

                        $scope.htmlbody = data.htmlbody;
                        $('#edit').froalaEditor('html.set', '' + data.htmlbody);
                    }
                }).error(function (data, status) {
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

        $scope.addActive = function (id) {
            $("#stylediv li").removeClass("style-slat-active");
            $("#stylediv li").addClass("style-slat");
            $("#" + id).removeClass("style-slat");
            $("#" + id).addClass("style-slat-active");
        };

        $scope.showStylesAfterData = function () {
            blockIdSelected = $(selectedBlockId).attr("id").toString();
            var arr = blockIdSelected.split('SSS');
            block_id = arr[0].replace("block", "");
        };

        $scope.showStyles = function () {
//                            showOverlay();
            var subCategoryId = $("#subCategoryIdTag").val();
            var queryurl;
            $scope.curPage = 0;
            $scope.pageSize = 2;
            if (block_clicked == "true" || blockIdSelected != "defaultblock1")
            {
                queryurl = getHost() + 'getAllEmailBlockModelsByBlockId?emailBlockId=' + block_id;
            }
            else
            {
                queryurl = getHost() + 'getAllEmailModelsBySubCategoryId?subCategoryId=' + subCategoryId;
            }
            $http({
                method: 'GET',
                url: queryurl
            }).success(function (data, status, headers, config) {
                $scope.datalistsstyles = data.d.details;
//                            alert("queryurl "+queryurl+"......................\n"+JSON.stringify(data.d.details)+"...style...");
                $scope.numberOfPages = function () {
                    return Math.ceil($scope.datalistsstyles.length / $scope.pageSize);
                };
                hideOverlay();
                if (data === error) {
                    alert(data);
                    hideOverlay();
                }

            }).error(function (data, status, headers, config) {
                hideOverlay();
                alert("No data available! Problem fetching the data.");
            });
        };

        $scope.showBlocks = function () {
            $("#" + addblockid).css("border", "");
//                        showOverlay();
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
                method: 'GET',
                url: getHost() + 'getAllBlocksForCompany'
            }).success(function (data, status, headers, config) {
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
                $scope.numberOfPages = function () {
                    return Math.ceil($scope.datalists.length / $scope.pageSize);
//                                hideOverlay();
                };
                if (data === error) {
                    alert(data);
                    hideOverlay();
                }
//                            hideOverlay();
            }).error(function (data, status, headers, config) {
                hideOverlay();
                alert("No data available! Problem fetching the data.");
            });
        };

        $scope.showImageOfBlock = function (id, mind_body_query) {
//                        showOverlay();
//                        alert(id);
//                        alert(mind_body_query);
            $(".block-button").addClass("hide");
            $("#blockdiv li").removeClass("block-slat-active");
            $("#blockdiv li").addClass("block-slat");
            $("#" + id).removeClass("block-slat");
            $("#" + id).addClass("block-slat-active");
            $("#div2" + id).removeClass("hide");
            $("#stylelist").css("display", "none");
            $("#blklist").css("display", "block");
            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            $(":button").removeAttr("disabled");
            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
            temp_block_id = id;
            temp_mind_body_query = mind_body_query;
            $http.get(getHost() + 'getAllEmailBlockModelsByBlockId?emailBlockId=' + id).success(function (data, status) {
                var jsondataDefault = data;
                hideOverlay();
                temp_style_id = data.d.details[0].emailBlockModelLookupId;
            }).error(function (error) {
                alert(JSON.stringify(error));
                hideOverlay();
            });
            $("#addblkbtn").prop("disabled", true);
        };

        $scope.showDataTemp = function (id) {
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

        $scope.showData = function (id, mind_body_query) {
            block_clicked = "true";
            blockIdSelected = "";
            block_id = id;
            addblockid = "block" + addBlockcCount;
            addBlockcCount++;
            if (mind_body_query == 0)
            {
                $scope.mindbodyid = "0";
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
                    method: 'GET',
                    url: getHost() + '/externalContent/isActivated?externalSourceKeywordLookupId=' + mind_body_query
                }).success(function (data, status, headers, config) {
                    var minddata = JSON.stringify(data.d.details);
                    if (minddata === "[true]") {
                        $http({
                            method: 'GET',
                            url: getHost() + '/externalContent/getListData/' + mind_body_query
                        }).success(function (data, status, headers, config) {
                            var parseData = JSON.parse(data.d.details);
                            $scope.datalists2 = parseData;
                            $("#loadingGifformindbody").hide();
                            $(".scrollydiv").show();
                            $("#clsbtn").css("display", "block");
                            $("#addblkbtn").prop('disabled', true).css("background-color", "#e3e3e3").css("color", "#9c9da1");
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                        });
                    }
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                });
            }
            $scope.showStyles();
        };

        $scope.select_category_details = function (id) {
            $scope.mindbodyid = id;
            $("#stylelist").css("display", "block");
            $scope.showStyles();
            showText(temp_style_id);
            $("#filtercontainer").hide();
            $("#fade").hide();
            $("#slider-button").click();
        };
        $scope.getFooterDetails = function () {
            $http({
                method: 'GET',
                url: getHost() + '/settings/getAllPreferences'
            }).success(function (data, status) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
            });
        };
        $scope.changeFooterDetails = function () {
            var address = $("#footerAddress").val();
            var websiteurl = $("#footerWebsiteUrl").val();
            ;
            var facebookurl = $("#footerFacebookUrl").val();
            ;
            var twitterUrl = $("#footerTwitterUrl").val();
            ;
            var instagramUrl = $("#footerInstagramUrl").val();
            var footerData = '{"facebookUrl":"' + facebookurl + '","twitterUrl":"' + twitterUrl + '","instagramUrl":"' + instagramUrl + '","websiteUrl":"' + websiteurl + '","address":"' + address + '"}';
            if (address) {
                $http({
                    method: 'POST',
                    url: getHost() + 'settings/setFooter',
                    data: footerData
                }).success(function (data) {
                    alert(detailssaved);
                    $("#emailFooterPopup").hide();

                }).error(function (data, status) {
                    alert(requesterror);
                });
            }
            else {
                alert("please enter the Address");
                $("#footerAddress").focus();
            }

        };

});



        //*************************************** emaileditor js end ************************//

  
