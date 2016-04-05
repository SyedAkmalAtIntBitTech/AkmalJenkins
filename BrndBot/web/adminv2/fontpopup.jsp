<%-- 
    Document   : fontpopup
    Created on : Mar 26, 2016, 12:00:35 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<div class="popUp_background" id="fontPopup" ng-app="colors" ng-controller="globalColors">
     <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer" ></div>
     <div class="popUp" ng-init="fontDisplay()">
         <input type="hidden" id="globalFontsId">
             <div class="content-area_header">
                <div id="addFont" class="popUp_title fleft"> Create a Font</div>
                <div id="editFont" class="popUp_title fleft"> Edit a Font</div>
                <div id="deleteGlobalFont" class="CTA_Button Button--Delete fright" ng-click="deleteFont()">Delete Font</div>
            </div>
         
              <div class="inputSection col1of2 pushUp_20">
                  <div id="nameFont"><div class="input_Label">Name this Font</div>
                      <input id="fontName" class="input_Field"></input></div>
                      
                      
                     <div id="editFonts"><div class="input_Label">Edit this Font</div>
                         <input id="editFontName" class="input_Field"</input></div> 
                
                  <div id="fontFamilyCssName"><div class="input_Label pushUp_30">Font CSS Name</div>
                    <input id="fontFamilyName" class="input_Field fleft"></input></div>
                    
                    <div id="editFontFamilyCssName"><div class="input_Label pushUp_30">Font CSS Name</div>
                    <input id="editFontFamilyName" class="input_Field fleft"></input></div>
               
               <div id="uploadOnEdit">
            <label class="fileContainer">
                <div id="triggerFile" class="CTA_Button Button--Gray--text fleft ">Upload TTF</div>
                <input type="file" id="fileName"  >
            </label>
        </div>
                  
                  
                  <div id="uploadTTF">
            <label class="fileContainer">
                <div id="triggerFile" class="CTA_Button Button--Gray--text fleft ">Upload TTF</div>
                <input type="file" id="uploadFileName" value=""/>
            </label>
        </div>
                
                <div id="createFont" ng-click="createFont()" class="CTA_Button Button--Gray pushUp_30 fleft">Create Font</div>
                <div id="editFontDetails" ng-click="updateFont()" class="CTA_Button Button--Gray pushUp_30 fleft">Edit Font</div>
            </div>
            

        </div>
            
        </div>