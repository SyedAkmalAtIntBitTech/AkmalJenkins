/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var global_host_address = "http://clients.brndbot.com/BrndBot/";
//var global_host_address = "http://development.brndbot.com/BrndBot/";
//var global_host_address = "http://development2.brndbot.com/BrndBot/";
//var global_host_address = "http://dev1.intbittech.com/BrndBot/";
var global_host_address = "http://localhost:8080/BrndBot/";
var factoryApp =  angular.module('factorys', []);
var brndBotSignupApp = angular.module('signupApp', ['factorys','ngRoute']);
var socialFlowApp = angular.module('socialFlowApp', ['factorys','ngRoute']);
var emailFlowApp = angular.module('emailFlowApp', ['factorys','ngRoute']);
var marketingFlowApp = angular.module('marketingFlowApp', ['factorys','ngRoute']);
var yourPlanFlowApp = angular.module('yourPlanFlowApp', ['factorys','ngRoute']);
var dashboardFlowApp = angular.module('dashboardFlowApp', ['factorys','ngRoute']);
var settingFlowApp = angular.module('settingFlowApp', ['factorys','ngRoute']);
var userGalleryApp = angular.module('userGalleryApp', ['factorys','ngRoute']);

factoryApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
var error = "system failure error";  
var FroalaLicenseKey ="snJ-7c1krD-13fD1wzF-7==";
function getHost(){
     return global_host_address;
}

function debugAlert(message){
    alert(message);
}

function kUserPreference_HasLoggedInFacebook (){
    return "facebook";
}

function getemail(){
    return "Email";
}

function getfacebook(){
    return "Facebook";
}

function gettwitter(){
    return "Twitter";
}

function getnote(){
    return "Reminder";
}

String.prototype.contains = function(it) { 
    return this.indexOf(it) !== -1; 
};
