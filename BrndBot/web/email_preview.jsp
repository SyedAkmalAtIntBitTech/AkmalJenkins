<%-- 
    Document   : email_preview.jsp
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
            <div class="pop-up-inner-ep">
                <div class="emailPreviews col-1of1 fleft"> 
                    <div class="emailPreview-desktop-col  fleft">
                        <div class="emailPreview-headers">Desktop Preview</div>
                        <div class="desktop-email-html"> HTML GOES HERE</div>
                    </div>
                    <div class="emailPreview-mobile-col fleft">
                        <div class="emailPreview-headers">Mobile Preview</div>
                        <div class="mobile-email-html">
                             <iframe id='dynamictable' style='position:relative;background-color:#FFF;' src='<%=iframeUrl%>'></iframe> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>