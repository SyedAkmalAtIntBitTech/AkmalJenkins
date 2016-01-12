<%-- 
    Document   : emaillistselection
    Created on : Jan 9, 2016, 11:56:23 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <title>BrndBot - Email List Selection</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <jsp:include page="basejsp.jsp" />
    <script>
        function selcheckbox(id){
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                $("#"+id).html(content);
            }
            else
            {
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
        }
    </script>
</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main">
    <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="exit-button-detail">
                   <a class=" exit-button-icon" href="">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                   </a>
            </div>
            <div class="page-title-with-back page-title-font">Email List Selection</div>
            <!--<div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>-->
        </div>
        <!--<div class="page-subnav-bar-with-dropdown"> 
              <div class="subnav-dropdown">
                 <span class="hub-dropdown-text">Email</span>
                  <object type="image/svg+xml" data="/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </object>
            </div>
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-link-active"> <a class="h3-active-subnav">Email Drafts</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Analytics</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email History</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Lists</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Settings</a></li>
                </ul>
            </div>
        </div>-->
    </div>
        <!--Main Content GENERIC--> 
            <div class="sequence-page-background">
        <div class="sequence-page-content-container">
            <div class="sequence-page-header">Choose a recipient list</div>
            <div class="email-list-selection fleft">
               <div class="col-1of1 fleft unit">
                    <div class="selection-container col-5p fleft"> 
                        <div id="chooselist" class="chooseList-icon" onclick="selcheckbox(this.id)"></div>
                    </div>
                    <div class="col-9of10 fleft ">
                        <div class="h2 col-1of1">Choose from your lists</div>
                        <div class="p chooseList-subtext col-1of1">Choose a recipient list</div>
                    </div>
                </div>
                <div class="col-1of1 fleft unit pushUp-15">
                    <div class="selection-container col-5p fleft"> 
                        <div id="uploademailaddrs" class="chooseList-icon" onclick="selcheckbox(this.id)"></div>
                    </div>
                    <div class="col-9of10 fleft">
                        <div class="h2 col-1of1">Enter or upload email addresses</div>
                        <div class="p chooseList-subtext col-1of1">Choose a recipient list</div>
                    </div>
                </div>
           
            
            <!--Inner Content Conatiner GENERIC-->
            
        </div>
        </div>
    </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
               <div class="bottom-continue-button button-text-1">Continue</div>
            </div>
        </div>
    </div>
<!--</div>-->
    </body>
</html>