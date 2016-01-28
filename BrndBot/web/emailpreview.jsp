<%-- 
    Document   : emailpreview.jsp
    Created on : 27 Jan, 2016, 1:42:48 PM
    Author     : satyajit-roy
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-5.css">
    <link rel="stylesheet" type="text/css" href="css/normalize5.css">
    <link rel="shortcut icon" href="favicon.png">
</head>    

<body>
    <div id="fade" class="black_overlay" ></div>
    <!--Top Nav-->  
    <div id="email_previewdiv">
        <div class="pop-up-background">
        <div class="pop-up-container-emailPreview"> 
              <a class=" exit-button-detail-ep link svg" href="" id="closePreview">
                    <img type="image/svg+xml" src="images/Icons/close.svg" class="closeemailpreview" style="cursor:pointer;"> </img>
                </a>
            <div class="pop-up-title-emailpreview "> 
               
                <div class="emailPreview-header fleft">Email Preview</div>
            </div>
            <!--inner-->
            <div class="pop-up-inner-ep height400">
                <div class="emailPreviews col-1of1 fleft"> 
                    <div class="emailPreview-desktop-col  fleft">
                        <div class="emailPreview-headers">Desktop Preview</div>
                        <div class="iphoneshow img-responsive" id="popup" style="display: block; height: 300px; width: 295px; margin-left: 215px; margin-top: 50px; border-color: transparent; background-image: url(http://localhost:8080/BrndBot/images/imac27.png); background-color: rgb(255, 255, 255); background-size: contain; background-repeat: no-repeat;">
                            <div class="content">  
                                <iframe id="dynamictable5" style="position: relative; width: 768px; border: none; height: 615px; top: -187px; left: -239px; transform: scale(0.3326); background-color: rgb(255, 255, 255);" src="<%=iframeUrl%>"></iframe>                   
                            </div>
                        </div>
<!--                        <div class="desktopshow">
                            <iframe id='dynamictable1' class="desktoppreview" src='<%=iframeUrl%>'></iframe> 
                        </div>                        -->
                    </div>
                    <div class="emailPreview-mobile-col fleft">
                        <div class="emailPreview-headers">Desktop Preview</div>
                        <div class="iphoneshow img-responsive" id="" style="display: block; height: 370px; width: 254px; margin-left: 85px; margin-top: 32px; border-color: transparent; background-image: url(http://localhost:8080/BrndBot/images/Phone.svg); background-color: rgb(255, 255, 255); background-size: contain; background-repeat: no-repeat;">
                            <div class="content">  
                                <iframe id="dynamictable6" style="position: relative; width: 358px; height: 573px; top: -109px; border: none; left: -77px; transform: scale(0.4999); background-color: rgb(255, 255, 255);" src="<%=iframeUrl%>"></iframe>                   
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>