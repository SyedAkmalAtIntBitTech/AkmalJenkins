<%-- 
    Document   : allorganizations
    Created on : Mar 2, 2016, 6:23:44 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>All Organizations</title>
    </head>
    <body class="body-normal">
        <jsp:include page="addorganization.jsp"/>
        <jsp:include page="adminheader.jsp"/>
         <div class="content-area">
        <div class="content-area_header">
            <div class="header_path fleft"> All Organizations </div>
            <div class="CTA_Button Button--Blue fright" id="addorg">Add Organization</div>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Organization Name </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Set Type </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Org Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a style="text-decoration:none;color:#888;" href="organizationdetails.jsp"><div class="CTA_Button Button--Gray fright">Manage Org</div></a>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Org Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Group</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Org</div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Org Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Group</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Org</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
  
    </body>
</html>
