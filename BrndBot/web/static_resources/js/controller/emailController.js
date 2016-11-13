emailFlowApp.controller("emailController", ['$scope', '$filter', '$window', '$location', 'blockModelFactory', 'companyFactory', 'categoryFactory', 'emailDraftFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', 'settingsFactory', 'companyMarketingProgramFactory', 'emailFactory', 'modelFactory', 'emailListFactory', 'scheduleActionsFactory', 'appSessionFactory', 'yourPlanFactory', 'rulesEngineFactory', 'onboardingFactory','franchiseFactory','pushedActionsFactory', 'utilFactory','companyImagesFactory','imageFactory','uploadImageFactory',
    function ($scope, $filter, $window, $location, blockModelFactory, companyFactory, categoryFactory, emailDraftFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService, settingsFactory, companyMarketingProgramFactory, emailFactory, modelFactory, emailListFactory, scheduleActionsFactory, appSessionFactory, yourPlanFactory, rulesEngineFactory, onboardingFactory, franchiseFactory, pushedActionsFactory, utilFactory,companyImagesFactory,imageFactory,uploadImageFactory) {

        $scope.objectParameter = kEmailFlowObject;
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
        $scope.ddSelectedUser= '0';
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
        $scope.colpic = false;
        $scope.colorcodeArray = [];
        var seldiv = "";
        var sliderDialog = "#emaileditorexternalpopup";
        $scope.user = "";
        $scope.companyAddressDetails = {};
        $scope.selectImageId="";
        $scope.addBlockStyle = true;
        
        
        // validation scope variables
        
        $scope.subjectValidation = subjectValidation;
        $scope.preHeaderValidation = preHeaderValidation;
        $scope.fromNameValidation = fromNameValidation;
        $scope.fromAddressValidation = fromAddressValidation;
        $scope.replyToValidation = replyToValidation;
        $scope.actionNameValidation = actionNameValidation;
        $scope.scheduleDateValidation = scheduleDateValidation;
        $scope.lesserDateValidation = lesserDateValidation;
        $scope.scheduleTimeValidation = scheduleTimeValidation;
        $scope.actionNameListValidation = actionNameListValidation;
        $scope.listValidation = listValidation;
        $scope.emailValidation = emailValidation;
        $scope.companyAddressValidation = companyAddressValidation;
        $scope.invalidCompanyAddressValidation = invalidCompanyAddressValidation;
        $scope.addressLine1Validation = addressLine1Validation;
        $scope.addressLine2Validation = addressLine2Validation;
        $scope.cityValidation = cityValidation;
        $scope.stateValidation = stateValidation;
        $scope.zipcodeValidation = zipcodeValidation;
        $scope.countryValidation = countryValidation;
        $scope.isEmailSubEmpty = false;
        $scope.ispreHeaderEmpty = false;
        $scope.actionTimeVal = false;
        $scope.actionDropdown = false;
        $scope.dateLesser = false;
        $scope.validateEmailAddress = false;
        $scope.validateFromEmailAddress = false;
        $scope.isEmailSaveAction = false;
        $scope.companyName = "";
        $scope.changeStyleAlert = false;
        $scope.pushedEmail = false; 
        $scope.emailList = "";
        $scope.emailTag = 0;
        $scope.noEmailList = "";
        $scope.noEmailSettings = false;
        $scope.postData = {};
        var sliderDialog = "#emaileditorexternalpopup";
        var companies = [];
        var companiesWithNoEmailList = [];
        var userRoles = {};
        this.tab = 1;

        this.selectTab = function (setTab){
           this.tab = setTab;  
        };

        this.isSelected = function(checkTab) {
            return this.tab === checkTab;
        };
         
        $scope.moreThanOneUser = false;

        
        $scope.ddSelectUserOptions = [ {
                text: 'Select',
                value: '0'
            }
        ];
        $scope.ddSelectUser = {text: "Select"};
        
        
        $scope.chooseUserOnChange = function (actionValue) {
            $scope.ddSelectedUser = actionValue.value;
        };
        
        $scope.toggleAll = function() {
           var toggleStatus = !$scope.isAllSelected;
           var checked = $("#selectAll:checked").val()
           if (checked){
               angular.forEach($scope.franchiseCompanies, function(itm){
                   itm.selected = toggleStatus;
               });
               for (var i = 0;i< $scope.franchiseCompanies.length; i++){
                   var company = $scope.franchiseCompanies[i];
                   var companyIDs = {};
                    companyIDs["companyId"] = company.companyId;

                    companies.push(companyIDs);
               }
           }else {
               toggleStatus = false;
               angular.forEach($scope.franchiseCompanies, function(itm){ itm.selected = toggleStatus; });
               for (var i = 0;i< $scope.franchiseCompanies.length; i++){
                   var company = $scope.franchiseCompanies[i];
                   var companyIDs = {};
                    companyIDs["companyId"] = company.companyId;

                    companies.pop(companyIDs);
               }
           }
        };

        $scope.optionToggled = function(){
          $scope.isAllSelected = $scope.franchiseCompanies.every(function(itm){ return itm.selected; });
        };
        
        $scope.sendReminderEmailToCreateEmailList = function(){
            appSessionFactory.getEmail().then(function(kGlobalEmailObject){
                var emailTagId = kGlobalEmailObject.emailTagId;
                var sendReminderEmailDetails = {"companyIds": companiesWithNoEmailList, "emailListTagId":emailTagId};
                franchiseFactory.sendReminderEmailToCreateEmailListPost(sendReminderEmailDetails).then(function (data){

                });
            });
        };
        $scope.setSelectCompany = function(company){
            var companyId = $("#"+company+ ":checked").val();
            
            if(companyId){
                var companyIDs = {};
                companyIDs["companyId"] = companyId;

                companies.push(companyIDs);
            }else {
                for(var i = 0 ; i< companies.length; i++){
                    var comp = companies[i];
                    if (comp.companyId == company){
                        companies.pop(comp);
                    }
                }
            }
            $scope.optionToggled();
        };

        //OnPageLoad
        $scope.emailEditorInit = function () {
            $scope.getAllCompanyImages();
            $scope.emailEditorContinueButton=true;
            $scope.loadingOverlay = true; //start Loading Overlay
            $('#slider-button').click(function () {
                if ($('#slider-button').css("margin-right") === "900px")
                {
                    $(sliderDialog).animate({"margin-right": '-=900px'});
                    $('#slider-button').animate({"margin-right": '-=788px'});
                } else
                {
                    $(sliderDialog).animate({"margin-right": '+=900px'});
                    $('#slider-button').animate({"margin-right": '+=788px'});
                }
            });
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                $scope.companyName = kGlobalCompanyObject.companyName;
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    if (kGlobalEmailObject.entityScheduleId) {
                        $scope.isEmailSaveAction = true;
                    }
                    if (kGlobalEmailObject.draftId) {
                        $scope.objectParameter.redirect='emaileditor';
                        $scope.objectParameter.categoryId=kGlobalEmailObject.categoryId;
                        $scope.objectParameter.subCategoryId=kGlobalEmailObject.subCategoryId;
                        $scope.objectParameter.lookupId= kGlobalEmailObject.lookupId;
                        $scope.objectParameter.mindbodyId= kGlobalEmailObject.mindbodyId;
                        $scope.objectParameter.draftId= kGlobalEmailObject.draftId;
                        $scope.objectParameter.emailSubject= kGlobalEmailObject.emailSubject;
                        $scope.objectParameter.preHeader= kGlobalEmailObject.preheader;
                        $scope.redirect();
                    }
                    if (!kGlobalEmailObject.emailScheduleId) {
                        companyMarketingProgramFactory.getAllUserMarketingProgramsSessionIdGet().then(function (urlList) {
                            //                $('#edit').froalaEditor({key: FroalaLicenseKey, linkList: urlList});
                            $scope.blockIdOnSelected('defaultblock1', 0, kGlobalEmailObject.mindbodyId);
                            if (!kGlobalEmailObject.draftId) {
                                modelFactory.EmailModelsIdGet(kGlobalEmailObject.subCategoryId).then(function (templateDate) {
                                    var blockList = templateDate.d.details.reverse();
                                    $scope.addHTMLInEmailEditor(blockList[0].modelId);
                                });
                            } else {
                                $scope.getEmailDrafts(kGlobalEmailObject.draftId);
                            }
                            $scope.loadingOverlay = false; //stop Loading Overlay
                            $scope.hideEmailEditorOverlay = true;
                            $scope.showBlocks();
                        });
                    } else {
                        $scope.blockIdOnSelected('defaultblock1', 0, 0);

                        settingsFactory.getAllPreferencesGet().then(function (data) {
                            $("#tinymceEditorBody").append(kGlobalEmailObject.htmlBody);
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                            $scope.loadingOverlay = false; //stop Loading Overlay
                            $scope.hideEmailEditorOverlay = true;
                            $scope.showBlocks();
                        });
                    }

                });
            });
            $scope.getColor();
        };
        $scope.getColor = function () {
            var colorcodeArray = [];
            var hexDigits = new Array
                    ("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f");
//Function to convert hex format to a rgb color
            function rgb2hex(rgb) {
                rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
                return  hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
            }
            function hex(x) {
                return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
            }
            $.ajax({
                async: false,
                url: global_host_address + 'settings/getColors.do',
                dataType: 'json',
                success: function (data) {
                    var k = 4;
                    for (var i = 0; i < data.d.details.length; i++)
                    {
                        colorcodeArray[i] = rgb2hex(data.d.details[i]);
                    }
                    for (var j = 0; j < defaultFroalaColors.length; j = (j + 2)) {
                        colorcodeArray[k] = defaultFroalaColors[j];
                        k++;
                    }
                    $scope.colorcode = {colors: colorcodeArray};
                }
            });

        };
        $scope.chengeTemplateColor = function (color) {
            $(seldiv).find('table:first').attr('bgcolor', '#' + color);
            $("#colpic").hide();
        };

        $scope.emailFlowValidation = function (emailSubject, preHeader) {
            if (!emailSubject) {
//                $scope.$parent = {emailsubject: ""};
                $("#emailSub").focus();
                $scope.isEmailSubEmpty = true;
                return false;
            }
            // commented for new requirement.
//            if (!preHeader) {
//                $("#preHeader").focus();
//                $scope.ispreHeaderEmpty = true;
//                return false;
//            }
            $scope.isEmailSubEmpty = false;
            $scope.ispreHeaderEmpty = false;
            return true;
        };
        
        $scope.redirect = function () {
            
            //preHeader kept hidden so sending empty value
            $scope.objectParameter.preHeader = "";
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                
                if ($scope.objectParameter.lookupId)
                {
                    kGlobalEmailObject.lookupId = $scope.objectParameter.lookupId;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }

                if ($scope.objectParameter.lookupId === 0)
                {
                    $scope.lookupId = $scope.objectParameter.lookupId;
                }


                if ($scope.objectParameter.categoryId)
                {
                    kGlobalEmailObject.categoryId = $scope.objectParameter.categoryId;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }

                if ($scope.objectParameter.subCategoryId)
                {
                    kGlobalEmailObject.subCategoryId = $scope.objectParameter.subCategoryId;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }
                
                if ($scope.objectParameter.mindbodyId)
                {
                    kGlobalEmailObject.mindbodyId = $scope.objectParameter.mindbodyId;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }

                if ($scope.objectParameter.draftId)
                {
                    kGlobalEmailObject.draftId = $scope.objectParameter.draftId;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }
                if ($scope.objectParameter.emailSubject)
                {
                    kGlobalEmailObject.emailSubject = $scope.objectParameter.emailSubject;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }
                if ($scope.objectParameter.preHeader)
                {
                    kGlobalEmailObject.preheader = $scope.objectParameter.preHeader;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                }
                if ($scope.objectParameter.mindbody === 'Mindbody')
                {
                    kGlobalEmailObject.mindbody = 'mindbody';
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    });
                    $scope.objectParameter.redirect = $scope.forwardone;
                }

                if ($scope.objectParameter.mindbody !== 'Mindbody')
                {
                    if ($scope.objectParameter.redirect === 'emailexternalsource')
                    {
                        $scope.objectParameter.redirect = $scope.forwardtwo;
                    }
                }

                if ($scope.objectParameter.redirect !== 'emaileditor') {
                    $location.path("/" + $scope.objectParameter.redirect);
                }
                
                if ($scope.objectParameter.redirect === 'emaileditor') {
                    if ($scope.emailFlowValidation($scope.objectParameter.emailSubject, $scope.objectParameter.preHeader))
                    {
                        $location.path("/" + $scope.objectParameter.redirect);
                    }
                }
            });
        };
        
        $scope.getSubject_preHeader = function () {
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (kGlobalEmailObject.entityScheduleId) {
                    $scope.emailsubject = kGlobalEmailObject.emailSubject;
                    $scope.preheader = kGlobalEmailObject.preheader;
                    if (kGlobalEmailObject.emailSubject) {
                        $scope.isEmailSaveAction = true;
                    } else {
                        $scope.isEmailSaveAction = false;
                    }
                }
            });
        };

        $scope.redirectToEmailFlow = function (forwardone)
        {
            redirectFactory.redirectFlowTo(forwardone);
            $window.location = getHost() + "user/" + forwardone;
        };
        $scope.getCategories = function ()
        { 
            categoryFactory.allCompanyCategoriesGet($scope.emailChannelId).then(function (data) {
                $scope.pageName = "emailcategory";
                $scope.header = "Select Category";
                $scope.forwardone = $scope.objectParameter.redirect;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getSubCategories = function (forwardone, forwardtwo)
        {
            $scope.pageName = "emailsubcategory";

            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                subCategoryFactory.allSubCategoriesGet(kGlobalEmailObject.categoryId).then(function (data) {
                    $scope.pageName = "emailsubcategory";
                    $scope.header = "Select Subcategory";
                    $scope.forwardone = forwardone;
                    $scope.objectParameter.redirect = forwardone;
                    $scope.forwardtwo = forwardtwo;
                    $scope.displayAllCategories = data.d.details;
                });
            });
        };
        $scope.getActive = function ()
        {
            $scope.hideGifImage = true;
//            $scope.loadingOverlay = true; //start Loading Overlay
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                externalContentFactory.activatedGet(kGlobalEmailObject.lookupId).then(function (data) {
                    var minddata = JSON.stringify(data.d.details);
                    if (minddata === "[true]") {
                        externalContentFactory.listDataGet(kGlobalEmailObject.lookupId).then(function (data) {
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
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (kGlobalEmailObject.draftId) {
                    emailDraftFactory.getEmailDraftGet(emailDrafts).then(function (data) {
                        if (data === "") {
                            $scope.emaildraftsstatus = "No email drafts present";
                        } else {
                            $scope.addBlockCount = data.blockAddedCount;
                            $scope.htmlbody = data.htmlbody;
                            $("#tinymceEditorBody").append(data.htmlbody);
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                        }
                    });
                }
            });
        };

        $scope.blockdivheader = true;
        $scope.styledivheader = false;
        $scope.blocktab = "emailSideBar-tab-active";
        $scope.styletab = "emailSideBar-tab";
        $scope.showStyles = function (isClick) {
//            if (isClick === "true")
//            {
//                $scope.blockdivheader = false;
//                $scope.styledivheader = true;
//                $scope.blocktab = "emailSideBar-tab";
//                $scope.styletab = "emailSideBar-tab-active";
//            }
            if ($scope.isBlockClicked === "true" || $scope.htmlBlockId !== "defaultblock1")
            {
                blockModelFactory.allEmailBlockModelGet($scope.selectedBlockId, false).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            } else
            {
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    modelFactory.EmailModelsIdGet(kGlobalEmailObject.subCategoryId).then(function (data) {
                        $scope.datalistsstyles = data.d.details;
                    });
                });
            }
        };

        $scope.showBlocks = function () {
//            $scope.blockdivheader = true;
//            $scope.styledivheader = false;
//            $scope.styletab = "emailSideBar-tab";
//            $scope.blocktab = "emailSideBar-tab-active";
            companyFactory.allBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;
            });
        };

        $scope.blockOnClick = function (id) {
            $scope.id = id;
            $scope.id = 'editor-block-slat';
            $scope.setBlockActive = 'editor-block-slat-selected';
            $scope.activeBlock = id;
//            TODO change to AngularJs, (Complicated code)
//            $("#blockdiv li").removeClass("block-slat-active");
//            $("#blockdiv li").addClass("block-slat");
//            $(".block-button").addClass("hide");
//            $("#blockdiv li").removeClass("block-slat-active");
//            $("#blockdiv li").addClass("block-slat");
//            $("#" + id).removeClass("block-slat");
//            $("#" + id).addClass("block-slat-active");
//            $("#div2" + id).removeClass("hide");
            $("#stylelist").css("display", "none");
            $("#blklist").css("display", "block");
//            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            $(":button").removeAttr("disabled");
//            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
        };


        $scope.didChooseBlock = function (block) {
//            $scope.loadingOverlay = true; //start Loading Overlay
            blockModelFactory.allEmailBlockModelGet(block.emailBlockId, false).then(function (data) {
                $scope.firstTemplateForBlock = data.d.details[0].emailBlockModelLookupId;
                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = block.emailBlockId;
                ++$scope.addBlockCount;
                $scope.htmlTagId = "block" + $scope.addBlockCount;
                if (block.externalSourceKeywordLookupId === 0)
                {
                    $scope.emailMindBodyPopup = false;
                    appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                        kGlobalEmailObject.mindbodyId = "0";
                        appSessionFactory.setEmail(kGlobalEmailObject).then(function () {
                        });
                    });
                    $scope.addHTMLInEmailEditor($scope.firstTemplateForBlock);
                    $scope.loadingOverlay = false;
                    $scope.blockOnClick(0);
//                    $scope.showStyles('true');
                } else
                {
                    $scope.hideMindbodyOverlay = false;
                    $scope.emailMindBodyPopup = true;
                    $("#fade").show();
                    $scope.overlayFade = true;
                    $scope.loadingOverlay = true; //start Loading Overlay
                    $scope.emailScrollyDiv = false;
                    externalContentFactory.activatedGet(block.externalSourceKeywordLookupId).then(function (data) {
                        var externalData = JSON.stringify(data.d.details);

                        if (externalData === "[true]") {
                            externalContentFactory.listDataGet(block.externalSourceKeywordLookupId).then(function (listData) {
                                var parseData = JSON.parse(listData.d.details);
                                $scope.mindbodyDataList = parseData;
                                $("#fade").show();
                                $('#slider-button').click();
                                $scope.loadingOverlay = false; //start Loading Overlay
                                $scope.hideMindbodyOverlay = true;
                                $scope.emailScrollyDiv = true;
//                                $scope.showStyles();
                            });
                        }
                    });
                }
            });
        };


        $scope.didSelectMindbodyDataId = function (id) {
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                kGlobalEmailObject.mindbodyId = id;
                appSessionFactory.setEmail(kGlobalEmailObject).then(function () {
                    $scope.showStyles("true");
                    $scope.addHTMLInEmailEditor($scope.firstTemplateForBlock);
                    $scope.closeMindbodyPopup();
                    $scope.blockOnClick(0);
                });
            });

        };

        $scope.addHTMLInEmailEditor = function (templateId) {
            var mindbodyId = 0;
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (!kGlobalEmailObject.mindbodyId) {
                } else {
                    mindbodyId = kGlobalEmailObject.mindbodyId;
                }
                externalContentFactory.layoutEmailModelGet(templateId, $scope.isBlockClicked, mindbodyId).then(function (data) {
                    var emailData = JSON.parse(data.d.details);
                    if ($scope.isBlockClicked === "false") {

                        var editorHtml = $('#tinymceEditorBody').html();
                        if (editorHtml.contains('id="defaultblock1"')) {
                            $("#defaultblock1").html('<div class="view">' + emailData.htmldata + '</div>');
//                        $("#defaultblock1").remove();
//                        editorHtml = $('#tinymceEditorBody').html();
                        } else
                        {
                            var styleHtml = '<div id=defaultblock1 class=module onclick="angular.element(this).scope().blockIdOnSelected(defaultblock1,0,' + mindbodyId + ')"><div class="view">' + emailData.htmldata + '</div></div>';
//                    var styleHtml = '<div id=defaultblock1 class=module onclick="angular.element(this).scope().blockIdOnSelected(defaultblock1,0)"><div class=\"view\"><table width=\"100%\" bgcolor=\"#2a2a2a\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tbody><tr><td><table bgcolor=\"#d41b29\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\"><div class=\"innerbg\"></div><div class=\"addremove\" style=\"margin-left: 975px;\"><div class=\"drag\"></div><div class=\"remove\"></div></div><tbody><tr><td width=\"100%\">' + emailData.htmldata + '</td></tr></tbody></table></div>';

                            $("#tinymceEditorBody").append(styleHtml);
                        }
                        if ($scope.companyName.indexOf("Dailey") >= 0) {
                            $scope.launchTinyMceEditorForOnlyImage();
                        } else {
                            $scope.launchTinyMceEditor();
                        }

                    } else {
                        var editorHtml = $('#tinymceEditorBody').html();
                        if (editorHtml.contains('id="' + $scope.htmlTagId + '"')) {
                            $("#" + $scope.htmlTagId).html('<div class="view">' + emailData.htmldata + '</div>');
//                        $("#" + $scope.htmlTagId).remove();
//                        var BlockHtml = '<div id=' + $scope.htmlTagId + ' onclick=angular.element(this).scope().blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
//                        $("#tinymceEditorBody").append(BlockHtml);
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                        } else
                        {
                            BlockHtml = '<div id=' + $scope.htmlTagId + '  class=module onclick=angular.element(this).scope().blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ',' + mindbodyId + ')><div class="view">' + emailData.htmldata + '</div></div>';
                            $("#tinymceEditorBody").append(BlockHtml);
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                        }
                    }
                });
            });
        };
        $scope.launchTinyMceEditor = function () {
            tinymce.EditorManager.editors = [];
            tinymce.init({
                selector: 'td.mce-content-body',
                extended_valid_elements: 'img[class|id|src|style|border=0|alt|title|hspace|vspace|width|height|max-width|max-height|align|onmouseover|onmouseout|name]',
//                forced_root_block : false,
                width: 400,
                convert_urls: false,
                inline: true,
                plugins: [
                    'advlist custombutton autolink lists link image charmap print preview anchor',
                    'searchreplace visualblocks code fullscreen',
                    'insertdatetime media table contextmenu paste',
                    'template paste textcolor colorpicker textpattern imagetools'
                ],
                toolbar1: 'undo | bold italic |  link forecolor | alignleft aligncenter alignright alignjustify | fontselect fontsizeselect custombutton',
                menubar: false
            });
            $('.innerbg').mouseenter(function (event) {
                $("#colpic").css({position: "absolute", top: event.pageY, left: "20px"}).css(" z-index", 30000).show();
                seldiv = $(this).parents('[bb-bgcolor]');
            });
            $(document).click(function () {
                $("#colpic").hide();
            });
            $('.view').find('table:first').find('td:first').mouseenter(function () {
                $(this).find('table:first').addClass('template-border-Active');
            });
            $('.view').find('table:first').find('td:first').mouseleave(function () {
                $(this).find('table:first').removeClass('template-border-Active');
            });
            $('.img_upload').click(function (){
                $scope.addBlockStyle = false;
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
            });
            $('.img_edit').click(function (){
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                launchEditor($scope.selectImageId);
            });
            $('.img_link').click(function (){
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                 $scope.addLinkPopup = true;
            });
            $('td img').mouseenter(function(event){
                $(this).parent('td').find('div:first').css("opacity",1);
            });
            $('td div img').mouseleave(function (event){
                $(this).parent('td').find('div:first').css("opacity",0);
            });
            $('td div .uploader_wrap').mouseenter(function(event){
                $(this).parent('td').find('div:first').css("opacity",1);
            });
            $('td div .uploader_wrap').mouseleave(function (event){
                $(this).parent('td').find('div:first').css("opacity",0);
            });
            
        };
         function launchEditor(id, src) {
                    featherEditor.launch({
                        image: id,
                        url: src,
                        cropPresets:[
                                'Custom',
                                'Original',
                                ['680x330', '68:33'],
                                ['350x350', '35:35'],
                                ['310x370', '31:37']
                            ]
                    });
                   return false;
                }
          var featherEditor = new Aviary.Feather({
                    apiKey: getAviaryApiKey(),
                    apiVersion: 3,
                    theme: 'dark', // Check out our new 'light' and 'dark' themes!
                    tools: 'all',
                    appendTo: '',
                    onSave: function(imageID, newURL) {
                        var img = document.getElementById(imageID);
                        var data = {folderName:"aviary",
                                 imageUrl:newURL
                             };
                        uploadImageFactory.uploadByImageUrlPost(data).then(function (response){
                            img.src = responseText.link;
                                       featherEditor.close();
                        });
                    },
                    onError: function(errorObj) {
                        alert(errorObj.message);
                    }
                }); 
         $scope.addLinkToImage = function (link) {
              $("#"+$scope.selectImageId).wrap('<a href='+link+'></a>');
              $scope.addLinkPopup = false;
         };      
         $scope.getAllCompanyImages = function () {
                    companyImagesFactory.companyImagesGet().then(function (getData) {
                        $scope.datalists = getData.d.details;
                        $scope.currentCompanyId = getData.d.details[0].fkCompanyId.companyId;
                    });
                };
         $scope.changeTemplateImage = function (imageId){ 
             $scope.addBlockStyle = true;
                var imageSrc = $("#"+imageId).attr("src");
             $("#"+$scope.selectImageId).attr("src",imageSrc);
         };
         $scope.showImageUploadPopup = function (){
                    $("#filesToUpload").trigger("click");
                };
         $scope.uploadFile = function () {
            imageFactory.saveImagePost(event.target.files[0]).then(function (data) {
                $scope.getAllCompanyImages();

               });
              };
        $scope.launchTinyMceEditorForOnlyImage = function () {
            tinymce.EditorManager.editors = [];
            tinymce.init({
                selector: 'td.mce-content-body',
                extended_valid_elements: 'img[class|id|src|style|border=0|alt|title|hspace|vspace|width|height|max-width|max-height|align|onmouseover|onmouseout|name]',
                width: 400,
                convert_urls: false,
                inline: true,
                plugins: [
                    'advlist autolink lists link image',
                    'media table',
                    'imagetools'
                ],
                 toolbar1: 'undo | bold italic |  link',
                menubar: false
            });
            $('.innerbg').mouseenter(function (event) {
                $("#colpic").css({position: "absolute", top: event.pageY, left: "20px"}).css(" z-index", 30000).show();
                seldiv = $(this).parents('[bb-bgcolor]');
            });
            $(document).click(function () {
                $("#colpic").hide();
            });
            $('.view').find('table:first').find('td:first').mouseenter(function () {
                $(this).find('table:first').addClass('template-border-Active');
            });
            $('.view').find('table:first').find('td:first').mouseleave(function () {
                $(this).find('table:first').removeClass('template-border-Active');
            });
            $('.img_upload').click(function (){
                $scope.addBlockStyle = false;
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
            });
            $('.img_edit').click(function (){
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                launchEditor($scope.selectImageId);
            });
            $('.img_link').click(function (){
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                 $scope.addLinkPopup = true;
            });
            $('td img').mouseenter(function(event){
                $(this).parent('td').find('div:first').css("opacity",1);
            });
            $('td div img').mouseleave(function (event){
                $(this).parent('td').find('div:first').css("opacity",0);
            });
            $('td div .uploader_wrap').mouseenter(function(event){
                $(this).parent('td').find('div:first').css("opacity",1);
            });
            $('td div .uploader_wrap').mouseleave(function (event){
                $(this).parent('td').find('div:first').css("opacity",0);
            });
        };
        $scope.blockIdOnSelected = function (selectedBlock, blockId, mindbodyId) {
            var selectedHtmlBlockId = selectedBlock.id;
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                kGlobalEmailObject.mindbodyId = mindbodyId;
                appSessionFactory.setEmail(kGlobalEmailObject).then(function () {
                });
            });

            $("#colpic").hide();
