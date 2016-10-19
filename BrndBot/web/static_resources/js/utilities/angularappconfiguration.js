/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

var factoryApp =  angular.module('factorys', ['ngAnimate']);
var brndBotSignupApp = angular.module('signupApp', ['factorys','ngRoute','ngDropdowns','ngAnimate']);
var socialFlowApp = angular.module('socialFlowApp', ['factorys','ngRoute','ngDropdowns','ngAnimate']);
var emailFlowApp = angular.module('emailFlowApp', ['factorys','ngRoute','sharedService','ngDropdowns','ngAnimate']);
var marketingFlowApp = angular.module('marketingFlowApp', ['factorys','ngRoute','ngDropdowns','ngAnimate']);
var yourPlanFlowApp = angular.module('yourPlanFlowApp', ['factorys','ngRoute','ngDropdowns','ngAnimate']);
var dashboardFlowApp = angular.module('dashboardFlowApp', ['factorys','ngRoute','sharedService','ngAnimate']);
var settingFlowApp = angular.module('settingFlowApp', ['factorys','ngRoute','ngAnimate']);
var userGalleryApp = angular.module('userGalleryApp', ['factorys','ngRoute','ngAnimate']);
var marketinghubFlowApp = angular.module('marketinghubFlowApp', ['factorys','ngRoute','ngAnimate']);
var socialhubFlowApp = angular.module('socialhubFlowApp', ['factorys','ngRoute','ngAnimate']);
var sharedServiceApp = angular.module('sharedService', []);
var imagesFlowApp = angular.module('imagesFlowApp', ['factorys','ngRoute','ngAnimate']);
var franchiseHubApp = angular.module('franchiseHubApp', ['factorys','ngAnimate']);

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
