<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>setting</title>
        <meta charset="UTF-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/angular.min.js"></script>  
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script src="js/prettify.js"></script>
        <script src="js/jquery.bsFormAlerts.js"></script>
        <script type="text/javascript" src="jscolor/jscolor.js"></script>

        <script src="js/tabcontent.js" type="text/javascript"></script>
        <script src="js/settings.js" type="text/javascript"></script>
        <link href="tabs/tabcontent.css" rel="stylesheet" type="text/css"/>
        <script src="js/settingspalettechooser.js" type="text/javascript"></script>
        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
                font-size:20px;
                position: relative;
                left:-15px;
                padding: 7px;
            }
        </style>
        <style>
            .fileUpload {
                position: relative;
                overflow: hidden;
                margin: 10px;
            }
            .fileUpload input.upload {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 20px;
                cursor: pointer;
                opacity: 0;
                filter: alpha(opacity=0);
            }

                     .container{
                            position: absolute;
                            float: left;
/*                            margin-left: -250px;*/
                        }

        </style>
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
            alert(IDNo);
        var s = $("#" + IDNo).attr("style");
                var s1 = s.split(":");
                $("#" + ElementID).css("background-color", s1[1].replace(";" , " "));
        }
            
            
                  var id = 1;
          var theme_id = 0;
        function doSomething(theme_id){
            alert("clicked");
            if (theme_id == "theme1"){
                    var s = $("#color1").attr("style");
                    var s1 = s.split(":");
                    alert(s1[1]);
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
                    $("#elementToPutStyleInto1").css("background-color", s1[1].replace(";" ," "));
                    $("#elementToPutStyleInto2").css("background-color", s2[1].replace(";" ," "));
                    $("#elementToPutStyleInto3").css("background-color", s3[1].replace(";" ," "));
                    $("#elementToPutStyleInto4").css("background-color", s4[1].replace(";" ," "));
                    $("#elementToPutStyleInto5").css("background-color", s5[1].replace(";" ," "));
                    $("#elementToPutStyleInto6").css("background-color", s6[1].replace(";" ," "));
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
                     $("#elementToPutStyleInto1").css("background-color", s1[1].replace(";" ," "));
                    $("#elementToPutStyleInto2").css("background-color", s2[1].replace(";" ," "));
                    $("#elementToPutStyleInto3").css("background-color", s3[1].replace(";" ," "));
                    $("#elementToPutStyleInto4").css("background-color", s4[1].replace(";" ," "));
                    $("#elementToPutStyleInto5").css("background-color", s5[1].replace(";" ," "));
                    $("#elementToPutStyleInto6").css("background-color", s6[1].replace(";" ," "));
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
                       $("#elementToPutStyleInto1").css("background-color", s1[1].replace(";" ," "));
                    $("#elementToPutStyleInto2").css("background-color", s2[1].replace(";" ," "));
                    $("#elementToPutStyleInto3").css("background-color", s3[1].replace(";" ," "));
                    $("#elementToPutStyleInto4").css("background-color", s4[1].replace(";" ," "));
                    $("#elementToPutStyleInto5").css("background-color", s5[1].replace(";" ," "));
                    $("#elementToPutStyleInto6").css("background-color", s6[1].replace(";" ," "));
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
                        $("#elementToPutStyleInto1").css("background-color", s1[1].replace(";" ," "));
                    $("#elementToPutStyleInto2").css("background-color", s2[1].replace(";" ," "));
                    $("#elementToPutStyleInto3").css("background-color", s3[1].replace(";" ," "));
                    $("#elementToPutStyleInto4").css("background-color", s4[1].replace(";" ," "));
                    $("#elementToPutStyleInto5").css("background-color", s5[1].replace(";" ," "));
                    $("#elementToPutStyleInto6").css("background-color", s6[1].replace(";" ," "));
            }

        }
        </script>
    </head>
    
    
    <body ng-app>
        <div ng-controller="controllerUserChanges">

            <div class="row">
                <jsp:include page="mainmenu.html"/><!--/end left column-->

                <div class="col-md-10 col-md-offset-2">
                    <div class="col-md-10 ">
                        <p id="text3"> Setting page</p>


                        <div style="width:500px; margin:0px; padding:120px 0 40px;">

                            <div class="tabcontents">
                                <div id="view1" style="width:550px; height:230px ">

                                    <b>Change Password</b>

                                    <form class="form-horizontal" id="signform" ng-submit="changePassword()" >

                                        <div class="group">
                                            <div class="col-md-3 col-md-offset-5">                            
                                                <p class="text-left"></p>
                                            </div>
                                        </div>

                                        <div class="group">
                                            <div class="col-md-3 col-md-offset-5">                            
                                                <input id="inputpassword" class="form-control simplebox" type="password" name="password" />
                                                <label>PASSWORD</label><br>
                                            </div>
                                        </div>
                                        <div class="group">
                                            <div class="col-md-3 col-md-offset-5">                            
                                                <input id="inputreenter" class="form-control simplebox" type="password" name="confirmpassword"/>
                                                <label>CONFIRM PASSWORD</label><br>
                                            </div>
                                        </div>

                                        <div  class="form-group">
                                            <div class="col-md-5 col-md-offset-5">
                                                <button type="submit"  class="btn btn-info">Enter</button><br>
                                            </div>
                                        </div>

                                    </form> 
                                </div>
                                <div id="view2" style="width:750px; height:auto;" >
                                    <b>Select color palettes</b>

                                    <div class="container">
                                        <div class="row">
                                        </div>

                                        <div id="contentdiv" class="row">   

                                            <div class="col-md-8 col-md-offset-0">
                                                <p id="comment1"> Choose a palette </p> 
                                                <p id="comment2">Click on a color to change it or choose from a theme. Don?t worry, you can always change it later.</p>
                                                <div class="col-md-12"><p id="test" class="span3" >MOST USED <span class="col-md-offset-5" id="leastuse">LEAST USE</span></p> </div>

                                                <div id="sortable" class="step_wrapper">
                                                    <div id="elementToPutStyleInto1" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color1}}"  onclick="getElementID('elementToPutStyleInto1')"></div>
                                                    <div id="elementToPutStyleInto2" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color2}}"  onclick="getElementID('elementToPutStyleInto2')"></div>
                                                    <div id="elementToPutStyleInto3" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color3}}"  onclick="getElementID('elementToPutStyleInto3')"></div>
                                                    <div id="elementToPutStyleInto4" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color4}}"  onclick="getElementID('elementToPutStyleInto4')"></div>
                                                    <div id="elementToPutStyleInto5" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color5}}"  onclick="getElementID('elementToPutStyleInto5')"></div>   
                                                    <div id="elementToPutStyleInto6" class="blankcolor-box step_box" style="background-color: {{user_preferences_colors.color6}}"  onclick="getElementID('elementToPutStyleInto6')"></div>
                                                    <div class="resetpalette"> <p id="resetpalette">RESET ORIGINAL PALETTE</p></div>
                                                </div>

                                            </div>

                                            <div class="col-md-7 col-md-offset-0 ">
                                                <div class="tabbable tabs-top">
                                                    <br>
                                                    <ul id="btn" class="nav nav-tabs" >

                                                        <li id="li1" class="active"><a id="a1" href="#picktheme" data-toggle="tab">PICK FROM A THEME</a></li>
                                                        <li id="li2" class=""><a id="a1" href="#custom" data-toggle="tab">CHOOSE CUSTOM</a></li>
                                                        <li id="li3" class=""><a id="a1" href="#logocolor" data-toggle="tab">PICK FROM LOGO COLORS</a></li>

                                                    </ul> 
                                                    <div class="tab-content">

                                                        <br>
                                                        <%! Integer i = 1;%>
                                                        <div class="tab-pane active" id="picktheme">
                                                            <div ng-repeat= "theme in themes" id="rep" >

                                                                <script type="text/javascript">
                                                                </script>
                                                                <div ng-repeat="colors in theme" id="rep1" >
                                                                    <div id="{{colors.id}}" onclick="getIDNo('{{colors.id}}')" class="foo col-md-2" style="background-color:{{colors.colorHex}};"></div>
                                                                    <div><p id="{{colors.theme_id}}" onclick="doSomething('{{colors.theme_id}}')">{{colors.theme_name}}</p></div>
                                                                </div> 
                                                                <div id='id'>
                                                                    <p><br/></p>
                                                                </div>
                                                            </div>

                                                            <!--                                                            <div class="pagination pagination-centered" ng-show="datalists.length">
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
                                                                                                                        </div>-->

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

                                                        <div class="tab-pane" id="logocolor" >
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
                                                            <div class="col-sm-offset-0 col-md-4" >
                                                                <form class="form-horizontal">
                                                                    <input type="hidden" id="finalcolor1" name="finalcolor1" ng-model="selColor.finalcolor1"  />
                                                                    <input type="hidden" id="finalcolor2" name="finalcolor2" ng-model="selColor.finalcolor2" />
                                                                    <input type="hidden" id="finalcolor3" name="finalcolor3" ng-model="selColor.finalcolor3"/>
                                                                    <input type="hidden" id="finalcolor4" name="finalcolor4" ng-model="selColor.finalcolor4"/>
                                                                    <input type="hidden" id="finalcolor5" name="finalcolor5" ng-model="selColor.finalcolor5"/>
                                                                    <input type="hidden" id="finalcolor6" name="finalcolor6" ng-model="selColor.finalcolor6"/>

                                                                    <div class="span4 col-md-offset-0">
                                                                        <button  type="button" class="btn btn-info" ng-click="createUserPreferences()">SAVE</button>
                                                                    </div>

                                                                </form>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                </div>
                                <div id="view3">
                                    <b>Look</b>
                                    <div class="row" id="buttonlength" style="">
                                        <div class="span7">
                                            <div class="col-md-7 pull-right pull-up">

                                                <div class="item"><p>Selected look preview</p>
                                                    <img id="image1" class="img-responsive" src="images/Lookimages/{{UserLooks.image_name}}" width="700" height="400"><br>

                                                </div>
                                            </div>
                                        </div>
                                        <button type="button"  id="contemporary1" class="btn btn-default btn-lg col-md-3">{{UserLooks.look_name}}</button><br><br><br>
                                    </div>
                                    <p>Choose a look</p>
                                    <div class="col-md-12 step_wrapper">
                                        <div  class="col-md-2 step_box" ng-repeat="first in First" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                            <img id="{{first.id}}" class="img-responsive lookchooser1 " src="images/Lookimages/{{ first.file_name}}"  onclick="showLook({{first.id}})" width=250 height=150 />
                                        </div>

                                        <div class="col-md-12"></div>
                                        <div class="col-md-2 step_box" ng-repeat="second in Second" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                            <img id="{{second.id}}" class="img-responsive lookchooser1" src="images/Lookimages/{{ second.file_name}}" onclick="showLook({{second.id}})" width=250 height=150 />
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="span3 col-md-offset-0 ">
                                            <div  class="form-group">
                                                <input type="hidden" name="lookid" id="lookid">
                                                <div class="span3 col-md-offset-0 ">
                                                    <button id="loochooserbutton" type="submit"  ng-click="updateLooks()" class="btn btn-info">SAVE</button><br><br><br>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div id="view4" >
                                    <b>Brand Personality</b>
                                    <div class="row" id="buttonlength" style="">
                                        <div class="span7">
                                            <div class="col-md-7 pull-right pull-up">

                                                <div class="item"><p>Selected brand preview</p>
                                                    <img id="image1" class="img-responsive" src="images/Brandimages/{{UserBrand.image_name}}" width="700" height="400"><br>

                                                </div>
                                            </div>
                                        </div>
                                        <button type="button"  id="contemporary1" class="btn btn-default btn-lg col-md-3">{{UserBrand.brand_name}}</button><br><br><br>


                                    </div>
                                    <div class="row" id="buttonlength" style="">
                                        <div class="span7">
                                            <div class="col-md-7 pull-right pull-up">
                                                <div class="item"><p >preview</p>
                                                    <img id="image2" class="img-responsive" src="" width="700" height="400"><br>
                                                </div>
                                            </div>
                                        </div>
                                        <form class="form-horizontal" ng-model="brands">
                                            <input id="hiddenform" name="hiddenform" type="hidden" ng-model="brands.brandName"><br>
                                            <div ng-repeat="first in First" class="span5 col-md-offset-1 ">
                                                <button type="button"  id="contemporary1" class="btn btn-default btn-lg col-md-3" onclick="showBrand('{{first.id}}','{{first.image_name}}')">{{first.brand_name}}</button><br><br><br>
                                            </div>  

                                            <div class="span4 col-md-offset-1">
                                                <button type="button" class="btn btn-info" ng-click="updateBrands()">SAVE</button>
                                            </div>
                                        </form>

                                    </div>

                                </div>
                                <div id="view5" style="width:400px; height:500px;">

                                    <b>Change Logo</b>
                                    <%!
                                         SqlMethods sqlmethods = new SqlMethods();
                                         Integer user_id = 0;
                                         String file_name = null;
                                    %>
                                    <%
                                        try{
                                            sqlmethods.session = request.getSession(true);
                                            user_id = (Integer)sqlmethods.session.getAttribute("UID");
                                            file_name = (String)sqlmethods.session.getAttribute("ImageFileName");
                                        }catch (Exception e){
                                            System.out.println(e.getCause());
                                            System.out.println(e.getMessage());
                                        }
                                    %>                                    
                                    <div class="row" id="buttonlength" style="">
                                        <div class="span7">
                                            <div class="col-md-7 pull-right pull-up">

                                                <div class="item"><p>Selected logo</p>
                                                    <img id="image1" class="img-responsive" src="images/Customers/<%= user_id %>/logo/<%= file_name %>" width="700" height="400"><br>

                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    
                                    <div style="width:540px;">
                                        <form name="formpersonality" action="<%= application.getContextPath()%>/changeLogo" enctype="multipart/form-data" method="post">
                                            <input type="hidden" name="upload" value="update"/>
                                            <input type="file" name="fileUpload" style="border: 1px solid;" name="filesToUpload"  id="filesToUpload" class="upload"/><br>

                                            <div style="position: absolute; float:left;">
                                                <div>
                                                    <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                                                    <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>

                            <ul class="tabs1" data-persist="true">
                                <li ><a href="#view1" style="width:180px;">Password</a></li>
                                <li><a href="#view2"  style="width:180px;" ng-click="showColors()">Color Palette</a></li>
                                <li><a href="#view3"  style="width:180px;" ng-click="getLooks()">Look</a></li>
                                <li><a href="#view4" ng-click="getBrands()">Brand Personality</a></li>
                                <li><a href="#view5"  style="width:180px;" ng-click="getLogo()">Logo</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>


            </div>
        </div>

    </body>
</html>
