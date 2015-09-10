<%-- 
    Document   : personality
    Created on : Jun 4, 2015, 2:21:41 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>personality</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> 
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script src="js/configurations.js"></script>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <script>
                function showText(brandid,image_name){
                             $("#hiddenform").val(brandid);
                             $("#image1").attr('src', '/BrndBot/DownloadImage?image_type=BRAND_PERSONALITY&image_name='+image_name);
                 }    
                 function sendbrandID(){
                    var brandID = $("#hiddenform").val() ;
                    if(brandID == ""){
                        alert("Please select a personality");
                    }
                    else{
                    var path = global_host_address + 'SubbrandPersonality?brndID=' + brandID +'&type=insert';
                    window.open(path,"_self");
                    }
                }     
  
        </script>    

        <script>

            angular.module("myapp", [])
                .controller("MyController", function($scope,$http) {
                                $scope.master = {};
                           $http({
                                    method : 'GET',
                                    url : 'GetBrandPersonality'
                            }).success(function(data, status, headers, config) {
                                    $scope.First = data.first;
                                    if (data === error){
                                        alert(data);
                                    }
                            }).error(function(data, status, headers, config) {
                                    alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                    });
                
        </script>
         <link rel="stylesheet" href="css/main1.css">
        <jsp:include page="basejsp.jsp" />
        
    </head>
    <body ng-app="myapp">
        <div class="container">

            <div id="contemporarycontainer">
                 <div class="span5 col-md-offset-1 ">
                     <p id="comment1" class="MH2">Choose a brand personality</p>
                <p class="commentlin BC2">Color is no object! Please choose the brand personality you think<br>
most represents your company and ignore the colors for now.</p>
                 </div>
                    <div class="row" id="buttonlength">
                        <div class="span7">
                            <div class="col-md-7 pull-right pull-up">

                                    <div class="item col-md-offset-2"><p >preview</p>
                                        <img id="image1" class="img-responsive" src="" width="700" height="400"><br>

                                    </div>
                            </div>
                     </ div>
                        <form class="form-horizontal"  ng-controller="MyController" ng-model="brands">
                            <input id="hiddenform" name="hiddenform" type="hidden" ng-model="brands.brandName"><br>
                            <div ng-repeat="first in First" class="span5 col-md-offset-1 ">
                                <button type="button"  id="contemporary1" class="btn btn-default btn-lg col-md-3" onclick="showText('{{first.id}}','{{first.image_name}}')">{{first.brand_name}}</button><br><br><br>
                            </div>  
                            
                            <div class="span4 col-md-offset-1">
                                <button type="button" class="button button--moema button--text-thick button--text-upper button--size-s" onclick="sendbrandID()">CONTINUE</button>
                            </div>
                        </form>
                     
                </div>

                    </div>
            </div>  

    </body>

</html>
