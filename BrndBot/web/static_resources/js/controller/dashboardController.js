dashboardFlowApp.controller("dashboardController", ['$scope','$window', '$location', 'categoryFactory', 'subCategoryFactory','externalContentFactory','redirectFactory','SharedService', function ($scope, $window, $location, categoryFactory, subCategoryFactory,externalContentFactory,redirectFactory,SharedService) {
        $scope.emailChannelId = 3;
        $scope.printChannelId = 2;
        $scope.imageChannelId = 1;
        $scope.forward = "";
        $scope.categoryId = "";
        $scope.lookupId="";
        $scope.externalSourceName="";
        $scope.subCategoryId="";
        $scope.mindbodyid="";
        $scope.emailsubject="";
        $scope.emailSubjectError="";
        $scope.sharedData="";
        $scope.redirect = function (redirect, categoryId, mindbody,lookupId, mindbodyid)
        {            
            $scope.lookupId=lookupId;
            if (mindbody === '')
            {
                $scope.categoryId = categoryId;
            }
            if (mindbody === 'Mindbody')
            {
                $scope.externalSourceName='mindbody';
                redirect =$scope.forwardone ;
                $scope.lookupId=lookupId;
                $scope.subCategoryId = categoryId;
            }
            if (mindbody === 'nonmindbody')
            {
                if (redirect==='emailexternalsource')
                {
                    redirect = $scope.forwardtwo;
                    $scope.subCategoryId = categoryId;
                }
                $scope.categoryId = categoryId;
            } 
            if (mindbodyid !== '')
            {
                $scope.mindbodyid = mindbodyid;
            } 
            $location.path("/" + redirect);
        };
        $scope.redirectToEmailFlow = function(forwardone)
        {
            redirectFactory.redirectFlowTo(forwardone);
            $window.location = getHost()+"user/"+forwardone;
            var emailsubject=$scope.emailsubject;
            if(emailsubject==='')
            {
                $scope.emailSubjectError="Email Subject Required!";
            }
        };
        $scope.getCategories = function (forwardone)
        {
            categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
                $scope.pageName = "emailcategory";
                $scope.header = "Select Category";
                $scope.forwardone = forwardone;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getSubCategories = function (forwardone,forwardtwo)
        {
            $scope.pageName = "emailsubcategory";
            subCategoryFactory.allSubCategoriesGet($scope.categoryId).then(function (data) {
                $scope.pageName = "emailsubcategory";
                $scope.header = "Select Subcategory";
                $scope.forwardone = forwardone;
                $scope.forwardtwo = forwardtwo;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getActive = function (lookupId)
        {
            externalContentFactory.activatedGet(lookupId).then(function (data) {
                var minddata= JSON.stringify(data.d.details);
                if(minddata === "[true]"){
                    externalContentFactory.listDataGet(lookupId).then(function (data) {
                        var parseData=JSON.parse(data.d.details);
                        $scope.mindbodylist=parseData.mindbody_data;
                    });
                }
                else
                {
                    
                }
            });
        };
        $scope.redirectFlow= function(pageName)
        { 
            redirectFactory.redirectFlowTo(pageName);        
        };


        $scope.redirectToMarketingProgram= function(pageName)
        {
            redirectFactory.redirectFlowTo(pageName);    
        };        
    }]);

