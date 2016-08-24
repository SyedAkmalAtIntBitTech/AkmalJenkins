
franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', 'franchiseFactory', function ($scope, $window, $location, franchiseFactory) {

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
                  growl(eval(JSON.stringify(data.d.operationStatus.messages)));
              });

          };

          $scope.showEditFranchisePopup = function(franchiseId){
              $scope.editFranchisePopup = true;
              $scope.editFranchisePopupDiv = true;
              $scope.franchiseId = franchiseId;
          };
    
        $scope.getAllFranchises = function () {
            franchiseFactory.getAllFranchises().then(function (data){
                $scope.franchiseDetails = data.d.details;
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
