<%-- 
    Document   : onboarding3
    Created on : Mar 31, 2016, 8:05:50 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
     <title>BrndBot-On Boarding</title>
      <jsp:include page="header.jsp"/>
</head>    
<body class="body-normal" ng-app ng-controller="onboardingcontroller">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav" ng-init="getServices()">
        <div class="topNav clear">
            <a href="#">
                <div class="topNav--BackButton fleft">
                <img  src="images/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
                </div>
            </a>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 3 of 5</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Connect with your other tools</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">BrndBot syncs with your other tools. If you are not using any of these services, just select 'none'!</div>
                    <div class="inputSection col-6of10 ">
                        <div class="input_Label">Please select a third party service</div>
                    
                        <select class="input_Field select_Field clear" id="services" >
                            <option class="input_Field" value="0">none</option>
                            <option class="input_Field" ng-repeat="service in services" value="{{service.externalSourceId}}">{{service.externalSourceName}}</option>
                        </select> 
<!--                        <div class="input_Field clear">
                            <span class="fleft">Service drop down</span>
                             <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                        </div>-->
                        
                    </div>
                    <div class="inputSection col-1of1 " id="mindbodyStudioDiv">
                        <div class="input_Label">Please enter Mindbody Studio Id</div>
                        <div class="col-6of10">
                            <input class="input_Field " type="text" id="mindbodyStudioId">
                        </div>
                        <a href="" id="actiovationLink" target="_blank"><div class="input_Label" id="activationLinkDiv"></div></a>
                        <div class="CTA_Button Button--Gray clear-colors" ng-click="getActivationLink()">Activation</div>
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <!--<a href="onboarding3activation.jsp">-->
        <div id="serviceContinueButton" class="bottom-ContinueButton fright" ng-click="saveServices()">CONTINUE</div>
        <!--</a>-->
    </div>
    
    
    
    
</body>
</html>
