<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : Sandeep kumar
    Edited By  : Satyajit Roy
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" href="css/popup.css"/>
    <script src="js/angular.min.js" type="text/javascript"></script>    
    <script src="js/bootstrap.min.js"></script>
    <script src="js/form.js"></script>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/emaillist.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="js/popup.js" type="text/javascript"></script> 
    <title>BrndBot - Email List</title>  
<!--      <script>
       
        
        
        var selected_draft="";
        var count=0;
        function selcheckbox(id){ 
              
//            alert(id+"--selected");
            content='<input type="checkbox" id="'+'draftId'+id+'" checked="true" hidden="">';
//            alert(content);
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                count-=1;
                $("#"+id).html(content);
                selected_draft = selected_draft.replace(id + ",", "");
            }
            else
            { 
                count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
                selected_draft = id+ "," + selected_draft;
              
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {
                $("#drftEmailDelete").show();
            }
            if(count==0)
            {
                $("#drftEmailDelete").hide();
            }
        }
        
        
        
        
        function emailDraftsController($http, $scope){
            
        $scope.deletedrafts = function (type) {
            
        var message;
        var requestBody;
        var responseMessage;
        if(selected_draft !==""){
           
        
        if (type == "deleteMultiple") {
            message = "Are you sure you want to delete these Draft(s)?";
//            alert("draft del ="+selected_draft);
            requestBody = {"type": "deleteSelected",
                "draft_ids": selected_draft, "entity_type": "null"};
            responseMessage = "Selected Drafts were deleted successfully";
//            alert("..."+requestBody.draft_ids);
        } else if (type == "delete") {
            message = "Are you sure you want to delete this Draft?";
            requestBody = {"type": "delete",
                            "draft_ids": selected_draft};
            responseMessage = "Selected Drafts were deleted successfully";
        }

        
        if (confirm(message)) {
            $http({
                method: 'POST',
                url:'deleteEmailDrafts.do',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== "") {
                    alert(responseMessage);
                    window.open(getHost() + 'emaildrafts.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("Some Error occured! Try after some time.");
            });
        }
       }else{
            alert("No Drafts Selected!");
       }
    };
            
        $scope.unseldrafts = function (id) {

          var myarray = selected_draft.split(',');
          for(var i = 0; i < myarray.length; i++)
          {
               content='<input type="checkbox" id="'+'draftId'+myarray[i]+'" checked="false" hidden="">';
//                   alert(id);
               var htm=$("#"+myarray[i]).html();
               if(htm.contains('class="check-icon"')){
                   count-=1;
                   $("#"+myarray[i]).html(content);
               }
               myarray[i] = myarray[i].replace(id + ",", "");
               $("#"+myarray[i]).removeClass('selection-icon-selected');
               $("#"+myarray[i]).addClass('selection-icon');
               $("#drftEmailDelete").hide();
                 selected_draft="";
          }
         };
            
        $scope.getAllDrafts = function(){
            
            $http({
                method : 'GET',
                url : getHost() + 'displayAllEmailDrafts.do'
            }).success(function(data, status) {
                if (data.nodrafts == "yes"){
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                }else {
                    $scope.emaildrafts = data.emaildrafts;
                }
//                     alert(JSON.stringify(data));
            }).error(function(data, status) {
                alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
            });
        };

        $scope.editDrafts = function(draft_id, category_id,email_subject, sub_category_id, sub_category_name){

            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id, 
                        "sub_category_id": sub_category_id, 
                        "sub_category_name": sub_category_name};

            $http({
                method : 'POST',
                url : getHost() + 'saveEmailDraftSessionValues.do',
                headers: {'Content-Type':'application/json'},
                data: JSON.stringify(draftdetails)
            }).success(function(data, status) {
                if (data == "false"){
                    alert("There was a problem while saving the draft! Please try again later.")
                }else {
                    window.open(getHost() + 'emaileditor.jsp?id='+null+'&draftid='+draft_id, "_self");                    
                }

            }).error(function(data, status) {
                alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
            });
        };
    };
    </script>-->
