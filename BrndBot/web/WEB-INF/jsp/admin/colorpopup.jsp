<%-- 
    Document   : colorpopup
    Created on : Mar 22, 2016, 6:09:40 PM
    Author     : Rasim parvez at IntBit Technologies.
--%>
<input type="text" hidden id="globalColorsId">
    <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer" ></div>
    <div class="popUp_background_colors " id="createColorCode" ng-app="colors"  ng-controller="globalColors">
        <div class="popUp_wide" ng-init="allColors"  > 
             <div class="content-area_header">
                <div class="popUp_title fleft" id="updateTheme"> Update a Color Theme</div>
                <div id="deleteGlobalColor" class="CTA_Button Button--Delete fright" ng-click="deleteColor()">Delete Color</div>
                
            </div>
            <div class="inputSection col1of2 pushUp_20">
                <div class="input_Label">Name this color theme</div>
                <input type="text" class="input_Field" id="colorNameInput"></input>
            </div>
            <div class="slatSection">
                <div class="palette-wrap-admin clear col1of1">
                    <div class="palette-swap-wrap fleft"> 
                        <div class="palette-admin col1of1" ng-style="{ backgroundColor: targetColorFirst }"></div>
                        <input ng-model='targetColorFirst' class="palette-beneath col1of1" id="firstValue" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                         <div class="palette-admin col1of1" ng-style="{ backgroundColor: targetColorSecond }"></div>
                        <input ng-model='targetColorSecond' class="palette-beneath col1of1" id="secondValue" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                         <div class="palette-admin col1of1" ng-style="{ backgroundColor: targetColorThird }"></div>
                        <input ng-model='targetColorThird' class="palette-beneath col1of1" id="thirdValue" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                          <div class="palette-admin col1of1" ng-style="{ backgroundColor: targetColorFourth }"></div>
                        <input ng-model='targetColorFourth' class="palette-beneath col1of1"  id="fourthValue"></input>
                    </div>
                </div>
            </div>
            <div >
  
   
</div>
       
            <div ng-click="updateGlobalColors()" class="CTA_Button Button--Gray pushUp_30 fleft">Update Palette</div>
            
        </div>
            
        </div>
  
  
    
    