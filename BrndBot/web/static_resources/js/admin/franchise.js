/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
function franchiseController($scope, $http) {
    $scope.addFranchisePopup = false;
    $scope.addFranchisePopupDiv = false;
    $scope.editFranchisePopup = false;
    $scope.editFranchisePopupDiv = false;
    $scope.franchiseId = "";    
    $scope.addCompanyPopup = false;
    
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
    $scope.getFranchiseHeadquarter();
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

    $scope.showEditFranchisePopup = function(franchiseId){
        $scope.editFranchisePopup = true;
        $scope.editFranchisePopupDiv = true;
        $scope.franchiseId = franchiseId;
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

    $scope.editFranchise = function () {

        var franchiseName = $("#editfranchiseName").val();
        var franchiseId = $scope.franchiseId;
        if (franchiseName === "") {

            alert(enterFranchiseName);
            $("#editfranchiseName").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + 'updateFranchise?franchiseId='+franchiseId,
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
    
    $scope.deleteFranchise = function(franchiseId){
        $.ajax({
            method: 'DELETE',
            url: getHost() + 'deleteFranchise?franchiseId='+franchiseId,
        }).success(function (data, status, headers, config)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
            window.open(getHost() + 'admin/franchise', "_self");
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });        
    };
    
    $scope.getCompaniesForFranchiseId = function(){
        var franchiseId = $scope.franchiseId;
            $http({
                method: 'GET',
                url: getHost() + 'getCompaniesForFranchiseId?franchiseId='+franchiseId
            }).success(function (data, status, headers, config)
            {
                $scope.franchiseCompanies = data.d.details;
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        
    };
    
    $scope.getFranchiseHeadquarter = function(){
        var franchiseId = $scope.franchiseId;
        $http({
            method: 'GET',
            url: getHost() + 'getFranchiseHeadquarter?franchiseId='+franchiseId
        }).success(function (data, status, headers, config)
        {   
            $scope.headquarterCompany = data.d.message;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };
    
    $scope.getAllNonSelectedCompanies = function(){
        var franchiseId = $scope.franchiseId;
            $.ajax({
                method: 'GET',
                url: getHost() + 'getAllNonSelectedCompanies?franchiseId='+franchiseId
            }).success(function (data, status, headers, config)
            {
                $scope.nonSelectedcompanies = data.d.details;
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
    };
    
    $scope.associateCompanyToFranchise = function(){
        var franchiseId = $scope.franchiseId;
        var companyId = $("#company option:selected").val();
            $.ajax({
                method: 'GET',
                url: getHost() + 'associateCompanyToFranchise?franchiseId='+franchiseId+'&companyId='+companyId
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/franchiseCompanies?franchiseId='+franchiseId, "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
    };
    
    $scope.activateCompanyAsHeadquarter = function(companyId){
        var r = confirm("Do you want to make this company headquarter!");
        var franchiseId = $scope.franchiseId;
        if (r == true) {
            $.ajax({
                method: 'GET',
                url: getHost() + 'activateCompanyAsFranchise?franchiseId='+franchiseId+'&companyId='+companyId
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/franchiseCompanies?franchiseId='+franchiseId, "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }

    };

    $scope.removeCompanyFromFranchise = function(companyId){
        var franchiseId = $scope.franchiseId;
            $.ajax({
                method: 'DELETE',
                url: getHost() + 'removeCompanyFromFranchise?franchiseId='+franchiseId+'&companyId='+companyId
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/franchiseCompanies?franchiseId='+franchiseId, "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
    };
    
}

