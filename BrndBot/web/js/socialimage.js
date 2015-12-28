/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("myapp", [])
        .controller("MyController", function($scope, $http) {
            $scope.showImages = function(){
                $("#popup").hide();
                $("#tabs-1").hide();
                $("#tabs-2").hide();         
                $("#tabs-3").show().css("width", "430px").show("slide", { direction: "right" }, 1000);                                                                                                                                                                                                                                             
                $("#imageGallery").show();
                $scope.curPage = 0;
                $scope.pageSize = 100;
                $http({
                method : 'GET',
                        url : 'GetUserImages'
                }).success(function(data, status, headers, config) {
//                                        alert(JSON.stringify(data));
                $scope.datalistimages = data;
                $scope.numberOfPages = function() {
                return Math.ceil($scope.datalistimages.length / $scope.pageSize);
                };
                if (data === error){
                    alert(data);
                }
        }).error(function(data, status, headers, config) {
        alert("No data available, problem fetching the data");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
        });
    };
});

