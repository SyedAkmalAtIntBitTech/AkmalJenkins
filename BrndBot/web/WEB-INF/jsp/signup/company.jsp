<%-- 
    Document   : newjsp
    Created on : 1 Jun, 2016, 6:02:21 PM
    Author     : sandeep
--%>
    <!--SideNav-->
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav" >
        <div class="topNav clear">
            <a href="#"><div class="topNav--BackButton fleft">
                    <img  src="images/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
            </div></a>                
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 2 of 5</span>
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
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    <div class="bottomNav">
        <a href="#/datasource"> <div class="bottom-ContinueButton fright">CONTINUE</div></a>
    </div>
