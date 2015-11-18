<%-- 
    Document   : selectpromotion
    Created on : Oct 9, 2015, 7:39:51 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <title>Marketing Programs</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <link href="css/marketingprograms.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="basejsp.jsp"/>
        <script src="js/marketingprogram.js" type="text/javascript"></script>
         
    </head>
    <%!
       String category_id = ""; 
    %>
    
    <%
        category_id = request.getParameter("categoryid");
    %>
    <body ng-app >
        <input id="categoryidHidden" type="hidden" value="<%=category_id%>">
        <input id="programidHidden" type="hidden" value="0">
        <div class="row" ng-controller="controllerMarketingCategories">
            <div class="row">
            <div class="col-md-1 col-lg-1 col-sm-2 ">
                 <jsp:include page="leftmenu.html"/>
            </div>
            <div class="col-md-11 col-lg-11 col-sm-10">
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12">
                    <div class="row">
                        <div class="col-md-8 col-lg-8 col-sm-12">
                            <p id="" class="selmarkprog fontpns">Select a category marketing program: </p>
                        </div>
                        <div class="col-md-4 col-lg-4 col-sm-12">
                            <button ng-click="submitclick()" type="submit" class="contbtn1 button button--moema button--text-thick button--text-upper button--size-s">continue</button>
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">                        
                            <p class="cattxt fontpnr">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                    </div>-->
                    </div>
               </div> 
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12">
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-sm-12" ng-init="getMarketingPrograms()">
                            <div class="mptxt fontpnr" >Marketing Programs</div>
                            <hr class="markline">
                            <ul class="promolist1">
                                <li id="mp" class="mrkli" ng-repeat="program in programs">
                                   <div id="{{program.id}}" class="prognamelst fontpns"  
                                        ng-click="showhtmldata(program.id,program.html_data)">
                                       {{program.name}}
                                   </div>
                                </li>
                            </ul>
                            </div>
                            <div class="col-md-6 col-lg-6 col-sm-12">
                                <div class="descript fontpnr">Description</div>
                                <hr class="descline ">
                                <div class="htmldatacontainer">
                                <div id="html_data" class="newclientpromo fontpnr">
                                    New Client Intro Offer Promotion
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>                                  
        </div>
            <script>
                $(document). ready(function (){
                    $(".htmldatacontainer").css("overflow","hidden");
                });
            </script>
    </body>
</html>
