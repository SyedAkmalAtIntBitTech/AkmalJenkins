<%-- 
    Document   : marketingprogramaddaction
    Created on : Dec 30, 2015, 7:39:20 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" type="text/css" href="css/popup.css"></link>
     <script src="js/popup.js" type="text/javascript"></script>
</head>    

<body >
    <div id="fade" class="black_overlay" ></div>
        <div id="addAction">
    <!--Top Nav-->   
    <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-newaction"> 
            <div class="pop-up-title pop-up-title-h1"> Create New Action</div>
             <div class="pop-up-exit-container" id="addactionClose">
                <a href="" class="pop-up-exit-icon">
                    <img onclick = "closeOverlay();" type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </a>
            </div> 
            <div class="pop-up-inner">
                <div class="input-field-container ">
                    <div class="input-header"> Action Name </div>
                    <div class="input-field-textfield input-placeholder">Enter Email Address</div>
                </div>
                <div class="line-divider"></div>
                <div class="cols-2">
                     <div class="input-field-container col-4of10 fleft pushright">
                        <div class="input-header"> Action Date </div>
                        <div class="input-field-textfield input-placeholder">Enter Action Date</div>
                    </div>
                     <div class="input-field-container col-4of10 fleft">
                        <div class="input-header"> Action Time </div>
                        <div class="input-field-textfield input-placeholder">Enter Action Time</div>
                    </div>
                </div>
                 <div class="input-field-container col-1of1">
                    <div class="input-header"> Action Type </div>
                    <div class="input-field-textfield input-placeholder">Enter Last Name</div>
                </div>               
            </div>
            
        </div>
    </div>
    <div class="pop-up-cta-container pop-up-cta-container-newaction">
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full"> Save Action</div>
        </a>
    </div> 
        </div>
    </body>
</html>