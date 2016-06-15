/* Tasmiya P.S
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

imagesFlowApp.controller("imageGalleryController", ['$scope', '$window', '$http', '$location', 'imageFactory','companyImagesFactory',function ($scope, $window, $http, $location, imageFactory,companyImagesFactory) {
   

        $scope.uploadLogo1 = function (myFile1) {
            
         var file = $("#filesToUpload").val();         
         alert(file.substring(file.lastIndexOf("\\") + 1, file.length));
        var file = myFile1;
            imageFactory.saveImagePost(file).then(function (data){
                alert(JSON.stringify(data));});
        };
        
        $scope.getAllCompanyImages = function ()
        {
            companyImagesFactory.companyImagesGet().then(function (data){
                alert(JSON.stringify(data));
                $scope.datalists = data.d.details;
                
               
            });
        };
        
        $scope.deleteImage = function (companyImageId)
        {
            companyImagesFactory.deleteCompanyImagesPost(companyImageId).then(function (data){
                alert(JSON.stringify(data));
                $scope.datalists = data.d.details;
                $scope.getAllCompanyImages();
                
               
            });
        };
        

        
        $scope.Showimguploadpopup = function()
        {
//        $scope.fadeblackOverlay="fadeblackOverlay";
        $scope.hidePopup=true;
        
    };
    
    $scope.closeimguploadpopup = function()
    {
        $scope.hidePopup=false;
        $scope.getAllCompanyImages();
    }
    
      $scope.showImage = function(){
       $scope.hidePopup=false;
};

              }]);

