<%-- 
    Document   : loadingoverlay
    Created on : Feb 19, 2016, 3:01:47 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BrndBot - Loading Overlay</title>
        <style>
            #loadoverlaydiv{
                background-color: white;
                opacity: 0.7;
                height: 100%;
                width: 100%;
                z-index: 9999999;
                position: fixed;
            }
            #loadingimg{
                position: fixed;
                top:40%;
                left:45%;
            }
            #loadoverlaydiv{display: none;}
        </style>
    </head>
    <body>
        <div id="loadoverlaydiv">
            <img id="loadingimg" src="images/YogaLoadingGif.gif" width="100px" height="100px" />
        </div>
    </body>
</html>
