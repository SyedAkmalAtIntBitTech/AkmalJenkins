<%-- 
    Document   : onboardinglogouploaded
    Created on : Mar 31, 2016, 8:21:35 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <a href="onboarding4logo.jsp">
                <div class="topNav--BackButton fleft">
                <img  src="../images/userimages/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
                </div>
            </a>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 3 of 4</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Upload your logo</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Congratulations your logo has been successfully uploaded!  </div>
                    <div class="logo-Uploaded-wrap clear">
                          <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940985f55b8d6cc5f7d7_Deafult-Image_350x350.jpg" class="logo-Uploaded fleft">
                          <img  src="../images/userimages/Valid-Check.svg" class="logoUpload-Success-svg fleft" style="cursor:pointer;"/>
                    </div>
                    <div class="Add_Button Button--Gray logoUpload--button">Change Logo</div>
                </div>
            </div>
        </div>   
    </div>
    <div class="bottomNav">
        <a href="onboardingpalette.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    
    
    
    
</body>
</html>