//            $("#" + selectedHtmlBlockId).children().find("table:first").children().find("table:first").addClass("template-border-Active");
//            $("#" + selectedHtmlBlockId).siblings().children().find("table:first").children().find("table:first").removeClass("template-border-Active");
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
            $scope.showAddBlockOnTemplateClick();
//            $scope.showStyles(true);
//            $scope.blockdivheader = false;
//            $scope.styledivheader = true;
//            $scope.blocktab = "emailSideBar-tab";
//            $scope.styletab = "emailSideBar-tab-active";
        };

        $scope.showAddBlockOnTemplateClick = function () {
            $scope.blockdivheader = true;
            $scope.styledivheader = false;
            $scope.styletab = "emailSideBar-tab";
            $scope.blocktab = "emailSideBar-tab-active";
        };
        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.companyAddressDetails = JSON.parse(data.d.details).companyAddress[0];
                $scope.companyAddressDetails.companyAddress=$scope.companyAddressDetails.addressLine1+" "
                                        +$scope.companyAddressDetails.addressLine2+", "
                                        +$scope.companyAddressDetails.city+", "
                                        +$scope.companyAddressDetails.state+" "
                                        +$scope.companyAddressDetails.zipCode+", "
                                        +$scope.companyAddressDetails.country;
                $scope.footerDetails = JSON.parse(data.d.details).companyProfile;
                $scope.company = $scope.footerDetails;
            });
        };

        $scope.validateCompanyAddress = function (companyData) {
             if (!companyData.companyAddress) {
                $scope.companyData.companyAddress = "";
                $("#companyAddress").focus();
                return false;
                } else if (!companyData.addressline2 || !companyData.city || !companyData.state || !companyData.zipcode || !companyData.country) {
                    $("#companyAddress").focus();
                    return false;
                } 
//            if (!companyData.addressLine1) {
//                $scope.companyAddressDetails.addressLine1 = "";
//                $("#addressLine1").focus();
//                return false;
//            }
//            else if (!companyData.addressLine2) {
//                $scope.companyAddressDetails.addressLine2 = "";
//                $("#addressLine2").focus();
//                return false;
//            }
//            else if (!companyData.city) {
//                $scope.companyAddressDetails.city = "";
//                $("#city").focus();
//                return false;
//            } else if (!companyData.state) {
//                $scope.companyAddressDetails.state = "";
//                $("#state").focus();
//                return false;
//            } else if (!companyData.zipCode) {
//                $scope.companyAddressDetails.zipCode = "";
//                $("#zipcode").focus();
//                return false;
//            } else if (!companyData.country) {
//                $scope.companyAddressDetails.country = "";
//                $("#country").focus();
//                return false;
//            }
            return true;
        };

        $scope.changeFooterDetails = function (company) {
            
            $scope.address1 = document.getElementById('street_number').value;
            $scope.address2 = document.getElementById('route').value;
            $scope.city = document.getElementById('locality').value;
            $scope.state = document.getElementById('administrative_area_level_1').value;
            $scope.zipcode = document.getElementById('postal_code').value;
            $scope.country = document.getElementById('country').value;
            
            if ($scope.validateCompanyAddress(company)) {

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
                var footerPopupDetails = {"facebookUrl": footerFacebookUrl, "twitterUrl": footerTwitterUrl, "instagramUrl": footerInstagramUrl, "websiteUrl": footerWebsiteUrl};
                $scope.emailFooterPopupDetails = false;
                settingsFactory.setFooterPost(footerPopupDetails).then(function (data) {
                    $scope.getFooterDetails();
                });

                var companyAddress = {"addressLine1": $scope.address1, "addressLine2": $scope.address2, "city": $scope.city, "state": $scope.state, "zipcode": $scope.zipcode, "country": $scope.country};
                onboardingFactory.saveCompanyAddress(companyAddress).then(function (data) {
                    growl(companyAddressSaved);
                });
            }
        };


        $scope.getUserFooter = function (footerData) {

            var companyAddress = "";
            if (footerData.companyAddress)
            {
                if (footerData.companyAddress[0].addressLine1) {
                    companyAddress = footerData.companyAddress[0].addressLine1 + "<br/>";
                }
                if (footerData.companyAddress[0].addressLine2) {
                    companyAddress = companyAddress + footerData.companyAddress[0].addressLine2 + "<br/>";
                }
                if (footerData.companyAddress[0].city)
                {
                    companyAddress = companyAddress + footerData.companyAddress[0].city;
                }
                if (footerData.companyAddress[0].state) {
                    companyAddress = companyAddress + ", " + footerData.companyAddress[0].state + "\t\t";
                }
                if (footerData.companyAddress[0].zipCode) {
                    companyAddress = companyAddress + footerData.companyAddress[0].zipCode + "<br/>";
                }
                if (footerData.companyAddress[0].country) {
                    companyAddress = companyAddress + footerData.companyAddress[0].country;
                }
            }

            var returnFooter = "";
            var footer = kGlobalFooterTop;
            
            var footerFB = kGlobalFooterFB;

            var footerTwitter = kGlobalFooterTwitter;

            var footerWebsite = kGlobalFooterWebsite;

            var footerInstagram = kGlobalFooterInstagram;

            var footerMiddle = kGlobalFooterMiddle;

            var footerAddress = kGlobalFooterAddress;

            var footerClose = kGlobalFooterBottom;


            returnFooter = footer;
            if (footerData.companyProfile) {
                if (footerData.companyProfile.facebookUrl)
                    returnFooter += footerFB.replace("$$$footerFB$$$", footerData.companyProfile.facebookUrl);
                if (footerData.companyProfile.twitterUrl)
                    returnFooter += footerTwitter.replace("$$$footerTwitter$$$", footerData.companyProfile.twitterUrl);
                if (footerData.companyProfile.instagramUrl)
                    returnFooter += footerInstagram.replace("$$$footerInstagram$$$", footerData.companyProfile.instagramUrl);
            }
            returnFooter += footerMiddle;
            if (footerData.companyProfile) {
                if (footerData.companyProfile.websiteUrl)
                    returnFooter += footerWebsite.replace("$$$footerWebsite$$$", footerData.companyProfile.websiteUrl);
            }
            returnFooter += footerAddress.replace("$$$footerAddress$$$", companyAddress);

            returnFooter += footerClose;

            return returnFooter;
        };


        $scope.emailPreviewOnClick = function () {
            $("#tinymceEditorBody").find("p").removeAttr("style").css("margin", "0px");
            settingsFactory.getAllPreferencesGet().then(function (data) {
                var footerData = JSON.parse(data.d.details);

//                if(!footerData.companyAddress){
//                    $scope.emailFooterPopupDetails=true;
//                }else{
                $scope.fadeClass = 'fadeClass';
                $scope.emailPreviewPopup = true;
                $scope.overlayFade = true;
                var footer = $scope.getUserFooter(footerData);
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
//                }
            });
        };

        $scope.exitFromEmailFlow = function() {
            document.location.href = "dashboard";
        }

        $scope.saveToDraftOnClick = function (isEmailEditor) {
            if (isEmailEditor) {
                settingsFactory.getAllPreferencesGet().then(function (data) {
                    var footerData = JSON.parse(data.d.details);

                    var footer = $scope.getUserFooter(footerData);

                    var sendData = {
                        htmlString: $('#tinymceEditorBody').html() + footer,
                        iframeName: $scope.randomIframeFilename.toString()
                    };

                    emailFactory.previewServletPost(sendData).then(function () {
                        appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                            if (!kGlobalEmailObject.draftId) {
                                if (!kGlobalEmailObject.lookupId) {
                                    kGlobalEmailObject.lookupId = 0;
                                }
                                var draftData = {
                                    bodyString: $('#tinymceEditorBody').html(),
                                    lookupId: kGlobalEmailObject.lookupId.toString(),
                                    mindbodyData: kGlobalEmailObject.mindbodyId.toString(),
                                    categoryId: kGlobalEmailObject.categoryId.toString(),
                                    subCategoryId: kGlobalEmailObject.subCategoryId.toString(),
                                    emailSubject: kGlobalEmailObject.emailSubject,
                                    emailPreHeader: kGlobalEmailObject.preheader,
                                    pushedEmail: kGlobalEmailObject.pushedEmail,
                                    blockAddedCount: $scope.addBlockCount.toString()
                                };

                                emailDraftFactory.saveEmailDraftsPost(draftData).then(function (responseText) {
                                    if (responseText !== "0") {
                                        appSessionFactory.setDashboardMessage("Draft saved successfully.").then(function (sessionSaved) {
                                            if (sessionSaved)
                                                document.location.href = "dashboard";
                                        });

                                    } else {
                                        growl("There was a problem while saving the draft! Please try again later.");
                                    }
                                });
                            } else {
                                var draftData = {
                                    draftId: kGlobalEmailObject.draftId.toString(),
                                    bodyString: $('#tinymceEditorBody').html(),
                                    lookupId: kGlobalEmailObject.lookupId.toString(),
                                    mindbodyData: kGlobalEmailObject.mindbodyId.toString(),
                                    categoryId: kGlobalEmailObject.categoryId.toString(),
                                    subCategoryId: kGlobalEmailObject.subCategoryId.toString(),
                                    emailSubject: kGlobalEmailObject.emailSubject,
                                    emailPreHeader: kGlobalEmailObject.preheader,
                                    pushedEmail: kGlobalEmailObject.pushedEmail,
                                    blockAddedCount: $scope.addBlockCount.toString()
                                };

                                emailDraftFactory.updateEmailDraftPost(draftData).then(function (responseText) {
                                    if (responseText === true) {
                                        growl("Draft updated successfully.");
                                        document.location.href = "dashboard";
                                    } else {
                                        growl("There was a problem while saving the draft! Please try again later.");
                                    }
                                });
                            }
                        });
                    });
                });
            } else {
                appSessionFactory.setDashboardMessage("Draft saved successfully.").then(function (sessionSaved) {
                    if (sessionSaved)
                        document.location.href = "dashboard";
                });
            }
        };        

        $scope.saveButtonOnClick = function () {
            $("#tinymceEditorBody").find("p").removeAttr("style").css("margin", "0px");
            settingsFactory.getAllPreferencesGet().then(function (footerResponseData) {
                var footerData = JSON.parse(footerResponseData.d.details);
                if (!footerData.companyAddress) {
                    $scope.emailFooterPopupDetails = true;
                } else {
                    var footer = $scope.getUserFooter(footerData);
                    var sendData = {
                        htmlString: $('#tinymceEditorBody').html() + footer,
                        iframeName: $scope.randomIframeFilename.toString()
                    };
                    emailFactory.previewServletPost(sendData).then(function (data) {
                        appSessionFactory.getEmail().then(function (kGlobalEmailObject) {

                            if (!kGlobalEmailObject.emailScheduleId) {
                                if (!kGlobalEmailObject.draftId) {
                                    var draftData = {
                                        bodyString: $('#tinymceEditorBody').html(),
                                        lookupId: kGlobalEmailObject.lookupId.toString(),
                                        mindbodyData: kGlobalEmailObject.mindbodyId.toString(),
                                        categoryId: kGlobalEmailObject.categoryId.toString(),
                                        subCategoryId: kGlobalEmailObject.subCategoryId.toString(),
                                        emailSubject: kGlobalEmailObject.emailSubject,
                                        emailPreHeader: kGlobalEmailObject.preheader,
                                        pushedEmail: kGlobalEmailObject.pushedEmail,
                                        blockAddedCount: $scope.addBlockCount.toString()
                                    };
                                    if (kGlobalEmailObject.pushedEmail){
                                    $scope.pushedEmail = true;
                                    }else {
                                        $scope.pushedEmail = false;
                                    }
                                    kGlobalEmailObject.htmlBody = $('#tinymceEditorBody').html();
                                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                                    });
                                    emailDraftFactory.saveEmailDraftsPost(draftData).then(function (responseText) {
                                        if (responseText != "0") {
                                            $scope.objectParameter.redirect='emaillistselection';
                                            $scope.objectParameter.draftId = responseText;
                                            $scope.redirect();
                                            //                                document.location.href = "emaillistselection?draftid=" + responseText + "&subject=" + sessionMap["emailSubject"] + "&iframeName=" + $scope.randomIframeFilename + "&categoryId=" + categoryId + "&subCategoryId=" + subCategoryId + "&emailSubject=" + email_subject + "&mindbodyId=" + mindbodydata + "&LookupId=" + LookupId;
                                        } else {
                                            growl("There was a problem while saving the draft! Please try again later.");
                                        }
                                    });

                                } else {
                                    var draftData = {
                                        draftId: kGlobalEmailObject.draftId.toString(),
                                        bodyString: $('#tinymceEditorBody').html(),
                                        lookupId: kGlobalEmailObject.lookupId.toString(),
                                        mindbodyData: kGlobalEmailObject.mindbodyId.toString(),
                                        categoryId: kGlobalEmailObject.categoryId.toString(),
                                        subCategoryId: kGlobalEmailObject.subCategoryId.toString(),
                                        emailSubject: kGlobalEmailObject.emailSubject,
                                        emailPreHeader: kGlobalEmailObject.preheader,
                                        pushedEmail: kGlobalEmailObject.pushedEmail,
                                        blockAddedCount: $scope.addBlockCount.toString()
                                    };
                                     if (kGlobalEmailObject.pushedEmail){
                                        $scope.pushedEmail = true;
                                    }else {
                                        $scope.pushedEmail = false;
                                    }
                                    kGlobalEmailObject.htmlBody = $('#tinymceEditorBody').html();
                                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                                    });
                                    emailDraftFactory.updateEmailDraftPost(draftData).then(function (responseText) {
                                        if (responseText === true) {
                                            $scope.objectParameter.redirect='emaillistselection';
                                            $scope.objectParameter.categoryId=kGlobalEmailObject.categoryId;
                                            $scope.objectParameter.draftId=kGlobalEmailObject.draftId;
                                            $scope.redirect();
                                        } else {
                                            growl("There was a problem while saving the draft! Please try again later.");
                                        }
                                    });

                            }
                        } else {
                            kGlobalEmailObject.htmlBody = $('#tinymceEditorBody').html();
                            appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {});
                            $scope.objectParameter.redirect='emaillistselection';
                            $scope.redirect();
                        }
                    });
                });
            }
            });
        };
        
        $scope.setPushedEmail = function(){
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (kGlobalEmailObject.pushedEmail){
                    $scope.pushedEmail = true;
                }else {
                    $scope.pushedEmail = false;
                }
            });

        };


        $scope.ddSelectEmailList = {
            text: "Please select an email list"
        };

        $scope.getCompaniesForFranchiseId = function () {
            
            appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                var franchiseId = kGlobalCompanyObject.franchiseId;
                appSessionFactory.getEmail().then(function(kGlobalEmailObject){
                    var emailTagId = kGlobalEmailObject.emailTagId;
                    $scope.pushedEmail = kGlobalEmailObject.pushedEmail;

                    franchiseFactory.getCompaniesForFranchiseIdAndEmailListTag(franchiseId,emailTagId).then(function (data) {
                        $scope.franchiseCompanies = data.d.details;
                        for (var i=0; i<data.d.details.length; i++){
                            var franchiseCompany = data.d.details[i];
                            if (franchiseCompany.isEmailList == false){
                                $scope.isEmailListPresentForCompany = false;
                                companiesWithNoEmailList.push(franchiseCompany.companyId);
                            }
                        }

                    });
                });
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    if (kGlobalEmailObject.emailListName) {
                        var emailObject = {"text": kGlobalEmailObject.emailListName, "value": kGlobalEmailObject.emailListName};
                        $scope.ddSelectEmailList.text = kGlobalEmailObject.emailListName;
                        $scope.chooseEmailListOnChange(emailObject);
                    }
                });
