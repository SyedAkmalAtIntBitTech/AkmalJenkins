/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

socialFlowApp.controller("socialController", ['$scope', '$filter', '$rootScope', '$location', '$window', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'companyMarketingProgramFactory', 'companyImagesFactory', 'companyFactory', 'imageFactory', 'socialPostFactory', 'scheduleActionsFactory', 'appSessionFactory', 'yourPlanFactory', function ($scope, $filter, $rootScope, $location, $window, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, companyMarketingProgramFactory, companyImagesFactory, companyFactory, imageFactory, socialPostFactory, scheduleActionsFactory, appSessionFactory, yourPlanFactory) {

        $scope.getTwitterActionsData = "";
        $scope.marketingProgramsList = "";
        $scope.twitter_action = "";
        $scope.show_Post_SchedulePopup = false;
        $scope.showSchedulePopup = false;
        $scope.showTwitterPopup = false;
        $scope.showImageGalleryPopup = false;
        $scope.showUserImages = true;
        $scope.showUploadImage = false;
        $scope.selectImageName = "";
        $scope.selectImageType = "";
        $scope.selectCompanyId = "";
        $scope.imageUnSelect = 'gallery-item-wrap-selected';
        $scope.imageSelected = 'gallery-item-wrap-selected-true';
        $scope.displayImage = 'https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg';
        $scope.addImageToPostButton = true;
        $scope.twitterImageDivToPost = false;
        $scope.imageToBeUploaded = '../images/uploadPhoto.svg';
        $scope.postType = 'Change To Link Post';
        $scope.TwtPostType = "Change To Link Post";
        $scope.existingAction = false;
        $scope.managepage = "";
        $scope.actionNameValidation = actionNameValidation;
        $scope.scheduleDateValidation = scheduleDateValidation;
        $scope.lesserDateValidation = lesserDateValidation;
        $scope.scheduleTimeValidation = scheduleTimeValidation;
        $scope.facebookPostValidation = facebookPostValidation;
        $scope.facebookLinkValidation = facebookLinkValidation;
        $scope.facebookDescriptionValidation = facebookDescriptionValidation;
        $scope.linkValidation = linkValidation;
        $scope.customLinkValidation = customLinkValidation;
        $scope.facebookImageValidation = facebookImageValidation;
        $scope.actionNameListValidation = actionNameListValidation;
        $scope.fbPostData = {};
        $scope.twitterPostData = {};
        $scope.fbLinkValidation = false;
        $scope.twitterLinkValidation = false;
//        $scope.imageValidation = false;
        $scope.twitterCustomLink = false;
        $scope.actionTimeVal = false;
        $scope.actionDropdown = false;
        $scope.dateLesser = false;
        $scope.imageValidation = false;
        $scope.twitterImageValidation = false;
        $scope.hideBaseSocialSequence = true;
        var schedule_desc = "";
        $rootScope.CurrentFbAccessToken = "";
        $scope.linkpost = false;
        $scope.isCurrentCompanyInFranchise = false;
        $scope.isCurrentCompanyAFranchiseHeadquarter = false;
        $scope.pushedEmail = false;
         $scope.ddSelectedUser= '0';
        $scope.ddSelectUserOptions = [{
                text: 'Select',
                value: '0'
            }
        ];
        $scope.ddSelectUser = {text: "Select"};
        $scope.chooseUserOnChange = function (actionValue) {
            $scope.ddSelectedUser = actionValue.value;
        };

        $scope.getCompanyStatus = function () {
            appSessionFactory.isCurrentCompanyInFranchise().then(function (isCurrent) {
                $scope.isCurrentCompanyInFranchise = isCurrent;
            });
            appSessionFactory.isCurrentCompanyAFranchiseHeadquarter().then(function (isHead) {
                $scope.isCurrentCompanyAFranchiseHeadquarter = isHead;
            });
        };

        $scope.pushedEmail = false;

        $scope.getManagePage = function (selectedSocialmedia, postData) {
            appSessionFactory.getFbPostData().then(function (kGlobalFbPostDataObject) {
                if (postData.shareText) {
                    kGlobalFbPostDataObject.postText = postData.shareText;
                }
                if ($scope.linkpost) {
                    if (postData.linkTitle) {
                        kGlobalFbPostDataObject.title = postData.linkTitle;
                    }
                    if (postData.linkDescription) {
                        kGlobalFbPostDataObject.url = postData.url;
                    }
                    if (postData.url) {
                        kGlobalFbPostDataObject.description = postData.linkDescription;
                    }
                } else if (!$scope.linkpost) {
                    kGlobalFbPostDataObject.title = "";
                    kGlobalFbPostDataObject.url = "";
                    kGlobalFbPostDataObject.description = "";
                }
                appSessionFactory.setFbPostData(kGlobalFbPostDataObject).then(function (data) {
                    var fbData = {access_token_method: "getAccessToken"};
                    settingsFactory.facebookPost(fbData).then(function (fbResponseData) {
                        var fbAccessToken = fbResponseData.d.message.split(",");
                        if (fbAccessToken[0] === 'null')
                        {
                            var data = JSON.stringify({redirectUrl: "user/socialsequence%23/facebookpost"});
                            settingsFactory.fbLoginPost(data).then(function (data) {
                                $window.location = data.d.details[0];
                            });
                        } else {
                            $rootScope.FbProfileName = fbAccessToken[1];
                            $rootScope.CurrentFbPageName = fbAccessToken[2];

                            $scope.openPostOrShedulePopup(selectedSocialmedia, postData);
                        }
                    });
                });
            });
        };

        $scope.selectManagePage = function (id) {
            $('.cat-slat').removeClass('highlightDiv').addClass('cat-slat-unselect');
            $("." + id).removeClass('cat-slat-unselect').addClass('highlightDiv');
        };
        $scope.isDefaultTwitterAccountSet = function (selectedSocialmedia, postData) {
            var data = {access_token_method: "getAccessToken"};
            settingsFactory.twitterPost(data).then(function (data) {
                var twitterAccessToken = data.d.message;
                if ((twitterAccessToken === null) || (twitterAccessToken === ""))
                {
                    settingsFactory.twitterLoginGet().then(function (responseText) {
                        $scope.showTwitterPopup = true;
                        $scope.getTwitterPin = responseText.d.details[0];
                    });
                } else {
                    $scope.showTwitterPopup = false;
                    $scope.openPostOrShedulePopup(selectedSocialmedia, postData);
                }
            });
        };

        $scope.getUserImages = function () {
            companyImagesFactory.companyImagesGet().then(function (data) {
                $scope.datalists = data.d.details;
            });
            companyFactory.currentCompanyGet().then(function (data) {
                $scope.companyId = data.d.details[0];
            });
        };
        $scope.getAllUsersInCompany = function () {
            yourPlanFactory.allUsersInCompanyGet().then(function (data) {
//                $scope.allUsers = data.d.details;
                $scope.ddSelectUserOptions = [{text: 'Select', value: '0'}];
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
        $scope.selectImage = function (id) {
            $scope.imageUnSelect = 'gallery-item-wrap-selected-true';
            angular.element(document.getElementsByClassName('gallery-item-wrap-selected-true')).removeClass('gallery-item-wrap-selected-true').addClass('gallery-item-wrap-selected');
            angular.element(document.getElementById(id)).addClass('gallery-item-wrap-selected-true');
        };

        $scope.selectImageToPost = function (imageName, imageType, companyId) {
            $scope.selectImageName = imageName;
            $scope.selectImageType = imageType;
            $scope.selectImageType.toLowerCase();
            $scope.selectCompanyId = companyId;
            appSessionFactory.getFbPostData().then(function (kGlobalFbPostDataObject) {
                kGlobalFbPostDataObject.imgNameToPost = imageName;
                kGlobalFbPostDataObject.imageType = $scope.selectImageType;
                appSessionFactory.setFbPostData(kGlobalFbPostDataObject).then(function (data) {
                });
            });
            $scope.addImage();
        };

        $scope.ddSelectlinkUrls = {
            text: "Please select an Url"
        };

        $scope.fbTwitterinit = function () {
            $scope.show_hide_ImageGalleryPopup(false);
        };

        $scope.getUrls = function () {
            $scope.ddSelectlinkUrlsOptions = [
            ];
            companyMarketingProgramFactory.getAllUserMarketingProgramsUserIdGet().then(function (data) {
                $scope.urls = data;
                $scope.show_BlackLayer = false;
                $scope.show_Post_SchedulePopup = false;
                //angulardd
                var linkUrlData = data;
                for (var i = 0; i < linkUrlData.length; i++)
                {
                    if (linkUrlData[i].link_name || linkUrlData[i].url) {
                        var linkUrlObject = {};
                        linkUrlObject["text"] = linkUrlData[i].link_name; //+ " - " + linkUrlData[i].url;
                        linkUrlObject["value"] = linkUrlData[i].link_name;
                        linkUrlObject["url"] = linkUrlData[i].url;
                        $scope.ddSelectlinkUrlsOptions.push(linkUrlObject);
                    }
                }
            });
        };

        $scope.getSelectedUrl = function (urlsLink) {
            $scope.facebookAction = urlsLink.value;
            $scope.fbLinkValidation = false;
            $scope.twitterLinkValidation = false;
            $scope.twitterPostData.url = urlsLink.url;
            $scope.fbPostData.url = urlsLink.url;
        };

        $scope.postToFacebook = function (fbPostData) {

            $scope.twitterActions = false;
            $scope.facebookActions = true;
            var data = {
                imageToPost: $scope.selectImageName,
                postText: fbPostData.shareText,
                title: fbPostData.linkTitle,
                url: fbPostData.url,
                description: fbPostData.linkDescription,
                imageType: $scope.selectImageType
            };
            socialPostFactory.facebookPost(data).then(function (data) {
                growl(JSON.stringify(data));
            });

        };

        $scope.postToTwitter = function (twitterShare) {
            var username = "sandeep264328"; // bit.ly username
            var key = "R_63e2f83120b743bc9d9534b841d41be6";
            var BitlyUserDetails = {longUrl: twitterShare.url,
                apiKey: key,
                login: username
            };
            socialPostFactory.shortenUrl(BitlyUserDetails).then(function (data) {
//                $scope.bit_url = urlData.data.url;
//                socialPostFactory.postToTwitterURL().then(function (data){
//                    growl(data);
//                });
            });

        };
        $scope.show_hide_SocialListSelectionPopup = function (flag) {
            if (flag === true) {
                $scope.show_BlackLayer = true;
                $scope.show_Post_SchedulePopup = true;
                $scope.showSendPopup = true;
                $scope.showSchedulePopup = false;
            } else {
                $scope.show_Post_SchedulePopup = false;
                $scope.showSchedulePopup = false;
            }

        };

        $scope.checkForCode = function () {
            var code = $scope.getUrlParameter("code");
            if (typeof code !== "undefined") {
                settingsFactory.fbGetTokenGet(code).then(function (data) {
                    $scope.setDefaultManagePage = true;
                    $scope.managepagesettings = true;
                    $scope.hideBaseSocialSequence = false;
                    $scope.managepage = true;
                    $scope.fbPagesDetails = data.d.details[0].fbPages;
                    $scope.fbProfileName = data.d.details[0].user_profile_name;
                });
            }
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
        $scope.getUrlParameter = function (sParam) {
            var sPageURL = decodeURIComponent($window.location.search.substring(1)),
                    sURLVariables = sPageURL.split('&'),
                    sParameterName, i;
            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
        };
        $scope.setPageAccessToken = function (accessToken, pageName, profileName) {
            $rootScope.CurrentFbAccessToken = accessToken;
            $rootScope.CurrentFbPageName = pageName;
            $rootScope.FbProfileName = profileName;
            $scope.postData = {};
            $scope.selectedSocialmedia = "facebook";
//            $scope.postTypeSelectionPopUp = true;
            $scope.postTo = "Post to Facebook";
        };
        $scope.postToSelectedPage = function () {
            $scope.managepage = false;
            var addDafaultmanagePage = true;//$("#setDefaultManagePage").prop('checked');
            if (addDafaultmanagePage) {
                var pageDetails = {
                    access_token_method: "setAccessToken",
                    access_token: $scope.CurrentFbAccessToken,
                    default_page_name: $scope.CurrentFbPageName,
                    fb_user_profile_name: $scope.FbProfileName
                };
                settingsFactory.facebookPost(pageDetails).then(function (data) {
                });
            }
            $location.path("/facebookpost");
        };
        $scope.changeTwitterPostType = function () {
            if ($scope.showTwitterLink === true) {
                $scope.TwtPostType = "Change To Link Post";
                $scope.showTwitterLink = false;
            } else {
                $scope.TwtPostType = "Change To Normal Post";
                $scope.showTwitterLink = true;
            }
        };

        $scope.show_hide_ImageGalleryPopup = function (flag) {
            if (flag === true) {
                $scope.selectTabGallery = 'popUp_subheader-tabs-active';
                $scope.selectTabUpload = 'popUp_subheader-tabs';
                $scope.showImageGalleryPopup = true;
            } else {
                $scope.selectImageName = "";
                $scope.selectImageType = "";
                $scope.showUserImages = true;
                $scope.showUploadImage = false;
                $scope.selectTabGallery = 'popUp_subheader-tabs';
                $scope.selectTabUpload = 'popUp_subheader-tabs-active';
                $scope.displayImage = 'https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg';
                $scope.addImageToPostButton = true;
                $scope.twitterImageDivToPost = false;
                $scope.showImageGalleryPopup = false;
            }
        };

        $scope.show_Hide_Upload_GalleryImages = function (flag) {
            if (flag === true) {
                $scope.selectTabGallery = 'popUp_subheader-tabs-active';
                $scope.selectTabUpload = 'popUp_subheader-tabs';
                $scope.showUserImages = true;
                $scope.showUploadImage = false;

            } else {
                $scope.selectTabGallery = 'popUp_subheader-tabs';
                $scope.selectTabUpload = 'popUp_subheader-tabs-active';
                $scope.showUserImages = false;
                $scope.showUploadImage = true;
            }
        };

        $scope.addImage = function () {
            $scope.displayImage = "/BrndBot/downloadImage?imageType=GALLERY&imageName=" + $scope.selectImageName + "&companyId=" + $scope.selectCompanyId + "";
            $scope.showImageGalleryPopup = false;
            $scope.addImageToPostButton = false;
            $scope.twitterImageDivToPost = true;

        };

        $scope.uploadLogo1 = function (myFile1) {
            var file = myFile1;
            imageFactory.saveImagePost(file).then(function (data) {
                $scope.selectTabGallery = 'popUp_subheader-tabs-active';
                $scope.selectTabUpload = 'popUp_subheader-tabs';
                $scope.showUserImages = true;
                $scope.showUploadImage = false;
                $scope.getUserImages();
            });
        };

        $scope.getSocialTwitterActions = function () {
            var getTwitterActionsData = {programid: $scope.marketingProgramsList, type: gettwitter()};
            scheduleActionsFactory.getActionsPost(getTwitterActionsData).then(function (twitterData) {
                $scope.twitter_actions = JSON.parse(twitterData.d.details);
            });
        };


//        $scope.validateProgramActions = function (marketingProgram) {
//            if (marketingProgram === null) {
//                $scope.existingAction = false;
//                $scope.getTwitterActionsData = {programid: "0", type: gettwitter()};
//                $scope.setTwitterActions($scope.getTwitterActionsData);
//            } else {
//                $scope.existingAction = true;
//                $scope.getTwitterActionsData = {programid: marketingProgram.toString(), type: gettwitter()};
//                $scope.setTwitterActions($scope.getTwitterActionsData);
//            }
//        };

        $scope.validateActions = function (twtAction) {
            if (twtAction === null) {
                $scope.existingAction = false;
            } else {
                $scope.new_schedule_title = "";
                $scope.existingAction = true;
            }
        };
        $scope.scheduleTwitter = function (action) {
        };

        $scope.hideFbPopup = function () {
//                $("#fbmanagePagePopUp").show();
            $scope.managepage = false;
        };
        $scope.changeFbPostType = function (type) {
//            if ($scope.fbPostValidation1(postData))
//            {
            if (type === "Change To Link Post") {
                $scope.linkpost = true;
                $scope.postType = 'Change To Normal Post';
            } else if (type === 'Change To Normal Post') {
                $scope.linkpost = false;
                $scope.postType = 'Change To Link Post';
                $scope.fbPostData.linkTitle = null;
                $scope.fbPostData.linkDescription = null;
                $scope.fbPostData.url = null;
            }
//            }
        };


//        ...................post or schedule new functions..................

        $scope.fbPostValidation = function (postData) {
            if (!postData.shareText) {
                $scope.fbPostData = {shareText: "", linkTitle: postData.linkTitle, linkDescription: postData.linkDescription, url: postData.url};
                $("#shareText").focus();
                return false;
            }
            if ($scope.linkpost) {
                if (!postData.linkTitle) {
                    $scope.imageValidation = false;
                    $scope.fbPostData = {shareText: postData.shareText, linkTitle: "", linkDescription: postData.linkDescription, url: postData.url};
                    $("#linkTitle").focus();
                    return false;
                }
                if (!postData.linkDescription) {
                    $scope.imageValidation = false;
                    $scope.fbPostData = {shareText: postData.shareText, linkTitle: postData.linkTitle, linkDescription: "", url: postData.url};
                    $("#linkDescription").focus();
                    return false;
                }
//                if (!$scope.facebookAction) {
//                    $scope.imageValidation = false;
//                    $scope.fbLinkValidation = true;
//                    return false;
//                }
                if (!postData.url) {
                    $scope.imageValidation = false;
                    $scope.fbPostData = {shareText: postData.shareText, linkTitle: postData.linkTitle, linkDescription: postData.linkDescription, url: ""};
                    $("#facebooklink").focus();
                    return false;
                }
            }
            if (!$scope.twitterImageDivToPost) {
//                $scope.fbPostData = {shareText: postData.shareText, img: fbPostData.img, linkTitle: postData.linkTitle, linkDescription: postData.linkDescription, url: postData.url};
                $scope.imageValidation = true;
                return false;
            }
            $scope.imageValidation = false;
            return true;
        };

        $scope.twitterPostValidation = function (postData) {
            if (!postData.text) {
                $scope.twitterPostData = {text: "", url: postData.url};
                $("#twitterShareText").focus();
                return false;
            }
            if ($scope.showTwitterLink) {
//                if (!$scope.facebookAction) {
//                    $scope.twitterImageValidation = false;
//                    $scope.twitterLinkValidation = true;
//                    return false;
//                }
                if (!postData.url) {
                    $scope.twitterImageValidation = false;
                    $scope.twitterPostData = {text: postData.text, url: ""};
//                    $scope.twitterCustomLink = true;
                    $("#linkOfUrls").focus();
                    return false;
                }
            }
            if (!$scope.twitterImageDivToPost) {
                $scope.twitterImageValidation = false;
//                $scope.twitterPostData = {text: postData.text, img: ""};
                $scope.twitterImageValidation = true;
                return false;
            }
            $scope.twitterImageValidation = false;
            return true;
        };

        $scope.openPostOrShedulePopup = function (selectedSocialmedia, postData) {
            $scope.postData = postData;
            $scope.selectedSocialmedia = selectedSocialmedia;

            if ($scope.fbPostValidation(postData))
            {
                $scope.postTypeSelectionPopUp = true;
                if (selectedSocialmedia === "facebook") {
                    $scope.postTo = "Post to Facebook";
                }
            }
            if ($scope.twitterPostValidation(postData))
            {
                $scope.postTypeSelectionPopUp = true;
                if (selectedSocialmedia === "twitter") {
                    $scope.postTo = "Post to Twitter";
                }
            }
        };

        $scope.postToSocialMedia = function (selectedSocialmedia, postData) {
            var data = "";
            var linktitle = "";
            if (selectedSocialmedia === "facebook") {
                appSessionFactory.getFbPostData().then(function (kGlobalFbPostDataObject) {
                    if (kGlobalFbPostDataObject.title) {
                        linktitle = kGlobalFbPostDataObject.title;
                    }
                    data = {
                        imageToPost: kGlobalFbPostDataObject.imgNameToPost,
                        postText: kGlobalFbPostDataObject.postText,
                        title: linktitle,
                        url: kGlobalFbPostDataObject.url,
                        description: kGlobalFbPostDataObject.description,
                        imageType: kGlobalFbPostDataObject.imageType
                    };
                    socialPostFactory.facebookPost(data).then(function (data) {
                        if (data.d.message === 'success') {
                            $scope.isPostSent = true;
                        }
                    });

                });



            } else if (selectedSocialmedia === "twitter") {
                if (!angular.isUndefined(postData.url)) {
                    var bitlyUrl = "";
                    settingsFactory.sortenUrl(postData.url).then(function (response) {
                        bitlyUrl = response.data.url;
                        data = {
                            imageToPost: $scope.selectImageName,
                            text: postData.text,
                            imageType: $scope.selectImageType,
                            shorturl: bitlyUrl
                        };
                        socialPostFactory.twitterPost(data).then(function (response) {
                            if (response.d.message === 'success') {
                                $scope.isPostSent = true;
                            }
                        });
                    });
                } else {
                    data = {
                        imageToPost: $scope.selectImageName,
                        text: postData.text,
                        imageType: $scope.selectImageType,
                        shorturl: bitlyUrl
                    };
                    socialPostFactory.twitterPost(data).then(function (response) {
                        if (response.d.message === 'success') {
                            $scope.isPostSent = true;
                        }
                    });
                }
            }
        };
        $scope.hidePopup = function (popupName) {
            if (popupName === "sendOrSchedulePopup") {
                $scope.postTypeSelectionPopUp = false;
            } else if (popupName === "schedulePopup") {
                $scope.schedulePopup = false;
            }
        };
        $scope.openSchedulePopup = function (selectedSocialmedia) {
            $scope.postTypeSelectionPopUp = false;
            $scope.schedulePopup = true;
            $scope.existingActionPopup = true;
            $scope.createNewActionPopup = false;
            $scope.activeClassExisting = 'active';
            $scope.activeClassNew = '';
            if (selectedSocialmedia === "facebook") {
                $scope.scheduleButtonData = "Schedule this Facebook Post";
            } else if (selectedSocialmedia === "twitter") {
                $scope.scheduleButtonData = "Schedule this twitter Post";
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

        $scope.ddSelectAction = {
            text: "Custom Action"
        };
        $scope.getFacebookActions = function (selectedMarketingProgrmsId) {
//            $scope.ddSelectActionName = [
//                {
//                    text: "Custom Action",
//                    value: "0"
//                }
//            ];
            var data = {programid: selectedMarketingProgrmsId.toString(), type: getfacebook()};
            scheduleActionsFactory.getActionsPost(data).then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM FACEBOOK"}];
                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
                $scope.socialAction = $scope.defaultAction[0].id;
                var actionData = eval(parseData);
                if (actionData !== '') {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                    for (var i = 0; i < actionData.length; i++)
                    {
                        var actionObject = {};
                        actionObject["text"] = actionData[i].schedule_title + " - " + $filter('date')(new Date(actionData[i].action_date), 'MMM-dd-yyyy');
                        actionObject["value"] = actionData[i].id;
                        $scope.ddSelectActionName.push(actionObject);
                    }
                }
                if (actionData === "") {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                }
            });
        };
        $scope.getTwitterActions = function (selectedMarketingProgrmaId) {
            var data = {programid: selectedMarketingProgrmaId.toString(), type: gettwitter()};
            scheduleActionsFactory.getActionsPost(data).then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM TWITTER"}];
                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
                $scope.socialAction = $scope.defaultAction[0].id;
                var actionData = eval(parseData);
                if (actionData !== '') {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                    for (var i = 0; i < actionData.length; i++)
                    {
//                        var actionDate=new Date(actionData[i].schedule_time);
                        var actionObject = {};
                        actionObject["text"] = actionData[i].schedule_title + " - " + $filter('date')(new Date(actionData[i].action_date), 'MMM-dd-yyyy');
                        actionObject["value"] = actionData[i].id;
                        $scope.ddSelectActionName.push(actionObject);
                    }
                }
                if (actionData === '')
                {
                    $scope.ddSelectActionName = [{text: "Custom Action", value: "0"}];
                }

            });
        };
//        $scope.getTwitterActions = function (selectedMarketingProgrmaId) {
//            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: gettwitter()});
//            scheduleActionsFactory.getActionsPost(data).then(function (data) {
//                var parseData = JSON.parse(data.d.details);
//                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM TWITTER"}];
//                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
//                $scope.socialAction = $scope.defaultAction[0].id;
//                growl(JSON.stringify(data));
//            });
//        };
//        $scope.getFacebookActions = function (selectedMarketingProgrmaId) {
//            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: getfacebook()});
//            scheduleActionsFactory.getActionsPost(data).then(function (data) {
//                var parseData = JSON.parse(data.d.details);
//                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM FACEBOOK"}];
//                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
//                $scope.socialAction = $scope.defaultAction[0].id;
//            });
//        };

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
                if (selectedSocialmedia === "facebook") {
                    $scope.getFacebookActions("0");
                } else if (selectedSocialmedia === "twitter") {
                    $scope.getTwitterActions("0");
                }
            });
        };
//        $scope.getAllMaketingPrograms = function (selectedSocialmedia) {
//            companyMarketingProgramFactory.getAllUserMarketingProgramsGet().then(function (data) {
//                $scope.defaultmarketingprogram = [{program_id: 0, name: '--General--', id: 0}];
//                $scope.marketing_programs = $scope.defaultmarketingprogram.concat(data);
//                $scope.selectedMarketingProgrma = $scope.marketing_programs[0].program_id;
//                if (selectedSocialmedia === "facebook") {
//                    $scope.getFacebookActions("0");
//                } else if (selectedSocialmedia === "twitter") {
//                    $scope.getTwitterActions("0");
//                }
//            });
//        };
        $scope.getActions = function (selectedSocialmedia, selectedMarketingProgrmsId) {
            $scope.selectedMarketingProgram = selectedMarketingProgrmsId.value;
            if (selectedSocialmedia === "facebook") {
                $scope.getFacebookActions(selectedMarketingProgrmsId.value);
            }
            if (selectedSocialmedia === "twitter") {
                $scope.getTwitterActions(selectedMarketingProgrmsId.value);
            }
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
                if (selectedSocialmedia === "facebook") {
                    $scope.schedulePostToFacebook(postData);
                } else if (selectedSocialmedia === "twitter") {
                    $scope.schedulePostToTwitter(postData);
                }
            }
        };


        $scope.setTwitterAccessTokenFromSocialHub = function (pinCode) {
            var pin = pinCode;
            settingsFactory.twitterGetTokenGet(pin).then(function (data) {
                if (pin.length > 0) {
                    $scope.showTwitterPopup = false;
                } else {
                    growl(pinerror);
                    $("#pinTextBox").focus();
                }
            });
        };

        $scope.closeTwitterPopup = function ()
        {
            $scope.showTwitterPopup = false;
        };

        var getEpochMillis = function (dateStr) {
            var r = /^\s*(\d{4})-(\d\d)-(\d\d)\s+(\d\d):(\d\d):(\d\d)\s+UTC\s*$/
                    , m = ("" + dateStr).match(r);
            return (m) ? Date.UTC(m[1], m[2] - 1, m[3], m[4], m[5], m[6]) : undefined;
        };

        $scope.schedulePostToFacebook = function (postData) {
            $scope.postedTo = getfacebook();
            var sendData = "";

            appSessionFactory.getFbPostData().then(function (kGlobalFbPostDataObject) {
                var shareText = kGlobalFbPostDataObject.postText;
                var linkTitle = kGlobalFbPostDataObject.title;
                var shareUrl = kGlobalFbPostDataObject.url;
                var linkDescription = kGlobalFbPostDataObject.description;
                var schedule_title = $("#ActionName").val();

                //            if (selectedMarketingProgrmaId !== 0) {
                if ($scope.existingActionPopup) {
                    sendData = {
                        type: getfacebook(),
                        image_name: kGlobalFbPostDataObject.imgNameToPost,
                        program_id: $scope.selectedMarketingProgram.toString(),
                        schedule_id: $scope.socialAction.toString(),
                        schedule_title: schedule_title,
                        image_type: kGlobalFbPostDataObject.imageType,
                        metadata: {
                            description: '"' + linkDescription + '"',
                            post_text: '"' + shareText + '"',
                            url: '"' + shareUrl + '"',
                            ManagedPage: '"' + $rootScope.CurrentFbPageName + '"',
                            title: '"' + linkTitle + '"'
                        }
                    };
                } else {

                    if (!$scope.ddSelectedUser)
                        $scope.ddSelectedUser = "0";
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

                    var timeValues = [];
                    timeValues = schedule_time.split(":");
                    var hours = timeValues[0];
                    var mins = timeValues[1].replace(" AM", "").replace(" PM", "");
//                    var delimiter = timeValues[2];

                    if (schedule_time.indexOf("PM") >= 0) {
                        hours = parseInt(hours) + 12;
                    }
                    var newtime = hours + ":" + mins + ":" + "00";
                    var currDate = moment(schedule_date).format(kGlobalDateFormat);

                    var epoch_time = getEpochMillis(currDate + " " + newtime + " " + 'UTC');

//                    var myEpoch = Date.parse(dateAndTime);

                    sendData = {
                        "schedule_time": epoch_time,
                        "schedule_title": schedule_title,
                        "program_id": $scope.selectedMarketingProgram.toString(),
                        "schedule_desc": "",
                        "type": getfacebook(),
                        "image_name": kGlobalFbPostDataObject.imgNameToPost,
                        "postText": shareText,
                        "title": linkTitle,
                        "url": shareUrl,
                        "description": linkDescription,
                        "image_type": kGlobalFbPostDataObject.imageType,
                        "userAssignedTo": $scope.ddSelectedUser,
                        metadata: {
                            description: '"' + linkDescription + '"',
                            post_text: '"' + shareText + '"',
                            url: '"' + shareUrl + '"',
                            ManagedPage: '"' + $rootScope.CurrentFbPageName + '"',
                            title: '"' + linkTitle + '"'
                        }

                    };
                }
                if ($scope.selectedMarketingProgram !== 0 || $scope.socialAction !== 0) {
                    scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (data) {
                        if (data.d.operationStatus.statusCode === "Success") {
                            $scope.isPostSuccess = true;
                            $scope.schedulePopup = false;
                        }
                    });
                } else {
                    scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (data) {
                        if (data.d.operationStatus.statusCode === "Success") {
                            $scope.isPostSuccess = true;
                            $scope.schedulePopup = false;
                        }
                    });
                }
            });
        };
        $scope.schedulePostToTwitter = function (postData) {
            $scope.postedTo = gettwitter();
            var sendData = "";
            var bitlyUrl = "";
            var data = {access_token_method: "getAccessToken"};
            settingsFactory.twitterPost(data).then(function (response) {
                var twitterData = response.d.message.split(",");
                var accessToken = twitterData[0];
                var tokenSecret = twitterData[1];
                if (!angular.isUndefined(postData.url)) {
                    settingsFactory.sortenUrl(postData.url).then(function (response) {
                        bitlyUrl = response.data.url;
                        if ($scope.selectedMarketingProgram !== 0 || $scope.socialAction !== 0) {
                            sendData = {
                                type: gettwitter(),
                                image_name: $scope.selectImageName,
                                program_id: $scope.selectedMarketingProgram.toString(),
                                schedule_id: $scope.socialAction.toString(),
                                image_type: $scope.selectImageType,
                                token_data: {
                                    "access_token": '"' + accessToken + '"',
                                    "token_secret": '"' + tokenSecret + '"'
                                },
                                metadata: {
                                    text: '"' + postData.text + '"',
                                    shorturl: '"' + bitlyUrl + '"'
                                }
                            };
                            scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (response) {
                                $scope.schedulePopup = false;
                                $scope.isPostSuccess = true;
//                                window.location = "dashboard";
                            });
                        } else {
                            if (!$scope.ddSelectedUser)
                                $scope.ddSelectedUser = "0";
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

                            var timeValues = [];
                            timeValues = schedule_time.split(":");
                            var hours = timeValues[0];
                            var mins = timeValues[1].replace(" AM", "").replace(" PM", "");
//                            var delimiter = timeValues[2];

                            if (schedule_time.indexOf("PM") >= 0) {
                                hours = parseInt(hours) + 12;
                            }
                            var newtime = hours + ":" + mins + ":" + "00";
                            var currDate = moment(schedule_date).format(kGlobalDateFormat);

                            var epoch_time = getEpochMillis(currDate + " " + newtime + " " + 'UTC');

//                            var myEpoch = Date.parse(dateAndTime);
//                            console.log("Epoch: " + myEpoch);

                            sendData = {
                                type: gettwitter(),
                                image_name: $scope.selectImageName,
                                schedule_time: epoch_time,
                                schedule_title: schedule_title,
                                program_id: $scope.selectedMarketingProgram.toString(),
                                schedule_desc: schedule_desc,
                                schedule_id: $scope.socialAction.toString(),
                                image_type: $scope.selectImageType,
                                userAssignedTo: $scope.ddSelectedUser,
                                token_data: {
                                    "access_token": '"' + accessToken + '"',
                                    "token_secret": '"' + tokenSecret + '"'
                                },
                                metadata: {
                                    text: '"' + postData.text + '"',
                                    shorturl: '"' + bitlyUrl + '"'
                                }
                            };
                            scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (response) {
                                $scope.schedulePopup = false;
                                $scope.isPostSuccess = true;
//                                window.location = "dashboard";
                            });
                        }
                    });
                } else {
                    if ($scope.selectedMarketingProgram !== 0 || $scope.socialAction !== 0) {
                        sendData = {
                            type: gettwitter(),
                            image_name: $scope.selectImageName,
                            program_id: $scope.selectedMarketingProgram.toString(),
                            schedule_id: $scope.socialAction.toString(),
                            image_type: $scope.selectImageType,
                            token_data: {
                                "access_token": '"' + accessToken + '"',
                                "token_secret": '"' + tokenSecret + '"'
                            },
                            metadata: {
                                text: '"' + postData.text + '"',
                                shorturl: '"' + bitlyUrl + '"'
                            }
                        };
                        scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (response) {
                            $scope.schedulePopup = false;
                            $scope.isPostSuccess = true;
//                            window.location = "dashboard";
                        });
                    } else {

                        if (!$scope.ddSelectedUser)
                            $scope.ddSelectedUser = "0";
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
                        var timeValues = [];
                        timeValues = schedule_time.split(":");
                        var hours = timeValues[0];
                        var mins = timeValues[1].replace(" AM", "").replace(" PM", "");
//                        var delimiter = timeValues[2];

                        if (schedule_time.indexOf("PM") >= 0) {
                            hours = parseInt(hours) + 12;
                        }
                        var newtime = hours + ":" + mins + ":" + "00";

                        var currDate = moment(schedule_date).format(kGlobalDateFormat);

                        var epoch_time = getEpochMillis(currDate + " " + newtime + " " + 'UTC');

//                        var myEpoch = Date.parse(dateAndTime);
                        sendData = {
                            type: gettwitter(),
                            image_name: $scope.selectImageName,
                            schedule_time: epoch_time,
                            schedule_title: schedule_title,
                            program_id: $scope.selectedMarketingProgram.toString(),
                            schedule_desc: schedule_desc,
                            schedule_id: $scope.socialAction.toString(),
                            image_type: $scope.selectImageType,
                            userAssignedTo: $scope.ddSelectedUser,
                            token_data: {
                                "access_token": '"' + accessToken + '"',
                                "token_secret": '"' + tokenSecret + '"'
                            },
                            metadata: {
                                text: '"' + postData.text + '"',
                                shorturl: '"' + bitlyUrl + '"'
                            }
                        };
                        scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (response) {
                            $scope.schedulePopup = false;
                            $scope.isPostSuccess = true;
//                            window.location = "dashboard";
                        });
                    }

                }
            });
        };
        $scope.previousButton = function (popupName) {
            $scope.schedulePopup = false;
            $scope.postTypeSelectionPopUp = true;
        };

        $scope.setTwitterActions = function (twitterDetails) {
            scheduleActionsFactory.getActionsPost(twitterDetails).then(function (twitterData) {
                $scope.twitter_actions = eval(JSON.parse(twitterData.d.details));
            });
        };

        $scope.validateProgramActions = function (marketingProgram) {
            if (marketingProgram === null) {
                $scope.existingAction = false;
                $scope.getTwitterActionsData = {programid: "0", type: gettwitter()};
                $scope.setTwitterActions($scope.getTwitterActionsData);
            } else {
                $scope.existingAction = true;
                $scope.getTwitterActionsData = {programid: marketingProgram.toString(), type: gettwitter()};
                $scope.setTwitterActions($scope.getTwitterActionsData);
            }
        };
        $scope.setDefaulttwitterAccount = function (twitterPin) {
            if (twitterPin.length > 0) {
                settingsFactory.twitterGetTokenGet(twitterPin).then(function (data) {
                    $scope.showTwitterPopup = false;
                    $location.path('/twitterpost');
                });
            } else {
                growl(pinerror);
                $("#pinTextBox").focus();
            }
        };

        $scope.showImageUploadPopup = function ()
        {
//            $scope.chooseImage = false;
            $("#filesToUpload").trigger("click");
        };

        $scope.imageModal = [];
        $scope.showImageUploaded = true;

        $scope.imageUpload = function (element) {
            var reader = new FileReader();
            reader.onload = $scope.imageIsLoaded;
            reader.readAsDataURL(element.files[0]);
        };

        $scope.imageIsLoaded = function (e) {
            $scope.$apply(function () {
                $scope.imageModal = [];
                $scope.imageModal.push(e.target.result);
            });
            var showImage = document.getElementById('upload');
            angular.element(showImage).triggerHandler('click');
        };
        $scope.uploadLogo = function (myFile) {
            var file = myFile;
            imageFactory.saveImagePost(file).then(function (data) {
                growl("Image Uploaded successfully");
                $scope.getUserImages();
            });
        };
        $scope.openAviaryEditor = function (imageId) {
            $scope.launchEditor(imageId);
        };
        var featherEditor = new Aviary.Feather({
            apiKey: getAviaryApiKey(),
            apiVersion: 3,
            theme: 'dark', // Check out our new 'light' and 'dark' themes!
            tools: 'all',
            appendTo: '',
            onSave: function (imageID, newURL) {
                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    var img = document.getElementById(imageID);
                    $.ajax({
                        url: getHost() + 'DownloadImageFromUrl',
                        method: 'POST',
                        async: false,
                        data: JSON.stringify({
                            folderName: "aviary",
                            imageUrl: newURL,
                            companyId: kGlobalCompanyObject.companyId,
                            userId: kGlobalCompanyObject.userId
                        }),
                        success: function (responseText) {
                            img.src = responseText.link;
                            featherEditor.close();
                        }
                    });
                });
            },
            onError: function (errorObj) {
                alert(errorObj.message);
            }
        });

        $scope.launchEditor = function (id, src) {

            featherEditor.launch({
                image: id,
                url: src,
                cropPresets: [
                    'Custom',
                    'Original',
                    ['1200x627', '120:62'],
                    ['600x600', '60:60']
                ]
            });
            return false;
        };
        $scope.setChannel = function (channel) {
            $scope.selectedChannel = channel;
            if (channel === 'facebook') {
                $location.path('/facebookpost');

            } else if (channel === 'twitter') {
                $location.path('/twitterpost');
            }

        };


    }]);
//socialFlowApp.directive('toggleClass', function() {
//    return {
//        restrict: 'C',
//        link: function(scope, element, attrs) {
//            element.bind('click', function() {
//                if(element.attr("class") == "gallery-item-wrap-selected") {
//                    element.removeClass("gallery-item-wrap-selected");
//                    element.addClass("gallery-item-wrap-selected-istrue");
//                } else {
//                    element.removeClass("gallery-item-wrap-selected-true");
//                    element.addClass("gallery-item-wrap-selected");
//                }
//            });
//        }
//    };
//});
