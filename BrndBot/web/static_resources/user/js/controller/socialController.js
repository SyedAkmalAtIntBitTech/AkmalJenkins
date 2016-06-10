/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

socialFlowApp.controller("socialController", ['$scope', '$location', '$window', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'companyMarketingProgramFactory', 'companyImagesFactory', 'companyFactory', 'imageFactory', 'socialPostFactory', function ($scope, $location, $window, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, companyMarketingProgramFactory, companyImagesFactory, companyFactory, imageFactory, socialPostFactory) {
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


        $scope.getManagePage = function () {
            var data = JSON.stringify({redirectUrl: "user/socialsequence"});
            settingsFactory.fbLoginPost(data).then(function (data) {
                $window.location = data.d.details[0];
            });

        };
        $scope.isDefaultTwitterAccountSet = function () {
            var data = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.twitterPost(data).then(function (data) {
                var twitterAccessToken = data.d.message;
                if ((twitterAccessToken === null) || (twitterAccessToken === ""))
                {
                    settingsFactory.twitterLoginGet().then(function (data1) {
                        alert(JSON.stringify(data1));
//                        $("#twitterSetPinPopUp").show();
//                        $("#twitterlink").html("<a href='" + responseText.d.details[0] + "' target='_blank'>get your pin</a>");
                    });
                } else {
//                      $("#twitterSetPinPopUp").hide();
                    $scope.showTwitterPopup = false;
                    $location.path('/twitterpost');
                }
                alert(JSON.stringify(data.d.message));
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
//            $scope.imageUnSelect = 'gallery-item-wrap-selected-true';
            angular.element(document.getElementsByClassName('gallery-item-wrap-selected-true')).addClass('gallery-item-wrap-selected').removeClass('gallery-item-wrap-selected-true');
            angular.element(document.getElementById(id)).removeClass('gallery-item-wrap-selected').addClass('gallery-item-wrap-selected-true');
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
//            alert(urlsLink);
//            $scope.linkUrls=urlsLink.split('--').pop();
//            alert($scope.linkUrls);
        };

        $scope.postToFacebook = function () {

        };

        $scope.postToTwitter = function (twitterShare) {

//            alert(JSON.stringify(twitterShare)+"\n"+$scope.selectImageName+"\n"+$scope.selectImageType);
            var username = "sandeep264328"; // bit.ly username
            var key = "R_63e2f83120b743bc9d9534b841d41be6";
            var BitlyUserDetails = {longUrl: twitterShare.url,
                apiKey: key,
                login: username
            };
            alert(JSON.stringify(BitlyUserDetails));
            socialPostFactory.shortenUrl(BitlyUserDetails).then(function (data) {
//                $scope.show_hide_SocialListSelectionPopup(true);
                alert(JSON.stringify(data));
//                $scope.bit_url = urlData.data.url;
//                socialPostFactory.postToTwitterURL().then(function (data){
//                    alert(data);
//                });
            });

//          showOverlay();
            //        var shareText = $("#twitterShareText").val();
            //        var url = $("#linkUrl").val();
            //        var image_name = selecImageName;
            //        var image_type = selecImageType;
            //
            //        var username = "sandeep264328"; // bit.ly username
            //        var key = "R_63e2f83120b743bc9d9534b841d41be6";
//            $.ajax({
//                url: "http://api.bit.ly/v3/shorten",
//                async: false,
//                data: {longUrl: twitterShare.url, apiKey: key, login: username},
//                dataType: "jsonp",
//                success: function (v)
//                {   alert(v);
//                    $scope.bit_url = v.data.url;
//                    socialPostFactory.postToTwitterURL().then(function (data){
//                        alert(data);
//                    });
//                    
////                    $.ajax({
////                        url: getHost() + "socialPost/postToTwitter",
////                        method: 'post',
////                        data: JSON.stringify({
////                            imageToPost: image_name,
////                            twittweraccestoken: $("#twittweraccestoken").val(),
////                            twitterTokenSecret: $("#twitterTokenSecret").val(),
////                            text: shareText,
////                            imageType: image_type,
////                            shorturl: bit_url
////                        }),
////                        success: function (responseText) {
////                            hideOverlay();
////                            $("#sendpopup").hide();
////                            $("#fade").hide();
////        //                    alert(JSON.stringify(responseText));
////                            var isSuccess = responseText.d.message;
////                            if (isSuccess === "success") {
////                                alert("Successfully posted to Twitter.");
////                                $("#twitterSuccessPostPopup").show();
////                            }
////                        },
////                        error: function (jqXHR, textStatus, errorThrown) {
////                            alert(JSON.stringify(jqXHR));
////                        }
////                    });
//                }
//            });

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
            alert(code);
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
//                $("#imagePopUp").hide();
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
//                alert(JSON.stringify(data));
            });
        };

        $scope.schedulePostToTwitter = function (flag) {
            if (flag === true) {
                $scope.showSchedulePopup = true;
                $scope.showSendPopup = false;
                $scope.facebookActions = false;
                $scope.twitterActions = true;
                companyMarketingProgramFactory.getAllUserMarketingProgramsGet().then(function (data) {
                    $scope.marketing_programs = data;
                    var getTwitterActionsData = {programid: "0", type: gettwitter()};
                    scheduleActionsFactory.getActionsPost(getTwitterActionsData).then(function (twitterData) {
                        alert((JSON.parse(twitterData.d.details)));
                        $scope.twitter_actions = JSON.parse(twitterData.d.details);
                    });
                });
            } else {

                $scope.showSchedulePopup = false;
                $scope.showSendPopup = true;
            }

        };

        $scope.getSocialTwitterActions = function () {
            alert($scope.marketingProgramsList);
            var getTwitterActionsData = {programid: $scope.marketingProgramsList, type: gettwitter()};
            scheduleActionsFactory.getActionsPost(getTwitterActionsData).then(function (twitterData) {
                alert((JSON.parse(twitterData.d.details)));
                $scope.twitter_actions = JSON.parse(twitterData.d.details);
            });
//        $http({
//            method: 'POST',
//            url: getHost() + 'actions/getActions',
//            data:{programid:program_id,type:gettwitter()}
//        }).success(function (data) {
////            alert("twitter.... "+JSON.stringify(data));
//            var parseData=JSON.parse(data.d.details)
//            $scope.twitter_actions = eval(parseData);
//        }).error(function (data) {
//            alert(requesterror);
//        });
        };
    }]);
