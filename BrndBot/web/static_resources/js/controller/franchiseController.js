
franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', 'franchiseFactory', function ($scope, $window, $location, franchiseFactory) {
                                                                                                                                                                   
        $scope.getAllFranchises = function () {
            franchiseFactory.getAllFranchises().then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
         $scope.getFranchisesForCompanyId = function (companyId) {
            franchiseFactory.getFranchisesForCompanyId(companyId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.activateCompanyAsFranchise = function (companyId, franchiseId) {
            franchiseFactory.activateCompanyAsFranchise(companyId, franchiseId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.getCompaniesForFranchiseId = function (franchiseId) {
            franchiseFactory.getCompaniesForFranchiseId(franchiseId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.associateCompanyToFranchise = function (companyId, franchiseId) {
            franchiseFactory.associateCompanyToFranchise(companyId, franchiseId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.removeCompanyFromFranchise = function (companyId, franchiseId) {
            franchiseFactory.removeCompanyFromFranchise(companyId, franchiseId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.saveFranchise = function () {
            franchiseFactory.saveFranchise('franchiseName').then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.updateFranchise = function () {
            franchiseFactory.updateFranchise('13','new name').then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
        $scope.deleteFranchise = function (franchiseId) {
            franchiseFactory.deleteFranchise(franchiseId).then(function (data){
                   alert("Response:"+JSON.stringify(data));
            });
        };
        
       
        $scope.isActive = function (viewLocation) { 
            return viewLocation === $location.path();
        };
    
        $scope.viewPushedEmailDetails = function ()
        {
            $scope.showPushedEmailDetails = true;
            $location.path("/pushedemaildetails");
        };
    }]);
