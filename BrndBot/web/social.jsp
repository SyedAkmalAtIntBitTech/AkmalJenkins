
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/slat.css">
    <link rel="shortcut icon" href="favicon.png">
    <meta charset="UTF-8" >
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/angular.min.js"></script>  
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
    <script src="js/tabcontent.js" type="text/javascript"></script>
    <link href="tabs/tabcontent.css" rel="stylesheet" type="text/css"/>
    <script src="js/socialsettings.js" type="text/javascript"></script>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>

    <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <%! 
            Object code = "";
            String ImageName="";
            String user_name = "";
        %>
        <% 
            try{
                code = (Object)request.getAttribute("objkey");
                user_name = (String)request.getAttribute("user_profile_name");
                ImageName=request.getParameter("image");
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                
            }
        
        %>
          
        <jsp:include page="basejsp.jsp" />
</head>    

<body ng-app ng-controller="controllerSocial" id="controllerSocial">
    <!--SideNav-->
    <%@include file="navbarv2.jsp" %>
     
    <script src="js/social.js" type="text/javascript"></script>   
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <!--<div class="exit-button-detail"></div>-->
            <div class="page-title-regular page-title-font">Your Social Hub</div>
        </div>
        <div class="page-subnav-bar-with-dropdown"> 
            <div class="subnav-dropdown pushright"  id="socialclick">
                 <span class="hub-dropdown-text">Social</span>
                  <img type="image/svg+xml" src="images/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </img>
            </div>
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-link-active" ng-click="getFacebookDetails()" id="fbsetting"> <a class="h3-active-subnav">Facebook Settings</a></li>
                    <li class="top-subnav-links" ng-click="getTwitterDetails()" id="twsetting"> <a class="h3">Twitter Settings</a></li>
                </ul>
            </div>
            <div class="dropdown-hub" id="socialdropdown">
                <ul class="dropdown-inner">
                    <li class="dropdown-section fleft col-1of1 " id="emailhubli">
                        <a href="emaillists.jsp">
                            <div><img type="image/svg+xml" src="images/Icons/Hub-Dropdowns_email.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                            <div class="fleft col-6of10 dropdown-label-active">Email Hub</div>
                        </a>                        
                    </li>
                    <li class="dropdown-section col-1of1 fleft" id="socialhubli" >
                        <a href="social.jsp">
                            <div><img type="image/svg+xml" src="images/Icons/Hub-Dropdowns_social.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                            <div class="fleft col-6of10 dropdown-label">Social Hub</div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
    <div class="page-background">
        <div class="page-content-container email-draft-page">
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container">
                <div class="fleft content">
                    <div class="page-content-title h2">Your Social Settings</div>
                    <!--List Starts Here-->
                    
        <div id="popup">
            <div id="content">
                <center>
                    <table id="fbmanagepages">
                        
                    </table>
                </center>
            </div>   
        </div>
        <div id="twitterpopup" style="display: none">
            <div id="content">
                <p>Please click the url and get the pin</p>
                <div id="twitterlink">wait...</div>
                Enter the pin<input type="text" id="pinTextBox" name="pinTextBox"><br><br><br>
                <input id="setPin" type="button" class="btn btn-primary" value="ok">
<!--                <input id="closetwitter" type="button" class="btn btn-primary" value="cancel">-->
            </div>   
        </div>        
        <div>

            <div class="row">

                <div class="col-md-10 col-md-offset-1">
                    <div class="col-md-10 ">
                        <p id="t3" class="MH1"> Setting page</p>


                        <div style="width:500px; margin:0px; padding:120px 0 40px;">

                            <div class="tabcontents">
                                <div id="view1" style="width:550px; height:230px ">

                                    <b>Facebook</b>
                                    <div id="fbpagename" ng-init="getFacebookDetails()">
                                        Profile Name : {{facebookPage.user_profile_page}}<br>
                                        Default Managed Page Name : {{facebookPage.fb_default_page_name}}<br>
                                        <button id="facebook" class="button button--moema button--text-thick button--text-upper button--size-s" name="change">change</button>
                                        <button id="fbclear" class="button button--moema button--text-thick button--text-upper button--size-s" name="fbclear" ng-click="clearFacebookDetails()">clear</button><br>
                                    </div>
                                    
                                    
                                </div>
                                <div id="view2" style="width:750px; height:auto;" >
                                    <b>Twitter</b>
                                    <div id="twpagename" >
                                        Profile Name : {{twitterPage.twitter_user_name}}<br>
                                        <button id="twitter" class="button button--moema button--text-thick button--text-upper button--size-s" name="change">change</button>
                                        <button id="twitterclear" class="button button--moema button--text-thick button--text-upper button--size-s" name="twitterclear" ng-click="clearTwitterDetails()">clear</button><br>
                                    </div>
                                    
                                </div>

                            </div>
                            <input type="hidden" id="fbusername" name="fbusername" value="<%= user_name %>"/>
                            <input type="hidden" id="fbaccessTokenSend" name="fbaccessTokenSend">
                            <input type="hidden" id="pagenameSend" name="pagenameSend">
                            <input type="hidden" id="fbdefaultAccessToken" name="fbdefaultAccessToken">
                            <input type="hidden" id="twaccessTokenSend" name="twaccessTokenSend" >
                            <ul class="tabs1" data-persist="true">
                                <li><a href="#view1" style="width:180px;" ng-click="getFacebookDetails()">Facebook</a></li>
                                <li><a href="#view2"  style="width:180px;" ng-click="getTwitterDetails()">Twitter</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>


            </div>
        </div>
<!--                    <div class="emailSettings-container fleft">
                       <div class="col-1of1 fleft">
                            <div class="col-7of10 fleft">
                                <div class="h4" style="">
                                    SOCIAL SETTINGS HERE
                                </div>
                            </div>
                        </div>
                      
                    </div>-->
                </div>
            </div>            
        </div>
        </div>
    </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
                 REMOVE BUTTON HERE
               <div class="remove-action-detail md-button button-text-1">Remove Selected Action(s)</div>
-->
            </div>
        </div>
         </div>
</div>
    </body>
</html>