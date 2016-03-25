/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
  

    
               
        var app = angular.module('colors', []);
     
app.directive('uiColorpicker', function() {
   
    return {
        restrict: 'E',
        require: 'ngModel',
        scope: false,
        replace: true,
        template: "<span><input class='input-small' /></span>",
        link: function(scope, element, attrs, ngModel) {
            
            var input = element.find('input');
            var options = angular.extend({
                color: ngModel.$viewValue,
                change: function(color) {
                    scope.$apply(function() {
                      ngModel.$setViewValue(color.toHexString());
                    });
                }
            }, scope.$eval(attrs.options));
            
            ngModel.$render = function() {
                
              input.spectrum('set', ngModel.$viewValue || '');
            };
            
            input.spectrum(options);
        }
    };
});

app.controller('globalColors', function($scope,$http) {
    
    $scope.openColorPalettePopup = function (colorName,colorCodeOne,colorCodeTwo,colorCodeThree,colorCodeFour,globalColorsId){
       
        $("#colorNameInput").val(colorName);
        $("#addOrganizationPopupDiv").show();
        $("#createColorCode").show();
        $("#firstValue").val(colorCodeOne);
        $("#secondValue").val(colorCodeTwo);
        $("#thirdValue").val(colorCodeThree);
        $("#fourthValue").val(colorCodeFour);
        $("#globalColorsId").val(globalColorsId);
        $scope.targetColorFirst = colorCodeOne;
        $scope.targetColorSecond = colorCodeTwo;
        $scope.targetColorThird= colorCodeThree;
        $scope.targetColorFourth = colorCodeFour;
    }
   
    $scope.displayGlobalcolors =function(){
        $http({
                            method : 'GET',
                            url : getHost()+'/getAllColorThemes.do'
                        }).success(function(data, status, headers, config) {
                        $scope.colorname = data.d.details;  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });     
        
    };
    
    
      $scope.updateGlobalColors = function () {
        
                    var colorNameInput= $("#colorNameInput").val();
                    var firstValue= $("#firstValue").val();
                    var secondValue= $("#secondValue").val();
                    var thirdValue= $("#thirdValue").val();
                    var fourthValue= $("#fourthValue").val();
                    var globalColorsId =$("#globalColorsId").val();
                    var globalColorValues = {"globalColorsId":globalColorsId,"colorName":colorNameInput,"color1": firstValue,
                                           "color2":secondValue,"color3":thirdValue,"color4":fourthValue};
                    $http({
                            method : 'POST',
                            url : getHost()+'/updateColorTheme.do',
                            contentType: "application/json",
                            data: JSON.stringify(globalColorValues)
                        }).success(function(data, status, headers, config) {    
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/assets.jsp', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
    };
    
    $scope.addGlobalColors = function () {
        
                    var colorNameInput= $("#colorNameInput").val();
                    var firstValue= $("#firstValue").val();
                    var secondValue= $("#secondValue").val();
                    var thirdValue= $("#thirdValue").val();
                    var fourthValue= $("#fourthValue").val();
                    var globalColorValues = {"colorName":colorNameInput,"color1": firstValue,
                                           "color2":secondValue,"color3":thirdValue,"color4":fourthValue};
                    $http({
                            method : 'POST',
                            url : getHost()+'/saveColorTheme.do',
                            contentType: "application/json",
                            data: JSON.stringify(globalColorValues)
                        }).success(function(data, status, headers, config) {    
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/assets.jsp', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
    };
    
});

