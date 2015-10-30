<%-- 
    Document   : marketingprogram
    Created on : Oct 20, 2015, 3:12:54 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <title>Marketing Programs</title>
        <meta charset="UTF-8">
        <%@ include file="checksession.jsp" %>
        <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/marketingprogramlist.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <jsp:include page="basejsp.jsp"/>
         
    </head>
    <body>
        <div class="row">
            <div class="col-md-1 col-lg-1 col-sm-1" style="width:45px;">
            <jsp:include page="leftmenu.html"/> 
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 padtab"> 
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 "> 
                     <div class="progtabs"> 
                         <ul class="proghislist fontpnr">
                             <li id="crtprogpg"><p>Create <br>New Program</p></li>
                             <li id="lstcurprogs"><p>Current Programs</p></li>
                             <li id="lstpstprogs"><p>Past <br>Programs</p></li>
                         </ul>
                     </div>
                    </div>
               </div>
            </div>
            <div class="col-md-9 col-sm-9 col-lg-9">
                <div class="row">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 ">
                            <div id="curprogs" class="pstprog fontpns">Your Current Programs</div>
                            <div id="pastprogs" class="curprog fontpns">Your Past Programs</div>
                        </div>
                    </div>
                        <div id="prog">
                            <div class="row">
                                <ul class="programsheader">
                                    <li><div class="prognamhead fontpnr">Programs</div></li>
                                    <li><div class="progactlfthead fontpnr">End Date</div></li>
                                    <li><div class="progactpsthead fontpnr">Number of Posts Left</div></li>
                                </ul>
                            </div>
                            <div class="row">
                                    <hr class="pstprogline">
                            </div>
                            <div class="row">
                                <ul class="programsheader">
                                    <li><div class="lstprog fontpns">Program Name</div>
                                        <div class="lststrtdt fontpnr">Marketing Program start Date | Template name </div>
                                    </li>
                                    <li >
                                    <ul class="li1">
                                        <li>
                                            <div class="lstlftact fontpnr">Oct 15</div>
                                        </li>
                                        <li >
                                            <div class="lstcomp fontpnr">15</div>
                                        </li>
                                        <li>
                                            <button class="viewbtn">View</button>
                                        </li>
                                    </ul>
                                    </li>
                                 </ul>
                            </div>
                            <div class="row">
                                <hr class="pstprogline">
                            </div>
                       </div>
                    </div>
                    <div id="email">
                        <div class="row">
                                <ul class="programsheader">
                                    <li><div class="prognamhead fontpnr">Programs</div></li>
                                    <li><div class="progcurlfthead fontpnr">End Date</div></li>
                                </ul>
                        </div>
                        <div class="row">
                                <hr class="pstprogline">
                        </div>
                        <ul class="programsheader">
                            <li><div class="lstprog fontpns">Program Name</div>
                                <div class="lststrtdt fontpnr">Marketing Program start Date | Template name </div>
                            </li>
                            <li>
                            <ul class="li1 lftcur">
                                <li>
                                    <div class="lstlftactcur fontpnr">Oct 15</div>
                                </li>
                                <li>
                                    <button class="viewbtn">View</button>
                                </li>
                            </ul>
                            </li>
                         </ul>
                        <div class="row">
                                <hr class="pstprogline">
                            </div>
                    </div>
                    
                </div>
            </div>
        </div>  
            <script>
                
                $( document ).ready(function() {
                    if(window.location.href.indexOf("curprog") !== -1)
                    {
                         $("#pastprogs").hide();
                    $("#curprogs").show();
                    $("#email").show();
                     $("#prog").hide();
                        alert("type : Currentprograms");
                    }
                    else if(window.location.href.indexOf("pastprog") !== -1)
                    {   
                        $("#pastprogs").show();
                    $("#prog").show();
                    $("#curprogs").hide();
                    $("#email").hide();
                        alert("type : Pastprogrograms");
                    }
                    else
                    {
                        $("#curprogs").show();
                        $("#email").show();
//                         $("#pastprogs").hide();
                        $("#pastprogs").hide();
//                        $("#prog").hide();
                        $("#prog").hide();
                        alert("type not found!!");
                    }
//                        var= curprogs;
//                        curprogs=location.search;
//                        alert(curprogs);
//                    window.location = "marketingprogramlist.jsp?type=curprogs";    
//                    $("#email").hide();
                });
                $("#lstpstprogs").click(function (){
                    $("#pastprogs").show();
                    $("#prog").show();
                    $("#curprogs").hide();
                    $("#email").hide();
                    location.href="marketingprogramlist.jsp?type=pastprog";
                });
                $("#lstcurprogs").click(function (){
                    $("#pastprogs").hide();
                    $("#curprogs").show();
                    $("#email ").show();
                     $("#prog").hide();
                     location.href="marketingprogramlist.jsp?type=curprog";
                });
                
                $("#crtprogpg").click(function (){
                   document.location.href="marketingcategory.jsp"; 
                });
            </script>
                
    </body>
</html>
