/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
function franchiseController($scope, $http) {
    $scope.addFranchisePopup = false;
    $scope.addFranchisePopupDiv = false;
    $scope.franchiseId = "";    
    
  $scope.getFranchiseId = function(){
    var qs = (function(a) {
        if (a == "") return {};
        var b = {};
        for (var i = 0; i < a.length; ++i)
        {
            var p=a[i].split('=', 2);
            if (p.length == 1)
                b[p[0]] = "";
            else
                b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
        }
        return b;
    })(window.location.search.substr(1).split('&'));
    
    $scope.franchiseId = qs["franchiseId"];    
    
  };

    $scope.franchise = function () {

        $http({
            method: 'GET',
            url: getHost() + 'getAllFranchises'
        }).success(function (data, status, headers, config) {
            $scope.franchiseDetails = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };

    $scope.addFranchise = function () {

        var franchiseName = $("#franchiseName").val();
        if (franchiseName === "") {

            alert(enterFranchiseName);
            $("#franchiseName").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + 'saveFranchise',
                dataType: "json",
                contentType: "application/json",
                data: franchiseName.toString()
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                window.open(getHost() + 'admin/franchise', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };    
    
    $scope.getCompaniesForFranchiseId = function(){
        var franchiseId = $scope.franchiseId;
            $.ajax({
                method: 'GET',
                url: getHost() + 'getCompaniesForFranchiseId?franchiseId='+franchiseId
            }).success(function (data, status, headers, config)
            {
                $scope.companies = data.d.details;
//                window.open(getHost() + 'admin/franchise', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        
    }
}

