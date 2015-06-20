<%-- 
    Document   : paletteChooser
    Created on : Jun 10, 2015, 2:57:42 PM
    Author     : intbit
--%>

<%@page import="com.controller.sqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Choose color palette</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
       <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script src="js/prettify.js"></script>
        <script src="js/jquery.bsFormAlerts.js"></script>
        <script type="text/javascript" src="jscolor/jscolor.js"></script>
        
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        
        <style>
                #sortable { list-style-type: none; margin: 0; padding: 0;}
                #sortable  { margin: 3px; padding: 0.4em; font-size: 5px; height: 120px; }
        </style>
        

        <script>

            angular.module("myapp", [])
                .controller("controllerGetColourPalettes", function($scope,$http) {
                        $scope.themes = [];
                    $http({
                                        method : 'GET',
                                        url : 'getColorPalettes'
                                }).success(function(data, status, headers, config) {
                                        $scope.colors  = data;
                                }).error(function(data, status, headers, config) {
                                        alert("No data available, problem fetching the data");
                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.
                                });
                        });
                	function controllerGetColoursFromLogo($scope, $http) {

                                    $scope.getLogoColors = function() {
                                                $http({
                                                        method : 'GET',
                                                        url : 'getColorsFromLogo'
                                                }).success(function(data, status, headers, config) {
                                                        $scope.color = data.Colors;
                                                }).error(function(data, status, headers, config) {
                                                        alert("No data available, problem fetching the data");
                                                        // called asynchronously if an error occurs
                                                        // or server returns response with an error status.
                                                });

		}; 
	}
        
                    function controllerCreateUserPreferences($scope, $http){
                          $scope.createUserPreferences = function() 
                          {
                                var color1 = $("#finalcolor1").val();
                                var color2 = $("#finalcolor2").val();
                                var color3 = $("#finalcolor3").val();
                                var color4 = $("#finalcolor4").val();
                                var color5 = $("#finalcolor5").val();
                                var color6 = $("#finalcolor6").val();   
                           if(color1 == ""  || color2== "" || color3== "" || color4== "" || color5 == "" || color6 == ""){
                               alert("Please fill all six colors, click MOST USED to select colors");
                           }
                           else{
                            var colorObject = {"finalcolor1":color1,"finalcolor2":color2,"finalcolor3":color3,"finalcolor4":color4,"finalcolor5":color5,"finalcolor6":color6};
                            
                            $http({
                              method: 'POST',
                              url: getHost() +'setUserPreferences',
                              headers: {'Content-Type': 'application/json'},
                              data:  colorObject
                            }).success(function (data) 
                              {
                                $scope.status=data;
                                window.open(getHost() +'dashboard.jsp',"_self");
                              })
                                .error(function(data, status) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.

                                alert("request not succesful");
                                console.log('request not succesful');
                              });
                            };
                        }
                        
                    };

        </script>

        </script>
                          <script>
                               var ElementID;
                         /*------ get selected element ID -----*/
                                function getElementID(IDNo){
                                    ElementID = IDNo;
                                    $('.step_wrapper').on('click','.step_box',function () {   
                                    $(this).parent().find('.step_box').css('width','').css('height','').css('border-color','').css('border-radius','');
                                    $(this).css('width','80px').css('height','40px').css('border-color','#FF0000').css('border-radius','10px');
                               
                                     });
                                    
                                    $( "#sortable" ).sortable();
                                    $( "#sortable" ).disableSelection();

                                    
                                }
                         /*------ pass color into the selected element got by id-----*/
                              function getIDNo(IDNo){
                                    var s=$("#" +IDNo).attr("style");
                                    var s1=s.split(":");
                                    $("#" +ElementID).css("background-color",s1[1]);     
                                }
                                
                          </script>
        

    </head>
    <body ng-app="myapp">
        
        <div class="container">
             <div class="row">
