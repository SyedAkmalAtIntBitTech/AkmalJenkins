<%-- 
    Document   : paletteChooser
    Created on : Jun 10, 2015, 2:57:42 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Choose color palette</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>
        <script src="js/prettify.js"></script>
        <script src="js/jquery.bsFormAlerts.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/configurations.js"></script>
        <script type="text/javascript" src="jscolor/jscolor.js"></script>
        <script>

            angular.module("myapp", [])
                .controller("MyController", function($scope,$http) {
                        $scope.themes = [];
                    $http({
                                        method : 'GET',
                                        url : 'getColorPalettes'
                                }).success(function(data, status, headers, config) {

                                        $scope.themes  = data;
                                }).error(function(data, status, headers, config) {
                                        alert("No data available, problem fetching the data");
                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.
                                });
                        });
                
        </script>
        
    </head>
    <body ng-app="myapp">
        
        <div class="container">
             <div class="row">
                <div class="span1">
                    <a href="personality.html">go back</a>
                    
                </div>
             </div>
                 
            <div id="contentdiv" class="row">   
            
                 <div class="col-md-4 col-md-offset-3">
                        <p id="comment1"> Choose a palette </p> 
                 </div>
                 <div class="col-md-7 col-md-offset-3">
                  <div class="tabbable tabs-top">
                      <ul id="btn" class="nav nav-tabs" >
                          
                          <li id="li1" class="active"><a id="a1" href="#picktheme" data-toggle="tab">PICK FROM A THEME</a></li>
                          <li id="li2" class=""><a id="a1" href="#custom" data-toggle="tab">CHOOSE CUSTOM</a></li>
                          <li id="li3" class=""><a id="a1" href="#logocolor" data-toggle="tab">PICK FROM LOGO COLORS</a></li>

                      </ul> 
                      <div class="tab-content">
                          <div ng-controller="MyController" class="tab-pane active" id="picktheme">
                                  
                                <div ng-repeat= "col1 in themes" >
                                    <div ng-repeat="theme1 in col1">
                                        <div class="foo" style="background-color:{{theme1.colorHex}};"></div>
                                    </div> 
                                        <div class="foo" style="background-color:#ae163e;"></div><p>USE THEME</p><br>
                                </div>
                                <form class="form-horizontal" action="#" method="post">

                                     <div class="form-group">

                                      <div class="col-md-4 col-md-offset-0 ">
                                          <input class="btn btn-info" type="button" value="CONTINUE"><br><br>
                                      </div>
                                  </div> 
                                </form>
                            </div>
                          
                          <div class="tab-pane" id="custom">
                              
                                
                              <p></p>
                                 <br> <p id="theme1">Theme1</p>
                                 <form class="form-horizontal">

                                   <div class="form-group">
                                       
                                       <div class="col-md-4 col-md-offset-0">
                                            <input id="themename" type="text" class="form-control" placeholder="Theme Name"><br>
                                            <input type="text" id="hex1" value="" name="hexvalue1" style="width:120px;" />
                                            <input type="text" id="hex2" value="" name="hexvalue2" style="width:120px;" />
                                            <input type="text" id="hex3" value="" name="hexvalue3" style="width:120px;" />
                                            <input type="text" id="hex4" value="" name="hexvalue4" style="width:120px;" />
                                            <input type="text" id="hex5" value="" name="hexvalue5" style="width:120px;" />
                                            <input type="text" id="hex6" value="" name="hexvalue6" style="width:120px;" />
                                       </div>
                                   </div>
                                     
                                  <div class="color-box1" style="background-color:  "></div>
                                  <div class="color-box2" style=""></div>
                                  <div class="color-box3" style=""></div>
                                  <div class="color-box4" style=""></div>
                                  <div class="color-box5" style=""></div>
                                  <div class="color-box6" style=""></div><br><br>
                                     
                                  <div class="form-group">
                                       
                                       <div class="col-md-4 col-md-offset-0">
                                           <br><br> <input class="btn btn-info" type="button" value="SAVE"><br><br>
                                           
                                       </div>
                                  </div>
                                 </form>
                              
                          </div>
                          
                          <div class="tab-pane" id="logocolor">
                              
                              <p>from logo color</p>
                          </div>
                          
                          
                      </div>
                    
                    </div>
                 </div>
                </div>
            
            </div>
<script type="text/javascript">
           $('.color-box1').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex1").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
           $('.color-box2').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex2").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
           $('.color-box3').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex3").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
           $('.color-box4').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex4").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
           $('.color-box5').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex5").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
           $('.color-box6').colpick({
                            colorScheme:'dark',
                            layout:'rgbhex',
                            color:'ff8800',
                            
                            onSubmit:function(hsb,hex,rgb,el) {
                                    $(el).css('background-color', '#'+hex);
                                   //for taking hex value
//                                     $(el).wrapInner('<input type=hidden name=name value='+hex+'/>')  ;
                                    $("#hex6").val(hex);
                                    $(el).colpickHide();
                                    
                            }
                    })
                    .css('background-color', '#ff0000');
            
            $( "#themename" )
            .keyup(function() {
            var value = $( this ).val();
            $( "#theme1" ).text( value );
            })
            .keypress();

            $(document).ready(function(){
                $("#li1").click(function(){
                    $("a1").css("font-size","25px");
                });
                
                $("#li2").click(function(){
                    $("a2").css("font-size","25px");
                });
                $("#li3").click(function(){
                    $("a3").css("font-size","25px");
                });
                
            });


        </script>
    </body>
</html>
