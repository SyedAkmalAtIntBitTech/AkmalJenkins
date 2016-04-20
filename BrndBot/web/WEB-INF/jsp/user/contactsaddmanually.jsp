<%-- 
    Document   : contactsaddmanually
    Created on : Apr 6, 2016, 5:37:04 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Contacts add manually</title>
    </head>
    <body class="body-normal">
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>   
      <div class="contentWrap--withNoSideNav noScroll">
          <div class="topNav clear menuWidth">
              
            <div class="topNav--BackButton fleft">
               <a class="exit-button-icon" href="emailexternalsource.jsp">
          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            
            </a>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft topNavigation">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                
            </div>
           
        </div>
       <div class="contentWrapInner" style="padding-top:calc(12vh - -10px);">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Choose who to send this email to:</div>
                </div>
                <div class="pane_content">
                    <div class="inputSection col-1of1">
                        <div class="input_Label">Email List</div>
                            <div class="input_Field">
                                <span>Email List Dropdown</span>
                                <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                            </div>
                    </div>
                    <div class="subSection pushUp_20 col-1of1 ">
                        <div class="col-1of1 clear">
                            <div class="section-Header font--darkGray fleft">ADD CONTACTS MANUALLY</div>
                            <a href="Email-SendTo.html"><div class="fright">
                                    <object type="image/svg+xml" data="/Final-Icons/Pop-Up-Exit-Button.svg" class="fleft closeSubSection-icon" style="cursor:pointer;"> </object>
                                </div></a>
                        </div>
                        <div class="inputSection col-1of1">
                            <div class="input_Label">Enter Email Lists</div>
                            <div class="input_Field-light input_Field_lg">
                                <span>Email List Dropdown</span>
                            </div>
                            <div class="input_Instructions">Separate by commas</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
    </div>
    </div>
    <div class="bottomNav">
        <a href="emaildetails.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    </body>
</html>
