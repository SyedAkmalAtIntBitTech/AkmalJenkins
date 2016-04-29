<%-- 
    Document   : addcontact.jsp
    Created on : 8 dec, 2015, 12:14:25 PM
    Author     : Satyajit Roy At IntBit Technologies
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="shortcut icon" href="favicon.png"/>
    <link rel="stylesheet" href="css/popup.css"/>
    <title>BrndBot - Add Contact</title>
</head>    

<body>
    
    <div id="fade" class="black_overlay"></div>
    <div id="addContact">
        <div class="pop-up-background">
                <div class="pop-up-exit-container">
                    <a>
                    <div class="pop-up-exit-icon" id="close"> <img type="image/svg+xml" src="images/Close.svg" class="exit-button-icon" style="cursor:pointer;"> </img></div>
                    </a>
                </div> 
            <div class="pop-up-container pop-up-container-newContact"> 
                <div class="pop-up-title pop-up-title-h1"> Add a New Contact</div>
                <div class="pop-up-inner">
                    <div class="input-field-container">
                        <div class="input-header"> Email Address </div>
                        <input type="text" class="input-field-textfield1 input-placeholder" placeholder="Enter Email Address" id="emailId"/>
                        <input type="hidden" id="uuid" value=""></input>
                        <input type="hidden" id="type" value=""></input>
                    </div>
                    <div class="line-divider"></div>
                    <div class="optional-text">Optional</div>
                     <div class="input-field-container">
                        <div class="input-header"> First Name </div>
                        <input type="text" value="" class="input-field-textfield1 input-placeholder" placeholder="Enter First Name" id="firstName"/>
                    </div>
                     <div class="input-field-container">
                        <div class="input-header"> Last Name </div>
                        <input type="text" value="" class="input-field-textfield1 input-placeholder" placeholder="Enter Last Name" id="lastName"/>
                    </div>

                </div>

            </div>
        </div>
        <div class="pop-up-cta-container pop-up-cta-container-newContact cur" ng-click="updateEmailID()">
        <a href="">
            <div class="pop-up-cta-button-full"> Save Contact</div>
        </a>
    </div> 
    </div>
</body>
</html>