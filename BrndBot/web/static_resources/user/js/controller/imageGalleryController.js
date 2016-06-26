/* Tasmiya P.S
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

imagesFlowApp.controller("imageGalleryController", ['$scope', '$window', '$http', '$location', 'imageFactory','companyImagesFactory',function ($scope, $window, $http, $location, imageFactory,companyImagesFactory) {
   

        $scope.uploadLogo = function (myFile) {   
        var file = $("#filesToUpload").val();         
        var file = myFile;
            imageFactory.saveImagePost(file).then(function (data){
                alert("Image Uploaded successfully");
                $scope.hidePopup=false;
                $scope.getAllCompanyImages();
            });
        };
        
        $scope.getAllCompanyImages = function ()
        {
            companyImagesFactory.companyImagesGet().then(function (data){
                $scope.datalists = data.d.details;
            });
        };
        
        $scope.deleteImage = function (companyImageId)
        {
            companyImagesFactory.deleteCompanyImagesPost(companyImageId).then(function (data){
                alert("Image deleted successfully");
                $scope.datalists = data.d.details;
                $scope.getAllCompanyImages();
            });
        };
        
        $scope.showImageUploadPopup = function()
        {
            $scope.hidePopup=true;
        };
    
        $scope.closeImageUploadPopup = function()
        {
            $scope.hidePopup=false;
            $scope.getAllCompanyImages();
        };
    
        $scope.showImage = function()
        {
            $scope.hidePopup=false;
        };

}]);
