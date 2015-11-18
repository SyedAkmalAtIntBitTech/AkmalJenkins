/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function controllerMarketingCategories($scope, $http, $window){
    $scope.getMarketingCategories = function(){
        $http({
            method: 'GET',
            url: 'displaymarketingCategory.do'
        }).success(function (data, status, headers, config) {
           // alert(JSON.stringify(data.marketingData[0].name));
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

    $scope.showMarketingPrograms = function(){
        
        var marketing_path = getHost() + 'marketingprograms.jsp?categoryid='+category_id;
        window.location.replace(marketing_path);
    };
    
    $scope.getMarketingPrograms = function(){
        $http({
            method: 'GET',
            url: 'displaymarketingProgram.do?marketingCategoryId='+$("#categoryidHidden").val()
        }).success(function (data, status, headers, config) {
            $scope.programs = data.marketingProgramsData;
            if (data === error) {
                alert(data);
            }
        }).error(function (data, status, headers, config) {
            alert("No data available, problem fetching the data");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });  
    };
    
    $scope.showhtmldata = function(id,html_data){
                $(".prognamelst").css("background-color", "#fff").css("color","#3f3f42");
                $("#"+id).css("background-color", "#5cc1a3").css("color","#f6f7f7");
            
            $(".htmldatacontainer").css("overflow", "scroll");
            $("#html_data").empty();
        $("#html_data").append(html_data);
        $("#programidHidden").val(id);
       $(".newclientpromo").css("transform","scale(0.57)");
    };
    $scope.submitclick = function(){
        if($("#programidHidden").val()!="0")
        {
        var path1 = getHost() + 'usermarketingprogram.jsp?categoryid='+$("#categoryidHidden").val()+'&programid='+$("#programidHidden").val();
        window.location.replace(path1);
        }
        else
        {
            alert("Please select a marketing program type");
        }
    
        
    };
}