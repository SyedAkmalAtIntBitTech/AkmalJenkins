<%-- 
    Document   : createemaillist
    Created on : Dec 30, 2015, 12:58:51 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="favicon.png"></link>
    <link rel="stylesheet" type="text/css" href="css/popup.css"></link>
    <script src="js/popup.js" type="text/javascript"></script>
    <title>BrndBot - Create email list</title>
</head>    
    <style>
        
        #addActionemllist{
            display:none;
        }
        
    </style>
<body>
    <div id="fade" class="black_overlay" ></div>
        <div id="addActionemllist">
    <!--Top Nav-->   
    <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-createEmail"> 
            <div class="pop-up-title pop-up-title-h1"> Create New Email List</div>
            <div class="pop-up-exit-container"  id="addactionlistClose">
             <a href="" class="pop-up-exit-icon">
                <img type="image/svg+xml" src="images/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
             </a>
            </div> 
            <div class="pop-up-inner">
<!--                <form class="form-horizontal" id="signform" >-->
                <div class="input-field-container ">
                    <div class="input-header"> Email List Name </div>
                    <input type="text" id="list_name" class="input-field-textfield input-placeholder" placeholder="Enter a name for the email list" ></input>
                </div>
                <div class="line-divider"></div>
                 <div class="input-field-container col-1of1">
                    <div class="input-header"> List Description </div>
                    <input id="list_description" type="text" class="input-field-textfield input-placeholder" placeholder="Enter List Description"></input>
                </div>
                <div class="input-field-container col-1of1">
                    <div class="input-header">Default from Address </div>
                    <input id="default_from_name" type="text" class="input-field-textfield input-placeholder" placeholder="Default from Address"></input>
                </div>
                <!--</form>-->
            </div>
        </div>
    </div>
    <div class="pop-up-cta-container pop-up-cta-container-newaction cur"  ng-click="createEmailList()">
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full">Create List</div>
        </a>
    </div>
    </div>
    </body>
</html>