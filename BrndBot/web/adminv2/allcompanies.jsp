<%-- 
    Document   : allcompanies
    Created on : Mar 2, 2016, 5:07:27 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="format-detection" content="telephone=no"/>
    <link rel="shortcut icon" href="../images/favicon.png"></link>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="admincss/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="admincss/admin.css"></link>
    <title>All Companies</title>
    </head>
    <body  class="body-normal">
      <jsp:include page="organisationheader.jsp"/>
      <div class="content-area">
        <div class="content-area_header">
            <div class="header_path fleft">All Companies</div>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Company Name </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Organization </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Company Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright"><a style="text-decoration:none;color:#888;" href="companydetails.jsp">Manage Company</a></div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Company Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Company</div>
                    </div>
                </li>  
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Company Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Company</div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
    </body>
</html>
