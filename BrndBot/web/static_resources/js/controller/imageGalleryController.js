/* Tasmiya P.S
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

imagesFlowApp.controller("imageGalleryController", ['$scope', '$window', '$http', '$location', 'imageFactory', 'companyImagesFactory', 'companyFactory', 'appSessionFactory', function ($scope, $window, $http, $location, imageFactory, companyImagesFactory, companyFactory, appSessionFactory) {

        $scope.logoValidation = logoValidation;
        
        $scope.getUserDetails = function(){
            appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;

                kGlobalCompanyObject.userHashId = 'undefined';
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function(data){});
                appSessionFactory.getDashboardMessage().then(function(message){
                    if(message)
                    {
                        growl(message);
                        appSessionFactory.clearDashboardMessage().then(function(message){
                        });
                    }
                appSessionFactory.getUser().then(function(kGlobalCompanyObject){
                        $scope.hasMultipleCompany = kGlobalCompanyObject.hasMultipleCompany;
                    });
                });
            });
        };
        
        //This was added by Andy for Gallery hover options
        $scope.hoverEditGalleryImage = false;
        $scope.hoverInGalleryImage = function () {
            this.hoverEditGalleryImage = true;
        };

        $scope.hoverOutGalleryImage = function () {
            this.hoverEditGalleryImage = false;
        };

        //End of Gallery hover options

        $scope.getCompanyId = function () {
            companyFactory.currentCompanyGet().then(function (companyData) {
                $scope.companyId = companyData.d.details[0];
            });
        };

        $scope.imageGalleryValidation = function (myFile) {
            if (!myFile) {
                $scope.myFile = "";
                return false;
            }
            return true;
        };

        $scope.uploadLogo = function (myFile) {
            var file = myFile;
            if ($scope.imageGalleryValidation(myFile))
            {
                imageFactory.saveImagePost(file).then(function (data) {
                    growl("Image Uploaded successfully");
                    $scope.hidePopup = false;
                    $("#fade").hide();
                    $scope.getAllCompanyImages();
                });
            }
        };

        $scope.getAllCompanyImages = function ()
        {
            $scope.getCompanyId();
            companyImagesFactory.companyImagesGet().then(function (data) {
                $scope.datalists = data.d.details;
            });
        };

        $scope.deleteImage = function (companyImageId)
        {
            companyImagesFactory.deleteCompanyImagesPost(companyImageId).then(function (data) {
                growl("Image deleted successfully");
                $scope.datalists = data.d.details;
                $scope.getAllCompanyImages();
            });
        };

        $scope.showImageUploadPopup = function ()
        {
//            $("#fade").show();
//            $scope.hidePopup = true;
            $("#filesToUpload").trigger("click");
        };

        $scope.closeImageUploadPopup = function ()
        {
            $scope.hidePopup = false;
            $("#fade").hide();
            $scope.getAllCompanyImages();
        };

        $scope.showImage = function ()
        {
            $scope.hidePopup = false;
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
                $scope.showImageUploaded = false;
            });
            var showImage = document.getElementById('upload');
            angular.element(showImage).triggerHandler('click');
        };

//        $scope.datalists = [];
//
//        $scope.imageUpload = function (myFile) {
//            growl(myFile);
////             var fd = new FormData();
////    //Take the first selected file
////            fd.append("file", $(myFile)[0].files[0]);
//
//            
//            var reader = new FileReader();
//            reader.onload = $scope.imageIsLoaded;
//            reader.readAsDataURL(myFile.files[0]);
//            var file = myFile;
//            imageFactory.saveImagePost(fd).then(function (data) {
//                growl(JSON.stringify(data));
//                growl("Image Uploaded successfully");
//                $scope.getAllCompanyImages();
//            });
//        };
//
//        $scope.imageIsLoaded = function (e) {
//            $scope.$apply(function () {
//                $scope.datalists.push(e.target.result);
//            });
//        };

    }]);

