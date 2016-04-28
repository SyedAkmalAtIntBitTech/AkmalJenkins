<%-- 
    Document   : onboardingpalette
    Created on : Mar 31, 2016, 8:28:31 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BrndBot- Color Palette</title>
        <jsp:include page="header.jsp"/>
    </head>
    <body class="body-normal" ng-app ng-controller="onboardingcontroller">

        <!--SideNav-->

        <!--Main Content Wrap-->
        <div class="contentWrap--withNoSideNav" ng-init="getLogoColors()">
            <div class="topNav clear">
                <a href="#">
                    <div class="topNav--BackButton fleft">
                        <img  src="images/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
                    </div>
                </a>
                <div class="topNav--TitleBar--withBackButton fleft">
                    <span class="topNav--TitleBar--Title fleft h2">Step 5 of 5</span>
                    <div class="topNav--TitleBar--CTABox fright">
                        <div class="CTA_Button Button--Gray">Help!</div>
                    </div>
                </div>
            </div>
            <div class="topNav--offset"></div>
            <div class="contentWrapInner">
                <div class="pane palette-pane">
                    <div class="pane_header clear">
                        <div class="pane_title fleft h2">Choose your color palette</div>
                    </div>
                    <div class="pane_content clear">
                        <div class="pane-palette_left fleft">
                            <div class="h3 font--lightGray">BrndBot syncs with your other tools. If you are not using any of these services, just select none!</div>
                            <div class="h4 font--lightGray onboarding-subtext">BrndBot syncs with your other tools. If you are not using any of these services, just select none!</div>
                            <div class="palette-wrap">
                                <div class="palette clear">
                                    <div id="color1Div" class="select-palette-colorswab fleft"><div id="color1" class="palette-colorswab fleft"></div></div>
                                    <div id="color2Div" class="select-palette-colorswab fleft"><div id="color2" class="palette-colorswab fleft"></div></div>
                                    <div id="color3Div" class="select-palette-colorswab fleft"><div id="color3" class="palette-colorswab fleft"></div></div>
                                    <div id="color4Div" class="select-palette-colorswab fleft"><div id="color4" class="palette-colorswab fleft"></div></div>
                                </div>
                            </div>
                            <div class="CTA_Button Button--Gray clear-colors" ng-click="clearColorPalette()">Clear Colors</div>
                        </div>
                        <div class="pane-palette_right fright" >
                            <div class="colorOptions-bar clear">
                                <div class="colorOptions-tab-active col-1of3 fleft" id="customColors">Custom Colors</div>
                                <div class="colorOptions-tab col-1of3 fleft" id="themeColors" ng-click="showThemeColors()">Choose from Theme</div>
                                <div class="colorOptions-tab col-1of3 fleft" id="logoColorsDiv" >Choose from Logo</div>
                            </div>
                            <div  class="pane-palette_right fleft">

                                <div class="color-picker-space" id="picker"></div>

                                <div  class="palette-wrap" id="logocolor" >
                                    <div class="palette clear" id="picktheme">
                                        <!--<div><button type="button" class="btn btn-primary" value="click to display colors" >click to display colors</button></div>-->
                                        <div ng-repeat="col in color">
                                            <div  class="logo-palette-colorswab fleft" style="background-color:{{col}};" onclick="getColorID('{{col.id}}','{{col}}')"></div>
                                        </div>
                                    </div>
                                </div>

                                <div id="themeColorsDiv" class="palette-theme-global">
                                    <div ng-repeat="color in themeColors" class="palette-wrap-globalColors">
                                        <div id="{{color.globalColorsId}}" class="palette-global clear">
                                            <div class="palette-colorswab-global fleft" style="background-color:{{color.color1}};" onclick="getColorID('{{color.globalColorsId}}','{{color.color1}}')"></div>
                                            <div class="palette-colorswab-global fleft" style="background-color:{{color.color2}};" onclick="getColorID('{{color.globalColorsId}}','{{color.color2}}')"></div>
                                            <div class="palette-colorswab-global fleft" style="background-color:{{color.color3}};" onclick="getColorID('{{color.globalColorsId}}','{{color.color3}}')"></div>
                                            <div class="palette-colorswab-global fleft" style="background-color:{{color.color4}};" onclick="getColorID('{{color.globalColorsId}}','{{color.color4}}')"></div>
                                            <div class="font--lightGray pointer" onclick="getThemeColors('{{color.color1}}','{{color.color2}}','{{color.color3}}','{{color.color4}}')">{{color.colorName}}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
        <div class="bottomNav">
            <a href="#">
                <div class="bottom-ContinueButton fright" ng-click="saveColorPalette()">CONTINUE</div>
            </a>
        </div>


    </div>




</body>
</html>
