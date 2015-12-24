function controllerMarketingCampaign($scope, $http) {
    
//    $scope.entities_selected_time = "";
//    $scope.master_facebook = getfacebook();
//    $scope.master_twitter = gettwitter();
//    $scope.master_email = getemail();
//    $scope.master_note = getnote();
//    $scope.getImageId = function(id,imagename)
//    {
//        alert(imageName);
//        $("#addimage").show();
//        $(".imageGallery-card").css("background-color", "#ffffff");
//        $(".imageGallery-card >div >div").css("color", "#5F6775");
//        $("#div"+id).css("background-color", "#5cc1a4");
//        $("#div"+id+" > div > div").css("color", "#ffffff");
//        $("#selectedimagename").val(imagename);
//        $("#selectedimageid").val(id);
//    };
//    
    
    $scope.showImages = function(){
                $("#addContact").hide();     
                $("#imageGalleryDiv").show();  
                $("#fade").show();
                //$("#imageGallerySection").show();
                $scope.curPage = 0;
                $scope.pageSize = 100;
                $http({
                    method : 'GET',
                    url : 'GetUserImages'
                }).success(function(data, status, headers, config) {
                //alert(JSON.stringify(data));
                  $scope.datalistimages = data;
//                $scope.numberOfPages = function() {
//                return Math.ceil($scope.datalistimages.length / $scope.pageSize);
//                };
//                if (data === error){
//                    alert(data);
//                }
        }).error(function(data, status, headers, config) {
        alert("No data available, problem fetching the data");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
        });
        };
};
