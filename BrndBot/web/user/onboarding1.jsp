<%-- 
    Document   : onboarding1
    Created on : Mar 31, 2016, 7:31:08 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <jsp:include page="hearderfiles.jsp"/>  
</head>    

<body class="body-normal">
<!--    SideNav
    
    Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <div class="topNav--TitleBar topNav--TitleBar1 fleft">
                <span class="topNav--TitleBar--Title fleft h2">Welcome Sunrise Studios!</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray" >Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">What would you like to do today?</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Lets get started! First, would you like to join an existing company, or set up a new one?</div>
                    <div class="selectCompany-wrap clear">
                        <div class="selectButton-wrap fleft pushRight_45">
                            <img  src="../images/userimages/Onboarding/addCompany.svg" style="cursor:pointer;"></img> 
                            <div class="selectButton-label">Set up a New Company</div>
                        </div>
                        <div class="selectButton-wrap fleft">
                            <img  src="../images/userimages/Onboarding/addUser.svg" style="cursor:pointer;"></img> 
                            <div class="selectButton-label">Join an Existing Company</div>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <a href="onboarding2.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    
    
    
    
</body>
</html>
