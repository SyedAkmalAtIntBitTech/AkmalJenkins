
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="shortcut icon" href="favicon.png">
    <title>BrndBot - Account Settings</title>
    <meta charset="UTF-8" >
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/bootstrap.css"></link>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/alertmessage.js" type="text/javascript"></script>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script> 
     <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css"/>
    <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <script src="js/popup.js" type="text/javascript"></script>
    <script src="js/reuseablefunctions.js"></script>
    <script src="js/colpick.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/main1.css">
    <script src="js/prettify.js"></script>
    <script src="js/jquery.bsFormAlerts.js"></script>
    <script type="text/javascript" src="jscolor/jscolor.js"></script>
    <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
    <script src="js/tabcontent.js" type="text/javascript"></script>
    <script src="js/settings.js" type="text/javascript"></script>
    <link href="css/tabcontent.css" rel="stylesheet" type="text/css"/>
    <script src="js/settingspalettechooser.js" type="text/javascript"></script>     
    <style>
        a{
            position:initial;
        }
        </style>
   <%--<jsp:include page="basejsp.jsp" />--%>
   <%! 
            String change = "";
        %>
        <% 
            try{
                change = (String)request.getParameter("change");
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                
            }
        
        %>
</head>    

<body ng-app>    
    <div class="content-main" ng-controller="controllerUserChanges">
        <input type="hidden" value="<%=change%>" id="change"></input>
        <!--SideNav-->

         <jsp:include page="header.jsp"/>  
          <jsp:include page="navbar.jsp"/>  
        <!--Top Nav-->   
        <div class="top-nav">
            <div class="page-title-bar col-1of1"> 
                <div class="page-title-regular page-title-font">User Settings</div>
            </div>
            <div class="page-subnav-bar-regular"> 
                <div class="top-subnav-tabs-container">
                    <ul class="top-subnav-nav-elements">
                        <li class="top-subnav-link-active" id="accountsettingtab"> <a class="h3-active-subnav">Account Settings</a></li>
                        <li class="top-subnav-links" id="logosettingtab"> <a class="h3">Logo Settings</a></li>
                        <li class="top-subnav-links" id="palettesettingtab"> <a class="h3">Palette Settings</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!--Main Content GENERIC--> 
        <div class="sequence-page-background">
            
            <div class="sequence-page-content-container" id="accountsettingdiv">            
                <!--Inner Content Conatiner GENERIC-->
                <div class="page-inner-content-container ">
                    <div class="fleft content">
                        <!--List Starts Here-->
                       <div class="input-field-container col-1of1 fleft">
                            <div class="input-header"> Change Password </div>
                            <input type="password" placeholder="Enter New Password" id="inputpassword" class="input-field-textfield5 width33 showornot"></input>
                            <input type="text" placeholder="Enter New Password" id="inputpassword1" class="input-field-textfield5 width33 hideornot"></input>
                            <input type="password" placeholder="Enter Confirm Password" id="inputreenter" class="input-field-textfield5 width33 showornot"></input>
                            <input type="text" placeholder="Enter Confirm Password" id="inputreenter1" class="input-field-textfield5 width33 hideornot"></input>
                            <div class="inlineblock margin-top-10">
                                <input type="checkbox" name="showpassword" value="" id="showpassword"><p class="posi"> Show Password</p></input>
                            </div>
                       </div>
                    </div>
                </div>                    
                <div class="" id="savePassword">
                    <div class="edit-button-detail md-button button-text-1 fleft savebutton savebuttonsettingspage" ng-click="changePassword()">Save Password</div>
                </div>
            </div>
            
            <div class="sequence-page-content-container" id="logosettingdiv">
                <div class="page-inner-content-container ">
                    <div class="fleft content">
                        <!--List Starts Here-->
                        <div class="h4 pushUp-60">
                            Logo
                        </div>
                        <div class="pushUp">
                            <form id="data" name="formpersonality"  enctype="multipart/form-data" method="post" class="ng-pristine ng-valid">
                                <div class="col-2of10 fleft cur1">
                                    <div class="logo-container"><textarea class="top55 left10 right10" id="filetext1">Choose an Image to upload</textarea>
                                        <input type="hidden" name="upload" value="update"></input>
                                        <input type="file" name="fileUpload" style="border: 1px solid;" class="upload" id="filevalue" onchange="changefilename()"></input>
                                    </div>
                                </div>
                                <div class="col-1of2 fleft">
                                    <button class="pushUp-60 edit-button-detail md-button" type="submit" id="Servicecontinue"> Change Logo </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>            
            </div>
            
            <div class="sequence-page-content-container" id="palettesettingdiv">
                <div id="" style="width:750px; height:auto;" >
                    <div class="container" ng-init="showColors()">
                        <div class="row">
                        </div>
                        <div id="contentdiv" class="row">   
                            <div class="col-md-8 col-md-offset-0">
                                <p id="comment2">Click on a color to change it or choose from a theme. Don't worry, you can always change it later.</p>
                                <div class="col-md-12"><p id="test" class="span3" >MOST USED<span class="col-md-offset-5" id="leastuse">LEAST USE</span></p> </div>
                                <div id="sortable" class="step_wrapper">
                                    <div id="elementToPutStyleInto1" class="blankcolor-box step_box ptr" style="background-color: {{user_preferences_colors[0]}}"  onclick="getElementID('elementToPutStyleInto1')"></div>
                                    <div id="elementToPutStyleInto2" class="blankcolor-box step_box ptr" style="background-color: {{user_preferences_colors[1]}}"  onclick="getElementID('elementToPutStyleInto2')"></div>
                                    <div id="elementToPutStyleInto3" class="blankcolor-box step_box ptr" style="background-color: {{user_preferences_colors[2]}}"  onclick="getElementID('elementToPutStyleInto3')"></div>
                                    <div id="elementToPutStyleInto4" class="blankcolor-box step_box ptr" style="background-color: {{user_preferences_colors[3]}}"  onclick="getElementID('elementToPutStyleInto4')"></div>
                                    <div class="resetpalette ptr"> <p id="resetpalette">RESET ORIGINAL PALETTE</p></div>
                                </div>
                            </div>
                            <div class="col-md-7 col-md-offset-0 ">         
                                <div class="">
                                    <div class="page-title-bar col-1of1"> 
                                    </div>
                                    <div class="page-subnav-bar-regular"> 
                                        <div class="top-subnav-tabs-container">
                                            <ul class="top-subnav-nav-elements">
                                                <li class="top-subnav-links" id="fromThem"> <a class="h3">PICK FROM A THEME</a></li>
                                                <li class="top-subnav-links" id="fromPalette"> <a class="h3">CHOOSE CUSTOM</a></li>
                                                <li class="top-subnav-links" id="fromLogo"> <a class="h3">PICK FROM LOGO COLORS</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                      <div class="tab-pane active" id="picktheme">
                                            <div style="height:250px; overflow-y:scroll;">
                                            <div ng-repeat="color in themes" class="palette-wrap-globalColors">
                                                <div id="{{color.globalColorsId}}" class="palette-global clear">
                                                    <div class="foo blankcolor-box step_box ptr" style="background-color:{{color.color1}};" onclick="setSelectedColor('{{color.color1}}')"></div>
                                                    <div class="foo blankcolor-box step_box ptr" style="background-color:{{color.color2}};" onclick="setSelectedColor('{{color.color2}}')"></div>
                                                    <div class="foo blankcolor-box step_box ptr" style="background-color:{{color.color3}};" onclick="setSelectedColor('{{color.color3}}')"></div>
                                                    <div class="foo blankcolor-box step_box ptr" style="background-color:{{color.color4}};" onclick="setSelectedColor('{{color.color4}}')"></div>
                                                    <div onclick="setThemeColors('{{color.color1}}','{{color.color2}}','{{color.color3}}','{{color.color4}}')"style="padding-top: 10px; color: #7f7f7f;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{color.colorName}}</div>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane displayNone" id="custom">
                                            <br>     
                                            <div  id="picker"></div><br><br>
                                        </div>
                                        <div class="tab-pane displayNone" id="logocolor" >
                                            <div class="tab-pane active" id="picktheme">
                                                <div ng-repeat="col in color">
                                                    <div class="foo" style="background-color:{{col}};" onclick="setSelectedColor('{{col}}')"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-0 col-md-4" >
                                                <form class="form-horizontal">
                                                    <input type="hidden" id="finalcolor1" name="finalcolor1" ng-model="selColor.finalcolor1"  />
                                                    <input type="hidden" id="finalcolor2" name="finalcolor2" ng-model="selColor.finalcolor2" />
                                                    <input type="hidden" id="finalcolor3" name="finalcolor3" ng-model="selColor.finalcolor3"/>
                                                    <input type="hidden" id="finalcolor4" name="finalcolor4" ng-model="selColor.finalcolor4"/>
                                                    <input type="hidden" id="finalcolor5" name="finalcolor5" ng-model="selColor.finalcolor5"/>
                                                    <input type="hidden" id="finalcolor6" name="finalcolor6" ng-model="selColor.finalcolor6"/>
                                                    <div class="span4 col-md-offset-0">
                                                        <button  type="button" class="edit-button-detail md-button button-text-1 fleft savebutton noborder" style="margin-left: -6%;" ng-click="createUserPreferences()">SAVE</button>
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
            
        </div>       
            
        <!-- BottomNav -->
    </div>
</body>
</html>