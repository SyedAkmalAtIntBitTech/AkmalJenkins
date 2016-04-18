<%-- 
    Document   : sendemail
    Created on : Apr 6, 2016, 5:02:40 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <link rel="shortcut icon" href="images/favicon.png">
        <title>Send Email</title>
    </head>
    <body class="body-normal">
           <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>    
        <div class="contentWrap--withNoSideNav">
        <div class="topNav clear menuWidth">
            <a href="EmailSequence-Editor.html"><div class="topNav--BackButton fleft">
                    
             <a class="exit-button-icon" href="emailsequence.jsp">
          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            
            </a>
                </div></a>
            <div class="topNav--TitleBar--withBackButton fleft topNavigation">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Choose Recepients</div>
                </div>
                <div class="pane_content">
                    <div class="inputSection col-1of1">
                        <div class="input_Label">Email List</div>
                            <div class="input_Field">
                                <span>Email List Dropdown</span>
                                <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                            </div>
                    </div>
                    <div class="inputSection col-1of1 ">
                        <a href="contactsaddmanually.jsp"><div class="CTA_Button Button--Gray fleft">Add Contacts Manually</div></a>
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    
    <div class="bottomNav">
        <a href="emaildetails.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE TO DETAILS</div>
        </a>
    </div>
    </body>
</html>
