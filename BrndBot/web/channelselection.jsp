<%-- 
    Document   : channelselectionv2
    Created on : Dec 11, 2015, 11:57:11 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>
<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String mindbodyid;%>
<% mindbodyid = request.getParameter("id");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Channel Selection</title>
    <meta charset="UTF-8"/>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="css/favicon.png"></link>
    <script src="js/alert_message.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/channelselection.js" type="text/javascript"></script>
    <style>#header,#email,#social,#print,#download{display:none;}</style>
      <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
      %>

        <%
            try {
                sql_methods.session = request.getSession(true);
                sql_methods.session.setAttribute("email_subject", null);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                if(category_id==null)
                {
                    category_id = sql_methods.session.getAttribute("category_id").toString();
                    sub_category_name = sql_methods.session.getAttribute("sub_category_name").toString();
                    sub_category_id = sql_methods.session.getAttribute("sub_category_id").toString();
                }
                else
                {
                    sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                    sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                    sql_methods.session.setAttribute("category_id", category_id);
                }

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        %>
        <jsp:include page="basejsp.jsp" />
</head>    

<body ng-app>
    <input type="hidden" name="mindbodyid" id="mindbodyid" value="<%=mindbodyid%>"/>
    <input type="hidden" name="category_id" id="category_id" value="<%=category_id%>"/>
    <input type="hidden" name="sub_category_id" id="sub_category_id" value="<%=sub_category_id%>"/>
    <input type="hidden" name="sub_category_name" id="sub_category_name" value="<%=sub_category_name%>"/>
    
    <!--SideNav-->
    <div class="content-main">
    <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1">
            <a class="exit-button-icon" href="dashboard.jsp">
            <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            </div>
            </a>
            <div id="channelhead" class="page-title-regular page-title-font">Channel Selection</div>
            <div class="page-cta-container"></div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="dashboard-background" ng-controller="selectPromoteMediaController">
            <div class="dashboard-content" ng-init="checkTemplateAvailability()">
                <div id="header" class="h1"> How would you like to promote this?</div>
                <div class="button-row col-1of1">
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_templates != 0"  data-hint="Social" >
                        <a onclick="selected_media('social', '<%=mindbodyid%>')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/bb_channelIcons_Social.svg" id="social" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="email_templates != 0" data-hint="Email" >
                        <a onclick="selected_media('emailsubject', '<%=mindbodyid%>')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/bb_channelIcons_Email.svg" id="email" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_templates_print != 0" data-hint="Print">
                        <a onclick="selected_media('print', '<%=mindbodyid%>')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/bb_channelIcons_Print.svg" id="print" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_templates_download != 0" data-hint="Download">
                        <a onclick="selected_media('image', '<%=mindbodyid%>')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/bb_channelIcons_imgDownload.svg" id="download" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                 
                </div>
            </div>
        </div>
    </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
             REMOVE BUTTON HERE
               <div class="remove-button-detail md-button button-text-1">Delete Selected Actions</div>

            </div>-->
<!--        </div>
        </div>
</div>-->
    </body>
</html>