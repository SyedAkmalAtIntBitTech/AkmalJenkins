<%-- 
    Document   : onboarding3
    Created on : Mar 31, 2016, 8:05:50 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
      <jsp:include page="hearderfiles.jsp"/>
</head>    
<script>
         $(document).ready(function (){
//             var compName= localStorage.getItem("companyName");
//             var orgName=$("#industry").val();
//             localStorage.setItem("industryName", orgName);
            var industryName=localStorage.getItem("industryName");
         });
     </script>
<body class="body-normal">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <a href="onboarding2.jsp">
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
                    <div class="pane_title fleft h2">Connect with your other tools</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">BrndBot syncs with your other tools. If you are not using any of these services, just select 'none'!</div>
                    <div class="inputSection col-6of10 ">
                        <div class="input_Label">Please select a third party service</div>
                        <select class="input_Field select_Field clear" id="service">
                            <option class="input_Field">Service drop down</option>
                        </select> 
<!--                        <div class="input_Field clear">
                            <span class="fleft">Service drop down</span>
                             <object type="image/svg+xml" data="/Final-Icons/dropdown.svg" class="dropdown-svg fright" style="cursor:pointer;"> </object>
                        </div>-->
                    </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <a href="onboarding3activation.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    
    
    
    
</body>
</html>
