<%-- 
    Document   : selectpromotingcategory
    Created on : Jul 20, 2015, 12:35:19 PM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>BrndBot - Content Selection</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <script src="js/angular.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
       
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/mindbodyutility.js" type="text/javascript"></script>
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <style>
            #picktheme{display: none;}
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
                font-size:20px;
                position: relative;
                left:-15px;
                padding: 7px;
            }
            .btn-info{
                vertical-align: central;
                background-color: #E65C00;
                height:30px;
                text-align:center;
                width: 120px;
                border-color: #E65C00;
                border-radius: 5px; 
                display:block;

            }
            .btn:hover{
                background-color:  #B24700;
            }
            .btn-info:hover,.btn-info:focus{
                background-color: #B24700;
                border-color: #E65C00;
            }

            table{
                position: relative;
                top: 150px;
            }
            th{
                font-size: 18px;
                padding: 5px;
                height: 90px;
                background-color: #00CC99;
            }

            td{
                padding: 10px;
            }
            .mindbodydatadiv{
                position: relative;
                padding-bottom: 15px;
                top:  90px;
            }

            /*            .datafromindbody li{
                            display: inline-table;
                            padding-left: 5px;
                            font-size: 18px;
            */
            .datahead{
                position: relative;
                top: 14%;
                left:0px;
                height: 50px;
                bottom: 9%;
            }
            
            .datafib{
                position: relative;
                margin-top:9%;
                left:43px;
            }
            .datafromindbody li{
                vertical-align:middle; 
                height: 2%;
                display: table-cell;
                position: relative;
                padding-top: 1%;
                padding-bottom: 1%;
                width: 739px;

            }
            .dataheaderfromindbody li{
                vertical-align:middle; 
                height: 6%;
                display: table-cell;
                position: relative;
                padding-top: 3%;
                padding-bottom: 3%;
                width: 739px;

            }
            #paginationbar{
                position: relative;
                margin-top: 100px;
                margin-left: 70px;    
            }


            .mindbodyOneRowData:hover {
                background-color: #00CC99;
                color: whitesmoke;
                cursor: pointer;
            }
            .mindbodyOneRowData{
                position: relative;
                width: 800px;
                left:0px;
                font-family: "proxima-nova",sans-serif;
                font-weight: 600;
/*                color: #95969A;*/
                color: #2d4355;
                font-style: normal;
                text-align: left;
                line-height: 25.9px;
                letter-spacing: 0em;
            }
            .mindbodyHeaderData{
                position: relative;
                width: 800px;
                left:0px;
                font-family: "proxima-nova",sans-serif;
                font-weight: 600;
                color: #95969A;
                font-style: normal;
                text-align: left;
                line-height: 25.9px;
                letter-spacing: 0em;
            }

        </style>
        <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
        %>

        <%
            String title = "";
            try {
                sql_methods.session = request.getSession(true);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                sql_methods.session.setAttribute("category_id", category_id);

//                if (sub_category_name.equalsIgnoreCase("promote todays class")) {
//                    title = " todays class";
//                } else if (sub_category_name.equalsIgnoreCase("promote work shop")) {
//                    title = "work shop";
//                }
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                sql_methods.closeConnection();
            }
        %>
        <script>

                    angular.module("myapp", [])
                    .controller("controllerGetMindBody", function($scope, $http) {
                         $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","300px").css("left","560px");
                          $("#continuebutton").hide();
                            $scope.showData = function(){

                            $scope.curPage = 0;
                                    $scope.pageSize = 4;
                                    $http({
                                    method : 'GET',
                                    url : 'MindBodyDataServlet'
                                    }).success(function(data, status, headers, config) {
                                        var minddata= JSON.stringify(data.mindbody_data);
                                        if(minddata === undefined){
                                        $('#loadingGif').remove();
                                        $('#nodata').show();
                                        $('#nodata').css("margin-left","180px");
                                        }
                                        $scope.datalists = data;
//                                    alert(JSON.stringify(data));
                                    
                                    $("#picktheme").css("overflow-y", "scroll");
                                    if (data.mindbody_data.length == 0){
                            $("#continuebutton").hide();
                            } else {
                            $("#continuebutton").show();
                            $("#picktheme").css("display","block");
                            }
                            if (data === error){
                            alert(data);
                            }
                             $('#loadingGif').remove();
                            }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                    });
                    var selected_id;
                    function select_category_details(id){

                    //  background-color: #00CC99;
                    // color: whitesmoke;
                    $(".mindbodyOneRowData").css("background-color", "#ffffff");
                            $(".mindbodyOneRowData").css("color", "#2d4355");
                            $("#" + id).css("background-color", "#00CC99");
                            $("#" + id).css("color", "whitesmoke");
                            selected_id = id;
                            $("#continuebutton").prop("disabled", false);
                            //                    var configuration = global_host_address + "socialeditor.jsp" + "?id=" +id;
                            //                    window.open(configuration, "_self")
                    }

            function selected_category(){

            var configuration = global_host_address + "selectpromotemedia.jsp" + "?id=" + selected_id;
                    window.open(configuration, "_self");
            }

        </script>
        <jsp:include page="basejsp.jsp" />

    </head>
    <body ng-app = "myapp">

        <div class="row" ng-controller="controllerGetMindBody">
            <jsp:include page="mainmenu.html"/>
            <!--/end left column-->

            <div class="col-md-11 col-md-offset-1">

                <div class="col-md-6 col-md-offset-1 datahead">
                    
                    <p id="text3" class="MH2">{{datalists.title}}</p>
                    <input style="position:relative;bottom:9.0em;left:55em;" type="button" id="continuebutton" class="button button--moema button--text-thick button--text-upper button--size-s" onclick="selected_category()" value="CONTINUE" disabled="true">
                </div>  
               
                <div class="col-md-10 col-md-offset-0 datafib">
                    <ul class="dataheaderfromindbody">
                        <div class="mindbodyHeaderData LE2" >
                            <li style="width: 400px;left:20px;">{{datalists.column_header[0]}}</li>
                            <li style="width: 250px">{{datalists.column_header[1]}}</li>
                            <li style="width: 100px">{{datalists.column_header[2]}}</li></div>
                    </ul>
                    <p id="nodata" hidden="true" class="MH2">No Mindbody data in selected Category <br>please select some other Category.</p>
                    <hr id="dividerline" style="width:850px;position:absolute;left:60px;top:32px;">

                    <div style="position:fixed;height:45%;margin-top: -1.8em;" class="tab-pane active" id="picktheme" ng-init="showData()">

                        <ul class="datafromindbody" ng-repeat="jsonclass in datalists.mindbody_data">
                            <div id="{{jsonclass.id}}" class="mindbodyOneRowData LE2" onclick="select_category_details('{{jsonclass.id}}')" >

                                <li style="width: 400px;left:20px;">{{jsonclass.column1}}</li>
                                <li style="width: 250px">{{jsonclass.column2}}</li>
                                <li style="width: 100px">{{jsonclass.column3}}</li></div>
                        </ul>

                    </div>

                </div>

            </div>      
        </div> 

    </body>
</html>
