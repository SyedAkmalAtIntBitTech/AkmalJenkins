<%-- 
    Document   : colorpopup
    Created on : Mar 22, 2016, 6:09:40 PM
    Author     : Rasim parvez at IntBit Technologies.
--%>
<input type="text" hidden id="globalColorsId">
    <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer" ></div>
    <div class="popUp_background_colors " id="addcolorpalette" ng-app="colors"  ng-controller="globalColors">
        <div class="popUp_wide"   > 
             <div class="content-area_header">
               
                 <div class="popUp_title fleft" id="addTheme"> Create a Color Theme</div>
            </div>
            <div class="inputSection col1of2 pushUp_20">
                <div class="input_Label">Name this color theme</div>
                <input type="text" class="input_Field" id="colorName"></input>
            </div>
            <div class="slatSection">
                <div class="palette-wrap-admin clear col1of1">
                    <div class="palette-swap-wrap fleft"> 
                        <div class="palette-admin col1of1" ng-style="{ backgroundColor: addTargetColorFirst }"></div>
                        <input ng-model='addTargetColorFirst' class="palette-beneath col1of1" id="firstColor" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                         <div class="palette-admin col1of1" ng-style="{ backgroundColor: addTargetColorSecond }"></div>
                        <input ng-model='addTargetColorSecond' class="palette-beneath col1of1" id="secondColor" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                         <div class="palette-admin col1of1" ng-style="{ backgroundColor: addTargetColorThird }"></div>
                        <input ng-model='addTargetColorThird' class="palette-beneath col1of1" id="thirdColor" ></input>
                    </div>
                     <div class="palette-swap-wrap fleft"> 
                          <div class="palette-admin col1of1" ng-style="{ backgroundColor: addTargetColorFourth }"></div>
                        <input ng-model='addTargetColorFourth' class="palette-beneath col1of1"  id="fourthColor"></input>
                    </div>
                </div>
            </div>
            <div >
  
   
</div>
       
            <div ng-click="addGlobalColors()" class="CTA_Button Button--Gray pushUp_30 fleft">Create Palette</div>
            
        </div>
            
        </div>
  
  
    
    