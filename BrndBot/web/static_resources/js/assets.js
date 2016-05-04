/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
  

    
               
        var app = angular.module('colors', []);

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
                            url : getHost()+'/getAllColorThemes'
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
                            url : getHost()+'/updateColorTheme',
                            contentType: "application/json",
                            data: JSON.stringify(globalColorValues)
                        }).success(function(data, status, headers, config) {    
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/assets', "_self"); 
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
                            url : getHost()+'/saveColorTheme',
                            contentType: "application/json",
                            data: JSON.stringify(globalColorValues)
                        }).success(function(data, status, headers, config) {    
                            
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/assets', "_self"); 
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
                        url : getHost()+'/getAllFonts'
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
                   
                    var fileNameSplit=fileName.split('\\').pop();
                    var fontTypeData = fileName.split(".").pop().toLowerCase();
                   
                   if (fontName=="")
                   {
                       
                    alert(fontContentName);
                    $("#fontName").focus();
                    return false;
                   }
                
                   if (fontFamilyName=="")
                   {
                       
                    alert(fontFamily);
                    $("#fontFamilyName").focus();
                    return false;
                   }
                      var fileType = fileName.split(".")[1];
                     
                    
//                       if((fileType !="ttf")) {
//                     alert("Please Select only TTF file!");
//                     $("#fileName").focus();
//                     return false;
//                       }
                     
                    
                      
                       var fontDataObject = getFontData();
                     
                    var globalFonts = {"fontName":fontName,"fontFamilyName":fontFamilyName,"fontType":fontTypeData,"fileName":fileNameSplit,"fontData": fontDataObject.base64ImgString}      
                    $http({
                    method : 'POST',
                    url : getHost()+'/saveFont',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(globalFonts)
                        }).success(function(data, status, headers, config) { 
                           
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/globalfonts', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
                    };
                    
                    
                    
                  $scope.updateFont = function () {
               
                  var editFontName= $("#editFontName").val();
                  var editFontFamilyName= $("#editFontFamilyName").val();
                  var globalFontsId= $("#globalFontsId").val();
                  var uploadFileName= $("#uploadFileName").val();
                  if(uploadFileName=="")
                  {
                   var ext = $('#uploadFileName').val().split('.').pop().toLowerCase();
                    if($.inArray(ext, ['ttf']) == -1) {
                    alert(uploadTTF);
                    return false;
                     }
                 }
                  
                  var updateGlobalFont = {"globalFontsId":globalFontsId,"fontName":editFontName,"fontFamilyName":editFontFamilyName,"fileName":uploadFileName}
                    $http({
                            method : 'POST',
                            url : getHost()+'/updateFont',
                            contentType: "application/json",
                            data: JSON.stringify(updateGlobalFont)
                        }).success(function(data, status, headers, config) {    
                           
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/globalfonts', "_self"); 
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
                       url : getHost()+'/deleteFont?globalFontsId='+globalFontsId
                   }).success(function(data, status, headers, config) {

                       $scope.fontDisplay = data.d.details;
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'admin/globalfonts', "_self");
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
                       url : getHost()+'/deleteColorTheme?globalColorsId='+globalColorsId
                   }).success(function(data, status, headers, config) {

                       $scope.allColors = data.d.details;
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'admin/assets', "_self");
                   }).error(function(data, status, headers, config) {
                           alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                   });     
               }
           }

           });

function fontConverter(id) {
    var obj =  document.getElementById(id);
    obj.addEventListener("change", readFile, false);
}

function readFile() {
    if (this.files.length === 1) {
        var reader = new FileReader();
        var file = this.files[0];
        var fileName = file.name;     
        reader.addEventListener("load", function () {
            var data = reader.result;
          base64ImgString = data;
          
        }, false);
        reader.readAsDataURL(file);
    }

}

function getFontData(){
 
    return {
        "fileName" : fileName,
        "base64ImgString" : base64ImgString,
       
    };
}
