<%-- 
    Document   : emaildetails
    Created on : Apr 6, 2016, 5:39:25 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Email details</title>
    </head>
    <body class="body-normal">
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>    
      <%@include file="emailschedule.jsp" %>    
    
        <div class="contentWrap--withNoSideNav">
             <a class="exit-button-icon" href="sendemail">
        <div class="topNav clear menuWidth">
             
            <div class="topNav--BackButton fleft">
              
               <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
                </a>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft topNavigation">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane--600px">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Email Details</div>
                    <div class="pane_header_CTABOX fright">
                         <a href="Email-PreviewPopUp.html"><div class="CTA_Button Button--Gray">Preview Email</div></a>
                    </div>
                </div>
                <div class="pane_content">
                    <div class="inputSection col-1of1">
                        <div class="input_Label">Please enter a subject line for this email:</div>
                        <div class="input_Field">Subject Line</div>
                        <div class="input_Instructions">Please select an industry</div>
                    </div>
                    <div class="overlay-section-wrap clear col-1of1 clear">
                        <div class="section-Header font--darkGray pushUp_30">EMAIL AUTOMATION DETAILS</div>
                        <div class="overlay-detail-wrap fleft">
                            <div class="input_Label">Days After Added To List</div>
                            <div class="input_Field">
                                <span>Days</span>
                                <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                            </div>
                        </div>
                        <div class="overlay-detail-wrap fleft">
                            <div class="input_Label">Email List</div>
                            <div class="input_Field">
                                <span>Time</span>
                                <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    
    <div class="bottomNav">
        
            <div id="emailschedule" class="bottom-ContinueButton fright">CONTINUE</div>
      
    </div>
    </body>
</html>