//                $scope.emailList = "1";
                $scope.getEmailSettings();
            });
        };
        $scope.getEmailListTagsForFranchise = function(){
            
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (kGlobalEmailObject.pushedEmail){
                    
                    appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                    var franchiseId = kGlobalCompanyObject.franchiseId;
                        $scope.ddSelectEmailListOptions = [];
                        emailListFactory.emailListTagsForFranchiseGet(franchiseId).then(function (data){
                            var parseData = data.d.details;
                            for (var i=0; i< parseData.length; i++){
                                var Tag = parseData[i].fkEmailListTagId;
                                var emailTag = {};
                                emailTag["text"] = Tag.tagName;
                                emailTag["value"] = Tag.tagId;
                                $scope.ddSelectEmailListOptions.push(emailTag);
                            }
                            $scope.noEmailList = false;
                        });
                    });
                }
            });
        };

        $scope.showEmailList = function () {
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (!kGlobalEmailObject.pushedEmail){
                    $scope.ddSelectEmailListOptions = [];
        //            $scope.redirectBaseURL();       //this function redirects to base if page is refreshed.            

                    appSessionFactory.getCompany().then(function (companyObject){
                        
                        emailListFactory.getAllEmailListNames(companyObject.companyId).then(function (data){
                            $scope.emailLists = data.d.details;
                            var emailAutomationData = $scope.emailLists;
                            for (var i = 0; i < emailAutomationData.length; i++)
                            {
                                var emailObject = {};
                                emailObject["text"] = emailAutomationData[i].emailListName;
                                emailObject["value"] = emailAutomationData[i].emailListId;
                                $scope.ddSelectEmailListOptions.push(emailObject);
                            }
                        });
                        $scope.showEmailDetails = false;
                        $scope.emailListDiv = true;

                    });

//            $scope.redirectBaseURL();       //this function redirects to base if page is refreshed.        

                    appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                        if (kGlobalEmailObject.emailListName) {
                            var emailObject = {"text": kGlobalEmailObject.emailListName, "value": emailList};
                            $scope.ddSelectEmailList.text = emailList;
                            $scope.chooseEmailListOnChange(emailObject);
                        }
                    });
                    $scope.emailList = "1";
                    $scope.getEmailSettings();
                }
            });
          };

        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.email_settings = parseData;
                $scope.replyAddress = parseData.reply_email_address;
                $scope.fromName = parseData.from_name;
                $scope.fromAddress = parseData.from_address;
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
            $scope.listSelectionValidation = false;
            $scope.toAddress = "";
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (kGlobalEmailObject.pushedEmail){
                    $scope.emailList = listName.text;
                    $scope.emailTag = listName.value;
                    kGlobalEmailObject.emailTagId = listName.value;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {});
                    
                    emailListFactory.getContactsOfEmailTag(listName.value).then(function (data){
                        var parseData = data.d.details;
                        var i = 0;
                        for (i = 0; i < parseData.length; i++) {
                            if (JSON.stringify(parseData[i].fkContactId.emailAddress) !== "") {
                                if (i === 0) {
                                    emails = eval(JSON.stringify(parseData[i].fkContactId.emailAddress));
                                } else {
                                    emails = emails + "," + eval(JSON.stringify(parseData[i].fkContactId.emailAddress));
                                }
                            }
                        }
                        $scope.emailAddresses = emails;
                        $scope.toAddress = emails;
                        
                    });
                }else {
                    
                    $scope.emailList = listName.text;
                    $scope.emailTag = listName.text;
                    if ($scope.emailList === "Manual") {
                        emails = "";
                        $scope.emailAddresses = emails;
                        $scope.toAddress = emails;
                    } else if ($scope.emailList !== "Manual")
                    {
                        var emails = "";
                        emailListFactory.getContactsOfEmailList(listName.value).then(function (data){
                            var parseData = data.d.details;
                            var i = 0;
                            for (i = 0; i < parseData.length; i++) {
                                if (JSON.stringify(parseData[i].fkContactId.emailAddress) !== "") {
                                    if (i === 0) {
                                        emails = eval(JSON.stringify(parseData[i].fkContactId.emailAddress));
                                    } else {
                                        emails = emails + "," + eval(JSON.stringify(parseData[i].fkContactId.emailAddress));
                                    }
                                }
                            }
                            $scope.emailAddresses = emails;
                            $scope.toAddress = emails;
                        });
                        $scope.emailContinueButton = true;
                    }
                }
            });

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
                                        growl("This file contains Invalid Email Address! \n'" + split[i] + "'\t is Invalid Email id in this file. \nPlease select a valid File.");
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
                    growl("This browser does not support HTML5!");
                }
            } else {
                growl("Please upload a valid CSV file!");
            }
        };

        $scope.validateEmails = function (emailAddresses) {
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = emailAddresses.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
//                    growl("This email list contains Invalid email " + "'" + result[i] + "'");
                    $scope.listSelectionValidation = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            return true;
        };

        $scope.emailListBackButton = true;
        $scope.continueEmailListOnClick = function (emailAddresses) {
//            TODO change to AngularJs
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                var pushedEmail = kGlobalEmailObject.pushedEmail;
                if (pushedEmail){
                    $location.path("/franchisecompanies");
                    settingsFactory.getEmailSettingsGet().then(function (data) {
                        console.log(JSON.stringify(data.d.details[0]));
                        var parseData = JSON.parse(data.d.details[0]);
                        var emailSettingsData = parseData.emailSettings;
                        if (emailSettingsData){
                            $scope.email_settings = emailSettingsData;
                            $scope.postData.replyAddress = emailSettingsData.reply_email_address;
                            $scope.postData.fromName = emailSettingsData.from_name;
                            $scope.postData.fromAddress = emailSettingsData.from_address;
                            $scope.postData.emailSubject = kGlobalEmailObject.emailSubject;
                            $scope.postData.toAddress = "intbit@intbittech.com";
                        }else {
                            $scope.noEmailSettings = true;
                            $scope.isPostSuccess = true;
                        }
                        
                    });
                }else {
                    if ($scope.validateEmails(emailAddresses)) {
                        if ($scope.emailList !== "Manual")
                        {
                            if ($scope.emailAddresses !== "")
                            {
                                $location.path("/emaildetails");
                                $scope.showEmailDetails = true;
                                $scope.emailListDiv = false;
                                $scope.emailContinueButton = false;
                                $scope.emaildetailscontbtn = true;
                                $scope.emailListBackButton = false;
                                $scope.emailDetailsBackButton = true;
                            } else {
                                growl("Please select atleast one email list or add email manually.");
                                $scope.selectCsvOnClick();
                                $("#emailaddresses").focus();
                                return false;
                            }

                        } else {
                            if ($scope.emailAddresses !== "")
                            {
                                $location.path("/emaildetails");
                                $scope.toAddress = emailAddresses;
                                $scope.showEmailDetails = true;
                                $scope.emailListDiv = false;
                                $scope.emailContinueButton = false;
                                $scope.emaildetailscontbtn = true;
                                $scope.emailListBackButton = false;
                                $scope.emailDetailsBackButton = true;
                            } else {
                                growl("Please select at least one email list or add email manually.");
                                selectCsvFile();
                                $("#emailaddresses").focus();
                                return false;
                            }
                        }
                    } else {

                    }
                    
                }
            });
        };

        $scope.backToEmailList = function () {
            $location.path('/emaillistselection');
            $scope.showEmailDetails = false;
            $scope.emailListDiv = true;
            $scope.emailContinueButton = false;
            $scope.emaildetailscontbtn = false;
            $scope.emailListBackButton = true;
            $scope.emailDetailsBackButton = false;
        };


        $scope.isEmailActionSave = function () {
//            $scope.redirectBaseURL();
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                $scope.emailSubject = kGlobalEmailObject.emailSubject;
                if (kGlobalEmailObject.fromName)
                    $scope.fromName = kGlobalEmailObject.fromName;
                if (kGlobalEmailObject.entityScheduleId) {
                    $scope.emaildetailscontbtn = false;
                    $scope.emailSaveActionbutton = true;
                } else {
                    $scope.emailSaveActionbutton = false;
                    $scope.emaildetailscontbtn = true;
                }
            });
        };
        $scope.emailListPreviewOnClick = function () {
            $scope.iframePath = getHost() + "download/HTML?fileName=" + $scope.randomIframeFilename + ".html";
            $scope.fadeClass = 'fadeClass';
            $("#fade").show();
            $scope.emailPreviewPopup = true;
        };

