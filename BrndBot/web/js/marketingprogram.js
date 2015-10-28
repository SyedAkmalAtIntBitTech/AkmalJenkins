/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function controllerMarketingCategories($scope, $http, $window){
            
    $scope.getMarketingCategories = function(){
        $http({
            method: 'GET',
            url: 'allmarketingCategory.do'
        }).success(function (data, status, headers, config) {
            //alert(JSON.stringify(data.marketingData[0].name));
            $scope.categories = data.marketingData;
            if (data === error) {
                alert(data);
            }
        }).error(function (data, status, headers, config) {
            alert("No data available, problem fetching the data");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });                
    };

    $scope.showMarketingPrograms = function(category_id){
        var marketing_path = getHost() + 'marketingprograms.jsp?categoryid='+category_id;
        $window.location.href(marketing_path);
    };
    
    $scope.getMarketingPrograms = function(){
        $http({
            method: 'GET',
            url: 'GetMarketingPrograms'
        }).success(function (data, status, headers, config) {
            $scope.programs = data;
            if (data === error) {
                alert(data);
            }
        }).error(function (data, status, headers, config) {
            alert("No data available, problem fetching the data");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });  
    };
    
    $scope.showhtmldata = function(html_data){
        $("#html_data").empty();
        $("#html_data").append(html_data);
    };
}