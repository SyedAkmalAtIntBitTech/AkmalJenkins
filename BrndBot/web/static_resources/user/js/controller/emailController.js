emailFlowApp.controller("emailController", ['$scope', '$window', '$location', 'blockModelFactory', 'companyFactory', 'categoryFactory', 'emailDraftFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', 'settingsFactory', 'companyMarketingProgramFactory', 'emailFactory', 'modelFactory', 'emailListFactory', 'scheduleActionsFactory', function ($scope, $window, $location, blockModelFactory, companyFactory, categoryFactory, emailDraftFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService, settingsFactory, companyMarketingProgramFactory, emailFactory, modelFactory, emailListFactory, scheduleActionsFactory) {
        $scope.footerEmailPopup = false;
        $scope.emailChannelId = 3;
        $scope.printChannelId = 2;
        $scope.imageChannelId = 1;
        $scope.forward = "";
        $scope.categoryId = "";
        $scope.lookupId = "";
        $scope.externalSourceName = "";
        $scope.subCategoryId = "";
        $scope.mindbodyid = "";
        $scope.emailSubject = "";
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
        $scope.previousTagId = "defaultBlock";
        $scope.fromAddress = getDefaultEmailId();
        $scope.schedulePopup = false;
        $scope.selectedSocialmedia = "email";
        $scope.emailAddresses = '';
        $scope.loadingOverlay = false;
        $scope.subjectValidation = subjectValidation;
        $scope.fromNameValidation = fromNameValidation;
        $scope.replyToValidation = replyToValidation;
        $scope.actionNameValidation = actionNameValidation;
        $scope.scheduleDateValidation = scheduleDateValidation;
        $scope.lesserDateValidation = lesserDateValidation;
        $scope.scheduleTimeValidation = scheduleTimeValidation;
        $scope.actionNameListValidation = actionNameListValidation;
        $scope.listValidation = listValidation;
        $scope.emailValidation = emailValidation;
        $scope.isEmailSubEmpty = false;
        $scope.actionTimeVal = false;
        $scope.actionDropdown = false;
        $scope.dateLesser = false;
        $scope.validateEmailAddress = false;
        $scope.validateEmailAddress = false;
        var sliderDialog = "#emaileditorexternalpopup";
        var emailDraftDetails = localStorage.getItem('emailDraftData');
//        
        //OnPageLoad
        $scope.emailEditorInit = function () {
            $scope.loadingOverlay = true; //start Loading Overlay
            if (emailDraftDetails !== null) {
                var paramDraftId = JSON.parse(emailDraftDetails).draftid;
                var paramCategoryId = JSON.parse(emailDraftDetails).category_id;
                var paramSubCategoryId = JSON.parse(emailDraftDetails).sub_category_id;
                var paramLookupId = JSON.parse(emailDraftDetails).lookupId;
                var paramEmailSubject = JSON.parse(emailDraftDetails).email_subject;
                var paramMindbodyId = JSON.parse(emailDraftDetails).mindbodyId;
                $scope.redirect('emaileditor', paramCategoryId, paramSubCategoryId, '', paramLookupId, paramMindbodyId, paramEmailSubject, paramDraftId);
            }

            $('#slider-button').click(function () {
                if ($('#slider-button').css("margin-right") === "788px")
                {
                    $(sliderDialog).animate({"margin-right": '-=900px'});
                    $('#slider-button').animate({"margin-right": '-=788px'});
                } else
                {
                    $(sliderDialog).animate({"margin-right": '+=900px'});
                    $('#slider-button').animate({"margin-right": '+=788px'});
                }
            });

//            var redirectFromDraft = localStorage.getItem("emailDraftData");
//            $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick', 'ng-click']);
            companyMarketingProgramFactory.getAllUserMarketingProgramsSessionIdGet().then(function (urlList) {
//                $('#edit').froalaEditor({key: FroalaLicenseKey, linkList: urlList});
                $scope.blockIdOnSelected('defaultblock1', 0);
                if (emailDraftDetails === null) {
                    modelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (templateDate) {
                        var blockList = templateDate.d.details.reverse();
                        $scope.addHTMLInEmailEditor(blockList[0].modelId);
                    });
                } else {
                    var DraftId = JSON.parse(emailDraftDetails).draftid;
                    $scope.getEmailDrafts(DraftId);
                }
                $scope.loadingOverlay = false; //stop Loading Overlay
                $scope.hideEmailEditorOverlay = true;
            });


        };

        $scope.emailFlowValidation = function (emailSubject) {
            if (!emailSubject) {
//                $scope.$parent = {emailsubject: ""};
                $("#emailSub").focus();
                $scope.isEmailSubEmpty = true;
                return false;
            }
            $scope.isEmailSubEmpty = false;
            return true;
        };

        $scope.redirect = function (redirect, categoryId, subCategoryId, mindbody, lookupId, mindbodyid, emailSubject, draftId)
        {
            localStorage.removeItem("emailDraftData");
            if (lookupId)
            {
                $scope.lookupId = lookupId;
            }
            if (categoryId)
            {
                $scope.categoryId = categoryId;
            }
            if (subCategoryId)
                $scope.subCategoryId = subCategoryId;
            if (mindbody === 'Mindbody')
            {
                $scope.externalSourceName = 'mindbody';
                redirect = $scope.forwardone;
            }
            if (mindbody !== 'Mindbody')
            {
                if (redirect === 'emailexternalsource')
                {
                    redirect = $scope.forwardtwo;
                }
            }
            if (mindbodyid)
            {
                $scope.mindbodyid = mindbodyid;
            }
            if (draftId)
            {
                $scope.draftId = draftId;
            }
            if (emailSubject)
            {
                $scope.emailSubject = emailSubject;
            }
            if (redirect !== 'emaileditor') {
                $location.path("/" + redirect);
            }
            if (redirect === 'emaileditor') {
                if ($scope.emailFlowValidation(emailSubject))
                {
                    $location.path("/" + redirect);
                }
            }
        };

        $scope.redirectBaseURL = function () {
            if (($scope.categoryId === "") && (emailDraftDetails === null)) {
                $location.path("/" + "baseemaileditor#/emailcategory");
            }
        };

        $scope.redirectToEmailFlow = function (forwardone)
        {
            redirectFactory.redirectFlowTo(forwardone);
            $window.location = getHost() + "user/" + forwardone;
//            var emailsubject = $scope.emailSubject;
//            if (emailsubject === '')
//            {
//                $scope.emailSubjectError = "Email Subject Required!";
//            }
        };
        $scope.getCategories = function (forwardone)
        {
            categoryFactory.allCompanyCategoriesGet($scope.emailChannelId).then(function (data) {
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
            $scope.hideGifImage = true;
//            $scope.loadingOverlay = true; //start Loading Overlay
            externalContentFactory.activatedGet(lookupId).then(function (data) {
                var minddata = JSON.stringify(data.d.details);
                if (minddata === "[true]") {
                    externalContentFactory.listDataGet(lookupId).then(function (data) {
                        var parseData = JSON.parse(data.d.details);
                        $scope.mindbodylist = parseData.mindbody_data;
//                        $scope.loadingOverlay = false; //stop Loading Overlay
                        $scope.hideGifImage = false;
//                        $scope.hideDataOverlay = true;
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
            if ($scope.draftId) {
                emailDraftFactory.getEmailDraftGet(emailDrafts).then(function (data) {
                    if (data === "") {
                        $scope.emaildraftsstatus = "No email drafts present";
                    } else {
                        $scope.htmlbody = data.htmlbody;
                        $("#tinymceEditorBody").append(data.htmlbody);
                        $scope.lunchTinyMceEditor();
                    }
                });
            }
        };

        $scope.blockdivheader = true;
        $scope.styledivheader = false;
        $scope.blocktab = "emailSideBar-tab-active";
        $scope.styletab = "emailSideBar-tab";
        $scope.showStyles = function (isClick) {
            if (isClick === "true")
            {
                $scope.blockdivheader = false;
                $scope.styledivheader = true;
                $scope.blocktab = "emailSideBar-tab";
                $scope.styletab = "emailSideBar-tab-active";
            }
            if ($scope.isBlockClicked === "true" || $scope.htmlBlockId !== "defaultblock1")
            {
                blockModelFactory.allEmailBlockModelGet($scope.selectedBlockId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            } else
            {
                modelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            }
        };

        $scope.showBlocks = function () {
            $scope.blockdivheader = true;
            $scope.styledivheader = false;
            $scope.styletab = "emailSideBar-tab";
            $scope.blocktab = "emailSideBar-tab-active";
            companyFactory.allBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;
            });
        };

        $scope.blockOnClick = function (id) {
//            TODO change to AngularJs, (Complicated code)
            $("#blockdiv li").removeClass("block-slat-active");
            $("#blockdiv li").addClass("block-slat");
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
        };


        $scope.didChooseBlock = function (selectedBlockId, externalSourceKeywordLookupId) {
            $scope.emailMindBodyPopup = true;
            $scope.loadingOverlay = true; //start Loading Overlay
            blockModelFactory.allEmailBlockModelGet(selectedBlockId).then(function (data) {
                $scope.firstTemplateForBlock = data.d.details[0].emailBlockModelLookupId;
                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = selectedBlockId;
                ++$scope.addBlockCount;
                $scope.htmlTagId = "block" + $scope.addBlockCount;

                if (externalSourceKeywordLookupId === 0)
                {
                    $scope.mindbodyid = "0";
                    $scope.addHTMLInEmailEditor(selectedBlockId);
                    $scope.loadingOverlay = false;
                } else
                {

                    $("#fade").show();
                    $scope.overlayFade = true;
                    $scope.loadingOverlay = true; //start Loading Overlay
                    $('#slider-button').click();
                    $scope.emailScrollyDiv = false;
                    externalContentFactory.activatedGet(externalSourceKeywordLookupId).then(function (data) {
                        var externalData = JSON.stringify(data.d.details);

                        if (externalData === "[true]") {
                            externalContentFactory.listDataGet(externalSourceKeywordLookupId).then(function (listData) {
                                var parseData = JSON.parse(listData.d.details);
                                $scope.mindbodyDataList = parseData;
                                $scope.loadingOverlay = false; //start Loading Overlay
                                $scope.hideMindbodyOverlay = true;
                                $scope.emailScrollyDiv = true;
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
            $scope.closeMindbodyPopup();
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
                    var editorHtml = $('#tinymceEditorBody').html();
                    if (editorHtml.contains('id="defaultblock1"')) {
                        $("#defaultblock1").remove();
                        editorHtml = $('#tinymceEditorBody').html();
                    }
                    var styleHtml = '<div id=defaultblock1 class=module onclick="angular.element(this).scope().blockIdOnSelected(defaultblock1,0)">' + emailData.htmldata + '</div>';
//                    var styleHtml = '<div id=defaultblock1 class=module onclick="angular.element(this).scope().blockIdOnSelected(defaultblock1,0)"><div class=\"view\"><table width=\"100%\" bgcolor=\"#2a2a2a\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tbody><tr><td><table bgcolor=\"#d41b29\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\"><div class=\"innerbg\"></div><div class=\"addremove\" style=\"margin-left: 975px;\"><div class=\"drag\"></div><div class=\"remove\"></div></div><tbody><tr><td width=\"100%\">' + emailData.htmldata + '</td></tr></tbody></table></div>';
                    $("#tinymceEditorBody").append(styleHtml);
                    $scope.lunchTinyMceEditor();
                } else {
                    var editorHtml = $('#tinymceEditorBody').html();
                    if (editorHtml.contains('id="' + $scope.htmlTagId + '"')) {
                        $("#" + $scope.htmlTagId).remove();
                        var BlockHtml = '<div id=' + $scope.htmlTagId + ' onclick=angular.element(this).scope().blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        $("#tinymceEditorBody").append(BlockHtml);
                        $scope.lunchTinyMceEditor();
                    } else
                    {
                        BlockHtml = '<div id=' + $scope.htmlTagId + ' onclick=angular.element(this).scope().blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        $("#tinymceEditorBody").append(BlockHtml);
                        $scope.lunchTinyMceEditor();
                    }
                }
            });

        };
        $scope.lunchTinyMceEditor = function () {
            tinymce.EditorManager.editors = [];
            tinymce.init({
                selector: 'td.mce-content-body',
                width: 400,
                inline: true,
                plugins: [
                    'advlist custombutton autolink lists link image charmap print preview anchor',
                    'searchreplace visualblocks code fullscreen',
                    'insertdatetime media table contextmenu paste',
                    'template paste textcolor colorpicker textpattern imagetools'
                ],
                toolbar1: ' insertfile undo redo | bold italic | alignleft aligncenter alignright alignjustify | link image ',
                toolbar2: ' forecolor backcolor | fontselect fontsizeselect custombutton fileuploader ',
                menubar: false
            });
            $('.innerbg').mouseenter(function () {
                var seldiv = $(this).parents('[st-sortable]');
                $(this).colpick({
                    layout: 'hex',
                    submit: 0,
                    onChange: function (hsb, hex, rgb, fromSetColor) {
                        $(seldiv).find('table:first').attr('bgcolor', '#' + hex);
                    }
                });
            });
        };
        $scope.blockIdOnSelected = function (selectedBlock, blockId) {
            var selectedHtmlBlockId = selectedBlock.id;
            $scope.selectedBlockId = blockId;
            if (selectedBlock === "defaultblock1" || selectedHtmlBlockId === "defaultblock1")
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
                $scope.company = $scope.footerDetails;
            });
        };

        $scope.changeFooterDetails = function (company) {
            var footerAddress = "";
            if (company.address)
                footerAddress = company.address;
            var footerWebsiteUrl = "";
            if (company.websiteUrl)
                footerWebsiteUrl = company.websiteUrl;
            var footerFacebookUrl = "";
            if (company.facebookUrl)
                footerFacebookUrl = company.facebookUrl;
            var footerTwitterUrl = "";
            if (company.twitterUrl)
                footerTwitterUrl = company.twitterUrl;
            var footerInstagramUrl = "";
            if (company.instagramUrl)
                footerInstagramUrl = company.instagramUrl;
            var footerPopupDetails = {"facebookUrl": footerFacebookUrl, "twitterUrl": footerTwitterUrl, "instagramUrl": footerInstagramUrl, "websiteUrl": footerWebsiteUrl, "address": footerAddress};
            $scope.emailFooterPopupDetails = false;
            settingsFactory.setFooterPost(footerPopupDetails).then(function (data) {
                $scope.getFooterDetails();
            });
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
            if (fb !== "")
                returnFooter += footerFB.replace("$$$footerFB$$$", fb);
            if (twitter !== "" && typeof twitter !== "undefined")
                returnFooter += footerTwitter.replace("$$$footerTwitter$$$", twitter);

            if (website !== "" && typeof website !== "undefined")
                returnFooter += footerWebsite.replace("$$$footerWebsite$$$", website);

            if (instagram !== "" && typeof instagram !== "undefined")
                returnFooter += footerInstagram.replace("$$$footerInstagram$$$", instagram);

            returnFooter += footerMiddle;

            if (address !== "" && typeof address !== "undefined")
                returnFooter += footerAddress.replace("$$$footerAddress$$$", address);

            returnFooter += footerClose;

            return returnFooter;
        };


        $scope.emailPreviewOnClick = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
//                $("#fade").show();
                $scope.fadeClass = 'fadeClass';
                $scope.emailPreviewPopup = true;
                var footerData = JSON.parse(data.d.details);
                $scope.overlayFade = true;
//                $("#fade").show();
                var footer = $scope.getUserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                        footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                        footerData.userProfile.address);
                var sendData = {
                    htmlString: $('#tinymceEditorBody').html() + footer,
                    iframeName: $scope.randomIframeFilename.toString()
                };

                emailFactory.previewServletPost(sendData).then(function () {
                    $scope.overlayFade = true;
                    $scope.iframePath = getHost() + "download/HTML?fileName=" + $scope.randomIframeFilename + ".html";
                    document.getElementById('dynamictable5').contentDocument.location.reload(true);
                    document.getElementById('dynamictable6').contentDocument.location.reload(true);
                });
            });
        };


        $scope.saveToDraftOnClick = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                var footerData = JSON.parse(data.d.details);
                var footer = $scope.getUserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                        footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                        footerData.userProfile.address);
                var sendData = {
                    htmlString: $('#tinymceEditorBody').html() + footer,
                    iframeName: $scope.randomIframeFilename.toString()
                };

                emailFactory.previewServletPost(sendData).then(function () {
                    if (!$scope.draftId) {
                        var draftData = {
                            bodyString: $('#tinymceEditorBody').html(),
                            lookupId: $scope.lookupId.toString(),
                            mindbodyData: $scope.mindbodyid.toString(),
                            categoryId: $scope.categoryId.toString(),
                            subCategoryId: $scope.subCategoryId.toString(),
                            emailSubject: $scope.emailSubject
                        };

                        emailDraftFactory.saveEmailDraftsPost(draftData).then(function (responseText) {
                            if (responseText !== "0") {
                                alert("Draft saved successfully.");
                                document.location.href = "dashboard";
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    } else {
                        var draftData = {
                            draftId: $scope.draftId.toString(),
                            bodyString: $('#tinymceEditorBody').html(),
                            lookupId: $scope.lookupId.toString(),
                            mindbodyData: $scope.mindbodyid.toString(),
                            categoryId: $scope.categoryId.toString(),
                            subCategoryId: $scope.subCategoryId.toString(),
                            emailSubject: $scope.emailSubject
                        };

                        emailDraftFactory.updateEmailDraftPost(draftData).then(function (responseText) {
                            if (responseText === true) {
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
        $scope.saveButtonOnClick = function () {
            settingsFactory.getAllPreferencesGet().then(function (footerResponseData) {
                var footerData = JSON.parse(footerResponseData.d.details);
                if (!footerData.userProfile) {
                    $scope.editFooter();
                    return false;
                }

                if (!footerData.userProfile.address) {
                    $scope.editFooter();
                    return false;
                }

                var footer = $scope.getUserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                        footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                        footerData.userProfile.address);
                var sendData = {
                    htmlString: $('#tinymceEditorBody').html() + footer,
                    iframeName: $scope.randomIframeFilename.toString()
                };
                emailFactory.previewServletPost(sendData).then(function (data) {
                    if (!$scope.draftId) {
                        var draftData = {
                            bodyString: $('#tinymceEditorBody').html(),
                            lookupId: $scope.lookupId.toString(),
                            mindbodyData: $scope.mindbodyid.toString(),
                            categoryId: $scope.categoryId.toString(),
                            subCategoryId: $scope.subCategoryId.toString(),
                            emailSubject: $scope.emailSubject
                        };
                        emailDraftFactory.saveEmailDraftsPost(draftData).then(function (responseText) {
                            if (responseText !== "0") {
                                $scope.redirect('emaillistselection', '', '', '', '', '', '', responseText);
                                document.location.href = "emaillistselection?draftid=" + responseText + "&subject=" + email_subject + "&iframeName=" + rendomIframeFilename + "&categoryId=" + categoryId + "&subCategoryId=" + subCategoryId + "&emailSubject=" + email_subject + "&mindbodyId=" + mindbodydata + "&LookupId=" + LookupId;
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    } else {
                        var draftData = {
                            draftId: $scope.draftId.toString(),
                            bodyString: $('#tinymceEditorBody').html(),
                            lookupId: $scope.lookupId.toString(),
                            mindbodyData: $scope.mindbodyid.toString(),
                            categoryId: $scope.categoryId.toString(),
                            subCategoryId: $scope.subCategoryId.toString(),
                            emailSubject: $scope.emailSubject
                        };
                        emailDraftFactory.updateEmailDraftPost(draftData).then(function (responseText) {
                            if (responseText) {
                                $scope.redirect('emaillistselection', $scope.categoryId, '', '', '', '', '', '', $scope.draftId);
                                document.location.href = "emaillistselection?draftid=" + draft_id + "&subject=" + email_subject + "&iframeName=" + rendomIframeFilename;
                            } else {
                                alert("There was a problem while saving the draft! Please try again later.");
                            }
                        });
                    }
                });
            });
        };



        $scope.ddSelectEmailList = {
            text: "Please select an email list"
        };

        $scope.showEmailList = function () {
            $scope.ddSelectEmailListOptions = [
//                {
//                    text: "Manual",
//                    value: "1"
//                }
            ];
            $scope.redirectBaseURL();       //this function redirects to base if page is refreshed.            
            emailListFactory.emailListGet("null", "allEmailListWithNoOfContacts").then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailLists_mindbody = parseData.allEmailListWithNoOfContacts.mindbody;
                $scope.showEmailDetails = false;
                $scope.emailListDiv = true;
                //angular DD
                var emailData = parseData.allEmailListWithNoOfContacts.user;
                for (var i = 0; i < emailData.length; i++)
                {
                    var emailObject = {};
                    emailObject["text"] = emailData[i].emailListName;
                    emailObject["value"] = emailData[i].emailListName;
                    $scope.ddSelectEmailListOptions.push(emailObject);
                }
                var emailMindBodyData = parseData.allEmailListWithNoOfContacts.mindbody;
                for (var i = 0; i < emailMindBodyData.length; i++)
                {
                    var emailObject = {};
                    emailObject["text"] = emailMindBodyData[i].emailListName;
                    emailObject["value"] = emailMindBodyData[i].emailListName;
                    $scope.ddSelectEmailListOptions.push(emailObject);
                }

            });
            $scope.emailList = "1";
        };

//        $scope.showEmailList = function () {
//            emailListFactory.emailListGet("null", "allEmailListWithNoOfContacts").then(function (data) {
//                var parseData = JSON.parse(data.d.details);
//                $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
//                $scope.emailLists_mindbody = parseData.allEmailListWithNoOfContacts.mindbody;
//            });
//            $scope.emailList = "1";
//        };

        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.email_settings = parseData;
            });
        };

        $scope.getProgramNames = function () {
            companyMarketingProgramFactory.getAllUserMarketingProgramsGet().then(function (marketingPrograms) {
                var actionCallData = {
                    programid: "0",
                    type: getemail()
                };
                scheduleActionsFactory.getActionsPost(actionCallData).then(function (actions) {
                    var parseData = JSON.parse(JSON.parse(actions.d.details));
                    $scope.email_actions = parseData;
                });
                $scope.marketing_programs = marketingPrograms;
            });
        };

        $scope.programsOnChange = function () {
            var actionCallData = {
                programid: $scope.selectedProgramId,
                type: getemail()
            };
            scheduleActionsFactory.getActionsPost(actionCallData).then(function (actions) {
                var parseData = JSON.parse(JSON.parse(data1.d.details));
                $scope.email_actions = parseData;
            });
        };

        $scope.selectCsvOnClick = function () {
            $scope.emailAddressTextarea = true;
            $scope.clickToUpload = true;
            $scope.uploadButton = true;
        };

        $scope.chooseEmailListOnChange = function (listName) {
            $scope.emailList = listName.value;
            $scope.listSelectionValidation = false;
            $scope.toAddress = "";
            if ($scope.emailList === "Manual") {
                emails = "";
                $scope.emailAddresses = emails;
                $scope.toAddress = emails;
            } else if ($scope.emailList !== "Manual")
            {
                var emails = "";
                emailListFactory.emailListGet($scope.emailList, "emailsForEmailList").then(function (data) {
                    var parseData = JSON.parse(data.d.details);
                    var JSONData;
                    if (JSON.stringify(parseData.mindbody_emailAddresses) === "[]")
                        JSONData = parseData.user_emailAddresses;
                    else
                        JSONData = parseData.mindbody_emailAddresses;
                    var i = 0;
                    for (i = 0; i < JSONData.length; i++) {
                        if (JSON.stringify(JSONData[i].emailAddress) !== "") {
                            if (i === 0) {
                                emails = eval(JSON.stringify(JSONData[i].emailAddress));
                            } else {
                                emails = emails + "," + eval(JSON.stringify(JSONData[i].emailAddress));
                            }
//                             $("#emailaddresses").val(emails);/
//                             $("#toaddress").val(emails);
//                               selectCsvFile();     
                        }
                    }
                    $scope.emailAddresses = emails;
                    $scope.toAddress = emails;
                });
                $scope.emailContinueButton = true;
            }
//            $scope.selectCsvOnClick();
        };

        $scope.uploadFileButton = function () {
            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var fileUpload = document.getElementById("fileid");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) !== "undefined") {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var table = document.createElement("table");
                        var rows = e.target.result.split("\n");
                        var split = e.target.result.split(",");
                        for (var i = 0; i < split.length; i++) {
                            var email = split[i].trim();
                            if (reg.test(email) !== "")
                            {
                                if (email !== "")
                                {
                                    if (reg.test(split[i]) === false) {
                                        alert("This file contains Invalid Email Address! \n'" + split[i] + "'\t is Invalid Email id in this file. \nPlease select a valid File.");
                                        $("#fileid").val("");
                                        $("#filetext").empty().append("Click to Select file");
                                        return false;
                                    }
                                }
                            }
                        }
                        if ($('#emailaddresses').val() === "") {
                            $('#emailaddresses').val(rows);
                        } else {
                            $('#emailaddresses').val($('#emailaddresses').val() + rows);
                        }
                    };
                    reader.readAsText(fileUpload.files[0]);
                } else {
                    alert("This browser does not support HTML5!");
                }
            } else {
                alert("Please upload a valid CSV file!");
            }
        };

        $scope.validateEmails = function (emailAddresses) {
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
//             var re = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
            var result = emailAddresses.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
//                    alert("This email list contains Invalid email " + "'" + result[i] + "'");
                    $scope.listSelectionValidation = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            return true;
        };

        $scope.emailListBackButton = true;
        $scope.continueEmailListOnClick = function (emailAddresses) {
//            TODO change to AngularJs
            if ($scope.validateEmails(emailAddresses)) {
                if ($scope.emailList !== "Manual")
                {
                    if ($scope.emailAddresses !== "")
                    {
                        $scope.showEmailDetails = true;
                        $scope.emailListDiv = false;
                        $scope.emailContinueButton = false;
                        $scope.emaildetailscontbtn = true;
                        $scope.emailListBackButton = false;
                        $scope.emailDetailsBackButton = true;
                    } else {
                        alert("Please select atleast one email list or add email manually.");
                        $scope.selectCsvOnClick();
                        $("#emailaddresses").focus();
                        return false;
                    }

                } else {
                    if ($scope.emailAddresses !== "")
                    {
                        $scope.toAddress = emailAddresses;
                        $scope.showEmailDetails = true;
                        $scope.emailListDiv = false;
                        $scope.emailContinueButton = false;
                        $scope.emaildetailscontbtn = true;
                        $scope.emailListBackButton = false;
                        $scope.emailDetailsBackButton = true;
                    } else {
                        alert("Please select at least one email list or add email manually.");
                        selectCsvFile();
                        $("#emailaddresses").focus();
                        return false;
                    }
                }
            } else {

            }

        };

        $scope.backToEmailList = function () {
            $scope.showEmailDetails = false;
            $scope.emailListDiv = true;
            $scope.emailContinueButton = true;
            $scope.emaildetailscontbtn = false;
            $scope.emailListBackButton = true;
            $scope.emailDetailsBackButton = false;
        };

        $scope.emailListPreviewOnClick = function () {
            $scope.iframePath = getHost() + "download/HTML?fileName=" + $scope.randomIframeFilename + ".html";
            $scope.fadeClass = 'fadeClass';
            $("#fade").show();
            $scope.emailPreviewPopup = true;
        };

//        $scope.continueEmailDetailsOnClick = function () {alert($scope.draftId);
//            $scope.emailSendPopup = true;
//            $("#fade").show();
//        };

        $scope.sendEmailOnClick = function (fromName, fromAddress, replyAddress, toAddress) {
            var sendEmailData = JSON.stringify({
                from_name: fromName,
                email_subject: $scope.emailSubject,
                email_addresses: $scope.toAddress,
                from_email_address: getDefaultEmailId(),
                reply_to_email_address: replyAddress,
                email_list: $scope.emailList,
                iframeName: $scope.randomIframeFilename.toString()
            });
            emailFactory.sendEmail(sendEmailData).then(function (data) {
                if (data.d.message === "true") {
                    emailDraftFactory.deleteEmailDraftPost($scope.draftId).then(function () {
                        if (responseText === "true")
                        {
                            window.location = "dashboard";
                        }
                    });
                }
            });
        };

//        $scope.emailListValidation = function (postData) {
//            if (postData) {
//                if (!postData.fromName) {
//                    $scope.fromName = "";
//                    $("#name").focus();
//                    return false;
//                }
//                if (!postData.replyAddress) {
//                    $scope.replyAddress = "";
//                    $("#email").focus();
//                    return false;
//                }
//            }
//            return true;
//        };

        $scope.emailListValidation = function (postData) {
            if (postData) {
                if (!postData.fromName) {
                    $scope.fromName = "";
                    $("#name").focus();
                    return false;
                }
                if (!postData.replyAddress) {
                    $scope.replyAddress = "";
                    $("#email").focus();
                    return false;
                }
            }
            return true;
        };

        $scope.replyEmailValidation = function (postData) {
            var replyAddress = postData.replyAddress;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = replyAddress.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#email").focus();
                    $scope.validateEmailAddress = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            $scope.validateEmailAddress = false;
            return true;
        };

        $scope.continueEmailDetailsOnClick = function (postData) {
            if ($scope.emailListValidation(postData))
            {
                if ($scope.replyEmailValidation(postData))
                {
                    $scope.schedulePopup = false;
                    $scope.postTypeSelectionPopUp = true;
                    $scope.postData = postData;
                    $scope.postTo = "Send Now";
                }
            }
        };

        $scope.createNewAction = function () {
            $scope.activeClassExisting = '';
            $scope.activeClassNew = 'active';
            $scope.existingActionPopup = false;
            $scope.createNewActionPopup = true;
        };
        $scope.existingAction = function () {
            $scope.activeClassExisting = 'active';
            $scope.activeClassNew = '';
            $scope.existingActionPopup = true;
            $scope.createNewActionPopup = false;
        };

        $scope.closeMindbodyPopup = function () {
            $scope.emailMindBodyPopup = false;
            $scope.overlayFade = false;
        };

        $scope.ddSelectMarketingCampaign = {
            text: "--General--"
        };

        $scope.getAllMaketingPrograms = function (selectedSocialmedia) {
            $scope.ddSelectMarketingCampaignName = [
                {
                    text: "--General--",
                    value: "0"
                }
            ];
            companyMarketingProgramFactory.getAllUserMarketingProgramsGet().then(function (data) {
                $scope.defaultmarketingprogram = [{program_id: 0, name: '--General--', id: 0}];
                $scope.marketing_programs = $scope.defaultmarketingprogram.concat(data);
                $scope.selectedMarketingProgrma = $scope.marketing_programs[0].program_id;
                var marketingData = data;
                for (var i = 0; i < marketingData.length; i++)
                {
                    var marketingObject = {};
                    marketingObject["text"] = marketingData[i].name;
                    marketingObject["value"] = marketingData[i].program_id;
                    $scope.ddSelectMarketingCampaignName.push(marketingObject);
                }
                $scope.getEmailAction();
            });
        };



//        $scope.getAllMaketingPrograms = function (selectedSocialmedia) {
//            companyMarketingProgramFactory.getAllUserMarketingProgramsGet().then(function (data) {
//                $scope.defaultmarketingprogram = [{program_id: 0, name: '--General--', id: 0}];
//                $scope.marketing_programs = $scope.defaultmarketingprogram.concat(data);
//                $scope.selectedMarketingProgrma = $scope.marketing_programs[0].program_id;
//                $scope.getEmailAction();
//            });
//        };

        $scope.getActions = function (selectedSocialmedia, selectedMarketingProgrmaId) {
            $scope.selectedMarketingProgrma = selectedMarketingProgrmaId.value;
            if (selectedSocialmedia === "email") {
                $scope.getFacebookActions(selectedMarketingProgrmaId.value);
            }
        };

        $scope.getFacebookActions = function (selectedMarketingProgrmaId) {
            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: getemail()});
            scheduleActionsFactory.getActionsPost(data).then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM EMAIL"}];
                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
                $scope.socialAction = $scope.defaultAction[0].id;
                var actionData = eval(parseData);
                if (actionData != '[]') {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                    for (var i = 0; i < actionData.length; i++)
                    {
                        var actionObject = {};
                        actionObject["text"] = actionData[i].schedule_title;
                        actionObject["value"] = actionData[i].id;
                        $scope.ddSelectActionName.push(actionObject);
                    }
                }
                if (parseData == "[]") {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                }
            });
        };

        $scope.setAction = function (selectedAction) {
            $scope.socialAction = selectedAction.value;
            $scope.actionDropdown = false;
        };

        $scope.schedulePostValidation = function () {
            if ($scope.createNewActionPopup) {
                var schedule_title = $("#ActionName").val();
                var schedule_date = $("#actionDate").val();
                var schedule_time = $("#actionTime").val().replace(/ /g, '');
                var actionName = schedule_title;
                var actionDateVal = schedule_date;
                var actionTimeVal = schedule_time;

                if (!actionName) {
                    $("#ActionName").focus();
                    $scope.actionName = "";
                    return false;
                }
                if (!actionDateVal) {
                    $("#actionDate").focus();
                    $scope.actionDateVal = "";
                    return false;
                }
                if (!actionTimeVal) {
//                $("#actionTime").focus();
                    $scope.actionTimeVal = "";
                    return false;
                }
            }
            if ($scope.existingActionPopup) {
                if (!$scope.socialAction) {
                    $scope.actionDropdown = true;
                    return false;
                }
            }
            return true;
        };

        $scope.schedulePost = function (selectedSocialmedia, postData) {
            if ($scope.schedulePostValidation())
            {
                $scope.actionTimeVal = false;
                if (selectedSocialmedia === "email") {
                    $scope.schedulePostToEmail(postData);
                }
            }
        };

        $scope.schedulePostToEmail = function (postData) {
            $scope.postedTo = getemail();
            var email_scheduling = $scope.getScheduleData($scope.selectedMarketingProgrma, postData);
            if ($scope.selectedMarketingProgrma !== 0 || $scope.socialAction !== 0) {
                scheduleActionsFactory.scheduleEmailActionsPost(email_scheduling).then(function (data) {
                    $scope.schedulePopup = false;
                    $scope.isPostSuccess = true;
//                            window.location = "dashboard";
                });
            }
        };

        $scope.getScheduleData = function (selectedMarketingProgrmaId, postData) {
            var email_scheduling = "";
            if (!$scope.createNewActionPopup) {
                email_scheduling = {
                    from_name: postData.fromName,
                    program_id: $scope.selectedMarketingProgrma.toString(),
                    schedule_id: $scope.socialAction.toString(),
                    email_subject: postData.emailSubject,
                    to_email_addresses: postData.toAddress,
                    email_addresses: postData.toAddress,
                    from_email_address: getDefaultEmailId(),
                    reply_to_email_address: postData.replyAddress,
                    email_list: $scope.emailList,
                    email_body: $("#dynamictable").contents().find("html").html(),
                    schedule_desc: ",,,",
                    iframeName: $scope.randomIframeFilename.toString()
                };

                scheduleActionsFactory.scheduleEmailActionsPost(email_scheduling).then(function (data) {
                    if (data.d.operationStatus.statusCode === "Success") {
                        $scope.schedulePopup = false;
                        $scope.isPostSuccess = true;
//                        window.location = "dashboard";
                    }
                });

                if (JSON.stringify(data) !== "") {
                    emailDraftFactory.deleteEmailDraftPost($scope.draftId).then(function (responseText) {
                        if (responseText === "true")
                        {
                            alert("Your Email has been Scheduled Successfully");
                            window.location = "dashboard";
                        }
                    });
                }
            } else {
                var schedule_title = $("#ActionName").val();
                var schedule_date = $("#actionDate").val();
                var schedule_time = $("#actionTime").val().replace(/ /g, '');
                var dateAndTime = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
                var fromDate = new Date(dateAndTime);
                var todayDate = new Date();
                if (fromDate < todayDate) {
                    $scope.dateLesser = true;
                    return false;
                }
                $scope.dateLesser = false;

                var myEpoch = Date.parse(dateAndTime);
                console.log("Epoch: " + myEpoch);

                email_scheduling = {
                    "from_name": postData.fromName,
                    "email_subject": postData.emailSubject,
                    "to_email_addresses": postData.toAddress,
                    "email_addresses": postData.toAddress,
                    "from_email_address": getDefaultEmailId(),
                    "reply_to_email_address": postData.replyAddress,
                    "email_list": $scope.emailList,
                    program_id: $scope.selectedMarketingProgrma.toString(),
                    "schedule_title": schedule_title,
                    "schedule_time": myEpoch,
                    "email_body": $("#dynamictable").contents().find("html").html(),
                    "schedule_desc": ",,,",
                    "iframeName": $scope.randomIframeFilename.toString()
                };
                scheduleActionsFactory.scheduleEmailPost(email_scheduling).then(function (data) {
                    if (data.d.operationStatus.statusCode === "Success") {
                        $scope.schedulePopup = false;
                        $scope.isPostSuccess = true;
//                        window.location = "dashboard";
                    }
                });
                if (JSON.stringify(data) !== "") {
                    emailDraftFactory.deleteEmailDraftPost($scope.draftId).then(function (responseText) {
                        if (responseText === "true")
                        {
                            alert("Your Email has been Scheduled Successfully");
                            window.location = "dashboard";
                        }
                    });
                }
            }
            return email_scheduling;
        };

        $scope.previewCloseButton = function () {
            $scope.emailPreviewPopup = false;
            $scope.fadeClass = 'unfadeClass';
        };

        $scope.editFooter = function () {
            $scope.emailFooterPopupDetails = true;
        };

        $scope.hideEmailFooterPopupDetails = function () {
            $scope.emailFooterPopupDetails = false;
        };

        $scope.closeSendPopup = function () {
            $scope.emailSendPopup = false;
            $("#fade").hide();
        };

        $scope.scheduleEmailPopup = function () {
            $scope.showSchedulePopup = true;
            $("#schedulepopup").show();
        };

        $scope.scheduleActionDate = function () {
            var picker = new Pikaday(
                    {
                        field: document.getElementById('schedule_date'),
                        firstDay: 1,
                        minDate: new Date(2000, 0, 1),
                        maxDate: new Date(2050, 12, 31),
                        yearRange: [2000, 2050]
                    });
        };
        $scope.addDeleteButton = function (id) {

        };

        $scope.closeschedulepopup = function () {
            $scope.showSchedulePopup = false;
        };

        $scope.openSchedulePopup = function () {
            $scope.postTypeSelectionPopUp = false;
            $scope.schedulePopup = true;
            $scope.existingActionPopup = true;
            $scope.createNewActionPopup = false;
            $scope.activeClassExisting = 'active';
            $scope.activeClassNew = '';
            $scope.scheduleButtonData = "Schedule";
        };

        $scope.hidePopup = function (popupName) {
            if (popupName === "sendOrSchedulePopup") {
                $scope.postTypeSelectionPopUp = false;
            } else if (popupName === "schedulePopup") {
                $scope.schedulePopup = false;
            }
        };

        $scope.previousButton = function (popupName) {
            $scope.schedulePopup = false;
            $scope.postTypeSelectionPopUp = true;
        };

//        $scope.ddSelectActionName = [
//            {
//                text: "CUSTOM EMAIL",
//                value: "0"
//            }
//        ];

        $scope.ddSelectAction = {
            text: "CUSTOM EMAIL"
        };

        $scope.getEmailAction = function () {
            $scope.ddSelectActionName = [
                {
                    text: "CUSTOM EMAIL",
                    value: "0"
                }
            ];
            var actionCallData = {
                programid: "0",
                type: getemail()
            };
            scheduleActionsFactory.getActionsPost(actionCallData).then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM FACEBOOK"}];
                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
                $scope.socialAction = $scope.defaultAction[0].id;
                var actionData = eval(parseData);
                for (var i = 0; i < actionData.length; i++)
                {
                    var actionObject = {};
                    actionObject["text"] = actionData[i].schedule_title;
                    actionObject["value"] = actionData[i].id;
                    $scope.ddSelectActionName.push(actionObject);
                }
            });
        };

//        $scope.getEmailAction = function () {
//            var actionCallData = {
//                programid: "0",
//                type: getemail()
//            };
//            scheduleActionsFactory.getActionsPost(actionCallData).then(function (data) {
//                var parseData = JSON.parse(data.d.details);
//                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM FACEBOOK"}];
//                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
//                $scope.socialAction = $scope.defaultAction[0].id;
//            });
//        };
        $scope.postToSocialMedia = function (selectedSocialmedia, postData) {
            $scope.isMailSent = false;
            if (selectedSocialmedia === "email") {
                var sendEmailData = JSON.stringify({
                    from_name: postData.fromName,
                    email_subject: postData.emailSubject,
                    email_addresses: postData.toAddress,
                    from_email_address: getDefaultEmailId(),
                    reply_to_email_address: postData.replyAddress,
                    email_list: $scope.emailList,
                    iframeName: $scope.randomIframeFilename.toString()
                });
                emailFactory.sendEmail(sendEmailData).then(function (data) {
                    if (data.d.message === "true") {
                        emailDraftFactory.deleteEmailDraftPost($scope.draftId).then(function (responseText) {
                            if (responseText === true)
                            {
                                $scope.isMailSent = true;
//                                window.location = "dashboard";
                            }
                        });
                    }
                });
            }
        };
    }]);


emailFlowApp.directive('fileReader', function () {
    return {
        $scope: {
            fileReader: "="
        },
        link: function ($scope, element) {
            $(element).on('change', function (changeEvent) {
                var files = changeEvent.target.files;
                if (files.length) {
                    var r = new FileReader();
                    r.onload = function (e) {
                        var contents = e.target.result;
                        $scope.$apply(function () {
                            $scope.emailAddresses = contents;
                        });
                    };

                    r.readAsText(files[0]);
                }
            });
        }
    };
});
