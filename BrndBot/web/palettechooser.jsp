<%-- 
    Document   : palettechooser1
    Created on : Jul 6, 2015, 3:49:03 PM
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
            .paginationclass{
    
                margin: 19px 28px;    
                }
                .paginationclass span{
                    margin-left:15px;
                    display:inline-block;
                }
                .pagination-controle li{
                    display: inline-block;
                }
                .pagination-controle button{
                    font-size: 12px;
                    margin-top: -26px;
                    cursor:pointer;

                }
                .pagination{
                    margin:5px 12px !important;
                }
            
        </style>


        <script>


            angular.module("myapp", [])
            .controller("controllerGetColourPalettes", function($scope, $http) {

                $scope.showData = function( ){

                 $scope.curPage = 0;
                 $scope.pageSize = 4;

                    $http({
                            method : 'GET',
                            url : 'GetColorPalettes'
                    }).success(function(data, status, headers, config) {

                        $scope.datalists = data;

                        $scope.numberOfPages = function() {
                                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                         };
                        if (data === error){
                            alert(data);
                        }
                    }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });

                    }
            });
                    
            function controllerGetColoursFromLogo($scope, $http) {

            $scope.getLogoColors = function() {
                $http({
                    method : 'GET',
                    url : 'GetColorsFromLogo'
            }).success(function(data, status, headers, config) {
                    $scope.color = data.Colors;
                    if (data === error ){
                        alert(data);
                    }
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
                    if (color1 == "" || color2 == "" || color3 == "" || color4 == "" || color5 == "" || color6 == ""){
                            alert("Please fill all six colors, click MOST USED to select colors");
                        }
                   else{
                       
                        var colorObject ="{&quot;"+getColor1()+"&quot;:&quot;"+color1+"&quot;, &quot;"+getColor2()+"&quot;:&quot;"+color2+"&quot;, &quot;"+getColor3()+"&quot;:&quot;"+color3+"&quot;, &quot;"+getColor4()+"&quot;:&quot;"+color4+"&quot;, &quot;"+getColor5()+"&quot;:&quot;"+color5+"&quot;, &quot;"+getColor6()+"&quot;:&quot;"+color6+"&quot;, &quot;type&quot;:&quot;save&quot;}";
                            $http({
                                method: 'POST',
                                        url: getHost() + 'SetUserPreferences',
                                        headers: {'Content-Type': 'application/json'},
                                        data:  colorObject
                                }).success(function (data){
                                    $scope.status = data;
                                    if(data === error){
                                        alert(data);
                                    }else{
                                        window.open(getHost() + 'dashboard.jsp', "_self");
                                    }
                                })
                    .error(function(data, status) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                            alert("request not succesful");
                    });
            }
            }

            };
                
                angular.module('myapp').filter('pagination', function()
                {
                 return function(input, start)
                 {
                  start = +start;
                  return input.slice(start);
                 };
                });
                
        </script>

    <script>
                var ElementID;
                /*------ get selected element ID -----*/
                        function getElementID(IDNo){
                        ElementID = IDNo;
                        $('.step_wrapper').on('click', '.step_box', function () {
                $(this).parent().find('.step_box').css('width', '').css('height', '').css('border-color', '').css('border-radius', '');
                                $(this).css('width', '80px').css('height', '40px').css('border-color', '#FF0000').css('border-radius', '10px');
                        });
                                $("#sortable").sortable();
                                $("#sortable").disableSelection();
                        }
                /*------ pass color into the selected element got by id-----*/
                function getIDNo(IDNo){
                var s = $("#" + IDNo).attr("style");
                        var s1 = s.split(":");
                        $("#" + ElementID).css("background-color", s1[1]);
                }

    </script>
    <script>
       
          var id = 1;
          var theme_id = 0;
        function doSomething(theme_id){
            if (theme_id == "theme1"){
                    var s = $("#color1").attr("style");
                    var s1 = s.split(":");
                    var s = $("#color2").attr("style");
                    var s2 = s.split(":");
                    var s = $("#color3").attr("style");
                    var s3 = s.split(":");
                    var s = $("#color4").attr("style");
                    var s4 = s.split(":");
                    var s = $("#color5").attr("style");
                    var s5 = s.split(":");
                    var s = $("#color6").attr("style");
                    var s6 = s.split(":");
                    $("#themeid").val("theme1");
                    $("#elementToPutStyleInto1").css("background-color", s1[1]);
                    $("#elementToPutStyleInto2").css("background-color", s2[1]);
                    $("#elementToPutStyleInto3").css("background-color", s3[1]);
                    $("#elementToPutStyleInto4").css("background-color", s4[1]);
                    $("#elementToPutStyleInto5").css("background-color", s5[1]);
                    $("#elementToPutStyleInto6").css("background-color", s6[1]);
            }else if(theme_id == "theme2"){
                var s = $("#color7").attr("style");
                        var s1 = s.split(":");
                        var s = $("#color8").attr("style");
                        var s2 = s.split(":");
                        var s = $("#color9").attr("style");
                        var s3 = s.split(":");
                        var s = $("#color10").attr("style");
                        var s4 = s.split(":");
                        var s = $("#color11").attr("style");
                        var s5 = s.split(":");
                        var s = $("#color12").attr("style");
                        var s6 = s.split(":");
                        $("#themeid").val("theme2");
                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
            }else if(theme_id == "theme3"){
                var s = $("#color13").attr("style");
                        var s1 = s.split(":");
                        var s = $("#color14").attr("style");
                        var s2 = s.split(":");
                        var s = $("#color15").attr("style");
                        var s3 = s.split(":");
                        var s = $("#color16").attr("style");
                        var s4 = s.split(":");
                        var s = $("#color17").attr("style");
                        var s5 = s.split(":");
                        var s = $("#color18").attr("style");
                        var s6 = s.split(":");
                        $("#themeid").val("theme2");
                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
            }else if(theme_id == "theme4"){
                var s = $("#color19").attr("style");
                        var s1 = s.split(":");
                        var s = $("#color20").attr("style");
                        var s2 = s.split(":");
                        var s = $("#color21").attr("style");
                        var s3 = s.split(":");
                        var s = $("#color22").attr("style");
                        var s4 = s.split(":");
                        var s = $("#color23").attr("style");
                        var s5 = s.split(":");
                        var s = $("#color24").attr("style");
                        var s6 = s.split(":");
                        $("#themeid").val("theme2");
                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
            }

        }

    </script>


</head>
<body ng-app="myapp" >

    <div class="container">
        <div class="row">
        </div>

        <div id="contentdiv" class="row">   

            <div class="col-md-8 col-md-offset-3">
                <p id="comment1">Choose a color palette</p> 
                <p id="comment2">Click on a color to change it or choose from a theme.<br>
Please arrange them with most used to least used in your designs.</p>
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
                        <%! Integer i=1; %>
                        <div ng-controller="controllerGetColourPalettes" class="tab-pane active" id="picktheme" ng-init="showData()">
                            <div ng-repeat= "theme in datalists | pagination: curPage * pageSize | limitTo: pageSize" id="rep" >
                                
                                <script type="text/javascript">
                                </script>
                                <div ng-repeat="colors in theme" id="rep1" >
                                    <div id="{{colors.id}}" onclick="getIDNo('{{colors.id}}')" class="foo col-md-2" style="background-color:{{colors.colorHex}};"></div>
                                    <div><p id="{{colors.theme_id}}" onclick="doSomething('{{colors.theme_id}}')">{{colors.theme_name}}</p></div>
                                </div> 
                                <div id='id'>
                                    <p><br/></p>
                                </div>
                                <script> 
//                                    doSomething(id);
//                                    id++; 
                                </script>
                            </div>

                            <div class="pagination pagination-centered" ng-show="datalists.length">
                            <ul class="pagination-controle pagination">
                             <li>
                              <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
                             ng-click="curPage=curPage-1"> &lt; PREV</button>
                             </li>
                             <li>
                             <span>Page {{curPage + 1}} of {{ numberOfPages() }}</span>
                             </li>
                             <li>
                             <button type="button" class="btn btn-primary"
                             ng-disabled="curPage >= themes.length/pageSize - 1"
                             ng-click="curPage = curPage+1">NEXT &gt;</button>
                             </li>
                            </ul>
                            </div>
                            
<!--                      <div class="col-md-3 pull-right ">
                                <div><p id="p1">USE THEME1</p></div><br>
                                <div><p id="p2">USE THEME2</p></div><br>
                                <div><p id="p3">USE THEME3</p></div><br>
                                <div><p id="p4">USE THEME4</p></div><br>
                            </div>-->
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
                        onSubmit:function(hsb, hex, rgb, el) {
                        //for haking hex value alert(hex);

                        $('#' + ElementID).css("background-color", "#" + hex);
                        }

                });
                        // for selecting theme     
