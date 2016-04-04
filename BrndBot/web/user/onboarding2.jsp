<%-- 
    Document   : onboarding2
    Created on : Mar 31, 2016, 7:35:39 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <jsp:include page="hearderfiles.jsp"/>   
</head>    

<body class="body-normal" ng-app ng-controller="onboardingcontroller">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav" >
        <div class="topNav clear">
            <a href="onboarding1.jsp"><div class="topNav--BackButton fleft">
                <img  src="../images/userimages/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
            </div></a>                
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 2 of 4</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner" ng-init="getOrganizations()">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Set up a new company</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Welcome to BrndBot! First what is your company name?</div>
                    <div class="inputSection col-6of10">
                        <div class="input_Label">Please enter your company name:</div>
                        <input class="input_Field" id="companyName" placeholder="Company Name"/>
                    </div>
                     <div class="h3 font--lightGray pushUp_30">We have built BrndBot differently for different industries to give you the best experience possible.</div>
                    <div class="inputSection col-6of10 ">
                        <div class="input_Label">Please select an industry</div>
                        <select  id="industryDropDown"  class="input_Field select_Field">
                                <option class="input_Field" value="0">Please select an industry</option>
                                <option class="input_Field" ng-repeat ="organization in organizations" value="{{organization.organizationId}}">{{organization.organizationName}}</option>
                        </select>
<!--                        <select class="input_Field select_Field" id="industry">
                            <option class="input_Field">Please select an industry</option>
                        </select>-->
<!--                        <div class="input_Field">
                            <span>Please select an industry</span>
                            <img  src="../images/userimages/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"></img>
                        </div>-->
                        <div class="input_Instructions">Please select an industry</div>
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <div class="bottom-ContinueButton fright" ng-click="saveOrganization()">CONTINUE</div>
    </div>
    
    
    
    
</body>
</html>