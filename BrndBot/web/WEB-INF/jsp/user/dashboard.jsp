<%-- 
    Document   : dashboard
    Created on : Apr 6, 2016, 3:22:21 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="shortcut icon" href="images/favicon.png">
        <title>Dashboard</title>
</head>    

<body class="body-normal">
     <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>          
    <!--Main Content Wrap-->
    <div class="contentWrap--withSideNav">
        <div class="topNav clear">
            <div class="topNav--TitleBar clear">
                <span class="topNav--TitleBar--Title fleft h2">Your Dashboard</span>
            </div>
        </div>
        <div class="contentWrapInner">
            <div class="topNav--offset"></div>
            <div class="pane pane--fullwidth">
                <div class="pane_header clear">
                    <div class="pane_title h1 fleft">What would you like to do today?</div>
                </div>
                <div class="pane_content clear">
                    
                    <div class="dashboard-MainCTA-col fleft">
                        <div class="dashboard-MainCTA "> 
                            <div class="dash-icon">
                                <img type="image/svg+xml" src="images/dashIcons-01.svg" class="dash-svg" style="cursor:pointer;">
                                 
                                </div>
                            <div class="dashboard-mainCTA-subtext">Content to be provided here</div>
                            <div class=" dashboard-mainCTA-button "><a href="socialsequence"><div class="Button--Blue Dash_Button">Create a Social Post</div></a></div>
                        </div>
                    </div>
                    
                    <div class="dashboard-MainCTA-col fleft">
                        <div class="dashboard-MainCTA "> 
                            <div class="dash-icon">
                                <img type="image/svg+xml" src="images/dashIcons-02.svg" class="dash-svg" style="cursor:pointer;">
                                 
                                </div>
                            <div class="dashboard-mainCTA-subtext">Content to be provided here</div>
                            <div class=" dashboard-mainCTA-button "><a href="emailcategory"><div class="Button--Blue Dash_Button">Create an Email</div></a></div>
                        </div>
                    </div>
                    
                    <div class="dashboard-MainCTA-col fleft">
                        <div class="dashboard-MainCTA "> 
                            <div class="dash-icon">
                                   <img type="image/svg+xml" src="images/dashIcons-01.svg" class="dash-svg" style="cursor:pointer;">
                                </div>
                            <div class="dashboard-mainCTA-subtext">Content to be provided here</div>
                            <div class=" dashboard-mainCTA-button "><div class="Button--Blue Dash_Button">Create a Campaign</div></div>
                        </div>
                    </div>
                    
                </div>
            </div>   
        </div>
    </div>
    
</body>
</html>