<%-- 
    Document   : addonetimeactions
    Created on : Mar 25, 2016, 6:16:51 PM
    Author     : development
--%>


<div class=" popUp_background" id="editMarketingProgramsPopup">
    <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
    <div class="popUp" >
        <div class="content-area_header">
            <div class="popUp_title fleft"> Create a One Time Action </div>
        </div>
        <div class="inputSection">
            <div class=" col1of2 pushUp_10 col-padding fleft" >
                <div class="input_Label">Action Name</div>
                <input class="input_Field" type="text" id="oneTimeActionName" data-ng-model="marketingProgramActions[indexvalue].title">            
            </div>		
            <div class=" col1of2 pushUp_10 col-padding fright">
                <div class="input_Label">Action Type</div>
                <select class="input_Field" id="actionTypeOneTimeActions" data-ng-model="marketingProgramActions[indexvalue].type">
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
                <input class="input_Field" placeholder="# of Days" type="text" id="sctionsNoOfDays" data-ng-model="marketingProgramActions[indexvalue].days">
            </div>
            <div class=" col1of2 pushUp_10 col-padding fright">
                <div class="input_Label">Action Time</div>
                <input class="input_Field" type="time" id="actionTime" data-ng-model="marketingProgramActions[indexvalue].time">
            </div>
        </div>
        <div class="inputSection">
            <div id="saveChangedOneTimeAction" class="CTA_Button Button--Gray fleft pushUp_30" ng-click="saveChangedOneTimeAction()">Save this Action</div>
        </div>
    </div>

</div>
