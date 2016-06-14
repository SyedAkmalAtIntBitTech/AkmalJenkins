imagesFlowApp.controller("imageGalleryController", ['$scope', '$window', '$location', 'imageFactory','assetsFactory',function ($scope, $window, $location, imageFactory,assetsFactory) {
alert("asdsad");
         
//        $scope.saveImage = function () {
//            alert("...");
//            var file = companyImage;
//            alert(file);
//            imageFactory.saveImagePost(file).then(function (data) {
//                alert(JSON.stringify(data));
//                $location.path("signup/choosepalette");
//            });
//        };
    
//     $scope.getUserImages = function () {
//            companyImagesFactory.companyImagesGet().then(function (data) {
//                $scope.datalists = data.d.details;
//            });
//            companyFactory.currentCompanyGet().then(function (data) {
//                $scope.companyId = data.d.details[0];
//            });
//        };

        $scope.uploadLogo1 = function (myFile1) {
            alert("...");
            var file = myFile1;
            alert(file);
            imageFactory.saveImagePost(file).then(function (data){
                alert(JSON.stringify(data));});
        };
        $scope.getimages = function ()
        {
            alert("...");
            assetsFactory.globalImageGet().then(function (data){
                
            });
        };

        
        $scope.Showimguploadpopup = function()
        {
        alert("...");
        $scope.hidePopup=true;
        }
    
      $scope.showImage = function(){
       $scope.hidePopup=false;
}
//    
//        $scope.uploadFile = function(){
//               var file = $scope.myFile;
//               
//               console.log('file is ' );
//               console.dir(file);
//               
//               var uploadUrl = "/fileUpload";
//               fileUpload.uploadFileToUrl(file, uploadUrl);
//            };
//
//        function Showimguploadpopup()
//{
//    alert("...");
//    document.getElementById("imagetext").value="";
//    document.getElementById("filesToUpload").value="";    
//    document.getElementById('fade').style.display = 'block';
//    document.getElementById('imagepopup').style.display = 'block';
//    document.getElementById('addAction').style.display = 'block';
//    
//    overlay();
//}
        }]);
