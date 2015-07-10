<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="../js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/angular.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="../css/main1.css" rel="stylesheet" type="text/css"/>
</head>
<script>
    function loginController($scope, $http) {
    $scope.user = {};

    $scope.checkUser = function () {
        $http({
            method: 'POST',
            url: getHost() + 'ServletLogin',
            headers: {'Content-Type': 'application/json'},
            data: $scope.user
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                window.open(getHost() + 'admin/index.jsp', "_self");
            } else if (data === "false") {
                alert("incorrect username or password");
                window.open(getHost() + 'admin/login.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
            .error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                alert("request not succesful");
            });

    };

}
</script>
<body ng-app>
  
<div class="container">
    <div class="jumbotron" style="margin-top: 40px;">

      <form class="form-horizontal" id="signform" ng-controller="loginController" ng-submit="checkUser()" >
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">                            
                        <p class="text-left">Login</p>
                    </div>
                </div>

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">                            
                        <input id="inputemail1" name="inputemail1" class="form-control simplebox" type="text" required ng-model="user.emailid">
                        <label>USER NAME</label><br>
                    </div>
                </div>

                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <input id="inputpassword" class="form-control simplebox" type="password" required ng-model="user.password">
                        <label>PASSWORD</label><br>
                    </div>
                </div>

                <div  class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button type="submit"  class="btn btn-info">Login</button><br><br>

                    </div>
                </div>
            </form>
  </div>

  <div class="row">
    <div class="clearfix visible-lg"></div>
  </div>
</div>

</body>
</html>
