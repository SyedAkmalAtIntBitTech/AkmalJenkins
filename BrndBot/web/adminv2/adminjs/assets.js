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
   
   
    $scope.addColorTheme = function (){
       
     $("#addcolorpalette").show();
     $("#addOrganizationPopupDiv").show();
     
    
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
      
                    var colorName= $("#colorName").val();
                    var firstColor= $("#firstColor").val();
                    var secondColor= $("#secondColor").val();
                    var thirdColor= $("#thirdColor").val();
                    var fourthColor= $("#fourthColor").val();
                    if(colorName==""){ 
                    alert(enterColorName);
                    $("#colorName").focus();
                    return NULL;  
                    }
                    if(firstColor==""){ 
                    alert(enterColorCode);
                    $("#firstColor").focus();
                    return NULL;  
                    }
                    if(secondColor==""){ 
                    alert(enterColorCode);
                    $("#secondColor").focus();
                    return NULL;  
                    }
                    if(thirdColor==""){ 
                    alert(enterColorCode);
                    $("#thirdColor").focus();
                    return NULL;  
                    }
                    if(fourthColor==""){ 
                    alert(enterColorCode);
                    $("#fourthColor").focus();
                    return NULL;  
                    }
                    var globalColorValues = {"colorName":colorName,"color1": firstColor,
                                           "color2":secondColor,"color3":thirdColor,"color4":fourthColor};
                                      
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
    
    
                    $scope.addFontContent = function (){
                     $("#fontPopup").show();
                     $("#addOrganizationPopupDiv").show();  
                     $("#editFont").hide();
                     $("#addFont").show();
                     $("#nameFont").show();
                      $("#editFonts").hide();
                      $("#fontFamilyCssName").show();
                      $("#editFontFamilyCssName").hide();
                      $("#uploadOnEdit").show();
                      $("#uploadTTF").hide();
                      $("#createFont").show();
                      $("#editFontDetails").hide();
                      $("#deleteGlobalFont").hide();
                    }

                    $scope.updateFontContent = function (fontName,fontFamilyName,globalFontsId,fileName){
                       
                     $("#globalFontsId").val(globalFontsId);
                     $("#editFontName").val(fontName);   
                     $("#editFontFamilyName").val(fontFamilyName);    
                     $("#fontPopup").show();
                     $("#addOrganizationPopupDiv").show();  
                     $("#editFont").show();
                     $("#addFont").hide();
                    $("#nameFont").hide();
                    $("#editFonts").show();
                    $("#fontFamilyCssName").hide();
                     $("#editFontFamilyCssName").show();
                     $("#uploadOnEdit").hide();
                      $("#uploadTTF").show();
                      $("#createFont").hide();
                      $("#editFontDetails").show();
                      $("#deleteGlobalFont").show();
                    }
                    $scope.getGlobalFonts =function(){
                        $http({
                        method : 'GET',
                        url : getHost()+'/getAllFonts.do'
                    }).success(function(data, status, headers, config) {
                    $scope.fontNames = data.d.details;  
                    }).error(function(data, status, headers, config) {
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    });     

                    };
    
    
                   $scope.createFont = function () {
             
                    var fontName= $("#fontName").val();
                    var fontFamilyName= $("#fontFamilyName").val();
                    var fileName= $("#fileName").val();
                   if (fontName=="")
                   {
                       
                    alert(fontContentName);
                    $("#fontName").focus();
                    return false;
                   }
                  if(fontFamilyName=="")
                   {
                       alert(fontFamily);
                        $("#fontFamilyName").focus();
                         return false;
                   }
                  if(fileName=="")
                   {
                       alert(fontFileName);
                         $("#fileName").focus();
                          return false;
                   }
                    var globalFonts = {"fontName":fontName,"fontFamilyName":fontFamilyName,"fileName": fileName}
                                           
                    $http({
                    method : 'POST',
                    url : getHost()+'/saveFont.do',
                    contentType: "application/json",
                    data: JSON.stringify(globalFonts)
                        }).success(function(data, status, headers, config) {    
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/globalfonts.jsp', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
                    };
                    
                    
                    
                  $scope.updateFont = function () {
               
                  var editFontName= $("#editFontName").val();
                  var editFontFamilyName= $("#editFontFamilyName").val();
                  var globalFontsId= $("#globalFontsId").val();
                  var uploadFileName= $("#uploadFileName").val();
                  var updateGlobalFont = {"globalFontsId":globalFontsId,"fontName":editFontName,"fontFamilyName":editFontFamilyName,"fileName":uploadFileName}
                    $http({
                            method : 'POST',
                            url : getHost()+'/updateFont.do',
                            contentType: "application/json",
                            data: JSON.stringify(updateGlobalFont)
                        }).success(function(data, status, headers, config) {    
                           
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/globalfonts.jsp', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
                  };

                $scope.deleteFont=function (globalFontsId){
               var deleteGlobalFont=confirm(deleteFont);
               var globalFontsId =$("#globalFontsId").val();
               if(deleteGlobalFont===true)
               {
                  $http({
                       method : 'GET',
                       url : getHost()+'/deleteFont.do?globalFontsId='+globalFontsId
                   }).success(function(data, status, headers, config) {

                       $scope.fontDisplay = data.d.details;
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'adminv2/globalfonts.jsp', "_self");
                   }).error(function(data, status, headers, config) {
                           alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                   });     
               }
           }
           
           
           $scope.deleteColor=function (globalFontsId){
               var deleteGlobalColor=confirm(deleteColorPalette);
               var globalColorsId =$("#globalColorsId").val();
               if(deleteGlobalColor===true)
               {
                  $http({
                       method : 'GET',
                       url : getHost()+'/deleteColorTheme.do?globalColorsId='+globalColorsId
                   }).success(function(data, status, headers, config) {

                       $scope.allColors = data.d.details;
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'adminv2/assets.jsp', "_self");
                   }).error(function(data, status, headers, config) {
                           alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                   });     
               }
           }

           });

