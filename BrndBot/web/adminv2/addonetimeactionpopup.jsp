<%-- 
    Document   : addonetimeactionpopup
    Created on : Mar 28, 2016, 4:21:42 PM
    Author     : development
--%>

<div class=" popUp_background" id="addMarketingProgramsPopup">
    <div id="addMarketingProgramsPopupDiv" class="popUp_backgroundlayer"></div>
    <div class="popUp" >
        <div class="content-area_header">
            <div class="popUp_title fleft"> Create a One Time Action </div>
        </div>
        <div class="inputSection">
            <div class=" col1of2 pushUp_10 col-padding fleft" >
                <div class="input_Label">Action Name</div>
                <input class="input_Field" type="text" id="newOneTimeActionName">            
            </div>		
            <div class=" col1of2 pushUp_10 col-padding fright">
                <div class="input_Label">Action Type</div>
                <select class="input_Field" id="newActionTypeOneTimeActions">
                    <option class="input_Field">Facebook</option>
                    <option class="input_Field">Twitter</option>
                    <option class="input_Field">Email</option>
                    <option class="input_Field">Reminder</option>
                </select>
            </div>
        </div>
        <div class="inputSection">
            <div class=" col1of2 pushUp_10 col-padding fleft" >
                <!--                    <div class="input_Label">Scheudling Type</div>
                                     <select class="input_Field">
                                        <option class="input_Field">DROPDOWN</option>
                                    </select>-->
                <div class="input_Label">Days</div>
                <input class="input_Field" placeholder="# of Days" type="number" id="newActionsNoOfDays">
            </div>
            <div class=" col1of2 pushUp_10 col-padding fright">
                <div class="input_Label">Action Time</div>
                <input class="input_Field" type="time" id="newActionTime" >
            </div>
        </div>
        <div class="inputSection">
            <div id="createOneTimeAction" class="CTA_Button Button--Gray fleft pushUp_30" ng-click="createOneTimeAction()">Create this Action</div>
        </div>
    </div>

</div>