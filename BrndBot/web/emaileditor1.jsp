<%-- 
    Document   : emaileditornew
    Created on : Oct 21, 2015, 9:13:55 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Editor</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
        <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>

    </head>
    <body ng-app="myapp">
        <div class="row">
            <div class="col-sm-1 col-md-1 col-lg-1 halfcol"><jsp:include page="leftmenu.html"/></div>
            <div class="col-sm-7 col-md-7 col-lg-7">
                        <div class="row">
                            <div class="col-sm-12 col-md-12 col-lg-12 bgcolor"> 
                            <div class="emleditorhead fontpnr">Froala Editor</div> 
                            <div class="framediv">
                                <iframe class="frm" src=""></iframe>
                            </div>    
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-sm-6">
                                <div class="btmdiv">
                                    <div class="row">
                                        <div class="col-lg-7 col-md-7 col-sm-7">
                                            <div class="editemail fontpnr">Edit this Email</div>
                                        </div>   
                                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1 col-md-offset-1">
                                            <div class="mobileprev fontpnr">Mobile Preview</div>
                                        </div>
                                        <div class="col-lg-1 col-md-1 col-sm-1">
                                            <div class="emledtrsavebtn"><input class="emailedtrsave fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="save"></div>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </div> 
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="blockselection">     
                    <div class="row">
                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="selblock fontpnr">Select a Block</div>
                        </div>
                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="addblkdiv"><input class="addblkbtn fontpns " type="button" value="Add Block"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="selblklinediv"><hr class="selblkline"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <ul id="blklist" class="blocklist fontpnr">
                                <li ng-repeat="blocks in datalists"> 
                                    <div id="{{blocks.block_id}}" ng-init="showImageOfBlock(blocks.block_id, blocks.mindbody_query)">{{blocks.block_name}}</div>
                                </li>
                                <li> 
                                    <div>Intro</div>
                                </li>
                                <li>
                                    <div>Article</div>
                                </li>
                                <li>
                                    <div>Image</div>
                                </li>
                                <li> 
                                    <div>Featured Workshop</div>
                                </li>
                                <li> 
                                    <div>Upcoming Workshop</div>
                                </li>
                                <li>
                                    <div>Promotion</div>
                                </li>
                                <li>
                                    <div>Social Icons</div>
                                </li>
                                <li> 
                                    <div>Sale</div>
                                </li>
                                <li> 
                                    <div>Featured Workshop</div>
                                </li>
                                <li> 
                                    <div>Upcoming Workshop</div>
                                </li>
                                <li>
                                    <div>Promotion</div>
                                </li>
                                <li>
                                    <div>Social Icons</div>
                                </li>
                                <li> 
                                    <div>Sale</div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-1 col-md-1 col-lg-1">
                <div class="blockstyletab">      
                    <ul class="righttabs fontpnr">
                        <li id="styletab">
                            <image src='images/sidebar/Icons_styleButton.svg' class="styleimg"/>
                            <p>STYLE</p>
                        </li>
                        <li id="blocktab"  ng-click="showBlocks()">
                            <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                            <p>BLOCK</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
            <script>
                $("#blklist").click(function (){
                    $(".addblkbtn").css("background-color","#0f76a6").css("color","#f6f7f7");
                });
                $("#styletab").click(function(){
                    $("#styletab").css("background-color","#ffffff").css("color","#19587c");
                    $("#blocktab").css("background-color","transparent").css("color","#19587c");
                });
                $("#blocktab").click(function(){
                    $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
                     $("#styletab").css("background-color","transparent").css("color","#19587c");
                });
               $( document ).ready(function() {
                $("#blocktab").css("background-color","#ffffff").css("color","#19587c");
                });
                
            </script>
    </body>
</html>