<!--                <div class="span1">
                    <a href="personality.html">go back</a>
                    
                </div>-->
             </div>
                 
            <div id="contentdiv" class="row">   
            
                 <div class="col-md-8 col-md-offset-3">
                        <p id="comment1"> Choose a palette </p> 
                        <p id="comment2">Click on a color to change it or choose from a theme. Don’t worry, you can always change it later.</p>
                        <div class="col-md-12"><p id="test" class="span3" >MOST USED <span class="col-md-offset-5" id="leastuse">LEAST USE</span></p> </div>
                        
                            
                            <div id="sortable" class="step_wrapper">
                                  <div id="elementToPutStyleInto1" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto1')"></div>
                                  <div id="elementToPutStyleInto2" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto2')"></div>
                                  <div id="elementToPutStyleInto3" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto3')"></div>
                                  <div id="elementToPutStyleInto4" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto4')"></div>
                                  <div id="elementToPutStyleInto5" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto5')"></div>   
                                  <div id="elementToPutStyleInto6" class="blankcolor-box step_box" style=""  onclick="getElementID('elementToPutStyleInto6')"></div>
                                  <div class="resetpalette"> <p id="resetpalette">RESET ORIGINAL PALETTE</p></div>
                            </div>
                                  
                           
                        </div>
                
                 <div class="col-md-7 col-md-offset-3 ">
                  <div class="tabbable tabs-top">
                      <br>
                      <ul id="btn" class="nav nav-tabs" >
                          
                          <li id="li1" class="active"><a id="a1" href="#picktheme" data-toggle="tab">PICK FROM A THEME</a></li>
                          <li id="li2" class=""><a id="a1" href="#custom" data-toggle="tab">CHOOSE CUSTOM</a></li>
                          <li id="li3" class=""><a id="a1" href="#logocolor" data-toggle="tab">PICK FROM LOGO COLORS</a></li>

                      </ul> 
                      <div class="tab-content">

                          <br>
                          
                          <div ng-controller="controllerGetColourPalettes" class="tab-pane active" id="picktheme">
                                <div ng-repeat= "col1 in colors" id="rep" >
                                    <div ng-repeat="theme1 in col1" id="rep1">
                                        
                                        <div id="{{theme1.id}}" onclick="getIDNo('{{theme1.id}}')"class="foo col-md-2" style="background-color:{{theme1.colorHex}};"></div>
                                    </div> 
                                    <div></div><p>&nbsp;</p><br>
                                   
                                </div>

                              <div class="col-md-3 pull-right ">
                                <div><p id="p1">USE THEME1</p></div><br>
                                <div><p id="p2">USE THEME2</p></div><br>
                                <div><p id="p3">USE THEME3</p></div><br>
                                <div><p id="p4">USE THEME4</p></div><br>
                              </div>
                            </div>
                          
                          <div class="tab-pane" id="custom">

                                <br>     
                             <div  id="picker"></div><br><br>

                          </div>
                                    
                                    <div ng-controller="controllerGetColoursFromLogo" class="tab-pane" id="logocolor" >
                                      <div  class="tab-pane active" id="picktheme">
                                          <div><button type="button" class="btn btn-primary" value="click to display colors" ng-click="getLogoColors()">click to display colors</button></div>
                                            <div ng-repeat="col in color">
                                                <div  id="{{col.id}}" class="foo" style="background-color:{{col.colorHex}};" onclick="getIDNo('{{col.id}}')"></div>
                                            </div>
                                    </div>
                                </div> 
                      </div><br>
                      <div>
                            <div  class="form-group">
                               <div class="col-sm-offset-0 col-md-4" ng-controller="controllerCreateUserPreferences" >
                                   <form class="form-horizontal">
                                            <input type="hidden" id="finalcolor1" name="finalcolor1" ng-model="selColor.finalcolor1"  />
                                            <input type="hidden" id="finalcolor2" name="finalcolor2" ng-model="selColor.finalcolor2" />
                                            <input type="hidden" id="finalcolor3" name="finalcolor3" ng-model="selColor.finalcolor3"/>
                                            <input type="hidden" id="finalcolor4" name="finalcolor4" ng-model="selColor.finalcolor4"/>
                                            <input type="hidden" id="finalcolor5" name="finalcolor5" ng-model="selColor.finalcolor5"/>
                                            <input type="hidden" id="finalcolor6" name="finalcolor6" ng-model="selColor.finalcolor6"/>

                                            <div class="span4 col-md-offset-0">
                                                <button  type="button" class="btn btn-info" ng-click="createUserPreferences()">CONTINUE</button>
                                            </div>
                                            
                                    </form>
                               </div>
                             </div>

                      </div>
                    
                    </div>
                 </div>
                </div>
            
      </div>

        <script>
