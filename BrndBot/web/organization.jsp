<%-- 
    Document   : organization
    Created on : May 21, 2015, 9:11:28 PM
    Author     : intbit
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js"></script>
        <script type="text/javascript" src="js/form.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link href="css/main1.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
            <div class="container" ng-app="myapp">
            <div class="row">
               
                <div class="span6">
                    <a href="UserRegistration.jsp">go back</a>
                    <a href="services.html" class="pull-right">next</a>
                </div>
                
            </div>
            <div id="contentdiv" class="row">
                <div class="col-md-4 col-md-offset-4">
                    <p id="comment1">Howdy!</p>
                    <p id="comment1">We?re BrndBot, its nice to meet you.</p><br><br>
                </div>
                <form class="form-horizontal" ng-model="organizations" ng-controller="MyController">
                     <div class="group">
                            
                             <div class="col-md-3 col-md-offset-4">
                                 <input id="inputcompanyname" class="form-control simplebox" type="text" required ng-model="organizations.company">
                                        <label>YOUR COMPANY NAME</label><br><br>
                            </div>
                        </div>
                    <div class="form-group">
                        <p id="comment3"  class="col-md-4 col-md-offset-4">Please select an organization:</p><br><br>
                            <div class="col-md-3 col-md-offset-4">
                                <select id="organizationdropdown" class="form-control" ng-model="organizations.org">
                                          <option ng-repeat ="org in organizations.org_name" value="{{org.id}}">{{org.organization_name}} </option>
                                </select>
                            </div>
                        </div>
                    <div  class="form-group">
                               <div class="col-sm-offset-4 col-md-5">
                                   <button id="organisationbutton" type="submit"  class="btn btn-info" ng-click="createOrganization()">CONTINUE</button><br><br><br>
                               </div>
                        </div>
                </form>
            </div>
        </div>

    </body>
</html>
