/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

var factoryApp =  angular.module('factorys', []);
var brndBotSignupApp = angular.module('signupApp', ['factorys','ngRoute','ngDropdowns']);
var socialFlowApp = angular.module('socialFlowApp', ['factorys','ngRoute','ngDropdowns']);
var emailFlowApp = angular.module('emailFlowApp', ['factorys','ngRoute','sharedService','ngDropdowns']);
var marketingFlowApp = angular.module('marketingFlowApp', ['factorys','ngRoute','ngDropdowns']);
var yourPlanFlowApp = angular.module('yourPlanFlowApp', ['factorys','ngRoute','ngDropdowns']);
var dashboardFlowApp = angular.module('dashboardFlowApp', ['factorys','ngRoute','sharedService']);
var settingFlowApp = angular.module('settingFlowApp', ['factorys','ngRoute','ngDropdowns']);
var userGalleryApp = angular.module('userGalleryApp', ['factorys','ngRoute']);
var marketinghubFlowApp = angular.module('marketinghubFlowApp', ['factorys','ngRoute']);
var socialhubFlowApp = angular.module('socialhubFlowApp', ['factorys','ngRoute']);
var sharedServiceApp = angular.module('sharedService', []);
var imagesFlowApp = angular.module('imagesFlowApp', ['factorys','ngRoute']);
var franchiseHubApp = angular.module('franchiseHubApp', ['factorys']);

factoryApp.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
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
                    scope.selected = 'color-swab';
                }
            });
        }
    };
    return directiveDefinitionObject;
});
factoryApp.directive('getfileName', function () {
    return {
        link: function (scope, element, attrs) {
            element.on('change', function  (evt) {
                var files = evt.target.files;
                $("#filetext").empty().text(files[0].name);              
            });
        }
    };
});
