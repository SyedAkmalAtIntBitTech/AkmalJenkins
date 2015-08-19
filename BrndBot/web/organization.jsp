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
                <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
                <link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/cs-select.css" />
		<link rel="stylesheet" type="text/css" href="css/cs-skin-border.css" />
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link href="css/main1.css" rel="stylesheet" type="text/css"/>
        <title>organization</title>
    </head>
    <body>
        <div class="container" ng-app="myapp">
            <div class="row">

                <div class="span6">
                </div>

            </div>
            <div id="contentdiv" class="row">
                <div class="col-md-4 col-md-offset-4">
                    <p id="comment1">Howdy!</p>
                    <p id="comment1">We're BrndBot, its nice to meet you.</p><br><br>
                </div>
                <form class="form-horizontal" ng-model="organizations" ng-controller="MyController">

                    <div class="group2">
                        <div class="col-md-3 col-md-offset-4">
                             
                            <input id="inputcompanyname" class="form-control simplebox1" type="text" required ng-model="organizations.company">
                             <label>YOUR COMPANY NAME</label><br>
                        </div>
                    </div>

                    <div class="form-group" >
                        <p id="comment1"  class="col-md-4 col-md-offset-4">Please select an organization:</p><br>
                        <p id="text-left-1" class="col-md-4 col-md-offset-4">If you do not have an organization, choose "No Organization".</p><br>
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
    <script src="js/classie.js"></script>
    <script src="js/selectFx.js"></script>
    <script>
			(function() {
				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx(el);
				} );
			})();
		</script>
           
</html>
