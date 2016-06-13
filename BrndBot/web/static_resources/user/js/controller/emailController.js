emailFlowApp.controller("emailController", ['$scope', '$window', '$location', 'blockModelFactory', 'companyFactory', 'categoryFactory', 'emailDraftFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', 'settingsFactory', 'companyMarketingFactory', 'emailFactory', function ($scope, $window, $location, blockModelFactory, companyFactory, categoryFactory, emailDraftFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService, settingsFactory, companyMarketingFactory, emailFactory) {
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
        $scope.selectedBlockId = "";
        $scope.isBlockClicked = "false";
        $scope.htmlBlockId = "defaultblock1";
        $scope.firstTemplateForBlock = "";
        $scope.addBlockCount = 0;
        $scope.draftId = 0;
        $scope.randomIframeFilename = event.timeStamp;
        $scope.htmlTagId = "";
        $scope.companyId = 0;
        //OnPageLoad
        $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick']);
        companyMarketingFactory.getAllUserMarketingProgramsSessionIdGet().then(function (urlList) {
            $('#edit').froalaEditor({key: FroalaLicenseKey, linkList: urlList});
        });
        
        $scope.blockIdOnSelected('defaultblock1', 0);
        modelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (templateDate) {
            var blockList = data.d.details.reverse();
            $scope.addHTMLInEmailEditor(blockList[0].modelId);
        });
        $scope.redirect = function (redirect, categoryId, mindbody, lookupId, mindbodyid, draftId)
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
            if (draftId !== '')
            {
                $scope.draftId = draftId;
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
                } else
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

        $scope.getEmailDrafts = function (emailDrafts) {
            if (!$scope.draftId) {
                emailDraftFactory.getEmailDraftGet(emailDrafts).then(function (data) {
                    if (data == "") {
                        $scope.emaildraftsstatus = "No email drafts present";
                    } else {

                        $scope.htmlbody = data.htmlbody;
                        $('#edit').fraoalaEditor('html.set', '' + data.htmlbody);
                    }
                });
            }
        };

        $scope.showStyles = function () {
            if (isBlockClicked == "true" || htmlBlockId != "defaultblock1")
            {
                blockModelFactory.allEmailBlockModelGet(selectedBlockId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            } else
            {
                blockModelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            }
        };

        $scope.showBlocks = function () {
            companyFactory.allBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;
            });
        };

        $scope.didChooseBlock = function (selectedBlockId, externalSourceKeywordLookupId) {

            blockModelFactory.allEmailBlockModelGet().then(function (data) {
                $scope.firstTemplateForBlock = data.d.details[0].emailBlockModelLookupId;

                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = selectedBlockId;

                ++$scope.addBlockCount;
                $scope.htmlTagId = "block" + $scope.addBlockCount;

                if (externalSourceKeywordLookupId == 0)
                {
                    $scope.mindbodyid = "0";
                    $scope.addHTMLInEmailEditor(selectedBlockId);
                } else
                {
                    externalContentFactory.activatedGet(externalSourceKeywordLookupId).then(function (data) {
                        var externalData = JSON.stringify(data.d.details);
                        if (externalData === "[true]") {

                            externalContentFactory.listDataGet(externalSourceKeywordLookupId).then(function (listData) {
                                var parseData = JSON.parse(listData.d.details);
                                $scope.mindbodyDataList = parseData;
                                $scope.showStyles();
                            });
                        }
                    });
                }
            });
        };


        $scope.didSelectMindbodyDataId = function (id) {
            $scope.mindbodyid = id;
            $scope.showStyles();
            $scope.addHTMLInEmailEditor($scope.firstTemplateForBlock);
        };

        $scope.addHTMLInEmailEditor = function (templateId) {
            var mindbodyId = 0;

            //TODO Ilyas to check while testing
            if (!$scope.mindbodyid) {
            } else {
                mindbodyId = $scope.mindbodyid;
            }

            externalContentFactory.layoutEmailModelGet(templateId, $scope.isBlockClicked, mindbodyId).then(function (data) {
                var emailData = JSON.parse(data.d.details);

                if ($scope.isBlockClicked === "false") {
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="defaultblock1"')) {
                        var jHtmlObject = jQuery(editorHtml);
                        var editor = jQuery("<p>").append(jHtmlObject);
                        editor.find("#defaultblock1").remove();
                        editorHtml = editor.html();
                    }
                    var styleHtml = '<div id=defaultblock1 ng-click="blockIdOnSelected(defaultblock1,0)">' + emailData.htmldata + '</div>';
                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
                } else {
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="' + $scope.htmlTagId + '"')) {
                        var jHtmlObject = jQuery(editorHtml);
                        var editor = jQuery("<p>").append(jHtmlObject);
                        var BlockHtml = '<div id=' + $scope.htmlTagId + ' ng-click=blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        editor.find("#" + $scope.htmlTagId).replaceWith(BlockHtml);
                        editorHtml = editor.html();
                        $('#edit').froalaEditor('html.set', '' + editorHtml + '');
                    } else
                    {
                        BlockHtml = '<div id=' + $scope.htmlTagId + ' ng-click=blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        $('#edit').froalaEditor('html.set', '' + editorHtml + '' + BlockHtml + '');
                    }
                }
            });

        };

        $scope.blockIdOnSelected = function (selectedBlock, blockId) {
            var selectedHtmlBlockId = selectedBlock.id;
            $scope.selectedBlockId = blockId;
            $("img").click(function () {
                uploadImageToEditor(this.id);
            });
            MoveBlock(selectedHtmlBlockId);
            if (selectedBlock == "defaultblock1" || selectedHtmlBlockId == "defaultblock1")
            {
                $scope.isBlockClicked = "false";
                $scope.htmlBlockId = "defaultblock1";
                $scope.htmlTagId = selectedHtmlBlockId;

            } else {
                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = blockId;
                $scope.htmlTagId = selectedHtmlBlockId;
            }
        };

        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
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
            } else {
                alert("please enter the Address");
                $("#footerAddress").focus();
            }
        };
        
        $scope.getUserFooter = function (fb, twitter, website, instagram, address) {
            var returnFooter = "";
            var footer = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#EEEEEE\" style=\"border-collapse:collapse;\"><tr><td valign=\"top\"> <center style=\"width: 100%;\"> <div style=\"max-width: 680px;\"> <!--[if (gte mso 9)|(IE)]> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" align=\"center\"> <tr> <td> <![endif]--> <!-- Atom Body: BEGIN --> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#EEEEEE\" width=\"100%\" style=\"max-width: 680px;\"> <tr> <td style=\"padding-top:15px;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\" style=\"max-width: 300px; background-color:#inherit\" class=\"mobile-padding\"> <tr>";

            var footerFB = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerFB$$$\"><img src=\"" + getHost() + "images/Facebook_Filled.png" + "\" alt=\"Facebook Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Facebook </td> </tr> </table> </td>";

            var footerTwitter = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerTwitter$$$\"><img src=\"" + getHost() + "images/Twitter_Filled.png" + "\" alt=\"Twitter Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Twitter </td> </tr> </table> </td>";

            var footerWebsite = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerWebsite$$$\"><img src=\"" + getHost() + "images/Website_Filled.png" + "\" alt=\"Website Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Website </td> </tr> </table> </td>";

            var footerInstagram = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerInstagram$$$\"><img src=\"" + getHost() + "images/Insta_Filled.png" + "\" alt=\"Instagram Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Instagram </td> </tr> </table> </td>";

            var footerMiddle = "</tr> </table> </td> </tr>";

            var footerAddress = "<!--HEADER: BEGIN--> <tr> <td style=\"font-family: sans-serif; font-size: 12px; mso-height-rule: exactly; line-height: 120%; text-align:center; color: #555555; padding: 20px 55px 20px 55px;\" class=\"fluid mobile-padding\"> $$$footerAddress$$$ </td> </tr> <!--HEADER: END-->";

            var footerClose = "</table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </div> </center> </td></tr></table>";


            returnFooter = footer;
            if (fb != "")
                returnFooter += footerFB.replace("$$$footerFB$$$", fb);
            if (twitter != "" && typeof twitter != "undefined")
                returnFooter += footerTwitter.replace("$$$footerTwitter$$$", twitter);

            if (website != "" && typeof website != "undefined")
                returnFooter += footerWebsite.replace("$$$footerWebsite$$$", website);

            if (instagram != "" && typeof instagram != "undefined")
                returnFooter += footerInstagram.replace("$$$footerInstagram$$$", instagram);

            returnFooter += footerMiddle;

            if (address != "" && typeof address != "undefined")
                returnFooter += footerAddress.replace("$$$footerAddress$$$", address);

            returnFooter += footerClose;

            return returnFooter;
        };
        
        $scope.emailPreviewOnClick = function() {
            settingsFactory.getAllPreferencesGet().then(function(data){
                var footerData = JSON.parse(data.d.details);
                if(!footerData.userProfile){
                    //TODO show footer
//                    $("#emailFooterPopup").show();
                } else{
                    if(!footerData.userProfile.address){
                          //TODO show footer
//                        $("#emailFooterPopup").show();
                    }else{
                          //TODO show email preview   
//                        $("#email_previewdiv").show();
                            var footer = $scope.getUserFooter(footerData.userProfile.facebookUrl,footerData.userProfile.twitterUrl,
                                        footerData.userProfile.websiteUrl,footerData.userProfile.instagramUrl,
                                        footerData.userProfile.address);
                                        
                            var sendData = {
                                        htmlString: $('#edit').froalaEditor('html.get')+footer,
                                        iframeName: rendomIframeFilename.toString()
                                    };
                            emailFactory.previewServletPost(sendData).then(function(){
                                //TODO change to angularJS
                                $("#dynamictable5").empty();
                                $("#dynamictable6").empty();
                                var iframePath = getHost() +"download/HTML?fileName="+$scope.randomIframeFilename+".html";
                                $("#dynamictable5").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                $("#dynamictable6").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                            });
                    }
                }
            });
        };
        
        $scope.saveToDraftOnClick = function() {
            settingsFactory.getAllPreferencesGet().then(function(data){
                var footerData = JSON.parse(data.d.details);
                var footer = $scope.getUserFooter(footerData.userProfile.facebookUrl,footerData.userProfile.twitterUrl,
                    footerData.userProfile.websiteUrl,footerData.userProfile.instagramUrl,
                    footerData.userProfile.address);
                var sendData = {
                            htmlString: $('#edit').froalaEditor('html.get')+footer,
                            iframeName: rendomIframeFilename.toString()
                        };
                emailFactory.previewServletPost(sendData).then(function(){   
                    if (!$scope.draftId){
                        var draftData = {
                            bodyString : $('#edit').froalaEditor('html.get'),
                            lookupId : $scope.lookupId,
                            mindbodyData : $scope.mindbodyid,
                            categoryId : $scope.categoryId,
                            subCategoryId : $scope.subCategoryId,
                            emailSubject : $scope.emailsubject
                        }
                        emailDraftFactory.saveEmailDraftsPost(draftData).then(function(responseText){
                            if (responseText != "0"){
                                alert("Draft saved successfully.");
                            $("#saveToDraft").bind('click');
                                    document.location.href = "dashboard";
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    } else { 
                        var draftData = {
                            draftId: $scope.draftId.toString(),
                            bodyString :$('#edit').froalaEditor('html.get'), 
                            lookupId : $scope.lookupId,
                            mindbodyData : $scope.mindbodyid,
                            categoryId : $scope.categoryId,
                            subCategoryId : $scope.subCategoryId,
                            emailSubject : $scope.emailsubject
                        }
                        emailDraftFactory.updateEmailDraftPost(draftData).then(function(responseText){
                            if (responseText == "true"){
                                alert("Draft updated successfully.");
                                document.location.href = "dashboard";
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    }
                }); 
                
            });
        };
        
        $scope.saveButtonOnClick = function() {
            settingsFactory.getAllPreferencesGet().then(function(footerResponseData){
                var footerData = JSON.parse(footerResponseData.d.details);
                var footer = UserFooter(footerData.userProfile.facebookUrl,footerData.userProfile.twitterUrl,
                        footerData.userProfile.websiteUrl,footerData.userProfile.instagramUrl,
                        footerData.userProfile.address);
                var sendData=JSON.stringify({
                    htmlString: $('#edit').froalaEditor('html.get')+footer,
                    iframeName: $scope.randomIframeFilename.toString()
                });
                emailFactory.previewServletPost(sendData).then(function(data){
                    if (!$scope.draftId){
                        var draftData = {
                            bodyString : $('#edit').froalaEditor('html.get'),
                            lookupId : $scope.lookupId,
                            mindbodyData : $scope.mindbodyid,
                            categoryId : $scope.categoryId,
                            subCategoryId : $scope.subCategoryId,
                            emailSubject : $scope.emailsubject
                        }
                        emailDraftFactory.saveEmailDraftsPost(draftData).then(function(responseText){
                            if (responseText != "0"){
                                alert("Draft saved successfully.");
                            $("#saveToDraft").bind('click');
                            $scope.redirect('emaillistselection', $scope.categoryId, '', '', '', responseText);
//                                    document.location.href = "emaillistselection?draftid=" + responseText + "&subject=" + email_subject+"&iframeName="+rendomIframeFilename+"&categoryId="+categoryId+"&subCategoryId="+subCategoryId+"&emailSubject="+email_subject+"&mindbodyId="+mindbodydata+"&LookupId="+LookupId;
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    } else { 
                        var draftData = {
                            draftId: $scope.draftId.toString(),
                            bodyString :$('#edit').froalaEditor('html.get'), 
                            lookupId : $scope.lookupId,
                            mindbodyData : $scope.mindbodyid,
                            categoryId : $scope.categoryId,
                            subCategoryId : $scope.subCategoryId,
                            emailSubject : $scope.emailsubject
                        }
                        emailDraftFactory.updateEmailDraftPost(draftData).then(function(responseText){
                            if (responseText == "true"){
                                $scope.redirect('emaillistselection', $scope.categoryId, '', '', '', $scope.draftId);
//                                document.location.href = "emaillistselection?draftid=" + draft_id + "&subject=" + email_subject+"&iframeName="+rendomIframeFilename;
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    }
                });
            });
        };

    }]);