//        $scope.continueEmailDetailsOnClick = function () {growl($scope.draftId);
//            $scope.emailSendPopup = true;
//            $("#fade").show();
//        };

        $scope.sendEmailOnClick = function (fromName, fromAddress, replyAddress, toAddress) {

            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                var sendEmailData = {
                    from_name: fromName,
                    email_subject: kGlobalEmailObject.emailSubject,
                    email_preheader: kGlobalEmailObject.preheader,
                    email_addresses: kGlobalEmailObject.toEmailAddresses,
                    from_email_address: fromAddress,//getDefaultEmailId(),
                    reply_to_email_address: replyAddress,
                    email_list: $scope.emailList,
                    iframeName: $scope.randomIframeFilename.toString()
                };
                emailFactory.sendEmail(sendEmailData).then(function (data) {
                    if (data.d.message === "true") {
                        emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function () {
                            if (responseText === "true")
                            {
                                window.location = "dashboard";
                            }
                        });
                    }
                });
            });
        };

        $scope.emailListValidation = function (postData) {
            if (postData) {
                if (!postData.emailSubject) {
                    $scope.emailSubject = "";
                    $("#subject").focus();
                    return false;
                }
                if (!postData.fromName) {
                    $scope.fromName = "";
                    $("#name").focus();
                    return false;
                }
                if (!postData.fromAddress) {
                    $scope.fromAddress = "";
                    $("#fromAddress").focus();
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
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
           
            if(postData.fromAddress){
                var replyAddress = postData.replyAddress;
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
            }
        };
        $scope.fromAddressEmailValidation  = function (postData){
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                if (!regex.test(postData.fromAddress)) {
                    $scope.validateFromEmailAddress = true;
                    $("#fromAddress").focus();
                    return false;
                }  
                $scope.validateFromEmailAddress = false;
                return true;
        };
        
        $scope.continueEmailDetailsOnClick = function (postData) {
            if ($scope.emailListValidation(postData))
            {
                if ($scope.fromAddressEmailValidation(postData))
                {
                    if ($scope.replyEmailValidation(postData))
                    {
                        $scope.schedulePopup = false;
                        $scope.postTypeSelectionPopUp = true;
                        $scope.postData = postData;
                        $scope.postTo = "Send Now";
                    }
                }
            }
        };

        $scope.saveEmailActionByScheduleId = function (postData) {

            if ($scope.emailListValidation(postData))
            {
                if ($scope.replyEmailValidation(postData))
                {
                    $scope.postData = postData;

                    appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                        if (kGlobalEmailObject.emailScheduleId)
                        {
                            var requestBody = {"type": "removetemplate",
                                "schedule_ids": kGlobalEmailObject.entityScheduleId, "entity_type": "Email",
                                "isRecurring": ""
                            };
                            yourPlanFactory.changeSchedulePost(requestBody).then(function (data) {

                                var email_scheduling = {
                                    from_name: $scope.postData.fromName,
                                    schedule_id: kGlobalEmailObject.entityScheduleId.toString(),
                                    schedule_title:kGlobalEmailObject.entityScheduleTitle,
                                    email_subject: $scope.postData.emailSubject,
                                    email_preheader: kGlobalEmailObject.preheader,
                                    to_email_addresses: $scope.postData.toAddress,
                                    from_email_address: $scope.postData.fromAddress,//getDefaultEmailId(),
                                    reply_to_email_address: $scope.postData.replyAddress,
                                    email_list: $scope.emailList,
                                    email_body: "",
                                    schedule_desc: ",,,",
                                    iframeName: $scope.randomIframeFilename.toString(),
                                    html_body: kGlobalEmailObject.htmlBody
                                };

                                scheduleActionsFactory.scheduleEmailActionsPost(email_scheduling).then(function (data) {
                                    if (data.d.operationStatus.statusCode === "Success") {
                                        $scope.schedulePopup = false;
                                        $scope.isPostSuccess = true;
                                        $scope.postedTo = getemail();
                                        //                        window.location = "dashboard";

                                        if (kGlobalEmailObject.draftId) {
                                            emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function (responseText) {

                                                appSessionFactory.clearEmail().then(function () {
                                                });
                                            });
                                        }


                                    }
                                });

                            });
                        } else {

                            var email_scheduling = {
                                from_name: $scope.postData.fromName,
                                schedule_id: kGlobalEmailObject.entityScheduleId.toString(),
                                schedule_title:kGlobalEmailObject.entityScheduleTitle,
                                email_subject: $scope.postData.emailSubject,
                                email_preheader: kGlobalEmailObject.preheader,
                                to_email_addresses: $scope.postData.toAddress,
                                from_email_address: $scope.postData.fromAddress,//getDefaultEmailId(),
                                reply_to_email_address: $scope.postData.replyAddress,
                                email_list: $scope.emailList,
                                email_body: $("#dynamictable").contents().find("html").html(),
                                schedule_desc: ",,,",
                                iframeName: $scope.randomIframeFilename.toString(),
                                html_body: kGlobalEmailObject.htmlBody
                            };

                            scheduleActionsFactory.scheduleEmailActionsPost(email_scheduling).then(function (data) {
                                if (data.d.operationStatus.statusCode === "Success") {
                                    $scope.schedulePopup = false;
                                    $scope.isPostSuccess = true;
                                    $scope.postedTo = getemail();
                                    //                        window.location = "dashboard";


                                    if (kGlobalEmailObject.draftId) {
                                        emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function (responseText) {

                                            appSessionFactory.clearEmail().then(function () {
                                            });
                                        });
                                    }


                                }
                            });

                        }
                    });
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
            $("#fade").hide();
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
                $scope.selectedMarketingProgram = $scope.marketing_programs[0].program_id;
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

        $scope.getActions = function (selectedSocialmedia, selectedMarketingProgramId) {
            $scope.selectedMarketingProgram = selectedMarketingProgramId.value;
            if (selectedSocialmedia === "email") {
                $scope.getFacebookActions(selectedMarketingProgramId.value);
            }
        };

        $scope.getFacebookActions = function (selectedMarketingProgramId) {
            var data = {programid: selectedMarketingProgramId.toString(), type: getemail()};
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
                        actionObject["text"] = actionData[i].schedule_title + " - " + $filter('date')(new Date(actionData[i].action_date), 'MMM-dd-yyyy');
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
                var schedule_time = $("#actionTime").val();
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
        $scope.getAllUsersInCompany = function () {
            yourPlanFactory.allUsersInCompanyGet().then(function (data) {
//                $scope.allUsers = data.d.details;
                $scope.ddSelectUserOptions = [{text: 'Select',value: '0'}];
                for (var i = 0; i < data.d.details.length; i++) {
                    $scope.ddSelectUserOptions.push({"text": data.d.details[i].firstName +" "+data.d.details[i].lastName, "value": data.d.details[i].userId});
                }
            });
            yourPlanFactory.noOfUsersInCompanyGet().then(function (data) {
                var noOfUsersInCompany = data.d.details;
                if (parseInt(noOfUsersInCompany) > 1) {
                    $scope.moreThanOneUser = true;
                }
            });
        };
        
         $scope.ChangeUserOnChange = function (changedValue){
            $scope.ddSelectedUser=changedValue.value;
        };

        $scope.schedulePostToEmail = function (postData) {
            $scope.postedTo = getemail();
            $scope.getScheduleData($scope.selectedMarketingProgram, postData);
        };
        $scope.getScheduleData = function (selectedMarketingProgramId, postData) {
            var email_scheduling = "";
            var schedule_title = $("#ActionName").val();
            if(!schedule_title){
                schedule_title='';
            }

            if (!$scope.createNewActionPopup) {
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    email_scheduling = {
                        from_name: postData.fromName,
                        program_id: $scope.selectedMarketingProgram.toString(),
                        schedule_id: $scope.socialAction.toString(),
                        email_subject: postData.emailSubject,
                        email_preheader: kGlobalEmailObject.preheader,
                        to_email_addresses: postData.toAddress,
                        email_addresses: postData.toAddress,
                        from_email_address: postData.fromAddress,//getDefaultEmailId(),
                        reply_to_email_address: postData.replyAddress,
                        email_list: $scope.emailList,
                        schedule_title: schedule_title,
                        email_body: $("#dynamictable").contents().find("html").html(),
                        schedule_desc: ",,,",
                        iframeName: $scope.randomIframeFilename.toString(),
                        html_body: kGlobalEmailObject.htmlBody
                    };
                    scheduleActionsFactory.scheduleEmailActionsPost(email_scheduling).then(function (data) {
                        if (data.d.operationStatus.statusCode === "Success") {

                            emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function (responseText) {
                                if(kGlobalEmailObject.pushedEmail){
                                     var approved = $("#autoApproved:checked").val();
                                     var autoApproved = false;
                                     var editable = false;
                                     if (approved){
                                         editable = false;
                                         autoApproved = true;
                                     }
                                     var emailTagId = kGlobalEmailObject.emailTagId;
                                     appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                                         var franchiseId = kGlobalCompanyObject.franchiseId;

                                         var pushedScheduledEntityDetails = {"autoApproved": autoApproved,
                                                                             "editable":editable, "franchiseId":franchiseId,
                                                                             "scheduledEntityListId":$scope.socialAction, 
                                                                             "emailListTagId": emailTagId};
                                         var actionCompaniesDetails = companies;
                                         var pushedScheduledActionCompaniesDetails = {"pushedScheduledEntityDetails": pushedScheduledEntityDetails,"actionCompaniesDetails":actionCompaniesDetails};
                                         pushedActionsFactory.saveSchedulePushedActionsCompanies(pushedScheduledActionCompaniesDetails).then(function (data){

                                         });                       
                                     });                        

                                }
                            $scope.schedulePopup = false;
                            $scope.isPostSuccess = true;

                            });

                        }
                    });

                });
            } else {

               if (!$scope.ddSelectedUser)
                    $scope.ddSelectedUser = "0";
                
                var schedule_title = $("#ActionName").val();
                var schedule_date = $("#actionDate").val();

                var schedule_time = $("#actionTime").val();
                
                var dateAndTime = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();    
                var fromDate = new Date(dateAndTime);
                var todayDate = new Date();
                if (fromDate < todayDate) {
                    $scope.dateLesser = true;
                    return false;
                }
                $scope.dateLesser = false;
                utilFactory.getEpoch(schedule_date, schedule_time).then(function (dateTimeEpoch) {
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    email_scheduling = {
                        "from_name": postData.fromName,
                        "email_subject": postData.emailSubject,
                        "email_preheader": kGlobalEmailObject.preheader,
                        "to_email_addresses": postData.toAddress,
                        "email_addresses": postData.toAddress,
                        "from_email_address": postData.fromAddress,//getDefaultEmailId(),
                        "reply_to_email_address": postData.replyAddress,
                        "email_list": $scope.emailList,
                        "program_id": $scope.selectedMarketingProgram.toString(),
                        "schedule_title": schedule_title,
                        "schedule_time": dateTimeEpoch,
                        "email_body": $("#dynamictable").contents().find("html").html(),
                        "schedule_desc": ",,,",
                        "iframeName": $scope.randomIframeFilename.toString(),
                        "html_body": kGlobalEmailObject.htmlBody,
                        "userAssignedTo": $scope.ddSelectedUser
                    };
                    scheduleActionsFactory.scheduleEmailPost(email_scheduling).then(function (data) {
                        
                        if (data.d.operationStatus.statusCode === "Success") {

                            emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function (responseText) {
                                if(kGlobalEmailObject.pushedEmail){
                                     var entity = data.d.details;
                                     var parsedEntity = JSON.parse(entity);
                                     var approved = $("#autoApproved:checked").val();
                                     var autoApproved = false;
                                     var editable = false;
                                     if (approved){
                                         editable = false;
                                         autoApproved = true;
                                     }
                                     var emailTagId = kGlobalEmailObject.emailTagId;
                                     appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                                         var franchiseId = kGlobalCompanyObject.franchiseId;
                                         var pushedScheduledEntityDetails = {"autoApproved": autoApproved,
                                                                             "editable":editable, "franchiseId":franchiseId,
                                                                             "scheduledEntityListId":parsedEntity.schedule_entity_id,
                                                                             "emailListTagId": emailTagId};
                                         var actionCompaniesDetails = companies;

                                         var pushedScheduledActionCompaniesDetails = {"pushedScheduledEntityDetails": pushedScheduledEntityDetails,
                                                                                      "actionCompaniesDetails":actionCompaniesDetails};
                                         pushedActionsFactory.saveSchedulePushedActionsCompanies(pushedScheduledActionCompaniesDetails).then(function (data){

                                         });                       
                                     });                        

                                }
                                $scope.schedulePopup = false;
                                $scope.isPostSuccess = true;
                            });
                        }
                    });


                });
            });
            }
            return email_scheduling;
        };

        $scope.previewCloseButton = function () {
            $scope.emailPreviewPopup = false;
            $scope.fadeClass = 'unfadeClass';
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
                        format: kGlobalDateFormat,
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
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
            if(kGlobalEmailObject.pushedEmail){
                if (companies.length == 0){
                    growl("no company selected, please select any one company");
                    $("#selectAll").focus();
                }else {
                    $scope.postTypeSelectionPopUp = false;
                    $scope.schedulePopup = true;
                    $scope.existingActionPopup = true;
                    $scope.createNewActionPopup = false;
                    $scope.activeClassExisting = 'active';
                    $scope.activeClassNew = '';
                    $scope.scheduleButtonData = "Schedule";
                }
    
            }else {
                    $scope.postTypeSelectionPopUp = false;
                    $scope.schedulePopup = true;
                    $scope.existingActionPopup = true;
                    $scope.createNewActionPopup = false;
                    $scope.activeClassExisting = 'active';
                    $scope.activeClassNew = '';
                    $scope.scheduleButtonData = "Schedule";
            }
            });

        };

        $scope.hidePopup = function (popupName) {
            if (popupName === "sendOrSchedulePopup") {
                $scope.postTypeSelectionPopUp = false;
            } else if (popupName === "schedulePopup") {
                $scope.schedulePopup = false;
            }else if (popupName === "addlinkpopup") {
                $scope.addLinkPopup = false;
            }
        };

        $scope.previousButton = function (popupName) {
            $scope.schedulePopup = false;
            $scope.postTypeSelectionPopUp = true;
        };


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
                    actionObject["text"] = actionData[i].schedule_title + " - " + $filter('date')(new Date(actionData[i].action_date), 'MMM-dd-yyyy');
                    actionObject["value"] = actionData[i].id;
                    $scope.ddSelectActionName.push(actionObject);
                }
            });
        };

        $scope.postToSocialMedia = function (selectedSocialmedia, postData) {
            $scope.isMailSent = false;
            if (selectedSocialmedia === "email") {
                var sendEmailData = {
                    from_name: postData.fromName,
                    email_subject: postData.emailSubject,
                    email_addresses: postData.toAddress,
                    from_email_address:  postData.fromAddress,//getDefaultEmailId(),
                    reply_to_email_address: postData.replyAddress,
                    email_list: $scope.emailList.toString(),
                    iframeName: $scope.randomIframeFilename.toString()
                };
                emailFactory.sendEmail(sendEmailData).then(function (data) {
                    if (data.d.message === "true") {
                        appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                            emailDraftFactory.deleteEmailDraftPost(kGlobalEmailObject.draftId).then(function (responseText) {
                                if (responseText === true)
                                {
                                    $scope.isMailSent = true;
//                                window.location = "dashboard";
                                }
                            });
                        });
                    }
                });
            }
        };
        