//        .................color picker...........
               
        $('#picker').colpick({
            flat:true,
            layout:'hex',
            onSubmit:function(hsb,hex,rgb,el) {                                                  
                          //for haking hex value alert(hex);

                     $('#' +ElementID).css("background-color","#"+hex);

                     }

                 });
                
        // for selecting theme     
          $(document).ready(function(){
                $("#p1").click(function(){
                 var s=$("#color1").attr("style");
                 var s1=s.split(":");
                      
                 var s=$("#color2").attr("style");
                 var s2=s.split(":");
                      
                 var s=$("#color3").attr("style");
                 var s3=s.split(":");
                 
                 var s=$("#color4").attr("style");
                 var s4=s.split(":");
                 
                 var s=$("#color5").attr("style");
                 var s5=s.split(":");
                 
                 var s=$("#color6").attr("style");
                 var s6=s.split(":");
                      
                $("#themeid").val("p1");      
                $("#elementToPutStyleInto1").css("background-color",s1[1]);
                $("#elementToPutStyleInto2").css("background-color",s2[1]);
                $("#elementToPutStyleInto3").css("background-color",s3[1]);
                $("#elementToPutStyleInto4").css("background-color",s4[1]);
                $("#elementToPutStyleInto5").css("background-color",s5[1]);
                $("#elementToPutStyleInto6").css("background-color",s6[1]);

                
                });
            });


            $(document).ready(function(){
                
                $("#p2").click(function(){
                 var s=$("#color6").attr("style");
                 var s1=s.split(":");
                      
                 var s=$("#color7").attr("style");
                 var s2=s.split(":");
                      
                 var s=$("#color8").attr("style");
                 var s3=s.split(":");
                 
                 var s=$("#color9").attr("style");
                 var s4=s.split(":");
                 
                 var s=$("#color10").attr("style");
                 var s5=s.split(":");
                 
                 var s=$("#color11").attr("style");
                 var s6=s.split(":");
                      
                $("#themeid").val("p2");      
                $("#elementToPutStyleInto1").css("background-color",s1[1]);
                $("#elementToPutStyleInto2").css("background-color",s2[1]);
                $("#elementToPutStyleInto3").css("background-color",s3[1]);
                $("#elementToPutStyleInto4").css("background-color",s4[1]);
                $("#elementToPutStyleInto5").css("background-color",s5[1]);
                $("#elementToPutStyleInto6").css("background-color",s6[1]);

                
                });
            });
        
        $(document).ready(function(){
                
                $("#p3").click(function(){
                 var s=$("#color11").attr("style");
                 var s1=s.split(":");
                      
                 var s=$("#color12").attr("style");
                 var s2=s.split(":");
                      
                 var s=$("#color13").attr("style");
                 var s3=s.split(":");
                 
                 var s=$("#color14").attr("style");
                 var s4=s.split(":");
                 
                 var s=$("#color15").attr("style");
                 var s5=s.split(":");
                 
                 var s=$("#color1").attr("style");
                 var s6=s.split(":");
                      
                 $("#themeid").val("p3");      
                $("#elementToPutStyleInto1").css("background-color",s1[1]);
                $("#elementToPutStyleInto2").css("background-color",s2[1]);
                $("#elementToPutStyleInto3").css("background-color",s3[1]);
                $("#elementToPutStyleInto4").css("background-color",s4[1]);
                $("#elementToPutStyleInto5").css("background-color",s5[1]);
                $("#elementToPutStyleInto6").css("background-color",s6[1]);


                
                });
            });

        $(document).ready(function(){
                
                $("#p4").click(function(){
                 var s=$("#color1").attr("style");
                 var s1=s.split(":");
                      
                 var s=$("#color7").attr("style");
                 var s2=s.split(":");
                      
                 var s=$("#color13").attr("style");
                 var s3=s.split(":");
                 
                 var s=$("#color15").attr("style");
                 var s4=s.split(":");
                 
                 var s=$("#color11").attr("style");
                 var s5=s.split(":");
                 
                 var s=$("#color12").attr("style");
                 var s6=s.split(":");
                      
                 $("#themeid").val("p4");      
                $("#elementToPutStyleInto1").css("background-color",s1[1]);
                $("#elementToPutStyleInto2").css("background-color",s2[1]);
                $("#elementToPutStyleInto3").css("background-color",s3[1]);
                $("#elementToPutStyleInto4").css("background-color",s4[1]);
                $("#elementToPutStyleInto5").css("background-color",s5[1]);
                $("#elementToPutStyleInto6").css("background-color",s6[1]);

                
                });
            });


   // pass color rgb values into the form text boxes
         $(document).ready(function(){
                
                $("#test").click(function(){
                 var s=$("#elementToPutStyleInto1").attr("style");
                 var s1=s.split(":");
                      
                 var s=$("#elementToPutStyleInto2").attr("style");
                 var s2=s.split(":");
                 var s=$("#elementToPutStyleInto3").attr("style");
                 var s3=s.split(":");
                 
                 var s=$("#elementToPutStyleInto3").attr("style");
                 var s4=s.split(":");
                 
                 var s=$("#elementToPutStyleInto5").attr("style");
                 var s5=s.split(":");
                 var s=$("#elementToPutStyleInto6").attr("style");
                 var s6=s.split(":");
                 
                 
                 document.getElementById("finalcolor1").value = s1[1].trim();
                 document.getElementById("finalcolor2").value = s2[1].trim();
                 document.getElementById("finalcolor3").value = s3[1].trim();
                 document.getElementById("finalcolor4").value = s4[1].trim();
                 document.getElementById("finalcolor5").value = s5[1].trim();
                 document.getElementById("finalcolor6").value = s6[1].trim();
                
                });
            });  


    //  Reset pslette color   

    $(document).ready(function(){
                
                $("#resetpalette").click(function(){   
              
                $("#elementToPutStyleInto1").css("background-color","#FFFFFF");
                $("#elementToPutStyleInto2").css("background-color","#FFFFFF");
                $("#elementToPutStyleInto3").css("background-color","#FFFFFF");
                $("#elementToPutStyleInto4").css("background-color","#FFFFFF");
                $("#elementToPutStyleInto5").css("background-color","#FFFFFF");
                $("#elementToPutStyleInto6").css("background-color","#FFFFFF");

  
                });
            });

        </script>
        
    </body>
</html>
