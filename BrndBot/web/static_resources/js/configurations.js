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
var emailFlowApp = angular.module('emailFlowApp', ['factorys','ngRoute','sharedService']);
var marketingFlowApp = angular.module('marketingFlowApp', ['factorys','ngRoute']);
var yourPlanFlowApp = angular.module('yourPlanFlowApp', ['factorys','ngRoute']);
var dashboardFlowApp = angular.module('dashboardFlowApp', ['factorys','ngRoute','sharedService']);
var settingFlowApp = angular.module('settingFlowApp', ['factorys','ngRoute']);
var userGalleryApp = angular.module('userGalleryApp', ['factorys','ngRoute']);
var error = "system failure error";  
var FroalaLicenseKey ="snJ-7c1krD-13fD1wzF-7==";
var sharedServiceApp = angular.module('sharedService', []);
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
factoryApp.directive('toggleClass', function () {
    var directiveDefinitionObject = {
        restrict: 'A',
        template: '<div ng-click="localFunction()" ng-class="selected"  ng-transclude></div>',
        replace: true,
        scope: {
            model: '='
        },
        transclude: true,
        link: function (scope, element, attrs) {
            scope.localFunction = function () {
                scope.model.value = scope.$id;
            };
            scope.$watch('model.value', function () {
                if (scope.model.value === scope.$id) {
                    scope.selected = "palette-colorswab-selected fleft";
                } else {
                    scope.selected = 'palette-colorswab fleft';
                }
            });
        }
    };
    return directiveDefinitionObject;
});


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
