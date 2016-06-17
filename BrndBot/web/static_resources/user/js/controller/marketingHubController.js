marketinghubFlowApp.controller("marketingHubController", ['$scope', '$location' ,'settingsFactory', function ($scope, $location,settingsFactory) {


$scope.getemailsettings = function () {
    settingsFactory.getEmailSettingsGet().then(function (data){
        alert("...");
        alert(JSON.stringify(data));
        

});

};

$scope.saveemailsettings = function () {
    alert("set");
        settingsFactory.saveEmailSettingsPost().then(function(data){
        alert(JSON.stringify(data));    
        });
    };


        
            }]);