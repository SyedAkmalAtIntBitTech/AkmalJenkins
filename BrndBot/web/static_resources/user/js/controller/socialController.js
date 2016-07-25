/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

socialFlowApp.controller("socialController", ['$scope', '$rootScope', '$location', '$window', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'companyMarketingProgramFactory', 'companyImagesFactory', 'companyFactory', 'imageFactory', 'socialPostFactory', 'scheduleActionsFactory', function ($scope, $rootScope, $location, $window, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, companyMarketingProgramFactory, companyImagesFactory, companyFactory, imageFactory, socialPostFactory, scheduleActionsFactory) {
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
        $scope.imageToBeUploaded = 'images/uploadPhoto.svg';
        $scope.postType = 'Change To Link Post';
        $scope.existingAction = false;
        $scope.managepage = "";
        var schedule_desc = "";
        $scope.actionNameValidation = actionNameValidation;
        $scope.scheduleDateValidation = scheduleDateValidation;
        $scope.scheduleTimeValidation = scheduleTimeValidation;
        $scope.facebookPostValidation = facebookPostValidation;
        $scope.fbPostData = [];
//        $scope.twitterPostData = [];
        
        $scope.getManagePage = function () {
            var fbData = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.facebookPost(fbData).then(function (fbResponseData) {
                var fbAccessToken = fbResponseData.d.message;
                if ((fbAccessToken === null) || (fbAccessToken === ""))
                {
                    var data = JSON.stringify({redirectUrl: "user/socialsequence"});
                    settingsFactory.fbLoginPost(data).then(function (data) {
                        $window.location = data.d.details[0];
                    });
                }
                else {
                    $location.path('/facebookpost');
                }
            });

        };
        $scope.isDefaultTwitterAccountSet = function () {
            var data = JSON.stringify({access_token_method: "getAccessToken"});
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
                    $location.path('/twitterpost');
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

        $scope.selectImage = function (id) {
////            $scope.imageUnSelect = 'gallery-item-wrap-selected-true';
////            angular.element(document.getElementsByClassName('gallery-item-wrap-selected-true')).removeClass('gallery-item-wrap-selected-true').addClass('gallery-item-wrap-selected');
//            angular.element(document.getElementById(id)).addClass('gallery-item-wrap-selected-true');
        };

        $scope.selectImageToPost = function (imageName, imageType, companyId) {
            $scope.selectImageName = imageName;
            $scope.selectImageType = imageType;
            $scope.selectImageType.toLowerCase();
            $scope.selectCompanyId = companyId;
        };

        $scope.getUrls = function () {
            companyMarketingProgramFactory.getAllUserMarketingProgramsUserIdGet().then(function (data) {
                $scope.urls = data;
                $scope.show_BlackLayer = false;
                $scope.show_Post_SchedulePopup = false;
            });
        };

        $scope.getSelectedUrl = function (urlsLink) {

        };

        $scope.postToFacebook = function (fbPostData) {

            $scope.twitterActions = false;
            $scope.facebookActions = true;
            var data = JSON.stringify({
                imageToPost: $scope.selectImageName,
                accessToken: $rootScope.CurrentFbAccessToken,
                postText: fbPostData.shareText,
                title: fbPostData.linkTitle,
                url: fbPostData.url,
                description: fbPostData.linkDescription,
                imageType: $scope.selectImageType
            });
            socialPostFactory.facebookPost(data).then(function (data) {
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
//                    alert(data);
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
                    $scope.managepage = true;
                    $scope.fbPagesDetails = data.d.details[0].fbPages;
                    $scope.fbProfileName = data.d.details[0].user_profile_name;
                });
            }
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
        };
        $scope.postToSelectedPage = function () {
            $scope.managepage = false;
            var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
            if (addDafaultmanagePage) {
                var pageDetails = JSON.stringify({
                    access_token_method: "setAccessToken",
                    access_token: $scope.CurrentFbAccessToken,
                    default_page_name: $scope.CurrentFbPageName,
                    fb_user_profile_name: $scope.FbProfileName
                });
                settingsFactory.facebookPost(pageDetails).then(function (data) {
                });
            }
            $location.path("/facebookpost");
        };
        $scope.changeTwitterPostType = function () {
            if ($scope.showTwitterLink === true) {
//                JSON.stringify(twitterShareTextValue.url="");
                $scope.showTwitterLink = false;
            } else {
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

        $scope.hideFbPopup1 = function (s) {
//                $("#fbmanagePagePopUp").show();
            $scope.managepage = false;
        };
        $scope.changeFbPostType = function (type) {
            if (type === "Change To Link Post") {
                $scope.linkpost = true;
                $scope.postType = 'Change To Normal Post';
            } else if (type === 'Change To Normal Post') {
                $scope.linkpost = false;
                $scope.postType = 'Change To Link Post';
                $scope.fbPostData = null;
                $("#linkTitle").val("");
                $("#linkDescription").val("");
                $("#linkUrl").val("");
            }

        };

//        ...................post or schedule new functions..................

        $scope.postValidation = function (postData) {
            if (!postData.shareText) {
                $scope.fbPostData = {shareText: ""};
                $("#shareText").focus();
                return false;
            }
//            if (!postData.text) {
//                $scope.twitterPostData = {text: ""};
//                $("#twitterShareText").focus();
//                return false;
//            }
            else
            {
                return true;
            }
        };

        $scope.openPostOrShedulePopup = function (selectedSocialmedia, postData) {
            if ($scope.postValidation(postData))
            {
                $scope.postTypeSelectionPopUp = true;
                $scope.postData = postData;
                $scope.selectedSocialmedia = selectedSocialmedia;

                if (selectedSocialmedia === "facebook") {
                    $scope.postTo = "Post to Facebook";
                } else if (selectedSocialmedia === "twitter") {
                    $scope.postTo = "Post to Twitter";
                }
            }
        };

        $scope.postToSocialMedia = function (selectedSocialmedia, postData) {
            var data = "";
            if (selectedSocialmedia === "facebook") {
                data = JSON.stringify({
                    imageToPost: $scope.selectImageName,
                    accessToken: $rootScope.CurrentFbAccessToken,
                    postText: postData.shareText,
                    title: postData.linkTitle,
                    url: postData.url,
                    description: postData.linkDescription,
                    imageType: $scope.selectImageType
                });

                socialPostFactory.facebookPost(data).then(function (data) {
                });
            } else if (selectedSocialmedia === "twitter") {
                if (!angular.isUndefined(postData.url)) {
                    var bitlyUrl = "";
                    settingsFactory.sortenUrl(postData.url).then(function (response) {
                        bitlyUrl = response.data.url;
                        data = JSON.stringify({
                            imageToPost: $scope.selectImageName,
                            text: postData.text,
                            imageType: $scope.selectImageType,
                            shorturl: bitlyUrl
                        });
                        socialPostFactory.twitterPost(data).then(function (response) {
                        });
                    });
                } else {
                    data = JSON.stringify({
                        imageToPost: $scope.selectImageName,
                        text: postData.text,
                        imageType: $scope.selectImageType,
                        shorturl: bitlyUrl
                    });
                    socialPostFactory.twitterPost(data).then(function (response) {
                    });
                }
            }
        };
        $scope.schedulePostToSocialMedia = function (selectedSocialmedia, postData) {
            if (selectedSocialmedia === "facebook") {
                var data = JSON.stringify({
                    imageToPost: $scope.selectImageName,
                    accessToken: $rootScope.CurrentFbAccessToken,
                    postText: postData.shareText,
                    title: postData.linkTitle,
                    url: postData.url,
                    description: postData.linkDescription,
                    imageType: $scope.selectImageType
                });
                //To Do facebook schedule post
            } else if (selectedSocialmedia === "twitter") {
                //To Do twitter schedule post
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
        $scope.ddSelectActionName = [
            {
                text: "Custom Action",
                value: "0"
            }
        ];
        $scope.ddSelectAction = {
            text: "Custom Action"
        };
        $scope.getFacebookActions = function (selectedMarketingProgrmaId) {
            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: getfacebook()});
            scheduleActionsFactory.getActionsPost(data).then(function (data) {
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
        $scope.getTwitterActions = function (selectedMarketingProgrmaId) {
            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: gettwitter()});
            scheduleActionsFactory.getActionsPost(data).then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM TWITTER"}];
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
//        $scope.getTwitterActions = function (selectedMarketingProgrmaId) {
//            var data = JSON.stringify({programid: selectedMarketingProgrmaId.toString(), type: gettwitter()});
//            scheduleActionsFactory.getActionsPost(data).then(function (data) {
//                var parseData = JSON.parse(data.d.details);
//                $scope.defaultAction = [{id: 0, schedule_title: "CUSTOM TWITTER"}];
//                $scope.SocialActionsDetails = $scope.defaultAction.concat(eval(parseData));
//                $scope.socialAction = $scope.defaultAction[0].id;
//                alert(JSON.stringify(data));
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
        $scope.ddSelectMarketingCampaignName = [
            {
                text: "--General--",
                value: "0"
            }
        ];

        $scope.ddSelectMarketingCampaign = {
            text: "--General--"
        };

        $scope.getAllMaketingPrograms = function (selectedSocialmedia) {
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
        $scope.getActions = function (selectedSocialmedia, selectedMarketingProgrmaId) {
            $scope.selectedMarketingProgrma = selectedMarketingProgrmaId.value;
            if (selectedSocialmedia === "facebook") {
                $scope.getFacebookActions(selectedMarketingProgrmaId);
            } else if (selectedSocialmedia === "twitter") {
                $scope.getTwitterActions(selectedMarketingProgrmaId.value);
            }
        };
        $scope.setAction = function (selectedAction) {
            $scope.socialAction = selectedAction.value;
        };

        $scope.schedulePostValidation = function () {
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
                $("#actionTime").focus();
                $scope.actionTimeVal = "";
                return false;
            }
            else
            {
                return true;
            }
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

        $scope.schedulePostToFacebook = function (postData) {
            var sendData = $scope.getScheduleData($scope.selectedMarketingProgrma, postData, getfacebook());
            if ($scope.selectedMarketingProgrma !== 0 || $scope.socialAction !== 0) {
                scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (data) {
                    if (data.d.operationStatus.statusCode === "Success") {
                        $scope.schedulePopup = false;
                        window.location = "dashboard";
                    }
                });
            } else {
                scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (data) {
                    if (data.d.operationStatus.statusCode === "Success") {
                        $scope.schedulePopup = false;
                        window.location = "dashboard";
                    }
                });
            }
        };
        $scope.schedulePostToTwitter = function (postData) {
            var sendData = "";
            var bitlyUrl = "";
            var data = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.twitterPost(data).then(function (response) {
                var twitterData = response.d.message.split(",");
                var accessToken = twitterData[0];
                var tokenSecret = twitterData[1];
                if (!angular.isUndefined(postData.url)) {
                    settingsFactory.sortenUrl(postData.url).then(function (response) {
                        bitlyUrl = response.data.url;
                        if ($scope.selectedMarketingProgrma !== 0 || $scope.socialAction !== 0) {
                            sendData = JSON.stringify([{
                                    type: gettwitter(),
                                    image_name: $scope.selectImageName,
                                    program_id: $scope.selectedMarketingProgrma.toString(),
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
                                }]);
                            scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (response) {
                            });
                        } else {
                            var schedule_title = $("#ActionName").val();
                            var schedule_date = $("#actionDate").val();
                            var schedule_time = $("#actionTime").val().replace(/ /g, '');
                            var dateAndTime = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
                            var myEpoch = Date.parse(dateAndTime);
                            sendData = [{
                                    type: gettwitter(),
                                    image_name: $scope.selectImageName,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: $scope.selectedMarketingProgrma.toString(),
                                    schedule_desc: schedule_desc,
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
                                }];
                            scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (response) {
                            });
                        }
                    });
                } else {
                    if ($scope.selectedMarketingProgrma !== 0 || $scope.socialAction !== 0) {
                        sendData = JSON.stringify([{
                                type: gettwitter(),
                                image_name: $scope.selectImageName,
                                program_id: $scope.selectedMarketingProgrma.toString(),
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
                            }]);
                        scheduleActionsFactory.scheduleSocialPostActionsPost(sendData).then(function (response) {
                        });
                    } else {
                        var schedule_title = $("#ActionName").val();
                        var schedule_date = $("#actionDate").val();
                        var schedule_time = $("#actionTime").val().replace(/ /g, '');
                        var dateAndTime = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
                        var myEpoch = Date.parse(dateAndTime);
                        sendData = [{
                                type: gettwitter(),
                                image_name: $scope.selectImageName,
                                schedule_time: myEpoch,
                                schedule_title: schedule_title,
                                program_id: $scope.selectedMarketingProgrma.toString(),
                                schedule_desc: schedule_desc,
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
                            }];
                        scheduleActionsFactory.scheduleSocialPostPost(sendData).then(function (response) {
                        });
                    }

                }
            });
        };
        $scope.getScheduleData = function (selectedMarketingProgrmaId, postData, socialMediaType) {
            var sendData = "";
//            if (selectedMarketingProgrmaId !== 0) {
            if ($scope.existingActionPopup) {
                sendData = JSON.stringify([{
                        type: socialMediaType,
                        image_name: $scope.selectImageName,
                        program_id: $scope.selectedMarketingProgrma.toString(),
                        schedule_id: $scope.socialAction.toString(),
                        image_type: $scope.selectImageType,
                        token_data: {
                            access_token: $rootScope.CurrentFbAccessToken
                        },
                        metadata: {
                            description: '"' + postData.linkDescription + '"',
                            post_text: '"' + postData.shareText + '"',
                            url: '"' + postData.url + '"',
                            ManagedPage: '"' + $rootScope.CurrentFbPageName + '"',
                            title: '"' + postData.linkTitle + '"'
                        }
                    }]);
            } else {
                var schedule_title = $("#ActionName").val();
                var schedule_date = $("#actionDate").val();
                var schedule_time = $("#actionTime").val().replace(/ /g, '');
                var dateAndTime = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
                var myEpoch = Date.parse(dateAndTime);

                console.log("Epoch: " + myEpoch);
                sendData = JSON.stringify([{
                        "schedule_time": myEpoch,
                        "schedule_title": schedule_title,
                        "program_id": $scope.selectedMarketingProgrma.toString(),
                        "schedule_desc": "",
                        "type": socialMediaType,
                        "image_name": $scope.selectImageName,
                        "accessToken": $rootScope.CurrentFbAccessToken,
                        "postText": postData.shareText,
                        "title": postData.linkTitle,
                        "url": postData.url,
                        "description": postData.linkDescription,
                        "image_type": $scope.selectImageType,
                        token_data: {
                            "access_token": $rootScope.CurrentFbAccessToken
                        },
                        metadata: {
                            description: '"' + postData.linkDescription + '"',
                            post_text: '"' + postData.shareText + '"',
                            url: '"' + postData.url + '"',
                            ManagedPage: '"' + $rootScope.CurrentFbPageName + '"',
                            title: '"' + postData.linkTitle + '"'
                        }
                    }]);
            }
            return sendData;
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
                alert(pinerror);
                $("#pinTextBox").focus();
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
