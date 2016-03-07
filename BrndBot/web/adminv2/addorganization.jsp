<%-- 
    Document   : addorganization
    Created on : Mar 2, 2016, 6:48:45 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

      <title>Add Organization</title>
    </head>
    <body>
        <div id="addorgpopupdiv"></div>
        <div id="addorganizationpopup" class="popUp_background">
        <div  class="popUp">
             <div class="content-area_header">
                <div class="popUp_title fleft"> Create an Organization </div>
            </div>
            <div class="inputSection col1of2">
                <div class="input_Label">Name this Organization?</div>
                <input id="orgname" class="input_Field" type="text" placeholder="Organization Name" />
                <div class="input_Label pushUp_30">Is this an organization or a group?</div>
                <select class="input_Field" id="orgdropdown">
                    <option class="input_Field">organization</option>
                    <option class="input_Field">group</option>
                </select>
                <div id="createorg" class="CTA_Button Button--Gray fleft pushUp_30">Create this Organization</div>
            </div>
        </div>
        </div> 
    </body>
</html>
