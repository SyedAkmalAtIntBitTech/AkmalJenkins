<%-- 
    Document   : globalimage
    Created on : Apr 5, 2016, 7:59:13 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Global Image</title>
         <link rel="shortcut icon" href="../images/favicon.png"/>  
    </head>
    <body class="body-normal">
        <jsp:include page="adminheader.jsp"/>
        <jsp:include page="popupimage.jsp"/>
         <div class="content-area">
        <div class="content-area_header">
            <div class="header_path fleft"> Global Images</div>
            <div id="addGlobalImage" class="CTA_Button Button--Blue fright">Add Image</div>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Image Name</span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Date Added</span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Image Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                    <span class="listCol_Text fleft"> Jan 3rd</span>
                </div>
                    <div class="listCol col1of4 fleft">
                        <a>
                            <div class="CTA_Button Button--Gray fright">View / Edit</div>
                        </a>
                    </div>
                </li>
            </ul>
            
        </div>
    </div>
         
         
         
    </body>
</html>
