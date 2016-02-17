<%-- 
    Document   : organization
    Created on : May 21, 2015, 9:11:28 PM
    Author     : intbit
--%>
<%@page import="com.controller.SqlMethods"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    SqlMethods sqlmethods = new SqlMethods();
    String checked = "false";
    Integer user_id = 0;
    String company = "";
%>

<%
    try {
        sqlmethods.session = request.getSession(true);
        checked = (String) sqlmethods.session.getAttribute("EmailID");
        if (checked == null || checked == "false") {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    } catch (Exception e) {
        out.println(sqlmethods.error);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js"></script>
        <script type="text/javascript" src="js/form.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
       
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/main1.css" rel="stylesheet" type="text/css"/>
        <title>organization</title>
            <script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
   
            <style>
                        .slt {
                           position:relative;
                           display:inline-block;
                           text-align:left;
                           line-height:30px;
                           clear:both;
                           font-weight:600;
                           color: #000000;
                           background-color: #ccc;
                           border:1px solid #f6f6f6;
                           border-radius:10px;
                           width:300px;
			}
                        option.optn{
                            background-color:#d4d4d4;
                            position: absolute;
                            top:50px;
                        }
                        /*
                option {
                        color: #000;
                        border-color: #000000;
                        border: 5px;
                        }
                      option:first-child, option.first {
                             color: #000;
                        border-color: #000000;
                        border: 5px;
                }*/
                
/*                    .slt option {
                             position:absolute;
				top:35px;
				left:0;
				width:188px;
				border:1px solid #ccc;
                                border-radius:70px;
				overflow:hidden;
				background:#f6f6f6;
				padding-top:2px;
				display:block;
                             }
                             */
                   
                
            </style>
            
        <jsp:include page="basejsp.jsp" />            
    </head>
    <body>
        <div class="container" ng-app="myapp">
            <div class="row">

                <div class="span6"></div>

            </div>
            <div id="contentdiv" class="row">
                <div class="col-md-4 col-md-offset-4">
                    <p id="comment1" class="MH2">Howdy!</p>
                    <p id="comment1" class="MH2">We're BrndBot, its nice to meet you.</p><br><br>
                </div>
                <form class="form-horizontal" ng-model="organizations" ng-controller="MyController">

                    <div class="group2" style="top:-25px;position:relative;">
                        <div class="col-md-3 col-md-offset-4">
                           <input id="inputcompanyname" name="inputcompanyname" class="form-control simplebox1" type="text" required/>
                             <label>YOUR COMPANY NAME</label><br>
                        </div>
                    </div>

                    <div class="group2" >
                        <p id="comment1"  class="col-md-4 col-md-offset-4 MH2">Please select an organization:</p>
                        <p id="text-left-1" class="col-md-4 col-md-offset-4 FL2">If you do not have an organization, choose "No Organization".</p><br>
                        <div class="col-md-3 col-md-offset-4"><br>
                            <select  id="organizationdropdown" name="organizationdropdown">
                                <option value="0">---SELECT---</option>
                                <option class="optn" ng-repeat ="org in organizations.org_name" value="{{org.id}}">{{org.organization_name}}</option>
                            </select>
                        </div>
                    </div>

                    <div  class="form-group">
                        <div class="col-sm-offset-4 col-md-5">
                            <button id="organisationbutton" type="submit"  class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="createOrganization()">CONTINUE</button><br><br><br>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    <script src="js/classie.js"></script>
    <script src="js/selectFx.js"></script>
    <script>
            (function() {
                    [].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
                            new SelectFx(el);
                    } );
            })();
    </script>
                
    <style>
                       
                      
/*                            #opt:hover
			{
				color: #000000;
				background:#7ab5d3;
                                
			}	 
                        
                        div.selectBox
			{
				position:relative;
				display:inline-block;
				cursor:default;
				text-align:left;
				line-height:30px;
				clear:both;
                                font-weight:600;
				color: #000000;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
                               background-color: #C6C6C6;
                                border:transparent;
			}
                        .selectBox{
                                position:relative;
				display:inline-block;
				cursor:default;
				text-align:left;
				line-height:30px;
				clear:both;
                                font-weight:600;
				color: #000000;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
                                background-color: #C6C6C6;
                                border:transparent;
                                
                        }
			span.selected
			{
				width:167px;
				text-indent:10px;
				border:0px solid #ccc;
                                border-top-left-radius: 5px;
                                border-bottom-left-radius:  5px;
				border-right:none;
				background-color: #C6C6C6;
				overflow:hidden;
			}
			span.selectArrow
			{
				width:20px;
				text-align:center;
				font-size:22px;
                                border-top-right-radius:5px;
                                border-bottom-right-radius:5px;
				-webkit-user-select: none;
				-khtml-user-select: none;
				-moz-user-select: none;
				-o-user-select: none;
				user-select: none;
				background-image: url('images/dropdown.png');
                                background-repeat: space;
			}
			
			span.selectArrow,span.selected
			{
				position:relative;
				float:left;
				height:30px;
				z-index:1;
			}
			
			div.selectOptions
			{
				position:absolute;
				top:35px;
				left:0;
				width:188px;
				border:1px solid #ccc;
                                border-radius:7px;
				overflow:hidden;
				background:#f6f6f6;
				padding-top:2px;
				display:none;
			}
				
			span.selectOption
			{   
                             
				display:block;
				width:100%;
				line-height:20px;
				padding:5px 10%;
                        }
			
			span.selectOption:hover
			{
				color: #000000;
				background:#7ab5d3;
                                
			}	
                            */
                </style>     
           
     </body>
</html>
