<%-- 
    Document   : organisationmain
    Created on : Mar 2, 2016, 2:08:49 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Organizations</title>    
</head>    
<body class="body-normal">
    <jsp:include page="adminheader.jsp"/>
    <div class="content-area">
        <div class="content-area_header">
            <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="allorganizations.jsp"> Organizations</a>  > </div>
            <div class="header_title fleft"> Organization Name </div>
            <div class="CTA_Button Button--Delete fright">Delete Organization</div>
        </div>
        <div class="inputSection col1of4">
            <div class="input_Label">Is this an organization or a group?</div>
            <select class="input_Field"><option class="input_Field">Dropdown</option></select>
            <div class="CTA_Button Button--Gray fleft pushUp_10">Update</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Email Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Email Blocks </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> External Value </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Block Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> None</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Block</div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Block Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> MINDBODY</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Block</div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Block Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> None</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Block</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Block</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Image Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Print Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Marketing Program Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Recurring Email Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
    </div>
  
</body>