</head>    
<body ng-app class="claro1" >
    <div class="content-main" ng-controller="EmailListController"  >
        <!--SideNav-->
        <%@include file="navbarv2.jsp" %>
       <jsp:include page="createemaillist.jsp"/> 
        <!--Top Nav-->   
        
        <div class="top-nav" ng-controller="EmailListController">
            <div class="page-title-bar col-1of1"> 
                <!--<div class="exit-button-detail"></div>-->
                <div class="page-title-regular page-title-font">Your Email Hub</div>
                <div class="page-cta-container">
                <a href="">
                 <div class="delete-button md-button button fleft"> Delete Email List</div>
                </a>
                <a href="">
                    <div id="addemlstbtn" class="add-action-button md-button button-text-1" ng-click="addemaillist()"> Add Email List</div>
                </a>
                </div>
            </div>
            <div class="page-subnav-bar-with-dropdown"> 
                <div class="subnav-dropdown pushright select-style" >
                    <img src="images/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </img>
                <select class="hub-dropdown-text emlsocdrpdwn">
                  <option class="hub-dropdown-text" value="Email">Email</option>
                  <option class="hub-dropdown-text" value="Social">Social</option>
                </select>
                     <!--<span >Email</span>-->
                
                </div>
                <div class="top-subnav-tabs-container">
                   <ul class="top-subnav-nav-elements">
                       <li class="top-subnav-links" id="emldrftab"> <a href="" class="h3">Email Drafts</a></li>
                       <li class="top-subnav-links" id="emlhistab"> <a class="h3" href="emailhistory.jsp">Email History and Analytics</a></li>
<!--                        <li class="top-subnav-links"> <a class="h3">Scheduled Emails</a></li>-->
                        <li class="top-subnav-link-active" id="emllistab"> <a href="" class="h3-active-subnav">Email Lists</a></li>
                        <li class="top-subnav-links" id="emlsettab"> <a class="h3">Email Settings</a></li>
                    </ul>
                </div>
                
            </div>
            
        </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
            <div class="page-content-container email-list-contact-page" id="emaillistsdiv" ng-controller="EmailListController">
             <jsp:include page="createemaillist.jsp"/> 
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title pushUp h2">Your Email Lists</div>
                    </div>
                    <!--List Starts Here-->
                    <div ng-init="showEmailListWithContacts()">
                        
                        <ul class="main-container fleft">
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailLists">
                             <div class="selection-container col-5p"> 
                                 <div class="selection-icon" id="qnique-{{email.emailListName}}" onclick="selemlcheckbox(this.id)"><input type="checkbox" id="{{email.id}}" value="{{email.emailListName}}" name="entityname" hidden></input></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 ">{{email.emailListName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Created on {{email.listAddedDate}}</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.noofcontants}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number of Contacts</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <a href="emaillistsdetails.jsp?list_name={{email.emailListName}}&type=user">
                                        <div class="small-button slat-button" >Manage List</div>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailListsMindbody">
                             <div class="selection-container col-5p hint--left emailconnectalign" data-hint="EmailConnect">
                                 <img src="images/Icons/emailConnect.svg" class="emailConnect-icon" style="cursor:pointer;"> </img>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <a href="emaillistsdetails.jsp?list_name={{email.emailListName}}&type=mindbody">
                                        <div class="slat-title email-list-slat-title col-1of1 ">{{email.emailListName}}</div>
                                        <div class="action-list-slat-description col-1of1 sh3"> </div>
                                    </a>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.noofcontants}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number of Contacts</div>
                                </div>             
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                </div>
                            </div>
                        </li>  
                    </ul>
                   </div>
                </div>
            </div>            
        </div>
            <div class="page-content-container email-list-contact-page email-draft-page" id="emaildraftsdiv" >
                <div ng-controller="emailDraftsController">
                <div class="fleft content" ng-init="getAllDrafts()">
                    <div class="page-content-title h2">Your Email Drafts</div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                            <div class="col-7of10 slat-unit fleft">
                                <div>
                                    <div ng-show="emaildraftnumber == '0'">{{emaildraftsstatus}}</div>
                                </div>
                            </div>
                        <li class="slat-container fleft selfclear"  ng-repeat="drafts in emaildrafts">
                            <div class="selection-container col-5p" id="deleteids"> 
                                <div class="selection-icon" id="{{drafts.id}}" onclick="selcheckbox(this.id)">
                                    <input type="checkbox" id="{{drafts.id}}" value="{{drafts.id}}" name="draftname" style="display:none;"></input>
                                </div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of1 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">{{drafts.emailsubject}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">This is an email draft| Last edited on {{drafts.editdate | date: "MMM dd yyyy"}}</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <a>
                                        <span class="small-button slat-button detail-button-font" ng-click="editDrafts(drafts.id, drafts.categoryid, drafts.emailsubject, drafts.subcategoryid, drafts.subcategoryname)">View and Edit Draft</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                     
                    </ul>
                </div>
            </div> 
            </div>
        </div>
      
    </div>
  
        <!--CTA Bar-->
<!--        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
                REMOVE BUTTON HERE
               <div class="remove-action-detail md-button button-text-1">Remove Selected Action(s)</div>
            </div>
        </div>-->
    </body>
</html>