<%-- 
    Document   : model
    Created on : Jun 24, 2015, 1:59:24 PM
    Author     : intbit
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BrndBot - Social Layout Model</title>     
    </head>
    <body class="body-normal" ng-app  ng-controller="">
        <%@include file="socialeditortemplatehead.jsp" %>
        <div class="content-area">
            <div class="content-area_header" ng-init="">
                <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="${pageContext.request.contextPath}/admin/printtemplates"> Print Templates</a>  &gt; </div>
                <div class="header_title fleft" id="organizationNameDiv">Add New Template</div>
            </div>
            <div class="inputSection col1of4" >
                <div class="input_Label">Name this template</div>
               <input id="templateName" class="input_Field" type="text"/>
            </div>

            <div id="">
                <div id='printtemplateedit' style="margin-top: 30px;">
                    <div id="tabs">
                        <ul>
                            <li><a href="#tabs-1">Text</a></li>
                            <li><a href="#tabs-2">Image</a></li>
                            <li><a href="#tabs-3">Button</a></li>
                            <li><a href="#tabs-4">Color/SVG Block</a></li>
                        </ul>
                        <div id="tabs-1">
                            <p>
                                <span class="selectedElement">Selected Item: None</span>
                            </p>
                            <p>
        
                                <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="textX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="textY" value="0" /></span>
                                <input type="button" class="coordButton" value="done">
                            </p>
                            <p>
                                Enter Text: <br /><textarea id="textArea"></textarea>
                            </p>
                            <hr>
                            <p>
                                <input type="button" class="textButton" id="boldButton" value="bold" />
                                <input type="button" class="textButton" id="italicButton" value="italic" /><br />
                                <input type="button" class="alignButton" id="leftButton" value="left" />
                                <input type="button" class="alignButton" id="centerButton" value="center" />
                                <input type="button" class="alignButton" id="rightButton" value="right" />
                            </p>

                            <p>
                                Font Size: <select id="textSize">
                                  
                                </select>
                            </p>

                            <p>
                                Font Family: <select id="textFontFamily">

                                </select>

                            </p>
                            <p>

                                    Font Color: <select id="fontColor">
                                                    <option value="#F27821">Font-Color-1</option>
                                                    <option value="#00A8BD">Font-Color-2</option>
                                                    <option value="#EE7766">Font-Color-3</option>
                                                    <option value="#EEEEEE">Font-Color-4</option>
                                                    <option value="#FFFFFF">Font-Color-5</option>
                                                    <option value="#353333">Font-Color-6</option>
                                                </select>

                            </p>
                            <hr>
                            <p>
                                Drop shadow:<br /><br />

                                Color: <input type="text" class='basic' id="dropShadowColorPick" value="black" />
                                Blur: <input id="blur" maxlength="2" size="2" value="0" /> px  <br /><br />
                                H Shadow: <input id="hShadow" maxlength="2" size="2" value="0" /> px 
                                V Shadow: <input id="vShadow" maxlength="2" size="2" value="0" /> px
                            </p>
                            <hr>
                            <p>
                                Leading / Line Height: 
                                <input maxlength="3" size="3" value="15" id="lineHeight" /> px
                            </p>
                            <p>
                                Kerning/ Letter Space: 

                                <input maxlength="3" size="3" value="0" id="letterSpace" /> px
                            </p>
                            <hr>
                            <p>
                                Opacity: <input type="text" id="opacity" maxlength="6" size="6" value="1" />
                            </p>
                            <p>
                                Rotate: <input type="text" id="rotate" maxlength="4" size="4" value="0" />deg
                            </p>
                        </div>
                        <div id="tabs-2">
                            <p>
                                <span class="selectedElement">Selected Item: None</span>
                            </p>
                            <p>
                       
                                <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="imageX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="imageY" value="0" /></span>
                                <input type="button" class="coordButton" value="done">
                            </p>
                            <p>
                                <span class="size">Height=0px, Width=0px</span>
                            </p>
                            <p>
                                Opacity: <input type="text" id="opacityImage" maxlength="6" size="6" value="1" />
                            </p>
                            <p>
                                Blend Color: 
                                <select id="blendColorSelect" >
                                    <option value="normal">normal</option>
                                    <option value="multiply">multiply</option>
                                    <option value="screen">screen</option>
                                    <option value="overlay">overlay</option>
                                    <option value="darken">darken</option>
                                    <option value="lighten">lighten</option>
                                    <option value="color-dodge">color-dodge</option>
                                    <option value="color-burn">color-burn</option>
                                    <option value="hard-light">hard-light</option>
                                    <option value="soft-light">soft-light</option>
                                    <option value="difference">difference</option>
                                    <option value="exclusion">exclusion</option>
                                    <option value="hue">hue</option>
                                    <option value="saturation">saturation</option>
                                    <option value="color">color</option>
                                    <option value="luminosity">luminosity</option>
                                </select>
                                <input type='text' class='basic' id='blendColorPick' value='black' />
                            </p>  
                            <hr>
           
                            <p>
                                <label id="selectImage" title="Please upload all your images using FTP client in the following folder: admin/AdminLayoutBackgroundImages">Please Select Image:</label><br /><br />
                                <select id="adminBackgroundImage"  title="Please upload all your images using FTP client in the following folder: admin/AdminLayoutBackgroundImages"><option value="none">---select---</option></select>

                            </p>
                            <hr>
                            <p>
                                Filters:
                            </p>   

                            <table>
                                <tr>
                                    <td>
                                        Blur:</td><td> <input id="blurFilter" class="filters" maxlength="2" size="2" value="0" /> px  </td></tr><tr><td>
                                        Grayscale:</td><td> <input id="grayscaleFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                                        Sepia:</td><td> <input id="sepiaFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                                        Saturate:</td><td> <input id="saturateFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr><tr><td>
                                        Hue Rotate:</td><td> <input id="hueRotateFilter" class="someMoreFilters" maxlength="2" size="2" value="0" /> deg  </td></tr><tr><td>
                                        Invert:</td><td> <input id="invertFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                                        Brightness:</td><td> <input id="brightnessFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr><tr><td>
                                        Contrast:</td><td> <input id="contrastFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr></table>

                            <p>
                                Drop shadow:<br /><br />

                                Color: <input type="text" class='basic' id="dropShadowFilterColorPick" value="black" />
                                Blur: <input class="dropShadowFilters" id="blurDropShadowFilter" maxlength="2" size="2" value="0" /> px  <br /><br />
                                H Shadow: <input class="dropShadowFilters" id="hShadowDropShadowFilter" maxlength="2" size="2" value="0" /> px 
                                V Shadow: <input class="dropShadowFilters" id="vShadowDropShadowFilter" maxlength="2" size="2" value="0" /> px
                            </p>


                        </div>
                        <div id="tabs-3">
                            <p>
                                <span class="selectedElement">Selected Item: None</span>
                            </p>
                            <p>
                   
                                <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="buttonX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="buttonY" value="0" /></span>
                                <input type="button" class="coordButton" value="done">
                            </p>
                            <p>
                                Button Style: <select id="buttonSelect">
                                    <option value="1">Button1</option>
                                    <option value="2">Button2</option>
                                    <option value="3">Button3</option>
                                    <option value="4">Button4</option>
                                    <option value="5">Button5</option>
                                </select>


                            </p>
                        </div>
                        <div id="tabs-4">
                            <p>
                                <span class="selectedElement">Selected Item: None</span>
                            </p>
                            <p>
                            
                                <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="blockX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="blockY" value="0" /></span>
                                <input type="button" class="coordButton" value="done">
                            </p>
                            <p>
                                <input type="button" id="uploadSVG" value="Refresh SVGs"><br /><br />
                                <label id="selectSVG" title="Please upload all your SVGs using FTP client in the following folder: admin/AdminLayoutSVG">Please Select an SVG:</label><br /><br />
                                <select id="adminSVG"  title="Please upload all your SVGs using FTP client in the following folder: admin/AdminLayoutSVG"><option value="none">---select---</option></select>

                            </p>
                             <p>
                                <input type="checkbox" id="circleCheckBox"> Circle
                            </p>

                            <p>
                                Block Size:
                            </p>
                            <p>
                                Width: <input type="text" id="blockWidth" maxlength="6" size="6" value="80" /> px 
                            </p>
                            <p>
                                height: <input type="text" id="blockHeight" maxlength="6" size="6" value="40" /> px
                            </p>

                            <p>
                                Opacity: <input type="text" id="opacityBlock" maxlength="6" size="6" value="1" />
                            </p>
                            <p>
                                Block Color: <input type='text' class='basic' id='blockColor' value='black' />
                            </p>
                                <p>
                                <select id="blockColorFromDropDown">
                                                <option>Color-1</option>
                                                <option>Color-2</option>
                                                <option>Color-3</option>
                                                <option>Color-4</option>
                                                <option>Color-5</option>
                                                <option>Color-6</option>

                                </select>
                            </p>


                            <p>
                                <input type="button" class="blockButton" id="blockButton" value="Apply" />
                            </p>

                            <hr>
                            <p>
                                Drop shadow:<br/><br/>

                                Color: <input type="text" class='basic' id="dropShadowColorPickBlock" value="black" />
                                Blur: <input class="dropShadowBlock" id="blurDropShadowBlock" maxlength="2" size="2" value="0" /> px  <br /><br />
                                H Shadow: <input class="dropShadowBlock" id="hShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px 
                                V Shadow: <input class="dropShadowBlock" id="vShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px
                            </p>
                        </div>
                    </div>
        
                    <div id="main">

                        <br />
                        <div id="slider" title="Please don`t slide after element is placed"></div>

                        <script>
                            $(function () {
                                $(function () {
                                    $(document).tooltip();
                                });
                                $("#slider").slider({
                                    min: 1,
                                    max: 1000,
                                    value: 500,
                                    range: "min",
                                    slide: function (event, ui) {
                                        var divZoom = ui.value / 1000;
                                        $(".container").css("zoom", "" + divZoom);
                                    }
                                });
                            });
                        </script>
                        <!-- End -->

                        <div class="container">

                        </div>

                    </div>
                
                    <div id="right">
                        <center>
                            <p>
                                Select Element: <select id="elementText">
                                </select>
                            </p>
                            <p>
                                <input type="button" class="rightButton" id="addTextButton" value="Add Text" />
                                <input type="button" class="rightButton" id="deleteTextButton" value="Delete Text" />
                            </p>

                            <p>
                                <input type="button" class="rightButton" id="addImageButton" value="Add Image" />
                                <input type="button" class="rightButton" id="deleteImageButton" value="Delete Image" />
                            </p>

     
                            <p>
                                <input type="button" class="rightButton" id="addBlockButton" value="Add Block" />
                                <input type="button" class="rightButton" id="deleteBlockButton" value="Delete Block" />
                            </p>
                            <p>
                                <input type="button" class="rightButton" id="addLogoButton" value="Add Logo" />
                                <input type="button" class="rightButton" id="deleteLogoButton" value="Delete Logo" />
                            </p>
                            <p>
                                <input type="button" class="rightButton" id="addSVGButton" value="Add SVG" />
                                <input type="button" class="rightButton" id="deleteSVGButton" value="Delete SVG" />
                            </p>
                            <div class='col-md-10' style="position: absolute; left: 1050px; top:70px; width: 600px; ">
                                <ul id='list2' class='col-md-10' >
                                    <li id="lab"></li>
                                </ul> 
                            </div>

                    </div>
                </div>
            </div>
            <div class="inputSection col1of4">
                <div class="input_Label">Please upload an image:</div>
            </div>
            <div class="inputSection uploadwidth">
                <div id="triggerfile" class="md-button gray-button" >Upload</div>
                <input type="file" name="filesToUpload[]" id="filesToUpload" class="upload fileupld" onchange="changeimagetext()" file-model="myFile">
            </div>
            <div class="input_Label fleft"> </div>
            <a href="${pageContext.request.contextPath}/admin/addemailtemplate"><div class="CTA_Button Button--Blue fleft pushUp_10" >Create New Template</div></a>
        </div>
     

    </body>
</html>