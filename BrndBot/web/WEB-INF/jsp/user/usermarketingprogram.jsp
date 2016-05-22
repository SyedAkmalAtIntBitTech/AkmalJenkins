<%-- 
    Document   : usermarketingprogram
    Created on : Jan 25, 2016, 6:00:00 PM
    Author     : Satyajit Roy at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BrndBot - User Marketing Program </title>
        <link rel="shortcut icon" href="images/favicon.png"/>
        <link rel="stylesheet" href="css/general.css">
        <script src="js/usermarketingprogram.js"></script>

        <style>.nav-tabs1{line-height: 0px !important;}*{box-sizing:initial;}</style>
        <%!
            String marketing_category_id = "";
            String marketing_program_id = "";
        %>

        <%
            marketing_category_id = request.getParameter("marketingCategoryId");
            marketing_program_id = request.getParameter("marketingProgramId");
        %>
    </head>
    <body ng-app>
        <div class="content-main" ng-controller="usermarketingprogram">
            <input type="hidden" value="<%=marketing_category_id%>" id="marketing_category_id"/>
            <input type="hidden" value="<%=marketing_program_id%>" id="marketing_program_id"/>
            <%@include file="header.jsp" %>      
            <%@include file="navbar.jsp" %>
            <div class="top-nav">
                <div class="page-title-bar col-1of1"> 
                    <a class=" exit-button-icon" href="marketingprogram?marketingCategoryId=<%=marketing_category_id%>">
                        <div class="exit-button-detail">               
                            <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                        </div>
                    </a>
                    <div class="page-title-with-back page-title-font">Create a Marketing Campaign</div>
                </div>
            </div>
                        
            <div class="top-nav-bar-offset"></div>
            <div class="content-wrap content-wrap-padding content-wrap-with-continue-bar">
                <div class="modal-pane modal-pane-width-full">
                    <div class="modal-pane-header-wrap">
                        <h2 class="">Marketing Program Details</h2>

                    </div>
                    <div class="sequence-pane-content-wrap">

                        <h3 class="sequence-pane-content-section-header">General Campaign Settings</h3>
                        <p class="sequence-pane-content-section-sub-header">These are the default settings for this campaign. They can be changed at any time in the campaign overview.</p>

                        <div class="input-wrap">
                            <label> Campaign Name </label>
                            <div class="input">
                                <input id="program_name" type="text" required="" >
                            </div>
                            <p> This is for internal use only</p>
                        </div>
                        <div class="input-wrap ">
                            <label> Date of Campaign Inserted Here </label>
                            <div class="input">
                                <input type="text" name="programdatetime" id="programdatetime" value="" readonly="">
                                <script>
                                            var picker = new Pikaday(
                                                    {
                                                        field: document.getElementById('programdatetime'),
                                                        firstDay: 1,
                                                        minDate: new Date(2000, 0, 1),
                                                        maxDate: new Date(2050, 12, 31),
                                                        yearRange: [2000, 2050]
                                                    });
                                </script>
                            </div>
                        </div>


                        <h3 class="sequence-pane-content-section-header">Campaign Link Settings<i>Not Required</i></h3>
                        <p class="sequence-pane-content-section-sub-header">This link will be able to be used throughout your templates. It should link to where your clients can take action e.g. sign up or learn more.</p>
                        <div class="input-wrap">
                            <label>URL </label>
                            <div class="input">
                                <input id="program_url" type="text" required="" onchange="validateurl()" placeholder="Ex. http://www.google.com">
                                <p> This can be copied from the browser</p>
                            </div>
                        </div>
                        <div class="input-wrap">
                            <label> Link Name </label>
                            <div class="input">
                                <input id="program_url_name" value="" type="text">
                                <p> This is for internal use only to reference this link URL. </p>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="continue-bar-offset"></div>
            </div>

            <div class="continue-bar">
                <a id="saveMarketingProgram" class="fright" ng-click="saveMarketingProgram()">CONTINUE</a>
            </div>

        </div>
    </body>
</html>