//        
//        $('html').click(function (e) {
//            $('[ng-controller="emailController"]').scope();
//            if (e.target.id == 'sendOrSchedulePopup') {
//                $('#sendOrSchedulePopupClose').click();      
//            }
//            if (e.target.id == 'schedulePopup') {
//                $('#schedulePopupClose').click();   
//            }
//        });
        
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
emailFlowApp.directive('customOnChange', function () {
                return {
                    restrict: 'A',
                    link: function (scope, element, attrs) {
                        var onChangeFunc = scope.$eval(attrs.customOnChange);
                        element.bind('change', onChangeFunc);
                    }
                };
            });
 // This example displays an address form, using the autocomplete feature
      // of the Google Places API to help users fill in the information.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
      var placeSearch, autocomplete;
      var componentForm = {
        street_number: 'long_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'long_name',
        country: 'long_name',
        postal_code: 'long_name'
      };

      function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
            /** @type {!HTMLInputElement} */(document.getElementById('companyAddress')),
            {types: ['geocode']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
      }

      function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();
        for (var component in componentForm) {
            document.getElementById(component).value = '';
            document.getElementById(component).disabled = false;
        }
        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
          }
        }
        var inputvalues = $('#country,#postal_code,#administrative_area_level_1,#locality,#route,#street_number');
        inputvalues.trigger('input');
      }

      // Bias the autocomplete object to the user's geographical location,
      // as supplied by the browser's 'navigator.geolocation' object.
      function geolocate() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
              center: geolocation,
              radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
          });
        }
      }