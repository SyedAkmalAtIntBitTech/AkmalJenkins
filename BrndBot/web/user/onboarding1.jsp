<%-- 
    Document   : onboarding1
    Created on : Mar 31, 2016, 7:31:08 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="../css/usercss/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/General-Utility.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/Navigation.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/Pane.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/Page-Structure.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/onboarding.css"></link>
    <link rel="stylesheet" type="text/css" href="../css/usercss/Form-Input.css"></link>
    <link rel="shortcut icon" href="../images/favicon.png">
</head>    

<body class="body-normal">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <div class="topNav--TitleBar fleft">
                <span class="topNav--TitleBar--Title fleft h2">Welcome Sunrise Studios!</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
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
