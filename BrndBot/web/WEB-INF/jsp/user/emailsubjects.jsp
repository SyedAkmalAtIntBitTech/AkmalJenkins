<%-- 
    Document   : emailsubjects
    Created on : Jan 8, 2016, 7:16:18 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html>
<head>
    <title>BrndBot - Email Subject</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/alertmessage.js" type="text/javascript"></script>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <title>BrndBot - Email Subject</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" href="css/popup.css">
    <script src="js/emailsubject.js" type="text/javascript"></script>
    <%! String mindbody_id = ""; %>
    <% mindbody_id = request.getParameter("id"); %>
    <%!
//        SqlMethods sql_methods = new SqlMethods();
        String email_subject = "";
        String from_emaileeditor = null;
    %>
    <%
        String categoryId = request.getParameter("categoryId");
        String subCategoryId = request.getParameter("subCategoryId");
        String mindbodyId = request.getParameter("mindbodyId");
        String email_subject = request.getParameter("emailSubject");
        
//        try {
//            sql_methods.session = request.getSession(true);          
//            email_subject=(String)sql_methods.session.getAttribute("email_subject");             
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }

    %>

</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main"> 
        <%@include file="header.jsp" %>
        <%@include file="navbar.jsp" %>

        <!--Top Nav-->   
        <div class="top-nav">
            <div class="page-title-bar col-1of1"> 
                <input type="hidden" value="<%=categoryId%>" id="categoryIdTag"/>
                <input type="hidden" value="<%=subCategoryId%>" id="subCategoryIdTag"/>
                <input type="hidden" value="<%=mindbodyId%>" id="mindbodyId"/>
                <input type="hidden" value="<%=email_subject%>" id="email_subject"/>
                <input type="hidden" class="input-field-textfield col-8of10" id="mindbodyid" placeholder="Enter Name of email" value="<%=mindbody_id%>"></input>
                <a class=" exit-button-icon" href="emailsubcategory?categoryId=<%=categoryId%>&subCategoryId=<%=subCategoryId%>&mindbodyId=<%=mindbodyId%>">
                    <div class="exit-button-detail">
                        <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                    </div>
                </a>
                <div class="page-title-with-back page-title-font">Email Details</div>
            </div>
        </div>
        <div class="sequence-page-background">
            <div class="sequence-page-content-container">
                <div class="sequence-page-header">Enter Subject Line</div>
                <div class="email-detail-selection col-1of1 fleft">
                    <div class="col-1of1 fleft">
                        <div class="col-9of10 fleft">
                            <% if (email_subject != null) {%>
                            <input id="emailsubject" class="input-field-textfield col-8of10" placeholder="Enter Name of email" name="emailsubject" type="text" value="<%= email_subject%>" required/>
                            <%} else {%>
                            <input id="emailsubject" class="input-field-textfield col-8of10" placeholder="Enter Name of email" name="emailsubject" type="text" required/>
                            <%}%>
                        </div>
                    </div>

                    <div class="col-1of1 pushUp fleft">
                        <div class="fleft">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                <a id="emailSubjectContinueButton" href="#">
                    <div class="bottom-continue-button button-text-1">Continue</div>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