//                $(document).ready(function(){
//                $("#theme1").click(function(){
//                    alert("text");
//                var s = $("#color1").attr("style");
//                        var s1 = s.split(":");
//                        var s = $("#color2").attr("style");
//                        var s2 = s.split(":");
//                        var s = $("#color3").attr("style");
//                        var s3 = s.split(":");
//                        var s = $("#color4").attr("style");
//                        var s4 = s.split(":");
//                        var s = $("#color5").attr("style");
//                        var s5 = s.split(":");
//                        var s = $("#color6").attr("style");
//                        var s6 = s.split(":");
//                        $("#themeid").val("theme1");
//                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
//                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
//                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
//                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
//                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
//                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
//                });
//                });
//                $(document).ready(function(){
//
//                $("#theme2").click(function(){
//                var s = $("#color7").attr("style");
//                        var s1 = s.split(":");
//                        var s = $("#color8").attr("style");
//                        var s2 = s.split(":");
//                        var s = $("#color9").attr("style");
//                        var s3 = s.split(":");
//                        var s = $("#color10").attr("style");
//                        var s4 = s.split(":");
//                        var s = $("#color11").attr("style");
//                        var s5 = s.split(":");
//                        var s = $("#color12").attr("style");
//                        var s6 = s.split(":");
//                        $("#themeid").val("theme2");
//                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
//                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
//                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
//                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
//                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
//                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
//                });
//                });
//                $(document).ready(function(){
//
//                $("#theme3").click(function(){
//                var s = $("#color13").attr("style");
//                        var s1 = s.split(":");
//                        var s = $("#color14").attr("style");
//                        var s2 = s.split(":");
//                        var s = $("#color15").attr("style");
//                        var s3 = s.split(":");
//                        var s = $("#color16").attr("style");
//                        var s4 = s.split(":");
//                        var s = $("#color17").attr("style");
//                        var s5 = s.split(":");
//                        var s = $("#color18").attr("style");
//                        var s6 = s.split(":");
//                        $("#themeid").val("theme3");
//                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
//                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
//                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
//                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
//                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
//                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
//                });
//                });
//                $(document).ready(function(){
//
//                $("#theme4").click(function(){
//                var s = $("#color19").attr("style");
//                        var s1 = s.split(":");
//                        var s = $("#color20").attr("style");
//                        var s2 = s.split(":");
//                        var s = $("#color21").attr("style");
//                        var s3 = s.split(":");
//                        var s = $("#color22").attr("style");
//                        var s4 = s.split(":");
//                        var s = $("#color23").attr("style");
//                        var s5 = s.split(":");
//                        var s = $("#color24").attr("style");
//                        var s6 = s.split(":");
//                        $("#themeid").val("theme4");
//                        $("#elementToPutStyleInto1").css("background-color", s1[1]);
//                        $("#elementToPutStyleInto2").css("background-color", s2[1]);
//                        $("#elementToPutStyleInto3").css("background-color", s3[1]);
//                        $("#elementToPutStyleInto4").css("background-color", s4[1]);
//                        $("#elementToPutStyleInto5").css("background-color", s5[1]);
//                        $("#elementToPutStyleInto6").css("background-color", s6[1]);
//                });
//                });
// pass color rgb values into the form text boxes
                $(document).ready(function(){

                $("#test").click(function(){
                   
                        var s1 =$("#elementToPutStyleInto1").css("background-color");                    
                        var s2 =$("#elementToPutStyleInto2").css("background-color");
                        var s3 = $("#elementToPutStyleInto3").css("background-color");                    
                        var s4 = $("#elementToPutStyleInto4").css("background-color");                     
                        var s5 = $("#elementToPutStyleInto5").css("background-color");                       
                        var s6 = $("#elementToPutStyleInto1").css("background-color");
                        document.getElementById("finalcolor1").value = s1;
                        document.getElementById("finalcolor2").value = s2;
                        document.getElementById("finalcolor3").value = s3;
                        document.getElementById("finalcolor4").value = s4;
                        document.getElementById("finalcolor5").value = s5;
                        document.getElementById("finalcolor6").value = s6;
                });
                });
//  Reset pslette color   

                $(document).ready(function(){

                $("#resetpalette").click(function(){

                $("#elementToPutStyleInto1").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto2").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto3").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto4").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto5").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto6").css("background-color", "#FFFFFF");
                });
                });

    </script>

</body>
</html>
