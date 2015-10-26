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
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
        <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

        <link href="css/froala_editor.css" rel="stylesheet" type="text/css"/>
        <link href="css/froala_style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/plugins/code_view.css">
        <link rel="stylesheet" href="css/plugins/colors.css">
        <link rel="stylesheet" href="css/plugins/emoticons.css">
        <link rel="stylesheet" href="css/plugins/image_manager.css">
        <link rel="stylesheet" href="css/plugins/image.css">
        <link rel="stylesheet" href="css/plugins/line_breaker.css">
        <link rel="stylesheet" href="css/plugins/table.css">
        <link rel="stylesheet" href="css/plugins/char_counter.css">
        <link rel="stylesheet" href="css/plugins/video.css">
        <link rel="stylesheet" href="css/plugins/fullscreen.css">
        <link rel="stylesheet" href="css/plugins/file.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">

        <jsp:include page="basejsp.jsp"/>  
    </head>
    <body >
        <div class="row">
            <div class="col-md-1 col-lg-1 col-sm-1">
                <jsp:include page="leftmenu.html"/>
            </div>
            
            <div class="col-md-11 col-lg-11 col-sm-11 ">
<style>
    #edit{
position: relative;
top:0px;
font-family:"proxima-nova";
font-weight:500;
left: 0em; 
color: #2D4444;
    
}
</style>
                <div class="row">
                    <div class="col-md-6 col-lg-6 col-sm-6">
                        <div class="row">
                            <div id="editor">
                                <div id='edit' style="margin-top: 30px;">
                                </div>
                            </div>
<!--                            <div class="framediv">
                            </div>                           -->
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
                    <div class="col-md-3 col-lg-3 col-sm-3">
                        <div class="row padlft">
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
                                            <li> 
                                                <div>Preheader</div>
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
                    </div>
                    <div class="col-md-1 col-lg-1 col-sm-1">
                        <div class="row">
                            <div class="blockstyletab">      
                                <ul class="righttabs fontpnr">
                                    <li id="styletab">
                                        <image src='images/sidebar/Icons_styleButton.svg' class="styleimg"/>
                                        <p>STYLE</p>
                                    </li>
                                    <li id="blocktab">
                                        <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                                        <p>BLOCK</p>
                                    </li>
                                </ul>

                            </div>
                        </div>                            
                    </div>

                </div>


            </div>
        </div>
        <script>
            $("#blklist").click(function () {
                $(".addblkbtn").css("background-color", "#0f76a6").css("color", "#f6f7f7");
            });
            $("#styletab").click(function () {
                $("#styletab").css("background-color", "#ffffff").css("color", "#19587c");
                $("#blocktab").css("background-color", "transparent").css("color", "#19587c");
            });
            $("#blocktab").click(function () {
                $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                $("#styletab").css("background-color", "transparent").css("color", "#19587c");
            });
            $(document).ready(function () {
                $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            });
        </script>


        <script type="text/javascript" src="js/froala_editor.min.js" ></script>
        <script type="text/javascript" src="js/plugins/align.min.js"></script>
        <script type="text/javascript" src="js/plugins/code_view.min.js"></script>
        <script type="text/javascript" src="js/plugins/colors.min.js" ></script>
        <script type="text/javascript" src="js/plugins/emoticons.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
        <script type="text/javascript" src="js/plugins/image.min.js"></script>
        <script type="text/javascript" src="js/plugins/file.min.js"></script>
        <script type="text/javascript" src="js/plugins/image_manager.min.js"></script>
        <script type="text/javascript" src="js/plugins/line_breaker.min.js"></script>
        <script type="text/javascript" src="js/plugins/link.min.js"></script>
        <script type="text/javascript" src="js/plugins/lists.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_format.min.js"></script>
        <script type="text/javascript" src="js/plugins/paragraph_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/video.min.js"></script>
        <script type="text/javascript" src="js/plugins/table.min.js"></script>
        <script type="text/javascript" src="js/plugins/url.min.js"></script>
        <script type="text/javascript" src="js/plugins/entities.min.js"></script>
        <script type="text/javascript" src="js/plugins/char_counter.min.js"></script>
        <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/save.min.js"></script>
        <script type="text/javascript" src="js/plugins/fullscreen.min.js"></script>
        <script type="text/javascript" src="js/plugins/quote.min.js"></script>
        <script>
                            $(function () {
                       $('#edit').froalaEditor();                    
                    });
        </script>


    </body>
</html>
