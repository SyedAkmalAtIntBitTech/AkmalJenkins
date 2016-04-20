<%-- 
    Document   : emailsequence-editor
    Created on : Apr 6, 2016, 4:09:56 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="shortcut icon" href="images/favicon.png">
        <title>emailsequence</title>
    </head>
    <body class="body-normal">
       
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %> 
      <jsp:include page="froalaeditor_styles.jsp"/>
      <jsp:include page="emailpreviewpopup.jsp"/>
      
        <div class="contentWrap--withNoSideNav noScroll">
        <div class="topNav clear menuWidth">
              
            <div class="topNav--BackButton fleft">
               <a class="exit-button-icon" href="emailexternalsource.jsp">
          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            
            </a>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft topNavigation">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <a href="/Marketing_Hubs/Email_Hub/MarketingHubs-EmailHub-Drafts.html"><div class="CTA_Button Button--Gray">Save as a Draft</div></a>
                </div>
                <div class="topNav--TitleBar--CTABox fright pushRight_30">
                    <div id="previewEmailsPopUp" class="CTA_Button Button--Gray">Preview Email</div>
                </div>
            </div>
           
        </div>
             
        <div class="topNav--offset"></div>
       
        <div class="email-LeftCol fleft" >
            
            <div class="email col-1of1 frolaeditor">
              
                <div id="editor" style="margin-top: 1%">
                <div id='edit'>
                                        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%" bgcolor="#EEEEEE" style="border-collapse:collapse;"><tr><td valign="top">
    <center style="width: 100%;">
       
         <div style="max-width: 680px; margin:auto;">
             
            <table cellspacing="0" cellpadding="0" border="0" align="left" bgcolor="#ffffff" width="100%" style="max-width: 680px;">
                
                <tr>
                    <td id="date1" userbackgroundcolor="color1" externalvalue="mindbody.enrollment.enrollmentstartdatetime"  userdateepoch="d 'at' h:m a" valign="middle" style="text-align: left; padding: 10px 55px; font-family: sans-serif; font-size: 25px; mso-height-rule: exactly; line-height: 110%; color: #FFFFFF ; background-color:#2BAADF;" class="mobile-padding">
                        FRIDAY AT 9:30 AM
                    </td>
                </tr>
              
                
                 
                <tr>
                    <td>
                        <img id="image12" src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" width="" height="" alt="alt_text" border="0" align="center" style=" max-width: 680px;" class="fluid"/>
                    </td>
                </tr>
                 
                
                
               
                <tr>
                    <td id="text1" userfontcolor="color1" externalvalue="mindbody.enrollment.enrollmentname" style="padding: 30px 55px 0px 55px; text-align: left; font-family: arial; font-size: 30px; mso-height-rule: exactly; line-height: 120%; color: #2BAADF; font-weight: bold; background-color:#FFFFFF" class="mobile-padding  h-adj-25px">
                        Workshop Name Goes Here
                    </td>
	            </tr>
               
                <tr>
                    <td style="padding:20px 55px 0px 55px; " class="mobile-padding">
                        <table align="left">
                            <tr>
                                <td style="width:50px;">
                                    <img id="image12" src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" width="50" height="50" alt="alt_text" border="0" align="center" style=" max-width: 50px; max-height: 50px; border-radius:300px" class="fluid">
                                </td>
                                <td id="text2" externalvalue="mindbody.enrollment.staffname" style="padding: 0px 15px; ; text-align: left; font-family: arial; font-size: 18px; mso-height-rule: exactly; line-height: 50px; color: #5c627a; font-weight: normal; background-color:#FFFFFF" class="mobile-padding  h-adj-20px">
                                    Teacher Name
                                </td>
                            </tr>
                     </table>
                </tr>
              
                <tr>
                    <td style="padding: 20px 55px 0px 55px; text-align: left; font-family: arial; font-size: 14px; mso-height-rule: exactly; line-height: 150%; color: #757677; font-weight: normal; background-color:#FFFFFF" class="mobile-padding">
                        This workshop is going to be great for all ages and skill levels. It is taught by one of our favorite instructors, make sure you don't miss out on all the great benefits of this workshop. 
                    </td>
	            </tr>
             
				<tr>
                    <td style="padding: 30px 55px 40px 55px;background-color:#FFFFFF; text-align: left; mso-height-rule: exactly; color: #ffffff;">
                       <table cellspacing="0" cellpadding="0" border="0" align="left" style="">
                            <tr>
                                <td style="text-align: left;">
                                    <a id="general1" userbackgroundcolor="color1" href="http://www.google.com" style=" font-family: sans-serif; font-size: 13px; line-height: 110%;padding: 10px 25px; text-align: center; text-decoration: none; display: block; border-radius: 3px; font-weight: bold;  background-color: #2BAADF; color: #FFFFFF; " >Sign Up Today</a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height:100px;"></td>
                </tr>
               
    
            </table>
        </div>
    </center>
</table>
                </div>
            </div>

               
            </div>
        </div>
        <div class="email-RightCol fright"> 
            <div class="email-subNav col-1of1 clear">
                <a href="/Email_Sequence/EmailSequence-Editor.html"><div class="email-subNav-tab-active col-1of2 fleft">Add a Block</div></a>
                <a href="/Email_Sequence/EmailSequence-Editor-Style.html"><div class="email-subNav-tab col-1of2 fleft">Block Styles</div></a>
            </div>
            <div class="email-RightCol-contentWrap col-1of1">
                <div class="email-blockSlat">Text Header</div>
                <a href="/Email_Sequence/Email-Editor-ChooseExternalData.html"><div class="email-blockSlat">Workshop Block</div></a>
            </div>
        </div>
            
    <div class="bottomNav">
        <a href="sendemail.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE TO SEND LIST</div>
        </a>
    </div>
        </div>
    
    </body>
</html>
